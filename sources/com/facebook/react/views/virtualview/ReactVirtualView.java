package com.facebook.react.views.virtualview;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.scroll.ReactHorizontalScrollView;
import com.facebook.react.views.scroll.ReactScrollView;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.systrace.Systrace;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactVirtualView.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u00107\u001a\u000208H\u0010¢\u0006\u0002\b9J\b\u0010:\u001a\u000208H\u0014J\r\u0010;\u001a\u000208H\u0001¢\u0006\u0002\b<J\b\u0010=\u001a\u000208H\u0014J0\u0010>\u001a\u0002082\u0006\u0010?\u001a\u00020\"2\u0006\u0010@\u001a\u0002002\u0006\u0010A\u001a\u0002002\u0006\u0010B\u001a\u0002002\u0006\u0010C\u001a\u000200H\u0014JR\u0010D\u001a\u0002082\b\u0010E\u001a\u0004\u0018\u00010*2\u0006\u0010@\u001a\u0002002\u0006\u0010A\u001a\u0002002\u0006\u0010B\u001a\u0002002\u0006\u0010C\u001a\u0002002\u0006\u0010F\u001a\u0002002\u0006\u0010G\u001a\u0002002\u0006\u0010H\u001a\u0002002\u0006\u0010I\u001a\u000200H\u0016J,\u0010J\u001a\u0002082\b\u0010K\u001a\u0004\u0018\u00010L2\b\u0010M\u001a\u0004\u0018\u00010N2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020PH\u0016J\u0012\u0010>\u001a\u0002082\b\u0010K\u001a\u0004\u0018\u00010LH\u0016J\u0010\u0010D\u001a\u0002082\u0006\u0010K\u001a\u00020LH\u0016J\u0010\u0010R\u001a\u0002082\u0006\u0010S\u001a\u00020\"H\u0002J\u0018\u0010T\u001a\u00020\"2\u0006\u0010U\u001a\u00020,2\u0006\u0010V\u001a\u00020,H\u0002J\u001a\u0010W\u001a\u0002082\b\u0010X\u001a\u0004\u0018\u00010\n2\u0006\u0010Y\u001a\u00020\nH\u0002J\u0010\u0010Z\u001a\u0002082\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010[\u001a\u0002082\u0006\u0010\t\u001a\u00020\nH\u0002J\n\u0010\\\u001a\u0004\u0018\u00010LH\u0002J\b\u0010]\u001a\u000208H\u0002J\u0012\u0010^\u001a\u0004\u0018\u00010L2\u0006\u0010_\u001a\u00020\"H\u0002J\b\u0010`\u001a\u000208H\u0002J)\u0010a\u001a\u0002082\u0006\u0010b\u001a\u0002042\u000e\b\u0002\u0010c\u001a\b\u0012\u0004\u0012\u0002040dH\u0080\bø\u0001\u0000¢\u0006\u0002\beR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\"X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\"X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00103\u001a\u0004\u0018\u0001048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u00106\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006f"}, d2 = {"Lcom/facebook/react/views/virtualview/ReactVirtualView;", "Lcom/facebook/react/views/view/ReactViewGroup;", "Lcom/facebook/react/views/scroll/ReactScrollViewHelper$ScrollListener;", "Lcom/facebook/react/views/scroll/ReactScrollViewHelper$LayoutChangeListener;", "Landroid/view/View$OnLayoutChangeListener;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "mode", "Lcom/facebook/react/views/virtualview/VirtualViewMode;", "getMode$ReactAndroid_release", "()Lcom/facebook/react/views/virtualview/VirtualViewMode;", "setMode$ReactAndroid_release", "(Lcom/facebook/react/views/virtualview/VirtualViewMode;)V", "renderState", "Lcom/facebook/react/views/virtualview/VirtualViewRenderState;", "getRenderState$ReactAndroid_release", "()Lcom/facebook/react/views/virtualview/VirtualViewRenderState;", "setRenderState$ReactAndroid_release", "(Lcom/facebook/react/views/virtualview/VirtualViewRenderState;)V", "modeChangeEmitter", "Lcom/facebook/react/views/virtualview/ModeChangeEmitter;", "getModeChangeEmitter$ReactAndroid_release", "()Lcom/facebook/react/views/virtualview/ModeChangeEmitter;", "setModeChangeEmitter$ReactAndroid_release", "(Lcom/facebook/react/views/virtualview/ModeChangeEmitter;)V", "prerenderRatio", "", "getPrerenderRatio$ReactAndroid_release", "()D", "setPrerenderRatio$ReactAndroid_release", "(D)V", "debugLogEnabled", "", "getDebugLogEnabled$ReactAndroid_release", "()Z", "detectWindowFocus", "getDetectWindowFocus$ReactAndroid_release", "onWindowFocusChangeListener", "Landroid/view/ViewTreeObserver$OnWindowFocusChangeListener;", "parentScrollView", "Landroid/view/View;", "lastRect", "Landroid/graphics/Rect;", "targetRect", "thresholdRect", "offsetX", "", "offsetY", "offsetChanged", "nativeId", "", "getNativeId$ReactAndroid_release", "()Ljava/lang/String;", "recycleView", "", "recycleView$ReactAndroid_release", "onAttachedToWindow", "doAttachedToWindow", "doAttachedToWindow$ReactAndroid_release", "onDetachedFromWindow", "onLayout", "changed", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "onLayoutChange", "v", "oldLeft", "oldTop", "oldRight", "oldBottom", "onScroll", "scrollView", "Landroid/view/ViewGroup;", "scrollEventType", "Lcom/facebook/react/views/scroll/ScrollEventType;", "xVelocity", "", "yVelocity", "dispatchOnModeChangeIfNeeded", "checkRectChange", "rectsOverlap", "rect1", "rect2", "maybeEmitModeChanges", "oldMode", "newMode", "emitAsyncModeChange", "emitSyncModeChange", "getParentScrollView", "cleanupLayoutListeners", "traverseParentStack", "addListeners", "updateParentOffset", "debugLog", "subtag", "block", "Lkotlin/Function0;", "debugLog$ReactAndroid_release", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactVirtualView extends ReactViewGroup implements ReactScrollViewHelper.ScrollListener, ReactScrollViewHelper.LayoutChangeListener, View.OnLayoutChangeListener {
    private final boolean debugLogEnabled;
    private final boolean detectWindowFocus;
    private final Rect lastRect;
    private VirtualViewMode mode;
    private ModeChangeEmitter modeChangeEmitter;
    private boolean offsetChanged;
    private int offsetX;
    private int offsetY;
    private final ViewTreeObserver.OnWindowFocusChangeListener onWindowFocusChangeListener;
    private View parentScrollView;
    private double prerenderRatio;
    private VirtualViewRenderState renderState;
    private final Rect targetRect;
    private final Rect thresholdRect;

    /* compiled from: ReactVirtualView.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VirtualViewMode.values().length];
            try {
                iArr[VirtualViewMode.Visible.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VirtualViewMode.Prerender.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[VirtualViewMode.Hidden.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactVirtualView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.renderState = VirtualViewRenderState.Unknown;
        this.prerenderRatio = ReactNativeFeatureFlags.virtualViewPrerenderRatio();
        this.debugLogEnabled = ReactNativeFeatureFlags.enableVirtualViewDebugFeatures();
        this.detectWindowFocus = ReactNativeFeatureFlags.enableVirtualViewWindowFocusDetection();
        this.onWindowFocusChangeListener = new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: com.facebook.react.views.virtualview.ReactVirtualView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
            public final void onWindowFocusChanged(boolean z) {
                ReactVirtualView.this.dispatchOnModeChangeIfNeeded(false);
            }
        };
        this.lastRect = new Rect();
        this.targetRect = new Rect();
        this.thresholdRect = new Rect();
    }

    /* renamed from: getMode$ReactAndroid_release, reason: from getter */
    public final VirtualViewMode getMode() {
        return this.mode;
    }

    public final void setMode$ReactAndroid_release(VirtualViewMode virtualViewMode) {
        this.mode = virtualViewMode;
    }

    /* renamed from: getRenderState$ReactAndroid_release, reason: from getter */
    public final VirtualViewRenderState getRenderState() {
        return this.renderState;
    }

    public final void setRenderState$ReactAndroid_release(VirtualViewRenderState virtualViewRenderState) {
        Intrinsics.checkNotNullParameter(virtualViewRenderState, "<set-?>");
        this.renderState = virtualViewRenderState;
    }

    /* renamed from: getModeChangeEmitter$ReactAndroid_release, reason: from getter */
    public final ModeChangeEmitter getModeChangeEmitter() {
        return this.modeChangeEmitter;
    }

    public final void setModeChangeEmitter$ReactAndroid_release(ModeChangeEmitter modeChangeEmitter) {
        this.modeChangeEmitter = modeChangeEmitter;
    }

    /* renamed from: getPrerenderRatio$ReactAndroid_release, reason: from getter */
    public final double getPrerenderRatio() {
        return this.prerenderRatio;
    }

    public final void setPrerenderRatio$ReactAndroid_release(double d) {
        this.prerenderRatio = d;
    }

    /* renamed from: getDebugLogEnabled$ReactAndroid_release, reason: from getter */
    public final boolean getDebugLogEnabled() {
        return this.debugLogEnabled;
    }

    /* renamed from: getDetectWindowFocus$ReactAndroid_release, reason: from getter */
    public final boolean getDetectWindowFocus() {
        return this.detectWindowFocus;
    }

    public final String getNativeId$ReactAndroid_release() {
        Object tag = getTag(R.id.view_tag_native_id);
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup
    public void recycleView$ReactAndroid_release() {
        ReactScrollViewHelper.removeScrollListener(this);
        ReactScrollViewHelper.removeLayoutChangeListener(this);
        cleanupLayoutListeners();
        this.mode = null;
        this.modeChangeEmitter = null;
        this.lastRect.setEmpty();
        this.parentScrollView = null;
        this.offsetX = 0;
        this.offsetY = 0;
        this.offsetChanged = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        doAttachedToWindow$ReactAndroid_release();
    }

    public final void doAttachedToWindow$ReactAndroid_release() {
        boolean z;
        ViewGroup parentScrollView = getParentScrollView();
        if (parentScrollView != null) {
            this.offsetChanged = true;
            ReactScrollViewHelper.addScrollListener(this);
            ReactScrollViewHelper.addLayoutChangeListener(this);
        } else {
            parentScrollView = null;
        }
        this.parentScrollView = parentScrollView;
        if (getDebugLogEnabled()) {
            z = ReactVirtualViewKt.IS_DEBUG_BUILD;
            if (z) {
                FLog.d("ReactVirtualView:onAttachedToWindow", " [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
            } else {
                FLog.w("ReactVirtualView:onAttachedToWindow", " [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
            }
        }
        if (this.detectWindowFocus) {
            getViewTreeObserver().addOnWindowFocusChangeListener(this.onWindowFocusChangeListener);
        }
        dispatchOnModeChangeIfNeeded(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ReactScrollViewHelper.removeScrollListener(this);
        ReactScrollViewHelper.removeLayoutChangeListener(this);
        if (this.detectWindowFocus) {
            getViewTreeObserver().addOnWindowFocusChangeListener(this.onWindowFocusChangeListener);
        }
        cleanupLayoutListeners();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            this.offsetChanged = true;
            dispatchOnModeChangeIfNeeded(false);
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        this.offsetChanged = (!this.offsetChanged && oldLeft == left && oldTop == top) ? false : true;
        dispatchOnModeChangeIfNeeded(true);
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.ScrollListener
    public void onScroll(ViewGroup scrollView, ScrollEventType scrollEventType, float xVelocity, float yVelocity) {
        if (Intrinsics.areEqual(scrollView, this.parentScrollView)) {
            dispatchOnModeChangeIfNeeded(false);
        }
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.ScrollListener
    public void onLayout(ViewGroup scrollView) {
        if (Intrinsics.areEqual(scrollView, this.parentScrollView)) {
            dispatchOnModeChangeIfNeeded(false);
        }
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.LayoutChangeListener
    public void onLayoutChange(ViewGroup scrollView) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        if (Intrinsics.areEqual(scrollView, this.parentScrollView)) {
            this.offsetChanged = true;
            dispatchOnModeChangeIfNeeded(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dispatchOnModeChangeIfNeeded(boolean checkRectChange) {
        View view;
        boolean z;
        VirtualViewMode virtualViewMode;
        boolean z2;
        boolean z3;
        boolean z4;
        if (this.modeChangeEmitter == null || (view = this.parentScrollView) == null) {
            return;
        }
        if (this.offsetChanged) {
            updateParentOffset();
        }
        this.targetRect.set(getLeft() + this.offsetX, getTop() + this.offsetY, getRight() + this.offsetX, getBottom() + this.offsetY);
        view.getDrawingRect(this.thresholdRect);
        if ((this.targetRect.width() != 0 || this.targetRect.height() != 0) && !this.thresholdRect.isEmpty()) {
            if (checkRectChange) {
                if (this.lastRect.isEmpty() || !Intrinsics.areEqual(this.lastRect, this.targetRect)) {
                    this.lastRect.set(this.targetRect);
                } else {
                    if (getDebugLogEnabled()) {
                        z3 = ReactVirtualViewKt.IS_DEBUG_BUILD;
                        if (z3) {
                            FLog.d("ReactVirtualView:dispatchOnModeChangeIfNeeded", "no rect change [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
                            return;
                        } else {
                            FLog.w("ReactVirtualView:dispatchOnModeChangeIfNeeded", "no rect change [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
                            return;
                        }
                    }
                    return;
                }
            }
            if (!rectsOverlap(this.targetRect, this.thresholdRect)) {
                if (this.prerenderRatio > 0.0d) {
                    this.thresholdRect.inset((int) ((-r12.width()) * this.prerenderRatio), (int) ((-this.thresholdRect.height()) * this.prerenderRatio));
                    z = rectsOverlap(this.targetRect, this.thresholdRect);
                } else {
                    z = false;
                }
                if (z) {
                    virtualViewMode = VirtualViewMode.Prerender;
                } else {
                    virtualViewMode = VirtualViewMode.Hidden;
                    this.thresholdRect.setEmpty();
                }
            } else if (this.detectWindowFocus) {
                if (hasWindowFocus()) {
                    virtualViewMode = VirtualViewMode.Visible;
                } else {
                    virtualViewMode = VirtualViewMode.Prerender;
                }
            } else {
                virtualViewMode = VirtualViewMode.Visible;
            }
            if (getDebugLogEnabled()) {
                z2 = ReactVirtualViewKt.IS_DEBUG_BUILD;
                if (z2) {
                    FLog.d("ReactVirtualView:dispatchOnModeChangeIfNeeded", ((Object) ("mode=" + this.mode + " target=" + this.targetRect.toShortString() + " threshold=" + this.thresholdRect.toShortString())) + " [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
                } else {
                    FLog.w("ReactVirtualView:dispatchOnModeChangeIfNeeded", ((Object) ("mode=" + this.mode + " target=" + this.targetRect.toShortString() + " threshold=" + this.thresholdRect.toShortString())) + " [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
                }
            }
            VirtualViewMode virtualViewMode2 = this.mode;
            if (virtualViewMode == virtualViewMode2) {
                return;
            }
            this.mode = virtualViewMode;
            maybeEmitModeChanges(virtualViewMode2, virtualViewMode);
            return;
        }
        if (getDebugLogEnabled()) {
            z4 = ReactVirtualViewKt.IS_DEBUG_BUILD;
            if (z4) {
                FLog.d("ReactVirtualView:dispatchOnModeChangeIfNeeded", ((Object) ("empty rects target=" + this.targetRect.toShortString() + " threshold=" + this.thresholdRect.toShortString())) + " [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
                return;
            }
            FLog.w("ReactVirtualView:dispatchOnModeChangeIfNeeded", ((Object) ("empty rects target=" + this.targetRect.toShortString() + " threshold=" + this.thresholdRect.toShortString())) + " [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
        }
    }

    private final boolean rectsOverlap(Rect rect1, Rect rect2) {
        return rect1.top < rect2.bottom && rect2.top < rect1.bottom && rect1.left < rect2.right && rect2.left < rect1.right;
    }

    private final void emitAsyncModeChange(VirtualViewMode mode) {
        ModeChangeEmitter modeChangeEmitter = this.modeChangeEmitter;
        if (modeChangeEmitter != null) {
            modeChangeEmitter.emitModeChange(mode, this.targetRect, this.thresholdRect, false);
        }
    }

    private final void emitSyncModeChange(VirtualViewMode mode) {
        ModeChangeEmitter modeChangeEmitter = this.modeChangeEmitter;
        if (modeChangeEmitter != null) {
            modeChangeEmitter.emitModeChange(mode, this.targetRect, this.thresholdRect, true);
        }
    }

    private final ViewGroup getParentScrollView() {
        return traverseParentStack(true);
    }

    private final void cleanupLayoutListeners() {
        traverseParentStack(false);
    }

    private final ViewGroup traverseParentStack(boolean addListeners) {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof ReactScrollView) {
                return (ViewGroup) parent;
            }
            if (parent instanceof ReactHorizontalScrollView) {
                return (ViewGroup) parent;
            }
            if (parent instanceof ReactRoot) {
                return null;
            }
            if (parent instanceof View) {
                View view = (View) parent;
                ReactVirtualView reactVirtualView = this;
                view.removeOnLayoutChangeListener(reactVirtualView);
                if (addListeners) {
                    view.addOnLayoutChangeListener(reactVirtualView);
                }
            }
        }
        return null;
    }

    private final void updateParentOffset() {
        View view = this.parentScrollView;
        if (view == null) {
            return;
        }
        this.offsetX = 0;
        this.offsetY = 0;
        this.offsetChanged = false;
        for (ViewParent parent = getParent(); parent != null && !Intrinsics.areEqual(parent, view); parent = parent.getParent()) {
            if (parent instanceof View) {
                View view2 = (View) parent;
                this.offsetX += view2.getLeft();
                this.offsetY += view2.getTop();
            }
        }
    }

    public static /* synthetic */ void debugLog$ReactAndroid_release$default(ReactVirtualView reactVirtualView, String subtag, Function0 block, int i, Object obj) {
        boolean z;
        if ((i & 2) != 0) {
            block = new Function0<String>() { // from class: com.facebook.react.views.virtualview.ReactVirtualView$debugLog$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "";
                }
            };
        }
        Intrinsics.checkNotNullParameter(subtag, "subtag");
        Intrinsics.checkNotNullParameter(block, "block");
        if (reactVirtualView.getDebugLogEnabled()) {
            z = ReactVirtualViewKt.IS_DEBUG_BUILD;
            if (z) {
                FLog.d("ReactVirtualView:" + subtag, block.invoke() + " [" + reactVirtualView.getId() + "][" + reactVirtualView.getNativeId$ReactAndroid_release() + "]");
                return;
            }
            FLog.w("ReactVirtualView:" + subtag, block.invoke() + " [" + reactVirtualView.getId() + "][" + reactVirtualView.getNativeId$ReactAndroid_release() + "]");
        }
    }

    public final void debugLog$ReactAndroid_release(String subtag, Function0<String> block) {
        boolean z;
        Intrinsics.checkNotNullParameter(subtag, "subtag");
        Intrinsics.checkNotNullParameter(block, "block");
        if (getDebugLogEnabled()) {
            z = ReactVirtualViewKt.IS_DEBUG_BUILD;
            if (z) {
                String invoke = block.invoke();
                FLog.d("ReactVirtualView:" + subtag, ((Object) invoke) + " [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
                return;
            }
            String invoke2 = block.invoke();
            FLog.w("ReactVirtualView:" + subtag, ((Object) invoke2) + " [" + getId() + "][" + getNativeId$ReactAndroid_release() + "]");
        }
    }

    private final void maybeEmitModeChanges(VirtualViewMode oldMode, VirtualViewMode newMode) {
        boolean z;
        if (getDebugLogEnabled()) {
            z = ReactVirtualViewKt.IS_DEBUG_BUILD;
            if (z) {
                int id = getId();
                FLog.d("ReactVirtualView:Mode change", ((Object) (oldMode + "->" + newMode)) + " [" + id + "][" + getNativeId$ReactAndroid_release() + "]");
            } else {
                int id2 = getId();
                FLog.w("ReactVirtualView:Mode change", ((Object) (oldMode + "->" + newMode)) + " [" + id2 + "][" + getNativeId$ReactAndroid_release() + "]");
            }
        }
        Systrace.beginSection(0L, "VirtualView::mode change " + oldMode + " -> " + newMode + ", nativeID=" + getNativeId$ReactAndroid_release());
        int i = WhenMappings.$EnumSwitchMapping$0[newMode.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                emitAsyncModeChange(VirtualViewMode.Hidden);
            } else if (oldMode != VirtualViewMode.Visible) {
                emitAsyncModeChange(VirtualViewMode.Prerender);
            }
        } else if (this.renderState == VirtualViewRenderState.Unknown) {
            emitSyncModeChange(VirtualViewMode.Visible);
        } else if (oldMode != VirtualViewMode.Prerender || this.renderState != VirtualViewRenderState.Rendered) {
            emitSyncModeChange(VirtualViewMode.Visible);
        }
        Systrace.endSection(0L);
    }
}
