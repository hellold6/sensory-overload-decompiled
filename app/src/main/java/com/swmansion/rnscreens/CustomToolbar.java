package com.swmansion.rnscreens;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Choreographer;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.rnscreens.utils.InsetsKtKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomToolbar.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0016J0\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001eH\u0014J\u0006\u0010\"\u001a\u00020\u0017J(\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u001eH\u0002J\b\u0010(\u001a\u00020\u0017H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/swmansion/rnscreens/CustomToolbar;", "Landroidx/appcompat/widget/Toolbar;", "context", "Landroid/content/Context;", "config", "Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "<init>", "(Landroid/content/Context;Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;)V", "getConfig", "()Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "shouldAvoidDisplayCutout", "", "getShouldAvoidDisplayCutout", "()Z", "shouldApplyTopInset", "getShouldApplyTopInset", "lastInsets", "Landroidx/core/graphics/Insets;", "isForceShadowStateUpdateOnLayoutRequested", "isLayoutEnqueued", "layoutCallback", "Landroid/view/Choreographer$FrameCallback;", "requestLayout", "", "onApplyWindowInsets", "Landroid/view/WindowInsets;", "insets", "onLayout", "hasSizeChanged", CmcdData.STREAM_TYPE_LIVE, "", "t", "r", "b", "updateContentInsets", "applyExactPadding", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "requestForceShadowStateUpdateOnLayout", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class CustomToolbar extends Toolbar {
    private final ScreenStackHeaderConfig config;
    private boolean isForceShadowStateUpdateOnLayoutRequested;
    private boolean isLayoutEnqueued;
    private Insets lastInsets;
    private final Choreographer.FrameCallback layoutCallback;

    public final ScreenStackHeaderConfig getConfig() {
        return this.config;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomToolbar(Context context, ScreenStackHeaderConfig config) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        Insets NONE = Insets.NONE;
        Intrinsics.checkNotNullExpressionValue(NONE, "NONE");
        this.lastInsets = NONE;
        this.layoutCallback = new Choreographer.FrameCallback() { // from class: com.swmansion.rnscreens.CustomToolbar$layoutCallback$1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long frameTimeNanos) {
                CustomToolbar.this.isLayoutEnqueued = false;
                CustomToolbar customToolbar = CustomToolbar.this;
                customToolbar.measure(View.MeasureSpec.makeMeasureSpec(customToolbar.getWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(CustomToolbar.this.getHeight(), Integer.MIN_VALUE));
                CustomToolbar customToolbar2 = CustomToolbar.this;
                customToolbar2.layout(customToolbar2.getLeft(), CustomToolbar.this.getTop(), CustomToolbar.this.getRight(), CustomToolbar.this.getBottom());
            }
        };
    }

    private final boolean getShouldAvoidDisplayCutout() {
        return this.config.getIsTopInsetEnabled();
    }

    private final boolean getShouldApplyTopInset() {
        return this.config.getIsTopInsetEnabled();
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        Window window;
        WindowManager.LayoutParams attributes;
        super.requestLayout();
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        Activity currentActivity = ((ThemedReactContext) context).getCurrentActivity();
        Integer valueOf = (currentActivity == null || (window = currentActivity.getWindow()) == null || (attributes = window.getAttributes()) == null) ? null : Integer.valueOf(attributes.softInputMode);
        if (Build.VERSION.SDK_INT > 29 || valueOf == null || valueOf.intValue() != 32 || this.isLayoutEnqueued || this.layoutCallback == null) {
            return;
        }
        this.isLayoutEnqueued = true;
        ReactChoreographer.INSTANCE.getInstance().postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.layoutCallback);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(insets);
        WindowInsets rootWindowInsets = getRootWindowInsets();
        CustomToolbar customToolbar = this;
        Insets resolveInsetsOrZero$default = InsetsKtKt.resolveInsetsOrZero$default(customToolbar, WindowInsetsCompat.Type.displayCutout(), rootWindowInsets, false, 4, null);
        Insets resolveInsetsOrZero$default2 = InsetsKtKt.resolveInsetsOrZero$default(customToolbar, WindowInsetsCompat.Type.systemBars(), rootWindowInsets, false, 4, null);
        Insets resolveInsetsOrZero = InsetsKtKt.resolveInsetsOrZero(customToolbar, WindowInsetsCompat.Type.systemBars(), rootWindowInsets, true);
        Insets of = Insets.of(resolveInsetsOrZero$default.left + resolveInsetsOrZero$default2.left, 0, resolveInsetsOrZero$default.right + resolveInsetsOrZero$default2.right, 0);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        Insets of2 = Insets.of(0, Math.max(resolveInsetsOrZero$default.top, getShouldApplyTopInset() ? resolveInsetsOrZero.top : 0), 0, Math.max(resolveInsetsOrZero$default.bottom, 0));
        Intrinsics.checkNotNullExpressionValue(of2, "of(...)");
        Insets add = Insets.add(of, of2);
        Intrinsics.checkNotNullExpressionValue(add, "add(...)");
        if (!Intrinsics.areEqual(this.lastInsets, add)) {
            this.lastInsets = add;
            applyExactPadding(add.left, this.lastInsets.top, this.lastInsets.right, this.lastInsets.bottom);
        }
        return onApplyWindowInsets;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean hasSizeChanged, int l, int t, int r, int b) {
        super.onLayout(hasSizeChanged, l, t, r, b);
        this.config.onNativeToolbarLayout(this, hasSizeChanged || this.isForceShadowStateUpdateOnLayoutRequested);
        this.isForceShadowStateUpdateOnLayoutRequested = false;
    }

    public final void updateContentInsets() {
        setContentInsetStartWithNavigation(this.config.getPreferredContentInsetStartWithNavigation());
        setContentInsetsRelative(this.config.getPreferredContentInsetStart(), this.config.getDefaultStartInset());
    }

    private final void applyExactPadding(int left, int top, int right, int bottom) {
        requestForceShadowStateUpdateOnLayout();
        setPadding(left, top, right, bottom);
    }

    private final void requestForceShadowStateUpdateOnLayout() {
        this.isForceShadowStateUpdateOnLayoutRequested = getShouldAvoidDisplayCutout();
    }
}
