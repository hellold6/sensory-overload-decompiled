package expo.modules.medialibrary;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.media3.common.MimeTypes;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MediaLibraryUtils.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u00012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tJ\u0016\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tJ4\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0012\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0013H\u0086@¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\u00062\u0010\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0013¢\u0006\u0002\u0010\u0017J1\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u000f\u001a\u00020\u00102\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0013\"\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J'\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\u00192\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\u0010\u001cJ\u0010\u0010%\u001a\u00020#2\b\u0010&\u001a\u0004\u0018\u00010\u0006J\u0018\u0010'\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010\u00062\u0006\u0010(\u001a\u00020\u000eJ\u001a\u0010)\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010\u00062\u0006\u0010(\u001a\u00020\u000eH\u0007J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060+2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010,\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0006JB\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010#0\u00052\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00060\u00132\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013H\u0086@¢\u0006\u0002\u00101¨\u00063"}, d2 = {"Lexpo/modules/medialibrary/MediaLibraryUtils;", "", "<init>", "()V", "getFileNameAndExtension", "Lkotlin/Pair;", "", "name", "safeMoveFile", "Ljava/io/File;", "src", "destDir", "safeCopyFile", "deleteAssets", "", "context", "Landroid/content/Context;", "selection", "selectionArgs", "", "(Landroid/content/Context;Ljava/lang/String;[Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryPlaceholdersFor", "assetIds", "([Ljava/lang/String;)Ljava/lang/String;", "getAssetsById", "", "Lexpo/modules/medialibrary/MediaLibraryUtils$AssetFile;", "assetsId", "(Landroid/content/Context;[Ljava/lang/String;)Ljava/util/List;", "getMimeTypeFromFileUrl", ImagesContract.URL, "getMimeType", "contentResolver", "Landroid/content/ContentResolver;", "uri", "Landroid/net/Uri;", "getAssetsUris", "mimeTypeToExternalUri", "mimeType", "getRelativePathForAssetType", "useCameraDir", "getEnvDirectoryForAssetType", "getManifestPermissions", "", "hasManifestPermission", "permission", "scanFile", "paths", "mimeTypes", "(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AssetFile", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaLibraryUtils {
    public static final MediaLibraryUtils INSTANCE = new MediaLibraryUtils();

    /* compiled from: MediaLibraryUtils.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lexpo/modules/medialibrary/MediaLibraryUtils$AssetFile;", "Ljava/io/File;", "pathname", "", "assetId", "mimeType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAssetId", "()Ljava/lang/String;", "getMimeType", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class AssetFile extends File {
        private final String assetId;
        private final String mimeType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AssetFile(String pathname, String assetId, String mimeType) {
            super(pathname);
            Intrinsics.checkNotNullParameter(pathname, "pathname");
            Intrinsics.checkNotNullParameter(assetId, "assetId");
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            this.assetId = assetId;
            this.mimeType = mimeType;
        }

        public final String getAssetId() {
            return this.assetId;
        }

        public final String getMimeType() {
            return this.mimeType;
        }
    }

    private MediaLibraryUtils() {
    }

    public final Pair<String, String> getFileNameAndExtension(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Integer valueOf = Integer.valueOf(StringsKt.lastIndexOf$default((CharSequence) name, ".", 0, false, 6, (Object) null));
        if (valueOf.intValue() == -1) {
            valueOf = null;
        }
        int intValue = valueOf != null ? valueOf.intValue() : name.length();
        String substring = name.substring(intValue);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String substring2 = name.substring(0, intValue);
        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
        return new Pair<>(substring2, substring);
    }

    public final File safeMoveFile(File src, File destDir) throws IOException {
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(destDir, "destDir");
        File safeCopyFile = safeCopyFile(src, destDir);
        src.delete();
        return safeCopyFile;
    }

    public final File safeCopyFile(File src, File destDir) throws IOException {
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(destDir, "destDir");
        File file = new File(destDir, src.getName());
        String name = src.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        Pair<String, String> fileNameAndExtension = getFileNameAndExtension(name);
        String component1 = fileNameAndExtension.component1();
        String component2 = fileNameAndExtension.component2();
        int i = 0;
        while (file.exists()) {
            file = new File(destDir, component1 + "_" + i + component2);
            i++;
            if (i > 32767) {
                throw new IOException("File name suffix limit reached (32767)");
            }
        }
        FileChannel channel = new FileInputStream(src).getChannel();
        try {
            FileChannel fileChannel = channel;
            channel = new FileOutputStream(file).getChannel();
            try {
                if (fileChannel.transferTo(0L, fileChannel.size(), channel) != fileChannel.size()) {
                    file.delete();
                    throw new IOException("Could not save file to " + destDir + " Not enough space.");
                }
                CloseableKt.closeFinally(channel, null);
                CloseableKt.closeFinally(channel, null);
                return file;
            } finally {
            }
        } finally {
        }
    }

    public final Object deleteAssets(Context context, String str, String[] strArr, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new MediaLibraryUtils$deleteAssets$2(context, str, strArr, null), continuation);
    }

    public final String queryPlaceholdersFor(String[] assetIds) {
        Intrinsics.checkNotNullParameter(assetIds, "assetIds");
        String[] strArr = new String[assetIds.length];
        ArraysKt.fill$default(strArr, "?", 0, 0, 6, (Object) null);
        return ArraysKt.joinToString$default(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public final List<AssetFile> getAssetsById(Context context, String... assetsId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetsId, "assetsId");
        Cursor query = context.getContentResolver().query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), new String[]{"_id", "_data", "bucket_id", "mime_type"}, "_id IN ( " + queryPlaceholdersFor(assetsId) + " )", assetsId, null);
        try {
            Cursor cursor = query;
            if (cursor == null) {
                throw new AssetFileException("Could not get assets. Query returns null.");
            }
            if (cursor.getCount() != assetsId.length) {
                throw new AssetFileException("Could not get all of the requested assets");
            }
            ArrayList arrayList = new ArrayList();
            while (cursor.moveToNext()) {
                String string = cursor.getString(cursor.getColumnIndex("_data"));
                int columnIndex = cursor.getColumnIndex("_id");
                int columnIndex2 = cursor.getColumnIndex("mime_type");
                Intrinsics.checkNotNull(string);
                String string2 = cursor.getString(columnIndex);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(columnIndex2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                AssetFile assetFile = new AssetFile(string, string2, string3);
                if (!assetFile.exists() || !assetFile.isFile()) {
                    throw new AssetFileException("Path " + string + " does not exist or isn't file.");
                }
                arrayList.add(assetFile);
            }
            CloseableKt.closeFinally(query, null);
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(query, th);
                throw th2;
            }
        }
    }

    private final String getMimeTypeFromFileUrl(String url) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(url);
        if (fileExtensionFromUrl == null) {
            return null;
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
    }

    public final String getMimeType(ContentResolver contentResolver, Uri uri) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String type = contentResolver.getType(uri);
        if (type != null) {
            return type;
        }
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return getMimeTypeFromFileUrl(uri2);
    }

    public final List<Uri> getAssetsUris(Context context, String[] assetsId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetsId, "assetsId");
        ArrayList arrayList = new ArrayList();
        Cursor query = context.getContentResolver().query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), new String[]{"_id", "mime_type"}, "_id IN (" + TextUtils.join(",", assetsId) + " )", null, null);
        if (query == null) {
            return arrayList;
        }
        Cursor cursor = query;
        try {
            Cursor cursor2 = cursor;
            while (cursor2.moveToNext()) {
                Uri withAppendedId = ContentUris.withAppendedId(INSTANCE.mimeTypeToExternalUri(cursor2.getString(cursor2.getColumnIndex("mime_type"))), cursor2.getLong(cursor2.getColumnIndex("_id")));
                Intrinsics.checkNotNullExpressionValue(withAppendedId, "withAppendedId(...)");
                arrayList.add(withAppendedId);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor, null);
            return arrayList;
        } finally {
        }
    }

    public final Uri mimeTypeToExternalUri(String mimeType) {
        if (mimeType == null) {
            Uri EXTERNAL_CONTENT_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Intrinsics.checkNotNullExpressionValue(EXTERNAL_CONTENT_URI, "EXTERNAL_CONTENT_URI");
            return EXTERNAL_CONTENT_URI;
        }
        String str = mimeType;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "image", false, 2, (Object) null)) {
            Uri EXTERNAL_CONTENT_URI2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Intrinsics.checkNotNullExpressionValue(EXTERNAL_CONTENT_URI2, "EXTERNAL_CONTENT_URI");
            return EXTERNAL_CONTENT_URI2;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) MimeTypes.BASE_TYPE_VIDEO, false, 2, (Object) null)) {
            Uri EXTERNAL_CONTENT_URI3 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            Intrinsics.checkNotNullExpressionValue(EXTERNAL_CONTENT_URI3, "EXTERNAL_CONTENT_URI");
            return EXTERNAL_CONTENT_URI3;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) MimeTypes.BASE_TYPE_AUDIO, false, 2, (Object) null)) {
            Uri EXTERNAL_CONTENT_URI4 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Intrinsics.checkNotNullExpressionValue(EXTERNAL_CONTENT_URI4, "EXTERNAL_CONTENT_URI");
            return EXTERNAL_CONTENT_URI4;
        }
        return MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI();
    }

    public final String getRelativePathForAssetType(String mimeType, boolean useCameraDir) {
        if ((mimeType != null && StringsKt.contains$default((CharSequence) mimeType, (CharSequence) "image", false, 2, (Object) null)) || (mimeType != null && StringsKt.contains$default((CharSequence) mimeType, (CharSequence) MimeTypes.BASE_TYPE_VIDEO, false, 2, (Object) null))) {
            if (useCameraDir) {
                String DIRECTORY_DCIM = Environment.DIRECTORY_DCIM;
                Intrinsics.checkNotNullExpressionValue(DIRECTORY_DCIM, "DIRECTORY_DCIM");
                return DIRECTORY_DCIM;
            }
            String DIRECTORY_PICTURES = Environment.DIRECTORY_PICTURES;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_PICTURES, "DIRECTORY_PICTURES");
            return DIRECTORY_PICTURES;
        }
        if (mimeType != null && StringsKt.contains$default((CharSequence) mimeType, (CharSequence) MimeTypes.BASE_TYPE_AUDIO, false, 2, (Object) null)) {
            String DIRECTORY_MUSIC = Environment.DIRECTORY_MUSIC;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_MUSIC, "DIRECTORY_MUSIC");
            return DIRECTORY_MUSIC;
        }
        if (useCameraDir) {
            String DIRECTORY_DCIM2 = Environment.DIRECTORY_DCIM;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_DCIM2, "DIRECTORY_DCIM");
            return DIRECTORY_DCIM2;
        }
        String DIRECTORY_PICTURES2 = Environment.DIRECTORY_PICTURES;
        Intrinsics.checkNotNullExpressionValue(DIRECTORY_PICTURES2, "DIRECTORY_PICTURES");
        return DIRECTORY_PICTURES2;
    }

    @Deprecated(message = "It uses deprecated Android method under the hood. See implementation for details.")
    public final File getEnvDirectoryForAssetType(String mimeType, boolean useCameraDir) {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(getRelativePathForAssetType(mimeType, useCameraDir));
        Intrinsics.checkNotNullExpressionValue(externalStoragePublicDirectory, "getExternalStoragePublicDirectory(...)");
        return externalStoragePublicDirectory;
    }

    private final Set<String> getManifestPermissions(Context context) {
        Set<String> set;
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
        try {
            String[] strArr = packageManager.getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null && (set = ArraysKt.toSet(strArr)) != null) {
                return set;
            }
            return SetsKt.emptySet();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("expo-media-library", "Failed to list AndroidManifest.xml permissions");
            e.printStackTrace();
            return SetsKt.emptySet();
        }
    }

    public final boolean hasManifestPermission(Context context, String permission) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(permission, "permission");
        return getManifestPermissions(context).contains(permission);
    }

    public final Object scanFile(Context context, String[] strArr, String[] strArr2, Continuation<? super Pair<String, ? extends Uri>> continuation) {
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        final SafeContinuation safeContinuation2 = safeContinuation;
        MediaScannerConnection.scanFile(context, strArr, strArr2, new MediaScannerConnection.OnScanCompletedListener() { // from class: expo.modules.medialibrary.MediaLibraryUtils$scanFile$2$1
            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public final void onScanCompleted(String path, Uri uri) {
                Intrinsics.checkNotNullParameter(path, "path");
                Continuation<Pair<String, ? extends Uri>> continuation2 = safeContinuation2;
                Result.Companion companion = Result.INSTANCE;
                continuation2.resumeWith(Result.m1409constructorimpl(new Pair(path, uri)));
            }
        });
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }
}
