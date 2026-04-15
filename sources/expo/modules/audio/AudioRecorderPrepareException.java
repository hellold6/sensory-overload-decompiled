package expo.modules.audio;

import expo.modules.kotlin.exception.CodedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioExceptions.kt */
@kotlin.Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/audio/AudioRecorderPrepareException;", "Lexpo/modules/kotlin/exception/CodedException;", "cause", "", "<init>", "(Ljava/lang/Throwable;)V", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioRecorderPrepareException extends CodedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioRecorderPrepareException(Throwable cause) {
        super("Failed to prepare the AudioRecorder", cause);
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}
