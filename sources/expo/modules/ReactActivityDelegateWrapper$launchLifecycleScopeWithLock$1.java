package expo.modules;

import androidx.core.app.FrameMetricsAggregator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReactActivityDelegateWrapper.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.ReactActivityDelegateWrapper$launchLifecycleScopeWithLock$1", f = "ReactActivityDelegateWrapper.kt", i = {0, 0, 1}, l = {FrameMetricsAggregator.EVERY_DURATION, 485}, m = "invokeSuspend", n = {"$this$launch", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0"})
/* loaded from: classes3.dex */
public final class ReactActivityDelegateWrapper$launchLifecycleScopeWithLock$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $block;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ReactActivityDelegateWrapper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ReactActivityDelegateWrapper$launchLifecycleScopeWithLock$1(ReactActivityDelegateWrapper reactActivityDelegateWrapper, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super ReactActivityDelegateWrapper$launchLifecycleScopeWithLock$1> continuation) {
        super(2, continuation);
        this.this$0 = reactActivityDelegateWrapper;
        this.$block = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ReactActivityDelegateWrapper$launchLifecycleScopeWithLock$1 reactActivityDelegateWrapper$launchLifecycleScopeWithLock$1 = new ReactActivityDelegateWrapper$launchLifecycleScopeWithLock$1(this.this$0, this.$block, continuation);
        reactActivityDelegateWrapper$launchLifecycleScopeWithLock$1.L$0 = obj;
        return reactActivityDelegateWrapper$launchLifecycleScopeWithLock$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ReactActivityDelegateWrapper$launchLifecycleScopeWithLock$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0051, code lost:
    
        if (r9.lock(null, r8) == r0) goto L19;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L32
            if (r1 == r3) goto L21
            if (r1 != r2) goto L19
            java.lang.Object r0 = r8.L$0
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L17
            goto L64
        L17:
            r9 = move-exception
            goto L70
        L19:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L21:
            java.lang.Object r1 = r8.L$2
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            java.lang.Object r3 = r8.L$1
            kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
            java.lang.Object r5 = r8.L$0
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r3
            goto L54
        L32:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            r5 = r9
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            expo.modules.ReactActivityDelegateWrapper r9 = r8.this$0
            kotlinx.coroutines.sync.Mutex r9 = expo.modules.ReactActivityDelegateWrapper.access$getMutex$p(r9)
            kotlin.jvm.functions.Function2<kotlinx.coroutines.CoroutineScope, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r8.$block
            r6 = r8
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r8.L$0 = r5
            r8.L$1 = r9
            r8.L$2 = r1
            r8.label = r3
            java.lang.Object r3 = r9.lock(r4, r6)
            if (r3 != r0) goto L54
            goto L62
        L54:
            r8.L$0 = r9     // Catch: java.lang.Throwable -> L6c
            r8.L$1 = r4     // Catch: java.lang.Throwable -> L6c
            r8.L$2 = r4     // Catch: java.lang.Throwable -> L6c
            r8.label = r2     // Catch: java.lang.Throwable -> L6c
            java.lang.Object r1 = r1.invoke(r5, r8)     // Catch: java.lang.Throwable -> L6c
            if (r1 != r0) goto L63
        L62:
            return r0
        L63:
            r0 = r9
        L64:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L17
            r0.unlock(r4)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L6c:
            r0 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
        L70:
            r0.unlock(r4)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.ReactActivityDelegateWrapper$launchLifecycleScopeWithLock$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
