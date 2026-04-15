package expo.modules.medialibrary.assets;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import expo.modules.medialibrary.AssetQueryException;
import expo.modules.medialibrary.AssetsOptions;
import expo.modules.medialibrary.MediaLibraryConstantsKt;
import expo.modules.medialibrary.PermissionsException;
import expo.modules.medialibrary.UnableToLoadException;
import java.io.IOException;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.JobKt;

/* compiled from: GetAssets.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"getAssets", "Landroid/os/Bundle;", "context", "Landroid/content/Context;", "assetOptions", "Lexpo/modules/medialibrary/AssetsOptions;", "(Landroid/content/Context;Lexpo/modules/medialibrary/AssetsOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GetAssetsKt {
    public static final Object getAssets(Context context, AssetsOptions assetsOptions, Continuation<? super Bundle> continuation) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            GetAssetsQuery queryFromOptions = GetAssetsQueryKt.getQueryFromOptions(assetsOptions);
            String selection = queryFromOptions.getSelection();
            String order = queryFromOptions.getOrder();
            double limit = queryFromOptions.getLimit();
            int offset = queryFromOptions.getOffset();
            Cursor query = contentResolver.query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), MediaLibraryConstantsKt.getASSET_PROJECTION(), selection, null, order);
            try {
                Cursor cursor = query;
                JobKt.ensureActive(continuation.getContext());
                if (cursor == null) {
                    throw new AssetQueryException();
                }
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                Intrinsics.checkNotNull(contentResolver);
                ArrayList<? extends Parcelable> arrayList2 = arrayList;
                int i = (int) limit;
                Boolean resolveWithFullInfo = assetsOptions.getResolveWithFullInfo();
                AssetUtilsKt.putAssetsInfo(contentResolver, cursor, arrayList2, i, offset, resolveWithFullInfo != null ? resolveWithFullInfo.booleanValue() : false);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("assets", arrayList);
                bundle.putBoolean("hasNextPage", !cursor.isAfterLast());
                bundle.putString("endCursor", String.valueOf(cursor.getPosition()));
                bundle.putInt("totalCount", cursor.getCount());
                CloseableKt.closeFinally(query, null);
                return bundle;
            } finally {
            }
        } catch (Exception e) {
            if (!(e instanceof SecurityException)) {
                if (!(e instanceof IOException)) {
                    if (!(e instanceof IllegalArgumentException)) {
                        if (e instanceof UnsupportedOperationException) {
                            String message = e.getMessage();
                            if (message == null) {
                                message = "Permission denied: " + e.getMessage();
                            }
                            throw new PermissionsException(message);
                        }
                        throw e;
                    }
                    String message2 = e.getMessage();
                    if (message2 == null) {
                        message2 = "Invalid MediaType " + e.getMessage();
                    }
                    throw new UnableToLoadException(message2, e);
                }
                throw new UnableToLoadException("Could not read file: " + e.getMessage(), e);
            }
            throw new UnableToLoadException("Could not get asset: need read_external_storage permission", e);
        }
    }
}
