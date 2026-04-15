package expo.modules.kotlin.sharedobjects;

import expo.modules.kotlin.RuntimeContext;
import expo.modules.kotlin.UtilsKt;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.exception.InvalidSharedObjectIdException;
import expo.modules.kotlin.exception.UsingReleasedSharedObjectException;
import expo.modules.kotlin.jni.JavaScriptObject;
import expo.modules.kotlin.jni.JavaScriptWeakObject;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SharedObjectRegistry.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0015\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\tH\u0000¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\tH\u0000¢\u0006\u0004\b$\u0010%J\u0019\u0010&\u001a\u0004\u0018\u00010\u000e2\u0006\u0010 \u001a\u00020\tH\u0000¢\u0006\u0004\b'\u0010%J\u0017\u0010&\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b(J\u0017\u0010)\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0019\u001a\u00020\u000eH\u0000¢\u0006\u0002\b*J\u0017\u0010+\u001a\u0004\u0018\u00010\u000f2\u0006\u0010,\u001a\u00020\u000eH\u0000¢\u0006\u0002\b-J\u0013\u0010.\u001a\u00020\t*\u00020\tH\u0002¢\u0006\u0004\b/\u00100R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR6\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rj\u0002`\u00100\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u00061"}, d2 = {"Lexpo/modules/kotlin/sharedobjects/SharedObjectRegistry;", "", "runtimeContext", "Lexpo/modules/kotlin/RuntimeContext;", "<init>", "(Lexpo/modules/kotlin/RuntimeContext;)V", "runtimeContextHolder", "Ljava/lang/ref/WeakReference;", "currentId", "Lexpo/modules/kotlin/sharedobjects/SharedObjectId;", "I", "pairs", "", "Lkotlin/Pair;", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "Lexpo/modules/kotlin/jni/JavaScriptWeakObject;", "Lexpo/modules/kotlin/sharedobjects/SharedObjectPair;", "getPairs$expo_modules_core_release", "()Ljava/util/Map;", "setPairs$expo_modules_core_release", "(Ljava/util/Map;)V", "pullNextId", "pullNextId-HSeVr_g", "()I", "add", "native", "js", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "add-5WKnsLU$expo_modules_core_release", "(Lexpo/modules/kotlin/sharedobjects/SharedObject;Lexpo/modules/kotlin/jni/JavaScriptObject;)I", "delete", "", "id", "delete-kyJHjyY$expo_modules_core_release", "(I)V", "toNativeObject", "toNativeObject-kyJHjyY$expo_modules_core_release", "(I)Lexpo/modules/kotlin/sharedobjects/SharedObject;", "toNativeObjectOrNull", "toNativeObjectOrNull-kyJHjyY$expo_modules_core_release", "toNativeObjectOrNull$expo_modules_core_release", "toJavaScriptObjectOrNull", "toJavaScriptObjectOrNull$expo_modules_core_release", "toWeakJavaScriptObjectOrNull", "nativeObject", "toWeakJavaScriptObjectOrNull$expo_modules_core_release", "ensureWasNotRelease", "ensureWasNotRelease-tuC-2VU", "(I)I", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SharedObjectRegistry {
    private int currentId;
    private Map<SharedObjectId, Pair<SharedObject, JavaScriptWeakObject>> pairs;
    private final WeakReference<RuntimeContext> runtimeContextHolder;

    public SharedObjectRegistry(RuntimeContext runtimeContext) {
        Intrinsics.checkNotNullParameter(runtimeContext, "runtimeContext");
        this.runtimeContextHolder = UtilsKt.weak(runtimeContext);
        this.currentId = SharedObjectId.m1246constructorimpl(1);
        this.pairs = new LinkedHashMap();
    }

    public final Map<SharedObjectId, Pair<SharedObject, JavaScriptWeakObject>> getPairs$expo_modules_core_release() {
        return this.pairs;
    }

    public final void setPairs$expo_modules_core_release(Map<SharedObjectId, Pair<SharedObject, JavaScriptWeakObject>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.pairs = map;
    }

    /* renamed from: pullNextId-HSeVr_g, reason: not valid java name */
    private final int m1257pullNextIdHSeVr_g() {
        int i;
        synchronized (this) {
            i = this.currentId;
            this.currentId = SharedObjectId.m1246constructorimpl(i + 1);
        }
        return i;
    }

    /* renamed from: add-5WKnsLU$expo_modules_core_release, reason: not valid java name */
    public final int m1258add5WKnsLU$expo_modules_core_release(SharedObject r14, JavaScriptObject js) {
        Intrinsics.checkNotNullParameter(r14, "native");
        Intrinsics.checkNotNullParameter(js, "js");
        int m1257pullNextIdHSeVr_g = m1257pullNextIdHSeVr_g();
        r14.m1244setSharedObjectIdkyJHjyY$expo_modules_core_release(m1257pullNextIdHSeVr_g);
        JavaScriptObject.defineProperty$default(js, SharedObjectRegistryKt.sharedObjectIdPropertyName, m1257pullNextIdHSeVr_g, (List) null, 4, (Object) null);
        RuntimeContext runtimeContext = this.runtimeContextHolder.get();
        if (runtimeContext == null) {
            throw new Exceptions.AppContextLost();
        }
        runtimeContext.getJsiContext$expo_modules_core_release().setNativeStateForSharedObject(m1257pullNextIdHSeVr_g, js);
        int additionalMemoryPressure = r14.getAdditionalMemoryPressure();
        if (additionalMemoryPressure > 0) {
            js.setExternalMemoryPressure(additionalMemoryPressure);
        }
        if (r14 instanceof SharedRef) {
            JavaScriptObject.defineProperty$default(js, "nativeRefType", ((SharedRef) r14).getNativeRefType(), (List) null, 4, (Object) null);
        }
        JavaScriptWeakObject createWeak = js.createWeak();
        synchronized (this) {
            this.pairs.put(SharedObjectId.m1245boximpl(m1257pullNextIdHSeVr_g), TuplesKt.to(r14, createWeak));
            Unit unit = Unit.INSTANCE;
        }
        if (r14.getRuntimeContextHolder().get() == null) {
            r14.setRuntimeContextHolder(UtilsKt.weak(runtimeContext));
        }
        return m1257pullNextIdHSeVr_g;
    }

    /* renamed from: delete-kyJHjyY$expo_modules_core_release, reason: not valid java name */
    public final void m1259deletekyJHjyY$expo_modules_core_release(int id) {
        Pair<SharedObject, JavaScriptWeakObject> remove;
        synchronized (this) {
            remove = this.pairs.remove(SharedObjectId.m1245boximpl(id));
        }
        if (remove != null) {
            SharedObject component1 = remove.component1();
            component1.m1244setSharedObjectIdkyJHjyY$expo_modules_core_release(SharedObjectId.m1246constructorimpl(0));
            component1.sharedObjectDidRelease();
        }
    }

    /* renamed from: toNativeObject-kyJHjyY$expo_modules_core_release, reason: not valid java name */
    public final SharedObject m1260toNativeObjectkyJHjyY$expo_modules_core_release(int id) {
        Pair<SharedObject, JavaScriptWeakObject> pair = this.pairs.get(SharedObjectId.m1245boximpl(m1256ensureWasNotReleasetuC2VU(id)));
        SharedObject first = pair != null ? pair.getFirst() : null;
        if (first != null) {
            return first;
        }
        throw new InvalidSharedObjectIdException();
    }

    /* renamed from: toNativeObjectOrNull-kyJHjyY$expo_modules_core_release, reason: not valid java name */
    public final SharedObject m1261toNativeObjectOrNullkyJHjyY$expo_modules_core_release(int id) {
        SharedObject first;
        synchronized (this) {
            Pair<SharedObject, JavaScriptWeakObject> pair = this.pairs.get(SharedObjectId.m1245boximpl(id));
            first = pair != null ? pair.getFirst() : null;
        }
        return first;
    }

    public final SharedObject toNativeObjectOrNull$expo_modules_core_release(JavaScriptObject js) {
        Intrinsics.checkNotNullParameter(js, "js");
        if (!js.hasProperty(SharedObjectRegistryKt.sharedObjectIdPropertyName)) {
            return null;
        }
        Pair<SharedObject, JavaScriptWeakObject> pair = this.pairs.get(SharedObjectId.m1245boximpl(SharedObjectId.m1246constructorimpl(js.getProperty(SharedObjectRegistryKt.sharedObjectIdPropertyName).getInt())));
        if (pair != null) {
            return pair.getFirst();
        }
        return null;
    }

    public final JavaScriptObject toJavaScriptObjectOrNull$expo_modules_core_release(SharedObject r2) {
        JavaScriptObject lock;
        JavaScriptWeakObject second;
        Intrinsics.checkNotNullParameter(r2, "native");
        synchronized (this) {
            Pair<SharedObject, JavaScriptWeakObject> pair = this.pairs.get(SharedObjectId.m1245boximpl(r2.m1243getSharedObjectIdHSeVr_g$expo_modules_core_release()));
            lock = (pair == null || (second = pair.getSecond()) == null) ? null : second.lock();
        }
        return lock;
    }

    public final JavaScriptWeakObject toWeakJavaScriptObjectOrNull$expo_modules_core_release(SharedObject nativeObject) {
        JavaScriptWeakObject second;
        Intrinsics.checkNotNullParameter(nativeObject, "nativeObject");
        synchronized (this) {
            Pair<SharedObject, JavaScriptWeakObject> pair = this.pairs.get(SharedObjectId.m1245boximpl(nativeObject.m1243getSharedObjectIdHSeVr_g$expo_modules_core_release()));
            second = pair != null ? pair.getSecond() : null;
        }
        return second;
    }

    /* renamed from: ensureWasNotRelease-tuC-2VU, reason: not valid java name */
    private final int m1256ensureWasNotReleasetuC2VU(int i) {
        if (this.pairs.containsKey(SharedObjectId.m1245boximpl(i)) || i == 0 || i >= this.currentId) {
            return i;
        }
        throw new UsingReleasedSharedObjectException();
    }
}
