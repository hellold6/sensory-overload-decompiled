package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: BuiltinSpecialProperties.kt */
/* loaded from: classes3.dex */
public final class BuiltinSpecialPropertiesKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName child(FqName fqName, String str) {
        Name identifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return fqName.child(identifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName childSafe(FqNameUnsafe fqNameUnsafe, String str) {
        Name identifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return fqNameUnsafe.child(identifier).toSafe();
    }
}
