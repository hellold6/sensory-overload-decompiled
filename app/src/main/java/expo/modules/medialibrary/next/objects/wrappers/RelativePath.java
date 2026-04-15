package expo.modules.medialibrary.next.objects.wrappers;

import android.os.Environment;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: RelativePath.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b\u0087@\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\u0005J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0016"}, d2 = {"Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "toFilePath", "toFilePath-impl", "equals", "", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "Companion", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class RelativePath {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String value;

    /* renamed from: box-impl */
    public static final /* synthetic */ RelativePath m1323boximpl(String str) {
        return new RelativePath(str);
    }

    /* renamed from: equals-impl */
    public static boolean m1325equalsimpl(String str, Object obj) {
        return (obj instanceof RelativePath) && Intrinsics.areEqual(str, ((RelativePath) obj).m1330unboximpl());
    }

    /* renamed from: equals-impl0 */
    public static final boolean m1326equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual(str, str2);
    }

    /* renamed from: hashCode-impl */
    public static int m1327hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* renamed from: toString-impl */
    public static String m1329toStringimpl(String str) {
        return "RelativePath(value=" + str + ")";
    }

    public boolean equals(Object obj) {
        return m1325equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m1327hashCodeimpl(this.value);
    }

    public String toString() {
        return m1329toStringimpl(this.value);
    }

    /* renamed from: unbox-impl */
    public final /* synthetic */ String m1330unboximpl() {
        return this.value;
    }

    private /* synthetic */ RelativePath(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    /* renamed from: constructor-impl */
    public static String m1324constructorimpl(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (new Regex("^[\\w -]+(/[\\w -]+)*/$").matches(value)) {
            return value;
        }
        throw new IllegalArgumentException(("Invalid relative path: " + value).toString());
    }

    /* renamed from: toFilePath-impl */
    public static final String m1328toFilePathimpl(String str) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + str + "/";
    }

    /* compiled from: RelativePath.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath$Companion;", "", "<init>", "()V", "create", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "mimeType", "Lexpo/modules/medialibrary/next/objects/wrappers/MimeType;", "albumName", "", "create-wht0CjE", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: create-wht0CjE$default */
        public static /* synthetic */ String m1331createwht0CjE$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            return companion.m1332createwht0CjE(str, str2);
        }

        /* renamed from: create-wht0CjE */
        public final String m1332createwht0CjE(String mimeType, String albumName) {
            if (albumName != null) {
                return RelativePath.m1324constructorimpl(MimeType.m1306albumRootDirectoryimpl(mimeType) + "/" + albumName + "/");
            }
            return RelativePath.m1324constructorimpl(MimeType.m1307assetRootDirectoryimpl(mimeType) + "/");
        }
    }
}
