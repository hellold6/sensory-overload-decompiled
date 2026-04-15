package expo.modules.video.playbackService;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.CommandButton;
import androidx.media3.session.MediaSession;
import com.google.common.collect.ImmutableList;
import expo.modules.video.player.VideoPlayer;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExpoVideoPlaybackService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.video.playbackService.ExpoVideoPlaybackService$registerPlayer$1", f = "ExpoVideoPlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class ExpoVideoPlaybackService$registerPlayer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VideoPlayer $videoPlayer;
    int label;
    final /* synthetic */ ExpoVideoPlaybackService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoVideoPlaybackService$registerPlayer$1(VideoPlayer videoPlayer, ExpoVideoPlaybackService expoVideoPlaybackService, Continuation<? super ExpoVideoPlaybackService$registerPlayer$1> continuation) {
        super(2, continuation);
        this.$videoPlayer = videoPlayer;
        this.this$0 = expoVideoPlaybackService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExpoVideoPlaybackService$registerPlayer$1(this.$videoPlayer, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExpoVideoPlaybackService$registerPlayer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Map map;
        CommandButton commandButton;
        CommandButton commandButton2;
        Map map2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ExoPlayer player = this.$videoPlayer.getPlayer();
        map = this.this$0.mediaSessions;
        if (map.get(player) != null) {
            return Unit.INSTANCE;
        }
        MediaSession.Builder callback = new MediaSession.Builder(this.this$0, player).setId("ExpoVideoPlaybackService_" + player.hashCode()).setCallback((MediaSession.Callback) new VideoMediaSessionCallback());
        commandButton = this.this$0.seekBackwardButton;
        commandButton2 = this.this$0.seekForwardButton;
        MediaSession build = callback.setCustomLayout((List<CommandButton>) ImmutableList.of(commandButton, commandButton2)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        map2 = this.this$0.mediaSessions;
        map2.put(player, build);
        this.this$0.addSession(build);
        this.this$0.setShowNotification(this.$videoPlayer.getShowNowPlayingNotification(), player);
        return Unit.INSTANCE;
    }
}
