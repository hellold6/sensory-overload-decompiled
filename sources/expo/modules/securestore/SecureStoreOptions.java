package expo.modules.securestore;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SecureStoreOptions.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR$\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR$\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lexpo/modules/securestore/SecureStoreOptions;", "Lexpo/modules/kotlin/records/Record;", "Ljava/io/Serializable;", "authenticationPrompt", "", "keychainService", AuthenticationHelper.REQUIRE_AUTHENTICATION_PROPERTY, "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getAuthenticationPrompt$annotations", "()V", "getAuthenticationPrompt", "()Ljava/lang/String;", "setAuthenticationPrompt", "(Ljava/lang/String;)V", "getKeychainService$annotations", "getKeychainService", "setKeychainService", "getRequireAuthentication$annotations", "getRequireAuthentication", "()Z", "setRequireAuthentication", "(Z)V", "expo-secure-store_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SecureStoreOptions implements Record, Serializable {
    private String authenticationPrompt;
    private String keychainService;
    private boolean requireAuthentication;

    public SecureStoreOptions() {
        this(null, null, false, 7, null);
    }

    @Field
    public static /* synthetic */ void getAuthenticationPrompt$annotations() {
    }

    @Field
    public static /* synthetic */ void getKeychainService$annotations() {
    }

    @Field
    public static /* synthetic */ void getRequireAuthentication$annotations() {
    }

    public SecureStoreOptions(String authenticationPrompt, String keychainService, boolean z) {
        Intrinsics.checkNotNullParameter(authenticationPrompt, "authenticationPrompt");
        Intrinsics.checkNotNullParameter(keychainService, "keychainService");
        this.authenticationPrompt = authenticationPrompt;
        this.keychainService = keychainService;
        this.requireAuthentication = z;
    }

    public /* synthetic */ SecureStoreOptions(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? " " : str, (i & 2) != 0 ? SecureStoreModule.DEFAULT_KEYSTORE_ALIAS : str2, (i & 4) != 0 ? false : z);
    }

    public final String getAuthenticationPrompt() {
        return this.authenticationPrompt;
    }

    public final void setAuthenticationPrompt(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.authenticationPrompt = str;
    }

    public final String getKeychainService() {
        return this.keychainService;
    }

    public final void setKeychainService(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.keychainService = str;
    }

    public final boolean getRequireAuthentication() {
        return this.requireAuthentication;
    }

    public final void setRequireAuthentication(boolean z) {
        this.requireAuthentication = z;
    }
}
