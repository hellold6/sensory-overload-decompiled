package expo.modules.image.events;

import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import expo.modules.image.ExpoImageViewWrapper;
import expo.modules.image.records.ImageErrorEvent;
import expo.modules.image.svg.SVGPictureDrawable;
import expo.modules.kotlin.viewevent.ViewEventCallback;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: GlideRequestListener.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J2\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016J6\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\tH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lexpo/modules/image/events/GlideRequestListener;", "Lcom/bumptech/glide/request/RequestListener;", "Landroid/graphics/drawable/Drawable;", "expoImageViewWrapper", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/image/ExpoImageViewWrapper;", "<init>", "(Ljava/lang/ref/WeakReference;)V", "onLoadFailed", "", "e", "Lcom/bumptech/glide/load/engine/GlideException;", "model", "", "target", "Lcom/bumptech/glide/request/target/Target;", "isFirstResource", "onResourceReady", "resource", "dataSource", "Lcom/bumptech/glide/load/DataSource;", "expo-image_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GlideRequestListener implements RequestListener<Drawable> {
    private final WeakReference<ExpoImageViewWrapper> expoImageViewWrapper;

    public GlideRequestListener(WeakReference<ExpoImageViewWrapper> expoImageViewWrapper) {
        Intrinsics.checkNotNullParameter(expoImageViewWrapper, "expoImageViewWrapper");
        this.expoImageViewWrapper = expoImageViewWrapper;
    }

    @Override // com.bumptech.glide.request.RequestListener
    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
        String str;
        ViewEventCallback<ImageErrorEvent> onError$expo_image_release;
        String message;
        Intrinsics.checkNotNullParameter(target, "target");
        if (e == null || (message = e.getMessage()) == null || (str = StringsKt.removeSuffix(message, (CharSequence) "\n call GlideException#logRootCauses(String) for more detail")) == null) {
            str = "Unknown error";
        }
        ExpoImageViewWrapper expoImageViewWrapper = this.expoImageViewWrapper.get();
        if (expoImageViewWrapper != null && (onError$expo_image_release = expoImageViewWrapper.getOnError$expo_image_release()) != null) {
            onError$expo_image_release.invoke(new ImageErrorEvent(str));
        }
        Log.e("ExpoImage", str);
        if (e == null) {
            return false;
        }
        e.logRootCauses("ExpoImage");
        return false;
    }

    @Override // com.bumptech.glide.request.RequestListener
    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        boolean z = resource instanceof SVGPictureDrawable;
        SVGPictureDrawable sVGPictureDrawable = z ? (SVGPictureDrawable) resource : null;
        int svgIntrinsicWidth = sVGPictureDrawable != null ? sVGPictureDrawable.getSvgIntrinsicWidth() : resource.getIntrinsicWidth();
        SVGPictureDrawable sVGPictureDrawable2 = z ? (SVGPictureDrawable) resource : null;
        int svgIntrinsicHeight = sVGPictureDrawable2 != null ? sVGPictureDrawable2.getSvgIntrinsicHeight() : resource.getIntrinsicHeight();
        ExpoImageViewWrapper expoImageViewWrapper = this.expoImageViewWrapper.get();
        if (expoImageViewWrapper == null) {
            return false;
        }
        BuildersKt__Builders_commonKt.launch$default(expoImageViewWrapper.getAppContext().getMainQueue(), null, null, new GlideRequestListener$onResourceReady$1(expoImageViewWrapper, dataSource, model, svgIntrinsicWidth, svgIntrinsicHeight, resource, null), 3, null);
        return false;
    }
}
