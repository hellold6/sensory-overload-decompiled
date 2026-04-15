package expo.modules.audio.service;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.SessionCommand;
import androidx.media3.session.SessionResult;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioMediaSessionCallback.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J.\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lexpo/modules/audio/service/AudioMediaSessionCallback;", "Landroidx/media3/session/MediaSession$Callback;", "<init>", "()V", "onConnect", "Landroidx/media3/session/MediaSession$ConnectionResult;", "session", "Landroidx/media3/session/MediaSession;", "controller", "Landroidx/media3/session/MediaSession$ControllerInfo;", "onCustomCommand", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/media3/session/SessionResult;", "command", "Landroidx/media3/session/SessionCommand;", "args", "Landroid/os/Bundle;", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioMediaSessionCallback implements MediaSession.Callback {
    @Override // androidx.media3.session.MediaSession.Callback
    public MediaSession.ConnectionResult onConnect(MediaSession session, MediaSession.ControllerInfo controller) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(controller, "controller");
        try {
            MediaSession.ConnectionResult build = new MediaSession.ConnectionResult.AcceptedResultBuilder(session).setAvailablePlayerCommands(MediaSession.ConnectionResult.DEFAULT_PLAYER_COMMANDS.buildUpon().add(5).add(12).add(11).remove(6).remove(8).remove(7).remove(9).build()).setAvailableSessionCommands(MediaSession.ConnectionResult.DEFAULT_SESSION_COMMANDS.buildUpon().add(new SessionCommand(AudioControlsService.ACTION_SEEK_BACKWARD, Bundle.EMPTY)).add(new SessionCommand(AudioControlsService.ACTION_SEEK_FORWARD, Bundle.EMPTY)).build()).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            return build;
        } catch (Exception unused) {
            MediaSession.ConnectionResult reject = MediaSession.ConnectionResult.reject();
            Intrinsics.checkNotNullExpressionValue(reject, "reject(...)");
            return reject;
        }
    }

    @Override // androidx.media3.session.MediaSession.Callback
    public ListenableFuture<SessionResult> onCustomCommand(MediaSession session, MediaSession.ControllerInfo controller, SessionCommand command, Bundle args) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(controller, "controller");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(args, "args");
        String str = command.customAction;
        int hashCode = str.hashCode();
        if (hashCode != -2041083429) {
            if (hashCode == 407156421 && str.equals(AudioControlsService.ACTION_SEEK_BACKWARD)) {
                session.getPlayer().seekTo(session.getPlayer().getCurrentPosition() - 10000);
            }
        } else if (str.equals(AudioControlsService.ACTION_SEEK_FORWARD)) {
            session.getPlayer().seekTo(session.getPlayer().getCurrentPosition() + 10000);
        }
        ListenableFuture<SessionResult> onCustomCommand = super.onCustomCommand(session, controller, command, args);
        Intrinsics.checkNotNullExpressionValue(onCustomCommand, "onCustomCommand(...)");
        return onCustomCommand;
    }
}
