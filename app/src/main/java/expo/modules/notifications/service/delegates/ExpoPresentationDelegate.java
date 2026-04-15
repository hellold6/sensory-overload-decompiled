package expo.modules.notifications.service.delegates;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.Pair;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import expo.modules.notifications.notifications.SoundResolver;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehaviorRecord;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.presentation.builders.ExpoNotificationBuilder;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.interfaces.PresentationDelegate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ExpoPresentationDelegate.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 '2\u00020\u0001:\u0001'B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0017H\u0016J\u0016\u0010\u0018\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0017H\u0016J\b\u0010\u001b\u001a\u00020\u000bH\u0016J \u0010\u001c\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u001e\u001a\u0004\u0018\u00010\u000fH\u0094@¢\u0006\u0002\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\r2\u0006\u0010!\u001a\u00020\"H\u0014J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoPresentationDelegate;", "Lexpo/modules/notifications/service/interfaces/PresentationDelegate;", "context", "Landroid/content/Context;", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "<init>", "(Landroid/content/Context;Landroidx/core/app/NotificationManagerCompat;)V", "getContext", "()Landroid/content/Context;", "presentNotification", "", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "behavior", "Lexpo/modules/notifications/notifications/model/NotificationBehaviorRecord;", "getNotificationSoundUri", "Landroid/net/Uri;", "getNotifyId", "", "request", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "getAllPresentedNotifications", "", "dismissNotifications", NotificationsService.IDENTIFIERS_KEY, "", "dismissAllNotifications", "createNotification", "Landroid/app/Notification;", NotificationsService.NOTIFICATION_BEHAVIOR_KEY, "(Lexpo/modules/notifications/notifications/model/Notification;Lexpo/modules/notifications/notifications/model/NotificationBehaviorRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotification", "statusBarNotification", "Landroid/service/notification/StatusBarNotification;", "fromBundle", "Lorg/json/JSONObject;", "bundle", "Landroid/os/Bundle;", "Companion", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ExpoPresentationDelegate implements PresentationDelegate {
    protected static final int ANDROID_NOTIFICATION_ID = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    protected static final String INTERNAL_IDENTIFIER_AUTHORITY = "foreign_notifications";
    protected static final String INTERNAL_IDENTIFIER_ID_KEY = "id";
    protected static final String INTERNAL_IDENTIFIER_SCHEME = "expo-notifications";
    protected static final String INTERNAL_IDENTIFIER_TAG_KEY = "tag";
    private final Context context;
    private final NotificationManagerCompat notificationManager;

    /* JADX INFO: Access modifiers changed from: protected */
    public Object createNotification(Notification notification, NotificationBehaviorRecord notificationBehaviorRecord, Continuation<? super android.app.Notification> continuation) {
        return createNotification$suspendImpl(this, notification, notificationBehaviorRecord, continuation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getNotifyId(NotificationRequest request) {
        return 0;
    }

    public ExpoPresentationDelegate(Context context, NotificationManagerCompat notificationManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationManager, "notificationManager");
        this.context = context;
        this.notificationManager = notificationManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getContext() {
        return this.context;
    }

    public /* synthetic */ ExpoPresentationDelegate(Context context, NotificationManagerCompat notificationManagerCompat, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? NotificationManagerCompat.from(context) : notificationManagerCompat);
    }

    /* compiled from: ExpoPresentationDelegate.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0007J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0004R\u000e\u0010\u0004\u001a\u00020\u0005X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0084T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoPresentationDelegate$Companion;", "", "<init>", "()V", "ANDROID_NOTIFICATION_ID", "", "INTERNAL_IDENTIFIER_SCHEME", "", "INTERNAL_IDENTIFIER_AUTHORITY", "INTERNAL_IDENTIFIER_TAG_KEY", "INTERNAL_IDENTIFIER_ID_KEY", "parseNotificationIdentifier", "Landroid/util/Pair;", NotificationsService.IDENTIFIER_KEY, "getInternalIdentifierKey", NotificationsService.NOTIFICATION_KEY, "Landroid/service/notification/StatusBarNotification;", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Pair<String, Integer> parseNotificationIdentifier(String identifier) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            try {
                Uri parse = Uri.parse(identifier);
                if (!Intrinsics.areEqual(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_SCHEME, parse.getScheme()) || !Intrinsics.areEqual(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_AUTHORITY, parse.getAuthority())) {
                    return null;
                }
                String queryParameter = parse.getQueryParameter(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_TAG_KEY);
                String queryParameter2 = parse.getQueryParameter("id");
                Intrinsics.checkNotNull(queryParameter2);
                return new Pair<>(queryParameter, Integer.valueOf(Integer.parseInt(queryParameter2)));
            } catch (NullPointerException e) {
                Log.e(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_SCHEME, "Malformed foreign notification identifier: " + identifier, e);
                return null;
            } catch (NumberFormatException e2) {
                Log.e(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_SCHEME, "Malformed foreign notification identifier: " + identifier, e2);
                return null;
            } catch (UnsupportedOperationException e3) {
                Log.e(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_SCHEME, "Malformed foreign notification identifier: " + identifier, e3);
                return null;
            }
        }

        protected final String getInternalIdentifierKey(StatusBarNotification notification) {
            Intrinsics.checkNotNullParameter(notification, "notification");
            Uri.Builder buildUpon = Uri.parse("expo-notifications://foreign_notifications").buildUpon();
            String tag = notification.getTag();
            if (tag != null) {
                buildUpon.appendQueryParameter(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_TAG_KEY, tag);
            }
            buildUpon.appendQueryParameter("id", String.valueOf(notification.getId()));
            String builder = buildUpon.toString();
            Intrinsics.checkNotNullExpressionValue(builder, "with(...)");
            return builder;
        }
    }

    @Override // expo.modules.notifications.service.interfaces.PresentationDelegate
    public void presentNotification(Notification notification, NotificationBehaviorRecord behavior) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        if (behavior == null || behavior.getShouldPresentAlert()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new ExpoPresentationDelegate$presentNotification$1(this, notification, behavior, null), 3, null);
        } else if (behavior.getShouldPlaySound()) {
            Uri notificationSoundUri = getNotificationSoundUri(notification);
            if (notificationSoundUri == null) {
                notificationSoundUri = Settings.System.DEFAULT_NOTIFICATION_URI;
            }
            RingtoneManager.getRingtone(this.context, notificationSoundUri).play();
        }
    }

    private final Uri getNotificationSoundUri(Notification notification) {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT >= 26) {
            String notificationChannel2 = notification.getNotificationRequest().getTrigger().getNotificationChannel();
            if (notificationChannel2 == null || (notificationChannel = this.notificationManager.getNotificationChannel(notificationChannel2)) == null) {
                return null;
            }
            return notificationChannel.getSound();
        }
        return new SoundResolver(this.context).resolve(notification.getNotificationRequest().getContent().getSoundName());
    }

    @Override // expo.modules.notifications.service.interfaces.PresentationDelegate
    public Collection<Notification> getAllPresentedNotifications() {
        Object systemService = this.context.getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        StatusBarNotification[] activeNotifications = ((NotificationManager) systemService).getActiveNotifications();
        Intrinsics.checkNotNullExpressionValue(activeNotifications, "getActiveNotifications(...)");
        ArrayList arrayList = new ArrayList();
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            Intrinsics.checkNotNull(statusBarNotification);
            Notification notification = getNotification(statusBarNotification);
            if (notification != null) {
                arrayList.add(notification);
            }
        }
        return arrayList;
    }

    @Override // expo.modules.notifications.service.interfaces.PresentationDelegate
    public void dismissNotifications(Collection<String> identifiers) {
        Object obj;
        Intrinsics.checkNotNullParameter(identifiers, "identifiers");
        for (String str : identifiers) {
            Pair<String, Integer> parseNotificationIdentifier = INSTANCE.parseNotificationIdentifier(str);
            if (parseNotificationIdentifier != null) {
                NotificationManagerCompat from = NotificationManagerCompat.from(this.context);
                String str2 = (String) parseNotificationIdentifier.first;
                Object second = parseNotificationIdentifier.second;
                Intrinsics.checkNotNullExpressionValue(second, "second");
                from.cancel(str2, ((Number) second).intValue());
            } else {
                Iterator<T> it = getAllPresentedNotifications().iterator();
                while (true) {
                    if (it.hasNext()) {
                        obj = it.next();
                        if (Intrinsics.areEqual(((Notification) obj).getNotificationRequest().getIdentifier(), str)) {
                            break;
                        }
                    } else {
                        obj = null;
                        break;
                    }
                }
                Notification notification = (Notification) obj;
                NotificationManagerCompat.from(this.context).cancel(str, getNotifyId(notification != null ? notification.getNotificationRequest() : null));
            }
        }
    }

    @Override // expo.modules.notifications.service.interfaces.PresentationDelegate
    public void dismissAllNotifications() {
        NotificationManagerCompat.from(this.context).cancelAll();
    }

    static /* synthetic */ Object createNotification$suspendImpl(ExpoPresentationDelegate expoPresentationDelegate, Notification notification, NotificationBehaviorRecord notificationBehaviorRecord, Continuation<? super android.app.Notification> continuation) {
        ExpoNotificationBuilder expoNotificationBuilder = new ExpoNotificationBuilder(expoPresentationDelegate.context, notification, new SharedPreferencesNotificationCategoriesStore(expoPresentationDelegate.context));
        expoNotificationBuilder.setAllowedBehavior(notificationBehaviorRecord);
        return expoNotificationBuilder.build(continuation);
    }

    protected Notification getNotification(StatusBarNotification statusBarNotification) {
        Intrinsics.checkNotNullParameter(statusBarNotification, "statusBarNotification");
        android.app.Notification notification = statusBarNotification.getNotification();
        byte[] byteArray = notification.extras.getByteArray(ExpoNotificationBuilder.EXTRAS_MARSHALLED_NOTIFICATION_REQUEST_KEY);
        if (byteArray != null) {
            try {
                Parcel obtain = Parcel.obtain();
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                NotificationRequest createFromParcel = NotificationRequest.CREATOR.createFromParcel(obtain);
                Intrinsics.checkNotNullExpressionValue(createFromParcel, "createFromParcel(...)");
                obtain.recycle();
                return new Notification(createFromParcel, new Date(statusBarNotification.getPostTime()));
            } catch (Exception unused) {
                Log.e(INTERNAL_IDENTIFIER_SCHEME, "Could not have unmarshalled NotificationRequest from (" + statusBarNotification.getTag() + ", " + statusBarNotification.getId() + ").");
            }
        }
        NotificationContent.Builder builder = new NotificationContent.Builder();
        CharSequence contentTitle = NotificationCompat.getContentTitle(notification);
        NotificationContent.Builder title = builder.setTitle(contentTitle != null ? contentTitle.toString() : null);
        CharSequence contentText = NotificationCompat.getContentText(notification);
        NotificationContent.Builder text = title.setText(contentText != null ? contentText.toString() : null);
        CharSequence subText = NotificationCompat.getSubText(notification);
        NotificationContent.Builder sound = text.setSubtitle(subText != null ? subText.toString() : null).setAutoDismiss(NotificationCompat.getAutoCancel(notification)).setSticky(NotificationCompat.getOngoing(notification)).setPriority(NotificationPriority.fromNativeValue(notification.priority)).setVibrationPattern(notification.vibrate).setSound(notification.sound);
        Bundle extras = notification.extras;
        Intrinsics.checkNotNullExpressionValue(extras, "extras");
        return new Notification(new NotificationRequest(INSTANCE.getInternalIdentifierKey(statusBarNotification), sound.setBody(fromBundle(extras)).build(), null), new Date(statusBarNotification.getPostTime()));
    }

    protected JSONObject fromBundle(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, JSONObject.wrap(bundle.get(str)));
            } catch (JSONException e) {
                Log.d(INTERNAL_IDENTIFIER_SCHEME, "Error encountered while serializing Android notification extras: " + str + " -> " + bundle.get(str), e);
            }
        }
        return jSONObject;
    }
}
