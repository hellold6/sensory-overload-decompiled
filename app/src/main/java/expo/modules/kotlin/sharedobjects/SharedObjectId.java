package expo.modules.kotlin.sharedobjects;

import expo.modules.kotlin.RuntimeContext;
import expo.modules.kotlin.jni.JavaScriptObject;
import expo.modules.kotlin.jni.JavaScriptWeakObject;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SharedObjectRegistry.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u000f\u0010\rJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u001e\u0010\u0005J\u0010\u0010\u001f\u001a\u00020 HÖ\u0001¢\u0006\u0004\b!\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006#"}, d2 = {"Lexpo/modules/kotlin/sharedobjects/SharedObjectId;", "", "value", "", "constructor-impl", "(I)I", "getValue", "()I", "toNativeObject", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "runtimeContext", "Lexpo/modules/kotlin/RuntimeContext;", "toNativeObject-impl", "(ILexpo/modules/kotlin/RuntimeContext;)Lexpo/modules/kotlin/sharedobjects/SharedObject;", "toNativeObjectOrNull", "toNativeObjectOrNull-impl", "toJavaScriptObjectNull", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "toJavaScriptObjectNull-impl", "(ILexpo/modules/kotlin/RuntimeContext;)Lexpo/modules/kotlin/jni/JavaScriptObject;", "toWeakJavaScriptObjectNull", "Lexpo/modules/kotlin/jni/JavaScriptWeakObject;", "toWeakJavaScriptObjectNull-impl", "(ILexpo/modules/kotlin/RuntimeContext;)Lexpo/modules/kotlin/jni/JavaScriptWeakObject;", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class SharedObjectId {
    private final int value;

    /* renamed from: box-impl */
    public static final /* synthetic */ SharedObjectId m1245boximpl(int i) {
        return new SharedObjectId(i);
    }

    /* renamed from: constructor-impl */
    public static int m1246constructorimpl(int i) {
        return i;
    }

    /* renamed from: equals-impl */
    public static boolean m1247equalsimpl(int i, Object obj) {
        return (obj instanceof SharedObjectId) && i == ((SharedObjectId) obj).m1255unboximpl();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m1248equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: hashCode-impl */
    public static int m1249hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* renamed from: toString-impl */
    public static String m1253toStringimpl(int i) {
        return "SharedObjectId(value=" + i + ")";
    }

    public boolean equals(Object obj) {
        return m1247equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m1249hashCodeimpl(this.value);
    }

    public String toString() {
        return m1253toStringimpl(this.value);
    }

    /* renamed from: unbox-impl */
    public final /* synthetic */ int m1255unboximpl() {
        return this.value;
    }

    private /* synthetic */ SharedObjectId(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    /* renamed from: toNativeObject-impl */
    public static final SharedObject m1251toNativeObjectimpl(int i, RuntimeContext runtimeContext) {
        Intrinsics.checkNotNullParameter(runtimeContext, "runtimeContext");
        return runtimeContext.getSharedObjectRegistry().m1260toNativeObjectkyJHjyY$expo_modules_core_release(i);
    }

    /* renamed from: toNativeObjectOrNull-impl */
    public static final SharedObject m1252toNativeObjectOrNullimpl(int i, RuntimeContext runtimeContext) {
        Intrinsics.checkNotNullParameter(runtimeContext, "runtimeContext");
        return runtimeContext.getSharedObjectRegistry().m1261toNativeObjectOrNullkyJHjyY$expo_modules_core_release(i);
    }

    /* renamed from: toJavaScriptObjectNull-impl */
    public static final JavaScriptObject m1250toJavaScriptObjectNullimpl(int i, RuntimeContext runtimeContext) {
        Intrinsics.checkNotNullParameter(runtimeContext, "runtimeContext");
        SharedObject m1252toNativeObjectOrNullimpl = m1252toNativeObjectOrNullimpl(i, runtimeContext);
        if (m1252toNativeObjectOrNullimpl == null) {
            return null;
        }
        return runtimeContext.getSharedObjectRegistry().toJavaScriptObjectOrNull$expo_modules_core_release(m1252toNativeObjectOrNullimpl);
    }

    /* renamed from: toWeakJavaScriptObjectNull-impl */
    public static final JavaScriptWeakObject m1254toWeakJavaScriptObjectNullimpl(int i, RuntimeContext runtimeContext) {
        Intrinsics.checkNotNullParameter(runtimeContext, "runtimeContext");
        SharedObject m1252toNativeObjectOrNullimpl = m1252toNativeObjectOrNullimpl(i, runtimeContext);
        if (m1252toNativeObjectOrNullimpl == null) {
            return null;
        }
        return runtimeContext.getSharedObjectRegistry().toWeakJavaScriptObjectOrNull$expo_modules_core_release(m1252toNativeObjectOrNullimpl);
    }
}
