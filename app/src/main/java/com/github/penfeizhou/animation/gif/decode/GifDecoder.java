package com.github.penfeizhou.animation.gif.decode;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.Log;
import com.github.penfeizhou.animation.decode.Frame;
import com.github.penfeizhou.animation.decode.FrameSeqDecoder;
import com.github.penfeizhou.animation.gif.io.GifReader;
import com.github.penfeizhou.animation.gif.io.GifWriter;
import com.github.penfeizhou.animation.io.Reader;
import com.github.penfeizhou.animation.loader.Loader;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class GifDecoder extends FrameSeqDecoder<GifReader, GifWriter> {
    private static final String TAG = "GifDecoder";
    private int bgColor;
    private GifWriter mGifWriter;
    private int mLoopCount;
    private final Paint paint;
    private final SnapShot snapShot;

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected int getDesiredSample(int i, int i2) {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SnapShot {
        ByteBuffer byteBuffer;

        private SnapShot() {
        }
    }

    public GifDecoder(Loader loader, FrameSeqDecoder.RenderListener renderListener) {
        super(loader, renderListener);
        this.mGifWriter = new GifWriter();
        Paint paint = new Paint();
        this.paint = paint;
        this.bgColor = 0;
        this.snapShot = new SnapShot();
        this.mLoopCount = 1;
        paint.setAntiAlias(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public GifWriter getWriter() {
        if (this.mGifWriter == null) {
            this.mGifWriter = new GifWriter();
        }
        return this.mGifWriter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public GifReader getReader(Reader reader) {
        return new GifReader(reader);
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected int getLoopCount() {
        return this.mLoopCount;
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected void release() {
        this.snapShot.byteBuffer = null;
        this.mGifWriter = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public Rect read(GifReader gifReader) throws IOException {
        int i = -1;
        int i2 = 0;
        int i3 = 0;
        ColorTable colorTable = null;
        GraphicControlExtension graphicControlExtension = null;
        for (Block block : GifParser.parse(gifReader)) {
            if (block instanceof LogicalScreenDescriptor) {
                LogicalScreenDescriptor logicalScreenDescriptor = (LogicalScreenDescriptor) block;
                i2 = logicalScreenDescriptor.screenWidth;
                i3 = logicalScreenDescriptor.screenHeight;
                if (logicalScreenDescriptor.gColorTableFlag()) {
                    i = logicalScreenDescriptor.bgColorIndex & 255;
                }
            } else if (block instanceof ColorTable) {
                colorTable = (ColorTable) block;
            } else if (block instanceof GraphicControlExtension) {
                graphicControlExtension = (GraphicControlExtension) block;
            } else if (block instanceof ImageDescriptor) {
                this.frames.add(new GifFrame(gifReader, colorTable, graphicControlExtension, (ImageDescriptor) block));
            } else if (block instanceof ApplicationExtension) {
                ApplicationExtension applicationExtension = (ApplicationExtension) block;
                if ("NETSCAPE2.0".equals(applicationExtension.identifier)) {
                    int i4 = applicationExtension.loopCount;
                    if (i4 == 0) {
                        this.mLoopCount = 0;
                    } else if (i4 > 0) {
                        this.mLoopCount = i4 + 1;
                    }
                }
            }
        }
        long j = (((i2 * i3) / (this.sampleSize * this.sampleSize)) + 1) * 4;
        int i5 = (int) j;
        try {
            this.frameBuffer = ByteBuffer.allocate(i5);
            this.snapShot.byteBuffer = ByteBuffer.allocate(i5);
            if (colorTable != null && i >= 0 && i < colorTable.getColorTable().length) {
                int i6 = colorTable.getColorTable()[i];
                this.bgColor = Color.rgb(i6 & 255, (i6 >> 8) & 255, (i6 >> 16) & 255);
            }
            return new Rect(0, 0, i2, i3);
        } catch (OutOfMemoryError e) {
            Log.e(TAG, String.format("OutOfMemoryError in GifDecoder: Buffer needed: %.2fMB (%,d bytes)", Double.valueOf(j / 1048576.0d), Long.valueOf(j)));
            this.frameBuffer = null;
            this.snapShot.byteBuffer = null;
            throw e;
        }
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected void renderFrame(Frame<GifReader, GifWriter> frame) {
        GifFrame gifFrame = (GifFrame) frame;
        Bitmap obtainBitmap = obtainBitmap(this.fullRect.width() / this.sampleSize, this.fullRect.height() / this.sampleSize);
        Canvas canvas = this.cachedCanvas.get(obtainBitmap);
        if (canvas == null) {
            canvas = new Canvas(obtainBitmap);
            this.cachedCanvas.put(obtainBitmap, canvas);
        }
        this.frameBuffer.rewind();
        obtainBitmap.copyPixelsFromBuffer(this.frameBuffer);
        int i = !gifFrame.transparencyFlag() ? this.bgColor : 0;
        if (this.frameIndex == 0) {
            obtainBitmap.eraseColor(i);
        } else {
            GifFrame gifFrame2 = (GifFrame) this.frames.get(this.frameIndex - 1);
            canvas.save();
            canvas.clipRect(gifFrame2.frameX / this.sampleSize, gifFrame2.frameY / this.sampleSize, (gifFrame2.frameX + gifFrame2.frameWidth) / this.sampleSize, (gifFrame2.frameY + gifFrame2.frameHeight) / this.sampleSize);
            int i2 = gifFrame2.disposalMethod;
            if (i2 == 2) {
                canvas.drawColor(this.bgColor, PorterDuff.Mode.CLEAR);
            } else if (i2 == 3) {
                this.snapShot.byteBuffer.rewind();
                canvas.drawColor(this.bgColor, PorterDuff.Mode.CLEAR);
                Bitmap obtainBitmap2 = obtainBitmap(this.fullRect.width() / this.sampleSize, this.fullRect.height() / this.sampleSize);
                obtainBitmap2.copyPixelsFromBuffer(this.snapShot.byteBuffer);
                canvas.drawBitmap(obtainBitmap2, 0.0f, 0.0f, this.paint);
                recycleBitmap(obtainBitmap2);
            }
            canvas.restore();
            if (gifFrame.disposalMethod == 3 && gifFrame2.disposalMethod != 3) {
                this.frameBuffer.rewind();
                this.snapShot.byteBuffer.rewind();
                this.snapShot.byteBuffer.put(this.frameBuffer);
            }
        }
        Bitmap obtainBitmap3 = obtainBitmap(frame.frameWidth / this.sampleSize, frame.frameHeight / this.sampleSize);
        gifFrame.draw(canvas, this.paint, this.sampleSize, obtainBitmap3, getWriter());
        canvas.drawColor(i, PorterDuff.Mode.DST_OVER);
        recycleBitmap(obtainBitmap3);
        this.frameBuffer.rewind();
        obtainBitmap.copyPixelsToBuffer(this.frameBuffer);
        recycleBitmap(obtainBitmap);
    }
}
