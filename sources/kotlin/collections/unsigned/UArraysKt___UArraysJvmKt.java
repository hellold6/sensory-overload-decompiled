package kotlin.collections.unsigned;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: _UArraysJvm.kt */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u001f\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\u0000\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b\r\u0010\u000e\u001a\u001c\u0010\u0000\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0019\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014*\u00020\u0002H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0019\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014*\u00020\bH\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0019\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014*\u00020\fH\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0019\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014*\u00020\u0010H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a/\u0010\u001d\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0007¢\u0006\u0004\b!\u0010\"\u001a/\u0010\u001d\u001a\u00020\u0004*\u00020\b2\u0006\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0007¢\u0006\u0004\b#\u0010$\u001a/\u0010\u001d\u001a\u00020\u0004*\u00020\f2\u0006\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0007¢\u0006\u0004\b%\u0010&\u001a/\u0010\u001d\u001a\u00020\u0004*\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u000f2\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0007¢\u0006\u0004\b'\u0010(\u001a\u0015\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0004\b*\u0010+\u001a\u0015\u0010)\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0007¢\u0006\u0004\b,\u0010-\u001a\u0015\u0010)\u001a\u0004\u0018\u00010\u000b*\u00020\fH\u0007¢\u0006\u0004\b.\u0010/\u001a\u0015\u0010)\u001a\u0004\u0018\u00010\u000f*\u00020\u0010H\u0007¢\u0006\u0004\b0\u00101\u001a=\u00102\u001a\u0004\u0018\u00010\u0001\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0000¢\u0006\u0004\b7\u00108\u001a=\u00102\u001a\u0004\u0018\u00010\u0007\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a=\u00102\u001a\u0004\u0018\u00010\u000b\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a=\u00102\u001a\u0004\u0018\u00010\u000f\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00102\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001a1\u0010?\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0001`BH\u0007¢\u0006\u0004\bC\u0010D\u001a1\u0010?\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00070Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0007`BH\u0007¢\u0006\u0004\bE\u0010F\u001a1\u0010?\u001a\u0004\u0018\u00010\u000b*\u00020\f2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000b0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000b`BH\u0007¢\u0006\u0004\bG\u0010H\u001a1\u0010?\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000f0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000f`BH\u0007¢\u0006\u0004\bI\u0010J\u001a\u0015\u0010K\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0004\bL\u0010+\u001a\u0015\u0010K\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0007¢\u0006\u0004\bM\u0010-\u001a\u0015\u0010K\u001a\u0004\u0018\u00010\u000b*\u00020\fH\u0007¢\u0006\u0004\bN\u0010/\u001a\u0015\u0010K\u001a\u0004\u0018\u00010\u000f*\u00020\u0010H\u0007¢\u0006\u0004\bO\u00101\u001a=\u0010P\u001a\u0004\u0018\u00010\u0001\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0000¢\u0006\u0004\bQ\u00108\u001a=\u0010P\u001a\u0004\u0018\u00010\u0007\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0000¢\u0006\u0004\bR\u0010:\u001a=\u0010P\u001a\u0004\u0018\u00010\u000b\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0000¢\u0006\u0004\bS\u0010<\u001a=\u0010P\u001a\u0004\u0018\u00010\u000f\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00102\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0000¢\u0006\u0004\bT\u0010>\u001a1\u0010U\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0001`BH\u0007¢\u0006\u0004\bV\u0010D\u001a1\u0010U\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00070Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0007`BH\u0007¢\u0006\u0004\bW\u0010F\u001a1\u0010U\u001a\u0004\u0018\u00010\u000b*\u00020\f2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000b0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000b`BH\u0007¢\u0006\u0004\bX\u0010H\u001a1\u0010U\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000f0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000f`BH\u0007¢\u0006\u0004\bY\u0010J\u001a+\u0010Z\u001a\u00020[*\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020[06H\u0087\bø\u0001\u0000¢\u0006\u0004\b\\\u0010]\u001a+\u0010Z\u001a\u00020[*\u00020\b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020[06H\u0087\bø\u0001\u0000¢\u0006\u0004\b\\\u0010^\u001a+\u0010Z\u001a\u00020[*\u00020\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020[06H\u0087\bø\u0001\u0000¢\u0006\u0004\b\\\u0010_\u001a+\u0010Z\u001a\u00020[*\u00020\u00102\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020[06H\u0087\bø\u0001\u0000¢\u0006\u0004\b\\\u0010`\u001a+\u0010Z\u001a\u00020a*\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020a06H\u0087\bø\u0001\u0000¢\u0006\u0004\bb\u0010c\u001a+\u0010Z\u001a\u00020a*\u00020\b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020a06H\u0087\bø\u0001\u0000¢\u0006\u0004\bb\u0010d\u001a+\u0010Z\u001a\u00020a*\u00020\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020a06H\u0087\bø\u0001\u0000¢\u0006\u0004\bb\u0010e\u001a+\u0010Z\u001a\u00020a*\u00020\u00102\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020a06H\u0087\bø\u0001\u0000¢\u0006\u0004\bb\u0010f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006g"}, d2 = {"elementAt", "Lkotlin/UInt;", "Lkotlin/UIntArray;", "index", "", "elementAt-qFRl0hI", "([II)I", "Lkotlin/ULong;", "Lkotlin/ULongArray;", "elementAt-r7IrZao", "([JI)J", "Lkotlin/UByte;", "Lkotlin/UByteArray;", "elementAt-PpDY95g", "([BI)B", "Lkotlin/UShort;", "Lkotlin/UShortArray;", "elementAt-nggk6HY", "([SI)S", "asList", "", "asList--ajY-9A", "([I)Ljava/util/List;", "asList-QwZRm1k", "([J)Ljava/util/List;", "asList-GBYM_sE", "([B)Ljava/util/List;", "asList-rL5Bavg", "([S)Ljava/util/List;", "binarySearch", "element", "fromIndex", "toIndex", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch-EtDCXyQ", "([SSII)I", "max", "max--ajY-9A", "([I)Lkotlin/UInt;", "max-QwZRm1k", "([J)Lkotlin/ULong;", "max-GBYM_sE", "([B)Lkotlin/UByte;", "max-rL5Bavg", "([S)Lkotlin/UShort;", "maxBy", "R", "", "selector", "Lkotlin/Function1;", "maxBy-jgv0xPQ", "([ILkotlin/jvm/functions/Function1;)Lkotlin/UInt;", "maxBy-MShoTSo", "([JLkotlin/jvm/functions/Function1;)Lkotlin/ULong;", "maxBy-JOV_ifY", "([BLkotlin/jvm/functions/Function1;)Lkotlin/UByte;", "maxBy-xTcfx_M", "([SLkotlin/jvm/functions/Function1;)Lkotlin/UShort;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "maxWith-YmdZ_VM", "([ILjava/util/Comparator;)Lkotlin/UInt;", "maxWith-zrEWJaI", "([JLjava/util/Comparator;)Lkotlin/ULong;", "maxWith-XMRcp5o", "([BLjava/util/Comparator;)Lkotlin/UByte;", "maxWith-eOHTfZs", "([SLjava/util/Comparator;)Lkotlin/UShort;", "min", "min--ajY-9A", "min-QwZRm1k", "min-GBYM_sE", "min-rL5Bavg", "minBy", "minBy-jgv0xPQ", "minBy-MShoTSo", "minBy-JOV_ifY", "minBy-xTcfx_M", "minWith", "minWith-YmdZ_VM", "minWith-zrEWJaI", "minWith-XMRcp5o", "minWith-eOHTfZs", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "Ljava/math/BigInteger;", "sumOfBigInteger", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "kotlin-stdlib"}, k = 5, mv = {2, 1, 0}, pn = "kotlin.collections", xi = ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX, xs = "kotlin/collections/unsigned/UArraysKt")
/* loaded from: classes3.dex */
class UArraysKt___UArraysJvmKt {
    /* renamed from: elementAt-qFRl0hI, reason: not valid java name */
    private static final int m1897elementAtqFRl0hI(int[] elementAt, int i) {
        Intrinsics.checkNotNullParameter(elementAt, "$this$elementAt");
        return UIntArray.m1566getpVg5ArA(elementAt, i);
    }

    /* renamed from: elementAt-r7IrZao, reason: not valid java name */
    private static final long m1898elementAtr7IrZao(long[] elementAt, int i) {
        Intrinsics.checkNotNullParameter(elementAt, "$this$elementAt");
        return ULongArray.m1645getsVKNKU(elementAt, i);
    }

    /* renamed from: elementAt-PpDY95g, reason: not valid java name */
    private static final byte m1895elementAtPpDY95g(byte[] elementAt, int i) {
        Intrinsics.checkNotNullParameter(elementAt, "$this$elementAt");
        return UByteArray.m1487getw2LRezQ(elementAt, i);
    }

    /* renamed from: elementAt-nggk6HY, reason: not valid java name */
    private static final short m1896elementAtnggk6HY(short[] elementAt, int i) {
        Intrinsics.checkNotNullParameter(elementAt, "$this$elementAt");
        return UShortArray.m1750getMh2AYeg(elementAt, i);
    }

    /* renamed from: asList--ajY-9A, reason: not valid java name */
    public static final List<UInt> m1883asListajY9A(int[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$1(asList);
    }

    /* renamed from: asList-QwZRm1k, reason: not valid java name */
    public static final List<ULong> m1885asListQwZRm1k(long[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$2(asList);
    }

    /* renamed from: asList-GBYM_sE, reason: not valid java name */
    public static final List<UByte> m1884asListGBYM_sE(byte[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$3(asList);
    }

    /* renamed from: asList-rL5Bavg, reason: not valid java name */
    public static final List<UShort> m1886asListrL5Bavg(short[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$4(asList);
    }

    /* renamed from: binarySearch-2fe2U9s$default, reason: not valid java name */
    public static /* synthetic */ int m1888binarySearch2fe2U9s$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UIntArray.m1567getSizeimpl(iArr);
        }
        return UArraysKt.m1887binarySearch2fe2U9s(iArr, i, i2, i3);
    }

    /* renamed from: binarySearch-2fe2U9s, reason: not valid java name */
    public static final int m1887binarySearch2fe2U9s(int[] binarySearch, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        AbstractList.INSTANCE.checkRangeIndexes$kotlin_stdlib(i2, i3, UIntArray.m1567getSizeimpl(binarySearch));
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int uintCompare = UnsignedKt.uintCompare(binarySearch[i5], i);
            if (uintCompare < 0) {
                i2 = i5 + 1;
            } else {
                if (uintCompare <= 0) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return -(i2 + 1);
    }

    /* renamed from: binarySearch-K6DWlUc$default, reason: not valid java name */
    public static /* synthetic */ int m1892binarySearchK6DWlUc$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m1646getSizeimpl(jArr);
        }
        return UArraysKt.m1891binarySearchK6DWlUc(jArr, j, i, i2);
    }

    /* renamed from: binarySearch-K6DWlUc, reason: not valid java name */
    public static final int m1891binarySearchK6DWlUc(long[] binarySearch, long j, int i, int i2) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        AbstractList.INSTANCE.checkRangeIndexes$kotlin_stdlib(i, i2, ULongArray.m1646getSizeimpl(binarySearch));
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int ulongCompare = UnsignedKt.ulongCompare(binarySearch[i4], j);
            if (ulongCompare < 0) {
                i = i4 + 1;
            } else {
                if (ulongCompare <= 0) {
                    return i4;
                }
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    /* renamed from: binarySearch-WpHrYlw$default, reason: not valid java name */
    public static /* synthetic */ int m1894binarySearchWpHrYlw$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m1488getSizeimpl(bArr);
        }
        return UArraysKt.m1893binarySearchWpHrYlw(bArr, b, i, i2);
    }

    /* renamed from: binarySearch-WpHrYlw, reason: not valid java name */
    public static final int m1893binarySearchWpHrYlw(byte[] binarySearch, byte b, int i, int i2) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        AbstractList.INSTANCE.checkRangeIndexes$kotlin_stdlib(i, i2, UByteArray.m1488getSizeimpl(binarySearch));
        int i3 = b & 255;
        int i4 = i2 - 1;
        while (i <= i4) {
            int i5 = (i + i4) >>> 1;
            int uintCompare = UnsignedKt.uintCompare(binarySearch[i5], i3);
            if (uintCompare < 0) {
                i = i5 + 1;
            } else {
                if (uintCompare <= 0) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return -(i + 1);
    }

    /* renamed from: binarySearch-EtDCXyQ$default, reason: not valid java name */
    public static /* synthetic */ int m1890binarySearchEtDCXyQ$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m1751getSizeimpl(sArr);
        }
        return UArraysKt.m1889binarySearchEtDCXyQ(sArr, s, i, i2);
    }

    /* renamed from: binarySearch-EtDCXyQ, reason: not valid java name */
    public static final int m1889binarySearchEtDCXyQ(short[] binarySearch, short s, int i, int i2) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        AbstractList.INSTANCE.checkRangeIndexes$kotlin_stdlib(i, i2, UShortArray.m1751getSizeimpl(binarySearch));
        int i3 = s & UShort.MAX_VALUE;
        int i4 = i2 - 1;
        while (i <= i4) {
            int i5 = (i + i4) >>> 1;
            int uintCompare = UnsignedKt.uintCompare(binarySearch[i5], i3);
            if (uintCompare < 0) {
                i = i5 + 1;
            } else {
                if (uintCompare <= 0) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return -(i + 1);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max--ajY-9A, reason: not valid java name */
    public static final /* synthetic */ UInt m1899maxajY9A(int[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        return UArraysKt.m2268maxOrNullajY9A(max);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max-QwZRm1k, reason: not valid java name */
    public static final /* synthetic */ ULong m1901maxQwZRm1k(long[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        return UArraysKt.m2270maxOrNullQwZRm1k(max);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max-GBYM_sE, reason: not valid java name */
    public static final /* synthetic */ UByte m1900maxGBYM_sE(byte[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        return UArraysKt.m2269maxOrNullGBYM_sE(max);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max-rL5Bavg, reason: not valid java name */
    public static final /* synthetic */ UShort m1902maxrL5Bavg(short[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        return UArraysKt.m2271maxOrNullrL5Bavg(max);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-jgv0xPQ, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UInt m1905maxByjgv0xPQ(int[] maxBy, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m1569isEmptyimpl(maxBy)) {
            return null;
        }
        int m1566getpVg5ArA = UIntArray.m1566getpVg5ArA(maxBy, 0);
        int lastIndex = ArraysKt.getLastIndex(maxBy);
        if (lastIndex == 0) {
            return UInt.m1500boximpl(m1566getpVg5ArA);
        }
        R invoke = selector.invoke(UInt.m1500boximpl(m1566getpVg5ArA));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                int m1566getpVg5ArA2 = UIntArray.m1566getpVg5ArA(maxBy, i);
                R invoke2 = selector.invoke(UInt.m1500boximpl(m1566getpVg5ArA2));
                if (invoke.compareTo(invoke2) < 0) {
                    m1566getpVg5ArA = m1566getpVg5ArA2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return UInt.m1500boximpl(m1566getpVg5ArA);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-MShoTSo, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> ULong m1904maxByMShoTSo(long[] maxBy, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m1648isEmptyimpl(maxBy)) {
            return null;
        }
        long m1645getsVKNKU = ULongArray.m1645getsVKNKU(maxBy, 0);
        int lastIndex = ArraysKt.getLastIndex(maxBy);
        if (lastIndex == 0) {
            return ULong.m1579boximpl(m1645getsVKNKU);
        }
        R invoke = selector.invoke(ULong.m1579boximpl(m1645getsVKNKU));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                long m1645getsVKNKU2 = ULongArray.m1645getsVKNKU(maxBy, i);
                R invoke2 = selector.invoke(ULong.m1579boximpl(m1645getsVKNKU2));
                if (invoke.compareTo(invoke2) < 0) {
                    m1645getsVKNKU = m1645getsVKNKU2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return ULong.m1579boximpl(m1645getsVKNKU);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-JOV_ifY, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UByte m1903maxByJOV_ifY(byte[] maxBy, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m1490isEmptyimpl(maxBy)) {
            return null;
        }
        byte m1487getw2LRezQ = UByteArray.m1487getw2LRezQ(maxBy, 0);
        int lastIndex = ArraysKt.getLastIndex(maxBy);
        if (lastIndex == 0) {
            return UByte.m1421boximpl(m1487getw2LRezQ);
        }
        R invoke = selector.invoke(UByte.m1421boximpl(m1487getw2LRezQ));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                byte m1487getw2LRezQ2 = UByteArray.m1487getw2LRezQ(maxBy, i);
                R invoke2 = selector.invoke(UByte.m1421boximpl(m1487getw2LRezQ2));
                if (invoke.compareTo(invoke2) < 0) {
                    m1487getw2LRezQ = m1487getw2LRezQ2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return UByte.m1421boximpl(m1487getw2LRezQ);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-xTcfx_M, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UShort m1906maxByxTcfx_M(short[] maxBy, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m1753isEmptyimpl(maxBy)) {
            return null;
        }
        short m1750getMh2AYeg = UShortArray.m1750getMh2AYeg(maxBy, 0);
        int lastIndex = ArraysKt.getLastIndex(maxBy);
        if (lastIndex == 0) {
            return UShort.m1686boximpl(m1750getMh2AYeg);
        }
        R invoke = selector.invoke(UShort.m1686boximpl(m1750getMh2AYeg));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                short m1750getMh2AYeg2 = UShortArray.m1750getMh2AYeg(maxBy, i);
                R invoke2 = selector.invoke(UShort.m1686boximpl(m1750getMh2AYeg2));
                if (invoke.compareTo(invoke2) < 0) {
                    m1750getMh2AYeg = m1750getMh2AYeg2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return UShort.m1686boximpl(m1750getMh2AYeg);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-YmdZ_VM, reason: not valid java name */
    public static final /* synthetic */ UInt m1908maxWithYmdZ_VM(int[] maxWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return UArraysKt.m2277maxWithOrNullYmdZ_VM(maxWith, comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-zrEWJaI, reason: not valid java name */
    public static final /* synthetic */ ULong m1910maxWithzrEWJaI(long[] maxWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return UArraysKt.m2279maxWithOrNullzrEWJaI(maxWith, comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-XMRcp5o, reason: not valid java name */
    public static final /* synthetic */ UByte m1907maxWithXMRcp5o(byte[] maxWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return UArraysKt.m2276maxWithOrNullXMRcp5o(maxWith, comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-eOHTfZs, reason: not valid java name */
    public static final /* synthetic */ UShort m1909maxWitheOHTfZs(short[] maxWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return UArraysKt.m2278maxWithOrNulleOHTfZs(maxWith, comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min--ajY-9A, reason: not valid java name */
    public static final /* synthetic */ UInt m1911minajY9A(int[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        return UArraysKt.m2324minOrNullajY9A(min);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min-QwZRm1k, reason: not valid java name */
    public static final /* synthetic */ ULong m1913minQwZRm1k(long[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        return UArraysKt.m2326minOrNullQwZRm1k(min);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min-GBYM_sE, reason: not valid java name */
    public static final /* synthetic */ UByte m1912minGBYM_sE(byte[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        return UArraysKt.m2325minOrNullGBYM_sE(min);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min-rL5Bavg, reason: not valid java name */
    public static final /* synthetic */ UShort m1914minrL5Bavg(short[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        return UArraysKt.m2327minOrNullrL5Bavg(min);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-jgv0xPQ, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UInt m1917minByjgv0xPQ(int[] minBy, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m1569isEmptyimpl(minBy)) {
            return null;
        }
        int m1566getpVg5ArA = UIntArray.m1566getpVg5ArA(minBy, 0);
        int lastIndex = ArraysKt.getLastIndex(minBy);
        if (lastIndex == 0) {
            return UInt.m1500boximpl(m1566getpVg5ArA);
        }
        R invoke = selector.invoke(UInt.m1500boximpl(m1566getpVg5ArA));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                int m1566getpVg5ArA2 = UIntArray.m1566getpVg5ArA(minBy, i);
                R invoke2 = selector.invoke(UInt.m1500boximpl(m1566getpVg5ArA2));
                if (invoke.compareTo(invoke2) > 0) {
                    m1566getpVg5ArA = m1566getpVg5ArA2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return UInt.m1500boximpl(m1566getpVg5ArA);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-MShoTSo, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> ULong m1916minByMShoTSo(long[] minBy, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m1648isEmptyimpl(minBy)) {
            return null;
        }
        long m1645getsVKNKU = ULongArray.m1645getsVKNKU(minBy, 0);
        int lastIndex = ArraysKt.getLastIndex(minBy);
        if (lastIndex == 0) {
            return ULong.m1579boximpl(m1645getsVKNKU);
        }
        R invoke = selector.invoke(ULong.m1579boximpl(m1645getsVKNKU));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                long m1645getsVKNKU2 = ULongArray.m1645getsVKNKU(minBy, i);
                R invoke2 = selector.invoke(ULong.m1579boximpl(m1645getsVKNKU2));
                if (invoke.compareTo(invoke2) > 0) {
                    m1645getsVKNKU = m1645getsVKNKU2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return ULong.m1579boximpl(m1645getsVKNKU);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-JOV_ifY, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UByte m1915minByJOV_ifY(byte[] minBy, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m1490isEmptyimpl(minBy)) {
            return null;
        }
        byte m1487getw2LRezQ = UByteArray.m1487getw2LRezQ(minBy, 0);
        int lastIndex = ArraysKt.getLastIndex(minBy);
        if (lastIndex == 0) {
            return UByte.m1421boximpl(m1487getw2LRezQ);
        }
        R invoke = selector.invoke(UByte.m1421boximpl(m1487getw2LRezQ));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                byte m1487getw2LRezQ2 = UByteArray.m1487getw2LRezQ(minBy, i);
                R invoke2 = selector.invoke(UByte.m1421boximpl(m1487getw2LRezQ2));
                if (invoke.compareTo(invoke2) > 0) {
                    m1487getw2LRezQ = m1487getw2LRezQ2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return UByte.m1421boximpl(m1487getw2LRezQ);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-xTcfx_M, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UShort m1918minByxTcfx_M(short[] minBy, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m1753isEmptyimpl(minBy)) {
            return null;
        }
        short m1750getMh2AYeg = UShortArray.m1750getMh2AYeg(minBy, 0);
        int lastIndex = ArraysKt.getLastIndex(minBy);
        if (lastIndex == 0) {
            return UShort.m1686boximpl(m1750getMh2AYeg);
        }
        R invoke = selector.invoke(UShort.m1686boximpl(m1750getMh2AYeg));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                short m1750getMh2AYeg2 = UShortArray.m1750getMh2AYeg(minBy, i);
                R invoke2 = selector.invoke(UShort.m1686boximpl(m1750getMh2AYeg2));
                if (invoke.compareTo(invoke2) > 0) {
                    m1750getMh2AYeg = m1750getMh2AYeg2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return UShort.m1686boximpl(m1750getMh2AYeg);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-YmdZ_VM, reason: not valid java name */
    public static final /* synthetic */ UInt m1920minWithYmdZ_VM(int[] minWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return UArraysKt.m2333minWithOrNullYmdZ_VM(minWith, comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-zrEWJaI, reason: not valid java name */
    public static final /* synthetic */ ULong m1922minWithzrEWJaI(long[] minWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return UArraysKt.m2335minWithOrNullzrEWJaI(minWith, comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-XMRcp5o, reason: not valid java name */
    public static final /* synthetic */ UByte m1919minWithXMRcp5o(byte[] minWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return UArraysKt.m2332minWithOrNullXMRcp5o(minWith, comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-eOHTfZs, reason: not valid java name */
    public static final /* synthetic */ UShort m1921minWitheOHTfZs(short[] minWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return UArraysKt.m2334minWithOrNulleOHTfZs(minWith, comparator);
    }

    private static final BigDecimal sumOfBigDecimal(int[] sumOf, Function1<? super UInt, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal valueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        int m1567getSizeimpl = UIntArray.m1567getSizeimpl(sumOf);
        for (int i = 0; i < m1567getSizeimpl; i++) {
            valueOf = valueOf.add(selector.invoke(UInt.m1500boximpl(UIntArray.m1566getpVg5ArA(sumOf, i))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "add(...)");
        }
        return valueOf;
    }

    private static final BigDecimal sumOfBigDecimal(long[] sumOf, Function1<? super ULong, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal valueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        int m1646getSizeimpl = ULongArray.m1646getSizeimpl(sumOf);
        for (int i = 0; i < m1646getSizeimpl; i++) {
            valueOf = valueOf.add(selector.invoke(ULong.m1579boximpl(ULongArray.m1645getsVKNKU(sumOf, i))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "add(...)");
        }
        return valueOf;
    }

    private static final BigDecimal sumOfBigDecimal(byte[] sumOf, Function1<? super UByte, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal valueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        int m1488getSizeimpl = UByteArray.m1488getSizeimpl(sumOf);
        for (int i = 0; i < m1488getSizeimpl; i++) {
            valueOf = valueOf.add(selector.invoke(UByte.m1421boximpl(UByteArray.m1487getw2LRezQ(sumOf, i))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "add(...)");
        }
        return valueOf;
    }

    private static final BigDecimal sumOfBigDecimal(short[] sumOf, Function1<? super UShort, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal valueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        int m1751getSizeimpl = UShortArray.m1751getSizeimpl(sumOf);
        for (int i = 0; i < m1751getSizeimpl; i++) {
            valueOf = valueOf.add(selector.invoke(UShort.m1686boximpl(UShortArray.m1750getMh2AYeg(sumOf, i))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "add(...)");
        }
        return valueOf;
    }

    private static final BigInteger sumOfBigInteger(int[] sumOf, Function1<? super UInt, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger valueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        int m1567getSizeimpl = UIntArray.m1567getSizeimpl(sumOf);
        for (int i = 0; i < m1567getSizeimpl; i++) {
            valueOf = valueOf.add(selector.invoke(UInt.m1500boximpl(UIntArray.m1566getpVg5ArA(sumOf, i))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "add(...)");
        }
        return valueOf;
    }

    private static final BigInteger sumOfBigInteger(long[] sumOf, Function1<? super ULong, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger valueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        int m1646getSizeimpl = ULongArray.m1646getSizeimpl(sumOf);
        for (int i = 0; i < m1646getSizeimpl; i++) {
            valueOf = valueOf.add(selector.invoke(ULong.m1579boximpl(ULongArray.m1645getsVKNKU(sumOf, i))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "add(...)");
        }
        return valueOf;
    }

    private static final BigInteger sumOfBigInteger(byte[] sumOf, Function1<? super UByte, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger valueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        int m1488getSizeimpl = UByteArray.m1488getSizeimpl(sumOf);
        for (int i = 0; i < m1488getSizeimpl; i++) {
            valueOf = valueOf.add(selector.invoke(UByte.m1421boximpl(UByteArray.m1487getw2LRezQ(sumOf, i))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "add(...)");
        }
        return valueOf;
    }

    private static final BigInteger sumOfBigInteger(short[] sumOf, Function1<? super UShort, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger valueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        int m1751getSizeimpl = UShortArray.m1751getSizeimpl(sumOf);
        for (int i = 0; i < m1751getSizeimpl; i++) {
            valueOf = valueOf.add(selector.invoke(UShort.m1686boximpl(UShortArray.m1750getMh2AYeg(sumOf, i))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "add(...)");
        }
        return valueOf;
    }
}
