package expo.modules.medialibrary.next.objects.asset;

import android.net.Uri;
import android.os.Bundle;
import expo.modules.kotlin.runtime.Runtime;
import expo.modules.kotlin.sharedobjects.SharedObject;
import expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate;
import expo.modules.medialibrary.next.objects.wrappers.MediaType;
import expo.modules.medialibrary.next.records.AssetInfo;
import expo.modules.medialibrary.next.records.Location;
import expo.modules.medialibrary.next.records.Shape;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: Asset.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u0004\u0018\u00010\rH\u0086@¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\rH\u0086@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0012\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0014\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0086@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\rH\u0086@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u001a\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u001b\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001d\u001a\u00020\u001eH\u0086@¢\u0006\u0004\b\u001f\u0010\u000eJ\u0010\u0010 \u001a\u0004\u0018\u00010!H\u0086@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\"\u001a\u00020#H\u0086@¢\u0006\u0002\u0010\u000eJ\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0086@¢\u0006\u0004\b(\u0010)J\u0018\u0010*\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'H\u0086@¢\u0006\u0004\b+\u0010)J\u000e\u0010,\u001a\u00020%H\u0086@¢\u0006\u0002\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006-"}, d2 = {"Lexpo/modules/medialibrary/next/objects/asset/Asset;", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "assetDelegate", "Lexpo/modules/medialibrary/next/objects/asset/delegates/AssetDelegate;", "<init>", "(Lexpo/modules/medialibrary/next/objects/asset/delegates/AssetDelegate;)V", "getAssetDelegate", "()Lexpo/modules/medialibrary/next/objects/asset/delegates/AssetDelegate;", "contentUri", "Landroid/net/Uri;", "getContentUri", "()Landroid/net/Uri;", "getCreationTime", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDuration", "getFilename", "", "getHeight", "", "getWidth", "getShape", "Lexpo/modules/medialibrary/next/records/Shape;", "getMediaType", "Lexpo/modules/medialibrary/next/objects/wrappers/MediaType;", "getModificationTime", "getUri", "getInfo", "Lexpo/modules/medialibrary/next/records/AssetInfo;", "getMimeType", "Lexpo/modules/medialibrary/next/objects/wrappers/MimeType;", "getMimeType-dctPOJs", "getLocation", "Lexpo/modules/medialibrary/next/records/Location;", "getExif", "Landroid/os/Bundle;", "move", "", "relativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "move-dXLngQ8", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copy", "copy-dXLngQ8", "delete", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Asset extends SharedObject {
    private final AssetDelegate assetDelegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Asset(AssetDelegate assetDelegate) {
        super((Runtime) null, 1, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(assetDelegate, "assetDelegate");
        this.assetDelegate = assetDelegate;
    }

    public final AssetDelegate getAssetDelegate() {
        return this.assetDelegate;
    }

    public final Uri getContentUri() {
        return this.assetDelegate.getContentUri();
    }

    public final Object getCreationTime(Continuation<? super Long> continuation) {
        return this.assetDelegate.getCreationTime(continuation);
    }

    public final Object getDuration(Continuation<? super Long> continuation) {
        return this.assetDelegate.getDuration(continuation);
    }

    public final Object getFilename(Continuation<? super String> continuation) {
        return this.assetDelegate.getFilename(continuation);
    }

    public final Object getHeight(Continuation<? super Integer> continuation) {
        return this.assetDelegate.getHeight(continuation);
    }

    public final Object getWidth(Continuation<? super Integer> continuation) {
        return this.assetDelegate.getWidth(continuation);
    }

    public final Object getShape(Continuation<? super Shape> continuation) {
        return this.assetDelegate.getShape(continuation);
    }

    public final Object getMediaType(Continuation<? super MediaType> continuation) {
        return this.assetDelegate.getMediaType(continuation);
    }

    public final Object getModificationTime(Continuation<? super Long> continuation) {
        return this.assetDelegate.getModificationTime(continuation);
    }

    public final Object getUri(Continuation<? super Uri> continuation) {
        return this.assetDelegate.getUri(continuation);
    }

    public final Object getInfo(Continuation<? super AssetInfo> continuation) {
        return this.assetDelegate.getInfo(continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: getMimeType-dctPOJs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1292getMimeTypedctPOJs(kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.objects.wrappers.MimeType> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof expo.modules.medialibrary.next.objects.asset.Asset$getMimeType$1
            if (r0 == 0) goto L14
            r0 = r5
            expo.modules.medialibrary.next.objects.asset.Asset$getMimeType$1 r0 = (expo.modules.medialibrary.next.objects.asset.Asset$getMimeType$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.asset.Asset$getMimeType$1 r0 = new expo.modules.medialibrary.next.objects.asset.Asset$getMimeType$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L38
            if (r2 != r3) goto L30
            kotlin.ResultKt.throwOnFailure(r5)
            expo.modules.medialibrary.next.objects.wrappers.MimeType r5 = (expo.modules.medialibrary.next.objects.wrappers.MimeType) r5
            java.lang.String r5 = r5.m1321unboximpl()
            goto L46
        L30:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L38:
            kotlin.ResultKt.throwOnFailure(r5)
            expo.modules.medialibrary.next.objects.asset.delegates.AssetDelegate r5 = r4.assetDelegate
            r0.label = r3
            java.lang.Object r5 = r5.mo1295getMimeTypedctPOJs(r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            java.lang.String r5 = (java.lang.String) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.Asset.m1292getMimeTypedctPOJs(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object getLocation(Continuation<? super Location> continuation) {
        return this.assetDelegate.getLocation(continuation);
    }

    public final Object getExif(Continuation<? super Bundle> continuation) {
        return this.assetDelegate.getExif(continuation);
    }

    /* renamed from: move-dXLngQ8, reason: not valid java name */
    public final Object m1293movedXLngQ8(String str, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new Asset$move$2(this, str, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* renamed from: copy-dXLngQ8, reason: not valid java name */
    public final Object m1291copydXLngQ8(String str, Continuation<? super Asset> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new Asset$copy$2(this, str, null), continuation);
    }

    public final Object delete(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new Asset$delete$2(this, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
