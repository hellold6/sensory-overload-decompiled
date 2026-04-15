package expo.modules.audio;

import expo.modules.kotlin.exception.CodedException;

/* compiled from: AudioExceptions.kt */
@kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lexpo/modules/audio/GetAudioInputNotSupportedException;", "Lexpo/modules/kotlin/exception/CodedException;", "<init>", "()V", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GetAudioInputNotSupportedException extends CodedException {
    public GetAudioInputNotSupportedException() {
        super("Getting current audio input is not supported on devices running Android version lower than Android 9.0", null, 2, null);
    }
}
