package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.dialog.AlertFragment;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSBottomTabsScreenManagerInterface;

/* loaded from: classes2.dex */
public class RNSBottomTabsScreenManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSBottomTabsScreenManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSBottomTabsScreenManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1770963447:
                if (str.equals("specialEffects")) {
                    c = 0;
                    break;
                }
                break;
            case -1559978286:
                if (str.equals("iconResourceName")) {
                    c = 1;
                    break;
                }
                break;
            case -1439500848:
                if (str.equals("orientation")) {
                    c = 2;
                    break;
                }
                break;
            case -1270820115:
                if (str.equals("isFocused")) {
                    c = 3;
                    break;
                }
                break;
            case -1186468415:
                if (str.equals("overrideScrollViewContentInsetAdjustmentBehavior")) {
                    c = 4;
                    break;
                }
                break;
            case -881409398:
                if (str.equals("tabKey")) {
                    c = 5;
                    break;
                }
                break;
            case -776576227:
                if (str.equals("iconImageSource")) {
                    c = 6;
                    break;
                }
                break;
            case -737911981:
                if (str.equals("iconType")) {
                    c = 7;
                    break;
                }
                break;
            case -569869622:
                if (str.equals("selectedIconSfSymbolName")) {
                    c = '\b';
                    break;
                }
                break;
            case -270334418:
                if (str.equals("scrollEdgeAppearance")) {
                    c = '\t';
                    break;
                }
                break;
            case 28389121:
                if (str.equals("standardAppearance")) {
                    c = '\n';
                    break;
                }
                break;
            case 110371416:
                if (str.equals(AlertFragment.ARG_TITLE)) {
                    c = 11;
                    break;
                }
                break;
            case 143186447:
                if (str.equals("iconSfSymbolName")) {
                    c = '\f';
                    break;
                }
                break;
            case 642560482:
                if (str.equals("systemItem")) {
                    c = '\r';
                    break;
                }
                break;
            case 1001537282:
                if (str.equals("selectedIconImageSource")) {
                    c = 14;
                    break;
                }
                break;
            case 1072026510:
                if (str.equals("badgeValue")) {
                    c = 15;
                    break;
                }
                break;
            case 1293147047:
                if (str.equals("iconResource")) {
                    c = 16;
                    break;
                }
                break;
            case 1519110851:
                if (str.equals("tabBarItemBadgeBackgroundColor")) {
                    c = 17;
                    break;
                }
                break;
            case 1595935908:
                if (str.equals("tabBarItemBadgeTextColor")) {
                    c = 18;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setSpecialEffects(t, (ReadableMap) obj);
                return;
            case 1:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setIconResourceName(t, obj != null ? (String) obj : null);
                return;
            case 2:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setOrientation(t, (String) obj);
                return;
            case 3:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setIsFocused(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 4:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setOverrideScrollViewContentInsetAdjustmentBehavior(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case 5:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setTabKey(t, obj != null ? (String) obj : null);
                return;
            case 6:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setIconImageSource(t, (ReadableMap) obj);
                return;
            case 7:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setIconType(t, (String) obj);
                return;
            case '\b':
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setSelectedIconSfSymbolName(t, obj != null ? (String) obj : null);
                return;
            case '\t':
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setScrollEdgeAppearance(t, new DynamicFromObject(obj));
                return;
            case '\n':
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setStandardAppearance(t, new DynamicFromObject(obj));
                return;
            case 11:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setTitle(t, obj != null ? (String) obj : null);
                return;
            case '\f':
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setIconSfSymbolName(t, obj != null ? (String) obj : null);
                return;
            case '\r':
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setSystemItem(t, (String) obj);
                return;
            case 14:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setSelectedIconImageSource(t, (ReadableMap) obj);
                return;
            case 15:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setBadgeValue(t, obj != null ? (String) obj : null);
                return;
            case 16:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setIconResource(t, (ReadableMap) obj);
                return;
            case 17:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setTabBarItemBadgeBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 18:
                ((RNSBottomTabsScreenManagerInterface) this.mViewManager).setTabBarItemBadgeTextColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                return;
        }
    }
}
