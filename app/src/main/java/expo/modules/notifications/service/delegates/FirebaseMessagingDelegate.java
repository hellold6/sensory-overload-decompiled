package expo.modules.notifications.service.delegates;

import android.content.Context;
import android.os.Bundle;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.interfaces.taskManager.TaskServiceProviderHelper;
import expo.modules.notifications.notifications.RemoteMessageSerializer;
import expo.modules.notifications.notifications.background.BackgroundRemoteNotificationTaskConsumer;
import expo.modules.notifications.notifications.debug.DebugLogging;
import expo.modules.notifications.notifications.interfaces.INotificationContent;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.model.RemoteNotificationContent;
import expo.modules.notifications.notifications.model.triggers.FirebaseNotificationTrigger;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.tokens.interfaces.FirebaseTokenListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FirebaseMessagingDelegate.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0004J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0004J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001b"}, d2 = {"Lexpo/modules/notifications/service/delegates/FirebaseMessagingDelegate;", "Lexpo/modules/notifications/service/interfaces/FirebaseMessagingDelegate;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "onNewToken", "", "token", "", "onMessageReceived", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "createNotification", "Lexpo/modules/notifications/notifications/model/Notification;", "getNotificationIdentifier", "createNotificationRequest", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", NotificationsService.IDENTIFIER_KEY, UriUtil.LOCAL_CONTENT_SCHEME, "Lexpo/modules/notifications/notifications/interfaces/INotificationContent;", "notificationTrigger", "Lexpo/modules/notifications/notifications/model/triggers/FirebaseNotificationTrigger;", "onDeletedMessages", "Companion", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class FirebaseMessagingDelegate implements expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate {
    private static String sLastToken;
    private final Context context;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final WeakHashMap<FirebaseTokenListener, WeakReference<FirebaseTokenListener>> sTokenListenersReferences = new WeakHashMap<>();
    private static WeakHashMap<BackgroundRemoteNotificationTaskConsumer, WeakReference<BackgroundRemoteNotificationTaskConsumer>> sBackgroundTaskConsumerReferences = new WeakHashMap<>();

    @JvmStatic
    public static final void addTokenListener(FirebaseTokenListener firebaseTokenListener) {
        INSTANCE.addTokenListener(firebaseTokenListener);
    }

    /* compiled from: FirebaseMessagingDelegate.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\fH\u0007J\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0014J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u001bJ\u0016\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR*\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\r0\u000bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR,\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\r0\u000bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000f\"\u0004\b\u0016\u0010\u0017¨\u0006!"}, d2 = {"Lexpo/modules/notifications/service/delegates/FirebaseMessagingDelegate$Companion;", "", "<init>", "()V", "sLastToken", "", "getSLastToken", "()Ljava/lang/String;", "setSLastToken", "(Ljava/lang/String;)V", "sTokenListenersReferences", "Ljava/util/WeakHashMap;", "Lexpo/modules/notifications/tokens/interfaces/FirebaseTokenListener;", "Ljava/lang/ref/WeakReference;", "getSTokenListenersReferences", "()Ljava/util/WeakHashMap;", "addTokenListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "sBackgroundTaskConsumerReferences", "Lexpo/modules/notifications/notifications/background/BackgroundRemoteNotificationTaskConsumer;", "getSBackgroundTaskConsumerReferences", "setSBackgroundTaskConsumerReferences", "(Ljava/util/WeakHashMap;)V", "addBackgroundTaskConsumer", "taskConsumer", "getBackgroundTasks", "", "runTaskManagerTasks", "applicationContext", "Landroid/content/Context;", "bundle", "Landroid/os/Bundle;", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        protected final String getSLastToken() {
            return FirebaseMessagingDelegate.sLastToken;
        }

        protected final void setSLastToken(String str) {
            FirebaseMessagingDelegate.sLastToken = str;
        }

        protected final WeakHashMap<FirebaseTokenListener, WeakReference<FirebaseTokenListener>> getSTokenListenersReferences() {
            return FirebaseMessagingDelegate.sTokenListenersReferences;
        }

        @JvmStatic
        public final void addTokenListener(FirebaseTokenListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            if (getSTokenListenersReferences().containsKey(listener)) {
                return;
            }
            getSTokenListenersReferences().put(listener, new WeakReference<>(listener));
            String sLastToken = getSLastToken();
            if (sLastToken != null) {
                listener.onNewToken(sLastToken);
            }
        }

        protected final WeakHashMap<BackgroundRemoteNotificationTaskConsumer, WeakReference<BackgroundRemoteNotificationTaskConsumer>> getSBackgroundTaskConsumerReferences() {
            return FirebaseMessagingDelegate.sBackgroundTaskConsumerReferences;
        }

        protected final void setSBackgroundTaskConsumerReferences(WeakHashMap<BackgroundRemoteNotificationTaskConsumer, WeakReference<BackgroundRemoteNotificationTaskConsumer>> weakHashMap) {
            Intrinsics.checkNotNullParameter(weakHashMap, "<set-?>");
            FirebaseMessagingDelegate.sBackgroundTaskConsumerReferences = weakHashMap;
        }

        public final void addBackgroundTaskConsumer(BackgroundRemoteNotificationTaskConsumer taskConsumer) {
            Intrinsics.checkNotNullParameter(taskConsumer, "taskConsumer");
            if (getSBackgroundTaskConsumerReferences().containsKey(taskConsumer)) {
                return;
            }
            getSBackgroundTaskConsumerReferences().put(taskConsumer, new WeakReference<>(taskConsumer));
        }

        public final List<BackgroundRemoteNotificationTaskConsumer> getBackgroundTasks() {
            Collection<WeakReference<BackgroundRemoteNotificationTaskConsumer>> values = getSBackgroundTaskConsumerReferences().values();
            Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = values.iterator();
            while (it.hasNext()) {
                BackgroundRemoteNotificationTaskConsumer backgroundRemoteNotificationTaskConsumer = (BackgroundRemoteNotificationTaskConsumer) ((WeakReference) it.next()).get();
                if (backgroundRemoteNotificationTaskConsumer != null) {
                    arrayList.add(backgroundRemoteNotificationTaskConsumer);
                }
            }
            return arrayList;
        }

        public final void runTaskManagerTasks(Context applicationContext, Bundle bundle) {
            Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            TaskServiceProviderHelper.INSTANCE.getTaskServiceImpl(applicationContext);
            Iterator<T> it = getBackgroundTasks().iterator();
            while (it.hasNext()) {
                ((BackgroundRemoteNotificationTaskConsumer) it.next()).executeTask(bundle);
            }
        }
    }

    public FirebaseMessagingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    protected final Context getContext() {
        return this.context;
    }

    @Override // expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate
    public void onNewToken(String token) {
        FirebaseTokenListener firebaseTokenListener;
        Intrinsics.checkNotNullParameter(token, "token");
        for (WeakReference<FirebaseTokenListener> weakReference : sTokenListenersReferences.values()) {
            if (weakReference != null && (firebaseTokenListener = weakReference.get()) != null) {
                firebaseTokenListener.onNewToken(token);
            }
        }
        sLastToken = token;
    }

    @Override // expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        DebugLogging.INSTANCE.logRemoteMessage("FirebaseMessagingDelegate.onMessageReceived: message", remoteMessage);
        Notification createNotification = createNotification(remoteMessage);
        DebugLogging.INSTANCE.logNotification("FirebaseMessagingDelegate.onMessageReceived: notification", createNotification);
        NotificationsService.Companion.receive$default(NotificationsService.INSTANCE, this.context, createNotification, null, 4, null);
        Companion companion = INSTANCE;
        Context applicationContext = this.context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        Bundle bundle = RemoteMessageSerializer.toBundle(remoteMessage);
        Intrinsics.checkNotNullExpressionValue(bundle, "toBundle(...)");
        companion.runTaskManagerTasks(applicationContext, bundle);
    }

    protected final Notification createNotification(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        return new Notification(createNotificationRequest(getNotificationIdentifier(remoteMessage), new RemoteNotificationContent(remoteMessage), new FirebaseNotificationTrigger(remoteMessage)), new Date(remoteMessage.getSentTime()));
    }

    protected final String getNotificationIdentifier(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        String str = remoteMessage.getData().get("tag");
        if (str != null) {
            return str;
        }
        String messageId = remoteMessage.getMessageId();
        if (messageId != null) {
            return messageId;
        }
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        return uuid;
    }

    protected NotificationRequest createNotificationRequest(String identifier, INotificationContent content, FirebaseNotificationTrigger notificationTrigger) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(notificationTrigger, "notificationTrigger");
        return new NotificationRequest(identifier, content, notificationTrigger);
    }

    @Override // expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate
    public void onDeletedMessages() {
        NotificationsService.INSTANCE.handleDropped(this.context);
    }
}
