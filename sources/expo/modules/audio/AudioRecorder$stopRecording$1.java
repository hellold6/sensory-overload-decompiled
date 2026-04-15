package expo.modules.audio;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.messaging.Constants;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AudioRecorder.kt */
@kotlin.Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.audio.AudioRecorder$stopRecording$1", f = "AudioRecorder.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AudioRecorder$stopRecording$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ AudioRecorder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioRecorder$stopRecording$1(AudioRecorder audioRecorder, String str, Continuation<? super AudioRecorder$stopRecording$1> continuation) {
        super(2, continuation);
        this.this$0 = audioRecorder;
        this.$url = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioRecorder$stopRecording$1(this.this$0, this.$url, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioRecorder$stopRecording$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        AudioRecorder audioRecorder = this.this$0;
        audioRecorder.emit("recordingStatusUpdate", MapsKt.mapOf(TuplesKt.to("id", audioRecorder.getId()), TuplesKt.to("isFinished", Boxing.boxBoolean(true)), TuplesKt.to("hasError", Boxing.boxBoolean(false)), TuplesKt.to(Constants.IPC_BUNDLE_KEY_SEND_ERROR, null), TuplesKt.to(ImagesContract.URL, this.$url)));
        return Unit.INSTANCE;
    }
}
