package expo.modules.video;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.video.records.VideoThumbnailOptions;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* compiled from: MediaMetadataRetriever.kt */
@Metadata(d1 = {"\u0000H\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001aA\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\b\u0007H\u0086@¢\u0006\u0002\u0010\b\u001a%\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001f\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u0002H\u0002¢\u0006\u0002\u0010\u0018\u001a\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002¨\u0006\u001e"}, d2 = {"safeUse", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/media/MediaMetadataRetriever;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/media/MediaMetadataRetriever;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateThumbnailAtTime", "Lexpo/modules/video/VideoThumbnail;", "requestedTime", "Lkotlin/time/Duration;", "options", "Lexpo/modules/video/records/VideoThumbnailOptions;", "generateThumbnailAtTime-8Mi8wO0", "(Landroid/media/MediaMetadataRetriever;JLexpo/modules/video/records/VideoThumbnailOptions;)Lexpo/modules/video/VideoThumbnail;", "calculateActualFrameTime", "mediaMetadataRetriever", "time", "calculateActualFrameTime-HG0u8IE", "(Landroid/media/MediaMetadataRetriever;J)J", "frameTime", "", "(Landroid/media/MediaMetadataRetriever;)Ljava/lang/Double;", "constrainToDimensions", "Landroid/graphics/Bitmap;", ViewProps.MAX_WIDTH, "", ViewProps.MAX_HEIGHT, "expo-video_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaMetadataRetrieverKt {
    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object safeUse(android.media.MediaMetadataRetriever r4, kotlin.jvm.functions.Function2<? super android.media.MediaMetadataRetriever, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r5, kotlin.coroutines.Continuation<? super T> r6) {
        /*
            boolean r0 = r6 instanceof expo.modules.video.MediaMetadataRetrieverKt$safeUse$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.video.MediaMetadataRetrieverKt$safeUse$1 r0 = (expo.modules.video.MediaMetadataRetrieverKt$safeUse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.video.MediaMetadataRetrieverKt$safeUse$1 r0 = new expo.modules.video.MediaMetadataRetrieverKt$safeUse$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            android.media.MediaMetadataRetriever r4 = (android.media.MediaMetadataRetriever) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L48
            goto L44
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L48
            r0.label = r3     // Catch: java.lang.Throwable -> L48
            java.lang.Object r6 = r5.invoke(r4, r0)     // Catch: java.lang.Throwable -> L48
            if (r6 != r1) goto L44
            return r1
        L44:
            kotlin.UByte$$ExternalSyntheticBackport0.m1479m(r4)
            return r6
        L48:
            r5 = move-exception
            kotlin.UByte$$ExternalSyntheticBackport0.m1479m(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.video.MediaMetadataRetrieverKt.safeUse(android.media.MediaMetadataRetriever, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: generateThumbnailAtTime-8Mi8wO0$default, reason: not valid java name */
    public static /* synthetic */ VideoThumbnail m1388generateThumbnailAtTime8Mi8wO0$default(MediaMetadataRetriever mediaMetadataRetriever, long j, VideoThumbnailOptions videoThumbnailOptions, int i, Object obj) {
        if ((i & 2) != 0) {
            videoThumbnailOptions = null;
        }
        return m1387generateThumbnailAtTime8Mi8wO0(mediaMetadataRetriever, j, videoThumbnailOptions);
    }

    /* renamed from: generateThumbnailAtTime-8Mi8wO0, reason: not valid java name */
    public static final VideoThumbnail m1387generateThumbnailAtTime8Mi8wO0(MediaMetadataRetriever generateThumbnailAtTime, long j, VideoThumbnailOptions videoThumbnailOptions) {
        MediaMetadataRetriever mediaMetadataRetriever;
        Intrinsics.checkNotNullParameter(generateThumbnailAtTime, "$this$generateThumbnailAtTime");
        Bitmap bitmap = null;
        Pair<Integer, Integer> nativeSizeLimit = videoThumbnailOptions != null ? videoThumbnailOptions.toNativeSizeLimit() : null;
        if (nativeSizeLimit != null) {
            int intValue = nativeSizeLimit.component1().intValue();
            int intValue2 = nativeSizeLimit.component2().intValue();
            if (Build.VERSION.SDK_INT >= 27) {
                mediaMetadataRetriever = generateThumbnailAtTime;
                bitmap = mediaMetadataRetriever.getScaledFrameAtTime(Duration.m2781getInWholeMicrosecondsimpl(j), 3, intValue, intValue2);
            } else {
                mediaMetadataRetriever = generateThumbnailAtTime;
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(Duration.m2781getInWholeMicrosecondsimpl(j), 3);
                if (frameAtTime != null) {
                    bitmap = constrainToDimensions(frameAtTime, intValue, intValue2);
                }
            }
        } else {
            mediaMetadataRetriever = generateThumbnailAtTime;
            bitmap = mediaMetadataRetriever.getFrameAtTime(Duration.m2781getInWholeMicrosecondsimpl(j), 3);
        }
        Bitmap bitmap2 = bitmap;
        if (bitmap2 == null) {
            throw new IllegalStateException("Failed to generate thumbnail");
        }
        return new VideoThumbnail(bitmap2, j, m1386calculateActualFrameTimeHG0u8IE(mediaMetadataRetriever, j), null);
    }

    /* renamed from: calculateActualFrameTime-HG0u8IE, reason: not valid java name */
    private static final long m1386calculateActualFrameTimeHG0u8IE(MediaMetadataRetriever mediaMetadataRetriever, long j) {
        Double frameTime = frameTime(mediaMetadataRetriever);
        if (frameTime == null) {
            return j;
        }
        return DurationKt.toDuration(MathKt.roundToLong(Duration.m2781getInWholeMicrosecondsimpl(j) / r0) * frameTime.doubleValue(), DurationUnit.MICROSECONDS);
    }

    private static final Double frameTime(MediaMetadataRetriever mediaMetadataRetriever) {
        String extractMetadata;
        if (Build.VERSION.SDK_INT >= 28 && (extractMetadata = mediaMetadataRetriever.extractMetadata(9)) != null) {
            double parseDouble = Double.parseDouble(extractMetadata);
            String extractMetadata2 = mediaMetadataRetriever.extractMetadata(32);
            if (extractMetadata2 != null) {
                return Double.valueOf((parseDouble * 1000) / Double.parseDouble(extractMetadata2));
            }
        }
        return null;
    }

    private static final Bitmap constrainToDimensions(Bitmap bitmap, int i, int i2) {
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        float max = Math.max(width / i, height / i2);
        if (max <= 1.0f) {
            return bitmap;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (width / max), (int) (height / max), true);
        Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(...)");
        return createScaledBitmap;
    }
}
