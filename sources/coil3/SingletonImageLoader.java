package coil3;

import android.content.Context;
import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SingletonImageLoader.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0007J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u0011\u001a\u00020\fH\u0007J\u0014\u0010\u0012\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0002R\u0011\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005X\u0082\u0004¨\u0006\u0014"}, d2 = {"Lcoil3/SingletonImageLoader;", "", "<init>", "()V", "reference", "Lkotlinx/atomicfu/AtomicRef;", "get", "Lcoil3/ImageLoader;", "context", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "setSafe", "", "factory", "Lcoil3/SingletonImageLoader$Factory;", "setUnsafe", "imageLoader", "reset", "newImageLoader", "Factory", "coil_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SingletonImageLoader {
    public static final SingletonImageLoader INSTANCE = new SingletonImageLoader();
    private static final /* synthetic */ SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private singletonImageLoader$VolatileWrapper$atomicfu$private = new SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private(null);

    /* compiled from: SingletonImageLoader.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lcoil3/SingletonImageLoader$Factory;", "", "newImageLoader", "Lcoil3/ImageLoader;", "context", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "coil_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* loaded from: classes.dex */
    public interface Factory {
        ImageLoader newImageLoader(Context context);
    }

    private final /* synthetic */ Object updateAndGet$atomicfu(Object obj, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, ? extends Object> function1) {
        Object obj2;
        Object invoke;
        do {
            obj2 = atomicReferenceFieldUpdater.get(obj);
            invoke = function1.invoke(obj2);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, obj, obj2, invoke));
        return invoke;
    }

    private SingletonImageLoader() {
    }

    @JvmStatic
    public static final ImageLoader get(Context context) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        atomicReferenceFieldUpdater = SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private.reference$volatile$FU;
        Object obj = atomicReferenceFieldUpdater.get(singletonImageLoader$VolatileWrapper$atomicfu$private);
        ImageLoader imageLoader = obj instanceof ImageLoader ? (ImageLoader) obj : null;
        return imageLoader == null ? INSTANCE.newImageLoader(context) : imageLoader;
    }

    @JvmStatic
    public static final void setSafe(Factory factory) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2;
        boolean isDefault;
        atomicReferenceFieldUpdater = SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private.reference$volatile$FU;
        Object obj = atomicReferenceFieldUpdater.get(singletonImageLoader$VolatileWrapper$atomicfu$private);
        if (obj instanceof ImageLoader) {
            isDefault = SingletonImageLoaderKt.isDefault((ImageLoader) obj);
            if (isDefault) {
                throw new IllegalStateException("The singleton image loader has already been created. This indicates that\n                    'setSafe' is being called after the first 'get' call. Ensure that 'setSafe' is\n                    called before any Coil API usages (e.g. `load`, `AsyncImage`,\n                    `rememberAsyncImagePainter`, etc.).".toString());
            }
        } else {
            atomicReferenceFieldUpdater2 = SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private.reference$volatile$FU;
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater2, singletonImageLoader$VolatileWrapper$atomicfu$private, obj, factory);
        }
    }

    @JvmStatic
    public static final void setUnsafe(ImageLoader imageLoader) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        atomicReferenceFieldUpdater = SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private.reference$volatile$FU;
        atomicReferenceFieldUpdater.set(singletonImageLoader$VolatileWrapper$atomicfu$private, imageLoader);
    }

    @JvmStatic
    public static final void setUnsafe(Factory factory) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        atomicReferenceFieldUpdater = SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private.reference$volatile$FU;
        atomicReferenceFieldUpdater.set(singletonImageLoader$VolatileWrapper$atomicfu$private, factory);
    }

    @JvmStatic
    public static final void reset() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        atomicReferenceFieldUpdater = SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private.reference$volatile$FU;
        atomicReferenceFieldUpdater.set(singletonImageLoader$VolatileWrapper$atomicfu$private, null);
    }

    private final ImageLoader newImageLoader(Context context) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        ImageLoader imageLoader;
        Factory factory;
        SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private singletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private = singletonImageLoader$VolatileWrapper$atomicfu$private;
        atomicReferenceFieldUpdater = SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private.reference$volatile$FU;
        ImageLoader imageLoader2 = null;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(singletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private);
            if (obj instanceof ImageLoader) {
                imageLoader = imageLoader2;
                imageLoader2 = (ImageLoader) obj;
            } else {
                if (imageLoader2 == null) {
                    Factory factory2 = obj instanceof Factory ? (Factory) obj : null;
                    if (factory2 == null || (imageLoader2 = factory2.newImageLoader(context)) == null) {
                        Factory applicationImageLoaderFactory = SingletonImageLoader_androidKt.applicationImageLoaderFactory(context);
                        if (applicationImageLoaderFactory != null) {
                            imageLoader2 = applicationImageLoaderFactory.newImageLoader(context);
                        } else {
                            factory = SingletonImageLoaderKt.DefaultSingletonImageLoaderFactory;
                            imageLoader2 = factory.newImageLoader(context);
                        }
                    }
                }
                imageLoader = imageLoader2;
            }
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, singletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private, obj, imageLoader2)) {
                Intrinsics.checkNotNull(imageLoader2, "null cannot be cast to non-null type coil3.ImageLoader");
                return imageLoader2;
            }
            imageLoader2 = imageLoader;
        }
    }
}
