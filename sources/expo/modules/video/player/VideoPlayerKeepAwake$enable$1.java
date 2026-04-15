package expo.modules.video.player;

import androidx.media3.common.Player;
import expo.modules.video.VideoManager;
import java.lang.ref.WeakReference;
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
/* compiled from: VideoPlayerKeepAwake.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.video.player.VideoPlayerKeepAwake$enable$1", f = "VideoPlayerKeepAwake.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class VideoPlayerKeepAwake$enable$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoPlayerKeepAwake this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoPlayerKeepAwake$enable$1(VideoPlayerKeepAwake videoPlayerKeepAwake, Continuation<? super VideoPlayerKeepAwake$enable$1> continuation) {
        super(2, continuation);
        this.this$0 = videoPlayerKeepAwake;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerKeepAwake$enable$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPlayerKeepAwake$enable$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        WeakReference weakReference;
        Player.Listener createPlayerListener;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            weakReference = this.this$0.videoPlayer;
            VideoPlayer videoPlayer = (VideoPlayer) weakReference.get();
            if (videoPlayer == null) {
                return Unit.INSTANCE;
            }
            createPlayerListener = this.this$0.createPlayerListener();
            this.this$0.playerListener = createPlayerListener;
            videoPlayer.getPlayer().addListener(createPlayerListener);
            VideoManager.INSTANCE.requestKeepAwake(videoPlayer);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
