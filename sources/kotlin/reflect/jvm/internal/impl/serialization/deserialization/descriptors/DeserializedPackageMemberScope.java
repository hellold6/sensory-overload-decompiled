package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;

/* compiled from: DeserializedPackageMemberScope.kt */
/* loaded from: classes3.dex */
public class DeserializedPackageMemberScope extends DeserializedMemberScope {
    private final String debugName;
    private final PackageFragmentDescriptor packageDescriptor;
    private final FqName packageFqName;

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected void addEnumEntryDescriptors(Collection<DeclarationDescriptor> result, Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public /* bridge */ /* synthetic */ Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter, Function1 function1) {
        return getContributedDescriptors(descriptorKindFilter, (Function1<? super Name, Boolean>) function1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DeserializedPackageMemberScope(kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor r10, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package r11, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r12, kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion r13, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource r14, kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r15, java.lang.String r16, kotlin.jvm.functions.Function0<? extends java.util.Collection<kotlin.reflect.jvm.internal.impl.name.Name>> r17) {
        /*
            r9 = this;
            r7 = r16
            java.lang.String r0 = "packageDescriptor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "proto"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "metadataVersion"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "components"
            r3 = r15
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            java.lang.String r0 = "debugName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "classNames"
            r8 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r3 = new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r0 = r11.getTypeTable()
            java.lang.String r4 = "getTypeTable(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            r3.<init>(r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable$Companion r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable.Companion
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable r4 = r11.getVersionRequirementTable()
            java.lang.String r6 = "getVersionRequirementTable(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable r4 = r0.create(r4)
            r1 = r10
            r2 = r12
            r5 = r13
            r6 = r14
            r0 = r15
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r0.createContext(r1, r2, r3, r4, r5, r6)
            java.util.List r2 = r11.getFunctionList()
            java.lang.String r1 = "getFunctionList(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            java.util.List r3 = r11.getPropertyList()
            java.lang.String r1 = "getPropertyList(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            java.util.List r4 = r11.getTypeAliasList()
            java.lang.String r1 = "getTypeAliasList(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r1)
            r1 = r0
            r5 = r8
            r0 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            r9.packageDescriptor = r10
            r9.debugName = r7
            kotlin.reflect.jvm.internal.impl.name.FqName r1 = r10.getFqName()
            r9.packageFqName = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope.<init>(kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver, kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource, kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents, java.lang.String, kotlin.jvm.functions.Function0):void");
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public List<DeclarationDescriptor> getContributedDescriptors(DescriptorKindFilter kindFilter, Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        Collection<DeclarationDescriptor> computeDescriptors = computeDescriptors(kindFilter, nameFilter, NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
        Iterable<ClassDescriptorFactory> fictitiousClassDescriptorFactories = getC().getComponents().getFictitiousClassDescriptorFactories();
        ArrayList arrayList = new ArrayList();
        Iterator<ClassDescriptorFactory> it = fictitiousClassDescriptorFactories.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, it.next().getAllContributedClassesIfPossible(this.packageFqName));
        }
        return CollectionsKt.plus((Collection) computeDescriptors, (Iterable) arrayList);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    public boolean hasClass(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (super.hasClass(name)) {
            return true;
        }
        Iterable<ClassDescriptorFactory> fictitiousClassDescriptorFactories = getC().getComponents().getFictitiousClassDescriptorFactories();
        if ((fictitiousClassDescriptorFactories instanceof Collection) && ((Collection) fictitiousClassDescriptorFactories).isEmpty()) {
            return false;
        }
        Iterator<ClassDescriptorFactory> it = fictitiousClassDescriptorFactories.iterator();
        while (it.hasNext()) {
            if (it.next().shouldCreateClass(this.packageFqName, name)) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected ClassId createClassId(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new ClassId(this.packageFqName, name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo2700getContributedClassifier(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        mo2704recordLookup(name, location);
        return super.mo2700getContributedClassifier(name, location);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    /* renamed from: recordLookup */
    public void mo2704recordLookup(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        UtilsKt.record(getC().getComponents().getLookupTracker(), location, this.packageDescriptor, name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected Set<Name> getNonDeclaredFunctionNames() {
        return SetsKt.emptySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected Set<Name> getNonDeclaredVariableNames() {
        return SetsKt.emptySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected Set<Name> getNonDeclaredClassifierNames() {
        return SetsKt.emptySet();
    }

    public String toString() {
        return this.debugName;
    }
}
