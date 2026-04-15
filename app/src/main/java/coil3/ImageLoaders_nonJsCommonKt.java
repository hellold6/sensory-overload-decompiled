package coil3;

import coil3.request.ImageRequest;
import coil3.request.ImageResult;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__BuildersKt;

/* compiled from: imageLoaders.nonJsCommon.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"executeBlocking", "Lcoil3/request/ImageResult;", "Lcoil3/ImageLoader;", "request", "Lcoil3/request/ImageRequest;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageLoaders_nonJsCommonKt {
    public static final ImageResult executeBlocking(ImageLoader imageLoader, ImageRequest imageRequest) {
        Object runBlocking$default;
        runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new ImageLoaders_nonJsCommonKt$executeBlocking$1(imageLoader, imageRequest, null), 1, null);
        return (ImageResult) runBlocking$default;
    }
}
