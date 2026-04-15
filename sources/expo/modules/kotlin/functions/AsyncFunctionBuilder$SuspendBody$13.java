package expo.modules.kotlin.functions;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function7;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "<destruct>", ""}, k = 3, mv = {2, 1, 0}, xi = 176)
@DebugMetadata(c = "expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$13", f = "AsyncFunctionBuilder.kt", i = {}, l = {74}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AsyncFunctionBuilder$SuspendBody$13 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    final /* synthetic */ Function7<P0, P1, P2, P3, P4, P5, Continuation<? super R>, Object> $block;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AsyncFunctionBuilder$SuspendBody$13(Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super Continuation<? super R>, ? extends Object> function7, Continuation<? super AsyncFunctionBuilder$SuspendBody$13> continuation) {
        super(3, continuation);
        this.$block = function7;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        AsyncFunctionBuilder$SuspendBody$13 asyncFunctionBuilder$SuspendBody$13 = new AsyncFunctionBuilder$SuspendBody$13(this.$block, continuation);
        asyncFunctionBuilder$SuspendBody$13.L$0 = objArr;
        return asyncFunctionBuilder$SuspendBody$13.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        Object[] objArr = (Object[]) this.L$0;
        Object obj2 = objArr[0];
        Object obj3 = objArr[1];
        Object obj4 = objArr[2];
        Object obj5 = objArr[3];
        Object obj6 = objArr[4];
        Object obj7 = objArr[5];
        Function7<P0, P1, P2, P3, P4, P5, Continuation<? super R>, Object> function7 = this.$block;
        this.label = 1;
        Object invoke = function7.invoke(obj2, obj3, obj4, obj5, obj6, obj7, this);
        return invoke == coroutine_suspended ? coroutine_suspended : invoke;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        Object[] objArr = (Object[]) this.L$0;
        return this.$block.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], this);
    }
}
