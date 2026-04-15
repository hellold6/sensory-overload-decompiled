package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.ModalHostViewManagerInterface;
import expo.modules.notifications.service.NotificationsService;

/* loaded from: classes2.dex */
public class ModalHostViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & ModalHostViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public ModalHostViewManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1851617609:
                if (str.equals("presentationStyle")) {
                    c = 0;
                    break;
                }
                break;
            case -1850124175:
                if (str.equals("supportedOrientations")) {
                    c = 1;
                    break;
                }
                break;
            case -1726194350:
                if (str.equals("transparent")) {
                    c = 2;
                    break;
                }
                break;
            case -1618432855:
                if (str.equals(NotificationsService.IDENTIFIER_KEY)) {
                    c = 3;
                    break;
                }
                break;
            case -1156137512:
                if (str.equals("statusBarTranslucent")) {
                    c = 4;
                    break;
                }
                break;
            case -1112923708:
                if (str.equals("allowSwipeDismissal")) {
                    c = 5;
                    break;
                }
                break;
            case -795203165:
                if (str.equals("animated")) {
                    c = 6;
                    break;
                }
                break;
            case 466743410:
                if (str.equals(ViewProps.VISIBLE)) {
                    c = 7;
                    break;
                }
                break;
            case 1116050554:
                if (str.equals("navigationBarTranslucent")) {
                    c = '\b';
                    break;
                }
                break;
            case 1195991583:
                if (str.equals("hardwareAccelerated")) {
                    c = '\t';
                    break;
                }
                break;
            case 2031205598:
                if (str.equals("animationType")) {
                    c = '\n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((ModalHostViewManagerInterface) this.mViewManager).setPresentationStyle(t, (String) obj);
                return;
            case 1:
                ((ModalHostViewManagerInterface) this.mViewManager).setSupportedOrientations(t, (ReadableArray) obj);
                return;
            case 2:
                ((ModalHostViewManagerInterface) this.mViewManager).setTransparent(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 3:
                ((ModalHostViewManagerInterface) this.mViewManager).setIdentifier(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 4:
                ((ModalHostViewManagerInterface) this.mViewManager).setStatusBarTranslucent(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 5:
                ((ModalHostViewManagerInterface) this.mViewManager).setAllowSwipeDismissal(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 6:
                ((ModalHostViewManagerInterface) this.mViewManager).setAnimated(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 7:
                ((ModalHostViewManagerInterface) this.mViewManager).setVisible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '\b':
                ((ModalHostViewManagerInterface) this.mViewManager).setNavigationBarTranslucent(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '\t':
                ((ModalHostViewManagerInterface) this.mViewManager).setHardwareAccelerated(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '\n':
                ((ModalHostViewManagerInterface) this.mViewManager).setAnimationType(t, (String) obj);
                return;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                return;
        }
    }
}
