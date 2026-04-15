package expo.modules.medialibrary.next.objects.asset.deleters;

import android.net.Uri;
import expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AssetModernDeleter.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@¢\u0006\u0002\u0010\fJ\u001c\u0010\b\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0096@¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetModernDeleter;", "Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "mediaStorePermissionsDelegate", "Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;", "<init>", "(Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;)V", "getMediaStorePermissionsDelegate", "()Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;", "delete", "", "contentUri", "Landroid/net/Uri;", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "contentUris", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetModernDeleter implements AssetDeleter {
    private final MediaStorePermissionsDelegate mediaStorePermissionsDelegate;

    public AssetModernDeleter(MediaStorePermissionsDelegate mediaStorePermissionsDelegate) {
        Intrinsics.checkNotNullParameter(mediaStorePermissionsDelegate, "mediaStorePermissionsDelegate");
        this.mediaStorePermissionsDelegate = mediaStorePermissionsDelegate;
    }

    public final MediaStorePermissionsDelegate getMediaStorePermissionsDelegate() {
        return this.mediaStorePermissionsDelegate;
    }

    @Override // expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter
    public Object delete(Uri uri, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AssetModernDeleter$delete$2(this, uri, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Override // expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter
    public Object delete(List<? extends Uri> list, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AssetModernDeleter$delete$4(list, this, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
