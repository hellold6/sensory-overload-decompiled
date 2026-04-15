package expo.modules.medialibrary.assets;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: GetAssetInfo.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"getAssetInfo", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "Lkotlin/collections/ArrayList;", "context", "Landroid/content/Context;", "assetId", "", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GetAssetInfoKt {
    public static final Object getAssetInfo(Context context, String str, Continuation<? super ArrayList<Bundle>> continuation) {
        return AssetUtilsKt.queryAssetInfo(context, "_id=?", new String[]{str}, true, continuation);
    }
}
