package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGFeColorMatrixManagerInterface;

/* loaded from: classes2.dex */
public class RNSVGFeColorMatrixManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGFeColorMatrixManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGFeColorMatrixManagerDelegate(BaseViewManager baseViewManager) {
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
            case -823812830:
                if (str.equals("values")) {
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
            case 104364:
                if (str.equals("in1")) {
                    c = 5;
                    break;
                }
                break;
            case 3575610:
                if (str.equals("type")) {
                    c = 6;
                    break;
                }
                break;
            case 113126854:
                if (str.equals("width")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGFeColorMatrixManagerInterface) this.mViewManager).setHeight(t, new DynamicFromObject(obj));
                return;
            case 1:
                ((RNSVGFeColorMatrixManagerInterface) this.mViewManager).setResult(t, obj != null ? (String) obj : null);
                return;
            case 2:
                ((RNSVGFeColorMatrixManagerInterface) this.mViewManager).setValues(t, (ReadableArray) obj);
                return;
            case 3:
                ((RNSVGFeColorMatrixManagerInterface) this.mViewManager).setX(t, new DynamicFromObject(obj));
                return;
            case 4:
                ((RNSVGFeColorMatrixManagerInterface) this.mViewManager).setY(t, new DynamicFromObject(obj));
                return;
            case 5:
                ((RNSVGFeColorMatrixManagerInterface) this.mViewManager).setIn1(t, obj != null ? (String) obj : null);
                return;
            case 6:
                ((RNSVGFeColorMatrixManagerInterface) this.mViewManager).setType(t, (String) obj);
                return;
            case 7:
                ((RNSVGFeColorMatrixManagerInterface) this.mViewManager).setWidth(t, new DynamicFromObject(obj));
                return;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                return;
        }
    }
}
