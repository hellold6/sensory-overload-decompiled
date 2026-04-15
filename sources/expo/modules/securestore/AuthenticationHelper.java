package expo.modules.securestore;

import android.app.Activity;
import android.content.Context;
import androidx.biometric.BiometricManager;
import com.facebook.react.modules.dialog.AlertFragment;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.ActivityProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AuthenticationHelper.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lexpo/modules/securestore/AuthenticationHelper;", "", "context", "Landroid/content/Context;", "moduleRegistry", "Lexpo/modules/core/ModuleRegistry;", "<init>", "(Landroid/content/Context;Lexpo/modules/core/ModuleRegistry;)V", "isAuthenticating", "", "authenticateCipher", "Ljavax/crypto/Cipher;", "cipher", "requiresAuthentication", AlertFragment.ARG_TITLE, "", "(Ljavax/crypto/Cipher;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openAuthenticationPrompt", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "(Ljavax/crypto/Cipher;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "assertBiometricsSupport", "", "getCurrentActivity", "Landroid/app/Activity;", "Companion", "expo-secure-store_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AuthenticationHelper {
    public static final String REQUIRE_AUTHENTICATION_PROPERTY = "requireAuthentication";
    private final Context context;
    private boolean isAuthenticating;
    private final ModuleRegistry moduleRegistry;

    public AuthenticationHelper(Context context, ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.context = context;
        this.moduleRegistry = moduleRegistry;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object authenticateCipher(javax.crypto.Cipher r5, boolean r6, java.lang.String r7, kotlin.coroutines.Continuation<? super javax.crypto.Cipher> r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof expo.modules.securestore.AuthenticationHelper$authenticateCipher$1
            if (r0 == 0) goto L14
            r0 = r8
            expo.modules.securestore.AuthenticationHelper$authenticateCipher$1 r0 = (expo.modules.securestore.AuthenticationHelper$authenticateCipher$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            expo.modules.securestore.AuthenticationHelper$authenticateCipher$1 r0 = new expo.modules.securestore.AuthenticationHelper$authenticateCipher$1
            r0.<init>(r4, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L40
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r8)
            if (r6 == 0) goto L59
            r0.label = r3
            java.lang.Object r8 = r4.openAuthenticationPrompt(r5, r7, r0)
            if (r8 != r1) goto L40
            return r1
        L40:
            androidx.biometric.BiometricPrompt$AuthenticationResult r8 = (androidx.biometric.BiometricPrompt.AuthenticationResult) r8
            androidx.biometric.BiometricPrompt$CryptoObject r5 = r8.getCryptoObject()
            if (r5 == 0) goto L4f
            javax.crypto.Cipher r5 = r5.getCipher()
            if (r5 == 0) goto L4f
            return r5
        L4f:
            expo.modules.securestore.AuthenticationException r5 = new expo.modules.securestore.AuthenticationException
            java.lang.String r6 = "Couldn't get cipher from authentication result"
            r7 = 2
            r8 = 0
            r5.<init>(r6, r8, r7, r8)
            throw r5
        L59:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.securestore.AuthenticationHelper.authenticateCipher(javax.crypto.Cipher, boolean, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object openAuthenticationPrompt(javax.crypto.Cipher r8, java.lang.String r9, kotlin.coroutines.Continuation<? super androidx.biometric.BiometricPrompt.AuthenticationResult> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof expo.modules.securestore.AuthenticationHelper$openAuthenticationPrompt$1
            if (r0 == 0) goto L14
            r0 = r10
            expo.modules.securestore.AuthenticationHelper$openAuthenticationPrompt$1 r0 = (expo.modules.securestore.AuthenticationHelper$openAuthenticationPrompt$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            expo.modules.securestore.AuthenticationHelper$openAuthenticationPrompt$1 r0 = new expo.modules.securestore.AuthenticationHelper$openAuthenticationPrompt$1
            r0.<init>(r7, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L35
            if (r2 != r4) goto L2d
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L2b
            goto L72
        L2b:
            r8 = move-exception
            goto L7d
        L2d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L35:
            kotlin.ResultKt.throwOnFailure(r10)
            boolean r10 = r7.isAuthenticating
            r2 = 2
            r5 = 0
            if (r10 != 0) goto L80
            r7.isAuthenticating = r4
            r7.assertBiometricsSupport()     // Catch: java.lang.Throwable -> L2b
            android.app.Activity r10 = r7.getCurrentActivity()     // Catch: java.lang.Throwable -> L2b
            boolean r6 = r10 instanceof androidx.fragment.app.FragmentActivity     // Catch: java.lang.Throwable -> L2b
            if (r6 == 0) goto L4e
            androidx.fragment.app.FragmentActivity r10 = (androidx.fragment.app.FragmentActivity) r10     // Catch: java.lang.Throwable -> L2b
            goto L4f
        L4e:
            r10 = r5
        L4f:
            if (r10 == 0) goto L75
            expo.modules.securestore.AuthenticationPrompt r2 = new expo.modules.securestore.AuthenticationPrompt     // Catch: java.lang.Throwable -> L2b
            android.content.Context r6 = r7.context     // Catch: java.lang.Throwable -> L2b
            r2.<init>(r10, r6, r9)     // Catch: java.lang.Throwable -> L2b
            kotlinx.coroutines.MainCoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Throwable -> L2b
            kotlinx.coroutines.MainCoroutineDispatcher r9 = r9.getImmediate()     // Catch: java.lang.Throwable -> L2b
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9     // Catch: java.lang.Throwable -> L2b
            expo.modules.securestore.AuthenticationHelper$openAuthenticationPrompt$2 r10 = new expo.modules.securestore.AuthenticationHelper$openAuthenticationPrompt$2     // Catch: java.lang.Throwable -> L2b
            r10.<init>(r2, r8, r5)     // Catch: java.lang.Throwable -> L2b
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10     // Catch: java.lang.Throwable -> L2b
            r0.label = r4     // Catch: java.lang.Throwable -> L2b
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r0)     // Catch: java.lang.Throwable -> L2b
            if (r10 != r1) goto L72
            return r1
        L72:
            r7.isAuthenticating = r3
            return r10
        L75:
            expo.modules.securestore.AuthenticationException r8 = new expo.modules.securestore.AuthenticationException     // Catch: java.lang.Throwable -> L2b
            java.lang.String r9 = "Cannot display biometric prompt when the app is not in the foreground"
            r8.<init>(r9, r5, r2, r5)     // Catch: java.lang.Throwable -> L2b
            throw r8     // Catch: java.lang.Throwable -> L2b
        L7d:
            r7.isAuthenticating = r3
            throw r8
        L80:
            expo.modules.securestore.AuthenticationException r8 = new expo.modules.securestore.AuthenticationException
            java.lang.String r9 = "Authentication is already in progress"
            r8.<init>(r9, r5, r2, r5)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.securestore.AuthenticationHelper.openAuthenticationPrompt(javax.crypto.Cipher, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void assertBiometricsSupport() {
        BiometricManager from = BiometricManager.from(this.context);
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        int canAuthenticate = from.canAuthenticate(15);
        if (canAuthenticate == -2) {
            throw new AuthenticationException("Biometric authentication is unsupported", null, 2, null);
        }
        if (canAuthenticate != -1) {
            if (canAuthenticate != 1) {
                if (canAuthenticate == 15) {
                    throw new AuthenticationException("An update is required before the biometrics can be used", null, 2, null);
                }
                if (canAuthenticate == 11) {
                    throw new AuthenticationException("No biometrics are currently enrolled", null, 2, null);
                }
                if (canAuthenticate != 12) {
                    return;
                }
            }
            throw new AuthenticationException("No hardware available for biometric authentication. Use expo-local-authentication to check if the device supports it", null, 2, null);
        }
        throw new AuthenticationException("Biometric authentication status is unknown", null, 2, null);
    }

    private final Activity getCurrentActivity() {
        Object module = this.moduleRegistry.getModule(ActivityProvider.class);
        Intrinsics.checkNotNullExpressionValue(module, "getModule(...)");
        return ((ActivityProvider) module).getCurrentActivity();
    }
}
