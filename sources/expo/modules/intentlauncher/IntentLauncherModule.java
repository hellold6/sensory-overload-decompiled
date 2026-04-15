package expo.modules.intentlauncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import androidx.tracing.Trace;
import expo.modules.intentlauncher.exceptions.ActivityAlreadyStartedException;
import expo.modules.intentlauncher.exceptions.PackageNotFoundException;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.events.OnActivityResultPayload;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.functions.UntypedAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.types.ReturnType;
import expo.modules.kotlin.types.ReturnTypeProvider;
import expo.modules.kotlin.types.TypeConverterProvider;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: IntentLauncherModule.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lexpo/modules/intentlauncher/IntentLauncherModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "pendingPromise", "Lexpo/modules/kotlin/Promise;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-intent-launcher_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IntentLauncherModule extends Module {
    private Promise pendingPromise;

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
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        IntentLauncherModule intentLauncherModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (intentLauncherModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(intentLauncherModule);
            moduleDefinitionBuilder.Name("ExpoIntentLauncher");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            TypeConverterProvider converters = moduleDefinitionBuilder2.getConverters();
            AnyType[] anyTypeArr = new AnyType[2];
            AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType == null) {
                anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.intentlauncher.IntentLauncherModule$definition$lambda$14$$inlined$AsyncFunctionWithPromise$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters);
            }
            anyTypeArr[0] = anyType;
            AnyType anyType2 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(IntentLauncherParams.class), false));
            if (anyType2 == null) {
                anyType2 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(IntentLauncherParams.class), false, new Function0<KType>() { // from class: expo.modules.intentlauncher.IntentLauncherModule$definition$lambda$14$$inlined$AsyncFunctionWithPromise$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(IntentLauncherParams.class);
                    }
                }), converters);
            }
            anyTypeArr[1] = anyType2;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("startActivity", anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.intentlauncher.IntentLauncherModule$definition$lambda$14$$inlined$AsyncFunctionWithPromise$3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Promise promise2;
                    UnexpectedException unexpectedException;
                    Bundle bundle;
                    ComponentName componentName;
                    Context context;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    IntentLauncherParams intentLauncherParams = (IntentLauncherParams) objArr[1];
                    String str = (String) obj;
                    promise2 = IntentLauncherModule.this.pendingPromise;
                    if (promise2 != null) {
                        throw new ActivityAlreadyStartedException();
                    }
                    Intent intent = new Intent(str);
                    if (intentLauncherParams.getClassName() != null) {
                        if (intentLauncherParams.getPackageName() == null) {
                            context = IntentLauncherModule.this.getContext();
                            componentName = new ComponentName(context, intentLauncherParams.getClassName());
                        } else {
                            componentName = new ComponentName(intentLauncherParams.getPackageName(), intentLauncherParams.getClassName());
                        }
                        intent.setComponent(componentName);
                    }
                    if (intentLauncherParams.getData() != null && intentLauncherParams.getType() != null) {
                        intent.setDataAndType(Uri.parse(intentLauncherParams.getData()), intentLauncherParams.getType());
                    } else if (intentLauncherParams.getData() != null) {
                        intent.setData(Uri.parse(intentLauncherParams.getData()));
                    } else if (intentLauncherParams.getType() != null) {
                        intent.setType(intentLauncherParams.getType());
                    }
                    Map<String, Object> extra = intentLauncherParams.getExtra();
                    if (extra != null) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(extra.size()));
                        Iterator<T> it = extra.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            Object key = entry.getKey();
                            Object value = entry.getValue();
                            if (value instanceof Double) {
                                Number number = (Number) value;
                                if (number.doubleValue() > 2.147483647E9d || number.doubleValue() < -2.147483648E9d) {
                                    value = Long.valueOf((long) number.doubleValue());
                                } else {
                                    value = Integer.valueOf((int) number.doubleValue());
                                }
                            }
                            linkedHashMap.put(key, value);
                        }
                        bundle = IntentLauncherModuleKt.toBundle(linkedHashMap);
                        intent.putExtras(bundle);
                    }
                    Integer flags = intentLauncherParams.getFlags();
                    if (flags != null) {
                        intent.addFlags(flags.intValue());
                    }
                    String category = intentLauncherParams.getCategory();
                    if (category != null) {
                        intent.addCategory(category);
                    }
                    try {
                        IntentLauncherModule.this.getAppContext().getThrowingActivity().startActivityForResult(intent, 12);
                        IntentLauncherModule.this.pendingPromise = promise;
                    } catch (Throwable th) {
                        if (th instanceof CodedException) {
                            unexpectedException = (CodedException) th;
                        } else if (th instanceof expo.modules.core.errors.CodedException) {
                            expo.modules.core.errors.CodedException codedException = (expo.modules.core.errors.CodedException) th;
                            String code = codedException.getCode();
                            Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                            unexpectedException = new CodedException(code, codedException.getMessage(), codedException.getCause());
                        } else {
                            unexpectedException = new UnexpectedException(th);
                        }
                        promise.reject(unexpectedException);
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder2.getAsyncFunctions().put("startActivity", asyncFunctionWithPromiseComponent2);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3 = asyncFunctionWithPromiseComponent2;
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            TypeConverterProvider converters2 = moduleDefinitionBuilder3.getConverters();
            AnyType[] anyTypeArr2 = new AnyType[1];
            AnyType anyType3 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType3 == null) {
                anyType3 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.intentlauncher.IntentLauncherModule$definition$lambda$14$$inlined$Function$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters2);
            }
            anyTypeArr2[0] = anyType3;
            ReturnTypeProvider returnTypeProvider = ReturnTypeProvider.INSTANCE;
            ReturnType returnType = returnTypeProvider.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType == null) {
                returnType = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType);
            }
            moduleDefinitionBuilder3.getSyncFunctions().put("openApplication", new SyncFunctionComponent("openApplication", anyTypeArr2, returnType, new Function1<Object[], Object>() { // from class: expo.modules.intentlauncher.IntentLauncherModule$definition$lambda$14$$inlined$Function$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Context context;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    String str = (String) objArr[0];
                    context = IntentLauncherModule.this.getContext();
                    Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
                    if (launchIntentForPackage == null) {
                        throw new PackageNotFoundException(str);
                    }
                    IntentLauncherModule.this.getAppContext().getThrowingActivity().startActivity(launchIntentForPackage);
                    return Unit.INSTANCE;
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("getApplicationIcon", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.intentlauncher.IntentLauncherModule$definition$lambda$14$$inlined$AsyncFunction$1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Context context;
                        Bitmap bitmap;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        context = IntentLauncherModule.this.getContext();
                        PackageManager packageManager = context.getPackageManager();
                        try {
                            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                            Intrinsics.checkNotNull(applicationInfo);
                            Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
                            Intrinsics.checkNotNullExpressionValue(applicationIcon, "getApplicationIcon(...)");
                            if (applicationIcon instanceof BitmapDrawable) {
                                bitmap = ((BitmapDrawable) applicationIcon).getBitmap();
                            } else if (applicationIcon instanceof AdaptiveIconDrawable) {
                                AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) applicationIcon;
                                Bitmap createBitmap = Bitmap.createBitmap(adaptiveIconDrawable.getIntrinsicWidth(), adaptiveIconDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                                Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
                                Canvas canvas = new Canvas(createBitmap);
                                applicationIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                                adaptiveIconDrawable.draw(canvas);
                                bitmap = createBitmap;
                            } else {
                                bitmap = null;
                            }
                            if (bitmap != null) {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                String str2 = "data:image/png;base64," + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                            }
                        } catch (PackageManager.NameNotFoundException unused) {
                            throw new PackageNotFoundException(str);
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters3 = moduleDefinitionBuilder4.getConverters();
                AnyType[] anyTypeArr3 = new AnyType[1];
                AnyType anyType4 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
                if (anyType4 == null) {
                    anyType4 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.intentlauncher.IntentLauncherModule$definition$lambda$14$$inlined$AsyncFunction$2
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(String.class);
                        }
                    }), converters3);
                }
                anyTypeArr3[0] = anyType4;
                Function1<Object[], String> function1 = new Function1<Object[], String>() { // from class: expo.modules.intentlauncher.IntentLauncherModule$definition$lambda$14$$inlined$AsyncFunction$3
                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(Object[] objArr) {
                        Context context;
                        Bitmap bitmap;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        String str = (String) objArr[0];
                        context = IntentLauncherModule.this.getContext();
                        PackageManager packageManager = context.getPackageManager();
                        try {
                            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                            Intrinsics.checkNotNull(applicationInfo);
                            Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
                            Intrinsics.checkNotNullExpressionValue(applicationIcon, "getApplicationIcon(...)");
                            if (applicationIcon instanceof BitmapDrawable) {
                                bitmap = ((BitmapDrawable) applicationIcon).getBitmap();
                            } else if (applicationIcon instanceof AdaptiveIconDrawable) {
                                AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) applicationIcon;
                                Bitmap createBitmap = Bitmap.createBitmap(adaptiveIconDrawable.getIntrinsicWidth(), adaptiveIconDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                                Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
                                Canvas canvas = new Canvas(createBitmap);
                                applicationIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                                adaptiveIconDrawable.draw(canvas);
                                bitmap = createBitmap;
                            } else {
                                bitmap = null;
                            }
                            if (bitmap != null) {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                String str2 = "data:image/png;base64," + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                                return str2 == null ? "" : str2;
                            }
                            return "";
                        } catch (PackageManager.NameNotFoundException unused) {
                            throw new PackageNotFoundException(str);
                        }
                    }
                };
                if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(String.class, String.class)) {
                                    untypedAsyncFunctionComponent = new StringAsyncFunctionComponent("getApplicationIcon", anyTypeArr3, function1);
                                } else {
                                    untypedAsyncFunctionComponent = new UntypedAsyncFunctionComponent("getApplicationIcon", anyTypeArr3, function1);
                                }
                            } else {
                                untypedAsyncFunctionComponent = new FloatAsyncFunctionComponent("getApplicationIcon", anyTypeArr3, function1);
                            }
                        } else {
                            untypedAsyncFunctionComponent = new DoubleAsyncFunctionComponent("getApplicationIcon", anyTypeArr3, function1);
                        }
                    } else {
                        untypedAsyncFunctionComponent = new BoolAsyncFunctionComponent("getApplicationIcon", anyTypeArr3, function1);
                    }
                } else {
                    untypedAsyncFunctionComponent = new IntAsyncFunctionComponent("getApplicationIcon", anyTypeArr3, function1);
                }
                asyncFunctionWithPromiseComponent = untypedAsyncFunctionComponent;
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("getApplicationIcon", asyncFunctionWithPromiseComponent);
            moduleDefinitionBuilder.getEventListeners().put(EventName.ON_ACTIVITY_RESULT, new EventListenerWithSenderAndPayload(EventName.ON_ACTIVITY_RESULT, new Function2<Activity, OnActivityResultPayload, Unit>() { // from class: expo.modules.intentlauncher.IntentLauncherModule$definition$lambda$14$$inlined$OnActivityResult$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Activity activity, OnActivityResultPayload onActivityResultPayload) {
                    invoke2(activity, onActivityResultPayload);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Activity sender, OnActivityResultPayload payload) {
                    Promise promise;
                    Bundle extras;
                    Intrinsics.checkNotNullParameter(sender, "sender");
                    Intrinsics.checkNotNullParameter(payload, "payload");
                    if (payload.getRequestCode() != 12) {
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("resultCode", payload.getResultCode());
                    if (payload.getData() != null) {
                        Intent data = payload.getData();
                        if (data != null) {
                            bundle.putString("data", data.toString());
                        }
                        Intent data2 = payload.getData();
                        if (data2 != null && (extras = data2.getExtras()) != null) {
                            bundle.putBundle("extra", extras);
                        }
                    }
                    promise = IntentLauncherModule.this.pendingPromise;
                    if (promise != null) {
                        promise.resolve(bundle);
                    }
                    IntentLauncherModule.this.pendingPromise = null;
                }
            }));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
