package expo.modules.medialibrary.next.objects.album.factories;

import android.content.ContentResolver;
import android.content.Context;
import expo.modules.medialibrary.next.exceptions.AlbumCouldNotBeCreated;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.album.Album;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter;
import expo.modules.medialibrary.next.objects.asset.factories.AssetFactory;
import expo.modules.medialibrary.next.objects.wrappers.RelativePath;
import java.io.File;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlbumLegacyFactory.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J,\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ$\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0018H\u0096@¢\u0006\u0002\u0010 J.\u0010!\u001a\u00020\"2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\u001bH\u0082@¢\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002¢\u0006\u0004\b(\u0010)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00070\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006*"}, d2 = {"Lexpo/modules/medialibrary/next/objects/album/factories/AlbumLegacyFactory;", "Lexpo/modules/medialibrary/next/objects/album/factories/AlbumFactory;", "assetFactory", "Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "assetDeleter", "Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "context", "Landroid/content/Context;", "<init>", "(Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;Landroid/content/Context;)V", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "create", "Lexpo/modules/medialibrary/next/objects/album/Album;", "id", "", "createFromAssets", "albumName", "assets", "", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "deleteOriginalAssets", "", "(Ljava/lang/String;Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFromFilePaths", "filePaths", "Landroid/net/Uri;", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processAssetsLocation", "", "relativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "processAssetsLocation-KTsBHyQ", "(Ljava/util/List;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createAlbumDirectoryIfNotExists", "createAlbumDirectoryIfNotExists-YsBE8GI", "(Ljava/lang/String;)V", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlbumLegacyFactory implements AlbumFactory {
    private final AssetDeleter assetDeleter;
    private final AssetFactory assetFactory;
    private final WeakReference<Context> contextRef;

    public AlbumLegacyFactory(AssetFactory assetFactory, AssetDeleter assetDeleter, Context context) {
        Intrinsics.checkNotNullParameter(assetFactory, "assetFactory");
        Intrinsics.checkNotNullParameter(assetDeleter, "assetDeleter");
        Intrinsics.checkNotNullParameter(context, "context");
        this.assetFactory = assetFactory;
        this.assetDeleter = assetDeleter;
        this.contextRef = new WeakReference<>(context);
    }

    private final ContentResolver getContentResolver() {
        ContentResolver contentResolver = WeakReferenceExtensionsKt.getOrThrow(this.contextRef).getContentResolver();
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new AlbumCouldNotBeCreated("Failed to create album: ContentResolver is unavailable.", null, 2, null);
    }

    @Override // expo.modules.medialibrary.next.objects.album.factories.AlbumFactory
    public Album create(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return new Album(id, this.assetDeleter, this.assetFactory, WeakReferenceExtensionsKt.getOrThrow(this.contextRef));
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a8, code lost:
    
        if (r11 == r1) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
    @Override // expo.modules.medialibrary.next.objects.album.factories.AlbumFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object createFromAssets(java.lang.String r8, java.util.List<expo.modules.medialibrary.next.objects.asset.Asset> r9, boolean r10, kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.objects.album.Album> r11) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.factories.AlbumLegacyFactory.createFromAssets(java.lang.String, java.util.List, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00a0 -> B:21:0x00a4). Please report as a decompilation issue!!! */
    @Override // expo.modules.medialibrary.next.objects.album.factories.AlbumFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object createFromFilePaths(java.lang.String r9, java.util.List<? extends android.net.Uri> r10, kotlin.coroutines.Continuation<? super expo.modules.medialibrary.next.objects.album.Album> r11) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.factories.AlbumLegacyFactory.createFromFilePaths(java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00d2 -> B:11:0x00d4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0093 -> B:25:0x0095). Please report as a decompilation issue!!! */
    /* renamed from: processAssetsLocation-KTsBHyQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1288processAssetsLocationKTsBHyQ(java.util.List<expo.modules.medialibrary.next.objects.asset.Asset> r7, java.lang.String r8, boolean r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.album.factories.AlbumLegacyFactory.m1288processAssetsLocationKTsBHyQ(java.util.List, java.lang.String, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: createAlbumDirectoryIfNotExists-YsBE8GI, reason: not valid java name */
    private final void m1287createAlbumDirectoryIfNotExistsYsBE8GI(String relativePath) {
        File file = new File(RelativePath.m1328toFilePathimpl(relativePath));
        if (!file.exists() && !file.mkdirs()) {
            file = null;
        }
        if (file == null) {
            throw new AlbumCouldNotBeCreated("Could not create album directory", null, 2, null);
        }
    }
}
