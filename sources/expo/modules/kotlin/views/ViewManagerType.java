package expo.modules.kotlin.views;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: ViewManagerType.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/kotlin/views/ViewManagerType;", "", "<init>", "(Ljava/lang/String;I)V", "SIMPLE", "GROUP", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ViewManagerType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ViewManagerType[] $VALUES;
    public static final ViewManagerType SIMPLE = new ViewManagerType("SIMPLE", 0);
    public static final ViewManagerType GROUP = new ViewManagerType("GROUP", 1);

    private static final /* synthetic */ ViewManagerType[] $values() {
        return new ViewManagerType[]{SIMPLE, GROUP};
    }

    public static EnumEntries<ViewManagerType> getEntries() {
        return $ENTRIES;
    }

    private ViewManagerType(String str, int i) {
    }

    static {
        ViewManagerType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static ViewManagerType valueOf(String str) {
        return (ViewManagerType) Enum.valueOf(ViewManagerType.class, str);
    }

    public static ViewManagerType[] values() {
        return (ViewManagerType[]) $VALUES.clone();
    }
}
