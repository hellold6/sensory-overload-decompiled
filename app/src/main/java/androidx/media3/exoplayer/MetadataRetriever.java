package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.extractor.DefaultExtractorsFactory;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class MetadataRetriever implements AutoCloseable {
    public static final int DEFAULT_MAXIMUM_PARALLEL_RETRIEVALS = 5;
    private final List<ListenableFuture<?>> allFutures;
    private final Clock clock;
    private MetadataRetrieverInternal internalRetriever;
    private final Object lock;
    private final MediaItem mediaItem;
    private final MediaSource.Factory mediaSourceFactory;
    private SettableFuture<InternalResult> preparationFuture;
    private boolean released;

    /* loaded from: classes.dex */
    public static final class Builder {
        private Clock clock;
        private final Context context;
        private final MediaItem mediaItem;
        private MediaSource.Factory mediaSourceFactory;

        public Builder(Context context, MediaItem mediaItem) {
            this.context = context != null ? context.getApplicationContext() : null;
            this.mediaItem = (MediaItem) Assertions.checkNotNull(mediaItem);
            this.clock = Clock.DEFAULT;
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            this.mediaSourceFactory = (MediaSource.Factory) Assertions.checkNotNull(factory);
            return this;
        }

        public Builder setClock(Clock clock) {
            this.clock = (Clock) Assertions.checkNotNull(clock);
            return this;
        }

        public MetadataRetriever build() {
            if (this.mediaSourceFactory == null) {
                Assertions.checkStateNotNull(this.context, "Context must be provided if MediaSource.Factory is not set.");
                this.mediaSourceFactory = new DefaultMediaSourceFactory(this.context, new DefaultExtractorsFactory().setMp4ExtractorFlags(6));
            }
            return new MetadataRetriever(this.mediaItem, (MediaSource.Factory) Assertions.checkNotNull(this.mediaSourceFactory), this.clock);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class InternalResult {
        public final Timeline timeline;
        public final TrackGroupArray trackGroups;

        public InternalResult(TrackGroupArray trackGroupArray, Timeline timeline) {
            this.trackGroups = trackGroupArray;
            this.timeline = timeline;
        }
    }

    private MetadataRetriever(MediaItem mediaItem, MediaSource.Factory factory, Clock clock) {
        this.mediaItem = mediaItem;
        this.mediaSourceFactory = factory;
        this.clock = clock;
        this.lock = new Object();
        this.allFutures = new ArrayList();
    }

    public ListenableFuture<TrackGroupArray> retrieveTrackGroups() {
        synchronized (this.lock) {
            if (this.released) {
                return Futures.immediateFailedFuture(new IllegalStateException("Retriever is released."));
            }
            startPreparation();
            final SettableFuture create = SettableFuture.create();
            this.allFutures.add(create);
            Futures.addCallback((ListenableFuture) Assertions.checkNotNull(this.preparationFuture), new FutureCallback<InternalResult>() { // from class: androidx.media3.exoplayer.MetadataRetriever.1
                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(InternalResult internalResult) {
                    create.set(internalResult.trackGroups);
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable th) {
                    create.setException(th);
                }
            }, MoreExecutors.directExecutor());
            return create;
        }
    }

    public ListenableFuture<Timeline> retrieveTimeline() {
        synchronized (this.lock) {
            if (this.released) {
                return Futures.immediateFailedFuture(new IllegalStateException("Retriever is released."));
            }
            startPreparation();
            final SettableFuture create = SettableFuture.create();
            this.allFutures.add(create);
            Futures.addCallback((ListenableFuture) Assertions.checkNotNull(this.preparationFuture), new FutureCallback<InternalResult>() { // from class: androidx.media3.exoplayer.MetadataRetriever.2
                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(InternalResult internalResult) {
                    create.set(internalResult.timeline);
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable th) {
                    create.setException(th);
                }
            }, MoreExecutors.directExecutor());
            return create;
        }
    }

    public ListenableFuture<Long> retrieveDurationUs() {
        synchronized (this.lock) {
            if (this.released) {
                return Futures.immediateFailedFuture(new IllegalStateException("Retriever is released."));
            }
            ListenableFuture<Timeline> retrieveTimeline = retrieveTimeline();
            final SettableFuture create = SettableFuture.create();
            this.allFutures.add(create);
            Futures.addCallback(retrieveTimeline, new FutureCallback<Timeline>() { // from class: androidx.media3.exoplayer.MetadataRetriever.3
                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(Timeline timeline) {
                    if (timeline.isEmpty()) {
                        create.set(Long.valueOf(C.TIME_UNSET));
                    } else {
                        create.set(Long.valueOf(timeline.getWindow(0, new Timeline.Window()).getDurationUs()));
                    }
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable th) {
                    create.setException(th);
                }
            }, MoreExecutors.directExecutor());
            return create;
        }
    }

    private void startPreparation() {
        if (this.preparationFuture == null) {
            this.preparationFuture = SettableFuture.create();
            MetadataRetrieverInternal metadataRetrieverInternal = new MetadataRetrieverInternal(this.mediaSourceFactory, this.mediaItem, this.clock, new MetadataRetrieverInternal.OnPreparedListener() { // from class: androidx.media3.exoplayer.MetadataRetriever$$ExternalSyntheticLambda0
                @Override // androidx.media3.exoplayer.MetadataRetriever.MetadataRetrieverInternal.OnPreparedListener
                public final void onPrepared(TrackGroupArray trackGroupArray, Timeline timeline) {
                    MetadataRetriever.this.m162x61c6fc37(trackGroupArray, timeline);
                }
            }, new MetadataRetrieverInternal.OnFailureListener() { // from class: androidx.media3.exoplayer.MetadataRetriever$$ExternalSyntheticLambda1
                @Override // androidx.media3.exoplayer.MetadataRetriever.MetadataRetrieverInternal.OnFailureListener
                public final void onFailure(Exception exc) {
                    MetadataRetriever.this.m163xfc67beb8(exc);
                }
            });
            this.internalRetriever = metadataRetrieverInternal;
            metadataRetrieverInternal.queueRetrieval();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startPreparation$0$androidx-media3-exoplayer-MetadataRetriever, reason: not valid java name */
    public /* synthetic */ void m162x61c6fc37(TrackGroupArray trackGroupArray, Timeline timeline) {
        synchronized (this.lock) {
            ((SettableFuture) Assertions.checkNotNull(this.preparationFuture)).set(new InternalResult(trackGroupArray, timeline));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startPreparation$1$androidx-media3-exoplayer-MetadataRetriever, reason: not valid java name */
    public /* synthetic */ void m163xfc67beb8(Exception exc) {
        synchronized (this.lock) {
            ((SettableFuture) Assertions.checkNotNull(this.preparationFuture)).setException(exc);
        }
    }

    @Deprecated
    public static ListenableFuture<TrackGroupArray> retrieveMetadata(Context context, MediaItem mediaItem) {
        return retrieveMetadata(context, mediaItem, Clock.DEFAULT);
    }

    @Deprecated
    public static ListenableFuture<TrackGroupArray> retrieveMetadata(MediaSource.Factory factory, MediaItem mediaItem) {
        return retrieveMetadata(factory, mediaItem, Clock.DEFAULT);
    }

    @Deprecated
    static ListenableFuture<TrackGroupArray> retrieveMetadata(Context context, MediaItem mediaItem, Clock clock) {
        MetadataRetriever build = new Builder(context, mediaItem).setClock(clock).build();
        try {
            ListenableFuture<TrackGroupArray> retrieveTrackGroups = build.retrieveTrackGroups();
            if (build != null) {
                build.close();
            }
            return retrieveTrackGroups;
        } catch (Throwable th) {
            if (build != null) {
                try {
                    build.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Deprecated
    private static ListenableFuture<TrackGroupArray> retrieveMetadata(MediaSource.Factory factory, MediaItem mediaItem, Clock clock) {
        MetadataRetriever build = new Builder(null, mediaItem).setMediaSourceFactory(factory).setClock(clock).build();
        try {
            ListenableFuture<TrackGroupArray> retrieveTrackGroups = build.retrieveTrackGroups();
            if (build != null) {
                build.close();
            }
            return retrieveTrackGroups;
        } catch (Throwable th) {
            if (build != null) {
                try {
                    build.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void setMaximumParallelRetrievals(int i) {
        Assertions.checkArgument(i >= 1);
        SharedWorkerThread.MAX_PARALLEL_RETRIEVALS.set(i);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.lock) {
            if (this.released) {
                return;
            }
            this.released = true;
            Futures.whenAllComplete(this.allFutures).run(new Runnable() { // from class: androidx.media3.exoplayer.MetadataRetriever$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    MetadataRetriever.this.m161lambda$close$2$androidxmedia3exoplayerMetadataRetriever();
                }
            }, MoreExecutors.directExecutor());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$close$2$androidx-media3-exoplayer-MetadataRetriever, reason: not valid java name */
    public /* synthetic */ void m161lambda$close$2$androidxmedia3exoplayerMetadataRetriever() {
        synchronized (this.lock) {
            MetadataRetrieverInternal metadataRetrieverInternal = this.internalRetriever;
            if (metadataRetrieverInternal != null) {
                metadataRetrieverInternal.release();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class MetadataRetrieverInternal {
        private static final int MESSAGE_CHECK_FOR_FAILURE = 2;
        private static final int MESSAGE_CONTINUE_LOADING = 3;
        private static final int MESSAGE_PREPARE_SOURCE = 1;
        private static final int MESSAGE_RELEASE = 4;
        private static final SharedWorkerThread SHARED_WORKER_THREAD = new SharedWorkerThread();
        private final MediaItem mediaItem;
        private final MediaSource.Factory mediaSourceFactory;
        private final HandlerWrapper mediaSourceHandler;
        private final OnFailureListener onFailureListener;
        private final OnPreparedListener onPreparedListener;

        /* loaded from: classes.dex */
        public interface OnFailureListener {
            void onFailure(Exception exc);
        }

        /* loaded from: classes.dex */
        public interface OnPreparedListener {
            void onPrepared(TrackGroupArray trackGroupArray, Timeline timeline);
        }

        public MetadataRetrieverInternal(MediaSource.Factory factory, MediaItem mediaItem, Clock clock, OnPreparedListener onPreparedListener, OnFailureListener onFailureListener) {
            this.mediaSourceFactory = factory;
            this.mediaItem = mediaItem;
            this.onPreparedListener = onPreparedListener;
            this.onFailureListener = onFailureListener;
            this.mediaSourceHandler = clock.createHandler(SHARED_WORKER_THREAD.addWorker(), new MediaSourceHandlerCallback());
        }

        public void queueRetrieval() {
            SHARED_WORKER_THREAD.startRetrieval(this);
        }

        public void start() {
            this.mediaSourceHandler.obtainMessage(1, this.mediaItem).sendToTarget();
        }

        public void release() {
            this.mediaSourceHandler.obtainMessage(4).sendToTarget();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public final class MediaSourceHandlerCallback implements Handler.Callback {
            private static final int ERROR_POLL_INTERVAL_MS = 100;
            private MediaPeriod mediaPeriod;
            private MediaSource mediaSource;
            private final MediaSourceCaller mediaSourceCaller = new MediaSourceCaller();
            private boolean released;
            private Timeline timeline;

            public MediaSourceHandlerCallback() {
            }

            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (this.released) {
                    return true;
                }
                int i = message.what;
                if (i == 1) {
                    MediaSource createMediaSource = MetadataRetrieverInternal.this.mediaSourceFactory.createMediaSource((MediaItem) message.obj);
                    this.mediaSource = createMediaSource;
                    createMediaSource.prepareSource(this.mediaSourceCaller, null, PlayerId.UNSET);
                    MetadataRetrieverInternal.this.mediaSourceHandler.sendEmptyMessage(2);
                    return true;
                }
                if (i == 2) {
                    try {
                        MediaPeriod mediaPeriod = this.mediaPeriod;
                        if (mediaPeriod == null) {
                            ((MediaSource) Assertions.checkNotNull(this.mediaSource)).maybeThrowSourceInfoRefreshError();
                        } else {
                            mediaPeriod.maybeThrowPrepareError();
                        }
                        MetadataRetrieverInternal.this.mediaSourceHandler.sendEmptyMessageDelayed(2, 100);
                    } catch (IOException e) {
                        MetadataRetrieverInternal.this.onFailureListener.onFailure(e);
                        MetadataRetrieverInternal.this.mediaSourceHandler.obtainMessage(4).sendToTarget();
                    }
                    return true;
                }
                if (i == 3) {
                    ((MediaPeriod) Assertions.checkNotNull(this.mediaPeriod)).continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(0L).build());
                    return true;
                }
                if (i != 4) {
                    return false;
                }
                if (this.mediaPeriod != null) {
                    ((MediaSource) Assertions.checkNotNull(this.mediaSource)).releasePeriod(this.mediaPeriod);
                }
                MediaSource mediaSource = this.mediaSource;
                if (mediaSource != null) {
                    mediaSource.releaseSource(this.mediaSourceCaller);
                }
                MetadataRetrieverInternal.this.mediaSourceHandler.removeCallbacksAndMessages(null);
                MetadataRetrieverInternal.SHARED_WORKER_THREAD.removeWorker();
                this.released = true;
                return true;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* loaded from: classes.dex */
            public final class MediaSourceCaller implements MediaSource.MediaSourceCaller {
                private boolean mediaPeriodCreated;
                private final MediaPeriodCallback mediaPeriodCallback = new MediaPeriodCallback();
                private final Allocator allocator = new DefaultAllocator(true, 65536);

                public MediaSourceCaller() {
                }

                @Override // androidx.media3.exoplayer.source.MediaSource.MediaSourceCaller
                public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
                    MediaSourceHandlerCallback.this.timeline = timeline;
                    if (this.mediaPeriodCreated) {
                        return;
                    }
                    this.mediaPeriodCreated = true;
                    MediaSourceHandlerCallback.this.mediaPeriod = mediaSource.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(0)), this.allocator, 0L);
                    MediaSourceHandlerCallback.this.mediaPeriod.prepare(this.mediaPeriodCallback, 0L);
                }

                /* loaded from: classes.dex */
                private final class MediaPeriodCallback implements MediaPeriod.Callback {
                    private MediaPeriodCallback() {
                    }

                    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
                    public void onPrepared(MediaPeriod mediaPeriod) {
                        MetadataRetrieverInternal.this.onPreparedListener.onPrepared(mediaPeriod.getTrackGroups(), (Timeline) Assertions.checkNotNull(MediaSourceHandlerCallback.this.timeline));
                        MetadataRetrieverInternal.this.mediaSourceHandler.obtainMessage(4).sendToTarget();
                    }

                    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
                    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
                        MetadataRetrieverInternal.this.mediaSourceHandler.obtainMessage(3).sendToTarget();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class SharedWorkerThread {
        public static final AtomicInteger MAX_PARALLEL_RETRIEVALS = new AtomicInteger(5);
        private HandlerThread mediaSourceThread;
        private final Deque<MetadataRetrieverInternal> pendingRetrievals = new ArrayDeque();
        private int referenceCount;

        public synchronized Looper addWorker() {
            if (this.mediaSourceThread == null) {
                Assertions.checkState(this.referenceCount == 0);
                HandlerThread handlerThread = new HandlerThread("ExoPlayer:MetadataRetriever");
                this.mediaSourceThread = handlerThread;
                handlerThread.start();
            }
            this.referenceCount++;
            return ((HandlerThread) Assertions.checkNotNull(this.mediaSourceThread)).getLooper();
        }

        public synchronized void startRetrieval(MetadataRetrieverInternal metadataRetrieverInternal) {
            this.pendingRetrievals.addLast(metadataRetrieverInternal);
            maybeStartNewRetrieval();
        }

        public synchronized void removeWorker() {
            int i = this.referenceCount - 1;
            this.referenceCount = i;
            if (i == 0) {
                ((HandlerThread) Assertions.checkNotNull(this.mediaSourceThread)).quit();
                this.mediaSourceThread = null;
                this.pendingRetrievals.clear();
            } else {
                maybeStartNewRetrieval();
            }
        }

        private void maybeStartNewRetrieval() {
            if (!this.pendingRetrievals.isEmpty() && this.referenceCount - this.pendingRetrievals.size() < MAX_PARALLEL_RETRIEVALS.get()) {
                this.pendingRetrievals.removeFirst().start();
            }
        }
    }
}
