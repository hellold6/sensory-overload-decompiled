package androidx.media3.exoplayer.hls;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ads.AdsLoader;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes.dex */
public final class HlsInterstitialsAdsLoader implements AdsLoader {
    private static final String TAG = "HlsInterstitiaAdsLoader";
    private final Map<Object, AdPlaybackState> activeAdPlaybackStates;
    private final Map<Object, AdsLoader.EventListener> activeEventListeners;
    private final DataSource.Factory dataSourceFactory;
    private final Map<Object, Set<String>> insertedInterstitialIds;
    private boolean isReleased;
    private final List<Listener> listeners;
    private Loader loader;
    private PlayerMessage pendingAssetListResolutionMessage;
    private ExoPlayer player;
    private final PlayerListener playerListener;
    private final Map<Object, AdPlaybackState> resumptionStates;
    private final Map<Object, TreeMap<Long, AssetListData>> unresolvedAssetLists;
    private final Set<Object> unsupportedAdsIds;

    /* loaded from: classes.dex */
    public interface Listener {
        default void onAdCompleted(MediaItem mediaItem, Object obj, int i, int i2) {
        }

        default void onAssetListLoadCompleted(MediaItem mediaItem, Object obj, int i, int i2, AssetList assetList) {
        }

        default void onAssetListLoadFailed(MediaItem mediaItem, Object obj, int i, int i2, IOException iOException, boolean z) {
        }

        default void onAssetListLoadStarted(MediaItem mediaItem, Object obj, int i, int i2) {
        }

        default void onContentTimelineChanged(MediaItem mediaItem, Object obj, Timeline timeline) {
        }

        default void onMetadata(MediaItem mediaItem, Object obj, int i, int i2, Metadata metadata) {
        }

        default void onPrepareCompleted(MediaItem mediaItem, Object obj, int i, int i2) {
        }

        default void onPrepareError(MediaItem mediaItem, Object obj, int i, int i2, IOException iOException) {
        }

        default void onStart(MediaItem mediaItem, Object obj, AdViewProvider adViewProvider) {
        }

        default void onStop(MediaItem mediaItem, Object obj, AdPlaybackState adPlaybackState) {
        }
    }

    /* loaded from: classes.dex */
    public static final class AssetList {
        static final AssetList EMPTY = new AssetList(ImmutableList.of(), ImmutableList.of());
        public final ImmutableList<Asset> assets;
        public final ImmutableList<StringAttribute> stringAttributes;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AssetList(ImmutableList<Asset> immutableList, ImmutableList<StringAttribute> immutableList2) {
            this.assets = immutableList;
            this.stringAttributes = immutableList2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AssetList)) {
                return false;
            }
            AssetList assetList = (AssetList) obj;
            return Objects.equals(this.assets, assetList.assets) && Objects.equals(this.stringAttributes, assetList.stringAttributes);
        }

        public int hashCode() {
            return Objects.hash(this.assets, this.stringAttributes);
        }
    }

    /* loaded from: classes.dex */
    public static final class Asset {
        public final long durationUs;
        public final Uri uri;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Asset(Uri uri, long j) {
            this.uri = uri;
            this.durationUs = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Asset)) {
                return false;
            }
            Asset asset = (Asset) obj;
            return this.durationUs == asset.durationUs && Objects.equals(this.uri, asset.uri);
        }

        public int hashCode() {
            return Objects.hash(this.uri, Long.valueOf(this.durationUs));
        }
    }

    /* loaded from: classes.dex */
    public static final class StringAttribute {
        public final String name;
        public final String value;

        /* JADX INFO: Access modifiers changed from: package-private */
        public StringAttribute(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StringAttribute)) {
                return false;
            }
            StringAttribute stringAttribute = (StringAttribute) obj;
            return Objects.equals(this.name, stringAttribute.name) && Objects.equals(this.value, stringAttribute.value);
        }

        public int hashCode() {
            return Objects.hash(this.name, this.value);
        }
    }

    /* loaded from: classes.dex */
    public static class AdsResumptionState {
        private static final String FIELD_ADS_ID = Util.intToStringMaxRadix(0);
        private static final String FIELD_AD_PLAYBACK_STATE = Util.intToStringMaxRadix(1);
        private final AdPlaybackState adPlaybackState;
        public final String adsId;

        public AdsResumptionState(String str, AdPlaybackState adPlaybackState) {
            Assertions.checkArgument(str.equals(adPlaybackState.adsId));
            this.adsId = str;
            this.adPlaybackState = adPlaybackState;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AdsResumptionState)) {
                return false;
            }
            AdsResumptionState adsResumptionState = (AdsResumptionState) obj;
            return Objects.equals(this.adsId, adsResumptionState.adsId) && Objects.equals(this.adPlaybackState, adsResumptionState.adPlaybackState);
        }

        public int hashCode() {
            return Objects.hash(this.adsId, this.adPlaybackState);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString(FIELD_ADS_ID, this.adsId);
            bundle.putBundle(FIELD_AD_PLAYBACK_STATE, this.adPlaybackState.toBundle());
            return bundle;
        }

        public static AdsResumptionState fromBundle(Bundle bundle) {
            String str = (String) Assertions.checkNotNull(bundle.getString(FIELD_ADS_ID));
            return new AdsResumptionState(str, AdPlaybackState.fromBundle((Bundle) Assertions.checkNotNull(bundle.getBundle(FIELD_AD_PLAYBACK_STATE))).withAdsId(str));
        }
    }

    /* loaded from: classes.dex */
    public static final class AdsMediaSourceFactory implements MediaSource.Factory {
        private final AdViewProvider adViewProvider;
        private final HlsInterstitialsAdsLoader adsLoader;
        private final MediaSource.Factory mediaSourceFactory;

        public AdsMediaSourceFactory(HlsInterstitialsAdsLoader hlsInterstitialsAdsLoader, AdViewProvider adViewProvider, Context context) {
            this(hlsInterstitialsAdsLoader, context, null, adViewProvider);
        }

        public AdsMediaSourceFactory(HlsInterstitialsAdsLoader hlsInterstitialsAdsLoader, AdViewProvider adViewProvider, MediaSource.Factory factory) {
            this(hlsInterstitialsAdsLoader, null, factory, adViewProvider);
        }

        private AdsMediaSourceFactory(HlsInterstitialsAdsLoader hlsInterstitialsAdsLoader, Context context, MediaSource.Factory factory, AdViewProvider adViewProvider) {
            boolean z = false;
            Assertions.checkArgument((context == null && factory == null) ? false : true);
            this.adsLoader = hlsInterstitialsAdsLoader;
            factory = factory == null ? new HlsMediaSource.Factory(new DefaultDataSource.Factory((Context) Assertions.checkNotNull(context))) : factory;
            this.mediaSourceFactory = factory;
            this.adViewProvider = adViewProvider;
            int[] supportedTypes = factory.getSupportedTypes();
            int length = supportedTypes.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (supportedTypes[i] == 2) {
                    z = true;
                    break;
                }
                i++;
            }
            Assertions.checkState(z);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public int[] getSupportedTypes() {
            return new int[]{2};
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public AdsMediaSourceFactory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            this.mediaSourceFactory.setDrmSessionManagerProvider(drmSessionManagerProvider);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public AdsMediaSourceFactory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            this.mediaSourceFactory.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource createMediaSource(MediaItem mediaItem) {
            Assertions.checkNotNull(mediaItem.localConfiguration);
            MediaSource createMediaSource = this.mediaSourceFactory.createMediaSource(mediaItem);
            if (mediaItem.localConfiguration.adsConfiguration == null) {
                return createMediaSource;
            }
            if (!(mediaItem.localConfiguration.adsConfiguration.adsId instanceof String)) {
                throw new IllegalArgumentException("Please use an AdsConfiguration with an adsId of type String when using HlsInterstitialsAdsLoader");
            }
            return new AdsMediaSource(createMediaSource, new DataSpec(mediaItem.localConfiguration.adsConfiguration.adTagUri), Assertions.checkNotNull(mediaItem.localConfiguration.adsConfiguration.adsId), this.mediaSourceFactory, this.adsLoader, this.adViewProvider, false);
        }
    }

    public HlsInterstitialsAdsLoader(Context context) {
        this(new DefaultDataSource.Factory(context));
    }

    public HlsInterstitialsAdsLoader(DataSource.Factory factory) {
        this.dataSourceFactory = factory;
        this.playerListener = new PlayerListener();
        this.activeEventListeners = new HashMap();
        this.activeAdPlaybackStates = new HashMap();
        this.insertedInterstitialIds = new HashMap();
        this.unresolvedAssetLists = new HashMap();
        this.resumptionStates = new HashMap();
        this.listeners = new ArrayList();
        this.unsupportedAdsIds = new HashSet();
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void setPlayer(Player player) {
        boolean z = true;
        Assertions.checkState(!this.isReleased);
        Assertions.checkArgument(player == null || (player instanceof ExoPlayer));
        if (Objects.equals(this.player, player)) {
            return;
        }
        if (this.player != null && !this.activeEventListeners.isEmpty()) {
            this.player.removeListener(this.playerListener);
        }
        if (player != null && !this.activeEventListeners.isEmpty()) {
            z = false;
        }
        Assertions.checkState(z);
        this.player = (ExoPlayer) player;
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void setSupportedContentTypes(int... iArr) {
        for (int i : iArr) {
            if (i == 2) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public ImmutableList<AdsResumptionState> getAdsResumptionStates() {
        String str;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (AdPlaybackState adPlaybackState : this.activeAdPlaybackStates.values()) {
            boolean endsWithLivePostrollPlaceHolder = adPlaybackState.endsWithLivePostrollPlaceHolder();
            if (!endsWithLivePostrollPlaceHolder && (adPlaybackState.adsId instanceof String)) {
                builder.add((ImmutableList.Builder) new AdsResumptionState((String) adPlaybackState.adsId, adPlaybackState.copy()));
            } else {
                if (endsWithLivePostrollPlaceHolder) {
                    str = "getAdsResumptionStates(): ignoring active ad playback state of live stream. adsId=" + adPlaybackState.adsId;
                } else {
                    str = "getAdsResumptionStates(): ignoring active ad playback state when creating resumption states. `adsId` is not of type String: " + Util.castNonNull(adPlaybackState.adsId).getClass();
                }
                Log.i(TAG, str);
            }
        }
        return builder.build();
    }

    public void addAdResumptionState(AdsResumptionState adsResumptionState) {
        addAdResumptionState(adsResumptionState.adsId, adsResumptionState.adPlaybackState);
    }

    public void addAdResumptionState(Object obj, AdPlaybackState adPlaybackState) {
        Assertions.checkArgument(!adPlaybackState.endsWithLivePostrollPlaceHolder());
        if (!this.activeAdPlaybackStates.containsKey(obj)) {
            this.resumptionStates.put(obj, adPlaybackState.copy().withAdsId(obj));
        } else {
            Log.w(TAG, "Attempting to add an ad resumption state for an adsId that is currently active. adsId=" + obj);
        }
    }

    public boolean removeAdResumptionState(Object obj) {
        return this.resumptionStates.remove(obj) != null;
    }

    public void clearAllAdResumptionStates() {
        this.resumptionStates.clear();
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void start(AdsMediaSource adsMediaSource, DataSpec dataSpec, final Object obj, final AdViewProvider adViewProvider, AdsLoader.EventListener eventListener) {
        if (this.isReleased) {
            eventListener.onAdPlaybackState(new AdPlaybackState(obj, new long[0]));
            return;
        }
        if (this.activeAdPlaybackStates.containsKey(obj) || this.unsupportedAdsIds.contains(obj)) {
            throw new IllegalStateException("media item with adsId='" + obj + "' already started. Make sure adsIds are unique within the same playlist.");
        }
        if (this.activeEventListeners.isEmpty()) {
            ((ExoPlayer) Assertions.checkStateNotNull(this.player, "setPlayer(Player) needs to be called")).addListener(this.playerListener);
        }
        this.activeEventListeners.put(obj, eventListener);
        final MediaItem mediaItem = adsMediaSource.getMediaItem();
        if (isHlsMediaItem(mediaItem)) {
            this.insertedInterstitialIds.put(obj, new HashSet());
            this.unresolvedAssetLists.put(obj, new TreeMap<>());
            if ((obj instanceof String) && this.resumptionStates.containsKey(obj)) {
                putAndNotifyAdPlaybackStateUpdate(obj, (AdPlaybackState) Assertions.checkNotNull(this.resumptionStates.remove(obj)));
            } else {
                this.activeAdPlaybackStates.put(obj, AdPlaybackState.NONE);
            }
            notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$$ExternalSyntheticLambda3
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj2) {
                    ((HlsInterstitialsAdsLoader.Listener) obj2).onStart(MediaItem.this, obj, adViewProvider);
                }
            });
            return;
        }
        Log.w(TAG, "Unsupported media item. Playing without ads for adsId=" + obj);
        putAndNotifyAdPlaybackStateUpdate(obj, new AdPlaybackState(obj, new long[0]));
        this.unsupportedAdsIds.add(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x013d  */
    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean handleContentTimelineChanged(final androidx.media3.exoplayer.source.ads.AdsMediaSource r12, androidx.media3.common.Timeline r13) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader.handleContentTimelineChanged(androidx.media3.exoplayer.source.ads.AdsMediaSource, androidx.media3.common.Timeline):boolean");
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void handlePrepareComplete(final AdsMediaSource adsMediaSource, final int i, final int i2) {
        final Object adsId = adsMediaSource.getAdsId();
        if (this.isReleased || this.unsupportedAdsIds.contains(adsId)) {
            return;
        }
        notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$$ExternalSyntheticLambda7
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                HlsInterstitialsAdsLoader.Listener listener = (HlsInterstitialsAdsLoader.Listener) obj;
                listener.onPrepareCompleted(AdsMediaSource.this.getMediaItem(), adsId, i, i2);
            }
        });
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void handlePrepareError(final AdsMediaSource adsMediaSource, final int i, final int i2, final IOException iOException) {
        final Object adsId = adsMediaSource.getAdsId();
        putAndNotifyAdPlaybackStateUpdate(adsId, ((AdPlaybackState) Assertions.checkNotNull(this.activeAdPlaybackStates.get(adsId))).withAdLoadError(i, i2));
        if (this.isReleased || this.unsupportedAdsIds.contains(adsId)) {
            return;
        }
        notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$$ExternalSyntheticLambda0
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                HlsInterstitialsAdsLoader.Listener listener = (HlsInterstitialsAdsLoader.Listener) obj;
                listener.onPrepareError(AdsMediaSource.this.getMediaItem(), adsId, i, i2, iOException);
            }
        });
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void stop(final AdsMediaSource adsMediaSource, AdsLoader.EventListener eventListener) {
        Object adsId = adsMediaSource.getAdsId();
        this.activeEventListeners.remove(adsId);
        final AdPlaybackState remove = this.activeAdPlaybackStates.remove(adsId);
        if (this.player != null && this.activeEventListeners.isEmpty()) {
            this.player.removeListener(this.playerListener);
            if (this.isReleased) {
                this.player = null;
            }
        }
        if (!this.isReleased && !this.unsupportedAdsIds.contains(adsId)) {
            if (remove != null && (adsId instanceof String) && this.resumptionStates.containsKey(adsId)) {
                this.resumptionStates.put(adsId, remove);
            }
            notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$$ExternalSyntheticLambda6
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    ((HlsInterstitialsAdsLoader.Listener) obj).onStop(r0.getMediaItem(), AdsMediaSource.this.getAdsId(), (AdPlaybackState) Assertions.checkNotNull(remove));
                }
            });
        }
        this.insertedInterstitialIds.remove(adsId);
        this.unsupportedAdsIds.remove(adsId);
        this.unresolvedAssetLists.remove(adsId);
        cancelPendingAssetListResolutionMessage();
        if (this.pendingAssetListResolutionMessage == null || !adsMediaSource.getMediaItem().equals(((PlayerMessage) Util.castNonNull(this.pendingAssetListResolutionMessage)).getPayload())) {
            return;
        }
        cancelPendingAssetListResolutionMessage();
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void release() {
        if (this.activeEventListeners.isEmpty()) {
            this.player = null;
        }
        clearAllAdResumptionStates();
        cancelPendingAssetListResolutionMessage();
        Loader loader = this.loader;
        if (loader != null) {
            loader.release();
            this.loader = null;
        }
        this.isReleased = true;
    }

    private void startLoadingAssetList(final AssetListData assetListData) {
        cancelPendingAssetListResolutionMessage();
        getLoader().startLoading(new ParsingLoadable(this.dataSourceFactory.createDataSource(), (Uri) Assertions.checkNotNull(assetListData.interstitial.assetListUri), 6, new AssetListParser()), new LoaderCallback(assetListData), 1);
        notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$$ExternalSyntheticLambda4
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((HlsInterstitialsAdsLoader.Listener) obj).onAssetListLoadStarted(r0.mediaItem, r0.adsId, r0.adGroupIndex, HlsInterstitialsAdsLoader.AssetListData.this.adIndexInAdGroup);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeExecuteOrSetNextAssetListResolutionMessage(Object obj, Timeline timeline, int i, long j) {
        long j2;
        Loader loader = this.loader;
        if (loader == null || !loader.isLoading()) {
            cancelPendingAssetListResolutionMessage();
            Timeline.Window window = timeline.getWindow(i, new Timeline.Window());
            long j3 = window.positionInFirstPeriodUs + j;
            final RunnableAtPosition nextAssetResolution = getNextAssetResolution(obj, j3);
            if (nextAssetResolution == null) {
                return;
            }
            if (nextAssetResolution.adStartTimeUs != Long.MAX_VALUE) {
                j2 = nextAssetResolution.adStartTimeUs;
            } else {
                j2 = window.durationUs;
            }
            long max = Math.max(j3, j2 - (nextAssetResolution.targetDurationUs * 3));
            if (max - j3 < 200000) {
                nextAssetResolution.run();
                return;
            }
            PlayerMessage position = ((ExoPlayer) Assertions.checkNotNull(this.player)).createMessage(new PlayerMessage.Target() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$$ExternalSyntheticLambda5
                @Override // androidx.media3.exoplayer.PlayerMessage.Target
                public final void handleMessage(int i2, Object obj2) {
                    HlsInterstitialsAdsLoader.RunnableAtPosition.this.run();
                }
            }).setPayload(window.mediaItem).setLooper((Looper) Assertions.checkNotNull(Looper.myLooper())).setPosition(Util.usToMs(max - window.positionInFirstPeriodUs));
            this.pendingAssetListResolutionMessage = position;
            position.send();
        }
    }

    private RunnableAtPosition getNextAssetResolution(Object obj, long j) {
        final TreeMap treeMap = (TreeMap) Assertions.checkNotNull(this.unresolvedAssetLists.get(obj));
        for (final Long l : treeMap.keySet()) {
            if (treeMap.size() == 1 || j <= l.longValue()) {
                final AssetListData assetListData = (AssetListData) Assertions.checkNotNull((AssetListData) treeMap.get(l));
                return new RunnableAtPosition(l.longValue(), assetListData.targetDurationUs, new Runnable() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        HlsInterstitialsAdsLoader.this.m215x24977f42(treeMap, l, assetListData);
                    }
                });
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getNextAssetResolution$7$androidx-media3-exoplayer-hls-HlsInterstitialsAdsLoader, reason: not valid java name */
    public /* synthetic */ void m215x24977f42(TreeMap treeMap, Long l, AssetListData assetListData) {
        if (treeMap.remove(l) != null) {
            startLoadingAssetList(assetListData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelPendingAssetListResolutionMessage() {
        PlayerMessage playerMessage = this.pendingAssetListResolutionMessage;
        if (playerMessage != null) {
            playerMessage.cancel();
            this.pendingAssetListResolutionMessage = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getUnresolvedAssetListWindowPositionForContentPositionUs(long j, Timeline timeline, int i) {
        Timeline.Period period = timeline.getPeriod(i, new Timeline.Period());
        long j2 = j - period.positionInWindowUs;
        AdPlaybackState adPlaybackState = period.adPlaybackState;
        int adGroupIndexForPositionUs = adPlaybackState.getAdGroupIndexForPositionUs(j2, C.TIME_UNSET);
        if (adGroupIndexForPositionUs != -1) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(adGroupIndexForPositionUs);
            TreeMap<Long, AssetListData> treeMap = this.unresolvedAssetLists.get(adPlaybackState.adsId);
            if (treeMap != null && treeMap.containsKey(Long.valueOf(adGroup.timeUs))) {
                return adGroup.timeUs - timeline.getWindow(period.windowIndex, new Timeline.Window()).positionInFirstPeriodUs;
            }
        }
        return C.TIME_UNSET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListeners(Consumer<Listener> consumer) {
        for (int i = 0; i < this.listeners.size(); i++) {
            consumer.accept(this.listeners.get(i));
        }
    }

    private Loader getLoader() {
        if (this.loader == null) {
            this.loader = new Loader("HLS-interstitials");
        }
        return this.loader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean putAndNotifyAdPlaybackStateUpdate(Object obj, AdPlaybackState adPlaybackState) {
        if (adPlaybackState.equals(this.activeAdPlaybackStates.put(obj, adPlaybackState))) {
            return false;
        }
        AdsLoader.EventListener eventListener = this.activeEventListeners.get(obj);
        if (eventListener != null) {
            eventListener.onAdPlaybackState(adPlaybackState);
            return true;
        }
        this.activeAdPlaybackStates.remove(obj);
        this.insertedInterstitialIds.remove(obj);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAssetResolutionFailed(Object obj, int i, int i2) {
        AdPlaybackState adPlaybackState = this.activeAdPlaybackStates.get(obj);
        if (adPlaybackState == null) {
            return;
        }
        putAndNotifyAdPlaybackStateUpdate(obj, adPlaybackState.withAdLoadError(i, i2));
    }

    private static boolean isLiveMediaItem(MediaItem mediaItem, Timeline timeline) {
        int firstWindowIndex = timeline.getFirstWindowIndex(false);
        Timeline.Window window = new Timeline.Window();
        while (firstWindowIndex != -1) {
            timeline.getWindow(firstWindowIndex, window);
            if (window.mediaItem.equals(mediaItem)) {
                return window.isLive();
            }
            firstWindowIndex = timeline.getNextWindowIndex(firstWindowIndex, 0, false);
        }
        return false;
    }

    private static boolean isHlsMediaItem(MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = (MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration);
        return Objects.equals(localConfiguration.mimeType, MimeTypes.APPLICATION_M3U8) || Util.inferContentType(localConfiguration.uri) == 2;
    }

    private AdPlaybackState mapInterstitialsForLive(MediaItem mediaItem, HlsMediaPlaylist hlsMediaPlaylist, AdPlaybackState adPlaybackState, long j, Set<String> set) {
        int i;
        ArrayList arrayList = new ArrayList(hlsMediaPlaylist.interstitials);
        AdPlaybackState adPlaybackState2 = adPlaybackState;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            HlsMediaPlaylist.Interstitial interstitial = (HlsMediaPlaylist.Interstitial) arrayList.get(i2);
            if (!set.contains(interstitial.id) && !interstitial.cue.contains("POST")) {
                long resolveInterstitialStartTimeUs = resolveInterstitialStartTimeUs(interstitial, hlsMediaPlaylist) - hlsMediaPlaylist.startTimeUs;
                if (resolveInterstitialStartTimeUs >= 0 && hlsMediaPlaylist.durationUs + (hlsMediaPlaylist.targetDurationUs * 3) >= resolveInterstitialStartTimeUs) {
                    long j2 = j + resolveInterstitialStartTimeUs;
                    boolean z = true;
                    int i3 = adPlaybackState2.adGroupCount - 1;
                    int i4 = adPlaybackState2.adGroupCount - 2;
                    while (true) {
                        int i5 = i4;
                        i = i3;
                        i3 = i5;
                        if (i3 < adPlaybackState2.removedAdGroupCount) {
                            break;
                        }
                        AdPlaybackState.AdGroup adGroup = adPlaybackState2.getAdGroup(i3);
                        if (adGroup.timeUs == j2) {
                            z = false;
                            break;
                        }
                        if (adGroup.timeUs < j2) {
                            i3++;
                            break;
                        }
                        i4 = i3 - 1;
                    }
                    i = i3;
                    if (z) {
                        if (i < getLowestValidAdGroupInsertionIndex(adPlaybackState2)) {
                            Log.w(TAG, "Skipping insertion of interstitial attempted to be inserted behind an already initialized ad group.");
                        } else {
                            adPlaybackState2 = adPlaybackState2.withNewAdGroup(i, j2);
                        }
                    }
                    adPlaybackState2 = insertOrUpdateInterstitialInAdGroup(mediaItem, interstitial, adPlaybackState2, i, hlsMediaPlaylist.targetDurationUs);
                    set.add(interstitial.id);
                }
            }
        }
        return adPlaybackState2;
    }

    private AdPlaybackState mapInterstitialsForVod(Timeline.Window window, HlsMediaPlaylist hlsMediaPlaylist, AdPlaybackState adPlaybackState, Set<String> set) {
        ImmutableList<HlsMediaPlaylist.Interstitial> immutableList;
        AdPlaybackState adPlaybackState2 = adPlaybackState;
        int i = 0;
        Assertions.checkArgument(adPlaybackState2.adGroupCount == adPlaybackState2.removedAdGroupCount);
        ImmutableList<HlsMediaPlaylist.Interstitial> immutableList2 = hlsMediaPlaylist.interstitials;
        long j = hlsMediaPlaylist.startTimeUs + window.positionInFirstPeriodUs;
        long j2 = window.durationUs + j;
        while (i < immutableList2.size()) {
            HlsMediaPlaylist.Interstitial interstitial = immutableList2.get(i);
            long resolveInterstitialStartTimeUs = resolveInterstitialStartTimeUs(interstitial, hlsMediaPlaylist);
            if (resolveInterstitialStartTimeUs < j && interstitial.cue.contains(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_PRE)) {
                resolveInterstitialStartTimeUs = j;
            } else if (resolveInterstitialStartTimeUs > j2 && interstitial.cue.contains("POST")) {
                resolveInterstitialStartTimeUs = j2;
            } else if (resolveInterstitialStartTimeUs < j || j2 < resolveInterstitialStartTimeUs) {
                immutableList = immutableList2;
                i++;
                immutableList2 = immutableList;
            }
            long j3 = j2 == resolveInterstitialStartTimeUs ? Long.MIN_VALUE : resolveInterstitialStartTimeUs - hlsMediaPlaylist.startTimeUs;
            int adGroupIndexForPositionUs = adPlaybackState2.getAdGroupIndexForPositionUs(j3, hlsMediaPlaylist.durationUs);
            if (adGroupIndexForPositionUs == -1) {
                adGroupIndexForPositionUs = adPlaybackState2.removedAdGroupCount;
                adPlaybackState2 = adPlaybackState2.withNewAdGroup(adPlaybackState2.removedAdGroupCount, j3);
            } else if (adPlaybackState2.getAdGroup(adGroupIndexForPositionUs).timeUs != j3) {
                adGroupIndexForPositionUs++;
                adPlaybackState2 = adPlaybackState2.withNewAdGroup(adGroupIndexForPositionUs, j3);
            }
            int i2 = adGroupIndexForPositionUs;
            immutableList = immutableList2;
            adPlaybackState2 = insertOrUpdateInterstitialInAdGroup(window.mediaItem, interstitial, adPlaybackState2, i2, hlsMediaPlaylist.targetDurationUs);
            set.add(interstitial.id);
            i++;
            immutableList2 = immutableList;
        }
        return adPlaybackState2;
    }

    private AdPlaybackState insertOrUpdateInterstitialInAdGroup(MediaItem mediaItem, HlsMediaPlaylist.Interstitial interstitial, AdPlaybackState adPlaybackState, int i, long j) {
        long[] jArr;
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i);
        if (adGroup.getIndexOfAdId(interstitial.id) != -1) {
            return adPlaybackState;
        }
        int max = Math.max(adGroup.count, 0);
        long resolveInterstitialDurationUs = resolveInterstitialDurationUs(interstitial, C.TIME_UNSET);
        if (max == 0) {
            jArr = new long[1];
        } else {
            long[] jArr2 = adGroup.durationsUs;
            long[] jArr3 = new long[jArr2.length + 1];
            System.arraycopy(jArr2, 0, jArr3, 0, jArr2.length);
            jArr = jArr3;
        }
        jArr[jArr.length - 1] = resolveInterstitialDurationUs;
        if (interstitial.resumeOffsetUs != C.TIME_UNSET) {
            resolveInterstitialDurationUs = interstitial.resumeOffsetUs;
        } else if (resolveInterstitialDurationUs == C.TIME_UNSET) {
            resolveInterstitialDurationUs = 0;
        }
        AdPlaybackState withContentResumeOffsetUs = adPlaybackState.withAdCount(i, max + 1).withAdId(i, max, interstitial.id).withAdDurationsUs(i, jArr).withContentResumeOffsetUs(i, adGroup.contentResumeOffsetUs + resolveInterstitialDurationUs);
        if (interstitial.assetUri != null) {
            return withContentResumeOffsetUs.withAvailableAdMediaItem(i, max, new MediaItem.Builder().setUri(interstitial.assetUri).setMimeType(MimeTypes.APPLICATION_M3U8).build());
        }
        Object checkNotNull = Assertions.checkNotNull(withContentResumeOffsetUs.adsId);
        ((TreeMap) Assertions.checkNotNull(this.unresolvedAssetLists.get(checkNotNull))).put(Long.valueOf(adGroup.timeUs != Long.MIN_VALUE ? adGroup.timeUs : Long.MAX_VALUE), new AssetListData(mediaItem, checkNotNull, interstitial, i, max, j));
        return withContentResumeOffsetUs;
    }

    private static int getLowestValidAdGroupInsertionIndex(AdPlaybackState adPlaybackState) {
        int i = adPlaybackState.adGroupCount;
        while (true) {
            i--;
            if (i >= adPlaybackState.removedAdGroupCount) {
                for (int i2 : adPlaybackState.getAdGroup(i).states) {
                    if (i2 != 0) {
                        return i + 1;
                    }
                }
            } else {
                return adPlaybackState.removedAdGroupCount;
            }
        }
    }

    private static long resolveInterstitialDurationUs(HlsMediaPlaylist.Interstitial interstitial, long j) {
        if (interstitial.playoutLimitUs != C.TIME_UNSET) {
            return interstitial.playoutLimitUs;
        }
        if (interstitial.durationUs != C.TIME_UNSET) {
            return interstitial.durationUs;
        }
        if (interstitial.endDateUnixUs != C.TIME_UNSET) {
            return interstitial.endDateUnixUs - interstitial.startDateUnixUs;
        }
        return interstitial.plannedDurationUs != C.TIME_UNSET ? interstitial.plannedDurationUs : j;
    }

    private static long resolveInterstitialStartTimeUs(HlsMediaPlaylist.Interstitial interstitial, HlsMediaPlaylist hlsMediaPlaylist) {
        long resolveInterstitialDurationUs;
        if (interstitial.cue.contains(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_PRE)) {
            return hlsMediaPlaylist.startTimeUs;
        }
        if (interstitial.cue.contains("POST")) {
            return hlsMediaPlaylist.startTimeUs + hlsMediaPlaylist.durationUs;
        }
        if (interstitial.snapTypes.contains(HlsMediaPlaylist.Interstitial.SNAP_TYPE_OUT)) {
            return getClosestSegmentBoundaryUs(interstitial.startDateUnixUs, hlsMediaPlaylist);
        }
        if (interstitial.snapTypes.contains(HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN)) {
            if (interstitial.resumeOffsetUs != C.TIME_UNSET) {
                resolveInterstitialDurationUs = interstitial.resumeOffsetUs;
            } else {
                resolveInterstitialDurationUs = resolveInterstitialDurationUs(interstitial, 0L);
            }
            return getClosestSegmentBoundaryUs(interstitial.startDateUnixUs + resolveInterstitialDurationUs, hlsMediaPlaylist) - resolveInterstitialDurationUs;
        }
        return interstitial.startDateUnixUs;
    }

    private static long getClosestSegmentBoundaryUs(long j, HlsMediaPlaylist hlsMediaPlaylist) {
        long j2;
        long j3;
        long j4 = j - hlsMediaPlaylist.startTimeUs;
        if (j4 <= 0 || hlsMediaPlaylist.segments.isEmpty()) {
            return hlsMediaPlaylist.startTimeUs;
        }
        if (j4 >= hlsMediaPlaylist.durationUs) {
            j2 = hlsMediaPlaylist.startTimeUs;
            j3 = hlsMediaPlaylist.durationUs;
        } else {
            HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get((int) Math.min(j4 / hlsMediaPlaylist.targetDurationUs, hlsMediaPlaylist.segments.size() - 1));
            if (j4 - segment.relativeStartTimeUs < Math.abs(j4 - (segment.relativeStartTimeUs + segment.durationUs))) {
                j2 = hlsMediaPlaylist.startTimeUs;
                j3 = segment.relativeStartTimeUs;
            } else {
                j2 = hlsMediaPlaylist.startTimeUs + segment.relativeStartTimeUs;
                j3 = segment.durationUs;
            }
        }
        return j2 + j3;
    }

    /* loaded from: classes.dex */
    private class PlayerListener implements Player.Listener {
        private final Timeline.Period period;

        private PlayerListener() {
            this.period = new Timeline.Period();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMetadata(final Metadata metadata) {
            ExoPlayer exoPlayer = HlsInterstitialsAdsLoader.this.player;
            if (exoPlayer == null || !exoPlayer.isPlayingAd()) {
                return;
            }
            exoPlayer.getCurrentTimeline().getPeriod(exoPlayer.getCurrentPeriodIndex(), this.period);
            final Object obj = this.period.adPlaybackState.adsId;
            if (obj == null || !HlsInterstitialsAdsLoader.this.activeAdPlaybackStates.containsKey(obj)) {
                return;
            }
            final MediaItem mediaItem = (MediaItem) Assertions.checkNotNull(exoPlayer.getCurrentMediaItem());
            final int currentAdGroupIndex = exoPlayer.getCurrentAdGroupIndex();
            final int currentAdIndexInAdGroup = exoPlayer.getCurrentAdIndexInAdGroup();
            HlsInterstitialsAdsLoader.this.notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$PlayerListener$$ExternalSyntheticLambda1
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj2) {
                    ((HlsInterstitialsAdsLoader.Listener) obj2).onMetadata(MediaItem.this, obj, currentAdGroupIndex, currentAdIndexInAdGroup, metadata);
                }
            });
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTimelineChanged(Timeline timeline, int i) {
            if (timeline.isEmpty()) {
                HlsInterstitialsAdsLoader.this.cancelPendingAssetListResolutionMessage();
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            if (HlsInterstitialsAdsLoader.this.player == null || positionInfo.mediaItem == null || positionInfo2.mediaItem == null || i == 4) {
                HlsInterstitialsAdsLoader.this.cancelPendingAssetListResolutionMessage();
                return;
            }
            Timeline currentTimeline = HlsInterstitialsAdsLoader.this.player.getCurrentTimeline();
            Object obj = currentTimeline.getPeriod(positionInfo2.periodIndex, this.period).adPlaybackState.adsId;
            if (obj == null || !HlsInterstitialsAdsLoader.this.activeAdPlaybackStates.containsKey(obj)) {
                HlsInterstitialsAdsLoader.this.cancelPendingAssetListResolutionMessage();
                return;
            }
            if ((i == 0 || i == 3) && positionInfo.adGroupIndex != -1) {
                currentTimeline.getPeriod(positionInfo.periodIndex, this.period);
                markAdAsPlayedAndNotifyListeners(positionInfo.mediaItem, obj, positionInfo.adGroupIndex, positionInfo.adIndexInAdGroup);
            } else if (i == 1 || i == 2) {
                long msToUs = Util.msToUs(positionInfo2.contentPositionMs);
                long unresolvedAssetListWindowPositionForContentPositionUs = HlsInterstitialsAdsLoader.this.getUnresolvedAssetListWindowPositionForContentPositionUs(msToUs, currentTimeline, positionInfo2.periodIndex);
                HlsInterstitialsAdsLoader.this.maybeExecuteOrSetNextAssetListResolutionMessage(obj, currentTimeline, positionInfo2.mediaItemIndex, unresolvedAssetListWindowPositionForContentPositionUs != C.TIME_UNSET ? unresolvedAssetListWindowPositionForContentPositionUs : msToUs);
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int i) {
            ExoPlayer exoPlayer = HlsInterstitialsAdsLoader.this.player;
            if (i == 4 && exoPlayer != null && exoPlayer.isPlayingAd()) {
                exoPlayer.getCurrentTimeline().getPeriod(exoPlayer.getCurrentPeriodIndex(), this.period);
                Object obj = this.period.adPlaybackState.adsId;
                if (obj == null || !HlsInterstitialsAdsLoader.this.activeAdPlaybackStates.containsKey(obj)) {
                    return;
                }
                markAdAsPlayedAndNotifyListeners((MediaItem) Assertions.checkNotNull(exoPlayer.getCurrentMediaItem()), obj, exoPlayer.getCurrentAdGroupIndex(), exoPlayer.getCurrentAdIndexInAdGroup());
            }
        }

        private void markAdAsPlayedAndNotifyListeners(final MediaItem mediaItem, final Object obj, final int i, final int i2) {
            AdPlaybackState adPlaybackState = (AdPlaybackState) HlsInterstitialsAdsLoader.this.activeAdPlaybackStates.get(obj);
            if (adPlaybackState == null || adPlaybackState.getAdGroup(i).states[i2] != 1) {
                return;
            }
            HlsInterstitialsAdsLoader.this.putAndNotifyAdPlaybackStateUpdate(obj, adPlaybackState.withPlayedAd(i, i2));
            HlsInterstitialsAdsLoader.this.notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$PlayerListener$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj2) {
                    ((HlsInterstitialsAdsLoader.Listener) obj2).onAdCompleted(MediaItem.this, obj, i, i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class LoaderCallback implements Loader.Callback<ParsingLoadable<AssetList>> {
        private final AssetListData assetListData;

        public LoaderCallback(AssetListData assetListData) {
            this.assetListData = assetListData;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public void onLoadCompleted(ParsingLoadable<AssetList> parsingLoadable, long j, long j2) {
            final AssetList result = parsingLoadable.getResult();
            AdPlaybackState adPlaybackState = (AdPlaybackState) HlsInterstitialsAdsLoader.this.activeAdPlaybackStates.get(this.assetListData.adsId);
            int i = 0;
            if (adPlaybackState == null || result == null || result.assets.isEmpty()) {
                if (adPlaybackState != null) {
                    handleAssetResolutionFailed(new IOException("empty asset list"), false);
                    return;
                }
                return;
            }
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(this.assetListData.adGroupIndex);
            long j3 = adGroup.durationsUs[this.assetListData.adIndexInAdGroup];
            long j4 = C.TIME_UNSET;
            long j5 = 0;
            long j6 = j3 != C.TIME_UNSET ? adGroup.durationsUs[this.assetListData.adIndexInAdGroup] : 0L;
            int i2 = adGroup.count;
            if (result.assets.size() > 1) {
                adPlaybackState = adPlaybackState.withAdCount(this.assetListData.adGroupIndex, (result.assets.size() + i2) - 1);
                adGroup = adPlaybackState.getAdGroup(this.assetListData.adGroupIndex);
            }
            int i3 = this.assetListData.adIndexInAdGroup;
            long[] jArr = (long[]) adGroup.durationsUs.clone();
            while (i < result.assets.size()) {
                Asset asset = result.assets.get(i);
                if (i > 0) {
                    i3 = (i2 + i) - 1;
                }
                jArr[i3] = asset.durationUs;
                j5 += asset.durationUs;
                adPlaybackState = adPlaybackState.withAvailableAdMediaItem(this.assetListData.adGroupIndex, i3, new MediaItem.Builder().setUri(asset.uri).setMimeType(MimeTypes.APPLICATION_M3U8).build());
                i++;
                j4 = j4;
            }
            long j7 = j4;
            AdPlaybackState withAdDurationsUs = adPlaybackState.withAdDurationsUs(this.assetListData.adGroupIndex, jArr);
            if (this.assetListData.interstitial.resumeOffsetUs == j7) {
                withAdDurationsUs = withAdDurationsUs.withContentResumeOffsetUs(this.assetListData.adGroupIndex, (withAdDurationsUs.getAdGroup(this.assetListData.adGroupIndex).contentResumeOffsetUs - j6) + j5);
            }
            HlsInterstitialsAdsLoader.this.putAndNotifyAdPlaybackStateUpdate(this.assetListData.adsId, withAdDurationsUs);
            HlsInterstitialsAdsLoader.this.notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$LoaderCallback$$ExternalSyntheticLambda1
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    HlsInterstitialsAdsLoader.LoaderCallback.this.m217x4e88d248(result, (HlsInterstitialsAdsLoader.Listener) obj);
                }
            });
            maybeContinueAssetResolution();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onLoadCompleted$0$androidx-media3-exoplayer-hls-HlsInterstitialsAdsLoader$LoaderCallback, reason: not valid java name */
        public /* synthetic */ void m217x4e88d248(AssetList assetList, Listener listener) {
            listener.onAssetListLoadCompleted(this.assetListData.mediaItem, this.assetListData.adsId, this.assetListData.adGroupIndex, this.assetListData.adIndexInAdGroup, assetList);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public void onLoadCanceled(ParsingLoadable<AssetList> parsingLoadable, long j, long j2, boolean z) {
            handleAssetResolutionFailed(null, true);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public Loader.LoadErrorAction onLoadError(ParsingLoadable<AssetList> parsingLoadable, long j, long j2, IOException iOException, int i) {
            handleAssetResolutionFailed(iOException, false);
            return Loader.DONT_RETRY;
        }

        private void handleAssetResolutionFailed(final IOException iOException, final boolean z) {
            HlsInterstitialsAdsLoader.this.notifyAssetResolutionFailed(this.assetListData.adsId, this.assetListData.adGroupIndex, this.assetListData.adIndexInAdGroup);
            HlsInterstitialsAdsLoader.this.notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader$LoaderCallback$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    HlsInterstitialsAdsLoader.LoaderCallback.this.m216x5a47ca9c(iOException, z, (HlsInterstitialsAdsLoader.Listener) obj);
                }
            });
            maybeContinueAssetResolution();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$handleAssetResolutionFailed$1$androidx-media3-exoplayer-hls-HlsInterstitialsAdsLoader$LoaderCallback, reason: not valid java name */
        public /* synthetic */ void m216x5a47ca9c(IOException iOException, boolean z, Listener listener) {
            listener.onAssetListLoadFailed(this.assetListData.mediaItem, this.assetListData.adsId, this.assetListData.adGroupIndex, this.assetListData.adIndexInAdGroup, iOException, z);
        }

        private void maybeContinueAssetResolution() {
            ExoPlayer exoPlayer = HlsInterstitialsAdsLoader.this.player;
            if (exoPlayer == null || exoPlayer.getPlaybackState() == 1 || !this.assetListData.mediaItem.equals(exoPlayer.getCurrentMediaItem())) {
                return;
            }
            long msToUs = Util.msToUs(exoPlayer.getContentPosition());
            Timeline currentTimeline = exoPlayer.getCurrentTimeline();
            long unresolvedAssetListWindowPositionForContentPositionUs = HlsInterstitialsAdsLoader.this.getUnresolvedAssetListWindowPositionForContentPositionUs(msToUs, currentTimeline, exoPlayer.getCurrentPeriodIndex());
            HlsInterstitialsAdsLoader.this.maybeExecuteOrSetNextAssetListResolutionMessage(this.assetListData.adsId, currentTimeline, exoPlayer.getCurrentMediaItemIndex(), unresolvedAssetListWindowPositionForContentPositionUs != C.TIME_UNSET ? unresolvedAssetListWindowPositionForContentPositionUs : msToUs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class RunnableAtPosition implements Runnable {
        public final long adStartTimeUs;
        private final Runnable runnable;
        private final long targetDurationUs;

        public RunnableAtPosition(long j, long j2, Runnable runnable) {
            this.adStartTimeUs = j;
            this.targetDurationUs = j2;
            this.runnable = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AssetListData {
        private final int adGroupIndex;
        private final int adIndexInAdGroup;
        private final Object adsId;
        private final HlsMediaPlaylist.Interstitial interstitial;
        private final MediaItem mediaItem;
        private final long targetDurationUs;

        public AssetListData(MediaItem mediaItem, Object obj, HlsMediaPlaylist.Interstitial interstitial, int i, int i2, long j) {
            Assertions.checkArgument(interstitial.assetListUri != null);
            this.mediaItem = mediaItem;
            this.adsId = obj;
            this.adGroupIndex = i;
            this.adIndexInAdGroup = i2;
            this.targetDurationUs = j;
            this.interstitial = interstitial;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AssetListData)) {
                return false;
            }
            AssetListData assetListData = (AssetListData) obj;
            return this.adGroupIndex == assetListData.adGroupIndex && this.adIndexInAdGroup == assetListData.adIndexInAdGroup && this.targetDurationUs == assetListData.targetDurationUs && Objects.equals(this.mediaItem, assetListData.mediaItem) && Objects.equals(this.adsId, assetListData.adsId) && Objects.equals(this.interstitial, assetListData.interstitial);
        }

        public int hashCode() {
            return (int) ((((((((((this.mediaItem.hashCode() * 31) + this.adsId.hashCode()) * 31) + this.interstitial.hashCode()) * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + this.targetDurationUs);
        }
    }
}
