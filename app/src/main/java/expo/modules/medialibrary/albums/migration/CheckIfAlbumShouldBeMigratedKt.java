package expo.modules.medialibrary.albums.migration;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: CheckIfAlbumShouldBeMigrated.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0087@¢\u0006\u0002\u0010\u0006\u001a \u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0083@¢\u0006\u0002\u0010\u0006¨\u0006\t"}, d2 = {"checkIfAlbumShouldBeMigrated", "", "context", "Landroid/content/Context;", "albumId", "", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlbumDirectory", "Ljava/io/File;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CheckIfAlbumShouldBeMigratedKt {
    /* JADX WARN: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object checkIfAlbumShouldBeMigrated(android.content.Context r4, java.lang.String r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            boolean r0 = r6 instanceof expo.modules.medialibrary.albums.migration.CheckIfAlbumShouldBeMigratedKt$checkIfAlbumShouldBeMigrated$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.medialibrary.albums.migration.CheckIfAlbumShouldBeMigratedKt$checkIfAlbumShouldBeMigrated$1 r0 = (expo.modules.medialibrary.albums.migration.CheckIfAlbumShouldBeMigratedKt$checkIfAlbumShouldBeMigrated$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.medialibrary.albums.migration.CheckIfAlbumShouldBeMigratedKt$checkIfAlbumShouldBeMigrated$1 r0 = new expo.modules.medialibrary.albums.migration.CheckIfAlbumShouldBeMigratedKt$checkIfAlbumShouldBeMigrated$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3e
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.label = r3
            java.lang.Object r6 = getAlbumDirectory(r4, r5, r0)
            if (r6 != r1) goto L3e
            return r1
        L3e:
            java.io.File r6 = (java.io.File) r6
            if (r6 == 0) goto L4c
            boolean r4 = r6.canWrite()
            r4 = r4 ^ r3
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r4
        L4c:
            expo.modules.medialibrary.AlbumNotFound r4 = new expo.modules.medialibrary.AlbumNotFound
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.albums.migration.CheckIfAlbumShouldBeMigratedKt.checkIfAlbumShouldBeMigrated(android.content.Context, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getAlbumDirectory(Context context, String str, Continuation<? super File> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new CheckIfAlbumShouldBeMigratedKt$getAlbumDirectory$2(str, context, null), continuation);
    }
}
