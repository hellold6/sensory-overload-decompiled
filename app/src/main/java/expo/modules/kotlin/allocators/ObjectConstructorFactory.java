package expo.modules.kotlin.allocators;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;

/* compiled from: ObjectConstructorFactory.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\bJ$\u0010\t\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\nH\u0002J(\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\bH\u0002J\"\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\nH\u0002¨\u0006\r"}, d2 = {"Lexpo/modules/kotlin/allocators/ObjectConstructorFactory;", "", "<init>", "()V", "get", "Lexpo/modules/kotlin/allocators/ObjectConstructor;", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Lkotlin/reflect/KClass;", "tryToUseDefaultConstructor", "Ljava/lang/Class;", "tryToUseDefaultKotlinConstructor", "useUnsafeAllocator", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ObjectConstructorFactory {
    public final <T> ObjectConstructor<T> get(KClass<T> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        ObjectConstructor<T> tryToUseDefaultConstructor = tryToUseDefaultConstructor(JvmClassMappingKt.getJavaClass((KClass) clazz));
        return (tryToUseDefaultConstructor == null && (tryToUseDefaultConstructor = tryToUseDefaultKotlinConstructor(clazz)) == null) ? useUnsafeAllocator(JvmClassMappingKt.getJavaClass((KClass) clazz)) : tryToUseDefaultConstructor;
    }

    private final <T> ObjectConstructor<T> tryToUseDefaultConstructor(Class<T> clazz) {
        try {
            final Constructor<T> declaredConstructor = clazz.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new ObjectConstructor() { // from class: expo.modules.kotlin.allocators.ObjectConstructorFactory$$ExternalSyntheticLambda1
                @Override // expo.modules.kotlin.allocators.ObjectConstructor
                public final Object construct() {
                    Object tryToUseDefaultConstructor$lambda$0;
                    tryToUseDefaultConstructor$lambda$0 = ObjectConstructorFactory.tryToUseDefaultConstructor$lambda$0(declaredConstructor);
                    return tryToUseDefaultConstructor$lambda$0;
                }
            };
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object tryToUseDefaultConstructor$lambda$0(Constructor constructor) {
        return constructor.newInstance(new Object[0]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x004d, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004b, code lost:
    
        if (r1 == false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final <T> expo.modules.kotlin.allocators.ObjectConstructor<T> tryToUseDefaultKotlinConstructor(kotlin.reflect.KClass<T> r7) {
        /*
            r6 = this;
            java.util.Collection r7 = r7.getConstructors()
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.Iterator r7 = r7.iterator()
            r0 = 0
            r1 = 0
            r2 = r0
        Ld:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto L4b
            java.lang.Object r3 = r7.next()
            r4 = r3
            kotlin.reflect.KFunction r4 = (kotlin.reflect.KFunction) r4
            java.util.List r4 = r4.getParameters()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            boolean r5 = r4 instanceof java.util.Collection
            if (r5 == 0) goto L2e
            r5 = r4
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L2e
            goto L45
        L2e:
            java.util.Iterator r4 = r4.iterator()
        L32:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L45
            java.lang.Object r5 = r4.next()
            kotlin.reflect.KParameter r5 = (kotlin.reflect.KParameter) r5
            boolean r5 = r5.isOptional()
            if (r5 != 0) goto L32
            goto Ld
        L45:
            if (r1 == 0) goto L48
            goto L4d
        L48:
            r1 = 1
            r2 = r3
            goto Ld
        L4b:
            if (r1 != 0) goto L4e
        L4d:
            r2 = r0
        L4e:
            kotlin.reflect.KFunction r2 = (kotlin.reflect.KFunction) r2
            if (r2 != 0) goto L53
            return r0
        L53:
            expo.modules.kotlin.allocators.ObjectConstructorFactory$$ExternalSyntheticLambda2 r7 = new expo.modules.kotlin.allocators.ObjectConstructorFactory$$ExternalSyntheticLambda2
            r7.<init>()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.allocators.ObjectConstructorFactory.tryToUseDefaultKotlinConstructor(kotlin.reflect.KClass):expo.modules.kotlin.allocators.ObjectConstructor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object tryToUseDefaultKotlinConstructor$lambda$2(KFunction kFunction) {
        return kFunction.callBy(MapsKt.emptyMap());
    }

    private final <T> ObjectConstructor<T> useUnsafeAllocator(Class<T> clazz) {
        final UnsafeAllocator<T> createAllocator = UnsafeAllocator.INSTANCE.createAllocator(clazz);
        return new ObjectConstructor() { // from class: expo.modules.kotlin.allocators.ObjectConstructorFactory$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.allocators.ObjectConstructor
            public final Object construct() {
                Object newInstance;
                newInstance = UnsafeAllocator.this.newInstance();
                return newInstance;
            }
        };
    }
}
