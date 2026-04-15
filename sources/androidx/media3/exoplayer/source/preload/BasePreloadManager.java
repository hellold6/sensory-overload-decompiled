package androidx.media3.exoplayer.source.preload;

import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.base.Supplier;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

/* loaded from: classes.dex */
public abstract class BasePreloadManager<T, PreloadStatusT> {
    private final Handler applicationHandler;
    private final ListenerSet<PreloadManagerListener> listeners;
    private final Object lock = new Object();
    private final Map<MediaItem, BasePreloadManager<T, PreloadStatusT>.MediaSourceHolder> mediaItemMediaSourceHolderMap;
    private final MediaSource.Factory mediaSourceFactory;
    protected final Comparator<T> rankingDataComparator;
    private final PriorityQueue<BasePreloadManager<T, PreloadStatusT>.MediaSourceHolder> sourceHolderPriorityQueue;
    private final TargetPreloadStatusControl<T, PreloadStatusT> targetPreloadStatusControl;
    private PreloadStatusT targetPreloadStatusOfCurrentPreloadingSource;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$0(PreloadManagerListener preloadManagerListener, FlagSet flagSet) {
    }

    protected abstract void clearSourceInternal(MediaSource mediaSource);

    protected MediaSource createMediaSourceForPreloading(MediaSource mediaSource) {
        return mediaSource;
    }

    protected abstract void preloadSourceInternal(MediaSource mediaSource, PreloadStatusT preloadstatust);

    protected void releaseInternal() {
    }

    protected abstract void releaseSourceInternal(MediaSource mediaSource);

    protected boolean shouldStartPreloadingNextSource() {
        return true;
    }

    /* loaded from: classes.dex */
    protected static abstract class BuilderBase<T, PreloadStatusT> {
        protected Supplier<MediaSource.Factory> mediaSourceFactorySupplier;
        protected final Comparator<T> rankingDataComparator;
        protected final TargetPreloadStatusControl<T, PreloadStatusT> targetPreloadStatusControl;

        public abstract BasePreloadManager<T, PreloadStatusT> build();

        public BuilderBase(Comparator<T> comparator, TargetPreloadStatusControl<T, PreloadStatusT> targetPreloadStatusControl, Supplier<MediaSource.Factory> supplier) {
            this.rankingDataComparator = comparator;
            this.targetPreloadStatusControl = targetPreloadStatusControl;
            this.mediaSourceFactorySupplier = supplier;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BasePreloadManager(Comparator<T> comparator, TargetPreloadStatusControl<T, PreloadStatusT> targetPreloadStatusControl, MediaSource.Factory factory) {
        Handler createHandlerForCurrentOrMainLooper = Util.createHandlerForCurrentOrMainLooper();
        this.applicationHandler = createHandlerForCurrentOrMainLooper;
        this.rankingDataComparator = comparator;
        this.targetPreloadStatusControl = targetPreloadStatusControl;
        this.mediaSourceFactory = factory;
        this.listeners = new ListenerSet<>(createHandlerForCurrentOrMainLooper.getLooper(), Clock.DEFAULT, new ListenerSet.IterationFinishedEvent() { // from class: androidx.media3.exoplayer.source.preload.BasePreloadManager$$ExternalSyntheticLambda5
            @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
            public final void invoke(Object obj, FlagSet flagSet) {
                BasePreloadManager.lambda$new$0((PreloadManagerListener) obj, flagSet);
            }
        });
        this.mediaItemMediaSourceHolderMap = new HashMap();
        this.sourceHolderPriorityQueue = new PriorityQueue<>();
    }

    public void addListener(PreloadManagerListener preloadManagerListener) {
        this.listeners.add(preloadManagerListener);
    }

    public void removeListener(PreloadManagerListener preloadManagerListener) {
        verifyApplicationThread();
        this.listeners.remove(preloadManagerListener);
    }

    public void clearListeners() {
        verifyApplicationThread();
        this.listeners.clear();
    }

    public final int getSourceCount() {
        return this.mediaItemMediaSourceHolderMap.size();
    }

    public final void add(MediaItem mediaItem, T t) {
        add(this.mediaSourceFactory.createMediaSource(mediaItem), (MediaSource) t);
    }

    public final void add(MediaSource mediaSource, T t) {
        MediaSource createMediaSourceForPreloading = createMediaSourceForPreloading(mediaSource);
        this.mediaItemMediaSourceHolderMap.put(createMediaSourceForPreloading.getMediaItem(), new MediaSourceHolder(createMediaSourceForPreloading, t));
    }

    public final void invalidate() {
        synchronized (this.lock) {
            this.sourceHolderPriorityQueue.clear();
            this.sourceHolderPriorityQueue.addAll(this.mediaItemMediaSourceHolderMap.values());
            while (!this.sourceHolderPriorityQueue.isEmpty() && !maybeStartPreloadNextSource()) {
                this.sourceHolderPriorityQueue.poll();
            }
        }
    }

    public final MediaSource getMediaSource(MediaItem mediaItem) {
        if (this.mediaItemMediaSourceHolderMap.containsKey(mediaItem)) {
            return this.mediaItemMediaSourceHolderMap.get(mediaItem).mediaSource;
        }
        return null;
    }

    public final boolean remove(MediaItem mediaItem) {
        if (!this.mediaItemMediaSourceHolderMap.containsKey(mediaItem)) {
            return false;
        }
        MediaSource mediaSource = this.mediaItemMediaSourceHolderMap.get(mediaItem).mediaSource;
        this.mediaItemMediaSourceHolderMap.remove(mediaItem);
        releaseSourceInternal(mediaSource);
        return true;
    }

    public final boolean remove(MediaSource mediaSource) {
        MediaItem mediaItem = mediaSource.getMediaItem();
        if (!this.mediaItemMediaSourceHolderMap.containsKey(mediaItem) || mediaSource != this.mediaItemMediaSourceHolderMap.get(mediaItem).mediaSource) {
            return false;
        }
        this.mediaItemMediaSourceHolderMap.remove(mediaItem);
        releaseSourceInternal(mediaSource);
        return true;
    }

    public final void reset() {
        Iterator<BasePreloadManager<T, PreloadStatusT>.MediaSourceHolder> it = this.mediaItemMediaSourceHolderMap.values().iterator();
        while (it.hasNext()) {
            releaseSourceInternal(it.next().mediaSource);
        }
        this.mediaItemMediaSourceHolderMap.clear();
        synchronized (this.lock) {
            this.sourceHolderPriorityQueue.clear();
            this.targetPreloadStatusOfCurrentPreloadingSource = null;
        }
    }

    public final void release() {
        reset();
        releaseInternal();
        clearListeners();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onPreloadCompleted(final MediaSource mediaSource) {
        synchronized (this.lock) {
            if (isPreloading(mediaSource)) {
                this.applicationHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.source.preload.BasePreloadManager$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        BasePreloadManager.this.m254xf9589f29(mediaSource);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onPreloadCompleted$2$androidx-media3-exoplayer-source-preload-BasePreloadManager, reason: not valid java name */
    public /* synthetic */ void m254xf9589f29(final MediaSource mediaSource) {
        this.listeners.sendEvent(-1, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.source.preload.BasePreloadManager$$ExternalSyntheticLambda0
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                ((PreloadManagerListener) obj).onCompleted(MediaSource.this.getMediaItem());
            }
        });
        m256x7039cf91(mediaSource);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onPreloadError(final PreloadException preloadException, final MediaSource mediaSource) {
        synchronized (this.lock) {
            if (isPreloading(mediaSource)) {
                this.applicationHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.source.preload.BasePreloadManager$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        BasePreloadManager.this.m255x773570a8(preloadException, mediaSource);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onPreloadError$4$androidx-media3-exoplayer-source-preload-BasePreloadManager, reason: not valid java name */
    public /* synthetic */ void m255x773570a8(final PreloadException preloadException, MediaSource mediaSource) {
        this.listeners.sendEvent(-1, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.source.preload.BasePreloadManager$$ExternalSyntheticLambda1
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                ((PreloadManagerListener) obj).onError(PreloadException.this);
            }
        });
        m256x7039cf91(mediaSource);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onPreloadSkipped(final MediaSource mediaSource) {
        synchronized (this.lock) {
            if (isPreloading(mediaSource)) {
                Util.postOrRun(this.applicationHandler, new Runnable() { // from class: androidx.media3.exoplayer.source.preload.BasePreloadManager$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        BasePreloadManager.this.m256x7039cf91(mediaSource);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: maybeAdvanceToNextSource, reason: merged with bridge method [inline-methods] */
    public void m256x7039cf91(MediaSource mediaSource) {
        synchronized (this.lock) {
            if (!isPreloading(mediaSource)) {
                return;
            }
            do {
                this.sourceHolderPriorityQueue.poll();
                if (this.sourceHolderPriorityQueue.isEmpty()) {
                    break;
                }
            } while (!maybeStartPreloadNextSource());
        }
    }

    private boolean isPreloading(MediaSource mediaSource) {
        return !this.sourceHolderPriorityQueue.isEmpty() && ((MediaSourceHolder) Assertions.checkNotNull(this.sourceHolderPriorityQueue.peek())).mediaSource == mediaSource;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final PreloadStatusT getTargetPreloadStatus(MediaSource mediaSource) {
        synchronized (this.lock) {
            if (!isPreloading(mediaSource)) {
                return null;
            }
            return this.targetPreloadStatusOfCurrentPreloadingSource;
        }
    }

    private boolean maybeStartPreloadNextSource() {
        if (!shouldStartPreloadingNextSource()) {
            return false;
        }
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.sourceHolderPriorityQueue.peek());
        this.targetPreloadStatusOfCurrentPreloadingSource = this.targetPreloadStatusControl.getTargetPreloadStatus(mediaSourceHolder.rankingData);
        preloadSourceInternal(mediaSourceHolder.mediaSource, this.targetPreloadStatusOfCurrentPreloadingSource);
        return true;
    }

    private void verifyApplicationThread() {
        if (Looper.myLooper() != this.applicationHandler.getLooper()) {
            throw new IllegalStateException("Preload manager is accessed on the wrong thread.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MediaSourceHolder implements Comparable<BasePreloadManager<T, PreloadStatusT>.MediaSourceHolder> {
        public final MediaSource mediaSource;
        public final T rankingData;

        public MediaSourceHolder(MediaSource mediaSource, T t) {
            this.mediaSource = mediaSource;
            this.rankingData = t;
        }

        @Override // java.lang.Comparable
        public int compareTo(BasePreloadManager<T, PreloadStatusT>.MediaSourceHolder mediaSourceHolder) {
            return BasePreloadManager.this.rankingDataComparator.compare(this.rankingData, mediaSourceHolder.rankingData);
        }
    }
}
