package com.facebook.react.devsupport;

import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.debug.FpsDebugFrameCallback;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: FpsView.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\rH\u0014J0\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/facebook/react/devsupport/FpsView;", "Landroid/widget/FrameLayout;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "<init>", "(Lcom/facebook/react/bridge/ReactContext;)V", "textView", "Landroid/widget/TextView;", "frameCallback", "Lcom/facebook/react/modules/debug/FpsDebugFrameCallback;", "fpsMonitorRunnable", "Lcom/facebook/react/devsupport/FpsView$FPSMonitorRunnable;", "onAttachedToWindow", "", "onDetachedFromWindow", "setCurrentFPS", "currentFPS", "", "currentJSFPS", "droppedUIFrames", "", "total4PlusFrameStutters", "runningOnFabric", "", "FPSMonitorRunnable", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FpsView extends FrameLayout {
    private static final int UPDATE_INTERVAL_MS = 500;
    private final FPSMonitorRunnable fpsMonitorRunnable;
    private final FpsDebugFrameCallback frameCallback;
    private final TextView textView;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FpsView(com.facebook.react.bridge.ReactContext r10) {
        /*
            r9 = this;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            r0 = r10
            android.content.Context r0 = (android.content.Context) r0
            r9.<init>(r0)
            int r1 = com.facebook.react.R.layout.fps_view
            r2 = r9
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            android.widget.FrameLayout.inflate(r0, r1, r2)
            int r0 = com.facebook.react.R.id.fps_text
            android.view.View r0 = r9.findViewById(r0)
            java.lang.String r1 = "null cannot be cast to non-null type android.widget.TextView"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r9.textView = r0
            com.facebook.react.modules.debug.FpsDebugFrameCallback r0 = new com.facebook.react.modules.debug.FpsDebugFrameCallback
            r0.<init>(r10)
            r9.frameCallback = r0
            com.facebook.react.devsupport.FpsView$FPSMonitorRunnable r10 = new com.facebook.react.devsupport.FpsView$FPSMonitorRunnable
            r10.<init>()
            r9.fpsMonitorRunnable = r10
            r7 = 0
            boolean r8 = r0.getIsRunningOnFabric()
            r2 = 0
            r4 = 0
            r6 = 0
            r1 = r9
            r1.setCurrentFPS(r2, r4, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.devsupport.FpsView.<init>(com.facebook.react.bridge.ReactContext):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.frameCallback.reset();
        FpsDebugFrameCallback.start$default(this.frameCallback, 0.0d, 1, null);
        this.fpsMonitorRunnable.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.frameCallback.stop();
        this.fpsMonitorRunnable.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentFPS(double currentFPS, double currentJSFPS, int droppedUIFrames, int total4PlusFrameStutters, boolean runningOnFabric) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "UI: %.1f fps\n%d dropped so far\n%d stutters (4+) so far", Arrays.copyOf(new Object[]{Double.valueOf(currentFPS), Integer.valueOf(droppedUIFrames), Integer.valueOf(total4PlusFrameStutters)}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        if (!runningOnFabric) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format(Locale.US, "\nJS: %.1f fps", Arrays.copyOf(new Object[]{Double.valueOf(currentJSFPS)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            format = format + format2;
        }
        this.textView.setText(format);
        FLog.d(ReactConstants.TAG, format);
    }

    /* compiled from: FpsView.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016J\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/facebook/react/devsupport/FpsView$FPSMonitorRunnable;", "Ljava/lang/Runnable;", "<init>", "(Lcom/facebook/react/devsupport/FpsView;)V", "shouldStop", "", "totalFramesDropped", "", "total4PlusFrameStutters", "run", "", "start", "stop", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    private final class FPSMonitorRunnable implements Runnable {
        private boolean shouldStop;
        private int total4PlusFrameStutters;
        private int totalFramesDropped;

        public FPSMonitorRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.shouldStop) {
                return;
            }
            this.totalFramesDropped += FpsView.this.frameCallback.getExpectedNumFrames() - FpsView.this.frameCallback.getNumFrames();
            this.total4PlusFrameStutters += FpsView.this.frameCallback.getFourPlusFrameStutters();
            FpsView fpsView = FpsView.this;
            fpsView.setCurrentFPS(fpsView.frameCallback.getFps(), FpsView.this.frameCallback.getJsFPS(), this.totalFramesDropped, this.total4PlusFrameStutters, FpsView.this.frameCallback.getIsRunningOnFabric());
            FpsView.this.frameCallback.reset();
            FpsView.this.postDelayed(this, 500L);
        }

        public final void start() {
            this.shouldStop = false;
            FpsView.this.post(this);
        }

        public final void stop() {
            this.shouldStop = true;
        }
    }
}
