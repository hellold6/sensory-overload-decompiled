package expo.modules.fetch;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

/* compiled from: NativeResponse.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.fetch.NativeResponse$onResponse$1", f = "NativeResponse.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class NativeResponse$onResponse$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Response $response;
    int label;
    final /* synthetic */ NativeResponse this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeResponse$onResponse$1(Response response, NativeResponse nativeResponse, Continuation<? super NativeResponse$onResponse$1> continuation) {
        super(2, continuation);
        this.$response = response;
        this.this$0 = nativeResponse;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NativeResponse$onResponse$1(this.$response, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NativeResponse$onResponse$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BufferedSource bodySource;
        ResponseState state;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ResponseBody body = this.$response.body();
        if (body != null && (bodySource = body.getBodySource()) != null) {
            this.this$0.pumpResponseBodyStream(bodySource);
            this.$response.close();
            state = this.this$0.getState();
            if (state == ResponseState.BODY_STREAMING_STARTED) {
                this.this$0.emit("didComplete", new Object[0]);
            }
            this.this$0.setState(ResponseState.BODY_COMPLETED);
            this.this$0.emit("readyForJSFinalization", new Object[0]);
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
