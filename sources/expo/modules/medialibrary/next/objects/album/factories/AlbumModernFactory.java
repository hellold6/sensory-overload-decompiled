package expo.modules.medialibrary.next.objects.album.factories;

import android.content.ContentResolver;
import android.content.Context;
import expo.modules.medialibrary.next.exceptions.AlbumCouldNotBeCreated;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.album.Album;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter;
import expo.modules.medialibrary.next.objects.asset.factories.AssetFactory;
import expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlbumModernFactory.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J,\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00162\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0096@¢\u0006\u0002\u0010\u001eJ$\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00162\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001aH\u0096@¢\u0006\u0002\u0010\"J.\u0010#\u001a\u00020$2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001c\u001a\u00020\u001dH\u0082@¢\u0006\u0004\b'\u0010(R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\t0\t0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006)"}, d2 = {"Lexpo/modules/medialibrary/next/objects/album/factories/AlbumModernFactory;", "Lexpo/modules/medialibrary/next/objects/album/factories/AlbumFactory;", "assetFactory", "Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "assetDeleter", "Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "mediaStorePermissionsDelegate", "Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;", "context", "Landroid/content/Context;", "<init>", "(Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;Landroid/content/Context;)V", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "create", "Lexpo/modules/medialibrary/next/objects/album/Album;", "id", "", "createFromAssets", "albumName", "assets", "", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "deleteOriginalAssets", "", "(Ljava/lang/String;Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFromFilePaths", "filePaths", "Landroid/net/Uri;", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processAssetsLocation", "", "relativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "processAssetsLocation-KTsBHyQ", "(Ljava/util/List;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlbumModernFactory implements AlbumFactory {
    private final AssetDeleter assetDeleter;
    private final AssetFactory assetFactory;
    private final WeakReference<Context> contextRef;
    private final MediaStorePermissionsDelegate mediaStorePermissionsDelegate;

    public AlbumModernFactory(AssetFactory assetFactory, AssetDeleter assetDeleter, MediaStorePermissionsDelegate mediaStorePermissionsDelegate, Context context) {
        Intrinsics.checkNotNullParameter(assetFactory, "assetFactory");
        Intrinsics.checkNotNullParameter(assetDeleter, "assetDeleter");
        Intrinsics.checkNotNullParameter(mediaStorePermissionsDelegate, "mediaStorePermissionsDelegate");
        Intrinsics.checkNotNullParameter(context, "context");
        this.assetFactory = assetFactory;
        this.assetDeleter = assetDeleter;
        this.mediaStorePermissionsDelegate = mediaStorePermissionsDelegate;
        this.contextRef = new WeakReference<>(context);
    }

    private final ContentResolver getContentResolver() {
        ContentResolver contentResolver = WeakReferenceExtensionsKt.getOrThrow(this.contextRef).getContentResolver();
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new AlbumCouldNotBeCreated("Failed to create album: ContentResolver is unavailable.", null, 2, null);
    }

    @Override // expo.modules.medialibrary.next.objects.album.factories.AlbumFactory
    public Album create(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return new Album(id, this.assetDeleter, this.assetFactory, WeakReferenceExtensionsKt.getOrThrow(this.contextRef));
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0096, code lost:
    
        if (r12 == r2) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
    @Override // expo.modules.medialibrary.next.objects.album.factories.AlbumFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object createFromAssets(java.lang.String r9, java.util.List<expo.modules.medialibrary.next.objects.asset.Asset> r10, boolean r11, kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.objects.album.Album> r12) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory.createFromAssets(java.lang.String, java.util.List, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x008a, code lost:
    
        if (r10 == r1) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @Override // expo.modules.medialibrary.next.objects.album.factories.AlbumFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object createFromFilePaths(java.lang.String r8, java.util.List<? extends android.net.Uri> r9, kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.objects.album.Album> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory$createFromFilePaths$1
            if (r0 == 0) goto L14
            r0 = r10
            expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory$createFromFilePaths$1 r0 = (expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory$createFromFilePaths$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory$createFromFilePaths$1 r0 = new expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory$createFromFilePaths$1
            r0.<init>(r7, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 2
            r5 = 0
            if (r2 == 0) goto L42
            if (r2 == r3) goto L36
            if (r2 != r4) goto L2e
            kotlin.ResultKt.throwOnFailure(r10)
            goto L8d
        L2e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L36:
            java.lang.Object r8 = r0.L$1
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L61
        L42:
            kotlin.ResultKt.throwOnFailure(r10)
            expo.modules.medialibrary.next.objects.wrappers.MimeType$Companion r10 = expo.modules.medialibrary.next.objects.wrappers.MimeType.INSTANCE
            r2 = 0
            java.lang.Object r2 = r9.get(r2)
            android.net.Uri r2 = (android.net.Uri) r2
            java.lang.String r10 = r10.m1322fromdctPOJs(r2)
            expo.modules.medialibrary.next.objects.wrappers.RelativePath$Companion r2 = expo.modules.medialibrary.next.objects.wrappers.RelativePath.INSTANCE
            java.lang.String r8 = r2.m1332createwht0CjE(r10, r8)
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r9 = r9.iterator()
            r6 = r9
            r9 = r8
            r8 = r6
        L61:
            boolean r10 = r8.hasNext()
            if (r10 == 0) goto L7c
            java.lang.Object r10 = r8.next()
            android.net.Uri r10 = (android.net.Uri) r10
            expo.modules.medialibrary.next.objects.asset.factories.AssetFactory r2 = r7.assetFactory
            r0.L$0 = r9
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r10 = r2.mo1300createBuevYFM(r10, r9, r0)
            if (r10 != r1) goto L61
            goto L8c
        L7c:
            android.content.ContentResolver r8 = r7.getContentResolver()
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r4
            java.lang.Object r10 = expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt.m1279queryAlbumIdqTZuolk(r8, r9, r0)
            if (r10 != r1) goto L8d
        L8c:
            return r1
        L8d:
            java.lang.String r10 = (java.lang.String) r10
            if (r10 == 0) goto La1
            expo.modules.medialibrary.next.objects.album.Album r8 = new expo.modules.medialibrary.next.objects.album.Album
            expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter r9 = r7.assetDeleter
            expo.modules.medialibrary.next.objects.asset.factories.AssetFactory r0 = r7.assetFactory
            java.lang.ref.WeakReference<android.content.Context> r1 = r7.contextRef
            android.content.Context r1 = expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt.getOrThrow(r1)
            r8.<init>(r10, r9, r0, r1)
            return r8
        La1:
            expo.modules.medialibrary.next.exceptions.AlbumCouldNotBeCreated r8 = new expo.modules.medialibrary.next.exceptions.AlbumCouldNotBeCreated
            java.lang.String r9 = "Failed to create album: newly created album was not found in the MediaStore."
            r8.<init>(r9, r5, r4, r5)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory.createFromFilePaths(java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a8, code lost:
    
        if (r11.requestMediaLibraryWritePermission(r2, r0) == r1) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0119 -> B:12:0x011b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00dd -> B:26:0x00de). Please report as a decompilation issue!!! */
    /* renamed from: processAssetsLocation-KTsBHyQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1290processAssetsLocationKTsBHyQ(java.util.List<expo.modules.medialibrary.next.objects.asset.Asset> r9, java.lang.String r10, boolean r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory.m1290processAssetsLocationKTsBHyQ(java.util.List, java.lang.String, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
