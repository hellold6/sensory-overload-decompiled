package kotlin.reflect.jvm.internal.impl.name;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: StandardClassIds.kt */
/* loaded from: classes3.dex */
public final class StandardClassIdsKt {
    public static final ClassId baseId(String str) {
        FqName base_kotlin_package = StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE();
        Name identifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return new ClassId(base_kotlin_package, identifier);
    }

    public static final ClassId unsignedId(ClassId classId) {
        FqName base_kotlin_package = StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE();
        Name identifier = Name.identifier("U" + classId.getShortClassName().getIdentifier());
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return new ClassId(base_kotlin_package, identifier);
    }

    public static final ClassId reflectId(String str) {
        FqName base_reflect_package = StandardClassIds.INSTANCE.getBASE_REFLECT_PACKAGE();
        Name identifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return new ClassId(base_reflect_package, identifier);
    }

    public static final ClassId primitiveArrayId(Name name) {
        FqName packageFqName = StandardClassIds.INSTANCE.getArray().getPackageFqName();
        Name identifier = Name.identifier(name.getIdentifier() + StandardClassIds.INSTANCE.getArray().getShortClassName().getIdentifier());
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return new ClassId(packageFqName, identifier);
    }

    public static final ClassId collectionsId(String str) {
        FqName base_collections_package = StandardClassIds.INSTANCE.getBASE_COLLECTIONS_PACKAGE();
        Name identifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return new ClassId(base_collections_package, identifier);
    }

    public static final ClassId rangesId(String str) {
        FqName base_ranges_package = StandardClassIds.INSTANCE.getBASE_RANGES_PACKAGE();
        Name identifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return new ClassId(base_ranges_package, identifier);
    }

    public static final ClassId annotationId(String str) {
        FqName base_annotation_package = StandardClassIds.INSTANCE.getBASE_ANNOTATION_PACKAGE();
        Name identifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return new ClassId(base_annotation_package, identifier);
    }

    public static final ClassId coroutinesId(String str) {
        FqName base_coroutines_package = StandardClassIds.INSTANCE.getBASE_COROUTINES_PACKAGE();
        Name identifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return new ClassId(base_coroutines_package, identifier);
    }

    public static final ClassId enumsId(String str) {
        FqName base_enums_package = StandardClassIds.INSTANCE.getBASE_ENUMS_PACKAGE();
        Name identifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return new ClassId(base_enums_package, identifier);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> Map<V, K> inverseMap(Map<K, ? extends V> map) {
        Set<Map.Entry<K, ? extends V>> entrySet = map.entrySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(entrySet, 10)), 16));
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Pair pair = TuplesKt.to(entry.getValue(), entry.getKey());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }
}
