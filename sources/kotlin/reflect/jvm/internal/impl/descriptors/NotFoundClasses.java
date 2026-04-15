package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* compiled from: NotFoundClasses.kt */
/* loaded from: classes3.dex */
public final class NotFoundClasses {
    private final MemoizedFunctionToNotNull<ClassRequest, ClassDescriptor> classes;
    private final ModuleDescriptor module;
    private final MemoizedFunctionToNotNull<FqName, PackageFragmentDescriptor> packageFragments;
    private final StorageManager storageManager;

    public NotFoundClasses(StorageManager storageManager, ModuleDescriptor module) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(module, "module");
        this.storageManager = storageManager;
        this.module = module;
        this.packageFragments = storageManager.createMemoizedFunction(new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses$$Lambda$0
            private final NotFoundClasses arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                PackageFragmentDescriptor packageFragments$lambda$0;
                packageFragments$lambda$0 = NotFoundClasses.packageFragments$lambda$0(this.arg$0, (FqName) obj);
                return packageFragments$lambda$0;
            }
        });
        this.classes = storageManager.createMemoizedFunction(new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses$$Lambda$1
            private final NotFoundClasses arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                ClassDescriptor classes$lambda$2;
                classes$lambda$2 = NotFoundClasses.classes$lambda$2(this.arg$0, (NotFoundClasses.ClassRequest) obj);
                return classes$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NotFoundClasses.kt */
    /* loaded from: classes3.dex */
    public static final class ClassRequest {
        private final ClassId classId;
        private final List<Integer> typeParametersCount;

        public final ClassId component1() {
            return this.classId;
        }

        public final List<Integer> component2() {
            return this.typeParametersCount;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClassRequest)) {
                return false;
            }
            ClassRequest classRequest = (ClassRequest) obj;
            return Intrinsics.areEqual(this.classId, classRequest.classId) && Intrinsics.areEqual(this.typeParametersCount, classRequest.typeParametersCount);
        }

        public int hashCode() {
            return (this.classId.hashCode() * 31) + this.typeParametersCount.hashCode();
        }

        public String toString() {
            return "ClassRequest(classId=" + this.classId + ", typeParametersCount=" + this.typeParametersCount + ')';
        }

        public ClassRequest(ClassId classId, List<Integer> typeParametersCount) {
            Intrinsics.checkNotNullParameter(classId, "classId");
            Intrinsics.checkNotNullParameter(typeParametersCount, "typeParametersCount");
            this.classId = classId;
            this.typeParametersCount = typeParametersCount;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PackageFragmentDescriptor packageFragments$lambda$0(NotFoundClasses notFoundClasses, FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        return new EmptyPackageFragmentDescriptor(notFoundClasses.module, fqName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClassDescriptor classes$lambda$2(NotFoundClasses notFoundClasses, ClassRequest classRequest) {
        PackageFragmentDescriptor invoke;
        ClassDescriptor classDescriptor;
        Intrinsics.checkNotNullParameter(classRequest, "<destruct>");
        ClassId component1 = classRequest.component1();
        List<Integer> component2 = classRequest.component2();
        if (component1.isLocal()) {
            throw new UnsupportedOperationException("Unresolved local class: " + component1);
        }
        ClassId outerClassId = component1.getOuterClassId();
        if (outerClassId != null && (classDescriptor = notFoundClasses.getClass(outerClassId, CollectionsKt.drop(component2, 1))) != null) {
            invoke = classDescriptor;
        } else {
            invoke = notFoundClasses.packageFragments.invoke(component1.getPackageFqName());
        }
        boolean isNestedClass = component1.isNestedClass();
        StorageManager storageManager = notFoundClasses.storageManager;
        DeclarationDescriptor declarationDescriptor = invoke;
        Name shortClassName = component1.getShortClassName();
        Integer num = (Integer) CollectionsKt.firstOrNull((List) component2);
        return new MockClassDescriptor(storageManager, declarationDescriptor, shortClassName, isNestedClass, num != null ? num.intValue() : 0);
    }

    /* compiled from: NotFoundClasses.kt */
    /* loaded from: classes3.dex */
    public static final class MockClassDescriptor extends ClassDescriptorBase {
        private final List<TypeParameterDescriptor> declaredTypeParameters;
        private final boolean isInner;
        private final ClassTypeConstructorImpl typeConstructor;

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        /* renamed from: getCompanionObjectDescriptor */
        public ClassDescriptor mo2691getCompanionObjectDescriptor() {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        /* renamed from: getUnsubstitutedPrimaryConstructor */
        public ClassConstructorDescriptor mo2692getUnsubstitutedPrimaryConstructor() {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public ValueClassRepresentation<SimpleType> getValueClassRepresentation() {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        public boolean isActual() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isCompanionObject() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isData() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        public boolean isExpect() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        public boolean isExternal() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isFun() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isInline() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isValue() {
            return false;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public MockClassDescriptor(kotlin.reflect.jvm.internal.impl.storage.StorageManager r10, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r11, kotlin.reflect.jvm.internal.impl.name.Name r12, boolean r13, int r14) {
            /*
                r9 = this;
                java.lang.String r0 = "storageManager"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                java.lang.String r0 = "container"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                java.lang.String r0 = "name"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
                kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r5 = kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.NO_SOURCE
                r6 = 0
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r12
                r1.<init>(r2, r3, r4, r5, r6)
                r1.isInner = r13
                r10 = 0
                kotlin.ranges.IntRange r10 = kotlin.ranges.RangesKt.until(r10, r14)
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                java.util.ArrayList r11 = new java.util.ArrayList
                r12 = 10
                int r12 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r10, r12)
                r11.<init>(r12)
                java.util.Collection r11 = (java.util.Collection) r11
                java.util.Iterator r10 = r10.iterator()
            L33:
                boolean r12 = r10.hasNext()
                if (r12 == 0) goto L69
                r12 = r10
                kotlin.collections.IntIterator r12 = (kotlin.collections.IntIterator) r12
                int r7 = r12.nextInt()
                r8 = r2
                r2 = r1
                kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r2
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r12 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r3 = r12.getEMPTY()
                kotlin.reflect.jvm.internal.impl.types.Variance r5 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
                java.lang.StringBuilder r12 = new java.lang.StringBuilder
                java.lang.String r13 = "T"
                r12.<init>(r13)
                java.lang.StringBuilder r12 = r12.append(r7)
                java.lang.String r12 = r12.toString()
                kotlin.reflect.jvm.internal.impl.name.Name r6 = kotlin.reflect.jvm.internal.impl.name.Name.identifier(r12)
                r4 = 0
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r12 = kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl.createWithDefaultBound(r2, r3, r4, r5, r6, r7, r8)
                r2 = r8
                r11.add(r12)
                goto L33
            L69:
                java.util.List r11 = (java.util.List) r11
                r1.declaredTypeParameters = r11
                kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl r10 = new kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl
                r11 = r1
                kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r11 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r11
                r12 = r1
                kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters r12 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters) r12
                java.util.List r12 = kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters(r12)
                r13 = r1
                kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r13 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r13
                kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r13 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getModule(r13)
                kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r13 = r13.getBuiltIns()
                kotlin.reflect.jvm.internal.impl.types.SimpleType r13 = r13.getAnyType()
                java.util.Set r13 = kotlin.collections.SetsKt.setOf(r13)
                java.util.Collection r13 = (java.util.Collection) r13
                r10.<init>(r11, r12, r13, r2)
                r1.typeConstructor = r10
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.MockClassDescriptor.<init>(kotlin.reflect.jvm.internal.impl.storage.StorageManager, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, kotlin.reflect.jvm.internal.impl.name.Name, boolean, int):void");
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public ClassKind getKind() {
            return ClassKind.CLASS;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        public Modality getModality() {
            return Modality.FINAL;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility
        public DescriptorVisibility getVisibility() {
            DescriptorVisibility PUBLIC = DescriptorVisibilities.PUBLIC;
            Intrinsics.checkNotNullExpressionValue(PUBLIC, "PUBLIC");
            return PUBLIC;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
        public ClassTypeConstructorImpl getTypeConstructor() {
            return this.typeConstructor;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
        public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
            return this.declaredTypeParameters;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
        public boolean isInner() {
            return this.isInner;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
        public Annotations getAnnotations() {
            return Annotations.Companion.getEMPTY();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
        public MemberScope.Empty getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            return MemberScope.Empty.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public MemberScope.Empty getStaticScope() {
            return MemberScope.Empty.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public Collection<ClassConstructorDescriptor> getConstructors() {
            return SetsKt.emptySet();
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public Collection<ClassDescriptor> getSealedSubclasses() {
            return CollectionsKt.emptyList();
        }

        public String toString() {
            return "class " + getName() + " (not found)";
        }
    }

    public final ClassDescriptor getClass(ClassId classId, List<Integer> typeParametersCount) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        Intrinsics.checkNotNullParameter(typeParametersCount, "typeParametersCount");
        return this.classes.invoke(new ClassRequest(classId, typeParametersCount));
    }
}
