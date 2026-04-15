package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.serialization.SerializerExtensionProtocol;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* compiled from: BuiltInSerializerProtocol.kt */
/* loaded from: classes3.dex */
public final class BuiltInSerializerProtocol extends SerializerExtensionProtocol {
    public static final BuiltInSerializerProtocol INSTANCE = new BuiltInSerializerProtocol();

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private BuiltInSerializerProtocol() {
        /*
            r18 = this;
            kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r1 = kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite.newInstance()
            kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.registerAllExtensions(r1)
            java.lang.String r0 = "apply(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, java.lang.Integer> r2 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.packageFqName
            java.lang.String r0 = "packageFqName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Constructor, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r3 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.constructorAnnotation
            java.lang.String r0 = "constructorAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r4 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.classAnnotation
            java.lang.String r0 = "classAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r5 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.functionAnnotation
            java.lang.String r0 = "functionAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r7 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.propertyAnnotation
            java.lang.String r0 = "propertyAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r8 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.propertyGetterAnnotation
            java.lang.String r0 = "propertyGetterAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r9 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.propertySetterAnnotation
            java.lang.String r0 = "propertySetterAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$EnumEntry, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r13 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.enumEntryAnnotation
            java.lang.String r0 = "enumEntryAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value> r14 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.compileTimeValue
            java.lang.String r0 = "compileTimeValue"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r15 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.parameterAnnotation
            java.lang.String r0 = "parameterAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r0)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r0 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.typeAnnotation
            java.lang.String r6 = "typeAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r6)
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation>> r6 = kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf.typeParameterAnnotation
            java.lang.String r10 = "typeParameterAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r10)
            r17 = r6
            r6 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r16 = r0
            r0 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInSerializerProtocol.<init>():void");
    }

    public final String getBuiltInsFilePath(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        return StringsKt.replace$default(fqName.asString(), FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX, false, 4, (Object) null) + IOUtils.DIR_SEPARATOR_UNIX + getBuiltInsFileName(fqName);
    }

    public final String getBuiltInsFileName(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        return shortName(fqName) + ".kotlin_builtins";
    }

    private final String shortName(FqName fqName) {
        if (fqName.isRoot()) {
            return "default-package";
        }
        String asString = fqName.shortName().asString();
        Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
        return asString;
    }
}
