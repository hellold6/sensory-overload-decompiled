package expo.modules.audio;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;

/* compiled from: AudioRecords.kt */
@kotlin.Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lexpo/modules/audio/AudioLockScreenOptions;", "Lexpo/modules/kotlin/records/Record;", "showSeekForward", "", "showSeekBackward", "<init>", "(ZZ)V", "getShowSeekForward$annotations", "()V", "getShowSeekForward", "()Z", "getShowSeekBackward$annotations", "getShowSeekBackward", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioLockScreenOptions implements Record {
    private final boolean showSeekBackward;
    private final boolean showSeekForward;

    @Field
    public static /* synthetic */ void getShowSeekBackward$annotations() {
    }

    @Field
    public static /* synthetic */ void getShowSeekForward$annotations() {
    }

    public AudioLockScreenOptions(boolean z, boolean z2) {
        this.showSeekForward = z;
        this.showSeekBackward = z2;
    }

    public final boolean getShowSeekForward() {
        return this.showSeekForward;
    }

    public final boolean getShowSeekBackward() {
        return this.showSeekBackward;
    }
}
