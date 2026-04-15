package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationBehaviorRecord.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002BE\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\"\u001a\u00020\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J\t\u0010$\u001a\u00020\u0004HÆ\u0003J\t\u0010%\u001a\u00020\u0004HÆ\u0003J\t\u0010&\u001a\u00020\u0004HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\nHÆ\u0003JG\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0006\u0010)\u001a\u00020*J\u0013\u0010+\u001a\u00020\u00042\b\u0010,\u001a\u0004\u0018\u00010-HÖ\u0003J\t\u0010.\u001a\u00020*HÖ\u0001J\t\u0010/\u001a\u00020\nHÖ\u0001J\u0016\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020*R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001c\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010R\u001c\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0016\u0010\u0010R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0010R\u001e\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b!\u0010\u0010¨\u00065"}, d2 = {"Lexpo/modules/notifications/notifications/model/NotificationBehaviorRecord;", "Lexpo/modules/kotlin/records/Record;", "Landroid/os/Parcelable;", "shouldShowAlert", "", "shouldShowBanner", "shouldShowList", "shouldPlaySound", "shouldSetBadge", "priority", "", "<init>", "(ZZZZZLjava/lang/String;)V", "getShouldShowAlert$annotations", "()V", "getShouldShowAlert", "()Z", "getShouldShowBanner$annotations", "getShouldShowBanner", "getShouldShowList$annotations", "getShouldShowList", "getShouldPlaySound$annotations", "getShouldPlaySound", "getShouldSetBadge$annotations", "getShouldSetBadge", "getPriority$annotations", "getPriority", "()Ljava/lang/String;", "priorityOverride", "Lexpo/modules/notifications/notifications/enums/NotificationPriority;", "getPriorityOverride", "()Lexpo/modules/notifications/notifications/enums/NotificationPriority;", "shouldPresentAlert", "getShouldPresentAlert", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY, "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class NotificationBehaviorRecord implements Record, Parcelable {
    public static final Parcelable.Creator<NotificationBehaviorRecord> CREATOR = new Creator();
    private final String priority;
    private final boolean shouldPlaySound;
    private final boolean shouldSetBadge;
    private final boolean shouldShowAlert;
    private final boolean shouldShowBanner;
    private final boolean shouldShowList;

    /* compiled from: NotificationBehaviorRecord.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<NotificationBehaviorRecord> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NotificationBehaviorRecord createFromParcel(Parcel parcel) {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            boolean z5 = true;
            boolean z6 = false;
            if (parcel.readInt() != 0) {
                z = true;
            } else {
                z = true;
                z5 = false;
            }
            if (parcel.readInt() != 0) {
                z2 = false;
                z6 = z;
            } else {
                z2 = false;
            }
            if (parcel.readInt() != 0) {
                z3 = z2;
                z2 = z;
            } else {
                z3 = z2;
            }
            if (parcel.readInt() != 0) {
                z4 = z3;
                z3 = z;
            } else {
                z4 = z3;
            }
            if (parcel.readInt() != 0) {
                z4 = z;
            }
            return new NotificationBehaviorRecord(z5, z6, z2, z3, z4, parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NotificationBehaviorRecord[] newArray(int i) {
            return new NotificationBehaviorRecord[i];
        }
    }

    public NotificationBehaviorRecord() {
        this(false, false, false, false, false, null, 63, null);
    }

    public static /* synthetic */ NotificationBehaviorRecord copy$default(NotificationBehaviorRecord notificationBehaviorRecord, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = notificationBehaviorRecord.shouldShowAlert;
        }
        if ((i & 2) != 0) {
            z2 = notificationBehaviorRecord.shouldShowBanner;
        }
        if ((i & 4) != 0) {
            z3 = notificationBehaviorRecord.shouldShowList;
        }
        if ((i & 8) != 0) {
            z4 = notificationBehaviorRecord.shouldPlaySound;
        }
        if ((i & 16) != 0) {
            z5 = notificationBehaviorRecord.shouldSetBadge;
        }
        if ((i & 32) != 0) {
            str = notificationBehaviorRecord.priority;
        }
        boolean z6 = z5;
        String str2 = str;
        return notificationBehaviorRecord.copy(z, z2, z3, z4, z6, str2);
    }

    @Field
    public static /* synthetic */ void getPriority$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldPlaySound$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldSetBadge$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldShowAlert$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldShowBanner$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldShowList$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getShouldShowAlert() {
        return this.shouldShowAlert;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getShouldShowBanner() {
        return this.shouldShowBanner;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getShouldShowList() {
        return this.shouldShowList;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getShouldPlaySound() {
        return this.shouldPlaySound;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getShouldSetBadge() {
        return this.shouldSetBadge;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPriority() {
        return this.priority;
    }

    public final NotificationBehaviorRecord copy(boolean shouldShowAlert, boolean shouldShowBanner, boolean shouldShowList, boolean shouldPlaySound, boolean shouldSetBadge, String priority) {
        return new NotificationBehaviorRecord(shouldShowAlert, shouldShowBanner, shouldShowList, shouldPlaySound, shouldSetBadge, priority);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotificationBehaviorRecord)) {
            return false;
        }
        NotificationBehaviorRecord notificationBehaviorRecord = (NotificationBehaviorRecord) other;
        return this.shouldShowAlert == notificationBehaviorRecord.shouldShowAlert && this.shouldShowBanner == notificationBehaviorRecord.shouldShowBanner && this.shouldShowList == notificationBehaviorRecord.shouldShowList && this.shouldPlaySound == notificationBehaviorRecord.shouldPlaySound && this.shouldSetBadge == notificationBehaviorRecord.shouldSetBadge && Intrinsics.areEqual(this.priority, notificationBehaviorRecord.priority);
    }

    public int hashCode() {
        int hashCode = ((((((((Boolean.hashCode(this.shouldShowAlert) * 31) + Boolean.hashCode(this.shouldShowBanner)) * 31) + Boolean.hashCode(this.shouldShowList)) * 31) + Boolean.hashCode(this.shouldPlaySound)) * 31) + Boolean.hashCode(this.shouldSetBadge)) * 31;
        String str = this.priority;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "NotificationBehaviorRecord(shouldShowAlert=" + this.shouldShowAlert + ", shouldShowBanner=" + this.shouldShowBanner + ", shouldShowList=" + this.shouldShowList + ", shouldPlaySound=" + this.shouldPlaySound + ", shouldSetBadge=" + this.shouldSetBadge + ", priority=" + this.priority + ")";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.shouldShowAlert ? 1 : 0);
        dest.writeInt(this.shouldShowBanner ? 1 : 0);
        dest.writeInt(this.shouldShowList ? 1 : 0);
        dest.writeInt(this.shouldPlaySound ? 1 : 0);
        dest.writeInt(this.shouldSetBadge ? 1 : 0);
        dest.writeString(this.priority);
    }

    public NotificationBehaviorRecord(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str) {
        this.shouldShowAlert = z;
        this.shouldShowBanner = z2;
        this.shouldShowList = z3;
        this.shouldPlaySound = z4;
        this.shouldSetBadge = z5;
        this.priority = str;
    }

    public /* synthetic */ NotificationBehaviorRecord(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? false : z5, (i & 32) != 0 ? null : str);
    }

    public final boolean getShouldShowAlert() {
        return this.shouldShowAlert;
    }

    public final boolean getShouldShowBanner() {
        return this.shouldShowBanner;
    }

    public final boolean getShouldShowList() {
        return this.shouldShowList;
    }

    public final boolean getShouldPlaySound() {
        return this.shouldPlaySound;
    }

    public final boolean getShouldSetBadge() {
        return this.shouldSetBadge;
    }

    public final String getPriority() {
        return this.priority;
    }

    public final NotificationPriority getPriorityOverride() {
        String str = this.priority;
        if (str != null) {
            return NotificationPriority.fromEnumValue(str);
        }
        return null;
    }

    public final boolean getShouldPresentAlert() {
        return this.shouldShowBanner || this.shouldShowList || this.shouldShowAlert;
    }
}
