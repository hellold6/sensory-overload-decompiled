package androidx.media3.session;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda46 implements Runnable {
    public final /* synthetic */ MediaController f$0;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda46(MediaController mediaController) {
        this.f$0 = mediaController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.release();
    }
}
