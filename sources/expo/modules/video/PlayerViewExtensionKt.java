package expo.modules.video;

import android.widget.ImageButton;
import androidx.media3.ui.DefaultTimeBar;
import androidx.media3.ui.PlayerView;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerViewExtension.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0001\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004H\u0001\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0004H\u0001¨\u0006\t"}, d2 = {"applyRequiresLinearPlayback", "", "Landroidx/media3/ui/PlayerView;", "requireLinearPlayback", "", "setTimeBarInteractive", "interactive", "setFullscreenButtonVisibility", ViewProps.VISIBLE, "expo-video_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlayerViewExtensionKt {
    public static final void applyRequiresLinearPlayback(PlayerView playerView, boolean z) {
        Intrinsics.checkNotNullParameter(playerView, "<this>");
        playerView.setShowFastForwardButton(!z);
        playerView.setShowRewindButton(!z);
        playerView.setShowPreviousButton(!z);
        playerView.setShowNextButton(!z);
        setTimeBarInteractive(playerView, z);
    }

    public static final void setTimeBarInteractive(PlayerView playerView, boolean z) {
        Intrinsics.checkNotNullParameter(playerView, "<this>");
        DefaultTimeBar defaultTimeBar = (DefaultTimeBar) playerView.findViewById(androidx.media3.ui.R.id.exo_progress);
        if (z) {
            if (defaultTimeBar != null) {
                defaultTimeBar.setScrubberColor(0);
            }
            if (defaultTimeBar != null) {
                defaultTimeBar.setEnabled(false);
                return;
            }
            return;
        }
        if (defaultTimeBar != null) {
            defaultTimeBar.setScrubberColor(-1);
        }
        if (defaultTimeBar != null) {
            defaultTimeBar.setEnabled(true);
        }
    }

    public static final void setFullscreenButtonVisibility(PlayerView playerView, boolean z) {
        Intrinsics.checkNotNullParameter(playerView, "<this>");
        ImageButton imageButton = (ImageButton) playerView.findViewById(androidx.media3.ui.R.id.exo_fullscreen);
        if (imageButton != null) {
            imageButton.setVisibility(z ? 0 : 8);
        }
    }
}
