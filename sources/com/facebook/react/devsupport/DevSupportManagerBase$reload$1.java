package com.facebook.react.devsupport;

import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.DevServerHelper;
import com.facebook.react.packagerconnection.RequestHandler;
import java.util.Map;
import kotlin.Metadata;

/* compiled from: DevSupportManagerBase.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016¨\u0006\u000b"}, d2 = {"com/facebook/react/devsupport/DevSupportManagerBase$reload$1", "Lcom/facebook/react/devsupport/DevServerHelper$PackagerCommandListener;", "onPackagerConnected", "", "onPackagerDisconnected", "onPackagerReloadCommand", "onPackagerDevMenuCommand", "customCommandHandlers", "", "", "Lcom/facebook/react/packagerconnection/RequestHandler;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DevSupportManagerBase$reload$1 implements DevServerHelper.PackagerCommandListener {
    final /* synthetic */ DevSupportManagerBase this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DevSupportManagerBase$reload$1(DevSupportManagerBase devSupportManagerBase) {
        this.this$0 = devSupportManagerBase;
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerConnected() {
        this.this$0.isPackagerConnected = true;
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerDisconnected() {
        this.this$0.isPackagerConnected = false;
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerReloadCommand() {
        if (!InspectorFlags.getFuseboxEnabled()) {
            this.this$0.getDevServerHelper().disableDebugger();
        }
        final DevSupportManagerBase devSupportManagerBase = this.this$0;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$reload$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DevSupportManagerBase.this.handleReloadJS();
            }
        });
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerDevMenuCommand() {
        final DevSupportManagerBase devSupportManagerBase = this.this$0;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$reload$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DevSupportManagerBase.this.showDevOptionsDialog();
            }
        });
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public Map<String, RequestHandler> customCommandHandlers() {
        Map<String, RequestHandler> map;
        map = this.this$0.customPackagerCommandHandlers;
        return map;
    }
}
