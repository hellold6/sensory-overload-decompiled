package expo.modules.audio;

import com.facebook.hermes.intl.Constants;
import expo.modules.kotlin.types.Enumerable;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: AudioRecords.kt */
@kotlin.Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0011\u001a\u00020\u0012R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0013"}, d2 = {"Lexpo/modules/audio/RecordingSource;", "Lexpo/modules/kotlin/types/Enumerable;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "CAMCORDER", "DEFAULT", "MIC", "REMOTE_SUBMIX", "UNPROCESSED", "VOICE_COMMUNICATION", "VOICE_PERFORMANCE", "VOICE_RECOGNITION", "toAudioSource", "", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RecordingSource implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ RecordingSource[] $VALUES;
    public static final RecordingSource CAMCORDER = new RecordingSource("CAMCORDER", 0, "camcorder");
    public static final RecordingSource DEFAULT = new RecordingSource("DEFAULT", 1, Constants.COLLATION_DEFAULT);
    public static final RecordingSource MIC = new RecordingSource("MIC", 2, "mic");
    public static final RecordingSource REMOTE_SUBMIX = new RecordingSource("REMOTE_SUBMIX", 3, "remote_submix");
    public static final RecordingSource UNPROCESSED = new RecordingSource("UNPROCESSED", 4, "unprocessed");
    public static final RecordingSource VOICE_COMMUNICATION = new RecordingSource("VOICE_COMMUNICATION", 5, "voice_communication");
    public static final RecordingSource VOICE_PERFORMANCE = new RecordingSource("VOICE_PERFORMANCE", 6, "voice_performance");
    public static final RecordingSource VOICE_RECOGNITION = new RecordingSource("VOICE_RECOGNITION", 7, "voice_recognition");
    private final String value;

    /* compiled from: AudioRecords.kt */
    @kotlin.Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RecordingSource.values().length];
            try {
                iArr[RecordingSource.CAMCORDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RecordingSource.DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[RecordingSource.MIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[RecordingSource.REMOTE_SUBMIX.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[RecordingSource.UNPROCESSED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[RecordingSource.VOICE_COMMUNICATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[RecordingSource.VOICE_PERFORMANCE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[RecordingSource.VOICE_RECOGNITION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ RecordingSource[] $values() {
        return new RecordingSource[]{CAMCORDER, DEFAULT, MIC, REMOTE_SUBMIX, UNPROCESSED, VOICE_COMMUNICATION, VOICE_PERFORMANCE, VOICE_RECOGNITION};
    }

    public static EnumEntries<RecordingSource> getEntries() {
        return $ENTRIES;
    }

    private RecordingSource(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        RecordingSource[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final int toAudioSource() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return 5;
            case 2:
                return 0;
            case 3:
                return 1;
            case 4:
                return 8;
            case 5:
                return 9;
            case 6:
                return 7;
            case 7:
                return 10;
            case 8:
                return 6;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static RecordingSource valueOf(String str) {
        return (RecordingSource) Enum.valueOf(RecordingSource.class, str);
    }

    public static RecordingSource[] values() {
        return (RecordingSource[]) $VALUES.clone();
    }
}
