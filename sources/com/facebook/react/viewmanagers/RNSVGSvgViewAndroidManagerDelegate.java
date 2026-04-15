package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* loaded from: classes2.dex */
public class RNSVGSvgViewAndroidManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGSvgViewAndroidManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGSvgViewAndroidManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2064426617:
                if (str.equals("bbHeight")) {
                    c = 0;
                    break;
                }
                break;
            case -1989576717:
                if (str.equals(ViewProps.BORDER_RIGHT_COLOR)) {
                    c = 1;
                    break;
                }
                break;
            case -1697814026:
                if (str.equals("backfaceVisibility")) {
                    c = 2;
                    break;
                }
                break;
            case -1567958285:
                if (str.equals("vbHeight")) {
                    c = 3;
                    break;
                }
                break;
            case -1470826662:
                if (str.equals(ViewProps.BORDER_TOP_COLOR)) {
                    c = 4;
                    break;
                }
                break;
            case -1308858324:
                if (str.equals(ViewProps.BORDER_BOTTOM_COLOR)) {
                    c = 5;
                    break;
                }
                break;
            case -1228066334:
                if (str.equals(ViewProps.BORDER_TOP_LEFT_RADIUS)) {
                    c = 6;
                    break;
                }
                break;
            case -1141400650:
                if (str.equals("accessible")) {
                    c = 7;
                    break;
                }
                break;
            case -1122140597:
                if (str.equals(ViewProps.BORDER_TOP_START_RADIUS)) {
                    c = '\b';
                    break;
                }
                break;
            case -867333731:
                if (str.equals(ViewProps.BORDER_BOTTOM_START_RADIUS)) {
                    c = '\t';
                    break;
                }
                break;
            case -679581037:
                if (str.equals("hasTVPreferredFocus")) {
                    c = '\n';
                    break;
                }
                break;
            case -631506969:
                if (str.equals("nextFocusDown")) {
                    c = 11;
                    break;
                }
                break;
            case -631278772:
                if (str.equals("nextFocusLeft")) {
                    c = '\f';
                    break;
                }
                break;
            case -483490364:
                if (str.equals(ViewProps.BORDER_TOP_END_RADIUS)) {
                    c = '\r';
                    break;
                }
                break;
            case -329721498:
                if (str.equals("bbWidth")) {
                    c = 14;
                    break;
                }
                break;
            case -293492298:
                if (str.equals(ViewProps.POINTER_EVENTS)) {
                    c = 15;
                    break;
                }
                break;
            case -252105751:
                if (str.equals(ReactClippingViewGroupHelper.PROP_REMOVE_CLIPPED_SUBVIEWS)) {
                    c = 16;
                    break;
                }
                break;
            case -242276144:
                if (str.equals(ViewProps.BORDER_LEFT_COLOR)) {
                    c = 17;
                    break;
                }
                break;
            case -223134121:
                if (str.equals(ViewProps.BORDER_START_END_RADIUS)) {
                    c = 18;
                    break;
                }
                break;
            case -148030058:
                if (str.equals(ViewProps.BORDER_BOTTOM_END_RADIUS)) {
                    c = 19;
                    break;
                }
                break;
            case -109689771:
                if (str.equals("nativeForegroundAndroid")) {
                    c = 20;
                    break;
                }
                break;
            case -27894242:
                if (str.equals(ViewProps.BORDER_START_START_RADIUS)) {
                    c = 21;
                    break;
                }
                break;
            case 3351622:
                if (str.equals("minX")) {
                    c = 22;
                    break;
                }
                break;
            case 3351623:
                if (str.equals("minY")) {
                    c = 23;
                    break;
                }
                break;
            case 92903173:
                if (str.equals("align")) {
                    c = 24;
                    break;
                }
                break;
            case 94842723:
                if (str.equals("color")) {
                    c = 25;
                    break;
                }
                break;
            case 240482938:
                if (str.equals("vbWidth")) {
                    c = 26;
                    break;
                }
                break;
            case 306963138:
                if (str.equals(ViewProps.BORDER_BLOCK_START_COLOR)) {
                    c = 27;
                    break;
                }
                break;
            case 333432965:
                if (str.equals(ViewProps.BORDER_TOP_RIGHT_RADIUS)) {
                    c = 28;
                    break;
                }
                break;
            case 503397728:
                if (str.equals("nextFocusForward")) {
                    c = 29;
                    break;
                }
                break;
            case 581268560:
                if (str.equals(ViewProps.BORDER_BOTTOM_LEFT_RADIUS)) {
                    c = 30;
                    break;
                }
                break;
            case 588239831:
                if (str.equals(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS)) {
                    c = 31;
                    break;
                }
                break;
            case 660795168:
                if (str.equals("nextFocusUp")) {
                    c = ' ';
                    break;
                }
                break;
            case 684610594:
                if (str.equals(ViewProps.BORDER_BLOCK_COLOR)) {
                    c = '!';
                    break;
                }
                break;
            case 722830999:
                if (str.equals(ViewProps.BORDER_COLOR)) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 737768677:
                if (str.equals("borderStyle")) {
                    c = '#';
                    break;
                }
                break;
            case 762983977:
                if (str.equals(ViewProps.BORDER_BLOCK_END_COLOR)) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case 910681861:
                if (str.equals(ViewProps.BORDER_END_START_RADIUS)) {
                    c = '%';
                    break;
                }
                break;
            case 926871597:
                if (str.equals("hitSlop")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 1220735892:
                if (str.equals(ViewProps.BORDER_END_COLOR)) {
                    c = '\'';
                    break;
                }
                break;
            case 1349188574:
                if (str.equals(ViewProps.BORDER_RADIUS)) {
                    c = '(';
                    break;
                }
                break;
            case 1629011506:
                if (str.equals("focusable")) {
                    c = ')';
                    break;
                }
                break;
            case 1667773924:
                if (str.equals(ViewProps.NEEDS_OFFSCREEN_ALPHA_COMPOSITING)) {
                    c = '*';
                    break;
                }
                break;
            case 1735382270:
                if (str.equals(ViewProps.BORDER_END_END_RADIUS)) {
                    c = '+';
                    break;
                }
                break;
            case 1747724810:
                if (str.equals("nativeBackgroundAndroid")) {
                    c = ',';
                    break;
                }
                break;
            case 1908075304:
                if (str.equals("meetOrSlice")) {
                    c = '-';
                    break;
                }
                break;
            case 1910855543:
                if (str.equals("nextFocusRight")) {
                    c = FilenameUtils.EXTENSION_SEPARATOR;
                    break;
                }
                break;
            case 2119889261:
                if (str.equals(ViewProps.BORDER_START_COLOR)) {
                    c = IOUtils.DIR_SEPARATOR_UNIX;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBbHeight(t, new DynamicFromObject(obj));
                return;
            case 1:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderRightColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 2:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBackfaceVisibility(t, obj != null ? (String) obj : null);
                return;
            case 3:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setVbHeight(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 4:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderTopColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 5:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderBottomColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 6:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderTopLeftRadius(t, new DynamicFromObject(obj));
                return;
            case 7:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setAccessible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '\b':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderTopStartRadius(t, new DynamicFromObject(obj));
                return;
            case '\t':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderBottomStartRadius(t, new DynamicFromObject(obj));
                return;
            case '\n':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setHasTVPreferredFocus(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 11:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setNextFocusDown(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '\f':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setNextFocusLeft(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '\r':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderTopEndRadius(t, new DynamicFromObject(obj));
                return;
            case 14:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBbWidth(t, new DynamicFromObject(obj));
                return;
            case 15:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setPointerEvents(t, obj != null ? (String) obj : null);
                return;
            case 16:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setRemoveClippedSubviews(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 17:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderLeftColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 18:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderStartEndRadius(t, new DynamicFromObject(obj));
                return;
            case 19:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderBottomEndRadius(t, new DynamicFromObject(obj));
                return;
            case 20:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setNativeForegroundAndroid(t, (ReadableMap) obj);
                return;
            case 21:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderStartStartRadius(t, new DynamicFromObject(obj));
                return;
            case 22:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setMinX(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 23:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setMinY(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 24:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setAlign(t, obj != null ? (String) obj : null);
                return;
            case 25:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 26:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setVbWidth(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 27:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderBlockStartColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 28:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderTopRightRadius(t, new DynamicFromObject(obj));
                return;
            case 29:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setNextFocusForward(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 30:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderBottomLeftRadius(t, new DynamicFromObject(obj));
                return;
            case 31:
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderBottomRightRadius(t, new DynamicFromObject(obj));
                return;
            case ' ':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setNextFocusUp(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '!':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderBlockColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case '\"':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case '#':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderStyle(t, obj != null ? (String) obj : null);
                return;
            case '$':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderBlockEndColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case '%':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderEndStartRadius(t, new DynamicFromObject(obj));
                return;
            case '&':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setHitSlop(t, new DynamicFromObject(obj));
                return;
            case '\'':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderEndColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case '(':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderRadius(t, new DynamicFromObject(obj));
                return;
            case ')':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setFocusable(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '*':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setNeedsOffscreenAlphaCompositing(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '+':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderEndEndRadius(t, new DynamicFromObject(obj));
                return;
            case ',':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setNativeBackgroundAndroid(t, (ReadableMap) obj);
                return;
            case '-':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setMeetOrSlice(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '.':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setNextFocusRight(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '/':
                ((RNSVGSvgViewAndroidManagerInterface) this.mViewManager).setBorderStartColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                return;
        }
    }
}
