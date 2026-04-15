package expo.modules.haptics;

import android.view.HapticFeedbackConstants;
import expo.modules.haptics.arguments.HapticTypeNotSupportedException;
import expo.modules.haptics.arguments.HapticsNotSupportedException;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: HapticsRecord.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u001c\u001a\u00020\u001dR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006\u001e"}, d2 = {"Lexpo/modules/haptics/HapticType;", "Lexpo/modules/kotlin/types/Enumerable;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "CONFIRM", "REJECT", "GESTURE_START", "GESTURE_END", "TOGGLE_ON", "TOGGLE_OFF", "CLOCK_TICK", "CONTEXT_CLICK", "DRAG_START", "KEYBOARD_TAP", "KEYBOARD_PRESS", "KEYBOARD_RELEASE", "LONG_PRESS", "VIRTUAL_KEY", "NO_HAPTICS", "SEGMENT_TICK", "SEGMENT_FREQUENT_TICK", "TEXT_HANDLE_MOVE", "VIRTUAL_KEY_RELEASE", "toHapticFeedbackType", "", "expo-haptics_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HapticType implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ HapticType[] $VALUES;
    private final String value;
    public static final HapticType CONFIRM = new HapticType("CONFIRM", 0, "confirm");
    public static final HapticType REJECT = new HapticType("REJECT", 1, "reject");
    public static final HapticType GESTURE_START = new HapticType("GESTURE_START", 2, "gesture-start");
    public static final HapticType GESTURE_END = new HapticType("GESTURE_END", 3, "gesture-end");
    public static final HapticType TOGGLE_ON = new HapticType("TOGGLE_ON", 4, "toggle-on");
    public static final HapticType TOGGLE_OFF = new HapticType("TOGGLE_OFF", 5, "toggle-off");
    public static final HapticType CLOCK_TICK = new HapticType("CLOCK_TICK", 6, "clock-tick");
    public static final HapticType CONTEXT_CLICK = new HapticType("CONTEXT_CLICK", 7, "context-click");
    public static final HapticType DRAG_START = new HapticType("DRAG_START", 8, "drag-start");
    public static final HapticType KEYBOARD_TAP = new HapticType("KEYBOARD_TAP", 9, "keyboard-tap");
    public static final HapticType KEYBOARD_PRESS = new HapticType("KEYBOARD_PRESS", 10, "keyboard-press");
    public static final HapticType KEYBOARD_RELEASE = new HapticType("KEYBOARD_RELEASE", 11, "keyboard-release");
    public static final HapticType LONG_PRESS = new HapticType("LONG_PRESS", 12, "long-press");
    public static final HapticType VIRTUAL_KEY = new HapticType("VIRTUAL_KEY", 13, "virtual-key");
    public static final HapticType NO_HAPTICS = new HapticType("NO_HAPTICS", 14, "no-haptics");
    public static final HapticType SEGMENT_TICK = new HapticType("SEGMENT_TICK", 15, "segment-tick");
    public static final HapticType SEGMENT_FREQUENT_TICK = new HapticType("SEGMENT_FREQUENT_TICK", 16, "segment-frequent-tick");
    public static final HapticType TEXT_HANDLE_MOVE = new HapticType("TEXT_HANDLE_MOVE", 17, "text-handle-move");
    public static final HapticType VIRTUAL_KEY_RELEASE = new HapticType("VIRTUAL_KEY_RELEASE", 18, "virtual-key-release");

    /* compiled from: HapticsRecord.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HapticType.values().length];
            try {
                iArr[HapticType.CLOCK_TICK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HapticType.CONTEXT_CLICK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HapticType.KEYBOARD_TAP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[HapticType.LONG_PRESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[HapticType.VIRTUAL_KEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ HapticType[] $values() {
        return new HapticType[]{CONFIRM, REJECT, GESTURE_START, GESTURE_END, TOGGLE_ON, TOGGLE_OFF, CLOCK_TICK, CONTEXT_CLICK, DRAG_START, KEYBOARD_TAP, KEYBOARD_PRESS, KEYBOARD_RELEASE, LONG_PRESS, VIRTUAL_KEY, NO_HAPTICS, SEGMENT_TICK, SEGMENT_FREQUENT_TICK, TEXT_HANDLE_MOVE, VIRTUAL_KEY_RELEASE};
    }

    public static EnumEntries<HapticType> getEntries() {
        return $ENTRIES;
    }

    private HapticType(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        HapticType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final int toHapticFeedbackType() {
        try {
            return HapticFeedbackConstants.class.getDeclaredField(name()).getInt(null);
        } catch (IllegalAccessException unused) {
            throw new HapticTypeNotSupportedException(this.value);
        } catch (NoSuchFieldException unused2) {
            int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
            if (i == 1) {
                return 4;
            }
            if (i == 2) {
                return 6;
            }
            if (i == 3) {
                return 3;
            }
            if (i == 4) {
                return 0;
            }
            if (i == 5) {
                return 1;
            }
            throw new HapticsNotSupportedException();
        }
    }

    public static HapticType valueOf(String str) {
        return (HapticType) Enum.valueOf(HapticType.class, str);
    }

    public static HapticType[] values() {
        return (HapticType[]) $VALUES.clone();
    }
}
