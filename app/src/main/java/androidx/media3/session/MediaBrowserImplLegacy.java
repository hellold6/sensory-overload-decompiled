package androidx.media3.session;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaBrowserImplLegacy;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MediaBrowserImplLegacy extends MediaControllerImplLegacy implements MediaBrowser.MediaBrowserImpl {
    private static final String TAG = "MB2ImplLegacy";
    private final HashMap<MediaLibraryService.LibraryParams, MediaBrowserCompat> browserCompats;
    private ImmutableMap<String, CommandButton> commandButtonsForMediaItems;
    private final MediaBrowser instance;
    private final HashMap<String, List<SubscribeCallback>> subscribeCallbacks;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaBrowserImplLegacy(Context context, MediaBrowser mediaBrowser, SessionToken sessionToken, Bundle bundle, Looper looper, androidx.media3.common.util.BitmapLoader bitmapLoader, long j) {
        super(context, mediaBrowser, sessionToken, bundle, looper, bitmapLoader, j);
        this.browserCompats = new HashMap<>();
        this.subscribeCallbacks = new HashMap<>();
        this.instance = mediaBrowser;
        this.commandButtonsForMediaItems = ImmutableMap.of();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.media3.session.MediaControllerImplLegacy
    public MediaBrowser getInstance() {
        return this.instance;
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public void release() {
        Iterator<MediaBrowserCompat> it = this.browserCompats.values().iterator();
        while (it.hasNext()) {
            it.next().disconnect();
        }
        this.browserCompats.clear();
        super.release();
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public SessionCommands getAvailableSessionCommands() {
        if (getBrowserCompat() != null) {
            return super.getAvailableSessionCommands().buildUpon().addAllLibraryCommands().build();
        }
        return super.getAvailableSessionCommands();
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        ImmutableList<String> immutableList = mediaItem.mediaMetadata.supportedCommands;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = this.commandButtonsForMediaItems.get(immutableList.get(i));
            if (commandButton != null && commandButton.sessionCommand != null) {
                builder.add((ImmutableList.Builder) commandButton);
            }
        }
        return builder.build();
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(50000)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        SettableFuture create = SettableFuture.create();
        MediaBrowserCompat browserCompat = getBrowserCompat(libraryParams);
        if (browserCompat != null) {
            create.set(LibraryResult.ofItem(createRootMediaItem(browserCompat), null));
            return create;
        }
        Bundle bundle = libraryParams == null ? new Bundle() : LegacyConversions.convertToRootHints(libraryParams);
        bundle.putInt("androidx.media.utils.MediaBrowserCompat.extras.CUSTOM_BROWSER_ACTION_LIMIT", getInstance().getMaxCommandsForMediaItems());
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(getContext(), getConnectedToken().getComponentName(), new GetLibraryRootCallback(create, libraryParams), bundle);
        this.browserCompats.put(libraryParams, mediaBrowserCompat);
        mediaBrowserCompat.connect();
        return create;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        Bundle createOptionsForSubscription = createOptionsForSubscription(libraryParams);
        SettableFuture create = SettableFuture.create();
        SubscribeCallback subscribeCallback = new SubscribeCallback(str, createOptionsForSubscription, create);
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            list = new ArrayList<>();
            this.subscribeCallbacks.put(str, list);
        }
        list.add(subscribeCallback);
        browserCompat.subscribe(str, createOptionsForSubscription, subscribeCallback);
        return create;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<Void>> unsubscribe(String str) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-3));
        }
        for (int i = 0; i < list.size(); i++) {
            browserCompat.unsubscribe(str, list.get(i));
        }
        return Futures.immediateFuture(LibraryResult.ofVoid());
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        Bundle createOptionsWithPagingInfo = createOptionsWithPagingInfo(libraryParams, i, i2);
        SettableFuture create = SettableFuture.create();
        List<MediaBrowserCompat.MediaItem> childrenFromSubscription = getChildrenFromSubscription(str, i);
        evictChildrenFromSubscription(str);
        if (childrenFromSubscription != null) {
            create.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(childrenFromSubscription), new MediaLibraryService.LibraryParams.Builder().setExtras(createOptionsWithPagingInfo).build()));
            return create;
        }
        browserCompat.subscribe(str, createOptionsWithPagingInfo, new GetChildrenCallback(create, str));
        return create;
    }

    private List<MediaBrowserCompat.MediaItem> getChildrenFromSubscription(String str, int i) {
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).canServeGetChildrenRequest(str, i)) {
                return list.get(i2).receivedChildren;
            }
        }
        return null;
    }

    private void evictChildrenFromSubscription(String str) {
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).receivedChildren != null) {
                list.get(i).receivedChildren = null;
                return;
            }
        }
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<MediaItem>> getItem(String str) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        final SettableFuture create = SettableFuture.create();
        browserCompat.getItem(str, new MediaBrowserCompat.ItemCallback() { // from class: androidx.media3.session.MediaBrowserImplLegacy.1
            @Override // androidx.media3.session.legacy.MediaBrowserCompat.ItemCallback
            public void onItemLoaded(MediaBrowserCompat.MediaItem mediaItem) {
                if (mediaItem != null) {
                    create.set(LibraryResult.ofItem(LegacyConversions.convertToMediaItem(mediaItem), null));
                } else {
                    create.set(LibraryResult.ofError(-3));
                }
            }

            @Override // androidx.media3.session.legacy.MediaBrowserCompat.ItemCallback
            public void onError(String str2) {
                create.set(LibraryResult.ofError(-1));
            }
        });
        return create;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_SEARCH)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        browserCompat.search(str, getExtras(libraryParams), new AnonymousClass2());
        return Futures.immediateFuture(LibraryResult.ofVoid());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.media3.session.MediaBrowserImplLegacy$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 extends MediaBrowserCompat.SearchCallback {
        AnonymousClass2() {
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
        public void onSearchResult(final String str, Bundle bundle, final List<MediaBrowserCompat.MediaItem> list) {
            MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplLegacy$2$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    MediaBrowserImplLegacy.AnonymousClass2.this.m314x87f18d83(str, list, (MediaBrowser.Listener) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onSearchResult$0$androidx-media3-session-MediaBrowserImplLegacy$2, reason: not valid java name */
        public /* synthetic */ void m314x87f18d83(String str, List list, MediaBrowser.Listener listener) {
            listener.onSearchResultChanged(MediaBrowserImplLegacy.this.getInstance(), str, list.size(), null);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
        public void onError(final String str, Bundle bundle) {
            MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplLegacy$2$$ExternalSyntheticLambda1
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    MediaBrowserImplLegacy.AnonymousClass2.this.m313x841a1c17(str, (MediaBrowser.Listener) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onError$1$androidx-media3-session-MediaBrowserImplLegacy$2, reason: not valid java name */
        public /* synthetic */ void m313x841a1c17(String str, MediaBrowser.Listener listener) {
            listener.onSearchResultChanged(MediaBrowserImplLegacy.this.getInstance(), str, 0, null);
        }
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        final SettableFuture create = SettableFuture.create();
        Bundle createOptionsWithPagingInfo = createOptionsWithPagingInfo(libraryParams, i, i2);
        createOptionsWithPagingInfo.putInt("android.media.browse.extra.PAGE", i);
        createOptionsWithPagingInfo.putInt("android.media.browse.extra.PAGE_SIZE", i2);
        browserCompat.search(str, createOptionsWithPagingInfo, new MediaBrowserCompat.SearchCallback() { // from class: androidx.media3.session.MediaBrowserImplLegacy.3
            @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
            public void onSearchResult(String str2, Bundle bundle, List<MediaBrowserCompat.MediaItem> list) {
                create.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(list), null));
            }

            @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
            public void onError(String str2, Bundle bundle) {
                create.set(LibraryResult.ofError(-1));
            }
        });
        return create;
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat != null) {
            final SettableFuture create = SettableFuture.create();
            browserCompat.sendCustomAction(sessionCommand.customAction, bundle, new MediaBrowserCompat.CustomActionCallback() { // from class: androidx.media3.session.MediaBrowserImplLegacy.4
                @Override // androidx.media3.session.legacy.MediaBrowserCompat.CustomActionCallback
                public void onResult(String str, Bundle bundle2, Bundle bundle3) {
                    Bundle bundle4 = new Bundle(bundle2);
                    bundle4.putAll(bundle3);
                    create.set(new SessionResult(0, bundle4));
                }

                @Override // androidx.media3.session.legacy.MediaBrowserCompat.CustomActionCallback
                public void onError(String str, Bundle bundle2, Bundle bundle3) {
                    Bundle bundle4 = new Bundle(bundle2);
                    bundle4.putAll(bundle3);
                    create.set(new SessionResult(-1, bundle4));
                }
            });
            return create;
        }
        return Futures.immediateFuture(new SessionResult(-4));
    }

    private MediaBrowserCompat getBrowserCompat(MediaLibraryService.LibraryParams libraryParams) {
        return this.browserCompats.get(libraryParams);
    }

    private static Bundle createOptionsForSubscription(MediaLibraryService.LibraryParams libraryParams) {
        return libraryParams == null ? new Bundle() : new Bundle(libraryParams.extras);
    }

    private static Bundle createOptionsWithPagingInfo(MediaLibraryService.LibraryParams libraryParams, int i, int i2) {
        Bundle createOptionsForSubscription = createOptionsForSubscription(libraryParams);
        createOptionsForSubscription.putInt("android.media.browse.extra.PAGE", i);
        createOptionsForSubscription.putInt("android.media.browse.extra.PAGE_SIZE", i2);
        return createOptionsForSubscription;
    }

    private static Bundle getExtras(MediaLibraryService.LibraryParams libraryParams) {
        if (libraryParams != null) {
            return libraryParams.extras;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaItem createRootMediaItem(MediaBrowserCompat mediaBrowserCompat) {
        String root = mediaBrowserCompat.getRoot();
        return new MediaItem.Builder().setMediaId(root).setMediaMetadata(new MediaMetadata.Builder().setIsBrowsable(true).setMediaType(20).setIsPlayable(false).setExtras(mediaBrowserCompat.getExtras()).build()).build();
    }

    /* loaded from: classes.dex */
    private class GetLibraryRootCallback extends MediaBrowserCompat.ConnectionCallback {
        private final MediaLibraryService.LibraryParams params;
        private final SettableFuture<LibraryResult<MediaItem>> result;

        public GetLibraryRootCallback(SettableFuture<LibraryResult<MediaItem>> settableFuture, MediaLibraryService.LibraryParams libraryParams) {
            this.result = settableFuture;
            this.params = libraryParams;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnected() {
            ArrayList parcelableArrayList;
            MediaBrowserCompat mediaBrowserCompat = (MediaBrowserCompat) MediaBrowserImplLegacy.this.browserCompats.get(this.params);
            if (mediaBrowserCompat == null) {
                this.result.set(LibraryResult.ofError(-1));
                return;
            }
            Bundle extras = mediaBrowserCompat.getExtras();
            if (extras != null && (parcelableArrayList = extras.getParcelableArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ROOT_LIST")) != null) {
                ImmutableMap.Builder builder = null;
                for (int i = 0; i < parcelableArrayList.size(); i++) {
                    CommandButton convertCustomBrowseActionToCommandButton = LegacyConversions.convertCustomBrowseActionToCommandButton((Bundle) parcelableArrayList.get(i));
                    if (convertCustomBrowseActionToCommandButton != null) {
                        if (builder == null) {
                            builder = new ImmutableMap.Builder().putAll(MediaBrowserImplLegacy.this.commandButtonsForMediaItems);
                        }
                        builder.put(((SessionCommand) Assertions.checkNotNull(convertCustomBrowseActionToCommandButton.sessionCommand)).customAction, convertCustomBrowseActionToCommandButton);
                    }
                }
                if (builder != null) {
                    MediaBrowserImplLegacy.this.commandButtonsForMediaItems = builder.buildKeepingLast();
                }
            }
            this.result.set(LibraryResult.ofItem(MediaBrowserImplLegacy.this.createRootMediaItem(mediaBrowserCompat), LegacyConversions.convertToLibraryParams(MediaBrowserImplLegacy.this.context, extras)));
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionSuspended() {
            onConnectionFailed();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionFailed() {
            this.result.set(LibraryResult.ofError(-3));
            MediaBrowserImplLegacy.this.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SubscribeCallback extends MediaBrowserCompat.SubscriptionCallback {
        private final SettableFuture<LibraryResult<Void>> future;
        private List<MediaBrowserCompat.MediaItem> receivedChildren;
        private final Bundle subscriptionOptions;
        private final String subscriptionParentId;

        public SubscribeCallback(String str, Bundle bundle, SettableFuture<LibraryResult<Void>> settableFuture) {
            this.subscriptionParentId = str;
            this.subscriptionOptions = bundle;
            this.future = settableFuture;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str) {
            onError(this.subscriptionParentId, this.subscriptionOptions);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            onErrorInternal(this.subscriptionParentId, this.subscriptionOptions);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoadedInternal(this.subscriptionParentId, list);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            onChildrenLoadedInternal(this.subscriptionParentId, list);
        }

        private void onErrorInternal(final String str, final Bundle bundle) {
            if (this.future.isDone()) {
                MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda1
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaBrowserImplLegacy.SubscribeCallback.this.m316xeed82158(str, bundle, (MediaBrowser.Listener) obj);
                    }
                });
            }
            this.future.set(LibraryResult.ofError(-1));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onErrorInternal$0$androidx-media3-session-MediaBrowserImplLegacy$SubscribeCallback, reason: not valid java name */
        public /* synthetic */ void m316xeed82158(String str, Bundle bundle, MediaBrowser.Listener listener) {
            listener.onChildrenChanged(MediaBrowserImplLegacy.this.getInstance(), str, Integer.MAX_VALUE, new MediaLibraryService.LibraryParams.Builder().setExtras(bundle).build());
        }

        private void onChildrenLoadedInternal(final String str, final List<MediaBrowserCompat.MediaItem> list) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "SubscribeCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat == null) {
                return;
            }
            if (list == null) {
                onError(this.subscriptionParentId, this.subscriptionOptions);
                return;
            }
            final MediaLibraryService.LibraryParams convertToLibraryParams = LegacyConversions.convertToLibraryParams(MediaBrowserImplLegacy.this.context, browserCompat.getNotifyChildrenChangedOptions());
            this.receivedChildren = list;
            MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    MediaBrowserImplLegacy.SubscribeCallback.this.m315x14c2eea3(str, list, convertToLibraryParams, (MediaBrowser.Listener) obj);
                }
            });
            this.future.set(LibraryResult.ofVoid());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onChildrenLoadedInternal$1$androidx-media3-session-MediaBrowserImplLegacy$SubscribeCallback, reason: not valid java name */
        public /* synthetic */ void m315x14c2eea3(String str, List list, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
            listener.onChildrenChanged(MediaBrowserImplLegacy.this.getInstance(), str, list.size(), libraryParams);
        }

        public boolean canServeGetChildrenRequest(String str, int i) {
            return this.subscriptionParentId.equals(str) && this.receivedChildren != null && i == this.subscriptionOptions.getInt("android.media.browse.extra.PAGE", 0);
        }
    }

    /* loaded from: classes.dex */
    private class GetChildrenCallback extends MediaBrowserCompat.SubscriptionCallback {
        private final SettableFuture<LibraryResult<ImmutableList<MediaItem>>> future;
        private final String parentId;

        public GetChildrenCallback(SettableFuture<LibraryResult<ImmutableList<MediaItem>>> settableFuture, String str) {
            this.future = settableFuture;
            this.parentId = str;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str) {
            onErrorInternal();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            onErrorInternal();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoadedInternal(str, list);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            onChildrenLoadedInternal(str, list);
        }

        private void onErrorInternal() {
            this.future.set(LibraryResult.ofError(-1));
        }

        private void onChildrenLoadedInternal(String str, List<MediaBrowserCompat.MediaItem> list) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "GetChildrenCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat == null) {
                this.future.set(LibraryResult.ofError(-100));
                return;
            }
            browserCompat.unsubscribe(this.parentId, this);
            if (list == null) {
                this.future.set(LibraryResult.ofError(-1));
            } else {
                this.future.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(list), null));
            }
        }
    }
}
