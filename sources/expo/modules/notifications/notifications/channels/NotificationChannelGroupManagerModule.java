package expo.modules.notifications.notifications.channels;

import android.app.NotificationChannelGroup;
import android.os.Build;
import android.os.Bundle;
import androidx.tracing.Trace;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
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
import expo.modules.notifications.ModuleNotFoundException;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: NotificationChannelGroupManagerModule.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lexpo/modules/notifications/notifications/channels/NotificationChannelGroupManagerModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "groupManager", "Lexpo/modules/notifications/notifications/channels/managers/NotificationsChannelGroupManager;", "groupSerializer", "Lexpo/modules/notifications/notifications/channels/serializers/NotificationsChannelGroupSerializer;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getNameFromOptions", "", "groupOptions", "Lexpo/modules/core/arguments/ReadableArguments;", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NotificationChannelGroupManagerModule extends Module {
    private NotificationsChannelGroupManager groupManager;
    private NotificationsChannelGroupSerializer groupSerializer;

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent2;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        NotificationChannelGroupManagerModule notificationChannelGroupManagerModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (notificationChannelGroupManagerModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(notificationChannelGroupManagerModule);
            moduleDefinitionBuilder.Name("ExpoNotificationChannelGroupManager");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$OnCreate$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Object obj;
                    try {
                        obj = NotificationChannelGroupManagerModule.this.getAppContext().getLegacyModuleRegistry().getModule(NotificationsChannelsProvider.class);
                    } catch (Exception unused) {
                        obj = null;
                    }
                    NotificationsChannelsProvider notificationsChannelsProvider = (NotificationsChannelsProvider) obj;
                    if (notificationsChannelsProvider == null) {
                        throw new ModuleNotFoundException(Reflection.getOrCreateKotlinClass(NotificationsChannelsProvider.class));
                    }
                    NotificationChannelGroupManagerModule.this.groupManager = notificationsChannelsProvider.getGroupManager();
                    NotificationChannelGroupManagerModule.this.groupSerializer = notificationsChannelsProvider.getGroupSerializer();
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                untypedAsyncFunctionComponent = new AsyncFunctionWithPromiseComponent("getNotificationChannelGroupAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        NotificationsChannelGroupManager notificationsChannelGroupManager;
                        NotificationsChannelGroupSerializer notificationsChannelGroupSerializer;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        if (Build.VERSION.SDK_INT >= 26) {
                            notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                            NotificationsChannelGroupSerializer notificationsChannelGroupSerializer2 = null;
                            if (notificationsChannelGroupManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                                notificationsChannelGroupManager = null;
                            }
                            NotificationChannelGroup notificationChannelGroup = notificationsChannelGroupManager.getNotificationChannelGroup(str);
                            notificationsChannelGroupSerializer = NotificationChannelGroupManagerModule.this.groupSerializer;
                            if (notificationsChannelGroupSerializer == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("groupSerializer");
                            } else {
                                notificationsChannelGroupSerializer2 = notificationsChannelGroupSerializer;
                            }
                            notificationsChannelGroupSerializer2.toBundle(notificationChannelGroup);
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters = moduleDefinitionBuilder2.getConverters();
                AnyType[] anyTypeArr = new AnyType[1];
                AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
                if (anyType == null) {
                    anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$2
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(String.class);
                        }
                    }), converters);
                }
                anyTypeArr[0] = anyType;
                untypedAsyncFunctionComponent = new UntypedAsyncFunctionComponent("getNotificationChannelGroupAsync", anyTypeArr, new Function1<Object[], Bundle>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Bundle invoke(Object[] objArr) {
                        NotificationsChannelGroupManager notificationsChannelGroupManager;
                        NotificationsChannelGroupSerializer notificationsChannelGroupSerializer;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        String str = (String) objArr[0];
                        NotificationsChannelGroupSerializer notificationsChannelGroupSerializer2 = null;
                        if (Build.VERSION.SDK_INT < 26) {
                            return null;
                        }
                        notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                        if (notificationsChannelGroupManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                            notificationsChannelGroupManager = null;
                        }
                        NotificationChannelGroup notificationChannelGroup = notificationsChannelGroupManager.getNotificationChannelGroup(str);
                        notificationsChannelGroupSerializer = NotificationChannelGroupManagerModule.this.groupSerializer;
                        if (notificationsChannelGroupSerializer == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("groupSerializer");
                        } else {
                            notificationsChannelGroupSerializer2 = notificationsChannelGroupSerializer;
                        }
                        return notificationsChannelGroupSerializer2.toBundle(notificationChannelGroup);
                    }
                });
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getNotificationChannelGroupAsync", untypedAsyncFunctionComponent);
            moduleDefinitionBuilder.getAsyncFunctions().put("getNotificationChannelGroupsAsync", new UntypedAsyncFunctionComponent("getNotificationChannelGroupsAsync", new AnyType[0], new Function1<Object[], List<? extends Bundle>>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$4
                @Override // kotlin.jvm.functions.Function1
                public final List<? extends Bundle> invoke(Object[] it) {
                    NotificationsChannelGroupManager notificationsChannelGroupManager;
                    NotificationsChannelGroupSerializer notificationsChannelGroupSerializer;
                    Intrinsics.checkNotNullParameter(it, "it");
                    NotificationsChannelGroupSerializer notificationsChannelGroupSerializer2 = null;
                    if (Build.VERSION.SDK_INT < 26) {
                        return null;
                    }
                    notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                    if (notificationsChannelGroupManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                        notificationsChannelGroupManager = null;
                    }
                    List<NotificationChannelGroup> notificationChannelGroups = notificationsChannelGroupManager.getNotificationChannelGroups();
                    Intrinsics.checkNotNullExpressionValue(notificationChannelGroups, "getNotificationChannelGroups(...)");
                    List<NotificationChannelGroup> list = notificationChannelGroups;
                    notificationsChannelGroupSerializer = NotificationChannelGroupManagerModule.this.groupSerializer;
                    if (notificationsChannelGroupSerializer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("groupSerializer");
                    } else {
                        notificationsChannelGroupSerializer2 = notificationsChannelGroupSerializer;
                    }
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator<T> it2 = list.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(notificationsChannelGroupSerializer2.toBundle((NotificationChannelGroup) it2.next()));
                    }
                    return arrayList;
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            TypeConverterProvider converters2 = moduleDefinitionBuilder3.getConverters();
            AnyType[] anyTypeArr2 = new AnyType[2];
            AnyType anyType2 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType2 == null) {
                anyType2 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters2);
            }
            anyTypeArr2[0] = anyType2;
            AnyType anyType3 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false));
            if (anyType3 == null) {
                anyType3 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$6
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(ReadableArguments.class);
                    }
                }), converters2);
            }
            anyTypeArr2[1] = anyType3;
            moduleDefinitionBuilder3.getAsyncFunctions().put("setNotificationChannelGroupAsync", new UntypedAsyncFunctionComponent("setNotificationChannelGroupAsync", anyTypeArr2, new Function1<Object[], Bundle>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$7
                @Override // kotlin.jvm.functions.Function1
                public final Bundle invoke(Object[] objArr) {
                    NotificationsChannelGroupManager notificationsChannelGroupManager;
                    String nameFromOptions;
                    NotificationsChannelGroupSerializer notificationsChannelGroupSerializer;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    ReadableArguments readableArguments = (ReadableArguments) objArr[1];
                    String str = (String) obj;
                    NotificationsChannelGroupSerializer notificationsChannelGroupSerializer2 = null;
                    if (Build.VERSION.SDK_INT < 26) {
                        return null;
                    }
                    notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                    if (notificationsChannelGroupManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                        notificationsChannelGroupManager = null;
                    }
                    nameFromOptions = NotificationChannelGroupManagerModule.this.getNameFromOptions(readableArguments);
                    NotificationChannelGroup createNotificationChannelGroup = notificationsChannelGroupManager.createNotificationChannelGroup(str, nameFromOptions, readableArguments);
                    notificationsChannelGroupSerializer = NotificationChannelGroupManagerModule.this.groupSerializer;
                    if (notificationsChannelGroupSerializer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("groupSerializer");
                    } else {
                        notificationsChannelGroupSerializer2 = notificationsChannelGroupSerializer;
                    }
                    return notificationsChannelGroupSerializer2.toBundle(createNotificationChannelGroup);
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("deleteNotificationChannelGroupAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$8
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        NotificationsChannelGroupManager notificationsChannelGroupManager;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        if (Build.VERSION.SDK_INT >= 26) {
                            notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                            if (notificationsChannelGroupManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                                notificationsChannelGroupManager = null;
                            }
                            notificationsChannelGroupManager.deleteNotificationChannelGroup(str);
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
                    anyType4 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$9
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(String.class);
                        }
                    }), converters3);
                }
                anyTypeArr3[0] = anyType4;
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$10
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        NotificationsChannelGroupManager notificationsChannelGroupManager;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        String str = (String) objArr[0];
                        if (Build.VERSION.SDK_INT >= 26) {
                            notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                            if (notificationsChannelGroupManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                                notificationsChannelGroupManager = null;
                            }
                            notificationsChannelGroupManager.deleteNotificationChannelGroup(str);
                        }
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    untypedAsyncFunctionComponent2 = new StringAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr3, function1);
                                } else {
                                    untypedAsyncFunctionComponent2 = new UntypedAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr3, function1);
                                }
                            } else {
                                untypedAsyncFunctionComponent2 = new FloatAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr3, function1);
                            }
                        } else {
                            untypedAsyncFunctionComponent2 = new DoubleAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr3, function1);
                        }
                    } else {
                        untypedAsyncFunctionComponent2 = new BoolAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr3, function1);
                    }
                } else {
                    untypedAsyncFunctionComponent2 = new IntAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr3, function1);
                }
                asyncFunctionWithPromiseComponent = untypedAsyncFunctionComponent2;
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("deleteNotificationChannelGroupAsync", asyncFunctionWithPromiseComponent);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getNameFromOptions(ReadableArguments groupOptions) {
        String string = groupOptions.getString("name");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
