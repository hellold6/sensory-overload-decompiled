package com.facebook.imageformat;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageFormatCheckerUtils.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007J \u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0007J(\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000eH\u0007¨\u0006\u0012"}, d2 = {"Lcom/facebook/imageformat/ImageFormatCheckerUtils;", "", "<init>", "()V", "asciiBytes", "", "value", "", "startsWithPattern", "", "byteArray", "pattern", "hasPatternAt", "offset", "", "indexOfPattern", "byteArrayLen", "patternLen", "imagepipeline-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageFormatCheckerUtils {
    public static final ImageFormatCheckerUtils INSTANCE = new ImageFormatCheckerUtils();

    private ImageFormatCheckerUtils() {
    }

    @JvmStatic
    public static final byte[] asciiBytes(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            Charset forName = Charset.forName("ASCII");
            Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
            byte[] bytes = value.getBytes(forName);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return bytes;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    @JvmStatic
    public static final boolean startsWithPattern(byte[] byteArray, byte[] pattern) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return hasPatternAt(byteArray, pattern, 0);
    }

    @JvmStatic
    public static final boolean hasPatternAt(byte[] byteArray, byte[] pattern, int offset) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        if (pattern.length + offset > byteArray.length) {
            return false;
        }
        Iterable indices = ArraysKt.getIndices(pattern);
        if ((indices instanceof Collection) && ((Collection) indices).isEmpty()) {
            return true;
        }
        Iterator it = indices.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            if (byteArray[offset + nextInt] != pattern[nextInt]) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001a, code lost:
    
        if (r1 > r9) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001e, code lost:
    
        if (r8[r1] != r2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0020, code lost:
    
        if (r1 > r9) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0022, code lost:
    
        r3 = r1 + 1;
        r5 = (r3 + r11) - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0027, code lost:
    
        if (r3 >= r5) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002d, code lost:
    
        if (r8[r3] != r10[r4]) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x002f, code lost:
    
        r3 = r3 + 1;
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0034, code lost:
    
        if (r3 != r5) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0036, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0037, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        if (r8[r1] != r2) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
    
        r1 = r1 + 1;
     */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int indexOfPattern(byte[] r8, int r9, byte[] r10, int r11) {
        /*
            java.lang.String r0 = "byteArray"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "pattern"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            r0 = -1
            if (r11 <= r9) goto Le
            return r0
        Le:
            r1 = 0
            r2 = r10[r1]
            int r9 = r9 - r11
        L12:
            if (r1 > r9) goto L3a
            r3 = r8[r1]
            r4 = 1
            if (r3 == r2) goto L20
        L19:
            int r1 = r1 + r4
            if (r1 > r9) goto L20
            r3 = r8[r1]
            if (r3 != r2) goto L19
        L20:
            if (r1 > r9) goto L37
            int r3 = r1 + 1
            int r5 = r3 + r11
            int r5 = r5 - r4
        L27:
            if (r3 >= r5) goto L34
            r6 = r8[r3]
            r7 = r10[r4]
            if (r6 != r7) goto L34
            int r3 = r3 + 1
            int r4 = r4 + 1
            goto L27
        L34:
            if (r3 != r5) goto L37
            return r1
        L37:
            int r1 = r1 + 1
            goto L12
        L3a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imageformat.ImageFormatCheckerUtils.indexOfPattern(byte[], int, byte[], int):int");
    }
}
