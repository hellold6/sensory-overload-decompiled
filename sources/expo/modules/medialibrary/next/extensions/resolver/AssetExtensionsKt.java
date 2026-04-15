package expo.modules.medialibrary.next.extensions.resolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import expo.modules.medialibrary.MediaLibraryConstantsKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AssetExtensions.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\b\u001a\u0004\u0018\u00010\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\t\u001a\u0004\u0018\u00010\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\f\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\r\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005\u001a,\u0010\u0011\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0014\u0010\u0019\u001a\u00020\u001a*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0007\u001a\u001a\u0010\u001c\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e\u001a!\u0010\u001f\u001a\u00020\u001a*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0016¢\u0006\u0004\b!\u0010\"\u001a)\u0010#\u001a\u00020\u001a*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0001¢\u0006\u0004\b$\u0010%\u001a\u0012\u0010&\u001a\u00020\u001a*\u00020\u00022\u0006\u0010'\u001a\u00020\u0001¨\u0006("}, d2 = {"queryAssetDisplayName", "", "Landroid/content/ContentResolver;", "contentUri", "Landroid/net/Uri;", "(Landroid/content/ContentResolver;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryAssetDateTaken", "", "queryAssetDateModified", "queryAssetDuration", "queryAssetWidth", "", "queryAssetHeight", "queryAssetData", "queryAssetBucketId", "queryAssetMediaStoreItem", "Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreItem;", "insertPendingAsset", "displayName", "mimeType", "Lexpo/modules/medialibrary/next/objects/wrappers/MimeType;", "relativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "insertPendingAsset-cT81_0k", "(Landroid/content/ContentResolver;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "publishPendingAsset", "", "uri", "safeUpdate", "values", "Landroid/content/ContentValues;", "updateRelativePath", "newRelativePath", "updateRelativePath-XUFtwTY", "(Landroid/content/ContentResolver;Landroid/net/Uri;Ljava/lang/String;)V", "updateRelativePathAndName", "updateRelativePathAndName-SGAlvP8", "(Landroid/content/ContentResolver;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)V", "deleteBy", "assetPath", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetExtensionsKt {
    public static final Object queryAssetDisplayName(ContentResolver contentResolver, Uri uri, Continuation<? super String> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, uri, AssetMediaStoreProperty.DisplayName.getColumn(), AssetExtensionsKt$queryAssetDisplayName$2.INSTANCE, null, null, null, continuation, 56, null);
    }

    public static final Object queryAssetDateTaken(ContentResolver contentResolver, Uri uri, Continuation<? super Long> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, uri, AssetMediaStoreProperty.DateTaken.getColumn(), AssetExtensionsKt$queryAssetDateTaken$2.INSTANCE, null, null, null, continuation, 56, null);
    }

    public static final Object queryAssetDateModified(ContentResolver contentResolver, Uri uri, Continuation<? super Long> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, uri, AssetMediaStoreProperty.DateModified.getColumn(), AssetExtensionsKt$queryAssetDateModified$2.INSTANCE, null, null, null, continuation, 56, null);
    }

    public static final Object queryAssetDuration(ContentResolver contentResolver, Uri uri, Continuation<? super Long> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, uri, AssetMediaStoreProperty.Duration.getColumn(), AssetExtensionsKt$queryAssetDuration$2.INSTANCE, null, null, null, continuation, 56, null);
    }

    public static final Object queryAssetWidth(ContentResolver contentResolver, Uri uri, Continuation<? super Integer> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, uri, AssetMediaStoreProperty.Width.getColumn(), AssetExtensionsKt$queryAssetWidth$2.INSTANCE, null, null, null, continuation, 56, null);
    }

    public static final Object queryAssetHeight(ContentResolver contentResolver, Uri uri, Continuation<? super Integer> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, uri, AssetMediaStoreProperty.Height.getColumn(), AssetExtensionsKt$queryAssetHeight$2.INSTANCE, null, null, null, continuation, 56, null);
    }

    public static final Object queryAssetData(ContentResolver contentResolver, Uri uri, Continuation<? super String> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, uri, AssetMediaStoreProperty.Data.getColumn(), AssetExtensionsKt$queryAssetData$2.INSTANCE, null, null, null, continuation, 56, null);
    }

    public static final Object queryAssetBucketId(ContentResolver contentResolver, Uri uri, Continuation<? super Integer> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, uri, AssetMediaStoreProperty.BucketId.getColumn(), AssetExtensionsKt$queryAssetBucketId$2.INSTANCE, null, null, null, continuation, 56, null);
    }

    public static final Object queryAssetMediaStoreItem(ContentResolver contentResolver, Uri uri, Continuation<? super AssetMediaStoreItem> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AssetExtensionsKt$queryAssetMediaStoreItem$2(uri, contentResolver, null), continuation);
    }

    /* renamed from: insertPendingAsset-cT81_0k, reason: not valid java name */
    public static final Object m1281insertPendingAssetcT81_0k(ContentResolver contentResolver, String str, String str2, String str3, Continuation<? super Uri> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AssetExtensionsKt$insertPendingAsset$2(str2, contentResolver, str, str3, null), continuation);
    }

    public static final void publishPendingAsset(ContentResolver contentResolver, Uri uri) {
        Intrinsics.checkNotNullParameter(contentResolver, "<this>");
        Intrinsics.checkNotNullParameter(uri, "uri");
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_pending", (Integer) 0);
        safeUpdate(contentResolver, uri, contentValues);
    }

    public static final int safeUpdate(ContentResolver contentResolver, Uri uri, ContentValues values) {
        Intrinsics.checkNotNullParameter(contentResolver, "<this>");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(values, "values");
        if (Build.VERSION.SDK_INT >= 30) {
            return contentResolver.update(uri, values, null);
        }
        return contentResolver.update(uri, values, null, null);
    }

    /* renamed from: updateRelativePath-XUFtwTY, reason: not valid java name */
    public static final void m1282updateRelativePathXUFtwTY(ContentResolver updateRelativePath, Uri contentUri, String newRelativePath) {
        Intrinsics.checkNotNullParameter(updateRelativePath, "$this$updateRelativePath");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(newRelativePath, "newRelativePath");
        ContentValues contentValues = new ContentValues();
        contentValues.put("relative_path", newRelativePath);
        updateRelativePath.update(contentUri, contentValues, null, null);
    }

    /* renamed from: updateRelativePathAndName-SGAlvP8, reason: not valid java name */
    public static final void m1283updateRelativePathAndNameSGAlvP8(ContentResolver updateRelativePathAndName, Uri contentUri, String newRelativePath, String displayName) {
        Intrinsics.checkNotNullParameter(updateRelativePathAndName, "$this$updateRelativePathAndName");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(newRelativePath, "newRelativePath");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        ContentValues contentValues = new ContentValues();
        contentValues.put("relative_path", newRelativePath);
        contentValues.put("_display_name", displayName);
        updateRelativePathAndName.update(contentUri, contentValues, null, null);
    }

    public static final void deleteBy(ContentResolver contentResolver, String assetPath) {
        Intrinsics.checkNotNullParameter(contentResolver, "<this>");
        Intrinsics.checkNotNullParameter(assetPath, "assetPath");
        contentResolver.delete(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), "_data=?", new String[]{assetPath});
    }
}
