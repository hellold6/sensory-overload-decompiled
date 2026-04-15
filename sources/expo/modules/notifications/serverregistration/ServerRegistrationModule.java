package expo.modules.notifications.serverregistration;

import android.content.Context;
import androidx.tracing.Trace;
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
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.types.TypeConverterProvider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: ServerRegistrationModule.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\n\u001a\u00020\u0015H\u0016R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lexpo/modules/notifications/serverregistration/ServerRegistrationModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "installationId", "Lexpo/modules/notifications/serverregistration/InstallationId;", "getInstallationId", "()Lexpo/modules/notifications/serverregistration/InstallationId;", "installationId$delegate", "Lkotlin/Lazy;", "mRegistrationInfo", "Lexpo/modules/notifications/serverregistration/RegistrationInfo;", "getMRegistrationInfo", "()Lexpo/modules/notifications/serverregistration/RegistrationInfo;", "mRegistrationInfo$delegate", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ServerRegistrationModule extends Module {

    /* renamed from: installationId$delegate, reason: from kotlin metadata */
    private final Lazy installationId = LazyKt.lazy(new Function0() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            InstallationId installationId_delegate$lambda$0;
            installationId_delegate$lambda$0 = ServerRegistrationModule.installationId_delegate$lambda$0(ServerRegistrationModule.this);
            return installationId_delegate$lambda$0;
        }
    });

    /* renamed from: mRegistrationInfo$delegate, reason: from kotlin metadata */
    private final Lazy mRegistrationInfo = LazyKt.lazy(new Function0() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            RegistrationInfo mRegistrationInfo_delegate$lambda$1;
            mRegistrationInfo_delegate$lambda$1 = ServerRegistrationModule.mRegistrationInfo_delegate$lambda$1(ServerRegistrationModule.this);
            return mRegistrationInfo_delegate$lambda$1;
        }
    });

    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final InstallationId installationId_delegate$lambda$0(ServerRegistrationModule serverRegistrationModule) {
        return new InstallationId(serverRegistrationModule.getContext());
    }

    protected final InstallationId getInstallationId() {
        return (InstallationId) this.installationId.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RegistrationInfo getMRegistrationInfo() {
        return (RegistrationInfo) this.mRegistrationInfo.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RegistrationInfo mRegistrationInfo_delegate$lambda$1(ServerRegistrationModule serverRegistrationModule) {
        return new RegistrationInfo(serverRegistrationModule.getContext());
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent2;
        ServerRegistrationModule serverRegistrationModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (serverRegistrationModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(serverRegistrationModule);
            moduleDefinitionBuilder.Name("NotificationsServerRegistrationModule");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr = new AnyType[0];
            Function1<Object[], String> function1 = new Function1<Object[], String>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$4$$inlined$AsyncFunction$1
                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ServerRegistrationModule.this.m1381getInstallationId();
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                untypedAsyncFunctionComponent = new StringAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                            } else {
                                untypedAsyncFunctionComponent = new UntypedAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                            }
                        } else {
                            untypedAsyncFunctionComponent = new FloatAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                        }
                    } else {
                        untypedAsyncFunctionComponent = new DoubleAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                    }
                } else {
                    untypedAsyncFunctionComponent = new BoolAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                }
            } else {
                untypedAsyncFunctionComponent = new IntAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getInstallationIdAsync", untypedAsyncFunctionComponent);
            moduleDefinitionBuilder.getAsyncFunctions().put("getRegistrationInfoAsync", new UntypedAsyncFunctionComponent("getRegistrationInfoAsync", new AnyType[0], new Function1<Object[], String>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$4$$inlined$AsyncFunction$2
                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    RegistrationInfo mRegistrationInfo;
                    Intrinsics.checkNotNullParameter(it, "it");
                    mRegistrationInfo = ServerRegistrationModule.this.getMRegistrationInfo();
                    return mRegistrationInfo.get();
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                untypedAsyncFunctionComponent2 = new AsyncFunctionWithPromiseComponent("setRegistrationInfoAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$4$$inlined$AsyncFunction$3
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        RegistrationInfo mRegistrationInfo;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        mRegistrationInfo = ServerRegistrationModule.this.getMRegistrationInfo();
                        mRegistrationInfo.set((String) promise);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters = moduleDefinitionBuilder3.getConverters();
                AnyType[] anyTypeArr2 = new AnyType[1];
                AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
                if (anyType == null) {
                    anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$4$$inlined$AsyncFunction$4
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.nullableTypeOf(String.class);
                        }
                    }), converters);
                }
                anyTypeArr2[0] = anyType;
                Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$4$$inlined$AsyncFunction$5
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        RegistrationInfo mRegistrationInfo;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        String str = (String) objArr[0];
                        mRegistrationInfo = ServerRegistrationModule.this.getMRegistrationInfo();
                        mRegistrationInfo.set(str);
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    untypedAsyncFunctionComponent2 = new StringAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                                } else {
                                    untypedAsyncFunctionComponent2 = new UntypedAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                                }
                            } else {
                                untypedAsyncFunctionComponent2 = new FloatAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                            }
                        } else {
                            untypedAsyncFunctionComponent2 = new DoubleAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                        }
                    } else {
                        untypedAsyncFunctionComponent2 = new BoolAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                    }
                } else {
                    untypedAsyncFunctionComponent2 = new IntAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                }
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("setRegistrationInfoAsync", untypedAsyncFunctionComponent2);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* renamed from: getInstallationId, reason: collision with other method in class */
    public String m1381getInstallationId() {
        String orCreateUUID = getInstallationId().getOrCreateUUID();
        Intrinsics.checkNotNullExpressionValue(orCreateUUID, "getOrCreateUUID(...)");
        return orCreateUUID;
    }
}
