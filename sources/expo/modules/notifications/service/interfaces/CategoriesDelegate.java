package expo.modules.notifications.service.interfaces;

import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.service.NotificationsService;
import java.util.Collection;
import kotlin.Metadata;

/* compiled from: CategoriesDelegate.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0004H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lexpo/modules/notifications/service/interfaces/CategoriesDelegate;", "", "getCategories", "", "Lexpo/modules/notifications/notifications/model/NotificationCategory;", "setCategory", "category", "deleteCategory", "", NotificationsService.IDENTIFIER_KEY, "", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CategoriesDelegate {
    boolean deleteCategory(String identifier);

    Collection<NotificationCategory> getCategories();

    NotificationCategory setCategory(NotificationCategory category);
}
