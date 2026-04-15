package androidx.media3.exoplayer.offline;

import android.util.SparseArray;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.exoplayer.dash.offline.DashDownloader$$ExternalSyntheticLambda0;
import androidx.media3.exoplayer.offline.DownloadRequest;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class DefaultDownloaderFactory implements DownloaderFactory {
    private final CacheDataSource.Factory cacheDataSourceFactory;
    private final Executor executor;
    private final SparseArray<SegmentDownloaderFactory> segmentDownloaderFactories;

    @Deprecated
    public DefaultDownloaderFactory(CacheDataSource.Factory factory) {
        this(factory, new DashDownloader$$ExternalSyntheticLambda0());
    }

    public DefaultDownloaderFactory(CacheDataSource.Factory factory, Executor executor) {
        this.cacheDataSourceFactory = (CacheDataSource.Factory) Assertions.checkNotNull(factory);
        this.executor = (Executor) Assertions.checkNotNull(executor);
        this.segmentDownloaderFactories = new SparseArray<>();
    }

    @Override // androidx.media3.exoplayer.offline.DownloaderFactory
    public Downloader createDownloader(DownloadRequest downloadRequest) {
        int inferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(downloadRequest.uri, downloadRequest.mimeType);
        if (inferContentTypeForUriAndMimeType == 0 || inferContentTypeForUriAndMimeType == 1 || inferContentTypeForUriAndMimeType == 2) {
            return createSegmentDownloader(downloadRequest, inferContentTypeForUriAndMimeType);
        }
        if (inferContentTypeForUriAndMimeType == 4) {
            DownloadRequest.ByteRange byteRange = downloadRequest.byteRange;
            return new ProgressiveDownloader(new MediaItem.Builder().setUri(downloadRequest.uri).setCustomCacheKey(downloadRequest.customCacheKey).build(), this.cacheDataSourceFactory, this.executor, byteRange != null ? byteRange.offset : 0L, byteRange != null ? byteRange.length : -1L);
        }
        throw new IllegalArgumentException("Unsupported type: " + inferContentTypeForUriAndMimeType);
    }

    private Downloader createSegmentDownloader(DownloadRequest downloadRequest, int i) {
        SegmentDownloaderFactory segmentDownloaderFactory = getSegmentDownloaderFactory(i, this.cacheDataSourceFactory);
        MediaItem build = new MediaItem.Builder().setUri(downloadRequest.uri).setStreamKeys(downloadRequest.streamKeys).setCustomCacheKey(downloadRequest.customCacheKey).build();
        if (downloadRequest.timeRange != null) {
            segmentDownloaderFactory.setStartPositionUs(downloadRequest.timeRange.startPositionUs).setDurationUs(downloadRequest.timeRange.durationUs);
        }
        return segmentDownloaderFactory.setExecutor(this.executor).create(build);
    }

    private SegmentDownloaderFactory getSegmentDownloaderFactory(int i, CacheDataSource.Factory factory) {
        if (Util.contains(this.segmentDownloaderFactories, i)) {
            return this.segmentDownloaderFactories.get(i);
        }
        try {
            return loadSegmentDownloaderFactory(i, factory);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Module missing for content type " + i, e);
        }
    }

    private SegmentDownloaderFactory loadSegmentDownloaderFactory(int i, CacheDataSource.Factory factory) throws ClassNotFoundException {
        SegmentDownloaderFactory createSegmentDownloaderFactory;
        if (i == 0) {
            createSegmentDownloaderFactory = createSegmentDownloaderFactory(Class.forName("androidx.media3.exoplayer.dash.offline.DashDownloader$Factory").asSubclass(SegmentDownloaderFactory.class), factory);
        } else if (i == 1) {
            createSegmentDownloaderFactory = createSegmentDownloaderFactory(Class.forName("androidx.media3.exoplayer.smoothstreaming.offline.SsDownloader$Factory").asSubclass(SegmentDownloaderFactory.class), factory);
        } else if (i == 2) {
            createSegmentDownloaderFactory = createSegmentDownloaderFactory(Class.forName("androidx.media3.exoplayer.hls.offline.HlsDownloader$Factory").asSubclass(SegmentDownloaderFactory.class), factory);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + i);
        }
        this.segmentDownloaderFactories.put(i, createSegmentDownloaderFactory);
        return createSegmentDownloaderFactory;
    }

    private static SegmentDownloaderFactory createSegmentDownloaderFactory(Class<? extends SegmentDownloaderFactory> cls, CacheDataSource.Factory factory) {
        try {
            return cls.getConstructor(CacheDataSource.Factory.class).newInstance(factory);
        } catch (Exception e) {
            throw new IllegalStateException("Downloader factory missing", e);
        }
    }
}
