package expo.modules.notifications.notifications.debug;

import android.os.Bundle;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.service.NotificationsService;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DebugLogging.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J \u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0003J\u0016\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013¨\u0006\u0014"}, d2 = {"Lexpo/modules/notifications/notifications/debug/DebugLogging;", "", "<init>", "()V", "logBundle", "", "caller", "", "bundleToLog", "Landroid/os/Bundle;", "bundleString", "ignoredCaller", "indent", "", "logRemoteMessage", "message", "Lcom/google/firebase/messaging/RemoteMessage;", "logNotification", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DebugLogging {
    public static final DebugLogging INSTANCE = new DebugLogging();

    @JvmStatic
    public static final void logBundle(String caller, Bundle bundleToLog) {
        Intrinsics.checkNotNullParameter(caller, "caller");
        Intrinsics.checkNotNullParameter(bundleToLog, "bundleToLog");
    }

    public final void logNotification(String caller, Notification notification) {
        Intrinsics.checkNotNullParameter(caller, "caller");
        Intrinsics.checkNotNullParameter(notification, "notification");
    }

    public final void logRemoteMessage(String caller, RemoteMessage message) {
        Intrinsics.checkNotNullParameter(caller, "caller");
        Intrinsics.checkNotNullParameter(message, "message");
    }

    private DebugLogging() {
    }

    private final String bundleString(final String ignoredCaller, final Bundle bundleToLog, final int indent) {
        final StringBuilder sb = new StringBuilder();
        bundleToLog.keySet().forEach(new Consumer() { // from class: expo.modules.notifications.notifications.debug.DebugLogging$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DebugLogging.bundleString$lambda$1$lambda$0(bundleToLog, sb, indent, ignoredCaller, (String) obj);
            }
        });
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bundleString$lambda$1$lambda$0(Bundle bundle, StringBuilder sb, int i, String str, String key) {
        String str2;
        Intrinsics.checkNotNullParameter(key, "key");
        Object obj = bundle.get(key);
        if (obj instanceof Bundle) {
            sb.append(StringsKt.repeat(" ", i) + key + "\n");
            sb.append(INSTANCE.bundleString(str, (Bundle) obj, i + 2));
        } else {
            if (obj == null || (str2 = obj.toString()) == null) {
                str2 = "(null)";
            }
            sb.append(StringsKt.repeat(" ", i) + key + ": " + str2 + "\n");
        }
    }
}
