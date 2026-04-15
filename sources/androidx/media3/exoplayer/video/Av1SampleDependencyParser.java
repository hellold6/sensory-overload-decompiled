package androidx.media3.exoplayer.video;

import androidx.media3.container.ObuParser;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes.dex */
public final class Av1SampleDependencyParser {
    private static final int MAX_OBU_COUNT_FOR_PARTIAL_SKIP = 8;
    private ObuParser.SequenceHeader sequenceHeader;

    public int sampleLimitAfterSkippingNonReferenceFrame(ByteBuffer byteBuffer, boolean z) {
        List<ObuParser.Obu> split = ObuParser.split(byteBuffer);
        updateSequenceHeaders(split);
        int size = split.size() - 1;
        int i = 0;
        while (size >= 0 && canSkipObu(split.get(size), z)) {
            if (split.get(size).type == 6 || split.get(size).type == 3) {
                i++;
            }
            size--;
        }
        if (i > 1 || size + 1 >= 8) {
            return byteBuffer.limit();
        }
        if (size >= 0) {
            return split.get(size).payload.limit();
        }
        return byteBuffer.position();
    }

    public void queueInputBuffer(ByteBuffer byteBuffer) {
        updateSequenceHeaders(ObuParser.split(byteBuffer));
    }

    public void reset() {
        this.sequenceHeader = null;
    }

    private boolean canSkipObu(ObuParser.Obu obu, boolean z) {
        ObuParser.SequenceHeader sequenceHeader;
        ObuParser.FrameHeader parse;
        if (obu.type == 2 || obu.type == 15) {
            return true;
        }
        if (obu.type != 3 || z) {
            return ((obu.type != 6 && obu.type != 3) || (sequenceHeader = this.sequenceHeader) == null || (parse = ObuParser.FrameHeader.parse(sequenceHeader, obu)) == null || parse.isDependedOn()) ? false : true;
        }
        return false;
    }

    private void updateSequenceHeaders(List<ObuParser.Obu> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).type == 1) {
                this.sequenceHeader = ObuParser.SequenceHeader.parse(list.get(i));
            }
        }
    }
}
