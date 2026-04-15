package expo.modules.notifications.notifications.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.modules.dialog.AlertFragment;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import expo.modules.notifications.notifications.interfaces.INotificationContent;
import expo.modules.notifications.notifications.presentation.builders.DownloadImageKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: RemoteNotificationContent.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 A2\u00020\u0001:\u0001AB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\u0018\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010<\u001a\u000208H\u0016J\u0018\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u00072\u0006\u0010@\u001a\u000208H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0014\u0010\u001e\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001bR\u0016\u0010 \u001a\u0004\u0018\u00010!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0016\u0010$\u001a\u0004\u0018\u00010%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0016\u0010,\u001a\u0004\u0018\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001bR\u0011\u00101\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001bR\u0016\u00102\u001a\u0004\u0018\u00010\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0016R\u0014\u00104\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001bR\u0016\u00105\u001a\u0004\u0018\u00010\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0016R\u0018\u00107\u001a\u0004\u0018\u000108X\u0096\u0004¢\u0006\n\n\u0002\u0010;\u001a\u0004\b9\u0010:¨\u0006B"}, d2 = {"Lexpo/modules/notifications/notifications/model/RemoteNotificationContent;", "Lexpo/modules/notifications/notifications/interfaces/INotificationContent;", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "<init>", "(Lcom/google/firebase/messaging/RemoteMessage;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "notificationData", "Lexpo/modules/notifications/notifications/model/NotificationData;", "Ljava/util/Map;", "getImage", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "containsImage", "", AlertFragment.ARG_TITLE, "", "getTitle", "()Ljava/lang/String;", "text", "getText", "shouldPlayDefaultSound", "getShouldPlayDefaultSound", "()Z", "soundName", "getSoundName", "shouldUseDefaultVibrationPattern", "getShouldUseDefaultVibrationPattern", NotificationsChannelSerializer.VIBRATION_PATTERN_KEY, "", "getVibrationPattern", "()[J", "body", "Lorg/json/JSONObject;", "getBody", "()Lorg/json/JSONObject;", "priority", "Lexpo/modules/notifications/notifications/enums/NotificationPriority;", "getPriority", "()Lexpo/modules/notifications/notifications/enums/NotificationPriority;", "color", "", "getColor", "()Ljava/lang/Number;", "isAutoDismiss", "isDataOnly", "categoryId", "getCategoryId", "isSticky", "subText", "getSubText", "badgeCount", "", "getBadgeCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "describeContents", "writeToParcel", "", "dest", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY, "CREATOR", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RemoteNotificationContent implements INotificationContent {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer badgeCount;
    private final JSONObject body;
    private final String categoryId;
    private final boolean isAutoDismiss;
    private final boolean isDataOnly;
    private final boolean isSticky;
    private final Map<String, ? extends String> notificationData;
    private final RemoteMessage remoteMessage;
    private final boolean shouldPlayDefaultSound;
    private final String soundName;
    private final String subText;
    private final String text;
    private final String title;
    private final long[] vibrationPattern;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RemoteNotificationContent(RemoteMessage remoteMessage) {
        long[] vibrateTimings;
        String sound;
        String body;
        String title;
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        this.remoteMessage = remoteMessage;
        Map<String, String> data = remoteMessage.getData();
        Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
        Map<String, ? extends String> m1358constructorimpl = NotificationData.m1358constructorimpl(data);
        this.notificationData = m1358constructorimpl;
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        this.title = (notification == null || (title = notification.getTitle()) == null) ? NotificationData.m1371getTitleimpl(m1358constructorimpl) : title;
        RemoteMessage.Notification notification2 = remoteMessage.getNotification();
        this.text = (notification2 == null || (body = notification2.getBody()) == null) ? NotificationData.m1366getMessageimpl(m1358constructorimpl) : body;
        RemoteMessage.Notification notification3 = remoteMessage.getNotification();
        this.shouldPlayDefaultSound = (notification3 != null ? notification3.getSound() : null) == null && NotificationData.m1367getShouldPlayDefaultSoundimpl(m1358constructorimpl);
        RemoteMessage.Notification notification4 = remoteMessage.getNotification();
        this.soundName = (notification4 == null || (sound = notification4.getSound()) == null) ? NotificationData.m1369getSoundimpl(m1358constructorimpl) : sound;
        RemoteMessage.Notification notification5 = remoteMessage.getNotification();
        this.vibrationPattern = (notification5 == null || (vibrateTimings = notification5.getVibrateTimings()) == null) ? NotificationData.m1372getVibrationPatternimpl(m1358constructorimpl) : vibrateTimings;
        this.body = NotificationData.m1363getBodyimpl(m1358constructorimpl);
        this.isAutoDismiss = NotificationData.m1361getAutoDismissimpl(m1358constructorimpl);
        this.isDataOnly = remoteMessage.getNotification() == null;
        this.categoryId = NotificationData.m1364getCategoryIdimpl(m1358constructorimpl);
        this.isSticky = NotificationData.m1374isStickyimpl(m1358constructorimpl);
        this.subText = NotificationData.m1370getSubTextimpl(m1358constructorimpl);
        this.badgeCount = NotificationData.m1362getBadgeimpl(m1358constructorimpl);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RemoteNotificationContent(android.os.Parcel r2) {
        /*
            r1 = this;
            java.lang.String r0 = "parcel"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.Class<com.google.firebase.messaging.RemoteMessage> r0 = com.google.firebase.messaging.RemoteMessage.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r2 = r2.readParcelable(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            com.google.firebase.messaging.RemoteMessage r2 = (com.google.firebase.messaging.RemoteMessage) r2
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.notifications.model.RemoteNotificationContent.<init>(android.os.Parcel):void");
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public Object getImage(Context context, Continuation<? super Bitmap> continuation) {
        RemoteMessage.Notification notification = this.remoteMessage.getNotification();
        Uri imageUrl = notification != null ? notification.getImageUrl() : null;
        if (imageUrl == null) {
            return null;
        }
        Object downloadImage$default = DownloadImageKt.downloadImage$default(imageUrl, 0L, 0L, continuation, 6, null);
        return downloadImage$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? downloadImage$default : (Bitmap) downloadImage$default;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public boolean containsImage() {
        RemoteMessage.Notification notification = this.remoteMessage.getNotification();
        return (notification != null ? notification.getImageUrl() : null) != null;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public String getTitle() {
        return this.title;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public String getText() {
        return this.text;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public boolean getShouldPlayDefaultSound() {
        return this.shouldPlayDefaultSound;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public String getSoundName() {
        return this.soundName;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public boolean getShouldUseDefaultVibrationPattern() {
        RemoteMessage.Notification notification = this.remoteMessage.getNotification();
        return notification != null ? notification.getDefaultVibrateSettings() : NotificationData.m1368getShouldUseDefaultVibrationPatternimpl(this.notificationData);
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public long[] getVibrationPattern() {
        return this.vibrationPattern;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public JSONObject getBody() {
        return this.body;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public NotificationPriority getPriority() {
        if (this.remoteMessage.getPriority() == 1) {
            return NotificationPriority.HIGH;
        }
        return NotificationPriority.DEFAULT;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public Number getColor() {
        String m1365getColorimpl;
        RemoteMessage.Notification notification = this.remoteMessage.getNotification();
        if (notification == null || (m1365getColorimpl = notification.getColor()) == null) {
            m1365getColorimpl = NotificationData.m1365getColorimpl(this.notificationData);
        }
        return m1365getColorimpl != null ? Integer.valueOf(Color.parseColor(m1365getColorimpl)) : null;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    /* renamed from: isAutoDismiss, reason: from getter */
    public boolean getIsAutoDismiss() {
        return this.isAutoDismiss;
    }

    /* renamed from: isDataOnly, reason: from getter */
    public final boolean getIsDataOnly() {
        return this.isDataOnly;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public String getCategoryId() {
        return this.categoryId;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    /* renamed from: isSticky, reason: from getter */
    public boolean getIsSticky() {
        return this.isSticky;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public String getSubText() {
        return this.subText;
    }

    @Override // expo.modules.notifications.notifications.interfaces.INotificationContent
    public Integer getBadgeCount() {
        return this.badgeCount;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeParcelable(this.remoteMessage, flags);
    }

    /* compiled from: RemoteNotificationContent.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lexpo/modules/notifications/notifications/model/RemoteNotificationContent$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lexpo/modules/notifications/notifications/model/RemoteNotificationContent;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lexpo/modules/notifications/notifications/model/RemoteNotificationContent;", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: expo.modules.notifications.notifications.model.RemoteNotificationContent$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes3.dex */
    public static final class Companion implements Parcelable.Creator<RemoteNotificationContent> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteNotificationContent createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new RemoteNotificationContent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteNotificationContent[] newArray(int size) {
            return new RemoteNotificationContent[size];
        }
    }
}
