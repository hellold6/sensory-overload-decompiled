package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.StreamKey;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.hls.HlsSampleStreamWrapper;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class HlsMediaPeriod implements MediaPeriod, HlsPlaylistTracker.PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    private int audioVideoSampleStreamWrapperCount;
    private final CmcdConfiguration cmcdConfiguration;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private MediaPeriod.Callback mediaPeriodCallback;
    private final TransferListener mediaTransferListener;
    private final int metadataType;
    private int pendingPrepareCount;
    private final PlayerId playerId;
    private final HlsPlaylistTracker playlistTracker;
    private final long timestampAdjusterInitializationTimeoutMs;
    private TrackGroupArray trackGroups;
    private final boolean useSessionKeys;
    private final HlsSampleStreamWrapper.Callback sampleStreamWrapperCallback = new SampleStreamWrapperCallback();
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices = new IdentityHashMap<>();
    private final TimestampAdjusterProvider timestampAdjusterProvider = new TimestampAdjusterProvider();
    private HlsSampleStreamWrapper[] sampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private int[][] manifestUrlIndicesPerWrapper = new int[0];

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        return C.TIME_UNSET;
    }

    static /* synthetic */ int access$106(HlsMediaPeriod hlsMediaPeriod) {
        int i = hlsMediaPeriod.pendingPrepareCount - 1;
        hlsMediaPeriod.pendingPrepareCount = i;
        return i;
    }

    public HlsMediaPeriod(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, TransferListener transferListener, CmcdConfiguration cmcdConfiguration, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, Allocator allocator, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, boolean z, int i, boolean z2, PlayerId playerId, long j) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.mediaTransferListener = transferListener;
        this.cmcdConfiguration = cmcdConfiguration;
        this.drmSessionManager = drmSessionManager;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.eventDispatcher = eventDispatcher2;
        this.allocator = allocator;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.allowChunklessPreparation = z;
        this.metadataType = i;
        this.useSessionKeys = z2;
        this.playerId = playerId;
        this.timestampAdjusterInitializationTimeoutMs = j;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory.empty();
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.release();
        }
        this.mediaPeriodCallback = null;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.mediaPeriodCallback = callback;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(j);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.maybeThrowPrepareError();
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return (TrackGroupArray) Assertions.checkNotNull(this.trackGroups);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [int] */
    /* JADX WARN: Type inference failed for: r14v8 */
    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        int[] iArr;
        TrackGroupArray trackGroupArray;
        int i;
        int i2;
        HlsMultivariantPlaylist hlsMultivariantPlaylist = (HlsMultivariantPlaylist) Assertions.checkNotNull(this.playlistTracker.getMultivariantPlaylist());
        boolean isEmpty = hlsMultivariantPlaylist.variants.isEmpty();
        boolean z = !isEmpty;
        int i3 = 0;
        if (!isEmpty) {
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.sampleStreamWrappers[0];
            iArr = this.manifestUrlIndicesPerWrapper[0];
            trackGroupArray = hlsSampleStreamWrapper.getTrackGroups();
            i = hlsSampleStreamWrapper.getPrimaryTrackGroupIndex();
        } else {
            iArr = new int[0];
            trackGroupArray = TrackGroupArray.EMPTY;
            i = 0;
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        boolean z3 = false;
        for (ExoTrackSelection exoTrackSelection : list) {
            TrackGroup trackGroup = exoTrackSelection.getTrackGroup();
            int indexOf = trackGroupArray.indexOf(trackGroup);
            if (indexOf == -1) {
                ?? r14 = z;
                i2 = i3;
                while (true) {
                    HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
                    if (r14 >= hlsSampleStreamWrapperArr.length) {
                        break;
                    }
                    TrackGroupArray trackGroups = hlsSampleStreamWrapperArr[r14].getTrackGroups();
                    int indexOf2 = trackGroups.indexOf(trackGroup);
                    if (indexOf2 != -1) {
                        int i4 = trackGroups.get(indexOf2).type != 1 ? 2 : 1;
                        int[] iArr2 = this.manifestUrlIndicesPerWrapper[r14];
                        for (int i5 = i2; i5 < exoTrackSelection.length(); i5++) {
                            arrayList.add(new StreamKey(i4, iArr2[exoTrackSelection.getIndexInTrackGroup(i5)]));
                        }
                    } else {
                        r14++;
                    }
                }
            } else if (indexOf == i) {
                for (int i6 = i3; i6 < exoTrackSelection.length(); i6++) {
                    arrayList.add(new StreamKey(i3, iArr[exoTrackSelection.getIndexInTrackGroup(i6)]));
                }
                i2 = i3;
                z3 = true;
            } else {
                i2 = i3;
                z2 = true;
            }
            i3 = i2;
        }
        int i7 = i3;
        if (z2 && !z3) {
            int i8 = iArr[i7];
            int i9 = hlsMultivariantPlaylist.variants.get(iArr[i7]).format.bitrate;
            for (int i10 = 1; i10 < iArr.length; i10++) {
                int i11 = hlsMultivariantPlaylist.variants.get(iArr[i10]).format.bitrate;
                if (i11 < i9) {
                    i8 = iArr[i10];
                    i9 = i11;
                }
            }
            arrayList.add(new StreamKey(i7, i8));
        }
        return arrayList;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        int[] iArr = new int[exoTrackSelectionArr.length];
        int[] iArr2 = new int[exoTrackSelectionArr.length];
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            SampleStream sampleStream = sampleStreamArr[i];
            iArr[i] = sampleStream == null ? -1 : this.streamWrapperIndices.get(sampleStream).intValue();
            iArr2[i] = -1;
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i];
            if (exoTrackSelection != null) {
                TrackGroup trackGroup = exoTrackSelection.getTrackGroup();
                int i2 = 0;
                while (true) {
                    HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
                    if (i2 >= hlsSampleStreamWrapperArr.length) {
                        break;
                    }
                    if (hlsSampleStreamWrapperArr[i2].getTrackGroups().indexOf(trackGroup) != -1) {
                        iArr2[i] = i2;
                        break;
                    }
                    i2++;
                }
            }
        }
        this.streamWrapperIndices.clear();
        int length = exoTrackSelectionArr.length;
        SampleStream[] sampleStreamArr2 = new SampleStream[length];
        SampleStream[] sampleStreamArr3 = new SampleStream[exoTrackSelectionArr.length];
        ExoTrackSelection[] exoTrackSelectionArr2 = new ExoTrackSelection[exoTrackSelectionArr.length];
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = new HlsSampleStreamWrapper[this.sampleStreamWrappers.length];
        int i3 = 0;
        int i4 = 0;
        boolean z = false;
        while (i3 < this.sampleStreamWrappers.length) {
            for (int i5 = 0; i5 < exoTrackSelectionArr.length; i5++) {
                ExoTrackSelection exoTrackSelection2 = null;
                sampleStreamArr3[i5] = iArr[i5] == i3 ? sampleStreamArr[i5] : null;
                if (iArr2[i5] == i3) {
                    exoTrackSelection2 = exoTrackSelectionArr[i5];
                }
                exoTrackSelectionArr2[i5] = exoTrackSelection2;
            }
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.sampleStreamWrappers[i3];
            int[] iArr3 = iArr;
            int i6 = i3;
            int i7 = i4;
            boolean selectTracks = hlsSampleStreamWrapper.selectTracks(exoTrackSelectionArr2, zArr, sampleStreamArr3, zArr2, j, z);
            boolean z2 = false;
            for (int i8 = 0; i8 < exoTrackSelectionArr.length; i8++) {
                SampleStream sampleStream2 = sampleStreamArr3[i8];
                if (iArr2[i8] == i6) {
                    Assertions.checkNotNull(sampleStream2);
                    sampleStreamArr2[i8] = sampleStream2;
                    this.streamWrapperIndices.put(sampleStream2, Integer.valueOf(i6));
                    z2 = true;
                } else if (iArr3[i8] == i6) {
                    Assertions.checkState(sampleStream2 == null);
                }
            }
            if (z2) {
                hlsSampleStreamWrapperArr2[i7] = hlsSampleStreamWrapper;
                i4 = i7 + 1;
                if (i7 == 0) {
                    hlsSampleStreamWrapper.setIsPrimaryTimestampSource(true);
                    if (!selectTracks) {
                        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr3 = this.enabledSampleStreamWrappers;
                        if (hlsSampleStreamWrapperArr3.length != 0 && hlsSampleStreamWrapper == hlsSampleStreamWrapperArr3[0]) {
                        }
                    }
                    this.timestampAdjusterProvider.reset();
                    z = true;
                } else {
                    hlsSampleStreamWrapper.setIsPrimaryTimestampSource(i6 < this.audioVideoSampleStreamWrapperCount);
                }
            } else {
                i4 = i7;
            }
            i3 = i6 + 1;
            iArr = iArr3;
        }
        System.arraycopy(sampleStreamArr2, 0, sampleStreamArr, 0, length);
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr4 = (HlsSampleStreamWrapper[]) Util.nullSafeArrayCopy(hlsSampleStreamWrapperArr2, i4);
        this.enabledSampleStreamWrappers = hlsSampleStreamWrapperArr4;
        ImmutableList copyOf = ImmutableList.copyOf(hlsSampleStreamWrapperArr4);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(copyOf, Lists.transform(copyOf, new Function() { // from class: androidx.media3.exoplayer.hls.HlsMediaPeriod$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                List trackTypes;
                trackTypes = ((HlsSampleStreamWrapper) obj).getTrackGroups().getTrackTypes();
                return trackTypes;
            }
        }));
        return j;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.enabledSampleStreamWrappers) {
            hlsSampleStreamWrapper.discardBuffer(j, z);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.trackGroups == null) {
            for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
                hlsSampleStreamWrapper.continuePreparing();
            }
            return false;
        }
        return this.compositeSequenceableLoader.continueLoading(loadingInfo);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j) {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.enabledSampleStreamWrappers;
        if (hlsSampleStreamWrapperArr.length > 0) {
            boolean seekToUs = hlsSampleStreamWrapperArr[0].seekToUs(j, false);
            int i = 1;
            while (true) {
                HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.enabledSampleStreamWrappers;
                if (i >= hlsSampleStreamWrapperArr2.length) {
                    break;
                }
                hlsSampleStreamWrapperArr2[i].seekToUs(j, seekToUs);
                i++;
            }
            if (seekToUs) {
                this.timestampAdjusterProvider.reset();
            }
        }
        return j;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.enabledSampleStreamWrappers) {
            if (hlsSampleStreamWrapper.isVideoSampleStream()) {
                return hlsSampleStreamWrapper.getAdjustedSeekPositionUs(j, seekParameters);
            }
        }
        return j;
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistChanged() {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.onPlaylistUpdated();
        }
        this.mediaPeriodCallback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public boolean onPlaylistError(Uri uri, LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo, boolean z) {
        boolean z2 = true;
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            z2 &= hlsSampleStreamWrapper.onPlaylistError(uri, loadErrorInfo, z);
        }
        this.mediaPeriodCallback.onContinueLoadingRequested(this);
        return z2;
    }

    private void buildAndPrepareSampleStreamWrappers(long j) {
        Map<String, DrmInitData> emptyMap;
        long j2;
        HlsMultivariantPlaylist hlsMultivariantPlaylist = (HlsMultivariantPlaylist) Assertions.checkNotNull(this.playlistTracker.getMultivariantPlaylist());
        if (this.useSessionKeys) {
            emptyMap = deriveOverridingDrmInitData(hlsMultivariantPlaylist.sessionKeyDrmInitData);
        } else {
            emptyMap = Collections.emptyMap();
        }
        Map<String, DrmInitData> map = emptyMap;
        boolean isEmpty = hlsMultivariantPlaylist.variants.isEmpty();
        List<HlsMultivariantPlaylist.Rendition> list = hlsMultivariantPlaylist.audios;
        List<HlsMultivariantPlaylist.Rendition> list2 = hlsMultivariantPlaylist.subtitles;
        this.pendingPrepareCount = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (isEmpty) {
            j2 = j;
        } else {
            buildAndPrepareMainSampleStreamWrapper(hlsMultivariantPlaylist, j, arrayList, arrayList2, map);
            arrayList = arrayList;
            j2 = j;
        }
        buildAndPrepareAudioSampleStreamWrappers(j2, list, arrayList, arrayList2, map);
        this.audioVideoSampleStreamWrapperCount = arrayList.size();
        buildAndPrepareSubtitleSampleStreamWrappers(j2, list2, arrayList, arrayList2, map);
        this.sampleStreamWrappers = (HlsSampleStreamWrapper[]) arrayList.toArray(new HlsSampleStreamWrapper[0]);
        this.manifestUrlIndicesPerWrapper = (int[][]) arrayList2.toArray(new int[0]);
        this.pendingPrepareCount = this.sampleStreamWrappers.length;
        for (int i = 0; i < this.audioVideoSampleStreamWrapperCount; i++) {
            this.sampleStreamWrappers[i].setIsPrimaryTimestampSource(true);
        }
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.continuePreparing();
        }
        this.enabledSampleStreamWrappers = this.sampleStreamWrappers;
    }

    private void buildAndPrepareMainSampleStreamWrapper(HlsMultivariantPlaylist hlsMultivariantPlaylist, long j, List<HlsSampleStreamWrapper> list, List<int[]> list2, Map<String, DrmInitData> map) {
        int i;
        boolean z;
        boolean z2;
        int size = hlsMultivariantPlaylist.variants.size();
        int[] iArr = new int[size];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < hlsMultivariantPlaylist.variants.size(); i4++) {
            Format format = hlsMultivariantPlaylist.variants.get(i4).format;
            if (format.height > 0 || Util.getCodecsOfType(format.codecs, 2) != null) {
                iArr[i4] = 2;
                i2++;
            } else if (Util.getCodecsOfType(format.codecs, 1) != null) {
                iArr[i4] = 1;
                i3++;
            } else {
                iArr[i4] = -1;
            }
        }
        if (i2 > 0) {
            i = i2;
            z2 = false;
            z = true;
        } else if (i3 < size) {
            i = size - i3;
            z = false;
            z2 = true;
        } else {
            i = size;
            z = false;
            z2 = false;
        }
        Uri[] uriArr = new Uri[i];
        Format[] formatArr = new Format[i];
        int[] iArr2 = new int[i];
        int i5 = 0;
        for (int i6 = 0; i6 < hlsMultivariantPlaylist.variants.size(); i6++) {
            if ((!z || iArr[i6] == 2) && (!z2 || iArr[i6] != 1)) {
                HlsMultivariantPlaylist.Variant variant = hlsMultivariantPlaylist.variants.get(i6);
                uriArr[i5] = variant.url;
                formatArr[i5] = variant.format;
                iArr2[i5] = i6;
                i5++;
            }
        }
        String str = formatArr[0].codecs;
        int codecCountOfType = Util.getCodecCountOfType(str, 2);
        int codecCountOfType2 = Util.getCodecCountOfType(str, 1);
        boolean z3 = (codecCountOfType2 == 1 || (codecCountOfType2 == 0 && hlsMultivariantPlaylist.audios.isEmpty())) && codecCountOfType <= 1 && codecCountOfType2 + codecCountOfType > 0;
        HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper("main", (z || codecCountOfType2 <= 0) ? 0 : 1, uriArr, formatArr, hlsMultivariantPlaylist.muxedAudioFormat, hlsMultivariantPlaylist.muxedCaptionFormats, map, j);
        list.add(buildSampleStreamWrapper);
        list2.add(iArr2);
        if (this.allowChunklessPreparation && z3) {
            ArrayList arrayList = new ArrayList();
            if (codecCountOfType > 0) {
                Format[] formatArr2 = new Format[i];
                for (int i7 = 0; i7 < i; i7++) {
                    formatArr2[i7] = deriveVideoFormat(formatArr[i7]);
                }
                arrayList.add(new TrackGroup("main", formatArr2));
                if (codecCountOfType2 > 0 && (hlsMultivariantPlaylist.muxedAudioFormat != null || hlsMultivariantPlaylist.audios.isEmpty())) {
                    arrayList.add(new TrackGroup("main:audio", deriveAudioFormat(formatArr[0], hlsMultivariantPlaylist.muxedAudioFormat, false)));
                }
                List<Format> list3 = hlsMultivariantPlaylist.muxedCaptionFormats;
                if (list3 != null) {
                    for (int i8 = 0; i8 < list3.size(); i8++) {
                        arrayList.add(new TrackGroup("main:cc:" + i8, this.extractorFactory.getOutputTextFormat(list3.get(i8))));
                    }
                }
            } else {
                Format[] formatArr3 = new Format[i];
                for (int i9 = 0; i9 < i; i9++) {
                    formatArr3[i9] = deriveAudioFormat(formatArr[i9], hlsMultivariantPlaylist.muxedAudioFormat, true);
                }
                arrayList.add(new TrackGroup("main", formatArr3));
            }
            TrackGroup trackGroup = new TrackGroup("main:id3", new Format.Builder().setId("ID3").setSampleMimeType(MimeTypes.APPLICATION_ID3).build());
            arrayList.add(trackGroup);
            buildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo((TrackGroup[]) arrayList.toArray(new TrackGroup[0]), 0, arrayList.indexOf(trackGroup));
        }
    }

    private void buildAndPrepareAudioSampleStreamWrappers(long j, List<HlsMultivariantPlaylist.Rendition> list, List<HlsSampleStreamWrapper> list2, List<int[]> list3, Map<String, DrmInitData> map) {
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        ArrayList arrayList3 = new ArrayList(list.size());
        HashSet hashSet = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).name;
            if (hashSet.add(str)) {
                arrayList.clear();
                arrayList2.clear();
                arrayList3.clear();
                boolean z = true;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (Objects.equals(str, list.get(i2).name)) {
                        HlsMultivariantPlaylist.Rendition rendition = list.get(i2);
                        arrayList3.add(Integer.valueOf(i2));
                        arrayList.add(rendition.url);
                        arrayList2.add(rendition.format);
                        z &= Util.getCodecCountOfType(rendition.format.codecs, 1) == 1;
                    }
                }
                String str2 = "audio:" + str;
                HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(str2, 1, (Uri[]) arrayList.toArray((Uri[]) Util.castNonNullTypeArray(new Uri[0])), (Format[]) arrayList2.toArray(new Format[0]), null, Collections.emptyList(), map, j);
                list3.add(Ints.toArray(arrayList3));
                list2.add(buildSampleStreamWrapper);
                if (this.allowChunklessPreparation && z) {
                    buildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo(new TrackGroup[]{new TrackGroup(str2, (Format[]) arrayList2.toArray(new Format[0]))}, 0, new int[0]);
                }
            }
        }
    }

    private void buildAndPrepareSubtitleSampleStreamWrappers(long j, List<HlsMultivariantPlaylist.Rendition> list, List<HlsSampleStreamWrapper> list2, List<int[]> list3, Map<String, DrmInitData> map) {
        List<HlsMultivariantPlaylist.Rendition> list4 = list;
        ArrayList arrayList = new ArrayList(list4.size());
        ArrayList arrayList2 = new ArrayList(list4.size());
        ArrayList arrayList3 = new ArrayList(list4.size());
        HashSet hashSet = new HashSet();
        int i = 0;
        int i2 = 0;
        while (i2 < list4.size()) {
            String str = list4.get(i2).name;
            if (hashSet.add(str)) {
                arrayList.clear();
                arrayList2.clear();
                arrayList3.clear();
                for (int i3 = i; i3 < list4.size(); i3++) {
                    if (Objects.equals(str, list4.get(i3).name)) {
                        HlsMultivariantPlaylist.Rendition rendition = list4.get(i3);
                        arrayList3.add(Integer.valueOf(i3));
                        arrayList.add(rendition.url);
                        arrayList2.add(rendition.format);
                    }
                }
                String str2 = "subtitle:" + str;
                Format[] formatArr = (Format[]) arrayList2.toArray(new Format[i]);
                HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(str2, 3, (Uri[]) arrayList.toArray((Uri[]) Util.castNonNullTypeArray(new Uri[i])), formatArr, null, ImmutableList.of(), map, j);
                list3.add(Ints.toArray(arrayList3));
                list2.add(buildSampleStreamWrapper);
                int length = formatArr.length;
                Format[] formatArr2 = new Format[length];
                int i4 = i;
                while (i4 < length) {
                    formatArr2[i4] = this.extractorFactory.getOutputTextFormat(formatArr[i4]);
                    i4++;
                    i = i;
                }
                int i5 = i;
                TrackGroup[] trackGroupArr = new TrackGroup[1];
                trackGroupArr[i5] = new TrackGroup(str2, formatArr2);
                i = i5;
                buildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo(trackGroupArr, i, new int[i]);
            }
            i2++;
            list4 = list;
        }
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(String str, int i, Uri[] uriArr, Format[] formatArr, Format format, List<Format> list, Map<String, DrmInitData> map, long j) {
        return new HlsSampleStreamWrapper(str, i, this.sampleStreamWrapperCallback, new HlsChunkSource(this.extractorFactory, this.playlistTracker, uriArr, formatArr, this.dataSourceFactory, this.mediaTransferListener, this.timestampAdjusterProvider, this.timestampAdjusterInitializationTimeoutMs, list, this.playerId, this.cmcdConfiguration), map, this.allocator, j, format, this.drmSessionManager, this.drmEventDispatcher, this.loadErrorHandlingPolicy, this.eventDispatcher, this.metadataType);
    }

    private static Map<String, DrmInitData> deriveOverridingDrmInitData(List<DrmInitData> list) {
        ArrayList arrayList = new ArrayList(list);
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i < arrayList.size()) {
            DrmInitData drmInitData = list.get(i);
            String str = drmInitData.schemeType;
            i++;
            int i2 = i;
            while (i2 < arrayList.size()) {
                DrmInitData drmInitData2 = (DrmInitData) arrayList.get(i2);
                if (TextUtils.equals(drmInitData2.schemeType, str)) {
                    drmInitData = drmInitData.merge(drmInitData2);
                    arrayList.remove(i2);
                } else {
                    i2++;
                }
            }
            hashMap.put(str, drmInitData);
        }
        return hashMap;
    }

    private static Format deriveVideoFormat(Format format) {
        String codecsOfType = Util.getCodecsOfType(format.codecs, 2);
        return new Format.Builder().setId(format.id).setLabel(format.label).setLabels(format.labels).setContainerMimeType(format.containerMimeType).setSampleMimeType(MimeTypes.getMediaMimeType(codecsOfType)).setCodecs(codecsOfType).setMetadata(format.metadata).setAverageBitrate(format.averageBitrate).setPeakBitrate(format.peakBitrate).setWidth(format.width).setHeight(format.height).setFrameRate(format.frameRate).setSelectionFlags(format.selectionFlags).setRoleFlags(format.roleFlags).build();
    }

    private static Format deriveAudioFormat(Format format, Format format2, boolean z) {
        Metadata metadata;
        int i;
        String str;
        String str2;
        List<Label> list;
        int i2;
        int i3;
        String str3;
        ImmutableList of = ImmutableList.of();
        if (format2 != null) {
            str2 = format2.codecs;
            metadata = format2.metadata;
            i2 = format2.channelCount;
            i = format2.selectionFlags;
            i3 = format2.roleFlags;
            str = format2.language;
            str3 = format2.label;
            list = format2.labels;
        } else {
            String codecsOfType = Util.getCodecsOfType(format.codecs, 1);
            metadata = format.metadata;
            if (z) {
                i2 = format.channelCount;
                i = format.selectionFlags;
                i3 = format.roleFlags;
                str = format.language;
                str3 = format.label;
                str2 = codecsOfType;
                list = format.labels;
            } else {
                i = 0;
                str = null;
                str2 = codecsOfType;
                list = of;
                i2 = -1;
                i3 = 0;
                str3 = null;
            }
        }
        return new Format.Builder().setId(format.id).setLabel(str3).setLabels(list).setContainerMimeType(format.containerMimeType).setSampleMimeType(MimeTypes.getMediaMimeType(str2)).setCodecs(str2).setMetadata(metadata).setAverageBitrate(z ? format.averageBitrate : -1).setPeakBitrate(z ? format.peakBitrate : -1).setChannelCount(i2).setSelectionFlags(i).setRoleFlags(i3).setLanguage(str).build();
    }

    /* loaded from: classes.dex */
    private class SampleStreamWrapperCallback implements HlsSampleStreamWrapper.Callback {
        private SampleStreamWrapperCallback() {
        }

        @Override // androidx.media3.exoplayer.hls.HlsSampleStreamWrapper.Callback
        public void onPrepared() {
            if (HlsMediaPeriod.access$106(HlsMediaPeriod.this) > 0) {
                return;
            }
            int i = 0;
            for (HlsSampleStreamWrapper hlsSampleStreamWrapper : HlsMediaPeriod.this.sampleStreamWrappers) {
                i += hlsSampleStreamWrapper.getTrackGroups().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i];
            int i2 = 0;
            for (HlsSampleStreamWrapper hlsSampleStreamWrapper2 : HlsMediaPeriod.this.sampleStreamWrappers) {
                int i3 = hlsSampleStreamWrapper2.getTrackGroups().length;
                int i4 = 0;
                while (i4 < i3) {
                    trackGroupArr[i2] = hlsSampleStreamWrapper2.getTrackGroups().get(i4);
                    i4++;
                    i2++;
                }
            }
            HlsMediaPeriod.this.trackGroups = new TrackGroupArray(trackGroupArr);
            HlsMediaPeriod.this.mediaPeriodCallback.onPrepared(HlsMediaPeriod.this);
        }

        @Override // androidx.media3.exoplayer.hls.HlsSampleStreamWrapper.Callback
        public void onPlaylistRefreshRequired(Uri uri) {
            HlsMediaPeriod.this.playlistTracker.refreshPlaylist(uri);
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
            HlsMediaPeriod.this.mediaPeriodCallback.onContinueLoadingRequested(HlsMediaPeriod.this);
        }
    }
}
