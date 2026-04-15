package coil3.fetch;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;
import okio.Buffer;
import okio.Source;
import okio.Timeout;

/* compiled from: ByteBufferFetcher.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"asSource", "Lokio/Source;", "Ljava/nio/ByteBuffer;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ByteBufferFetcherKt {
    public static final Source asSource(final ByteBuffer byteBuffer) {
        return new Source(byteBuffer) { // from class: coil3.fetch.ByteBufferFetcherKt$asSource$1
            private final ByteBuffer buffer;
            private final int len;

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                ByteBuffer slice = byteBuffer.slice();
                this.buffer = slice;
                this.len = slice.capacity();
            }

            @Override // okio.Source
            public long read(Buffer sink, long byteCount) {
                if (this.buffer.position() == this.len) {
                    return -1L;
                }
                this.buffer.limit(RangesKt.coerceAtMost((int) (this.buffer.position() + byteCount), this.len));
                return sink.write(this.buffer);
            }

            @Override // okio.Source
            public Timeout timeout() {
                return Timeout.NONE;
            }
        };
    }
}
