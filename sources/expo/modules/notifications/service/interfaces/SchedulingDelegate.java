package expo.modules.notifications.service.interfaces;

import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.service.NotificationsService;
import java.util.Collection;
import kotlin.Metadata;

/* compiled from: SchedulingDelegate.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u0005H&J\b\u0010\u000f\u001a\u00020\u0003H&¨\u0006\u0010"}, d2 = {"Lexpo/modules/notifications/service/interfaces/SchedulingDelegate;", "", "setupScheduledNotifications", "", "getAllScheduledNotifications", "", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "getScheduledNotification", NotificationsService.IDENTIFIER_KEY, "", "scheduleNotification", "request", "triggerNotification", "removeScheduledNotifications", NotificationsService.IDENTIFIERS_KEY, "removeAllScheduledNotifications", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface SchedulingDelegate {
    Collection<NotificationRequest> getAllScheduledNotifications();

    NotificationRequest getScheduledNotification(String identifier);

    void removeAllScheduledNotifications();

    void removeScheduledNotifications(Collection<String> identifiers);

    void scheduleNotification(NotificationRequest request);

    void setupScheduledNotifications();

    void triggerNotification(String identifier);
}
