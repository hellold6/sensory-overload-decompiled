package expo.modules.notifications.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.service.delegates.FirebaseMessagingDelegate;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoFirebaseMessagingService.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058TX\u0094\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lexpo/modules/notifications/service/ExpoFirebaseMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "<init>", "()V", "firebaseMessagingDelegate", "Lexpo/modules/notifications/service/interfaces/FirebaseMessagingDelegate;", "getFirebaseMessagingDelegate", "()Lexpo/modules/notifications/service/interfaces/FirebaseMessagingDelegate;", "firebaseMessagingDelegate$delegate", "Lkotlin/Lazy;", "onMessageReceived", "", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "onDeletedMessages", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ExpoFirebaseMessagingService extends FirebaseMessagingService {

    /* renamed from: firebaseMessagingDelegate$delegate, reason: from kotlin metadata */
    private final Lazy firebaseMessagingDelegate = LazyKt.lazy(new Function0() { // from class: expo.modules.notifications.service.ExpoFirebaseMessagingService$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            FirebaseMessagingDelegate firebaseMessagingDelegate_delegate$lambda$0;
            firebaseMessagingDelegate_delegate$lambda$0 = ExpoFirebaseMessagingService.firebaseMessagingDelegate_delegate$lambda$0(ExpoFirebaseMessagingService.this);
            return firebaseMessagingDelegate_delegate$lambda$0;
        }
    });

    protected expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate getFirebaseMessagingDelegate() {
        return (expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate) this.firebaseMessagingDelegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirebaseMessagingDelegate firebaseMessagingDelegate_delegate$lambda$0(ExpoFirebaseMessagingService expoFirebaseMessagingService) {
        return new FirebaseMessagingDelegate(expoFirebaseMessagingService);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        getFirebaseMessagingDelegate().onMessageReceived(remoteMessage);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        getFirebaseMessagingDelegate().onNewToken(token);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onDeletedMessages() {
        getFirebaseMessagingDelegate().onDeletedMessages();
    }
}
