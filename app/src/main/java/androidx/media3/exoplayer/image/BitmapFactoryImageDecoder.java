package androidx.media3.exoplayer.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.BitmapUtil;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.image.ImageDecoder;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class BitmapFactoryImageDecoder extends SimpleDecoder<DecoderInputBuffer, ImageOutputBuffer, ImageDecoderException> implements ImageDecoder {
    private final BitmapDecoder bitmapDecoder;
    private final Context context;
    private final int maxOutputSize;

    @Deprecated
    /* loaded from: classes.dex */
    public interface BitmapDecoder {
        Bitmap decode(byte[] bArr, int i) throws ImageDecoderException;
    }

    @Override // androidx.media3.decoder.SimpleDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ ImageOutputBuffer dequeueOutputBuffer() throws ImageDecoderException {
        return (ImageOutputBuffer) super.dequeueOutputBuffer();
    }

    /* loaded from: classes.dex */
    public static final class Factory implements ImageDecoder.Factory {
        private final BitmapDecoder bitmapDecoder;
        private final Context context;
        private int maxOutputSize;

        @Deprecated
        public Factory() {
            this(null, null);
        }

        public Factory(Context context) {
            this(context, null);
        }

        @Deprecated
        public Factory(BitmapDecoder bitmapDecoder) {
            this(null, bitmapDecoder);
        }

        private Factory(Context context, BitmapDecoder bitmapDecoder) {
            this.context = context;
            this.bitmapDecoder = bitmapDecoder;
            this.maxOutputSize = -1;
        }

        public Factory setMaxOutputSize(int i) {
            Assertions.checkArgument(i == -1 || i > 0);
            this.maxOutputSize = i;
            return this;
        }

        @Override // androidx.media3.exoplayer.image.ImageDecoder.Factory
        public int supportsFormat(Format format) {
            if (format.sampleMimeType == null || !MimeTypes.isImage(format.sampleMimeType)) {
                return RendererCapabilities.create(0);
            }
            if (Util.isBitmapFactorySupportedMimeType(format.sampleMimeType)) {
                return RendererCapabilities.create(4);
            }
            return RendererCapabilities.create(1);
        }

        @Override // androidx.media3.exoplayer.image.ImageDecoder.Factory
        public BitmapFactoryImageDecoder createImageDecoder() {
            return new BitmapFactoryImageDecoder(this.context, this.bitmapDecoder, this.maxOutputSize);
        }
    }

    private BitmapFactoryImageDecoder(Context context, BitmapDecoder bitmapDecoder, int i) {
        super(new DecoderInputBuffer[1], new ImageOutputBuffer[1]);
        this.context = context;
        this.bitmapDecoder = bitmapDecoder;
        this.maxOutputSize = i;
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "BitmapFactoryImageDecoder";
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    protected DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageOutputBuffer createOutputBuffer() {
        return new ImageOutputBuffer() { // from class: androidx.media3.exoplayer.image.BitmapFactoryImageDecoder.1
            @Override // androidx.media3.decoder.DecoderOutputBuffer
            public void release() {
                BitmapFactoryImageDecoder.this.releaseOutputBuffer(this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageDecoderException createUnexpectedDecodeException(Throwable th) {
        return new ImageDecoderException("Unexpected decode error", th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageDecoderException decode(DecoderInputBuffer decoderInputBuffer, ImageOutputBuffer imageOutputBuffer, boolean z) {
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data);
        Assertions.checkState(byteBuffer.hasArray());
        Assertions.checkArgument(byteBuffer.arrayOffset() == 0);
        BitmapDecoder bitmapDecoder = this.bitmapDecoder;
        if (bitmapDecoder != null) {
            try {
                imageOutputBuffer.bitmap = bitmapDecoder.decode(byteBuffer.array(), byteBuffer.remaining());
            } catch (ImageDecoderException e) {
                return e;
            }
        } else {
            try {
                int i = this.maxOutputSize;
                if (i == -1) {
                    Context context = this.context;
                    if (context != null) {
                        Point currentDisplayModeSize = Util.getCurrentDisplayModeSize(context);
                        int i2 = currentDisplayModeSize.x;
                        int i3 = currentDisplayModeSize.y;
                        if (decoderInputBuffer.format != null) {
                            if (decoderInputBuffer.format.tileCountHorizontal != -1) {
                                i2 *= decoderInputBuffer.format.tileCountHorizontal;
                            }
                            if (decoderInputBuffer.format.tileCountVertical != -1) {
                                i3 *= decoderInputBuffer.format.tileCountVertical;
                            }
                        }
                        i = (Math.max(i2, i3) * 2) - 1;
                    } else {
                        i = 4096;
                    }
                }
                imageOutputBuffer.bitmap = BitmapUtil.decode(byteBuffer.array(), byteBuffer.remaining(), null, i);
            } catch (ParserException e2) {
                return new ImageDecoderException("Could not decode image data with BitmapFactory.", e2);
            } catch (IOException e3) {
                return new ImageDecoderException(e3);
            }
        }
        imageOutputBuffer.timeUs = decoderInputBuffer.timeUs;
        return null;
    }
}
