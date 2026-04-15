package expo.modules.video;

import androidx.fragment.app.Fragment;
import androidx.media3.common.Player;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PictureInPictureHelperFragment.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lexpo/modules/video/PictureInPictureHelperFragment;", "Landroidx/fragment/app/Fragment;", "videoView", "Lexpo/modules/video/VideoView;", "<init>", "(Lexpo/modules/video/VideoView;)V", "id", "", "getId", "()Ljava/lang/String;", "onPictureInPictureModeChanged", "", "isInPictureInPictureMode", "", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PictureInPictureHelperFragment extends Fragment {
    private final String id;
    private final VideoView videoView;

    public PictureInPictureHelperFragment(VideoView videoView) {
        Intrinsics.checkNotNullParameter(videoView, "videoView");
        this.videoView = videoView;
        this.id = "PictureInPictureHelperFragment_" + UUID.randomUUID();
    }

    public final String getId() {
        return this.id;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        Player player;
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        if (isInPictureInPictureMode) {
            if (this.videoView.getWasAutoPaused() && (player = this.videoView.getPlayerView().getPlayer()) != null) {
                player.play();
            }
            this.videoView.layoutForPiPEnter();
            this.videoView.getOnPictureInPictureStart().invoke(Unit.INSTANCE);
            return;
        }
        this.videoView.setWillEnterPiP(false);
        this.videoView.layoutForPiPExit();
        this.videoView.getOnPictureInPictureStop().invoke(Unit.INSTANCE);
    }
}
