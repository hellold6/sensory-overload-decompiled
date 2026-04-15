package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGFeCompositeManagerInterface;

/* loaded from: classes2.dex */
public class RNSVGFeCompositeManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGFeCompositeManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGFeCompositeManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1221029593:
                if (str.equals("height")) {
                    c = 0;
                    break;
                }
                break;
            case -934426595:
                if (str.equals("result")) {
                    c = 1;
                    break;
                }
                break;
            case 120:
                if (str.equals("x")) {
                    c = 2;
                    break;
                }
                break;
            case 121:
                if (str.equals("y")) {
                    c = 3;
                    break;
                }
                break;
            case 3366:
                if (str.equals("k1")) {
                    c = 4;
                    break;
                }
                break;
            case 3367:
                if (str.equals("k2")) {
                    c = 5;
                    break;
                }
                break;
            case 3368:
                if (str.equals("k3")) {
                    c = 6;
                    break;
                }
                break;
            case 3369:
                if (str.equals("k4")) {
                    c = 7;
                    break;
                }
                break;
            case 104364:
                if (str.equals("in1")) {
                    c = '\b';
                    break;
                }
                break;
            case 104365:
                if (str.equals("in2")) {
                    c = '\t';
                    break;
                }
                break;
            case 113126854:
                if (str.equals("width")) {
                    c = '\n';
                    break;
                }
                break;
            case 1662708749:
                if (str.equals("operator1")) {
                    c = 11;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setHeight(t, new DynamicFromObject(obj));
                return;
            case 1:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setResult(t, obj != null ? (String) obj : null);
                return;
            case 2:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setX(t, new DynamicFromObject(obj));
                return;
            case 3:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setY(t, new DynamicFromObject(obj));
                return;
            case 4:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setK1(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 5:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setK2(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 6:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setK3(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 7:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setK4(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case '\b':
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setIn1(t, obj != null ? (String) obj : null);
                return;
            case '\t':
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setIn2(t, obj != null ? (String) obj : null);
                return;
            case '\n':
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setWidth(t, new DynamicFromObject(obj));
                return;
            case 11:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setOperator1(t, (String) obj);
                return;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                return;
        }
    }
}
