package expo.modules.securestore;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AuthenticationHelper.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.securestore.AuthenticationHelper", f = "AuthenticationHelper.kt", i = {}, l = {50}, m = "openAuthenticationPrompt", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AuthenticationHelper$openAuthenticationPrompt$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AuthenticationHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthenticationHelper$openAuthenticationPrompt$1(AuthenticationHelper authenticationHelper, Continuation<? super AuthenticationHelper$openAuthenticationPrompt$1> continuation) {
        super(continuation);
        this.this$0 = authenticationHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object openAuthenticationPrompt;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        openAuthenticationPrompt = this.this$0.openAuthenticationPrompt(null, null, this);
        return openAuthenticationPrompt;
    }
}
