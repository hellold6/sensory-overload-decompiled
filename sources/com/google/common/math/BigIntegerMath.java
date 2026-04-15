package com.google.common.math;

import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class BigIntegerMath {
    static final int SQRT2_PRECOMPUTE_THRESHOLD = 256;
    static final BigInteger SQRT2_PRECOMPUTED_BITS = new BigInteger("16a09e667f3bcc908b2fb1366ea957d3e3adec17512775099da2f590b0667322a", 16);
    private static final double LN_10 = Math.log(10.0d);
    private static final double LN_2 = Math.log(2.0d);

    public static BigInteger ceilingPowerOfTwo(BigInteger x) {
        return BigInteger.ZERO.setBit(log2(x, RoundingMode.CEILING));
    }

    public static BigInteger floorPowerOfTwo(BigInteger x) {
        return BigInteger.ZERO.setBit(log2(x, RoundingMode.FLOOR));
    }

    public static boolean isPowerOfTwo(BigInteger x) {
        Preconditions.checkNotNull(x);
        return x.signum() > 0 && x.getLowestSetBit() == x.bitLength() - 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0051 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int log2(java.math.BigInteger r3, java.math.RoundingMode r4) {
        /*
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r3)
            java.math.BigInteger r0 = (java.math.BigInteger) r0
            java.lang.String r1 = "x"
            com.google.common.math.MathPreconditions.checkPositive(r1, r0)
            int r0 = r3.bitLength()
            int r1 = r0 + (-1)
            int[] r2 = com.google.common.math.BigIntegerMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r4 = r4.ordinal()
            r4 = r2[r4]
            switch(r4) {
                case 1: goto L52;
                case 2: goto L51;
                case 3: goto L51;
                case 4: goto L49;
                case 5: goto L49;
                case 6: goto L22;
                case 7: goto L22;
                case 8: goto L22;
                default: goto L1c;
            }
        L1c:
            java.lang.AssertionError r3 = new java.lang.AssertionError
            r3.<init>()
            throw r3
        L22:
            r4 = 256(0x100, float:3.59E-43)
            if (r1 >= r4) goto L36
            java.math.BigInteger r4 = com.google.common.math.BigIntegerMath.SQRT2_PRECOMPUTED_BITS
            int r2 = 256 - r1
            java.math.BigInteger r4 = r4.shiftRight(r2)
            int r3 = r3.compareTo(r4)
            if (r3 > 0) goto L35
            goto L51
        L35:
            return r0
        L36:
            r4 = 2
            java.math.BigInteger r3 = r3.pow(r4)
            int r3 = r3.bitLength()
            int r3 = r3 + (-1)
            int r4 = r1 * 2
            int r4 = r4 + 1
            if (r3 >= r4) goto L48
            goto L51
        L48:
            return r0
        L49:
            boolean r3 = isPowerOfTwo(r3)
            if (r3 == 0) goto L50
            goto L51
        L50:
            return r0
        L51:
            return r1
        L52:
            boolean r3 = isPowerOfTwo(r3)
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.BigIntegerMath.log2(java.math.BigInteger, java.math.RoundingMode):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.BigIntegerMath$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int log10(java.math.BigInteger r6, java.math.RoundingMode r7) {
        /*
            java.lang.String r0 = "x"
            com.google.common.math.MathPreconditions.checkPositive(r0, r6)
            boolean r0 = fitsInLong(r6)
            if (r0 == 0) goto L14
            long r0 = r6.longValue()
            int r6 = com.google.common.math.LongMath.log10(r0, r7)
            return r6
        L14:
            java.math.RoundingMode r0 = java.math.RoundingMode.FLOOR
            int r0 = log2(r6, r0)
            double r0 = (double) r0
            double r2 = com.google.common.math.BigIntegerMath.LN_2
            double r0 = r0 * r2
            double r2 = com.google.common.math.BigIntegerMath.LN_10
            double r0 = r0 / r2
            int r0 = (int) r0
            java.math.BigInteger r1 = java.math.BigInteger.TEN
            java.math.BigInteger r1 = r1.pow(r0)
            int r2 = r1.compareTo(r6)
            if (r2 <= 0) goto L3d
        L2e:
            int r0 = r0 + (-1)
            java.math.BigInteger r2 = java.math.BigInteger.TEN
            java.math.BigInteger r1 = r1.divide(r2)
            int r2 = r1.compareTo(r6)
            if (r2 > 0) goto L2e
            goto L5d
        L3d:
            java.math.BigInteger r3 = java.math.BigInteger.TEN
            java.math.BigInteger r3 = r3.multiply(r1)
            int r4 = r3.compareTo(r6)
        L47:
            r5 = r4
            r4 = r2
            r2 = r5
            if (r2 > 0) goto L5c
            int r0 = r0 + 1
            java.math.BigInteger r1 = java.math.BigInteger.TEN
            java.math.BigInteger r1 = r1.multiply(r3)
            int r4 = r1.compareTo(r6)
            r5 = r3
            r3 = r1
            r1 = r5
            goto L47
        L5c:
            r2 = r4
        L5d:
            int[] r3 = com.google.common.math.BigIntegerMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r7 = r7.ordinal()
            r7 = r3[r7]
            r3 = 1
            switch(r7) {
                case 1: goto L90;
                case 2: goto L8f;
                case 3: goto L8f;
                case 4: goto L87;
                case 5: goto L87;
                case 6: goto L6f;
                case 7: goto L6f;
                case 8: goto L6f;
                default: goto L69;
            }
        L69:
            java.lang.AssertionError r6 = new java.lang.AssertionError
            r6.<init>()
            throw r6
        L6f:
            r7 = 2
            java.math.BigInteger r6 = r6.pow(r7)
            java.math.BigInteger r7 = r1.pow(r7)
            java.math.BigInteger r1 = java.math.BigInteger.TEN
            java.math.BigInteger r7 = r7.multiply(r1)
            int r6 = r6.compareTo(r7)
            if (r6 > 0) goto L85
            goto L8f
        L85:
            int r0 = r0 + r3
            return r0
        L87:
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L8e
            goto L8f
        L8e:
            int r0 = r0 + r3
        L8f:
            return r0
        L90:
            if (r2 != 0) goto L93
            goto L94
        L93:
            r3 = 0
        L94:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.BigIntegerMath.log10(java.math.BigInteger, java.math.RoundingMode):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static BigInteger sqrt(BigInteger x, RoundingMode mode) {
        MathPreconditions.checkNonNegative("x", x);
        if (fitsInLong(x)) {
            return BigInteger.valueOf(LongMath.sqrt(x.longValue(), mode));
        }
        BigInteger sqrtFloor = sqrtFloor(x);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(sqrtFloor.pow(2).equals(x));
                return sqrtFloor;
            case 2:
            case 3:
                return sqrtFloor;
            case 4:
            case 5:
                int intValue = sqrtFloor.intValue();
                return (intValue * intValue == x.intValue() && sqrtFloor.pow(2).equals(x)) ? sqrtFloor : sqrtFloor.add(BigInteger.ONE);
            case 6:
            case 7:
            case 8:
                if (sqrtFloor.pow(2).add(sqrtFloor).compareTo(x) < 0) {
                    return sqrtFloor.add(BigInteger.ONE);
                }
                return sqrtFloor;
            default:
                throw new AssertionError();
        }
    }

    private static BigInteger sqrtFloor(BigInteger x) {
        BigInteger shiftLeft;
        int log2 = log2(x, RoundingMode.FLOOR);
        if (log2 < 1023) {
            shiftLeft = sqrtApproxWithDoubles(x);
        } else {
            int i = (log2 - 52) & (-2);
            shiftLeft = sqrtApproxWithDoubles(x.shiftRight(i)).shiftLeft(i >> 1);
        }
        BigInteger shiftRight = shiftLeft.add(x.divide(shiftLeft)).shiftRight(1);
        if (shiftLeft.equals(shiftRight)) {
            return shiftLeft;
        }
        while (true) {
            BigInteger shiftRight2 = shiftRight.add(x.divide(shiftRight)).shiftRight(1);
            if (shiftRight2.compareTo(shiftRight) >= 0) {
                return shiftRight;
            }
            shiftRight = shiftRight2;
        }
    }

    private static BigInteger sqrtApproxWithDoubles(BigInteger x) {
        return DoubleMath.roundToBigInteger(Math.sqrt(DoubleUtils.bigToDouble(x)), RoundingMode.HALF_EVEN);
    }

    public static double roundToDouble(BigInteger x, RoundingMode mode) {
        return BigIntegerToDoubleRounder.INSTANCE.roundToDouble(x, mode);
    }

    /* loaded from: classes3.dex */
    private static class BigIntegerToDoubleRounder extends ToDoubleRounder<BigInteger> {
        static final BigIntegerToDoubleRounder INSTANCE = new BigIntegerToDoubleRounder();

        private BigIntegerToDoubleRounder() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.math.ToDoubleRounder
        public double roundToDoubleArbitrarily(BigInteger bigInteger) {
            return DoubleUtils.bigToDouble(bigInteger);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.math.ToDoubleRounder
        public int sign(BigInteger bigInteger) {
            return bigInteger.signum();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.math.ToDoubleRounder
        public BigInteger toX(double d, RoundingMode mode) {
            return DoubleMath.roundToBigInteger(d, mode);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.math.ToDoubleRounder
        public BigInteger minus(BigInteger a2, BigInteger b) {
            return a2.subtract(b);
        }
    }

    public static BigInteger divide(BigInteger p, BigInteger q, RoundingMode mode) {
        return new BigDecimal(p).divide(new BigDecimal(q), 0, mode).toBigIntegerExact();
    }

    public static BigInteger factorial(int n) {
        MathPreconditions.checkNonNegative("n", n);
        if (n < LongMath.factorials.length) {
            return BigInteger.valueOf(LongMath.factorials[n]);
        }
        ArrayList arrayList = new ArrayList(IntMath.divide(IntMath.log2(n, RoundingMode.CEILING) * n, 64, RoundingMode.CEILING));
        int length = LongMath.factorials.length;
        long j = LongMath.factorials[length - 1];
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
        long j2 = j >> numberOfTrailingZeros;
        int i = 1;
        int log2 = LongMath.log2(j2, RoundingMode.FLOOR) + 1;
        long j3 = length;
        int log22 = LongMath.log2(j3, RoundingMode.FLOOR);
        int i2 = log22 + 1;
        int i3 = 1 << log22;
        while (j3 <= n) {
            int i4 = i;
            long j4 = j3;
            if ((i3 & j4) != 0) {
                i3 <<= 1;
                i2++;
            }
            int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j4);
            long j5 = j4 >> numberOfTrailingZeros2;
            numberOfTrailingZeros += numberOfTrailingZeros2;
            if ((i2 - numberOfTrailingZeros2) + log2 >= 64) {
                arrayList.add(BigInteger.valueOf(j2));
                j2 = 1;
            }
            j2 *= j5;
            log2 = LongMath.log2(j2, RoundingMode.FLOOR) + i4;
            j3 = j4 + 1;
            i = i4;
        }
        if (j2 > 1) {
            arrayList.add(BigInteger.valueOf(j2));
        }
        return listProduct(arrayList).shiftLeft(numberOfTrailingZeros);
    }

    static BigInteger listProduct(List<BigInteger> nums) {
        return listProduct(nums, 0, nums.size());
    }

    static BigInteger listProduct(List<BigInteger> nums, int start, int end) {
        int i = end - start;
        if (i == 0) {
            return BigInteger.ONE;
        }
        if (i == 1) {
            return nums.get(start);
        }
        if (i == 2) {
            return nums.get(start).multiply(nums.get(start + 1));
        }
        if (i == 3) {
            return nums.get(start).multiply(nums.get(start + 1)).multiply(nums.get(start + 2));
        }
        int i2 = (end + start) >>> 1;
        return listProduct(nums, start, i2).multiply(listProduct(nums, i2, end));
    }

    public static BigInteger binomial(int n, int k) {
        int i;
        MathPreconditions.checkNonNegative("n", n);
        MathPreconditions.checkNonNegative("k", k);
        int i2 = 1;
        Preconditions.checkArgument(k <= n, "k (%s) > n (%s)", k, n);
        if (k > (n >> 1)) {
            k = n - k;
        }
        if (k < LongMath.biggestBinomials.length && n <= LongMath.biggestBinomials[k]) {
            return BigInteger.valueOf(LongMath.binomial(n, k));
        }
        BigInteger bigInteger = BigInteger.ONE;
        long j = n;
        int log2 = LongMath.log2(j, RoundingMode.CEILING);
        long j2 = 1;
        while (true) {
            int i3 = log2;
            while (i2 < k) {
                i = n - i2;
                i2++;
                i3 += log2;
                if (i3 >= 63) {
                    break;
                }
                j *= i;
                j2 *= i2;
            }
            return bigInteger.multiply(BigInteger.valueOf(j)).divide(BigInteger.valueOf(j2));
            bigInteger = bigInteger.multiply(BigInteger.valueOf(j)).divide(BigInteger.valueOf(j2));
            j = i;
            j2 = i2;
        }
    }

    static boolean fitsInLong(BigInteger x) {
        return x.bitLength() <= 63;
    }

    private BigIntegerMath() {
    }
}
