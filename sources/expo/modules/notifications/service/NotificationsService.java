package expo.modules.notifications.service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.core.app.RemoteInput;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.C;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationAction;
import expo.modules.notifications.notifications.model.NotificationBehaviorRecord;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.notifications.model.TextInputNotificationAction;
import expo.modules.notifications.notifications.model.TextInputNotificationResponse;
import expo.modules.notifications.service.delegates.ExpoCategoriesDelegate;
import expo.modules.notifications.service.delegates.ExpoHandlingDelegate;
import expo.modules.notifications.service.delegates.ExpoPresentationDelegate;
import expo.modules.notifications.service.delegates.ExpoSchedulingDelegate;
import expo.modules.notifications.service.interfaces.CategoriesDelegate;
import expo.modules.notifications.service.interfaces.HandlingDelegate;
import expo.modules.notifications.service.interfaces.PresentationDelegate;
import expo.modules.notifications.service.interfaces.SchedulingDelegate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationsService.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0016\u0018\u0000 %2\u00020\u0001:\u0001%B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0014J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0014J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0014J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010 \u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010!\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010#\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010$\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006&"}, d2 = {"Lexpo/modules/notifications/service/NotificationsService;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "getPresentationDelegate", "Lexpo/modules/notifications/service/interfaces/PresentationDelegate;", "context", "Landroid/content/Context;", "getHandlingDelegate", "Lexpo/modules/notifications/service/interfaces/HandlingDelegate;", "getCategoriesDelegate", "Lexpo/modules/notifications/service/interfaces/CategoriesDelegate;", "getSchedulingDelegate", "Lexpo/modules/notifications/service/interfaces/SchedulingDelegate;", "onReceive", "", "intent", "Landroid/content/Intent;", "handleIntent", "onPresentNotification", "onGetAllPresentedNotifications", "Landroid/os/Bundle;", "onDismissNotifications", "onDismissAllNotifications", "onReceiveNotification", "onReceiveNotificationResponse", "onNotificationsDropped", "onGetCategories", "onSetCategory", "onDeleteCategory", "onGetAllScheduledNotifications", "onGetScheduledNotification", "onScheduleNotification", "onNotificationTriggered", "onRemoveScheduledNotifications", "onRemoveAllScheduledNotifications", "onSetupScheduledNotifications", "Companion", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class NotificationsService extends BroadcastReceiver {
    private static final String DELETE_CATEGORY_TYPE = "deleteCategory";
    private static final String DISMISS_ALL_TYPE = "dismissAll";
    private static final String DISMISS_SELECTED_TYPE = "dismissSelected";
    private static final String DROPPED_TYPE = "dropped";
    public static final int ERROR_CODE = 1;
    public static final String EVENT_TYPE_KEY = "type";
    public static final String EXCEPTION_KEY = "exception";
    private static final String GET_ALL_DISPLAYED_TYPE = "getAllDisplayed";
    private static final String GET_ALL_SCHEDULED_TYPE = "getAllScheduled";
    private static final String GET_CATEGORIES_TYPE = "getCategories";
    private static final String GET_SCHEDULED_TYPE = "getScheduled";
    public static final String IDENTIFIERS_KEY = "identifiers";
    public static final String IDENTIFIER_KEY = "identifier";
    public static final String NOTIFICATIONS_KEY = "notifications";
    public static final String NOTIFICATION_ACTION_KEY = "notificationAction";
    public static final String NOTIFICATION_BEHAVIOR_KEY = "notificationBehavior";
    public static final String NOTIFICATION_CATEGORIES_KEY = "notificationCategories";
    public static final String NOTIFICATION_CATEGORY_KEY = "notificationCategory";
    public static final String NOTIFICATION_EVENT_ACTION = "expo.modules.notifications.NOTIFICATION_EVENT";
    public static final String NOTIFICATION_KEY = "notification";
    public static final String NOTIFICATION_REQUESTS_KEY = "notificationRequests";
    public static final String NOTIFICATION_REQUEST_KEY = "notificationRequest";
    public static final String NOTIFICATION_RESPONSE_KEY = "notificationResponse";
    private static final String PRESENT_TYPE = "present";
    public static final String RECEIVER_KEY = "receiver";
    private static final String RECEIVE_RESPONSE_TYPE = "receiveResponse";
    private static final String RECEIVE_TYPE = "receive";
    private static final String REMOVE_ALL_TYPE = "removeAll";
    private static final String REMOVE_SELECTED_TYPE = "removeSelected";
    private static final String SCHEDULE_TYPE = "schedule";
    private static final String SET_CATEGORY_TYPE = "setCategory";
    public static final String SUCCEEDED_KEY = "succeeded";
    public static final int SUCCESS_CODE = 0;
    public static final String TEXT_INPUT_NOTIFICATION_RESPONSE_KEY = "textInputNotificationResponse";
    private static final String TRIGGER_TYPE = "trigger";
    public static final String USER_TEXT_RESPONSE_KEY = "userTextResponse";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<String> SETUP_ACTIONS = CollectionsKt.listOf((Object[]) new String[]{"android.intent.action.BOOT_COMPLETED", "android.intent.action.REBOOT", "android.intent.action.MY_PACKAGE_REPLACED", "android.intent.action.QUICKBOOT_POWERON", "com.htc.intent.action.QUICKBOOT_POWERON"});

    /* compiled from: NotificationsService.kt */
    @Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\n\b\u0002\u00102\u001a\u0004\u0018\u000103J.\u00104\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00105\u001a\u0002062\n\b\u0002\u00107\u001a\u0004\u0018\u0001082\n\b\u0002\u00102\u001a\u0004\u0018\u000103J\"\u00109\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00105\u001a\u0002062\n\b\u0002\u00102\u001a\u0004\u0018\u000103J-\u0010:\u001a\u00020/2\u0006\u00100\u001a\u0002012\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00050<2\n\b\u0002\u00102\u001a\u0004\u0018\u000103¢\u0006\u0002\u0010=J\u001a\u0010>\u001a\u00020/2\u0006\u00100\u001a\u0002012\n\b\u0002\u00102\u001a\u0004\u0018\u000103J\u000e\u0010?\u001a\u00020/2\u0006\u00100\u001a\u000201J\u001a\u0010@\u001a\u00020/2\u0006\u00100\u001a\u0002012\n\b\u0002\u00102\u001a\u0004\u0018\u000103J\"\u0010A\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010B\u001a\u00020C2\n\b\u0002\u00102\u001a\u0004\u0018\u000103J\"\u0010D\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u00052\n\b\u0002\u00102\u001a\u0004\u0018\u000103J\u001a\u0010F\u001a\u00020/2\u0006\u00100\u001a\u0002012\n\b\u0002\u0010G\u001a\u0004\u0018\u000103J$\u0010H\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u00052\n\b\u0002\u0010G\u001a\u0004\u0018\u000103H\u0007J\"\u0010I\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010J\u001a\u00020K2\n\b\u0002\u0010G\u001a\u0004\u0018\u000103J\"\u0010L\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u00052\n\b\u0002\u0010G\u001a\u0004\u0018\u000103J(\u0010M\u001a\u00020/2\u0006\u00100\u001a\u0002012\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00050N2\n\b\u0002\u0010G\u001a\u0004\u0018\u000103J\u001a\u0010O\u001a\u00020/2\u0006\u00100\u001a\u0002012\n\b\u0002\u0010G\u001a\u0004\u0018\u000103J\u0016\u0010P\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010Q\u001a\u00020RJ\b\u0010S\u001a\u00020TH\u0004J\u0010\u0010U\u001a\u00020T2\u0006\u0010E\u001a\u00020\u0005H\u0004J\u0018\u0010V\u001a\u0004\u0018\u00010W2\u0006\u00100\u001a\u0002012\u0006\u0010Q\u001a\u00020RJ\u0016\u0010X\u001a\u00020Y2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u0005J\u001e\u0010Z\u001a\u00020Y2\u0006\u00100\u001a\u0002012\u0006\u00105\u001a\u0002062\u0006\u0010[\u001a\u00020\\J\u0018\u0010]\u001a\u00020R2\u0006\u00100\u001a\u0002012\b\u0010Q\u001a\u0004\u0018\u00010RJ\u000e\u0010^\u001a\u00020_2\u0006\u0010Q\u001a\u00020RJ\u0012\u0010`\u001a\u0004\u0018\u00010_2\u0006\u0010Q\u001a\u00020RH\u0007J\u0016\u0010a\u001a\u00020/2\u0006\u0010Q\u001a\u00020R2\u0006\u0010b\u001a\u00020_J\u0012\u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010e\u001a\u00020fH\u0002J-\u0010g\u001a\u0004\u0018\u0001Hh\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\b\u0010k\u001a\u0004\u0018\u00010dH\u0002¢\u0006\u0002\u0010lR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006m"}, d2 = {"Lexpo/modules/notifications/service/NotificationsService$Companion;", "", "<init>", "()V", "NOTIFICATION_EVENT_ACTION", "", "SETUP_ACTIONS", "", "getSETUP_ACTIONS", "()Ljava/util/List;", "USER_TEXT_RESPONSE_KEY", "GET_ALL_DISPLAYED_TYPE", "PRESENT_TYPE", "DISMISS_SELECTED_TYPE", "DISMISS_ALL_TYPE", "RECEIVE_TYPE", "RECEIVE_RESPONSE_TYPE", "DROPPED_TYPE", "GET_CATEGORIES_TYPE", "SET_CATEGORY_TYPE", "DELETE_CATEGORY_TYPE", "SCHEDULE_TYPE", "TRIGGER_TYPE", "GET_ALL_SCHEDULED_TYPE", "GET_SCHEDULED_TYPE", "REMOVE_SELECTED_TYPE", "REMOVE_ALL_TYPE", "SUCCESS_CODE", "", "ERROR_CODE", "EVENT_TYPE_KEY", "EXCEPTION_KEY", "RECEIVER_KEY", "NOTIFICATION_KEY", "NOTIFICATION_RESPONSE_KEY", "TEXT_INPUT_NOTIFICATION_RESPONSE_KEY", "SUCCEEDED_KEY", "IDENTIFIERS_KEY", "IDENTIFIER_KEY", "NOTIFICATION_BEHAVIOR_KEY", "NOTIFICATIONS_KEY", "NOTIFICATION_CATEGORY_KEY", "NOTIFICATION_CATEGORIES_KEY", "NOTIFICATION_REQUEST_KEY", "NOTIFICATION_REQUESTS_KEY", "NOTIFICATION_ACTION_KEY", "getAllPresented", "", "context", "Landroid/content/Context;", NotificationsService.RECEIVER_KEY, "Landroid/os/ResultReceiver;", NotificationsService.PRESENT_TYPE, NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "behavior", "Lexpo/modules/notifications/notifications/model/NotificationBehaviorRecord;", NotificationsService.RECEIVE_TYPE, "dismiss", NotificationsService.IDENTIFIERS_KEY, "", "(Landroid/content/Context;[Ljava/lang/String;Landroid/os/ResultReceiver;)V", NotificationsService.DISMISS_ALL_TYPE, "handleDropped", NotificationsService.GET_CATEGORIES_TYPE, NotificationsService.SET_CATEGORY_TYPE, "category", "Lexpo/modules/notifications/notifications/model/NotificationCategory;", NotificationsService.DELETE_CATEGORY_TYPE, NotificationsService.IDENTIFIER_KEY, "getAllScheduledNotifications", "resultReceiver", "getScheduledNotification", NotificationsService.SCHEDULE_TYPE, NotificationsService.NOTIFICATION_REQUEST_KEY, "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "removeScheduledNotification", "removeScheduledNotifications", "", "removeAllScheduledNotifications", "doWork", "intent", "Landroid/content/Intent;", "getUriBuilder", "Landroid/net/Uri$Builder;", "getUriBuilderForIdentifier", "findDesignatedBroadcastReceiver", "Landroid/content/pm/ActivityInfo;", "createNotificationTrigger", "Landroid/app/PendingIntent;", "createNotificationResponseIntent", "action", "Lexpo/modules/notifications/notifications/model/NotificationAction;", "createNotificationResponseBroadcastIntent", "getNotificationResponseFromBroadcastIntent", "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "getNotificationResponseFromOpenIntent", "setNotificationResponseToIntent", NotificationsService.NOTIFICATION_RESPONSE_KEY, "marshalObject", "", "objectToMarshal", "Landroid/os/Parcelable;", "unmarshalObject", ExifInterface.GPS_DIRECTION_TRUE, "creator", "Landroid/os/Parcelable$Creator;", "byteArray", "(Landroid/os/Parcelable$Creator;[B)Ljava/lang/Object;", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getSETUP_ACTIONS() {
            return NotificationsService.SETUP_ACTIONS;
        }

        public static /* synthetic */ void getAllPresented$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.getAllPresented(context, resultReceiver);
        }

        public final void getAllPresented(Context context, ResultReceiver r5) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().build());
            intent.putExtra("type", NotificationsService.GET_ALL_DISPLAYED_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, r5);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void present$default(Companion companion, Context context, Notification notification, NotificationBehaviorRecord notificationBehaviorRecord, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                notificationBehaviorRecord = null;
            }
            if ((i & 8) != 0) {
                resultReceiver = null;
            }
            companion.present(context, notification, notificationBehaviorRecord, resultReceiver);
        }

        public final void present(Context context, Notification notification, NotificationBehaviorRecord notificationBehaviorRecord, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notification, "notification");
            String identifier = notification.getNotificationRequest().getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilderForIdentifier(identifier).appendPath(NotificationsService.PRESENT_TYPE).build());
            intent.putExtra("type", NotificationsService.PRESENT_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent.putExtra(NotificationsService.NOTIFICATION_BEHAVIOR_KEY, notificationBehaviorRecord);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void receive$default(Companion companion, Context context, Notification notification, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.receive(context, notification, resultReceiver);
        }

        public final void receive(Context context, Notification notification, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notification, "notification");
            String identifier = notification.getNotificationRequest().getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilderForIdentifier(identifier).appendPath(NotificationsService.RECEIVE_TYPE).build());
            intent.putExtra("type", NotificationsService.RECEIVE_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void dismiss$default(Companion companion, Context context, String[] strArr, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.dismiss(context, strArr, resultReceiver);
        }

        public final void dismiss(Context context, String[] r6, ResultReceiver r7) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(r6, "identifiers");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("dismiss").build());
            intent.putExtra("type", NotificationsService.DISMISS_SELECTED_TYPE);
            intent.putExtra(NotificationsService.IDENTIFIERS_KEY, r6);
            intent.putExtra(NotificationsService.RECEIVER_KEY, r7);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void dismissAll$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.dismissAll(context, resultReceiver);
        }

        public final void dismissAll(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("dismiss").build());
            intent.putExtra("type", NotificationsService.DISMISS_ALL_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public final void handleDropped(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION);
            intent.putExtra("type", NotificationsService.DROPPED_TYPE);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void getCategories$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.getCategories(context, resultReceiver);
        }

        public final void getCategories(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("categories").build());
            intent.putExtra("type", NotificationsService.GET_CATEGORIES_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void setCategory$default(Companion companion, Context context, NotificationCategory notificationCategory, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.setCategory(context, notificationCategory, resultReceiver);
        }

        public final void setCategory(Context context, NotificationCategory category, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(category, "category");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("categories").appendPath(category.getIdentifier()).build());
            intent.putExtra("type", NotificationsService.SET_CATEGORY_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_CATEGORY_KEY, (Parcelable) category);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void deleteCategory$default(Companion companion, Context context, String str, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.deleteCategory(context, str, resultReceiver);
        }

        public final void deleteCategory(Context context, String identifier, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("categories").appendPath(identifier).build());
            intent.putExtra("type", NotificationsService.DELETE_CATEGORY_TYPE);
            intent.putExtra(NotificationsService.IDENTIFIER_KEY, identifier);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void getAllScheduledNotifications$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.getAllScheduledNotifications(context, resultReceiver);
        }

        public final void getAllScheduledNotifications(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION);
            intent.putExtra("type", NotificationsService.GET_ALL_SCHEDULED_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void getScheduledNotification$default(Companion companion, Context context, String str, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.getScheduledNotification(context, str, resultReceiver);
        }

        public final void getScheduledNotification(Context context, String r6, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(r6, "identifier");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").appendPath(r6).build());
            intent.putExtra("type", NotificationsService.GET_SCHEDULED_TYPE);
            intent.putExtra(NotificationsService.IDENTIFIER_KEY, r6);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void schedule$default(Companion companion, Context context, NotificationRequest notificationRequest, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.schedule(context, notificationRequest, resultReceiver);
        }

        public final void schedule(Context context, NotificationRequest notificationRequest, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notificationRequest, "notificationRequest");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").appendPath(notificationRequest.getIdentifier()).build());
            intent.putExtra("type", NotificationsService.SCHEDULE_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_REQUEST_KEY, (Parcelable) notificationRequest);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void removeScheduledNotification$default(Companion companion, Context context, String str, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.removeScheduledNotification(context, str, resultReceiver);
        }

        public final void removeScheduledNotification(Context context, String r3, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(r3, "identifier");
            removeScheduledNotifications(context, CollectionsKt.listOf(r3), resultReceiver);
        }

        public static /* synthetic */ void removeScheduledNotifications$default(Companion companion, Context context, Collection collection, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.removeScheduledNotifications(context, collection, resultReceiver);
        }

        public final void removeScheduledNotifications(Context context, Collection<String> r6, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(r6, "identifiers");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").build());
            intent.putExtra("type", NotificationsService.REMOVE_SELECTED_TYPE);
            intent.putExtra(NotificationsService.IDENTIFIERS_KEY, (String[]) r6.toArray(new String[0]));
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void removeAllScheduledNotifications$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.removeAllScheduledNotifications(context, resultReceiver);
        }

        public final void removeAllScheduledNotifications(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION);
            intent.putExtra("type", NotificationsService.REMOVE_ALL_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public final void doWork(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            ActivityInfo findDesignatedBroadcastReceiver = findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
                context.sendBroadcast(intent);
            } else {
                Log.e("expo-notifications", "No service capable of handling notifications found (intent = " + intent.getAction() + "). Ensure that you have configured your AndroidManifest.xml properly.");
            }
        }

        protected final Uri.Builder getUriBuilder() {
            Uri.Builder buildUpon = Uri.parse("expo-notifications://notifications/").buildUpon();
            Intrinsics.checkNotNullExpressionValue(buildUpon, "buildUpon(...)");
            return buildUpon;
        }

        protected final Uri.Builder getUriBuilderForIdentifier(String r2) {
            Intrinsics.checkNotNullParameter(r2, "identifier");
            Uri.Builder appendPath = getUriBuilder().appendPath(r2);
            Intrinsics.checkNotNullExpressionValue(appendPath, "appendPath(...)");
            return appendPath;
        }

        public final ActivityInfo findDesignatedBroadcastReceiver(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intent intent2 = new Intent(intent.getAction()).setPackage(context.getPackageName());
            Intrinsics.checkNotNullExpressionValue(intent2, "setPackage(...)");
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent2, 0);
            Intrinsics.checkNotNullExpressionValue(queryBroadcastReceivers, "queryBroadcastReceivers(...)");
            ResolveInfo resolveInfo = (ResolveInfo) CollectionsKt.firstOrNull((List) queryBroadcastReceivers);
            if (resolveInfo != null) {
                return resolveInfo.activityInfo;
            }
            return null;
        }

        public final PendingIntent createNotificationTrigger(Context context, String r8) {
            String className;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(r8, "identifier");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").appendPath(r8).appendPath(NotificationsService.TRIGGER_TYPE).build());
            ActivityInfo findDesignatedBroadcastReceiver = NotificationsService.INSTANCE.findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
            }
            intent.putExtra("type", NotificationsService.TRIGGER_TYPE);
            intent.putExtra(NotificationsService.IDENTIFIER_KEY, r8);
            int i = Build.VERSION.SDK_INT >= 31 ? 33554432 : 0;
            ComponentName component = intent.getComponent();
            PendingIntent broadcast = PendingIntent.getBroadcast(context, (component == null || (className = component.getClassName()) == null) ? NotificationsService.class.hashCode() : className.hashCode(), intent, i | C.BUFFER_FLAG_FIRST_SAMPLE);
            Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
            return broadcast;
        }

        public final PendingIntent createNotificationResponseIntent(Context context, Notification r7, NotificationAction action) {
            String className;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(r7, "notification");
            Intrinsics.checkNotNullParameter(action, "action");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath(r7.getNotificationRequest().getIdentifier()).appendPath("actions").appendPath(action.getIdentifier()).build());
            ActivityInfo findDesignatedBroadcastReceiver = NotificationsService.INSTANCE.findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
            }
            intent.putExtra("type", NotificationsService.RECEIVE_RESPONSE_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, r7);
            intent.putExtra(NotificationsService.NOTIFICATION_ACTION_KEY, (Parcelable) action);
            if (action.opensAppToForeground() && Build.VERSION.SDK_INT >= 31) {
                return ExpoHandlingDelegate.INSTANCE.createPendingIntentForOpeningApp(context, intent);
            }
            int i = Build.VERSION.SDK_INT >= 31 ? 33554432 : 0;
            ComponentName component = intent.getComponent();
            PendingIntent broadcast = PendingIntent.getBroadcast(context, (component == null || (className = component.getClassName()) == null) ? NotificationsService.class.hashCode() : className.hashCode(), intent, i | C.BUFFER_FLAG_FIRST_SAMPLE);
            Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
            return broadcast;
        }

        public final Intent createNotificationResponseBroadcastIntent(Context context, Intent intent) {
            TextInputNotificationAction notificationAction;
            Intrinsics.checkNotNullParameter(context, "context");
            Bundle extras = intent != null ? intent.getExtras() : null;
            Notification notification = extras != null ? (Notification) extras.getParcelable(NotificationsService.NOTIFICATION_KEY) : null;
            NotificationAction notificationAction2 = extras != null ? (NotificationAction) extras.getParcelable(NotificationsService.NOTIFICATION_ACTION_KEY) : null;
            if (notification == null || notificationAction2 == null) {
                throw new IllegalArgumentException("notification (" + notification + ") and action (" + notificationAction2 + ") should not be null");
            }
            Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
            String string = resultsFromIntent != null ? resultsFromIntent.getString(NotificationsService.USER_TEXT_RESPONSE_KEY) : null;
            boolean z = string != null && (notificationAction2 instanceof TextInputNotificationAction);
            if (z) {
                TextInputNotificationAction textInputNotificationAction = (TextInputNotificationAction) notificationAction2;
                notificationAction = new TextInputNotificationAction(textInputNotificationAction.getIdentifier(), textInputNotificationAction.getTitle(), false, textInputNotificationAction.getPlaceholder());
            } else {
                notificationAction = new NotificationAction(notificationAction2.getIdentifier(), notificationAction2.getTitle(), false);
            }
            Intent intent2 = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath(notification.getNotificationRequest().getIdentifier()).appendPath("actions").appendPath(notificationAction.getIdentifier()).build());
            ActivityInfo findDesignatedBroadcastReceiver = NotificationsService.INSTANCE.findDesignatedBroadcastReceiver(context, intent2);
            if (findDesignatedBroadcastReceiver != null) {
                intent2.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
            }
            intent2.putExtra("type", NotificationsService.RECEIVE_RESPONSE_TYPE);
            intent2.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent2.putExtra(NotificationsService.NOTIFICATION_ACTION_KEY, (Parcelable) notificationAction);
            if (z) {
                RemoteInput build = new RemoteInput.Builder(NotificationsService.USER_TEXT_RESPONSE_KEY).build();
                Intrinsics.checkNotNullExpressionValue(build, "build(...)");
                Bundle bundle = new Bundle();
                bundle.putString(NotificationsService.USER_TEXT_RESPONSE_KEY, string);
                RemoteInput.addResultsToIntent(new RemoteInput[]{build}, intent2, bundle);
            }
            return intent2;
        }

        public final NotificationResponse getNotificationResponseFromBroadcastIntent(Intent intent) {
            String str;
            Intrinsics.checkNotNullParameter(intent, "intent");
            Notification notification = (Notification) intent.getParcelableExtra(NotificationsService.NOTIFICATION_KEY);
            if (notification == null) {
                throw new IllegalArgumentException("notification not found in the intent extras.");
            }
            NotificationAction notificationAction = (NotificationAction) intent.getParcelableExtra(NotificationsService.NOTIFICATION_ACTION_KEY);
            if (notificationAction == null) {
                throw new IllegalArgumentException("notificationAction not found in the intent extras.");
            }
            if (notificationAction instanceof TextInputNotificationAction) {
                Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
                if (resultsFromIntent == null || (str = resultsFromIntent.getString(NotificationsService.USER_TEXT_RESPONSE_KEY)) == null) {
                    str = "";
                }
                return new TextInputNotificationResponse(notificationAction, notification, str);
            }
            return new NotificationResponse(notificationAction, notification);
        }

        public final NotificationResponse getNotificationResponseFromOpenIntent(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            byte[] byteArrayExtra = intent.getByteArrayExtra(NotificationsService.NOTIFICATION_RESPONSE_KEY);
            if (byteArrayExtra != null) {
                Companion companion = NotificationsService.INSTANCE;
                Parcelable.Creator<NotificationResponse> CREATOR = NotificationResponse.CREATOR;
                Intrinsics.checkNotNullExpressionValue(CREATOR, "CREATOR");
                return (NotificationResponse) companion.unmarshalObject(CREATOR, byteArrayExtra);
            }
            byte[] byteArrayExtra2 = intent.getByteArrayExtra(NotificationsService.TEXT_INPUT_NOTIFICATION_RESPONSE_KEY);
            if (byteArrayExtra2 == null) {
                return null;
            }
            Companion companion2 = NotificationsService.INSTANCE;
            Parcelable.Creator<TextInputNotificationResponse> CREATOR2 = TextInputNotificationResponse.CREATOR;
            Intrinsics.checkNotNullExpressionValue(CREATOR2, "CREATOR");
            return (NotificationResponse) companion2.unmarshalObject(CREATOR2, byteArrayExtra2);
        }

        public final void setNotificationResponseToIntent(Intent intent, NotificationResponse r4) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            String str = NotificationsService.NOTIFICATION_RESPONSE_KEY;
            Intrinsics.checkNotNullParameter(r4, "notificationResponse");
            try {
                if (r4 instanceof TextInputNotificationResponse) {
                    str = NotificationsService.TEXT_INPUT_NOTIFICATION_RESPONSE_KEY;
                }
                Intrinsics.checkNotNull(intent.putExtra(str, marshalObject(r4)));
            } catch (Exception e) {
                Log.e("expo-notifications", "Could not marshal notification response: " + r4.getActionIdentifier() + ".");
                e.printStackTrace();
            }
        }

        private final byte[] marshalObject(Parcelable objectToMarshal) {
            Parcel obtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain(...)");
            objectToMarshal.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            Intrinsics.checkNotNullExpressionValue(marshall, "marshall(...)");
            obtain.recycle();
            return marshall;
        }

        private final <T> T unmarshalObject(Parcelable.Creator<T> creator, byte[] byteArray) {
            if (byteArray == null) {
                return null;
            }
            try {
                Parcel obtain = Parcel.obtain();
                Intrinsics.checkNotNullExpressionValue(obtain, "obtain(...)");
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                T createFromParcel = creator.createFromParcel(obtain);
                obtain.recycle();
                return createFromParcel;
            } catch (Exception e) {
                Log.e("expo-notifications", "Could not unmarshall NotificationResponse from Intent.extra.", e);
                return null;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected PresentationDelegate getPresentationDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoPresentationDelegate(context, null, 2, 0 == true ? 1 : 0);
    }

    protected HandlingDelegate getHandlingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoHandlingDelegate(context);
    }

    protected CategoriesDelegate getCategoriesDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoCategoriesDelegate(context);
    }

    protected SchedulingDelegate getSchedulingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoSchedulingDelegate(context);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        final BroadcastReceiver.PendingResult goAsync = goAsync();
        ThreadsKt.thread$default(false, false, null, null, 0, new Function0() { // from class: expo.modules.notifications.service.NotificationsService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onReceive$lambda$1;
                onReceive$lambda$1 = NotificationsService.onReceive$lambda$1(intent, goAsync, this, context);
                return onReceive$lambda$1;
            }
        }, 31, null);
    }

    public static final Unit onReceive$lambda$1(Intent intent, BroadcastReceiver.PendingResult pendingResult, NotificationsService notificationsService, Context context) {
        if (intent != null) {
            try {
                notificationsService.handleIntent(context, intent);
            } catch (Throwable th) {
                pendingResult.finish();
                throw th;
            }
        }
        pendingResult.finish();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x004b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:24:0x011e A[Catch: Exception -> 0x013c, TryCatch #0 {Exception -> 0x013c, blocks: (B:15:0x003f, B:17:0x0047, B:18:0x004b, B:20:0x0050, B:22:0x0058, B:24:0x011e, B:27:0x005e, B:29:0x0066, B:30:0x006b, B:32:0x0073, B:33:0x0078, B:35:0x0080, B:36:0x0085, B:38:0x008d, B:39:0x0093, B:41:0x009b, B:42:0x00a0, B:44:0x00a8, B:45:0x00ae, B:47:0x00b6, B:48:0x00ba, B:50:0x00c2, B:51:0x00c6, B:53:0x00ce, B:54:0x00d2, B:56:0x00da, B:57:0x00de, B:59:0x00e6, B:60:0x00ea, B:62:0x00f2, B:63:0x00f7, B:65:0x00ff, B:66:0x0104, B:68:0x010c, B:69:0x0111, B:71:0x0119, B:72:0x0125, B:73:0x013b), top: B:14:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleIntent(android.content.Context r7, android.content.Intent r8) {
        /*
            Method dump skipped, instructions count: 476
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.NotificationsService.handleIntent(android.content.Context, android.content.Intent):void");
    }

    public void onPresentNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        PresentationDelegate presentationDelegate = getPresentationDelegate(context);
        Bundle extras = intent.getExtras();
        Notification notification = extras != null ? (Notification) extras.getParcelable(NOTIFICATION_KEY) : null;
        Intrinsics.checkNotNull(notification);
        Bundle extras2 = intent.getExtras();
        presentationDelegate.presentNotification(notification, extras2 != null ? (NotificationBehaviorRecord) extras2.getParcelable(NOTIFICATION_BEHAVIOR_KEY) : null);
    }

    public Bundle onGetAllPresentedNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NOTIFICATIONS_KEY, new ArrayList<>(getPresentationDelegate(context).getAllPresentedNotifications()));
        return bundle;
    }

    public void onDismissNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        PresentationDelegate presentationDelegate = getPresentationDelegate(context);
        Bundle extras = intent.getExtras();
        String[] stringArray = extras != null ? extras.getStringArray(IDENTIFIERS_KEY) : null;
        Intrinsics.checkNotNull(stringArray);
        presentationDelegate.dismissNotifications(ArraysKt.asList(stringArray));
    }

    public void onDismissAllNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getPresentationDelegate(context).dismissAllNotifications();
    }

    public void onReceiveNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        HandlingDelegate handlingDelegate = getHandlingDelegate(context);
        Parcelable parcelableExtra = intent.getParcelableExtra(NOTIFICATION_KEY);
        Intrinsics.checkNotNull(parcelableExtra);
        handlingDelegate.handleNotification((Notification) parcelableExtra);
    }

    public void onReceiveNotificationResponse(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getHandlingDelegate(context).handleNotificationResponse(INSTANCE.getNotificationResponseFromBroadcastIntent(intent));
    }

    public void onNotificationsDropped(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getHandlingDelegate(context).handleNotificationsDropped();
    }

    public Bundle onGetCategories(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NOTIFICATION_CATEGORIES_KEY, new ArrayList<>(getCategoriesDelegate(context).getCategories()));
        return bundle;
    }

    public Bundle onSetCategory(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        CategoriesDelegate categoriesDelegate = getCategoriesDelegate(context);
        Parcelable parcelableExtra = intent.getParcelableExtra(NOTIFICATION_CATEGORY_KEY);
        Intrinsics.checkNotNull(parcelableExtra);
        bundle.putParcelable(NOTIFICATION_CATEGORY_KEY, categoriesDelegate.setCategory((NotificationCategory) parcelableExtra));
        return bundle;
    }

    public Bundle onDeleteCategory(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        CategoriesDelegate categoriesDelegate = getCategoriesDelegate(context);
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString(IDENTIFIER_KEY) : null;
        Intrinsics.checkNotNull(string);
        bundle.putBoolean(SUCCEEDED_KEY, categoriesDelegate.deleteCategory(string));
        return bundle;
    }

    public Bundle onGetAllScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NOTIFICATION_REQUESTS_KEY, new ArrayList<>(getSchedulingDelegate(context).getAllScheduledNotifications()));
        return bundle;
    }

    public Bundle onGetScheduledNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString(IDENTIFIER_KEY) : null;
        Intrinsics.checkNotNull(string);
        bundle.putParcelable(NOTIFICATION_REQUEST_KEY, schedulingDelegate.getScheduledNotification(string));
        return bundle;
    }

    public void onScheduleNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        NotificationRequest notificationRequest = extras != null ? (NotificationRequest) extras.getParcelable(NOTIFICATION_REQUEST_KEY) : null;
        Intrinsics.checkNotNull(notificationRequest);
        schedulingDelegate.scheduleNotification(notificationRequest);
    }

    public void onNotificationTriggered(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString(IDENTIFIER_KEY) : null;
        Intrinsics.checkNotNull(string);
        schedulingDelegate.triggerNotification(string);
    }

    public void onRemoveScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        String[] stringArray = extras != null ? extras.getStringArray(IDENTIFIERS_KEY) : null;
        Intrinsics.checkNotNull(stringArray);
        schedulingDelegate.removeScheduledNotifications(ArraysKt.asList(stringArray));
    }

    public void onRemoveAllScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getSchedulingDelegate(context).removeAllScheduledNotifications();
    }

    public void onSetupScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getSchedulingDelegate(context).setupScheduledNotifications();
    }
}
