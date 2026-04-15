package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

/* compiled from: AbstractSignatureParts.kt */
/* loaded from: classes3.dex */
public abstract class AbstractSignatureParts<TAnnotation> {
    public abstract boolean forceWarning(TAnnotation tannotation, KotlinTypeMarker kotlinTypeMarker);

    public abstract AbstractAnnotationTypeQualifierResolver<TAnnotation> getAnnotationTypeQualifierResolver();

    public abstract Iterable<TAnnotation> getAnnotations(KotlinTypeMarker kotlinTypeMarker);

    public abstract Iterable<TAnnotation> getContainerAnnotations();

    public abstract AnnotationQualifierApplicabilityType getContainerApplicabilityType();

    public abstract JavaTypeQualifiersByElementType getContainerDefaultTypeQualifiers();

    public abstract boolean getContainerIsVarargParameter();

    protected abstract NullabilityQualifierWithMigrationStatus getDefaultNullability(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, JavaDefaultQualifiers javaDefaultQualifiers);

    public abstract boolean getEnableImprovementsInStrictMode();

    public abstract KotlinTypeMarker getEnhancedForWarnings(KotlinTypeMarker kotlinTypeMarker);

    public boolean getForceOnlyHeadTypeConstructor() {
        return false;
    }

    public abstract FqNameUnsafe getFqNameUnsafe(KotlinTypeMarker kotlinTypeMarker);

    public abstract boolean getSkipRawTypeArguments();

    public abstract TypeSystemContext getTypeSystem();

    public abstract boolean isArrayOrPrimitiveArray(KotlinTypeMarker kotlinTypeMarker);

    public abstract boolean isCovariant();

    public abstract boolean isEqual(KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2);

    public abstract boolean isFromJava(TypeParameterMarker typeParameterMarker);

    public boolean isNotNullTypeParameterCompat(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return false;
    }

    private final NullabilityQualifier getNullabilityQualifier(KotlinTypeMarker kotlinTypeMarker) {
        TypeSystemContext typeSystem = getTypeSystem();
        if (typeSystem.isMarkedNullable(typeSystem.lowerBoundIfFlexible(kotlinTypeMarker))) {
            return NullabilityQualifier.NULLABLE;
        }
        if (typeSystem.isMarkedNullable(typeSystem.upperBoundIfFlexible(kotlinTypeMarker))) {
            return null;
        }
        return NullabilityQualifier.NOT_NULL;
    }

    private final JavaTypeQualifiers extractQualifiers(KotlinTypeMarker kotlinTypeMarker) {
        NullabilityQualifier nullabilityQualifier;
        NullabilityQualifier nullabilityQualifier2 = getNullabilityQualifier(kotlinTypeMarker);
        MutabilityQualifier mutabilityQualifier = null;
        if (nullabilityQualifier2 == null) {
            KotlinTypeMarker enhancedForWarnings = getEnhancedForWarnings(kotlinTypeMarker);
            nullabilityQualifier = enhancedForWarnings != null ? getNullabilityQualifier(enhancedForWarnings) : null;
        } else {
            nullabilityQualifier = nullabilityQualifier2;
        }
        TypeSystemContext typeSystem = getTypeSystem();
        if (JavaToKotlinClassMap.INSTANCE.isReadOnly(getFqNameUnsafe(typeSystem.lowerBoundIfFlexible(kotlinTypeMarker)))) {
            mutabilityQualifier = MutabilityQualifier.READ_ONLY;
        } else if (JavaToKotlinClassMap.INSTANCE.isMutable(getFqNameUnsafe(typeSystem.upperBoundIfFlexible(kotlinTypeMarker)))) {
            mutabilityQualifier = MutabilityQualifier.MUTABLE;
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, getTypeSystem().isDefinitelyNotNullType(kotlinTypeMarker) || isNotNullTypeParameterCompat(kotlinTypeMarker), nullabilityQualifier != nullabilityQualifier2);
    }

    private final JavaTypeQualifiers extractQualifiersFromAnnotations(final TypeAndDefaultQualifiers typeAndDefaultQualifiers) {
        List emptyList;
        AnnotationQualifierApplicabilityType containerApplicabilityType;
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus;
        KotlinTypeMarker type;
        TypeConstructorMarker typeConstructor;
        if (typeAndDefaultQualifiers.getType() == null) {
            TypeSystemContext typeSystem = getTypeSystem();
            TypeParameterMarker typeParameterForArgument = typeAndDefaultQualifiers.getTypeParameterForArgument();
            if ((typeParameterForArgument != null ? typeSystem.getVariance(typeParameterForArgument) : null) == TypeVariance.IN) {
                return JavaTypeQualifiers.Companion.getNONE();
            }
        }
        boolean z = false;
        boolean z2 = typeAndDefaultQualifiers.getTypeParameterForArgument() == null;
        KotlinTypeMarker type2 = typeAndDefaultQualifiers.getType();
        if (type2 == null || (emptyList = getAnnotations(type2)) == null) {
            emptyList = CollectionsKt.emptyList();
        }
        TypeSystemContext typeSystem2 = getTypeSystem();
        KotlinTypeMarker type3 = typeAndDefaultQualifiers.getType();
        TypeParameterMarker typeParameterClassifier = (type3 == null || (typeConstructor = typeSystem2.typeConstructor(type3)) == null) ? null : typeSystem2.getTypeParameterClassifier(typeConstructor);
        boolean z3 = getContainerApplicabilityType() == AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS;
        if (z2) {
            if (!z3 && getEnableImprovementsInStrictMode() && (type = typeAndDefaultQualifiers.getType()) != null && isArrayOrPrimitiveArray(type)) {
                Iterable<TAnnotation> containerAnnotations = getContainerAnnotations();
                ArrayList arrayList = new ArrayList();
                for (TAnnotation tannotation : containerAnnotations) {
                    if (!getAnnotationTypeQualifierResolver().isTypeUseAnnotation(tannotation)) {
                        arrayList.add(tannotation);
                    }
                }
                emptyList = CollectionsKt.plus((Collection) arrayList, (Iterable) emptyList);
            } else {
                emptyList = CollectionsKt.plus((Iterable) getContainerAnnotations(), (Iterable) emptyList);
            }
        }
        MutabilityQualifier extractMutability = getAnnotationTypeQualifierResolver().extractMutability(emptyList);
        NullabilityQualifierWithMigrationStatus extractNullability = getAnnotationTypeQualifierResolver().extractNullability((Iterable) emptyList, (Function1) new Function1(this, typeAndDefaultQualifiers) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$0
            private final AbstractSignatureParts arg$0;
            private final AbstractSignatureParts.TypeAndDefaultQualifiers arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = typeAndDefaultQualifiers;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                boolean extractQualifiersFromAnnotations$lambda$6;
                extractQualifiersFromAnnotations$lambda$6 = AbstractSignatureParts.extractQualifiersFromAnnotations$lambda$6(this.arg$0, this.arg$1, obj);
                return Boolean.valueOf(extractQualifiersFromAnnotations$lambda$6);
            }
        });
        if (extractNullability != null) {
            NullabilityQualifier qualifier = extractNullability.getQualifier();
            if (extractNullability.getQualifier() == NullabilityQualifier.NOT_NULL && typeParameterClassifier != null) {
                z = true;
            }
            return new JavaTypeQualifiers(qualifier, extractMutability, z, extractNullability.isForWarningOnly());
        }
        if (z2 || z3) {
            containerApplicabilityType = getContainerApplicabilityType();
        } else {
            containerApplicabilityType = AnnotationQualifierApplicabilityType.TYPE_USE;
        }
        JavaTypeQualifiersByElementType defaultQualifiers = typeAndDefaultQualifiers.getDefaultQualifiers();
        JavaDefaultQualifiers javaDefaultQualifiers = defaultQualifiers != null ? defaultQualifiers.get(containerApplicabilityType) : null;
        NullabilityQualifierWithMigrationStatus boundsNullability = typeParameterClassifier != null ? getBoundsNullability(typeParameterClassifier) : null;
        NullabilityQualifierWithMigrationStatus defaultNullability = getDefaultNullability(boundsNullability, javaDefaultQualifiers);
        boolean z4 = (boundsNullability != null ? boundsNullability.getQualifier() : null) == NullabilityQualifier.NOT_NULL || !(typeParameterClassifier == null || javaDefaultQualifiers == null || !javaDefaultQualifiers.getDefinitelyNotNull());
        TypeParameterMarker typeParameterForArgument2 = typeAndDefaultQualifiers.getTypeParameterForArgument();
        if (typeParameterForArgument2 == null || (nullabilityQualifierWithMigrationStatus = getBoundsNullability(typeParameterForArgument2)) == null) {
            nullabilityQualifierWithMigrationStatus = null;
        } else if (nullabilityQualifierWithMigrationStatus.getQualifier() == NullabilityQualifier.NULLABLE) {
            nullabilityQualifierWithMigrationStatus = NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatus, NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null);
        }
        NullabilityQualifierWithMigrationStatus mostSpecific = mostSpecific(nullabilityQualifierWithMigrationStatus, defaultNullability);
        NullabilityQualifier qualifier2 = mostSpecific != null ? mostSpecific.getQualifier() : null;
        if (mostSpecific != null && mostSpecific.isForWarningOnly()) {
            z = true;
        }
        return new JavaTypeQualifiers(qualifier2, extractMutability, z4, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean extractQualifiersFromAnnotations$lambda$6(AbstractSignatureParts abstractSignatureParts, TypeAndDefaultQualifiers typeAndDefaultQualifiers, Object extractNullability) {
        Intrinsics.checkNotNullParameter(extractNullability, "$this$extractNullability");
        return abstractSignatureParts.forceWarning(extractNullability, typeAndDefaultQualifiers.getType());
    }

    private final NullabilityQualifierWithMigrationStatus mostSpecific(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus2) {
        return (nullabilityQualifierWithMigrationStatus != null && (nullabilityQualifierWithMigrationStatus2 == null || ((!nullabilityQualifierWithMigrationStatus.isForWarningOnly() || nullabilityQualifierWithMigrationStatus2.isForWarningOnly()) && ((!nullabilityQualifierWithMigrationStatus.isForWarningOnly() && nullabilityQualifierWithMigrationStatus2.isForWarningOnly()) || (nullabilityQualifierWithMigrationStatus.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus2.getQualifier()) >= 0 && nullabilityQualifierWithMigrationStatus.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus2.getQualifier()) > 0))))) ? nullabilityQualifierWithMigrationStatus : nullabilityQualifierWithMigrationStatus2;
    }

    private final NullabilityQualifierWithMigrationStatus getBoundsNullability(TypeParameterMarker typeParameterMarker) {
        ArrayList arrayList;
        NullabilityQualifier nullabilityQualifier;
        TypeSystemContext typeSystem = getTypeSystem();
        if (!isFromJava(typeParameterMarker)) {
            return null;
        }
        List<KotlinTypeMarker> upperBounds = typeSystem.getUpperBounds(typeParameterMarker);
        List<KotlinTypeMarker> list = upperBounds;
        boolean z = list instanceof Collection;
        if (!z || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (!typeSystem.isError((KotlinTypeMarker) it.next())) {
                    if (!z || !list.isEmpty()) {
                        Iterator<T> it2 = list.iterator();
                        while (it2.hasNext()) {
                            if (getNullabilityQualifier((KotlinTypeMarker) it2.next()) != null) {
                                arrayList = upperBounds;
                                break;
                            }
                        }
                    }
                    if (!z || !list.isEmpty()) {
                        Iterator<T> it3 = list.iterator();
                        while (it3.hasNext()) {
                            if (getEnhancedForWarnings((KotlinTypeMarker) it3.next()) != null) {
                                ArrayList arrayList2 = new ArrayList();
                                Iterator<T> it4 = list.iterator();
                                while (it4.hasNext()) {
                                    KotlinTypeMarker enhancedForWarnings = getEnhancedForWarnings((KotlinTypeMarker) it4.next());
                                    if (enhancedForWarnings != null) {
                                        arrayList2.add(enhancedForWarnings);
                                    }
                                }
                                arrayList = arrayList2;
                                List<KotlinTypeMarker> list2 = arrayList;
                                if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                                    Iterator<T> it5 = list2.iterator();
                                    while (it5.hasNext()) {
                                        if (!typeSystem.isNullableType((KotlinTypeMarker) it5.next())) {
                                            nullabilityQualifier = NullabilityQualifier.NOT_NULL;
                                            break;
                                        }
                                    }
                                }
                                nullabilityQualifier = NullabilityQualifier.NULLABLE;
                                return new NullabilityQualifierWithMigrationStatus(nullabilityQualifier, arrayList != upperBounds);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers> computeIndexedQualifiers(kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r10, java.lang.Iterable<? extends kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker> r11, final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementInfo r12, boolean r13) {
        /*
            r9 = this;
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "overrides"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.util.List r0 = r9.toIndexed(r10)
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r11, r2)
            r1.<init>(r2)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r2 = r11.iterator()
        L1f:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L33
            java.lang.Object r3 = r2.next()
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r3 = (kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker) r3
            java.util.List r3 = r9.toIndexed(r3)
            r1.add(r3)
            goto L1f
        L33:
            java.util.List r1 = (java.util.List) r1
            boolean r2 = r9.getForceOnlyHeadTypeConstructor()
            r3 = 1
            if (r2 != 0) goto L6c
            boolean r2 = r9.isCovariant()
            if (r2 == 0) goto L67
            boolean r2 = r11 instanceof java.util.Collection
            if (r2 == 0) goto L50
            r2 = r11
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L50
            goto L67
        L50:
            java.util.Iterator r11 = r11.iterator()
        L54:
            boolean r2 = r11.hasNext()
            if (r2 == 0) goto L67
            java.lang.Object r2 = r11.next()
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r2 = (kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker) r2
            boolean r2 = r9.isEqual(r10, r2)
            if (r2 != 0) goto L54
            goto L6c
        L67:
            int r10 = r0.size()
            goto L6d
        L6c:
            r10 = r3
        L6d:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers[] r11 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers[r10]
            r2 = 0
            r4 = r2
        L71:
            if (r4 >= r10) goto Ld4
            java.lang.Object r5 = r0.get(r4)
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$TypeAndDefaultQualifiers r5 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts.TypeAndDefaultQualifiers) r5
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r5 = r9.extractQualifiersFromAnnotations(r5)
            r6 = r1
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Collection r7 = (java.util.Collection) r7
            java.util.Iterator r6 = r6.iterator()
        L8b:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto Lb1
            java.lang.Object r8 = r6.next()
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r8 = kotlin.collections.CollectionsKt.getOrNull(r8, r4)
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$TypeAndDefaultQualifiers r8 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts.TypeAndDefaultQualifiers) r8
            if (r8 == 0) goto Laa
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r8 = r8.getType()
            if (r8 == 0) goto Laa
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r8 = r9.extractQualifiers(r8)
            goto Lab
        Laa:
            r8 = 0
        Lab:
            if (r8 == 0) goto L8b
            r7.add(r8)
            goto L8b
        Lb1:
            java.util.List r7 = (java.util.List) r7
            java.util.Collection r7 = (java.util.Collection) r7
            if (r4 != 0) goto Lbf
            boolean r6 = r9.isCovariant()
            if (r6 == 0) goto Lbf
            r6 = r3
            goto Lc0
        Lbf:
            r6 = r2
        Lc0:
            if (r4 != 0) goto Lca
            boolean r8 = r9.getContainerIsVarargParameter()
            if (r8 == 0) goto Lca
            r8 = r3
            goto Lcb
        Lca:
            r8 = r2
        Lcb:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r5 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementUtilsKt.computeQualifiersForOverride(r5, r7, r6, r8, r13)
            r11[r4] = r5
            int r4 = r4 + 1
            goto L71
        Ld4:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$1 r10 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$1
            r10.<init>(r12, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts.computeIndexedQualifiers(kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker, java.lang.Iterable, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementInfo, boolean):kotlin.jvm.functions.Function1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JavaTypeQualifiers computeIndexedQualifiers$lambda$18(TypeEnhancementInfo typeEnhancementInfo, JavaTypeQualifiers[] javaTypeQualifiersArr, int i) {
        Map<Integer, JavaTypeQualifiers> map;
        JavaTypeQualifiers javaTypeQualifiers;
        return (typeEnhancementInfo == null || (map = typeEnhancementInfo.getMap()) == null || (javaTypeQualifiers = map.get(Integer.valueOf(i))) == null) ? (i < 0 || i >= javaTypeQualifiersArr.length) ? JavaTypeQualifiers.Companion.getNONE() : javaTypeQualifiersArr[i] : javaTypeQualifiers;
    }

    private final <T> void flattenTree(T t, List<T> list, Function1<? super T, ? extends Iterable<? extends T>> function1) {
        list.add(t);
        Iterable<? extends T> invoke = function1.invoke(t);
        if (invoke != null) {
            Iterator<? extends T> it = invoke.iterator();
            while (it.hasNext()) {
                flattenTree(it.next(), list, function1);
            }
        }
    }

    private final <T> List<T> flattenTree(T t, Function1<? super T, ? extends Iterable<? extends T>> function1) {
        ArrayList arrayList = new ArrayList(1);
        flattenTree(t, arrayList, function1);
        return arrayList;
    }

    private final JavaTypeQualifiersByElementType extractAndMergeDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType) {
        return getAnnotationTypeQualifierResolver().extractAndMergeDefaultQualifiers(javaTypeQualifiersByElementType, getAnnotations(kotlinTypeMarker));
    }

    private final List<TypeAndDefaultQualifiers> toIndexed(KotlinTypeMarker kotlinTypeMarker) {
        final TypeSystemContext typeSystem = getTypeSystem();
        return flattenTree(new TypeAndDefaultQualifiers(kotlinTypeMarker, extractAndMergeDefaultQualifiers(kotlinTypeMarker, getContainerDefaultTypeQualifiers()), null), new Function1(this, typeSystem) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$2
            private final AbstractSignatureParts arg$0;
            private final TypeSystemContext arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = typeSystem;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                Iterable indexed$lambda$23$lambda$22;
                indexed$lambda$23$lambda$22 = AbstractSignatureParts.toIndexed$lambda$23$lambda$22(this.arg$0, this.arg$1, (AbstractSignatureParts.TypeAndDefaultQualifiers) obj);
                return indexed$lambda$23$lambda$22;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Iterable toIndexed$lambda$23$lambda$22(AbstractSignatureParts abstractSignatureParts, TypeSystemContext typeSystemContext, TypeAndDefaultQualifiers it) {
        TypeConstructorMarker typeConstructor;
        List<TypeParameterMarker> parameters;
        TypeAndDefaultQualifiers typeAndDefaultQualifiers;
        KotlinTypeMarker type;
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = null;
        if (abstractSignatureParts.getSkipRawTypeArguments() && (type = it.getType()) != null && typeSystemContext.isRawType(type)) {
            return null;
        }
        KotlinTypeMarker type2 = it.getType();
        if (type2 != null && (typeConstructor = typeSystemContext.typeConstructor(type2)) != null && (parameters = typeSystemContext.getParameters(typeConstructor)) != null) {
            List<TypeParameterMarker> list = parameters;
            List<TypeArgumentMarker> arguments = typeSystemContext.getArguments(it.getType());
            Iterator<T> it2 = list.iterator();
            Iterator<T> it3 = arguments.iterator();
            ArrayList arrayList2 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(list, 10), CollectionsKt.collectionSizeOrDefault(arguments, 10)));
            while (it2.hasNext() && it3.hasNext()) {
                TypeParameterMarker typeParameterMarker = (TypeParameterMarker) it2.next();
                KotlinTypeMarker type3 = typeSystemContext.getType((TypeArgumentMarker) it3.next());
                if (type3 == null) {
                    typeAndDefaultQualifiers = new TypeAndDefaultQualifiers(null, it.getDefaultQualifiers(), typeParameterMarker);
                } else {
                    typeAndDefaultQualifiers = new TypeAndDefaultQualifiers(type3, abstractSignatureParts.extractAndMergeDefaultQualifiers(type3, it.getDefaultQualifiers()), typeParameterMarker);
                }
                arrayList2.add(typeAndDefaultQualifiers);
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractSignatureParts.kt */
    /* loaded from: classes3.dex */
    public static final class TypeAndDefaultQualifiers {
        private final JavaTypeQualifiersByElementType defaultQualifiers;
        private final KotlinTypeMarker type;
        private final TypeParameterMarker typeParameterForArgument;

        public TypeAndDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType, TypeParameterMarker typeParameterMarker) {
            this.type = kotlinTypeMarker;
            this.defaultQualifiers = javaTypeQualifiersByElementType;
            this.typeParameterForArgument = typeParameterMarker;
        }

        public final KotlinTypeMarker getType() {
            return this.type;
        }

        public final JavaTypeQualifiersByElementType getDefaultQualifiers() {
            return this.defaultQualifiers;
        }

        public final TypeParameterMarker getTypeParameterForArgument() {
            return this.typeParameterForArgument;
        }
    }
}
