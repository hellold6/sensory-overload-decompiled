package expo.modules.medialibrary.next.records;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: AssetField.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lexpo/modules/medialibrary/next/records/AssetField;", "Lexpo/modules/kotlin/types/Enumerable;", "", "key", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "CREATION_TIME", "MODIFICATION_TIME", "MEDIA_TYPE", "WIDTH", "HEIGHT", "DURATION", "toMediaStoreColumn", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetField implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AssetField[] $VALUES;
    private final String key;
    public static final AssetField CREATION_TIME = new AssetField("CREATION_TIME", 0, "creationTime");
    public static final AssetField MODIFICATION_TIME = new AssetField("MODIFICATION_TIME", 1, "modificationTime");
    public static final AssetField MEDIA_TYPE = new AssetField("MEDIA_TYPE", 2, "mediaType");
    public static final AssetField WIDTH = new AssetField("WIDTH", 3, "width");
    public static final AssetField HEIGHT = new AssetField("HEIGHT", 4, "height");
    public static final AssetField DURATION = new AssetField("DURATION", 5, "duration");

    /* compiled from: AssetField.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssetField.values().length];
            try {
                iArr[AssetField.CREATION_TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AssetField.MODIFICATION_TIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AssetField.MEDIA_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AssetField.WIDTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AssetField.HEIGHT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[AssetField.DURATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ AssetField[] $values() {
        return new AssetField[]{CREATION_TIME, MODIFICATION_TIME, MEDIA_TYPE, WIDTH, HEIGHT, DURATION};
    }

    public static EnumEntries<AssetField> getEntries() {
        return $ENTRIES;
    }

    private AssetField(String str, int i, String str2) {
        this.key = str2;
    }

    public final String getKey() {
        return this.key;
    }

    static {
        AssetField[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final String toMediaStoreColumn() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return "datetaken";
            case 2:
                return "date_modified";
            case 3:
                return "media_type";
            case 4:
                return "width";
            case 5:
                return "height";
            case 6:
                return "duration";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static AssetField valueOf(String str) {
        return (AssetField) Enum.valueOf(AssetField.class, str);
    }

    public static AssetField[] values() {
        return (AssetField[]) $VALUES.clone();
    }
}
