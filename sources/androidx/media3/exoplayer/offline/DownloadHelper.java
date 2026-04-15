package androidx.media3.exoplayer.offline;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseIntArray;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.DefaultRendererCapabilitiesList;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererCapabilitiesList;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.offline.DownloadHelper;
import androidx.media3.exoplayer.offline.DownloadRequest;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.BaseTrackSelection;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectionUtil;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.SeekMap;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes.dex */
public final class DownloadHelper {
    public static final DefaultTrackSelector.Parameters DEFAULT_TRACK_SELECTOR_PARAMETERS;

    @Deprecated
    public static final DefaultTrackSelector.Parameters DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT;
    private static final int MODE_NOT_PREPARE = 0;
    private static final int MODE_PREPARE_NON_PROGRESSIVE_SOURCE_AND_SELECT_TRACKS = 2;
    private static final int MODE_PREPARE_PROGRESSIVE_SOURCE = 1;
    private static final String TAG = "DownloadHelper";
    private boolean areTracksSelected;
    private Callback callback;
    private final Handler callbackHandler;
    private final boolean debugLoggingEnabled;
    private List<ExoTrackSelection>[][] immutableTrackSelectionsByPeriodAndRenderer;
    private boolean isPreparedWithMedia;
    private final MediaItem.LocalConfiguration localConfiguration;
    private MappingTrackSelector.MappedTrackInfo[] mappedTrackInfos;
    private MediaPreparer mediaPreparer;
    private final MediaSource mediaSource;
    private final int mode;
    private final RendererCapabilitiesList rendererCapabilities;
    private final SparseIntArray scratchSet;
    private TrackGroupArray[] trackGroupArrays;
    private List<ExoTrackSelection>[][] trackSelectionsByPeriodAndRenderer;
    private final DefaultTrackSelector trackSelector;
    private final Timeline.Window window;

    /* loaded from: classes.dex */
    public interface Callback {
        void onPrepareError(DownloadHelper downloadHelper, IOException iOException);

        void onPrepared(DownloadHelper downloadHelper, boolean z);
    }

    /* loaded from: classes.dex */
    public static class LiveContentUnsupportedException extends IOException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DrmSessionManager lambda$createMediaSourceInternal$4(DrmSessionManager drmSessionManager, MediaItem mediaItem) {
        return drmSessionManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$0() {
    }

    /* loaded from: classes.dex */
    public static final class Factory {
        private DataSource.Factory dataSourceFactory;
        private boolean debugLoggingEnabled;
        private DrmSessionManager drmSessionManager;
        private RenderersFactory renderersFactory;
        private TrackSelectionParameters trackSelectionParameters = DownloadHelper.DEFAULT_TRACK_SELECTOR_PARAMETERS;
        private Supplier<ReleasableExecutor> loadExecutorSupplier = null;

        public Factory setDataSourceFactory(DataSource.Factory factory) {
            this.dataSourceFactory = factory;
            return this;
        }

        public Factory setRenderersFactory(RenderersFactory renderersFactory) {
            this.renderersFactory = renderersFactory;
            return this;
        }

        public Factory setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
            this.trackSelectionParameters = trackSelectionParameters;
            return this;
        }

        public Factory setDrmSessionManager(DrmSessionManager drmSessionManager) {
            this.drmSessionManager = drmSessionManager;
            return this;
        }

        public Factory setLoadExecutor(Supplier<ReleasableExecutor> supplier) {
            this.loadExecutorSupplier = supplier;
            return this;
        }

        public Factory setDebugLoggingEnabled(boolean z) {
            this.debugLoggingEnabled = z;
            return this;
        }

        public DownloadHelper create(MediaItem mediaItem) {
            RendererCapabilitiesList unreleaseableRendererCapabilitiesList;
            boolean isProgressive = DownloadHelper.isProgressive((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration));
            Assertions.checkArgument(isProgressive || this.dataSourceFactory != null);
            MediaSource createMediaSourceInternal = (isProgressive && this.dataSourceFactory == null) ? null : DownloadHelper.createMediaSourceInternal(mediaItem, (DataSource.Factory) Util.castNonNull(this.dataSourceFactory), this.drmSessionManager, this.loadExecutorSupplier);
            TrackSelectionParameters trackSelectionParameters = this.trackSelectionParameters;
            if (this.renderersFactory != null) {
                unreleaseableRendererCapabilitiesList = new DefaultRendererCapabilitiesList.Factory(this.renderersFactory).createRendererCapabilitiesList();
            } else {
                unreleaseableRendererCapabilitiesList = new UnreleaseableRendererCapabilitiesList(new RendererCapabilities[0]);
            }
            return new DownloadHelper(mediaItem, createMediaSourceInternal, trackSelectionParameters, unreleaseableRendererCapabilitiesList, this.debugLoggingEnabled);
        }

        public DownloadHelper create(MediaSource mediaSource) {
            RendererCapabilitiesList unreleaseableRendererCapabilitiesList;
            MediaItem mediaItem = mediaSource.getMediaItem();
            TrackSelectionParameters trackSelectionParameters = this.trackSelectionParameters;
            if (this.renderersFactory != null) {
                unreleaseableRendererCapabilitiesList = new DefaultRendererCapabilitiesList.Factory(this.renderersFactory).createRendererCapabilitiesList();
            } else {
                unreleaseableRendererCapabilitiesList = new UnreleaseableRendererCapabilitiesList(new RendererCapabilities[0]);
            }
            return new DownloadHelper(mediaItem, mediaSource, trackSelectionParameters, unreleaseableRendererCapabilitiesList, this.debugLoggingEnabled);
        }
    }

    static {
        DefaultTrackSelector.Parameters build = DefaultTrackSelector.Parameters.DEFAULT.buildUpon().setForceHighestSupportedBitrate(true).setConstrainAudioChannelCountToDeviceCapabilities(false).build();
        DEFAULT_TRACK_SELECTOR_PARAMETERS = build;
        DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT = build;
    }

    @Deprecated
    public static DefaultTrackSelector.Parameters getDefaultTrackSelectorParameters(Context context) {
        return DEFAULT_TRACK_SELECTOR_PARAMETERS;
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem) {
        Assertions.checkArgument(isProgressive((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration)));
        return new Factory().create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, DataSource.Factory factory) {
        return new Factory().setDataSourceFactory(factory).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, DataSource.Factory factory, boolean z) {
        return new Factory().setDataSourceFactory(factory).setDebugLoggingEnabled(z).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, RenderersFactory renderersFactory, DataSource.Factory factory) {
        return new Factory().setDataSourceFactory(factory).setRenderersFactory(renderersFactory).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, RenderersFactory renderersFactory, DataSource.Factory factory, boolean z) {
        return new Factory().setDataSourceFactory(factory).setRenderersFactory(renderersFactory).setDebugLoggingEnabled(z).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory factory) {
        return new Factory().setDataSourceFactory(factory).setTrackSelectionParameters(trackSelectionParameters).setRenderersFactory(renderersFactory).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory factory, boolean z) {
        return new Factory().setDataSourceFactory(factory).setTrackSelectionParameters(trackSelectionParameters).setRenderersFactory(renderersFactory).setDebugLoggingEnabled(z).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory factory, DrmSessionManager drmSessionManager) {
        return new Factory().setDataSourceFactory(factory).setTrackSelectionParameters(trackSelectionParameters).setRenderersFactory(renderersFactory).setDrmSessionManager(drmSessionManager).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory factory, DrmSessionManager drmSessionManager, boolean z) {
        return new Factory().setDataSourceFactory(factory).setTrackSelectionParameters(trackSelectionParameters).setRenderersFactory(renderersFactory).setDrmSessionManager(drmSessionManager).setDebugLoggingEnabled(z).create(mediaItem);
    }

    public static MediaSource createMediaSource(DownloadRequest downloadRequest, DataSource.Factory factory) {
        return createMediaSource(downloadRequest, factory, null);
    }

    public static MediaSource createMediaSource(DownloadRequest downloadRequest, DataSource.Factory factory, DrmSessionManager drmSessionManager) {
        return createMediaSourceInternal(downloadRequest.toMediaItem(), factory, drmSessionManager, null);
    }

    public DownloadHelper(MediaItem mediaItem, MediaSource mediaSource, TrackSelectionParameters trackSelectionParameters, RendererCapabilitiesList rendererCapabilitiesList) {
        this(mediaItem, mediaSource, trackSelectionParameters, rendererCapabilitiesList, false);
    }

    public DownloadHelper(MediaItem mediaItem, MediaSource mediaSource, TrackSelectionParameters trackSelectionParameters, RendererCapabilitiesList rendererCapabilitiesList, boolean z) {
        int i;
        this.localConfiguration = (MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration);
        this.mediaSource = mediaSource;
        if (mediaSource == null) {
            i = 0;
        } else {
            i = mediaSource instanceof ProgressiveMediaSource ? 1 : 2;
        }
        this.mode = i;
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(trackSelectionParameters, new DownloadTrackSelection.Factory());
        this.trackSelector = defaultTrackSelector;
        this.rendererCapabilities = rendererCapabilitiesList;
        this.debugLoggingEnabled = z;
        this.scratchSet = new SparseIntArray();
        defaultTrackSelector.init(new TrackSelector.InvalidationListener() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda4
            @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
            public final void onTrackSelectionsInvalidated() {
                DownloadHelper.lambda$new$0();
            }
        }, new FakeBandwidthMeter());
        this.callbackHandler = Util.createHandlerForCurrentOrMainLooper();
        this.window = new Timeline.Window();
    }

    public void prepare(final Callback callback) {
        Assertions.checkState(this.callback == null);
        this.callback = callback;
        if (this.mode != 0) {
            this.mediaPreparer = new MediaPreparer((MediaSource) Assertions.checkNotNull(this.mediaSource), this);
        } else {
            this.callbackHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    DownloadHelper.this.m225xe2e8f097(callback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$prepare$1$androidx-media3-exoplayer-offline-DownloadHelper, reason: not valid java name */
    public /* synthetic */ void m225xe2e8f097(Callback callback) {
        callback.onPrepared(this, false);
    }

    public void release() {
        MediaPreparer mediaPreparer = this.mediaPreparer;
        if (mediaPreparer != null) {
            mediaPreparer.release();
        }
        this.trackSelector.release();
        this.rendererCapabilities.release();
    }

    public Object getManifest() {
        if (this.mode == 0) {
            return null;
        }
        assertPreparedWithMedia();
        if (this.mediaPreparer.timeline.getWindowCount() > 0) {
            return this.mediaPreparer.timeline.getWindow(0, this.window).manifest;
        }
        return null;
    }

    public int getPeriodCount() {
        if (this.mode == 0) {
            return 0;
        }
        assertPreparedWithMedia();
        return this.mediaPreparer.mediaPeriods.length;
    }

    public Tracks getTracks(int i) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        return TrackSelectionUtil.buildTracks(this.mappedTrackInfos[i], this.immutableTrackSelectionsByPeriodAndRenderer[i]);
    }

    public TrackGroupArray getTrackGroups(int i) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        return this.trackGroupArrays[i];
    }

    public MappingTrackSelector.MappedTrackInfo getMappedTrackInfo(int i) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        return this.mappedTrackInfos[i];
    }

    public List<ExoTrackSelection> getTrackSelections(int i, int i2) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        return this.immutableTrackSelectionsByPeriodAndRenderer[i][i2];
    }

    public void clearTrackSelections(int i) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        for (int i2 = 0; i2 < this.rendererCapabilities.size(); i2++) {
            this.trackSelectionsByPeriodAndRenderer[i][i2].clear();
        }
    }

    public void replaceTrackSelections(int i, TrackSelectionParameters trackSelectionParameters) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            clearTrackSelections(i);
            addTrackSelectionInternal(i, trackSelectionParameters);
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addTrackSelection(int i, TrackSelectionParameters trackSelectionParameters) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            addTrackSelectionInternal(i, trackSelectionParameters);
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addAudioLanguagesToSelection(String... strArr) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            DefaultTrackSelector.Parameters.Builder buildUpon = DEFAULT_TRACK_SELECTOR_PARAMETERS.buildUpon();
            buildUpon.setForceHighestSupportedBitrate(true);
            for (RendererCapabilities rendererCapabilities : this.rendererCapabilities.getRendererCapabilities()) {
                int trackType = rendererCapabilities.getTrackType();
                buildUpon.setTrackTypeDisabled(trackType, trackType != 1);
            }
            int periodCount = getPeriodCount();
            for (String str : strArr) {
                TrackSelectionParameters build = buildUpon.setPreferredAudioLanguage(str).build();
                for (int i = 0; i < periodCount; i++) {
                    addTrackSelectionInternal(i, build);
                }
            }
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addTextLanguagesToSelection(boolean z, String... strArr) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            DefaultTrackSelector.Parameters.Builder buildUpon = DEFAULT_TRACK_SELECTOR_PARAMETERS.buildUpon();
            buildUpon.setSelectUndeterminedTextLanguage(z);
            buildUpon.setForceHighestSupportedBitrate(true);
            for (RendererCapabilities rendererCapabilities : this.rendererCapabilities.getRendererCapabilities()) {
                int trackType = rendererCapabilities.getTrackType();
                buildUpon.setTrackTypeDisabled(trackType, trackType != 3);
            }
            int periodCount = getPeriodCount();
            for (String str : strArr) {
                TrackSelectionParameters build = buildUpon.setPreferredTextLanguage(str).build();
                for (int i = 0; i < periodCount; i++) {
                    addTrackSelectionInternal(i, build);
                }
            }
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addTrackSelectionForSingleRenderer(int i, int i2, DefaultTrackSelector.Parameters parameters, List<DefaultTrackSelector.SelectionOverride> list) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            DefaultTrackSelector.Parameters.Builder buildUpon = parameters.buildUpon();
            int i3 = 0;
            while (i3 < this.mappedTrackInfos[i].getRendererCount()) {
                buildUpon.setRendererDisabled(i3, i3 != i2);
                i3++;
            }
            if (list.isEmpty()) {
                addTrackSelectionInternal(i, buildUpon.build());
                return;
            }
            TrackGroupArray trackGroups = this.mappedTrackInfos[i].getTrackGroups(i2);
            for (int i4 = 0; i4 < list.size(); i4++) {
                buildUpon.setSelectionOverride(i2, trackGroups, list.get(i4));
                addTrackSelectionInternal(i, buildUpon.build());
            }
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public DownloadRequest getDownloadRequest(byte[] bArr) {
        return getDownloadRequest(this.localConfiguration.uri.toString(), bArr);
    }

    public DownloadRequest getDownloadRequest(byte[] bArr, long j, long j2) {
        return getDownloadRequest(this.localConfiguration.uri.toString(), bArr, j, j2);
    }

    public DownloadRequest getDownloadRequest(String str, byte[] bArr) {
        return getDownloadRequestBuilder(str, bArr).build();
    }

    public DownloadRequest getDownloadRequest(String str, byte[] bArr, long j, long j2) {
        DownloadRequest.Builder downloadRequestBuilder = getDownloadRequestBuilder(str, bArr);
        assertPreparedWithMedia();
        populateDownloadRequestBuilderWithDownloadRange(downloadRequestBuilder, j, j2);
        return downloadRequestBuilder.build();
    }

    private DownloadRequest.Builder getDownloadRequestBuilder(String str, byte[] bArr) {
        DownloadRequest.Builder data = new DownloadRequest.Builder(str, this.localConfiguration.uri).setMimeType(this.localConfiguration.mimeType).setKeySetId(this.localConfiguration.drmConfiguration != null ? this.localConfiguration.drmConfiguration.getKeySetId() : null).setCustomCacheKey(this.localConfiguration.customCacheKey).setData(bArr);
        if (this.mode == 2) {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int length = this.trackSelectionsByPeriodAndRenderer.length;
            for (int i = 0; i < length; i++) {
                arrayList2.clear();
                int length2 = this.trackSelectionsByPeriodAndRenderer[i].length;
                for (int i2 = 0; i2 < length2; i2++) {
                    arrayList2.addAll(this.trackSelectionsByPeriodAndRenderer[i][i2]);
                }
                arrayList.addAll(this.mediaPreparer.mediaPeriods[i].getStreamKeys(arrayList2));
            }
            data.setStreamKeys(arrayList);
        }
        return data;
    }

    private void populateDownloadRequestBuilderWithDownloadRange(DownloadRequest.Builder builder, long j, long j2) {
        int i = this.mode;
        if (i == 1) {
            populateDownloadRequestBuilderWithByteRange(builder, j, j2);
        } else {
            if (i != 2) {
                return;
            }
            populateDownloadRequestBuilderWithTimeRange(builder, j, j2);
        }
    }

    private void populateDownloadRequestBuilderWithByteRange(DownloadRequest.Builder builder, long j, long j2) {
        long j3;
        assertPreparedWithProgressiveSource();
        Timeline timeline = this.mediaPreparer.timeline;
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        long longValue = ((Long) timeline.getPeriodPositionUs(window, period, 0, Util.msToUs(j)).second).longValue();
        if (j2 != C.TIME_UNSET) {
            j3 = Util.msToUs(j2) + longValue;
            if (period.durationUs != C.TIME_UNSET) {
                j3 = Math.min(j3, period.durationUs - 1);
            }
        } else {
            j3 = -9223372036854775807L;
        }
        SeekMap seekMap = this.mediaPreparer.seekMap;
        if (seekMap.isSeekable()) {
            long j4 = seekMap.getSeekPoints(longValue).first.position;
            long j5 = -1;
            if (j3 != C.TIME_UNSET) {
                long j6 = seekMap.getSeekPoints(j3).second.position;
                if (longValue == j3 || j4 != j6) {
                    j5 = j6 - j4;
                }
            }
            builder.setByteRange(j4, j5);
            return;
        }
        Log.w(TAG, "Cannot set download byte range for progressive stream that is unseekable");
    }

    private void populateDownloadRequestBuilderWithTimeRange(DownloadRequest.Builder builder, long j, long j2) {
        long msToUs;
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        Timeline.Window window = this.mediaPreparer.timeline.getWindow(0, new Timeline.Window());
        if (j == C.TIME_UNSET) {
            msToUs = window.getDefaultPositionUs();
        } else {
            msToUs = Util.msToUs(j);
        }
        long durationUs = window.getDurationUs();
        long msToUs2 = j2 == C.TIME_UNSET ? durationUs : Util.msToUs(j2);
        if (durationUs != C.TIME_UNSET) {
            msToUs = Math.min(msToUs, durationUs);
            msToUs2 = Math.min(msToUs2, durationUs - msToUs);
        }
        builder.setTimeRange(msToUs, msToUs2);
    }

    @RequiresNonNull({"trackGroupArrays", "trackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline"})
    private void addTrackSelectionInternal(int i, TrackSelectionParameters trackSelectionParameters) throws ExoPlaybackException {
        this.trackSelector.setParameters(trackSelectionParameters);
        runTrackSelection(i);
        UnmodifiableIterator<TrackSelectionOverride> it = trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            this.trackSelector.setParameters(trackSelectionParameters.buildUpon().setOverrideForType(it.next()).build());
            runTrackSelection(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaPrepared() throws ExoPlaybackException {
        Assertions.checkNotNull(this.mediaPreparer);
        Assertions.checkNotNull(this.mediaPreparer.mediaPeriods);
        Assertions.checkNotNull(this.mediaPreparer.timeline);
        int i = this.mode;
        final boolean z = false;
        if (i == 2) {
            int length = this.mediaPreparer.mediaPeriods.length;
            int size = this.rendererCapabilities.size();
            this.trackSelectionsByPeriodAndRenderer = (List[][]) Array.newInstance((Class<?>) List.class, length, size);
            this.immutableTrackSelectionsByPeriodAndRenderer = (List[][]) Array.newInstance((Class<?>) List.class, length, size);
            for (int i2 = 0; i2 < length; i2++) {
                for (int i3 = 0; i3 < size; i3++) {
                    this.trackSelectionsByPeriodAndRenderer[i2][i3] = new ArrayList();
                    this.immutableTrackSelectionsByPeriodAndRenderer[i2][i3] = Collections.unmodifiableList(this.trackSelectionsByPeriodAndRenderer[i2][i3]);
                }
            }
            this.trackGroupArrays = new TrackGroupArray[length];
            this.mappedTrackInfos = new MappingTrackSelector.MappedTrackInfo[length];
            for (int i4 = 0; i4 < length; i4++) {
                this.trackGroupArrays[i4] = this.mediaPreparer.mediaPeriods[i4].getTrackGroups();
                this.trackSelector.onSelectionActivated(runTrackSelection(i4).info);
                this.mappedTrackInfos[i4] = (MappingTrackSelector.MappedTrackInfo) Assertions.checkNotNull(this.trackSelector.getCurrentMappedTrackInfo());
            }
            setPreparedWithNonProgressiveSourceAndTracksSelected();
            z = true;
        } else {
            Assertions.checkState(i == 1);
            Assertions.checkNotNull(this.mediaPreparer.seekMap);
            setPreparedWithProgressiveSource();
        }
        ((Handler) Assertions.checkNotNull(this.callbackHandler)).post(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DownloadHelper.this.m224x26f1c63b(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onMediaPrepared$2$androidx-media3-exoplayer-offline-DownloadHelper, reason: not valid java name */
    public /* synthetic */ void m224x26f1c63b(boolean z) {
        ((Callback) Assertions.checkNotNull(this.callback)).onPrepared(this, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaPreparationFailed(final IOException iOException) {
        ((Handler) Assertions.checkNotNull(this.callbackHandler)).post(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DownloadHelper.this.m223x83f2c593(iOException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onMediaPreparationFailed$3$androidx-media3-exoplayer-offline-DownloadHelper, reason: not valid java name */
    public /* synthetic */ void m223x83f2c593(IOException iOException) {
        ((Callback) Assertions.checkNotNull(this.callback)).onPrepareError(this, iOException);
    }

    @RequiresNonNull({"trackGroupArrays", "mappedTrackInfos", "trackSelectionsByPeriodAndRenderer", "immutableTrackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    private void setPreparedWithNonProgressiveSourceAndTracksSelected() {
        this.isPreparedWithMedia = true;
        this.areTracksSelected = true;
    }

    @RequiresNonNull({"mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.seekMap", "mediaPreparer.mediaPeriods"})
    private void setPreparedWithProgressiveSource() {
        this.isPreparedWithMedia = true;
    }

    @EnsuresNonNull({"mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    private void assertPreparedWithMedia() {
        Assertions.checkState(this.mode != 0);
        Assertions.checkState(this.isPreparedWithMedia);
    }

    @EnsuresNonNull({"trackGroupArrays", "mappedTrackInfos", "trackSelectionsByPeriodAndRenderer", "immutableTrackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    private void assertPreparedWithNonProgressiveSourceAndTracksSelected() {
        Assertions.checkState(this.mode == 2);
        Assertions.checkState(this.isPreparedWithMedia);
        Assertions.checkState(this.areTracksSelected);
    }

    @EnsuresNonNull({"mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.seekMap", "mediaPreparer.mediaPeriods"})
    private void assertPreparedWithProgressiveSource() {
        Assertions.checkState(this.mode == 1);
        Assertions.checkState(this.isPreparedWithMedia);
    }

    @RequiresNonNull({"trackGroupArrays", "trackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline"})
    private TrackSelectorResult runTrackSelection(int i) throws ExoPlaybackException {
        TrackSelectorResult selectTracks = this.trackSelector.selectTracks(this.rendererCapabilities.getRendererCapabilities(), this.trackGroupArrays[i], new MediaSource.MediaPeriodId(this.mediaPreparer.timeline.getUidOfPeriod(i)), this.mediaPreparer.timeline);
        for (int i2 = 0; i2 < selectTracks.length; i2++) {
            ExoTrackSelection exoTrackSelection = selectTracks.selections[i2];
            if (exoTrackSelection != null) {
                List<ExoTrackSelection> list = this.trackSelectionsByPeriodAndRenderer[i][i2];
                int i3 = 0;
                while (true) {
                    if (i3 < list.size()) {
                        ExoTrackSelection exoTrackSelection2 = list.get(i3);
                        if (exoTrackSelection2.getTrackGroup().equals(exoTrackSelection.getTrackGroup())) {
                            this.scratchSet.clear();
                            for (int i4 = 0; i4 < exoTrackSelection2.length(); i4++) {
                                this.scratchSet.put(exoTrackSelection2.getIndexInTrackGroup(i4), 0);
                            }
                            for (int i5 = 0; i5 < exoTrackSelection.length(); i5++) {
                                this.scratchSet.put(exoTrackSelection.getIndexInTrackGroup(i5), 0);
                            }
                            int[] iArr = new int[this.scratchSet.size()];
                            for (int i6 = 0; i6 < this.scratchSet.size(); i6++) {
                                iArr[i6] = this.scratchSet.keyAt(i6);
                            }
                            list.set(i3, new DownloadTrackSelection(exoTrackSelection2.getTrackGroup(), iArr));
                        } else {
                            i3++;
                        }
                    } else {
                        list.add(exoTrackSelection);
                        break;
                    }
                }
            }
        }
        if (this.debugLoggingEnabled) {
            logTrackSelectorResult(i, selectTracks);
        }
        return selectTracks;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static MediaSource createMediaSourceInternal(MediaItem mediaItem, DataSource.Factory factory, final DrmSessionManager drmSessionManager, Supplier<ReleasableExecutor> supplier) {
        DefaultMediaSourceFactory defaultMediaSourceFactory;
        if (isProgressive((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration))) {
            ProgressiveMediaSource.Factory factory2 = new ProgressiveMediaSource.Factory(factory);
            defaultMediaSourceFactory = factory2;
            if (supplier != null) {
                factory2.setDownloadExecutor(supplier);
                defaultMediaSourceFactory = factory2;
            }
        } else {
            defaultMediaSourceFactory = new DefaultMediaSourceFactory(factory, ExtractorsFactory.EMPTY);
        }
        if (drmSessionManager != null) {
            defaultMediaSourceFactory.setDrmSessionManagerProvider(new DrmSessionManagerProvider() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda0
                @Override // androidx.media3.exoplayer.drm.DrmSessionManagerProvider
                public final DrmSessionManager get(MediaItem mediaItem2) {
                    return DownloadHelper.lambda$createMediaSourceInternal$4(DrmSessionManager.this, mediaItem2);
                }
            });
        }
        return defaultMediaSourceFactory.createMediaSource(mediaItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isProgressive(MediaItem.LocalConfiguration localConfiguration) {
        return Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType) == 4;
    }

    private static void logTrackSelectorResult(int i, TrackSelectorResult trackSelectorResult) {
        Log.d(TAG, "Track selections changed, period index: " + i + ", tracks [");
        ImmutableList<Tracks.Group> groups = trackSelectorResult.tracks.getGroups();
        for (int i2 = 0; i2 < groups.size(); i2++) {
            Tracks.Group group = groups.get(i2);
            Log.d(TAG, "  group [");
            for (int i3 = 0; i3 < group.length; i3++) {
                Log.d(TAG, "    " + (group.isTrackSelected(i3) ? "[X]" : "[ ]") + " Track:" + i3 + ", " + Format.toLogString(group.getTrackFormat(i3)) + ", supported=" + Util.getFormatSupportString(group.getTrackSupport(i3)));
            }
            Log.d(TAG, "  ]");
        }
        Log.d(TAG, "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class MediaPreparer implements MediaSource.MediaSourceCaller, ProgressiveMediaSource.Listener, MediaPeriod.Callback, Handler.Callback {
        private static final int DOWNLOAD_HELPER_CALLBACK_MESSAGE_FAILED = 2;
        private static final int DOWNLOAD_HELPER_CALLBACK_MESSAGE_PREPARED = 1;
        private static final int MESSAGE_CHECK_FOR_FAILURE = 2;
        private static final int MESSAGE_CONTINUE_LOADING = 3;
        private static final int MESSAGE_PREPARE_SOURCE = 1;
        private static final int MESSAGE_RELEASE = 4;
        private final DownloadHelper downloadHelper;
        public MediaPeriod[] mediaPeriods;
        private final MediaSource mediaSource;
        private final Handler mediaSourceHandler;
        private final HandlerThread mediaSourceThread;
        private boolean released;
        public SeekMap seekMap;
        public Timeline timeline;
        private final Allocator allocator = new DefaultAllocator(true, 65536);
        private final ArrayList<MediaPeriod> pendingMediaPeriods = new ArrayList<>();
        private final Handler downloadHelperHandler = Util.createHandlerForCurrentOrMainLooper(new Handler.Callback() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$MediaPreparer$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean handleDownloadHelperCallbackMessage;
                handleDownloadHelperCallbackMessage = DownloadHelper.MediaPreparer.this.handleDownloadHelperCallbackMessage(message);
                return handleDownloadHelperCallbackMessage;
            }
        });

        public MediaPreparer(MediaSource mediaSource, DownloadHelper downloadHelper) {
            this.mediaSource = mediaSource;
            this.downloadHelper = downloadHelper;
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:DownloadHelper");
            this.mediaSourceThread = handlerThread;
            handlerThread.start();
            Handler createHandler = Util.createHandler(handlerThread.getLooper(), this);
            this.mediaSourceHandler = createHandler;
            createHandler.sendEmptyMessage(1);
        }

        public void release() {
            if (this.released) {
                return;
            }
            this.released = true;
            this.mediaSourceHandler.sendEmptyMessage(4);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                MediaSource mediaSource = this.mediaSource;
                if (mediaSource instanceof ProgressiveMediaSource) {
                    ((ProgressiveMediaSource) mediaSource).setListener(this);
                }
                this.mediaSource.prepareSource(this, null, PlayerId.UNSET);
                this.mediaSourceHandler.sendEmptyMessage(2);
                return true;
            }
            int i2 = 0;
            if (i == 2) {
                try {
                    if (this.mediaPeriods == null) {
                        this.mediaSource.maybeThrowSourceInfoRefreshError();
                    } else {
                        while (i2 < this.pendingMediaPeriods.size()) {
                            this.pendingMediaPeriods.get(i2).maybeThrowPrepareError();
                            i2++;
                        }
                    }
                    this.mediaSourceHandler.sendEmptyMessageDelayed(2, 100L);
                } catch (IOException e) {
                    this.downloadHelperHandler.obtainMessage(2, e).sendToTarget();
                }
                return true;
            }
            if (i == 3) {
                MediaPeriod mediaPeriod = (MediaPeriod) message.obj;
                if (this.pendingMediaPeriods.contains(mediaPeriod)) {
                    mediaPeriod.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(0L).build());
                }
                return true;
            }
            if (i != 4) {
                return false;
            }
            MediaPeriod[] mediaPeriodArr = this.mediaPeriods;
            if (mediaPeriodArr != null) {
                int length = mediaPeriodArr.length;
                while (i2 < length) {
                    this.mediaSource.releasePeriod(mediaPeriodArr[i2]);
                    i2++;
                }
            }
            MediaSource mediaSource2 = this.mediaSource;
            if (mediaSource2 instanceof ProgressiveMediaSource) {
                ((ProgressiveMediaSource) mediaSource2).clearListener();
            }
            this.mediaSource.releaseSource(this);
            this.mediaSourceHandler.removeCallbacksAndMessages(null);
            this.mediaSourceThread.quit();
            return true;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.MediaSourceCaller
        public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
            MediaPeriod[] mediaPeriodArr;
            if (this.timeline != null) {
                return;
            }
            if (timeline.getWindow(0, new Timeline.Window()).isLive()) {
                this.downloadHelperHandler.obtainMessage(2, new LiveContentUnsupportedException()).sendToTarget();
                return;
            }
            this.timeline = timeline;
            this.mediaPeriods = new MediaPeriod[timeline.getPeriodCount()];
            int i = 0;
            while (true) {
                mediaPeriodArr = this.mediaPeriods;
                if (i >= mediaPeriodArr.length) {
                    break;
                }
                MediaPeriod createPeriod = this.mediaSource.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(i)), this.allocator, 0L);
                this.mediaPeriods[i] = createPeriod;
                this.pendingMediaPeriods.add(createPeriod);
                i++;
            }
            for (MediaPeriod mediaPeriod : mediaPeriodArr) {
                mediaPeriod.prepare(this, 0L);
            }
        }

        @Override // androidx.media3.exoplayer.source.ProgressiveMediaSource.Listener
        public void onSeekMap(MediaSource mediaSource, SeekMap seekMap) {
            this.seekMap = seekMap;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
        public void onPrepared(MediaPeriod mediaPeriod) {
            this.pendingMediaPeriods.remove(mediaPeriod);
            if (this.pendingMediaPeriods.isEmpty()) {
                this.mediaSourceHandler.removeMessages(2);
                this.downloadHelperHandler.sendEmptyMessage(1);
            }
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
            if (this.pendingMediaPeriods.contains(mediaPeriod)) {
                this.mediaSourceHandler.obtainMessage(3, mediaPeriod).sendToTarget();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean handleDownloadHelperCallbackMessage(Message message) {
            if (this.released) {
                return false;
            }
            int i = message.what;
            if (i == 1) {
                try {
                    this.downloadHelper.onMediaPrepared();
                } catch (ExoPlaybackException e) {
                    this.downloadHelperHandler.obtainMessage(2, new IOException(e)).sendToTarget();
                }
                return true;
            }
            if (i != 2) {
                return false;
            }
            release();
            this.downloadHelper.onMediaPreparationFailed((IOException) Util.castNonNull(message.obj));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class DownloadTrackSelection extends BaseTrackSelection {
        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Object getSelectionData() {
            return null;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        }

        /* loaded from: classes.dex */
        private static final class Factory implements ExoTrackSelection.Factory {
            private Factory() {
            }

            @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection.Factory
            public ExoTrackSelection[] createTrackSelections(ExoTrackSelection.Definition[] definitionArr, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
                ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
                for (int i = 0; i < definitionArr.length; i++) {
                    exoTrackSelectionArr[i] = definitionArr[i] == null ? null : new DownloadTrackSelection(definitionArr[i].group, definitionArr[i].tracks);
                }
                return exoTrackSelectionArr;
            }
        }

        public DownloadTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
        }
    }

    /* loaded from: classes.dex */
    private static final class FakeBandwidthMeter implements BandwidthMeter {
        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public void addEventListener(Handler handler, BandwidthMeter.EventListener eventListener) {
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public long getBitrateEstimate() {
            return 0L;
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public TransferListener getTransferListener() {
            return null;
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        }

        private FakeBandwidthMeter() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class UnreleaseableRendererCapabilitiesList implements RendererCapabilitiesList {
        private final RendererCapabilities[] rendererCapabilities;

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList
        public void release() {
        }

        private UnreleaseableRendererCapabilitiesList(RendererCapabilities[] rendererCapabilitiesArr) {
            this.rendererCapabilities = rendererCapabilitiesArr;
        }

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList
        public RendererCapabilities[] getRendererCapabilities() {
            return this.rendererCapabilities;
        }

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList
        public int size() {
            return this.rendererCapabilities.length;
        }
    }
}
