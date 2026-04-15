package expo.modules.medialibrary.next.objects.album;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Environment;
import com.facebook.common.util.UriUtil;
import expo.modules.kotlin.runtime.Runtime;
import expo.modules.kotlin.sharedobjects.SharedObject;
import expo.modules.medialibrary.next.exceptions.AlbumPropertyNotFoundException;
import expo.modules.medialibrary.next.exceptions.ContentResolverNotObtainedException;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter;
import expo.modules.medialibrary.next.objects.asset.factories.AssetFactory;
import expo.modules.medialibrary.next.objects.wrappers.RelativePath;
import java.io.File;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;

/* compiled from: Album.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0019\u001a\u00020\u0003H\u0086@¢\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u001cH\u0086@¢\u0006\u0004\b\u001d\u0010\u001aJ\u0017\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0003H\u0002¢\u0006\u0004\b \u0010!J\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0086@¢\u0006\u0002\u0010\u001aJ\u000e\u0010%\u001a\u00020&H\u0086@¢\u0006\u0002\u0010\u001aJ\u0016\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020$H\u0086@¢\u0006\u0002\u0010)R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\t0\t0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006*"}, d2 = {"Lexpo/modules/medialibrary/next/objects/album/Album;", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "id", "", "assetDeleter", "Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "assetFactory", "Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "context", "Landroid/content/Context;", "<init>", "(Ljava/lang/String;Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;Landroid/content/Context;)V", "getId", "()Ljava/lang/String;", "getAssetDeleter", "()Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "getAssetFactory", "()Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "getTitle", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRelativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "getRelativePath-MwbCjzw", "createRelativePathFrom", "filePath", "createRelativePathFrom-MwbCjzw", "(Ljava/lang/String;)Ljava/lang/String;", "getAssets", "", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "delete", "", "add", UriUtil.LOCAL_ASSET_SCHEME, "(Lexpo/modules/medialibrary/next/objects/asset/Asset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Album extends SharedObject {
    private final AssetDeleter assetDeleter;
    private final AssetFactory assetFactory;
    private final WeakReference<Context> contextRef;
    private final String id;

    public final String getId() {
        return this.id;
    }

    public final AssetDeleter getAssetDeleter() {
        return this.assetDeleter;
    }

    public final AssetFactory getAssetFactory() {
        return this.assetFactory;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Album(String id, AssetDeleter assetDeleter, AssetFactory assetFactory, Context context) {
        super((Runtime) null, 1, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(assetDeleter, "assetDeleter");
        Intrinsics.checkNotNullParameter(assetFactory, "assetFactory");
        Intrinsics.checkNotNullParameter(context, "context");
        this.id = id;
        this.assetDeleter = assetDeleter;
        this.assetFactory = assetFactory;
        this.contextRef = new WeakReference<>(context);
    }

    private final ContentResolver getContentResolver() {
        ContentResolver contentResolver = WeakReferenceExtensionsKt.getOrThrow(this.contextRef).getContentResolver();
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new ContentResolverNotObtainedException(null, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0048 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTitle(kotlin.coroutines.Continuation<? super java.lang.String> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof expo.modules.medialibrary.next.objects.album.Album$getTitle$1
            if (r0 == 0) goto L14
            r0 = r5
            expo.modules.medialibrary.next.objects.album.Album$getTitle$1 r0 = (expo.modules.medialibrary.next.objects.album.Album$getTitle$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.album.Album$getTitle$1 r0 = new expo.modules.medialibrary.next.objects.album.Album$getTitle$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L44
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            android.content.ContentResolver r5 = r4.getContentResolver()
            java.lang.String r2 = r4.id
            r0.label = r3
            java.lang.Object r5 = expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt.queryAlbumTitle(r5, r2, r0)
            if (r5 != r1) goto L44
            return r1
        L44:
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L49
            return r5
        L49:
            expo.modules.medialibrary.next.exceptions.AlbumPropertyNotFoundException r5 = new expo.modules.medialibrary.next.exceptions.AlbumPropertyNotFoundException
            java.lang.String r0 = r4.id
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Album with ID="
            r1.<init>(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = " does not exist in MediaStore"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 2
            r2 = 0
            r5.<init>(r0, r2, r1, r2)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.Album.getTitle(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x005d, code lost:
    
        if (r9 == r1) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008a, code lost:
    
        if (r9 == r1) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* renamed from: getRelativePath-MwbCjzw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1285getRelativePathMwbCjzw(kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.objects.wrappers.RelativePath> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof expo.modules.medialibrary.next.objects.album.Album$getRelativePath$1
            if (r0 == 0) goto L14
            r0 = r9
            expo.modules.medialibrary.next.objects.album.Album$getRelativePath$1 r0 = (expo.modules.medialibrary.next.objects.album.Album$getRelativePath$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.album.Album$getRelativePath$1 r0 = new expo.modules.medialibrary.next.objects.album.Album$getRelativePath$1
            r0.<init>(r8, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = " does not exist in MediaStore"
            java.lang.String r4 = "Album with ID="
            r5 = 1
            r6 = 2
            r7 = 0
            if (r2 == 0) goto L48
            if (r2 == r5) goto L3a
            if (r2 != r6) goto L32
            kotlin.ResultKt.throwOnFailure(r9)
            goto L8d
        L32:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L3a:
            kotlin.ResultKt.throwOnFailure(r9)
            expo.modules.medialibrary.next.objects.wrappers.RelativePath r9 = (expo.modules.medialibrary.next.objects.wrappers.RelativePath) r9
            if (r9 == 0) goto L46
            java.lang.String r9 = r9.m1330unboximpl()
            goto L60
        L46:
            r9 = r7
            goto L60
        L48:
            kotlin.ResultKt.throwOnFailure(r9)
            int r9 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            if (r9 < r2) goto L7e
            android.content.ContentResolver r9 = r8.getContentResolver()
            java.lang.String r2 = r8.id
            r0.label = r5
            java.lang.Object r9 = expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt.queryAlbumRelativePath(r9, r2, r0)
            if (r9 != r1) goto L60
            goto L8c
        L60:
            java.lang.String r9 = (java.lang.String) r9
            if (r9 == 0) goto L65
            return r9
        L65:
            expo.modules.medialibrary.next.exceptions.AlbumPropertyNotFoundException r9 = new expo.modules.medialibrary.next.exceptions.AlbumPropertyNotFoundException
            java.lang.String r0 = r8.id
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0, r7, r6, r7)
            throw r9
        L7e:
            android.content.ContentResolver r9 = r8.getContentResolver()
            java.lang.String r2 = r8.id
            r0.label = r6
            java.lang.Object r9 = expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt.queryAlbumFilepath(r9, r2, r0)
            if (r9 != r1) goto L8d
        L8c:
            return r1
        L8d:
            java.lang.String r9 = (java.lang.String) r9
            if (r9 == 0) goto L96
            java.lang.String r9 = r8.m1284createRelativePathFromMwbCjzw(r9)
            return r9
        L96:
            expo.modules.medialibrary.next.exceptions.AlbumPropertyNotFoundException r9 = new expo.modules.medialibrary.next.exceptions.AlbumPropertyNotFoundException
            java.lang.String r0 = r8.id
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0, r7, r6, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.Album.m1285getRelativePathMwbCjzw(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: createRelativePathFrom-MwbCjzw, reason: not valid java name */
    private final String m1284createRelativePathFromMwbCjzw(String filePath) {
        String parent = new File(filePath).getParent();
        if (parent == null) {
            throw new AlbumPropertyNotFoundException("Could get a relative path for the album", null, 2, null);
        }
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Intrinsics.checkNotNull(absolutePath);
        return RelativePath.m1324constructorimpl(StringsKt.trimStart(StringsKt.removePrefix(parent, (CharSequence) absolutePath), IOUtils.DIR_SEPARATOR_UNIX) + "/");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005d A[LOOP:0: B:11:0x0057->B:13:0x005d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getAssets(kotlin.coroutines.Continuation<? super java.util.List<expo.modules.medialibrary.next.objects.asset.Asset>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof expo.modules.medialibrary.next.objects.album.Album$getAssets$1
            if (r0 == 0) goto L14
            r0 = r5
            expo.modules.medialibrary.next.objects.album.Album$getAssets$1 r0 = (expo.modules.medialibrary.next.objects.album.Album$getAssets$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.album.Album$getAssets$1 r0 = new expo.modules.medialibrary.next.objects.album.Album$getAssets$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L44
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            android.content.ContentResolver r5 = r4.getContentResolver()
            java.lang.String r2 = r4.id
            r0.label = r3
            java.lang.Object r5 = expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt.queryAlbumAssetsContentUris(r5, r2, r0)
            if (r5 != r1) goto L44
            return r1
        L44:
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r5, r1)
            r0.<init>(r1)
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r5 = r5.iterator()
        L57:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L6d
            java.lang.Object r1 = r5.next()
            android.net.Uri r1 = (android.net.Uri) r1
            expo.modules.medialibrary.next.objects.asset.factories.AssetFactory r2 = r4.assetFactory
            expo.modules.medialibrary.next.objects.asset.Asset r1 = r2.create(r1)
            r0.add(r1)
            goto L57
        L6d:
            java.util.List r0 = (java.util.List) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.Album.getAssets(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x007f, code lost:
    
        if (r2.delete(r4, r0) != r1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0081, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004a, code lost:
    
        if (r7 == r1) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0066 A[LOOP:0: B:18:0x0060->B:20:0x0066, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object delete(kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof expo.modules.medialibrary.next.objects.album.Album$delete$1
            if (r0 == 0) goto L14
            r0 = r7
            expo.modules.medialibrary.next.objects.album.Album$delete$1 r0 = (expo.modules.medialibrary.next.objects.album.Album$delete$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.album.Album$delete$1 r0 = new expo.modules.medialibrary.next.objects.album.Album$delete$1
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3d
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L82
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L35:
            java.lang.Object r2 = r0.L$0
            expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter r2 = (expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4d
        L3d:
            kotlin.ResultKt.throwOnFailure(r7)
            expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter r2 = r6.assetDeleter
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r7 = r6.getAssets(r0)
            if (r7 != r1) goto L4d
            goto L81
        L4d:
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r4 = new java.util.ArrayList
            r5 = 10
            int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r5)
            r4.<init>(r5)
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.Iterator r7 = r7.iterator()
        L60:
            boolean r5 = r7.hasNext()
            if (r5 == 0) goto L74
            java.lang.Object r5 = r7.next()
            expo.modules.medialibrary.next.objects.asset.Asset r5 = (expo.modules.medialibrary.next.objects.asset.Asset) r5
            android.net.Uri r5 = r5.getContentUri()
            r4.add(r5)
            goto L60
        L74:
            java.util.List r4 = (java.util.List) r4
            r7 = 0
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r7 = r2.delete(r4, r0)
            if (r7 != r1) goto L82
        L81:
            return r1
        L82:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.Album.delete(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x005c, code lost:
    
        if (r6.m1293movedXLngQ8((java.lang.String) r7, r0) != r1) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004e, code lost:
    
        if (r7 == r1) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object add(expo.modules.medialibrary.next.objects.asset.Asset r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof expo.modules.medialibrary.next.objects.album.Album$add$1
            if (r0 == 0) goto L14
            r0 = r7
            expo.modules.medialibrary.next.objects.album.Album$add$1 r0 = (expo.modules.medialibrary.next.objects.album.Album$add$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.album.Album$add$1 r0 = new expo.modules.medialibrary.next.objects.album.Album$add$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L43
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5f
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            java.lang.Object r6 = r0.L$0
            expo.modules.medialibrary.next.objects.asset.Asset r6 = (expo.modules.medialibrary.next.objects.asset.Asset) r6
            kotlin.ResultKt.throwOnFailure(r7)
            expo.modules.medialibrary.next.objects.wrappers.RelativePath r7 = (expo.modules.medialibrary.next.objects.wrappers.RelativePath) r7
            java.lang.String r7 = r7.m1330unboximpl()
            goto L51
        L43:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r5.m1285getRelativePathMwbCjzw(r0)
            if (r7 != r1) goto L51
            goto L5e
        L51:
            java.lang.String r7 = (java.lang.String) r7
            r2 = 0
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r6.m1293movedXLngQ8(r7, r0)
            if (r6 != r1) goto L5f
        L5e:
            return r1
        L5f:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.Album.add(expo.modules.medialibrary.next.objects.asset.Asset, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
