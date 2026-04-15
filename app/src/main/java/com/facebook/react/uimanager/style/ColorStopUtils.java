package com.facebook.react.uimanager.style;

import androidx.core.graphics.ColorUtils;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ColorStop.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\t\u001a\u00020\nJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0002¢\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/uimanager/style/ColorStopUtils;", "", "<init>", "()V", "getFixedColorStops", "", "Lcom/facebook/react/uimanager/style/ProcessedColorStop;", "colorStops", "Lcom/facebook/react/uimanager/style/ColorStop;", "gradientLineLength", "", "processColorTransitionHints", "originalStops", "", "([Lcom/facebook/react/uimanager/style/ProcessedColorStop;)Ljava/util/List;", "resolveColorStopPosition", ViewProps.POSITION, "Lcom/facebook/react/uimanager/LengthPercentage;", "(Lcom/facebook/react/uimanager/LengthPercentage;F)Ljava/lang/Float;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ColorStopUtils {
    public static final ColorStopUtils INSTANCE = new ColorStopUtils();

    /* compiled from: ColorStop.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LengthPercentageType.values().length];
            try {
                iArr[LengthPercentageType.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LengthPercentageType.PERCENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ColorStopUtils() {
    }

    public final List<ProcessedColorStop> getFixedColorStops(List<ColorStop> colorStops, float gradientLineLength) {
        Intrinsics.checkNotNullParameter(colorStops, "colorStops");
        int size = colorStops.size();
        ProcessedColorStop[] processedColorStopArr = new ProcessedColorStop[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            processedColorStopArr[i2] = new ProcessedColorStop(null, null, 3, null);
        }
        Float resolveColorStopPosition = resolveColorStopPosition(colorStops.get(0).getPosition(), gradientLineLength);
        float floatValue = resolveColorStopPosition != null ? resolveColorStopPosition.floatValue() : 0.0f;
        int size2 = colorStops.size();
        int i3 = 0;
        boolean z = false;
        while (i3 < size2) {
            ColorStop colorStop = colorStops.get(i3);
            Float resolveColorStopPosition2 = resolveColorStopPosition(colorStop.getPosition(), gradientLineLength);
            if (resolveColorStopPosition2 == null) {
                if (i3 == 0) {
                    resolveColorStopPosition2 = Float.valueOf(0.0f);
                } else {
                    resolveColorStopPosition2 = i3 == colorStops.size() - 1 ? Float.valueOf(1.0f) : null;
                }
            }
            if (resolveColorStopPosition2 != null) {
                Float valueOf = Float.valueOf(Math.max(resolveColorStopPosition2.floatValue(), floatValue));
                processedColorStopArr[i3] = new ProcessedColorStop(colorStop.getColor(), valueOf);
                floatValue = valueOf.floatValue();
            } else {
                z = true;
            }
            i3++;
        }
        if (z) {
            for (int i4 = 1; i4 < size; i4++) {
                Float position = processedColorStopArr[i4].getPosition();
                Float position2 = processedColorStopArr[i].getPosition();
                int i5 = i4 - i;
                int i6 = i5 - 1;
                if (position != null && position2 != null && i6 > 0) {
                    float floatValue2 = (position.floatValue() - position2.floatValue()) / i5;
                    if (1 <= i6) {
                        int i7 = 1;
                        while (true) {
                            int i8 = i + i7;
                            processedColorStopArr[i8] = new ProcessedColorStop(colorStops.get(i8).getColor(), Float.valueOf(position2.floatValue() + (i7 * floatValue2)));
                            if (i7 == i6) {
                                break;
                            }
                            i7++;
                        }
                    }
                    i = i4;
                }
            }
        }
        return processColorTransitionHints(processedColorStopArr);
    }

    private final List<ProcessedColorStop> processColorTransitionHints(ProcessedColorStop[] originalStops) {
        int i;
        int i2;
        ProcessedColorStop[] processedColorStopArr = originalStops;
        List<ProcessedColorStop> mutableList = ArraysKt.toMutableList(processedColorStopArr);
        int i3 = 1;
        int length = processedColorStopArr.length - 1;
        int i4 = 1;
        int i5 = 0;
        while (i4 < length) {
            if (processedColorStopArr[i4].getColor() == null && (i2 = i4 + i5) >= i3) {
                int i6 = i2 - 1;
                Float position = mutableList.get(i6).getPosition();
                int i7 = i2 + 1;
                Float position2 = mutableList.get(i7).getPosition();
                Float position3 = mutableList.get(i2).getPosition();
                if (position != null && position2 != null && position3 != null) {
                    float floatValue = position3.floatValue() - position.floatValue();
                    float floatValue2 = position2.floatValue() - position3.floatValue();
                    float floatValue3 = position2.floatValue() - position.floatValue();
                    Integer color = mutableList.get(i6).getColor();
                    Integer color2 = mutableList.get(i7).getColor();
                    if (FloatUtil.floatsEqual(floatValue, floatValue2)) {
                        mutableList.remove(i2);
                        i5--;
                    } else if (FloatUtil.floatsEqual(floatValue, 0.0f)) {
                        mutableList.get(i2).setColor(color2);
                    } else if (FloatUtil.floatsEqual(floatValue2, 0.0f)) {
                        mutableList.get(i2).setColor(color);
                    } else {
                        ArrayList arrayList = new ArrayList(9);
                        if (floatValue > floatValue2) {
                            int i8 = 0;
                            while (i8 < 7) {
                                arrayList.add(new ProcessedColorStop(null, Float.valueOf(position.floatValue() + (((i8 + 7.0f) / 13.0f) * floatValue))));
                                i8++;
                                length = length;
                            }
                            i = length;
                            arrayList.add(new ProcessedColorStop(null, Float.valueOf(position3.floatValue() + (0.33333334f * floatValue2))));
                            arrayList.add(new ProcessedColorStop(null, Float.valueOf(position3.floatValue() + (floatValue2 * 0.6666667f))));
                        } else {
                            i = length;
                            arrayList.add(new ProcessedColorStop(null, Float.valueOf(position.floatValue() + (0.33333334f * floatValue))));
                            arrayList.add(new ProcessedColorStop(null, Float.valueOf(position.floatValue() + (0.6666667f * floatValue))));
                            for (int i9 = 0; i9 < 7; i9++) {
                                arrayList.add(new ProcessedColorStop(null, Float.valueOf(position3.floatValue() + ((i9 / 13.0f) * floatValue2))));
                            }
                        }
                        double log = Math.log(0.5d) / ((float) Math.log(floatValue / floatValue3));
                        Iterator it = arrayList.iterator();
                        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                        while (it.hasNext()) {
                            Object next = it.next();
                            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                            ProcessedColorStop processedColorStop = (ProcessedColorStop) next;
                            if (processedColorStop.getPosition() != null) {
                                float pow = (float) Math.pow((processedColorStop.getPosition().floatValue() - position.floatValue()) / floatValue3, log);
                                if (Math.abs(pow) <= Float.MAX_VALUE && !Float.isNaN(pow) && color != null) {
                                    int intValue = color.intValue();
                                    if (color2 != null) {
                                        processedColorStop.setColor(Integer.valueOf(ColorUtils.blendARGB(intValue, color2.intValue(), pow)));
                                    }
                                }
                            }
                        }
                        mutableList.remove(i2);
                        mutableList.addAll(i2, arrayList);
                        i5 += 8;
                        i4++;
                        processedColorStopArr = originalStops;
                        length = i;
                        i3 = 1;
                    }
                }
            }
            i = length;
            i4++;
            processedColorStopArr = originalStops;
            length = i;
            i3 = 1;
        }
        return mutableList;
    }

    private final Float resolveColorStopPosition(LengthPercentage position, float gradientLineLength) {
        if (position == null) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[position.getType().ordinal()];
        if (i == 1) {
            return Float.valueOf(PixelUtil.toPixelFromDIP(position.resolve(0.0f)) / gradientLineLength);
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        return Float.valueOf(position.resolve(1.0f));
    }
}
