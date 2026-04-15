package expo.modules.medialibrary.next.extensions.resolver;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CursorExtensions.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"extractAssetContentUri", "Landroid/net/Uri;", "Landroid/database/Cursor;", "idColumn", "", "typeColumn", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CursorExtensionsKt {
    public static final Uri extractAssetContentUri(Cursor cursor, int i, int i2) {
        Uri uri;
        Intrinsics.checkNotNullParameter(cursor, "<this>");
        long j = cursor.getLong(i);
        int i3 = cursor.getInt(i2);
        if (Build.VERSION.SDK_INT == 29) {
            if (i3 == 1) {
                uri = MediaStore.Images.Media.getContentUri("external_primary");
            } else if (i3 == 2) {
                uri = MediaStore.Audio.Media.getContentUri("external_primary");
            } else if (i3 == 3) {
                uri = MediaStore.Video.Media.getContentUri("external_primary");
            } else {
                uri = MediaStore.Files.getContentUri("external_primary");
            }
        } else if (i3 == 1) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (i3 == 2) {
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        } else if (i3 == 3) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = AlbumExtensionsKt.getEXTERNAL_CONTENT_URI();
        }
        Uri withAppendedId = ContentUris.withAppendedId(uri, j);
        Intrinsics.checkNotNullExpressionValue(withAppendedId, "withAppendedId(...)");
        return withAppendedId;
    }
}
