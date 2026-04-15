package kotlin.reflect.jvm.internal.impl.name;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: StandardClassIds.kt */
/* loaded from: classes3.dex */
public final class StandardClassIds {
    private static final ClassId AbstractMap;
    private static final ClassId Annotation;
    private static final ClassId AnnotationRetention;
    private static final ClassId AnnotationTarget;
    private static final ClassId Any;
    private static final ClassId Array;
    private static final FqName BASE_ANNOTATIONS_JVM_PACKAGE;
    private static final FqName BASE_ANNOTATION_PACKAGE;
    private static final FqName BASE_COLLECTIONS_PACKAGE;
    private static final FqName BASE_CONCURRENT_ATOMICS_PACKAGE;
    private static final FqName BASE_CONCURRENT_PACKAGE;
    private static final FqName BASE_CONTRACTS_PACKAGE;
    private static final FqName BASE_COROUTINES_INTRINSICS_PACKAGE;
    private static final FqName BASE_COROUTINES_PACKAGE;
    private static final FqName BASE_ENUMS_PACKAGE;
    private static final FqName BASE_INTERNAL_IR_PACKAGE;
    private static final FqName BASE_INTERNAL_PACKAGE;
    private static final FqName BASE_JVM_FUNCTIONS_PACKAGE;
    private static final FqName BASE_JVM_INTERNAL_PACKAGE;
    private static final FqName BASE_JVM_PACKAGE;
    private static final FqName BASE_KOTLIN_PACKAGE;
    private static final FqName BASE_RANGES_PACKAGE;
    private static final FqName BASE_REFLECT_PACKAGE;
    private static final FqName BASE_SEQUENCES_PACKAGE;
    private static final FqName BASE_TEST_PACKAGE;
    private static final FqName BASE_TEXT_PACKAGE;
    private static final ClassId Boolean;
    private static final ClassId Byte;
    private static final ClassId Char;
    private static final ClassId CharIterator;
    private static final ClassId CharRange;
    private static final ClassId CharSequence;
    private static final ClassId Cloneable;
    private static final ClassId Collection;
    private static final ClassId Comparable;
    private static final ClassId Continuation;
    private static final ClassId DeprecationLevel;
    private static final ClassId Double;
    private static final ClassId Enum;
    private static final ClassId EnumEntries;
    private static final ClassId Float;
    private static final ClassId Function;
    public static final StandardClassIds INSTANCE = new StandardClassIds();
    private static final ClassId Int;
    private static final ClassId IntRange;
    private static final ClassId Iterable;
    private static final ClassId Iterator;
    private static final ClassId KCallable;
    private static final ClassId KClass;
    private static final ClassId KFunction;
    private static final ClassId KMutableProperty;
    private static final ClassId KMutableProperty0;
    private static final ClassId KMutableProperty1;
    private static final ClassId KMutableProperty2;
    private static final ClassId KProperty;
    private static final ClassId KProperty0;
    private static final ClassId KProperty1;
    private static final ClassId KProperty2;
    private static final ClassId KType;
    private static final ClassId List;
    private static final ClassId ListIterator;
    private static final ClassId Long;
    private static final ClassId LongRange;
    private static final ClassId Map;
    private static final ClassId MapEntry;
    private static final ClassId MutableCollection;
    private static final ClassId MutableIterable;
    private static final ClassId MutableIterator;
    private static final ClassId MutableList;
    private static final ClassId MutableListIterator;
    private static final ClassId MutableMap;
    private static final ClassId MutableMapEntry;
    private static final ClassId MutableSet;
    private static final ClassId Nothing;
    private static final ClassId Number;
    private static final ClassId Result;
    private static final ClassId Set;
    private static final ClassId Short;
    private static final ClassId String;
    private static final ClassId Throwable;
    private static final ClassId UByte;
    private static final ClassId UInt;
    private static final ClassId ULong;
    private static final ClassId UShort;
    private static final ClassId Unit;
    private static final Set<ClassId> allBuiltinTypes;
    private static final Set<FqName> builtInsPackages;
    private static final Set<FqName> builtInsPackagesWithDefaultNamedImport;
    private static final Set<ClassId> constantAllowedTypes;
    private static final Map<ClassId, ClassId> elementTypeByPrimitiveArrayType;
    private static final Map<ClassId, ClassId> elementTypeByUnsignedArrayType;
    private static final Map<ClassId, ClassId> primitiveArrayTypeByElementType;
    private static final Set<ClassId> primitiveTypes;
    private static final Set<ClassId> signedIntegerTypes;
    private static final Map<ClassId, ClassId> unsignedArrayTypeByElementType;
    private static final Set<ClassId> unsignedTypes;

    private StandardClassIds() {
    }

    static {
        FqName fqName = new FqName("kotlin");
        BASE_KOTLIN_PACKAGE = fqName;
        Name identifier = Name.identifier("reflect");
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        FqName child = fqName.child(identifier);
        BASE_REFLECT_PACKAGE = child;
        Name identifier2 = Name.identifier("collections");
        Intrinsics.checkNotNullExpressionValue(identifier2, "identifier(...)");
        FqName child2 = fqName.child(identifier2);
        BASE_COLLECTIONS_PACKAGE = child2;
        Name identifier3 = Name.identifier("sequences");
        Intrinsics.checkNotNullExpressionValue(identifier3, "identifier(...)");
        BASE_SEQUENCES_PACKAGE = fqName.child(identifier3);
        Name identifier4 = Name.identifier("ranges");
        Intrinsics.checkNotNullExpressionValue(identifier4, "identifier(...)");
        FqName child3 = fqName.child(identifier4);
        BASE_RANGES_PACKAGE = child3;
        Name identifier5 = Name.identifier("jvm");
        Intrinsics.checkNotNullExpressionValue(identifier5, "identifier(...)");
        FqName child4 = fqName.child(identifier5);
        BASE_JVM_PACKAGE = child4;
        Name identifier6 = Name.identifier("annotations");
        Intrinsics.checkNotNullExpressionValue(identifier6, "identifier(...)");
        FqName child5 = fqName.child(identifier6);
        Name identifier7 = Name.identifier("jvm");
        Intrinsics.checkNotNullExpressionValue(identifier7, "identifier(...)");
        BASE_ANNOTATIONS_JVM_PACKAGE = child5.child(identifier7);
        Name identifier8 = Name.identifier("internal");
        Intrinsics.checkNotNullExpressionValue(identifier8, "identifier(...)");
        BASE_JVM_INTERNAL_PACKAGE = child4.child(identifier8);
        Name identifier9 = Name.identifier("functions");
        Intrinsics.checkNotNullExpressionValue(identifier9, "identifier(...)");
        BASE_JVM_FUNCTIONS_PACKAGE = child4.child(identifier9);
        Name identifier10 = Name.identifier("annotation");
        Intrinsics.checkNotNullExpressionValue(identifier10, "identifier(...)");
        FqName child6 = fqName.child(identifier10);
        BASE_ANNOTATION_PACKAGE = child6;
        Name identifier11 = Name.identifier("internal");
        Intrinsics.checkNotNullExpressionValue(identifier11, "identifier(...)");
        FqName child7 = fqName.child(identifier11);
        BASE_INTERNAL_PACKAGE = child7;
        Name identifier12 = Name.identifier("ir");
        Intrinsics.checkNotNullExpressionValue(identifier12, "identifier(...)");
        BASE_INTERNAL_IR_PACKAGE = child7.child(identifier12);
        Name identifier13 = Name.identifier("coroutines");
        Intrinsics.checkNotNullExpressionValue(identifier13, "identifier(...)");
        FqName child8 = fqName.child(identifier13);
        BASE_COROUTINES_PACKAGE = child8;
        Name identifier14 = Name.identifier("intrinsics");
        Intrinsics.checkNotNullExpressionValue(identifier14, "identifier(...)");
        BASE_COROUTINES_INTRINSICS_PACKAGE = child8.child(identifier14);
        Name identifier15 = Name.identifier("enums");
        Intrinsics.checkNotNullExpressionValue(identifier15, "identifier(...)");
        BASE_ENUMS_PACKAGE = fqName.child(identifier15);
        Name identifier16 = Name.identifier("contracts");
        Intrinsics.checkNotNullExpressionValue(identifier16, "identifier(...)");
        BASE_CONTRACTS_PACKAGE = fqName.child(identifier16);
        Name identifier17 = Name.identifier("concurrent");
        Intrinsics.checkNotNullExpressionValue(identifier17, "identifier(...)");
        FqName child9 = fqName.child(identifier17);
        BASE_CONCURRENT_PACKAGE = child9;
        Name identifier18 = Name.identifier("atomics");
        Intrinsics.checkNotNullExpressionValue(identifier18, "identifier(...)");
        FqName child10 = child9.child(identifier18);
        BASE_CONCURRENT_ATOMICS_PACKAGE = child10;
        Name identifier19 = Name.identifier("test");
        Intrinsics.checkNotNullExpressionValue(identifier19, "identifier(...)");
        BASE_TEST_PACKAGE = fqName.child(identifier19);
        Name identifier20 = Name.identifier("text");
        Intrinsics.checkNotNullExpressionValue(identifier20, "identifier(...)");
        BASE_TEXT_PACKAGE = fqName.child(identifier20);
        builtInsPackagesWithDefaultNamedImport = SetsKt.setOf((Object[]) new FqName[]{fqName, child2, child3, child6});
        builtInsPackages = SetsKt.setOf((Object[]) new FqName[]{fqName, child2, child3, child6, child, child7, child8, child10});
        Nothing = StandardClassIdsKt.access$baseId("Nothing");
        Unit = StandardClassIdsKt.access$baseId("Unit");
        Any = StandardClassIdsKt.access$baseId("Any");
        Enum = StandardClassIdsKt.access$baseId("Enum");
        Annotation = StandardClassIdsKt.access$baseId("Annotation");
        Array = StandardClassIdsKt.access$baseId("Array");
        ClassId access$baseId = StandardClassIdsKt.access$baseId("Boolean");
        Boolean = access$baseId;
        ClassId access$baseId2 = StandardClassIdsKt.access$baseId("Char");
        Char = access$baseId2;
        ClassId access$baseId3 = StandardClassIdsKt.access$baseId("Byte");
        Byte = access$baseId3;
        ClassId access$baseId4 = StandardClassIdsKt.access$baseId("Short");
        Short = access$baseId4;
        ClassId access$baseId5 = StandardClassIdsKt.access$baseId("Int");
        Int = access$baseId5;
        ClassId access$baseId6 = StandardClassIdsKt.access$baseId("Long");
        Long = access$baseId6;
        ClassId access$baseId7 = StandardClassIdsKt.access$baseId("Float");
        Float = access$baseId7;
        ClassId access$baseId8 = StandardClassIdsKt.access$baseId("Double");
        Double = access$baseId8;
        UByte = StandardClassIdsKt.access$unsignedId(access$baseId3);
        UShort = StandardClassIdsKt.access$unsignedId(access$baseId4);
        UInt = StandardClassIdsKt.access$unsignedId(access$baseId5);
        ULong = StandardClassIdsKt.access$unsignedId(access$baseId6);
        CharSequence = StandardClassIdsKt.access$baseId("CharSequence");
        String = StandardClassIdsKt.access$baseId("String");
        Throwable = StandardClassIdsKt.access$baseId("Throwable");
        Cloneable = StandardClassIdsKt.access$baseId("Cloneable");
        KProperty = StandardClassIdsKt.access$reflectId("KProperty");
        KMutableProperty = StandardClassIdsKt.access$reflectId("KMutableProperty");
        KProperty0 = StandardClassIdsKt.access$reflectId("KProperty0");
        KMutableProperty0 = StandardClassIdsKt.access$reflectId("KMutableProperty0");
        KProperty1 = StandardClassIdsKt.access$reflectId("KProperty1");
        KMutableProperty1 = StandardClassIdsKt.access$reflectId("KMutableProperty1");
        KProperty2 = StandardClassIdsKt.access$reflectId("KProperty2");
        KMutableProperty2 = StandardClassIdsKt.access$reflectId("KMutableProperty2");
        KFunction = StandardClassIdsKt.access$reflectId("KFunction");
        KClass = StandardClassIdsKt.access$reflectId("KClass");
        KCallable = StandardClassIdsKt.access$reflectId("KCallable");
        KType = StandardClassIdsKt.access$reflectId("KType");
        Comparable = StandardClassIdsKt.access$baseId("Comparable");
        Number = StandardClassIdsKt.access$baseId("Number");
        Function = StandardClassIdsKt.access$baseId("Function");
        Set<ClassId> of = SetsKt.setOf((Object[]) new ClassId[]{access$baseId, access$baseId2, access$baseId3, access$baseId4, access$baseId5, access$baseId6, access$baseId7, access$baseId8});
        primitiveTypes = of;
        signedIntegerTypes = SetsKt.setOf((Object[]) new ClassId[]{access$baseId3, access$baseId4, access$baseId5, access$baseId6});
        Set<ClassId> set = of;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set, 10)), 16));
        for (Object obj : set) {
            linkedHashMap.put(obj, StandardClassIdsKt.access$primitiveArrayId(((ClassId) obj).getShortClassName()));
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        primitiveArrayTypeByElementType = linkedHashMap2;
        elementTypeByPrimitiveArrayType = StandardClassIdsKt.access$inverseMap(linkedHashMap2);
        Set<ClassId> of2 = SetsKt.setOf((Object[]) new ClassId[]{UByte, UShort, UInt, ULong});
        unsignedTypes = of2;
        Set<ClassId> set2 = of2;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set2, 10)), 16));
        for (Object obj2 : set2) {
            linkedHashMap3.put(obj2, StandardClassIdsKt.access$primitiveArrayId(((ClassId) obj2).getShortClassName()));
        }
        LinkedHashMap linkedHashMap4 = linkedHashMap3;
        unsignedArrayTypeByElementType = linkedHashMap4;
        elementTypeByUnsignedArrayType = StandardClassIdsKt.access$inverseMap(linkedHashMap4);
        Set<ClassId> set3 = primitiveTypes;
        Set<ClassId> set4 = unsignedTypes;
        Set plus = SetsKt.plus((Set) set3, (Iterable) set4);
        ClassId classId = String;
        constantAllowedTypes = SetsKt.plus((Set<? extends ClassId>) plus, classId);
        Continuation = StandardClassIdsKt.access$coroutinesId("Continuation");
        Iterator = StandardClassIdsKt.access$collectionsId("Iterator");
        Iterable = StandardClassIdsKt.access$collectionsId("Iterable");
        Collection = StandardClassIdsKt.access$collectionsId("Collection");
        List = StandardClassIdsKt.access$collectionsId("List");
        ListIterator = StandardClassIdsKt.access$collectionsId("ListIterator");
        Set = StandardClassIdsKt.access$collectionsId("Set");
        ClassId access$collectionsId = StandardClassIdsKt.access$collectionsId("Map");
        Map = access$collectionsId;
        AbstractMap = StandardClassIdsKt.access$collectionsId("AbstractMap");
        MutableIterator = StandardClassIdsKt.access$collectionsId("MutableIterator");
        CharIterator = StandardClassIdsKt.access$collectionsId("CharIterator");
        MutableIterable = StandardClassIdsKt.access$collectionsId("MutableIterable");
        MutableCollection = StandardClassIdsKt.access$collectionsId("MutableCollection");
        MutableList = StandardClassIdsKt.access$collectionsId("MutableList");
        MutableListIterator = StandardClassIdsKt.access$collectionsId("MutableListIterator");
        MutableSet = StandardClassIdsKt.access$collectionsId("MutableSet");
        ClassId access$collectionsId2 = StandardClassIdsKt.access$collectionsId("MutableMap");
        MutableMap = access$collectionsId2;
        Name identifier21 = Name.identifier("Entry");
        Intrinsics.checkNotNullExpressionValue(identifier21, "identifier(...)");
        MapEntry = access$collectionsId.createNestedClassId(identifier21);
        Name identifier22 = Name.identifier("MutableEntry");
        Intrinsics.checkNotNullExpressionValue(identifier22, "identifier(...)");
        MutableMapEntry = access$collectionsId2.createNestedClassId(identifier22);
        Result = StandardClassIdsKt.access$baseId("Result");
        IntRange = StandardClassIdsKt.access$rangesId("IntRange");
        LongRange = StandardClassIdsKt.access$rangesId("LongRange");
        CharRange = StandardClassIdsKt.access$rangesId("CharRange");
        AnnotationRetention = StandardClassIdsKt.access$annotationId("AnnotationRetention");
        AnnotationTarget = StandardClassIdsKt.access$annotationId("AnnotationTarget");
        DeprecationLevel = StandardClassIdsKt.access$baseId("DeprecationLevel");
        EnumEntries = StandardClassIdsKt.access$enumsId("EnumEntries");
        allBuiltinTypes = SetsKt.plus((Set<? extends ClassId>) SetsKt.plus((Set<? extends ClassId>) SetsKt.plus((Set<? extends ClassId>) SetsKt.plus((Set<? extends ClassId>) SetsKt.plus((Set) set3, (Iterable) set4), classId), Unit), Any), Enum);
    }

    public final FqName getBASE_KOTLIN_PACKAGE() {
        return BASE_KOTLIN_PACKAGE;
    }

    public final FqName getBASE_REFLECT_PACKAGE() {
        return BASE_REFLECT_PACKAGE;
    }

    public final FqName getBASE_COLLECTIONS_PACKAGE() {
        return BASE_COLLECTIONS_PACKAGE;
    }

    public final FqName getBASE_RANGES_PACKAGE() {
        return BASE_RANGES_PACKAGE;
    }

    public final FqName getBASE_ANNOTATION_PACKAGE() {
        return BASE_ANNOTATION_PACKAGE;
    }

    public final FqName getBASE_COROUTINES_PACKAGE() {
        return BASE_COROUTINES_PACKAGE;
    }

    public final FqName getBASE_ENUMS_PACKAGE() {
        return BASE_ENUMS_PACKAGE;
    }

    public final ClassId getArray() {
        return Array;
    }

    public final ClassId getKFunction() {
        return KFunction;
    }

    public final ClassId getKClass() {
        return KClass;
    }

    public final ClassId getMutableList() {
        return MutableList;
    }

    public final ClassId getMutableSet() {
        return MutableSet;
    }

    public final ClassId getMutableMap() {
        return MutableMap;
    }

    public final ClassId getEnumEntries() {
        return EnumEntries;
    }
}
