package expo.modules.medialibrary.albums;

import android.content.Context;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: CreateAlbum.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\t\u001a(\u0010\n\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"createAlbum", "Landroid/os/Bundle;", "context", "Landroid/content/Context;", "albumName", "", "assetId", "copyAsset", "", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createAlbumWithInitialFileUri", "assetUri", "Landroid/net/Uri;", "(Landroid/content/Context;Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreateAlbumKt {
    public static final Object createAlbum(Context context, String str, String str2, boolean z, Continuation<? super Bundle> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new CreateAlbumKt$createAlbum$2(z, context, str2, str, null), continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0086, code lost:
    
        if (r2.execute(r0) == r1) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0096 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object createAlbumWithInitialFileUri(android.content.Context r6, java.lang.String r7, android.net.Uri r8, kotlin.coroutines.Continuation<? super android.os.Bundle> r9) {
        /*
            boolean r0 = r9 instanceof expo.modules.medialibrary.albums.CreateAlbumKt$createAlbumWithInitialFileUri$1
            if (r0 == 0) goto L14
            r0 = r9
            expo.modules.medialibrary.albums.CreateAlbumKt$createAlbumWithInitialFileUri$1 r0 = (expo.modules.medialibrary.albums.CreateAlbumKt$createAlbumWithInitialFileUri$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            expo.modules.medialibrary.albums.CreateAlbumKt$createAlbumWithInitialFileUri$1 r0 = new expo.modules.medialibrary.albums.CreateAlbumKt$createAlbumWithInitialFileUri$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r9)
            return r9
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            java.lang.Object r6 = r0.L$1
            r7 = r6
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r6 = r0.L$0
            android.content.Context r6 = (android.content.Context) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L89
        L42:
            kotlin.ResultKt.throwOnFailure(r9)
            expo.modules.medialibrary.MediaLibraryUtils r9 = expo.modules.medialibrary.MediaLibraryUtils.INSTANCE
            android.content.ContentResolver r2 = r6.getContentResolver()
            java.lang.String r5 = "getContentResolver(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            java.lang.String r9 = r9.getMimeType(r2, r8)
            java.lang.String r2 = "`."
            if (r9 == 0) goto Lcc
            java.lang.String r5 = r8.getPath()
            if (r5 == 0) goto Lb3
            java.io.File r9 = expo.modules.medialibrary.albums.AlbumUtilsKt.createAlbumFile(r9, r7)
            java.io.File r2 = new java.io.File
            r2.<init>(r5)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L98
            expo.modules.medialibrary.assets.CreateAssetWithAlbumFile r2 = new expo.modules.medialibrary.assets.CreateAssetWithAlbumFile
            java.lang.String r8 = r8.toString()
            java.lang.String r5 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r5)
            r5 = 0
            r2.<init>(r6, r8, r5, r9)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r2.execute(r0)
            if (r8 != r1) goto L89
            goto L96
        L89:
            r8 = 0
            r0.L$0 = r8
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r6 = expo.modules.medialibrary.albums.GetAlbumKt.getAlbum(r6, r7, r0)
            if (r6 != r1) goto L97
        L96:
            return r1
        L97:
            return r6
        L98:
            expo.modules.medialibrary.AlbumException r6 = new expo.modules.medialibrary.AlbumException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "Failed to create album: the local media file with uri: `"
            r7.<init>(r9)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = "` does not exist."
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        Lb3:
            expo.modules.medialibrary.AlbumException r6 = new expo.modules.medialibrary.AlbumException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "Failed to create album: could not determine path of the asset with uri: `"
            r7.<init>(r9)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r2)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        Lcc:
            expo.modules.medialibrary.AlbumException r6 = new expo.modules.medialibrary.AlbumException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "Failed to create album: could not determine MIME type of the asset with uri: `"
            r7.<init>(r9)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r2)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.albums.CreateAlbumKt.createAlbumWithInitialFileUri(android.content.Context, java.lang.String, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
