package expo.modules.medialibrary.albums;

import android.content.Context;
import expo.modules.medialibrary.MediaLibraryUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: RemoveAssetsFromAlbum.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"removeAssetsFromAlbum", "", "context", "Landroid/content/Context;", "assetIds", "", "", "albumId", "(Landroid/content/Context;[Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RemoveAssetsFromAlbumKt {
    public static final Object removeAssetsFromAlbum(Context context, String[] strArr, String str, Continuation<? super Unit> continuation) {
        Object deleteAssets = MediaLibraryUtils.INSTANCE.deleteAssets(context, "bucket_id=? AND _id IN (" + ArraysKt.joinToString$default(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + " )", new String[]{str}, continuation);
        return deleteAssets == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? deleteAssets : Unit.INSTANCE;
    }
}
