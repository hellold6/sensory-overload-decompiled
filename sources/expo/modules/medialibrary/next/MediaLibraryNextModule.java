package expo.modules.medialibrary.next;

import android.content.Context;
import android.os.Build;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.modules.Module;
import expo.modules.medialibrary.next.objects.album.AlbumQuery;
import expo.modules.medialibrary.next.objects.album.factories.AlbumFactory;
import expo.modules.medialibrary.next.objects.album.factories.AlbumLegacyFactory;
import expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetLegacyDeleter;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetModernDeleter;
import expo.modules.medialibrary.next.objects.asset.factories.AssetFactory;
import expo.modules.medialibrary.next.objects.asset.factories.AssetLegacyFactory;
import expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory;
import expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate;
import expo.modules.medialibrary.next.permissions.SystemPermissionsDelegate;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: MediaLibraryNextModule.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010'\u001a\u00020(H\u0016R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\r\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\r\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020#8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\r\u001a\u0004\b$\u0010%¨\u0006)"}, d2 = {"Lexpo/modules/medialibrary/next/MediaLibraryNextModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "systemPermissionsDelegate", "Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;", "getSystemPermissionsDelegate", "()Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;", "systemPermissionsDelegate$delegate", "Lkotlin/Lazy;", "mediaStorePermissionsDelegate", "Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;", "getMediaStorePermissionsDelegate", "()Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;", "mediaStorePermissionsDelegate$delegate", "albumQuery", "Lexpo/modules/medialibrary/next/objects/album/AlbumQuery;", "getAlbumQuery", "()Lexpo/modules/medialibrary/next/objects/album/AlbumQuery;", "albumQuery$delegate", "albumFactory", "Lexpo/modules/medialibrary/next/objects/album/factories/AlbumFactory;", "getAlbumFactory", "()Lexpo/modules/medialibrary/next/objects/album/factories/AlbumFactory;", "albumFactory$delegate", "assetFactory", "Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "getAssetFactory", "()Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "assetFactory$delegate", "assetDeleter", "Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "getAssetDeleter", "()Lexpo/modules/medialibrary/next/objects/asset/deleters/AssetDeleter;", "assetDeleter$delegate", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaLibraryNextModule extends Module {

    /* renamed from: systemPermissionsDelegate$delegate, reason: from kotlin metadata */
    private final Lazy systemPermissionsDelegate = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.next.MediaLibraryNextModule$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            SystemPermissionsDelegate systemPermissionsDelegate_delegate$lambda$0;
            systemPermissionsDelegate_delegate$lambda$0 = MediaLibraryNextModule.systemPermissionsDelegate_delegate$lambda$0(MediaLibraryNextModule.this);
            return systemPermissionsDelegate_delegate$lambda$0;
        }
    });

    /* renamed from: mediaStorePermissionsDelegate$delegate, reason: from kotlin metadata */
    private final Lazy mediaStorePermissionsDelegate = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.next.MediaLibraryNextModule$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            MediaStorePermissionsDelegate mediaStorePermissionsDelegate_delegate$lambda$1;
            mediaStorePermissionsDelegate_delegate$lambda$1 = MediaLibraryNextModule.mediaStorePermissionsDelegate_delegate$lambda$1(MediaLibraryNextModule.this);
            return mediaStorePermissionsDelegate_delegate$lambda$1;
        }
    });

    /* renamed from: albumQuery$delegate, reason: from kotlin metadata */
    private final Lazy albumQuery = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.next.MediaLibraryNextModule$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AlbumQuery albumQuery_delegate$lambda$2;
            albumQuery_delegate$lambda$2 = MediaLibraryNextModule.albumQuery_delegate$lambda$2(MediaLibraryNextModule.this);
            return albumQuery_delegate$lambda$2;
        }
    });

    /* renamed from: albumFactory$delegate, reason: from kotlin metadata */
    private final Lazy albumFactory = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.next.MediaLibraryNextModule$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AlbumFactory albumFactory_delegate$lambda$3;
            albumFactory_delegate$lambda$3 = MediaLibraryNextModule.albumFactory_delegate$lambda$3(MediaLibraryNextModule.this);
            return albumFactory_delegate$lambda$3;
        }
    });

    /* renamed from: assetFactory$delegate, reason: from kotlin metadata */
    private final Lazy assetFactory = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.next.MediaLibraryNextModule$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AssetFactory assetFactory_delegate$lambda$4;
            assetFactory_delegate$lambda$4 = MediaLibraryNextModule.assetFactory_delegate$lambda$4(MediaLibraryNextModule.this);
            return assetFactory_delegate$lambda$4;
        }
    });

    /* renamed from: assetDeleter$delegate, reason: from kotlin metadata */
    private final Lazy assetDeleter = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.next.MediaLibraryNextModule$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AssetDeleter assetDeleter_delegate$lambda$5;
            assetDeleter_delegate$lambda$5 = MediaLibraryNextModule.assetDeleter_delegate$lambda$5(MediaLibraryNextModule.this);
            return assetDeleter_delegate$lambda$5;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SystemPermissionsDelegate getSystemPermissionsDelegate() {
        return (SystemPermissionsDelegate) this.systemPermissionsDelegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SystemPermissionsDelegate systemPermissionsDelegate_delegate$lambda$0(MediaLibraryNextModule mediaLibraryNextModule) {
        return new SystemPermissionsDelegate(mediaLibraryNextModule.getAppContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaStorePermissionsDelegate getMediaStorePermissionsDelegate() {
        return (MediaStorePermissionsDelegate) this.mediaStorePermissionsDelegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaStorePermissionsDelegate mediaStorePermissionsDelegate_delegate$lambda$1(MediaLibraryNextModule mediaLibraryNextModule) {
        return new MediaStorePermissionsDelegate(mediaLibraryNextModule.getAppContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AlbumQuery getAlbumQuery() {
        return (AlbumQuery) this.albumQuery.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AlbumQuery albumQuery_delegate$lambda$2(MediaLibraryNextModule mediaLibraryNextModule) {
        return new AlbumQuery(mediaLibraryNextModule.getAlbumFactory(), mediaLibraryNextModule.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AlbumFactory getAlbumFactory() {
        return (AlbumFactory) this.albumFactory.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AlbumFactory albumFactory_delegate$lambda$3(MediaLibraryNextModule mediaLibraryNextModule) {
        if (Build.VERSION.SDK_INT >= 30) {
            return new AlbumModernFactory(mediaLibraryNextModule.getAssetFactory(), mediaLibraryNextModule.getAssetDeleter(), mediaLibraryNextModule.getMediaStorePermissionsDelegate(), mediaLibraryNextModule.getContext());
        }
        return new AlbumLegacyFactory(mediaLibraryNextModule.getAssetFactory(), mediaLibraryNextModule.getAssetDeleter(), mediaLibraryNextModule.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AssetFactory getAssetFactory() {
        return (AssetFactory) this.assetFactory.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AssetFactory assetFactory_delegate$lambda$4(MediaLibraryNextModule mediaLibraryNextModule) {
        if (Build.VERSION.SDK_INT >= 30) {
            return new AssetModernFactory(mediaLibraryNextModule.getAssetDeleter(), mediaLibraryNextModule.getMediaStorePermissionsDelegate(), mediaLibraryNextModule.getContext());
        }
        return new AssetLegacyFactory(mediaLibraryNextModule.getAssetDeleter(), mediaLibraryNextModule.getSystemPermissionsDelegate(), mediaLibraryNextModule.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AssetDeleter getAssetDeleter() {
        return (AssetDeleter) this.assetDeleter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AssetDeleter assetDeleter_delegate$lambda$5(MediaLibraryNextModule mediaLibraryNextModule) {
        if (Build.VERSION.SDK_INT >= 30) {
            return new AssetModernDeleter(mediaLibraryNextModule.getMediaStorePermissionsDelegate());
        }
        return new AssetLegacyDeleter(mediaLibraryNextModule.getSystemPermissionsDelegate(), mediaLibraryNextModule.getContext());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r9v1 expo.modules.kotlin.classcomponent.ClassComponentBuilder, still in use, count: 2, list:
          (r9v1 expo.modules.kotlin.classcomponent.ClassComponentBuilder) from 0x00a1: MOVE (r18v0 expo.modules.kotlin.classcomponent.ClassComponentBuilder) = (r9v1 expo.modules.kotlin.classcomponent.ClassComponentBuilder)
          (r9v1 expo.modules.kotlin.classcomponent.ClassComponentBuilder) from 0x0093: MOVE (r18v21 expo.modules.kotlin.classcomponent.ClassComponentBuilder) = (r9v1 expo.modules.kotlin.classcomponent.ClassComponentBuilder)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:80)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:56)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:447)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    @Override // expo.modules.kotlin.modules.Module
    public expo.modules.kotlin.modules.ModuleDefinitionData definition() {
        /*
            Method dump skipped, instructions count: 6074
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.MediaLibraryNextModule.definition():expo.modules.kotlin.modules.ModuleDefinitionData");
    }
}
