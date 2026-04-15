package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Util;

/* loaded from: classes.dex */
final class FixedSampleSizeRechunker {
    private static final int MAX_SAMPLE_SIZE = 8192;

    /* loaded from: classes.dex */
    public static final class Results {
        public final long duration;
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;
        public final long totalSize;

        private Results(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2, long j, long j2) {
            this.offsets = jArr;
            this.sizes = iArr;
            this.maximumSize = i;
            this.timestamps = jArr2;
            this.flags = iArr2;
            this.duration = j;
            this.totalSize = j2;
        }
    }

    public static Results rechunk(int i, long[] jArr, int[] iArr, long j) {
        int[] iArr2 = iArr;
        int i2 = 8192 / i;
        int i3 = 0;
        int i4 = 0;
        for (int i5 : iArr2) {
            i4 += Util.ceilDivide(i5, i2);
        }
        long[] jArr2 = new long[i4];
        int[] iArr3 = new int[i4];
        long[] jArr3 = new long[i4];
        int[] iArr4 = new int[i4];
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i3 < iArr2.length) {
            int i10 = iArr2[i3];
            long j2 = jArr[i3];
            while (i10 > 0) {
                int min = Math.min(i2, i10);
                jArr2[i8] = j2;
                int i11 = i * min;
                iArr3[i8] = i11;
                i7 += i11;
                i9 = Math.max(i9, i11);
                jArr3[i8] = i6 * j;
                iArr4[i8] = 1;
                j2 += iArr3[i8];
                i6 += min;
                i10 -= min;
                i8++;
                i2 = i2;
            }
            i3++;
            iArr2 = iArr;
        }
        return new Results(jArr2, iArr3, i9, jArr3, iArr4, j * i6, i7);
    }

    private FixedSampleSizeRechunker() {
    }
}
