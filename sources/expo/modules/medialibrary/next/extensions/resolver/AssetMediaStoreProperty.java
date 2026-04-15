package expo.modules.medialibrary.next.extensions.resolver;

import android.database.Cursor;
import com.google.common.net.HttpHeaders;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: AssetMediaStoreItem.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0019B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0010\u001a\u00020\u0011*\u00020\u0012J\f\u0010\u0013\u001a\u0004\u0018\u00010\u0003*\u00020\u0012J\u0011\u0010\u0014\u001a\u0004\u0018\u00010\u0011*\u00020\u0012¢\u0006\u0002\u0010\u0015J\u0011\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u0012¢\u0006\u0002\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u001a"}, d2 = {"Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreProperty;", "", "column", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getColumn", "()Ljava/lang/String;", "DisplayName", "Height", HttpHeaders.WIDTH, "DateTaken", "DateModified", "Duration", "Data", "BucketId", "columnIndex", "", "Landroid/database/Cursor;", "getString", "getInt", "(Landroid/database/Cursor;)Ljava/lang/Integer;", "getLong", "", "(Landroid/database/Cursor;)Ljava/lang/Long;", "Companion", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetMediaStoreProperty {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AssetMediaStoreProperty[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final String column;
    public static final AssetMediaStoreProperty DisplayName = new AssetMediaStoreProperty("DisplayName", 0, "_display_name");
    public static final AssetMediaStoreProperty Height = new AssetMediaStoreProperty("Height", 1, "height");
    public static final AssetMediaStoreProperty Width = new AssetMediaStoreProperty(HttpHeaders.WIDTH, 2, "width");
    public static final AssetMediaStoreProperty DateTaken = new AssetMediaStoreProperty("DateTaken", 3, "datetaken");
    public static final AssetMediaStoreProperty DateModified = new AssetMediaStoreProperty("DateModified", 4, "date_modified");
    public static final AssetMediaStoreProperty Duration = new AssetMediaStoreProperty("Duration", 5, "duration");
    public static final AssetMediaStoreProperty Data = new AssetMediaStoreProperty("Data", 6, "_data");
    public static final AssetMediaStoreProperty BucketId = new AssetMediaStoreProperty("BucketId", 7, "bucket_id");

    private static final /* synthetic */ AssetMediaStoreProperty[] $values() {
        return new AssetMediaStoreProperty[]{DisplayName, Height, Width, DateTaken, DateModified, Duration, Data, BucketId};
    }

    public static EnumEntries<AssetMediaStoreProperty> getEntries() {
        return $ENTRIES;
    }

    private AssetMediaStoreProperty(String str, int i, String str2) {
        this.column = str2;
    }

    public final String getColumn() {
        return this.column;
    }

    static {
        AssetMediaStoreProperty[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    public final int columnIndex(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "<this>");
        return cursor.getColumnIndexOrThrow(this.column);
    }

    public final String getString(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "<this>");
        return cursor.getString(columnIndex(cursor));
    }

    public final Integer getInt(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "<this>");
        return Integer.valueOf(cursor.getInt(columnIndex(cursor)));
    }

    public final Long getLong(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "<this>");
        return Long.valueOf(cursor.getLong(columnIndex(cursor)));
    }

    /* compiled from: AssetMediaStoreItem.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreProperty$Companion;", "", "<init>", "()V", "projection", "", "", "includeDuration", "", "(Z)[Ljava/lang/String;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String[] projection(boolean includeDuration) {
            AssetMediaStoreProperty[] values = AssetMediaStoreProperty.values();
            ArrayList arrayList = new ArrayList();
            for (AssetMediaStoreProperty assetMediaStoreProperty : values) {
                if (includeDuration || assetMediaStoreProperty != AssetMediaStoreProperty.Duration) {
                    arrayList.add(assetMediaStoreProperty);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(((AssetMediaStoreProperty) it.next()).getColumn());
            }
            return (String[]) arrayList3.toArray(new String[0]);
        }
    }

    public static AssetMediaStoreProperty valueOf(String str) {
        return (AssetMediaStoreProperty) Enum.valueOf(AssetMediaStoreProperty.class, str);
    }

    public static AssetMediaStoreProperty[] values() {
        return (AssetMediaStoreProperty[]) $VALUES.clone();
    }
}
