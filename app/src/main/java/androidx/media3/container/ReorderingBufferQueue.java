package androidx.media3.container;

import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* loaded from: classes.dex */
public final class ReorderingBufferQueue {
    private BuffersWithTimestamp lastQueuedBuffer;
    private final OutputConsumer outputConsumer;
    private final ArrayDeque<ParsableByteArray> unusedParsableByteArrays = new ArrayDeque<>();
    private final ArrayDeque<BuffersWithTimestamp> unusedBuffersWithTimestamp = new ArrayDeque<>();
    private final PriorityQueue<BuffersWithTimestamp> pendingBuffers = new PriorityQueue<>();
    private int reorderingQueueSize = -1;

    /* loaded from: classes.dex */
    public interface OutputConsumer {
        void consume(long j, ParsableByteArray parsableByteArray);
    }

    public ReorderingBufferQueue(OutputConsumer outputConsumer) {
        this.outputConsumer = outputConsumer;
    }

    public void setMaxSize(int i) {
        Assertions.checkState(i >= 0);
        this.reorderingQueueSize = i;
        flushQueueDownToSize(i);
    }

    public int getMaxSize() {
        return this.reorderingQueueSize;
    }

    public void add(long j, ParsableByteArray parsableByteArray) {
        BuffersWithTimestamp pop;
        int i = this.reorderingQueueSize;
        if (i == 0 || (i != -1 && this.pendingBuffers.size() >= this.reorderingQueueSize && j < ((BuffersWithTimestamp) Util.castNonNull(this.pendingBuffers.peek())).presentationTimeUs)) {
            this.outputConsumer.consume(j, parsableByteArray);
            return;
        }
        ParsableByteArray copy = copy(parsableByteArray);
        BuffersWithTimestamp buffersWithTimestamp = this.lastQueuedBuffer;
        if (buffersWithTimestamp != null && j == buffersWithTimestamp.presentationTimeUs) {
            this.lastQueuedBuffer.nalBuffers.add(copy);
            return;
        }
        if (this.unusedBuffersWithTimestamp.isEmpty()) {
            pop = new BuffersWithTimestamp();
        } else {
            pop = this.unusedBuffersWithTimestamp.pop();
        }
        pop.init(j, copy);
        this.pendingBuffers.add(pop);
        this.lastQueuedBuffer = pop;
        int i2 = this.reorderingQueueSize;
        if (i2 != -1) {
            flushQueueDownToSize(i2);
        }
    }

    private ParsableByteArray copy(ParsableByteArray parsableByteArray) {
        ParsableByteArray pop;
        if (this.unusedParsableByteArrays.isEmpty()) {
            pop = new ParsableByteArray();
        } else {
            pop = this.unusedParsableByteArrays.pop();
        }
        pop.reset(parsableByteArray.bytesLeft());
        System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), pop.getData(), 0, pop.bytesLeft());
        return pop;
    }

    public void clear() {
        this.pendingBuffers.clear();
    }

    public void flush() {
        flushQueueDownToSize(0);
    }

    private void flushQueueDownToSize(int i) {
        while (this.pendingBuffers.size() > i) {
            BuffersWithTimestamp buffersWithTimestamp = (BuffersWithTimestamp) Util.castNonNull(this.pendingBuffers.poll());
            for (int i2 = 0; i2 < buffersWithTimestamp.nalBuffers.size(); i2++) {
                this.outputConsumer.consume(buffersWithTimestamp.presentationTimeUs, buffersWithTimestamp.nalBuffers.get(i2));
                this.unusedParsableByteArrays.push(buffersWithTimestamp.nalBuffers.get(i2));
            }
            buffersWithTimestamp.nalBuffers.clear();
            BuffersWithTimestamp buffersWithTimestamp2 = this.lastQueuedBuffer;
            if (buffersWithTimestamp2 != null && buffersWithTimestamp2.presentationTimeUs == buffersWithTimestamp.presentationTimeUs) {
                this.lastQueuedBuffer = null;
            }
            this.unusedBuffersWithTimestamp.push(buffersWithTimestamp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class BuffersWithTimestamp implements Comparable<BuffersWithTimestamp> {
        public long presentationTimeUs = C.TIME_UNSET;
        public final List<ParsableByteArray> nalBuffers = new ArrayList();

        public void init(long j, ParsableByteArray parsableByteArray) {
            Assertions.checkArgument(j != C.TIME_UNSET);
            Assertions.checkState(this.nalBuffers.isEmpty());
            this.presentationTimeUs = j;
            this.nalBuffers.add(parsableByteArray);
        }

        @Override // java.lang.Comparable
        public int compareTo(BuffersWithTimestamp buffersWithTimestamp) {
            return Long.compare(this.presentationTimeUs, buffersWithTimestamp.presentationTimeUs);
        }
    }
}
