package expo.modules.filesystem;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tracing.Trace;
import expo.modules.interfaces.filesystem.FilePermissionModuleInterface;
import expo.modules.interfaces.filesystem.Permission;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.classcomponent.ClassComponentBuilder;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.functions.SuspendFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.functions.UntypedAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ConstantComponentBuilder;
import expo.modules.kotlin.objects.PropertyComponentBuilder;
import expo.modules.kotlin.objects.PropertyComponentBuilderWithThis;
import expo.modules.kotlin.typedarray.TypedArray;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.Either;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.types.ReturnType;
import expo.modules.kotlin.types.ReturnTypeProvider;
import expo.modules.kotlin.types.TypeConverterProvider;
import java.io.File;
import java.net.URI;
import java.util.EnumSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: FileSystemModule.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0017R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lexpo/modules/filesystem/FileSystemModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FileSystemModule extends Module {
    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.AppContextLost();
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        String str;
        String str2;
        String str3;
        String str4;
        ModuleDefinitionBuilder moduleDefinitionBuilder;
        ClassComponentBuilder classComponentBuilder;
        ModuleDefinitionBuilder moduleDefinitionBuilder2;
        Object obj;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        ClassComponentBuilder classComponentBuilder2;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent2;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        ClassComponentBuilder classComponentBuilder3;
        ClassComponentBuilder classComponentBuilder4;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent3;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3;
        ClassComponentBuilder classComponentBuilder5;
        ClassComponentBuilder classComponentBuilder6;
        String str5;
        String str6;
        String str7;
        ModuleDefinitionBuilder moduleDefinitionBuilder3;
        ClassComponentBuilder classComponentBuilder7;
        FileSystemModule fileSystemModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (fileSystemModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = new ModuleDefinitionBuilder(fileSystemModule);
            moduleDefinitionBuilder4.Name("FileSystem");
            ConstantComponentBuilder constantComponentBuilder = new ConstantComponentBuilder("documentDirectory");
            constantComponentBuilder.setGetter(new Function0<String>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Constant$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    Context context;
                    context = FileSystemModule.this.getContext();
                    return Uri.fromFile(context.getFilesDir()) + "/";
                }
            });
            moduleDefinitionBuilder4.getConstants().put("documentDirectory", constantComponentBuilder);
            ConstantComponentBuilder constantComponentBuilder2 = new ConstantComponentBuilder("cacheDirectory");
            constantComponentBuilder2.setGetter(new Function0<String>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Constant$2
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    Context context;
                    context = FileSystemModule.this.getContext();
                    return Uri.fromFile(context.getCacheDir()) + "/";
                }
            });
            moduleDefinitionBuilder4.getConstants().put("cacheDirectory", constantComponentBuilder2);
            ConstantComponentBuilder constantComponentBuilder3 = new ConstantComponentBuilder("bundleDirectory");
            constantComponentBuilder3.setGetter(new Function0<String>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Constant$3
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "asset://";
                }
            });
            moduleDefinitionBuilder4.getConstants().put("bundleDirectory", constantComponentBuilder3);
            ModuleDefinitionBuilder moduleDefinitionBuilder5 = moduleDefinitionBuilder4;
            PropertyComponentBuilder propertyComponentBuilder = new PropertyComponentBuilder("totalDiskSpace");
            AnyType[] anyTypeArr = new AnyType[0];
            ReturnTypeProvider returnTypeProvider = ReturnTypeProvider.INSTANCE;
            ReturnType returnType = returnTypeProvider.getTypes().get(Reflection.getOrCreateKotlinClass(Long.class));
            if (returnType == null) {
                returnType = new ReturnType(Reflection.getOrCreateKotlinClass(Long.class));
                str = "create";
                returnTypeProvider.getTypes().put(Reflection.getOrCreateKotlinClass(Long.class), returnType);
            } else {
                str = "create";
            }
            propertyComponentBuilder.setGetter(new SyncFunctionComponent("get", anyTypeArr, returnType, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Property$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Context context;
                    Intrinsics.checkNotNullParameter(it, "it");
                    context = FileSystemModule.this.getContext();
                    return Long.valueOf(new File(context.getFilesDir().getPath()).getTotalSpace());
                }
            }));
            moduleDefinitionBuilder5.getProperties().put("totalDiskSpace", propertyComponentBuilder);
            ModuleDefinitionBuilder moduleDefinitionBuilder6 = moduleDefinitionBuilder4;
            PropertyComponentBuilder propertyComponentBuilder2 = new PropertyComponentBuilder("availableDiskSpace");
            AnyType[] anyTypeArr2 = new AnyType[0];
            ReturnTypeProvider returnTypeProvider2 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType2 = returnTypeProvider2.getTypes().get(Reflection.getOrCreateKotlinClass(Long.class));
            if (returnType2 == null) {
                returnType2 = new ReturnType(Reflection.getOrCreateKotlinClass(Long.class));
                returnTypeProvider2.getTypes().put(Reflection.getOrCreateKotlinClass(Long.class), returnType2);
            }
            propertyComponentBuilder2.setGetter(new SyncFunctionComponent("get", anyTypeArr2, returnType2, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Property$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Context context;
                    Intrinsics.checkNotNullParameter(it, "it");
                    context = FileSystemModule.this.getContext();
                    return Long.valueOf(new File(context.getFilesDir().getPath()).getFreeSpace());
                }
            }));
            moduleDefinitionBuilder6.getProperties().put("availableDiskSpace", propertyComponentBuilder2);
            AsyncFunctionBuilder AsyncFunction = moduleDefinitionBuilder4.AsyncFunction("downloadFileAsync");
            String name = AsyncFunction.getName();
            TypeConverterProvider converters = AsyncFunction.getConverters();
            AnyType[] anyTypeArr3 = new AnyType[3];
            AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(URI.class), false));
            if (anyType == null) {
                str2 = "validatePath";
                str3 = "delete";
                anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(URI.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Coroutine$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(URI.class);
                    }
                }), converters);
            } else {
                str2 = "validatePath";
                str3 = "delete";
            }
            anyTypeArr3[0] = anyType;
            AnyType anyType2 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false));
            if (anyType2 == null) {
                anyType2 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Coroutine$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemPath.class);
                    }
                }), converters);
            }
            anyTypeArr3[1] = anyType2;
            AnyType anyType3 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(DownloadOptions.class), true));
            if (anyType3 == null) {
                anyType3 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DownloadOptions.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Coroutine$3
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(DownloadOptions.class);
                    }
                }), converters);
            }
            anyTypeArr3[2] = anyType3;
            AsyncFunction.setAsyncFunctionComponent(new SuspendFunctionComponent(name, anyTypeArr3, new FileSystemModule$definition$lambda$62$$inlined$Coroutine$4(null)));
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            moduleDefinitionBuilder4.RegisterActivityContracts(new FileSystemModule$definition$1$7(objectRef, this, null));
            AsyncFunctionBuilder AsyncFunction2 = moduleDefinitionBuilder4.AsyncFunction("pickDirectoryAsync");
            String name2 = AsyncFunction2.getName();
            TypeConverterProvider converters2 = AsyncFunction2.getConverters();
            AnyType[] anyTypeArr4 = new AnyType[1];
            AnyType anyType4 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Uri.class), true));
            if (anyType4 == null) {
                str4 = "constructor";
                anyType4 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Uri.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Coroutine$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(Uri.class);
                    }
                }), converters2);
            } else {
                str4 = "constructor";
            }
            anyTypeArr4[0] = anyType4;
            AsyncFunction2.setAsyncFunctionComponent(new SuspendFunctionComponent(name2, anyTypeArr4, new FileSystemModule$definition$lambda$62$$inlined$Coroutine$6(null, objectRef)));
            AsyncFunctionBuilder AsyncFunction3 = moduleDefinitionBuilder4.AsyncFunction("pickFileAsync");
            String name3 = AsyncFunction3.getName();
            TypeConverterProvider converters3 = AsyncFunction3.getConverters();
            AnyType[] anyTypeArr5 = new AnyType[2];
            AnyType anyType5 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Uri.class), true));
            if (anyType5 == null) {
                anyType5 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Uri.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Coroutine$7
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(Uri.class);
                    }
                }), converters3);
            }
            anyTypeArr5[0] = anyType5;
            AnyType anyType6 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType6 == null) {
                anyType6 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Coroutine$8
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters3);
            }
            anyTypeArr5[1] = anyType6;
            AsyncFunction3.setAsyncFunctionComponent(new SuspendFunctionComponent(name3, anyTypeArr5, new FileSystemModule$definition$lambda$62$$inlined$Coroutine$9(null, objectRef)));
            ModuleDefinitionBuilder moduleDefinitionBuilder7 = moduleDefinitionBuilder4;
            TypeConverterProvider converters4 = moduleDefinitionBuilder7.getConverters();
            AnyType[] anyTypeArr6 = new AnyType[1];
            AnyType anyType7 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(URI.class), false));
            if (anyType7 == null) {
                anyType7 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(URI.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Function$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(URI.class);
                    }
                }), converters4);
            }
            anyTypeArr6[0] = anyType7;
            ReturnTypeProvider returnTypeProvider3 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType3 = returnTypeProvider3.getTypes().get(Reflection.getOrCreateKotlinClass(PathInfo.class));
            if (returnType3 == null) {
                returnType3 = new ReturnType(Reflection.getOrCreateKotlinClass(PathInfo.class));
                returnTypeProvider3.getTypes().put(Reflection.getOrCreateKotlinClass(PathInfo.class), returnType3);
            }
            moduleDefinitionBuilder7.getSyncFunctions().put("info", new SyncFunctionComponent("info", anyTypeArr6, returnType3, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Function$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    EnumSet<Permission> noneOf;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    File file = new File((URI) objArr[0]);
                    FilePermissionModuleInterface filePermission = FileSystemModule.this.getAppContext().getFilePermission();
                    if (filePermission == null || (noneOf = filePermission.getPathPermissions(FileSystemModule.this.getAppContext().getReactContext(), file.getPath())) == null) {
                        noneOf = EnumSet.noneOf(Permission.class);
                    }
                    if (noneOf.contains(Permission.READ) && file.exists()) {
                        return new PathInfo(file.exists(), Boolean.valueOf(file.isDirectory()));
                    }
                    return new PathInfo(false, null);
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder8 = moduleDefinitionBuilder4;
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FileSystemFile.class);
            Module module = moduleDefinitionBuilder8.getModule();
            if (module == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            AppContext appContext = module.getAppContext();
            String simpleName = JvmClassMappingKt.getJavaClass(orCreateKotlinClass).getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
            AnyType anyType8 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            ClassComponentBuilder classComponentBuilder8 = new ClassComponentBuilder(appContext, simpleName, orCreateKotlinClass, anyType8 == null ? new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Class$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(FileSystemFile.class);
                }
            }), null) : anyType8, moduleDefinitionBuilder8.getConverters());
            TypeConverterProvider converters5 = classComponentBuilder8.getConverters();
            AnyType[] anyTypeArr7 = new AnyType[1];
            AnyType anyType9 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Uri.class), false));
            if (anyType9 == null) {
                anyType9 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Uri.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Constructor$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Uri.class);
                    }
                }), converters5);
            }
            anyTypeArr7[0] = anyType9;
            ReturnTypeProvider returnTypeProvider4 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType4 = returnTypeProvider4.getTypes().get(Reflection.getOrCreateKotlinClass(Object.class));
            if (returnType4 == null) {
                returnType4 = new ReturnType(Reflection.getOrCreateKotlinClass(Object.class));
                returnTypeProvider4.getTypes().put(Reflection.getOrCreateKotlinClass(Object.class), returnType4);
            }
            String str8 = str4;
            classComponentBuilder8.setConstructor(new SyncFunctionComponent(str8, anyTypeArr7, returnType4, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Constructor$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return new FileSystemFile((Uri) objArr[0]);
                }
            }));
            ClassComponentBuilder classComponentBuilder9 = classComponentBuilder8;
            TypeConverterProvider converters6 = classComponentBuilder9.getConverters();
            AnyType[] anyTypeArr8 = new AnyType[1];
            AnyType anyType10 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType10 == null) {
                moduleDefinitionBuilder = moduleDefinitionBuilder4;
                anyType10 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters6);
            } else {
                moduleDefinitionBuilder = moduleDefinitionBuilder4;
            }
            anyTypeArr8[0] = anyType10;
            ReturnTypeProvider returnTypeProvider5 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType5 = returnTypeProvider5.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType5 == null) {
                returnType5 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider5.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType5);
            }
            String str9 = str3;
            classComponentBuilder9.getSyncFunctions().put(str9, new SyncFunctionComponent(str9, anyTypeArr8, returnType5, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemFile) objArr[0]).delete();
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder10 = classComponentBuilder8;
            TypeConverterProvider converters7 = classComponentBuilder10.getConverters();
            AnyType[] anyTypeArr9 = new AnyType[1];
            AnyType anyType11 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType11 == null) {
                classComponentBuilder = classComponentBuilder10;
                anyType11 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$3
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters7);
            } else {
                classComponentBuilder = classComponentBuilder10;
            }
            anyTypeArr9[0] = anyType11;
            ReturnTypeProvider returnTypeProvider6 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType6 = returnTypeProvider6.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType6 == null) {
                returnType6 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider6.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType6);
            }
            String str10 = str2;
            classComponentBuilder.getSyncFunctions().put(str10, new SyncFunctionComponent(str10, anyTypeArr9, returnType6, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemFile) objArr[0]).validatePath();
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder11 = classComponentBuilder8;
            TypeConverterProvider converters8 = classComponentBuilder11.getConverters();
            AnyType[] anyTypeArr10 = new AnyType[2];
            AnyType anyType12 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType12 == null) {
                moduleDefinitionBuilder2 = moduleDefinitionBuilder8;
                anyType12 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters8);
            } else {
                moduleDefinitionBuilder2 = moduleDefinitionBuilder8;
            }
            anyTypeArr10[0] = anyType12;
            AnyType anyType13 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(CreateOptions.class), true));
            if (anyType13 == null) {
                anyType13 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(CreateOptions.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$6
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(CreateOptions.class);
                    }
                }), converters8);
            }
            anyTypeArr10[1] = anyType13;
            ReturnTypeProvider returnTypeProvider7 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType7 = returnTypeProvider7.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType7 == null) {
                returnType7 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider7.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType7);
            }
            String str11 = str;
            classComponentBuilder11.getSyncFunctions().put(str11, new SyncFunctionComponent(str11, anyTypeArr10, returnType7, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj2 = objArr[0];
                    CreateOptions createOptions = (CreateOptions) objArr[1];
                    FileSystemFile fileSystemFile = (FileSystemFile) obj2;
                    if (createOptions == null) {
                        createOptions = new CreateOptions(false, false, false, 7, null);
                    }
                    fileSystemFile.create(createOptions);
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder12 = classComponentBuilder8;
            TypeConverterProvider converters9 = classComponentBuilder12.getConverters();
            AnyType[] anyTypeArr11 = new AnyType[3];
            AnyType anyType14 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType14 == null) {
                obj = "Required value was null.";
                anyType14 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$8
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters9);
            } else {
                obj = "Required value was null.";
            }
            anyTypeArr11[0] = anyType14;
            AnyType anyType15 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Either.class), false));
            if (anyType15 == null) {
                anyType15 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Either.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$9
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Either.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.typeOf(TypedArray.class)));
                    }
                }), converters9);
            }
            anyTypeArr11[1] = anyType15;
            AnyType anyType16 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(WriteOptions.class), true));
            if (anyType16 == null) {
                anyType16 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(WriteOptions.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$10
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(WriteOptions.class);
                    }
                }), converters9);
            }
            anyTypeArr11[2] = anyType16;
            ReturnTypeProvider returnTypeProvider8 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType8 = returnTypeProvider8.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType8 == null) {
                returnType8 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider8.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType8);
            }
            classComponentBuilder12.getSyncFunctions().put("write", new SyncFunctionComponent("write", anyTypeArr11, returnType8, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj2 = objArr[0];
                    Object obj3 = objArr[1];
                    WriteOptions writeOptions = (WriteOptions) objArr[2];
                    Either either = (Either) obj3;
                    FileSystemFile fileSystemFile = (FileSystemFile) obj2;
                    if (either.isFirstType(Reflection.getOrCreateKotlinClass(String.class))) {
                        String str12 = (String) either.getFirstType(Reflection.getOrCreateKotlinClass(String.class));
                        if ((writeOptions != null ? writeOptions.getEncoding() : null) == EncodingType.BASE64) {
                            byte[] decode = Base64.decode(str12, 0);
                            Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
                            fileSystemFile.write(decode);
                        } else {
                            fileSystemFile.write(str12);
                        }
                    }
                    if (either.isSecondType(Reflection.getOrCreateKotlinClass(TypedArray.class))) {
                        fileSystemFile.write((TypedArray) either.getSecondType(Reflection.getOrCreateKotlinClass(TypedArray.class)));
                    }
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder13 = classComponentBuilder8;
            if (Intrinsics.areEqual(FileSystemFile.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("text", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$AsyncFunction$1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        ((FileSystemFile) promise).text();
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters10 = classComponentBuilder13.getConverters();
                AnyType[] anyTypeArr12 = new AnyType[1];
                AnyType anyType17 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
                if (anyType17 == null) {
                    anyType17 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$AsyncFunction$2
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(FileSystemFile.class);
                        }
                    }), converters10);
                }
                anyTypeArr12[0] = anyType17;
                Function1<Object[], String> function1 = new Function1<Object[], String>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$AsyncFunction$3
                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        return ((FileSystemFile) objArr[0]).text();
                    }
                };
                if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(String.class, String.class)) {
                                    untypedAsyncFunctionComponent = new StringAsyncFunctionComponent("text", anyTypeArr12, function1);
                                } else {
                                    untypedAsyncFunctionComponent = new UntypedAsyncFunctionComponent("text", anyTypeArr12, function1);
                                }
                            } else {
                                untypedAsyncFunctionComponent = new FloatAsyncFunctionComponent("text", anyTypeArr12, function1);
                            }
                        } else {
                            untypedAsyncFunctionComponent = new DoubleAsyncFunctionComponent("text", anyTypeArr12, function1);
                        }
                    } else {
                        untypedAsyncFunctionComponent = new BoolAsyncFunctionComponent("text", anyTypeArr12, function1);
                    }
                } else {
                    untypedAsyncFunctionComponent = new IntAsyncFunctionComponent("text", anyTypeArr12, function1);
                }
                asyncFunctionWithPromiseComponent = untypedAsyncFunctionComponent;
            }
            classComponentBuilder13.getAsyncFunctions().put("text", asyncFunctionWithPromiseComponent);
            ClassComponentBuilder classComponentBuilder14 = classComponentBuilder8;
            TypeConverterProvider converters11 = classComponentBuilder14.getConverters();
            AnyType[] anyTypeArr13 = new AnyType[1];
            AnyType anyType18 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType18 == null) {
                classComponentBuilder2 = classComponentBuilder14;
                anyType18 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$12
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters11);
            } else {
                classComponentBuilder2 = classComponentBuilder14;
            }
            anyTypeArr13[0] = anyType18;
            ReturnTypeProvider returnTypeProvider9 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType9 = returnTypeProvider9.getTypes().get(Reflection.getOrCreateKotlinClass(String.class));
            if (returnType9 == null) {
                returnType9 = new ReturnType(Reflection.getOrCreateKotlinClass(String.class));
                returnTypeProvider9.getTypes().put(Reflection.getOrCreateKotlinClass(String.class), returnType9);
            }
            classComponentBuilder2.getSyncFunctions().put("textSync", new SyncFunctionComponent("textSync", anyTypeArr13, returnType9, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$13
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return ((FileSystemFile) objArr[0]).text();
                }
            }));
            ClassComponentBuilder classComponentBuilder15 = classComponentBuilder8;
            if (Intrinsics.areEqual(FileSystemFile.class, Promise.class)) {
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("base64", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$AsyncFunction$4
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        ((FileSystemFile) promise).base64();
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters12 = classComponentBuilder15.getConverters();
                AnyType[] anyTypeArr14 = new AnyType[1];
                AnyType anyType19 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
                if (anyType19 == null) {
                    anyType19 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$AsyncFunction$5
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(FileSystemFile.class);
                        }
                    }), converters12);
                }
                anyTypeArr14[0] = anyType19;
                Function1<Object[], String> function12 = new Function1<Object[], String>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$AsyncFunction$6
                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        return ((FileSystemFile) objArr[0]).base64();
                    }
                };
                if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(String.class, String.class)) {
                                    untypedAsyncFunctionComponent2 = new StringAsyncFunctionComponent("base64", anyTypeArr14, function12);
                                } else {
                                    untypedAsyncFunctionComponent2 = new UntypedAsyncFunctionComponent("base64", anyTypeArr14, function12);
                                }
                            } else {
                                untypedAsyncFunctionComponent2 = new FloatAsyncFunctionComponent("base64", anyTypeArr14, function12);
                            }
                        } else {
                            untypedAsyncFunctionComponent2 = new DoubleAsyncFunctionComponent("base64", anyTypeArr14, function12);
                        }
                    } else {
                        untypedAsyncFunctionComponent2 = new BoolAsyncFunctionComponent("base64", anyTypeArr14, function12);
                    }
                } else {
                    untypedAsyncFunctionComponent2 = new IntAsyncFunctionComponent("base64", anyTypeArr14, function12);
                }
                asyncFunctionWithPromiseComponent2 = untypedAsyncFunctionComponent2;
            }
            classComponentBuilder15.getAsyncFunctions().put("base64", asyncFunctionWithPromiseComponent2);
            ClassComponentBuilder classComponentBuilder16 = classComponentBuilder8;
            TypeConverterProvider converters13 = classComponentBuilder16.getConverters();
            AnyType[] anyTypeArr15 = new AnyType[1];
            AnyType anyType20 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType20 == null) {
                classComponentBuilder3 = classComponentBuilder16;
                anyType20 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$14
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters13);
            } else {
                classComponentBuilder3 = classComponentBuilder16;
            }
            anyTypeArr15[0] = anyType20;
            ReturnTypeProvider returnTypeProvider10 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType10 = returnTypeProvider10.getTypes().get(Reflection.getOrCreateKotlinClass(String.class));
            if (returnType10 == null) {
                returnType10 = new ReturnType(Reflection.getOrCreateKotlinClass(String.class));
                returnTypeProvider10.getTypes().put(Reflection.getOrCreateKotlinClass(String.class), returnType10);
            }
            classComponentBuilder3.getSyncFunctions().put("base64Sync", new SyncFunctionComponent("base64Sync", anyTypeArr15, returnType10, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$15
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return ((FileSystemFile) objArr[0]).base64();
                }
            }));
            ClassComponentBuilder classComponentBuilder17 = classComponentBuilder8;
            if (Intrinsics.areEqual(FileSystemFile.class, Promise.class)) {
                asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("bytes", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$AsyncFunction$7
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        ((FileSystemFile) promise).bytes();
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
                classComponentBuilder4 = classComponentBuilder17;
            } else {
                TypeConverterProvider converters14 = classComponentBuilder17.getConverters();
                AnyType[] anyTypeArr16 = new AnyType[1];
                AnyType anyType21 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
                if (anyType21 == null) {
                    classComponentBuilder4 = classComponentBuilder17;
                    anyType21 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$AsyncFunction$8
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(FileSystemFile.class);
                        }
                    }), converters14);
                } else {
                    classComponentBuilder4 = classComponentBuilder17;
                }
                anyTypeArr16[0] = anyType21;
                Function1<Object[], byte[]> function13 = new Function1<Object[], byte[]>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$AsyncFunction$9
                    @Override // kotlin.jvm.functions.Function1
                    public final byte[] invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        return ((FileSystemFile) objArr[0]).bytes();
                    }
                };
                if (Intrinsics.areEqual(byte[].class, Integer.TYPE)) {
                    untypedAsyncFunctionComponent3 = new IntAsyncFunctionComponent("bytes", anyTypeArr16, function13);
                } else if (Intrinsics.areEqual(byte[].class, Boolean.TYPE)) {
                    untypedAsyncFunctionComponent3 = new BoolAsyncFunctionComponent("bytes", anyTypeArr16, function13);
                } else if (Intrinsics.areEqual(byte[].class, Double.TYPE)) {
                    untypedAsyncFunctionComponent3 = new DoubleAsyncFunctionComponent("bytes", anyTypeArr16, function13);
                } else if (Intrinsics.areEqual(byte[].class, Float.TYPE)) {
                    untypedAsyncFunctionComponent3 = new FloatAsyncFunctionComponent("bytes", anyTypeArr16, function13);
                } else if (Intrinsics.areEqual(byte[].class, String.class)) {
                    untypedAsyncFunctionComponent3 = new StringAsyncFunctionComponent("bytes", anyTypeArr16, function13);
                } else {
                    untypedAsyncFunctionComponent3 = new UntypedAsyncFunctionComponent("bytes", anyTypeArr16, function13);
                }
                asyncFunctionWithPromiseComponent3 = untypedAsyncFunctionComponent3;
            }
            classComponentBuilder4.getAsyncFunctions().put("bytes", asyncFunctionWithPromiseComponent3);
            ClassComponentBuilder classComponentBuilder18 = classComponentBuilder8;
            TypeConverterProvider converters15 = classComponentBuilder18.getConverters();
            AnyType[] anyTypeArr17 = new AnyType[1];
            AnyType anyType22 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType22 == null) {
                classComponentBuilder5 = classComponentBuilder18;
                anyType22 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$16
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters15);
            } else {
                classComponentBuilder5 = classComponentBuilder18;
            }
            anyTypeArr17[0] = anyType22;
            ReturnTypeProvider returnTypeProvider11 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType11 = returnTypeProvider11.getTypes().get(Reflection.getOrCreateKotlinClass(byte[].class));
            if (returnType11 == null) {
                returnType11 = new ReturnType(Reflection.getOrCreateKotlinClass(byte[].class));
                returnTypeProvider11.getTypes().put(Reflection.getOrCreateKotlinClass(byte[].class), returnType11);
            }
            classComponentBuilder5.getSyncFunctions().put("bytesSync", new SyncFunctionComponent("bytesSync", anyTypeArr17, returnType11, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return ((FileSystemFile) objArr[0]).bytes();
                }
            }));
            ClassComponentBuilder classComponentBuilder19 = classComponentBuilder8;
            TypeConverterProvider converters16 = classComponentBuilder19.getConverters();
            AnyType[] anyTypeArr18 = new AnyType[2];
            AnyType anyType23 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType23 == null) {
                anyType23 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$18
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters16);
            }
            anyTypeArr18[0] = anyType23;
            AnyType anyType24 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(InfoOptions.class), true));
            if (anyType24 == null) {
                anyType24 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(InfoOptions.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$19
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(InfoOptions.class);
                    }
                }), converters16);
            }
            anyTypeArr18[1] = anyType24;
            ReturnTypeProvider returnTypeProvider12 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType12 = returnTypeProvider12.getTypes().get(Reflection.getOrCreateKotlinClass(FileInfo.class));
            if (returnType12 == null) {
                returnType12 = new ReturnType(Reflection.getOrCreateKotlinClass(FileInfo.class));
                returnTypeProvider12.getTypes().put(Reflection.getOrCreateKotlinClass(FileInfo.class), returnType12);
            }
            classComponentBuilder19.getSyncFunctions().put("info", new SyncFunctionComponent("info", anyTypeArr18, returnType12, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$20
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return ((FileSystemFile) objArr[0]).info((InfoOptions) objArr[1]);
                }
            }));
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis = new PropertyComponentBuilderWithThis(classComponentBuilder8.getOwnerType().getKType(), "exists");
            AnyType[] anyTypeArr19 = {new AnyType(propertyComponentBuilderWithThis.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider13 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType13 = returnTypeProvider13.getTypes().get(Reflection.getOrCreateKotlinClass(Boolean.class));
            if (returnType13 == null) {
                returnType13 = new ReturnType(Reflection.getOrCreateKotlinClass(Boolean.class));
                returnTypeProvider13.getTypes().put(Reflection.getOrCreateKotlinClass(Boolean.class), returnType13);
            }
            SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent("get", anyTypeArr19, returnType13, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Property$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(((FileSystemFile) it[0]).getExists());
                }
            });
            syncFunctionComponent.setOwnerType(propertyComponentBuilderWithThis.getThisType());
            syncFunctionComponent.setCanTakeOwner(true);
            propertyComponentBuilderWithThis.setGetter(syncFunctionComponent);
            classComponentBuilder8.getProperties().put("exists", propertyComponentBuilderWithThis);
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis2 = new PropertyComponentBuilderWithThis(classComponentBuilder8.getOwnerType().getKType(), "modificationTime");
            AnyType[] anyTypeArr20 = {new AnyType(propertyComponentBuilderWithThis2.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider14 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType14 = returnTypeProvider14.getTypes().get(Reflection.getOrCreateKotlinClass(Long.class));
            if (returnType14 == null) {
                returnType14 = new ReturnType(Reflection.getOrCreateKotlinClass(Long.class));
                returnTypeProvider14.getTypes().put(Reflection.getOrCreateKotlinClass(Long.class), returnType14);
            }
            SyncFunctionComponent syncFunctionComponent2 = new SyncFunctionComponent("get", anyTypeArr20, returnType14, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Property$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ((FileSystemFile) it[0]).getModificationTime();
                }
            });
            syncFunctionComponent2.setOwnerType(propertyComponentBuilderWithThis2.getThisType());
            syncFunctionComponent2.setCanTakeOwner(true);
            propertyComponentBuilderWithThis2.setGetter(syncFunctionComponent2);
            classComponentBuilder8.getProperties().put("modificationTime", propertyComponentBuilderWithThis2);
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis3 = new PropertyComponentBuilderWithThis(classComponentBuilder8.getOwnerType().getKType(), "creationTime");
            AnyType[] anyTypeArr21 = {new AnyType(propertyComponentBuilderWithThis3.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider15 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType15 = returnTypeProvider15.getTypes().get(Reflection.getOrCreateKotlinClass(Long.class));
            if (returnType15 == null) {
                returnType15 = new ReturnType(Reflection.getOrCreateKotlinClass(Long.class));
                returnTypeProvider15.getTypes().put(Reflection.getOrCreateKotlinClass(Long.class), returnType15);
            }
            SyncFunctionComponent syncFunctionComponent3 = new SyncFunctionComponent("get", anyTypeArr21, returnType15, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Property$3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ((FileSystemFile) it[0]).getCreationTime();
                }
            });
            syncFunctionComponent3.setOwnerType(propertyComponentBuilderWithThis3.getThisType());
            syncFunctionComponent3.setCanTakeOwner(true);
            propertyComponentBuilderWithThis3.setGetter(syncFunctionComponent3);
            classComponentBuilder8.getProperties().put("creationTime", propertyComponentBuilderWithThis3);
            ClassComponentBuilder classComponentBuilder20 = classComponentBuilder8;
            TypeConverterProvider converters17 = classComponentBuilder20.getConverters();
            AnyType[] anyTypeArr22 = new AnyType[2];
            AnyType anyType25 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType25 == null) {
                classComponentBuilder6 = classComponentBuilder20;
                anyType25 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$21
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters17);
            } else {
                classComponentBuilder6 = classComponentBuilder20;
            }
            anyTypeArr22[0] = anyType25;
            AnyType anyType26 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false));
            if (anyType26 == null) {
                anyType26 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$22
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemPath.class);
                    }
                }), converters17);
            }
            anyTypeArr22[1] = anyType26;
            ReturnTypeProvider returnTypeProvider16 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType16 = returnTypeProvider16.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType16 == null) {
                returnType16 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider16.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType16);
            }
            classComponentBuilder6.getSyncFunctions().put("copy", new SyncFunctionComponent("copy", anyTypeArr22, returnType16, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$23
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemFile) objArr[0]).copy((FileSystemPath) objArr[1]);
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder21 = classComponentBuilder8;
            TypeConverterProvider converters18 = classComponentBuilder21.getConverters();
            AnyType[] anyTypeArr23 = new AnyType[2];
            AnyType anyType27 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType27 == null) {
                str5 = "copy";
                anyType27 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$24
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters18);
            } else {
                str5 = "copy";
            }
            anyTypeArr23[0] = anyType27;
            AnyType anyType28 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false));
            if (anyType28 == null) {
                anyType28 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$25
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemPath.class);
                    }
                }), converters18);
            }
            anyTypeArr23[1] = anyType28;
            ReturnTypeProvider returnTypeProvider17 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType17 = returnTypeProvider17.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType17 == null) {
                returnType17 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider17.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType17);
            }
            classComponentBuilder21.getSyncFunctions().put("move", new SyncFunctionComponent("move", anyTypeArr23, returnType17, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$26
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemFile) objArr[0]).move((FileSystemPath) objArr[1]);
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder22 = classComponentBuilder8;
            TypeConverterProvider converters19 = classComponentBuilder22.getConverters();
            AnyType[] anyTypeArr24 = new AnyType[2];
            AnyType anyType29 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType29 == null) {
                str6 = "move";
                anyType29 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$27
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters19);
            } else {
                str6 = "move";
            }
            anyTypeArr24[0] = anyType29;
            AnyType anyType30 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType30 == null) {
                anyType30 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$28
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters19);
            }
            anyTypeArr24[1] = anyType30;
            ReturnTypeProvider returnTypeProvider18 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType18 = returnTypeProvider18.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType18 == null) {
                returnType18 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider18.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType18);
            }
            classComponentBuilder22.getSyncFunctions().put("rename", new SyncFunctionComponent("rename", anyTypeArr24, returnType18, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$29
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemFile) objArr[0]).rename((String) objArr[1]);
                    return Unit.INSTANCE;
                }
            }));
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis4 = new PropertyComponentBuilderWithThis(classComponentBuilder8.getOwnerType().getKType(), "uri");
            AnyType[] anyTypeArr25 = {new AnyType(propertyComponentBuilderWithThis4.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider19 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType19 = returnTypeProvider19.getTypes().get(Reflection.getOrCreateKotlinClass(String.class));
            if (returnType19 == null) {
                returnType19 = new ReturnType(Reflection.getOrCreateKotlinClass(String.class));
                returnTypeProvider19.getTypes().put(Reflection.getOrCreateKotlinClass(String.class), returnType19);
            }
            SyncFunctionComponent syncFunctionComponent4 = new SyncFunctionComponent("get", anyTypeArr25, returnType19, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Property$4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ((FileSystemFile) it[0]).asString();
                }
            });
            syncFunctionComponent4.setOwnerType(propertyComponentBuilderWithThis4.getThisType());
            syncFunctionComponent4.setCanTakeOwner(true);
            propertyComponentBuilderWithThis4.setGetter(syncFunctionComponent4);
            classComponentBuilder8.getProperties().put("uri", propertyComponentBuilderWithThis4);
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis5 = new PropertyComponentBuilderWithThis(classComponentBuilder8.getOwnerType().getKType(), "contentUri");
            AnyType[] anyTypeArr26 = {new AnyType(propertyComponentBuilderWithThis5.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider20 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType20 = returnTypeProvider20.getTypes().get(Reflection.getOrCreateKotlinClass(Uri.class));
            if (returnType20 == null) {
                returnType20 = new ReturnType(Reflection.getOrCreateKotlinClass(Uri.class));
                returnTypeProvider20.getTypes().put(Reflection.getOrCreateKotlinClass(Uri.class), returnType20);
            }
            SyncFunctionComponent syncFunctionComponent5 = new SyncFunctionComponent("get", anyTypeArr26, returnType20, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Property$5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ((FileSystemFile) it[0]).asContentUri();
                }
            });
            syncFunctionComponent5.setOwnerType(propertyComponentBuilderWithThis5.getThisType());
            syncFunctionComponent5.setCanTakeOwner(true);
            propertyComponentBuilderWithThis5.setGetter(syncFunctionComponent5);
            classComponentBuilder8.getProperties().put("contentUri", propertyComponentBuilderWithThis5);
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis6 = new PropertyComponentBuilderWithThis(classComponentBuilder8.getOwnerType().getKType(), "md5");
            AnyType[] anyTypeArr27 = {new AnyType(propertyComponentBuilderWithThis6.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider21 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType21 = returnTypeProvider21.getTypes().get(Reflection.getOrCreateKotlinClass(String.class));
            if (returnType21 == null) {
                returnType21 = new ReturnType(Reflection.getOrCreateKotlinClass(String.class));
                returnTypeProvider21.getTypes().put(Reflection.getOrCreateKotlinClass(String.class), returnType21);
            }
            SyncFunctionComponent syncFunctionComponent6 = new SyncFunctionComponent("get", anyTypeArr27, returnType21, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Property$6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    try {
                        return ((FileSystemFile) it[0]).getMd5();
                    } catch (Exception unused) {
                        return null;
                    }
                }
            });
            syncFunctionComponent6.setOwnerType(propertyComponentBuilderWithThis6.getThisType());
            syncFunctionComponent6.setCanTakeOwner(true);
            propertyComponentBuilderWithThis6.setGetter(syncFunctionComponent6);
            classComponentBuilder8.getProperties().put("md5", propertyComponentBuilderWithThis6);
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis7 = new PropertyComponentBuilderWithThis(classComponentBuilder8.getOwnerType().getKType(), "size");
            AnyType[] anyTypeArr28 = {new AnyType(propertyComponentBuilderWithThis7.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider22 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType22 = returnTypeProvider22.getTypes().get(Reflection.getOrCreateKotlinClass(Long.class));
            if (returnType22 == null) {
                returnType22 = new ReturnType(Reflection.getOrCreateKotlinClass(Long.class));
                returnTypeProvider22.getTypes().put(Reflection.getOrCreateKotlinClass(Long.class), returnType22);
            }
            SyncFunctionComponent syncFunctionComponent7 = new SyncFunctionComponent("get", anyTypeArr28, returnType22, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Property$7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    try {
                        return ((FileSystemFile) it[0]).getSize();
                    } catch (Exception unused) {
                        return null;
                    }
                }
            });
            syncFunctionComponent7.setOwnerType(propertyComponentBuilderWithThis7.getThisType());
            syncFunctionComponent7.setCanTakeOwner(true);
            propertyComponentBuilderWithThis7.setGetter(syncFunctionComponent7);
            classComponentBuilder8.getProperties().put("size", propertyComponentBuilderWithThis7);
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis8 = new PropertyComponentBuilderWithThis(classComponentBuilder8.getOwnerType().getKType(), "type");
            AnyType[] anyTypeArr29 = {new AnyType(propertyComponentBuilderWithThis8.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider23 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType23 = returnTypeProvider23.getTypes().get(Reflection.getOrCreateKotlinClass(String.class));
            if (returnType23 == null) {
                returnType23 = new ReturnType(Reflection.getOrCreateKotlinClass(String.class));
                returnTypeProvider23.getTypes().put(Reflection.getOrCreateKotlinClass(String.class), returnType23);
            }
            SyncFunctionComponent syncFunctionComponent8 = new SyncFunctionComponent("get", anyTypeArr29, returnType23, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Property$8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ((FileSystemFile) it[0]).getType();
                }
            });
            syncFunctionComponent8.setOwnerType(propertyComponentBuilderWithThis8.getThisType());
            syncFunctionComponent8.setCanTakeOwner(true);
            propertyComponentBuilderWithThis8.setGetter(syncFunctionComponent8);
            classComponentBuilder8.getProperties().put("type", propertyComponentBuilderWithThis8);
            ClassComponentBuilder classComponentBuilder23 = classComponentBuilder8;
            TypeConverterProvider converters20 = classComponentBuilder23.getConverters();
            AnyType[] anyTypeArr30 = new AnyType[1];
            AnyType anyType31 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType31 == null) {
                str7 = str11;
                anyType31 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$30
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters20);
            } else {
                str7 = str11;
            }
            anyTypeArr30[0] = anyType31;
            ReturnTypeProvider returnTypeProvider24 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType24 = returnTypeProvider24.getTypes().get(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class));
            if (returnType24 == null) {
                returnType24 = new ReturnType(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class));
                returnTypeProvider24.getTypes().put(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class), returnType24);
            }
            classComponentBuilder23.getSyncFunctions().put(TtmlNode.TEXT_EMPHASIS_MARK_OPEN, new SyncFunctionComponent(TtmlNode.TEXT_EMPHASIS_MARK_OPEN, anyTypeArr30, returnType24, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$38$$inlined$Function$31
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return new FileSystemFileHandle((FileSystemFile) objArr[0]);
                }
            }));
            moduleDefinitionBuilder2.getClassData().add(classComponentBuilder8.buildClass());
            ModuleDefinitionBuilder moduleDefinitionBuilder9 = moduleDefinitionBuilder;
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class);
            Module module2 = moduleDefinitionBuilder9.getModule();
            if (module2 == null) {
                throw new IllegalArgumentException(obj.toString());
            }
            AppContext appContext2 = module2.getAppContext();
            String simpleName2 = JvmClassMappingKt.getJavaClass(orCreateKotlinClass2).getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName2, "getSimpleName(...)");
            AnyType anyType32 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class), false));
            ClassComponentBuilder classComponentBuilder24 = new ClassComponentBuilder(appContext2, simpleName2, orCreateKotlinClass2, anyType32 == null ? new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Class$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(FileSystemFileHandle.class);
                }
            }), null) : anyType32, moduleDefinitionBuilder9.getConverters());
            TypeConverterProvider converters21 = classComponentBuilder24.getConverters();
            AnyType[] anyTypeArr31 = new AnyType[1];
            AnyType anyType33 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false));
            if (anyType33 == null) {
                moduleDefinitionBuilder3 = moduleDefinitionBuilder9;
                anyType33 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFile.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Constructor$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFile.class);
                    }
                }), converters21);
            } else {
                moduleDefinitionBuilder3 = moduleDefinitionBuilder9;
            }
            anyTypeArr31[0] = anyType33;
            ReturnTypeProvider returnTypeProvider25 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType25 = returnTypeProvider25.getTypes().get(Reflection.getOrCreateKotlinClass(Object.class));
            if (returnType25 == null) {
                returnType25 = new ReturnType(Reflection.getOrCreateKotlinClass(Object.class));
                returnTypeProvider25.getTypes().put(Reflection.getOrCreateKotlinClass(Object.class), returnType25);
            }
            classComponentBuilder24.setConstructor(new SyncFunctionComponent(str8, anyTypeArr31, returnType25, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Constructor$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return new FileSystemFileHandle((FileSystemFile) objArr[0]);
                }
            }));
            ClassComponentBuilder classComponentBuilder25 = classComponentBuilder24;
            TypeConverterProvider converters22 = classComponentBuilder25.getConverters();
            AnyType[] anyTypeArr32 = new AnyType[2];
            AnyType anyType34 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class), false));
            if (anyType34 == null) {
                classComponentBuilder7 = classComponentBuilder24;
                anyType34 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Function$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFileHandle.class);
                    }
                }), converters22);
            } else {
                classComponentBuilder7 = classComponentBuilder24;
            }
            anyTypeArr32[0] = anyType34;
            AnyType anyType35 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Integer.class), false));
            if (anyType35 == null) {
                anyType35 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Function$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Integer.TYPE);
                    }
                }), converters22);
            }
            anyTypeArr32[1] = anyType35;
            ReturnTypeProvider returnTypeProvider26 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType26 = returnTypeProvider26.getTypes().get(Reflection.getOrCreateKotlinClass(byte[].class));
            if (returnType26 == null) {
                returnType26 = new ReturnType(Reflection.getOrCreateKotlinClass(byte[].class));
                returnTypeProvider26.getTypes().put(Reflection.getOrCreateKotlinClass(byte[].class), returnType26);
            }
            classComponentBuilder25.getSyncFunctions().put("readBytes", new SyncFunctionComponent("readBytes", anyTypeArr32, returnType26, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Function$3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return ((FileSystemFileHandle) objArr[0]).read(((Number) objArr[1]).intValue());
                }
            }));
            ClassComponentBuilder classComponentBuilder26 = classComponentBuilder7;
            TypeConverterProvider converters23 = classComponentBuilder26.getConverters();
            AnyType[] anyTypeArr33 = new AnyType[2];
            AnyType anyType36 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class), false));
            if (anyType36 == null) {
                anyType36 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Function$4
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFileHandle.class);
                    }
                }), converters23);
            }
            anyTypeArr33[0] = anyType36;
            AnyType anyType37 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(byte[].class), false));
            if (anyType37 == null) {
                anyType37 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(byte[].class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Function$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(byte[].class);
                    }
                }), converters23);
            }
            anyTypeArr33[1] = anyType37;
            ReturnTypeProvider returnTypeProvider27 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType27 = returnTypeProvider27.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType27 == null) {
                returnType27 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider27.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType27);
            }
            classComponentBuilder26.getSyncFunctions().put("writeBytes", new SyncFunctionComponent("writeBytes", anyTypeArr33, returnType27, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Function$6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemFileHandle) objArr[0]).write((byte[]) objArr[1]);
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder27 = classComponentBuilder7;
            TypeConverterProvider converters24 = classComponentBuilder27.getConverters();
            AnyType[] anyTypeArr34 = new AnyType[1];
            AnyType anyType38 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class), false));
            if (anyType38 == null) {
                anyType38 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemFileHandle.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Function$7
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemFileHandle.class);
                    }
                }), converters24);
            }
            anyTypeArr34[0] = anyType38;
            ReturnTypeProvider returnTypeProvider28 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType28 = returnTypeProvider28.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType28 == null) {
                returnType28 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider28.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType28);
            }
            classComponentBuilder27.getSyncFunctions().put("close", new SyncFunctionComponent("close", anyTypeArr34, returnType28, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Function$8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemFileHandle) objArr[0]).close();
                    return Unit.INSTANCE;
                }
            }));
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis9 = new PropertyComponentBuilderWithThis(classComponentBuilder7.getOwnerType().getKType(), "offset");
            AnyType[] anyTypeArr35 = {new AnyType(propertyComponentBuilderWithThis9.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider29 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType29 = returnTypeProvider29.getTypes().get(Reflection.getOrCreateKotlinClass(Long.class));
            if (returnType29 == null) {
                returnType29 = new ReturnType(Reflection.getOrCreateKotlinClass(Long.class));
                returnTypeProvider29.getTypes().put(Reflection.getOrCreateKotlinClass(Long.class), returnType29);
            }
            SyncFunctionComponent syncFunctionComponent9 = new SyncFunctionComponent("get", anyTypeArr35, returnType29, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Property$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ((FileSystemFileHandle) it[0]).getOffset();
                }
            });
            syncFunctionComponent9.setOwnerType(propertyComponentBuilderWithThis9.getThisType());
            syncFunctionComponent9.setCanTakeOwner(true);
            propertyComponentBuilderWithThis9.setGetter(syncFunctionComponent9);
            classComponentBuilder7.getProperties().put("offset", propertyComponentBuilderWithThis9);
            AnyType[] anyTypeArr36 = new AnyType[2];
            anyTypeArr36[0] = new AnyType(propertyComponentBuilderWithThis9.getThisType(), null, 2, null);
            AnyType anyType39 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Long.class), false));
            if (anyType39 == null) {
                anyType39 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Long.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$set$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Long.TYPE);
                    }
                }), null);
            }
            anyTypeArr36[1] = anyType39;
            ReturnTypeProvider returnTypeProvider30 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType30 = returnTypeProvider30.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType30 == null) {
                returnType30 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider30.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType30);
            }
            SyncFunctionComponent syncFunctionComponent10 = new SyncFunctionComponent("set", anyTypeArr36, returnType30, new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$set$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj2 = it[0];
                    Object obj3 = it[1];
                    if (obj3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    ((FileSystemFileHandle) obj2).setOffset(Long.valueOf(((Long) obj3).longValue()));
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr) {
                    invoke2(objArr);
                    return Unit.INSTANCE;
                }
            });
            syncFunctionComponent10.setOwnerType(propertyComponentBuilderWithThis9.getThisType());
            syncFunctionComponent10.setCanTakeOwner(true);
            propertyComponentBuilderWithThis9.setSetter(syncFunctionComponent10);
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis10 = new PropertyComponentBuilderWithThis(classComponentBuilder7.getOwnerType().getKType(), "size");
            AnyType[] anyTypeArr37 = {new AnyType(propertyComponentBuilderWithThis10.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider31 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType31 = returnTypeProvider31.getTypes().get(Reflection.getOrCreateKotlinClass(Long.class));
            if (returnType31 == null) {
                returnType31 = new ReturnType(Reflection.getOrCreateKotlinClass(Long.class));
                returnTypeProvider31.getTypes().put(Reflection.getOrCreateKotlinClass(Long.class), returnType31);
            }
            SyncFunctionComponent syncFunctionComponent11 = new SyncFunctionComponent("get", anyTypeArr37, returnType31, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$46$$inlined$Property$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ((FileSystemFileHandle) it[0]).getSize();
                }
            });
            syncFunctionComponent11.setOwnerType(propertyComponentBuilderWithThis10.getThisType());
            syncFunctionComponent11.setCanTakeOwner(true);
            propertyComponentBuilderWithThis10.setGetter(syncFunctionComponent11);
            classComponentBuilder7.getProperties().put("size", propertyComponentBuilderWithThis10);
            moduleDefinitionBuilder3.getClassData().add(classComponentBuilder7.buildClass());
            ModuleDefinitionBuilder moduleDefinitionBuilder10 = moduleDefinitionBuilder;
            KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(FileSystemDirectory.class);
            Module module3 = moduleDefinitionBuilder10.getModule();
            if (module3 == null) {
                throw new IllegalArgumentException(obj.toString());
            }
            AppContext appContext3 = module3.getAppContext();
            String simpleName3 = JvmClassMappingKt.getJavaClass(orCreateKotlinClass3).getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName3, "getSimpleName(...)");
            AnyType anyType40 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            ClassComponentBuilder classComponentBuilder28 = new ClassComponentBuilder(appContext3, simpleName3, orCreateKotlinClass3, anyType40 == null ? new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$$inlined$Class$3
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(FileSystemDirectory.class);
                }
            }), null) : anyType40, moduleDefinitionBuilder10.getConverters());
            TypeConverterProvider converters25 = classComponentBuilder28.getConverters();
            AnyType[] anyTypeArr38 = new AnyType[1];
            AnyType anyType41 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Uri.class), false));
            if (anyType41 == null) {
                anyType41 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Uri.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Constructor$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Uri.class);
                    }
                }), converters25);
            }
            anyTypeArr38[0] = anyType41;
            ReturnTypeProvider returnTypeProvider32 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType32 = returnTypeProvider32.getTypes().get(Reflection.getOrCreateKotlinClass(Object.class));
            if (returnType32 == null) {
                returnType32 = new ReturnType(Reflection.getOrCreateKotlinClass(Object.class));
                returnTypeProvider32.getTypes().put(Reflection.getOrCreateKotlinClass(Object.class), returnType32);
            }
            classComponentBuilder28.setConstructor(new SyncFunctionComponent(str8, anyTypeArr38, returnType32, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Constructor$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return new FileSystemDirectory((Uri) objArr[0]);
                }
            }));
            ClassComponentBuilder classComponentBuilder29 = classComponentBuilder28;
            TypeConverterProvider converters26 = classComponentBuilder29.getConverters();
            AnyType[] anyTypeArr39 = new AnyType[1];
            AnyType anyType42 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType42 == null) {
                anyType42 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters26);
            }
            anyTypeArr39[0] = anyType42;
            ReturnTypeProvider returnTypeProvider33 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType33 = returnTypeProvider33.getTypes().get(Reflection.getOrCreateKotlinClass(DirectoryInfo.class));
            if (returnType33 == null) {
                returnType33 = new ReturnType(Reflection.getOrCreateKotlinClass(DirectoryInfo.class));
                returnTypeProvider33.getTypes().put(Reflection.getOrCreateKotlinClass(DirectoryInfo.class), returnType33);
            }
            classComponentBuilder29.getSyncFunctions().put("info", new SyncFunctionComponent("info", anyTypeArr39, returnType33, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return ((FileSystemDirectory) objArr[0]).info();
                }
            }));
            ClassComponentBuilder classComponentBuilder30 = classComponentBuilder28;
            TypeConverterProvider converters27 = classComponentBuilder30.getConverters();
            AnyType[] anyTypeArr40 = new AnyType[1];
            AnyType anyType43 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType43 == null) {
                anyType43 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$3
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters27);
            }
            anyTypeArr40[0] = anyType43;
            ReturnTypeProvider returnTypeProvider34 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType34 = returnTypeProvider34.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType34 == null) {
                returnType34 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider34.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType34);
            }
            classComponentBuilder30.getSyncFunctions().put(str9, new SyncFunctionComponent(str9, anyTypeArr40, returnType34, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemDirectory) objArr[0]).delete();
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder31 = classComponentBuilder28;
            TypeConverterProvider converters28 = classComponentBuilder31.getConverters();
            AnyType[] anyTypeArr41 = new AnyType[2];
            AnyType anyType44 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType44 == null) {
                anyType44 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters28);
            }
            anyTypeArr41[0] = anyType44;
            AnyType anyType45 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(CreateOptions.class), true));
            if (anyType45 == null) {
                anyType45 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(CreateOptions.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$6
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(CreateOptions.class);
                    }
                }), converters28);
            }
            anyTypeArr41[1] = anyType45;
            ReturnTypeProvider returnTypeProvider35 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType35 = returnTypeProvider35.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType35 == null) {
                returnType35 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider35.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType35);
            }
            String str12 = str7;
            classComponentBuilder31.getSyncFunctions().put(str12, new SyncFunctionComponent(str12, anyTypeArr41, returnType35, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj2 = objArr[0];
                    CreateOptions createOptions = (CreateOptions) objArr[1];
                    FileSystemDirectory fileSystemDirectory = (FileSystemDirectory) obj2;
                    if (createOptions == null) {
                        createOptions = new CreateOptions(false, false, false, 7, null);
                    }
                    fileSystemDirectory.create(createOptions);
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder32 = classComponentBuilder28;
            TypeConverterProvider converters29 = classComponentBuilder32.getConverters();
            AnyType[] anyTypeArr42 = new AnyType[2];
            AnyType anyType46 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType46 == null) {
                anyType46 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$8
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters29);
            }
            anyTypeArr42[0] = anyType46;
            AnyType anyType47 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType47 == null) {
                anyType47 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$9
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters29);
            }
            anyTypeArr42[1] = anyType47;
            ReturnTypeProvider returnTypeProvider36 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType36 = returnTypeProvider36.getTypes().get(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class));
            if (returnType36 == null) {
                returnType36 = new ReturnType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class));
                returnTypeProvider36.getTypes().put(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), returnType36);
            }
            classComponentBuilder32.getSyncFunctions().put("createDirectory", new SyncFunctionComponent("createDirectory", anyTypeArr42, returnType36, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return ((FileSystemDirectory) objArr[0]).createDirectory((String) objArr[1]);
                }
            }));
            ClassComponentBuilder classComponentBuilder33 = classComponentBuilder28;
            TypeConverterProvider converters30 = classComponentBuilder33.getConverters();
            AnyType[] anyTypeArr43 = new AnyType[3];
            AnyType anyType48 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType48 == null) {
                anyType48 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$11
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters30);
            }
            anyTypeArr43[0] = anyType48;
            AnyType anyType49 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType49 == null) {
                anyType49 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$12
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters30);
            }
            anyTypeArr43[1] = anyType49;
            AnyType anyType50 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType50 == null) {
                anyType50 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$13
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters30);
            }
            anyTypeArr43[2] = anyType50;
            ReturnTypeProvider returnTypeProvider37 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType37 = returnTypeProvider37.getTypes().get(Reflection.getOrCreateKotlinClass(FileSystemFile.class));
            if (returnType37 == null) {
                returnType37 = new ReturnType(Reflection.getOrCreateKotlinClass(FileSystemFile.class));
                returnTypeProvider37.getTypes().put(Reflection.getOrCreateKotlinClass(FileSystemFile.class), returnType37);
            }
            classComponentBuilder33.getSyncFunctions().put("createFile", new SyncFunctionComponent("createFile", anyTypeArr43, returnType37, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj2 = objArr[0];
                    return ((FileSystemDirectory) obj2).createFile((String) objArr[2], (String) objArr[1]);
                }
            }));
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis11 = new PropertyComponentBuilderWithThis(classComponentBuilder28.getOwnerType().getKType(), "exists");
            AnyType[] anyTypeArr44 = {new AnyType(propertyComponentBuilderWithThis11.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider38 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType38 = returnTypeProvider38.getTypes().get(Reflection.getOrCreateKotlinClass(Boolean.class));
            if (returnType38 == null) {
                returnType38 = new ReturnType(Reflection.getOrCreateKotlinClass(Boolean.class));
                returnTypeProvider38.getTypes().put(Reflection.getOrCreateKotlinClass(Boolean.class), returnType38);
            }
            SyncFunctionComponent syncFunctionComponent12 = new SyncFunctionComponent("get", anyTypeArr44, returnType38, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Property$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(((FileSystemDirectory) it[0]).getExists());
                }
            });
            syncFunctionComponent12.setOwnerType(propertyComponentBuilderWithThis11.getThisType());
            syncFunctionComponent12.setCanTakeOwner(true);
            propertyComponentBuilderWithThis11.setGetter(syncFunctionComponent12);
            classComponentBuilder28.getProperties().put("exists", propertyComponentBuilderWithThis11);
            ClassComponentBuilder classComponentBuilder34 = classComponentBuilder28;
            TypeConverterProvider converters31 = classComponentBuilder34.getConverters();
            AnyType[] anyTypeArr45 = new AnyType[1];
            AnyType anyType51 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType51 == null) {
                anyType51 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$15
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters31);
            }
            anyTypeArr45[0] = anyType51;
            ReturnTypeProvider returnTypeProvider39 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType39 = returnTypeProvider39.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType39 == null) {
                returnType39 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider39.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType39);
            }
            classComponentBuilder34.getSyncFunctions().put(str10, new SyncFunctionComponent(str10, anyTypeArr45, returnType39, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemDirectory) objArr[0]).validatePath();
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder35 = classComponentBuilder28;
            TypeConverterProvider converters32 = classComponentBuilder35.getConverters();
            AnyType[] anyTypeArr46 = new AnyType[2];
            AnyType anyType52 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType52 == null) {
                anyType52 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$17
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters32);
            }
            anyTypeArr46[0] = anyType52;
            AnyType anyType53 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false));
            if (anyType53 == null) {
                anyType53 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$18
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemPath.class);
                    }
                }), converters32);
            }
            anyTypeArr46[1] = anyType53;
            ReturnTypeProvider returnTypeProvider40 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType40 = returnTypeProvider40.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType40 == null) {
                returnType40 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider40.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType40);
            }
            String str13 = str5;
            classComponentBuilder35.getSyncFunctions().put(str13, new SyncFunctionComponent(str13, anyTypeArr46, returnType40, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$19
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemDirectory) objArr[0]).copy((FileSystemPath) objArr[1]);
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder36 = classComponentBuilder28;
            TypeConverterProvider converters33 = classComponentBuilder36.getConverters();
            AnyType[] anyTypeArr47 = new AnyType[2];
            AnyType anyType54 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType54 == null) {
                anyType54 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$20
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters33);
            }
            anyTypeArr47[0] = anyType54;
            AnyType anyType55 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false));
            if (anyType55 == null) {
                anyType55 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemPath.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$21
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemPath.class);
                    }
                }), converters33);
            }
            anyTypeArr47[1] = anyType55;
            ReturnTypeProvider returnTypeProvider41 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType41 = returnTypeProvider41.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType41 == null) {
                returnType41 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider41.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType41);
            }
            String str14 = str6;
            classComponentBuilder36.getSyncFunctions().put(str14, new SyncFunctionComponent(str14, anyTypeArr47, returnType41, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$22
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemDirectory) objArr[0]).move((FileSystemPath) objArr[1]);
                    return Unit.INSTANCE;
                }
            }));
            ClassComponentBuilder classComponentBuilder37 = classComponentBuilder28;
            TypeConverterProvider converters34 = classComponentBuilder37.getConverters();
            AnyType[] anyTypeArr48 = new AnyType[2];
            AnyType anyType56 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType56 == null) {
                anyType56 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$23
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters34);
            }
            anyTypeArr48[0] = anyType56;
            AnyType anyType57 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType57 == null) {
                anyType57 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$24
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters34);
            }
            anyTypeArr48[1] = anyType57;
            ReturnTypeProvider returnTypeProvider42 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType42 = returnTypeProvider42.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType42 == null) {
                returnType42 = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider42.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType42);
            }
            classComponentBuilder37.getSyncFunctions().put("rename", new SyncFunctionComponent("rename", anyTypeArr48, returnType42, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$25
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((FileSystemDirectory) objArr[0]).rename((String) objArr[1]);
                    return Unit.INSTANCE;
                }
            }));
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis12 = new PropertyComponentBuilderWithThis(classComponentBuilder28.getOwnerType().getKType(), "uri");
            AnyType[] anyTypeArr49 = {new AnyType(propertyComponentBuilderWithThis12.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider43 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType43 = returnTypeProvider43.getTypes().get(Reflection.getOrCreateKotlinClass(String.class));
            if (returnType43 == null) {
                returnType43 = new ReturnType(Reflection.getOrCreateKotlinClass(String.class));
                returnTypeProvider43.getTypes().put(Reflection.getOrCreateKotlinClass(String.class), returnType43);
            }
            SyncFunctionComponent syncFunctionComponent13 = new SyncFunctionComponent("get", anyTypeArr49, returnType43, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Property$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ((FileSystemDirectory) it[0]).asString();
                }
            });
            syncFunctionComponent13.setOwnerType(propertyComponentBuilderWithThis12.getThisType());
            syncFunctionComponent13.setCanTakeOwner(true);
            propertyComponentBuilderWithThis12.setGetter(syncFunctionComponent13);
            classComponentBuilder28.getProperties().put("uri", propertyComponentBuilderWithThis12);
            PropertyComponentBuilderWithThis propertyComponentBuilderWithThis13 = new PropertyComponentBuilderWithThis(classComponentBuilder28.getOwnerType().getKType(), "size");
            AnyType[] anyTypeArr50 = {new AnyType(propertyComponentBuilderWithThis13.getThisType(), null, 2, null)};
            ReturnTypeProvider returnTypeProvider44 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType44 = returnTypeProvider44.getTypes().get(Reflection.getOrCreateKotlinClass(Long.class));
            if (returnType44 == null) {
                returnType44 = new ReturnType(Reflection.getOrCreateKotlinClass(Long.class));
                returnTypeProvider44.getTypes().put(Reflection.getOrCreateKotlinClass(Long.class), returnType44);
            }
            SyncFunctionComponent syncFunctionComponent14 = new SyncFunctionComponent("get", anyTypeArr50, returnType44, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Property$3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Long.valueOf(((FileSystemDirectory) it[0]).getSize());
                }
            });
            syncFunctionComponent14.setOwnerType(propertyComponentBuilderWithThis13.getThisType());
            syncFunctionComponent14.setCanTakeOwner(true);
            propertyComponentBuilderWithThis13.setGetter(syncFunctionComponent14);
            classComponentBuilder28.getProperties().put("size", propertyComponentBuilderWithThis13);
            ClassComponentBuilder classComponentBuilder38 = classComponentBuilder28;
            TypeConverterProvider converters35 = classComponentBuilder38.getConverters();
            AnyType[] anyTypeArr51 = new AnyType[1];
            AnyType anyType58 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false));
            if (anyType58 == null) {
                anyType58 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemDirectory.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$26
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemDirectory.class);
                    }
                }), converters35);
            }
            anyTypeArr51[0] = anyType58;
            ReturnTypeProvider returnTypeProvider45 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType45 = returnTypeProvider45.getTypes().get(Reflection.getOrCreateKotlinClass(List.class));
            if (returnType45 == null) {
                returnType45 = new ReturnType(Reflection.getOrCreateKotlinClass(List.class));
                returnTypeProvider45.getTypes().put(Reflection.getOrCreateKotlinClass(List.class), returnType45);
            }
            classComponentBuilder38.getSyncFunctions().put("listAsRecords", new SyncFunctionComponent("listAsRecords", anyTypeArr51, returnType45, new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$62$lambda$61$$inlined$Function$27
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    return ((FileSystemDirectory) objArr[0]).listAsRecords();
                }
            }));
            moduleDefinitionBuilder10.getClassData().add(classComponentBuilder28.buildClass());
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
