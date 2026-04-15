package expo.modules.notifications.serverregistration;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RegistrationInfo.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lexpo/modules/notifications/serverregistration/RegistrationInfo;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "nonBackedUpRegistrationInfoFile", "Ljava/io/File;", "getNonBackedUpRegistrationInfoFile", "()Ljava/io/File;", "get", "", "set", "", "registrationInfo", "Companion", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class RegistrationInfo {
    public static final String REGISTRATION_INFO_FILE_NAME = "expo_notifications_registration_info.txt";
    private final Context context;

    public RegistrationInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    protected final File getNonBackedUpRegistrationInfoFile() {
        return new File(this.context.getNoBackupFilesDir(), REGISTRATION_INFO_FILE_NAME);
    }

    public final String get() {
        if (getNonBackedUpRegistrationInfoFile().exists()) {
            return FilesKt.readText$default(getNonBackedUpRegistrationInfoFile(), null, 1, null);
        }
        return null;
    }

    public final void set(String registrationInfo) {
        getNonBackedUpRegistrationInfoFile().delete();
        if (registrationInfo != null) {
            FilesKt.writeText$default(getNonBackedUpRegistrationInfoFile(), registrationInfo, null, 2, null);
        }
    }
}
