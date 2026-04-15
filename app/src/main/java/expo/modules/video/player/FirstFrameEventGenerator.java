package expo.modules.video.player;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import expo.modules.video.enums.ContentFit;
import expo.modules.video.utils.MutableWeakReference;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FirstFrameEventGenerator.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0014\u001a\u00020\tH\u0016J\u001a\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\b\u0010\u001d\u001a\u00020\tH\u0002J\b\u0010\u001e\u001a\u00020\u0012H\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\f\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00030\u00030\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lexpo/modules/video/player/FirstFrameEventGenerator;", "Landroidx/media3/common/Player$Listener;", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "currentViewReference", "Lexpo/modules/video/utils/MutableWeakReference;", "Landroidx/media3/ui/PlayerView;", "onFirstFrameRendered", "Lkotlin/Function0;", "", "<init>", "(Landroidx/media3/exoplayer/ExoPlayer;Lexpo/modules/video/utils/MutableWeakReference;Lkotlin/jvm/functions/Function0;)V", "playerReference", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getPlayerReference", "()Ljava/lang/ref/WeakReference;", "hasPendingOnFirstFrame", "", "hasSentFirstFrameForCurrentMediaItem", "onRenderedFirstFrame", "onMediaItemTransition", "mediaItem", "Landroidx/media3/common/MediaItem;", "reason", "", "onSurfaceSizeChanged", "width", "height", "maybeCallOnFirstFrameRendered", "isPlayerSurfaceLayoutValid", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FirstFrameEventGenerator implements Player.Listener {
    private final MutableWeakReference<PlayerView> currentViewReference;
    private boolean hasPendingOnFirstFrame;
    private boolean hasSentFirstFrameForCurrentMediaItem;
    private final Function0<Unit> onFirstFrameRendered;
    private final WeakReference<ExoPlayer> playerReference;

    public FirstFrameEventGenerator(ExoPlayer player, MutableWeakReference<PlayerView> currentViewReference, Function0<Unit> onFirstFrameRendered) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(currentViewReference, "currentViewReference");
        Intrinsics.checkNotNullParameter(onFirstFrameRendered, "onFirstFrameRendered");
        this.currentViewReference = currentViewReference;
        this.onFirstFrameRendered = onFirstFrameRendered;
        this.playerReference = new WeakReference<>(player);
        player.addListener(this);
    }

    public final WeakReference<ExoPlayer> getPlayerReference() {
        return this.playerReference;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onRenderedFirstFrame() {
        if (isPlayerSurfaceLayoutValid()) {
            maybeCallOnFirstFrameRendered();
        } else {
            this.hasPendingOnFirstFrame = true;
        }
    }

    @Override // androidx.media3.common.Player.Listener
    public void onMediaItemTransition(MediaItem mediaItem, int reason) {
        this.hasSentFirstFrameForCurrentMediaItem = false;
        super.onMediaItemTransition(mediaItem, reason);
    }

    @Override // androidx.media3.common.Player.Listener
    public void onSurfaceSizeChanged(int width, int height) {
        if (isPlayerSurfaceLayoutValid() && this.hasPendingOnFirstFrame) {
            maybeCallOnFirstFrameRendered();
        }
    }

    private final void maybeCallOnFirstFrameRendered() {
        if (!this.hasSentFirstFrameForCurrentMediaItem) {
            this.onFirstFrameRendered.invoke();
        }
        this.hasPendingOnFirstFrame = false;
        this.hasSentFirstFrameForCurrentMediaItem = true;
    }

    private final boolean isPlayerSurfaceLayoutValid() {
        PlayerView playerView;
        ExoPlayer exoPlayer = this.playerReference.get();
        if (exoPlayer != null && (playerView = this.currentViewReference.get()) != null) {
            int width = exoPlayer.getSurfaceSize().getWidth();
            int height = exoPlayer.getSurfaceSize().getHeight();
            int i = exoPlayer.getVideoSize().width;
            int i2 = exoPlayer.getVideoSize().height;
            float f = exoPlayer.getVideoSize().pixelWidthHeightRatio;
            if (width == 0 || height == 0) {
                return false;
            }
            return ((double) Math.abs(((((float) i) / ((float) i2)) * f) - (width / height))) < 0.05d || (playerView.getResizeMode() == ContentFit.FILL.toResizeMode()) || (i == 0 || i2 == 0);
        }
        return false;
    }
}
