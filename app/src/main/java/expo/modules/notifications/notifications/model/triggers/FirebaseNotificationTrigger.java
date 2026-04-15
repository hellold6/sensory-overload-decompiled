package expo.modules.notifications.notifications.model.triggers;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.BundleKt;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.notifications.RemoteMessageSerializer;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FirebaseNotificationTrigger.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0012\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0017J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lexpo/modules/notifications/notifications/model/triggers/FirebaseNotificationTrigger;", "Lexpo/modules/notifications/notifications/interfaces/NotificationTrigger;", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "<init>", "(Lcom/google/firebase/messaging/RemoteMessage;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "getRemoteMessage", "()Lcom/google/firebase/messaging/RemoteMessage;", "getNotificationChannel", "", "toBundle", "Landroid/os/Bundle;", "describeContents", "", "writeToParcel", "", "dest", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY, "Companion", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FirebaseNotificationTrigger implements NotificationTrigger {
    private final RemoteMessage remoteMessage;
    public static final Parcelable.Creator<FirebaseNotificationTrigger> CREATOR = new Parcelable.Creator<FirebaseNotificationTrigger>() { // from class: expo.modules.notifications.notifications.model.triggers.FirebaseNotificationTrigger$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FirebaseNotificationTrigger createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new FirebaseNotificationTrigger(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FirebaseNotificationTrigger[] newArray(int size) {
            return new FirebaseNotificationTrigger[size];
        }
    };

    public /* synthetic */ FirebaseNotificationTrigger(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FirebaseNotificationTrigger(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        this.remoteMessage = remoteMessage;
    }

    public final RemoteMessage getRemoteMessage() {
        return this.remoteMessage;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private FirebaseNotificationTrigger(android.os.Parcel r2) {
        /*
            r1 = this;
            java.lang.Class<expo.modules.notifications.notifications.model.triggers.FirebaseNotificationTrigger> r0 = expo.modules.notifications.notifications.model.triggers.FirebaseNotificationTrigger.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r2 = r2.readParcelable(r0)
            com.google.firebase.messaging.RemoteMessage r2 = (com.google.firebase.messaging.RemoteMessage) r2
            if (r2 == 0) goto L12
            r1.<init>(r2)
            return
        L12:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "RemoteMessage from readParcelable must not be null"
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.notifications.model.triggers.FirebaseNotificationTrigger.<init>(android.os.Parcel):void");
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationTrigger
    public String getNotificationChannel() {
        String str;
        RemoteMessage.Notification notification = this.remoteMessage.getNotification();
        if (notification == null || (str = notification.getChannelId()) == null) {
            str = this.remoteMessage.getData().get("channelId");
        }
        return str == null ? NotificationTrigger.DefaultImpls.getNotificationChannel(this) : str;
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationTrigger
    public Bundle toBundle() {
        return BundleKt.bundleOf(TuplesKt.to("type", "push"), TuplesKt.to("remoteMessage", RemoteMessageSerializer.toBundle(this.remoteMessage)));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeParcelable(this.remoteMessage, 0);
    }
}
