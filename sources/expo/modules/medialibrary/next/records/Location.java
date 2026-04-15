package expo.modules.medialibrary.next.records;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Location.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ&\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000b\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000b\u0012\u0004\b\f\u0010\b\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lexpo/modules/medialibrary/next/records/Location;", "Lexpo/modules/kotlin/records/Record;", "latitude", "", "longitude", "<init>", "(Ljava/lang/Double;Ljava/lang/Double;)V", "getLatitude$annotations", "()V", "getLatitude", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLongitude$annotations", "getLongitude", "component1", "component2", "copy", "(Ljava/lang/Double;Ljava/lang/Double;)Lexpo/modules/medialibrary/next/records/Location;", "equals", "", "other", "", "hashCode", "", "toString", "", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Location implements Record {
    private final Double latitude;
    private final Double longitude;

    public static /* synthetic */ Location copy$default(Location location, Double d, Double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = location.latitude;
        }
        if ((i & 2) != 0) {
            d2 = location.longitude;
        }
        return location.copy(d, d2);
    }

    @Field
    public static /* synthetic */ void getLatitude$annotations() {
    }

    @Field
    public static /* synthetic */ void getLongitude$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final Double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component2, reason: from getter */
    public final Double getLongitude() {
        return this.longitude;
    }

    public final Location copy(Double latitude, Double longitude) {
        return new Location(latitude, longitude);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Location)) {
            return false;
        }
        Location location = (Location) other;
        return Intrinsics.areEqual((Object) this.latitude, (Object) location.latitude) && Intrinsics.areEqual((Object) this.longitude, (Object) location.longitude);
    }

    public int hashCode() {
        Double d = this.latitude;
        int hashCode = (d == null ? 0 : d.hashCode()) * 31;
        Double d2 = this.longitude;
        return hashCode + (d2 != null ? d2.hashCode() : 0);
    }

    public String toString() {
        return "Location(latitude=" + this.latitude + ", longitude=" + this.longitude + ")";
    }

    public Location(Double d, Double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public final Double getLatitude() {
        return this.latitude;
    }

    public final Double getLongitude() {
        return this.longitude;
    }
}
