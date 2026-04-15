package expo.modules.medialibrary.assets;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: CreateAsset.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001aH\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0086@¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"createAssetWithAlbumId", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "Lkotlin/collections/ArrayList;", "context", "Landroid/content/Context;", "uri", "", "resolveWithAdditionalData", "", "albumId", "(Landroid/content/Context;Ljava/lang/String;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreateAssetKt {
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0058, code lost:
    
        if (r11 == r1) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0071 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object createAssetWithAlbumId(android.content.Context r7, java.lang.String r8, boolean r9, java.lang.String r10, kotlin.coroutines.Continuation<? super java.util.ArrayList<android.os.Bundle>> r11) {
        /*
            boolean r0 = r11 instanceof expo.modules.medialibrary.assets.CreateAssetKt$createAssetWithAlbumId$1
            if (r0 == 0) goto L14
            r0 = r11
            expo.modules.medialibrary.assets.CreateAssetKt$createAssetWithAlbumId$1 r0 = (expo.modules.medialibrary.assets.CreateAssetKt$createAssetWithAlbumId$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            expo.modules.medialibrary.assets.CreateAssetKt$createAssetWithAlbumId$1 r0 = new expo.modules.medialibrary.assets.CreateAssetKt$createAssetWithAlbumId$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L47
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            kotlin.ResultKt.throwOnFailure(r11)
            return r11
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L36:
            boolean r7 = r0.Z$0
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r0.L$0
            android.content.Context r9 = (android.content.Context) r9
            kotlin.ResultKt.throwOnFailure(r11)
            r6 = r9
            r9 = r7
            r7 = r6
            goto L5b
        L47:
            kotlin.ResultKt.throwOnFailure(r11)
            if (r10 == 0) goto L5e
            r0.L$0 = r7
            r0.L$1 = r8
            r0.Z$0 = r9
            r0.label = r4
            java.lang.Object r11 = expo.modules.medialibrary.albums.AlbumUtilsKt.getAlbumFileOrNull(r7, r10, r0)
            if (r11 != r1) goto L5b
            goto L70
        L5b:
            java.io.File r11 = (java.io.File) r11
            goto L5f
        L5e:
            r11 = r5
        L5f:
            expo.modules.medialibrary.assets.CreateAssetWithAlbumFile r10 = new expo.modules.medialibrary.assets.CreateAssetWithAlbumFile
            r10.<init>(r7, r8, r9, r11)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r7 = r10.execute(r0)
            if (r7 != r1) goto L71
        L70:
            return r1
        L71:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.assets.CreateAssetKt.createAssetWithAlbumId(android.content.Context, java.lang.String, boolean, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object createAssetWithAlbumId$default(Context context, String str, boolean z, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        return createAssetWithAlbumId(context, str, z, str2, continuation);
    }
}
