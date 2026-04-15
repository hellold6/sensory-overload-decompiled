package expo.modules.video.enums;

import com.google.firebase.messaging.Constants;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: PlayerStatus.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lexpo/modules/video/enums/PlayerStatus;", "Lexpo/modules/kotlin/types/Enumerable;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "IDLE", "LOADING", "READY_TO_PLAY", "ERROR", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlayerStatus implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PlayerStatus[] $VALUES;
    private final String value;
    public static final PlayerStatus IDLE = new PlayerStatus("IDLE", 0, "idle");
    public static final PlayerStatus LOADING = new PlayerStatus("LOADING", 1, "loading");
    public static final PlayerStatus READY_TO_PLAY = new PlayerStatus("READY_TO_PLAY", 2, "readyToPlay");
    public static final PlayerStatus ERROR = new PlayerStatus("ERROR", 3, Constants.IPC_BUNDLE_KEY_SEND_ERROR);

    private static final /* synthetic */ PlayerStatus[] $values() {
        return new PlayerStatus[]{IDLE, LOADING, READY_TO_PLAY, ERROR};
    }

    public static EnumEntries<PlayerStatus> getEntries() {
        return $ENTRIES;
    }

    private PlayerStatus(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        PlayerStatus[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static PlayerStatus valueOf(String str) {
        return (PlayerStatus) Enum.valueOf(PlayerStatus.class, str);
    }

    public static PlayerStatus[] values() {
        return (PlayerStatus[]) $VALUES.clone();
    }
}
