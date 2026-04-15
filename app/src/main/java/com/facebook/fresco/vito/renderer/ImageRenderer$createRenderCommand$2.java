package com.facebook.fresco.vito.renderer;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageRenderer.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
/* loaded from: classes2.dex */
public final class ImageRenderer$createRenderCommand$2 implements Function1<Canvas, Unit> {
    final /* synthetic */ Matrix $imageTransformation;
    final /* synthetic */ Paint $paint;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ DrawableImageDataModel $this_createRenderCommand;

    public ImageRenderer$createRenderCommand$2(DrawableImageDataModel drawableImageDataModel, Paint paint, Matrix matrix, Shape shape) {
        this.$this_createRenderCommand = drawableImageDataModel;
        this.$paint = paint;
        this.$imageTransformation = matrix;
        this.$shape = shape;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Canvas canvas) {
        invoke2(canvas);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.$this_createRenderCommand.getDrawable().setBounds(0, 0, this.$this_createRenderCommand.getWidth(), this.$this_createRenderCommand.getHeight());
        if (this.$this_createRenderCommand.getDrawable().getColorFilter() != null) {
            this.$this_createRenderCommand.getDrawable().setColorFilter(null);
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.$this_createRenderCommand.getWidth(), this.$this_createRenderCommand.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        this.$this_createRenderCommand.getDrawable().draw(new Canvas(createBitmap));
        ImageRenderer imageRenderer = ImageRenderer.INSTANCE;
        Paint paint = this.$paint;
        Matrix matrix = this.$imageTransformation;
        paint.setShader(new BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.getShader().setLocalMatrix(matrix);
        this.$shape.draw(canvas, this.$paint);
    }
}
