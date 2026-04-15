package kotlin;

import android.content.res.TypedArray;
import android.media.MediaDrm;
import android.media.MediaMetadataRetriever;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes3.dex */
public final /* synthetic */ class UByte$$ExternalSyntheticBackport0 {
    public static /* synthetic */ int m(byte b) {
        return b & 255;
    }

    public static /* synthetic */ int m(int i, int i2) {
        return (int) ((i & 4294967295L) / (i2 & 4294967295L));
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ long m1478m(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? j : j - j2;
        }
        if (j >= 0) {
            return j % j2;
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if ((j3 ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE)) {
            j2 = 0;
        }
        return j3 - j2;
    }

    public static /* synthetic */ String m(CharSequence charSequence, Iterable iterable) {
        if (charSequence == null) {
            throw new NullPointerException(TtmlNode.RUBY_DELIMITER);
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            while (true) {
                sb.append((CharSequence) it.next());
                if (!it.hasNext()) {
                    break;
                }
                sb.append(charSequence);
            }
        }
        return sb.toString();
    }

    public static /* synthetic */ String m(String str) {
        int length = str.length();
        while (length > 0) {
            int codePointBefore = Character.codePointBefore(str, length);
            if (!Character.isWhitespace(codePointBefore)) {
                break;
            }
            length -= Character.charCount(codePointBefore);
        }
        return str.substring(0, length);
    }

    public static /* synthetic */ List m(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(Objects.requireNonNull(obj));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m1479m(Object obj) {
        if (obj instanceof AutoCloseable) {
            ((AutoCloseable) obj).close();
            return;
        }
        if (obj instanceof ExecutorService) {
            m((ExecutorService) obj);
            return;
        }
        if (obj instanceof TypedArray) {
            ((TypedArray) obj).recycle();
            return;
        }
        if (obj instanceof MediaMetadataRetriever) {
            ((MediaMetadataRetriever) obj).release();
        } else if (obj instanceof MediaDrm) {
            ((MediaDrm) obj).release();
        } else {
            m$1(obj);
        }
    }

    public static /* synthetic */ void m(ExecutorService executorService) {
        boolean isTerminated;
        if (executorService == ForkJoinPool.commonPool() || (isTerminated = executorService.isTerminated())) {
            return;
        }
        executorService.shutdown();
        boolean z = false;
        while (!isTerminated) {
            try {
                isTerminated = executorService.awaitTermination(1L, TimeUnit.DAYS);
            } catch (InterruptedException unused) {
                if (!z) {
                    executorService.shutdownNow();
                    z = true;
                }
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public static /* synthetic */ int m$1(int i, int i2) {
        return (int) ((i & 4294967295L) % (i2 & 4294967295L));
    }

    public static /* synthetic */ long m$1(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0L : 1L;
        }
        if (j >= 0) {
            return j / j2;
        }
        long j3 = ((j >>> 1) / j2) << 1;
        return j3 + (((j - (j3 * j2)) ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0 : 1);
    }

    public static /* synthetic */ void m$1(Object obj) {
        throw new IllegalArgumentException();
    }
}
