package expo.modules.notifications.notifications.categories;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.service.NotificationsService;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExpoNotificationCategoriesModule.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExpoNotificationCategoriesModule$definition$1$1$1 implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ ExpoNotificationCategoriesModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExpoNotificationCategoriesModule$definition$1$1$1(Promise promise, ExpoNotificationCategoriesModule expoNotificationCategoriesModule) {
        this.$promise = promise;
        this.this$0 = expoNotificationCategoriesModule;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Bundle bundle) {
        invoke(num.intValue(), bundle);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        ArrayList parcelableArrayList = bundle != null ? bundle.getParcelableArrayList(NotificationsService.NOTIFICATION_CATEGORIES_KEY) : null;
        if (i == 0 && parcelableArrayList != null) {
            this.$promise.resolve((Collection<? extends Object>) this.this$0.serializeCategories(parcelableArrayList));
        } else {
            this.$promise.reject("ERR_CATEGORIES_FETCH_FAILED", "A list of notification categories could not be fetched.", null);
        }
    }
}
