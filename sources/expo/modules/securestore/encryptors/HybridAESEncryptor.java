package expo.modules.securestore.encryptors;

import android.content.Context;
import android.util.Base64;
import androidx.core.os.EnvironmentCompat;
import expo.modules.securestore.AuthenticationHelper;
import expo.modules.securestore.EncryptException;
import expo.modules.securestore.KeyStoreException;
import expo.modules.securestore.SecureStoreModule;
import expo.modules.securestore.SecureStoreOptions;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HybridAESEncryptor.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0016J6\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ6\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010 R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006&"}, d2 = {"Lexpo/modules/securestore/encryptors/HybridAESEncryptor;", "Lexpo/modules/securestore/encryptors/KeyBasedEncryptor;", "Ljava/security/KeyStore$PrivateKeyEntry;", "mContext", "Landroid/content/Context;", "mAESEncryptor", "Lexpo/modules/securestore/encryptors/AESEncryptor;", "<init>", "(Landroid/content/Context;Lexpo/modules/securestore/encryptors/AESEncryptor;)V", "mSecureRandom", "Ljava/security/SecureRandom;", "getExtendedKeyStoreAlias", "", "options", "Lexpo/modules/securestore/SecureStoreOptions;", AuthenticationHelper.REQUIRE_AUTHENTICATION_PROPERTY, "", "getKeyStoreAlias", "initializeKeyStoreEntry", "keyStore", "Ljava/security/KeyStore;", "createEncryptedItem", "Lorg/json/JSONObject;", "plaintextValue", "keyStoreEntry", "authenticationPrompt", "authenticationHelper", "Lexpo/modules/securestore/AuthenticationHelper;", "(Ljava/lang/String;Ljava/security/KeyStore$PrivateKeyEntry;ZLjava/lang/String;Lexpo/modules/securestore/AuthenticationHelper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptItem", "key", "encryptedItem", "(Ljava/lang/String;Lorg/json/JSONObject;Ljava/security/KeyStore$PrivateKeyEntry;Lexpo/modules/securestore/SecureStoreOptions;Lexpo/modules/securestore/AuthenticationHelper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rSACipher", "Ljavax/crypto/Cipher;", "getRSACipher", "()Ljavax/crypto/Cipher;", "Companion", "expo-secure-store_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HybridAESEncryptor implements KeyBasedEncryptor<KeyStore.PrivateKeyEntry> {
    private static final String ENCRYPTED_SECRET_KEY_PROPERTY = "esk";
    public static final String NAME = "hybrid";
    private static final String RSA_CIPHER = "RSA/None/PKCS1Padding";
    private final AESEncryptor mAESEncryptor;
    private Context mContext;
    private final SecureRandom mSecureRandom;

    public HybridAESEncryptor(Context mContext, AESEncryptor mAESEncryptor) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mAESEncryptor, "mAESEncryptor");
        this.mContext = mContext;
        this.mAESEncryptor = mAESEncryptor;
        this.mSecureRandom = new SecureRandom();
    }

    @Override // expo.modules.securestore.encryptors.KeyBasedEncryptor
    public /* bridge */ /* synthetic */ Object createEncryptedItem(String str, KeyStore.PrivateKeyEntry privateKeyEntry, boolean z, String str2, AuthenticationHelper authenticationHelper, Continuation continuation) {
        return createEncryptedItem2(str, privateKeyEntry, z, str2, authenticationHelper, (Continuation<? super JSONObject>) continuation);
    }

    @Override // expo.modules.securestore.encryptors.KeyBasedEncryptor
    public /* bridge */ /* synthetic */ Object decryptItem(String str, JSONObject jSONObject, KeyStore.PrivateKeyEntry privateKeyEntry, SecureStoreOptions secureStoreOptions, AuthenticationHelper authenticationHelper, Continuation continuation) {
        return decryptItem2(str, jSONObject, privateKeyEntry, secureStoreOptions, authenticationHelper, (Continuation<? super String>) continuation);
    }

    @Override // expo.modules.securestore.encryptors.KeyBasedEncryptor
    public String getExtendedKeyStoreAlias(SecureStoreOptions options, boolean requireAuthentication) {
        String str;
        Intrinsics.checkNotNullParameter(options, "options");
        if (requireAuthentication) {
            str = SecureStoreModule.AUTHENTICATED_KEYSTORE_SUFFIX;
        } else {
            str = SecureStoreModule.UNAUTHENTICATED_KEYSTORE_SUFFIX;
        }
        return getKeyStoreAlias(options) + ":" + str;
    }

    @Override // expo.modules.securestore.encryptors.KeyBasedEncryptor
    public String getKeyStoreAlias(SecureStoreOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return "RSA/None/PKCS1Padding:" + options.getKeychainService();
    }

    @Override // expo.modules.securestore.encryptors.KeyBasedEncryptor
    public KeyStore.PrivateKeyEntry initializeKeyStoreEntry(KeyStore keyStore, SecureStoreOptions options) throws GeneralSecurityException {
        Intrinsics.checkNotNullParameter(keyStore, "keyStore");
        Intrinsics.checkNotNullParameter(options, "options");
        throw new KeyStoreException("Tried to initialize HybridAESEncryptor key store entry on Android SDK >= 23. This shouldn't happen. If you see this message report an issue at https://github.com/expo/expo.");
    }

    /* renamed from: createEncryptedItem, reason: avoid collision after fix types in other method */
    public Object createEncryptedItem2(String str, KeyStore.PrivateKeyEntry privateKeyEntry, boolean z, String str2, AuthenticationHelper authenticationHelper, Continuation<? super JSONObject> continuation) throws GeneralSecurityException, JSONException {
        throw new EncryptException("HybridAESEncryption should not be used on Android SDK >= 23. This shouldn't happen. If you see this message report an issue at https://github.com/expo/expo.", EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, null, 8, null);
    }

    /* renamed from: decryptItem, reason: avoid collision after fix types in other method */
    public Object decryptItem2(String str, JSONObject jSONObject, KeyStore.PrivateKeyEntry privateKeyEntry, SecureStoreOptions secureStoreOptions, AuthenticationHelper authenticationHelper, Continuation<? super String> continuation) throws GeneralSecurityException, JSONException {
        byte[] decode = Base64.decode(jSONObject.getString(ENCRYPTED_SECRET_KEY_PROPERTY), 0);
        Cipher rSACipher = getRSACipher();
        rSACipher.init(2, privateKeyEntry.getPrivateKey());
        return this.mAESEncryptor.decryptItem2(str, jSONObject, new KeyStore.SecretKeyEntry(new SecretKeySpec(rSACipher.doFinal(decode), "AES")), secureStoreOptions, authenticationHelper, continuation);
    }

    private final Cipher getRSACipher() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance(RSA_CIPHER);
        Intrinsics.checkNotNullExpressionValue(cipher, "getInstance(...)");
        return cipher;
    }
}
