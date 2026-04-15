package expo.modules.audio;

import expo.modules.kotlin.types.Enumerable;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: AudioRecords.kt */
@kotlin.Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lexpo/modules/audio/InterruptionMode;", "Lexpo/modules/kotlin/types/Enumerable;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "DO_NOT_MIX", "DUCK_OTHERS", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class InterruptionMode implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ InterruptionMode[] $VALUES;
    public static final InterruptionMode DO_NOT_MIX = new InterruptionMode("DO_NOT_MIX", 0, "doNotMix");
    public static final InterruptionMode DUCK_OTHERS = new InterruptionMode("DUCK_OTHERS", 1, "duckOthers");
    private final String value;

    private static final /* synthetic */ InterruptionMode[] $values() {
        return new InterruptionMode[]{DO_NOT_MIX, DUCK_OTHERS};
    }

    public static EnumEntries<InterruptionMode> getEntries() {
        return $ENTRIES;
    }

    private InterruptionMode(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        InterruptionMode[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static InterruptionMode valueOf(String str) {
        return (InterruptionMode) Enum.valueOf(InterruptionMode.class, str);
    }

    public static InterruptionMode[] values() {
        return (InterruptionMode[]) $VALUES.clone();
    }
}
