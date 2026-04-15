package expo.modules.medialibrary.assets;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.imagepipeline.common.RotationOptions;
import expo.modules.medialibrary.MediaLibraryConstantsKt;
import expo.modules.medialibrary.MediaType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AssetUtils.kt */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001aL\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\f\u001a<\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000b\u001a\u0016\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0002\u001a\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u0007\u001a\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0019\u001a\u00020\u001a\u001a<\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160 2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0016\u001a\u000e\u0010#\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0016\u001a*\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160 2\u0006\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0016¨\u0006("}, d2 = {"queryAssetInfo", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "Lkotlin/collections/ArrayList;", "context", "Landroid/content/Context;", "selection", "", "selectionArgs", "", "resolveWithFullInfo", "", "(Landroid/content/Context;Ljava/lang/String;[Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "putAssetsInfo", "", "contentResolver", "Landroid/content/ContentResolver;", "cursor", "Landroid/database/Cursor;", "response", "", "limit", "", "offset", "getExifFullInfo", "exifInterface", "Landroidx/exifinterface/media/ExifInterface;", "getExifLocationForUri", "photoUri", "Landroid/net/Uri;", "getExifLocationLegacy", "getAssetDimensionsFromCursor", "Lkotlin/Pair;", "mediaType", "localUriColumnIndex", "exportMediaType", "maybeRotateAssetSize", "width", "height", "orientation", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetUtilsKt {
    public static final Object queryAssetInfo(Context context, String str, String[] strArr, boolean z, Continuation<? super ArrayList<Bundle>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AssetUtilsKt$queryAssetInfo$2(context, str, strArr, z, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void putAssetsInfo(android.content.ContentResolver r22, android.database.Cursor r23, java.util.List<android.os.Bundle> r24, int r25, int r26, boolean r27) throws java.io.IOException, java.lang.UnsupportedOperationException {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.assets.AssetUtilsKt.putAssetsInfo(android.content.ContentResolver, android.database.Cursor, java.util.List, int, int, boolean):void");
    }

    public static final void getExifFullInfo(ExifInterface exifInterface, Bundle response) {
        Intrinsics.checkNotNullParameter(exifInterface, "exifInterface");
        Intrinsics.checkNotNullParameter(response, "response");
        Bundle bundle = new Bundle();
        for (String[] strArr : MediaLibraryConstantsKt.getEXIF_TAGS()) {
            String str = strArr[0];
            String str2 = strArr[1];
            if (exifInterface.getAttribute(str2) != null) {
                int hashCode = str.hashCode();
                if (hashCode != -1325958191) {
                    if (hashCode != -891985903) {
                        if (hashCode == 104431 && str.equals("int")) {
                            bundle.putInt(str2, exifInterface.getAttributeInt(str2, 0));
                        }
                    } else if (str.equals("string")) {
                        bundle.putString(str2, exifInterface.getAttribute(str2));
                    }
                } else if (str.equals("double")) {
                    bundle.putDouble(str2, exifInterface.getAttributeDouble(str2, 0.0d));
                }
            }
        }
        response.putParcelable("exif", bundle);
    }

    public static final Bundle getExifLocationForUri(ContentResolver contentResolver, Uri photoUri) throws UnsupportedOperationException, IOException {
        Bundle bundle;
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(photoUri, "photoUri");
        try {
            Uri requireOriginal = MediaStore.setRequireOriginal(photoUri);
            Intrinsics.checkNotNullExpressionValue(requireOriginal, "setRequireOriginal(...)");
            InputStream openInputStream = contentResolver.openInputStream(requireOriginal);
            if (openInputStream == null) {
                return null;
            }
            InputStream inputStream = openInputStream;
            try {
                double[] latLong = new ExifInterface(inputStream).getLatLong();
                if (latLong != null) {
                    double d = latLong[0];
                    double d2 = latLong[1];
                    bundle = new Bundle();
                    bundle.putDouble("latitude", d);
                    bundle.putDouble("longitude", d2);
                } else {
                    bundle = null;
                }
                CloseableKt.closeFinally(inputStream, null);
                return bundle;
            } finally {
            }
        } catch (IOException e) {
            Log.w("expo-media-library", "Could not parse EXIF tags for " + photoUri);
            e.printStackTrace();
            return null;
        } catch (UnsupportedOperationException unused) {
            throw new UnsupportedOperationException("Cannot access ExifInterface because of missing ACCESS_MEDIA_LOCATION permission");
        }
    }

    public static final Bundle getExifLocationLegacy(ExifInterface exifInterface) {
        Intrinsics.checkNotNullParameter(exifInterface, "exifInterface");
        double[] latLong = exifInterface.getLatLong();
        if (latLong == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putDouble("latitude", latLong[0]);
        bundle.putDouble("longitude", latLong[1]);
        return bundle;
    }

    public static final Pair<Integer, Integer> getAssetDimensionsFromCursor(ContentResolver contentResolver, ExifInterface exifInterface, Cursor cursor, int i, int i2) throws IOException {
        int attributeInt;
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(i2);
        if (i == 3) {
            try {
                AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(Uri.parse("file://" + string), "r");
                try {
                    AssetFileDescriptor assetFileDescriptor = openAssetFileDescriptor;
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    try {
                        MediaMetadataRetriever mediaMetadataRetriever2 = mediaMetadataRetriever;
                        Intrinsics.checkNotNull(assetFileDescriptor);
                        mediaMetadataRetriever2.setDataSource(assetFileDescriptor.getFileDescriptor());
                        String extractMetadata = mediaMetadataRetriever2.extractMetadata(18);
                        Intrinsics.checkNotNull(extractMetadata);
                        int parseInt = Integer.parseInt(extractMetadata);
                        String extractMetadata2 = mediaMetadataRetriever2.extractMetadata(19);
                        Intrinsics.checkNotNull(extractMetadata2);
                        int parseInt2 = Integer.parseInt(extractMetadata2);
                        String extractMetadata3 = mediaMetadataRetriever2.extractMetadata(24);
                        Intrinsics.checkNotNull(extractMetadata3);
                        Pair<Integer, Integer> maybeRotateAssetSize = maybeRotateAssetSize(parseInt, parseInt2, Integer.parseInt(extractMetadata3));
                        AutoCloseableKt.closeFinally(mediaMetadataRetriever, null);
                        CloseableKt.closeFinally(openAssetFileDescriptor, null);
                        return maybeRotateAssetSize;
                    } finally {
                    }
                } finally {
                }
            } catch (FileNotFoundException e) {
                Log.e("expo-media-library", "ContentResolver failed to read " + string + ": " + e.getMessage());
            } catch (NumberFormatException e2) {
                Log.e("expo-media-library", "MediaMetadataRetriever unexpectedly returned non-integer: " + e2.getMessage());
            } catch (RuntimeException e3) {
                Log.e("expo-media-library", "MediaMetadataRetriever finished with unexpected error: " + e3.getMessage());
            }
        }
        int columnIndex = cursor.getColumnIndex("width");
        int columnIndex2 = cursor.getColumnIndex("height");
        int columnIndex3 = cursor.getColumnIndex("orientation");
        int i3 = cursor.getInt(columnIndex);
        int i4 = cursor.getInt(columnIndex2);
        int i5 = cursor.getInt(columnIndex3);
        if (i == 1 && (i3 <= 0 || i4 <= 0)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(string, options);
            int i6 = options.outWidth;
            i4 = options.outHeight;
            i3 = i6;
        }
        if (exifInterface != null && ((attributeInt = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1)) == 5 || attributeInt == 6 || attributeInt == 7 || attributeInt == 8)) {
            i5 = 90;
        }
        return maybeRotateAssetSize(i3, i4, i5);
    }

    public static final String exportMediaType(int i) {
        MediaType mediaType;
        if (i == 1) {
            mediaType = MediaType.PHOTO;
        } else {
            if (i != 2) {
                if (i == 3) {
                    mediaType = MediaType.VIDEO;
                } else if (i != 4) {
                    mediaType = MediaType.UNKNOWN;
                }
            }
            mediaType = MediaType.AUDIO;
        }
        return mediaType.getApiName();
    }

    public static final Pair<Integer, Integer> maybeRotateAssetSize(int i, int i2, int i3) {
        if (Math.abs(i3) % RotationOptions.ROTATE_180 == 90) {
            return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i));
        }
        return new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
    }
}
