package expo.modules.video;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: VideoExceptions.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/video/PlaybackException;", "Lexpo/modules/kotlin/exception/CodedException;", "reason", "", "cause", "", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlaybackException extends CodedException {
    public /* synthetic */ PlaybackException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : th);
    }

    public PlaybackException(String str, Throwable th) {
        super("A playback exception has occurred: " + (str == null ? "reason unknown" : str), th);
    }
}
