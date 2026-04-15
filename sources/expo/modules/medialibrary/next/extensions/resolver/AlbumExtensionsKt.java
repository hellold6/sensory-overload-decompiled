package expo.modules.medialibrary.next.extensions.resolver;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.MediaStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AlbumExtensions.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\u001a\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\b\u001a\u001c\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\b\u001a\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\b\u001a\u001e\u0010\f\u001a\u0004\u0018\u00010\u0005*\u00020\u00062\u0006\u0010\r\u001a\u00020\nH\u0086@¢\u0006\u0004\b\u000e\u0010\b\u001a\u001c\u0010\f\u001a\u0004\u0018\u00010\u0005*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\b\u001a \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\b\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0012"}, d2 = {"EXTERNAL_CONTENT_URI", "Landroid/net/Uri;", "getEXTERNAL_CONTENT_URI", "()Landroid/net/Uri;", "queryAlbumTitle", "", "Landroid/content/ContentResolver;", "bucketId", "(Landroid/content/ContentResolver;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryAlbumRelativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "queryAlbumFilepath", "queryAlbumId", "relativePath", "queryAlbumId-qTZuolk", "name", "queryAlbumAssetsContentUris", "", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlbumExtensionsKt {
    private static final Uri EXTERNAL_CONTENT_URI;

    static {
        Uri contentUri = MediaStore.Files.getContentUri("external");
        Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(...)");
        EXTERNAL_CONTENT_URI = contentUri;
    }

    public static final Uri getEXTERNAL_CONTENT_URI() {
        return EXTERNAL_CONTENT_URI;
    }

    public static final Object queryAlbumTitle(ContentResolver contentResolver, String str, Continuation<? super String> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, EXTERNAL_CONTENT_URI, "bucket_display_name", AlbumExtensionsKt$queryAlbumTitle$2.INSTANCE, "bucket_id = ?", new String[]{str}, null, continuation, 32, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object queryAlbumRelativePath(android.content.ContentResolver r11, java.lang.String r12, kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.objects.wrappers.RelativePath> r13) {
        /*
            boolean r0 = r13 instanceof expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$1
            if (r0 == 0) goto L14
            r0 = r13
            expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$1 r0 = (expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$1 r0 = new expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$1
            r0.<init>(r13)
        L19:
            r8 = r0
            java.lang.Object r13 = r8.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            kotlin.ResultKt.throwOnFailure(r13)
            goto L55
        L2b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L33:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r2
            android.net.Uri r2 = expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt.EXTERNAL_CONTENT_URI
            expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2 r1 = new kotlin.jvm.functions.Function2<android.database.Cursor, java.lang.Integer, expo.modules.medialibrary.next.objects.wrappers.RelativePath>() { // from class: expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2
                static {
                    /*
                        expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2 r0 = new expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2) expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2.INSTANCE expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ expo.modules.medialibrary.next.objects.wrappers.RelativePath invoke(android.database.Cursor r1, java.lang.Integer r2) {
                    /*
                        r0 = this;
                        android.database.Cursor r1 = (android.database.Cursor) r1
                        java.lang.Number r2 = (java.lang.Number) r2
                        int r2 = r2.intValue()
                        java.lang.String r1 = r0.m1280invokeYrlpHeI(r1, r2)
                        expo.modules.medialibrary.next.objects.wrappers.RelativePath r1 = expo.modules.medialibrary.next.objects.wrappers.RelativePath.m1323boximpl(r1)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                }

                /* renamed from: invoke-YrlpHeI, reason: not valid java name */
                public final java.lang.String m1280invokeYrlpHeI(android.database.Cursor r2, int r3) {
                    /*
                        r1 = this;
                        java.lang.String r0 = "$this$queryOne"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                        java.lang.String r2 = r2.getString(r3)
                        java.lang.String r3 = "getString(...)"
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                        java.lang.String r2 = expo.modules.medialibrary.next.objects.wrappers.RelativePath.m1324constructorimpl(r2)
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumRelativePath$2.m1280invokeYrlpHeI(android.database.Cursor, int):java.lang.String");
                }
            }
            r4 = r1
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.String[] r6 = new java.lang.String[r13]
            r1 = 0
            r6[r1] = r12
            r8.label = r13
            java.lang.String r3 = "relative_path"
            java.lang.String r5 = "bucket_id = ?"
            r7 = 0
            r9 = 32
            r10 = 0
            r1 = r11
            java.lang.Object r13 = expo.modules.medialibrary.next.extensions.resolver.QueryOneKt.queryOne$default(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            if (r13 != r0) goto L55
            return r0
        L55:
            expo.modules.medialibrary.next.objects.wrappers.RelativePath r13 = (expo.modules.medialibrary.next.objects.wrappers.RelativePath) r13
            if (r13 == 0) goto L5e
            java.lang.String r11 = r13.m1330unboximpl()
            return r11
        L5e:
            r11 = 0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt.queryAlbumRelativePath(android.content.ContentResolver, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object queryAlbumFilepath(ContentResolver contentResolver, String str, Continuation<? super String> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, EXTERNAL_CONTENT_URI, "_data", AlbumExtensionsKt$queryAlbumFilepath$2.INSTANCE, "bucket_id = ?", new String[]{str}, null, continuation, 32, null);
    }

    /* renamed from: queryAlbumId-qTZuolk, reason: not valid java name */
    public static final Object m1279queryAlbumIdqTZuolk(ContentResolver contentResolver, String str, Continuation<? super String> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, EXTERNAL_CONTENT_URI, "bucket_id", AlbumExtensionsKt$queryAlbumId$2.INSTANCE, "relative_path = ?", new String[]{str}, null, continuation, 32, null);
    }

    public static final Object queryAlbumId(ContentResolver contentResolver, String str, Continuation<? super String> continuation) {
        return QueryOneKt.queryOne$default(contentResolver, EXTERNAL_CONTENT_URI, "bucket_id", AlbumExtensionsKt$queryAlbumId$4.INSTANCE, "bucket_display_name = ?", new String[]{str}, null, continuation, 32, null);
    }

    public static final Object queryAlbumAssetsContentUris(ContentResolver contentResolver, String str, Continuation<? super List<? extends Uri>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AlbumExtensionsKt$queryAlbumAssetsContentUris$2(contentResolver, str, null), continuation);
    }
}
