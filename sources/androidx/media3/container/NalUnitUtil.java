package androidx.media3.container;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import com.google.common.math.DoubleMath;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;
    public static final int H264_NAL_UNIT_TYPE_AUD = 9;
    public static final int H264_NAL_UNIT_TYPE_IDR = 5;
    public static final int H264_NAL_UNIT_TYPE_NON_IDR = 1;
    public static final int H264_NAL_UNIT_TYPE_PARTITION_A = 2;
    public static final int H264_NAL_UNIT_TYPE_PPS = 8;
    public static final int H264_NAL_UNIT_TYPE_PREFIX = 14;
    public static final int H264_NAL_UNIT_TYPE_SEI = 6;
    public static final int H264_NAL_UNIT_TYPE_SPS = 7;
    public static final int H264_NAL_UNIT_TYPE_UNSPECIFIED = 24;
    public static final int H265_NAL_UNIT_TYPE_AUD = 35;
    public static final int H265_NAL_UNIT_TYPE_BLA_W_LP = 16;
    public static final int H265_NAL_UNIT_TYPE_CRA = 21;
    public static final int H265_NAL_UNIT_TYPE_PPS = 34;
    public static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final int H265_NAL_UNIT_TYPE_RASL_R = 9;
    public static final int H265_NAL_UNIT_TYPE_SPS = 33;
    public static final int H265_NAL_UNIT_TYPE_SUFFIX_SEI = 40;
    public static final int H265_NAL_UNIT_TYPE_UNSPECIFIED = 48;
    public static final int H265_NAL_UNIT_TYPE_VPS = 32;
    private static final int INVALID_ID = -1;

    @Deprecated
    public static final int NAL_UNIT_TYPE_AUD = 9;

    @Deprecated
    public static final int NAL_UNIT_TYPE_IDR = 5;

    @Deprecated
    public static final int NAL_UNIT_TYPE_NON_IDR = 1;

    @Deprecated
    public static final int NAL_UNIT_TYPE_PARTITION_A = 2;

    @Deprecated
    public static final int NAL_UNIT_TYPE_PPS = 8;

    @Deprecated
    public static final int NAL_UNIT_TYPE_PREFIX = 14;

    @Deprecated
    public static final int NAL_UNIT_TYPE_SEI = 6;

    @Deprecated
    public static final int NAL_UNIT_TYPE_SPS = 7;
    private static final String TAG = "NalUnitUtil";
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    private static int applyConformanceWindowToHeight(int i, int i2, int i3, int i4) {
        return i - ((i2 == 1 ? 2 : 1) * (i3 + i4));
    }

    private static int applyConformanceWindowToWidth(int i, int i2, int i3, int i4) {
        int i5 = 2;
        if (i2 != 1 && i2 != 2) {
            i5 = 1;
        }
        return i - (i5 * (i3 + i4));
    }

    public static boolean isH264NalUnitDependedOn(byte b) {
        if (((b & 96) >> 5) != 0) {
            return true;
        }
        int i = b & Ascii.US;
        return (i == 1 || i == 9 || i == 14) ? false : true;
    }

    /* loaded from: classes.dex */
    public static final class SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int maxNumReorderFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, int i8, int i9, boolean z, boolean z2, int i10, int i11, int i12, boolean z3, int i13, int i14, int i15, int i16) {
            this.profileIdc = i;
            this.constraintsFlagsAndReservedZero2Bits = i2;
            this.levelIdc = i3;
            this.seqParameterSetId = i4;
            this.maxNumRefFrames = i5;
            this.width = i6;
            this.height = i7;
            this.pixelWidthHeightRatio = f;
            this.bitDepthLumaMinus8 = i8;
            this.bitDepthChromaMinus8 = i9;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = i10;
            this.picOrderCountType = i11;
            this.picOrderCntLsbLength = i12;
            this.deltaPicOrderAlwaysZeroFlag = z3;
            this.colorSpace = i13;
            this.colorRange = i14;
            this.colorTransfer = i15;
            this.maxNumReorderFrames = i16;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265NalHeader {
        public final int layerId;
        public final int nalUnitType;
        public final int temporalId;

        public H265NalHeader(int i, int i2, int i3) {
            this.nalUnitType = i;
            this.layerId = i2;
            this.temporalId = i3;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265LayerInfo {
        public final int layerIdInVps;
        public final int viewId;

        public H265LayerInfo(int i, int i2) {
            this.layerIdInVps = i;
            this.viewId = i2;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265ProfileTierLevel {
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;

        public H265ProfileTierLevel(int i, boolean z, int i2, int i3, int[] iArr, int i4) {
            this.generalProfileSpace = i;
            this.generalTierFlag = z;
            this.generalProfileIdc = i2;
            this.generalProfileCompatibilityFlags = i3;
            this.constraintBytes = iArr;
            this.generalLevelIdc = i4;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265ProfileTierLevelsAndIndices {
        public final int[] indices;
        public final ImmutableList<H265ProfileTierLevel> profileTierLevels;

        public H265ProfileTierLevelsAndIndices(List<H265ProfileTierLevel> list, int[] iArr) {
            this.profileTierLevels = ImmutableList.copyOf((Collection) list);
            this.indices = iArr;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265RepFormat {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int height;
        public final int width;

        public H265RepFormat(int i, int i2, int i3, int i4, int i5) {
            this.chromaFormatIdc = i;
            this.bitDepthLumaMinus8 = i2;
            this.bitDepthChromaMinus8 = i3;
            this.width = i4;
            this.height = i5;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265RepFormatsAndIndices {
        public final int[] indices;
        public final ImmutableList<H265RepFormat> repFormats;

        public H265RepFormatsAndIndices(List<H265RepFormat> list, int[] iArr) {
            this.repFormats = ImmutableList.copyOf((Collection) list);
            this.indices = iArr;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265VideoSignalInfo {
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;

        public H265VideoSignalInfo(int i, int i2, int i3) {
            this.colorSpace = i;
            this.colorRange = i2;
            this.colorTransfer = i3;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265VideoSignalInfosAndIndices {
        public final int[] indices;
        public final ImmutableList<H265VideoSignalInfo> videoSignalInfos;

        public H265VideoSignalInfosAndIndices(List<H265VideoSignalInfo> list, int[] iArr) {
            this.videoSignalInfos = ImmutableList.copyOf((Collection) list);
            this.indices = iArr;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265VpsData {
        public final ImmutableList<H265LayerInfo> layerInfos;
        public final H265NalHeader nalHeader;
        public final H265ProfileTierLevelsAndIndices profileTierLevelsAndIndices;
        public final H265RepFormatsAndIndices repFormatsAndIndices;
        public final H265VideoSignalInfosAndIndices videoSignalInfosAndIndices;

        public H265VpsData(H265NalHeader h265NalHeader, List<H265LayerInfo> list, H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices, H265RepFormatsAndIndices h265RepFormatsAndIndices, H265VideoSignalInfosAndIndices h265VideoSignalInfosAndIndices) {
            this.nalHeader = h265NalHeader;
            this.layerInfos = list != null ? ImmutableList.copyOf((Collection) list) : ImmutableList.of();
            this.profileTierLevelsAndIndices = h265ProfileTierLevelsAndIndices;
            this.repFormatsAndIndices = h265RepFormatsAndIndices;
            this.videoSignalInfosAndIndices = h265VideoSignalInfosAndIndices;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int decodedHeight;
        public final int decodedWidth;
        public final int height;
        public final int maxNumReorderPics;
        public final int maxSubLayersMinus1;
        public final H265NalHeader nalHeader;
        public final float pixelWidthHeightRatio;
        public final H265ProfileTierLevel profileTierLevel;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(H265NalHeader h265NalHeader, int i, H265ProfileTierLevel h265ProfileTierLevel, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, float f, int i10, int i11, int i12, int i13) {
            this.nalHeader = h265NalHeader;
            this.maxSubLayersMinus1 = i;
            this.profileTierLevel = h265ProfileTierLevel;
            this.chromaFormatIdc = i2;
            this.bitDepthLumaMinus8 = i3;
            this.bitDepthChromaMinus8 = i4;
            this.seqParameterSetId = i5;
            this.width = i6;
            this.height = i7;
            this.pixelWidthHeightRatio = f;
            this.maxNumReorderPics = i10;
            this.colorSpace = i11;
            this.colorRange = i12;
            this.colorTransfer = i13;
            this.decodedWidth = i8;
            this.decodedHeight = i9;
        }
    }

    /* loaded from: classes.dex */
    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i, int i2, boolean z) {
            this.picParameterSetId = i;
            this.seqParameterSetId = i2;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    /* loaded from: classes.dex */
    public static final class H265Sei3dRefDisplayInfoData {
        public final int exponentRefDisplayWidth;
        public final int exponentRefViewingDist;
        public final int leftViewId;
        public final int mantissaRefDisplayWidth;
        public final int mantissaRefViewingDist;
        public final int numRefDisplays;
        public final int precRefDisplayWidth;
        public final int precRefViewingDist;
        public final int rightViewId;

        public H265Sei3dRefDisplayInfoData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.precRefDisplayWidth = i;
            this.precRefViewingDist = i2;
            this.numRefDisplays = i3;
            this.leftViewId = i4;
            this.rightViewId = i5;
            this.exponentRefDisplayWidth = i6;
            this.mantissaRefDisplayWidth = i7;
            this.exponentRefViewingDist = i8;
            this.mantissaRefViewingDist = i9;
        }
    }

    public static int unescapeStream(byte[] bArr, int i) {
        int i2;
        synchronized (scratchEscapePositionsLock) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                try {
                    i3 = findNextUnescapeIndex(bArr, i3, i);
                    if (i3 < i) {
                        int[] iArr = scratchEscapePositions;
                        if (iArr.length <= i4) {
                            scratchEscapePositions = Arrays.copyOf(iArr, iArr.length * 2);
                        }
                        scratchEscapePositions[i4] = i3;
                        i3 += 3;
                        i4++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            i2 = i - i4;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i4; i7++) {
                int i8 = scratchEscapePositions[i7] - i6;
                System.arraycopy(bArr, i6, bArr, i5, i8);
                int i9 = i5 + i8;
                int i10 = i9 + 1;
                bArr[i9] = 0;
                i5 = i9 + 2;
                bArr[i10] = 0;
                i6 += i8 + 3;
            }
            System.arraycopy(bArr, i6, bArr, i5, i2 - i5);
        }
        return i2;
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 < position) {
                int i4 = byteBuffer.get(i) & 255;
                if (i2 == 3) {
                    if (i4 == 1 && (byteBuffer.get(i3) & Ascii.US) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(i - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (i4 == 0) {
                    i2++;
                }
                if (i4 != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    @Deprecated
    public static boolean isNalUnitSei(String str, byte b) {
        return (MimeTypes.VIDEO_H264.equals(str) && (b & Ascii.US) == 6) || (MimeTypes.VIDEO_H265.equals(str) && ((b & 126) >> 1) == 39);
    }

    public static boolean isNalUnitSei(Format format, byte b) {
        return ((Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H264) || MimeTypes.containsCodecsCorrespondingToMimeType(format.codecs, MimeTypes.VIDEO_H264)) && (b & Ascii.US) == 6) || ((Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H265) || MimeTypes.containsCodecsCorrespondingToMimeType(format.codecs, MimeTypes.VIDEO_H265)) && ((b & 126) >> 1) == 39);
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & Ascii.US;
    }

    public static int numberOfBytesInNalUnitHeader(Format format) {
        if (Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H264)) {
            return 1;
        }
        return (Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H265) || MimeTypes.containsCodecsCorrespondingToMimeType(format.codecs, MimeTypes.VIDEO_H265)) ? 2 : 0;
    }

    public static boolean isDependedOn(byte[] bArr, int i, int i2, Format format) {
        if (Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H264)) {
            return isH264NalUnitDependedOn(bArr[i]);
        }
        if (Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H265)) {
            return isH265NalUnitDependedOn(bArr, i, i2, format);
        }
        return true;
    }

    private static boolean isH265NalUnitDependedOn(byte[] bArr, int i, int i2, Format format) {
        H265NalHeader parseH265NalHeader = parseH265NalHeader(new ParsableNalUnitBitArray(bArr, i, i2 + i));
        if (parseH265NalHeader.nalUnitType == 35) {
            return false;
        }
        return (parseH265NalHeader.nalUnitType <= 14 && parseH265NalHeader.nalUnitType % 2 == 0 && parseH265NalHeader.temporalId == format.maxSubLayers - 1) ? false : true;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    public static SpsData parseSpsNalUnit(byte[] bArr, int i, int i2) {
        return parseSpsNalUnitPayload(bArr, i + 1, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x021a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.media3.container.NalUnitUtil.SpsData parseSpsNalUnitPayload(byte[] r32, int r33, int r34) {
        /*
            Method dump skipped, instructions count: 607
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseSpsNalUnitPayload(byte[], int, int):androidx.media3.container.NalUnitUtil$SpsData");
    }

    public static H265VpsData parseH265VpsNalUnit(byte[] bArr, int i, int i2) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        return parseH265VpsNalUnitPayload(parsableNalUnitBitArray, parseH265NalHeader(parsableNalUnitBitArray));
    }

    private static H265NalHeader parseH265NalHeader(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBit();
        return new H265NalHeader(parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(3) - 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static H265VpsData parseH265VpsNalUnitPayload(ParsableNalUnitBitArray parsableNalUnitBitArray, H265NalHeader h265NalHeader) {
        int[] iArr;
        int i;
        int i2;
        int[] iArr2;
        H265VideoSignalInfosAndIndices h265VideoSignalInfosAndIndices;
        int i3;
        int i4;
        int i5;
        boolean[][] zArr;
        ImmutableList immutableList;
        int i6;
        boolean[][] zArr2;
        int[] iArr3;
        int i7;
        int i8;
        parsableNalUnitBitArray.skipBits(4);
        boolean readBit = parsableNalUnitBitArray.readBit();
        boolean readBit2 = parsableNalUnitBitArray.readBit();
        int readBits = parsableNalUnitBitArray.readBits(6);
        int i9 = readBits + 1;
        int readBits2 = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBits(17);
        H265ProfileTierLevel parseH265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray, true, readBits2, null);
        for (int i10 = parsableNalUnitBitArray.readBit() ? 0 : readBits2; i10 <= readBits2; i10++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        }
        int readBits3 = parsableNalUnitBitArray.readBits(6);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices = new H265ProfileTierLevelsAndIndices(ImmutableList.of(parseH265ProfileTierLevel), new int[1]);
        Object[] objArr = i9 >= 2 && readUnsignedExpGolombCodedInt >= 2;
        Object[] objArr2 = readBit && readBit2;
        int i11 = readBits3 + 1;
        Object[] objArr3 = i11 >= i9;
        if (objArr == false || objArr2 == false || objArr3 == false) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[][] iArr4 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, readUnsignedExpGolombCodedInt, i11);
        int[] iArr5 = new int[readUnsignedExpGolombCodedInt];
        int i12 = 1;
        int[] iArr6 = new int[readUnsignedExpGolombCodedInt];
        iArr4[0][0] = 0;
        iArr5[0] = 1;
        iArr6[0] = 0;
        for (int i13 = 1; i13 < readUnsignedExpGolombCodedInt; i13++) {
            int i14 = 0;
            for (int i15 = 0; i15 <= readBits3; i15++) {
                if (parsableNalUnitBitArray.readBit()) {
                    iArr4[i13][i14] = i15;
                    iArr6[i13] = i15;
                    i14++;
                }
                iArr5[i13] = i14;
            }
        }
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(64);
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int i16 = 0;
            while (i16 < readUnsignedExpGolombCodedInt2) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                skipH265HrdParameters(parsableNalUnitBitArray, i16 == 0 || parsableNalUnitBitArray.readBit(), readBits2);
                i16++;
            }
        }
        if (!parsableNalUnitBitArray.readBit()) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        parsableNalUnitBitArray.byteAlign();
        H265ProfileTierLevel parseH265ProfileTierLevel2 = parseH265ProfileTierLevel(parsableNalUnitBitArray, false, readBits2, parseH265ProfileTierLevel);
        boolean readBit3 = parsableNalUnitBitArray.readBit();
        int i17 = 6;
        boolean[] zArr3 = new boolean[16];
        int i18 = 0;
        for (int i19 = 0; i19 < 16; i19++) {
            boolean readBit4 = parsableNalUnitBitArray.readBit();
            zArr3[i19] = readBit4;
            if (readBit4) {
                i18++;
            }
        }
        if (i18 == 0 || !zArr3[1]) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[] iArr7 = new int[i18];
        for (int i20 = 0; i20 < i18 - (readBit3 ? 1 : 0); i20++) {
            iArr7[i20] = parsableNalUnitBitArray.readBits(3);
        }
        int[] iArr8 = new int[i18 + 1];
        if (readBit3) {
            int i21 = 1;
            while (i21 < i18) {
                int[] iArr9 = iArr8;
                for (int i22 = 0; i22 < i21; i22++) {
                    iArr9[i21] = iArr9[i21] + iArr7[i22] + 1;
                }
                i21++;
                iArr8 = iArr9;
            }
            iArr = iArr8;
            iArr[i18] = 6;
        } else {
            iArr = iArr8;
        }
        int[][] iArr10 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i9, i18);
        int[] iArr11 = new int[i9];
        iArr11[0] = 0;
        boolean readBit5 = parsableNalUnitBitArray.readBit();
        int i23 = 1;
        while (i23 < i9) {
            if (readBit5) {
                i8 = i23;
                iArr11[i8] = parsableNalUnitBitArray.readBits(i17);
            } else {
                i8 = i23;
                iArr11[i8] = i8;
            }
            if (readBit3) {
                for (int i24 = 0; i24 < i18; i24++) {
                    iArr10[i8][i24] = (iArr11[i8] & ((1 << iArr[r33]) - 1)) >> iArr[i24];
                }
            } else {
                int i25 = 0;
                while (i25 < i18) {
                    int i26 = i25;
                    iArr10[i8][i26] = parsableNalUnitBitArray.readBits(iArr7[i25] + 1);
                    i25 = i26 + 1;
                }
            }
            i23 = i8 + 1;
            i17 = 6;
        }
        int[] iArr12 = new int[i11];
        int i27 = 1;
        int i28 = 0;
        while (i28 < i9) {
            iArr12[iArr11[i28]] = -1;
            int[] iArr13 = iArr12;
            int i29 = 0;
            int i30 = 0;
            while (i29 < 16) {
                if (zArr3[i29]) {
                    if (i29 == i12) {
                        iArr13[iArr11[i28]] = iArr10[i28][i30];
                    }
                    i30++;
                }
                i29++;
                i12 = 1;
            }
            if (i28 > 0) {
                int i31 = 0;
                while (true) {
                    if (i31 >= i28) {
                        i27++;
                        break;
                    }
                    int i32 = i31;
                    if (iArr13[iArr11[i28]] == iArr13[iArr11[i31]]) {
                        break;
                    }
                    i31 = i32 + 1;
                }
            }
            i28++;
            iArr12 = iArr13;
            i12 = 1;
        }
        int[] iArr14 = iArr12;
        int readBits4 = parsableNalUnitBitArray.readBits(4);
        if (i27 < 2 || readBits4 == 0) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[] iArr15 = new int[i27];
        for (int i33 = 0; i33 < i27; i33++) {
            iArr15[i33] = parsableNalUnitBitArray.readBits(readBits4);
        }
        int[] iArr16 = new int[i11];
        int i34 = 0;
        while (i34 < i9) {
            int[] iArr17 = iArr16;
            iArr17[Math.min(iArr11[i34], readBits3)] = i34;
            i34++;
            iArr16 = iArr17;
        }
        int[] iArr18 = iArr16;
        ImmutableList.Builder builder = ImmutableList.builder();
        int i35 = 0;
        while (i35 <= readBits3) {
            int i36 = i27;
            int[] iArr19 = iArr15;
            int min = Math.min(iArr14[i35], i36 - 1);
            builder.add((ImmutableList.Builder) new H265LayerInfo(iArr18[i35], min >= 0 ? iArr19[min] : -1));
            i35++;
            i27 = i36;
            iArr15 = iArr19;
            iArr6 = iArr6;
        }
        int[] iArr20 = iArr6;
        ImmutableList build = builder.build();
        if (((H265LayerInfo) build.get(0)).viewId == -1) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int i37 = 1;
        while (true) {
            if (i37 > readBits3) {
                i = -1;
                i2 = -1;
                break;
            }
            i = -1;
            if (((H265LayerInfo) build.get(i37)).viewId != -1) {
                i2 = i37;
                break;
            }
            i37++;
        }
        if (i2 == i) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        boolean[][] zArr4 = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, i9, i9);
        boolean[][] zArr5 = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, i9, i9);
        for (int i38 = 1; i38 < i9; i38++) {
            for (int i39 = 0; i39 < i38; i39++) {
                boolean[] zArr6 = zArr4[i38];
                boolean[] zArr7 = zArr5[i38];
                boolean readBit6 = parsableNalUnitBitArray.readBit();
                zArr7[i39] = readBit6;
                zArr6[i39] = readBit6;
            }
        }
        for (int i40 = 1; i40 < i9; i40++) {
            int i41 = 0;
            while (i41 < readBits) {
                boolean[][] zArr8 = zArr5;
                int i42 = 0;
                while (true) {
                    if (i42 < i40) {
                        boolean[] zArr9 = zArr8[i40];
                        if (zArr9[i42] && zArr8[i42][i41]) {
                            zArr9[i41] = true;
                            break;
                        }
                        i42++;
                    }
                }
                i41++;
                zArr5 = zArr8;
            }
        }
        boolean[][] zArr10 = zArr5;
        int[] iArr21 = new int[i11];
        for (int i43 = 0; i43 < i9; i43++) {
            int i44 = 0;
            for (int i45 = 0; i45 < i43; i45++) {
                i44 += zArr4[i43][i45] ? 1 : 0;
            }
            iArr21[iArr11[i43]] = i44;
        }
        int i46 = 0;
        for (int i47 = 0; i47 < i9; i47++) {
            if (iArr21[iArr11[i47]] == 0) {
                i46++;
            }
        }
        if (i46 > 1) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[] iArr22 = new int[i9];
        int[] iArr23 = new int[readUnsignedExpGolombCodedInt];
        if (parsableNalUnitBitArray.readBit()) {
            iArr2 = iArr21;
            int i48 = 0;
            while (i48 < i9) {
                int i49 = i48;
                iArr22[i49] = parsableNalUnitBitArray.readBits(3);
                i48 = i49 + 1;
            }
        } else {
            iArr2 = iArr21;
            Arrays.fill(iArr22, 0, i9, readBits2);
        }
        int i50 = 0;
        while (i50 < readUnsignedExpGolombCodedInt) {
            int i51 = i50;
            int[] iArr24 = iArr11;
            int[] iArr25 = iArr22;
            int i52 = 0;
            for (int i53 = 0; i53 < iArr5[i51]; i53++) {
                i52 = Math.max(i52, iArr25[((H265LayerInfo) build.get(iArr4[i51][i53])).layerIdInVps]);
            }
            iArr23[i51] = i52 + 1;
            i50 = i51 + 1;
            iArr11 = iArr24;
            iArr22 = iArr25;
        }
        int[] iArr26 = iArr11;
        if (parsableNalUnitBitArray.readBit()) {
            int i54 = 0;
            while (i54 < readBits) {
                int i55 = i54 + 1;
                int i56 = i55;
                while (i56 < i9) {
                    if (zArr4[i56][i54]) {
                        i7 = i54;
                        parsableNalUnitBitArray.skipBits(3);
                    } else {
                        i7 = i54;
                    }
                    i56++;
                    i54 = i7;
                }
                i54 = i55;
            }
        }
        parsableNalUnitBitArray.skipBit();
        int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        ImmutableList.Builder builder2 = ImmutableList.builder();
        builder2.add((ImmutableList.Builder) parseH265ProfileTierLevel);
        if (readUnsignedExpGolombCodedInt3 > 1) {
            builder2.add((ImmutableList.Builder) parseH265ProfileTierLevel2);
            for (int i57 = 2; i57 < readUnsignedExpGolombCodedInt3; i57++) {
                parseH265ProfileTierLevel2 = parseH265ProfileTierLevel(parsableNalUnitBitArray, parsableNalUnitBitArray.readBit(), readBits2, parseH265ProfileTierLevel2);
                builder2.add((ImmutableList.Builder) parseH265ProfileTierLevel2);
            }
        }
        ImmutableList build2 = builder2.build();
        int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + readUnsignedExpGolombCodedInt;
        if (readUnsignedExpGolombCodedInt4 > readUnsignedExpGolombCodedInt) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int readBits5 = parsableNalUnitBitArray.readBits(2);
        boolean[][] zArr11 = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, readUnsignedExpGolombCodedInt4, i11);
        int[] iArr27 = new int[readUnsignedExpGolombCodedInt4];
        int i58 = 0;
        int[] iArr28 = new int[readUnsignedExpGolombCodedInt4];
        int i59 = 0;
        while (i59 < readUnsignedExpGolombCodedInt) {
            iArr27[i59] = i58;
            iArr28[i59] = iArr20[i59];
            if (readBits5 == 0) {
                i6 = i59;
                zArr2 = zArr11;
                immutableList = build2;
                iArr3 = iArr27;
                Arrays.fill(zArr11[i6], i58, iArr5[i6], true);
                iArr3[i6] = iArr5[i6];
            } else {
                immutableList = build2;
                i6 = i59;
                zArr2 = zArr11;
                iArr3 = iArr27;
                if (readBits5 == 1) {
                    int i60 = iArr20[i6];
                    for (int i61 = 0; i61 < iArr5[i6]; i61++) {
                        zArr2[i6][i61] = iArr4[i6][i61] == i60;
                    }
                    iArr3[i6] = 1;
                } else {
                    i58 = 0;
                    zArr2[0][0] = true;
                    iArr3[0] = 1;
                    i59 = i6 + 1;
                    zArr11 = zArr2;
                    iArr27 = iArr3;
                    build2 = immutableList;
                }
            }
            i58 = 0;
            i59 = i6 + 1;
            zArr11 = zArr2;
            iArr27 = iArr3;
            build2 = immutableList;
        }
        ImmutableList immutableList2 = build2;
        boolean[][] zArr12 = zArr11;
        int[] iArr29 = iArr27;
        int[] iArr30 = new int[i11];
        int i62 = 2;
        int[] iArr31 = new int[2];
        iArr31[1] = i11;
        iArr31[i58] = readUnsignedExpGolombCodedInt4;
        boolean[][] zArr13 = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, iArr31);
        int i63 = 1;
        int i64 = 0;
        while (i63 < readUnsignedExpGolombCodedInt4) {
            int i65 = readBits5;
            if (readBits5 == i62) {
                for (int i66 = 0; i66 < iArr5[i63]; i66++) {
                    zArr12[i63][i66] = parsableNalUnitBitArray.readBit();
                    int i67 = iArr29[i63];
                    boolean z = zArr12[i63][i66];
                    iArr29[i63] = i67 + (z ? 1 : 0);
                    if (z) {
                        iArr28[i63] = iArr4[i63][i66];
                    }
                }
            }
            if (i64 == 0) {
                i3 = 0;
                if (iArr4[i63][0] == 0 && zArr12[i63][0]) {
                    for (int i68 = 1; i68 < iArr5[i63]; i68++) {
                        if (iArr4[i63][i68] == i2 && zArr12[i63][i2]) {
                            i64 = i63;
                        }
                    }
                }
            } else {
                i3 = 0;
            }
            int i69 = i3;
            while (i69 < iArr5[i63]) {
                if (readUnsignedExpGolombCodedInt3 > 1) {
                    zArr13[i63][i69] = zArr12[i63][i69];
                    zArr = zArr4;
                    i4 = readUnsignedExpGolombCodedInt3;
                    int log2 = DoubleMath.log2(readUnsignedExpGolombCodedInt3, RoundingMode.CEILING);
                    if (!zArr13[i63][i69]) {
                        int i70 = ((H265LayerInfo) build.get(iArr4[i63][i69])).layerIdInVps;
                        int i71 = i3;
                        while (i71 < i69) {
                            i5 = i2;
                            if (zArr10[i70][((H265LayerInfo) build.get(iArr4[i63][i71])).layerIdInVps]) {
                                zArr13[i63][i69] = true;
                                break;
                            }
                            i71++;
                            i2 = i5;
                        }
                    }
                    i5 = i2;
                    if (zArr13[i63][i69]) {
                        if (i64 > 0 && i63 == i64) {
                            iArr30[i69] = parsableNalUnitBitArray.readBits(log2);
                        } else {
                            parsableNalUnitBitArray.skipBits(log2);
                        }
                    }
                } else {
                    i4 = readUnsignedExpGolombCodedInt3;
                    i5 = i2;
                    zArr = zArr4;
                }
                i69++;
                zArr4 = zArr;
                readUnsignedExpGolombCodedInt3 = i4;
                i2 = i5;
            }
            int i72 = readUnsignedExpGolombCodedInt3;
            int i73 = i2;
            boolean[][] zArr14 = zArr4;
            if (iArr29[i63] == 1 && iArr2[iArr28[i63]] > 0) {
                parsableNalUnitBitArray.skipBit();
            }
            i63++;
            readBits5 = i65;
            zArr4 = zArr14;
            readUnsignedExpGolombCodedInt3 = i72;
            i2 = i73;
            i62 = 2;
        }
        boolean[][] zArr15 = zArr4;
        if (i64 == 0) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        H265RepFormatsAndIndices parseH265RepFormatsAndIndices = parseH265RepFormatsAndIndices(parsableNalUnitBitArray, i9);
        parsableNalUnitBitArray.skipBits(2);
        for (int i74 = 1; i74 < i9; i74++) {
            if (iArr2[iArr26[i74]] == 0) {
                parsableNalUnitBitArray.skipBit();
            }
        }
        skipH265DpbSize(parsableNalUnitBitArray, readUnsignedExpGolombCodedInt4, iArr23, iArr5, zArr13);
        skipToH265VuiPresentFlagAfterDpbSize(parsableNalUnitBitArray, i9, zArr15);
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.byteAlign();
            h265VideoSignalInfosAndIndices = parseH265VideoSignalInfosAndIndices(parsableNalUnitBitArray, i9, readUnsignedExpGolombCodedInt, iArr23);
        } else {
            h265VideoSignalInfosAndIndices = null;
        }
        return new H265VpsData(h265NalHeader, build, new H265ProfileTierLevelsAndIndices(immutableList2, iArr30), parseH265RepFormatsAndIndices, h265VideoSignalInfosAndIndices);
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] bArr, int i, int i2, H265VpsData h265VpsData) {
        return parseH265SpsNalUnitPayload(bArr, i + 2, i2, parseH265NalHeader(new ParsableNalUnitBitArray(bArr, i, i2)), h265VpsData);
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0214  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.media3.container.NalUnitUtil.H265SpsData parseH265SpsNalUnitPayload(byte[] r20, int r21, int r22, androidx.media3.container.NalUnitUtil.H265NalHeader r23, androidx.media3.container.NalUnitUtil.H265VpsData r24) {
        /*
            Method dump skipped, instructions count: 636
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseH265SpsNalUnitPayload(byte[], int, int, androidx.media3.container.NalUnitUtil$H265NalHeader, androidx.media3.container.NalUnitUtil$H265VpsData):androidx.media3.container.NalUnitUtil$H265SpsData");
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i, int i2) {
        return parsePpsNalUnitPayload(bArr, i + 1, i2);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] bArr, int i, int i2) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    public static H265Sei3dRefDisplayInfoData parseH265Sei3dRefDisplayInfo(byte[] bArr, int i, int i2) {
        byte b;
        int max;
        int max2;
        int i3 = i + 2;
        int i4 = i2 - 1;
        while (true) {
            b = bArr[i4];
            if (b != 0 || i4 <= i3) {
                break;
            }
            i4--;
        }
        if (b != 0 && i4 > i3) {
            ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i3, i4 + 1);
            while (parsableNalUnitBitArray.canReadBits(16)) {
                int readBits = parsableNalUnitBitArray.readBits(8);
                int i5 = 0;
                while (readBits == 255) {
                    i5 += 255;
                    readBits = parsableNalUnitBitArray.readBits(8);
                }
                int i6 = i5 + readBits;
                int readBits2 = parsableNalUnitBitArray.readBits(8);
                int i7 = 0;
                while (readBits2 == 255) {
                    i7 += 255;
                    readBits2 = parsableNalUnitBitArray.readBits(8);
                }
                int i8 = i7 + readBits2;
                if (i8 == 0 || !parsableNalUnitBitArray.canReadBits(i8)) {
                    break;
                }
                if (i6 == 176) {
                    int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    boolean readBit = parsableNalUnitBitArray.readBit();
                    int readUnsignedExpGolombCodedInt2 = readBit ? parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() : 0;
                    int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    int i9 = -1;
                    int i10 = -1;
                    int i11 = -1;
                    int i12 = -1;
                    int i13 = -1;
                    int i14 = -1;
                    for (int i15 = 0; i15 <= readUnsignedExpGolombCodedInt3; i15++) {
                        i9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        i10 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        i11 = parsableNalUnitBitArray.readBits(6);
                        if (i11 == 63) {
                            return null;
                        }
                        if (i11 == 0) {
                            max = Math.max(0, readUnsignedExpGolombCodedInt - 30);
                        } else {
                            max = Math.max(0, (i11 + readUnsignedExpGolombCodedInt) - 31);
                        }
                        i12 = parsableNalUnitBitArray.readBits(max);
                        if (readBit) {
                            int readBits3 = parsableNalUnitBitArray.readBits(6);
                            if (readBits3 == 63) {
                                return null;
                            }
                            if (readBits3 == 0) {
                                max2 = Math.max(0, readUnsignedExpGolombCodedInt2 - 30);
                            } else {
                                max2 = Math.max(0, (readBits3 + readUnsignedExpGolombCodedInt2) - 31);
                            }
                            i13 = readBits3;
                            i14 = parsableNalUnitBitArray.readBits(max2);
                        }
                        if (parsableNalUnitBitArray.readBit()) {
                            parsableNalUnitBitArray.skipBits(10);
                        }
                    }
                    return new H265Sei3dRefDisplayInfoData(readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2, readUnsignedExpGolombCodedInt3 + 1, i9, i10, i11, i12, i13, i14);
                }
                parsableNalUnitBitArray.skipBits(i8 * 8);
            }
        }
        return null;
    }

    public static int findNalUnit(byte[] bArr, int i, int i2, boolean[] zArr) {
        int i3 = i2 - i;
        Assertions.checkState(i3 >= 0);
        if (i3 == 0) {
            return i2;
        }
        if (zArr[0]) {
            clearPrefixFlags(zArr);
            return i - 3;
        }
        if (i3 > 1 && zArr[1] && bArr[i] == 1) {
            clearPrefixFlags(zArr);
            return i - 2;
        }
        if (i3 > 2 && zArr[2] && bArr[i] == 0 && bArr[i + 1] == 1) {
            clearPrefixFlags(zArr);
            return i - 1;
        }
        int i4 = i2 - 1;
        int i5 = i + 2;
        while (i5 < i4) {
            byte b = bArr[i5];
            if ((b & 254) == 0) {
                int i6 = i5 - 2;
                if (bArr[i6] == 0 && bArr[i5 - 1] == 0 && b == 1) {
                    clearPrefixFlags(zArr);
                    return i6;
                }
                i5 -= 2;
            }
            i5 += 3;
        }
        zArr[0] = i3 <= 2 ? !(i3 != 2 ? !(zArr[1] && bArr[i4] == 1) : !(zArr[2] && bArr[i2 + (-2)] == 0 && bArr[i4] == 1)) : bArr[i2 + (-3)] == 0 && bArr[i2 + (-2)] == 0 && bArr[i4] == 1;
        zArr[1] = i3 <= 1 ? zArr[2] && bArr[i4] == 0 : bArr[i2 + (-2)] == 0 && bArr[i4] == 0;
        zArr[2] = bArr[i4] == 0;
        return i2;
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public static String getH265BaseLayerCodecsString(List<byte[]> list) {
        for (int i = 0; i < list.size(); i++) {
            byte[] bArr = list.get(i);
            int length = bArr.length;
            if (length > 3) {
                ImmutableList<Integer> findNalUnitPositions = findNalUnitPositions(bArr);
                for (int i2 = 0; i2 < findNalUnitPositions.size(); i2++) {
                    if (findNalUnitPositions.get(i2).intValue() + 3 < length) {
                        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, findNalUnitPositions.get(i2).intValue() + 3, length);
                        H265NalHeader parseH265NalHeader = parseH265NalHeader(parsableNalUnitBitArray);
                        if (parseH265NalHeader.nalUnitType == 33 && parseH265NalHeader.layerId == 0) {
                            return createCodecStringFromH265SpsPalyoad(parsableNalUnitBitArray);
                        }
                    }
                }
            }
        }
        return null;
    }

    private static ImmutableList<Integer> findNalUnitPositions(byte[] bArr) {
        boolean[] zArr = new boolean[3];
        ImmutableList.Builder builder = ImmutableList.builder();
        int i = 0;
        while (i < bArr.length) {
            int findNalUnit = findNalUnit(bArr, i, bArr.length, zArr);
            if (findNalUnit != bArr.length) {
                builder.add((ImmutableList.Builder) Integer.valueOf(findNalUnit));
            }
            i = findNalUnit + 3;
        }
        return builder.build();
    }

    private static String createCodecStringFromH265SpsPalyoad(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBits(4);
        int readBits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        H265ProfileTierLevel parseH265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray, true, readBits, null);
        return CodecSpecificDataUtil.buildHevcCodecString(parseH265ProfileTierLevel.generalProfileSpace, parseH265ProfileTierLevel.generalTierFlag, parseH265ProfileTierLevel.generalProfileIdc, parseH265ProfileTierLevel.generalProfileCompatibilityFlags, parseH265ProfileTierLevel.constraintBytes, parseH265ProfileTierLevel.generalLevelIdc);
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == 0 && bArr[i + 1] == 0 && bArr[i + 2] == 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [int] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    private static void skipH265HrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray, boolean z, int i) {
        boolean z2;
        boolean readBit;
        if (z) {
            boolean readBit2 = parsableNalUnitBitArray.readBit();
            boolean readBit3 = parsableNalUnitBitArray.readBit();
            if (readBit2 || readBit3) {
                z2 = parsableNalUnitBitArray.readBit();
                if (z2) {
                    parsableNalUnitBitArray.skipBits(19);
                }
                parsableNalUnitBitArray.skipBits(8);
                if (z2) {
                    parsableNalUnitBitArray.skipBits(4);
                }
                parsableNalUnitBitArray.skipBits(15);
                r1 = readBit3;
                r9 = readBit2;
            } else {
                z2 = false;
                r1 = readBit3;
                r9 = readBit2;
            }
        } else {
            r9 = 0;
            r1 = 0;
            z2 = false;
        }
        for (int i2 = 0; i2 <= i; i2++) {
            boolean readBit4 = parsableNalUnitBitArray.readBit();
            if (!readBit4) {
                readBit4 = parsableNalUnitBitArray.readBit();
            }
            if (readBit4) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                readBit = false;
            } else {
                readBit = parsableNalUnitBitArray.readBit();
            }
            int readUnsignedExpGolombCodedInt = !readBit ? parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() : 0;
            int i3 = r9 + r1;
            for (int i4 = 0; i4 < i3; i4++) {
                for (int i5 = 0; i5 <= readUnsignedExpGolombCodedInt; i5++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    if (z2) {
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    }
                    parsableNalUnitBitArray.skipBit();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.container.NalUnitUtil.H265ProfileTierLevel parseH265ProfileTierLevel(androidx.media3.container.ParsableNalUnitBitArray r19, boolean r20, int r21, androidx.media3.container.NalUnitUtil.H265ProfileTierLevel r22) {
        /*
            r0 = r19
            r1 = r21
            r2 = r22
            r3 = 6
            int[] r4 = new int[r3]
            r5 = 2
            r6 = 8
            r7 = 0
            if (r20 == 0) goto L3c
            int r2 = r0.readBits(r5)
            boolean r8 = r0.readBit()
            r9 = 5
            int r9 = r0.readBits(r9)
            r10 = r7
            r11 = r10
        L1e:
            r12 = 32
            if (r10 >= r12) goto L2e
            boolean r12 = r0.readBit()
            if (r12 == 0) goto L2b
            r12 = 1
            int r12 = r12 << r10
            r11 = r11 | r12
        L2b:
            int r10 = r10 + 1
            goto L1e
        L2e:
            r10 = r7
        L2f:
            if (r10 >= r3) goto L3a
            int r12 = r0.readBits(r6)
            r4[r10] = r12
            int r10 = r10 + 1
            goto L2f
        L3a:
            r13 = r2
            goto L49
        L3c:
            if (r2 == 0) goto L50
            int r3 = r2.generalProfileSpace
            boolean r8 = r2.generalTierFlag
            int r9 = r2.generalProfileIdc
            int r11 = r2.generalProfileCompatibilityFlags
            int[] r4 = r2.constraintBytes
            r13 = r3
        L49:
            r17 = r4
            r14 = r8
            r15 = r9
            r16 = r11
            goto L57
        L50:
            r17 = r4
            r13 = r7
            r14 = r13
            r15 = r14
            r16 = r15
        L57:
            int r18 = r0.readBits(r6)
            r2 = r7
        L5c:
            if (r7 >= r1) goto L71
            boolean r3 = r0.readBit()
            if (r3 == 0) goto L66
            int r2 = r2 + 88
        L66:
            boolean r3 = r0.readBit()
            if (r3 == 0) goto L6e
            int r2 = r2 + 8
        L6e:
            int r7 = r7 + 1
            goto L5c
        L71:
            r0.skipBits(r2)
            if (r1 <= 0) goto L7b
            int r6 = r6 - r1
            int r6 = r6 * r5
            r0.skipBits(r6)
        L7b:
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r12 = new androidx.media3.container.NalUnitUtil$H265ProfileTierLevel
            r12.<init>(r13, r14, r15, r16, r17, r18)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseH265ProfileTierLevel(androidx.media3.container.ParsableNalUnitBitArray, boolean, int, androidx.media3.container.NalUnitUtil$H265ProfileTierLevel):androidx.media3.container.NalUnitUtil$H265ProfileTierLevel");
    }

    private static H265RepFormatsAndIndices parseH265RepFormatsAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int i2 = readUnsignedExpGolombCodedInt + 1;
        ImmutableList.Builder builderWithExpectedSize = ImmutableList.builderWithExpectedSize(i2);
        int[] iArr = new int[i];
        for (int i3 = 0; i3 < i2; i3++) {
            builderWithExpectedSize.add((ImmutableList.Builder) parseH265RepFormat(parsableNalUnitBitArray));
        }
        int i4 = 1;
        if (i2 <= 1 || !parsableNalUnitBitArray.readBit()) {
            while (i4 < i) {
                iArr[i4] = Math.min(i4, readUnsignedExpGolombCodedInt);
                i4++;
            }
        } else {
            int log2 = DoubleMath.log2(i2, RoundingMode.CEILING);
            while (i4 < i) {
                iArr[i4] = parsableNalUnitBitArray.readBits(log2);
                i4++;
            }
        }
        return new H265RepFormatsAndIndices(builderWithExpectedSize.build(), iArr);
    }

    private static H265RepFormat parseH265RepFormat(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int i;
        int i2;
        int i3;
        int readBits = parsableNalUnitBitArray.readBits(16);
        int readBits2 = parsableNalUnitBitArray.readBits(16);
        if (parsableNalUnitBitArray.readBit()) {
            int readBits3 = parsableNalUnitBitArray.readBits(2);
            if (readBits3 == 3) {
                parsableNalUnitBitArray.skipBit();
            }
            int readBits4 = parsableNalUnitBitArray.readBits(4);
            i3 = parsableNalUnitBitArray.readBits(4);
            i2 = readBits4;
            i = readBits3;
        } else {
            i = 0;
            i2 = 0;
            i3 = 0;
        }
        if (parsableNalUnitBitArray.readBit()) {
            int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            readBits = applyConformanceWindowToWidth(readBits, i, readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2);
            readBits2 = applyConformanceWindowToHeight(readBits2, i, readUnsignedExpGolombCodedInt3, readUnsignedExpGolombCodedInt4);
        }
        return new H265RepFormat(i, i2, i3, readBits, readBits2);
    }

    private static void skipH265DpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i, int[] iArr, int[] iArr2, boolean[][] zArr) {
        boolean z;
        for (int i2 = 1; i2 < i; i2++) {
            boolean readBit = parsableNalUnitBitArray.readBit();
            int i3 = 0;
            while (i3 < iArr[i2]) {
                if (i3 <= 0 || !readBit) {
                    z = i3 == 0;
                } else {
                    z = parsableNalUnitBitArray.readBit();
                }
                if (z) {
                    for (int i4 = 0; i4 < iArr2[i2]; i4++) {
                        if (zArr[i2][i4]) {
                            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        }
                    }
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                i3++;
            }
        }
    }

    private static void skipToH265VuiPresentFlagAfterDpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i, boolean[][] zArr) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 2;
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(readUnsignedExpGolombCodedInt);
        } else {
            for (int i2 = 1; i2 < i; i2++) {
                for (int i3 = 0; i3 < i2; i3++) {
                    if (zArr[i2][i3]) {
                        parsableNalUnitBitArray.skipBits(readUnsignedExpGolombCodedInt);
                    }
                }
            }
        }
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        for (int i4 = 1; i4 <= readUnsignedExpGolombCodedInt2; i4++) {
            parsableNalUnitBitArray.skipBits(8);
        }
    }

    private static H265VideoSignalInfosAndIndices parseH265VideoSignalInfosAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i, int i2, int[] iArr) {
        if (!parsableNalUnitBitArray.readBit() ? parsableNalUnitBitArray.readBit() : true) {
            parsableNalUnitBitArray.skipBit();
        }
        boolean readBit = parsableNalUnitBitArray.readBit();
        boolean readBit2 = parsableNalUnitBitArray.readBit();
        if (readBit || readBit2) {
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < iArr[i3]; i4++) {
                    boolean readBit3 = readBit ? parsableNalUnitBitArray.readBit() : false;
                    boolean readBit4 = readBit2 ? parsableNalUnitBitArray.readBit() : false;
                    if (readBit3) {
                        parsableNalUnitBitArray.skipBits(32);
                    }
                    if (readBit4) {
                        parsableNalUnitBitArray.skipBits(18);
                    }
                }
            }
        }
        boolean readBit5 = parsableNalUnitBitArray.readBit();
        int readBits = readBit5 ? parsableNalUnitBitArray.readBits(4) + 1 : i;
        ImmutableList.Builder builderWithExpectedSize = ImmutableList.builderWithExpectedSize(readBits);
        int[] iArr2 = new int[i];
        for (int i5 = 0; i5 < readBits; i5++) {
            builderWithExpectedSize.add((ImmutableList.Builder) parseH265VideoSignalInfo(parsableNalUnitBitArray));
        }
        if (readBit5 && readBits > 1) {
            for (int i6 = 0; i6 < i; i6++) {
                iArr2[i6] = parsableNalUnitBitArray.readBits(4);
            }
        }
        return new H265VideoSignalInfosAndIndices(builderWithExpectedSize.build(), iArr2);
    }

    private static H265VideoSignalInfo parseH265VideoSignalInfo(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBits(3);
        int i = parsableNalUnitBitArray.readBit() ? 1 : 2;
        int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(parsableNalUnitBitArray.readBits(8));
        int isoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(parsableNalUnitBitArray.readBits(8));
        parsableNalUnitBitArray.skipBits(8);
        return new H265VideoSignalInfo(isoColorPrimariesToColorSpace, i, isoTransferCharacteristicsToColorTransfer);
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int i2 = 8;
        int i3 = 8;
        for (int i4 = 0; i4 < i; i4++) {
            if (i2 != 0) {
                i2 = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i3) + 256) % 256;
            }
            if (i2 != 0) {
                i3 = i2;
            }
        }
    }

    private static void skipHrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        parsableNalUnitBitArray.skipBits(8);
        for (int i = 0; i < readUnsignedExpGolombCodedInt; i++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBits(20);
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        for (int i = 0; i < 4; i++) {
            int i2 = 0;
            while (i2 < 6) {
                int i3 = 1;
                if (!parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                } else {
                    int min = Math.min(64, 1 << ((i << 1) + 4));
                    if (i > 1) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i4 = 0; i4 < min; i4++) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                }
                if (i == 3) {
                    i3 = 3;
                }
                i2 += i3;
            }
        }
    }

    private static void skipH265ShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int[] iArr = new int[0];
        int[] iArr2 = new int[0];
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < readUnsignedExpGolombCodedInt; i3++) {
            if (i3 != 0 && parsableNalUnitBitArray.readBit()) {
                int i4 = i + i2;
                int readUnsignedExpGolombCodedInt2 = (1 - ((parsableNalUnitBitArray.readBit() ? 1 : 0) * 2)) * (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                int i5 = i4 + 1;
                boolean[] zArr = new boolean[i5];
                for (int i6 = 0; i6 <= i4; i6++) {
                    if (!parsableNalUnitBitArray.readBit()) {
                        zArr[i6] = parsableNalUnitBitArray.readBit();
                    } else {
                        zArr[i6] = true;
                    }
                }
                int[] iArr3 = new int[i5];
                int[] iArr4 = new int[i5];
                int i7 = 0;
                for (int i8 = i2 - 1; i8 >= 0; i8--) {
                    int i9 = iArr2[i8] + readUnsignedExpGolombCodedInt2;
                    if (i9 < 0 && zArr[i + i8]) {
                        iArr3[i7] = i9;
                        i7++;
                    }
                }
                if (readUnsignedExpGolombCodedInt2 < 0 && zArr[i4]) {
                    iArr3[i7] = readUnsignedExpGolombCodedInt2;
                    i7++;
                }
                for (int i10 = 0; i10 < i; i10++) {
                    int i11 = iArr[i10] + readUnsignedExpGolombCodedInt2;
                    if (i11 < 0 && zArr[i10]) {
                        iArr3[i7] = i11;
                        i7++;
                    }
                }
                int[] copyOf = Arrays.copyOf(iArr3, i7);
                int i12 = 0;
                for (int i13 = i - 1; i13 >= 0; i13--) {
                    int i14 = iArr[i13] + readUnsignedExpGolombCodedInt2;
                    if (i14 > 0 && zArr[i13]) {
                        iArr4[i12] = i14;
                        i12++;
                    }
                }
                if (readUnsignedExpGolombCodedInt2 > 0 && zArr[i4]) {
                    iArr4[i12] = readUnsignedExpGolombCodedInt2;
                    i12++;
                }
                for (int i15 = 0; i15 < i2; i15++) {
                    int i16 = iArr2[i15] + readUnsignedExpGolombCodedInt2;
                    if (i16 > 0 && zArr[i + i15]) {
                        iArr4[i12] = i16;
                        i12++;
                    }
                }
                iArr2 = Arrays.copyOf(iArr4, i12);
                iArr = copyOf;
                i = i7;
                i2 = i12;
            } else {
                int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int[] iArr5 = new int[readUnsignedExpGolombCodedInt3];
                int i17 = 0;
                while (i17 < readUnsignedExpGolombCodedInt3) {
                    iArr5[i17] = (i17 > 0 ? iArr5[i17 - 1] : 0) - (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                    parsableNalUnitBitArray.skipBit();
                    i17++;
                }
                int[] iArr6 = new int[readUnsignedExpGolombCodedInt4];
                int i18 = 0;
                while (i18 < readUnsignedExpGolombCodedInt4) {
                    iArr6[i18] = (i18 > 0 ? iArr6[i18 - 1] : 0) + parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
                    parsableNalUnitBitArray.skipBit();
                    i18++;
                }
                i = readUnsignedExpGolombCodedInt3;
                iArr = iArr5;
                i2 = readUnsignedExpGolombCodedInt4;
                iArr2 = iArr6;
            }
        }
    }

    private NalUnitUtil() {
    }
}
