package expo.modules.medialibrary.next.objects.asset.deleters;

import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: AssetDeleter.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH¦@¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "", "delete", "", "contentUri", "Landroid/net/Uri;", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "contentUris", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface AssetDeleter {
    Object delete(Uri uri, Continuation<? super Unit> continuation);

    Object delete(List<? extends Uri> list, Continuation<? super Unit> continuation);
}
