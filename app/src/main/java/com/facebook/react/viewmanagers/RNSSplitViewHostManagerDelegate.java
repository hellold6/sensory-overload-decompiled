package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSSplitViewHostManagerInterface;

/* loaded from: classes2.dex */
public class RNSSplitViewHostManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSSplitViewHostManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSSplitViewHostManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1485785973:
                if (str.equals("preferredSplitBehavior")) {
                    c = 0;
                    break;
                }
                break;
            case -1439500848:
                if (str.equals("orientation")) {
                    c = 1;
                    break;
                }
                break;
            case -868016417:
                if (str.equals("primaryEdge")) {
                    c = 2;
                    break;
                }
                break;
            case -723350406:
                if (str.equals("showInspector")) {
                    c = 3;
                    break;
                }
                break;
            case 84368445:
                if (str.equals("showSecondaryToggleButton")) {
                    c = 4;
                    break;
                }
                break;
            case 429590084:
                if (str.equals("preferredDisplayMode")) {
                    c = 5;
                    break;
                }
                break;
            case 1093280619:
                if (str.equals("presentsWithGesture")) {
                    c = 6;
                    break;
                }
                break;
            case 1446428553:
                if (str.equals("displayModeButtonVisibility")) {
                    c = 7;
                    break;
                }
                break;
            case 1758738573:
                if (str.equals("columnMetrics")) {
                    c = '\b';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSSplitViewHostManagerInterface) this.mViewManager).setPreferredSplitBehavior(t, (String) obj);
                return;
            case 1:
                ((RNSSplitViewHostManagerInterface) this.mViewManager).setOrientation(t, (String) obj);
                return;
            case 2:
                ((RNSSplitViewHostManagerInterface) this.mViewManager).setPrimaryEdge(t, (String) obj);
                return;
            case 3:
                ((RNSSplitViewHostManagerInterface) this.mViewManager).setShowInspector(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 4:
                ((RNSSplitViewHostManagerInterface) this.mViewManager).setShowSecondaryToggleButton(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 5:
                ((RNSSplitViewHostManagerInterface) this.mViewManager).setPreferredDisplayMode(t, (String) obj);
                return;
            case 6:
                ((RNSSplitViewHostManagerInterface) this.mViewManager).setPresentsWithGesture(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case 7:
                ((RNSSplitViewHostManagerInterface) this.mViewManager).setDisplayModeButtonVisibility(t, (String) obj);
                return;
            case '\b':
                ((RNSSplitViewHostManagerInterface) this.mViewManager).setColumnMetrics(t, (ReadableMap) obj);
                return;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                return;
        }
    }
}
