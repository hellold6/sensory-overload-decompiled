package com.facebook.react.views.text;

import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan;
import com.facebook.yoga.YogaBaselineFunction;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactTextShadowNode.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 (2\u00020\u0001:\u0001(B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0002J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\tH\u0016J\b\u0010\u001f\u001a\u00020\tH\u0016J\b\u0010 \u001a\u00020\u000fH\u0016J\u0010\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\tH\u0007J\u0016\u0010%\u001a\u0010\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010'\u0018\u00010&H\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006)"}, d2 = {"Lcom/facebook/react/views/text/ReactTextShadowNode;", "Lcom/facebook/react/views/text/ReactBaseTextShadowNode;", "reactTextViewManagerCallback", "Lcom/facebook/react/views/text/ReactTextViewManagerCallback;", "<init>", "(Lcom/facebook/react/views/text/ReactTextViewManagerCallback;)V", "preparedSpannableText", "Landroid/text/Spannable;", "shouldNotifyOnTextLayout", "", "textMeasureFunction", "Lcom/facebook/yoga/YogaMeasureFunction;", "mTextBaselineFunction", "Lcom/facebook/yoga/YogaBaselineFunction;", "initMeasureFunction", "", "measureSpannedText", "Landroid/text/Layout;", "text", "width", "", "widthMode", "Lcom/facebook/yoga/YogaMeasureMode;", "textAlign", "", "getTextAlign", "()I", "onBeforeLayout", "nativeViewHierarchyOptimizer", "Lcom/facebook/react/uimanager/NativeViewHierarchyOptimizer;", "isVirtualAnchor", "hoistNativeChildren", "markUpdated", "onCollectExtraUpdates", "uiViewOperationQueue", "Lcom/facebook/react/uimanager/UIViewOperationQueue;", "setShouldNotifyOnTextLayout", "calculateLayoutOnChildren", "", "Lcom/facebook/react/uimanager/ReactShadowNode;", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ReactTextShadowNode extends ReactBaseTextShadowNode {
    private static final Companion Companion = new Companion(null);
    private static final TextPaint textPaintInstance = new TextPaint(1);
    private final YogaBaselineFunction mTextBaselineFunction;
    private Spannable preparedSpannableText;
    private boolean shouldNotifyOnTextLayout;
    private final YogaMeasureFunction textMeasureFunction;

    /* JADX WARN: Multi-variable type inference failed */
    public ReactTextShadowNode() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean hoistNativeChildren() {
        return true;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtualAnchor() {
        return false;
    }

    public /* synthetic */ ReactTextShadowNode(ReactTextViewManagerCallback reactTextViewManagerCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : reactTextViewManagerCallback);
    }

    public ReactTextShadowNode(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        super(reactTextViewManagerCallback);
        this.textMeasureFunction = new YogaMeasureFunction() { // from class: com.facebook.react.views.text.ReactTextShadowNode$$ExternalSyntheticLambda0
            @Override // com.facebook.yoga.YogaMeasureFunction
            public final long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
                long textMeasureFunction$lambda$2;
                textMeasureFunction$lambda$2 = ReactTextShadowNode.textMeasureFunction$lambda$2(ReactTextShadowNode.this, yogaNode, f, yogaMeasureMode, f2, yogaMeasureMode2);
                return textMeasureFunction$lambda$2;
            }
        };
        this.mTextBaselineFunction = new YogaBaselineFunction() { // from class: com.facebook.react.views.text.ReactTextShadowNode$$ExternalSyntheticLambda1
            @Override // com.facebook.yoga.YogaBaselineFunction
            public final float baseline(YogaNode yogaNode, float f, float f2) {
                float mTextBaselineFunction$lambda$4;
                mTextBaselineFunction$lambda$4 = ReactTextShadowNode.mTextBaselineFunction$lambda$4(ReactTextShadowNode.this, yogaNode, f, f2);
                return mTextBaselineFunction$lambda$4;
            }
        };
        initMeasureFunction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x016d, code lost:
    
        if (r0 > r21) goto L61;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long textMeasureFunction$lambda$2(com.facebook.react.views.text.ReactTextShadowNode r17, com.facebook.yoga.YogaNode r18, float r19, com.facebook.yoga.YogaMeasureMode r20, float r21, com.facebook.yoga.YogaMeasureMode r22) {
        /*
            Method dump skipped, instructions count: 386
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextShadowNode.textMeasureFunction$lambda$2(com.facebook.react.views.text.ReactTextShadowNode, com.facebook.yoga.YogaNode, float, com.facebook.yoga.YogaMeasureMode, float, com.facebook.yoga.YogaMeasureMode):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float mTextBaselineFunction$lambda$4(ReactTextShadowNode reactTextShadowNode, YogaNode yogaNode, float f, float f2) {
        Spannable spannable = reactTextShadowNode.preparedSpannableText;
        if (spannable == null) {
            throw new IllegalStateException("Spannable element has not been prepared in onBeforeLayout".toString());
        }
        Layout measureSpannedText = reactTextShadowNode.measureSpannedText(spannable, f, YogaMeasureMode.EXACTLY);
        return measureSpannedText.getLineBaseline(measureSpannedText.getLineCount() - 1);
    }

    private final void initMeasureFunction() {
        if (isVirtual()) {
            return;
        }
        setMeasureFunction(this.textMeasureFunction);
        setBaselineFunction(this.mTextBaselineFunction);
    }

    private final Layout measureSpannedText(Spannable text, float width, YogaMeasureMode widthMode) {
        Layout.Alignment alignment;
        TextPaint textPaint = textPaintInstance;
        textPaint.setTextSize(this.mTextAttributes.getEffectiveFontSize());
        float f = width;
        Spannable spannable = text;
        BoringLayout.Metrics isBoring = BoringLayout.isBoring(spannable, textPaint);
        float desiredWidth = isBoring == null ? Layout.getDesiredWidth(spannable, textPaint) : Float.NaN;
        boolean z = widthMode == YogaMeasureMode.UNDEFINED || f < 0.0f;
        int textAlign = getTextAlign();
        if (textAlign == 1) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        } else if (textAlign == 3) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        } else if (textAlign == 5) {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        } else {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        }
        if (isBoring == null && (z || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= f))) {
            StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(spannable, 0, text.length(), textPaint, (int) Math.ceil(desiredWidth)).setAlignment(alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(this.mIncludeFontPadding).setBreakStrategy(this.mTextBreakStrategy).setHyphenationFrequency(this.mHyphenationFrequency);
            Intrinsics.checkNotNullExpressionValue(hyphenationFrequency, "setHyphenationFrequency(...)");
            if (Build.VERSION.SDK_INT >= 26) {
                hyphenationFrequency.setJustificationMode(this.mJustificationMode);
            }
            if (Build.VERSION.SDK_INT >= 28) {
                hyphenationFrequency.setUseLineSpacingFromFallbacks(true);
            }
            StaticLayout build = hyphenationFrequency.build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            return build;
        }
        if (isBoring != null && (z || isBoring.width <= f)) {
            BoringLayout make = BoringLayout.make(spannable, textPaint, (int) Math.max(isBoring.width, 0.0d), alignment, 1.0f, 0.0f, isBoring, this.mIncludeFontPadding);
            Intrinsics.checkNotNullExpressionValue(make, "make(...)");
            return make;
        }
        Layout.Alignment alignment2 = alignment;
        if (Build.VERSION.SDK_INT > 29) {
            f = (float) Math.ceil(f);
        }
        StaticLayout.Builder hyphenationFrequency2 = StaticLayout.Builder.obtain(spannable, 0, text.length(), textPaint, (int) f).setAlignment(alignment2).setLineSpacing(0.0f, 1.0f).setIncludePad(this.mIncludeFontPadding).setBreakStrategy(this.mTextBreakStrategy).setHyphenationFrequency(this.mHyphenationFrequency);
        Intrinsics.checkNotNullExpressionValue(hyphenationFrequency2, "setHyphenationFrequency(...)");
        if (Build.VERSION.SDK_INT >= 26) {
            hyphenationFrequency2.setJustificationMode(this.mJustificationMode);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            hyphenationFrequency2.setUseLineSpacingFromFallbacks(true);
        }
        StaticLayout build2 = hyphenationFrequency2.build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        return build2;
    }

    private final int getTextAlign() {
        int i = this.mTextAlign;
        if (getLayoutDirection() == YogaDirection.RTL) {
            if (i == 3) {
                return 5;
            }
            if (i == 5) {
                return 3;
            }
        }
        return i;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onBeforeLayout(NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        Intrinsics.checkNotNullParameter(nativeViewHierarchyOptimizer, "nativeViewHierarchyOptimizer");
        this.preparedSpannableText = spannedFromShadowNode(this, null, true, nativeViewHierarchyOptimizer);
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void markUpdated() {
        super.markUpdated();
        super.dirty();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        Intrinsics.checkNotNullParameter(uiViewOperationQueue, "uiViewOperationQueue");
        super.onCollectExtraUpdates(uiViewOperationQueue);
        Spannable spannable = this.preparedSpannableText;
        if (spannable == null) {
            return;
        }
        uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(spannable, -1, this.mContainsImages, getPadding(4), getPadding(1), getPadding(5), getPadding(3), getTextAlign(), this.mTextBreakStrategy, this.mJustificationMode));
    }

    @ReactProp(name = "onTextLayout")
    public final void setShouldNotifyOnTextLayout(boolean shouldNotifyOnTextLayout) {
        this.shouldNotifyOnTextLayout = shouldNotifyOnTextLayout;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public Iterable<ReactShadowNode<?>> calculateLayoutOnChildren() {
        Map<Integer, ReactShadowNode> map = this.mInlineViews;
        if (map == null || map.isEmpty()) {
            return null;
        }
        Spannable spannable = this.preparedSpannableText;
        if (spannable == null) {
            throw new IllegalStateException("Spannable element has not been prepared in onBeforeLayout".toString());
        }
        Spannable spannable2 = spannable;
        TextInlineViewPlaceholderSpan[] textInlineViewPlaceholderSpanArr = (TextInlineViewPlaceholderSpan[]) spannable2.getSpans(0, spannable2.length(), TextInlineViewPlaceholderSpan.class);
        ArrayList arrayList = new ArrayList();
        Iterator it = ArrayIteratorKt.iterator(textInlineViewPlaceholderSpanArr);
        while (it.hasNext()) {
            TextInlineViewPlaceholderSpan textInlineViewPlaceholderSpan = (TextInlineViewPlaceholderSpan) it.next();
            Map<Integer, ReactShadowNode> map2 = this.mInlineViews;
            ReactShadowNode reactShadowNode = map2 != null ? map2.get(Integer.valueOf(textInlineViewPlaceholderSpan.getReactTag())) : null;
            if (reactShadowNode == null) {
                throw new IllegalStateException("Child is null".toString());
            }
            reactShadowNode.calculateLayout();
            arrayList.add(reactShadowNode);
        }
        return arrayList;
    }

    /* compiled from: ReactTextShadowNode.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/facebook/react/views/text/ReactTextShadowNode$Companion;", "", "<init>", "()V", "textPaintInstance", "Landroid/text/TextPaint;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
