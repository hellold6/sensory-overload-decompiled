package kotlin.reflect.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

/* compiled from: RuntimeTypeMapper.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"signature", "", "Ljava/lang/reflect/Method;", "getSignature", "(Ljava/lang/reflect/Method;)Ljava/lang/String;", "kotlin-reflection"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RuntimeTypeMapperKt {
    public static final /* synthetic */ String access$getSignature(Method method) {
        return getSignature(method);
    }

    public static final String getSignature(Method method) {
        StringBuilder append = new StringBuilder().append(method.getName());
        Class<?>[] parameterTypes = method.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
        StringBuilder append2 = append.append(ArraysKt.joinToString$default(parameterTypes, "", "(", ")", 0, (CharSequence) null, new Function1() { // from class: kotlin.reflect.jvm.internal.RuntimeTypeMapperKt$$Lambda$0
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                CharSequence _get_signature_$lambda$0;
                _get_signature_$lambda$0 = RuntimeTypeMapperKt._get_signature_$lambda$0((Class) obj);
                return _get_signature_$lambda$0;
            }
        }, 24, (Object) null));
        Class<?> returnType = method.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
        return append2.append(ReflectClassUtilKt.getDesc(returnType)).toString();
    }

    public static final CharSequence _get_signature_$lambda$0(Class cls) {
        Intrinsics.checkNotNull(cls);
        return ReflectClassUtilKt.getDesc(cls);
    }
}
