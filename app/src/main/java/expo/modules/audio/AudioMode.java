package expo.modules.audio;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AudioRecords.kt */
@kotlin.Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0011\u0012\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0016\u0010\r¨\u0006\u0017"}, d2 = {"Lexpo/modules/audio/AudioMode;", "Lexpo/modules/kotlin/records/Record;", "shouldPlayInBackground", "", "shouldRouteThroughEarpiece", "interruptionMode", "Lexpo/modules/audio/InterruptionMode;", "allowsBackgroundRecording", "<init>", "(ZLjava/lang/Boolean;Lexpo/modules/audio/InterruptionMode;Z)V", "getShouldPlayInBackground$annotations", "()V", "getShouldPlayInBackground", "()Z", "getShouldRouteThroughEarpiece$annotations", "getShouldRouteThroughEarpiece", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getInterruptionMode$annotations", "getInterruptionMode", "()Lexpo/modules/audio/InterruptionMode;", "getAllowsBackgroundRecording$annotations", "getAllowsBackgroundRecording", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioMode implements Record {
    private final boolean allowsBackgroundRecording;
    private final InterruptionMode interruptionMode;
    private final boolean shouldPlayInBackground;
    private final Boolean shouldRouteThroughEarpiece;

    @Field
    public static /* synthetic */ void getAllowsBackgroundRecording$annotations() {
    }

    @Field
    public static /* synthetic */ void getInterruptionMode$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldPlayInBackground$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldRouteThroughEarpiece$annotations() {
    }

    public AudioMode(boolean z, Boolean bool, InterruptionMode interruptionMode, boolean z2) {
        this.shouldPlayInBackground = z;
        this.shouldRouteThroughEarpiece = bool;
        this.interruptionMode = interruptionMode;
        this.allowsBackgroundRecording = z2;
    }

    public /* synthetic */ AudioMode(boolean z, Boolean bool, InterruptionMode interruptionMode, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, bool, interruptionMode, (i & 8) != 0 ? false : z2);
    }

    public final boolean getShouldPlayInBackground() {
        return this.shouldPlayInBackground;
    }

    public final Boolean getShouldRouteThroughEarpiece() {
        return this.shouldRouteThroughEarpiece;
    }

    public final InterruptionMode getInterruptionMode() {
        return this.interruptionMode;
    }

    public final boolean getAllowsBackgroundRecording() {
        return this.allowsBackgroundRecording;
    }
}
