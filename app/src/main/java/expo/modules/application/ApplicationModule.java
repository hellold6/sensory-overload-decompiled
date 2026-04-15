package expo.modules.application;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import androidx.tracing.Trace;
import com.android.installreferrer.api.InstallReferrerClient;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.functions.UntypedAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ConstantComponentBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.types.TypeConverterProvider;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: ApplicationModule.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\n \f*\u0004\u0018\u00010\u00100\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lexpo/modules/application/ApplicationModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "packageName", "", "kotlin.jvm.PlatformType", "getPackageName", "()Ljava/lang/String;", "packageManager", "Landroid/content/pm/PackageManager;", "getPackageManager", "()Landroid/content/pm/PackageManager;", "expo-application_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ApplicationModule extends Module {
    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent2;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent3;
        ApplicationModule applicationModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (applicationModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(applicationModule);
            moduleDefinitionBuilder.Name("ExpoApplication");
            ConstantComponentBuilder constantComponentBuilder = new ConstantComponentBuilder("applicationName");
            constantComponentBuilder.setGetter(new Function0<String>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$Constant$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    Context context;
                    Context context2;
                    context = ApplicationModule.this.getContext();
                    ApplicationInfo applicationInfo = context.getApplicationInfo();
                    context2 = ApplicationModule.this.getContext();
                    return applicationInfo.loadLabel(context2.getPackageManager()).toString();
                }
            });
            moduleDefinitionBuilder.getConstants().put("applicationName", constantComponentBuilder);
            ConstantComponentBuilder constantComponentBuilder2 = new ConstantComponentBuilder("applicationId");
            constantComponentBuilder2.setGetter(new Function0<String>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$Constant$2
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    String packageName;
                    packageName = ApplicationModule.this.getPackageName();
                    return packageName;
                }
            });
            moduleDefinitionBuilder.getConstants().put("applicationId", constantComponentBuilder2);
            ConstantComponentBuilder constantComponentBuilder3 = new ConstantComponentBuilder("nativeApplicationVersion");
            constantComponentBuilder3.setGetter(new Function0<String>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$Constant$3
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    PackageManager packageManager;
                    String packageName;
                    PackageInfo packageInfoCompat;
                    packageManager = ApplicationModule.this.getPackageManager();
                    Intrinsics.checkNotNullExpressionValue(packageManager, "access$getPackageManager(...)");
                    packageName = ApplicationModule.this.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName, "access$getPackageName(...)");
                    packageInfoCompat = ApplicationModuleKt.getPackageInfoCompat(packageManager, packageName, 0);
                    return packageInfoCompat.versionName;
                }
            });
            moduleDefinitionBuilder.getConstants().put("nativeApplicationVersion", constantComponentBuilder3);
            ConstantComponentBuilder constantComponentBuilder4 = new ConstantComponentBuilder("nativeBuildVersion");
            constantComponentBuilder4.setGetter(new Function0<String>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$Constant$4
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    PackageManager packageManager;
                    String packageName;
                    PackageInfo packageInfoCompat;
                    long longVersionCode;
                    packageManager = ApplicationModule.this.getPackageManager();
                    Intrinsics.checkNotNullExpressionValue(packageManager, "access$getPackageManager(...)");
                    packageName = ApplicationModule.this.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName, "access$getPackageName(...)");
                    packageInfoCompat = ApplicationModuleKt.getPackageInfoCompat(packageManager, packageName, 0);
                    longVersionCode = ApplicationModuleKt.getLongVersionCode(packageInfoCompat);
                    return String.valueOf((int) longVersionCode);
                }
            });
            moduleDefinitionBuilder.getConstants().put("nativeBuildVersion", constantComponentBuilder4);
            ConstantComponentBuilder constantComponentBuilder5 = new ConstantComponentBuilder("androidId");
            constantComponentBuilder5.setGetter(new Function0<String>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$Constant$5
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    Context context;
                    context = ApplicationModule.this.getContext();
                    return Settings.Secure.getString(context.getContentResolver(), "android_id");
                }
            });
            moduleDefinitionBuilder.getConstants().put("androidId", constantComponentBuilder5);
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr = new AnyType[0];
            Function1<Object[], Double> function1 = new Function1<Object[], Double>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$AsyncFunction$1
                @Override // kotlin.jvm.functions.Function1
                public final Double invoke(Object[] it) {
                    Context context;
                    Context context2;
                    PackageInfo packageInfoCompat;
                    Intrinsics.checkNotNullParameter(it, "it");
                    context = ApplicationModule.this.getContext();
                    PackageManager packageManager = context.getPackageManager();
                    context2 = ApplicationModule.this.getContext();
                    String packageName = context2.getPackageName();
                    Intrinsics.checkNotNull(packageManager);
                    Intrinsics.checkNotNull(packageName);
                    packageInfoCompat = ApplicationModuleKt.getPackageInfoCompat(packageManager, packageName, 0);
                    return Double.valueOf(packageInfoCompat.firstInstallTime);
                }
            };
            if (!Intrinsics.areEqual(Double.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Double.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Double.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Double.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Double.class, String.class)) {
                                untypedAsyncFunctionComponent = new StringAsyncFunctionComponent("getInstallationTimeAsync", anyTypeArr, function1);
                            } else {
                                untypedAsyncFunctionComponent = new UntypedAsyncFunctionComponent("getInstallationTimeAsync", anyTypeArr, function1);
                            }
                        } else {
                            untypedAsyncFunctionComponent = new FloatAsyncFunctionComponent("getInstallationTimeAsync", anyTypeArr, function1);
                        }
                    } else {
                        untypedAsyncFunctionComponent = new DoubleAsyncFunctionComponent("getInstallationTimeAsync", anyTypeArr, function1);
                    }
                } else {
                    untypedAsyncFunctionComponent = new BoolAsyncFunctionComponent("getInstallationTimeAsync", anyTypeArr, function1);
                }
            } else {
                untypedAsyncFunctionComponent = new IntAsyncFunctionComponent("getInstallationTimeAsync", anyTypeArr, function1);
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getInstallationTimeAsync", untypedAsyncFunctionComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr2 = new AnyType[0];
            Function1<Object[], Double> function12 = new Function1<Object[], Double>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$AsyncFunction$2
                @Override // kotlin.jvm.functions.Function1
                public final Double invoke(Object[] it) {
                    Context context;
                    Context context2;
                    PackageInfo packageInfoCompat;
                    Intrinsics.checkNotNullParameter(it, "it");
                    context = ApplicationModule.this.getContext();
                    PackageManager packageManager = context.getPackageManager();
                    context2 = ApplicationModule.this.getContext();
                    String packageName = context2.getPackageName();
                    Intrinsics.checkNotNull(packageManager);
                    Intrinsics.checkNotNull(packageName);
                    packageInfoCompat = ApplicationModuleKt.getPackageInfoCompat(packageManager, packageName, 0);
                    return Double.valueOf(packageInfoCompat.lastUpdateTime);
                }
            };
            if (!Intrinsics.areEqual(Double.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Double.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Double.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Double.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Double.class, String.class)) {
                                untypedAsyncFunctionComponent2 = new StringAsyncFunctionComponent("getLastUpdateTimeAsync", anyTypeArr2, function12);
                            } else {
                                untypedAsyncFunctionComponent2 = new UntypedAsyncFunctionComponent("getLastUpdateTimeAsync", anyTypeArr2, function12);
                            }
                        } else {
                            untypedAsyncFunctionComponent2 = new FloatAsyncFunctionComponent("getLastUpdateTimeAsync", anyTypeArr2, function12);
                        }
                    } else {
                        untypedAsyncFunctionComponent2 = new DoubleAsyncFunctionComponent("getLastUpdateTimeAsync", anyTypeArr2, function12);
                    }
                } else {
                    untypedAsyncFunctionComponent2 = new BoolAsyncFunctionComponent("getLastUpdateTimeAsync", anyTypeArr2, function12);
                }
            } else {
                untypedAsyncFunctionComponent2 = new IntAsyncFunctionComponent("getLastUpdateTimeAsync", anyTypeArr2, function12);
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("getLastUpdateTimeAsync", untypedAsyncFunctionComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                untypedAsyncFunctionComponent3 = new AsyncFunctionWithPromiseComponent("getInstallReferrerAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$AsyncFunction$3
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Context context;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        StringBuilder sb = new StringBuilder();
                        context = ApplicationModule.this.getContext();
                        InstallReferrerClient build = InstallReferrerClient.newBuilder(context).build();
                        build.startConnection(new ApplicationModule$definition$1$8$1(build, sb, promise));
                    }
                });
            } else {
                TypeConverterProvider converters = moduleDefinitionBuilder4.getConverters();
                AnyType[] anyTypeArr3 = new AnyType[1];
                AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Promise.class), false));
                if (anyType == null) {
                    anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$AsyncFunction$4
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(Promise.class);
                        }
                    }), converters);
                }
                anyTypeArr3[0] = anyType;
                Function1<Object[], Unit> function13 = new Function1<Object[], Unit>() { // from class: expo.modules.application.ApplicationModule$definition$lambda$8$$inlined$AsyncFunction$5
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Context context;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        Promise promise = (Promise) objArr[0];
                        StringBuilder sb = new StringBuilder();
                        context = ApplicationModule.this.getContext();
                        InstallReferrerClient build = InstallReferrerClient.newBuilder(context).build();
                        build.startConnection(new ApplicationModule$definition$1$8$1(build, sb, promise));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    untypedAsyncFunctionComponent3 = new StringAsyncFunctionComponent("getInstallReferrerAsync", anyTypeArr3, function13);
                                } else {
                                    untypedAsyncFunctionComponent3 = new UntypedAsyncFunctionComponent("getInstallReferrerAsync", anyTypeArr3, function13);
                                }
                            } else {
                                untypedAsyncFunctionComponent3 = new FloatAsyncFunctionComponent("getInstallReferrerAsync", anyTypeArr3, function13);
                            }
                        } else {
                            untypedAsyncFunctionComponent3 = new DoubleAsyncFunctionComponent("getInstallReferrerAsync", anyTypeArr3, function13);
                        }
                    } else {
                        untypedAsyncFunctionComponent3 = new BoolAsyncFunctionComponent("getInstallReferrerAsync", anyTypeArr3, function13);
                    }
                } else {
                    untypedAsyncFunctionComponent3 = new IntAsyncFunctionComponent("getInstallReferrerAsync", anyTypeArr3, function13);
                }
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("getInstallReferrerAsync", untypedAsyncFunctionComponent3);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPackageName() {
        return getContext().getPackageName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PackageManager getPackageManager() {
        return getContext().getPackageManager();
    }
}
