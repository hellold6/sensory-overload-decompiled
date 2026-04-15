package expo.modules.medialibrary.next.objects.wrappers;

import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import androidx.media3.common.MimeTypes;
import com.google.firebase.messaging.Constants;
import expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;

/* compiled from: MimeType.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\b\u0087@\u0018\u0000 *2\u00020\u0001:\u0001*B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u000fJ\r\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0013\u0010\u000fJ\r\u0010\u0014\u001a\u00020\u0003¢\u0006\u0004\b\u0015\u0010\u0005J\r\u0010\u0016\u001a\u00020\u0003¢\u0006\u0004\b\u0017\u0010\u0005J\r\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010 \u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020%HÖ\u0001¢\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b)\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0013\u0010\n\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005\u0088\u0001\u0002¨\u0006+"}, d2 = {"Lexpo/modules/medialibrary/next/objects/wrappers/MimeType;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "type", "getType-impl", "subType", "getSubType-impl", "isImage", "", "isImage-impl", "(Ljava/lang/String;)Z", "isVideo", "isVideo-impl", "isAudio", "isAudio-impl", "assetRootDirectory", "assetRootDirectory-impl", "albumRootDirectory", "albumRootDirectory-impl", "externalStorageAssetDirectory", "Ljava/io/File;", "externalStorageAssetDirectory-impl", "(Ljava/lang/String;)Ljava/io/File;", "mediaCollectionUri", "Landroid/net/Uri;", "mediaCollectionUri-impl", "(Ljava/lang/String;)Landroid/net/Uri;", "equals", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "Companion", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class MimeType {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String value;

    /* renamed from: box-impl */
    public static final /* synthetic */ MimeType m1308boximpl(String str) {
        return new MimeType(str);
    }

    /* renamed from: equals-impl */
    public static boolean m1310equalsimpl(String str, Object obj) {
        return (obj instanceof MimeType) && Intrinsics.areEqual(str, ((MimeType) obj).m1321unboximpl());
    }

    /* renamed from: equals-impl0 */
    public static final boolean m1311equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual(str, str2);
    }

    /* renamed from: hashCode-impl */
    public static int m1315hashCodeimpl(String str) {
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    /* renamed from: toString-impl */
    public static String m1320toStringimpl(String str) {
        return "MimeType(value=" + str + ")";
    }

    public boolean equals(Object obj) {
        return m1310equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m1315hashCodeimpl(this.value);
    }

    public String toString() {
        return m1320toStringimpl(this.value);
    }

    /* renamed from: unbox-impl */
    public final /* synthetic */ String m1321unboximpl() {
        return this.value;
    }

    private /* synthetic */ MimeType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    /* renamed from: constructor-impl */
    public static String m1309constructorimpl(String str) {
        if (str != null) {
            if (!new Regex("^[\\w-]+/([\\w-]+)*$").matches(str)) {
                throw new IllegalArgumentException(("Invalid MIME type: " + str).toString());
            }
        }
        return str;
    }

    /* renamed from: getType-impl */
    public static final String m1314getTypeimpl(String str) {
        if (str != null) {
            return StringsKt.substringBefore$default(str, IOUtils.DIR_SEPARATOR_UNIX, (String) null, 2, (Object) null);
        }
        return null;
    }

    /* renamed from: getSubType-impl */
    public static final String m1313getSubTypeimpl(String str) {
        if (str != null) {
            return StringsKt.substringAfter$default(str, IOUtils.DIR_SEPARATOR_UNIX, (String) null, 2, (Object) null);
        }
        return null;
    }

    /* renamed from: isImage-impl */
    public static final boolean m1317isImageimpl(String str) {
        return Intrinsics.areEqual(m1314getTypeimpl(str), "image");
    }

    /* renamed from: isVideo-impl */
    public static final boolean m1318isVideoimpl(String str) {
        return Intrinsics.areEqual(m1314getTypeimpl(str), MimeTypes.BASE_TYPE_VIDEO);
    }

    /* renamed from: isAudio-impl */
    public static final boolean m1316isAudioimpl(String str) {
        return Intrinsics.areEqual(m1314getTypeimpl(str), MimeTypes.BASE_TYPE_AUDIO);
    }

    /* renamed from: assetRootDirectory-impl */
    public static final String m1307assetRootDirectoryimpl(String str) {
        if (str == null) {
            String DIRECTORY_DCIM = Environment.DIRECTORY_DCIM;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_DCIM, "DIRECTORY_DCIM");
            return DIRECTORY_DCIM;
        }
        if (m1317isImageimpl(str) || m1318isVideoimpl(str)) {
            String DIRECTORY_DCIM2 = Environment.DIRECTORY_DCIM;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_DCIM2, "DIRECTORY_DCIM");
            return DIRECTORY_DCIM2;
        }
        if (m1316isAudioimpl(str)) {
            String DIRECTORY_MUSIC = Environment.DIRECTORY_MUSIC;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_MUSIC, "DIRECTORY_MUSIC");
            return DIRECTORY_MUSIC;
        }
        String DIRECTORY_DCIM3 = Environment.DIRECTORY_DCIM;
        Intrinsics.checkNotNullExpressionValue(DIRECTORY_DCIM3, "DIRECTORY_DCIM");
        return DIRECTORY_DCIM3;
    }

    /* renamed from: albumRootDirectory-impl */
    public static final String m1306albumRootDirectoryimpl(String str) {
        if (str == null) {
            String DIRECTORY_PICTURES = Environment.DIRECTORY_PICTURES;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_PICTURES, "DIRECTORY_PICTURES");
            return DIRECTORY_PICTURES;
        }
        if (m1317isImageimpl(str) || m1318isVideoimpl(str)) {
            String DIRECTORY_PICTURES2 = Environment.DIRECTORY_PICTURES;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_PICTURES2, "DIRECTORY_PICTURES");
            return DIRECTORY_PICTURES2;
        }
        if (m1316isAudioimpl(str)) {
            String DIRECTORY_MUSIC = Environment.DIRECTORY_MUSIC;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_MUSIC, "DIRECTORY_MUSIC");
            return DIRECTORY_MUSIC;
        }
        String DIRECTORY_PICTURES3 = Environment.DIRECTORY_PICTURES;
        Intrinsics.checkNotNullExpressionValue(DIRECTORY_PICTURES3, "DIRECTORY_PICTURES");
        return DIRECTORY_PICTURES3;
    }

    /* renamed from: externalStorageAssetDirectory-impl */
    public static final File m1312externalStorageAssetDirectoryimpl(String str) {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(m1307assetRootDirectoryimpl(str));
        Intrinsics.checkNotNullExpressionValue(externalStoragePublicDirectory, "getExternalStoragePublicDirectory(...)");
        return externalStoragePublicDirectory;
    }

    /* renamed from: mediaCollectionUri-impl */
    public static final Uri m1319mediaCollectionUriimpl(String str) {
        if (str == null) {
            return AlbumExtensionsKt.getEXTERNAL_CONTENT_URI();
        }
        if (m1317isImageimpl(str)) {
            Uri EXTERNAL_CONTENT_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Intrinsics.checkNotNullExpressionValue(EXTERNAL_CONTENT_URI, "EXTERNAL_CONTENT_URI");
            return EXTERNAL_CONTENT_URI;
        }
        if (m1318isVideoimpl(str)) {
            Uri EXTERNAL_CONTENT_URI2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            Intrinsics.checkNotNullExpressionValue(EXTERNAL_CONTENT_URI2, "EXTERNAL_CONTENT_URI");
            return EXTERNAL_CONTENT_URI2;
        }
        if (!m1316isAudioimpl(str)) {
            return AlbumExtensionsKt.getEXTERNAL_CONTENT_URI();
        }
        Uri EXTERNAL_CONTENT_URI3 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Intrinsics.checkNotNullExpressionValue(EXTERNAL_CONTENT_URI3, "EXTERNAL_CONTENT_URI");
        return EXTERNAL_CONTENT_URI3;
    }

    /* compiled from: MimeType.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/medialibrary/next/objects/wrappers/MimeType$Companion;", "", "<init>", "()V", Constants.MessagePayloadKeys.FROM, "Lexpo/modules/medialibrary/next/objects/wrappers/MimeType;", "fileUri", "Landroid/net/Uri;", "from-dctPOJs", "(Landroid/net/Uri;)Ljava/lang/String;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: from-dctPOJs */
        public final String m1322fromdctPOJs(Uri fileUri) {
            Intrinsics.checkNotNullParameter(fileUri, "fileUri");
            String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(fileUri.toString());
            if (fileExtensionFromUrl == null) {
                return MimeType.m1309constructorimpl(null);
            }
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
            if (mimeTypeFromExtension == null) {
                return MimeType.m1309constructorimpl(null);
            }
            return MimeType.m1309constructorimpl(mimeTypeFromExtension);
        }
    }
}
