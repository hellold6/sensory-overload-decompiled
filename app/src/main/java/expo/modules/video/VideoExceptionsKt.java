package expo.modules.video;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoExceptions.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u001c\u0010\u0002\u001a\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"defaultServiceBindingTip", "", "getPlaybackServiceErrorMessage", "message", "tip", "expo-video_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoExceptionsKt {
    private static final String defaultServiceBindingTip = "Make sure that the expo-video config plugin is properly configured to avoid issues with displaying the now playing notification and sustaining background playback.";

    public static /* synthetic */ String getPlaybackServiceErrorMessage$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = defaultServiceBindingTip;
        }
        return getPlaybackServiceErrorMessage(str, str2);
    }

    public static final String getPlaybackServiceErrorMessage(String str, String tip) {
        Intrinsics.checkNotNullParameter(tip, "tip");
        if (str == null) {
            str = "Expo-video playback service binder error";
        }
        return str + ". " + tip;
    }
}
