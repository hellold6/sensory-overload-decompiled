package expo.modules.securestore.encryptors;

import expo.modules.securestore.AuthenticationHelper;
import expo.modules.securestore.SecureStoreOptions;
import java.security.KeyStore;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AESEncryptor.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.securestore.encryptors.AESEncryptor", f = "AESEncryptor.kt", i = {0}, l = {131}, m = "decryptItem", n = {"ciphertextBytes"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class AESEncryptor$decryptItem$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AESEncryptor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AESEncryptor$decryptItem$1(AESEncryptor aESEncryptor, Continuation<? super AESEncryptor$decryptItem$1> continuation) {
        super(continuation);
        this.this$0 = aESEncryptor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.decryptItem2((String) null, (JSONObject) null, (KeyStore.SecretKeyEntry) null, (SecureStoreOptions) null, (AuthenticationHelper) null, (Continuation<? super String>) this);
    }
}
