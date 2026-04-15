package expo.modules.medialibrary.albums;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AddAssetsToAlbum.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a4\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0001H\u0086@¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"addAssetsToAlbum", "", "context", "Landroid/content/Context;", "assetIds", "", "", "albumId", "copyToAlbum", "(Landroid/content/Context;[Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AddAssetsToAlbumKt {
    public static final Object addAssetsToAlbum(Context context, String[] strArr, String str, boolean z, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AddAssetsToAlbumKt$addAssetsToAlbum$2(z, context, str, strArr, null), continuation);
    }
}
