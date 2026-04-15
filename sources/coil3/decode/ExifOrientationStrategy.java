package coil3.decode;

import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

/* compiled from: ExifOrientationStrategy.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lcoil3/decode/ExifOrientationStrategy;", "", "supports", "", "mimeType", "", Constants.ScionAnalytics.PARAM_SOURCE, "Lokio/BufferedSource;", "Companion", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ExifOrientationStrategy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final ExifOrientationStrategy IGNORE = new ExifOrientationStrategy() { // from class: coil3.decode.ExifOrientationStrategy$$ExternalSyntheticLambda0
        @Override // coil3.decode.ExifOrientationStrategy
        public final boolean supports(String str, BufferedSource bufferedSource) {
            boolean IGNORE$lambda$0;
            IGNORE$lambda$0 = ExifOrientationStrategy.IGNORE$lambda$0(str, bufferedSource);
            return IGNORE$lambda$0;
        }
    };
    public static final ExifOrientationStrategy RESPECT_PERFORMANCE = new ExifOrientationStrategy() { // from class: coil3.decode.ExifOrientationStrategy$$ExternalSyntheticLambda1
        @Override // coil3.decode.ExifOrientationStrategy
        public final boolean supports(String str, BufferedSource bufferedSource) {
            boolean RESPECT_PERFORMANCE$lambda$1;
            RESPECT_PERFORMANCE$lambda$1 = ExifOrientationStrategy.RESPECT_PERFORMANCE$lambda$1(str, bufferedSource);
            return RESPECT_PERFORMANCE$lambda$1;
        }
    };
    public static final ExifOrientationStrategy RESPECT_ALL = new ExifOrientationStrategy() { // from class: coil3.decode.ExifOrientationStrategy$$ExternalSyntheticLambda2
        @Override // coil3.decode.ExifOrientationStrategy
        public final boolean supports(String str, BufferedSource bufferedSource) {
            boolean RESPECT_ALL$lambda$2;
            RESPECT_ALL$lambda$2 = ExifOrientationStrategy.RESPECT_ALL$lambda$2(str, bufferedSource);
            return RESPECT_ALL$lambda$2;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    static boolean IGNORE$lambda$0(String str, BufferedSource bufferedSource) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static boolean RESPECT_ALL$lambda$2(String str, BufferedSource bufferedSource) {
        return true;
    }

    boolean supports(String mimeType, BufferedSource source);

    /* compiled from: ExifOrientationStrategy.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001R\u0013\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001R\u0013\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\b"}, d2 = {"Lcoil3/decode/ExifOrientationStrategy$Companion;", "", "<init>", "()V", "IGNORE", "Lcoil3/decode/ExifOrientationStrategy;", "RESPECT_PERFORMANCE", "RESPECT_ALL", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static boolean RESPECT_PERFORMANCE$lambda$1(String str, BufferedSource bufferedSource) {
        if (str != null) {
            return Intrinsics.areEqual(str, "image/jpeg") || Intrinsics.areEqual(str, "image/webp") || Intrinsics.areEqual(str, "image/heic") || Intrinsics.areEqual(str, "image/heif");
        }
        return false;
    }
}
