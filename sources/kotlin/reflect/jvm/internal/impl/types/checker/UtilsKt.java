package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;

/* compiled from: utils.kt */
/* loaded from: classes3.dex */
public final class UtilsKt {
    public static final KotlinType findCorrespondingSupertype(KotlinType subtype, KotlinType supertype, TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        Intrinsics.checkNotNullParameter(subtype, "subtype");
        Intrinsics.checkNotNullParameter(supertype, "supertype");
        Intrinsics.checkNotNullParameter(typeCheckingProcedureCallbacks, "typeCheckingProcedureCallbacks");
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(new SubtypePathNode(subtype, null));
        TypeConstructor constructor = supertype.getConstructor();
        while (!arrayDeque.isEmpty()) {
            SubtypePathNode subtypePathNode = (SubtypePathNode) arrayDeque.poll();
            KotlinType type = subtypePathNode.getType();
            TypeConstructor constructor2 = type.getConstructor();
            if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor2, constructor)) {
                boolean isMarkedNullable = type.isMarkedNullable();
                for (SubtypePathNode previous = subtypePathNode.getPrevious(); previous != null; previous = previous.getPrevious()) {
                    KotlinType type2 = previous.getType();
                    List<TypeProjection> arguments = type2.getArguments();
                    if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
                        Iterator<T> it = arguments.iterator();
                        while (it.hasNext()) {
                            if (((TypeProjection) it.next()).getProjectionKind() != Variance.INVARIANT) {
                                KotlinType safeSubstitute = CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(TypeConstructorSubstitution.Companion.create(type2), false, 1, null).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT);
                                Intrinsics.checkNotNullExpressionValue(safeSubstitute, "safeSubstitute(...)");
                                type = approximate(safeSubstitute);
                                break;
                            }
                        }
                    }
                    type = TypeConstructorSubstitution.Companion.create(type2).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT);
                    Intrinsics.checkNotNull(type);
                    isMarkedNullable = isMarkedNullable || type2.isMarkedNullable();
                }
                TypeConstructor constructor3 = type.getConstructor();
                if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor3, constructor)) {
                    return TypeUtils.makeNullableAsSpecified(type, isMarkedNullable);
                }
                throw new AssertionError("Type constructors should be equals!\nsubstitutedSuperType: " + debugInfo(constructor3) + ", \n\nsupertype: " + debugInfo(constructor) + " \n" + typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor3, constructor));
            }
            for (KotlinType kotlinType : constructor2.mo2699getSupertypes()) {
                Intrinsics.checkNotNull(kotlinType);
                arrayDeque.add(new SubtypePathNode(kotlinType, subtypePathNode));
            }
        }
        return null;
    }

    private static final KotlinType approximate(KotlinType kotlinType) {
        return CapturedTypeApproximationKt.approximateCapturedTypes(kotlinType).getUpper();
    }

    private static final String debugInfo(TypeConstructor typeConstructor) {
        StringBuilder sb = new StringBuilder();
        debugInfo$lambda$1$unaryPlus("type: " + typeConstructor, sb);
        debugInfo$lambda$1$unaryPlus("hashCode: " + typeConstructor.hashCode(), sb);
        debugInfo$lambda$1$unaryPlus("javaClass: " + typeConstructor.getClass().getCanonicalName(), sb);
        for (ClassifierDescriptor mo2698getDeclarationDescriptor = typeConstructor.mo2698getDeclarationDescriptor(); mo2698getDeclarationDescriptor != null; mo2698getDeclarationDescriptor = mo2698getDeclarationDescriptor.getContainingDeclaration()) {
            debugInfo$lambda$1$unaryPlus("fqName: " + DescriptorRenderer.FQ_NAMES_IN_TYPES.render(mo2698getDeclarationDescriptor), sb);
            debugInfo$lambda$1$unaryPlus("javaClass: " + mo2698getDeclarationDescriptor.getClass().getCanonicalName(), sb);
        }
        return sb.toString();
    }

    private static final StringBuilder debugInfo$lambda$1$unaryPlus(String str, StringBuilder sb) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return sb.append(str).append('\n');
    }
}
