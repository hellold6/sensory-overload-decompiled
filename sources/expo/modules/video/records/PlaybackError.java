package expo.modules.video.records;

import androidx.media3.common.PlaybackException;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import expo.modules.notifications.service.NotificationsService;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlaybackError.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u000f2\u00020\u00012\u00020\u0002:\u0001\u000fB\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tR&\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0006¨\u0006\u0010"}, d2 = {"Lexpo/modules/video/records/PlaybackError;", "Lexpo/modules/kotlin/records/Record;", "Ljava/io/Serializable;", "message", "", "<init>", "(Ljava/lang/String;)V", NotificationsService.EXCEPTION_KEY, "Landroidx/media3/common/PlaybackException;", "(Landroidx/media3/common/PlaybackException;)V", "getMessage$annotations", "()V", "getMessage", "()Ljava/lang/String;", "setMessage", "Companion", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlaybackError implements Record, Serializable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String message;

    /* JADX WARN: Multi-variable type inference failed */
    public PlaybackError() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Field
    public static /* synthetic */ void getMessage$annotations() {
    }

    public PlaybackError(String str) {
        this.message = str;
    }

    public /* synthetic */ PlaybackError(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String str) {
        this.message = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PlaybackError(PlaybackException exception) {
        this(INSTANCE.errorMessageFromException(exception));
        Intrinsics.checkNotNullParameter(exception, "exception");
    }

    /* compiled from: PlaybackError.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"Lexpo/modules/video/records/PlaybackError$Companion;", "", "<init>", "()V", "errorMessageFromException", "", NotificationsService.EXCEPTION_KEY, "Landroidx/media3/common/PlaybackException;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String errorMessageFromException(PlaybackException exception) {
            String str;
            String localizedMessage = exception.getLocalizedMessage();
            Throwable cause = exception.getCause();
            if (cause == null || (str = cause.getLocalizedMessage()) == null) {
                str = "";
            }
            return "A playback exception has occurred: " + (localizedMessage + " " + str);
        }
    }
}
