package expo.modules.audio;

import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AudioPlayer.kt */
@kotlin.Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.audio.AudioPlayer$sendPlayerUpdate$2", f = "AudioPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class AudioPlayer$sendPlayerUpdate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<String, Object> $map;
    int label;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioPlayer$sendPlayerUpdate$2(AudioPlayer audioPlayer, Map<String, ? extends Object> map, Continuation<? super AudioPlayer$sendPlayerUpdate$2> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayer;
        this.$map = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayer$sendPlayerUpdate$2(this.this$0, this.$map, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioPlayer$sendPlayerUpdate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Map<String, Object> plus;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Map<String, Object> currentStatus = this.this$0.currentStatus();
        Map<String, Object> map = this.$map;
        if (map != null && (plus = MapsKt.plus(currentStatus, map)) != null) {
            currentStatus = plus;
        }
        this.this$0.emit("playbackStatusUpdate", currentStatus);
        return Unit.INSTANCE;
    }
}
