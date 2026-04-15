package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class RNSVGSymbolManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGSymbolManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGSymbolManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1567958285:
                if (str.equals("vbHeight")) {
                    c = 0;
                    break;
                }
                break;
            case -1274492040:
                if (str.equals(ViewProps.FILTER)) {
                    c = 1;
                    break;
                }
                break;
            case -1267206133:
                if (str.equals(ViewProps.OPACITY)) {
                    c = 2;
                    break;
                }
                break;
            case -1081239615:
                if (str.equals("matrix")) {
                    c = 3;
                    break;
                }
                break;
            case -993894751:
                if (str.equals("propList")) {
                    c = 4;
                    break;
                }
                break;
            case -933864895:
                if (str.equals("markerEnd")) {
                    c = 5;
                    break;
                }
                break;
            case -933857362:
                if (str.equals("markerMid")) {
                    c = 6;
                    break;
                }
                break;
            case -891980232:
                if (str.equals("stroke")) {
                    c = 7;
                    break;
                }
                break;
            case -734428249:
                if (str.equals("fontWeight")) {
                    c = '\b';
                    break;
                }
                break;
            case -729118945:
                if (str.equals("fillRule")) {
                    c = '\t';
                    break;
                }
                break;
            case -416535885:
                if (str.equals("strokeOpacity")) {
                    c = '\n';
                    break;
                }
                break;
            case -293492298:
                if (str.equals(ViewProps.POINTER_EVENTS)) {
                    c = 11;
                    break;
                }
                break;
            case -53677816:
                if (str.equals("fillOpacity")) {
                    c = '\f';
                    break;
                }
                break;
            case -44578051:
                if (str.equals("strokeDashoffset")) {
                    c = '\r';
                    break;
                }
                break;
            case 3143043:
                if (str.equals("fill")) {
                    c = 14;
                    break;
                }
                break;
            case 3148879:
                if (str.equals("font")) {
                    c = 15;
                    break;
                }
                break;
            case 3344108:
                if (str.equals("mask")) {
                    c = 16;
                    break;
                }
                break;
            case 3351622:
                if (str.equals("minX")) {
                    c = 17;
                    break;
                }
                break;
            case 3351623:
                if (str.equals("minY")) {
                    c = 18;
                    break;
                }
                break;
            case 3373707:
                if (str.equals("name")) {
                    c = 19;
                    break;
                }
                break;
            case 78845486:
                if (str.equals("strokeMiterlimit")) {
                    c = 20;
                    break;
                }
                break;
            case 92903173:
                if (str.equals("align")) {
                    c = 21;
                    break;
                }
                break;
            case 94842723:
                if (str.equals("color")) {
                    c = 22;
                    break;
                }
                break;
            case 104482996:
                if (str.equals("vectorEffect")) {
                    c = 23;
                    break;
                }
                break;
            case 217109576:
                if (str.equals("markerStart")) {
                    c = 24;
                    break;
                }
                break;
            case 240482938:
                if (str.equals("vbWidth")) {
                    c = 25;
                    break;
                }
                break;
            case 365601008:
                if (str.equals("fontSize")) {
                    c = 26;
                    break;
                }
                break;
            case 401643183:
                if (str.equals("strokeDasharray")) {
                    c = 27;
                    break;
                }
                break;
            case 917656469:
                if (str.equals("clipPath")) {
                    c = 28;
                    break;
                }
                break;
            case 917735020:
                if (str.equals("clipRule")) {
                    c = 29;
                    break;
                }
                break;
            case 1027575302:
                if (str.equals("strokeLinecap")) {
                    c = 30;
                    break;
                }
                break;
            case 1671764162:
                if (str.equals("display")) {
                    c = 31;
                    break;
                }
                break;
            case 1790285174:
                if (str.equals("strokeLinejoin")) {
                    c = ' ';
                    break;
                }
                break;
            case 1847674614:
                if (str.equals("responsible")) {
                    c = '!';
                    break;
                }
                break;
            case 1908075304:
                if (str.equals("meetOrSlice")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 1924065902:
                if (str.equals("strokeWidth")) {
                    c = '#';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setVbHeight(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 1:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setFilter(t, obj != null ? (String) obj : null);
                return;
            case 2:
                this.mViewManager.setOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 3:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setMatrix(t, (ReadableArray) obj);
                return;
            case 4:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setPropList(t, (ReadableArray) obj);
                return;
            case 5:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setMarkerEnd(t, obj != null ? (String) obj : null);
                return;
            case 6:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setMarkerMid(t, obj != null ? (String) obj : null);
                return;
            case 7:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setStroke(t, new DynamicFromObject(obj));
                return;
            case '\b':
                ((RNSVGSymbolManagerInterface) this.mViewManager).setFontWeight(t, new DynamicFromObject(obj));
                return;
            case '\t':
                ((RNSVGSymbolManagerInterface) this.mViewManager).setFillRule(t, obj != null ? ((Double) obj).intValue() : 1);
                return;
            case '\n':
                ((RNSVGSymbolManagerInterface) this.mViewManager).setStrokeOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 11:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setPointerEvents(t, obj != null ? (String) obj : null);
                return;
            case '\f':
                ((RNSVGSymbolManagerInterface) this.mViewManager).setFillOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case '\r':
                ((RNSVGSymbolManagerInterface) this.mViewManager).setStrokeDashoffset(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 14:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setFill(t, new DynamicFromObject(obj));
                return;
            case 15:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setFont(t, new DynamicFromObject(obj));
                return;
            case 16:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setMask(t, obj != null ? (String) obj : null);
                return;
            case 17:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setMinX(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 18:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setMinY(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 19:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setName(t, obj != null ? (String) obj : null);
                return;
            case 20:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setStrokeMiterlimit(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 21:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setAlign(t, obj != null ? (String) obj : null);
                return;
            case 22:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 23:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setVectorEffect(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 24:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setMarkerStart(t, obj != null ? (String) obj : null);
                return;
            case 25:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setVbWidth(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 26:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setFontSize(t, new DynamicFromObject(obj));
                return;
            case 27:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setStrokeDasharray(t, new DynamicFromObject(obj));
                return;
            case 28:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setClipPath(t, obj != null ? (String) obj : null);
                return;
            case 29:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setClipRule(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 30:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setStrokeLinecap(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 31:
                ((RNSVGSymbolManagerInterface) this.mViewManager).setDisplay(t, obj != null ? (String) obj : null);
                return;
            case ' ':
                ((RNSVGSymbolManagerInterface) this.mViewManager).setStrokeLinejoin(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '!':
                ((RNSVGSymbolManagerInterface) this.mViewManager).setResponsible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '\"':
                ((RNSVGSymbolManagerInterface) this.mViewManager).setMeetOrSlice(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '#':
                ((RNSVGSymbolManagerInterface) this.mViewManager).setStrokeWidth(t, new DynamicFromObject(obj));
                return;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                return;
        }
    }
}
