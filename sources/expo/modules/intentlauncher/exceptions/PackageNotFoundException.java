package expo.modules.intentlauncher.exceptions;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PackageNotFoundException.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/intentlauncher/exceptions/PackageNotFoundException;", "Lexpo/modules/kotlin/exception/CodedException;", "packageName", "", "<init>", "(Ljava/lang/String;)V", "expo-intent-launcher_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PackageNotFoundException extends CodedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PackageNotFoundException(String packageName) {
        super("Package not found: " + packageName, null, 2, null);
        Intrinsics.checkNotNullParameter(packageName, "packageName");
    }
}
