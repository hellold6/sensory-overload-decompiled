package expo.modules.audio;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.Map;

/* compiled from: AudioRecords.kt */
@kotlin.Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\n\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR*\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lexpo/modules/audio/AudioSource;", "Lexpo/modules/kotlin/records/Record;", "uri", "", "headers", "", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getUri$annotations", "()V", "getUri", "()Ljava/lang/String;", "getHeaders$annotations", "getHeaders", "()Ljava/util/Map;", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioSource implements Record {
    private final Map<String, String> headers;
    private final String uri;

    @Field
    public static /* synthetic */ void getHeaders$annotations() {
    }

    @Field
    public static /* synthetic */ void getUri$annotations() {
    }

    public AudioSource(String str, Map<String, String> map) {
        this.uri = str;
        this.headers = map;
    }

    public final String getUri() {
        return this.uri;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }
}
