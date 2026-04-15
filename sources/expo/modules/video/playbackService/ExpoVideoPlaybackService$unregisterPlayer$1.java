package expo.modules.video.playbackService;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.MediaSession;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExpoVideoPlaybackService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.video.playbackService.ExpoVideoPlaybackService$unregisterPlayer$1", f = "ExpoVideoPlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class ExpoVideoPlaybackService$unregisterPlayer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ExoPlayer $player;
    int label;
    final /* synthetic */ ExpoVideoPlaybackService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoVideoPlaybackService$unregisterPlayer$1(ExpoVideoPlaybackService expoVideoPlaybackService, ExoPlayer exoPlayer, Continuation<? super ExpoVideoPlaybackService$unregisterPlayer$1> continuation) {
        super(2, continuation);
        this.this$0 = expoVideoPlaybackService;
        this.$player = exoPlayer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExpoVideoPlaybackService$unregisterPlayer$1(this.this$0, this.$player, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExpoVideoPlaybackService$unregisterPlayer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Map map;
        Map map2;
        MediaSession findMostRecentInteractionSession;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.hidePlayerNotification(this.$player);
            map = this.this$0.mediaSessions;
            MediaSession mediaSession = (MediaSession) map.remove(this.$player);
            if (mediaSession != null) {
                mediaSession.release();
            }
            map2 = this.this$0.mediaSessions;
            if (map2.isEmpty()) {
                this.this$0.cleanup();
                this.this$0.stopSelf();
            } else {
                ExpoVideoPlaybackService expoVideoPlaybackService = this.this$0;
                findMostRecentInteractionSession = expoVideoPlaybackService.findMostRecentInteractionSession();
                expoVideoPlaybackService.setMostRecentInteractionSession(findMostRecentInteractionSession);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
