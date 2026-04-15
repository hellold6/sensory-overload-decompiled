package expo.modules.notifications.notifications.scheduling;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.service.NotificationsService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NotificationScheduler.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NotificationScheduler$definition$1$1$1 implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ NotificationScheduler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationScheduler$definition$1$1$1(Promise promise, NotificationScheduler notificationScheduler) {
        this.$promise = promise;
        this.this$0 = notificationScheduler;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Bundle bundle) {
        invoke(num.intValue(), bundle);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        if (i == 0) {
            ArrayList parcelableArrayList = bundle != null ? bundle.getParcelableArrayList(NotificationsService.NOTIFICATION_REQUESTS_KEY) : null;
            if (parcelableArrayList == null) {
                this.$promise.reject("ERR_NOTIFICATIONS_FAILED_TO_FETCH", "Failed to fetch scheduled notifications.", null);
                return;
            } else {
                this.$promise.resolve((Collection<? extends Object>) this.this$0.serializeScheduledNotificationRequests(parcelableArrayList));
                return;
            }
        }
        Serializable serializable = bundle != null ? bundle.getSerializable(NotificationsService.EXCEPTION_KEY) : null;
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.lang.Exception");
        this.$promise.reject("ERR_NOTIFICATIONS_FAILED_TO_FETCH", "Failed to fetch scheduled notifications.", (Exception) serializable);
    }
}
