package expo.modules.medialibrary;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import androidx.tracing.Trace;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.activityresult.AppContextActivityResultLauncher;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.SuspendFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ConstantComponentBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.types.TypeConverterProvider;
import expo.modules.medialibrary.MediaLibraryModule;
import expo.modules.medialibrary.contracts.DeleteContractInput;
import expo.modules.medialibrary.contracts.WriteContractInput;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: MediaLibraryModule.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0016J)\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020\u000e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0003¢\u0006\u0002\u0010$J)\u0010%\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010&\u001a\u00020\u000e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0003¢\u0006\u0002\u0010$J\u001e\u0010'\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0003J$\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u0012\u0010*\u001a\u00020(2\b\b\u0002\u0010+\u001a\u00020\u000eH\u0002J\b\u0010,\u001a\u00020\u000eH\u0002J\u0016\u0010-\u001a\u00020(2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\b\u0010/\u001a\u00020\u000eH\u0002J&\u00100\u001a\u00020(2\f\u00101\u001a\b\u0012\u0004\u0012\u00020!0 2\b\b\u0002\u00102\u001a\u00020\u000eH\u0082@¢\u0006\u0002\u00103J\u0010\u00104\u001a\u00020\u000e2\u0006\u00105\u001a\u000206H\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0018\u00010\tR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0018\u00010\tR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000e0\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0014\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0012R\u0014\u0010\u001e\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0012¨\u00068"}, d2 = {"Lexpo/modules/medialibrary/MediaLibraryModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "imagesObserver", "Lexpo/modules/medialibrary/MediaLibraryModule$MediaStoreContentObserver;", "videosObserver", "deleteLauncher", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultLauncher;", "Lexpo/modules/medialibrary/contracts/DeleteContractInput;", "", "writeLauncher", "Lexpo/modules/medialibrary/contracts/WriteContractInput;", "isExpoGo", "()Z", "isExpoGo$delegate", "Lkotlin/Lazy;", "allowedPermissionsList", "", "Lexpo/modules/medialibrary/GranularPermission;", "getAllowedPermissionsList", "()Ljava/util/List;", "allowedPermissionsList$delegate", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "isMissingPermissions", "isMissingWritePermission", "getManifestPermissions", "", "", "writeOnly", "granularPermissions", "(ZLjava/util/List;)[Ljava/lang/String;", "getGranularPermissions", "shouldIncludeGranular", "assertGranularPermissionIntegrity", "", "getManifestDeclaredPermissions", "requireSystemPermissions", "isWritePermissionRequired", "hasReadPermissions", "maybeThrowIfExpoGo", "permissions", "hasWritePermissions", "requestMediaLibraryActionPermission", "assetIds", "needsDeletePermission", "([Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasWritePermissionForUri", "uri", "Landroid/net/Uri;", "MediaStoreContentObserver", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaLibraryModule extends Module {
    private AppContextActivityResultLauncher<DeleteContractInput, Boolean> deleteLauncher;
    private MediaStoreContentObserver imagesObserver;
    private MediaStoreContentObserver videosObserver;
    private AppContextActivityResultLauncher<WriteContractInput, Boolean> writeLauncher;

    /* renamed from: isExpoGo$delegate, reason: from kotlin metadata */
    private final Lazy isExpoGo = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.MediaLibraryModule$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            boolean isExpoGo_delegate$lambda$0;
            isExpoGo_delegate$lambda$0 = MediaLibraryModule.isExpoGo_delegate$lambda$0(MediaLibraryModule.this);
            return Boolean.valueOf(isExpoGo_delegate$lambda$0);
        }
    });

    /* renamed from: allowedPermissionsList$delegate, reason: from kotlin metadata */
    private final Lazy allowedPermissionsList = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.MediaLibraryModule$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            List allowedPermissionsList_delegate$lambda$1;
            allowedPermissionsList_delegate$lambda$1 = MediaLibraryModule.allowedPermissionsList_delegate$lambda$1(MediaLibraryModule.this);
            return allowedPermissionsList_delegate$lambda$1;
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

    private final boolean isExpoGo() {
        return ((Boolean) this.isExpoGo.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isExpoGo_delegate$lambda$0(MediaLibraryModule mediaLibraryModule) {
        return Boolean.parseBoolean(mediaLibraryModule.getContext().getResources().getString(R.string.is_expo_go));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<GranularPermission> getAllowedPermissionsList() {
        return (List) this.allowedPermissionsList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List allowedPermissionsList_delegate$lambda$1(MediaLibraryModule mediaLibraryModule) {
        return mediaLibraryModule.isExpoGo() ? CollectionsKt.listOf(GranularPermission.AUDIO) : mediaLibraryModule.getManifestDeclaredPermissions(mediaLibraryModule.getContext(), CollectionsKt.listOf((Object[]) new GranularPermission[]{GranularPermission.PHOTO, GranularPermission.VIDEO, GranularPermission.AUDIO}));
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        MediaLibraryModule mediaLibraryModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (mediaLibraryModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(mediaLibraryModule);
            moduleDefinitionBuilder.Name("ExpoMediaLibrary");
            ConstantComponentBuilder constantComponentBuilder = new ConstantComponentBuilder("MediaType");
            constantComponentBuilder.setGetter(new Function0<Map<String, ? extends String>>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Constant$1
                @Override // kotlin.jvm.functions.Function0
                public final Map<String, ? extends String> invoke() {
                    return MediaType.INSTANCE.getConstants();
                }
            });
            moduleDefinitionBuilder.getConstants().put("MediaType", constantComponentBuilder);
            ConstantComponentBuilder constantComponentBuilder2 = new ConstantComponentBuilder("SortBy");
            constantComponentBuilder2.setGetter(new Function0<Map<String, ? extends String>>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Constant$2
                @Override // kotlin.jvm.functions.Function0
                public final Map<String, ? extends String> invoke() {
                    return SortBy.INSTANCE.getConstants();
                }
            });
            moduleDefinitionBuilder.getConstants().put("SortBy", constantComponentBuilder2);
            ConstantComponentBuilder constantComponentBuilder3 = new ConstantComponentBuilder("CHANGE_LISTENER_NAME");
            constantComponentBuilder3.setGetter(new Function0<String>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Constant$3
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return MediaLibraryConstantsKt.LIBRARY_DID_CHANGE_EVENT;
                }
            });
            moduleDefinitionBuilder.getConstants().put("CHANGE_LISTENER_NAME", constantComponentBuilder3);
            moduleDefinitionBuilder.Events(MediaLibraryConstantsKt.LIBRARY_DID_CHANGE_EVENT);
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            TypeConverterProvider converters = moduleDefinitionBuilder2.getConverters();
            AnyType[] anyTypeArr = new AnyType[2];
            AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Boolean.class), false));
            if (anyType == null) {
                anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$AsyncFunctionWithPromise$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Boolean.TYPE);
                    }
                }), converters);
            }
            anyTypeArr[0] = anyType;
            AnyType anyType2 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(List.class), true));
            if (anyType2 == null) {
                anyType2 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(List.class), true, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$AsyncFunctionWithPromise$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(GranularPermission.class)));
                    }
                }), converters);
            }
            anyTypeArr[1] = anyType2;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("requestPermissionsAsync", anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$AsyncFunctionWithPromise$3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    String[] manifestPermissions;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    List list = (List) objArr[1];
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (list == null) {
                        list = MediaLibraryModule.this.getAllowedPermissionsList();
                    }
                    MediaLibraryModule.this.maybeThrowIfExpoGo(list);
                    Permissions permissions = MediaLibraryModule.this.getAppContext().getPermissions();
                    MediaLibraryPermissionPromiseWrapper mediaLibraryPermissionPromiseWrapper = new MediaLibraryPermissionPromiseWrapper(list, promise, new WeakReference(MediaLibraryModule.this.getContext()));
                    manifestPermissions = MediaLibraryModule.this.getManifestPermissions(booleanValue, list);
                    Permissions.askForPermissionsWithPermissionsManager(permissions, mediaLibraryPermissionPromiseWrapper, (String[]) Arrays.copyOf(manifestPermissions, manifestPermissions.length));
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder2.getAsyncFunctions().put("requestPermissionsAsync", asyncFunctionWithPromiseComponent);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2 = asyncFunctionWithPromiseComponent;
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            TypeConverterProvider converters2 = moduleDefinitionBuilder3.getConverters();
            AnyType[] anyTypeArr2 = new AnyType[2];
            AnyType anyType3 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Boolean.class), false));
            if (anyType3 == null) {
                anyType3 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$AsyncFunctionWithPromise$4
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Boolean.TYPE);
                    }
                }), converters2);
            }
            anyTypeArr2[0] = anyType3;
            AnyType anyType4 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(List.class), true));
            if (anyType4 == null) {
                anyType4 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(List.class), true, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$AsyncFunctionWithPromise$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(GranularPermission.class)));
                    }
                }), converters2);
            }
            anyTypeArr2[1] = anyType4;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("getPermissionsAsync", anyTypeArr2, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$AsyncFunctionWithPromise$6
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    String[] manifestPermissions;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    List list = (List) objArr[1];
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (list == null) {
                        list = MediaLibraryModule.this.getAllowedPermissionsList();
                    }
                    MediaLibraryModule.this.maybeThrowIfExpoGo(list);
                    Permissions permissions = MediaLibraryModule.this.getAppContext().getPermissions();
                    MediaLibraryPermissionPromiseWrapper mediaLibraryPermissionPromiseWrapper = new MediaLibraryPermissionPromiseWrapper(list, promise, new WeakReference(MediaLibraryModule.this.getContext()));
                    manifestPermissions = MediaLibraryModule.this.getManifestPermissions(booleanValue, list);
                    Permissions.getPermissionsWithPermissionsManager(permissions, mediaLibraryPermissionPromiseWrapper, (String[]) Arrays.copyOf(manifestPermissions, manifestPermissions.length));
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder3.getAsyncFunctions().put("getPermissionsAsync", asyncFunctionWithPromiseComponent3);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent4 = asyncFunctionWithPromiseComponent3;
            AsyncFunctionBuilder AsyncFunction = moduleDefinitionBuilder.AsyncFunction("saveToLibraryAsync");
            String name = AsyncFunction.getName();
            TypeConverterProvider converters3 = AsyncFunction.getConverters();
            AnyType[] anyTypeArr3 = new AnyType[1];
            AnyType anyType5 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType5 == null) {
                anyType5 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters3);
            }
            anyTypeArr3[0] = anyType5;
            AsyncFunction.setAsyncFunctionComponent(new SuspendFunctionComponent(name, anyTypeArr3, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$2(null, this)));
            AsyncFunctionBuilder AsyncFunction2 = moduleDefinitionBuilder.AsyncFunction("createAssetAsync");
            String name2 = AsyncFunction2.getName();
            TypeConverterProvider converters4 = AsyncFunction2.getConverters();
            AnyType[] anyTypeArr4 = new AnyType[2];
            AnyType anyType6 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType6 == null) {
                anyType6 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$3
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters4);
            }
            anyTypeArr4[0] = anyType6;
            AnyType anyType7 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType7 == null) {
                anyType7 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$4
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters4);
            }
            anyTypeArr4[1] = anyType7;
            AsyncFunction2.setAsyncFunctionComponent(new SuspendFunctionComponent(name2, anyTypeArr4, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$5(null, this)));
            AsyncFunctionBuilder AsyncFunction3 = moduleDefinitionBuilder.AsyncFunction("addAssetsToAlbumAsync");
            String name3 = AsyncFunction3.getName();
            TypeConverterProvider converters5 = AsyncFunction3.getConverters();
            AnyType[] anyTypeArr5 = new AnyType[3];
            AnyType anyType8 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String[].class), false));
            if (anyType8 == null) {
                anyType8 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String[].class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$6
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String[].class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)));
                    }
                }), converters5);
            }
            anyTypeArr5[0] = anyType8;
            AnyType anyType9 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType9 == null) {
                anyType9 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$7
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters5);
            }
            anyTypeArr5[1] = anyType9;
            AnyType anyType10 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Boolean.class), false));
            if (anyType10 == null) {
                anyType10 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$8
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Boolean.TYPE);
                    }
                }), converters5);
            }
            anyTypeArr5[2] = anyType10;
            AsyncFunction3.setAsyncFunctionComponent(new SuspendFunctionComponent(name3, anyTypeArr5, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9(null, this)));
            AsyncFunctionBuilder AsyncFunction4 = moduleDefinitionBuilder.AsyncFunction("removeAssetsFromAlbumAsync");
            String name4 = AsyncFunction4.getName();
            TypeConverterProvider converters6 = AsyncFunction4.getConverters();
            AnyType[] anyTypeArr6 = new AnyType[2];
            AnyType anyType11 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String[].class), false));
            if (anyType11 == null) {
                anyType11 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String[].class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$10
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String[].class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)));
                    }
                }), converters6);
            }
            anyTypeArr6[0] = anyType11;
            AnyType anyType12 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType12 == null) {
                anyType12 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$11
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters6);
            }
            anyTypeArr6[1] = anyType12;
            AsyncFunction4.setAsyncFunctionComponent(new SuspendFunctionComponent(name4, anyTypeArr6, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12(null, this)));
            AsyncFunctionBuilder AsyncFunction5 = moduleDefinitionBuilder.AsyncFunction("deleteAssetsAsync");
            String name5 = AsyncFunction5.getName();
            TypeConverterProvider converters7 = AsyncFunction5.getConverters();
            AnyType[] anyTypeArr7 = new AnyType[1];
            AnyType anyType13 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String[].class), false));
            if (anyType13 == null) {
                anyType13 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String[].class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$13
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String[].class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)));
                    }
                }), converters7);
            }
            anyTypeArr7[0] = anyType13;
            AsyncFunction5.setAsyncFunctionComponent(new SuspendFunctionComponent(name5, anyTypeArr7, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$14(null, this)));
            AsyncFunctionBuilder AsyncFunction6 = moduleDefinitionBuilder.AsyncFunction("getAssetInfoAsync");
            String name6 = AsyncFunction6.getName();
            TypeConverterProvider converters8 = AsyncFunction6.getConverters();
            AnyType[] anyTypeArr8 = new AnyType[2];
            AnyType anyType14 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType14 == null) {
                anyType14 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$15
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters8);
            }
            anyTypeArr8[0] = anyType14;
            AnyType anyType15 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Map.class), true));
            if (anyType15 == null) {
                anyType15 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), true, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$16
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(Map.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.nullableTypeOf(Object.class)));
                    }
                }), converters8);
            }
            anyTypeArr8[1] = anyType15;
            AsyncFunction6.setAsyncFunctionComponent(new SuspendFunctionComponent(name6, anyTypeArr8, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$17(null, this)));
            AsyncFunctionBuilder AsyncFunction7 = moduleDefinitionBuilder.AsyncFunction("getAlbumsAsync");
            String name7 = AsyncFunction7.getName();
            TypeConverterProvider converters9 = AsyncFunction7.getConverters();
            AnyType[] anyTypeArr9 = new AnyType[1];
            AnyType anyType16 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Map.class), true));
            if (anyType16 == null) {
                anyType16 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), true, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$18
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(Map.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.nullableTypeOf(Object.class)));
                    }
                }), converters9);
            }
            anyTypeArr9[0] = anyType16;
            AsyncFunction7.setAsyncFunctionComponent(new SuspendFunctionComponent(name7, anyTypeArr9, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$19(null, this)));
            AsyncFunctionBuilder AsyncFunction8 = moduleDefinitionBuilder.AsyncFunction("getAlbumAsync");
            String name8 = AsyncFunction8.getName();
            TypeConverterProvider converters10 = AsyncFunction8.getConverters();
            AnyType[] anyTypeArr10 = new AnyType[1];
            AnyType anyType17 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType17 == null) {
                anyType17 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$20
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters10);
            }
            anyTypeArr10[0] = anyType17;
            AsyncFunction8.setAsyncFunctionComponent(new SuspendFunctionComponent(name8, anyTypeArr10, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$21(null, this)));
            AsyncFunctionBuilder AsyncFunction9 = moduleDefinitionBuilder.AsyncFunction("createAlbumAsync");
            String name9 = AsyncFunction9.getName();
            TypeConverterProvider converters11 = AsyncFunction9.getConverters();
            AnyType[] anyTypeArr11 = new AnyType[4];
            AnyType anyType18 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType18 == null) {
                anyType18 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$22
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters11);
            }
            anyTypeArr11[0] = anyType18;
            AnyType anyType19 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType19 == null) {
                anyType19 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$23
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters11);
            }
            anyTypeArr11[1] = anyType19;
            AnyType anyType20 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Boolean.class), false));
            if (anyType20 == null) {
                anyType20 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$24
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Boolean.TYPE);
                    }
                }), converters11);
            }
            anyTypeArr11[2] = anyType20;
            AnyType anyType21 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Uri.class), true));
            if (anyType21 == null) {
                anyType21 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Uri.class), true, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$25
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(Uri.class);
                    }
                }), converters11);
            }
            anyTypeArr11[3] = anyType21;
            AsyncFunction9.setAsyncFunctionComponent(new SuspendFunctionComponent(name9, anyTypeArr11, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$26(null, this)));
            AsyncFunctionBuilder AsyncFunction10 = moduleDefinitionBuilder.AsyncFunction("deleteAlbumsAsync");
            String name10 = AsyncFunction10.getName();
            TypeConverterProvider converters12 = AsyncFunction10.getConverters();
            AnyType[] anyTypeArr12 = new AnyType[1];
            AnyType anyType22 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String[].class), false));
            if (anyType22 == null) {
                anyType22 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String[].class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$27
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String[].class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)));
                    }
                }), converters12);
            }
            anyTypeArr12[0] = anyType22;
            AsyncFunction10.setAsyncFunctionComponent(new SuspendFunctionComponent(name10, anyTypeArr12, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$28(null, this)));
            AsyncFunctionBuilder AsyncFunction11 = moduleDefinitionBuilder.AsyncFunction("getAssetsAsync");
            String name11 = AsyncFunction11.getName();
            TypeConverterProvider converters13 = AsyncFunction11.getConverters();
            AnyType[] anyTypeArr13 = new AnyType[1];
            AnyType anyType23 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(AssetsOptions.class), false));
            if (anyType23 == null) {
                anyType23 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(AssetsOptions.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$29
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(AssetsOptions.class);
                    }
                }), converters13);
            }
            anyTypeArr13[0] = anyType23;
            AsyncFunction11.setAsyncFunctionComponent(new SuspendFunctionComponent(name11, anyTypeArr13, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$30(null, this)));
            AsyncFunctionBuilder AsyncFunction12 = moduleDefinitionBuilder.AsyncFunction("migrateAlbumIfNeededAsync");
            String name12 = AsyncFunction12.getName();
            TypeConverterProvider converters14 = AsyncFunction12.getConverters();
            AnyType[] anyTypeArr14 = new AnyType[1];
            AnyType anyType24 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType24 == null) {
                anyType24 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$31
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters14);
            }
            anyTypeArr14[0] = anyType24;
            AsyncFunction12.setAsyncFunctionComponent(new SuspendFunctionComponent(name12, anyTypeArr14, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32(null, this)));
            AsyncFunctionBuilder AsyncFunction13 = moduleDefinitionBuilder.AsyncFunction("albumNeedsMigrationAsync");
            String name13 = AsyncFunction13.getName();
            TypeConverterProvider converters15 = AsyncFunction13.getConverters();
            AnyType[] anyTypeArr15 = new AnyType[1];
            AnyType anyType25 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType25 == null) {
                anyType25 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$33
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters15);
            }
            anyTypeArr15[0] = anyType25;
            AsyncFunction13.setAsyncFunctionComponent(new SuspendFunctionComponent(name13, anyTypeArr15, new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$34(null, this)));
            moduleDefinitionBuilder.OnStartObserving(new Function0<Unit>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$1$19
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    MediaLibraryModule.MediaStoreContentObserver mediaStoreContentObserver;
                    mediaStoreContentObserver = MediaLibraryModule.this.imagesObserver;
                    if (mediaStoreContentObserver != null) {
                        return;
                    }
                    Handler handler = new Handler(Looper.getMainLooper());
                    ContentResolver contentResolver = MediaLibraryModule.this.getContext().getContentResolver();
                    MediaLibraryModule mediaLibraryModule2 = MediaLibraryModule.this;
                    MediaLibraryModule.MediaStoreContentObserver mediaStoreContentObserver2 = new MediaLibraryModule.MediaStoreContentObserver(MediaLibraryModule.this, handler, 1);
                    contentResolver.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, mediaStoreContentObserver2);
                    mediaLibraryModule2.imagesObserver = mediaStoreContentObserver2;
                    MediaLibraryModule mediaLibraryModule3 = MediaLibraryModule.this;
                    MediaLibraryModule.MediaStoreContentObserver mediaStoreContentObserver3 = new MediaLibraryModule.MediaStoreContentObserver(MediaLibraryModule.this, handler, 3);
                    contentResolver.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, mediaStoreContentObserver3);
                    mediaLibraryModule3.videosObserver = mediaStoreContentObserver3;
                }
            });
            moduleDefinitionBuilder.OnStopObserving(new Function0<Unit>() { // from class: expo.modules.medialibrary.MediaLibraryModule$definition$1$20
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    MediaLibraryModule.MediaStoreContentObserver mediaStoreContentObserver;
                    MediaLibraryModule.MediaStoreContentObserver mediaStoreContentObserver2;
                    ContentResolver contentResolver = MediaLibraryModule.this.getContext().getContentResolver();
                    mediaStoreContentObserver = MediaLibraryModule.this.imagesObserver;
                    if (mediaStoreContentObserver != null) {
                        MediaLibraryModule mediaLibraryModule2 = MediaLibraryModule.this;
                        contentResolver.unregisterContentObserver(mediaStoreContentObserver);
                        mediaLibraryModule2.imagesObserver = null;
                    }
                    mediaStoreContentObserver2 = MediaLibraryModule.this.videosObserver;
                    if (mediaStoreContentObserver2 != null) {
                        MediaLibraryModule mediaLibraryModule3 = MediaLibraryModule.this;
                        contentResolver.unregisterContentObserver(mediaStoreContentObserver2);
                        mediaLibraryModule3.videosObserver = null;
                    }
                }
            });
            moduleDefinitionBuilder.RegisterActivityContracts(new MediaLibraryModule$definition$1$21(this, null));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    private final boolean isMissingPermissions() {
        return hasReadPermissions();
    }

    private final boolean isMissingWritePermission() {
        return hasWritePermissions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String[] getManifestPermissions(boolean writeOnly, List<? extends GranularPermission> granularPermissions) {
        boolean z = Build.VERSION.SDK_INT >= 29 && MediaLibraryUtils.INSTANCE.hasManifestPermission(getContext(), "android.permission.ACCESS_MEDIA_LOCATION") && !(Build.VERSION.SDK_INT >= 33 && granularPermissions.size() == 1 && granularPermissions.contains(GranularPermission.AUDIO));
        boolean z2 = Build.VERSION.SDK_INT < 33 && MediaLibraryUtils.INSTANCE.hasManifestPermission(getContext(), "android.permission.WRITE_EXTERNAL_STORAGE");
        boolean z3 = Build.VERSION.SDK_INT >= 33;
        boolean z4 = z3 && !writeOnly;
        SpreadBuilder spreadBuilder = new SpreadBuilder(4);
        spreadBuilder.add(z2 ? "android.permission.WRITE_EXTERNAL_STORAGE" : null);
        spreadBuilder.add((writeOnly || z3) ? null : "android.permission.READ_EXTERNAL_STORAGE");
        spreadBuilder.add(z ? "android.permission.ACCESS_MEDIA_LOCATION" : null);
        spreadBuilder.addSpread(getGranularPermissions(z4, granularPermissions));
        return (String[]) CollectionsKt.listOfNotNull(spreadBuilder.toArray(new String[spreadBuilder.size()])).toArray(new String[0]);
    }

    private final String[] getGranularPermissions(boolean shouldIncludeGranular, List<? extends GranularPermission> granularPermissions) {
        if (shouldIncludeGranular) {
            assertGranularPermissionIntegrity(getContext(), granularPermissions);
            String[] strArr = new String[3];
            strArr[0] = granularPermissions.contains(GranularPermission.PHOTO) ? "android.permission.READ_MEDIA_IMAGES" : null;
            strArr[1] = granularPermissions.contains(GranularPermission.VIDEO) ? "android.permission.READ_MEDIA_VIDEO" : null;
            strArr[2] = granularPermissions.contains(GranularPermission.AUDIO) ? "android.permission.READ_MEDIA_AUDIO" : null;
            return (String[]) CollectionsKt.listOfNotNull((Object[]) strArr).toArray(new String[0]);
        }
        return new String[0];
    }

    private final void assertGranularPermissionIntegrity(Context context, List<? extends GranularPermission> granularPermissions) {
        for (GranularPermission granularPermission : granularPermissions) {
            if (!MediaLibraryUtils.INSTANCE.hasManifestPermission(context, granularPermission.toManifestPermission())) {
                throw new PermissionsException("You have requested the " + granularPermission + " permission, but it is not declared in AndroidManifest. Update expo-media-library config plugin to include the permission before requesting it.");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<GranularPermission> getManifestDeclaredPermissions(Context context, List<? extends GranularPermission> granularPermissions) {
        if (Build.VERSION.SDK_INT < 33) {
            return granularPermissions;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : granularPermissions) {
            if (MediaLibraryUtils.INSTANCE.hasManifestPermission(context, ((GranularPermission) obj).toManifestPermission())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void requireSystemPermissions$default(MediaLibraryModule mediaLibraryModule, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        mediaLibraryModule.requireSystemPermissions(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requireSystemPermissions(boolean isWritePermissionRequired) {
        if (isWritePermissionRequired ? isMissingWritePermission() : isMissingPermissions()) {
            throw new PermissionsException(isWritePermissionRequired ? MediaLibraryConstantsKt.ERROR_NO_WRITE_PERMISSION_MESSAGE : MediaLibraryConstantsKt.ERROR_NO_PERMISSIONS_MESSAGE);
        }
    }

    private final boolean hasReadPermissions() {
        if (Build.VERSION.SDK_INT >= 33) {
            List<GranularPermission> allowedPermissionsList = getAllowedPermissionsList();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(allowedPermissionsList, 10));
            Iterator<T> it = allowedPermissionsList.iterator();
            while (it.hasNext()) {
                arrayList.add(((GranularPermission) it.next()).toManifestPermission());
            }
            List mutableList = CollectionsKt.toMutableList((Collection) arrayList);
            if (Build.VERSION.SDK_INT >= 34) {
                mutableList.add("android.permission.READ_MEDIA_VISUAL_USER_SELECTED");
            }
            List<String> list = mutableList;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (String str : list) {
                Permissions permissions = getAppContext().getPermissions();
                arrayList2.add(Boolean.valueOf(permissions != null ? permissions.hasGrantedPermissions(str) : false));
            }
            ArrayList arrayList3 = arrayList2;
            if (!(arrayList3 instanceof Collection) || !arrayList3.isEmpty()) {
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    if (((Boolean) it2.next()).booleanValue()) {
                        return false;
                    }
                }
            }
            return true;
        }
        String[] strArr = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        if (getAppContext().getPermissions() != null) {
            return !r4.hasGrantedPermissions((String[]) Arrays.copyOf(strArr, 2));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void maybeThrowIfExpoGo(List<? extends GranularPermission> permissions) {
        if (isExpoGo()) {
            if (permissions.contains(GranularPermission.PHOTO) || permissions.contains(GranularPermission.VIDEO)) {
                throw new PermissionsException("Due to changes in Androids permission requirements, Expo Go can no longer provide full access to the media library. To test the full functionality of this module, you can create a development build");
            }
        }
    }

    private final boolean hasWritePermissions() {
        Permissions permissions;
        if (Build.VERSION.SDK_INT < 33 && (permissions = getAppContext().getPermissions()) != null) {
            return !permissions.hasGrantedPermissions("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x009a, code lost:
    
        if (r9 == r1) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00bc, code lost:
    
        if (r9 == r1) goto L46;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object requestMediaLibraryActionPermission(java.lang.String[] r7, boolean r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof expo.modules.medialibrary.MediaLibraryModule$requestMediaLibraryActionPermission$1
            if (r0 == 0) goto L14
            r0 = r9
            expo.modules.medialibrary.MediaLibraryModule$requestMediaLibraryActionPermission$1 r0 = (expo.modules.medialibrary.MediaLibraryModule$requestMediaLibraryActionPermission$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            expo.modules.medialibrary.MediaLibraryModule$requestMediaLibraryActionPermission$1 r0 = new expo.modules.medialibrary.MediaLibraryModule$requestMediaLibraryActionPermission$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3a
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lbf
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L36:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L9d
        L3a:
            kotlin.ResultKt.throwOnFailure(r9)
            int r9 = android.os.Build.VERSION.SDK_INT
            r2 = 30
            if (r9 >= r2) goto L46
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L46:
            expo.modules.medialibrary.MediaLibraryUtils r9 = expo.modules.medialibrary.MediaLibraryUtils.INSTANCE
            android.content.Context r2 = r6.getContext()
            java.util.List r7 = r9.getAssetsUris(r2, r7)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Iterator r7 = r7.iterator()
        L5d:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L74
            java.lang.Object r2 = r7.next()
            r5 = r2
            android.net.Uri r5 = (android.net.Uri) r5
            boolean r5 = r6.hasWritePermissionForUri(r5)
            if (r5 != 0) goto L5d
            r9.add(r2)
            goto L5d
        L74:
            java.util.List r9 = (java.util.List) r9
            boolean r7 = r9.isEmpty()
            if (r7 == 0) goto L7f
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L7f:
            r7 = 0
            if (r8 == 0) goto La4
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher<expo.modules.medialibrary.contracts.DeleteContractInput, java.lang.Boolean> r8 = r6.deleteLauncher
            if (r8 != 0) goto L8c
            java.lang.String r8 = "deleteLauncher"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            goto L8d
        L8c:
            r7 = r8
        L8d:
            expo.modules.medialibrary.contracts.DeleteContractInput r8 = new expo.modules.medialibrary.contracts.DeleteContractInput
            r8.<init>(r9)
            java.io.Serializable r8 = (java.io.Serializable) r8
            r0.label = r4
            java.lang.Object r9 = r7.launch(r8, r0)
            if (r9 != r1) goto L9d
            goto Lbe
        L9d:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r7 = r9.booleanValue()
            goto Lc5
        La4:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher<expo.modules.medialibrary.contracts.WriteContractInput, java.lang.Boolean> r8 = r6.writeLauncher
            if (r8 != 0) goto Lae
            java.lang.String r8 = "writeLauncher"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            goto Laf
        Lae:
            r7 = r8
        Laf:
            expo.modules.medialibrary.contracts.WriteContractInput r8 = new expo.modules.medialibrary.contracts.WriteContractInput
            r8.<init>(r9)
            java.io.Serializable r8 = (java.io.Serializable) r8
            r0.label = r3
            java.lang.Object r9 = r7.launch(r8, r0)
            if (r9 != r1) goto Lbf
        Lbe:
            return r1
        Lbf:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r7 = r9.booleanValue()
        Lc5:
            if (r7 == 0) goto Lca
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        Lca:
            expo.modules.medialibrary.PermissionsException r7 = new expo.modules.medialibrary.PermissionsException
            java.lang.String r8 = "User didn't grant write permission to requested files."
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.MediaLibraryModule.requestMediaLibraryActionPermission(java.lang.String[], boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object requestMediaLibraryActionPermission$default(MediaLibraryModule mediaLibraryModule, String[] strArr, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return mediaLibraryModule.requestMediaLibraryActionPermission(strArr, z, continuation);
    }

    private final boolean hasWritePermissionForUri(Uri uri) {
        return getContext().checkUriPermission(uri, Binder.getCallingPid(), Binder.getCallingUid(), 2) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MediaLibraryModule.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lexpo/modules/medialibrary/MediaLibraryModule$MediaStoreContentObserver;", "Landroid/database/ContentObserver;", "handler", "Landroid/os/Handler;", "mMediaType", "", "<init>", "(Lexpo/modules/medialibrary/MediaLibraryModule;Landroid/os/Handler;I)V", "mAssetsTotalCount", "onChange", "", "selfChange", "", "uri", "Landroid/net/Uri;", "getAssetsTotalCount", "mediaType", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class MediaStoreContentObserver extends ContentObserver {
        private int mAssetsTotalCount;
        private final int mMediaType;
        final /* synthetic */ MediaLibraryModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MediaStoreContentObserver(MediaLibraryModule mediaLibraryModule, Handler handler, int i) {
            super(handler);
            Intrinsics.checkNotNullParameter(handler, "handler");
            this.this$0 = mediaLibraryModule;
            this.mMediaType = i;
            this.mAssetsTotalCount = getAssetsTotalCount(i);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            int assetsTotalCount = getAssetsTotalCount(this.mMediaType);
            if (this.mAssetsTotalCount != assetsTotalCount) {
                this.mAssetsTotalCount = assetsTotalCount;
                this.this$0.sendEvent(MediaLibraryConstantsKt.LIBRARY_DID_CHANGE_EVENT, new Bundle());
            }
        }

        private final int getAssetsTotalCount(int mediaType) {
            Cursor query = this.this$0.getContext().getContentResolver().query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), new String[0], "media_type == " + mediaType, null, null);
            try {
                Cursor cursor = query;
                int count = cursor != null ? cursor.getCount() : 0;
                CloseableKt.closeFinally(query, null);
                return count;
            } finally {
            }
        }
    }
}
