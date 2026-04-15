package androidx.media3.common.audio;

import androidx.media3.common.C;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import java.nio.ByteBuffer;
import java.util.Objects;

/* loaded from: classes.dex */
public final class GainProcessor extends BaseAudioProcessor {
    private final GainProvider gainProvider;
    private long readFrames;

    /* loaded from: classes.dex */
    public interface GainProvider {
        float getGainFactorAtSamplePosition(long j, int i);

        long isUnityUntil(long j, int i);
    }

    public GainProcessor(GainProvider gainProvider) {
        this.gainProvider = (GainProvider) Assertions.checkNotNull(gainProvider);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        int i = audioFormat.encoding;
        if (i == 2 || i == 4) {
            return audioFormat;
        }
        throw new AudioProcessor.UnhandledAudioFormatException("Invalid PCM encoding. Expected 16 bit PCM or float PCM.", audioFormat);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        return (!super.isActive() || Objects.equals(this.inputAudioFormat, AudioProcessor.AudioFormat.NOT_SET) || this.gainProvider.isUnityUntil(0L, this.inputAudioFormat.sampleRate) == Long.MIN_VALUE) ? false : true;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        Assertions.checkState(!Objects.equals(this.inputAudioFormat, AudioProcessor.AudioFormat.NOT_SET), "Audio processor must be configured and flushed before calling queueInput().");
        if (byteBuffer.hasRemaining()) {
            Assertions.checkArgument(byteBuffer.remaining() % this.inputAudioFormat.bytesPerFrame == 0, "Queued an incomplete frame.");
            ByteBuffer replaceOutputBuffer = replaceOutputBuffer(byteBuffer.remaining());
            while (byteBuffer.hasRemaining()) {
                float gainFactorAtSamplePosition = this.gainProvider.getGainFactorAtSamplePosition(this.readFrames, this.inputAudioFormat.sampleRate);
                if (gainFactorAtSamplePosition == 1.0f) {
                    int limit = byteBuffer.limit();
                    long isUnityUntil = this.gainProvider.isUnityUntil(this.readFrames, this.inputAudioFormat.sampleRate);
                    Assertions.checkState(isUnityUntil != C.TIME_UNSET, "Expected a valid end boundary for unity region.");
                    if (isUnityUntil != Long.MIN_VALUE) {
                        byteBuffer.limit(Math.min(limit, ((int) ((isUnityUntil - this.readFrames) * this.inputAudioFormat.bytesPerFrame)) + byteBuffer.position()));
                    }
                    this.readFrames += byteBuffer.remaining() / this.inputAudioFormat.bytesPerFrame;
                    replaceOutputBuffer.put(byteBuffer);
                    byteBuffer.limit(limit);
                } else {
                    for (int i = 0; i < this.inputAudioFormat.channelCount; i++) {
                        int i2 = this.inputAudioFormat.encoding;
                        if (i2 == 2) {
                            replaceOutputBuffer.putShort((short) (byteBuffer.getShort() * gainFactorAtSamplePosition));
                        } else if (i2 == 4) {
                            replaceOutputBuffer.putFloat(byteBuffer.getFloat() * gainFactorAtSamplePosition);
                        } else {
                            throw new IllegalStateException("Unexpected PCM encoding: " + this.inputAudioFormat.encoding);
                        }
                    }
                    this.readFrames++;
                }
            }
            replaceOutputBuffer.flip();
        }
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        this.readFrames = 0L;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.readFrames = 0L;
    }
}
