package com.facebook.fresco.vito.renderer;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import com.facebook.fresco.vito.renderer.util.ColorUtils;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageRenderer.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J?\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u0002`\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J>\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u0002`\u0005*\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086\b¢\u0006\u0002\u0010\u0014J2\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u0002`\u0005*\u00020\u00152\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086\b¢\u0006\u0002\u0010\u0016J>\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u0002`\u0005*\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086\b¢\u0006\u0002\u0010\u0018J8\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u0002`\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086\b¢\u0006\u0002\u0010\u001cJ.\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u0002`\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086\b¢\u0006\u0002\u0010\u001eJ!\u0010\u001f\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0010H\u0086\b¨\u0006!"}, d2 = {"Lcom/facebook/fresco/vito/renderer/ImageRenderer;", "", "<init>", "()V", "createImageDataModelRenderCommand", "Lcom/facebook/fresco/vito/renderer/RenderCommand;", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "", "model", "Lcom/facebook/fresco/vito/renderer/ImageDataModel;", "shape", "Lcom/facebook/fresco/vito/renderer/Shape;", "paint", "Landroid/graphics/Paint;", "imageTransformation", "Landroid/graphics/Matrix;", "(Lcom/facebook/fresco/vito/renderer/ImageDataModel;Lcom/facebook/fresco/vito/renderer/Shape;Landroid/graphics/Paint;Landroid/graphics/Matrix;)Lkotlin/jvm/functions/Function1;", "createRenderCommand", "Lcom/facebook/fresco/vito/renderer/BitmapImageDataModel;", "(Lcom/facebook/fresco/vito/renderer/BitmapImageDataModel;Lcom/facebook/fresco/vito/renderer/Shape;Landroid/graphics/Paint;Landroid/graphics/Matrix;)Lkotlin/jvm/functions/Function1;", "Lcom/facebook/fresco/vito/renderer/ColorIntImageDataModel;", "(Lcom/facebook/fresco/vito/renderer/ColorIntImageDataModel;Lcom/facebook/fresco/vito/renderer/Shape;Landroid/graphics/Paint;)Lkotlin/jvm/functions/Function1;", "Lcom/facebook/fresco/vito/renderer/DrawableImageDataModel;", "(Lcom/facebook/fresco/vito/renderer/DrawableImageDataModel;Lcom/facebook/fresco/vito/renderer/Shape;Landroid/graphics/Paint;Landroid/graphics/Matrix;)Lkotlin/jvm/functions/Function1;", "bitmapRenderCommand", "bitmap", "Landroid/graphics/Bitmap;", "(Landroid/graphics/Paint;Landroid/graphics/Bitmap;Landroid/graphics/Matrix;)Lkotlin/jvm/functions/Function1;", "paintRenderCommand", "(Lcom/facebook/fresco/vito/renderer/Shape;Landroid/graphics/Paint;)Lkotlin/jvm/functions/Function1;", "setBitmap", "shaderTransformation", "renderer_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageRenderer {
    public static final ImageRenderer INSTANCE = new ImageRenderer();

    private ImageRenderer() {
    }

    public static /* synthetic */ Function1 createImageDataModelRenderCommand$default(ImageRenderer imageRenderer, ImageDataModel imageDataModel, Shape shape, Paint paint, Matrix matrix, int i, Object obj) {
        if ((i & 8) != 0) {
            matrix = null;
        }
        return imageRenderer.createImageDataModelRenderCommand(imageDataModel, shape, paint, matrix);
    }

    public final Function1<Canvas, Unit> createImageDataModelRenderCommand(ImageDataModel model, Shape shape, Paint paint, Matrix imageTransformation) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        if (!(model instanceof BitmapImageDataModel)) {
            if (!(model instanceof ColorIntImageDataModel)) {
                if (!(model instanceof DrawableImageDataModel)) {
                    throw new NoWhenBranchMatchedException();
                }
                DrawableImageDataModel drawableImageDataModel = (DrawableImageDataModel) model;
                return shape instanceof RectShape ? new ImageRenderer$createRenderCommand$1(drawableImageDataModel, imageTransformation, shape, paint) : new ImageRenderer$createRenderCommand$2(drawableImageDataModel, paint, imageTransformation, shape);
            }
            paint.setColor(ColorUtils.INSTANCE.multiplyColorAlpha(((ColorIntImageDataModel) model).getColorInt(), paint.getAlpha()));
            return new ImageRenderer$paintRenderCommand$1(shape, paint);
        }
        BitmapImageDataModel bitmapImageDataModel = (BitmapImageDataModel) model;
        if (shape instanceof RectShape) {
            return new ImageRenderer$bitmapRenderCommand$1(imageTransformation, bitmapImageDataModel.getBitmap(), paint);
        }
        if (shape instanceof CircleShape) {
            if (!bitmapImageDataModel.getIsBitmapCircular()) {
                paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                paint.getShader().setLocalMatrix(imageTransformation);
                return new ImageRenderer$paintRenderCommand$1(shape, paint);
            }
            return new ImageRenderer$bitmapRenderCommand$1(imageTransformation, bitmapImageDataModel.getBitmap(), paint);
        }
        paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.getShader().setLocalMatrix(imageTransformation);
        return new ImageRenderer$paintRenderCommand$1(shape, paint);
    }

    public static /* synthetic */ Function1 createRenderCommand$default(ImageRenderer imageRenderer, BitmapImageDataModel bitmapImageDataModel, Shape shape, Paint paint, Matrix matrix, int i, Object obj) {
        if ((i & 4) != 0) {
            matrix = null;
        }
        Intrinsics.checkNotNullParameter(bitmapImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        if (!(shape instanceof RectShape)) {
            if (shape instanceof CircleShape) {
                if (!bitmapImageDataModel.getIsBitmapCircular()) {
                    paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                    paint.getShader().setLocalMatrix(matrix);
                    return new ImageRenderer$paintRenderCommand$1(shape, paint);
                }
                return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
            }
            paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            paint.getShader().setLocalMatrix(matrix);
            return new ImageRenderer$paintRenderCommand$1(shape, paint);
        }
        return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
    }

    public final Function1<Canvas, Unit> createRenderCommand(BitmapImageDataModel bitmapImageDataModel, Shape shape, Paint paint, Matrix matrix) {
        Intrinsics.checkNotNullParameter(bitmapImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        if (!(shape instanceof RectShape)) {
            if (shape instanceof CircleShape) {
                if (!bitmapImageDataModel.getIsBitmapCircular()) {
                    paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                    paint.getShader().setLocalMatrix(matrix);
                    return new ImageRenderer$paintRenderCommand$1(shape, paint);
                }
                return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
            }
            paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            paint.getShader().setLocalMatrix(matrix);
            return new ImageRenderer$paintRenderCommand$1(shape, paint);
        }
        return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
    }

    public final Function1<Canvas, Unit> createRenderCommand(ColorIntImageDataModel colorIntImageDataModel, Shape shape, Paint paint) {
        Intrinsics.checkNotNullParameter(colorIntImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        paint.setColor(ColorUtils.INSTANCE.multiplyColorAlpha(colorIntImageDataModel.getColorInt(), paint.getAlpha()));
        return new ImageRenderer$paintRenderCommand$1(shape, paint);
    }

    public static /* synthetic */ Function1 createRenderCommand$default(ImageRenderer imageRenderer, DrawableImageDataModel drawableImageDataModel, Shape shape, Paint paint, Matrix matrix, int i, Object obj) {
        if ((i & 4) != 0) {
            matrix = null;
        }
        Intrinsics.checkNotNullParameter(drawableImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        return shape instanceof RectShape ? new ImageRenderer$createRenderCommand$1(drawableImageDataModel, matrix, shape, paint) : new ImageRenderer$createRenderCommand$2(drawableImageDataModel, paint, matrix, shape);
    }

    public final Function1<Canvas, Unit> createRenderCommand(DrawableImageDataModel drawableImageDataModel, Shape shape, Paint paint, Matrix matrix) {
        Intrinsics.checkNotNullParameter(drawableImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        return shape instanceof RectShape ? new ImageRenderer$createRenderCommand$1(drawableImageDataModel, matrix, shape, paint) : new ImageRenderer$createRenderCommand$2(drawableImageDataModel, paint, matrix, shape);
    }

    public final Function1<Canvas, Unit> bitmapRenderCommand(Paint paint, Bitmap bitmap, Matrix imageTransformation) {
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        return new ImageRenderer$bitmapRenderCommand$1(imageTransformation, bitmap, paint);
    }

    public final Function1<Canvas, Unit> paintRenderCommand(Shape shape, Paint paint) {
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        return new ImageRenderer$paintRenderCommand$1(shape, paint);
    }

    public static /* synthetic */ Paint setBitmap$default(ImageRenderer imageRenderer, Paint paint, Bitmap bitmap, Matrix matrix, int i, Object obj) {
        if ((i & 2) != 0) {
            matrix = null;
        }
        Intrinsics.checkNotNullParameter(paint, "<this>");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.getShader().setLocalMatrix(matrix);
        return paint;
    }

    public final Paint setBitmap(Paint paint, Bitmap bitmap, Matrix matrix) {
        Intrinsics.checkNotNullParameter(paint, "<this>");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.getShader().setLocalMatrix(matrix);
        return paint;
    }
}
