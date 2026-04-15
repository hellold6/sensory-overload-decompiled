package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class HevcConfig {
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int decodedHeight;
    public final int decodedWidth;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderPics;
    public final int maxSubLayers;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int stereoMode;
    public final NalUnitUtil.H265VpsData vpsData;
    public final int width;

    public static HevcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        return parseImpl(parsableByteArray, false, null);
    }

    public static HevcConfig parseLayered(ParsableByteArray parsableByteArray, NalUnitUtil.H265VpsData h265VpsData) throws ParserException {
        return parseImpl(parsableByteArray, true, h265VpsData);
    }

    private static HevcConfig parseImpl(ParsableByteArray parsableByteArray, boolean z, NalUnitUtil.H265VpsData h265VpsData) throws ParserException {
        boolean z2;
        int i;
        NalUnitUtil.H265Sei3dRefDisplayInfoData parseH265Sei3dRefDisplayInfo;
        try {
            if (z) {
                parsableByteArray.skipBytes(4);
            } else {
                parsableByteArray.skipBytes(21);
            }
            int readUnsignedByte = parsableByteArray.readUnsignedByte() & 3;
            int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                z2 = true;
                if (i2 >= readUnsignedByte2) {
                    break;
                }
                parsableByteArray.skipBytes(1);
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                for (int i4 = 0; i4 < readUnsignedShort; i4++) {
                    int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
                    i3 += readUnsignedShort2 + 4;
                    parsableByteArray.skipBytes(readUnsignedShort2);
                }
                i2++;
            }
            parsableByteArray.setPosition(position);
            byte[] bArr = new byte[i3];
            NalUnitUtil.H265VpsData h265VpsData2 = h265VpsData;
            int i5 = -1;
            int i6 = -1;
            int i7 = -1;
            int i8 = -1;
            int i9 = -1;
            int i10 = -1;
            int i11 = -1;
            int i12 = -1;
            int i13 = -1;
            int i14 = -1;
            int i15 = -1;
            int i16 = -1;
            float f = 1.0f;
            String str = null;
            int i17 = 0;
            int i18 = 0;
            while (i17 < readUnsignedByte2) {
                int readUnsignedByte3 = parsableByteArray.readUnsignedByte() & 63;
                int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
                NalUnitUtil.H265VpsData h265VpsData3 = h265VpsData2;
                int i19 = 0;
                while (i19 < readUnsignedShort3) {
                    int readUnsignedShort4 = parsableByteArray.readUnsignedShort();
                    boolean z3 = z2;
                    int i20 = readUnsignedByte;
                    System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, i18, NalUnitUtil.NAL_START_CODE.length);
                    int length = i18 + NalUnitUtil.NAL_START_CODE.length;
                    System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), bArr, length, readUnsignedShort4);
                    if (readUnsignedByte3 == 32 && i19 == 0) {
                        h265VpsData3 = NalUnitUtil.parseH265VpsNalUnit(bArr, length, length + readUnsignedShort4);
                        i = readUnsignedByte2;
                    } else if (readUnsignedByte3 == 33 && i19 == 0) {
                        NalUnitUtil.H265SpsData parseH265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr, length, length + readUnsignedShort4, h265VpsData3);
                        i5 = parseH265SpsNalUnit.maxSubLayersMinus1 + 1;
                        i6 = parseH265SpsNalUnit.width;
                        int i21 = parseH265SpsNalUnit.height;
                        int i22 = parseH265SpsNalUnit.decodedWidth;
                        i = readUnsignedByte2;
                        int i23 = parseH265SpsNalUnit.decodedHeight;
                        i10 = parseH265SpsNalUnit.bitDepthLumaMinus8 + 8;
                        i11 = parseH265SpsNalUnit.bitDepthChromaMinus8 + 8;
                        int i24 = parseH265SpsNalUnit.colorSpace;
                        int i25 = parseH265SpsNalUnit.colorRange;
                        int i26 = parseH265SpsNalUnit.colorTransfer;
                        float f2 = parseH265SpsNalUnit.pixelWidthHeightRatio;
                        int i27 = parseH265SpsNalUnit.maxNumReorderPics;
                        if (parseH265SpsNalUnit.profileTierLevel != null) {
                            str = CodecSpecificDataUtil.buildHevcCodecString(parseH265SpsNalUnit.profileTierLevel.generalProfileSpace, parseH265SpsNalUnit.profileTierLevel.generalTierFlag, parseH265SpsNalUnit.profileTierLevel.generalProfileIdc, parseH265SpsNalUnit.profileTierLevel.generalProfileCompatibilityFlags, parseH265SpsNalUnit.profileTierLevel.constraintBytes, parseH265SpsNalUnit.profileTierLevel.generalLevelIdc);
                        }
                        f = f2;
                        i16 = i27;
                        i13 = i25;
                        i14 = i26;
                        i9 = i23;
                        i12 = i24;
                        i7 = i21;
                        i8 = i22;
                    } else {
                        i = readUnsignedByte2;
                        if (readUnsignedByte3 == 39 && i19 == 0 && (parseH265Sei3dRefDisplayInfo = NalUnitUtil.parseH265Sei3dRefDisplayInfo(bArr, length, length + readUnsignedShort4)) != null && h265VpsData3 != null) {
                            i15 = parseH265Sei3dRefDisplayInfo.leftViewId == h265VpsData3.layerInfos.get(0).viewId ? 4 : 5;
                            i18 = length + readUnsignedShort4;
                            parsableByteArray.skipBytes(readUnsignedShort4);
                            i19++;
                            z2 = z3;
                            readUnsignedByte = i20;
                            readUnsignedByte2 = i;
                        }
                    }
                    i18 = length + readUnsignedShort4;
                    parsableByteArray.skipBytes(readUnsignedShort4);
                    i19++;
                    z2 = z3;
                    readUnsignedByte = i20;
                    readUnsignedByte2 = i;
                }
                i17++;
                h265VpsData2 = h265VpsData3;
            }
            return new HevcConfig(i3 == 0 ? Collections.emptyList() : Collections.singletonList(bArr), readUnsignedByte + 1, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, f, i16, str, h265VpsData2);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ParserException.createForMalformedContainer("Error parsing".concat(z ? "L-HEVC config" : "HEVC config"), e);
        }
    }

    private HevcConfig(List<byte[]> list, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, float f, int i13, String str, NalUnitUtil.H265VpsData h265VpsData) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i;
        this.maxSubLayers = i2;
        this.width = i3;
        this.height = i4;
        this.decodedWidth = i5;
        this.decodedHeight = i6;
        this.bitdepthLuma = i7;
        this.bitdepthChroma = i8;
        this.colorSpace = i9;
        this.colorRange = i10;
        this.colorTransfer = i11;
        this.stereoMode = i12;
        this.pixelWidthHeightRatio = f;
        this.maxNumReorderPics = i13;
        this.codecs = str;
        this.vpsData = h265VpsData;
    }
}
