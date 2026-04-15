package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.MetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerAbiStability;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;

/* compiled from: JvmPackagePartSource.kt */
/* loaded from: classes3.dex */
public final class JvmPackagePartSource implements DeserializedContainerSource {
    private final DeserializedContainerAbiStability abiStability;
    private final JvmClassName className;
    private final JvmClassName facadeClassName;
    private final IncompatibleVersionErrorData<MetadataVersion> incompatibility;
    private final boolean isPreReleaseInvisible;
    private final KotlinJvmBinaryClass knownJvmBinaryClass;
    private final String moduleName;

    public JvmPackagePartSource(JvmClassName className, JvmClassName jvmClassName, ProtoBuf.Package packageProto, NameResolver nameResolver, IncompatibleVersionErrorData<MetadataVersion> incompatibleVersionErrorData, boolean z, DeserializedContainerAbiStability abiStability, KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        String string;
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(packageProto, "packageProto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(abiStability, "abiStability");
        this.className = className;
        this.facadeClassName = jvmClassName;
        this.incompatibility = incompatibleVersionErrorData;
        this.isPreReleaseInvisible = z;
        this.abiStability = abiStability;
        this.knownJvmBinaryClass = kotlinJvmBinaryClass;
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Package, Integer> packageModuleName = JvmProtoBuf.packageModuleName;
        Intrinsics.checkNotNullExpressionValue(packageModuleName, "packageModuleName");
        Integer num = (Integer) ProtoBufUtilKt.getExtensionOrNull(packageProto, packageModuleName);
        this.moduleName = (num == null || (string = nameResolver.getString(num.intValue())) == null) ? "main" : string;
    }

    public JvmClassName getClassName() {
        return this.className;
    }

    public JvmClassName getFacadeClassName() {
        return this.facadeClassName;
    }

    public final KotlinJvmBinaryClass getKnownJvmBinaryClass() {
        return this.knownJvmBinaryClass;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public JvmPackagePartSource(kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass r11, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package r12, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r13, kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData<kotlin.reflect.jvm.internal.impl.metadata.deserialization.MetadataVersion> r14, boolean r15, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerAbiStability r16) {
        /*
            r10 = this;
            java.lang.String r0 = "kotlinClass"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "packageProto"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "abiStability"
            r8 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            kotlin.reflect.jvm.internal.impl.name.ClassId r0 = r11.getClassId()
            kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r2 = kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName.byClassId(r0)
            java.lang.String r0 = "byClassId(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader r0 = r11.getClassHeader()
            java.lang.String r0 = r0.getMultifileClassName()
            r1 = 0
            if (r0 == 0) goto L3c
            r3 = r0
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 <= 0) goto L3c
            kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r0 = kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName.byInternalName(r0)
            r1 = r0
        L3c:
            r9 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r15
            r3 = r1
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource.<init>(kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver, kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData, boolean, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerAbiStability):void");
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource
    public String getPresentableString() {
        return "Class '" + getClassId().asSingleFqName().asString() + '\'';
    }

    public final Name getSimpleName() {
        String internalName = getClassName().getInternalName();
        Intrinsics.checkNotNullExpressionValue(internalName, "getInternalName(...)");
        Name identifier = Name.identifier(StringsKt.substringAfterLast$default(internalName, IOUtils.DIR_SEPARATOR_UNIX, (String) null, 2, (Object) null));
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        return identifier;
    }

    public final ClassId getClassId() {
        FqName packageFqName = getClassName().getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName, "getPackageFqName(...)");
        return new ClassId(packageFqName, getSimpleName());
    }

    public String toString() {
        return getClass().getSimpleName() + ": " + getClassName();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
    public SourceFile getContainingFile() {
        SourceFile NO_SOURCE_FILE = SourceFile.NO_SOURCE_FILE;
        Intrinsics.checkNotNullExpressionValue(NO_SOURCE_FILE, "NO_SOURCE_FILE");
        return NO_SOURCE_FILE;
    }
}
