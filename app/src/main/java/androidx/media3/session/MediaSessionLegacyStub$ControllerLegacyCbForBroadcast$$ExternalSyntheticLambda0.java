package androidx.media3.session;

import android.os.Handler;
import androidx.media3.session.MediaSessionLegacyStub;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0 implements Executor {
    public final /* synthetic */ Handler f$0;

    public /* synthetic */ MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0(Handler handler) {
        this.f$0 = handler;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        MediaSessionLegacyStub.ControllerLegacyCbForBroadcast.$r8$lambda$mCEi04OcFi8gu0FD463twzV2nG8(this.f$0, runnable);
    }
}
