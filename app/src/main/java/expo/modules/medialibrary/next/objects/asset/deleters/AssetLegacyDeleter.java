package expo.modules.medialibrary.next.objects.asset.deleters;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import expo.modules.medialibrary.next.exceptions.ContentResolverNotObtainedException;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.permissions.SystemPermissionsDelegate;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AssetLegacyDeleter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@¢\u0006\u0002\u0010\u0015J\u001c\u0010\u0011\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u0017H\u0096@¢\u0006\u0002\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00050\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetLegacyDeleter;", "Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "systemPermissionsDelegate", "Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;", "context", "Landroid/content/Context;", "<init>", "(Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;Landroid/content/Context;)V", "getSystemPermissionsDelegate", "()Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "delete", "", "contentUri", "Landroid/net/Uri;", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "contentUris", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetLegacyDeleter implements AssetDeleter {
    private final WeakReference<Context> contextRef;
    private final SystemPermissionsDelegate systemPermissionsDelegate;

    public AssetLegacyDeleter(SystemPermissionsDelegate systemPermissionsDelegate, Context context) {
        Intrinsics.checkNotNullParameter(systemPermissionsDelegate, "systemPermissionsDelegate");
        Intrinsics.checkNotNullParameter(context, "context");
        this.systemPermissionsDelegate = systemPermissionsDelegate;
        this.contextRef = new WeakReference<>(context);
    }

    public final SystemPermissionsDelegate getSystemPermissionsDelegate() {
        return this.systemPermissionsDelegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContentResolver getContentResolver() {
        ContentResolver contentResolver = WeakReferenceExtensionsKt.getOrThrow(this.contextRef).getContentResolver();
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new ContentResolverNotObtainedException(null, 1, null);
    }

    @Override // expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter
    public Object delete(Uri uri, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AssetLegacyDeleter$delete$2(this, uri, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Override // expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter
    public Object delete(List<? extends Uri> list, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AssetLegacyDeleter$delete$4(list, this, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
