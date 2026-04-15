package expo.modules.audio;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AudioPlayer.kt */
@kotlin.Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.audio.AudioPlayer$setVolume$1", f = "AudioPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class AudioPlayer$setVolume$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Float $volume;
    int label;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioPlayer$setVolume$1(Float f, AudioPlayer audioPlayer, Continuation<? super AudioPlayer$setVolume$1> continuation) {
        super(2, continuation);
        this.$volume = f;
        this.this$0 = audioPlayer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayer$setVolume$1(this.$volume, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioPlayer$setVolume$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Float f = this.$volume;
        float coerceIn = f != null ? RangesKt.coerceIn(f.floatValue(), 0.0f, 1.0f) : 1.0f;
        if (this.this$0.getIsMuted()) {
            if (coerceIn > 0.0f) {
                this.this$0.setPreviousVolume(coerceIn);
            }
            this.this$0.getRef().setVolume(0.0f);
        } else {
            this.this$0.setPreviousVolume(coerceIn);
            this.this$0.getRef().setVolume(coerceIn);
        }
        return Unit.INSTANCE;
    }
}
