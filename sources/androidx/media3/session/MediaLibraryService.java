package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* loaded from: classes.dex */
public abstract class MediaLibraryService extends MediaSessionService {
    public static final String SERVICE_INTERFACE = "androidx.media3.session.MediaLibraryService";

    @Override // androidx.media3.session.MediaSessionService
    public abstract MediaLibrarySession onGetSession(MediaSession.ControllerInfo controllerInfo);

    /* loaded from: classes.dex */
    public static final class MediaLibrarySession extends MediaSession {
        public static final int LIBRARY_ERROR_REPLICATION_MODE_FATAL = 1;
        public static final int LIBRARY_ERROR_REPLICATION_MODE_NONE = 0;
        public static final int LIBRARY_ERROR_REPLICATION_MODE_NON_FATAL = 2;

        @Override // androidx.media3.session.MediaSession
        /* bridge */ /* synthetic */ MediaSessionImpl createImpl(Context context, String str, Player player, PendingIntent pendingIntent, ImmutableList immutableList, ImmutableList immutableList2, ImmutableList immutableList3, MediaSession.Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z2, int i) {
            return createImpl(context, str, player, pendingIntent, (ImmutableList<CommandButton>) immutableList, (ImmutableList<CommandButton>) immutableList2, (ImmutableList<CommandButton>) immutableList3, callback, bundle, bundle2, bitmapLoader, z, z2, i);
        }

        /* loaded from: classes.dex */
        public interface Callback extends MediaSession.Callback {
            default ListenableFuture<LibraryResult<MediaItem>> onGetLibraryRoot(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, LibraryParams libraryParams) {
                return Futures.immediateFuture(LibraryResult.ofError(-6));
            }

            default ListenableFuture<LibraryResult<MediaItem>> onGetItem(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str) {
                return Futures.immediateFuture(LibraryResult.ofError(-6));
            }

            default ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> onGetChildren(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, LibraryParams libraryParams) {
                return Futures.immediateFuture(LibraryResult.ofError(-6));
            }

            default ListenableFuture<LibraryResult<Void>> onSubscribe(final MediaLibrarySession mediaLibrarySession, final MediaSession.ControllerInfo controllerInfo, final String str, final LibraryParams libraryParams) {
                return Util.transformFutureAsync(onGetItem(mediaLibrarySession, controllerInfo, str), new AsyncFunction() { // from class: androidx.media3.session.MediaLibraryService$MediaLibrarySession$Callback$$ExternalSyntheticLambda0
                    @Override // com.google.common.util.concurrent.AsyncFunction
                    public final ListenableFuture apply(Object obj) {
                        return MediaLibraryService.MediaLibrarySession.Callback.lambda$onSubscribe$0(MediaSession.ControllerInfo.this, mediaLibrarySession, str, libraryParams, (LibraryResult) obj);
                    }
                });
            }

            /* JADX WARN: Multi-variable type inference failed */
            static /* synthetic */ ListenableFuture lambda$onSubscribe$0(MediaSession.ControllerInfo controllerInfo, MediaLibrarySession mediaLibrarySession, String str, LibraryParams libraryParams, LibraryResult libraryResult) throws Exception {
                if (libraryResult.resultCode != 0 || libraryResult.value == 0 || ((MediaItem) libraryResult.value).mediaMetadata.isBrowsable == null || !((MediaItem) libraryResult.value).mediaMetadata.isBrowsable.booleanValue()) {
                    return Futures.immediateFuture(LibraryResult.ofError(libraryResult.resultCode != 0 ? libraryResult.resultCode : -3));
                }
                if (controllerInfo.getControllerVersion() != 0) {
                    mediaLibrarySession.notifyChildrenChanged(controllerInfo, str, Integer.MAX_VALUE, libraryParams);
                }
                return Futures.immediateFuture(LibraryResult.ofVoid());
            }

            default ListenableFuture<LibraryResult<Void>> onUnsubscribe(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str) {
                return Futures.immediateFuture(LibraryResult.ofVoid());
            }

            default ListenableFuture<LibraryResult<Void>> onSearch(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, LibraryParams libraryParams) {
                return Futures.immediateFuture(LibraryResult.ofError(-6));
            }

            default ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> onGetSearchResult(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, LibraryParams libraryParams) {
                return Futures.immediateFuture(LibraryResult.ofError(-6));
            }
        }

        /* loaded from: classes.dex */
        public static final class Builder extends MediaSession.BuilderBase<MediaLibrarySession, Builder, Callback> {
            private int libraryErrorReplicationMode;

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public /* bridge */ /* synthetic */ Builder setCommandButtonsForMediaItems(List list) {
                return setCommandButtonsForMediaItems((List<CommandButton>) list);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public /* bridge */ /* synthetic */ Builder setCustomLayout(List list) {
                return setCustomLayout((List<CommandButton>) list);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public /* bridge */ /* synthetic */ Builder setMediaButtonPreferences(List list) {
                return setMediaButtonPreferences((List<CommandButton>) list);
            }

            public Builder(MediaLibraryService mediaLibraryService, Player player, Callback callback) {
                this((Context) mediaLibraryService, player, callback);
            }

            public Builder(Context context, Player player, Callback callback) {
                super(context, player, callback);
                this.libraryErrorReplicationMode = 1;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setSessionActivity(PendingIntent pendingIntent) {
                return (Builder) super.setSessionActivity(pendingIntent);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setId(String str) {
                return (Builder) super.setId(str);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setExtras(Bundle bundle) {
                return (Builder) super.setExtras(bundle);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setSessionExtras(Bundle bundle) {
                return (Builder) super.setSessionExtras(bundle);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
                return (Builder) super.setBitmapLoader(bitmapLoader);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setCustomLayout(List<CommandButton> list) {
                return (Builder) super.setCustomLayout(list);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setMediaButtonPreferences(List<CommandButton> list) {
                return (Builder) super.setMediaButtonPreferences(list);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
                return (Builder) super.setShowPlayButtonIfPlaybackIsSuppressed(z);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setPeriodicPositionUpdateEnabled(boolean z) {
                return (Builder) super.setPeriodicPositionUpdateEnabled(z);
            }

            public Builder setLibraryErrorReplicationMode(int i) {
                this.libraryErrorReplicationMode = i;
                return this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setCommandButtonsForMediaItems(List<CommandButton> list) {
                return (Builder) super.setCommandButtonsForMediaItems(list);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.session.MediaSession.BuilderBase
            public MediaLibrarySession build() {
                if (this.bitmapLoader == null) {
                    this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader(this.context));
                }
                return new MediaLibrarySession(this.context, this.id, this.player, this.sessionActivity, this.customLayout, this.mediaButtonPreferences, this.commandButtonsForMediaItems, this.callback, this.tokenExtras, this.sessionExtras, (androidx.media3.common.util.BitmapLoader) Assertions.checkNotNull(this.bitmapLoader), this.playIfSuppressed, this.isPeriodicPositionUpdateEnabled, this.libraryErrorReplicationMode);
            }
        }

        MediaLibrarySession(Context context, String str, Player player, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, MediaSession.Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z2, int i) {
            super(context, str, player, pendingIntent, immutableList, immutableList2, immutableList3, callback, bundle, bundle2, bitmapLoader, z, z2, i);
        }

        @Override // androidx.media3.session.MediaSession
        MediaLibrarySessionImpl createImpl(Context context, String str, Player player, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, MediaSession.Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z2, int i) {
            return new MediaLibrarySessionImpl(this, context, str, player, pendingIntent, immutableList, immutableList2, immutableList3, (Callback) callback, bundle, bundle2, bitmapLoader, z, z2, i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media3.session.MediaSession
        public MediaLibrarySessionImpl getImpl() {
            return (MediaLibrarySessionImpl) super.getImpl();
        }

        public ImmutableList<MediaSession.ControllerInfo> getSubscribedControllers(String str) {
            return getImpl().getSubscribedControllers(str);
        }

        public void notifyChildrenChanged(MediaSession.ControllerInfo controllerInfo, String str, int i, LibraryParams libraryParams) {
            Assertions.checkArgument(i >= 0);
            getImpl().notifyChildrenChanged((MediaSession.ControllerInfo) Assertions.checkNotNull(controllerInfo), Assertions.checkNotEmpty(str), i, libraryParams);
        }

        public void notifyChildrenChanged(String str, int i, LibraryParams libraryParams) {
            Assertions.checkArgument(i >= 0);
            getImpl().notifyChildrenChanged(Assertions.checkNotEmpty(str), i, libraryParams);
        }

        public void notifySearchResultChanged(MediaSession.ControllerInfo controllerInfo, String str, int i, LibraryParams libraryParams) {
            Assertions.checkArgument(i >= 0);
            getImpl().notifySearchResultChanged((MediaSession.ControllerInfo) Assertions.checkNotNull(controllerInfo), Assertions.checkNotEmpty(str), i, libraryParams);
        }

        public void clearReplicatedLibraryError() {
            getImpl().clearReplicatedLibraryError();
        }
    }

    /* loaded from: classes.dex */
    public static final class LibraryParams {
        public final Bundle extras;
        public final boolean isOffline;
        public final boolean isRecent;
        public final boolean isSuggested;
        private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(0);
        private static final String FIELD_RECENT = Util.intToStringMaxRadix(1);
        private static final String FIELD_OFFLINE = Util.intToStringMaxRadix(2);
        private static final String FIELD_SUGGESTED = Util.intToStringMaxRadix(3);

        private LibraryParams(Bundle bundle, boolean z, boolean z2, boolean z3) {
            this.extras = new Bundle(bundle);
            this.isRecent = z;
            this.isOffline = z2;
            this.isSuggested = z3;
        }

        /* loaded from: classes.dex */
        public static final class Builder {
            private Bundle extras = Bundle.EMPTY;
            private boolean offline;
            private boolean recent;
            private boolean suggested;

            public Builder setRecent(boolean z) {
                this.recent = z;
                return this;
            }

            public Builder setOffline(boolean z) {
                this.offline = z;
                return this;
            }

            public Builder setSuggested(boolean z) {
                this.suggested = z;
                return this;
            }

            public Builder setExtras(Bundle bundle) {
                this.extras = (Bundle) Assertions.checkNotNull(bundle);
                return this;
            }

            public LibraryParams build() {
                return new LibraryParams(this.extras, this.recent, this.offline, this.suggested);
            }
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putBundle(FIELD_EXTRAS, this.extras);
            bundle.putBoolean(FIELD_RECENT, this.isRecent);
            bundle.putBoolean(FIELD_OFFLINE, this.isOffline);
            bundle.putBoolean(FIELD_SUGGESTED, this.isSuggested);
            return bundle;
        }

        public static LibraryParams fromBundle(Bundle bundle) {
            Bundle bundle2 = bundle.getBundle(FIELD_EXTRAS);
            boolean z = bundle.getBoolean(FIELD_RECENT, false);
            boolean z2 = bundle.getBoolean(FIELD_OFFLINE, false);
            boolean z3 = bundle.getBoolean(FIELD_SUGGESTED, false);
            if (bundle2 == null) {
                bundle2 = Bundle.EMPTY;
            }
            return new LibraryParams(bundle2, z, z2, z3);
        }
    }

    @Override // androidx.media3.session.MediaSessionService, android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            return null;
        }
        if (SERVICE_INTERFACE.equals(intent.getAction())) {
            return getServiceBinder();
        }
        return super.onBind(intent);
    }
}
