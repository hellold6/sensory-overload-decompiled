package com.facebook.react.views.text.frescosupport;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogLevel;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogger;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.internal.ReactTextInlineImageShadowNode;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: FrescoBasedReactTextInlineImageShadowNode.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 +2\u00020\u0001:\u0001+B+\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u0012\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\fH\u0007J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0013H\u0007J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001eH\u0016J\u0012\u0010!\u001a\u00020\u00152\b\u0010\"\u001a\u0004\u0018\u00010\u0010H\u0007J\b\u0010#\u001a\u0004\u0018\u00010\nJ\b\u0010$\u001a\u0004\u0018\u00010\fJ\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020(H\u0016J\u0018\u0010)\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003J\b\u0010*\u001a\u0004\u0018\u00010\u0006R \u0010\u0002\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageShadowNode;", "Lcom/facebook/react/views/text/internal/ReactTextInlineImageShadowNode;", "draweeControllerBuilder", "Lcom/facebook/drawee/controller/AbstractDraweeControllerBuilder;", "Lcom/facebook/imagepipeline/request/ImageRequest;", "callerContext", "", "<init>", "(Lcom/facebook/drawee/controller/AbstractDraweeControllerBuilder;Ljava/lang/Object;)V", "uri", "Landroid/net/Uri;", "headers", "Lcom/facebook/react/bridge/ReadableMap;", "width", "", ViewProps.RESIZE_MODE, "", "height", "tintColor", "", "setSource", "", "sources", "Lcom/facebook/react/bridge/ReadableArray;", "setHeaders", "newHeaders", "setTintColor", "newTintColor", "setWidth", "newWidth", "Lcom/facebook/react/bridge/Dynamic;", "setHeight", "newHeight", "setResizeMode", "newResizeMode", "getUri", "getHeaders", "isVirtual", "", "buildInlineImageSpan", "Lcom/facebook/react/views/text/internal/span/TextInlineImageSpan;", "getDraweeControllerBuilder", "getCallerContext", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FrescoBasedReactTextInlineImageShadowNode extends ReactTextInlineImageShadowNode {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Object callerContext;
    private final AbstractDraweeControllerBuilder<?, ImageRequest, ?, ?> draweeControllerBuilder;
    private ReadableMap headers;
    private float height;
    private String resizeMode;
    private int tintColor;
    private Uri uri;
    private float width;

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    public FrescoBasedReactTextInlineImageShadowNode(AbstractDraweeControllerBuilder<?, ImageRequest, ?, ?> draweeControllerBuilder, Object obj) {
        Intrinsics.checkNotNullParameter(draweeControllerBuilder, "draweeControllerBuilder");
        this.draweeControllerBuilder = draweeControllerBuilder;
        this.callerContext = obj;
        this.width = Float.NaN;
        this.height = Float.NaN;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0037, code lost:
    
        if (r1.getScheme() == null) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003d  */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "src")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setSource(com.facebook.react.bridge.ReadableArray r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L2c
            int r1 = r5.size()
            if (r1 == 0) goto L2c
            r1 = 0
            com.facebook.react.bridge.ReadableType r2 = r5.getType(r1)
            com.facebook.react.bridge.ReadableType r3 = com.facebook.react.bridge.ReadableType.Map
            if (r2 == r3) goto L13
            goto L2c
        L13:
            com.facebook.react.bridge.ReadableMap r5 = r5.getMap(r1)
            if (r5 == 0) goto L20
            java.lang.String r1 = "uri"
            java.lang.String r5 = r5.getString(r1)
            goto L2d
        L20:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Required value was null."
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L2c:
            r5 = r0
        L2d:
            if (r5 == 0) goto L4e
            android.net.Uri r1 = android.net.Uri.parse(r5)     // Catch: java.lang.Exception -> L3b
            java.lang.String r2 = r1.getScheme()     // Catch: java.lang.Exception -> L3a
            if (r2 != 0) goto L3a
            goto L3b
        L3a:
            r0 = r1
        L3b:
            if (r0 != 0) goto L4e
            com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageShadowNode$Companion r0 = com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageShadowNode.INSTANCE
            com.facebook.react.uimanager.ThemedReactContext r1 = r4.getThemedContext()
            java.lang.String r2 = "getThemedContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            android.content.Context r1 = (android.content.Context) r1
            android.net.Uri r0 = r0.getResourceDrawableUri(r1, r5)
        L4e:
            android.net.Uri r5 = r4.uri
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r5)
            if (r5 != 0) goto L59
            r4.markUpdated()
        L59:
            r4.uri = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageShadowNode.setSource(com.facebook.react.bridge.ReadableArray):void");
    }

    @ReactProp(name = "headers")
    public final void setHeaders(ReadableMap newHeaders) {
        this.headers = newHeaders;
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public final void setTintColor(int newTintColor) {
        this.tintColor = newTintColor;
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setWidth(Dynamic newWidth) {
        Intrinsics.checkNotNullParameter(newWidth, "newWidth");
        if (newWidth.getType() == ReadableType.Number) {
            this.width = (float) newWidth.asDouble();
        } else {
            FLog.w(ReactConstants.TAG, "Inline images must not have percentage based width");
            this.width = Float.NaN;
        }
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setHeight(Dynamic newHeight) {
        Intrinsics.checkNotNullParameter(newHeight, "newHeight");
        if (newHeight.getType() == ReadableType.Number) {
            this.height = (float) newHeight.asDouble();
        } else {
            FLog.w(ReactConstants.TAG, "Inline images must not have percentage based height");
            this.height = Float.NaN;
        }
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public final void setResizeMode(String newResizeMode) {
        this.resizeMode = newResizeMode;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final ReadableMap getHeaders() {
        return this.headers;
    }

    @Override // com.facebook.react.views.text.internal.ReactTextInlineImageShadowNode
    public TextInlineImageSpan buildInlineImageSpan() {
        Resources resources = getThemedContext().getResources();
        int ceil = (int) Math.ceil(this.width);
        int ceil2 = (int) Math.ceil(this.height);
        Intrinsics.checkNotNull(resources);
        return new FrescoBasedReactTextInlineImageSpan(resources, ceil2, ceil, this.tintColor, getUri(), getHeaders(), getDraweeControllerBuilder(), getCallerContext(), this.resizeMode);
    }

    public final AbstractDraweeControllerBuilder<?, ImageRequest, ?, ?> getDraweeControllerBuilder() {
        return this.draweeControllerBuilder;
    }

    public final Object getCallerContext() {
        return this.callerContext;
    }

    /* compiled from: FrescoBasedReactTextInlineImageShadowNode.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lcom/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageShadowNode$Companion;", "", "<init>", "()V", "getResourceDrawableUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "name", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Uri getResourceDrawableUri(Context context, String name) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (name == null || name.length() == 0) {
                return null;
            }
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String lowerCase = name.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(context.getResources().getIdentifier(StringsKt.replace$default(lowerCase, "-", "_", false, 4, (Object) null), "drawable", context.getPackageName()))).build();
        }
    }

    static {
        LegacyArchitectureLogger.assertLegacyArchitecture("FrescoBasedReactTextInlineImageShadowNode", LegacyArchitectureLogLevel.ERROR);
    }
}
