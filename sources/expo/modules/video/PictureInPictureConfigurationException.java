package expo.modules.video;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

/* compiled from: VideoExceptions.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lexpo/modules/video/PictureInPictureConfigurationException;", "Lexpo/modules/kotlin/exception/CodedException;", "<init>", "()V", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PictureInPictureConfigurationException extends CodedException {
    public PictureInPictureConfigurationException() {
        super("Current activity does not support picture-in-picture. Make sure you have configured the `expo-video` config plugin correctly.", null, 2, null);
    }
}
