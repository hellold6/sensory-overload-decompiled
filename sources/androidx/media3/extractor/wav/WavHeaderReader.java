package androidx.media3.extractor.wav;

import android.util.Pair;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.WavUtil;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
final class WavHeaderReader {
    private static final String TAG = "WavHeaderReader";
    private static final byte[] WAVEEXT_SUBFORMAT = {0, 0, 0, 0, Ascii.DLE, 0, Byte.MIN_VALUE, 0, 0, -86, 0, 56, -101, 113};
    private static final byte[] AMBISONIC_SUBFORMAT = {0, 0, 33, 7, -45, 17, -122, 68, -56, -63, -54, 0, 0, 0};

    public static boolean checkFileType(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        if (peek.id != 1380533830 && peek.id != 1380333108) {
            return false;
        }
        extractorInput.peekFully(parsableByteArray.getData(), 0, 4);
        parsableByteArray.setPosition(0);
        int readInt = parsableByteArray.readInt();
        if (readInt == 1463899717) {
            return true;
        }
        Log.e(TAG, "Unsupported form type: " + readInt);
        return false;
    }

    public static long readRf64SampleDataSize(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        if (peek.id != 1685272116) {
            extractorInput.resetPeekPosition();
            return -1L;
        }
        extractorInput.advancePeekPosition(8);
        parsableByteArray.setPosition(0);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 8);
        long readLittleEndianLong = parsableByteArray.readLittleEndianLong();
        extractorInput.skipFully(((int) peek.size) + 8);
        return readLittleEndianLong;
    }

    public static WavFormat readFormat(ExtractorInput extractorInput) throws IOException {
        byte[] bArr;
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        ChunkHeader skipToChunk = skipToChunk(WavUtil.FMT_FOURCC, extractorInput, parsableByteArray);
        Assertions.checkState(skipToChunk.size >= 16);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 16);
        parsableByteArray.setPosition(0);
        int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianUnsignedShort2 = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianUnsignedIntToInt = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readLittleEndianUnsignedIntToInt2 = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readLittleEndianUnsignedShort3 = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianUnsignedShort4 = parsableByteArray.readLittleEndianUnsignedShort();
        int i = ((int) skipToChunk.size) - 16;
        if (i > 0) {
            bArr = new byte[i];
            extractorInput.peekFully(bArr, 0, i);
            if (readLittleEndianUnsignedShort == 65534 && i == 24) {
                ParsableByteArray parsableByteArray2 = new ParsableByteArray(bArr);
                parsableByteArray2.readLittleEndianUnsignedShort();
                int readLittleEndianUnsignedShort5 = parsableByteArray2.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort5 != 0 && readLittleEndianUnsignedShort5 != readLittleEndianUnsignedShort4) {
                    throw ParserException.createForUnsupportedContainerFeature("validBits ( " + readLittleEndianUnsignedShort5 + ")  != bitsPerSample( " + readLittleEndianUnsignedShort4 + ") are not supported");
                }
                int readLittleEndianUnsignedIntToInt3 = parsableByteArray2.readLittleEndianUnsignedIntToInt();
                if ((readLittleEndianUnsignedIntToInt3 >> 18) != 0) {
                    throw ParserException.createForUnsupportedContainerFeature("invalid channel mask " + readLittleEndianUnsignedIntToInt3);
                }
                if (readLittleEndianUnsignedIntToInt3 != 0 && Integer.bitCount(readLittleEndianUnsignedIntToInt3) != readLittleEndianUnsignedShort2) {
                    throw ParserException.createForUnsupportedContainerFeature("invalid number of channels (" + Integer.bitCount(readLittleEndianUnsignedIntToInt3) + ") in channel mask " + readLittleEndianUnsignedIntToInt3);
                }
                readLittleEndianUnsignedShort = parsableByteArray2.readLittleEndianUnsignedShort();
                byte[] bArr2 = new byte[14];
                parsableByteArray2.readBytes(bArr2, 0, 14);
                if (!Arrays.equals(bArr2, WAVEEXT_SUBFORMAT) && !Arrays.equals(bArr2, AMBISONIC_SUBFORMAT)) {
                    throw ParserException.createForUnsupportedContainerFeature("invalid wav format extension guid");
                }
            }
        } else {
            bArr = Util.EMPTY_BYTE_ARRAY;
        }
        int i2 = readLittleEndianUnsignedShort;
        extractorInput.skipFully((int) (extractorInput.getPeekPosition() - extractorInput.getPosition()));
        return new WavFormat(i2, readLittleEndianUnsignedShort2, readLittleEndianUnsignedIntToInt, readLittleEndianUnsignedIntToInt2, readLittleEndianUnsignedShort3, readLittleEndianUnsignedShort4, bArr);
    }

    public static Pair<Long, Long> skipToSampleData(ExtractorInput extractorInput) throws IOException {
        extractorInput.resetPeekPosition();
        ChunkHeader skipToChunk = skipToChunk(1684108385, extractorInput, new ParsableByteArray(8));
        extractorInput.skipFully(8);
        return Pair.create(Long.valueOf(extractorInput.getPosition()), Long.valueOf(skipToChunk.size));
    }

    private static ChunkHeader skipToChunk(int i, ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws IOException {
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        while (peek.id != i) {
            Log.w(TAG, "Ignoring unknown WAV chunk: " + peek.id);
            long j = peek.size;
            long j2 = 8 + j;
            if (peek.size % 2 != 0) {
                j2 = 9 + j;
            }
            if (j2 > 2147483647L) {
                throw ParserException.createForUnsupportedContainerFeature("Chunk is too large (~2GB+) to skip; id: " + peek.id);
            }
            extractorInput.skipFully((int) j2);
            peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        }
        return peek;
    }

    private WavHeaderReader() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ChunkHeader {
        public static final int SIZE_IN_BYTES = 8;
        public final int id;
        public final long size;

        private ChunkHeader(int i, long j) {
            this.id = i;
            this.size = j;
        }

        public static ChunkHeader peek(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws IOException {
            extractorInput.peekFully(parsableByteArray.getData(), 0, 8);
            parsableByteArray.setPosition(0);
            return new ChunkHeader(parsableByteArray.readInt(), parsableByteArray.readLittleEndianUnsignedInt());
        }
    }
}
