package expo.modules.image.thumbhash;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThumbhashEncoder.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\f"}, d2 = {"Lexpo/modules/image/thumbhash/ThumbhashEncoder;", "", "<init>", "()V", "encode", "", "bitmap", "Landroid/graphics/Bitmap;", "resizeKeepingAspectRatio", "maxSize", "", "Channel", "expo-image_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ThumbhashEncoder {
    public static final ThumbhashEncoder INSTANCE = new ThumbhashEncoder();

    private ThumbhashEncoder() {
    }

    public final byte[] encode(Bitmap bitmap) {
        int i;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Bitmap resizeKeepingAspectRatio = resizeKeepingAspectRatio(bitmap, 100);
        int width = resizeKeepingAspectRatio.getWidth();
        int height = resizeKeepingAspectRatio.getHeight();
        int i2 = width * height;
        resizeKeepingAspectRatio.getPixels(new int[i2], 0, width, 0, 0, width, height);
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        for (int i3 = 0; i3 < i2; i3++) {
            float alpha = Color.alpha(r4[i3]) / 255.0f;
            float f5 = alpha / 255.0f;
            f2 += Color.red(r4[i3]) * f5;
            f3 += Color.green(r4[i3]) * f5;
            f4 += f5 * Color.blue(r4[i3]);
            f += alpha;
        }
        if (f > 0.0f) {
            f2 /= f;
            f3 /= f;
            f4 /= f;
        }
        boolean z = f < ((float) i2);
        int i4 = z ? 5 : 7;
        int max = Math.max(1, Math.round((i4 * width) / Math.max(width, height)));
        int max2 = Math.max(1, Math.round((i4 * height) / Math.max(width, height)));
        float[] fArr = new float[i2];
        float[] fArr2 = new float[i2];
        float[] fArr3 = new float[i2];
        float[] fArr4 = new float[i2];
        int i5 = 0;
        while (i5 < i2) {
            int i6 = i2;
            float alpha2 = (Color.alpha(r4[i5]) & 255) / 255.0f;
            float f6 = 1.0f - alpha2;
            float f7 = alpha2 / 255.0f;
            float red = (f2 * f6) + (Color.red(r4[i5]) * f7);
            float green = (f3 * f6) + (Color.green(r4[i5]) * f7);
            float blue = (f6 * f4) + (f7 * Color.blue(r4[i5]));
            float f8 = red + green;
            fArr[i5] = (f8 + blue) / 3.0f;
            fArr2[i5] = (f8 / 2.0f) - blue;
            fArr3[i5] = red - green;
            fArr4[i5] = alpha2;
            i5++;
            i2 = i6;
        }
        Channel encode = new Channel(Math.max(3, max), Math.max(3, max2)).encode(width, height, fArr);
        Channel encode2 = new Channel(3, 3).encode(width, height, fArr2);
        Channel encode3 = new Channel(3, 3).encode(width, height, fArr3);
        Channel encode4 = z ? new Channel(5, 5).encode(width, height, fArr4) : null;
        boolean z2 = width > height;
        int round = Math.round(encode.getDc() * 63.0f) | (Math.round((encode2.getDc() * 31.5f) + 31.5f) << 6) | (Math.round((encode3.getDc() * 31.5f) + 31.5f) << 12) | (Math.round(encode.getScale() * 31.0f) << 18) | (z ? 8388608 : 0);
        if (z2) {
            max = max2;
        }
        int round2 = (z2 ? 32768 : 0) | (Math.round(encode3.getScale() * 63.0f) << 9) | (Math.round(encode2.getScale() * 63.0f) << 3) | max;
        int i7 = z ? 6 : 5;
        int length = encode.getAc().length + encode2.getAc().length + encode3.getAc().length;
        if (z) {
            Intrinsics.checkNotNull(encode4);
            i = encode4.getAc().length;
        } else {
            i = 0;
        }
        byte[] bArr = new byte[(((length + i) + 1) / 2) + i7];
        bArr[0] = (byte) round;
        bArr[1] = (byte) (round >> 8);
        bArr[2] = (byte) (round >> 16);
        bArr[3] = (byte) round2;
        bArr[4] = (byte) (round2 >> 8);
        if (z) {
            Intrinsics.checkNotNull(encode4);
            bArr[5] = (byte) (Math.round(encode4.getDc() * 15.0f) | (Math.round(encode4.getScale() * 15.0f) << 4));
        }
        int writeTo = encode3.writeTo(bArr, i7, encode2.writeTo(bArr, i7, encode.writeTo(bArr, i7, 0)));
        if (z) {
            Intrinsics.checkNotNull(encode4);
            encode4.writeTo(bArr, i7, writeTo);
        }
        return bArr;
    }

    private final Bitmap resizeKeepingAspectRatio(Bitmap bitmap, int maxSize) {
        int i;
        float width = bitmap.getWidth() / bitmap.getHeight();
        if (width > 1.0f) {
            i = (int) (maxSize / width);
        } else {
            int i2 = (int) (maxSize * width);
            i = maxSize;
            maxSize = i2;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, maxSize, i, true);
        Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(...)");
        return createScaledBitmap;
    }

    /* compiled from: ThumbhashEncoder.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\r\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0014J\u001e\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012¨\u0006%"}, d2 = {"Lexpo/modules/image/thumbhash/ThumbhashEncoder$Channel;", "", "nx", "", "ny", "<init>", "(II)V", "getNx", "()I", "setNx", "(I)V", "getNy", "setNy", "dc", "", "getDc", "()F", "setDc", "(F)V", "ac", "", "getAc", "()[F", "setAc", "([F)V", "scale", "getScale", "setScale", "encode", "w", CmcdData.STREAMING_FORMAT_HLS, "channel", "writeTo", "hash", "", "start", "index", "expo-image_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    private static final class Channel {
        private float[] ac;
        private float dc;
        private int nx;
        private int ny;
        private float scale;

        public Channel(int i, int i2) {
            this.nx = i;
            this.ny = i2;
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                int i5 = i3 > 0 ? 0 : 1;
                while (true) {
                    int i6 = this.ny;
                    if (i5 * i6 < this.nx * (i6 - i3)) {
                        i4++;
                        i5++;
                    }
                }
                i3++;
            }
            this.ac = new float[i4];
        }

        public final int getNx() {
            return this.nx;
        }

        public final int getNy() {
            return this.ny;
        }

        public final void setNx(int i) {
            this.nx = i;
        }

        public final void setNy(int i) {
            this.ny = i;
        }

        public final float getDc() {
            return this.dc;
        }

        public final void setDc(float f) {
            this.dc = f;
        }

        public final float[] getAc() {
            return this.ac;
        }

        public final void setAc(float[] fArr) {
            Intrinsics.checkNotNullParameter(fArr, "<set-?>");
            this.ac = fArr;
        }

        public final float getScale() {
            return this.scale;
        }

        public final void setScale(float f) {
            this.scale = f;
        }

        public final Channel encode(int w, int h, float[] channel) {
            double d;
            int i = h;
            Intrinsics.checkNotNullParameter(channel, "channel");
            float[] fArr = new float[w];
            int i2 = this.ny;
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                int i5 = 0;
                while (true) {
                    int i6 = this.ny;
                    if (i5 * i6 < this.nx * (i6 - i3)) {
                        int i7 = 0;
                        while (true) {
                            d = 3.141592653589793d;
                            if (i7 >= w) {
                                break;
                            }
                            fArr[i7] = (float) Math.cos((3.141592653589793d / w) * i5 * (i7 + 0.5f));
                            i7++;
                        }
                        int i8 = 0;
                        float f = 0.0f;
                        while (i8 < i) {
                            double d2 = d;
                            float cos = (float) Math.cos((d2 / i) * i3 * (i8 + 0.5f));
                            for (int i9 = 0; i9 < w; i9++) {
                                f += channel[(i8 * w) + i9] * fArr[i9] * cos;
                            }
                            i8++;
                            i = h;
                            d = d2;
                        }
                        float f2 = f / (w * h);
                        if (i5 > 0 || i3 > 0) {
                            this.ac[i4] = f2;
                            this.scale = Math.max(this.scale, Math.abs(f2));
                            i4++;
                        } else {
                            this.dc = f2;
                        }
                        i5++;
                        i = h;
                    }
                }
                i3++;
                i = h;
            }
            if (this.scale > 0.0f) {
                int length = this.ac.length;
                for (int i10 = 0; i10 < length; i10++) {
                    float[] fArr2 = this.ac;
                    fArr2[i10] = ((0.5f / this.scale) * fArr2[i10]) + 0.5f;
                }
            }
            return this;
        }

        public final int writeTo(byte[] hash, int start, int index) {
            Intrinsics.checkNotNullParameter(hash, "hash");
            for (float f : this.ac) {
                int i = (index >> 1) + start;
                hash[i] = (byte) ((Math.round(f * 15.0f) << ((index & 1) << 2)) | hash[i]);
                index++;
            }
            return index;
        }
    }
}
