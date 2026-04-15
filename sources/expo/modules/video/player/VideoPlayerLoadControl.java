package expo.modules.video.player;

import androidx.media3.common.util.Util;
import expo.modules.video.player.DefaultLoadControl;
import expo.modules.video.records.BufferOptions;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoPlayerLoadControl.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR$\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\n¨\u0006\u0015"}, d2 = {"Lexpo/modules/video/player/VideoPlayerLoadControl;", "Lexpo/modules/video/player/DefaultLoadControl;", "<init>", "()V", "value", "", "targetBufferMs", "getTargetBufferMs", "()J", "setTargetBufferMs", "(J)V", "bufferForPlaybackMs", "getBufferForPlaybackMs", "setBufferForPlaybackMs", "bufferForPlaybackAfterRebufferMs", "getBufferForPlaybackAfterRebufferMs", "setBufferForPlaybackAfterRebufferMs", "applyBufferOptions", "", "bufferOptions", "Lexpo/modules/video/records/BufferOptions;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoPlayerLoadControl extends DefaultLoadControl {
    private final long getTargetBufferMs() {
        return this.maxBufferUs / 1000;
    }

    private final void setTargetBufferMs(long j) {
        this.minBufferUs = Util.msToUs(j);
        this.maxBufferUs = Util.msToUs(j);
    }

    private final long getBufferForPlaybackMs() {
        return this.bufferForPlaybackUs / 1000;
    }

    private final void setBufferForPlaybackMs(long j) {
        this.bufferForPlaybackUs = Util.msToUs(j);
    }

    private final long getBufferForPlaybackAfterRebufferMs() {
        return this.bufferForPlaybackAfterRebufferUs / 1000;
    }

    private final void setBufferForPlaybackAfterRebufferMs(long j) {
        this.bufferForPlaybackAfterRebufferUs = Util.msToUs(j);
    }

    public final void applyBufferOptions(BufferOptions bufferOptions) {
        long minBufferForPlayback;
        Intrinsics.checkNotNullParameter(bufferOptions, "bufferOptions");
        Double preferredForwardBufferDuration = bufferOptions.getPreferredForwardBufferDuration();
        setTargetBufferMs(preferredForwardBufferDuration != null ? (long) (preferredForwardBufferDuration.doubleValue() * 1000) : 50000L);
        this.targetBufferBytesOverwrite = bufferOptions.getMaxBufferBytes() == 0 ? -1 : (int) bufferOptions.getMaxBufferBytes();
        if (this.targetBufferBytesOverwrite != -1) {
            Iterator<DefaultLoadControl.PlayerLoadingState> it = this.loadingStates.values().iterator();
            while (it.hasNext()) {
                it.next().targetBufferBytes = this.targetBufferBytesOverwrite;
            }
        }
        this.prioritizeTimeOverSizeThresholds = bufferOptions.getPrioritizeTimeOverSizeThreshold();
        double d = 1000;
        if (bufferOptions.getMinBufferForPlayback() * d > getTargetBufferMs()) {
            minBufferForPlayback = getTargetBufferMs();
        } else {
            minBufferForPlayback = (long) (bufferOptions.getMinBufferForPlayback() * d);
        }
        setBufferForPlaybackMs(minBufferForPlayback);
        setBufferForPlaybackAfterRebufferMs(minBufferForPlayback);
        updateAllocator();
    }
}
