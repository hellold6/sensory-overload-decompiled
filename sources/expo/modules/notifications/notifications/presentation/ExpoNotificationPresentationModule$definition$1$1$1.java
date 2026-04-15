package expo.modules.notifications.notifications.presentation;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.service.NotificationsService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExpoNotificationPresentationModule.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExpoNotificationPresentationModule$definition$1$1$1 implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ ExpoNotificationPresentationModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExpoNotificationPresentationModule$definition$1$1$1(Promise promise, ExpoNotificationPresentationModule expoNotificationPresentationModule) {
        this.$promise = promise;
        this.this$0 = expoNotificationPresentationModule;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Bundle bundle) {
        invoke(num.intValue(), bundle);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        ArrayList parcelableArrayList = bundle != null ? bundle.getParcelableArrayList(NotificationsService.NOTIFICATIONS_KEY) : null;
        if (i == 0 && parcelableArrayList != null) {
            this.$promise.resolve((Collection<? extends Object>) this.this$0.serializeNotifications(parcelableArrayList));
        } else {
            Serializable serializable = bundle != null ? bundle.getSerializable(NotificationsService.EXCEPTION_KEY) : null;
            this.$promise.reject("ERR_NOTIFICATIONS_FETCH_FAILED", "A list of displayed notifications could not be fetched.", serializable instanceof Exception ? (Exception) serializable : null);
        }
    }
}
