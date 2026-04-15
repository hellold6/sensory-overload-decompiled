package expo.modules.video.enums;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: AudioMixingMode.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u0011"}, d2 = {"Lexpo/modules/video/enums/AudioMixingMode;", "Lexpo/modules/kotlin/types/Enumerable;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "MIX_WITH_OTHERS", "DUCK_OTHERS", "AUTO", "DO_NOT_MIX", "priority", "", "getPriority", "()I", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioMixingMode implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AudioMixingMode[] $VALUES;
    private final String value;
    public static final AudioMixingMode MIX_WITH_OTHERS = new AudioMixingMode("MIX_WITH_OTHERS", 0, "mixWithOthers");
    public static final AudioMixingMode DUCK_OTHERS = new AudioMixingMode("DUCK_OTHERS", 1, "duckOthers");
    public static final AudioMixingMode AUTO = new AudioMixingMode("AUTO", 2, "auto");
    public static final AudioMixingMode DO_NOT_MIX = new AudioMixingMode("DO_NOT_MIX", 3, "doNotMix");

    /* compiled from: AudioMixingMode.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AudioMixingMode.values().length];
            try {
                iArr[AudioMixingMode.DO_NOT_MIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AudioMixingMode.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AudioMixingMode.DUCK_OTHERS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AudioMixingMode.MIX_WITH_OTHERS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ AudioMixingMode[] $values() {
        return new AudioMixingMode[]{MIX_WITH_OTHERS, DUCK_OTHERS, AUTO, DO_NOT_MIX};
    }

    public static EnumEntries<AudioMixingMode> getEntries() {
        return $ENTRIES;
    }

    private AudioMixingMode(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        AudioMixingMode[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final int getPriority() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 3;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 1;
        }
        if (i == 4) {
            return 0;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static AudioMixingMode valueOf(String str) {
        return (AudioMixingMode) Enum.valueOf(AudioMixingMode.class, str);
    }

    public static AudioMixingMode[] values() {
        return (AudioMixingMode[]) $VALUES.clone();
    }
}
