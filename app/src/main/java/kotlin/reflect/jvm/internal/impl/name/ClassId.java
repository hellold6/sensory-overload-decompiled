package kotlin.reflect.jvm.internal.impl.name;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* compiled from: ClassId.kt */
/* loaded from: classes3.dex */
public final class ClassId {
    public static final Companion Companion = new Companion(null);
    private final boolean isLocal;
    private final FqName packageFqName;
    private final FqName relativeClassName;

    @JvmStatic
    public static final ClassId topLevel(FqName fqName) {
        return Companion.topLevel(fqName);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClassId)) {
            return false;
        }
        ClassId classId = (ClassId) obj;
        return Intrinsics.areEqual(this.packageFqName, classId.packageFqName) && Intrinsics.areEqual(this.relativeClassName, classId.relativeClassName) && this.isLocal == classId.isLocal;
    }

    public int hashCode() {
        return (((this.packageFqName.hashCode() * 31) + this.relativeClassName.hashCode()) * 31) + Boolean.hashCode(this.isLocal);
    }

    public ClassId(FqName packageFqName, FqName relativeClassName, boolean z) {
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        Intrinsics.checkNotNullParameter(relativeClassName, "relativeClassName");
        this.packageFqName = packageFqName;
        this.relativeClassName = relativeClassName;
        this.isLocal = z;
        relativeClassName.isRoot();
    }

    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    public final FqName getRelativeClassName() {
        return this.relativeClassName;
    }

    public final boolean isLocal() {
        return this.isLocal;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ClassId(FqName packageFqName, Name topLevelName) {
        this(packageFqName, FqName.Companion.topLevel(topLevelName), false);
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        Intrinsics.checkNotNullParameter(topLevelName, "topLevelName");
    }

    public final Name getShortClassName() {
        return this.relativeClassName.shortName();
    }

    public final ClassId getOuterClassId() {
        FqName parent = this.relativeClassName.parent();
        if (parent.isRoot()) {
            return null;
        }
        return new ClassId(this.packageFqName, parent, this.isLocal);
    }

    public final boolean isNestedClass() {
        return !this.relativeClassName.parent().isRoot();
    }

    public final ClassId createNestedClassId(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new ClassId(this.packageFqName, this.relativeClassName.child(name), this.isLocal);
    }

    public final FqName asSingleFqName() {
        return this.packageFqName.isRoot() ? this.relativeClassName : new FqName(this.packageFqName.asString() + FilenameUtils.EXTENSION_SEPARATOR + this.relativeClassName.asString());
    }

    private static final String asString$escapeSlashes(FqName fqName) {
        String asString = fqName.asString();
        return StringsKt.contains$default((CharSequence) asString, IOUtils.DIR_SEPARATOR_UNIX, false, 2, (Object) null) ? "`" + asString + '`' : asString;
    }

    public final String asString() {
        if (this.packageFqName.isRoot()) {
            return asString$escapeSlashes(this.relativeClassName);
        }
        return StringsKt.replace$default(this.packageFqName.asString(), FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX, false, 4, (Object) null) + "/" + asString$escapeSlashes(this.relativeClassName);
    }

    public String toString() {
        return this.packageFqName.isRoot() ? "/" + asString() : asString();
    }

    /* compiled from: ClassId.kt */
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ClassId topLevel(FqName topLevelFqName) {
            Intrinsics.checkNotNullParameter(topLevelFqName, "topLevelFqName");
            return new ClassId(topLevelFqName.parent(), topLevelFqName.shortName());
        }

        public static /* synthetic */ ClassId fromString$default(Companion companion, String str, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.fromString(str, z);
        }

        @JvmStatic
        public final ClassId fromString(String string, boolean z) {
            String replace$default;
            String str;
            Intrinsics.checkNotNullParameter(string, "string");
            String str2 = string;
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str2, '`', 0, false, 6, (Object) null);
            if (indexOf$default == -1) {
                indexOf$default = string.length();
            }
            int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) str2, "/", indexOf$default, false, 4, (Object) null);
            if (lastIndexOf$default == -1) {
                replace$default = StringsKt.replace$default(string, "`", "", false, 4, (Object) null);
                str = "";
            } else {
                String substring = string.substring(0, lastIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                String replace$default2 = StringsKt.replace$default(substring, IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR, false, 4, (Object) null);
                String substring2 = string.substring(lastIndexOf$default + 1);
                Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                replace$default = StringsKt.replace$default(substring2, "`", "", false, 4, (Object) null);
                str = replace$default2;
            }
            return new ClassId(new FqName(str), new FqName(replace$default), z);
        }
    }
}
