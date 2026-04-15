package androidx.media3.container;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.UByte$$ExternalSyntheticBackport0;

/* loaded from: classes.dex */
public final class MdtaMetadataEntry implements Metadata.Entry {
    public static final byte AUXILIARY_TRACKS_SAMPLES_INTERLEAVED = 1;
    public static final byte AUXILIARY_TRACKS_SAMPLES_NOT_INTERLEAVED = 0;
    public static final int DEFAULT_LOCALE_INDICATOR = 0;
    public static final String KEY_ANDROID_CAPTURE_FPS = "com.android.capture.fps";
    public static final String KEY_AUXILIARY_TRACKS_INTERLEAVED = "auxiliary.tracks.interleaved";
    public static final String KEY_AUXILIARY_TRACKS_LENGTH = "auxiliary.tracks.length";
    public static final String KEY_AUXILIARY_TRACKS_MAP = "auxiliary.tracks.map";
    public static final String KEY_AUXILIARY_TRACKS_OFFSET = "auxiliary.tracks.offset";
    public static final int TYPE_INDICATOR_8_BIT_UNSIGNED_INT = 75;
    public static final int TYPE_INDICATOR_FLOAT32 = 23;
    public static final int TYPE_INDICATOR_INT32 = 67;
    public static final int TYPE_INDICATOR_RESERVED = 0;
    public static final int TYPE_INDICATOR_STRING = 1;
    public static final int TYPE_INDICATOR_UNSIGNED_INT64 = 78;
    public final String key;
    public final int localeIndicator;
    public final int typeIndicator;
    public final byte[] value;

    public MdtaMetadataEntry(String str, byte[] bArr, int i) {
        this(str, bArr, 0, i);
    }

    public MdtaMetadataEntry(String str, byte[] bArr, int i, int i2) {
        validateData(str, bArr, i2);
        this.key = str;
        this.value = bArr;
        this.localeIndicator = i;
        this.typeIndicator = i2;
    }

    public List<Integer> getAuxiliaryTrackTypesFromMap() {
        Assertions.checkState(this.key.equals(KEY_AUXILIARY_TRACKS_MAP), "Metadata is not an auxiliary tracks map");
        byte b = this.value[1];
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < b; i++) {
            arrayList.add(Integer.valueOf(this.value[i + 2]));
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) obj;
            if (this.key.equals(mdtaMetadataEntry.key) && Arrays.equals(this.value, mdtaMetadataEntry.value) && this.localeIndicator == mdtaMetadataEntry.localeIndicator && this.typeIndicator == mdtaMetadataEntry.typeIndicator) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((527 + this.key.hashCode()) * 31) + Arrays.hashCode(this.value)) * 31) + this.localeIndicator) * 31) + this.typeIndicator;
    }

    public String toString() {
        String formattedValueForAuxiliaryTracksMap;
        int i = this.typeIndicator;
        if (i == 0) {
            if (this.key.equals(KEY_AUXILIARY_TRACKS_MAP)) {
                formattedValueForAuxiliaryTracksMap = getFormattedValueForAuxiliaryTracksMap(getAuxiliaryTrackTypesFromMap());
            }
            formattedValueForAuxiliaryTracksMap = Util.toHexString(this.value);
        } else if (i == 1) {
            formattedValueForAuxiliaryTracksMap = Util.fromUtf8Bytes(this.value);
        } else if (i == 23) {
            formattedValueForAuxiliaryTracksMap = String.valueOf(Float.intBitsToFloat(Ints.fromByteArray(this.value)));
        } else if (i == 67) {
            formattedValueForAuxiliaryTracksMap = String.valueOf(Ints.fromByteArray(this.value));
        } else if (i == 75) {
            formattedValueForAuxiliaryTracksMap = String.valueOf(UByte$$ExternalSyntheticBackport0.m(this.value[0]));
        } else {
            if (i == 78) {
                formattedValueForAuxiliaryTracksMap = String.valueOf(new ParsableByteArray(this.value).readUnsignedLongToLong());
            }
            formattedValueForAuxiliaryTracksMap = Util.toHexString(this.value);
        }
        return "mdta: key=" + this.key + ", value=" + formattedValueForAuxiliaryTracksMap;
    }

    private static void validateData(String str, byte[] bArr, int i) {
        byte b;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1949883051:
                if (str.equals(KEY_ANDROID_CAPTURE_FPS)) {
                    c = 0;
                    break;
                }
                break;
            case -269399509:
                if (str.equals(KEY_AUXILIARY_TRACKS_INTERLEAVED)) {
                    c = 1;
                    break;
                }
                break;
            case 1011693540:
                if (str.equals(KEY_AUXILIARY_TRACKS_LENGTH)) {
                    c = 2;
                    break;
                }
                break;
            case 1098277265:
                if (str.equals(KEY_AUXILIARY_TRACKS_OFFSET)) {
                    c = 3;
                    break;
                }
                break;
            case 2002123038:
                if (str.equals(KEY_AUXILIARY_TRACKS_MAP)) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (i == 23 && bArr.length == 4) {
                    r2 = true;
                }
                Assertions.checkArgument(r2);
                return;
            case 1:
                if (i == 75 && bArr.length == 1 && ((b = bArr[0]) == 0 || b == 1)) {
                    r2 = true;
                }
                Assertions.checkArgument(r2);
                return;
            case 2:
            case 3:
                if (i == 78 && bArr.length == 8) {
                    r2 = true;
                }
                Assertions.checkArgument(r2);
                return;
            case 4:
                Assertions.checkArgument(i == 0);
                return;
            default:
                return;
        }
    }

    private static String getFormattedValueForAuxiliaryTracksMap(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("track types = ");
        Joiner.on(',').appendTo(sb, (Iterable<? extends Object>) list);
        return sb.toString();
    }
}
