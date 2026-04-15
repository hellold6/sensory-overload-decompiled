package expo.modules.notifications.tokens;

import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PushTokenModule.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
final class PushTokenModule$definition$1$3$1<TResult> implements OnCompleteListener {
    final /* synthetic */ Promise $promise;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PushTokenModule$definition$1$3$1(Promise promise) {
        this.$promise = promise;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<Void> task) {
        String str;
        Intrinsics.checkNotNullParameter(task, "task");
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            Promise promise = this.$promise;
            if (exception == null || (str = exception.getMessage()) == null) {
                str = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            promise.reject("E_UNREGISTER_FOR_NOTIFICATIONS_FAILED", "Unregistering for notifications failed: " + str, exception);
            return;
        }
        this.$promise.resolve((Object) null);
    }
}
