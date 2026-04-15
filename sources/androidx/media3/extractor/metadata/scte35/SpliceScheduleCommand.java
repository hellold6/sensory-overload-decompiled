package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.C;
import androidx.media3.common.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class SpliceScheduleCommand extends SpliceCommand {
    public final List<Event> events;

    /* loaded from: classes.dex */
    public static final class Event {
        public final boolean autoReturn;
        public final int availNum;
        public final int availsExpected;
        public final long breakDurationUs;
        public final List<ComponentSplice> componentSpliceList;
        public final boolean outOfNetworkIndicator;
        public final boolean programSpliceFlag;
        public final boolean spliceEventCancelIndicator;
        public final long spliceEventId;
        public final int uniqueProgramId;
        public final long utcSpliceTime;

        private Event(long j, boolean z, boolean z2, boolean z3, List<ComponentSplice> list, long j2, boolean z4, long j3, int i, int i2, int i3) {
            this.spliceEventId = j;
            this.spliceEventCancelIndicator = z;
            this.outOfNetworkIndicator = z2;
            this.programSpliceFlag = z3;
            this.componentSpliceList = Collections.unmodifiableList(list);
            this.utcSpliceTime = j2;
            this.autoReturn = z4;
            this.breakDurationUs = j3;
            this.uniqueProgramId = i;
            this.availNum = i2;
            this.availsExpected = i3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Event parseFromSection(ParsableByteArray parsableByteArray) {
            boolean z;
            ArrayList arrayList;
            boolean z2;
            boolean z3;
            long j;
            boolean z4;
            long j2;
            int i;
            int i2;
            int i3;
            boolean z5;
            long j3;
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            boolean z6 = true;
            if ((parsableByteArray.readUnsignedByte() & 128) != 0) {
                z = true;
            } else {
                z = true;
                z6 = false;
            }
            ArrayList arrayList2 = new ArrayList();
            if (z6) {
                arrayList = arrayList2;
                z2 = false;
                z3 = false;
                j = C.TIME_UNSET;
                z4 = false;
                j2 = C.TIME_UNSET;
                i = 0;
                i2 = 0;
                i3 = 0;
            } else {
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                boolean z7 = (readUnsignedByte & 128) != 0 ? z : false;
                boolean z8 = (readUnsignedByte & 64) != 0 ? z : false;
                boolean z9 = (readUnsignedByte & 32) != 0 ? z : false;
                long readUnsignedInt2 = z8 ? parsableByteArray.readUnsignedInt() : C.TIME_UNSET;
                if (!z8) {
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    ArrayList arrayList3 = new ArrayList(readUnsignedByte2);
                    int i4 = 0;
                    while (i4 < readUnsignedByte2) {
                        arrayList3.add(new ComponentSplice(parsableByteArray.readUnsignedByte(), parsableByteArray.readUnsignedInt()));
                        i4++;
                        readUnsignedByte2 = readUnsignedByte2;
                    }
                    arrayList2 = arrayList3;
                }
                if (z9) {
                    long readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    boolean z10 = (128 & readUnsignedByte3) != 0;
                    j3 = ((((readUnsignedByte3 & 1) << 32) | parsableByteArray.readUnsignedInt()) * 1000) / 90;
                    z5 = z10;
                } else {
                    z5 = false;
                    j3 = C.TIME_UNSET;
                }
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                int readUnsignedByte4 = parsableByteArray.readUnsignedByte();
                boolean z11 = z7;
                z4 = z5;
                z2 = z11;
                i3 = parsableByteArray.readUnsignedByte();
                long j4 = readUnsignedInt2;
                i = readUnsignedShort;
                i2 = readUnsignedByte4;
                long j5 = j3;
                arrayList = arrayList2;
                z3 = z8;
                j = j4;
                j2 = j5;
            }
            return new Event(readUnsignedInt, z6, z2, z3, arrayList, j, z4, j2, i, i2, i3);
        }
    }

    /* loaded from: classes.dex */
    public static final class ComponentSplice {
        public final int componentTag;
        public final long utcSpliceTime;

        private ComponentSplice(int i, long j) {
            this.componentTag = i;
            this.utcSpliceTime = j;
        }
    }

    private SpliceScheduleCommand(List<Event> list) {
        this.events = Collections.unmodifiableList(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SpliceScheduleCommand parseFromSection(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        ArrayList arrayList = new ArrayList(readUnsignedByte);
        for (int i = 0; i < readUnsignedByte; i++) {
            arrayList.add(Event.parseFromSection(parsableByteArray));
        }
        return new SpliceScheduleCommand(arrayList);
    }
}
