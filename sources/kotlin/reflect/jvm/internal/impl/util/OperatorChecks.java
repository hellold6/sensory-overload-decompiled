package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ImplicitClassReceiver;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.util.MemberKindCheck;
import kotlin.reflect.jvm.internal.impl.util.ReturnsCheck;
import kotlin.reflect.jvm.internal.impl.util.ValueParameterCountCheck;

/* compiled from: modifierChecks.kt */
/* loaded from: classes3.dex */
public final class OperatorChecks extends AbstractModifierChecks {
    public static final OperatorChecks INSTANCE = new OperatorChecks();
    private static final List<Checks> checks = CollectionsKt.listOf((Object[]) new Checks[]{new Checks(OperatorNameConventions.GET, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, new ValueParameterCountCheck.AtLeast(1)}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.SET, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, new ValueParameterCountCheck.AtLeast(2)}, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$$Lambda$0
        @Override // kotlin.jvm.functions.Function1
        public Object invoke(Object obj) {
            String checks$lambda$2;
            checks$lambda$2 = OperatorChecks.checks$lambda$2((FunctionDescriptor) obj);
            return checks$lambda$2;
        }
    }), new Checks(OperatorNameConventions.GET_VALUE, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new ValueParameterCountCheck.AtLeast(2), IsKPropertyCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.SET_VALUE, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new ValueParameterCountCheck.AtLeast(3), IsKPropertyCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.PROVIDE_DELEGATE, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new ValueParameterCountCheck.Equals(2), IsKPropertyCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.INVOKE, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.CONTAINS, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, ReturnsCheck.ReturnsBoolean.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.ITERATOR, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.NEXT, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.HAS_NEXT, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE, ReturnsCheck.ReturnsBoolean.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.RANGE_TO, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.RANGE_UNTIL, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.EQUALS, new Check[]{MemberKindCheck.Member.INSTANCE}, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$$Lambda$1
        @Override // kotlin.jvm.functions.Function1
        public Object invoke(Object obj) {
            String checks$lambda$6;
            checks$lambda$6 = OperatorChecks.checks$lambda$6((FunctionDescriptor) obj);
            return checks$lambda$6;
        }
    }), new Checks(OperatorNameConventions.COMPARE_TO, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ReturnsCheck.ReturnsInt.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.BINARY_OPERATION_NAMES, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.SIMPLE_UNARY_OPERATION_NAMES, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(CollectionsKt.listOf((Object[]) new Name[]{OperatorNameConventions.INC, OperatorNameConventions.DEC}), new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE}, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$$Lambda$2
        @Override // kotlin.jvm.functions.Function1
        public Object invoke(Object obj) {
            String checks$lambda$8;
            checks$lambda$8 = OperatorChecks.checks$lambda$8((FunctionDescriptor) obj);
            return checks$lambda$8;
        }
    }), new Checks(OperatorNameConventions.ASSIGNMENT_OPERATIONS, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ReturnsCheck.ReturnsUnit.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.COMPONENT_REGEX, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null)});

    private OperatorChecks() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks
    public List<Checks> getChecks$descriptors() {
        return checks;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String checks$lambda$2(FunctionDescriptor Checks) {
        Intrinsics.checkNotNullParameter(Checks, "$this$Checks");
        List<ValueParameterDescriptor> valueParameters = Checks.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "getValueParameters(...)");
        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) CollectionsKt.lastOrNull((List) valueParameters);
        boolean z = false;
        if (valueParameterDescriptor != null && !DescriptorUtilsKt.declaresOrInheritsDefaultValue(valueParameterDescriptor) && valueParameterDescriptor.getVarargElementType() == null) {
            z = true;
        }
        OperatorChecks operatorChecks = INSTANCE;
        if (z) {
            return null;
        }
        return "last parameter should not have a default value or be a vararg";
    }

    private static final boolean checks$lambda$6$isAny(DeclarationDescriptor declarationDescriptor) {
        return (declarationDescriptor instanceof ClassDescriptor) && KotlinBuiltIns.isAny((ClassDescriptor) declarationDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a8 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String checks$lambda$6(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r3) {
        /*
            java.lang.String r0 = "$this$Checks"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            kotlin.reflect.jvm.internal.impl.util.OperatorChecks r0 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks.INSTANCE
            kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks r0 = (kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks) r0
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r3.getContainingDeclaration()
            java.lang.String r1 = "getContainingDeclaration(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            boolean r0 = checks$lambda$6$isAny(r0)
            if (r0 != 0) goto L54
            java.util.Collection r0 = r3.getOverriddenDescriptors()
            java.lang.String r2 = "getOverriddenDescriptors(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L2d
            goto L4b
        L2d:
            java.util.Iterator r0 = r0.iterator()
        L31:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L4b
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r2
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r2 = r2.getContainingDeclaration()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            boolean r2 = checks$lambda$6$isAny(r2)
            if (r2 == 0) goto L31
            goto L54
        L4b:
            boolean r0 = kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt.isTypedEqualsInValueClass(r3)
            if (r0 == 0) goto L52
            goto L54
        L52:
            r0 = 0
            goto L55
        L54:
            r0 = 1
        L55:
            if (r0 != 0) goto La8
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "must override ''equals()'' in Any"
            r0.<init>(r2)
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r2 = r3.getContainingDeclaration()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            boolean r1 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isValueClass(r2)
            if (r1 == 0) goto La3
            kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer r1 = kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.SHORT_NAMES_IN_TYPES
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r3 = r3.getContainingDeclaration()
            java.lang.String r2 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r3
            kotlin.reflect.jvm.internal.impl.types.SimpleType r3 = r3.getDefaultType()
            java.lang.String r2 = "getDefaultType(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r3
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.replaceArgumentsWithStarProjections(r3)
            java.lang.String r3 = r1.renderType(r3)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = " or define ''equals(other: "
            r1.<init>(r2)
            java.lang.StringBuilder r3 = r1.append(r3)
            java.lang.String r1 = "): Boolean''"
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r3 = r3.toString()
            r0.append(r3)
        La3:
            java.lang.String r3 = r0.toString()
            return r3
        La8:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.util.OperatorChecks.checks$lambda$6(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String checks$lambda$8(FunctionDescriptor Checks) {
        boolean z;
        Intrinsics.checkNotNullParameter(Checks, "$this$Checks");
        ReceiverParameterDescriptor dispatchReceiverParameter = Checks.getDispatchReceiverParameter();
        if (dispatchReceiverParameter == null) {
            dispatchReceiverParameter = Checks.getExtensionReceiverParameter();
        }
        OperatorChecks operatorChecks = INSTANCE;
        boolean z2 = false;
        if (dispatchReceiverParameter != null) {
            KotlinType returnType = Checks.getReturnType();
            if (returnType != null) {
                KotlinType type = dispatchReceiverParameter.getType();
                Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                z = TypeUtilsKt.isSubtypeOf(returnType, type);
            } else {
                z = false;
            }
            if (z || operatorChecks.incDecCheckForExpectClass(Checks, dispatchReceiverParameter)) {
                z2 = true;
            }
        }
        if (z2) {
            return null;
        }
        return "receiver must be a supertype of the return type";
    }

    private final boolean incDecCheckForExpectClass(FunctionDescriptor functionDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor) {
        ClassId classId;
        KotlinType returnType;
        ReceiverValue value = receiverParameterDescriptor.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        if (!(value instanceof ImplicitClassReceiver)) {
            return false;
        }
        ClassDescriptor classDescriptor = ((ImplicitClassReceiver) value).getClassDescriptor();
        if (!classDescriptor.isExpect() || (classId = DescriptorUtilsKt.getClassId(classDescriptor)) == null) {
            return false;
        }
        ClassifierDescriptor findClassifierAcrossModuleDependencies = FindClassInModuleKt.findClassifierAcrossModuleDependencies(DescriptorUtilsKt.getModule(classDescriptor), classId);
        TypeAliasDescriptor typeAliasDescriptor = findClassifierAcrossModuleDependencies instanceof TypeAliasDescriptor ? (TypeAliasDescriptor) findClassifierAcrossModuleDependencies : null;
        if (typeAliasDescriptor == null || (returnType = functionDescriptor.getReturnType()) == null) {
            return false;
        }
        return TypeUtilsKt.isSubtypeOf(returnType, typeAliasDescriptor.getExpandedType());
    }
}
