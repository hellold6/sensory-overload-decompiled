package expo.modules.medialibrary.next.objects.asset.factories;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import expo.modules.medialibrary.next.exceptions.ContentResolverNotObtainedException;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.asset.Asset;
import expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate;
import expo.modules.medialibrary.next.objects.asset.delegates.AssetModernDelegate;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter;
import expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AssetModernFactory.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\"\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096@¢\u0006\u0004\b\u001e\u0010\u001fJ*\u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010!\u001a\u00020\"H\u0082@¢\u0006\u0004\b#\u0010$R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00070\u00070\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006%"}, d2 = {"Lexpo/modules/medialibrary/next/objects/asset/factories/AssetModernFactory;", "Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "assetDeleter", "Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "mediaStorePermissionsDelegate", "Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;", "context", "Landroid/content/Context;", "<init>", "(Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;Landroid/content/Context;)V", "getAssetDeleter", "()Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "getMediaStorePermissionsDelegate", "()Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "createAssetDelegate", "Lexpo/modules/medialibrary/next/objects/asset/delegates/AssetDelegate;", "contentUri", "Landroid/net/Uri;", "create", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "filePath", "relativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "create-BuevYFM", "(Landroid/net/Uri;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createAssetInternal", "forceUniqueName", "", "createAssetInternal-7lvfX64", "(Landroid/net/Uri;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetModernFactory implements AssetFactory {
    private final AssetDeleter assetDeleter;
    private final WeakReference<Context> contextRef;
    private final MediaStorePermissionsDelegate mediaStorePermissionsDelegate;

    public AssetModernFactory(AssetDeleter assetDeleter, MediaStorePermissionsDelegate mediaStorePermissionsDelegate, Context context) {
        Intrinsics.checkNotNullParameter(assetDeleter, "assetDeleter");
        Intrinsics.checkNotNullParameter(mediaStorePermissionsDelegate, "mediaStorePermissionsDelegate");
        Intrinsics.checkNotNullParameter(context, "context");
        this.assetDeleter = assetDeleter;
        this.mediaStorePermissionsDelegate = mediaStorePermissionsDelegate;
        this.contextRef = new WeakReference<>(context);
    }

    public final AssetDeleter getAssetDeleter() {
        return this.assetDeleter;
    }

    public final MediaStorePermissionsDelegate getMediaStorePermissionsDelegate() {
        return this.mediaStorePermissionsDelegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContentResolver getContentResolver() {
        ContentResolver contentResolver = WeakReferenceExtensionsKt.getOrThrow(this.contextRef).getContentResolver();
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new ContentResolverNotObtainedException(null, 1, null);
    }

    private final AssetDelegate createAssetDelegate(Uri contentUri) {
        return new AssetModernDelegate(contentUri, this.assetDeleter, this.mediaStorePermissionsDelegate, WeakReferenceExtensionsKt.getOrThrow(this.contextRef));
    }

    @Override // expo.modules.medialibrary.next.objects.asset.factories.AssetFactory
    public Asset create(Uri contentUri) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        return new Asset(createAssetDelegate(contentUri));
    }

    @Override // expo.modules.medialibrary.next.objects.asset.factories.AssetFactory
    /* renamed from: create-BuevYFM */
    public Object mo1300createBuevYFM(Uri uri, String str, Continuation<? super Asset> continuation) {
        return m1304createAssetInternal7lvfX64(uri, str, false, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createAssetInternal-7lvfX64, reason: not valid java name */
    public final Object m1304createAssetInternal7lvfX64(Uri uri, String str, boolean z, Continuation<? super Asset> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AssetModernFactory$createAssetInternal$2(this, uri, z, str, null), continuation);
    }
}
