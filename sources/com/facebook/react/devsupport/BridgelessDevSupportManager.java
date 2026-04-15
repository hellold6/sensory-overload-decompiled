package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.packagerconnection.RequestHandler;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BridgelessDevSupportManager.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aB#\b\u0016\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0019\u0010\u001cJ\b\u0010 \u001a\u00020!H\u0016R\u0014\u0010\u001d\u001a\u00020\u00078TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Lcom/facebook/react/devsupport/BridgelessDevSupportManager;", "Lcom/facebook/react/devsupport/DevSupportManagerBase;", "applicationContext", "Landroid/content/Context;", "reactInstanceManagerHelper", "Lcom/facebook/react/devsupport/ReactInstanceDevHelper;", "packagerPathForJSBundleName", "", "enableOnCreate", "", "redBoxHandler", "Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "devBundleDownloadListener", "Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;", "minNumShakes", "", "customPackagerCommandHandlers", "", "Lcom/facebook/react/packagerconnection/RequestHandler;", "surfaceDelegateFactory", "Lcom/facebook/react/common/SurfaceDelegateFactory;", "devLoadingViewManager", "Lcom/facebook/react/devsupport/interfaces/DevLoadingViewManager;", "pausedInDebuggerOverlayManager", "Lcom/facebook/react/devsupport/interfaces/PausedInDebuggerOverlayManager;", "<init>", "(Landroid/content/Context;Lcom/facebook/react/devsupport/ReactInstanceDevHelper;Ljava/lang/String;ZLcom/facebook/react/devsupport/interfaces/RedBoxHandler;Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;ILjava/util/Map;Lcom/facebook/react/common/SurfaceDelegateFactory;Lcom/facebook/react/devsupport/interfaces/DevLoadingViewManager;Lcom/facebook/react/devsupport/interfaces/PausedInDebuggerOverlayManager;)V", "context", "(Landroid/content/Context;Lcom/facebook/react/devsupport/ReactInstanceDevHelper;Ljava/lang/String;)V", "uniqueTag", "getUniqueTag", "()Ljava/lang/String;", "handleReloadJS", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BridgelessDevSupportManager extends DevSupportManagerBase {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BridgelessDevSupportManager(Context applicationContext, ReactInstanceDevHelper reactInstanceManagerHelper, String str, boolean z, RedBoxHandler redBoxHandler, DevBundleDownloadListener devBundleDownloadListener, int i, Map<String, ? extends RequestHandler> map, SurfaceDelegateFactory surfaceDelegateFactory, DevLoadingViewManager devLoadingViewManager, PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager) {
        super(applicationContext, reactInstanceManagerHelper, str, z, redBoxHandler, devBundleDownloadListener, i, map, surfaceDelegateFactory, devLoadingViewManager, pausedInDebuggerOverlayManager);
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(reactInstanceManagerHelper, "reactInstanceManagerHelper");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BridgelessDevSupportManager(android.content.Context r14, com.facebook.react.devsupport.ReactInstanceDevHelper r15, java.lang.String r16) {
        /*
            r13 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "reactInstanceManagerHelper"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            android.content.Context r2 = r14.getApplicationContext()
            java.lang.String r14 = "getApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r14)
            r11 = 0
            r12 = 0
            r5 = 1
            r6 = 0
            r7 = 0
            r8 = 2
            r9 = 0
            r10 = 0
            r1 = r13
            r3 = r15
            r4 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.devsupport.BridgelessDevSupportManager.<init>(android.content.Context, com.facebook.react.devsupport.ReactInstanceDevHelper, java.lang.String):void");
    }

    @Override // com.facebook.react.devsupport.DevSupportManagerBase
    protected String getUniqueTag() {
        return "Bridgeless";
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void handleReloadJS() {
        UiThreadUtil.assertOnUiThread();
        hideRedboxDialog();
        getReactInstanceDevHelper().reload("BridgelessDevSupportManager.handleReloadJS()");
    }
}
