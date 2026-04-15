package expo.modules.notifications.service.delegates;

import android.content.Context;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.interfaces.CategoriesDelegate;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoCategoriesDelegate.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoCategoriesDelegate;", "Lexpo/modules/notifications/service/interfaces/CategoriesDelegate;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mStore", "Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationCategoriesStore;", "getCategories", "", "Lexpo/modules/notifications/notifications/model/NotificationCategory;", "setCategory", "category", "deleteCategory", "", NotificationsService.IDENTIFIER_KEY, "", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExpoCategoriesDelegate implements CategoriesDelegate {
    private final Context context;
    private final SharedPreferencesNotificationCategoriesStore mStore;

    public ExpoCategoriesDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mStore = new SharedPreferencesNotificationCategoriesStore(context);
    }

    protected final Context getContext() {
        return this.context;
    }

    @Override // expo.modules.notifications.service.interfaces.CategoriesDelegate
    public Collection<NotificationCategory> getCategories() {
        return this.mStore.getAllNotificationCategories();
    }

    @Override // expo.modules.notifications.service.interfaces.CategoriesDelegate
    public NotificationCategory setCategory(NotificationCategory category) {
        Intrinsics.checkNotNullParameter(category, "category");
        return this.mStore.saveNotificationCategory(category);
    }

    @Override // expo.modules.notifications.service.interfaces.CategoriesDelegate
    public boolean deleteCategory(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        return this.mStore.removeNotificationCategory(identifier);
    }
}
