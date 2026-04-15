package expo.modules.video.player;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoPlayerKeepAwake.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.video.player.VideoPlayerKeepAwake$disable$1", f = "VideoPlayerKeepAwake.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class VideoPlayerKeepAwake$disable$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoPlayerKeepAwake this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoPlayerKeepAwake$disable$1(VideoPlayerKeepAwake videoPlayerKeepAwake, Continuation<? super VideoPlayerKeepAwake$disable$1> continuation) {
        super(2, continuation);
        this.this$0 = videoPlayerKeepAwake;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerKeepAwake$disable$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPlayerKeepAwake$disable$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
    
        r0 = r2.this$0.playerListener;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r3) {
        /*
            r2 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r2.label
            if (r0 != 0) goto L3b
            kotlin.ResultKt.throwOnFailure(r3)
            expo.modules.video.player.VideoPlayerKeepAwake r3 = r2.this$0
            java.lang.ref.WeakReference r3 = expo.modules.video.player.VideoPlayerKeepAwake.access$getVideoPlayer$p(r3)
            java.lang.Object r3 = r3.get()
            expo.modules.video.player.VideoPlayer r3 = (expo.modules.video.player.VideoPlayer) r3
            if (r3 != 0) goto L1b
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        L1b:
            expo.modules.video.player.VideoPlayerKeepAwake r0 = r2.this$0
            androidx.media3.common.Player$Listener r0 = expo.modules.video.player.VideoPlayerKeepAwake.access$getPlayerListener$p(r0)
            if (r0 != 0) goto L26
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        L26:
            androidx.media3.exoplayer.ExoPlayer r1 = r3.getPlayer()
            r1.removeListener(r0)
            expo.modules.video.player.VideoPlayerKeepAwake r0 = r2.this$0
            r1 = 0
            expo.modules.video.player.VideoPlayerKeepAwake.access$setPlayerListener$p(r0, r1)
            expo.modules.video.VideoManager r0 = expo.modules.video.VideoManager.INSTANCE
            r0.releaseKeepAwake(r3)
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        L3b:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.video.player.VideoPlayerKeepAwake$disable$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
