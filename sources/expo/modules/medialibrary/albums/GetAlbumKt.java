package expo.modules.medialibrary.albums;

import android.content.Context;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: GetAlbum.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"getAlbum", "Landroid/os/Bundle;", "context", "Landroid/content/Context;", "albumName", "", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GetAlbumKt {
    public static final Object getAlbum(Context context, String str, Continuation<? super Bundle> continuation) {
        return AlbumUtilsKt.queryAlbum(context, "media_type != 0 AND bucket_display_name=?", new String[]{str}, continuation);
    }
}
