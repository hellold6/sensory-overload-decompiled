package expo.modules.notifications.notifications.scheduling;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import androidx.tracing.Trace;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.core.errors.InvalidArgumentException;
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
import expo.modules.notifications.UtilsKt;
import expo.modules.notifications.notifications.ArgumentsNotificationContentBuilder;
import expo.modules.notifications.notifications.NotificationSerializer;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.triggers.ChannelAwareTrigger;
import expo.modules.notifications.notifications.triggers.DailyTrigger;
import expo.modules.notifications.notifications.triggers.DateTrigger;
import expo.modules.notifications.notifications.triggers.MonthlyTrigger;
import expo.modules.notifications.notifications.triggers.TimeIntervalTrigger;
import expo.modules.notifications.notifications.triggers.WeeklyTrigger;
import expo.modules.notifications.notifications.triggers.YearlyTrigger;
import expo.modules.notifications.service.NotificationsService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KType;

/* compiled from: NotificationScheduler.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JF\u0010\n\u001a\u00020\u000b2<\u0010\f\u001a8\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\rj\u0002`\u0015H\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0004J\"\u0010\"\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u001fH\u0014J\u001c\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00120(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020#0*H\u0014R\u0014\u0010\u0004\u001a\u00020\u00058TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lexpo/modules/notifications/notifications/scheduling/NotificationScheduler;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "schedulingContext", "Landroid/content/Context;", "getSchedulingContext", "()Landroid/content/Context;", "handler", "Landroid/os/Handler;", "createResultReceiver", "Landroid/os/ResultReceiver;", "body", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "resultCode", "Landroid/os/Bundle;", "resultData", "", "Lexpo/modules/notifications/ResultReceiverBody;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "cancelScheduledNotificationAsync", NotificationsService.IDENTIFIER_KEY, "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "cancelAllScheduledNotificationsAsync", "triggerFromParams", "Lexpo/modules/notifications/notifications/interfaces/NotificationTrigger;", "params", "Lexpo/modules/core/arguments/ReadableArguments;", "createNotificationRequest", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", UriUtil.LOCAL_CONTENT_SCHEME, "Lexpo/modules/notifications/notifications/model/NotificationContent;", "notificationTrigger", "serializeScheduledNotificationRequests", "", "requests", "", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class NotificationScheduler extends Module {
    private final Handler handler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getSchedulingContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ResultReceiver createResultReceiver(Function2<? super Integer, ? super Bundle, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return UtilsKt.createDefaultResultReceiver(this.handler, body);
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent2;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        NotificationScheduler notificationScheduler = this;
        Trace.beginSection("[ExpoModulesCore] " + (notificationScheduler.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(notificationScheduler);
            moduleDefinitionBuilder.Name("ExpoNotificationScheduler");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("getAllScheduledNotificationsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        NotificationsService.INSTANCE.getAllScheduledNotifications(NotificationScheduler.this.getSchedulingContext(), NotificationScheduler.this.createResultReceiver(new NotificationScheduler$definition$1$1$1(promise, NotificationScheduler.this)));
                    }
                });
            } else {
                TypeConverterProvider converters = moduleDefinitionBuilder2.getConverters();
                AnyType[] anyTypeArr = new AnyType[1];
                AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Promise.class), false));
                if (anyType == null) {
                    anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$2
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(Promise.class);
                        }
                    }), converters);
                }
                anyTypeArr[0] = anyType;
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        NotificationsService.INSTANCE.getAllScheduledNotifications(NotificationScheduler.this.getSchedulingContext(), NotificationScheduler.this.createResultReceiver(new NotificationScheduler$definition$1$1$1((Promise) objArr[0], NotificationScheduler.this)));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    untypedAsyncFunctionComponent = new StringAsyncFunctionComponent("getAllScheduledNotificationsAsync", anyTypeArr, function1);
                                } else {
                                    untypedAsyncFunctionComponent = new UntypedAsyncFunctionComponent("getAllScheduledNotificationsAsync", anyTypeArr, function1);
                                }
                            } else {
                                untypedAsyncFunctionComponent = new FloatAsyncFunctionComponent("getAllScheduledNotificationsAsync", anyTypeArr, function1);
                            }
                        } else {
                            untypedAsyncFunctionComponent = new DoubleAsyncFunctionComponent("getAllScheduledNotificationsAsync", anyTypeArr, function1);
                        }
                    } else {
                        untypedAsyncFunctionComponent = new BoolAsyncFunctionComponent("getAllScheduledNotificationsAsync", anyTypeArr, function1);
                    }
                } else {
                    untypedAsyncFunctionComponent = new IntAsyncFunctionComponent("getAllScheduledNotificationsAsync", anyTypeArr, function1);
                }
                asyncFunctionWithPromiseComponent = untypedAsyncFunctionComponent;
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getAllScheduledNotificationsAsync", asyncFunctionWithPromiseComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            TypeConverterProvider converters2 = moduleDefinitionBuilder3.getConverters();
            AnyType[] anyTypeArr2 = new AnyType[3];
            AnyType anyType2 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType2 == null) {
                anyType2 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunctionWithPromise$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters2);
            }
            anyTypeArr2[0] = anyType2;
            AnyType anyType3 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false));
            if (anyType3 == null) {
                anyType3 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunctionWithPromise$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(ReadableArguments.class);
                    }
                }), converters2);
            }
            anyTypeArr2[1] = anyType3;
            AnyType anyType4 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true));
            if (anyType4 == null) {
                anyType4 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true, new Function0<KType>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunctionWithPromise$3
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(ReadableArguments.class);
                    }
                }), converters2);
            }
            anyTypeArr2[2] = anyType4;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("scheduleNotificationAsync", anyTypeArr2, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunctionWithPromise$4
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    ReadableArguments readableArguments = (ReadableArguments) objArr[2];
                    final String str = (String) obj;
                    try {
                        NotificationContent build = new ArgumentsNotificationContentBuilder(NotificationScheduler.this.getSchedulingContext()).setPayload((ReadableArguments) obj2).build();
                        NotificationScheduler notificationScheduler2 = NotificationScheduler.this;
                        Intrinsics.checkNotNull(build);
                        NotificationsService.INSTANCE.schedule(NotificationScheduler.this.getSchedulingContext(), notificationScheduler2.createNotificationRequest(str, build, NotificationScheduler.this.triggerFromParams(readableArguments)), NotificationScheduler.this.createResultReceiver(new Function2<Integer, Bundle, Unit>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$1$2$1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Bundle bundle) {
                                invoke(num.intValue(), bundle);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i, Bundle bundle) {
                                if (i == 0) {
                                    Promise.this.resolve(str);
                                    return;
                                }
                                Serializable serializable = bundle != null ? bundle.getSerializable(NotificationsService.EXCEPTION_KEY) : null;
                                Exception exc = serializable instanceof Exception ? (Exception) serializable : null;
                                Promise.this.reject("ERR_NOTIFICATIONS_FAILED_TO_SCHEDULE", "Failed to schedule the notification. " + (exc != null ? exc.getMessage() : null), exc);
                            }
                        }));
                    } catch (InvalidArgumentException e) {
                        promise.reject("ERR_NOTIFICATIONS_FAILED_TO_SCHEDULE", "Failed to schedule the notification. " + e.getMessage(), e);
                    } catch (NullPointerException e2) {
                        promise.reject("ERR_NOTIFICATIONS_FAILED_TO_SCHEDULE", "Failed to schedule the notification. Encountered unexpected null value. " + e2.getMessage(), e2);
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder3.getAsyncFunctions().put("scheduleNotificationAsync", asyncFunctionWithPromiseComponent3);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent4 = asyncFunctionWithPromiseComponent3;
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            TypeConverterProvider converters3 = moduleDefinitionBuilder4.getConverters();
            AnyType[] anyTypeArr3 = new AnyType[1];
            AnyType anyType5 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType5 == null) {
                anyType5 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunctionWithPromise$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters3);
            }
            anyTypeArr3[0] = anyType5;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent5 = new AsyncFunctionWithPromiseComponent("cancelScheduledNotificationAsync", anyTypeArr3, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunctionWithPromise$6
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    NotificationScheduler.this.cancelScheduledNotificationAsync((String) objArr[0], promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder4.getAsyncFunctions().put("cancelScheduledNotificationAsync", asyncFunctionWithPromiseComponent5);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent6 = asyncFunctionWithPromiseComponent5;
            ModuleDefinitionBuilder moduleDefinitionBuilder5 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("cancelAllScheduledNotificationsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$4
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        NotificationScheduler.this.cancelAllScheduledNotificationsAsync(promise);
                    }
                });
            } else {
                TypeConverterProvider converters4 = moduleDefinitionBuilder5.getConverters();
                AnyType[] anyTypeArr4 = new AnyType[1];
                AnyType anyType6 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Promise.class), false));
                if (anyType6 == null) {
                    anyType6 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$5
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(Promise.class);
                        }
                    }), converters4);
                }
                anyTypeArr4[0] = anyType6;
                Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$6
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        NotificationScheduler.this.cancelAllScheduledNotificationsAsync((Promise) objArr[0]);
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    untypedAsyncFunctionComponent2 = new StringAsyncFunctionComponent("cancelAllScheduledNotificationsAsync", anyTypeArr4, function12);
                                } else {
                                    untypedAsyncFunctionComponent2 = new UntypedAsyncFunctionComponent("cancelAllScheduledNotificationsAsync", anyTypeArr4, function12);
                                }
                            } else {
                                untypedAsyncFunctionComponent2 = new FloatAsyncFunctionComponent("cancelAllScheduledNotificationsAsync", anyTypeArr4, function12);
                            }
                        } else {
                            untypedAsyncFunctionComponent2 = new DoubleAsyncFunctionComponent("cancelAllScheduledNotificationsAsync", anyTypeArr4, function12);
                        }
                    } else {
                        untypedAsyncFunctionComponent2 = new BoolAsyncFunctionComponent("cancelAllScheduledNotificationsAsync", anyTypeArr4, function12);
                    }
                } else {
                    untypedAsyncFunctionComponent2 = new IntAsyncFunctionComponent("cancelAllScheduledNotificationsAsync", anyTypeArr4, function12);
                }
                asyncFunctionWithPromiseComponent2 = untypedAsyncFunctionComponent2;
            }
            moduleDefinitionBuilder5.getAsyncFunctions().put("cancelAllScheduledNotificationsAsync", asyncFunctionWithPromiseComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder6 = moduleDefinitionBuilder;
            TypeConverterProvider converters5 = moduleDefinitionBuilder6.getConverters();
            AnyType[] anyTypeArr5 = new AnyType[1];
            AnyType anyType7 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true));
            if (anyType7 == null) {
                anyType7 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true, new Function0<KType>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunctionWithPromise$7
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(ReadableArguments.class);
                    }
                }), converters5);
            }
            anyTypeArr5[0] = anyType7;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent7 = new AsyncFunctionWithPromiseComponent("getNextTriggerDateAsync", anyTypeArr5, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$definition$lambda$4$$inlined$AsyncFunctionWithPromise$8
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    try {
                        NotificationTrigger triggerFromParams = NotificationScheduler.this.triggerFromParams((ReadableArguments) objArr[0]);
                        if (triggerFromParams instanceof SchedulableNotificationTrigger) {
                            if (((SchedulableNotificationTrigger) triggerFromParams).nextTriggerDate() == null) {
                                promise.resolve((Object) null);
                                return;
                            } else {
                                promise.resolve(r5.getTime());
                                return;
                            }
                        }
                        String name = triggerFromParams == null ? "null" : triggerFromParams.getClass().getName();
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("It is not possible to get next trigger date for triggers other than calendar-based. Provided trigger resulted in %s trigger.", Arrays.copyOf(new Object[]{name}, 1));
                        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                        promise.reject("ERR_NOTIFICATIONS_INVALID_CALENDAR_TRIGGER", format, null);
                    } catch (InvalidArgumentException e) {
                        promise.reject("ERR_NOTIFICATIONS_FAILED_TO_GET_NEXT_TRIGGER_DATE", "Failed to get next trigger date for the trigger. " + e.getMessage(), e);
                    } catch (NullPointerException e2) {
                        promise.reject("ERR_NOTIFICATIONS_FAILED_TO_GET_NEXT_TRIGGER_DATE", "Failed to get next trigger date for the trigger. Encountered unexpected null value. " + e2.getMessage(), e2);
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder6.getAsyncFunctions().put("getNextTriggerDateAsync", asyncFunctionWithPromiseComponent7);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent8 = asyncFunctionWithPromiseComponent7;
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    public void cancelScheduledNotificationAsync(String identifier, final Promise promise) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(promise, "promise");
        NotificationsService.INSTANCE.removeScheduledNotification(getSchedulingContext(), identifier, createResultReceiver(new Function2() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit cancelScheduledNotificationAsync$lambda$5;
                cancelScheduledNotificationAsync$lambda$5 = NotificationScheduler.cancelScheduledNotificationAsync$lambda$5(Promise.this, ((Integer) obj).intValue(), (Bundle) obj2);
                return cancelScheduledNotificationAsync$lambda$5;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelScheduledNotificationAsync$lambda$5(Promise promise, int i, Bundle bundle) {
        if (i == 0) {
            promise.resolve((Object) null);
        } else {
            Serializable serializable = bundle != null ? bundle.getSerializable(NotificationsService.EXCEPTION_KEY) : null;
            promise.reject("ERR_NOTIFICATIONS_FAILED_TO_CANCEL", "Failed to cancel notification.", serializable instanceof Exception ? (Exception) serializable : null);
        }
        return Unit.INSTANCE;
    }

    public void cancelAllScheduledNotificationsAsync(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        NotificationsService.INSTANCE.removeAllScheduledNotifications(getSchedulingContext(), createResultReceiver(new Function2() { // from class: expo.modules.notifications.notifications.scheduling.NotificationScheduler$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit cancelAllScheduledNotificationsAsync$lambda$6;
                cancelAllScheduledNotificationsAsync$lambda$6 = NotificationScheduler.cancelAllScheduledNotificationsAsync$lambda$6(Promise.this, ((Integer) obj).intValue(), (Bundle) obj2);
                return cancelAllScheduledNotificationsAsync$lambda$6;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelAllScheduledNotificationsAsync$lambda$6(Promise promise, int i, Bundle bundle) {
        if (i == 0) {
            promise.resolve((Object) null);
        } else {
            Serializable serializable = bundle != null ? bundle.getSerializable(NotificationsService.EXCEPTION_KEY) : null;
            promise.reject("ERR_NOTIFICATIONS_FAILED_TO_CANCEL", "Failed to cancel all notifications.", serializable instanceof Exception ? (Exception) serializable : null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final NotificationTrigger triggerFromParams(ReadableArguments params) throws InvalidArgumentException {
        Number number;
        if (params == null) {
            return null;
        }
        String string = params.getString("channelId", null);
        String string2 = params.getString("type");
        if (string2 != null) {
            switch (string2.hashCode()) {
                case -791707519:
                    if (string2.equals("weekly")) {
                        Object obj = params.get("weekday");
                        Number number2 = obj instanceof Number ? (Number) obj : null;
                        Object obj2 = params.get("hour");
                        Number number3 = obj2 instanceof Number ? (Number) obj2 : null;
                        Object obj3 = params.get("minute");
                        number = obj3 instanceof Number ? (Number) obj3 : null;
                        if (number2 == null || number3 == null || number == null) {
                            throw new InvalidArgumentException("Invalid value(s) provided for weekly trigger.");
                        }
                        return new WeeklyTrigger(string, number2.intValue(), number3.intValue(), number.intValue());
                    }
                    break;
                case -734561654:
                    if (string2.equals("yearly")) {
                        Object obj4 = params.get("day");
                        Number number4 = obj4 instanceof Number ? (Number) obj4 : null;
                        Object obj5 = params.get("month");
                        Number number5 = obj5 instanceof Number ? (Number) obj5 : null;
                        Object obj6 = params.get("hour");
                        Number number6 = obj6 instanceof Number ? (Number) obj6 : null;
                        Object obj7 = params.get("minute");
                        number = obj7 instanceof Number ? (Number) obj7 : null;
                        if (number4 == null || number5 == null || number6 == null || number == null) {
                            throw new InvalidArgumentException("Invalid value(s) provided for yearly trigger.");
                        }
                        return new YearlyTrigger(string, number4.intValue(), number5.intValue(), number6.intValue(), number.intValue());
                    }
                    break;
                case 3076014:
                    if (string2.equals("date")) {
                        Object obj8 = params.get("timestamp");
                        number = obj8 instanceof Number ? (Number) obj8 : null;
                        if (number != null) {
                            return new DateTrigger(string, number.longValue());
                        }
                        throw new InvalidArgumentException("Invalid value provided as date of trigger.");
                    }
                    break;
                case 95346201:
                    if (string2.equals("daily")) {
                        Object obj9 = params.get("hour");
                        Number number7 = obj9 instanceof Number ? (Number) obj9 : null;
                        Object obj10 = params.get("minute");
                        number = obj10 instanceof Number ? (Number) obj10 : null;
                        if (number7 == null || number == null) {
                            throw new InvalidArgumentException("Invalid value(s) provided for daily trigger.");
                        }
                        return new DailyTrigger(string, number7.intValue(), number.intValue());
                    }
                    break;
                case 738950403:
                    if (string2.equals("channel")) {
                        return new ChannelAwareTrigger(string);
                    }
                    break;
                case 913014450:
                    if (string2.equals("timeInterval")) {
                        Object obj11 = params.get("seconds");
                        number = obj11 instanceof Number ? (Number) obj11 : null;
                        if (number != null) {
                            return new TimeIntervalTrigger(string, number.longValue(), params.getBoolean("repeats"), null, 8, null);
                        }
                        throw new InvalidArgumentException("Invalid value provided as interval of trigger.");
                    }
                    break;
                case 1236635661:
                    if (string2.equals("monthly")) {
                        Object obj12 = params.get("day");
                        Number number8 = obj12 instanceof Number ? (Number) obj12 : null;
                        Object obj13 = params.get("hour");
                        Number number9 = obj13 instanceof Number ? (Number) obj13 : null;
                        Object obj14 = params.get("minute");
                        number = obj14 instanceof Number ? (Number) obj14 : null;
                        if (number8 == null || number9 == null || number == null) {
                            throw new InvalidArgumentException("Invalid value(s) provided for yearly trigger.");
                        }
                        return new MonthlyTrigger(string, number8.intValue(), number9.intValue(), number.intValue());
                    }
                    break;
            }
        }
        throw new InvalidArgumentException("Trigger of type: " + string2 + " is not supported on Android.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NotificationRequest createNotificationRequest(String identifier, NotificationContent content, NotificationTrigger notificationTrigger) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(content, "content");
        return new NotificationRequest(identifier, content, notificationTrigger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<Bundle> serializeScheduledNotificationRequests(Collection<? extends NotificationRequest> requests) {
        Intrinsics.checkNotNullParameter(requests, "requests");
        Collection<? extends NotificationRequest> collection = requests;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(NotificationSerializer.toBundle((NotificationRequest) it.next()));
        }
        return arrayList;
    }
}
