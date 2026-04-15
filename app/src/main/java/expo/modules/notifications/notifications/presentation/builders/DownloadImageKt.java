package expo.modules.notifications.notifications.presentation.builders;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: DownloadImage.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a,\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"downloadImage", "Landroid/graphics/Bitmap;", "imageUrl", "Landroid/net/Uri;", "connectTimeout", "", "readTimeout", "(Landroid/net/Uri;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-notifications_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DownloadImageKt {
    /* JADX WARN: Can't wrap try/catch for region: R(9:1|(2:3|(7:5|6|7|(1:(1:10)(2:18|19))(3:20|21|(1:23))|11|12|(1:14)(1:16)))|26|6|7|(0)(0)|11|12|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0057, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
    
        r0 = kotlin.Result.INSTANCE;
        r14 = kotlin.Result.m1409constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object downloadImage(android.net.Uri r14, long r15, long r17, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$1
            if (r1 == 0) goto L16
            r1 = r0
            expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$1 r1 = (expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$1 r1 = new expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$1
            r1.<init>(r0)
        L1b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L34
            if (r3 != r4) goto L2c
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L57
            goto L50
        L2c:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L34:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L57
            long r5 = r15 + r17
            expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$2$1 r7 = new expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$2$1     // Catch: java.lang.Throwable -> L57
            r13 = 0
            r8 = r14
            r9 = r15
            r11 = r17
            r7.<init>(r8, r9, r11, r13)     // Catch: java.lang.Throwable -> L57
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7     // Catch: java.lang.Throwable -> L57
            r1.label = r4     // Catch: java.lang.Throwable -> L57
            java.lang.Object r0 = kotlinx.coroutines.TimeoutKt.withTimeout(r5, r7, r1)     // Catch: java.lang.Throwable -> L57
            if (r0 != r2) goto L50
            return r2
        L50:
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0     // Catch: java.lang.Throwable -> L57
            java.lang.Object r14 = kotlin.Result.m1409constructorimpl(r0)     // Catch: java.lang.Throwable -> L57
            goto L63
        L57:
            r0 = move-exception
            r14 = r0
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE
            java.lang.Object r14 = kotlin.ResultKt.createFailure(r14)
            java.lang.Object r14 = kotlin.Result.m1409constructorimpl(r14)
        L63:
            boolean r0 = kotlin.Result.m1415isFailureimpl(r14)
            if (r0 == 0) goto L6a
            r14 = 0
        L6a:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.notifications.presentation.builders.DownloadImageKt.downloadImage(android.net.Uri, long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object downloadImage$default(Uri uri, long j, long j2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 8000;
        }
        if ((i & 4) != 0) {
            j2 = 8000;
        }
        return downloadImage(uri, j, j2, continuation);
    }
}
