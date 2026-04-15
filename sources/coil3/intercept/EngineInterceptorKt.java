package coil3.intercept;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import coil3.request.ImageRequests_androidKt;
import coil3.request.Options;
import coil3.size.Precision;
import coil3.transform.Transformation;
import coil3.util.BitmapsKt;
import coil3.util.DrawableUtils;
import coil3.util.Logger;
import coil3.util.Utils_androidKt;
import com.facebook.react.uimanager.ViewProps;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Reflection;
import org.apache.commons.io.FilenameUtils;

/* compiled from: EngineInterceptor.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0080@¢\u0006\u0002\u0010\u000b\u001a0\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¨\u0006\u0013"}, d2 = {ViewProps.TRANSFORM, "Lcoil3/intercept/EngineInterceptor$ExecuteResult;", "result", "request", "Lcoil3/request/ImageRequest;", "options", "Lcoil3/request/Options;", "eventListener", "Lcoil3/EventListener;", "logger", "Lcoil3/util/Logger;", "(Lcoil3/intercept/EngineInterceptor$ExecuteResult;Lcoil3/request/ImageRequest;Lcoil3/request/Options;Lcoil3/EventListener;Lcoil3/util/Logger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertDrawableToBitmap", "Landroid/graphics/Bitmap;", "drawable", "Landroid/graphics/drawable/Drawable;", "transformations", "", "Lcoil3/transform/Transformation;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EngineInterceptorKt {
    /* JADX WARN: Removed duplicated region for block: B:12:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00f7 -> B:10:0x00fc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object transform(coil3.intercept.EngineInterceptor.ExecuteResult r18, coil3.request.ImageRequest r19, coil3.request.Options r20, coil3.EventListener r21, coil3.util.Logger r22, kotlin.coroutines.Continuation<? super coil3.intercept.EngineInterceptor.ExecuteResult> r23) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil3.intercept.EngineInterceptorKt.transform(coil3.intercept.EngineInterceptor$ExecuteResult, coil3.request.ImageRequest, coil3.request.Options, coil3.EventListener, coil3.util.Logger, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Bitmap convertDrawableToBitmap(Drawable drawable, Options options, List<? extends Transformation> list, Logger logger) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap.Config safeConfig = BitmapsKt.getSafeConfig(bitmap);
            if (ArraysKt.contains(Utils_androidKt.getVALID_TRANSFORMATION_CONFIGS(), safeConfig)) {
                return bitmap;
            }
            if (logger != null) {
                Logger.Level level = Logger.Level.Info;
                if (logger.getMinLevel().compareTo(level) <= 0) {
                    logger.log(EngineInterceptor.TAG, level, "Converting bitmap with config " + safeConfig + " to apply transformations: " + list + FilenameUtils.EXTENSION_SEPARATOR, null);
                }
            }
        } else if (logger != null) {
            Logger.Level level2 = Logger.Level.Info;
            if (logger.getMinLevel().compareTo(level2) <= 0) {
                logger.log(EngineInterceptor.TAG, level2, "Converting drawable of type " + Reflection.getOrCreateKotlinClass(drawable.getClass()).getQualifiedName() + " to apply transformations: " + list + FilenameUtils.EXTENSION_SEPARATOR, null);
            }
        }
        return DrawableUtils.INSTANCE.convertToBitmap(drawable, ImageRequests_androidKt.getBitmapConfig(options), options.getSize(), options.getScale(), options.getPrecision() == Precision.INEXACT);
    }
}
