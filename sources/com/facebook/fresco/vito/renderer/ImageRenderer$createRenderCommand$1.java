package com.facebook.fresco.vito.renderer;

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
public final class ImageRenderer$createRenderCommand$1 implements Function1<Canvas, Unit> {
    final /* synthetic */ Matrix $imageTransformation;
    final /* synthetic */ Paint $paint;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ DrawableImageDataModel $this_createRenderCommand;

    public ImageRenderer$createRenderCommand$1(DrawableImageDataModel drawableImageDataModel, Matrix matrix, Shape shape, Paint paint) {
        this.$this_createRenderCommand = drawableImageDataModel;
        this.$imageTransformation = matrix;
        this.$shape = shape;
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
        if (this.$this_createRenderCommand.getWidth() > 0 && this.$this_createRenderCommand.getHeight() > 0) {
            this.$this_createRenderCommand.getDrawable().setBounds(0, 0, this.$this_createRenderCommand.getWidth(), this.$this_createRenderCommand.getHeight());
            canvas.concat(this.$imageTransformation);
        } else {
            this.$this_createRenderCommand.getDrawable().setBounds((int) ((RectShape) this.$shape).getRect().left, (int) ((RectShape) this.$shape).getRect().top, (int) ((RectShape) this.$shape).getRect().right, (int) ((RectShape) this.$shape).getRect().bottom);
        }
        if (!Intrinsics.areEqual(this.$this_createRenderCommand.getDrawable().getColorFilter(), this.$paint.getColorFilter())) {
            this.$this_createRenderCommand.getDrawable().setColorFilter(this.$paint.getColorFilter());
        }
        this.$this_createRenderCommand.getDrawable().setAlpha(this.$paint.getAlpha());
        this.$this_createRenderCommand.getDrawable().draw(canvas);
    }
}
