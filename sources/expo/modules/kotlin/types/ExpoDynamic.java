package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import expo.modules.kotlin.exception.DynamicCastException;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: ExpoDynamic.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eJ\u0006\u0010\u000f\u001a\u00020\u000bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0013J\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0015J\u0006\u0010\u0017\u001a\u00020\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\f¨\u0006\u0019"}, d2 = {"Lexpo/modules/kotlin/types/ExpoDynamic;", "", "dynamic", "Lcom/facebook/react/bridge/Dynamic;", "<init>", "(Lcom/facebook/react/bridge/Dynamic;)V", "type", "Lexpo/modules/kotlin/types/ExpoDynamic$Type;", "getType", "()Lexpo/modules/kotlin/types/ExpoDynamic$Type;", "isNull", "", "()Z", "asArray", "", "asBoolean", "asDouble", "", "asInt", "", "asMap", "", "", "asString", "Type", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExpoDynamic {
    private final Dynamic dynamic;

    /* compiled from: ExpoDynamic.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReadableType.values().length];
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: ExpoDynamic.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lexpo/modules/kotlin/types/ExpoDynamic$Type;", "", "<init>", "(Ljava/lang/String;I)V", "Boolean", "Number", "String", "Map", "Array", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Type {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Type[] $VALUES;
        public static final Type Boolean = new Type("Boolean", 0);
        public static final Type Number = new Type("Number", 1);
        public static final Type String = new Type("String", 2);
        public static final Type Map = new Type("Map", 3);
        public static final Type Array = new Type("Array", 4);

        private static final /* synthetic */ Type[] $values() {
            return new Type[]{Boolean, Number, String, Map, Array};
        }

        public static EnumEntries<Type> getEntries() {
            return $ENTRIES;
        }

        private Type(String str, int i) {
        }

        static {
            Type[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public ExpoDynamic(Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(dynamic, "dynamic");
        this.dynamic = dynamic;
    }

    public final Type getType() {
        switch (WhenMappings.$EnumSwitchMapping$0[this.dynamic.getType().ordinal()]) {
            case 1:
                throw new IllegalStateException("ExpoDynamic is null");
            case 2:
                return Type.Boolean;
            case 3:
                return Type.Number;
            case 4:
                return Type.String;
            case 5:
                return Type.Map;
            case 6:
                return Type.Array;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean isNull() {
        if (this.dynamic.isNull()) {
            throw new IllegalStateException("ExpoDynamic is null");
        }
        return false;
    }

    public final List<Object> asArray() {
        ReadableArray asArray = this.dynamic.asArray();
        if (asArray != null) {
            return asArray.toArrayList();
        }
        throw new DynamicCastException(Reflection.getOrCreateKotlinClass(ReadableArray.class));
    }

    public final boolean asBoolean() {
        return this.dynamic.asBoolean();
    }

    public final double asDouble() {
        return this.dynamic.asDouble();
    }

    public final int asInt() {
        return this.dynamic.asInt();
    }

    public final Map<String, Object> asMap() {
        ReadableMap asMap = this.dynamic.asMap();
        if (asMap != null) {
            return asMap.toHashMap();
        }
        throw new DynamicCastException(Reflection.getOrCreateKotlinClass(ReadableMap.class));
    }

    public final String asString() {
        String asString = this.dynamic.asString();
        if (asString != null) {
            return asString;
        }
        throw new DynamicCastException(Reflection.getOrCreateKotlinClass(String.class));
    }
}
