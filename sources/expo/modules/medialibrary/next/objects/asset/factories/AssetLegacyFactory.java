package expo.modules.medialibrary.next.objects.asset.factories;

import android.content.ContentResolver;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import expo.modules.medialibrary.next.exceptions.ContentResolverNotObtainedException;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.asset.Asset;
import expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate;
import expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter;
import expo.modules.medialibrary.next.objects.wrappers.MimeType;
import expo.modules.medialibrary.next.objects.wrappers.RelativePath;
import expo.modules.medialibrary.next.permissions.SystemPermissionsDelegate;
import java.io.File;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AssetLegacyFactory.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\"\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096@¢\u0006\u0004\b\u001e\u0010\u001fJ!\u0010 \u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0004\b\"\u0010#JB\u0010$\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0006\u0012\u0004\u0018\u00010\u00180%2\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010'\u001a\b\u0012\u0004\u0012\u00020&0(2\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010(H\u0082@¢\u0006\u0002\u0010*R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00070\u00070\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006+"}, d2 = {"Lexpo/modules/medialibrary/next/objects/asset/factories/AssetLegacyFactory;", "Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "assetDeleter", "Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "systemPermissionsDelegate", "Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;", "context", "Landroid/content/Context;", "<init>", "(Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;Landroid/content/Context;)V", "getAssetDeleter", "()Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "getSystemPermissionsDelegate", "()Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "createAssetDelegate", "Lexpo/modules/medialibrary/next/objects/asset/delegates/AssetDelegate;", "contentUri", "Landroid/net/Uri;", "create", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "filePath", "relativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "create-BuevYFM", "(Landroid/net/Uri;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createDestinationDirectory", "Ljava/io/File;", "createDestinationDirectory-mc4yU64", "(Landroid/net/Uri;Ljava/lang/String;)Ljava/io/File;", "scanFile", "Lkotlin/Pair;", "", "paths", "", "mimeTypes", "(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetLegacyFactory implements AssetFactory {
    private final AssetDeleter assetDeleter;
    private final WeakReference<Context> contextRef;
    private final SystemPermissionsDelegate systemPermissionsDelegate;

    public AssetLegacyFactory(AssetDeleter assetDeleter, SystemPermissionsDelegate systemPermissionsDelegate, Context context) {
        Intrinsics.checkNotNullParameter(assetDeleter, "assetDeleter");
        Intrinsics.checkNotNullParameter(systemPermissionsDelegate, "systemPermissionsDelegate");
        Intrinsics.checkNotNullParameter(context, "context");
        this.assetDeleter = assetDeleter;
        this.systemPermissionsDelegate = systemPermissionsDelegate;
        this.contextRef = new WeakReference<>(context);
    }

    public final AssetDeleter getAssetDeleter() {
        return this.assetDeleter;
    }

    public final SystemPermissionsDelegate getSystemPermissionsDelegate() {
        return this.systemPermissionsDelegate;
    }

    private final ContentResolver getContentResolver() {
        ContentResolver contentResolver = WeakReferenceExtensionsKt.getOrThrow(this.contextRef).getContentResolver();
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new ContentResolverNotObtainedException(null, 1, null);
    }

    private final AssetDelegate createAssetDelegate(Uri contentUri) {
        return new AssetLegacyDelegate(contentUri, this.assetDeleter, this.systemPermissionsDelegate, WeakReferenceExtensionsKt.getOrThrow(this.contextRef));
    }

    @Override // expo.modules.medialibrary.next.objects.asset.factories.AssetFactory
    public Asset create(Uri contentUri) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        return new Asset(createAssetDelegate(contentUri));
    }

    @Override // expo.modules.medialibrary.next.objects.asset.factories.AssetFactory
    /* renamed from: create-BuevYFM */
    public Object mo1300createBuevYFM(Uri uri, String str, Continuation<? super Asset> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AssetLegacyFactory$create$2(this, uri, str, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createDestinationDirectory-mc4yU64, reason: not valid java name */
    public final File m1302createDestinationDirectorymc4yU64(Uri filePath, String relativePath) {
        File m1312externalStorageAssetDirectoryimpl;
        if (relativePath != null) {
            m1312externalStorageAssetDirectoryimpl = new File(RelativePath.m1328toFilePathimpl(relativePath));
        } else {
            String type = getContentResolver().getType(filePath);
            m1312externalStorageAssetDirectoryimpl = MimeType.m1312externalStorageAssetDirectoryimpl(type != null ? MimeType.m1309constructorimpl(type) : MimeType.INSTANCE.m1322fromdctPOJs(filePath));
        }
        m1312externalStorageAssetDirectoryimpl.mkdirs();
        return m1312externalStorageAssetDirectoryimpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object scanFile(Context context, String[] strArr, String[] strArr2, Continuation<? super Pair<String, ? extends Uri>> continuation) {
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        final SafeContinuation safeContinuation2 = safeContinuation;
        MediaScannerConnection.scanFile(context, strArr, strArr2, new MediaScannerConnection.OnScanCompletedListener() { // from class: expo.modules.medialibrary.next.objects.asset.factories.AssetLegacyFactory$scanFile$2$1
            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public final void onScanCompleted(String path, Uri uri) {
                Intrinsics.checkNotNullParameter(path, "path");
                Continuation<Pair<String, ? extends Uri>> continuation2 = safeContinuation2;
                Result.Companion companion = Result.INSTANCE;
                continuation2.resumeWith(Result.m1409constructorimpl(new Pair(path, uri)));
            }
        });
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }
}
