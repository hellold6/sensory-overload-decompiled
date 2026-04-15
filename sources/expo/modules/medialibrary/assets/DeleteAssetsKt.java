package expo.modules.medialibrary.assets;

import android.content.Context;
import expo.modules.medialibrary.MediaLibraryUtils;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* compiled from: DeleteAssets.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"deleteAssets", "", "context", "Landroid/content/Context;", "assetIds", "", "", "(Landroid/content/Context;[Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeleteAssetsKt {
    public static final Object deleteAssets(Context context, String[] strArr, Continuation<? super Boolean> continuation) {
        return MediaLibraryUtils.INSTANCE.deleteAssets(context, "_id IN (" + ArraysKt.joinToString$default(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + " )", null, continuation);
    }
}
