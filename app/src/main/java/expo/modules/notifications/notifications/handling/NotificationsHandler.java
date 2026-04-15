package expo.modules.notifications.notifications.handling;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.tracing.Trace;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventEmitter;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.types.TypeConverterProvider;
import expo.modules.notifications.NotificationWasAlreadyHandledException;
import expo.modules.notifications.notifications.NotificationManager;
import expo.modules.notifications.notifications.interfaces.INotificationContent;
import expo.modules.notifications.notifications.interfaces.NotificationListener;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehaviorRecord;
import expo.modules.notifications.notifications.model.RemoteNotificationContent;
import expo.modules.notifications.service.NotificationsService;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: NotificationsHandler.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0016J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lexpo/modules/notifications/notifications/handling/NotificationsHandler;", "Lexpo/modules/kotlin/modules/Module;", "Lexpo/modules/notifications/notifications/interfaces/NotificationListener;", "<init>", "()V", "notificationManager", "Lexpo/modules/notifications/notifications/NotificationManager;", "moduleRegistry", "Lexpo/modules/core/ModuleRegistry;", "notificationsHandlerThread", "Landroid/os/HandlerThread;", "handler", "Landroid/os/Handler;", "tasksMap", "", "", "Lexpo/modules/notifications/notifications/handling/SingleNotificationHandlerTask;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "handleNotificationAsync", "", NotificationsService.IDENTIFIER_KEY, "behavior", "Lexpo/modules/notifications/notifications/model/NotificationBehaviorRecord;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "onNotificationReceived", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "onTaskFinished", "task", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class NotificationsHandler extends Module implements NotificationListener {
    private Handler handler;
    private ModuleRegistry moduleRegistry;
    private NotificationManager notificationManager;
    private HandlerThread notificationsHandlerThread;
    private final Map<String, SingleNotificationHandlerTask> tasksMap = new LinkedHashMap();

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        NotificationsHandler notificationsHandler = this;
        Trace.beginSection("[ExpoModulesCore] " + (notificationsHandler.getClass() + ".ModuleDefinition"));
        try {
            final ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(notificationsHandler);
            moduleDefinitionBuilder.Name("ExpoNotificationsHandlerModule");
            moduleDefinitionBuilder.Events("onHandleNotification", "onHandleNotificationTimeout");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.notifications.notifications.handling.NotificationsHandler$definition$lambda$3$$inlined$OnCreate$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    ModuleRegistry moduleRegistry;
                    NotificationManager notificationManager;
                    HandlerThread handlerThread;
                    HandlerThread handlerThread2;
                    NotificationsHandler notificationsHandler2 = NotificationsHandler.this;
                    notificationsHandler2.moduleRegistry = notificationsHandler2.getAppContext().getLegacyModuleRegistry();
                    NotificationsHandler notificationsHandler3 = NotificationsHandler.this;
                    moduleRegistry = notificationsHandler3.moduleRegistry;
                    HandlerThread handlerThread3 = null;
                    if (moduleRegistry == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moduleRegistry");
                        moduleRegistry = null;
                    }
                    Object singletonModule = moduleRegistry.getSingletonModule("NotificationManager", NotificationManager.class);
                    if (singletonModule != null) {
                        notificationsHandler3.notificationManager = (NotificationManager) singletonModule;
                        notificationManager = NotificationsHandler.this.notificationManager;
                        if (notificationManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
                            notificationManager = null;
                        }
                        notificationManager.addListener(NotificationsHandler.this);
                        NotificationsHandler.this.notificationsHandlerThread = new HandlerThread("NotificationsHandlerThread - " + moduleDefinitionBuilder.getClass());
                        handlerThread = NotificationsHandler.this.notificationsHandlerThread;
                        if (handlerThread == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("notificationsHandlerThread");
                            handlerThread = null;
                        }
                        handlerThread.start();
                        NotificationsHandler notificationsHandler4 = NotificationsHandler.this;
                        handlerThread2 = NotificationsHandler.this.notificationsHandlerThread;
                        if (handlerThread2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("notificationsHandlerThread");
                        } else {
                            handlerThread3 = handlerThread2;
                        }
                        notificationsHandler4.handler = new Handler(handlerThread3.getLooper());
                        return;
                    }
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
            }));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new Function0<Unit>() { // from class: expo.modules.notifications.notifications.handling.NotificationsHandler$definition$lambda$3$$inlined$OnDestroy$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    NotificationManager notificationManager;
                    Map map;
                    HandlerThread handlerThread;
                    notificationManager = NotificationsHandler.this.notificationManager;
                    HandlerThread handlerThread2 = null;
                    if (notificationManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
                        notificationManager = null;
                    }
                    notificationManager.removeListener(NotificationsHandler.this);
                    map = NotificationsHandler.this.tasksMap;
                    Iterator it = map.values().iterator();
                    while (it.hasNext()) {
                        ((SingleNotificationHandlerTask) it.next()).stop();
                    }
                    handlerThread = NotificationsHandler.this.notificationsHandlerThread;
                    if (handlerThread == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("notificationsHandlerThread");
                    } else {
                        handlerThread2 = handlerThread;
                    }
                    handlerThread2.quit();
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            TypeConverterProvider converters = moduleDefinitionBuilder2.getConverters();
            AnyType[] anyTypeArr = new AnyType[2];
            AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType == null) {
                anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.handling.NotificationsHandler$definition$lambda$3$$inlined$AsyncFunctionWithPromise$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters);
            }
            anyTypeArr[0] = anyType;
            AnyType anyType2 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(NotificationBehaviorRecord.class), false));
            if (anyType2 == null) {
                anyType2 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(NotificationBehaviorRecord.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.handling.NotificationsHandler$definition$lambda$3$$inlined$AsyncFunctionWithPromise$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(NotificationBehaviorRecord.class);
                    }
                }), converters);
            }
            anyTypeArr[1] = anyType2;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("handleNotificationAsync", anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.handling.NotificationsHandler$definition$lambda$3$$inlined$AsyncFunctionWithPromise$3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    NotificationBehaviorRecord notificationBehaviorRecord = (NotificationBehaviorRecord) objArr[1];
                    NotificationsHandler.this.handleNotificationAsync((String) obj, notificationBehaviorRecord, promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder2.getAsyncFunctions().put("handleNotificationAsync", asyncFunctionWithPromiseComponent);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2 = asyncFunctionWithPromiseComponent;
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleNotificationAsync(String identifier, NotificationBehaviorRecord behavior, Promise promise) {
        SingleNotificationHandlerTask singleNotificationHandlerTask = this.tasksMap.get(identifier);
        if (singleNotificationHandlerTask == null) {
            throw new NotificationWasAlreadyHandledException(identifier);
        }
        singleNotificationHandlerTask.processNotificationWithBehavior(behavior, promise);
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationListener
    public void onNotificationReceived(Notification notification) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        Context reactContext = getAppContext().getReactContext();
        if (reactContext == null) {
            return;
        }
        INotificationContent content = notification.getNotificationRequest().getContent();
        if ((content instanceof RemoteNotificationContent) && ((RemoteNotificationContent) content).getIsDataOnly()) {
            return;
        }
        EventEmitter eventEmitter = getAppContext().eventEmitter(this);
        Handler handler = this.handler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
            handler = null;
        }
        SingleNotificationHandlerTask singleNotificationHandlerTask = new SingleNotificationHandlerTask(reactContext, eventEmitter, handler, notification, this);
        this.tasksMap.put(singleNotificationHandlerTask.getIdentifier(), singleNotificationHandlerTask);
        singleNotificationHandlerTask.start();
    }

    public final void onTaskFinished(SingleNotificationHandlerTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        this.tasksMap.remove(task.getIdentifier());
    }
}
