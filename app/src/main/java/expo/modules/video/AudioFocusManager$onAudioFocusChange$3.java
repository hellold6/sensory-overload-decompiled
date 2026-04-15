package expo.modules.video;

import expo.modules.video.enums.AudioMixingMode;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AudioFocusManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.video.AudioFocusManager$onAudioFocusChange$3", f = "AudioFocusManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class AudioFocusManager$onAudioFocusChange$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AudioMixingMode $audioMixingMode;
    int label;
    final /* synthetic */ AudioFocusManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioFocusManager$onAudioFocusChange$3(AudioFocusManager audioFocusManager, AudioMixingMode audioMixingMode, Continuation<? super AudioFocusManager$onAudioFocusChange$3> continuation) {
        super(2, continuation);
        this.this$0 = audioFocusManager;
        this.$audioMixingMode = audioMixingMode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioFocusManager$onAudioFocusChange$3(this.this$0, this.$audioMixingMode, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioFocusManager$onAudioFocusChange$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List<WeakReference> list;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            list = this.this$0.players;
            AudioMixingMode audioMixingMode = this.$audioMixingMode;
            AudioFocusManager audioFocusManager = this.this$0;
            for (WeakReference weakReference : list) {
                if (audioMixingMode == AudioMixingMode.DO_NOT_MIX) {
                    audioFocusManager.pausePlayerIfUnmuted(weakReference);
                } else {
                    audioFocusManager.duckPlayer(weakReference);
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
