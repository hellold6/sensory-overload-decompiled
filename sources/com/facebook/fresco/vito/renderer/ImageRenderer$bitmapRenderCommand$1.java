package com.facebook.fresco.vito.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageRenderer.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
/* loaded from: classes2.dex */
public final class ImageRenderer$bitmapRenderCommand$1 implements Function1<Canvas, Unit> {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ Matrix $imageTransformation;
    final /* synthetic */ Paint $paint;

    public ImageRenderer$bitmapRenderCommand$1(Matrix matrix, Bitmap bitmap, Paint paint) {
        this.$imageTransformation = matrix;
        this.$bitmap = bitmap;
        this.$paint = paint;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Canvas canvas) {
        invoke2(canvas);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.concat(this.$imageTransformation);
        canvas.drawBitmap(this.$bitmap, 0.0f, 0.0f, this.$paint);
    }
}
