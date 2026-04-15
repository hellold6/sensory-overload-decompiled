package expo.modules.securestore;

import androidx.core.os.EnvironmentCompat;
import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SecureStoreExceptions.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/securestore/DecryptException;", "Lexpo/modules/kotlin/exception/CodedException;", "message", "", "key", "keychain", "cause", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", "expo-secure-store_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DecryptException extends CodedException {
    public /* synthetic */ DecryptException(String str, String str2, String str3, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DecryptException(String str, String key, String keychain, Throwable th) {
        super("Could not decrypt the value for key '" + key + "' under keychain '" + keychain + "'. Caused by: " + (str == null ? EnvironmentCompat.MEDIA_UNKNOWN : str), th);
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(keychain, "keychain");
    }
}
