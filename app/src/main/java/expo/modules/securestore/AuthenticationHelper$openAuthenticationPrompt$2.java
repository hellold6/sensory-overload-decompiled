package expo.modules.securestore;

import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AuthenticationHelper.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.securestore.AuthenticationHelper$openAuthenticationPrompt$2", f = "AuthenticationHelper.kt", i = {}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class AuthenticationHelper$openAuthenticationPrompt$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BiometricPrompt.AuthenticationResult>, Object> {
    final /* synthetic */ AuthenticationPrompt $authenticationPrompt;
    final /* synthetic */ Cipher $cipher;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthenticationHelper$openAuthenticationPrompt$2(AuthenticationPrompt authenticationPrompt, Cipher cipher, Continuation<? super AuthenticationHelper$openAuthenticationPrompt$2> continuation) {
        super(2, continuation);
        this.$authenticationPrompt = authenticationPrompt;
        this.$cipher = cipher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthenticationHelper$openAuthenticationPrompt$2(this.$authenticationPrompt, this.$cipher, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super BiometricPrompt.AuthenticationResult> continuation) {
        return ((AuthenticationHelper$openAuthenticationPrompt$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.$authenticationPrompt.authenticate(this.$cipher, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        BiometricPrompt.AuthenticationResult authenticationResult = (BiometricPrompt.AuthenticationResult) obj;
        if (authenticationResult != null) {
            return authenticationResult;
        }
        throw new AuthenticationException("Couldn't get the authentication result", null, 2, null);
    }
}
