package expo.modules.medialibrary.next.extensions.resolver;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssetMediaStoreItem.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010 \u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jn\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0005HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0018\u0010\u0016R\u0015\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0019\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010¨\u0006+"}, d2 = {"Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreItem;", "", "displayName", "", "height", "", "width", "dateTaken", "", "dateModified", "duration", "data", "bucketId", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "getHeight", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getWidth", "getDateTaken", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDateModified", "getDuration", "getData", "getBucketId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreItem;", "equals", "", "other", "hashCode", "toString", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class AssetMediaStoreItem {
    private final String bucketId;
    private final String data;
    private final Long dateModified;
    private final Long dateTaken;
    private final String displayName;
    private final Long duration;
    private final Integer height;
    private final Integer width;

    public static /* synthetic */ AssetMediaStoreItem copy$default(AssetMediaStoreItem assetMediaStoreItem, String str, Integer num, Integer num2, Long l, Long l2, Long l3, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = assetMediaStoreItem.displayName;
        }
        if ((i & 2) != 0) {
            num = assetMediaStoreItem.height;
        }
        if ((i & 4) != 0) {
            num2 = assetMediaStoreItem.width;
        }
        if ((i & 8) != 0) {
            l = assetMediaStoreItem.dateTaken;
        }
        if ((i & 16) != 0) {
            l2 = assetMediaStoreItem.dateModified;
        }
        if ((i & 32) != 0) {
            l3 = assetMediaStoreItem.duration;
        }
        if ((i & 64) != 0) {
            str2 = assetMediaStoreItem.data;
        }
        if ((i & 128) != 0) {
            str3 = assetMediaStoreItem.bucketId;
        }
        String str4 = str2;
        String str5 = str3;
        Long l4 = l2;
        Long l5 = l3;
        return assetMediaStoreItem.copy(str, num, num2, l, l4, l5, str4, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getHeight() {
        return this.height;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getWidth() {
        return this.width;
    }

    /* renamed from: component4, reason: from getter */
    public final Long getDateTaken() {
        return this.dateTaken;
    }

    /* renamed from: component5, reason: from getter */
    public final Long getDateModified() {
        return this.dateModified;
    }

    /* renamed from: component6, reason: from getter */
    public final Long getDuration() {
        return this.duration;
    }

    /* renamed from: component7, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* renamed from: component8, reason: from getter */
    public final String getBucketId() {
        return this.bucketId;
    }

    public final AssetMediaStoreItem copy(String displayName, Integer height, Integer width, Long dateTaken, Long dateModified, Long duration, String data, String bucketId) {
        return new AssetMediaStoreItem(displayName, height, width, dateTaken, dateModified, duration, data, bucketId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssetMediaStoreItem)) {
            return false;
        }
        AssetMediaStoreItem assetMediaStoreItem = (AssetMediaStoreItem) other;
        return Intrinsics.areEqual(this.displayName, assetMediaStoreItem.displayName) && Intrinsics.areEqual(this.height, assetMediaStoreItem.height) && Intrinsics.areEqual(this.width, assetMediaStoreItem.width) && Intrinsics.areEqual(this.dateTaken, assetMediaStoreItem.dateTaken) && Intrinsics.areEqual(this.dateModified, assetMediaStoreItem.dateModified) && Intrinsics.areEqual(this.duration, assetMediaStoreItem.duration) && Intrinsics.areEqual(this.data, assetMediaStoreItem.data) && Intrinsics.areEqual(this.bucketId, assetMediaStoreItem.bucketId);
    }

    public int hashCode() {
        String str = this.displayName;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.height;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.width;
        int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Long l = this.dateTaken;
        int hashCode4 = (hashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.dateModified;
        int hashCode5 = (hashCode4 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.duration;
        int hashCode6 = (hashCode5 + (l3 == null ? 0 : l3.hashCode())) * 31;
        String str2 = this.data;
        int hashCode7 = (hashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.bucketId;
        return hashCode7 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "AssetMediaStoreItem(displayName=" + this.displayName + ", height=" + this.height + ", width=" + this.width + ", dateTaken=" + this.dateTaken + ", dateModified=" + this.dateModified + ", duration=" + this.duration + ", data=" + this.data + ", bucketId=" + this.bucketId + ")";
    }

    public AssetMediaStoreItem(String str, Integer num, Integer num2, Long l, Long l2, Long l3, String str2, String str3) {
        this.displayName = str;
        this.height = num;
        this.width = num2;
        this.dateTaken = l;
        this.dateModified = l2;
        this.duration = l3;
        this.data = str2;
        this.bucketId = str3;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final Integer getHeight() {
        return this.height;
    }

    public final Integer getWidth() {
        return this.width;
    }

    public final Long getDateTaken() {
        return this.dateTaken;
    }

    public final Long getDateModified() {
        return this.dateModified;
    }

    public final Long getDuration() {
        return this.duration;
    }

    public final String getData() {
        return this.data;
    }

    public final String getBucketId() {
        return this.bucketId;
    }
}
