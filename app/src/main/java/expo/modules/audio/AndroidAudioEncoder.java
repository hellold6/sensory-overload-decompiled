package expo.modules.audio;

import com.facebook.hermes.intl.Constants;
import expo.modules.kotlin.types.Enumerable;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: AudioRecords.kt */
@kotlin.Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0011"}, d2 = {"Lexpo/modules/audio/AndroidAudioEncoder;", "Lexpo/modules/kotlin/types/Enumerable;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "DEFAULT", "AMR_NB", "AMR_WB", "AAC", "HE_AAC", "AAC_ELD", "toMediaEncoding", "", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidAudioEncoder implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AndroidAudioEncoder[] $VALUES;
    private final String value;
    public static final AndroidAudioEncoder DEFAULT = new AndroidAudioEncoder("DEFAULT", 0, Constants.COLLATION_DEFAULT);
    public static final AndroidAudioEncoder AMR_NB = new AndroidAudioEncoder("AMR_NB", 1, "amr_nb");
    public static final AndroidAudioEncoder AMR_WB = new AndroidAudioEncoder("AMR_WB", 2, "amr_wb");
    public static final AndroidAudioEncoder AAC = new AndroidAudioEncoder("AAC", 3, "aac");
    public static final AndroidAudioEncoder HE_AAC = new AndroidAudioEncoder("HE_AAC", 4, "he_aac");
    public static final AndroidAudioEncoder AAC_ELD = new AndroidAudioEncoder("AAC_ELD", 5, "aac_eld");

    /* compiled from: AudioRecords.kt */
    @kotlin.Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AndroidAudioEncoder.values().length];
            try {
                iArr[AndroidAudioEncoder.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AndroidAudioEncoder.AMR_NB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AndroidAudioEncoder.AMR_WB.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AndroidAudioEncoder.AAC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AndroidAudioEncoder.HE_AAC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[AndroidAudioEncoder.AAC_ELD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ AndroidAudioEncoder[] $values() {
        return new AndroidAudioEncoder[]{DEFAULT, AMR_NB, AMR_WB, AAC, HE_AAC, AAC_ELD};
    }

    public static EnumEntries<AndroidAudioEncoder> getEntries() {
        return $ENTRIES;
    }

    private AndroidAudioEncoder(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        AndroidAudioEncoder[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final int toMediaEncoding() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static AndroidAudioEncoder valueOf(String str) {
        return (AndroidAudioEncoder) Enum.valueOf(AndroidAudioEncoder.class, str);
    }

    public static AndroidAudioEncoder[] values() {
        return (AndroidAudioEncoder[]) $VALUES.clone();
    }
}
