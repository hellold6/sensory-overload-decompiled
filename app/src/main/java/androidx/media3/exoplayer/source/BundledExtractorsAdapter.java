package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import java.io.IOException;

/* loaded from: classes.dex */
public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    private Extractor extractor;
    private ExtractorInput extractorInput;
    private final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory) {
        this.extractorsFactory = extractorsFactory;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0056, code lost:
    
        if (r1.getPosition() != r11) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007c, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0079, code lost:
    
        if (r1.getPosition() != r11) goto L38;
     */
    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(androidx.media3.common.DataReader r8, android.net.Uri r9, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r10, long r11, long r13, androidx.media3.extractor.ExtractorOutput r15) throws java.io.IOException {
        /*
            r7 = this;
            androidx.media3.extractor.DefaultExtractorInput r1 = new androidx.media3.extractor.DefaultExtractorInput
            r2 = r8
            r3 = r11
            r5 = r13
            r1.<init>(r2, r3, r5)
            r7.extractorInput = r1
            androidx.media3.extractor.Extractor r8 = r7.extractor
            if (r8 == 0) goto Lf
            return
        Lf:
            androidx.media3.extractor.ExtractorsFactory r8 = r7.extractorsFactory
            androidx.media3.extractor.Extractor[] r8 = r8.createExtractors(r9, r10)
            int r10 = r8.length
            com.google.common.collect.ImmutableList$Builder r10 = com.google.common.collect.ImmutableList.builderWithExpectedSize(r10)
            int r11 = r8.length
            r12 = 0
            r13 = 1
            if (r11 != r13) goto L25
            r8 = r8[r12]
            r7.extractor = r8
            goto L8c
        L25:
            int r11 = r8.length
            r14 = r12
        L27:
            if (r14 >= r11) goto L88
            r0 = r8[r14]
            boolean r2 = r0.sniff(r1)     // Catch: java.lang.Throwable -> L59 java.io.EOFException -> L6f
            if (r2 == 0) goto L45
            r7.extractor = r0     // Catch: java.lang.Throwable -> L59 java.io.EOFException -> L6f
            if (r0 != 0) goto L3d
            long r5 = r1.getPosition()
            int r11 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r11 != 0) goto L3e
        L3d:
            r12 = r13
        L3e:
            androidx.media3.common.util.Assertions.checkState(r12)
            r1.resetPeekPosition()
            goto L88
        L45:
            java.util.List r0 = r0.getSniffFailureDetails()     // Catch: java.lang.Throwable -> L59 java.io.EOFException -> L6f
            r10.addAll(r0)     // Catch: java.lang.Throwable -> L59 java.io.EOFException -> L6f
            androidx.media3.extractor.Extractor r0 = r7.extractor
            if (r0 != 0) goto L7e
            long r5 = r1.getPosition()
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L7c
            goto L7e
        L59:
            r0 = move-exception
            r8 = r0
            androidx.media3.extractor.Extractor r9 = r7.extractor
            if (r9 != 0) goto L67
            long r9 = r1.getPosition()
            int r9 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r9 != 0) goto L68
        L67:
            r12 = r13
        L68:
            androidx.media3.common.util.Assertions.checkState(r12)
            r1.resetPeekPosition()
            throw r8
        L6f:
            androidx.media3.extractor.Extractor r0 = r7.extractor
            if (r0 != 0) goto L7e
            long r5 = r1.getPosition()
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L7c
            goto L7e
        L7c:
            r0 = r12
            goto L7f
        L7e:
            r0 = r13
        L7f:
            androidx.media3.common.util.Assertions.checkState(r0)
            r1.resetPeekPosition()
            int r14 = r14 + 1
            goto L27
        L88:
            androidx.media3.extractor.Extractor r11 = r7.extractor
            if (r11 == 0) goto L92
        L8c:
            androidx.media3.extractor.Extractor r8 = r7.extractor
            r8.init(r15)
            return
        L92:
            androidx.media3.exoplayer.source.UnrecognizedInputFormatException r11 = new androidx.media3.exoplayer.source.UnrecognizedInputFormatException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "None of the available extractors ("
            r12.<init>(r13)
            java.lang.String r13 = ", "
            com.google.common.base.Joiner r13 = com.google.common.base.Joiner.on(r13)
            com.google.common.collect.ImmutableList r8 = com.google.common.collect.ImmutableList.copyOf(r8)
            androidx.media3.exoplayer.source.BundledExtractorsAdapter$$ExternalSyntheticLambda0 r14 = new androidx.media3.exoplayer.source.BundledExtractorsAdapter$$ExternalSyntheticLambda0
            r14.<init>()
            java.util.List r8 = com.google.common.collect.Lists.transform(r8, r14)
            java.lang.String r8 = r13.join(r8)
            java.lang.StringBuilder r8 = r12.append(r8)
            java.lang.String r12 = ") could read the stream."
            java.lang.StringBuilder r8 = r8.append(r12)
            java.lang.String r8 = r8.toString()
            java.lang.Object r9 = androidx.media3.common.util.Assertions.checkNotNull(r9)
            android.net.Uri r9 = (android.net.Uri) r9
            com.google.common.collect.ImmutableList r10 = r10.build()
            r11.<init>(r8, r9, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.BundledExtractorsAdapter.init(androidx.media3.common.DataReader, android.net.Uri, java.util.Map, long, long, androidx.media3.extractor.ExtractorOutput):void");
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void release() {
        Extractor extractor = this.extractor;
        if (extractor != null) {
            extractor.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        Extractor extractor = this.extractor;
        if (extractor == null) {
            return;
        }
        Extractor underlyingImplementation = extractor.getUnderlyingImplementation();
        if (underlyingImplementation instanceof Mp3Extractor) {
            ((Mp3Extractor) underlyingImplementation).disableSeeking();
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        ExtractorInput extractorInput = this.extractorInput;
        if (extractorInput != null) {
            return extractorInput.getPosition();
        }
        return -1L;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void seek(long j, long j2) {
        ((Extractor) Assertions.checkNotNull(this.extractor)).seek(j, j2);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        return ((Extractor) Assertions.checkNotNull(this.extractor)).read((ExtractorInput) Assertions.checkNotNull(this.extractorInput), positionHolder);
    }
}
