package expo.modules.audio;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;

/* compiled from: AudioRecords.kt */
@kotlin.Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000b\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000b\u0012\u0004\b\f\u0010\b\u001a\u0004\b\r\u0010\n¨\u0006\u000e"}, d2 = {"Lexpo/modules/audio/RecordOptions;", "Lexpo/modules/kotlin/records/Record;", "atTime", "", "forDuration", "<init>", "(Ljava/lang/Double;Ljava/lang/Double;)V", "getAtTime$annotations", "()V", "getAtTime", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getForDuration$annotations", "getForDuration", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RecordOptions implements Record {
    private final Double atTime;
    private final Double forDuration;

    @Field
    public static /* synthetic */ void getAtTime$annotations() {
    }

    @Field
    public static /* synthetic */ void getForDuration$annotations() {
    }

    public RecordOptions(Double d, Double d2) {
        this.atTime = d;
        this.forDuration = d2;
    }

    public final Double getAtTime() {
        return this.atTime;
    }

    public final Double getForDuration() {
        return this.forDuration;
    }
}
