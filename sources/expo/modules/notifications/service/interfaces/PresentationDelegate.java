package expo.modules.notifications.service.interfaces;

import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehaviorRecord;
import expo.modules.notifications.service.NotificationsService;
import java.util.Collection;
import kotlin.Metadata;

/* compiled from: PresentationDelegate.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tH&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000e"}, d2 = {"Lexpo/modules/notifications/service/interfaces/PresentationDelegate;", "", "presentNotification", "", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "behavior", "Lexpo/modules/notifications/notifications/model/NotificationBehaviorRecord;", "getAllPresentedNotifications", "", "dismissNotifications", NotificationsService.IDENTIFIERS_KEY, "", "dismissAllNotifications", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface PresentationDelegate {
    void dismissAllNotifications();

    void dismissNotifications(Collection<String> identifiers);

    Collection<Notification> getAllPresentedNotifications();

    void presentNotification(Notification notification, NotificationBehaviorRecord behavior);
}
