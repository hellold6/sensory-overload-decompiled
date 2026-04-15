package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.horcrux.svg.FilterProperties;
import java.util.HashMap;

/* loaded from: classes3.dex */
class FeColorMatrixView extends FilterPrimitiveView {
    String mIn1;
    FilterProperties.FeColorMatrixType mType;
    ReadableArray mValues;

    public FeColorMatrixView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setIn1(String str) {
        this.mIn1 = str;
        invalidate();
    }

    public void setType(String str) {
        this.mType = FilterProperties.FeColorMatrixType.getEnum(str);
        invalidate();
    }

    public void setValues(ReadableArray readableArray) {
        this.mValues = readableArray;
        invalidate();
    }

    @Override // com.horcrux.svg.FilterPrimitiveView
    public Bitmap applyFilter(HashMap<String, Bitmap> hashMap, Bitmap bitmap) {
        Bitmap source = getSource(hashMap, bitmap, this.mIn1);
        ColorMatrix colorMatrix = new ColorMatrix();
        int i = AnonymousClass1.$SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType[this.mType.ordinal()];
        if (i == 1) {
            if (this.mValues.size() >= 20) {
                float[] fArr = new float[this.mValues.size()];
                for (int i2 = 0; i2 < this.mValues.size(); i2++) {
                    fArr[i2] = ((float) this.mValues.getDouble(i2)) * (i2 % 5 == 4 ? 255 : 1);
                }
                colorMatrix.set(fArr);
                return FilterUtils.getBitmapWithColorMatrix(colorMatrix, source);
            }
            return source;
        }
        if (i == 2) {
            if (this.mValues.size() == 1) {
                colorMatrix.setSaturation((float) this.mValues.getDouble(0));
                return FilterUtils.getBitmapWithColorMatrix(colorMatrix, source);
            }
            return source;
        }
        if (i == 3) {
            if (this.mValues.size() == 1) {
                double d = (((float) this.mValues.getDouble(0)) * 3.141592653589793d) / 180.0d;
                float cos = (float) Math.cos(d);
                float sin = (float) Math.sin(d);
                float f = 0.715f - (cos * 0.715f);
                float f2 = sin * 0.715f;
                float f3 = 0.072f - (cos * 0.072f);
                float f4 = 0.213f - (cos * 0.213f);
                colorMatrix.set(new float[]{((cos * 0.787f) + 0.213f) - (sin * 0.213f), f - f2, f3 + (sin * 0.928f), 0.0f, 0.0f, f4 + (0.143f * sin), (0.285f * cos) + 0.715f + (0.14f * sin), f3 - (0.283f * sin), 0.0f, 0.0f, f4 - (0.787f * sin), f + f2, (cos * 0.928f) + 0.072f + (sin * 0.072f), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
            }
            return source;
        }
        if (i == 4) {
            colorMatrix.set(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.2125f, 0.7154f, 0.0721f, 0.0f, 0.0f});
        }
        return FilterUtils.getBitmapWithColorMatrix(colorMatrix, source);
    }

    /* renamed from: com.horcrux.svg.FeColorMatrixView$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType;

        static {
            int[] iArr = new int[FilterProperties.FeColorMatrixType.values().length];
            $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType = iArr;
            try {
                iArr[FilterProperties.FeColorMatrixType.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType[FilterProperties.FeColorMatrixType.SATURATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType[FilterProperties.FeColorMatrixType.HUE_ROTATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType[FilterProperties.FeColorMatrixType.LUMINANCE_TO_ALPHA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
