package expo.modules.medialibrary.next.objects.album;

import android.content.ContentResolver;
import android.content.Context;
import com.facebook.react.modules.dialog.AlertFragment;
import expo.modules.medialibrary.next.exceptions.ContentResolverNotObtainedException;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.album.factories.AlbumFactory;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlbumQuery.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00050\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"Lexpo/modules/medialibrary/next/objects/album/AlbumQuery;", "", "albumFactory", "Lexpo/modules/medialibrary/next/objects/album/factories/AlbumFactory;", "context", "Landroid/content/Context;", "<init>", "(Lexpo/modules/medialibrary/next/objects/album/factories/AlbumFactory;Landroid/content/Context;)V", "getAlbumFactory", "()Lexpo/modules/medialibrary/next/objects/album/factories/AlbumFactory;", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "getAlbum", "Lexpo/modules/medialibrary/next/objects/album/Album;", AlertFragment.ARG_TITLE, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlbumQuery {
    private final AlbumFactory albumFactory;
    private final WeakReference<Context> contextRef;

    public AlbumQuery(AlbumFactory albumFactory, Context context) {
        Intrinsics.checkNotNullParameter(albumFactory, "albumFactory");
        Intrinsics.checkNotNullParameter(context, "context");
        this.albumFactory = albumFactory;
        this.contextRef = new WeakReference<>(context);
    }

    public final AlbumFactory getAlbumFactory() {
        return this.albumFactory;
    }

    private final ContentResolver getContentResolver() {
        ContentResolver contentResolver = WeakReferenceExtensionsKt.getOrThrow(this.contextRef).getContentResolver();
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new ContentResolverNotObtainedException(null, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0046 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getAlbum(java.lang.String r5, kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.objects.album.Album> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof expo.modules.medialibrary.next.objects.album.AlbumQuery$getAlbum$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.medialibrary.next.objects.album.AlbumQuery$getAlbum$1 r0 = (expo.modules.medialibrary.next.objects.album.AlbumQuery$getAlbum$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.medialibrary.next.objects.album.AlbumQuery$getAlbum$1 r0 = new expo.modules.medialibrary.next.objects.album.AlbumQuery$getAlbum$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L42
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            android.content.ContentResolver r6 = r4.getContentResolver()
            r0.label = r3
            java.lang.Object r6 = expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt.queryAlbumId(r6, r5, r0)
            if (r6 != r1) goto L42
            return r1
        L42:
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L48
            r5 = 0
            return r5
        L48:
            expo.modules.medialibrary.next.objects.album.factories.AlbumFactory r5 = r4.albumFactory
            expo.modules.medialibrary.next.objects.album.Album r5 = r5.create(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.AlbumQuery.getAlbum(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
