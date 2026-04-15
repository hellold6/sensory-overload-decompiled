package expo.modules.audio;

import android.media.audiofx.Visualizer;
import expo.modules.audio.service.AudioControlsService;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: AudioPlayer.kt */
@kotlin.Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.audio.AudioPlayer$sharedObjectDidRelease$1", f = "AudioPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class AudioPlayer$sharedObjectDidRelease$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioPlayer$sharedObjectDidRelease$1(AudioPlayer audioPlayer, Continuation<? super AudioPlayer$sharedObjectDidRelease$1> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayer$sharedObjectDidRelease$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioPlayer$sharedObjectDidRelease$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Visualizer visualizer;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (this.this$0.getIsActiveForLockScreen()) {
            AudioControlsService.INSTANCE.clearSession();
        }
        coroutineScope = this.this$0.playerScope;
        CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        visualizer = this.this$0.visualizer;
        if (visualizer != null) {
            visualizer.release();
        }
        this.this$0.getRef().release();
        return Unit.INSTANCE;
    }
}
