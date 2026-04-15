package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import androidx.media3.decoder.DecoderException;

/* loaded from: classes.dex */
public class MediaCodecDecoderException extends DecoderException {
    public final MediaCodecInfo codecInfo;
    public final String diagnosticInfo;
    public final int errorCode;

    public MediaCodecDecoderException(Throwable th, MediaCodecInfo mediaCodecInfo) {
        super("Decoder failed: " + (mediaCodecInfo == null ? null : mediaCodecInfo.name), th);
        this.codecInfo = mediaCodecInfo;
        this.diagnosticInfo = th instanceof MediaCodec.CodecException ? ((MediaCodec.CodecException) th).getDiagnosticInfo() : null;
        this.errorCode = getErrorCodeV23(th);
    }

    private static int getErrorCodeV23(Throwable th) {
        if (th instanceof MediaCodec.CodecException) {
            return ((MediaCodec.CodecException) th).getErrorCode();
        }
        return 0;
    }
}
