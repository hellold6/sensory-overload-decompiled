package expo.modules.video.records;

import androidx.media3.common.Format;
import com.google.firebase.messaging.Constants;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.io.Serializable;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Tracks.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u0000 \u00112\u00020\u00012\u00020\u0002:\u0001\u0011B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bR\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f¨\u0006\u0012"}, d2 = {"Lexpo/modules/video/records/SubtitleTrack;", "Lexpo/modules/kotlin/records/Record;", "Ljava/io/Serializable;", "id", "", "language", Constants.ScionAnalytics.PARAM_LABEL, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "getLanguage$annotations", "getLanguage", "getLabel$annotations", "getLabel", "Companion", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubtitleTrack implements Record, Serializable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String id;
    private final String label;
    private final String language;

    @Field
    public static /* synthetic */ void getId$annotations() {
    }

    @Field
    public static /* synthetic */ void getLabel$annotations() {
    }

    @Field
    public static /* synthetic */ void getLanguage$annotations() {
    }

    public SubtitleTrack(String id, String str, String str2) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        this.language = str;
        this.label = str2;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getLabel() {
        return this.label;
    }

    /* compiled from: Tracks.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/video/records/SubtitleTrack$Companion;", "", "<init>", "()V", "fromFormat", "Lexpo/modules/video/records/SubtitleTrack;", "format", "Landroidx/media3/common/Format;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SubtitleTrack fromFormat(Format format) {
            String str;
            String str2;
            if (format == null || (str = format.id) == null || (str2 = format.language) == null) {
                return null;
            }
            return new SubtitleTrack(str, str2, new Locale(str2).getDisplayLanguage());
        }
    }
}
