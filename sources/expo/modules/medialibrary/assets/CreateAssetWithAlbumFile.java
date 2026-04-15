package expo.modules.medialibrary.assets;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import expo.modules.medialibrary.AssetFileException;
import expo.modules.medialibrary.MediaLibraryUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: CreateAsset.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\n\u0010\u0011\u001a\u0004\u0018\u00010\rH\u0003J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\rH\u0083@¢\u0006\u0002\u0010\u0016J\"\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018j\n\u0012\u0004\u0012\u00020\u0019\u0018\u0001`\u001aH\u0083@¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\t2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\"\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018j\n\u0012\u0004\u0012\u00020\u0019\u0018\u0001`\u001aH\u0086@¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001e"}, d2 = {"Lexpo/modules/medialibrary/assets/CreateAssetWithAlbumFile;", "", "context", "Landroid/content/Context;", "uri", "", "resolveWithAdditionalData", "", "albumFile", "Ljava/io/File;", "<init>", "(Landroid/content/Context;Ljava/lang/String;ZLjava/io/File;)V", "mUri", "Landroid/net/Uri;", "normalizeAssetUri", "isFileExtensionPresent", "()Z", "createContentResolverAssetEntry", "writeFileContentsToAsset", "", "localFile", "assetUri", "(Ljava/io/File;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createAssetUsingContentResolver", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "Lkotlin/collections/ArrayList;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createAssetFileLegacy", "execute", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreateAssetWithAlbumFile {
    private final File albumFile;
    private final Context context;
    private final Uri mUri;
    private final boolean resolveWithAdditionalData;

    public CreateAssetWithAlbumFile(Context context, String uri, boolean z, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.context = context;
        this.resolveWithAdditionalData = z;
        this.albumFile = file;
        this.mUri = normalizeAssetUri(uri);
    }

    public /* synthetic */ CreateAssetWithAlbumFile(Context context, String str, boolean z, File file, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i & 4) != 0 ? true : z, file);
    }

    private final Uri normalizeAssetUri(String uri) {
        if (StringsKt.startsWith$default(uri, "/", false, 2, (Object) null)) {
            Uri fromFile = Uri.fromFile(new File(uri));
            Intrinsics.checkNotNull(fromFile);
            return fromFile;
        }
        Uri parse = Uri.parse(uri);
        Intrinsics.checkNotNull(parse);
        return parse;
    }

    private final boolean isFileExtensionPresent() {
        String lastPathSegment = this.mUri.getLastPathSegment();
        if (lastPathSegment != null) {
            return StringsKt.contains$default((CharSequence) lastPathSegment, (CharSequence) ".", false, 2, (Object) null);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x002f, code lost:
    
        if (r3 != null) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.net.Uri createContentResolverAssetEntry() {
        /*
            r8 = this;
            android.content.Context r0 = r8.context
            android.content.ContentResolver r0 = r0.getContentResolver()
            expo.modules.medialibrary.MediaLibraryUtils r1 = expo.modules.medialibrary.MediaLibraryUtils.INSTANCE
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            android.net.Uri r2 = r8.mUri
            java.lang.String r1 = r1.getMimeType(r0, r2)
            android.net.Uri r2 = r8.mUri
            java.lang.String r2 = r2.getLastPathSegment()
            java.io.File r3 = r8.albumFile
            r4 = 1
            if (r3 == 0) goto L31
            java.io.File r5 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r6 = "getExternalStorageDirectory(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            java.io.File r3 = kotlin.io.FilesKt.relativeTo(r3, r5)
            if (r3 == 0) goto L31
            java.lang.String r3 = r3.getPath()
            if (r3 != 0) goto L37
        L31:
            expo.modules.medialibrary.MediaLibraryUtils r3 = expo.modules.medialibrary.MediaLibraryUtils.INSTANCE
            java.lang.String r3 = r3.getRelativePathForAssetType(r1, r4)
        L37:
            expo.modules.medialibrary.MediaLibraryUtils r5 = expo.modules.medialibrary.MediaLibraryUtils.INSTANCE
            android.net.Uri r5 = r5.mimeTypeToExternalUri(r1)
            android.content.ContentValues r6 = new android.content.ContentValues
            r6.<init>()
            java.lang.String r7 = "_display_name"
            r6.put(r7, r2)
            java.lang.String r2 = "mime_type"
            r6.put(r2, r1)
            java.lang.String r1 = "relative_path"
            r6.put(r1, r3)
            java.lang.String r1 = "is_pending"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r6.put(r1, r2)
            android.net.Uri r0 = r0.insert(r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.assets.CreateAssetWithAlbumFile.createContentResolverAssetEntry():android.net.Uri");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object writeFileContentsToAsset(File file, Uri uri, Continuation<? super Integer> continuation) throws IOException {
        return BuildersKt.withContext(Dispatchers.getIO(), new CreateAssetWithAlbumFile$writeFileContentsToAsset$2(this, file, uri, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object createAssetUsingContentResolver(Continuation<? super ArrayList<Bundle>> continuation) throws IOException {
        return BuildersKt.withContext(Dispatchers.getIO(), new CreateAssetWithAlbumFile$createAssetUsingContentResolver$2(this, null), continuation);
    }

    static /* synthetic */ File createAssetFileLegacy$default(CreateAssetWithAlbumFile createAssetWithAlbumFile, File file, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            file = null;
        }
        return createAssetWithAlbumFile.createAssetFileLegacy(file);
    }

    private final File createAssetFileLegacy(File albumFile) throws IOException {
        String path = this.mUri.getPath();
        Intrinsics.checkNotNull(path);
        File file = new File(path);
        MediaLibraryUtils mediaLibraryUtils = MediaLibraryUtils.INSTANCE;
        ContentResolver contentResolver = this.context.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "getContentResolver(...)");
        String mimeType = mediaLibraryUtils.getMimeType(contentResolver, this.mUri);
        if (mimeType == null) {
            throw new AssetFileException("Could not guess file type.");
        }
        if (albumFile == null) {
            albumFile = MediaLibraryUtils.INSTANCE.getEnvDirectoryForAssetType(mimeType, true);
        }
        File safeCopyFile = MediaLibraryUtils.INSTANCE.safeCopyFile(file, albumFile);
        if (albumFile.exists() && safeCopyFile.isFile()) {
            return safeCopyFile;
        }
        throw new AssetFileException("Could not create asset record. Related file does not exist.");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x009a A[Catch: Exception -> 0x003f, SecurityException -> 0x0042, IOException -> 0x0045, TryCatch #2 {IOException -> 0x0045, SecurityException -> 0x0042, Exception -> 0x003f, blocks: (B:15:0x0037, B:16:0x0083, B:18:0x009a, B:20:0x009e, B:25:0x00b1, B:26:0x00b6, B:27:0x003b, B:31:0x0051, B:33:0x0057, B:37:0x0061), top: B:7:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b1 A[Catch: Exception -> 0x003f, SecurityException -> 0x0042, IOException -> 0x0045, TryCatch #2 {IOException -> 0x0045, SecurityException -> 0x0042, Exception -> 0x003f, blocks: (B:15:0x0037, B:16:0x0083, B:18:0x009a, B:20:0x009e, B:25:0x00b1, B:26:0x00b6, B:27:0x003b, B:31:0x0051, B:33:0x0057, B:37:0x0061), top: B:7:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object execute(kotlin.coroutines.Continuation<? super java.util.ArrayList<android.os.Bundle>> r11) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.assets.CreateAssetWithAlbumFile.execute(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
