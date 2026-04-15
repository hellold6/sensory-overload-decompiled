package expo.modules;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ReactActivityDelegateWrapper.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.ReactActivityDelegateWrapper$onCreate$1", f = "ReactActivityDelegateWrapper.kt", i = {}, l = {151, 185}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ReactActivityDelegateWrapper$onCreate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ReactActivityDelegateWrapper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper$onCreate$1(ReactActivityDelegateWrapper reactActivityDelegateWrapper, Continuation<? super ReactActivityDelegateWrapper$onCreate$1> continuation) {
        super(2, continuation);
        this.this$0 = reactActivityDelegateWrapper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ReactActivityDelegateWrapper$onCreate$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ReactActivityDelegateWrapper$onCreate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00cf, code lost:
    
        if (r12 == r0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00d1, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0031, code lost:
    
        if (r12 == r0) goto L26;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1f
            if (r1 == r3) goto L1b
            if (r1 != r2) goto L13
            kotlin.ResultKt.throwOnFailure(r12)
            goto Ld2
        L13:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L1b:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L35
        L1f:
            kotlin.ResultKt.throwOnFailure(r12)
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            expo.modules.core.interfaces.ReactActivityHandler$DelayLoadAppHandler r1 = expo.modules.ReactActivityDelegateWrapper.access$getDelayLoadAppHandler(r12)
            r4 = r11
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r11.label = r3
            java.lang.Object r12 = expo.modules.ReactActivityDelegateWrapper.access$awaitDelayLoadAppWhenReady(r12, r1, r4)
            if (r12 != r0) goto L35
            goto Ld1
        L35:
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            kotlinx.coroutines.CompletableDeferred r12 = expo.modules.ReactActivityDelegateWrapper.access$getLoadAppReady$p(r12)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            r12.complete(r1)
            int r12 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r12 < r1) goto L5b
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            boolean r12 = r12.isWideColorGamutEnabled()
            if (r12 == 0) goto L5b
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            com.facebook.react.ReactActivity r12 = expo.modules.ReactActivityDelegateWrapper.access$getActivity$p(r12)
            android.view.Window r12 = r12.getWindow()
            r12.setColorMode(r3)
        L5b:
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            android.os.Bundle r5 = r12.composeLaunchOptions()
            expo.modules.rncompatibility.ReactNativeFeatureFlags r12 = expo.modules.rncompatibility.ReactNativeFeatureFlags.INSTANCE
            boolean r12 = r12.getEnableBridgelessArchitecture()
            if (r12 == 0) goto L81
            com.facebook.react.ReactDelegate r12 = new com.facebook.react.ReactDelegate
            expo.modules.ReactActivityDelegateWrapper r1 = r11.this$0
            android.app.Activity r1 = r1.getPlainActivity()
            expo.modules.ReactActivityDelegateWrapper r4 = r11.this$0
            com.facebook.react.ReactHost r4 = r4.getReactHost()
            expo.modules.ReactActivityDelegateWrapper r6 = r11.this$0
            java.lang.String r6 = r6.getMainComponentName()
            r12.<init>(r1, r4, r6, r5)
            goto La3
        L81:
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            android.app.Activity r7 = r12.getPlainActivity()
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            com.facebook.react.ReactNativeHost r8 = r12.getReactNativeHost()
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            java.lang.String r9 = r12.getMainComponentName()
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            boolean r10 = r12.getFabricEnabled()
            expo.modules.ReactActivityDelegateWrapper$onCreate$1$1 r4 = new expo.modules.ReactActivityDelegateWrapper$onCreate$1$1
            expo.modules.ReactActivityDelegateWrapper r6 = r11.this$0
            r4.<init>(r5, r7, r8, r9, r10)
            r12 = r4
            com.facebook.react.ReactDelegate r12 = (com.facebook.react.ReactDelegate) r12
        La3:
            java.lang.Class<com.facebook.react.ReactActivityDelegate> r1 = com.facebook.react.ReactActivityDelegate.class
            java.lang.String r4 = "mReactDelegate"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r4)
            r1.setAccessible(r3)
            expo.modules.ReactActivityDelegateWrapper r3 = r11.this$0
            com.facebook.react.ReactActivityDelegate r3 = r3.getDelegate()
            r1.set(r3, r12)
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            java.lang.String r12 = r12.getMainComponentName()
            if (r12 == 0) goto Ld2
            expo.modules.ReactActivityDelegateWrapper r12 = r11.this$0
            java.lang.String r1 = r12.getMainComponentName()
            r3 = r11
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r11.label = r2
            r2 = 0
            java.lang.Object r12 = expo.modules.ReactActivityDelegateWrapper.access$loadAppImpl(r12, r1, r2, r3)
            if (r12 != r0) goto Ld2
        Ld1:
            return r0
        Ld2:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.ReactActivityDelegateWrapper$onCreate$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
