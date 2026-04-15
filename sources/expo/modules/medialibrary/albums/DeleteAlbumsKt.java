package expo.modules.medialibrary.albums;

import kotlin.Metadata;

/* compiled from: DeleteAlbums.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"deleteAlbums", "", "context", "Landroid/content/Context;", "albumIds", "", "", "(Landroid/content/Context;[Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeleteAlbumsKt {
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008b, code lost:
    
        if (r11 == r1) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object deleteAlbums(android.content.Context r9, java.lang.String[] r10, kotlin.coroutines.Continuation<? super java.lang.Boolean> r11) {
        /*
            boolean r0 = r11 instanceof expo.modules.medialibrary.albums.DeleteAlbumsKt$deleteAlbums$1
            if (r0 == 0) goto L14
            r0 = r11
            expo.modules.medialibrary.albums.DeleteAlbumsKt$deleteAlbums$1 r0 = (expo.modules.medialibrary.albums.DeleteAlbumsKt$deleteAlbums$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            expo.modules.medialibrary.albums.DeleteAlbumsKt$deleteAlbums$1 r0 = new expo.modules.medialibrary.albums.DeleteAlbumsKt$deleteAlbums$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L48
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r11)
            return r11
        L2d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L35:
            java.lang.Object r9 = r0.L$2
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r10 = r0.L$1
            java.lang.String[] r10 = (java.lang.String[]) r10
            java.lang.Object r2 = r0.L$0
            android.content.Context r2 = (android.content.Context) r2
            kotlin.ResultKt.throwOnFailure(r11)
            r8 = r2
            r2 = r9
            r9 = r8
            goto L8e
        L48:
            kotlin.ResultKt.throwOnFailure(r11)
            expo.modules.medialibrary.MediaLibraryUtils r11 = expo.modules.medialibrary.MediaLibraryUtils.INSTANCE
            java.lang.String r11 = r11.queryPlaceholdersFor(r10)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "bucket_id IN ("
            r2.<init>(r5)
            java.lang.StringBuilder r11 = r2.append(r11)
            java.lang.String r2 = ")"
            java.lang.StringBuilder r11 = r11.append(r2)
            java.lang.String r11 = r11.toString()
            expo.modules.medialibrary.MediaLibraryUtils r6 = expo.modules.medialibrary.MediaLibraryUtils.INSTANCE
            java.lang.String r6 = r6.queryPlaceholdersFor(r10)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r5)
            java.lang.StringBuilder r5 = r7.append(r6)
            java.lang.StringBuilder r2 = r5.append(r2)
            java.lang.String r2 = r2.toString()
            expo.modules.medialibrary.MediaLibraryUtils r5 = expo.modules.medialibrary.MediaLibraryUtils.INSTANCE
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r2
            r0.label = r4
            java.lang.Object r11 = r5.deleteAssets(r9, r11, r10, r0)
            if (r11 != r1) goto L8e
            goto La7
        L8e:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto La9
            expo.modules.medialibrary.MediaLibraryUtils r11 = expo.modules.medialibrary.MediaLibraryUtils.INSTANCE
            r4 = 0
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r4
            r0.label = r3
            java.lang.Object r9 = r11.deleteAssets(r9, r2, r10, r0)
            if (r9 != r1) goto La8
        La7:
            return r1
        La8:
            return r9
        La9:
            r9 = 0
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.albums.DeleteAlbumsKt.deleteAlbums(android.content.Context, java.lang.String[], kotlin.coroutines.Continuation):java.lang.Object");
    }
}
