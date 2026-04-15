package androidx.media3.extractor.mp4;

import android.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.DolbyVisionConfig;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4AlternateGroupData;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.Mp4TimestampData;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.HevcConfig;
import androidx.media3.extractor.mp4.FixedSampleSizeRechunker;
import androidx.media3.extractor.ts.PsExtractor;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class BoxParser {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 4;
    private static final int SAMPLE_RATE_AMR_NB = 8000;
    private static final int SAMPLE_RATE_AMR_WB = 16000;
    private static final String TAG = "BoxParsers";
    private static final int TYPE_clcp = 1668047728;
    private static final int TYPE_mdta = 1835299937;
    private static final int TYPE_meta = 1835365473;
    private static final int TYPE_nclc = 1852009571;
    private static final int TYPE_nclx = 1852009592;
    private static final int TYPE_sbtl = 1935832172;
    private static final int TYPE_soun = 1936684398;
    private static final int TYPE_subp = 1937072752;
    private static final int TYPE_subt = 1937072756;
    private static final int TYPE_text = 1952807028;
    private static final int TYPE_vide = 1986618469;
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface SampleSizeBox {
        int getFixedSampleSize();

        int getSampleCount();

        int readNextSampleSize();
    }

    private static int getTrackTypeForHdlr(int i) {
        if (i == TYPE_soun) {
            return 1;
        }
        if (i == TYPE_vide) {
            return 2;
        }
        if (i == TYPE_text || i == TYPE_sbtl || i == TYPE_subt || i == TYPE_clcp || i == TYPE_subp) {
            return 3;
        }
        return i == 1835365473 ? 5 : -1;
    }

    public static int parseFullBoxFlags(int i) {
        return i & ViewCompat.MEASURED_SIZE_MASK;
    }

    public static int parseFullBoxVersion(int i) {
        return (i >> 24) & 255;
    }

    public static List<TrackSampleTable> parseTraks(Mp4Box.ContainerBox containerBox, GaplessInfoHolder gaplessInfoHolder, long j, DrmInitData drmInitData, boolean z, boolean z2, Function<Track, Track> function) throws ParserException {
        Track apply;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < containerBox.containerChildren.size(); i++) {
            Mp4Box.ContainerBox containerBox2 = containerBox.containerChildren.get(i);
            if (containerBox2.type == 1953653099 && (apply = function.apply(parseTrak(containerBox2, (Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_mvhd)), j, drmInitData, z, z2))) != null) {
                arrayList.add(parseStbl(apply, (Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox2.getContainerBoxOfType(Mp4Box.TYPE_mdia))).getContainerBoxOfType(Mp4Box.TYPE_minf))).getContainerBoxOfType(Mp4Box.TYPE_stbl)), gaplessInfoHolder));
            }
        }
        return arrayList;
    }

    public static Metadata parseUdta(Mp4Box.LeafBox leafBox) {
        ParsableByteArray parsableByteArray = leafBox.data;
        parsableByteArray.setPosition(8);
        Metadata metadata = new Metadata(new Metadata.Entry[0]);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1835365473) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(parseUdtaMeta(parsableByteArray, position + readInt));
            } else if (readInt2 == 1936553057) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(SmtaAtomUtil.parseSmta(parsableByteArray, position + readInt));
            } else if (readInt2 == -1451722374) {
                metadata = metadata.copyWithAppendedEntriesFrom(parseXyz(parsableByteArray));
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return metadata;
    }

    public static Mp4TimestampData parseMvhd(ParsableByteArray parsableByteArray) {
        long readLong;
        long readLong2;
        parsableByteArray.setPosition(8);
        if (parseFullBoxVersion(parsableByteArray.readInt()) == 0) {
            readLong = parsableByteArray.readUnsignedInt();
            readLong2 = parsableByteArray.readUnsignedInt();
        } else {
            readLong = parsableByteArray.readLong();
            readLong2 = parsableByteArray.readLong();
        }
        return new Mp4TimestampData(readLong, readLong2, parsableByteArray.readUnsignedInt());
    }

    public static Metadata parseMdtaFromMeta(Mp4Box.ContainerBox containerBox) {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_hdlr);
        Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_keys);
        Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_ilst);
        if (leafBoxOfType == null || leafBoxOfType2 == null || leafBoxOfType3 == null || parseHdlr(leafBoxOfType.data) != TYPE_mdta) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType2.data;
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        String[] strArr = new String[readInt];
        for (int i = 0; i < readInt; i++) {
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i] = parsableByteArray.readString(readInt2 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafBoxOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int readInt3 = parsableByteArray2.readInt();
            int readInt4 = parsableByteArray2.readInt() - 1;
            if (readInt4 >= 0 && readInt4 < readInt) {
                MdtaMetadataEntry parseMdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + readInt3, strArr[readInt4]);
                if (parseMdtaMetadataEntryFromIlst != null) {
                    arrayList.add(parseMdtaMetadataEntryFromIlst);
                }
            } else {
                Log.w(TAG, "Skipped metadata with unknown key index: " + readInt4);
            }
            parsableByteArray2.setPosition(position + readInt3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    public static void maybeSkipRemainingMetaBoxHeaderBytes(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() != 1751411826) {
            position += 4;
        }
        parsableByteArray.setPosition(position);
    }

    public static Track parseTrak(Mp4Box.ContainerBox containerBox, Mp4Box.LeafBox leafBox, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long[] jArr;
        long[] jArr2;
        Format format;
        Metadata metadata;
        Mp4Box.ContainerBox containerBoxOfType;
        Pair<long[], long[]> parseEdts;
        Mp4Box.ContainerBox containerBox2 = (Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox.getContainerBoxOfType(Mp4Box.TYPE_mdia));
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(Mp4Box.TYPE_hdlr))).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_tkhd))).data);
        long j2 = C.TIME_UNSET;
        long j3 = j == C.TIME_UNSET ? parseTkhd.duration : j;
        long j4 = parseMvhd(leafBox.data).timescale;
        if (j3 != C.TIME_UNSET) {
            j2 = Util.scaleLargeTimestamp(j3, 1000000L, j4);
        }
        long j5 = j2;
        Mp4Box.ContainerBox containerBox3 = (Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox2.getContainerBoxOfType(Mp4Box.TYPE_minf))).getContainerBoxOfType(Mp4Box.TYPE_stbl));
        MdhdData parseMdhd = parseMdhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(Mp4Box.TYPE_mdhd))).data);
        Mp4Box.LeafBox leafBoxOfType = containerBox3.getLeafBoxOfType(Mp4Box.TYPE_stsd);
        if (leafBoxOfType == null) {
            throw ParserException.createForMalformedContainer("Malformed sample table (stbl) missing sample description (stsd)", null);
        }
        StsdData parseStsd = parseStsd(leafBoxOfType.data, parseTkhd, parseMdhd.language, drmInitData, z2);
        if (z || (containerBoxOfType = containerBox.getContainerBoxOfType(Mp4Box.TYPE_edts)) == null || (parseEdts = parseEdts(containerBoxOfType)) == null) {
            jArr = null;
            jArr2 = null;
        } else {
            long[] jArr3 = (long[]) parseEdts.first;
            jArr2 = (long[]) parseEdts.second;
            jArr = jArr3;
        }
        if (parseStsd.format == null) {
            return null;
        }
        if (parseTkhd.alternateGroup != 0) {
            Mp4AlternateGroupData mp4AlternateGroupData = new Mp4AlternateGroupData(parseTkhd.alternateGroup);
            Format.Builder buildUpon = parseStsd.format.buildUpon();
            if (parseStsd.format.metadata != null) {
                metadata = parseStsd.format.metadata.copyWithAppendedEntries(mp4AlternateGroupData);
            } else {
                metadata = new Metadata(mp4AlternateGroupData);
            }
            format = buildUpon.setMetadata(metadata).build();
        } else {
            format = parseStsd.format;
        }
        return new Track(parseTkhd.id, trackTypeForHdlr, parseMdhd.timescale, j4, j5, parseMdhd.mediaDurationUs, format, parseStsd.requiredSampleTransformation, parseStsd.trackEncryptionBoxes, parseStsd.nalUnitLengthFieldLength, jArr, jArr2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static TrackSampleTable parseStbl(Track track, Mp4Box.ContainerBox containerBox, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox stz2SampleSizeBox;
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int[] iArr;
        int i8;
        int i9;
        long[] jArr;
        int[] iArr2;
        long j;
        long j2;
        int i10;
        long[] jArr2;
        int[] iArr3;
        int i11;
        int i12;
        int i13;
        Track track2 = track;
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_stsz);
        if (leafBoxOfType != null) {
            stz2SampleSizeBox = new StszSampleSizeBox(leafBoxOfType, track2.format);
        } else {
            Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_stz2);
            if (leafBoxOfType2 == null) {
                throw ParserException.createForMalformedContainer("Track has no sample table size information", null);
            }
            stz2SampleSizeBox = new Stz2SampleSizeBox(leafBoxOfType2);
        }
        int sampleCount = stz2SampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(track2, new long[0], new int[0], 0, new long[0], new int[0], 0L);
        }
        if (track2.type == 2 && track2.mediaDurationUs > 0) {
            track2 = track2.copyWithFormat(track2.format.buildUpon().setFrameRate(sampleCount / (((float) track2.mediaDurationUs) / 1000000.0f)).build());
        }
        Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_stco);
        if (leafBoxOfType3 == null) {
            leafBoxOfType3 = (Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_co64));
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType3.data;
        ParsableByteArray parsableByteArray2 = ((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_stsc))).data;
        ParsableByteArray parsableByteArray3 = ((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_stts))).data;
        Mp4Box.LeafBox leafBoxOfType4 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_stss);
        ParsableByteArray parsableByteArray4 = leafBoxOfType4 != null ? leafBoxOfType4.data : null;
        Mp4Box.LeafBox leafBoxOfType5 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_ctts);
        ParsableByteArray parsableByteArray5 = leafBoxOfType5 != null ? leafBoxOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int readUnsignedIntToInt = parsableByteArray3.readUnsignedIntToInt() - 1;
        int readUnsignedIntToInt2 = parsableByteArray3.readUnsignedIntToInt();
        int readUnsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            i = parsableByteArray5.readUnsignedIntToInt();
        } else {
            i = 0;
        }
        if (parsableByteArray4 != null) {
            parsableByteArray4.setPosition(12);
            i3 = parsableByteArray4.readUnsignedIntToInt();
            if (i3 > 0) {
                i2 = parsableByteArray4.readUnsignedIntToInt() - 1;
                i4 = 0;
            } else {
                i2 = -1;
                i4 = 0;
                parsableByteArray4 = null;
            }
        } else {
            i2 = -1;
            i3 = 0;
            i4 = 0;
        }
        int fixedSampleSize = stz2SampleSizeBox.getFixedSampleSize();
        String str = track2.format.sampleMimeType;
        if (((fixedSampleSize == -1 || !((MimeTypes.AUDIO_RAW.equals(str) || MimeTypes.AUDIO_MLAW.equals(str) || MimeTypes.AUDIO_ALAW.equals(str)) && readUnsignedIntToInt == 0 && i == 0 && i3 == 0)) ? i4 : 1) != 0) {
            long[] jArr3 = new long[chunkIterator.length];
            int[] iArr4 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr3[chunkIterator.index] = chunkIterator.offset;
                iArr4[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results rechunk = FixedSampleSizeRechunker.rechunk(fixedSampleSize, jArr3, iArr4, readUnsignedIntToInt3);
            long[] jArr4 = rechunk.offsets;
            int[] iArr5 = rechunk.sizes;
            int i14 = rechunk.maximumSize;
            long[] jArr5 = rechunk.timestamps;
            int[] iArr6 = rechunk.flags;
            long j3 = rechunk.duration;
            j2 = rechunk.totalSize;
            j = j3;
            i5 = 1;
            jArr = jArr5;
            iArr2 = iArr6;
            i10 = i14;
            iArr3 = iArr5;
            jArr2 = jArr4;
        } else {
            long[] jArr6 = new long[sampleCount];
            int[] iArr7 = new int[sampleCount];
            long[] jArr7 = new long[sampleCount];
            i5 = 1;
            int[] iArr8 = new int[sampleCount];
            ParsableByteArray parsableByteArray6 = parsableByteArray5;
            SampleSizeBox sampleSizeBox = stz2SampleSizeBox;
            int i15 = readUnsignedIntToInt3;
            ParsableByteArray parsableByteArray7 = parsableByteArray4;
            long j4 = 0;
            long j5 = 0;
            int i16 = i;
            int i17 = i2;
            int i18 = i4;
            int i19 = i18;
            int i20 = i19;
            int i21 = i20;
            int i22 = readUnsignedIntToInt2;
            long j6 = 0;
            int i23 = readUnsignedIntToInt;
            int i24 = i3;
            int i25 = i21;
            while (true) {
                if (i18 >= sampleCount) {
                    i6 = i23;
                    i7 = i22;
                    iArr = iArr7;
                    i8 = i20;
                    break;
                }
                long j7 = j5;
                int i26 = i20;
                boolean z2 = true;
                while (i26 == 0) {
                    z2 = chunkIterator.moveNext();
                    if (!z2) {
                        break;
                    }
                    int i27 = i23;
                    long j8 = chunkIterator.offset;
                    i26 = chunkIterator.numSamples;
                    j7 = j8;
                    i23 = i27;
                    i22 = i22;
                    sampleCount = sampleCount;
                }
                int i28 = sampleCount;
                i6 = i23;
                i7 = i22;
                if (!z2) {
                    Log.w(TAG, "Unexpected end of chunk data");
                    long[] copyOf = Arrays.copyOf(jArr6, i18);
                    int[] copyOf2 = Arrays.copyOf(iArr7, i18);
                    jArr7 = Arrays.copyOf(jArr7, i18);
                    iArr8 = Arrays.copyOf(iArr8, i18);
                    jArr6 = copyOf;
                    iArr = copyOf2;
                    sampleCount = i18;
                    i8 = i26;
                    break;
                }
                if (parsableByteArray6 != null) {
                    int i29 = i21;
                    while (i29 == 0 && i16 > 0) {
                        i29 = parsableByteArray6.readUnsignedIntToInt();
                        i19 = parsableByteArray6.readInt();
                        i16--;
                    }
                    i21 = i29 - 1;
                }
                jArr6[i18] = j7;
                int readNextSampleSize = sampleSizeBox.readNextSampleSize();
                iArr7[i18] = readNextSampleSize;
                j6 += readNextSampleSize;
                if (readNextSampleSize > i25) {
                    i25 = readNextSampleSize;
                }
                jArr7[i18] = j4 + i19;
                iArr8[i18] = parsableByteArray7 == null ? 1 : i4;
                if (i18 == i17) {
                    iArr8[i18] = 1;
                    i24--;
                    if (i24 > 0) {
                        i17 = ((ParsableByteArray) Assertions.checkNotNull(parsableByteArray7)).readUnsignedIntToInt() - 1;
                    }
                }
                j4 += i15;
                i22 = i7 - 1;
                if (i22 != 0 || i6 <= 0) {
                    i23 = i6;
                } else {
                    i23 = i6 - 1;
                    i22 = parsableByteArray3.readUnsignedIntToInt();
                    i15 = parsableByteArray3.readInt();
                }
                long j9 = j7 + iArr7[i18];
                i20 = i26 - 1;
                i18++;
                j5 = j9;
                sampleCount = i28;
            }
            long j10 = j4 + i19;
            if (parsableByteArray6 != null) {
                while (i16 > 0) {
                    if (parsableByteArray6.readUnsignedIntToInt() != 0) {
                        i9 = i4;
                        break;
                    }
                    parsableByteArray6.readInt();
                    i16--;
                }
            }
            i9 = 1;
            if (i24 != 0 || i7 != 0 || i8 != 0 || i6 != 0 || i21 != 0 || i9 == 0) {
                Log.w(TAG, "Inconsistent stbl box for track " + track2.id + ": remainingSynchronizationSamples " + i24 + ", remainingSamplesAtTimestampDelta " + i7 + ", remainingSamplesInChunk " + i8 + ", remainingTimestampDeltaChanges " + i6 + ", remainingSamplesAtTimestampOffset " + i21 + (i9 == 0 ? ", ctts invalid" : ""));
            }
            jArr = jArr7;
            iArr2 = iArr8;
            j = j10;
            j2 = j6;
            i10 = i25;
            jArr2 = jArr6;
            iArr3 = iArr;
        }
        if (track2.mediaDurationUs > 0) {
            long scaleLargeValue = Util.scaleLargeValue(j2 * 8, 1000000L, track2.mediaDurationUs, RoundingMode.HALF_DOWN);
            if (scaleLargeValue > 0 && scaleLargeValue < 2147483647L) {
                track2 = track2.copyWithFormat(track2.format.buildUpon().setAverageBitrate((int) scaleLargeValue).build());
            }
        }
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(j, 1000000L, track2.timescale);
        if (track2.editListDurations == null) {
            Util.scaleLargeTimestampsInPlace(jArr, 1000000L, track2.timescale);
            return new TrackSampleTable(track2, jArr2, iArr3, i10, jArr, iArr2, scaleLargeTimestamp);
        }
        Track track3 = track2;
        int[] iArr9 = iArr2;
        int i30 = i5;
        if (track3.editListDurations.length == i30 && track3.type == i30 && jArr.length >= 2) {
            long j11 = ((long[]) Assertions.checkNotNull(track3.editListMediaTimes))[i4];
            long scaleLargeTimestamp2 = j11 + Util.scaleLargeTimestamp(track3.editListDurations[i4], track3.timescale, track3.movieTimescale);
            if (canApplyEditWithGaplessInfo(jArr, j, j11, scaleLargeTimestamp2)) {
                long scaleLargeTimestamp3 = Util.scaleLargeTimestamp(j11 - jArr[i4], track3.format.sampleRate, track3.timescale);
                long scaleLargeTimestamp4 = Util.scaleLargeTimestamp(j - scaleLargeTimestamp2, track3.format.sampleRate, track3.timescale);
                if ((scaleLargeTimestamp3 != 0 || scaleLargeTimestamp4 != 0) && scaleLargeTimestamp3 <= 2147483647L && scaleLargeTimestamp4 <= 2147483647L) {
                    gaplessInfoHolder.encoderDelay = (int) scaleLargeTimestamp3;
                    gaplessInfoHolder.encoderPadding = (int) scaleLargeTimestamp4;
                    Util.scaleLargeTimestampsInPlace(jArr, 1000000L, track3.timescale);
                    return new TrackSampleTable(track3, jArr2, iArr3, i10, jArr, iArr9, Util.scaleLargeTimestamp(track3.editListDurations[i4], 1000000L, track3.movieTimescale));
                }
            }
        }
        if (track3.editListDurations.length == 1 && track3.editListDurations[i4] == 0) {
            long j12 = ((long[]) Assertions.checkNotNull(track3.editListMediaTimes))[i4];
            for (int i31 = i4; i31 < jArr.length; i31++) {
                jArr[i31] = Util.scaleLargeTimestamp(jArr[i31] - j12, 1000000L, track3.timescale);
            }
            return new TrackSampleTable(track3, jArr2, iArr3, i10, jArr, iArr9, Util.scaleLargeTimestamp(j - j12, 1000000L, track3.timescale));
        }
        boolean z3 = track3.type == 1 ? 1 : i4;
        int[] iArr10 = new int[track3.editListDurations.length];
        int[] iArr11 = new int[track3.editListDurations.length];
        long[] jArr8 = (long[]) Assertions.checkNotNull(track3.editListMediaTimes);
        int i32 = i4;
        int i33 = i32;
        int i34 = i33;
        int i35 = i34;
        while (i32 < track3.editListDurations.length) {
            int[] iArr12 = iArr10;
            int[] iArr13 = iArr11;
            long j13 = jArr8[i32];
            long[] jArr9 = jArr8;
            if (j13 != -1) {
                int i36 = i32;
                int i37 = i33;
                long scaleLargeTimestamp5 = Util.scaleLargeTimestamp(track3.editListDurations[i32], track3.timescale, track3.movieTimescale);
                i11 = i36;
                iArr12[i11] = Util.binarySearchFloor(jArr, j13, true, true);
                long j14 = j13 + scaleLargeTimestamp5;
                iArr13[i11] = Util.binarySearchCeil(jArr, j14, z3, (boolean) i4);
                int i38 = iArr12[i11];
                while (true) {
                    i13 = iArr12[i11];
                    if (i13 < 0 || (iArr9[i13] & 1) != 0) {
                        break;
                    }
                    iArr12[i11] = i13 - 1;
                }
                if (i13 < 0) {
                    iArr12[i11] = i38;
                    while (true) {
                        int i39 = iArr12[i11];
                        if (i39 >= iArr13[i11] || (iArr9[i39] & 1) != 0) {
                            break;
                        }
                        iArr12[i11] = i39 + 1;
                    }
                }
                if (track3.type == 2 && iArr12[i11] != iArr13[i11]) {
                    while (true) {
                        int i40 = iArr13[i11];
                        if (i40 >= jArr.length - 1 || jArr[i40 + 1] > j14) {
                            break;
                        }
                        iArr13[i11] = i40 + 1;
                    }
                }
                int i41 = iArr13[i11];
                int i42 = iArr12[i11];
                i34 += i41 - i42;
                i12 = i37 | (i35 != i42 ? 1 : 0);
                i35 = i41;
            } else {
                i11 = i32;
                i12 = i33;
            }
            i32 = i11 + 1;
            jArr8 = jArr9;
            i33 = i12;
            iArr10 = iArr12;
            iArr11 = iArr13;
            i4 = 0;
        }
        int[] iArr14 = iArr10;
        int[] iArr15 = iArr11;
        int i43 = i33 | (i34 != sampleCount ? 1 : 0);
        long[] jArr10 = i43 != 0 ? new long[i34] : jArr2;
        int[] iArr16 = i43 != 0 ? new int[i34] : iArr3;
        if (i43 != 0) {
            i10 = 0;
        }
        int[] iArr17 = i43 != 0 ? new int[i34] : iArr9;
        long[] jArr11 = new long[i34];
        int i44 = i10;
        long j15 = 0;
        int i45 = 0;
        int i46 = 0;
        boolean z4 = false;
        while (i45 < track3.editListDurations.length) {
            long j16 = track3.editListMediaTimes[i45];
            int i47 = iArr14[i45];
            int i48 = i43;
            int i49 = iArr15[i45];
            long[] jArr12 = jArr11;
            if (i48 != 0) {
                int i50 = i49 - i47;
                System.arraycopy(jArr2, i47, jArr10, i46, i50);
                System.arraycopy(iArr3, i47, iArr16, i46, i50);
                System.arraycopy(iArr9, i47, iArr17, i46, i50);
            }
            int i51 = i44;
            while (i47 < i49) {
                int i52 = i49;
                long[] jArr13 = jArr10;
                long scaleLargeTimestamp6 = Util.scaleLargeTimestamp(j15, 1000000L, track3.movieTimescale);
                long scaleLargeTimestamp7 = Util.scaleLargeTimestamp(jArr[i47] - j16, 1000000L, track3.timescale);
                if (scaleLargeTimestamp7 < 0) {
                    z4 = true;
                }
                jArr12[i46] = scaleLargeTimestamp6 + scaleLargeTimestamp7;
                if (i48 != 0 && iArr16[i46] > i51) {
                    i51 = iArr3[i47];
                }
                i46++;
                i47++;
                i49 = i52;
                jArr10 = jArr13;
            }
            j15 += track3.editListDurations[i45];
            i45++;
            i43 = i48;
            i44 = i51;
            jArr11 = jArr12;
        }
        return new TrackSampleTable(z4 ? track3.copyWithFormat(track3.format.buildUpon().setHasPrerollSamples(true).build()) : track3, jArr10, iArr16, i44, jArr11, iArr17, Util.scaleLargeTimestamp(j15, 1000000L, track3.movieTimescale));
    }

    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        maybeSkipRemainingMetaBoxHeaderBytes(parsableByteArray);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1768715124) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static Metadata parseXyz(ParsableByteArray parsableByteArray) {
        short readShort = parsableByteArray.readShort();
        parsableByteArray.skipBytes(2);
        String readString = parsableByteArray.readString(readShort);
        int max = Math.max(readString.lastIndexOf(43), readString.lastIndexOf(45));
        try {
            return new Metadata(new Mp4LocationData(Float.parseFloat(readString.substring(0, max)), Float.parseFloat(readString.substring(max, readString.length() - 1))));
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            return null;
        }
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        long j;
        parsableByteArray.setPosition(8);
        int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullBoxVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        int i = parseFullBoxVersion == 0 ? 4 : 8;
        int i2 = 0;
        while (true) {
            j = C.TIME_UNSET;
            if (i2 < i) {
                if (parsableByteArray.getData()[position + i2] != -1) {
                    long readUnsignedInt = parseFullBoxVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
                    if (readUnsignedInt != 0) {
                        j = readUnsignedInt;
                    }
                } else {
                    i2++;
                }
            } else {
                parsableByteArray.skipBytes(i);
                break;
            }
        }
        parsableByteArray.skipBytes(10);
        int i3 = 0;
        long j2 = j;
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(4);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && ((readInt4 == -65536 || readInt4 == 65536) && readInt5 == 0)) {
            i3 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && ((readInt4 == 65536 || readInt4 == -65536) && readInt5 == 0)) {
            i3 = RotationOptions.ROTATE_270;
        } else if ((readInt2 == -65536 || readInt2 == 65536) && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i3 = RotationOptions.ROTATE_180;
        }
        parsableByteArray.skipBytes(16);
        short readShort = parsableByteArray.readShort();
        parsableByteArray.skipBytes(2);
        return new TkhdData(readInt, j2, readUnsignedShort, i3, readShort, parsableByteArray.readShort());
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static MdhdData parseMdhd(ParsableByteArray parsableByteArray) {
        long j;
        parsableByteArray.setPosition(8);
        int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullBoxVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        int position = parsableByteArray.getPosition();
        int i = parseFullBoxVersion == 0 ? 4 : 8;
        int i2 = 0;
        while (true) {
            j = C.TIME_UNSET;
            if (i2 < i) {
                if (parsableByteArray.getData()[position + i2] != -1) {
                    long readUnsignedInt2 = parseFullBoxVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
                    if (readUnsignedInt2 != 0) {
                        long scaleLargeTimestamp = Util.scaleLargeTimestamp(readUnsignedInt2, 1000000L, readUnsignedInt);
                        readUnsignedInt = readUnsignedInt;
                        j = scaleLargeTimestamp;
                    }
                } else {
                    i2++;
                }
            } else {
                parsableByteArray.skipBytes(i);
                break;
            }
        }
        return new MdhdData(readUnsignedInt, j, getLanguageFromCode(parsableByteArray.readUnsignedShort()));
    }

    private static String getLanguageFromCode(int i) {
        char[] cArr = {(char) (((i >> 10) & 31) + 96), (char) (((i >> 5) & 31) + 96), (char) ((i & 31) + 96)};
        for (int i2 = 0; i2 < 3; i2++) {
            char c = cArr[i2];
            if (c < 'a' || c > 'z') {
                return null;
            }
        }
        return new String(cArr);
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, TkhdData tkhdData, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i = 0; i < readInt; i++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt2 > 0, "childAtomSize must be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == 1635148593 || readInt3 == 1635148595 || readInt3 == 1701733238 || readInt3 == 1831958048 || readInt3 == 1836070006 || readInt3 == 1752589105 || readInt3 == 1751479857 || readInt3 == 1932670515 || readInt3 == 1211250227 || readInt3 == 1748121139 || readInt3 == 1987063864 || readInt3 == 1987063865 || readInt3 == 1635135537 || readInt3 == 1685479798 || readInt3 == 1685479729 || readInt3 == 1685481573 || readInt3 == 1685481521 || readInt3 == 1634760241) {
                parseVideoSampleEntry(parsableByteArray, readInt3, position, readInt2, tkhdData.id, str, tkhdData.rotationDegrees, drmInitData, stsdData, i);
            } else if (readInt3 == 1836069985 || readInt3 == 1701733217 || readInt3 == 1633889587 || readInt3 == 1700998451 || readInt3 == 1633889588 || readInt3 == 1835823201 || readInt3 == 1685353315 || readInt3 == 1685353317 || readInt3 == 1685353320 || readInt3 == 1685353324 || readInt3 == 1685353336 || readInt3 == 1935764850 || readInt3 == 1935767394 || readInt3 == 1819304813 || readInt3 == 1936684916 || readInt3 == 1953984371 || readInt3 == 778924082 || readInt3 == 778924083 || readInt3 == 1835557169 || readInt3 == 1835560241 || readInt3 == 1634492771 || readInt3 == 1634492791 || readInt3 == 1970037111 || readInt3 == 1332770163 || readInt3 == 1716281667 || readInt3 == 1767992678 || readInt3 == 1768973165 || readInt3 == 1718641517) {
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, tkhdData.id, str, z, drmInitData, stsdData, i);
            } else if (readInt3 == 1414810956 || readInt3 == 1954034535 || readInt3 == 2004251764 || readInt3 == 1937010800 || readInt3 == 1664495672 || readInt3 == 1836070003) {
                StsdData stsdData2 = stsdData;
                parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, tkhdData, str, stsdData2);
                stsdData = stsdData2;
            } else if (readInt3 == 1835365492) {
                parseMetaDataSampleEntry(parsableByteArray, readInt3, position, tkhdData.id, stsdData);
            } else if (readInt3 == 1667329389) {
                stsdData.format = new Format.Builder().setId(tkhdData.id).setSampleMimeType(MimeTypes.APPLICATION_CAMERA_MOTION).build();
            }
            parsableByteArray.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, TkhdData tkhdData, String str, StsdData stsdData) {
        parsableByteArray.setPosition(i2 + 16);
        String str2 = MimeTypes.APPLICATION_TTML;
        ImmutableList immutableList = null;
        long j = Long.MAX_VALUE;
        if (i != 1414810956) {
            if (i == 1954034535) {
                int i4 = i3 - 16;
                byte[] bArr = new byte[i4];
                parsableByteArray.readBytes(bArr, 0, i4);
                immutableList = ImmutableList.of(bArr);
                str2 = MimeTypes.APPLICATION_TX3G;
            } else if (i == 2004251764) {
                str2 = MimeTypes.APPLICATION_MP4VTT;
            } else if (i == 1937010800) {
                j = 0;
            } else if (i == 1664495672) {
                stsdData.requiredSampleTransformation = 1;
                str2 = MimeTypes.APPLICATION_MP4CEA608;
            } else if (i == 1836070003) {
                int position = parsableByteArray.getPosition();
                parsableByteArray.skipBytes(4);
                if (parsableByteArray.readInt() == 1702061171) {
                    EsdsData parseEsdsFromParent = parseEsdsFromParent(parsableByteArray, position);
                    if (parseEsdsFromParent.initializationData == null || parseEsdsFromParent.initializationData.length != 64) {
                        return;
                    }
                    immutableList = ImmutableList.of(Util.getUtf8Bytes(formatVobsubIdx(parseEsdsFromParent.initializationData, tkhdData.width, tkhdData.height)));
                    str2 = MimeTypes.APPLICATION_VOBSUB;
                } else {
                    str2 = null;
                }
            } else {
                throw new IllegalStateException();
            }
        }
        if (str2 != null) {
            stsdData.format = new Format.Builder().setId(tkhdData.id).setSampleMimeType(str2).setLanguage(str).setSubsampleOffsetUs(j).setInitializationData(immutableList).build();
        }
    }

    private static String formatVobsubIdx(byte[] bArr, int i, int i2) {
        Assertions.checkState(bArr.length == 64);
        ArrayList arrayList = new ArrayList(16);
        for (int i3 = 0; i3 < bArr.length - 3; i3 += 4) {
            arrayList.add(String.format("%06x", Integer.valueOf(vobsubYuvToRgb(Ints.fromBytes(bArr[i3], bArr[i3 + 1], bArr[i3 + 2], bArr[i3 + 3])))));
        }
        return "size: " + i + "x" + i2 + "\npalette: " + Joiner.on(", ").join(arrayList) + "\n";
    }

    private static int vobsubYuvToRgb(int i) {
        int i2 = (i >> 16) & 255;
        int i3 = ((i >> 8) & 255) - 128;
        int i4 = (i & 255) - 128;
        return Util.constrainValue(i2 + ((i4 * 17790) / 10000), 0, 255) | (Util.constrainValue(((i3 * 14075) / 10000) + i2, 0, 255) << 16) | (Util.constrainValue((i2 - ((i4 * 3455) / 10000)) - ((i3 * 7169) / 10000), 0, 255) << 8);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        String str2;
        int i7;
        String str3;
        int i8;
        int i9;
        DrmInitData drmInitData2;
        int i10;
        int i11;
        int i12;
        int i13;
        NalUnitUtil.H265VpsData h265VpsData;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19 = i2;
        int i20 = i3;
        DrmInitData drmInitData3 = drmInitData;
        StsdData stsdData2 = stsdData;
        parsableByteArray.setPosition(i19 + 16);
        parsableByteArray.skipBytes(16);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        int i21 = i;
        if (i21 == 1701733238) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i19, i20);
            if (parseSampleEntryEncryptionData != null) {
                i21 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                drmInitData3 = drmInitData3 == null ? null : drmInitData3.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                stsdData2.trackEncryptionBoxes[i6] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        String str4 = MimeTypes.VIDEO_H263;
        if (i21 != 1831958048) {
            str2 = i21 == 1211250227 ? MimeTypes.VIDEO_H263 : null;
        } else {
            str2 = MimeTypes.VIDEO_MPEG;
        }
        float f = 1.0f;
        int i22 = 8;
        int i23 = 8;
        ByteBuffer byteBuffer = null;
        List<byte[]> list = null;
        String str5 = null;
        byte[] bArr = null;
        int i24 = -1;
        int i25 = -1;
        int i26 = -1;
        int i27 = -1;
        int i28 = -1;
        int i29 = -1;
        int i30 = -1;
        int i31 = -1;
        BtrtData btrtData = null;
        EsdsData esdsData = null;
        NalUnitUtil.H265VpsData h265VpsData2 = null;
        boolean z = false;
        while (position - i19 < i20) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (readInt == 0 && parsableByteArray.getPosition() - i2 == i20) {
                break;
            }
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1635148611) {
                ExtractorUtil.checkContainerInput(str2 == null, null);
                parsableByteArray.setPosition(position2 + 8);
                AvcConfig parse = AvcConfig.parse(parsableByteArray);
                List<byte[]> list2 = parse.initializationData;
                stsdData2.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
                if (!z) {
                    f = parse.pixelWidthHeightRatio;
                }
                String str6 = parse.codecs;
                int i32 = parse.maxNumReorderFrames;
                int i33 = parse.colorSpace;
                int i34 = parse.colorRange;
                list = list2;
                int i35 = parse.colorTransfer;
                int i36 = parse.bitdepthLuma;
                NalUnitUtil.H265VpsData h265VpsData3 = h265VpsData2;
                drmInitData2 = drmInitData3;
                h265VpsData = h265VpsData3;
                i12 = parse.bitdepthChroma;
                i7 = position;
                i10 = i21;
                str3 = str4;
                i8 = i33;
                i9 = i34;
                i31 = i35;
                i22 = i36;
                str5 = str6;
                str2 = MimeTypes.VIDEO_H264;
                i25 = i32;
            } else {
                i7 = position;
                if (readInt2 == 1752589123) {
                    ExtractorUtil.checkContainerInput(str2 == null, null);
                    parsableByteArray.setPosition(position2 + 8);
                    HevcConfig parse2 = HevcConfig.parse(parsableByteArray);
                    List<byte[]> list3 = parse2.initializationData;
                    stsdData2.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                    if (!z) {
                        f = parse2.pixelWidthHeightRatio;
                    }
                    int i37 = parse2.maxNumReorderPics;
                    int i38 = parse2.maxSubLayers;
                    String str7 = parse2.codecs;
                    list = list3;
                    if (parse2.stereoMode != -1) {
                        i24 = parse2.stereoMode;
                    }
                    int i39 = parse2.decodedWidth;
                    int i40 = parse2.decodedHeight;
                    int i41 = parse2.colorSpace;
                    int i42 = parse2.colorRange;
                    int i43 = parse2.colorTransfer;
                    i28 = i40;
                    i22 = parse2.bitdepthLuma;
                    i12 = parse2.bitdepthChroma;
                    drmInitData2 = drmInitData3;
                    i10 = i21;
                    str3 = str4;
                    i8 = i41;
                    i9 = i42;
                    i31 = i43;
                    h265VpsData = parse2.vpsData;
                    i25 = i37;
                    i26 = i38;
                    i27 = i39;
                    str2 = MimeTypes.VIDEO_H265;
                    str5 = str7;
                } else {
                    str3 = str4;
                    if (readInt2 == 1818785347) {
                        ExtractorUtil.checkContainerInput(MimeTypes.VIDEO_H265.equals(str2), "lhvC must follow hvcC atom");
                        NalUnitUtil.H265VpsData h265VpsData4 = h265VpsData2;
                        ExtractorUtil.checkContainerInput(h265VpsData4 != null && h265VpsData4.layerInfos.size() >= 2, "must have at least two layers");
                        parsableByteArray.setPosition(position2 + 8);
                        HevcConfig parseLayered = HevcConfig.parseLayered(parsableByteArray, (NalUnitUtil.H265VpsData) Assertions.checkNotNull(h265VpsData4));
                        ExtractorUtil.checkContainerInput(stsdData2.nalUnitLengthFieldLength == parseLayered.nalUnitLengthFieldLength, "nalUnitLengthFieldLength must be same for both hvcC and lhvC atoms");
                        if (parseLayered.colorSpace != -1) {
                            i15 = i29;
                            ExtractorUtil.checkContainerInput(i15 == parseLayered.colorSpace, "colorSpace must be the same for both views");
                        } else {
                            i15 = i29;
                        }
                        if (parseLayered.colorRange != -1) {
                            i16 = i30;
                            ExtractorUtil.checkContainerInput(i16 == parseLayered.colorRange, "colorRange must be the same for both views");
                        } else {
                            i16 = i30;
                        }
                        if (parseLayered.colorTransfer != -1) {
                            int i44 = i31;
                            i17 = i44;
                            ExtractorUtil.checkContainerInput(i44 == parseLayered.colorTransfer, "colorTransfer must be the same for both views");
                        } else {
                            i17 = i31;
                        }
                        ExtractorUtil.checkContainerInput(i22 == parseLayered.bitdepthLuma, "bitdepthLuma must be the same for both views");
                        ExtractorUtil.checkContainerInput(i23 == parseLayered.bitdepthChroma, "bitdepthChroma must be the same for both views");
                        List<byte[]> list4 = list;
                        if (list4 != null) {
                            list4 = ImmutableList.builder().addAll((Iterable) list4).addAll((Iterable) parseLayered.initializationData).build();
                            i18 = i15;
                        } else {
                            i18 = i15;
                            ExtractorUtil.checkContainerInput(false, "initializationData must be already set from hvcC atom");
                        }
                        String str8 = parseLayered.codecs;
                        str2 = MimeTypes.VIDEO_MV_HEVC;
                        drmInitData2 = drmInitData3;
                        i10 = i21;
                        i9 = i16;
                        i12 = i23;
                        i8 = i18;
                        i31 = i17;
                        str5 = str8;
                        h265VpsData = h265VpsData4;
                        list = list4;
                    } else {
                        List<byte[]> list5 = list;
                        i8 = i29;
                        i9 = i30;
                        int i45 = i31;
                        NalUnitUtil.H265VpsData h265VpsData5 = h265VpsData2;
                        if (readInt2 == 1986361461) {
                            VexuData parseVideoExtendedUsageBox = parseVideoExtendedUsageBox(parsableByteArray, position2, readInt);
                            if (parseVideoExtendedUsageBox != null && parseVideoExtendedUsageBox.eyesData != null) {
                                if (h265VpsData5 == null || h265VpsData5.layerInfos.size() < 2) {
                                    i14 = i24;
                                    if (i14 == -1) {
                                        i24 = parseVideoExtendedUsageBox.eyesData.striData.eyeViewsReversed ? 5 : 4;
                                        drmInitData2 = drmInitData3;
                                        list = list5;
                                        i10 = i21;
                                        i12 = i23;
                                        i31 = i45;
                                        h265VpsData = h265VpsData5;
                                    }
                                    i24 = i14;
                                    drmInitData2 = drmInitData3;
                                    list = list5;
                                    i10 = i21;
                                    i12 = i23;
                                    i31 = i45;
                                    h265VpsData = h265VpsData5;
                                } else {
                                    ExtractorUtil.checkContainerInput(parseVideoExtendedUsageBox.hasBothEyeViews(), "both eye views must be marked as available");
                                    ExtractorUtil.checkContainerInput(!parseVideoExtendedUsageBox.eyesData.striData.eyeViewsReversed, "for MV-HEVC, eye_views_reversed must be set to false");
                                }
                            }
                            i14 = i24;
                            i24 = i14;
                            drmInitData2 = drmInitData3;
                            list = list5;
                            i10 = i21;
                            i12 = i23;
                            i31 = i45;
                            h265VpsData = h265VpsData5;
                        } else {
                            int i46 = i24;
                            if (readInt2 == 1685480259 || readInt2 == 1685485123 || readInt2 == 1685485379) {
                                drmInitData2 = drmInitData3;
                                i10 = i21;
                                i11 = i46;
                                i12 = i23;
                                float f2 = f;
                                int i47 = i22;
                                i13 = i45;
                                int i48 = readInt - 8;
                                byte[] bArr2 = new byte[i48];
                                parsableByteArray.readBytes(bArr2, 0, i48);
                                if (list5 != null) {
                                    list = ImmutableList.builder().addAll((Iterable) list5).add((ImmutableList.Builder) bArr2).build();
                                } else {
                                    ExtractorUtil.checkContainerInput(false, "initializationData must already be set from hvcC or avcC atom");
                                    list = list5;
                                }
                                parsableByteArray.setPosition(position2 + 8);
                                DolbyVisionConfig parse3 = DolbyVisionConfig.parse(parsableByteArray);
                                if (parse3 != null) {
                                    String str9 = parse3.codecs;
                                    str2 = MimeTypes.VIDEO_DOLBY_VISION;
                                    str5 = str9;
                                }
                                i8 = i8;
                                i22 = i47;
                                f = f2;
                            } else if (readInt2 == 1987076931) {
                                ExtractorUtil.checkContainerInput(str2 == null, null);
                                String str10 = i21 == 1987063864 ? MimeTypes.VIDEO_VP8 : MimeTypes.VIDEO_VP9;
                                parsableByteArray.setPosition(position2 + 12);
                                byte readUnsignedByte = (byte) parsableByteArray.readUnsignedByte();
                                byte readUnsignedByte2 = (byte) parsableByteArray.readUnsignedByte();
                                int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                                i22 = readUnsignedByte3 >> 4;
                                i10 = i21;
                                byte b = (byte) ((readUnsignedByte3 >> 1) & 7);
                                if (str10.equals(MimeTypes.VIDEO_VP9)) {
                                    list5 = CodecSpecificDataUtil.buildVp9CodecPrivateInitializationData(readUnsignedByte, readUnsignedByte2, (byte) i22, b);
                                }
                                boolean z2 = (readUnsignedByte3 & 1) != 0;
                                int readUnsignedByte4 = parsableByteArray.readUnsignedByte();
                                int readUnsignedByte5 = parsableByteArray.readUnsignedByte();
                                int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(readUnsignedByte4);
                                int i49 = z2 ? 1 : 2;
                                i31 = ColorInfo.isoTransferCharacteristicsToColorTransfer(readUnsignedByte5);
                                str2 = str10;
                                drmInitData2 = drmInitData3;
                                i9 = i49;
                                h265VpsData = h265VpsData5;
                                i8 = isoColorPrimariesToColorSpace;
                                list = list5;
                                i24 = i46;
                                i12 = i22;
                            } else {
                                i10 = i21;
                                if (readInt2 == 1635135811) {
                                    int i50 = readInt - 8;
                                    byte[] bArr3 = new byte[i50];
                                    parsableByteArray.readBytes(bArr3, 0, i50);
                                    list = ImmutableList.of(bArr3);
                                    parsableByteArray.setPosition(position2 + 8);
                                    ColorInfo parseAv1c = parseAv1c(parsableByteArray);
                                    int i51 = parseAv1c.lumaBitdepth;
                                    int i52 = parseAv1c.chromaBitdepth;
                                    int i53 = parseAv1c.colorSpace;
                                    int i54 = parseAv1c.colorRange;
                                    i31 = parseAv1c.colorTransfer;
                                    i22 = i51;
                                    drmInitData2 = drmInitData3;
                                    i12 = i52;
                                    i8 = i53;
                                    i9 = i54;
                                    str2 = MimeTypes.VIDEO_AV1;
                                    h265VpsData = h265VpsData5;
                                } else if (readInt2 == 1668050025) {
                                    if (byteBuffer == null) {
                                        byteBuffer = allocateHdrStaticInfo();
                                    }
                                    ByteBuffer byteBuffer2 = byteBuffer;
                                    byteBuffer2.position(21);
                                    byteBuffer2.putShort(parsableByteArray.readShort());
                                    byteBuffer2.putShort(parsableByteArray.readShort());
                                    byteBuffer = byteBuffer2;
                                    drmInitData2 = drmInitData3;
                                    list = list5;
                                    i12 = i23;
                                    h265VpsData = h265VpsData5;
                                    i31 = i45;
                                } else if (readInt2 == 1835295606) {
                                    if (byteBuffer == null) {
                                        byteBuffer = allocateHdrStaticInfo();
                                    }
                                    ByteBuffer byteBuffer3 = byteBuffer;
                                    short readShort = parsableByteArray.readShort();
                                    short readShort2 = parsableByteArray.readShort();
                                    short readShort3 = parsableByteArray.readShort();
                                    short readShort4 = parsableByteArray.readShort();
                                    i12 = i23;
                                    short readShort5 = parsableByteArray.readShort();
                                    int i55 = i22;
                                    short readShort6 = parsableByteArray.readShort();
                                    drmInitData2 = drmInitData3;
                                    short readShort7 = parsableByteArray.readShort();
                                    short readShort8 = parsableByteArray.readShort();
                                    long readUnsignedInt = parsableByteArray.readUnsignedInt();
                                    long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
                                    byteBuffer3.position(1);
                                    byteBuffer3.putShort(readShort5);
                                    byteBuffer3.putShort(readShort6);
                                    byteBuffer3.putShort(readShort);
                                    byteBuffer3.putShort(readShort2);
                                    byteBuffer3.putShort(readShort3);
                                    byteBuffer3.putShort(readShort4);
                                    byteBuffer3.putShort(readShort7);
                                    byteBuffer3.putShort(readShort8);
                                    byteBuffer3.putShort((short) (readUnsignedInt / 10000));
                                    byteBuffer3.putShort((short) (readUnsignedInt2 / 10000));
                                    byteBuffer = byteBuffer3;
                                    list = list5;
                                    h265VpsData = h265VpsData5;
                                    i22 = i55;
                                    i31 = i45;
                                    i24 = i46;
                                    f = f;
                                } else {
                                    drmInitData2 = drmInitData3;
                                    i11 = i46;
                                    i12 = i23;
                                    float f3 = f;
                                    int i56 = i22;
                                    if (readInt2 == 1681012275) {
                                        ExtractorUtil.checkContainerInput(str2 == null, null);
                                        list = list5;
                                        h265VpsData = h265VpsData5;
                                        i22 = i56;
                                        str2 = str3;
                                    } else {
                                        if (readInt2 == 1702061171) {
                                            ExtractorUtil.checkContainerInput(str2 == null, null);
                                            esdsData = parseEsdsFromParent(parsableByteArray, position2);
                                            String str11 = esdsData.mimeType;
                                            byte[] bArr4 = esdsData.initializationData;
                                            list = bArr4 != null ? ImmutableList.of(bArr4) : list5;
                                            str2 = str11;
                                        } else {
                                            if (readInt2 == 1651798644) {
                                                btrtData = parseBtrtFromParent(parsableByteArray, position2);
                                            } else if (readInt2 == 1885434736) {
                                                f = parsePaspFromParent(parsableByteArray, position2);
                                                list = list5;
                                                h265VpsData = h265VpsData5;
                                                i22 = i56;
                                                i31 = i45;
                                                i24 = i11;
                                                z = true;
                                            } else if (readInt2 == 1937126244) {
                                                bArr = parseProjFromParent(parsableByteArray, position2, readInt);
                                            } else if (readInt2 == 1936995172) {
                                                int readUnsignedByte6 = parsableByteArray.readUnsignedByte();
                                                parsableByteArray.skipBytes(3);
                                                if (readUnsignedByte6 == 0) {
                                                    int readUnsignedByte7 = parsableByteArray.readUnsignedByte();
                                                    if (readUnsignedByte7 == 0) {
                                                        i11 = 0;
                                                    } else if (readUnsignedByte7 == 1) {
                                                        i11 = 1;
                                                    } else if (readUnsignedByte7 == 2) {
                                                        i11 = 2;
                                                    } else if (readUnsignedByte7 == 3) {
                                                        i11 = 3;
                                                    }
                                                }
                                            } else if (readInt2 == 1634760259) {
                                                int i57 = readInt - 12;
                                                byte[] bArr5 = new byte[i57];
                                                parsableByteArray.setPosition(position2 + 12);
                                                parsableByteArray.readBytes(bArr5, 0, i57);
                                                list = ImmutableList.of(bArr5);
                                                ColorInfo parseApvc = parseApvc(new ParsableByteArray(bArr5));
                                                int i58 = parseApvc.lumaBitdepth;
                                                int i59 = parseApvc.chromaBitdepth;
                                                int i60 = parseApvc.colorSpace;
                                                int i61 = parseApvc.colorRange;
                                                i31 = parseApvc.colorTransfer;
                                                i22 = i58;
                                                i12 = i59;
                                                i8 = i60;
                                                i9 = i61;
                                                str2 = MimeTypes.VIDEO_APV;
                                                h265VpsData = h265VpsData5;
                                                i24 = i11;
                                                f = f3;
                                            } else {
                                                if (readInt2 == 1668246642) {
                                                    i13 = i45;
                                                    if (i8 == -1 && i13 == -1) {
                                                        int readInt3 = parsableByteArray.readInt();
                                                        if (readInt3 == TYPE_nclx || readInt3 == TYPE_nclc) {
                                                            int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
                                                            int readUnsignedShort4 = parsableByteArray.readUnsignedShort();
                                                            parsableByteArray.skipBytes(2);
                                                            boolean z3 = readInt == 19 && (parsableByteArray.readUnsignedByte() & 128) != 0;
                                                            i8 = ColorInfo.isoColorPrimariesToColorSpace(readUnsignedShort3);
                                                            list = list5;
                                                            i9 = z3 ? 1 : 2;
                                                            h265VpsData = h265VpsData5;
                                                            i22 = i56;
                                                            i24 = i11;
                                                            f = f3;
                                                            i31 = ColorInfo.isoTransferCharacteristicsToColorTransfer(readUnsignedShort4);
                                                        } else {
                                                            Log.w(TAG, "Unsupported color type: " + Mp4Box.getBoxTypeString(readInt3));
                                                        }
                                                    }
                                                } else {
                                                    i13 = i45;
                                                }
                                                list = list5;
                                                i8 = i8;
                                                i22 = i56;
                                                f = f3;
                                            }
                                            list = list5;
                                        }
                                        h265VpsData = h265VpsData5;
                                        i22 = i56;
                                    }
                                    i31 = i45;
                                    i24 = i11;
                                    f = f3;
                                }
                                i24 = i46;
                            }
                            i31 = i13;
                            h265VpsData = h265VpsData5;
                            i24 = i11;
                        }
                    }
                }
            }
            position = i7 + readInt;
            DrmInitData drmInitData4 = drmInitData2;
            h265VpsData2 = h265VpsData;
            drmInitData3 = drmInitData4;
            i19 = i2;
            i20 = i3;
            stsdData2 = stsdData;
            i23 = i12;
            i21 = i10;
            str4 = str3;
            i29 = i8;
            i30 = i9;
        }
        DrmInitData drmInitData5 = drmInitData3;
        float f4 = f;
        List<byte[]> list6 = list;
        int i62 = i24;
        int i63 = i29;
        int i64 = i30;
        int i65 = i31;
        int i66 = i23;
        int i67 = i22;
        if (str2 == null) {
            return;
        }
        Format.Builder colorInfo = new Format.Builder().setId(i4).setSampleMimeType(str2).setCodecs(str5).setWidth(readUnsignedShort).setHeight(readUnsignedShort2).setDecodedWidth(i27).setDecodedHeight(i28).setPixelWidthHeightRatio(f4).setRotationDegrees(i5).setProjectionData(bArr).setStereoMode(i62).setInitializationData(list6).setMaxNumReorderSamples(i25).setMaxSubLayers(i26).setDrmInitData(drmInitData5).setLanguage(str).setColorInfo(new ColorInfo.Builder().setColorSpace(i63).setColorRange(i64).setColorTransfer(i65).setHdrStaticInfo(byteBuffer != null ? byteBuffer.array() : null).setLumaBitdepth(i67).setChromaBitdepth(i66).build());
        if (btrtData != null) {
            colorInfo.setAverageBitrate(Ints.saturatedCast(btrtData.avgBitrate)).setPeakBitrate(Ints.saturatedCast(btrtData.maxBitrate));
        } else if (esdsData != null) {
            colorInfo.setAverageBitrate(Ints.saturatedCast(esdsData.bitrate)).setPeakBitrate(Ints.saturatedCast(esdsData.peakBitrate));
        }
        stsdData.format = colorInfo.build();
    }

    private static ColorInfo parseAv1c(ParsableByteArray parsableByteArray) {
        ColorInfo.Builder builder = new ColorInfo.Builder();
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        parsableBitArray.skipBytes(1);
        int readBits = parsableBitArray.readBits(3);
        parsableBitArray.skipBits(6);
        boolean readBit = parsableBitArray.readBit();
        boolean readBit2 = parsableBitArray.readBit();
        if (readBits == 2 && readBit) {
            builder.setLumaBitdepth(readBit2 ? 12 : 10);
            builder.setChromaBitdepth(readBit2 ? 12 : 10);
        } else if (readBits <= 2) {
            builder.setLumaBitdepth(readBit ? 10 : 8);
            builder.setChromaBitdepth(readBit ? 10 : 8);
        }
        parsableBitArray.skipBits(13);
        parsableBitArray.skipBit();
        int readBits2 = parsableBitArray.readBits(4);
        if (readBits2 != 1) {
            Log.i(TAG, "Unsupported obu_type: " + readBits2);
            return builder.build();
        }
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported obu_extension_flag");
            return builder.build();
        }
        boolean readBit3 = parsableBitArray.readBit();
        parsableBitArray.skipBit();
        if (readBit3 && parsableBitArray.readBits(8) > 127) {
            Log.i(TAG, "Excessive obu_size");
            return builder.build();
        }
        int readBits3 = parsableBitArray.readBits(3);
        parsableBitArray.skipBit();
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported reduced_still_picture_header");
            return builder.build();
        }
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported timing_info_present_flag");
            return builder.build();
        }
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported initial_display_delay_present_flag");
            return builder.build();
        }
        int readBits4 = parsableBitArray.readBits(5);
        boolean z = false;
        for (int i = 0; i <= readBits4; i++) {
            parsableBitArray.skipBits(12);
            if (parsableBitArray.readBits(5) > 7) {
                parsableBitArray.skipBit();
            }
        }
        int readBits5 = parsableBitArray.readBits(4);
        int readBits6 = parsableBitArray.readBits(4);
        parsableBitArray.skipBits(readBits5 + 1);
        parsableBitArray.skipBits(readBits6 + 1);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(7);
        }
        parsableBitArray.skipBits(7);
        boolean readBit4 = parsableBitArray.readBit();
        if (readBit4) {
            parsableBitArray.skipBits(2);
        }
        if ((parsableBitArray.readBit() ? 2 : parsableBitArray.readBits(1)) > 0 && !parsableBitArray.readBit()) {
            parsableBitArray.skipBits(1);
        }
        if (readBit4) {
            parsableBitArray.skipBits(3);
        }
        parsableBitArray.skipBits(3);
        boolean readBit5 = parsableBitArray.readBit();
        if (readBits3 == 2 && readBit5) {
            parsableBitArray.skipBit();
        }
        if (readBits3 != 1 && parsableBitArray.readBit()) {
            z = true;
        }
        if (parsableBitArray.readBit()) {
            int readBits7 = parsableBitArray.readBits(8);
            int readBits8 = parsableBitArray.readBits(8);
            builder.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(readBits7)).setColorRange(((z || readBits7 != 1 || readBits8 != 13 || parsableBitArray.readBits(8) != 0) ? parsableBitArray.readBits(1) : 1) != 1 ? 2 : 1).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(readBits8));
        }
        return builder.build();
    }

    private static ColorInfo parseApvc(ParsableByteArray parsableByteArray) {
        ColorInfo.Builder builder = new ColorInfo.Builder();
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        parsableBitArray.skipBytes(1);
        int readBits = parsableBitArray.readBits(8);
        for (int i = 0; i < readBits; i++) {
            parsableBitArray.skipBytes(1);
            int readBits2 = parsableBitArray.readBits(8);
            for (int i2 = 0; i2 < readBits2; i2++) {
                parsableBitArray.skipBits(6);
                boolean readBit = parsableBitArray.readBit();
                parsableBitArray.skipBit();
                parsableBitArray.skipBytes(11);
                parsableBitArray.skipBits(4);
                int readBits3 = parsableBitArray.readBits(4) + 8;
                builder.setLumaBitdepth(readBits3);
                builder.setChromaBitdepth(readBits3);
                parsableBitArray.skipBytes(1);
                if (readBit) {
                    int readBits4 = parsableBitArray.readBits(8);
                    int readBits5 = parsableBitArray.readBits(8);
                    parsableBitArray.skipBytes(1);
                    builder.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(readBits4)).setColorRange(parsableBitArray.readBit() ? 1 : 2).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(readBits5));
                }
            }
        }
        return builder.build();
    }

    private static ByteBuffer allocateHdrStaticInfo() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static void parseMetaDataSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, StsdData stsdData) {
        parsableByteArray.setPosition(i2 + 16);
        if (i == 1835365492) {
            parsableByteArray.readNullTerminatedString();
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString();
            if (readNullTerminatedString != null) {
                stsdData.format = new Format.Builder().setId(i3).setSampleMimeType(readNullTerminatedString).build();
            }
        }
    }

    private static Pair<long[], long[]> parseEdts(Mp4Box.ContainerBox containerBox) {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_elst);
        if (leafBoxOfType == null) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType.data;
        parsableByteArray.setPosition(8);
        int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt];
        long[] jArr2 = new long[readUnsignedIntToInt];
        for (int i = 0; i < readUnsignedIntToInt; i++) {
            jArr[i] = parseFullBoxVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = parseFullBoxVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
            if (parsableByteArray.readShort() != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return parsableByteArray.readUnsignedIntToInt() / parsableByteArray.readUnsignedIntToInt();
    }

    /* JADX WARN: Removed duplicated region for block: B:155:0x04a0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:164:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void parseAudioSampleEntry(androidx.media3.common.util.ParsableByteArray r26, int r27, int r28, int r29, int r30, java.lang.String r31, boolean r32, androidx.media3.common.DrmInitData r33, androidx.media3.extractor.mp4.BoxParser.StsdData r34, int r35) throws androidx.media3.common.ParserException {
        /*
            Method dump skipped, instructions count: 1289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.BoxParser.parseAudioSampleEntry(androidx.media3.common.util.ParsableByteArray, int, int, int, int, java.lang.String, boolean, androidx.media3.common.DrmInitData, androidx.media3.extractor.mp4.BoxParser$StsdData, int):void");
    }

    private static int findBoxPosition(ParsableByteArray parsableByteArray, int i, int i2, int i3) throws ParserException {
        int position = parsableByteArray.getPosition();
        ExtractorUtil.checkContainerInput(position >= i2, null);
        while (position - i2 < i3) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == i) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static EsdsData parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 12);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedByte());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return new EsdsData(mimeTypeFromMp4ObjectType, null, -1L, -1L);
        }
        parsableByteArray.skipBytes(4);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        long j = readUnsignedInt2;
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        if (j <= 0) {
            j = -1;
        }
        return new EsdsData(mimeTypeFromMp4ObjectType, bArr, j, readUnsignedInt > 0 ? readUnsignedInt : -1L);
    }

    private static BtrtData parseBtrtFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        parsableByteArray.skipBytes(4);
        return new BtrtData(parsableByteArray.readUnsignedInt(), parsableByteArray.readUnsignedInt());
    }

    static VexuData parseVideoExtendedUsageBox(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int position = parsableByteArray.getPosition();
        EyesData eyesData = null;
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1702454643) {
                eyesData = parseStereoViewBox(parsableByteArray, position, readInt);
            }
            position += readInt;
        }
        if (eyesData == null) {
            return null;
        }
        return new VexuData(eyesData);
    }

    private static EyesData parseStereoViewBox(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1937011305) {
                parsableByteArray.skipBytes(4);
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                return new EyesData(new StriData((readUnsignedByte & 1) == 1, (readUnsignedByte & 2) == 2, (readUnsignedByte & 8) == 8));
            }
            position += readInt;
        }
        return null;
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1936289382 && (parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt)) != null) {
                return parseCommonEncryptionSinfFromParent;
            }
            position += readInt;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        int i3 = i + 8;
        int i4 = -1;
        int i5 = 0;
        String str = null;
        Integer num = null;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1718775137) {
                num = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == 1935894637) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == 1935894633) {
                i4 = i3;
                i5 = readInt;
            }
            i3 += readInt;
        }
        if (!C.CENC_TYPE_cenc.equals(str) && !C.CENC_TYPE_cbc1.equals(str) && !C.CENC_TYPE_cens.equals(str) && !C.CENC_TYPE_cbcs.equals(str)) {
            return null;
        }
        ExtractorUtil.checkContainerInput(num != null, "frma atom is mandatory");
        ExtractorUtil.checkContainerInput(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, str);
        ExtractorUtil.checkContainerInput(parseSchiFromParent != null, "tenc atom is mandatory");
        return Pair.create(num, (TrackEncryptionBox) Util.castNonNull(parseSchiFromParent));
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1952804451) {
                int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullBoxVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = readUnsignedByte & 15;
                    i4 = (readUnsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && readUnsignedByte2 == 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, readUnsignedByte3);
                }
                return new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += readInt;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1886547818) {
                return Arrays.copyOfRange(parsableByteArray.getData(), i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        return jArr[0] <= j2 && j2 < jArr[Util.constrainValue(4, 0, length)] && jArr[Util.constrainValue(jArr.length - 4, 0, length)] < j3 && j3 <= j;
    }

    private BoxParser() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) throws ParserException {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            ExtractorUtil.checkContainerInput(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long readUnsignedInt;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                readUnsignedInt = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                readUnsignedInt = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = readUnsignedInt;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class TkhdData {
        private final int alternateGroup;
        private final long duration;
        private final int height;
        private final int id;
        private final int rotationDegrees;
        private final int width;

        public TkhdData(int i, long j, int i2, int i3, int i4, int i5) {
            this.id = i;
            this.duration = j;
            this.alternateGroup = i2;
            this.rotationDegrees = i3;
            this.width = i4;
            this.height = i5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class EsdsData {
        private final long bitrate;
        private final byte[] initializationData;
        private final String mimeType;
        private final long peakBitrate;

        public EsdsData(String str, byte[] bArr, long j, long j2) {
            this.mimeType = str;
            this.initializationData = bArr;
            this.bitrate = j;
            this.peakBitrate = j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class BtrtData {
        private final long avgBitrate;
        private final long maxBitrate;

        public BtrtData(long j, long j2) {
            this.avgBitrate = j;
            this.maxBitrate = j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class StriData {
        private final boolean eyeViewsReversed;
        private final boolean hasLeftEyeView;
        private final boolean hasRightEyeView;

        public StriData(boolean z, boolean z2, boolean z3) {
            this.hasLeftEyeView = z;
            this.hasRightEyeView = z2;
            this.eyeViewsReversed = z3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class EyesData {
        private final StriData striData;

        public EyesData(StriData striData) {
            this.striData = striData;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class MdhdData {
        private final String language;
        private final long mediaDurationUs;
        private final long timescale;

        public MdhdData(long j, long j2, String str) {
            this.timescale = j;
            this.mediaDurationUs = j2;
            this.language = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class VexuData {
        private final EyesData eyesData;

        public VexuData(EyesData eyesData) {
            this.eyesData = eyesData;
        }

        public boolean hasBothEyeViews() {
            EyesData eyesData = this.eyesData;
            return eyesData != null && eyesData.striData.hasLeftEyeView && this.eyesData.striData.hasRightEyeView;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Mp4Box.LeafBox leafBox, Format format) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (MimeTypes.AUDIO_RAW.equals(format.sampleMimeType)) {
                int pcmFrameSize = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
                if (readUnsignedIntToInt == 0 || readUnsignedIntToInt % pcmFrameSize != 0) {
                    Log.w(BoxParser.TAG, "Audio sample size mismatch. stsd sample size: " + pcmFrameSize + ", stsz sample size: " + readUnsignedIntToInt);
                    readUnsignedIntToInt = pcmFrameSize;
                }
            }
            this.fixedSampleSize = readUnsignedIntToInt == 0 ? -1 : readUnsignedIntToInt;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getFixedSampleSize() {
            return this.fixedSampleSize;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == -1 ? this.data.readUnsignedIntToInt() : i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getFixedSampleSize() {
            return -1;
        }

        public Stz2SampleSizeBox(Mp4Box.LeafBox leafBox) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & 255;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 == 0) {
                int readUnsignedByte = this.data.readUnsignedByte();
                this.currentByte = readUnsignedByte;
                return (readUnsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
            }
            return this.currentByte & 15;
        }
    }
}
