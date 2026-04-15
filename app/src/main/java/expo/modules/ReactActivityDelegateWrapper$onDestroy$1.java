package expo.modules;

import androidx.core.app.FrameMetricsAggregator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ReactActivityDelegateWrapper.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.ReactActivityDelegateWrapper$onDestroy$1", f = "ReactActivityDelegateWrapper.kt", i = {0, 1}, l = {FrameMetricsAggregator.EVERY_DURATION, 242}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, s = {"L$0", "L$0"})
/* loaded from: classes3.dex */
final class ReactActivityDelegateWrapper$onDestroy$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ReactActivityDelegateWrapper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper$onDestroy$1(ReactActivityDelegateWrapper reactActivityDelegateWrapper, Continuation<? super ReactActivityDelegateWrapper$onDestroy$1> continuation) {
        super(2, continuation);
        this.this$0 = reactActivityDelegateWrapper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ReactActivityDelegateWrapper$onDestroy$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ReactActivityDelegateWrapper$onDestroy$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x004b, code lost:
    
        if (r7.lock(null, r6) == r0) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0071 A[Catch: all -> 0x001b, LOOP:0: B:9:0x006b->B:11:0x0071, LOOP_END, TryCatch #1 {all -> 0x001b, blocks: (B:7:0x0017, B:8:0x0061, B:9:0x006b, B:11:0x0071, B:13:0x0081, B:22:0x0087, B:16:0x00a9, B:15:0x00a2, B:25:0x0092), top: B:6:0x0017, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00a2 A[Catch: all -> 0x001b, TryCatch #1 {all -> 0x001b, blocks: (B:7:0x0017, B:8:0x0061, B:9:0x006b, B:11:0x0071, B:13:0x0081, B:22:0x0087, B:16:0x00a9, B:15:0x00a2, B:25:0x0092), top: B:6:0x0017, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
            r4 = 0
            if (r1 == 0) goto L33
            if (r1 == r3) goto L26
            if (r1 != r2) goto L1e
            java.lang.Object r0 = r6.L$1
            expo.modules.ReactActivityDelegateWrapper r0 = (expo.modules.ReactActivityDelegateWrapper) r0
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L1b
            goto L61
        L1b:
            r7 = move-exception
            goto Lb4
        L1e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L26:
            java.lang.Object r1 = r6.L$1
            expo.modules.ReactActivityDelegateWrapper r1 = (expo.modules.ReactActivityDelegateWrapper) r1
            java.lang.Object r3 = r6.L$0
            kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r3
            goto L4e
        L33:
            kotlin.ResultKt.throwOnFailure(r7)
            expo.modules.ReactActivityDelegateWrapper r7 = r6.this$0
            kotlinx.coroutines.sync.Mutex r7 = expo.modules.ReactActivityDelegateWrapper.access$getMutex$p(r7)
            expo.modules.ReactActivityDelegateWrapper r1 = r6.this$0
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6.L$0 = r7
            r6.L$1 = r1
            r6.label = r3
            java.lang.Object r3 = r7.lock(r4, r5)
            if (r3 != r0) goto L4e
            goto L5e
        L4e:
            kotlinx.coroutines.CompletableDeferred r3 = expo.modules.ReactActivityDelegateWrapper.access$getLoadAppReady$p(r1)     // Catch: java.lang.Throwable -> Lb1
            r6.L$0 = r7     // Catch: java.lang.Throwable -> Lb1
            r6.L$1 = r1     // Catch: java.lang.Throwable -> Lb1
            r6.label = r2     // Catch: java.lang.Throwable -> Lb1
            java.lang.Object r2 = r3.await(r6)     // Catch: java.lang.Throwable -> Lb1
            if (r2 != r0) goto L5f
        L5e:
            return r0
        L5f:
            r0 = r1
            r1 = r7
        L61:
            java.util.List r7 = expo.modules.ReactActivityDelegateWrapper.access$getReactActivityLifecycleListeners$p(r0)     // Catch: java.lang.Throwable -> L1b
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch: java.lang.Throwable -> L1b
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> L1b
        L6b:
            boolean r2 = r7.hasNext()     // Catch: java.lang.Throwable -> L1b
            if (r2 == 0) goto L81
            java.lang.Object r2 = r7.next()     // Catch: java.lang.Throwable -> L1b
            expo.modules.core.interfaces.ReactActivityLifecycleListener r2 = (expo.modules.core.interfaces.ReactActivityLifecycleListener) r2     // Catch: java.lang.Throwable -> L1b
            com.facebook.react.ReactActivity r3 = expo.modules.ReactActivityDelegateWrapper.access$getActivity$p(r0)     // Catch: java.lang.Throwable -> L1b
            android.app.Activity r3 = (android.app.Activity) r3     // Catch: java.lang.Throwable -> L1b
            r2.onDestroy(r3)     // Catch: java.lang.Throwable -> L1b
            goto L6b
        L81:
            expo.modules.core.interfaces.ReactActivityHandler$DelayLoadAppHandler r7 = expo.modules.ReactActivityDelegateWrapper.access$getDelayLoadAppHandler(r0)     // Catch: java.lang.Throwable -> L1b
            if (r7 == 0) goto La2
            com.facebook.react.ReactActivityDelegate r7 = r0.getDelegate()     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L91
            r7.onDestroy()     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L91
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L91
            goto La9
        L91:
            r7 = move-exception
            java.lang.String r0 = expo.modules.ReactActivityDelegateWrapper.access$getTAG$cp()     // Catch: java.lang.Throwable -> L1b
            java.lang.String r2 = "Exception occurred during onDestroy with delayed app loading"
            java.lang.Throwable r7 = (java.lang.Throwable) r7     // Catch: java.lang.Throwable -> L1b
            int r7 = android.util.Log.e(r0, r2, r7)     // Catch: java.lang.Throwable -> L1b
            kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)     // Catch: java.lang.Throwable -> L1b
            goto La9
        La2:
            com.facebook.react.ReactActivityDelegate r7 = r0.getDelegate()     // Catch: java.lang.Throwable -> L1b
            r7.onDestroy()     // Catch: java.lang.Throwable -> L1b
        La9:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L1b
            r1.unlock(r4)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        Lb1:
            r0 = move-exception
            r1 = r7
            r7 = r0
        Lb4:
            r1.unlock(r4)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.ReactActivityDelegateWrapper$onDestroy$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
