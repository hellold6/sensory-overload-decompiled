package expo.modules.fetch;

import com.google.firebase.messaging.Constants;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: NativeRequestRedirect.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0080\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/fetch/NativeRequestRedirect;", "Lexpo/modules/kotlin/types/Enumerable;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "FOLLOW", "ERROR", "MANUAL", "expo_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NativeRequestRedirect implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ NativeRequestRedirect[] $VALUES;
    private final String value;
    public static final NativeRequestRedirect FOLLOW = new NativeRequestRedirect("FOLLOW", 0, "follow");
    public static final NativeRequestRedirect ERROR = new NativeRequestRedirect("ERROR", 1, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
    public static final NativeRequestRedirect MANUAL = new NativeRequestRedirect("MANUAL", 2, "manual");

    private static final /* synthetic */ NativeRequestRedirect[] $values() {
        return new NativeRequestRedirect[]{FOLLOW, ERROR, MANUAL};
    }

    public static EnumEntries<NativeRequestRedirect> getEntries() {
        return $ENTRIES;
    }

    private NativeRequestRedirect(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        NativeRequestRedirect[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static NativeRequestRedirect valueOf(String str) {
        return (NativeRequestRedirect) Enum.valueOf(NativeRequestRedirect.class, str);
    }

    public static NativeRequestRedirect[] values() {
        return (NativeRequestRedirect[]) $VALUES.clone();
    }
}
