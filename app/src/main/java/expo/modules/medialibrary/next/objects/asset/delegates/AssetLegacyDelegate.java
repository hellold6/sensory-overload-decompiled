package expo.modules.medialibrary.next.objects.asset.delegates;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import expo.modules.medialibrary.next.exceptions.ContentResolverNotObtainedException;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.asset.Asset;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter;
import expo.modules.medialibrary.next.objects.wrappers.MediaType;
import expo.modules.medialibrary.next.permissions.SystemPermissionsDelegate;
import expo.modules.medialibrary.next.records.Location;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AssetLegacyDelegate.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010 \u001a\u0004\u0018\u00010!H\u0096@¢\u0006\u0002\u0010\"J\u0010\u0010#\u001a\u0004\u0018\u00010!H\u0096@¢\u0006\u0002\u0010\"J\u000e\u0010$\u001a\u00020%H\u0096@¢\u0006\u0002\u0010\"J\u000e\u0010&\u001a\u00020'H\u0096@¢\u0006\u0002\u0010\"J\u000e\u0010(\u001a\u00020'H\u0096@¢\u0006\u0002\u0010\"J\u0010\u0010)\u001a\u0004\u0018\u00010*H\u0096@¢\u0006\u0002\u0010\"J\u000e\u0010+\u001a\u00020,H\u0096@¢\u0006\u0002\u0010\"J\u0010\u0010-\u001a\u0004\u0018\u00010!H\u0096@¢\u0006\u0002\u0010\"J\u000e\u0010.\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010\"J\u000e\u0010/\u001a\u000200H\u0096@¢\u0006\u0002\u0010\"J\u0010\u00101\u001a\u000202H\u0096@¢\u0006\u0004\b3\u0010\"J\u0010\u00104\u001a\u0004\u0018\u000105H\u0096@¢\u0006\u0002\u0010\"J\u000e\u00106\u001a\u000207H\u0096@¢\u0006\u0002\u0010\"J\u000e\u00108\u001a\u000209H\u0096@¢\u0006\u0002\u0010\"J\u0018\u0010:\u001a\u0002092\u0006\u0010;\u001a\u00020<H\u0096@¢\u0006\u0004\b=\u0010>J\u0018\u0010?\u001a\u00020@2\u0006\u0010;\u001a\u00020<H\u0096@¢\u0006\u0004\bA\u0010>R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\t0\t0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001d¨\u0006B"}, d2 = {"Lexpo/modules/medialibrary/next/objects/asset/delegates/AssetLegacyDelegate;", "Lexpo/modules/medialibrary/next/objects/asset/delegates/AssetDelegate;", "contentUri", "Landroid/net/Uri;", "assetDeleter", "Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "systemPermissionsDelegate", "Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;", "context", "Landroid/content/Context;", "<init>", "(Landroid/net/Uri;Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;Landroid/content/Context;)V", "getAssetDeleter", "()Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "getSystemPermissionsDelegate", "()Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "value", "getContentUri", "()Landroid/net/Uri;", "mediaStoreToAssetAdapter", "Lexpo/modules/medialibrary/next/objects/asset/delegates/MediaStoreToAssetAdapter;", "getMediaStoreToAssetAdapter", "()Lexpo/modules/medialibrary/next/objects/asset/delegates/MediaStoreToAssetAdapter;", "mediaStoreToAssetAdapter$delegate", "Lkotlin/Lazy;", "getCreationTime", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDuration", "getFilename", "", "getHeight", "", "getWidth", "getShape", "Lexpo/modules/medialibrary/next/records/Shape;", "getMediaType", "Lexpo/modules/medialibrary/next/objects/wrappers/MediaType;", "getModificationTime", "getUri", "getInfo", "Lexpo/modules/medialibrary/next/records/AssetInfo;", "getMimeType", "Lexpo/modules/medialibrary/next/objects/wrappers/MimeType;", "getMimeType-dctPOJs", "getLocation", "Lexpo/modules/medialibrary/next/records/Location;", "getExif", "Landroid/os/Bundle;", "delete", "", "move", "relativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "move-dXLngQ8", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copy", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "copy-dXLngQ8", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetLegacyDelegate implements AssetDelegate {
    private final AssetDeleter assetDeleter;
    private Uri contentUri;
    private final WeakReference<Context> contextRef;

    /* renamed from: mediaStoreToAssetAdapter$delegate, reason: from kotlin metadata */
    private final Lazy mediaStoreToAssetAdapter;
    private final SystemPermissionsDelegate systemPermissionsDelegate;

    public AssetLegacyDelegate(Uri contentUri, AssetDeleter assetDeleter, SystemPermissionsDelegate systemPermissionsDelegate, Context context) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(assetDeleter, "assetDeleter");
        Intrinsics.checkNotNullParameter(systemPermissionsDelegate, "systemPermissionsDelegate");
        Intrinsics.checkNotNullParameter(context, "context");
        this.assetDeleter = assetDeleter;
        this.systemPermissionsDelegate = systemPermissionsDelegate;
        this.contextRef = new WeakReference<>(context);
        this.contentUri = contentUri;
        this.mediaStoreToAssetAdapter = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MediaStoreToAssetAdapter mediaStoreToAssetAdapter_delegate$lambda$0;
                mediaStoreToAssetAdapter_delegate$lambda$0 = AssetLegacyDelegate.mediaStoreToAssetAdapter_delegate$lambda$0(AssetLegacyDelegate.this);
                return mediaStoreToAssetAdapter_delegate$lambda$0;
            }
        });
    }

    public final AssetDeleter getAssetDeleter() {
        return this.assetDeleter;
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

    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    public Uri getContentUri() {
        return this.contentUri;
    }

    private final MediaStoreToAssetAdapter getMediaStoreToAssetAdapter() {
        return (MediaStoreToAssetAdapter) this.mediaStoreToAssetAdapter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaStoreToAssetAdapter mediaStoreToAssetAdapter_delegate$lambda$0(AssetLegacyDelegate assetLegacyDelegate) {
        return new MediaStoreToAssetAdapter(WeakReferenceExtensionsKt.getOrThrow(assetLegacyDelegate.contextRef));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getCreationTime(kotlin.coroutines.Continuation<? super java.lang.Long> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getCreationTime$1
            if (r0 == 0) goto L14
            r0 = r5
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getCreationTime$1 r0 = (expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getCreationTime$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getCreationTime$1 r0 = new expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getCreationTime$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            android.content.ContentResolver r5 = r4.getContentResolver()
            android.net.Uri r2 = r4.getContentUri()
            r0.label = r3
            java.lang.Object r5 = expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.queryAssetDateTaken(r5, r2, r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            java.lang.Long r5 = (java.lang.Long) r5
            expo.modules.medialibrary.next.objects.asset.delegates.MediaStoreToAssetAdapter r0 = r4.getMediaStoreToAssetAdapter()
            java.lang.Long r5 = r0.transformCreationTime(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.getCreationTime(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0059, code lost:
    
        if (r6 == r1) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0042, code lost:
    
        if (r6 == r1) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0049 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getDuration(kotlin.coroutines.Continuation<? super java.lang.Long> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getDuration$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getDuration$1 r0 = (expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getDuration$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getDuration$1 r0 = new expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getDuration$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5c
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L45
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.label = r4
            java.lang.Object r6 = r5.getMediaType(r0)
            if (r6 != r1) goto L45
            goto L5b
        L45:
            expo.modules.medialibrary.next.objects.wrappers.MediaType r2 = expo.modules.medialibrary.next.objects.wrappers.MediaType.VIDEO
            if (r6 == r2) goto L4b
            r6 = 0
            return r6
        L4b:
            android.content.ContentResolver r6 = r5.getContentResolver()
            android.net.Uri r2 = r5.getContentUri()
            r0.label = r3
            java.lang.Object r6 = expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.queryAssetDuration(r6, r2, r0)
            if (r6 != r1) goto L5c
        L5b:
            return r1
        L5c:
            java.lang.Long r6 = (java.lang.Long) r6
            expo.modules.medialibrary.next.objects.asset.delegates.MediaStoreToAssetAdapter r0 = r5.getMediaStoreToAssetAdapter()
            java.lang.Long r6 = r0.transformDuration(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.getDuration(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getFilename(kotlin.coroutines.Continuation<? super java.lang.String> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getFilename$1
            if (r0 == 0) goto L14
            r0 = r5
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getFilename$1 r0 = (expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getFilename$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getFilename$1 r0 = new expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getFilename$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            android.content.ContentResolver r5 = r4.getContentResolver()
            android.net.Uri r2 = r4.getContentUri()
            r0.label = r3
            java.lang.Object r5 = expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.queryAssetDisplayName(r5, r2, r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L4b
            return r5
        L4b:
            expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException r5 = new expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException
            java.lang.String r0 = "Filename"
            r1 = 2
            r2 = 0
            r5.<init>(r0, r2, r1, r2)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.getFilename(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x005d, code lost:
    
        if (r6 != r1) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005f, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004a, code lost:
    
        if (r6 == r1) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getHeight(kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getHeight$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getHeight$1 r0 = (expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getHeight$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getHeight$1 r0 = new expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getHeight$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 2
            if (r2 == 0) goto L39
            if (r2 == r3) goto L35
            if (r2 != r4) goto L2d
            kotlin.ResultKt.throwOnFailure(r6)
            goto L60
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4d
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            android.content.ContentResolver r6 = r5.getContentResolver()
            android.net.Uri r2 = r5.getContentUri()
            r0.label = r3
            java.lang.Object r6 = expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.queryAssetHeight(r6, r2, r0)
            if (r6 != r1) goto L4d
            goto L5f
        L4d:
            java.lang.Integer r6 = (java.lang.Integer) r6
            expo.modules.medialibrary.next.objects.asset.delegates.MediaStoreToAssetAdapter r2 = r5.getMediaStoreToAssetAdapter()
            android.net.Uri r3 = r5.getContentUri()
            r0.label = r4
            java.lang.Object r6 = r2.transformHeight(r6, r3, r0)
            if (r6 != r1) goto L60
        L5f:
            return r1
        L60:
            java.lang.Integer r6 = (java.lang.Integer) r6
            if (r6 == 0) goto L6d
            int r6 = r6.intValue()
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L6d:
            expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException r6 = new expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException
            java.lang.String r0 = "Height"
            r1 = 0
            r6.<init>(r0, r1, r4, r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.getHeight(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x005d, code lost:
    
        if (r6 != r1) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005f, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004a, code lost:
    
        if (r6 == r1) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getWidth(kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getWidth$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getWidth$1 r0 = (expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getWidth$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getWidth$1 r0 = new expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getWidth$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 2
            if (r2 == 0) goto L39
            if (r2 == r3) goto L35
            if (r2 != r4) goto L2d
            kotlin.ResultKt.throwOnFailure(r6)
            goto L60
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4d
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            android.content.ContentResolver r6 = r5.getContentResolver()
            android.net.Uri r2 = r5.getContentUri()
            r0.label = r3
            java.lang.Object r6 = expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.queryAssetWidth(r6, r2, r0)
            if (r6 != r1) goto L4d
            goto L5f
        L4d:
            java.lang.Integer r6 = (java.lang.Integer) r6
            expo.modules.medialibrary.next.objects.asset.delegates.MediaStoreToAssetAdapter r2 = r5.getMediaStoreToAssetAdapter()
            android.net.Uri r3 = r5.getContentUri()
            r0.label = r4
            java.lang.Object r6 = r2.transformWidth(r6, r3, r0)
            if (r6 != r1) goto L60
        L5f:
            return r1
        L60:
            java.lang.Integer r6 = (java.lang.Integer) r6
            if (r6 == 0) goto L6d
            int r6 = r6.intValue()
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L6d:
            expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException r6 = new expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException
            java.lang.String r0 = "Width"
            r1 = 0
            r6.<init>(r0, r1, r4, r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.getWidth(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0044, code lost:
    
        if (r7 == r1) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getShape(kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.records.Shape> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getShape$1
            if (r0 == 0) goto L14
            r0 = r7
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getShape$1 r0 = (expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getShape$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getShape$1 r0 = new expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getShape$1
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3b
            if (r2 == r4) goto L37
            if (r2 != r3) goto L2f
            int r0 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5b
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L47
        L3b:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.label = r4
            java.lang.Object r7 = r6.getWidth(r0)
            if (r7 != r1) goto L47
            goto L57
        L47:
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r0 = r6.getHeight(r0)
            if (r0 != r1) goto L58
        L57:
            return r1
        L58:
            r5 = r0
            r0 = r7
            r7 = r5
        L5b:
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            expo.modules.medialibrary.next.records.Shape r1 = new expo.modules.medialibrary.next.records.Shape
            r1.<init>(r0, r7)
            if (r0 <= 0) goto L6b
            if (r7 <= 0) goto L6b
            return r1
        L6b:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.getShape(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    public Object getMediaType(Continuation<? super MediaType> continuation) {
        return MediaType.INSTANCE.fromContentUri(getContentUri());
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getModificationTime(kotlin.coroutines.Continuation<? super java.lang.Long> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getModificationTime$1
            if (r0 == 0) goto L14
            r0 = r5
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getModificationTime$1 r0 = (expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getModificationTime$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getModificationTime$1 r0 = new expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getModificationTime$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            android.content.ContentResolver r5 = r4.getContentResolver()
            android.net.Uri r2 = r4.getContentUri()
            r0.label = r3
            java.lang.Object r5 = expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.queryAssetDateModified(r5, r2, r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            java.lang.Long r5 = (java.lang.Long) r5
            expo.modules.medialibrary.next.objects.asset.delegates.MediaStoreToAssetAdapter r0 = r4.getMediaStoreToAssetAdapter()
            java.lang.Long r5 = r0.transformModificationTime(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.getModificationTime(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getUri(kotlin.coroutines.Continuation<? super android.net.Uri> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getUri$1
            if (r0 == 0) goto L14
            r0 = r5
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getUri$1 r0 = (expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getUri$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getUri$1 r0 = new expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getUri$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            android.content.ContentResolver r5 = r4.getContentResolver()
            android.net.Uri r2 = r4.getContentUri()
            r0.label = r3
            java.lang.Object r5 = expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.queryAssetData(r5, r2, r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            java.lang.String r5 = (java.lang.String) r5
            expo.modules.medialibrary.next.objects.asset.delegates.MediaStoreToAssetAdapter r0 = r4.getMediaStoreToAssetAdapter()
            android.net.Uri r5 = r0.transformUri(r5)
            if (r5 == 0) goto L53
            return r5
        L53:
            expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException r5 = new expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException
            java.lang.String r0 = "Uri"
            r1 = 2
            r2 = 0
            r5.<init>(r0, r2, r1, r2)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.getUri(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x008d, code lost:
    
        if (r1 == r3) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x007d, code lost:
    
        if (r1 == r3) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getInfo(kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.records.AssetInfo> r22) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.getInfo(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /* renamed from: getMimeType-dctPOJs */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo1295getMimeTypedctPOJs(kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.objects.wrappers.MimeType> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getMimeType$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getMimeType$1 r0 = (expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getMimeType$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getMimeType$1 r0 = new expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$getMimeType$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r0 = r0.L$0
            expo.modules.medialibrary.next.objects.wrappers.MimeType$Companion r0 = (expo.modules.medialibrary.next.objects.wrappers.MimeType.Companion) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5c
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            android.content.ContentResolver r6 = r5.getContentResolver()
            android.net.Uri r2 = r5.getContentUri()
            java.lang.String r6 = r6.getType(r2)
            if (r6 == 0) goto L4c
            java.lang.String r6 = expo.modules.medialibrary.next.objects.wrappers.MimeType.m1309constructorimpl(r6)
            return r6
        L4c:
            expo.modules.medialibrary.next.objects.wrappers.MimeType$Companion r6 = expo.modules.medialibrary.next.objects.wrappers.MimeType.INSTANCE
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r0 = r5.getUri(r0)
            if (r0 != r1) goto L59
            return r1
        L59:
            r4 = r0
            r0 = r6
            r6 = r4
        L5c:
            android.net.Uri r6 = (android.net.Uri) r6
            java.lang.String r6 = r0.m1322fromdctPOJs(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.mo1295getMimeTypedctPOJs(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    public Object getLocation(Continuation<? super Location> continuation) {
        Location location;
        this.systemPermissionsDelegate.requireReadPermissions();
        InputStream openInputStream = getContentResolver().openInputStream(getContentUri());
        if (openInputStream == null) {
            return null;
        }
        InputStream inputStream = openInputStream;
        try {
            double[] latLong = new ExifInterface(inputStream).getLatLong();
            if (latLong != null) {
                location = new Location(Boxing.boxDouble(latLong[0]), Boxing.boxDouble(latLong[1]));
            } else {
                location = null;
            }
            CloseableKt.closeFinally(inputStream, null);
            return location;
        } finally {
        }
    }

    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    public Object getExif(Continuation<? super Bundle> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AssetLegacyDelegate$getExif$2(this, null), continuation);
    }

    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    public Object delete(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AssetLegacyDelegate$delete$2(this, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /* renamed from: move-dXLngQ8 */
    public Object mo1296movedXLngQ8(String str, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AssetLegacyDelegate$move$2(this, str, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Override // expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate
    /* renamed from: copy-dXLngQ8 */
    public Object mo1294copydXLngQ8(String str, Continuation<? super Asset> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AssetLegacyDelegate$copy$2(this, str, null), continuation);
    }
}
