package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* loaded from: classes3.dex */
public class JvmClassName {
    private FqName fqName;
    private final String internalName;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0072 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static /* synthetic */ void $$$reportNull$$$0(int r9) {
        /*
            r0 = 5
            r1 = 3
            if (r9 == r1) goto Lc
            if (r9 == r0) goto Lc
            switch(r9) {
                case 8: goto Lc;
                case 9: goto Lc;
                case 10: goto Lc;
                default: goto L9;
            }
        L9:
            java.lang.String r2 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto Le
        Lc:
            java.lang.String r2 = "@NotNull method %s.%s must not return null"
        Le:
            r3 = 2
            if (r9 == r1) goto L18
            if (r9 == r0) goto L18
            switch(r9) {
                case 8: goto L18;
                case 9: goto L18;
                case 10: goto L18;
                default: goto L16;
            }
        L16:
            r4 = r1
            goto L19
        L18:
            r4 = r3
        L19:
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName"
            r6 = 0
            switch(r9) {
                case 1: goto L2e;
                case 2: goto L2e;
                case 3: goto L2b;
                case 4: goto L26;
                case 5: goto L2b;
                case 6: goto L26;
                case 7: goto L21;
                case 8: goto L2b;
                case 9: goto L2b;
                case 10: goto L2b;
                default: goto L21;
            }
        L21:
            java.lang.String r7 = "internalName"
            r4[r6] = r7
            goto L32
        L26:
            java.lang.String r7 = "fqName"
            r4[r6] = r7
            goto L32
        L2b:
            r4[r6] = r5
            goto L32
        L2e:
            java.lang.String r7 = "classId"
            r4[r6] = r7
        L32:
            java.lang.String r6 = "byFqNameWithoutInnerClasses"
            java.lang.String r7 = "internalNameByClassId"
            r8 = 1
            if (r9 == r1) goto L53
            if (r9 == r0) goto L50
            switch(r9) {
                case 8: goto L4b;
                case 9: goto L46;
                case 10: goto L41;
                default: goto L3e;
            }
        L3e:
            r4[r8] = r5
            goto L55
        L41:
            java.lang.String r5 = "getInternalName"
            r4[r8] = r5
            goto L55
        L46:
            java.lang.String r5 = "getPackageFqName"
            r4[r8] = r5
            goto L55
        L4b:
            java.lang.String r5 = "getFqNameForClassNameWithoutDollars"
            r4[r8] = r5
            goto L55
        L50:
            r4[r8] = r6
            goto L55
        L53:
            r4[r8] = r7
        L55:
            switch(r9) {
                case 1: goto L68;
                case 2: goto L65;
                case 3: goto L6c;
                case 4: goto L62;
                case 5: goto L6c;
                case 6: goto L62;
                case 7: goto L5d;
                case 8: goto L6c;
                case 9: goto L6c;
                case 10: goto L6c;
                default: goto L58;
            }
        L58:
            java.lang.String r5 = "byInternalName"
            r4[r3] = r5
            goto L6c
        L5d:
            java.lang.String r5 = "<init>"
            r4[r3] = r5
            goto L6c
        L62:
            r4[r3] = r6
            goto L6c
        L65:
            r4[r3] = r7
            goto L6c
        L68:
            java.lang.String r5 = "byClassId"
            r4[r3] = r5
        L6c:
            java.lang.String r2 = java.lang.String.format(r2, r4)
            if (r9 == r1) goto L7d
            if (r9 == r0) goto L7d
            switch(r9) {
                case 8: goto L7d;
                case 9: goto L7d;
                case 10: goto L7d;
                default: goto L77;
            }
        L77:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r2)
            goto L82
        L7d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r2)
        L82:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName.$$$reportNull$$$0(int):void");
    }

    public static JvmClassName byInternalName(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        return new JvmClassName(str);
    }

    public static JvmClassName byClassId(ClassId classId) {
        if (classId == null) {
            $$$reportNull$$$0(1);
        }
        return new JvmClassName(internalNameByClassId(classId));
    }

    public static String internalNameByClassId(ClassId classId) {
        if (classId == null) {
            $$$reportNull$$$0(2);
        }
        FqName packageFqName = classId.getPackageFqName();
        String replace = classId.getRelativeClassName().asString().replace(FilenameUtils.EXTENSION_SEPARATOR, Typography.dollar);
        if (!packageFqName.isRoot()) {
            replace = packageFqName.asString().replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX) + "/" + replace;
        }
        if (replace == null) {
            $$$reportNull$$$0(3);
        }
        return replace;
    }

    public static JvmClassName byFqNameWithoutInnerClasses(FqName fqName) {
        if (fqName == null) {
            $$$reportNull$$$0(4);
        }
        JvmClassName jvmClassName = new JvmClassName(fqName.asString().replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX));
        jvmClassName.fqName = fqName;
        return jvmClassName;
    }

    private JvmClassName(String str) {
        if (str == null) {
            $$$reportNull$$$0(7);
        }
        this.internalName = str;
    }

    public FqName getFqNameForTopLevelClassMaybeWithDollars() {
        return new FqName(this.internalName.replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR));
    }

    public FqName getPackageFqName() {
        int lastIndexOf = this.internalName.lastIndexOf("/");
        if (lastIndexOf == -1) {
            FqName fqName = FqName.ROOT;
            if (fqName == null) {
                $$$reportNull$$$0(9);
            }
            return fqName;
        }
        return new FqName(this.internalName.substring(0, lastIndexOf).replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR));
    }

    public String getInternalName() {
        String str = this.internalName;
        if (str == null) {
            $$$reportNull$$$0(10);
        }
        return str;
    }

    public String toString() {
        return this.internalName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.internalName.equals(((JvmClassName) obj).internalName);
    }

    public int hashCode() {
        return this.internalName.hashCode();
    }
}
