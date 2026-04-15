package androidx.media3.exoplayer.smoothstreaming.manifest;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.offline.FilterableManifest;
import androidx.media3.extractor.mp4.TrackEncryptionBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* loaded from: classes.dex */
public class SsManifest implements FilterableManifest<SsManifest> {
    public static final int UNSET_LOOKAHEAD = -1;
    public final long durationUs;
    public final long dvrWindowLengthUs;
    public final boolean isLive;
    public final int lookAheadCount;
    public final int majorVersion;
    public final int minorVersion;
    public final ProtectionElement protectionElement;
    public final StreamElement[] streamElements;

    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    public /* bridge */ /* synthetic */ SsManifest copy(List list) {
        return copy((List<StreamKey>) list);
    }

    /* loaded from: classes.dex */
    public static class ProtectionElement {
        public final byte[] data;
        public final TrackEncryptionBox[] trackEncryptionBoxes;
        public final UUID uuid;

        public ProtectionElement(UUID uuid, byte[] bArr, TrackEncryptionBox[] trackEncryptionBoxArr) {
            this.uuid = uuid;
            this.data = bArr;
            this.trackEncryptionBoxes = trackEncryptionBoxArr;
        }
    }

    /* loaded from: classes.dex */
    public static class StreamElement {
        private static final String URL_PLACEHOLDER_BITRATE_1 = "{bitrate}";
        private static final String URL_PLACEHOLDER_BITRATE_2 = "{Bitrate}";
        private static final String URL_PLACEHOLDER_START_TIME_1 = "{start time}";
        private static final String URL_PLACEHOLDER_START_TIME_2 = "{start_time}";
        private final String baseUri;
        public final int chunkCount;
        private final List<Long> chunkStartTimes;
        private final long[] chunkStartTimesUs;
        private final String chunkTemplate;
        public final int displayHeight;
        public final int displayWidth;
        public final Format[] formats;
        public final String language;
        private final long lastChunkDurationUs;
        public final int maxHeight;
        public final int maxWidth;
        public final String name;
        public final String subType;
        public final long timescale;
        public final int type;

        public StreamElement(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, Format[] formatArr, List<Long> list, long j2) {
            this(str, str2, i, str3, j, str4, i2, i3, i4, i5, str5, formatArr, list, Util.scaleLargeTimestamps(list, 1000000L, j), Util.scaleLargeTimestamp(j2, 1000000L, j));
        }

        private StreamElement(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, Format[] formatArr, List<Long> list, long[] jArr, long j2) {
            this.baseUri = str;
            this.chunkTemplate = str2;
            this.type = i;
            this.subType = str3;
            this.timescale = j;
            this.name = str4;
            this.maxWidth = i2;
            this.maxHeight = i3;
            this.displayWidth = i4;
            this.displayHeight = i5;
            this.language = str5;
            this.formats = formatArr;
            this.chunkStartTimes = list;
            this.chunkStartTimesUs = jArr;
            this.lastChunkDurationUs = j2;
            this.chunkCount = list.size();
        }

        public StreamElement copy(Format[] formatArr) {
            return new StreamElement(this.baseUri, this.chunkTemplate, this.type, this.subType, this.timescale, this.name, this.maxWidth, this.maxHeight, this.displayWidth, this.displayHeight, this.language, formatArr, this.chunkStartTimes, this.chunkStartTimesUs, this.lastChunkDurationUs);
        }

        public int getChunkIndex(long j) {
            return Util.binarySearchFloor(this.chunkStartTimesUs, j, true, true);
        }

        public long getStartTimeUs(int i) {
            return this.chunkStartTimesUs[i];
        }

        public long getChunkDurationUs(int i) {
            if (i == this.chunkCount - 1) {
                return this.lastChunkDurationUs;
            }
            long[] jArr = this.chunkStartTimesUs;
            return jArr[i + 1] - jArr[i];
        }

        public Uri buildRequestUri(int i, int i2) {
            Assertions.checkState(this.formats != null);
            Assertions.checkState(this.chunkStartTimes != null);
            Assertions.checkState(i2 < this.chunkStartTimes.size());
            String num = Integer.toString(this.formats[i].bitrate);
            String l = this.chunkStartTimes.get(i2).toString();
            return UriUtil.resolveToUri(this.baseUri, this.chunkTemplate.replace(URL_PLACEHOLDER_BITRATE_1, num).replace(URL_PLACEHOLDER_BITRATE_2, num).replace(URL_PLACEHOLDER_START_TIME_1, l).replace(URL_PLACEHOLDER_START_TIME_2, l));
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SsManifest(int r19, int r20, long r21, long r23, long r25, int r27, boolean r28, androidx.media3.exoplayer.smoothstreaming.manifest.SsManifest.ProtectionElement r29, androidx.media3.exoplayer.smoothstreaming.manifest.SsManifest.StreamElement[] r30) {
        /*
            r18 = this;
            r0 = 0
            int r2 = (r23 > r0 ? 1 : (r23 == r0 ? 0 : -1))
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r2 != 0) goto Ld
            r5 = r3
            goto L18
        Ld:
            r7 = 1000000(0xf4240, double:4.940656E-318)
            r9 = r21
            r5 = r23
            long r5 = androidx.media3.common.util.Util.scaleLargeTimestamp(r5, r7, r9)
        L18:
            int r0 = (r25 > r0 ? 1 : (r25 == r0 ? 0 : -1))
            if (r0 != 0) goto L1d
            goto L28
        L1d:
            r9 = 1000000(0xf4240, double:4.940656E-318)
            r11 = r21
            r7 = r25
            long r3 = androidx.media3.common.util.Util.scaleLargeTimestamp(r7, r9, r11)
        L28:
            r7 = r18
            r8 = r19
            r9 = r20
            r14 = r27
            r15 = r28
            r16 = r29
            r17 = r30
            r12 = r3
            r10 = r5
            r7.<init>(r8, r9, r10, r12, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.smoothstreaming.manifest.SsManifest.<init>(int, int, long, long, long, int, boolean, androidx.media3.exoplayer.smoothstreaming.manifest.SsManifest$ProtectionElement, androidx.media3.exoplayer.smoothstreaming.manifest.SsManifest$StreamElement[]):void");
    }

    private SsManifest(int i, int i2, long j, long j2, int i3, boolean z, ProtectionElement protectionElement, StreamElement[] streamElementArr) {
        this.majorVersion = i;
        this.minorVersion = i2;
        this.durationUs = j;
        this.dvrWindowLengthUs = j2;
        this.lookAheadCount = i3;
        this.isLive = z;
        this.protectionElement = protectionElement;
        this.streamElements = streamElementArr;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    public final SsManifest copy(List<StreamKey> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        StreamElement streamElement = null;
        int i = 0;
        while (i < arrayList.size()) {
            StreamKey streamKey = (StreamKey) arrayList.get(i);
            StreamElement streamElement2 = this.streamElements[streamKey.groupIndex];
            if (streamElement2 != streamElement && streamElement != null) {
                arrayList2.add(streamElement.copy((Format[]) arrayList3.toArray(new Format[0])));
                arrayList3.clear();
            }
            arrayList3.add(streamElement2.formats[streamKey.streamIndex]);
            i++;
            streamElement = streamElement2;
        }
        if (streamElement != null) {
            arrayList2.add(streamElement.copy((Format[]) arrayList3.toArray(new Format[0])));
        }
        return new SsManifest(this.majorVersion, this.minorVersion, this.durationUs, this.dvrWindowLengthUs, this.lookAheadCount, this.isLive, this.protectionElement, (StreamElement[]) arrayList2.toArray(new StreamElement[0]));
    }
}
