package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSSearchBarManagerInterface;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;

/* loaded from: classes2.dex */
public class RNSSearchBarManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSSearchBarManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSSearchBarManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1619312835:
                if (str.equals("hideNavigationBar")) {
                    c = 0;
                    break;
                }
                break;
            case -1465798051:
                if (str.equals("headerIconColor")) {
                    c = 1;
                    break;
                }
                break;
            case -1339545093:
                if (str.equals("autoCapitalize")) {
                    c = 2;
                    break;
                }
                break;
            case -1063571914:
                if (str.equals("textColor")) {
                    c = 3;
                    break;
                }
                break;
            case -336520619:
                if (str.equals("barTintColor")) {
                    c = 4;
                    break;
                }
                break;
            case -256845969:
                if (str.equals("hintTextColor")) {
                    c = 5;
                    break;
                }
                break;
            case -186579527:
                if (str.equals("hideWhenScrolling")) {
                    c = 6;
                    break;
                }
                break;
            case -146361959:
                if (str.equals("cancelButtonText")) {
                    c = 7;
                    break;
                }
                break;
            case -109380883:
                if (str.equals("disableBackButtonOverride")) {
                    c = '\b';
                    break;
                }
                break;
            case -39414888:
                if (str.equals("shouldShowHintSearchIcon")) {
                    c = '\t';
                    break;
                }
                break;
            case 598246771:
                if (str.equals(ReactTextInputShadowNode.PROP_PLACEHOLDER)) {
                    c = '\n';
                    break;
                }
                break;
            case 1327599912:
                if (str.equals("tintColor")) {
                    c = 11;
                    break;
                }
                break;
            case 1399891586:
                if (str.equals("allowToolbarIntegration")) {
                    c = '\f';
                    break;
                }
                break;
            case 1584806451:
                if (str.equals("obscureBackground")) {
                    c = '\r';
                    break;
                }
                break;
            case 1706976804:
                if (str.equals("inputType")) {
                    c = 14;
                    break;
                }
                break;
            case 1792938725:
                if (str.equals("placement")) {
                    c = 15;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSSearchBarManagerInterface) this.mViewManager).setHideNavigationBar(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 1:
                ((RNSSearchBarManagerInterface) this.mViewManager).setHeaderIconColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 2:
                ((RNSSearchBarManagerInterface) this.mViewManager).setAutoCapitalize(t, (String) obj);
                return;
            case 3:
                ((RNSSearchBarManagerInterface) this.mViewManager).setTextColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 4:
                ((RNSSearchBarManagerInterface) this.mViewManager).setBarTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 5:
                ((RNSSearchBarManagerInterface) this.mViewManager).setHintTextColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 6:
                ((RNSSearchBarManagerInterface) this.mViewManager).setHideWhenScrolling(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case 7:
                ((RNSSearchBarManagerInterface) this.mViewManager).setCancelButtonText(t, obj != null ? (String) obj : null);
                return;
            case '\b':
                ((RNSSearchBarManagerInterface) this.mViewManager).setDisableBackButtonOverride(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '\t':
                ((RNSSearchBarManagerInterface) this.mViewManager).setShouldShowHintSearchIcon(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case '\n':
                ((RNSSearchBarManagerInterface) this.mViewManager).setPlaceholder(t, obj != null ? (String) obj : null);
                return;
            case 11:
                ((RNSSearchBarManagerInterface) this.mViewManager).setTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case '\f':
                ((RNSSearchBarManagerInterface) this.mViewManager).setAllowToolbarIntegration(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case '\r':
                ((RNSSearchBarManagerInterface) this.mViewManager).setObscureBackground(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 14:
                ((RNSSearchBarManagerInterface) this.mViewManager).setInputType(t, obj != null ? (String) obj : null);
                return;
            case 15:
                ((RNSSearchBarManagerInterface) this.mViewManager).setPlacement(t, (String) obj);
                return;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                return;
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* renamed from: receiveCommand */
    public void kotlinCompat$receiveCommand(T t, String str, ReadableArray readableArray) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1833485118:
                if (str.equals("cancelSearch")) {
                    c = 0;
                    break;
                }
                break;
            case -1270906598:
                if (str.equals("clearText")) {
                    c = 1;
                    break;
                }
                break;
            case -664358976:
                if (str.equals("toggleCancelButton")) {
                    c = 2;
                    break;
                }
                break;
            case 3027047:
                if (str.equals("blur")) {
                    c = 3;
                    break;
                }
                break;
            case 97604824:
                if (str.equals("focus")) {
                    c = 4;
                    break;
                }
                break;
            case 1984984239:
                if (str.equals("setText")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSSearchBarManagerInterface) this.mViewManager).cancelSearch(t);
                return;
            case 1:
                ((RNSSearchBarManagerInterface) this.mViewManager).clearText(t);
                return;
            case 2:
                ((RNSSearchBarManagerInterface) this.mViewManager).toggleCancelButton(t, readableArray.getBoolean(0));
                return;
            case 3:
                ((RNSSearchBarManagerInterface) this.mViewManager).blur(t);
                return;
            case 4:
                ((RNSSearchBarManagerInterface) this.mViewManager).focus(t);
                return;
            case 5:
                ((RNSSearchBarManagerInterface) this.mViewManager).setText(t, readableArray.getString(0));
                return;
            default:
                return;
        }
    }
}
