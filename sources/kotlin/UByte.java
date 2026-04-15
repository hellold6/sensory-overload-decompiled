package kotlin;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;

/* compiled from: UByte.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0097\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0017\u0010\fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u0018\u0010\u000fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0019\u0010\u0012J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u001d\u0010\fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u001e\u0010\u000fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u001f\u0010\u0012J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b \u0010\u001bJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\"\u0010\fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b#\u0010\u000fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b$\u0010\u0012J\u0018\u0010!\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b%\u0010\u001bJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b'\u0010\fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b(\u0010\u000fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b)\u0010\u0012J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b*\u0010\u001bJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b,\u0010\fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b-\u0010\u000fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b.\u0010\u0012J\u0018\u0010+\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b/\u0010\u001bJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b1\u0010\fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\b¢\u0006\u0004\b2\u0010\u000fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b3\u0010\u0012J\u0018\u00100\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b4\u0010\u001bJ\u0018\u00105\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b6\u00107J\u0018\u00105\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\rH\u0087\b¢\u0006\u0004\b8\u00109J\u0018\u00105\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b:\u0010\u0012J\u0018\u00105\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b;\u0010\u001bJ\u0010\u0010<\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b=\u0010\u0005J\u0010\u0010>\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b?\u0010\u0005J\u0018\u0010@\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bB\u0010CJ\u0018\u0010D\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bE\u0010CJ\u0018\u0010F\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bG\u00107J\u0018\u0010H\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bI\u00107J\u0018\u0010J\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bK\u00107J\u0010\u0010L\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\bM\u0010\u0005J\u0010\u0010N\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bO\u0010\u0005J\u0010\u0010P\u001a\u00020QH\u0087\b¢\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020\tH\u0087\b¢\u0006\u0004\bU\u0010VJ\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\\\u0010\u0005J\u0010\u0010]\u001a\u00020\rH\u0087\b¢\u0006\u0004\b^\u0010SJ\u0010\u0010_\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b`\u0010VJ\u0010\u0010a\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\bb\u0010ZJ\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0013\u0010o\u001a\u00020p2\b\u0010\n\u001a\u0004\u0018\u00010qHÖ\u0003J\t\u0010r\u001a\u00020\tHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006t"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "compareTo", "", "other", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(BJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", TtmlNode.TAG_DIV, "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(BB)B", "mod-xj2QHRw", "(BS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-w2LRezQ", "dec", "dec-w2LRezQ", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-7apg3OU", "and", "and-7apg3OU", "or", "or-7apg3OU", "xor", "xor-7apg3OU", "inv", "inv-w2LRezQ", "toByte", "toByte-impl", "toShort", "", "toShort-impl", "(B)S", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(B)F", "toDouble", "", "toDouble-impl", "(B)D", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class UByte implements Comparable<UByte> {
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UByte m1421boximpl(byte b) {
        return new UByte(b);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static byte m1427constructorimpl(byte b) {
        return b;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m1433equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).getData();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1434equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m1439hashCodeimpl(byte b) {
        return Byte.hashCode(b);
    }

    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m1465toByteimpl(byte b) {
        return b;
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m1468toIntimpl(byte b) {
        return b & 255;
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m1469toLongimpl(byte b) {
        return b & 255;
    }

    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m1470toShortimpl(byte b) {
        return (short) (b & 255);
    }

    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m1472toUBytew2LRezQ(byte b) {
        return b;
    }

    public boolean equals(Object other) {
        return m1433equalsimpl(this.data, other);
    }

    public int hashCode() {
        return m1439hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ byte getData() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return Intrinsics.compare(getData() & 255, uByte.getData() & 255);
    }

    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private int m1422compareTo7apg3OU(byte b) {
        return Intrinsics.compare(getData() & 255, b & 255);
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static int m1423compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255);
    }

    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m1426compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare(b & 255, s & UShort.MAX_VALUE);
    }

    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m1425compareToWZ4Q5Ns(byte b, int i) {
        int compare;
        compare = Integer.compare(UInt.m1506constructorimpl(b & 255) ^ Integer.MIN_VALUE, i ^ Integer.MIN_VALUE);
        return compare;
    }

    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m1424compareToVKZWuLQ(byte b, long j) {
        int compare;
        compare = Long.compare(ULong.m1585constructorimpl(b & 255) ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE);
        return compare;
    }

    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m1451plus7apg3OU(byte b, byte b2) {
        return UInt.m1506constructorimpl(UInt.m1506constructorimpl(b & 255) + UInt.m1506constructorimpl(b2 & 255));
    }

    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m1454plusxj2QHRw(byte b, short s) {
        return UInt.m1506constructorimpl(UInt.m1506constructorimpl(b & 255) + UInt.m1506constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m1453plusWZ4Q5Ns(byte b, int i) {
        return UInt.m1506constructorimpl(UInt.m1506constructorimpl(b & 255) + i);
    }

    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m1452plusVKZWuLQ(byte b, long j) {
        return ULong.m1585constructorimpl(ULong.m1585constructorimpl(b & 255) + j);
    }

    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m1442minus7apg3OU(byte b, byte b2) {
        return UInt.m1506constructorimpl(UInt.m1506constructorimpl(b & 255) - UInt.m1506constructorimpl(b2 & 255));
    }

    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m1445minusxj2QHRw(byte b, short s) {
        return UInt.m1506constructorimpl(UInt.m1506constructorimpl(b & 255) - UInt.m1506constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m1444minusWZ4Q5Ns(byte b, int i) {
        return UInt.m1506constructorimpl(UInt.m1506constructorimpl(b & 255) - i);
    }

    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m1443minusVKZWuLQ(byte b, long j) {
        return ULong.m1585constructorimpl(ULong.m1585constructorimpl(b & 255) - j);
    }

    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m1461times7apg3OU(byte b, byte b2) {
        return UInt.m1506constructorimpl(UInt.m1506constructorimpl(b & 255) * UInt.m1506constructorimpl(b2 & 255));
    }

    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m1464timesxj2QHRw(byte b, short s) {
        return UInt.m1506constructorimpl(UInt.m1506constructorimpl(b & 255) * UInt.m1506constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m1463timesWZ4Q5Ns(byte b, int i) {
        return UInt.m1506constructorimpl(UInt.m1506constructorimpl(b & 255) * i);
    }

    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m1462timesVKZWuLQ(byte b, long j) {
        return ULong.m1585constructorimpl(ULong.m1585constructorimpl(b & 255) * j);
    }

    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m1429div7apg3OU(byte b, byte b2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(b2 & 255));
    }

    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m1432divxj2QHRw(byte b, short s) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m1431divWZ4Q5Ns(byte b, int i) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m1506constructorimpl(b & 255), i);
    }

    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m1430divVKZWuLQ(byte b, long j) {
        return UByte$$ExternalSyntheticBackport0.m$1(ULong.m1585constructorimpl(b & 255), j);
    }

    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m1457rem7apg3OU(byte b, byte b2) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(b2 & 255));
    }

    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m1460remxj2QHRw(byte b, short s) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m1459remWZ4Q5Ns(byte b, int i) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1506constructorimpl(b & 255), i);
    }

    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m1458remVKZWuLQ(byte b, long j) {
        return UByte$$ExternalSyntheticBackport0.m1478m(ULong.m1585constructorimpl(b & 255), j);
    }

    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m1435floorDiv7apg3OU(byte b, byte b2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(b2 & 255));
    }

    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m1438floorDivxj2QHRw(byte b, short s) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m1437floorDivWZ4Q5Ns(byte b, int i) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m1506constructorimpl(b & 255), i);
    }

    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m1436floorDivVKZWuLQ(byte b, long j) {
        return UByte$$ExternalSyntheticBackport0.m$1(ULong.m1585constructorimpl(b & 255), j);
    }

    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m1446mod7apg3OU(byte b, byte b2) {
        return m1427constructorimpl((byte) UByte$$ExternalSyntheticBackport0.m$1(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(b2 & 255)));
    }

    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m1449modxj2QHRw(byte b, short s) {
        return UShort.m1692constructorimpl((short) UByte$$ExternalSyntheticBackport0.m$1(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(s & UShort.MAX_VALUE)));
    }

    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m1448modWZ4Q5Ns(byte b, int i) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1506constructorimpl(b & 255), i);
    }

    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m1447modVKZWuLQ(byte b, long j) {
        return UByte$$ExternalSyntheticBackport0.m1478m(ULong.m1585constructorimpl(b & 255), j);
    }

    /* renamed from: inc-w2LRezQ, reason: not valid java name */
    private static final byte m1440incw2LRezQ(byte b) {
        return m1427constructorimpl((byte) (b + 1));
    }

    /* renamed from: dec-w2LRezQ, reason: not valid java name */
    private static final byte m1428decw2LRezQ(byte b) {
        return m1427constructorimpl((byte) (b - 1));
    }

    /* renamed from: rangeTo-7apg3OU, reason: not valid java name */
    private static final UIntRange m1455rangeTo7apg3OU(byte b, byte b2) {
        return new UIntRange(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(b2 & 255), null);
    }

    /* renamed from: rangeUntil-7apg3OU, reason: not valid java name */
    private static final UIntRange m1456rangeUntil7apg3OU(byte b, byte b2) {
        return URangesKt.m2687untilJ1ME1BU(UInt.m1506constructorimpl(b & 255), UInt.m1506constructorimpl(b2 & 255));
    }

    /* renamed from: and-7apg3OU, reason: not valid java name */
    private static final byte m1420and7apg3OU(byte b, byte b2) {
        return m1427constructorimpl((byte) (b & b2));
    }

    /* renamed from: or-7apg3OU, reason: not valid java name */
    private static final byte m1450or7apg3OU(byte b, byte b2) {
        return m1427constructorimpl((byte) (b | b2));
    }

    /* renamed from: xor-7apg3OU, reason: not valid java name */
    private static final byte m1476xor7apg3OU(byte b, byte b2) {
        return m1427constructorimpl((byte) (b ^ b2));
    }

    /* renamed from: inv-w2LRezQ, reason: not valid java name */
    private static final byte m1441invw2LRezQ(byte b) {
        return m1427constructorimpl((byte) (~b));
    }

    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m1475toUShortMh2AYeg(byte b) {
        return UShort.m1692constructorimpl((short) (b & 255));
    }

    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m1473toUIntpVg5ArA(byte b) {
        return UInt.m1506constructorimpl(b & 255);
    }

    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m1474toULongsVKNKU(byte b) {
        return ULong.m1585constructorimpl(b & 255);
    }

    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m1467toFloatimpl(byte b) {
        return (float) UnsignedKt.uintToDouble(b & 255);
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m1466toDoubleimpl(byte b) {
        return UnsignedKt.uintToDouble(b & 255);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m1471toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }

    public String toString() {
        return m1471toStringimpl(this.data);
    }
}
