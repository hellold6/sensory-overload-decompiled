package expo.modules.video.playbackService;

import android.os.Binder;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoVideoPlaybackService.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/video/playbackService/PlaybackServiceBinder;", "Landroid/os/Binder;", NotificationCompat.CATEGORY_SERVICE, "Lexpo/modules/video/playbackService/ExpoVideoPlaybackService;", "<init>", "(Lexpo/modules/video/playbackService/ExpoVideoPlaybackService;)V", "getService", "()Lexpo/modules/video/playbackService/ExpoVideoPlaybackService;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlaybackServiceBinder extends Binder {
    private final ExpoVideoPlaybackService service;

    public PlaybackServiceBinder(ExpoVideoPlaybackService service) {
        Intrinsics.checkNotNullParameter(service, "service");
        this.service = service;
    }

    public final ExpoVideoPlaybackService getService() {
        return this.service;
    }
}
