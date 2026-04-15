package expo.modules.notifications.notifications.presentation.builders;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Parcel;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import com.facebook.common.util.UriUtil;
import expo.modules.notifications.notifications.SoundResolver;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import expo.modules.notifications.notifications.interfaces.INotificationContent;
import expo.modules.notifications.notifications.model.NotificationAction;
import expo.modules.notifications.notifications.model.NotificationBehaviorRecord;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.model.TextInputNotificationAction;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.delegates.SharedPreferencesNotificationCategoriesStore;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: ExpoNotificationBuilder.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0004\n\u0002\b\u0004\b\u0016\u0018\u0000 12\u00020\u0001:\u00011B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0004J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0015H\u0004J\u000e\u0010\u0016\u001a\u00020\u0017H\u0096@¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0004J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0016\u0010'\u001a\u0004\u0018\u00010(8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020$8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b,\u0010&R\u0016\u0010-\u001a\u0004\u0018\u00010.8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b/\u00100¨\u00062"}, d2 = {"Lexpo/modules/notifications/notifications/presentation/builders/ExpoNotificationBuilder;", "Lexpo/modules/notifications/notifications/presentation/builders/BaseNotificationBuilder;", "context", "Landroid/content/Context;", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "store", "Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationCategoriesStore;", "<init>", "(Landroid/content/Context;Lexpo/modules/notifications/notifications/model/Notification;Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationCategoriesStore;)V", "addActionsToBuilder", "", "builder", "Landroidx/core/app/NotificationCompat$Builder;", "categoryIdentifier", "", "buildButtonAction", "Landroidx/core/app/NotificationCompat$Action;", "action", "Lexpo/modules/notifications/notifications/model/NotificationAction;", "buildTextInputAction", "Lexpo/modules/notifications/notifications/model/TextInputNotificationAction;", "build", "Landroid/app/Notification;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "applySoundsAndVibrations", UriUtil.LOCAL_CONTENT_SCHEME, "Lexpo/modules/notifications/notifications/interfaces/INotificationContent;", "marshallNotificationRequest", "", "request", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "shouldPlaySound", "", "shouldVibrate", "priority", "", "getPriority", "()I", "largeIcon", "Landroid/graphics/Bitmap;", "getLargeIcon", "()Landroid/graphics/Bitmap;", "icon", "getIcon", "color", "", "getColor", "()Ljava/lang/Number;", "Companion", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ExpoNotificationBuilder extends BaseNotificationBuilder {
    public static final String EXTRAS_BODY_KEY = "body";
    public static final String EXTRAS_MARSHALLED_NOTIFICATION_REQUEST_KEY = "expo.notification_request";
    public static final String META_DATA_DEFAULT_COLOR_KEY = "expo.modules.notifications.default_notification_color";
    public static final String META_DATA_DEFAULT_ICON_KEY = "expo.modules.notifications.default_notification_icon";
    public static final String META_DATA_LARGE_ICON_KEY = "expo.modules.notifications.large_notification_icon";
    private final SharedPreferencesNotificationCategoriesStore store;

    @Override // expo.modules.notifications.notifications.interfaces.NotificationBuilder
    public Object build(Continuation<? super Notification> continuation) {
        return build$suspendImpl(this, continuation);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoNotificationBuilder(Context context, expo.modules.notifications.notifications.model.Notification notification, SharedPreferencesNotificationCategoriesStore store) {
        super(context, notification);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notification, "notification");
        Intrinsics.checkNotNullParameter(store, "store");
        this.store = store;
    }

    public void addActionsToBuilder(NotificationCompat.Builder builder, String categoryIdentifier) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(categoryIdentifier, "categoryIdentifier");
        List<NotificationAction> emptyList = CollectionsKt.emptyList();
        try {
            NotificationCategory notificationCategory = this.store.getNotificationCategory(categoryIdentifier);
            if (notificationCategory != null) {
                List<NotificationAction> actions = notificationCategory.getActions();
                Intrinsics.checkNotNullExpressionValue(actions, "getActions(...)");
                emptyList = actions;
            }
        } catch (IOException e) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("Could not read category with identifier: %s. %s", Arrays.copyOf(new Object[]{categoryIdentifier, e.getMessage()}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Log.e("expo-notifications", format);
        } catch (ClassNotFoundException e2) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("Could not read category with identifier: %s. %s", Arrays.copyOf(new Object[]{categoryIdentifier, e2.getMessage()}, 2));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            Log.e("expo-notifications", format2);
        }
        for (NotificationAction notificationAction : emptyList) {
            if (notificationAction instanceof TextInputNotificationAction) {
                builder.addAction(buildTextInputAction((TextInputNotificationAction) notificationAction));
            } else {
                builder.addAction(buildButtonAction(notificationAction));
            }
        }
    }

    protected final NotificationCompat.Action buildButtonAction(NotificationAction action) {
        Intrinsics.checkNotNullParameter(action, "action");
        NotificationCompat.Action build = new NotificationCompat.Action.Builder(getIcon(), action.getTitle(), NotificationsService.INSTANCE.createNotificationResponseIntent(getContext(), getNotification(), action)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    protected final NotificationCompat.Action buildTextInputAction(TextInputNotificationAction action) {
        Intrinsics.checkNotNullParameter(action, "action");
        PendingIntent createNotificationResponseIntent = NotificationsService.INSTANCE.createNotificationResponseIntent(getContext(), getNotification(), action);
        RemoteInput build = new RemoteInput.Builder(NotificationsService.USER_TEXT_RESPONSE_KEY).setLabel(action.getPlaceholder()).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        NotificationCompat.Action build2 = new NotificationCompat.Action.Builder(getIcon(), action.getTitle(), createNotificationResponseIntent).addRemoteInput(build).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        return build2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object build$suspendImpl(expo.modules.notifications.notifications.presentation.builders.ExpoNotificationBuilder r8, kotlin.coroutines.Continuation<? super android.app.Notification> r9) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.notifications.presentation.builders.ExpoNotificationBuilder.build$suspendImpl(expo.modules.notifications.notifications.presentation.builders.ExpoNotificationBuilder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void applySoundsAndVibrations(INotificationContent content, NotificationCompat.Builder builder) {
        boolean shouldPlaySound = shouldPlaySound();
        boolean shouldVibrate = shouldVibrate();
        if (!shouldPlaySound && !shouldVibrate) {
            builder.setSilent(true);
        }
        if (Build.VERSION.SDK_INT < 26) {
            boolean z = false;
            boolean z2 = shouldPlaySound && content.getShouldPlayDefaultSound();
            if (shouldVibrate && content.getShouldUseDefaultVibrationPattern()) {
                z = true;
            }
            if (z && z2) {
                Intrinsics.checkNotNull(builder.setDefaults(-1));
                return;
            }
            if (shouldPlaySound) {
                if (content.getSoundName() != null) {
                    builder.setSound(new SoundResolver(getContext()).resolve(content.getSoundName()));
                } else if (z2) {
                    builder.setDefaults(1);
                    builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                }
            }
            if (shouldVibrate) {
                long[] vibrationPattern = content.getVibrationPattern();
                if (vibrationPattern != null) {
                    builder.setVibrate(vibrationPattern);
                } else if (z) {
                    builder.setDefaults(2);
                }
            }
        }
    }

    protected final byte[] marshallNotificationRequest(NotificationRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        try {
            Parcel obtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain(...)");
            request.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Exception e) {
            Log.e("expo-notifications", "Could not marshalled notification request: " + request.getIdentifier() + ".", e);
            return null;
        }
    }

    private final boolean shouldPlaySound() {
        NotificationBehaviorRecord notificationBehavior = getNotificationBehavior();
        return (notificationBehavior != null ? notificationBehavior.getShouldPlaySound() : true) && (getNotificationContent().getShouldPlayDefaultSound() || getNotificationContent().getSoundName() != null);
    }

    private final boolean shouldVibrate() {
        NotificationBehaviorRecord notificationBehavior = getNotificationBehavior();
        return (notificationBehavior != null ? notificationBehavior.getShouldPlaySound() : true) && (getNotificationContent().getShouldUseDefaultVibrationPattern() || getNotificationContent().getVibrationPattern() != null);
    }

    private final int getPriority() {
        double min;
        NotificationPriority priority = getNotificationContent().getPriority();
        NotificationBehaviorRecord notificationBehavior = getNotificationBehavior();
        if (notificationBehavior == null) {
            if (priority != null) {
                return priority.getNativeValue();
            }
            return 1;
        }
        NotificationPriority priorityOverride = notificationBehavior.getPriorityOverride();
        if (priorityOverride != null) {
            return priorityOverride.getNativeValue();
        }
        if (priority == null) {
            priority = NotificationPriority.DEFAULT;
        }
        int nativeValue = priority.getNativeValue();
        if (notificationBehavior.getShouldPresentAlert()) {
            min = Math.max(1.0d, nativeValue);
        } else {
            min = Math.min(0.0d, nativeValue);
        }
        return (int) min;
    }

    protected final Bitmap getLargeIcon() {
        try {
            ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
            if (!applicationInfo.metaData.containsKey(META_DATA_LARGE_ICON_KEY)) {
                return null;
            }
            return BitmapFactory.decodeResource(getContext().getResources(), applicationInfo.metaData.getInt(META_DATA_LARGE_ICON_KEY));
        } catch (Exception e) {
            Log.e("expo-notifications", "Could not have fetched large notification icon.", e);
            return null;
        }
    }

    protected int getIcon() {
        try {
            ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
            if (applicationInfo.metaData.containsKey(META_DATA_DEFAULT_ICON_KEY)) {
                return applicationInfo.metaData.getInt(META_DATA_DEFAULT_ICON_KEY);
            }
        } catch (Exception e) {
            Log.e("expo-notifications", "Could not have fetched default notification icon.", e);
        }
        return getContext().getApplicationInfo().icon;
    }

    protected Number getColor() {
        Number color = getNotificationContent().getColor();
        if (color == null) {
            color = null;
            try {
                ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), 128);
                Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
                if (applicationInfo.metaData.containsKey(META_DATA_DEFAULT_COLOR_KEY)) {
                    return Integer.valueOf(getContext().getResources().getColor(applicationInfo.metaData.getInt(META_DATA_DEFAULT_COLOR_KEY), null));
                }
            } catch (Exception e) {
                Log.e("expo-notifications", "Could not have fetched default notification color.", e);
            }
        }
        return color;
    }
}
