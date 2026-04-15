package expo.modules;

import com.facebook.react.modules.core.PermissionListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ReactActivityDelegateWrapper.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.ReactActivityDelegateWrapper$requestPermissions$1", f = "ReactActivityDelegateWrapper.kt", i = {}, l = {359}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ReactActivityDelegateWrapper$requestPermissions$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PermissionListener $listener;
    final /* synthetic */ String[] $permissions;
    final /* synthetic */ int $requestCode;
    int label;
    final /* synthetic */ ReactActivityDelegateWrapper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper$requestPermissions$1(ReactActivityDelegateWrapper reactActivityDelegateWrapper, String[] strArr, int i, PermissionListener permissionListener, Continuation<? super ReactActivityDelegateWrapper$requestPermissions$1> continuation) {
        super(2, continuation);
        this.this$0 = reactActivityDelegateWrapper;
        this.$permissions = strArr;
        this.$requestCode = i;
        this.$listener = permissionListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ReactActivityDelegateWrapper$requestPermissions$1(this.this$0, this.$permissions, this.$requestCode, this.$listener, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ReactActivityDelegateWrapper$requestPermissions$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        this.this$0.getDelegate().requestPermissions(this.$permissions, this.$requestCode, this.$listener);
        return Unit.INSTANCE;
    }
}
