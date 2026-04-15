package androidx.media3.session;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.media3.common.util.Assertions;
import com.google.common.collect.ImmutableList;

/* loaded from: classes.dex */
public final class MediaNotification {
    public static final String NOTIFICATION_DISMISSED_EVENT_KEY = "androidx.media3.session.NOTIFICATION_DISMISSED_EVENT_KEY";
    public final Notification notification;
    public final int notificationId;

    /* loaded from: classes.dex */
    public interface Provider {

        /* loaded from: classes.dex */
        public interface Callback {
            void onNotificationChanged(MediaNotification mediaNotification);
        }

        MediaNotification createNotification(MediaSession mediaSession, ImmutableList<CommandButton> immutableList, ActionFactory actionFactory, Callback callback);

        boolean handleCustomCommand(MediaSession mediaSession, String str, Bundle bundle);
    }

    /* loaded from: classes.dex */
    public interface ActionFactory {
        NotificationCompat.Action createCustomAction(MediaSession mediaSession, IconCompat iconCompat, CharSequence charSequence, String str, Bundle bundle);

        NotificationCompat.Action createCustomActionFromCustomCommandButton(MediaSession mediaSession, CommandButton commandButton);

        NotificationCompat.Action createMediaAction(MediaSession mediaSession, IconCompat iconCompat, CharSequence charSequence, int i);

        PendingIntent createMediaActionPendingIntent(MediaSession mediaSession, long j);

        default PendingIntent createNotificationDismissalIntent(MediaSession mediaSession) {
            return createMediaActionPendingIntent(mediaSession, 3L);
        }
    }

    public MediaNotification(int i, Notification notification) {
        this.notificationId = i;
        this.notification = (Notification) Assertions.checkNotNull(notification);
    }
}
