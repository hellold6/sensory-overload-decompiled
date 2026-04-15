package expo.modules.video.enums;

import com.facebook.hermes.intl.Constants;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: FullscreenOrientation.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0010\u001a\u00020\u0011R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0012"}, d2 = {"Lexpo/modules/video/enums/FullscreenOrientation;", "Lexpo/modules/kotlin/types/Enumerable;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "LANDSCAPE", "PORTRAIT", "LANDSCAPE_LEFT", "LANDSCAPE_RIGHT", "PORTRAIT_UP", "PORTRAIT_DOWN", "DEFAULT", "toActivityOrientation", "", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FullscreenOrientation implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ FullscreenOrientation[] $VALUES;
    private final String value;
    public static final FullscreenOrientation LANDSCAPE = new FullscreenOrientation("LANDSCAPE", 0, "landscape");
    public static final FullscreenOrientation PORTRAIT = new FullscreenOrientation("PORTRAIT", 1, "portrait");
    public static final FullscreenOrientation LANDSCAPE_LEFT = new FullscreenOrientation("LANDSCAPE_LEFT", 2, "landscapeLeft");
    public static final FullscreenOrientation LANDSCAPE_RIGHT = new FullscreenOrientation("LANDSCAPE_RIGHT", 3, "landscapeRight");
    public static final FullscreenOrientation PORTRAIT_UP = new FullscreenOrientation("PORTRAIT_UP", 4, "portraitUp");
    public static final FullscreenOrientation PORTRAIT_DOWN = new FullscreenOrientation("PORTRAIT_DOWN", 5, "portraitDown");
    public static final FullscreenOrientation DEFAULT = new FullscreenOrientation("DEFAULT", 6, Constants.COLLATION_DEFAULT);

    /* compiled from: FullscreenOrientation.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FullscreenOrientation.values().length];
            try {
                iArr[FullscreenOrientation.LANDSCAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FullscreenOrientation.PORTRAIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FullscreenOrientation.LANDSCAPE_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FullscreenOrientation.LANDSCAPE_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[FullscreenOrientation.PORTRAIT_UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[FullscreenOrientation.PORTRAIT_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[FullscreenOrientation.DEFAULT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ FullscreenOrientation[] $values() {
        return new FullscreenOrientation[]{LANDSCAPE, PORTRAIT, LANDSCAPE_LEFT, LANDSCAPE_RIGHT, PORTRAIT_UP, PORTRAIT_DOWN, DEFAULT};
    }

    public static EnumEntries<FullscreenOrientation> getEntries() {
        return $ENTRIES;
    }

    private FullscreenOrientation(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        FullscreenOrientation[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final int toActivityOrientation() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return 11;
            case 2:
                return 12;
            case 3:
                return 0;
            case 4:
                return 8;
            case 5:
                return 1;
            case 6:
                return 9;
            case 7:
                return -1;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static FullscreenOrientation valueOf(String str) {
        return (FullscreenOrientation) Enum.valueOf(FullscreenOrientation.class, str);
    }

    public static FullscreenOrientation[] values() {
        return (FullscreenOrientation[]) $VALUES.clone();
    }
}
