package expo.modules.notifications.notifications.presentation.builders;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import expo.modules.notifications.R;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.interfaces.INotificationContent;
import expo.modules.notifications.notifications.interfaces.NotificationBuilder;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehaviorRecord;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.service.NotificationsService;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: BaseNotificationBuilder.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0011\u001a\u00020\u00012\b\u0010\u0012\u001a\u0004\u0018\u00010\rH\u0016J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010!\u001a\u00020\u001eH\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\"\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0018R\u0014\u0010$\u001a\u00020%8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u0006-"}, d2 = {"Lexpo/modules/notifications/notifications/presentation/builders/BaseNotificationBuilder;", "Lexpo/modules/notifications/notifications/interfaces/NotificationBuilder;", "context", "Landroid/content/Context;", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "<init>", "(Landroid/content/Context;Lexpo/modules/notifications/notifications/model/Notification;)V", "getContext", "()Landroid/content/Context;", "getNotification", "()Lexpo/modules/notifications/notifications/model/Notification;", "value", "Lexpo/modules/notifications/notifications/model/NotificationBehaviorRecord;", NotificationsService.NOTIFICATION_BEHAVIOR_KEY, "getNotificationBehavior", "()Lexpo/modules/notifications/notifications/model/NotificationBehaviorRecord;", "setAllowedBehavior", "behavior", "createBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "channelId", "", "getChannelId", "()Ljava/lang/String;", "notificationsChannelManager", "Lexpo/modules/notifications/notifications/channels/managers/NotificationsChannelManager;", "getNotificationsChannelManager", "()Lexpo/modules/notifications/notifications/channels/managers/NotificationsChannelManager;", "fallbackNotificationChannel", "Landroid/app/NotificationChannel;", "getFallbackNotificationChannel", "()Landroid/app/NotificationChannel;", "createFallbackChannel", "fallbackChannelName", "getFallbackChannelName", "notificationManager", "Landroid/app/NotificationManager;", "getNotificationManager", "()Landroid/app/NotificationManager;", "notificationContent", "Lexpo/modules/notifications/notifications/interfaces/INotificationContent;", "getNotificationContent", "()Lexpo/modules/notifications/notifications/interfaces/INotificationContent;", "Companion", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseNotificationBuilder implements NotificationBuilder {
    private static final String FALLBACK_CHANNEL_ID = "expo_notifications_fallback_notification_channel";
    private final Context context;
    private final Notification notification;
    private NotificationBehaviorRecord notificationBehavior;
    private static final int FALLBACK_CHANNEL_IMPORTANCE = 4;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseNotificationBuilder(Context context, Notification notification) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notification, "notification");
        this.context = context;
        this.notification = notification;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Notification getNotification() {
        return this.notification;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final NotificationBehaviorRecord getNotificationBehavior() {
        return this.notificationBehavior;
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationBuilder
    public NotificationBuilder setAllowedBehavior(NotificationBehaviorRecord behavior) {
        this.notificationBehavior = behavior;
        return this;
    }

    public final NotificationCompat.Builder createBuilder() {
        String channelId = getChannelId();
        return channelId != null ? new NotificationCompat.Builder(this.context, channelId) : new NotificationCompat.Builder(this.context);
    }

    protected final String getChannelId() {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        NotificationRequest notificationRequest = this.notification.getNotificationRequest();
        NotificationTrigger trigger = notificationRequest != null ? notificationRequest.getTrigger() : null;
        if (trigger == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("Couldn't get channel for the notifications - trigger is 'null'. Fallback to '%s' channel", Arrays.copyOf(new Object[]{FALLBACK_CHANNEL_ID}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Log.e(NotificationsService.NOTIFICATIONS_KEY, format);
            NotificationChannel fallbackNotificationChannel = getFallbackNotificationChannel();
            Intrinsics.checkNotNull(fallbackNotificationChannel);
            return fallbackNotificationChannel.getId();
        }
        String notificationChannel = trigger.getNotificationChannel();
        if (notificationChannel == null) {
            NotificationChannel fallbackNotificationChannel2 = getFallbackNotificationChannel();
            Intrinsics.checkNotNull(fallbackNotificationChannel2);
            return fallbackNotificationChannel2.getId();
        }
        NotificationChannel notificationChannel2 = getNotificationsChannelManager().getNotificationChannel(notificationChannel);
        if (notificationChannel2 == null) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("Channel '%s' doesn't exists. Fallback to '%s' channel", Arrays.copyOf(new Object[]{notificationChannel, FALLBACK_CHANNEL_ID}, 2));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            Log.e(NotificationsService.NOTIFICATIONS_KEY, format2);
            NotificationChannel fallbackNotificationChannel3 = getFallbackNotificationChannel();
            Intrinsics.checkNotNull(fallbackNotificationChannel3);
            return fallbackNotificationChannel3.getId();
        }
        return notificationChannel2.getId();
    }

    public NotificationsChannelManager getNotificationsChannelManager() {
        return new AndroidXNotificationsChannelManager(this.context, new AndroidXNotificationsChannelGroupManager(this.context));
    }

    private final NotificationChannel getFallbackNotificationChannel() {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        NotificationChannel notificationChannel = getNotificationManager().getNotificationChannel(FALLBACK_CHANNEL_ID);
        return notificationChannel == null ? createFallbackChannel() : notificationChannel;
    }

    protected final NotificationChannel createFallbackChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(FALLBACK_CHANNEL_ID, getFallbackChannelName(), FALLBACK_CHANNEL_IMPORTANCE);
        notificationChannel.setShowBadge(true);
        notificationChannel.enableVibration(true);
        getNotificationManager().createNotificationChannel(notificationChannel);
        return notificationChannel;
    }

    private final String getFallbackChannelName() {
        String string = this.context.getString(R.string.expo_notifications_fallback_channel_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    private final NotificationManager getNotificationManager() {
        Object systemService = this.context.getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        return (NotificationManager) systemService;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final INotificationContent getNotificationContent() {
        INotificationContent content = this.notification.getNotificationRequest().getContent();
        Intrinsics.checkNotNullExpressionValue(content, "getContent(...)");
        return content;
    }
}
