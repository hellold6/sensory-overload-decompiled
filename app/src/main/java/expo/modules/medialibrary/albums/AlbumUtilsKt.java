package expo.modules.medialibrary.albums;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import expo.modules.medialibrary.AlbumException;
import expo.modules.medialibrary.AssetFileException;
import expo.modules.medialibrary.MediaLibraryConstantsKt;
import expo.modules.medialibrary.MediaLibraryUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AlbumUtils.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a0\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007H\u0086@¢\u0006\u0002\u0010\b\u001a1\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0007\"\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\f\u001a.\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0080@¢\u0006\u0002\u0010\b\u001a \u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H\u0080@¢\u0006\u0002\u0010\u0011\u001a \u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0005H\u0080@¢\u0006\u0002\u0010\u0011\u001a\u001e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0005H\u0080@¢\u0006\u0002\u0010\u0011\u001a\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0000¨\u0006\u0017"}, d2 = {"queryAlbum", "Landroid/os/Bundle;", "context", "Landroid/content/Context;", "selection", "", "selectionArgs", "", "(Landroid/content/Context;Ljava/lang/String;[Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAssetsInAlbums", "", "albumIds", "(Landroid/content/Context;[Ljava/lang/String;)Ljava/util/List;", "getFileOrNullByContextResolver", "Ljava/io/File;", "getAlbumFileByNameOrNull", "albumName", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlbumFileOrNull", "albumId", "getAlbumFile", "createAlbumFile", "mimeType", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlbumUtilsKt {
    public static final Object queryAlbum(Context context, String str, String[] strArr, Continuation<? super Bundle> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AlbumUtilsKt$queryAlbum$2(context, str, strArr, null), continuation);
    }

    public static final List<String> getAssetsInAlbums(Context context, String... albumIds) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(albumIds, "albumIds");
        ArrayList arrayList = new ArrayList();
        Cursor query = context.getContentResolver().query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), new String[]{"_id"}, "bucket_id IN (" + MediaLibraryUtils.INSTANCE.queryPlaceholdersFor(albumIds) + " )", albumIds, null);
        try {
            Cursor cursor = query;
            if (cursor == null) {
                CloseableKt.closeFinally(query, null);
                return arrayList;
            }
            while (cursor.moveToNext()) {
                String string = cursor.getString(cursor.getColumnIndex("_id"));
                Intrinsics.checkNotNull(string);
                arrayList.add(string);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(query, null);
            return arrayList;
        } finally {
        }
    }

    public static final Object getFileOrNullByContextResolver(Context context, String str, String[] strArr, Continuation<? super File> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AlbumUtilsKt$getFileOrNullByContextResolver$2(context, str, strArr, null), continuation);
    }

    public static final Object getAlbumFileByNameOrNull(Context context, String str, Continuation<? super File> continuation) {
        return getFileOrNullByContextResolver(context, "media_type != 0 AND bucket_display_name=?", new String[]{str}, continuation);
    }

    public static final Object getAlbumFileOrNull(Context context, String str, Continuation<? super File> continuation) {
        return getFileOrNullByContextResolver(context, "bucket_id=?", new String[]{str}, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0042 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object getAlbumFile(android.content.Context r4, java.lang.String r5, kotlin.coroutines.Continuation<? super java.io.File> r6) {
        /*
            boolean r0 = r6 instanceof expo.modules.medialibrary.albums.AlbumUtilsKt$getAlbumFile$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.medialibrary.albums.AlbumUtilsKt$getAlbumFile$1 r0 = (expo.modules.medialibrary.albums.AlbumUtilsKt$getAlbumFile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.medialibrary.albums.AlbumUtilsKt$getAlbumFile$1 r0 = new expo.modules.medialibrary.albums.AlbumUtilsKt$getAlbumFile$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3e
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.label = r3
            java.lang.Object r6 = getAlbumFileOrNull(r4, r5, r0)
            if (r6 != r1) goto L3e
            return r1
        L3e:
            java.io.File r6 = (java.io.File) r6
            if (r6 == 0) goto L43
            return r6
        L43:
            expo.modules.medialibrary.AlbumException r4 = new expo.modules.medialibrary.AlbumException
            java.lang.String r5 = "Could not get album. Query returns null."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.albums.AlbumUtilsKt.getAlbumFile(android.content.Context, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final File createAlbumFile(String mimeType, String albumName) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        File envDirectoryForAssetType = MediaLibraryUtils.INSTANCE.getEnvDirectoryForAssetType(mimeType, false);
        if (envDirectoryForAssetType == null) {
            throw new AssetFileException("Could not guess asset type.");
        }
        File file = new File(envDirectoryForAssetType.getPath(), albumName);
        if (!file.exists() && !file.mkdirs()) {
            file = null;
        }
        if (file != null) {
            return file;
        }
        throw new AlbumException("Could not create album directory.");
    }
}
