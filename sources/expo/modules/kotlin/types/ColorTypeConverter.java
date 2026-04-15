package expo.modules.kotlin.types;

import android.graphics.Color;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.DynamicCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.SingleType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: ColorTypeConverter.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\"\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\"\u0010\f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000bH\u0016¨\u0006\u0017"}, d2 = {"Lexpo/modules/kotlin/types/ColorTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "Landroid/graphics/Color;", "<init>", "()V", "convertFromDynamic", "value", "Lcom/facebook/react/bridge/Dynamic;", "context", "Lexpo/modules/kotlin/AppContext;", "forceConversion", "", "convertFromAny", "", "colorFromDoubleArray", "", "colorFromInt", "", "colorFromString", "", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ColorTypeConverter extends DynamicAwareTypeConverters<Color> {

    /* compiled from: ColorTypeConverter.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReadableType.values().length];
            try {
                iArr[ReadableType.Number.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReadableType.String.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReadableType.Array.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // expo.modules.kotlin.types.NonNullableTypeConverter, expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Color convertFromDynamic(Dynamic value, AppContext context, boolean forceConversion) {
        Intrinsics.checkNotNullParameter(value, "value");
        int i = WhenMappings.$EnumSwitchMapping$0[value.getType().ordinal()];
        if (i == 1) {
            return colorFromInt((int) value.asDouble());
        }
        if (i == 2) {
            String asString = value.asString();
            if (asString != null) {
                return colorFromString(asString);
            }
            throw new DynamicCastException(Reflection.getOrCreateKotlinClass(String.class));
        }
        if (i != 3) {
            throw new UnexpectedException("Unknown argument type: " + value.getType());
        }
        ReadableArray asArray = value.asArray();
        if (asArray == null) {
            throw new DynamicCastException(Reflection.getOrCreateKotlinClass(ReadableArray.class));
        }
        ArrayList<Object> arrayList = asArray.toArrayList();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (Object obj : arrayList) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
            arrayList2.add(Double.valueOf(((Double) obj).doubleValue()));
        }
        return colorFromDoubleArray(CollectionsKt.toDoubleArray(arrayList2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Color convertFromAny(Object value, AppContext context, boolean forceConversion) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof Integer) {
            return colorFromInt(((Number) value).intValue());
        }
        if (value instanceof String) {
            return colorFromString((String) value);
        }
        if (value instanceof double[]) {
            return colorFromDoubleArray((double[]) value);
        }
        throw new UnexpectedException("Unknown argument type: " + Reflection.getOrCreateKotlinClass(value.getClass()));
    }

    private final Color colorFromDoubleArray(double[] value) {
        Double orNull = ArraysKt.getOrNull(value, 3);
        Color valueOf = Color.valueOf((float) value[0], (float) value[1], (float) value[2], (float) (orNull != null ? orNull.doubleValue() : 1.0d));
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        return valueOf;
    }

    private final Color colorFromInt(int value) {
        Color valueOf = Color.valueOf(value);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        return valueOf;
    }

    private final Color colorFromString(String value) {
        Map map;
        map = ColorTypeConverterKt.namedColors;
        List list = (List) map.get(value);
        if (list != null) {
            Color valueOf = Color.valueOf(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue(), ((Number) list.get(2)).floatValue(), ((Number) list.get(3)).floatValue());
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            return valueOf;
        }
        Color valueOf2 = Color.valueOf(Color.parseColor(value));
        Intrinsics.checkNotNullExpressionValue(valueOf2, "valueOf(...)");
        return valueOf2;
    }

    @Override // expo.modules.kotlin.types.NonNullableTypeConverter, expo.modules.kotlin.types.TypeConverter
    /* renamed from: getCppRequiredTypes */
    public ExpectedType get$cppRequireType() {
        return new ExpectedType(new SingleType(CppType.INT, null, 2, null), new SingleType(CppType.STRING, null, 2, null), new SingleType(CppType.PRIMITIVE_ARRAY, new ExpectedType[]{new ExpectedType(CppType.DOUBLE)}));
    }
}
