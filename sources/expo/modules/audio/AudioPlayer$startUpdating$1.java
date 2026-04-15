package expo.modules.audio;

import androidx.media3.extractor.ts.TsExtractor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AudioPlayer.kt */
@kotlin.Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.audio.AudioPlayer$startUpdating$1", f = "AudioPlayer.kt", i = {0, 1}, l = {TsExtractor.TS_STREAM_TYPE_AC3, TsExtractor.TS_STREAM_TYPE_HDMV_DTS}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
/* loaded from: classes3.dex */
public final class AudioPlayer$startUpdating$1 extends SuspendLambda implements Function2<FlowCollector<? super Unit>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioPlayer$startUpdating$1(AudioPlayer audioPlayer, Continuation<? super AudioPlayer$startUpdating$1> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AudioPlayer$startUpdating$1 audioPlayer$startUpdating$1 = new AudioPlayer$startUpdating$1(this.this$0, continuation);
        audioPlayer$startUpdating$1.L$0 = obj;
        return audioPlayer$startUpdating$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super Unit> flowCollector, Continuation<? super Unit> continuation) {
        return ((AudioPlayer$startUpdating$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0051, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay((long) r4, r6) != r0) goto L7;
     */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0051 -> B:6:0x0015). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L27
            if (r1 == r3) goto L1f
            if (r1 != r2) goto L17
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r7)
        L15:
            r7 = r1
            goto L2e
        L17:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1f:
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L3f
        L27:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
        L2e:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            r4 = r6
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r6.L$0 = r7
            r6.label = r3
            java.lang.Object r1 = r7.emit(r1, r4)
            if (r1 != r0) goto L3e
            goto L53
        L3e:
            r1 = r7
        L3f:
            expo.modules.audio.AudioPlayer r7 = r6.this$0
            double r4 = expo.modules.audio.AudioPlayer.access$getUpdateInterval$p(r7)
            long r4 = (long) r4
            r7 = r6
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r6.L$0 = r1
            r6.label = r2
            java.lang.Object r7 = kotlinx.coroutines.DelayKt.delay(r4, r7)
            if (r7 != r0) goto L15
        L53:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.audio.AudioPlayer$startUpdating$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
