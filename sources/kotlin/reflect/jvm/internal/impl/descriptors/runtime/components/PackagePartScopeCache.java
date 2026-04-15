package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ChainedMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* compiled from: PackagePartScopeCache.kt */
/* loaded from: classes3.dex */
public final class PackagePartScopeCache {
    private final ConcurrentHashMap<ClassId, MemberScope> cache;
    private final ReflectKotlinClassFinder kotlinClassFinder;
    private final DeserializedDescriptorResolver resolver;

    public PackagePartScopeCache(DeserializedDescriptorResolver resolver, ReflectKotlinClassFinder kotlinClassFinder) {
        Intrinsics.checkNotNullParameter(resolver, "resolver");
        Intrinsics.checkNotNullParameter(kotlinClassFinder, "kotlinClassFinder");
        this.resolver = resolver;
        this.kotlinClassFinder = kotlinClassFinder;
        this.cache = new ConcurrentHashMap<>();
    }

    public final MemberScope getPackagePartScope(ReflectKotlinClass fileClass) {
        ArrayList listOf;
        Intrinsics.checkNotNullParameter(fileClass, "fileClass");
        ConcurrentHashMap<ClassId, MemberScope> concurrentHashMap = this.cache;
        ClassId classId = fileClass.getClassId();
        MemberScope memberScope = concurrentHashMap.get(classId);
        if (memberScope == null) {
            FqName packageFqName = fileClass.getClassId().getPackageFqName();
            if (fileClass.getClassHeader().getKind() == KotlinClassHeader.Kind.MULTIFILE_CLASS) {
                List<String> multifilePartNames = fileClass.getClassHeader().getMultifilePartNames();
                ArrayList arrayList = new ArrayList();
                for (String str : multifilePartNames) {
                    ClassId.Companion companion = ClassId.Companion;
                    FqName fqNameForTopLevelClassMaybeWithDollars = JvmClassName.byInternalName(str).getFqNameForTopLevelClassMaybeWithDollars();
                    Intrinsics.checkNotNullExpressionValue(fqNameForTopLevelClassMaybeWithDollars, "getFqNameForTopLevelClassMaybeWithDollars(...)");
                    KotlinJvmBinaryClass findKotlinClass = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, companion.topLevel(fqNameForTopLevelClassMaybeWithDollars), this.resolver.getComponents().getConfiguration().getMetadataVersion());
                    if (findKotlinClass != null) {
                        arrayList.add(findKotlinClass);
                    }
                }
                listOf = arrayList;
            } else {
                listOf = CollectionsKt.listOf(fileClass);
            }
            EmptyPackageFragmentDescriptor emptyPackageFragmentDescriptor = new EmptyPackageFragmentDescriptor(this.resolver.getComponents().getModuleDescriptor(), packageFqName);
            ArrayList arrayList2 = new ArrayList();
            Iterator it = listOf.iterator();
            while (it.hasNext()) {
                MemberScope createKotlinPackagePartScope = this.resolver.createKotlinPackagePartScope(emptyPackageFragmentDescriptor, (KotlinJvmBinaryClass) it.next());
                if (createKotlinPackagePartScope != null) {
                    arrayList2.add(createKotlinPackagePartScope);
                }
            }
            MemberScope create = ChainedMemberScope.Companion.create("package " + packageFqName + " (" + fileClass + ')', CollectionsKt.toList(arrayList2));
            MemberScope putIfAbsent = concurrentHashMap.putIfAbsent(classId, create);
            memberScope = putIfAbsent == null ? create : putIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(memberScope, "getOrPut(...)");
        return memberScope;
    }
}
