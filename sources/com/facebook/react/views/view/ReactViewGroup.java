package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityManager;
import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.BlendModeHelper;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactAxOrderHelper;
import com.facebook.react.uimanager.ReactClippingProhibitedView;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactOverflowViewWithInset;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ReactZIndexedViewGroup;
import com.facebook.react.uimanager.ViewGroupDrawingOrderHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.uimanager.style.Overflow;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactViewGroup.kt */
@Metadata(d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\"\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 ¿\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007:\u0004¾\u0001¿\u0001B\u0011\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010;\u001a\u00020<H\u0002J\r\u0010=\u001a\u00020<H\u0010¢\u0006\u0002\b>J\u0018\u0010D\u001a\u00020<2\u0006\u0010E\u001a\u00020\u00112\u0006\u0010F\u001a\u00020\u0011H\u0014J0\u0010G\u001a\u00020<2\u0006\u0010H\u001a\u00020\u00132\u0006\u0010I\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00112\u0006\u0010K\u001a\u00020\u00112\u0006\u0010L\u001a\u00020\u0011H\u0014J\b\u0010M\u001a\u00020<H\u0017J\u0010\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u00020PH\u0017J\u0010\u0010Q\u001a\u00020<2\u0006\u0010R\u001a\u00020\u0011H\u0016J\u0012\u0010S\u001a\u00020<2\b\u0010T\u001a\u0004\u0018\u00010UH\u0007J\u0010\u0010V\u001a\u00020<2\u0006\u0010W\u001a\u000202H\u0016J\u0010\u0010X\u001a\u00020\u00132\u0006\u0010Y\u001a\u00020ZH\u0016J\u0010\u0010[\u001a\u00020\u00132\u0006\u0010Y\u001a\u00020ZH\u0016J\u0010\u0010\\\u001a\u00020\u00132\u0006\u0010Y\u001a\u00020ZH\u0016J\u0010\u0010]\u001a\u00020\u00132\u0006\u0010^\u001a\u00020ZH\u0016J\b\u0010_\u001a\u00020\u0013H\u0016J\u000e\u0010`\u001a\u00020<2\u0006\u00103\u001a\u00020\u0013J\u0016\u0010a\u001a\u00020<2\u0006\u0010b\u001a\u00020\u00112\u0006\u0010c\u001a\u000205J\u001d\u0010d\u001a\u00020<2\u0006\u0010b\u001a\u00020\u00112\b\u0010R\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010eJ\u0010\u0010f\u001a\u00020<2\u0006\u0010g\u001a\u000205H\u0007J\u0018\u0010f\u001a\u00020<2\u0006\u0010g\u001a\u0002052\u0006\u0010b\u001a\u00020\u0011H\u0007J\u0018\u0010f\u001a\u00020<2\u0006\u0010h\u001a\u00020i2\b\u0010g\u001a\u0004\u0018\u00010jJ\u0010\u0010k\u001a\u00020<2\b\u0010l\u001a\u0004\u0018\u00010*J\u0010\u0010s\u001a\u00020<2\u0006\u0010t\u001a\u00020\rH\u0016J\b\u0010u\u001a\u00020<H\u0016J\u0018\u0010u\u001a\u00020<2\u000e\u0010v\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010wH\u0016J\u0010\u0010x\u001a\u00020<2\u0006\u0010y\u001a\u00020\u0017H\u0016J\u0010\u0010z\u001a\u00020<2\u0006\u0010{\u001a\u00020\u0011H\u0002J\u0010\u0010|\u001a\u00020\u00132\u0006\u0010}\u001a\u00020\u0017H\u0002J\"\u0010~\u001a\u00020<2\u0006\u0010\u001d\u001a\u00020\r2\u0010\b\u0002\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010wH\u0002J5\u0010\u0080\u0001\u001a\u00020<2\u0006\u0010\u001d\u001a\u00020\r2\u0007\u0010\u0081\u0001\u001a\u00020\u00112\u0007\u0010\u0082\u0001\u001a\u00020\u00112\u0010\b\u0002\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010wH\u0002J\u0012\u0010\u0080\u0001\u001a\u00020<2\u0007\u0010\u0083\u0001\u001a\u00020\u0017H\u0002J-\u0010\u0084\u0001\u001a\u00020<2\u0007\u0010\u0085\u0001\u001a\u00020\u00112\u0007\u0010\u0086\u0001\u001a\u00020\u00112\u0007\u0010\u0087\u0001\u001a\u00020\u00112\u0007\u0010\u0088\u0001\u001a\u00020\u0011H\u0014J\t\u0010\u0089\u0001\u001a\u00020<H\u0014J\t\u0010\u008a\u0001\u001a\u00020\u0013H\u0002J\u0011\u0010\u008b\u0001\u001a\u00020<2\u0006\u0010}\u001a\u00020\u0017H\u0016J\u0011\u0010\u008c\u0001\u001a\u00020<2\u0006\u0010}\u001a\u00020\u0017H\u0016J\u001a\u0010\u008d\u0001\u001a\u00020<2\u0006\u0010}\u001a\u00020\u00172\u0007\u0010\u008e\u0001\u001a\u00020\u0013H\u0002J\u001b\u0010\u008f\u0001\u001a\u00020\u00112\u0007\u0010\u0090\u0001\u001a\u00020\u00112\u0007\u0010\u0091\u0001\u001a\u00020\u0011H\u0014J\u0012\u0010\u0092\u0001\u001a\u00020\u00112\u0007\u0010\u0091\u0001\u001a\u00020\u0011H\u0016J\t\u0010\u0093\u0001\u001a\u00020<H\u0016J\u0012\u0010\u0094\u0001\u001a\u00020<2\u0007\u0010\u0095\u0001\u001a\u00020\u0013H\u0014J\t\u0010\u0096\u0001\u001a\u00020<H\u0002J\u001a\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u00172\u0007\u0010\u0091\u0001\u001a\u00020\u0011H\u0000¢\u0006\u0003\b\u0098\u0001J \u0010\u0099\u0001\u001a\u00020<2\u0006\u0010}\u001a\u00020\u00172\u0007\u0010\u0091\u0001\u001a\u00020\u0011H\u0000¢\u0006\u0003\b\u009a\u0001J\u0017\u0010\u009b\u0001\u001a\u00020<2\u0006\u0010y\u001a\u00020\u0017H\u0000¢\u0006\u0003\b\u009c\u0001J\u000f\u0010\u009d\u0001\u001a\u00020<H\u0000¢\u0006\u0003\b\u009e\u0001J$\u0010\u009f\u0001\u001a\u00020\u00132\b\u0010y\u001a\u0004\u0018\u00010\u00172\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0003\u0010 \u0001J\u0011\u0010¡\u0001\u001a\u00020\u00112\u0006\u0010}\u001a\u00020\u0017H\u0002J\u001a\u0010¢\u0001\u001a\u00020<2\u0006\u0010}\u001a\u00020\u00172\u0007\u0010\u0091\u0001\u001a\u00020\u0011H\u0002J\u0012\u0010£\u0001\u001a\u00020<2\u0007\u0010\u0091\u0001\u001a\u00020\u0011H\u0002J)\u0010«\u0001\u001a\u00020<2\u0006\u0010I\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00112\u0006\u0010K\u001a\u00020\u00112\u0006\u0010L\u001a\u00020\u0011H\u0016J\u0014\u0010¬\u0001\u001a\u00020<2\t\u0010\u00ad\u0001\u001a\u0004\u0018\u00010UH\u0002J\u0013\u0010®\u0001\u001a\u00020<2\b\u0010¯\u0001\u001a\u00030°\u0001H\u0016J\u0013\u0010±\u0001\u001a\u00020<2\b\u0010¯\u0001\u001a\u00030°\u0001H\u0014J%\u0010²\u0001\u001a\u00020\u00132\b\u0010¯\u0001\u001a\u00030°\u00012\u0006\u0010}\u001a\u00020\u00172\b\u0010³\u0001\u001a\u00030´\u0001H\u0014J\u0010\u0010µ\u0001\u001a\u00020<2\u0007\u0010¶\u0001\u001a\u000205J\u0010\u0010·\u0001\u001a\u00020<2\u0007\u0010¸\u0001\u001a\u00020*J\u0007\u0010¹\u0001\u001a\u00020<J\u0019\u0010º\u0001\u001a\u00020<2\u000e\u0010»\u0001\u001a\t\u0012\u0004\u0012\u00020\u00170¼\u0001H\u0016J\u0007\u0010½\u0001\u001a\u00020<R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u001e\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0011@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010(\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00107\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010A\u001a\u00020@8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR$\u0010n\u001a\u00020\u00132\u0006\u0010m\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR\u0012\u0010¤\u0001\u001a\u0005\u0018\u00010¥\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010¦\u0001\u001a\u0004\u0018\u00010*2\t\u0010¦\u0001\u001a\u0004\u0018\u00010*8V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\b§\u0001\u0010¨\u0001\"\u0006\b©\u0001\u0010ª\u0001¨\u0006À\u0001"}, d2 = {"Lcom/facebook/react/views/view/ReactViewGroup;", "Landroid/view/ViewGroup;", "Lcom/facebook/react/touch/ReactInterceptingViewGroup;", "Lcom/facebook/react/uimanager/ReactClippingViewGroup;", "Lcom/facebook/react/uimanager/ReactPointerEventsView;", "Lcom/facebook/react/touch/ReactHitSlopView;", "Lcom/facebook/react/uimanager/ReactZIndexedViewGroup;", "Lcom/facebook/react/uimanager/ReactOverflowViewWithInset;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "overflowInset", "Landroid/graphics/Rect;", "getOverflowInset", "()Landroid/graphics/Rect;", "recycleCount", "", "_removeClippedSubviews", "", "inSubviewClippingLoop", "allChildren", "", "Landroid/view/View;", "[Landroid/view/View;", "value", "allChildrenCount", "getAllChildrenCount$ReactAndroid_release", "()I", "clippingRect", "hitSlopRect", "getHitSlopRect", "setHitSlopRect", "(Landroid/graphics/Rect;)V", ViewProps.POINTER_EVENTS, "Lcom/facebook/react/uimanager/PointerEvents;", "getPointerEvents", "()Lcom/facebook/react/uimanager/PointerEvents;", "setPointerEvents", "(Lcom/facebook/react/uimanager/PointerEvents;)V", "axOrderList", "", "", "getAxOrderList", "()Ljava/util/List;", "setAxOrderList", "(Ljava/util/List;)V", "childrenLayoutChangeListener", "Lcom/facebook/react/views/view/ReactViewGroup$ChildrenLayoutChangeListener;", "onInterceptTouchEventListener", "Lcom/facebook/react/touch/OnInterceptTouchEventListener;", ViewProps.NEEDS_OFFSCREEN_ALPHA_COMPOSITING, "backfaceOpacity", "", "backfaceVisible", "childrenRemovedWhileTransitioning", "", "accessibilityStateChangeListener", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "initView", "", "recycleView", "recycleView$ReactAndroid_release", "_drawingOrderHelper", "Lcom/facebook/react/uimanager/ViewGroupDrawingOrderHelper;", "drawingOrderHelper", "getDrawingOrderHelper", "()Lcom/facebook/react/uimanager/ViewGroupDrawingOrderHelper;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onLayout", "changed", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "requestLayout", "dispatchProvideStructure", "structure", "Landroid/view/ViewStructure;", "setBackgroundColor", "color", "setTranslucentBackgroundDrawable", AppStateModule.APP_STATE_BACKGROUND, "Landroid/graphics/drawable/Drawable;", "setOnInterceptTouchEventListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "onInterceptTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onTouchEvent", "onHoverEvent", "dispatchGenericMotionEvent", "ev", "hasOverlappingRendering", "setNeedsOffscreenAlphaCompositing", "setBorderWidth", ViewProps.POSITION, "width", "setBorderColor", "(ILjava/lang/Integer;)V", "setBorderRadius", ViewProps.BORDER_RADIUS, "property", "Lcom/facebook/react/uimanager/style/BorderRadiusProp;", "Lcom/facebook/react/uimanager/LengthPercentage;", "setBorderStyle", "style", "newValue", ReactClippingViewGroupHelper.PROP_REMOVE_CLIPPED_SUBVIEWS, "getRemoveClippedSubviews", "()Z", "setRemoveClippedSubviews", "(Z)V", "getClippingRect", "outClippingRect", "updateClippingRect", "excludedViews", "", "endViewTransition", "view", "trackChildViewTransition", "childId", "isChildRemovedWhileTransitioning", "child", "updateClippingToRect", "excludedViewsSet", "updateSubviewClipStatus", "idx", "clippedSoFar", "subview", "onSizeChanged", "w", CmcdData.STREAMING_FORMAT_HLS, "oldw", "oldh", "onAttachedToWindow", "customDrawOrderDisabled", "onViewAdded", "onViewRemoved", "checkViewClippingTag", "expectedTag", "getChildDrawingOrder", "childCount", "index", "getZIndexMappedChildIndex", "updateDrawingOrder", "dispatchSetPressed", "pressed", "resetPointerEvents", "getChildAtWithSubviewClippingEnabled", "getChildAtWithSubviewClippingEnabled$ReactAndroid_release", "addViewWithSubviewClippingEnabled", "addViewWithSubviewClippingEnabled$ReactAndroid_release", "removeViewWithSubviewClippingEnabled", "removeViewWithSubviewClippingEnabled$ReactAndroid_release", "removeAllViewsWithSubviewClippingEnabled", "removeAllViewsWithSubviewClippingEnabled$ReactAndroid_release", "isViewClipped", "(Landroid/view/View;Ljava/lang/Integer;)Z", "indexOfChildInAllChildren", "addInArray", "removeFromArray", "_overflow", "Lcom/facebook/react/uimanager/style/Overflow;", ViewProps.OVERFLOW, "getOverflow", "()Ljava/lang/String;", "setOverflow", "(Ljava/lang/String;)V", "setOverflowInset", "updateBackgroundDrawable", "drawable", "draw", "canvas", "Landroid/graphics/Canvas;", "dispatchDraw", "drawChild", "drawingTime", "", "setOpacityIfPossible", ViewProps.OPACITY, "setBackfaceVisibility", "backfaceVisibility", "setBackfaceVisibilityDependantOpacity", "addChildrenForAccessibility", "outChildren", "Ljava/util/ArrayList;", "cleanUpAxOrderListener", "ChildrenLayoutChangeListener", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ReactViewGroup extends ViewGroup implements ReactInterceptingViewGroup, ReactClippingViewGroup, ReactPointerEventsView, ReactHitSlopView, ReactZIndexedViewGroup, ReactOverflowViewWithInset {
    private static final int ARRAY_CAPACITY_INCREMENT = 12;
    private static final Companion Companion = new Companion(null);
    private static final ViewGroup.LayoutParams defaultLayoutParam = new ViewGroup.LayoutParams(0, 0);
    private ViewGroupDrawingOrderHelper _drawingOrderHelper;
    private Overflow _overflow;
    private boolean _removeClippedSubviews;
    private AccessibilityManager.AccessibilityStateChangeListener accessibilityStateChangeListener;
    private View[] allChildren;
    private int allChildrenCount;
    private List<String> axOrderList;
    private float backfaceOpacity;
    private boolean backfaceVisible;
    private ChildrenLayoutChangeListener childrenLayoutChangeListener;
    private Set<Integer> childrenRemovedWhileTransitioning;
    private Rect clippingRect;
    private Rect hitSlopRect;
    private volatile boolean inSubviewClippingLoop;
    private boolean needsOffscreenAlphaCompositing;
    private OnInterceptTouchEventListener onInterceptTouchEventListener;
    private final Rect overflowInset;
    private PointerEvents pointerEvents;
    private int recycleCount;

    /* compiled from: ReactViewGroup.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Overflow.values().length];
            try {
                iArr[Overflow.HIDDEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Overflow.SCROLL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Overflow.VISIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSetPressed(boolean pressed) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
    }

    public ReactViewGroup(Context context) {
        super(context);
        this.overflowInset = new Rect();
        this.pointerEvents = PointerEvents.AUTO;
        initView();
    }

    @Override // com.facebook.react.uimanager.ReactOverflowViewWithInset
    public Rect getOverflowInset() {
        return this.overflowInset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReactViewGroup.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005JP\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0006\u0010\u0013\u001a\u00020\u0007R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/react/views/view/ReactViewGroup$ChildrenLayoutChangeListener;", "Landroid/view/View$OnLayoutChangeListener;", "parent", "Lcom/facebook/react/views/view/ReactViewGroup;", "<init>", "(Lcom/facebook/react/views/view/ReactViewGroup;)V", "onLayoutChange", "", "v", "Landroid/view/View;", "left", "", ViewProps.TOP, "right", ViewProps.BOTTOM, "oldLeft", "oldTop", "oldRight", "oldBottom", "shutdown", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class ChildrenLayoutChangeListener implements View.OnLayoutChangeListener {
        private ReactViewGroup parent;

        public ChildrenLayoutChangeListener(ReactViewGroup reactViewGroup) {
            this.parent = reactViewGroup;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            ReactViewGroup reactViewGroup;
            Intrinsics.checkNotNullParameter(v, "v");
            ReactViewGroup reactViewGroup2 = this.parent;
            if (reactViewGroup2 == null || !reactViewGroup2.get_removeClippedSubviews() || (reactViewGroup = this.parent) == null) {
                return;
            }
            reactViewGroup.updateSubviewClipStatus(v);
        }

        public final void shutdown() {
            this.parent = null;
        }
    }

    /* renamed from: getAllChildrenCount$ReactAndroid_release, reason: from getter */
    public final int getAllChildrenCount() {
        return this.allChildrenCount;
    }

    @Override // com.facebook.react.touch.ReactHitSlopView
    public Rect getHitSlopRect() {
        return this.hitSlopRect;
    }

    public void setHitSlopRect(Rect rect) {
        this.hitSlopRect = rect;
    }

    @Override // com.facebook.react.uimanager.ReactPointerEventsView
    public PointerEvents getPointerEvents() {
        return this.pointerEvents;
    }

    public void setPointerEvents(PointerEvents pointerEvents) {
        Intrinsics.checkNotNullParameter(pointerEvents, "<set-?>");
        this.pointerEvents = pointerEvents;
    }

    public final List<String> getAxOrderList() {
        return this.axOrderList;
    }

    public final void setAxOrderList(List<String> list) {
        this.axOrderList = list;
    }

    private final void initView() {
        setClipChildren(false);
        this._removeClippedSubviews = false;
        this.inSubviewClippingLoop = false;
        this.allChildren = null;
        this.allChildrenCount = 0;
        this.clippingRect = null;
        setHitSlopRect(null);
        this._overflow = Overflow.VISIBLE;
        setPointerEvents(PointerEvents.AUTO);
        this.childrenLayoutChangeListener = null;
        this.onInterceptTouchEventListener = null;
        this.needsOffscreenAlphaCompositing = false;
        this._drawingOrderHelper = null;
        this.backfaceOpacity = 1.0f;
        this.backfaceVisible = true;
        this.childrenRemovedWhileTransitioning = null;
    }

    public void recycleView$ReactAndroid_release() {
        ChildrenLayoutChangeListener childrenLayoutChangeListener;
        this.recycleCount++;
        View[] viewArr = this.allChildren;
        if (viewArr != null && (childrenLayoutChangeListener = this.childrenLayoutChangeListener) != null) {
            if (childrenLayoutChangeListener != null) {
                childrenLayoutChangeListener.shutdown();
            }
            int i = this.allChildrenCount;
            for (int i2 = 0; i2 < i; i2++) {
                View view = viewArr[i2];
                if (view != null) {
                    view.removeOnLayoutChangeListener(this.childrenLayoutChangeListener);
                }
            }
        }
        initView();
        getOverflowInset().setEmpty();
        removeAllViews();
        if (getParent() != null) {
            ViewParent parent = getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
            ((ViewGroup) parent).removeView(this);
        }
        updateBackgroundDrawable(null);
        resetPointerEvents();
    }

    private final ViewGroupDrawingOrderHelper getDrawingOrderHelper() {
        if (this._drawingOrderHelper == null) {
            this._drawingOrderHelper = new ViewGroupDrawingOrderHelper(this);
        }
        ViewGroupDrawingOrderHelper viewGroupDrawingOrderHelper = this._drawingOrderHelper;
        if (viewGroupDrawingOrderHelper != null) {
            return viewGroupDrawingOrderHelper;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideStructure(ViewStructure structure) {
        Intrinsics.checkNotNullParameter(structure, "structure");
        try {
            super.dispatchProvideStructure(structure);
        } catch (NullPointerException e) {
            FLog.e(ReactConstants.TAG, "NullPointerException when executing dispatchProvideStructure", e);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
        BackgroundStyleApplicator.setBackgroundColor(this, Integer.valueOf(color));
    }

    @Deprecated(message = "setTranslucentBackgroundDrawable is deprecated since React Native 0.76.0 and will be removed in a future version")
    public final void setTranslucentBackgroundDrawable(Drawable background) {
        BackgroundStyleApplicator.setFeedbackUnderlay(this, background);
    }

    @Override // com.facebook.react.touch.ReactInterceptingViewGroup
    public void setOnInterceptTouchEventListener(OnInterceptTouchEventListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onInterceptTouchEventListener = listener;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        OnInterceptTouchEventListener onInterceptTouchEventListener = this.onInterceptTouchEventListener;
        if ((onInterceptTouchEventListener == null || !onInterceptTouchEventListener.onInterceptTouchEvent(this, event)) && PointerEvents.INSTANCE.canChildrenBeTouchTarget(getPointerEvents())) {
            return super.onInterceptTouchEvent(event);
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return PointerEvents.INSTANCE.canBeTouchTarget(getPointerEvents());
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (ReactFeatureFlags.dispatchPointerEvents) {
            return PointerEvents.INSTANCE.canBeTouchTarget(getPointerEvents());
        }
        return super.onHoverEvent(event);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        if (PointerEvents.INSTANCE.canChildrenBeTouchTarget(getPointerEvents())) {
            return super.dispatchGenericMotionEvent(ev);
        }
        return false;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return this.needsOffscreenAlphaCompositing;
    }

    public final void setNeedsOffscreenAlphaCompositing(boolean needsOffscreenAlphaCompositing) {
        this.needsOffscreenAlphaCompositing = needsOffscreenAlphaCompositing;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setBorderWidth(int position, float width) {
        BackgroundStyleApplicator.setBorderWidth(this, (LogicalEdge) LogicalEdge.getEntries().get(position), Float.valueOf(PixelUtil.toDIPFromPixel(width)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setBorderColor(int position, Integer color) {
        BackgroundStyleApplicator.setBorderColor(this, (LogicalEdge) LogicalEdge.getEntries().get(position), color);
    }

    @Deprecated(message = "setBorderRadius(Float) is deprecated and will be removed in the future.", replaceWith = @ReplaceWith(expression = "setBorderRadius(Float,LengthPercentage)", imports = {}))
    public final void setBorderRadius(float borderRadius) {
        BackgroundStyleApplicator.setBorderRadius(this, BorderRadiusProp.BORDER_RADIUS, Float.isNaN(borderRadius) ? null : new LengthPercentage(borderRadius, LengthPercentageType.POINT));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(message = "setBorderRadius(Float) is deprecated and will be removed in the future.", replaceWith = @ReplaceWith(expression = "setBorderRadius(Float,LengthPercentage)", imports = {}))
    public final void setBorderRadius(float borderRadius, int position) {
        BackgroundStyleApplicator.setBorderRadius(this, (BorderRadiusProp) BorderRadiusProp.getEntries().get(position), Float.isNaN(borderRadius) ? null : new LengthPercentage(borderRadius, LengthPercentageType.POINT));
    }

    public final void setBorderRadius(BorderRadiusProp property, LengthPercentage borderRadius) {
        Intrinsics.checkNotNullParameter(property, "property");
        BackgroundStyleApplicator.setBorderRadius(this, property, borderRadius);
    }

    public final void setBorderStyle(String style) {
        BackgroundStyleApplicator.setBorderStyle(this, style != null ? BorderStyle.INSTANCE.fromString(style) : null);
    }

    /* renamed from: getRemoveClippedSubviews, reason: from getter */
    public boolean get_removeClippedSubviews() {
        return this._removeClippedSubviews;
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z == this._removeClippedSubviews) {
            return;
        }
        this._removeClippedSubviews = z;
        this.childrenRemovedWhileTransitioning = null;
        if (z) {
            Rect rect = new Rect();
            ReactClippingViewGroupHelper.calculateClippingRect(this, rect);
            this.clippingRect = rect;
            int childCount = getChildCount();
            this.allChildrenCount = childCount;
            View[] viewArr = new View[Math.max(12, childCount)];
            this.childrenLayoutChangeListener = new ChildrenLayoutChangeListener(this);
            int i = this.allChildrenCount;
            for (int i2 = 0; i2 < i; i2++) {
                View childAt = getChildAt(i2);
                viewArr[i2] = childAt;
                childAt.addOnLayoutChangeListener(this.childrenLayoutChangeListener);
                Companion companion = Companion;
                Intrinsics.checkNotNull(childAt);
                companion.setViewClipped(childAt, false);
            }
            this.allChildren = viewArr;
            updateClippingRect();
            return;
        }
        View[] viewArr2 = this.allChildren;
        if (viewArr2 == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        if (this.childrenLayoutChangeListener == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        int i3 = this.allChildrenCount;
        for (int i4 = 0; i4 < i3; i4++) {
            View view = viewArr2[i4];
            if (view != null) {
                view.removeOnLayoutChangeListener(this.childrenLayoutChangeListener);
            }
        }
        Rect rect2 = this.clippingRect;
        if (rect2 == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        getDrawingRect(rect2);
        updateClippingToRect$default(this, rect2, null, 2, null);
        this.allChildren = null;
        this.clippingRect = null;
        this.allChildrenCount = 0;
        this.childrenLayoutChangeListener = null;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void getClippingRect(Rect outClippingRect) {
        Intrinsics.checkNotNullParameter(outClippingRect, "outClippingRect");
        Rect rect = this.clippingRect;
        if (rect == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        outClippingRect.set(rect);
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        updateClippingRect(null);
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect(Set<Integer> excludedViews) {
        if (this._removeClippedSubviews) {
            Rect rect = this.clippingRect;
            if (rect == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            ReactClippingViewGroupHelper.calculateClippingRect(this, rect);
            updateClippingToRect(rect, excludedViews);
        }
    }

    @Override // android.view.ViewGroup
    public void endViewTransition(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.endViewTransition(view);
        Set<Integer> set = this.childrenRemovedWhileTransitioning;
        if (set != null) {
            set.remove(Integer.valueOf(view.getId()));
        }
    }

    private final void trackChildViewTransition(int childId) {
        if (this.childrenRemovedWhileTransitioning == null) {
            this.childrenRemovedWhileTransitioning = new LinkedHashSet();
        }
        Set<Integer> set = this.childrenRemovedWhileTransitioning;
        if (set != null) {
            set.add(Integer.valueOf(childId));
        }
    }

    private final boolean isChildRemovedWhileTransitioning(View child) {
        Set<Integer> set = this.childrenRemovedWhileTransitioning;
        return set != null && set.contains(Integer.valueOf(child.getId()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void updateClippingToRect$default(ReactViewGroup reactViewGroup, Rect rect, Set set, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateClippingToRect");
        }
        if ((i & 2) != 0) {
            set = null;
        }
        reactViewGroup.updateClippingToRect(rect, set);
    }

    private final void updateClippingToRect(Rect clippingRect, Set<Integer> excludedViewsSet) {
        View[] viewArr = this.allChildren;
        if (viewArr == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        this.inSubviewClippingLoop = true;
        int i = this.allChildrenCount;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            try {
                updateSubviewClipStatus(clippingRect, i3, i2, excludedViewsSet);
                if (isViewClipped(viewArr[i3], Integer.valueOf(i3))) {
                    i2++;
                }
                if (i3 - i2 > getChildCount()) {
                    throw new IllegalStateException("Invalid clipping state. i=" + i3 + " clippedSoFar=" + i2 + " count=" + getChildCount() + " allChildrenCount=" + this.allChildrenCount + " recycleCount=" + this.recycleCount + "  excludedViews=" + (excludedViewsSet != null ? excludedViewsSet.size() : 0));
                }
            } catch (IndexOutOfBoundsException e) {
                HashSet hashSet = new HashSet();
                int i4 = 0;
                for (int i5 = 0; i5 < i3; i5++) {
                    i4 += isViewClipped(viewArr[i5], Integer.valueOf(i5)) ? 1 : 0;
                    hashSet.add(viewArr[i5]);
                }
                throw new IllegalStateException("Invalid clipping state. i=" + i3 + " clippedSoFar=" + i2 + " count=" + getChildCount() + " allChildrenCount=" + this.allChildrenCount + " recycleCount=" + this.recycleCount + " realClippedSoFar=" + i4 + " uniqueViewsCount=" + hashSet.size() + " excludedViews=" + (excludedViewsSet != null ? excludedViewsSet.size() : 0), e);
            }
        }
        this.inSubviewClippingLoop = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void updateSubviewClipStatus$default(ReactViewGroup reactViewGroup, Rect rect, int i, int i2, Set set, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateSubviewClipStatus");
        }
        if ((i3 & 8) != 0) {
            set = null;
        }
        reactViewGroup.updateSubviewClipStatus(rect, i, i2, set);
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0096, code lost:
    
        if (r9 != false) goto L42;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.facebook.react.views.view.ReactViewGroup] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void updateSubviewClipStatus(android.graphics.Rect r9, int r10, int r11, java.util.Set<java.lang.Integer> r12) {
        /*
            r8 = this;
            com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()
            android.view.View[] r0 = r8.allChildren
            r1 = 0
            if (r0 == 0) goto Lb
            r0 = r0[r10]
            goto Lc
        Lb:
            r0 = r1
        Lc:
            if (r0 == 0) goto Lb3
            int r2 = r0.getLeft()
            int r3 = r0.getTop()
            int r4 = r0.getRight()
            int r5 = r0.getBottom()
            boolean r9 = r9.intersects(r2, r3, r4, r5)
            android.view.animation.Animation r2 = r0.getAnimation()
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L32
            boolean r2 = r2.hasEnded()
            if (r2 != 0) goto L32
            r2 = r4
            goto L33
        L32:
            r2 = r3
        L33:
            if (r12 == 0) goto L45
            int r5 = r0.getId()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            boolean r5 = r12.contains(r5)
            if (r5 != r4) goto L45
            r5 = r4
            goto L46
        L45:
            r5 = r3
        L46:
            if (r12 == 0) goto L4a
            r6 = r4
            goto L4b
        L4a:
            r6 = r3
        L4b:
            if (r9 != 0) goto L6a
            java.lang.Integer r7 = java.lang.Integer.valueOf(r10)
            boolean r7 = r8.isViewClipped(r0, r7)
            if (r7 != 0) goto L6a
            if (r2 != 0) goto L6a
            android.view.View r2 = r8.getFocusedChild()
            if (r0 == r2) goto L6a
            if (r5 != 0) goto L6a
            com.facebook.react.views.view.ReactViewGroup$Companion r9 = com.facebook.react.views.view.ReactViewGroup.Companion
            com.facebook.react.views.view.ReactViewGroup.Companion.access$setViewClipped(r9, r0, r4)
            r8.removeViewInLayout(r0)
            goto L98
        L6a:
            if (r5 != 0) goto L6e
            if (r9 == 0) goto L96
        L6e:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
            boolean r2 = r8.isViewClipped(r0, r2)
            if (r2 == 0) goto L96
            int r10 = r10 - r11
            if (r10 < 0) goto L7d
            r9 = r4
            goto L7e
        L7d:
            r9 = r3
        L7e:
            if (r9 == 0) goto L8e
            com.facebook.react.views.view.ReactViewGroup$Companion r9 = com.facebook.react.views.view.ReactViewGroup.Companion
            com.facebook.react.views.view.ReactViewGroup.Companion.access$setViewClipped(r9, r0, r3)
            android.view.ViewGroup$LayoutParams r9 = com.facebook.react.views.view.ReactViewGroup.defaultLayoutParam
            r8.addViewInLayout(r0, r10, r9, r4)
            r8.invalidate()
            goto L98
        L8e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Check failed."
            r9.<init>(r10)
            throw r9
        L96:
            if (r9 == 0) goto L99
        L98:
            r6 = r4
        L99:
            if (r6 == 0) goto Lb2
            boolean r9 = r0 instanceof com.facebook.react.uimanager.ReactClippingViewGroup
            if (r9 == 0) goto La2
            r1 = r0
            com.facebook.react.uimanager.ReactClippingViewGroup r1 = (com.facebook.react.uimanager.ReactClippingViewGroup) r1
        La2:
            if (r1 == 0) goto Lab
            boolean r9 = r1.get_removeClippedSubviews()
            if (r9 != r4) goto Lab
            r3 = r4
        Lab:
            if (r3 == 0) goto Lb2
            com.facebook.react.uimanager.ReactClippingViewGroup r0 = (com.facebook.react.uimanager.ReactClippingViewGroup) r0
            r0.updateClippingRect(r12)
        Lb2:
            return
        Lb3:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Required value was null."
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewGroup.updateSubviewClipStatus(android.graphics.Rect, int, int, java.util.Set):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSubviewClipStatus(View subview) {
        ReactViewGroup reactViewGroup;
        if (this._removeClippedSubviews && getParent() != null) {
            Rect rect = this.clippingRect;
            if (rect == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            View[] viewArr = this.allChildren;
            if (viewArr == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            if (rect.intersects(subview.getLeft(), subview.getTop(), subview.getRight(), subview.getBottom()) != (!isViewClipped(subview, null))) {
                this.inSubviewClippingLoop = true;
                int i = this.allChildrenCount;
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    if (i2 >= i) {
                        reactViewGroup = this;
                        break;
                    }
                    View view = viewArr[i2];
                    if (view == subview) {
                        updateSubviewClipStatus$default(this, rect, i2, i3, null, 8, null);
                        reactViewGroup = this;
                        break;
                    }
                    int i4 = i2;
                    Rect rect2 = rect;
                    if (isViewClipped(view, Integer.valueOf(i4))) {
                        i3++;
                    }
                    rect = rect2;
                    i2 = i4 + 1;
                }
                reactViewGroup.inSubviewClippingLoop = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this._removeClippedSubviews) {
            updateClippingRect();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this._removeClippedSubviews) {
            updateClippingRect();
        }
    }

    private final boolean customDrawOrderDisabled() {
        return getId() != -1 && ViewUtil.getUIManagerType(getId()) == 2;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View child) {
        Intrinsics.checkNotNullParameter(child, "child");
        UiThreadUtil.assertOnUiThread();
        checkViewClippingTag(child, false);
        if (!customDrawOrderDisabled()) {
            getDrawingOrderHelper().handleAddView(child);
            setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        super.onViewAdded(child);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View child) {
        Intrinsics.checkNotNullParameter(child, "child");
        UiThreadUtil.assertOnUiThread();
        checkViewClippingTag(child, true);
        if (!customDrawOrderDisabled()) {
            getDrawingOrderHelper().handleRemoveView(child);
            setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        if (child.getParent() != null) {
            trackChildViewTransition(child.getId());
        }
        super.onViewRemoved(child);
    }

    private final void checkViewClippingTag(View child, boolean expectedTag) {
        if (this.inSubviewClippingLoop) {
            Object tag = child.getTag(R.id.view_clipped);
            if (!Intrinsics.areEqual(Boolean.valueOf(expectedTag), tag)) {
                ReactSoftExceptionLogger.logSoftException(ReactSoftExceptionLogger.Categories.RVG_ON_VIEW_REMOVED, new ReactNoCrashSoftException("View clipping tag mismatch: tag=" + tag + " expected=" + expectedTag));
            }
        }
        if (this._removeClippedSubviews) {
            child.setTag(R.id.view_clipped, Boolean.valueOf(expectedTag));
        } else {
            child.setTag(R.id.view_clipped, null);
        }
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int childCount, int index) {
        UiThreadUtil.assertOnUiThread();
        return !customDrawOrderDisabled() ? getDrawingOrderHelper().getChildDrawingOrder(childCount, index) : index;
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public int getZIndexMappedChildIndex(int index) {
        UiThreadUtil.assertOnUiThread();
        return (customDrawOrderDisabled() || !getDrawingOrderHelper().shouldEnableCustomDrawingOrder()) ? index : getDrawingOrderHelper().getChildDrawingOrder(getChildCount(), index);
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public void updateDrawingOrder() {
        if (customDrawOrderDisabled()) {
            return;
        }
        getDrawingOrderHelper().update();
        setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
        invalidate();
    }

    private final void resetPointerEvents() {
        setPointerEvents(PointerEvents.AUTO);
    }

    public final View getChildAtWithSubviewClippingEnabled$ReactAndroid_release(int index) {
        if (index < 0 || index >= this.allChildrenCount) {
            return null;
        }
        View[] viewArr = this.allChildren;
        if (viewArr != null) {
            return viewArr[index];
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public final void addViewWithSubviewClippingEnabled$ReactAndroid_release(final View child, int index) {
        Intrinsics.checkNotNullParameter(child, "child");
        if (!this._removeClippedSubviews) {
            throw new IllegalStateException("Check failed.");
        }
        Companion.setViewClipped(child, true);
        addInArray(child, index);
        Rect rect = this.clippingRect;
        if (rect == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        View[] viewArr = this.allChildren;
        if (viewArr == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        this.inSubviewClippingLoop = true;
        int i = 0;
        for (int i2 = 0; i2 < index; i2++) {
            if (isViewClipped(viewArr[i2], Integer.valueOf(i2))) {
                i++;
            }
        }
        updateSubviewClipStatus$default(this, rect, index, i, null, 8, null);
        this.inSubviewClippingLoop = false;
        child.addOnLayoutChangeListener(this.childrenLayoutChangeListener);
        if (child instanceof ReactClippingProhibitedView) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.views.view.ReactViewGroup$addViewWithSubviewClippingEnabled$1
                @Override // java.lang.Runnable
                public void run() {
                    if (child.isShown()) {
                        return;
                    }
                    ReactSoftExceptionLogger.logSoftException(ReactSoftExceptionLogger.Categories.CLIPPING_PROHIBITED_VIEW, new ReactNoCrashSoftException("Child view has been added to Parent view in which it is clipped and not visible. This is not legal for this particular child view. Child: [" + child.getId() + "] " + child + " Parent: [" + this.getId() + "] " + this));
                }
            });
        }
    }

    public final void removeViewWithSubviewClippingEnabled$ReactAndroid_release(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UiThreadUtil.assertOnUiThread();
        if (!this._removeClippedSubviews) {
            throw new IllegalStateException("Check failed.");
        }
        View[] viewArr = this.allChildren;
        if (viewArr == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        view.removeOnLayoutChangeListener(this.childrenLayoutChangeListener);
        int indexOfChildInAllChildren = indexOfChildInAllChildren(view);
        if (!isViewClipped(viewArr[indexOfChildInAllChildren], Integer.valueOf(indexOfChildInAllChildren))) {
            int i = 0;
            for (int i2 = 0; i2 < indexOfChildInAllChildren; i2++) {
                if (isViewClipped(viewArr[i2], Integer.valueOf(i2))) {
                    i++;
                }
            }
            removeViewsInLayout(indexOfChildInAllChildren - i, 1);
            invalidate();
        }
        removeFromArray(indexOfChildInAllChildren);
    }

    public final void removeAllViewsWithSubviewClippingEnabled$ReactAndroid_release() {
        if (!this._removeClippedSubviews) {
            throw new IllegalStateException("Check failed.");
        }
        View[] viewArr = this.allChildren;
        if (viewArr == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        int i = this.allChildrenCount;
        for (int i2 = 0; i2 < i; i2++) {
            View view = viewArr[i2];
            if (view != null) {
                view.removeOnLayoutChangeListener(this.childrenLayoutChangeListener);
            }
        }
        removeAllViewsInLayout();
        this.allChildrenCount = 0;
    }

    private final boolean isViewClipped(View view, Integer index) {
        if (view == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        Object tag = view.getTag(R.id.view_clipped);
        if (tag != null) {
            return ((Boolean) tag).booleanValue();
        }
        ViewParent parent = view.getParent();
        boolean isChildRemovedWhileTransitioning = isChildRemovedWhileTransitioning(view);
        if (index != null) {
            ReactSoftExceptionLogger.logSoftException(ReactSoftExceptionLogger.Categories.RVG_IS_VIEW_CLIPPED, new ReactNoCrashSoftException("View missing clipping tag: index=" + index + " parentNull=" + (parent == null) + " parentThis=" + (parent == this) + " transitioning=" + isChildRemovedWhileTransitioning));
        }
        if (parent == null || isChildRemovedWhileTransitioning) {
            return true;
        }
        if (parent == this) {
            return false;
        }
        throw new IllegalStateException("Check failed.");
    }

    private final int indexOfChildInAllChildren(View child) {
        int i = this.allChildrenCount;
        View[] viewArr = this.allChildren;
        if (viewArr == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (viewArr[i2] == child) {
                return i2;
            }
        }
        return -1;
    }

    private final void addInArray(View child, int index) {
        View[] viewArr = this.allChildren;
        if (viewArr == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        int i = this.allChildrenCount;
        int length = viewArr.length;
        if (index == i) {
            if (length == i) {
                View[] viewArr2 = new View[length + 12];
                System.arraycopy(viewArr, 0, viewArr2, 0, length);
                this.allChildren = viewArr2;
                viewArr = viewArr2;
            }
            int i2 = this.allChildrenCount;
            this.allChildrenCount = i2 + 1;
            viewArr[i2] = child;
            return;
        }
        if (index < i) {
            if (length == i) {
                View[] viewArr3 = new View[length + 12];
                System.arraycopy(viewArr, 0, viewArr3, 0, index);
                System.arraycopy(viewArr, index, viewArr3, index + 1, i - index);
                this.allChildren = viewArr3;
                viewArr = viewArr3;
            } else {
                System.arraycopy(viewArr, index, viewArr, index + 1, i - index);
            }
            viewArr[index] = child;
            this.allChildrenCount++;
            return;
        }
        throw new IndexOutOfBoundsException("index=" + index + " count=" + i);
    }

    private final void removeFromArray(int index) {
        View[] viewArr = this.allChildren;
        if (viewArr == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        int i = this.allChildrenCount;
        if (index == i - 1) {
            int i2 = i - 1;
            this.allChildrenCount = i2;
            viewArr[i2] = null;
        } else {
            if (index >= 0 && index < i) {
                System.arraycopy(viewArr, index + 1, viewArr, index, (i - index) - 1);
                int i3 = this.allChildrenCount - 1;
                this.allChildrenCount = i3;
                viewArr[i3] = null;
                return;
            }
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // com.facebook.react.uimanager.ReactOverflowView
    public String getOverflow() {
        Overflow overflow = this._overflow;
        int i = overflow == null ? -1 : WhenMappings.$EnumSwitchMapping$0[overflow.ordinal()];
        if (i == 1) {
            return ViewProps.HIDDEN;
        }
        if (i == 2) {
            return ViewProps.SCROLL;
        }
        if (i != 3) {
            return null;
        }
        return ViewProps.VISIBLE;
    }

    public void setOverflow(String str) {
        Overflow fromString;
        if (str == null) {
            fromString = Overflow.VISIBLE;
        } else {
            fromString = Overflow.INSTANCE.fromString(str);
        }
        this._overflow = fromString;
        invalidate();
    }

    @Override // com.facebook.react.uimanager.ReactOverflowViewWithInset
    public void setOverflowInset(int left, int top, int right, int bottom) {
        if (BlendModeHelper.needsIsolatedLayer(this) && (getOverflowInset().left != left || getOverflowInset().top != top || getOverflowInset().right != right || getOverflowInset().bottom != bottom)) {
            invalidate();
        }
        getOverflowInset().set(left, top, right, bottom);
    }

    private final void updateBackgroundDrawable(Drawable drawable) {
        super.setBackground(drawable);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (Build.VERSION.SDK_INT >= 29 && ViewUtil.getUIManagerType(this) == 2 && BlendModeHelper.needsIsolatedLayer(this)) {
            Rect overflowInset = getOverflowInset();
            canvas.saveLayer(overflowInset.left, overflowInset.top, getWidth() + (-overflowInset.right), getHeight() + (-overflowInset.bottom), null);
            super.draw(canvas);
            canvas.restore();
            return;
        }
        super.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (this._overflow != Overflow.VISIBLE || getTag(R.id.filter) != null) {
            BackgroundStyleApplicator.clipToPaddingBox(this, canvas);
        }
        super.dispatchDraw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007b  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean drawChild(android.graphics.Canvas r12, android.view.View r13, long r14) {
        /*
            r11 = this;
            java.lang.String r0 = "canvas"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "child"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            float r0 = r13.getElevation()
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            r1 = 1
            r2 = 0
            if (r0 <= 0) goto L17
            r0 = r1
            goto L18
        L17:
            r0 = r2
        L18:
            if (r0 == 0) goto L1d
            com.facebook.react.views.view.CanvasUtil.enableZ(r12, r1)
        L1d:
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 29
            r4 = 0
            if (r1 < r3) goto L6f
            r1 = r11
            android.view.View r1 = (android.view.View) r1
            int r1 = com.facebook.react.uimanager.common.ViewUtil.getUIManagerType(r1)
            r3 = 2
            if (r1 != r3) goto L6f
            r1 = r11
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            boolean r1 = com.facebook.react.uimanager.BlendModeHelper.needsIsolatedLayer(r1)
            if (r1 == 0) goto L6f
            int r1 = com.facebook.react.R.id.mix_blend_mode
            java.lang.Object r1 = r13.getTag(r1)
            boolean r3 = r1 instanceof android.graphics.BlendMode
            if (r3 == 0) goto L44
            android.graphics.BlendMode r1 = (android.graphics.BlendMode) r1
            r4 = r1
        L44:
            if (r4 == 0) goto L6f
            android.graphics.Paint r10 = new android.graphics.Paint
            r10.<init>()
            r10.setBlendMode(r4)
            android.graphics.Rect r1 = r11.getOverflowInset()
            int r3 = r1.left
            float r6 = (float) r3
            int r3 = r1.top
            float r7 = (float) r3
            int r3 = r11.getWidth()
            int r5 = r1.right
            int r5 = -r5
            int r3 = r3 + r5
            float r8 = (float) r3
            int r3 = r11.getHeight()
            int r1 = r1.bottom
            int r1 = -r1
            int r3 = r3 + r1
            float r9 = (float) r3
            r5 = r12
            r5.saveLayer(r6, r7, r8, r9, r10)
            goto L70
        L6f:
            r5 = r12
        L70:
            boolean r12 = super.drawChild(r5, r13, r14)
            if (r4 == 0) goto L79
            r5.restore()
        L79:
            if (r0 == 0) goto L7e
            com.facebook.react.views.view.CanvasUtil.enableZ(r5, r2)
        L7e:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewGroup.drawChild(android.graphics.Canvas, android.view.View, long):boolean");
    }

    public final void setOpacityIfPossible(float opacity) {
        this.backfaceOpacity = opacity;
        setBackfaceVisibilityDependantOpacity();
    }

    public final void setBackfaceVisibility(String backfaceVisibility) {
        Intrinsics.checkNotNullParameter(backfaceVisibility, "backfaceVisibility");
        this.backfaceVisible = Intrinsics.areEqual(ViewProps.VISIBLE, backfaceVisibility);
        setBackfaceVisibilityDependantOpacity();
    }

    public final void setBackfaceVisibilityDependantOpacity() {
        if (this.backfaceVisible) {
            setAlpha(this.backfaceOpacity);
            return;
        }
        float rotationX = getRotationX();
        float rotationY = getRotationY();
        if (rotationX >= -90.0f && rotationX < 90.0f && rotationY >= -90.0f && rotationY < 90.0f) {
            setAlpha(this.backfaceOpacity);
        } else {
            setAlpha(0.0f);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> outChildren) {
        Intrinsics.checkNotNullParameter(outChildren, "outChildren");
        ReactViewGroup reactViewGroup = (ReactViewGroup) getTag(R.id.accessibility_order_parent);
        List<String> list = reactViewGroup != null ? reactViewGroup.axOrderList : null;
        List<String> list2 = this.axOrderList;
        if (list2 == null) {
            if (list != null) {
                if (!isFocusable()) {
                    super.addChildrenForAccessibility(outChildren);
                    return;
                }
                if (isFocusable() && (getContentDescription() == null || Intrinsics.areEqual(getContentDescription(), ""))) {
                    super.addChildrenForAccessibility(outChildren);
                    ReactAxOrderHelper.INSTANCE.disableFocusForSubtree(this, list);
                    return;
                } else {
                    if (!isFocusable() || getContentDescription() == null) {
                        return;
                    }
                    Intrinsics.areEqual(getContentDescription(), "");
                    return;
                }
            }
            super.addChildrenForAccessibility(outChildren);
            return;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        if (this.accessibilityStateChangeListener == null && accessibilityManager != null) {
            AccessibilityManager.AccessibilityStateChangeListener accessibilityStateChangeListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: com.facebook.react.views.view.ReactViewGroup$$ExternalSyntheticLambda0
                @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
                public final void onAccessibilityStateChanged(boolean z) {
                    ReactViewGroup.addChildrenForAccessibility$lambda$1(ReactViewGroup.this, z);
                }
            };
            accessibilityManager.addAccessibilityStateChangeListener(accessibilityStateChangeListener);
            this.accessibilityStateChangeListener = accessibilityStateChangeListener;
        }
        int size = list2.size();
        View[] viewArr = new View[size];
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ReactAxOrderHelper reactAxOrderHelper = ReactAxOrderHelper.INSTANCE;
            View childAt = getChildAt(i);
            Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
            reactAxOrderHelper.buildAxOrderList(childAt, list2, viewArr);
        }
        for (int i2 = 0; i2 < size; i2++) {
            View view = viewArr[i2];
            if (view != null) {
                if (view.isFocusable()) {
                    outChildren.add(view);
                } else {
                    view.addChildrenForAccessibility(outChildren);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addChildrenForAccessibility$lambda$1(ReactViewGroup reactViewGroup, boolean z) {
        if (z) {
            return;
        }
        ReactAxOrderHelper.restoreFocusability(reactViewGroup);
    }

    public final void cleanUpAxOrderListener() {
        AccessibilityManager.AccessibilityStateChangeListener accessibilityStateChangeListener;
        Object systemService = getContext().getSystemService("accessibility");
        AccessibilityManager accessibilityManager = systemService instanceof AccessibilityManager ? (AccessibilityManager) systemService : null;
        if (accessibilityManager != null && (accessibilityStateChangeListener = this.accessibilityStateChangeListener) != null) {
            accessibilityManager.removeAccessibilityStateChangeListener(accessibilityStateChangeListener);
        }
        this.accessibilityStateChangeListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReactViewGroup.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/react/views/view/ReactViewGroup$Companion;", "", "<init>", "()V", "ARRAY_CAPACITY_INCREMENT", "", "defaultLayoutParam", "Landroid/view/ViewGroup$LayoutParams;", "setViewClipped", "", "view", "Landroid/view/View;", "clipped", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setViewClipped(View view, boolean clipped) {
            view.setTag(R.id.view_clipped, Boolean.valueOf(clipped));
        }
    }
}
