package com.facebook.react.bridge;

import android.util.JsonWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JsonWriterHelper.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0007J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\tH\u0002J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u000bH\u0002J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\rH\u0007J \u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u000f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0010H\u0002J\u001c\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0002J\u001a\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0002¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/bridge/JsonWriterHelper;", "", "<init>", "()V", "value", "", "writer", "Landroid/util/JsonWriter;", "dynamicValue", "Lcom/facebook/react/bridge/Dynamic;", "readableMapValue", "Lcom/facebook/react/bridge/ReadableMap;", "readableArrayValue", "Lcom/facebook/react/bridge/ReadableArray;", "mapValue", "map", "", "listValue", "list", "", "objectValue", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JsonWriterHelper {
    public static final JsonWriterHelper INSTANCE = new JsonWriterHelper();

    /* compiled from: JsonWriterHelper.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
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

    private JsonWriterHelper() {
    }

    @JvmStatic
    public static final void value(JsonWriter writer, Object value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (value instanceof Map) {
            INSTANCE.mapValue(writer, (Map) value);
            return;
        }
        if (value instanceof List) {
            INSTANCE.listValue(writer, (List) value);
            return;
        }
        if (value instanceof ReadableMap) {
            INSTANCE.readableMapValue(writer, (ReadableMap) value);
            return;
        }
        if (value instanceof ReadableArray) {
            readableArrayValue(writer, (ReadableArray) value);
        } else if (value instanceof Dynamic) {
            INSTANCE.dynamicValue(writer, (Dynamic) value);
        } else {
            INSTANCE.objectValue(writer, value);
        }
    }

    private final void dynamicValue(JsonWriter writer, Dynamic value) throws IOException {
        switch (WhenMappings.$EnumSwitchMapping$0[value.getType().ordinal()]) {
            case 1:
                writer.nullValue();
                return;
            case 2:
                writer.value(value.asBoolean());
                return;
            case 3:
                writer.value(value.asDouble());
                return;
            case 4:
                writer.value(value.asString());
                return;
            case 5:
                ReadableMap asMap = value.asMap();
                if (asMap == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                readableMapValue(writer, asMap);
                Unit unit = Unit.INSTANCE;
                return;
            case 6:
                ReadableArray asArray = value.asArray();
                if (asArray == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                readableArrayValue(writer, asArray);
                Unit unit2 = Unit.INSTANCE;
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final void readableMapValue(JsonWriter writer, ReadableMap value) throws IOException {
        writer.beginObject();
        try {
            ReadableMapKeySetIterator keySetIterator = value.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                writer.name(nextKey);
                switch (WhenMappings.$EnumSwitchMapping$0[value.getType(nextKey).ordinal()]) {
                    case 1:
                        writer.nullValue();
                        break;
                    case 2:
                        writer.value(value.getBoolean(nextKey));
                        break;
                    case 3:
                        writer.value(value.getDouble(nextKey));
                        break;
                    case 4:
                        writer.value(value.getString(nextKey));
                        break;
                    case 5:
                        ReadableMap map = value.getMap(nextKey);
                        if (map == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        readableMapValue(writer, map);
                        Unit unit = Unit.INSTANCE;
                        break;
                    case 6:
                        ReadableArray array = value.getArray(nextKey);
                        if (array == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        readableArrayValue(writer, array);
                        Unit unit2 = Unit.INSTANCE;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
        } finally {
            writer.endObject();
        }
    }

    @JvmStatic
    public static final void readableArrayValue(JsonWriter writer, ReadableArray value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.beginArray();
        try {
            int size = value.size();
            for (int i = 0; i < size; i++) {
                switch (WhenMappings.$EnumSwitchMapping$0[value.getType(i).ordinal()]) {
                    case 1:
                        writer.nullValue();
                        break;
                    case 2:
                        writer.value(value.getBoolean(i));
                        break;
                    case 3:
                        writer.value(value.getDouble(i));
                        break;
                    case 4:
                        writer.value(value.getString(i));
                        break;
                    case 5:
                        ReadableMap map = value.getMap(i);
                        JsonWriterHelper jsonWriterHelper = INSTANCE;
                        if (map == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        jsonWriterHelper.readableMapValue(writer, map);
                        Unit unit = Unit.INSTANCE;
                        break;
                    case 6:
                        ReadableArray array = value.getArray(i);
                        if (array == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        readableArrayValue(writer, array);
                        Unit unit2 = Unit.INSTANCE;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
        } finally {
            writer.endArray();
        }
    }

    private final void mapValue(JsonWriter writer, Map<?, ?> map) throws IOException {
        writer.beginObject();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            writer.name(String.valueOf(key));
            value(writer, value);
        }
        writer.endObject();
    }

    private final void listValue(JsonWriter writer, List<?> list) throws IOException {
        writer.beginArray();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            objectValue(writer, it.next());
        }
        writer.endArray();
    }

    private final void objectValue(JsonWriter writer, Object value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        if (value instanceof String) {
            writer.value((String) value);
        } else if (value instanceof Number) {
            writer.value((Number) value);
        } else {
            if (!(value instanceof Boolean)) {
                throw new IllegalArgumentException("Unknown value: " + value);
            }
            writer.value(((Boolean) value).booleanValue());
        }
    }
}
