package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGFilterManagerInterface;

/* loaded from: classes2.dex */
public class RNSVGFilterManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGFilterManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGFilterManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1535814968:
                if (str.equals("primitiveUnits")) {
                    c = 0;
                    break;
                }
                break;
            case -1221029593:
                if (str.equals("height")) {
                    c = 1;
                    break;
                }
                break;
            case -899348777:
                if (str.equals("filterUnits")) {
                    c = 2;
                    break;
                }
                break;
            case 120:
                if (str.equals("x")) {
                    c = 3;
                    break;
                }
                break;
            case 121:
                if (str.equals("y")) {
                    c = 4;
                    break;
                }
                break;
            case 3373707:
                if (str.equals("name")) {
                    c = 5;
                    break;
                }
                break;
            case 113126854:
                if (str.equals("width")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGFilterManagerInterface) this.mViewManager).setPrimitiveUnits(t, (String) obj);
                return;
            case 1:
                ((RNSVGFilterManagerInterface) this.mViewManager).setHeight(t, new DynamicFromObject(obj));
                return;
            case 2:
                ((RNSVGFilterManagerInterface) this.mViewManager).setFilterUnits(t, (String) obj);
                return;
            case 3:
                ((RNSVGFilterManagerInterface) this.mViewManager).setX(t, new DynamicFromObject(obj));
                return;
            case 4:
                ((RNSVGFilterManagerInterface) this.mViewManager).setY(t, new DynamicFromObject(obj));
                return;
            case 5:
                ((RNSVGFilterManagerInterface) this.mViewManager).setName(t, obj == null ? null : (String) obj);
                return;
            case 6:
                ((RNSVGFilterManagerInterface) this.mViewManager).setWidth(t, new DynamicFromObject(obj));
                return;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                return;
        }
    }
}
