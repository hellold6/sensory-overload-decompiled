package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.SpeedProviderUtil;
import androidx.media3.common.util.TimestampConsumer;
import androidx.media3.common.util.Util;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes.dex */
public final class SpeedChangingAudioProcessor implements AudioProcessor {
    private float currentSpeed;
    private boolean endOfStreamQueuedToSonic;
    private long framesRead;
    private boolean inputEnded;
    private final Object lock;
    private final LongArrayQueue pendingCallbackInputTimesUs;
    private final Queue<TimestampConsumer> pendingCallbacks;
    private final SynchronizedSonicAudioProcessor sonicAudioProcessor;
    private final SpeedProvider speedProvider;
    private AudioProcessor.AudioFormat pendingInputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
    private AudioProcessor.AudioFormat inputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;

    public SpeedChangingAudioProcessor(SpeedProvider speedProvider) {
        this.speedProvider = speedProvider;
        Object obj = new Object();
        this.lock = obj;
        this.sonicAudioProcessor = new SynchronizedSonicAudioProcessor(obj, true);
        this.pendingCallbackInputTimesUs = new LongArrayQueue();
        this.pendingCallbacks = new ArrayDeque();
        resetInternalState(true);
    }

    public static long getSampleCountAfterProcessorApplied(SpeedProvider speedProvider, int i, long j) {
        Assertions.checkArgument(speedProvider != null);
        Assertions.checkArgument(i > 0);
        long j2 = 0;
        Assertions.checkArgument(j >= 0);
        long j3 = 0;
        while (j2 < j) {
            long nextSpeedChangeSamplePosition = SpeedProviderUtil.getNextSpeedChangeSamplePosition(speedProvider, j2, i);
            if (nextSpeedChangeSamplePosition == -1 || nextSpeedChangeSamplePosition > j) {
                nextSpeedChangeSamplePosition = j;
            }
            float sampleAlignedSpeed = SpeedProviderUtil.getSampleAlignedSpeed(speedProvider, j2, i);
            j3 += Sonic.getExpectedFrameCountAfterProcessorApplied(i, i, sampleAlignedSpeed, sampleAlignedSpeed, nextSpeedChangeSamplePosition - j2);
            j2 = nextSpeedChangeSamplePosition;
        }
        return j3;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        this.pendingInputAudioFormat = audioFormat;
        AudioProcessor.AudioFormat configure = this.sonicAudioProcessor.configure(audioFormat);
        this.pendingOutputAudioFormat = configure;
        return configure;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        return !this.pendingOutputAudioFormat.equals(AudioProcessor.AudioFormat.NOT_SET);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public long getDurationAfterProcessorApplied(long j) {
        return SpeedProviderUtil.getDurationAfterSpeedProviderApplied(this.speedProvider, j);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        AudioProcessor.AudioFormat audioFormat;
        int i;
        synchronized (this.lock) {
            audioFormat = this.inputAudioFormat;
        }
        float sampleAlignedSpeed = SpeedProviderUtil.getSampleAlignedSpeed(this.speedProvider, this.framesRead, audioFormat.sampleRate);
        long nextSpeedChangeSamplePosition = SpeedProviderUtil.getNextSpeedChangeSamplePosition(this.speedProvider, this.framesRead, audioFormat.sampleRate);
        updateSpeed(sampleAlignedSpeed);
        int limit = byteBuffer.limit();
        if (nextSpeedChangeSamplePosition != -1) {
            i = (int) ((nextSpeedChangeSamplePosition - this.framesRead) * audioFormat.bytesPerFrame);
            byteBuffer.limit(Math.min(limit, byteBuffer.position() + i));
        } else {
            i = -1;
        }
        long position = byteBuffer.position();
        this.sonicAudioProcessor.queueInput(byteBuffer);
        if (i != -1 && byteBuffer.position() - position == i) {
            this.sonicAudioProcessor.queueEndOfStream();
            this.endOfStreamQueuedToSonic = true;
        }
        long position2 = byteBuffer.position() - position;
        Assertions.checkState(position2 % ((long) audioFormat.bytesPerFrame) == 0, "A frame was not queued completely.");
        this.framesRead += position2 / audioFormat.bytesPerFrame;
        byteBuffer.limit(limit);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueEndOfStream() {
        this.inputEnded = true;
        if (this.endOfStreamQueuedToSonic) {
            return;
        }
        this.sonicAudioProcessor.queueEndOfStream();
        this.endOfStreamQueuedToSonic = true;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public ByteBuffer getOutput() {
        return this.sonicAudioProcessor.getOutput();
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isEnded() {
        return this.inputEnded && this.sonicAudioProcessor.isEnded();
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void flush() {
        this.inputEnded = false;
        resetInternalState(false);
        synchronized (this.lock) {
            this.inputAudioFormat = this.pendingInputAudioFormat;
            this.sonicAudioProcessor.flush();
            processPendingCallbacks();
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void reset() {
        flush();
        this.pendingInputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingOutputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
        synchronized (this.lock) {
            this.inputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
            this.pendingCallbackInputTimesUs.clear();
            this.pendingCallbacks.clear();
        }
        resetInternalState(true);
        this.sonicAudioProcessor.reset();
    }

    public void getSpeedAdjustedTimeAsync(long j, TimestampConsumer timestampConsumer) {
        synchronized (this.lock) {
            int i = this.inputAudioFormat.sampleRate;
            if (i == -1) {
                this.pendingCallbackInputTimesUs.add(j);
                this.pendingCallbacks.add(timestampConsumer);
            } else {
                timestampConsumer.onTimestamp(getDurationUsAfterProcessorApplied(this.speedProvider, i, j));
            }
        }
    }

    public long getMediaDurationUs(long j) {
        int i;
        synchronized (this.lock) {
            i = this.inputAudioFormat.sampleRate;
        }
        if (i == -1) {
            return j;
        }
        return Util.sampleCountToDurationUs(getInputFrameCountForOutput(this.speedProvider, i, Util.scaleLargeValue(j, i, 1000000L, RoundingMode.HALF_EVEN)), i);
    }

    static long getInputFrameCountForOutput(SpeedProvider speedProvider, int i, long j) {
        int i2 = i;
        Assertions.checkArgument(i2 > 0);
        Assertions.checkArgument(j >= 0);
        long j2 = j;
        long j3 = 0;
        while (j2 > 0) {
            long nextSpeedChangeSamplePosition = SpeedProviderUtil.getNextSpeedChangeSamplePosition(speedProvider, j3, i);
            float sampleAlignedSpeed = SpeedProviderUtil.getSampleAlignedSpeed(speedProvider, j3, i);
            long expectedFrameCountAfterProcessorApplied = Sonic.getExpectedFrameCountAfterProcessorApplied(i2, i, sampleAlignedSpeed, sampleAlignedSpeed, nextSpeedChangeSamplePosition - j3);
            if (nextSpeedChangeSamplePosition == -1 || expectedFrameCountAfterProcessorApplied > j2) {
                j3 += Sonic.getExpectedInputFrameCountForOutputFrameCount(i, i, sampleAlignedSpeed, sampleAlignedSpeed, j2);
                j2 = 0;
            } else {
                j2 -= expectedFrameCountAfterProcessorApplied;
                j3 = nextSpeedChangeSamplePosition;
            }
            i2 = i;
        }
        return j3;
    }

    private static long getDurationUsAfterProcessorApplied(SpeedProvider speedProvider, int i, long j) {
        return Util.sampleCountToDurationUs(getSampleCountAfterProcessorApplied(speedProvider, i, Util.scaleLargeValue(j, i, 1000000L, RoundingMode.HALF_EVEN)), i);
    }

    private void processPendingCallbacks() {
        synchronized (this.lock) {
            if (this.inputAudioFormat.sampleRate == -1) {
                return;
            }
            while (!this.pendingCallbacks.isEmpty()) {
                this.pendingCallbacks.remove().onTimestamp(getDurationUsAfterProcessorApplied(this.speedProvider, this.inputAudioFormat.sampleRate, this.pendingCallbackInputTimesUs.remove()));
            }
        }
    }

    private void updateSpeed(float f) {
        if (f != this.currentSpeed) {
            this.currentSpeed = f;
            this.sonicAudioProcessor.setSpeed(f);
            this.sonicAudioProcessor.setPitch(f);
            this.sonicAudioProcessor.flush();
            this.endOfStreamQueuedToSonic = false;
        }
    }

    private void resetInternalState(boolean z) {
        if (z) {
            this.currentSpeed = 1.0f;
        }
        this.framesRead = 0L;
        this.endOfStreamQueuedToSonic = false;
    }
}
