package expo.modules.medialibrary.next.objects.album.factories;

import android.net.Uri;
import expo.modules.medialibrary.next.objects.album.Album;
import expo.modules.medialibrary.next.objects.asset.Asset;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: AlbumFactory.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J,\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH¦@¢\u0006\u0002\u0010\rJ$\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\tH¦@¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lexpo/modules/medialibrary/next/objects/album/factories/AlbumFactory;", "", "create", "Lexpo/modules/medialibrary/next/objects/album/Album;", "id", "", "createFromAssets", "albumName", "assets", "", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "deleteOriginalAssets", "", "(Ljava/lang/String;Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFromFilePaths", "filePaths", "Landroid/net/Uri;", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface AlbumFactory {
    Album create(String id);

    Object createFromAssets(String str, List<Asset> list, boolean z, Continuation<? super Album> continuation);

    Object createFromFilePaths(String str, List<? extends Uri> list, Continuation<? super Album> continuation);
}
