package expo.modules;

import android.content.Intent;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.bridge.ReactContext;
import expo.modules.rncompatibility.ReactNativeFeatureFlags;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ReactActivityDelegateWrapper.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.ReactActivityDelegateWrapper$onActivityResult$1", f = "ReactActivityDelegateWrapper.kt", i = {}, l = {278}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ReactActivityDelegateWrapper$onActivityResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Intent $data;
    final /* synthetic */ int $requestCode;
    final /* synthetic */ int $resultCode;
    int label;
    final /* synthetic */ ReactActivityDelegateWrapper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper$onActivityResult$1(ReactActivityDelegateWrapper reactActivityDelegateWrapper, int i, int i2, Intent intent, Continuation<? super ReactActivityDelegateWrapper$onActivityResult$1> continuation) {
        super(2, continuation);
        this.this$0 = reactActivityDelegateWrapper;
        this.$requestCode = i;
        this.$resultCode = i2;
        this.$data = intent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ReactActivityDelegateWrapper$onActivityResult$1(this.this$0, this.$requestCode, this.$resultCode, this.$data, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ReactActivityDelegateWrapper$onActivityResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CompletableDeferred completableDeferred;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            completableDeferred = this.this$0.loadAppReady;
            this.label = 1;
            if (completableDeferred.await(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (!ReactNativeFeatureFlags.INSTANCE.getEnableBridgelessArchitecture() && this.this$0.getDelegate().getReactInstanceManager().getCurrentReactContext() == null) {
            final ReactActivityDelegateWrapper reactActivityDelegateWrapper = this.this$0;
            final int i2 = this.$requestCode;
            final int i3 = this.$resultCode;
            final Intent intent = this.$data;
            this.this$0.getDelegate().getReactInstanceManager().addReactInstanceEventListener(new ReactInstanceEventListener() { // from class: expo.modules.ReactActivityDelegateWrapper$onActivityResult$1$reactContextListener$1
                @Override // com.facebook.react.ReactInstanceEventListener
                public void onReactContextInitialized(ReactContext context) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    ReactActivityDelegateWrapper.this.getDelegate().getReactInstanceManager().removeReactInstanceEventListener(this);
                    ReactActivityDelegateWrapper.this.getDelegate().onActivityResult(i2, i3, intent);
                }
            });
            return Unit.INSTANCE;
        }
        this.this$0.getDelegate().onActivityResult(this.$requestCode, this.$resultCode, this.$data);
        return Unit.INSTANCE;
    }
}
