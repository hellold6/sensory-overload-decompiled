package coil3;

import android.content.Context;
import androidx.media3.common.MimeTypes;
import coil3.ComponentRegistry;
import coil3.EventListener;
import coil3.Extras;
import coil3.ImageLoader;
import coil3.RealImageLoader;
import coil3.disk.DiskCache;
import coil3.disk.UtilsKt;
import coil3.memory.MemoryCache;
import coil3.request.CachePolicy;
import coil3.request.Disposable;
import coil3.request.ImageRequest;
import coil3.request.ImageResult;
import coil3.size.Precision;
import coil3.util.ContextsKt;
import coil3.util.Logger;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.Constants;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import okio.FileSystem;

/* compiled from: ImageLoader.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u001dJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H¦@¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001eÀ\u0006\u0001"}, d2 = {"Lcoil3/ImageLoader;", "", "defaults", "Lcoil3/request/ImageRequest$Defaults;", "getDefaults", "()Lcoil3/request/ImageRequest$Defaults;", "components", "Lcoil3/ComponentRegistry;", "getComponents", "()Lcoil3/ComponentRegistry;", "memoryCache", "Lcoil3/memory/MemoryCache;", "getMemoryCache", "()Lcoil3/memory/MemoryCache;", "diskCache", "Lcoil3/disk/DiskCache;", "getDiskCache", "()Lcoil3/disk/DiskCache;", "enqueue", "Lcoil3/request/Disposable;", "request", "Lcoil3/request/ImageRequest;", "execute", "Lcoil3/request/ImageResult;", "(Lcoil3/request/ImageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shutdown", "", "newBuilder", "Lcoil3/ImageLoader$Builder;", "Builder", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ImageLoader {
    Disposable enqueue(ImageRequest request);

    Object execute(ImageRequest imageRequest, Continuation<? super ImageResult> continuation);

    ComponentRegistry getComponents();

    ImageRequest.Defaults getDefaults();

    DiskCache getDiskCache();

    MemoryCache getMemoryCache();

    Builder newBuilder();

    void shutdown();

    /* compiled from: ImageLoader.kt */
    @Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\b\u0016\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\"\u0010\u001d\u001a\u00020\u00002\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f¢\u0006\u0002\b\"H\u0086\bJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0016J\u0010\u0010#\u001a\u00020\u00002\b\u0010#\u001a\u0004\u0018\u00010\u0010J\u0016\u0010#\u001a\u00020\u00002\u000e\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100%J\u0010\u0010&\u001a\u00020\u00002\b\u0010&\u001a\u0004\u0018\u00010\u0012J\u0016\u0010&\u001a\u00020\u00002\u000e\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120%J\u000e\u0010'\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0014J\u000e\u0010-\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.J\u000e\u0010/\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u000200J\u000e\u00101\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u000200J\u000e\u00102\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u000200J\u000e\u00103\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u000200J\u0010\u00104\u001a\u00020\u00002\b\u00105\u001a\u0004\u0018\u000106J\u001c\u00104\u001a\u00020\u00002\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u000207\u0012\u0006\u0012\u0004\u0018\u0001060\u001fJ\u0010\u00108\u001a\u00020\u00002\b\u00105\u001a\u0004\u0018\u000106J\u001c\u00108\u001a\u00020\u00002\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u000207\u0012\u0006\u0012\u0004\u0018\u0001060\u001fJ\u0010\u00109\u001a\u00020\u00002\b\u00105\u001a\u0004\u0018\u000106J\u001c\u00109\u001a\u00020\u00002\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u000207\u0012\u0006\u0012\u0004\u0018\u0001060\u001fJ\u000e\u0010:\u001a\u00020\u00002\u0006\u0010;\u001a\u00020<J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010;\u001a\u00020<J\u000e\u0010>\u001a\u00020\u00002\u0006\u0010;\u001a\u00020<J\u0010\u0010\u0017\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0006\u0010?\u001a\u00020@R\u0014\u0010\n\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006A"}, d2 = {"Lcoil3/ImageLoader$Builder;", "", "context", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "<init>", "(Landroid/content/Context;)V", "options", "Lcoil3/RealImageLoader$Options;", "(Lcoil3/RealImageLoader$Options;)V", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/content/Context;", "defaults", "Lcoil3/request/ImageRequest$Defaults;", "memoryCacheLazy", "Lkotlin/Lazy;", "Lcoil3/memory/MemoryCache;", "diskCacheLazy", "Lcoil3/disk/DiskCache;", "eventListenerFactory", "Lcoil3/EventListener$Factory;", "componentRegistry", "Lcoil3/ComponentRegistry;", "logger", "Lcoil3/util/Logger;", "extras", "Lcoil3/Extras$Builder;", "getExtras", "()Lcoil3/Extras$Builder;", "components", "builder", "Lkotlin/Function1;", "Lcoil3/ComponentRegistry$Builder;", "", "Lkotlin/ExtensionFunctionType;", "memoryCache", "initializer", "Lkotlin/Function0;", "diskCache", "fileSystem", "Lokio/FileSystem;", "eventListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcoil3/EventListener;", "factory", "precision", "Lcoil3/size/Precision;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "interceptorCoroutineContext", "fetcherCoroutineContext", "decoderCoroutineContext", ReactTextInputShadowNode.PROP_PLACEHOLDER, "image", "Lcoil3/Image;", "Lcoil3/request/ImageRequest;", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "fallback", "memoryCachePolicy", "policy", "Lcoil3/request/CachePolicy;", "diskCachePolicy", "networkCachePolicy", "build", "Lcoil3/ImageLoader;", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Builder {
        private final Context application;
        private ComponentRegistry componentRegistry;
        private ImageRequest.Defaults defaults;
        private Lazy<? extends DiskCache> diskCacheLazy;
        private EventListener.Factory eventListenerFactory;
        private final Extras.Builder extras;
        private Logger logger;
        private Lazy<? extends MemoryCache> memoryCacheLazy;

        /* JADX INFO: Access modifiers changed from: private */
        public static final Image error$lambda$15(Image image, ImageRequest imageRequest) {
            return image;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final EventListener eventListener$lambda$6(EventListener eventListener, ImageRequest imageRequest) {
            return eventListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Image fallback$lambda$17(Image image, ImageRequest imageRequest) {
            return image;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Image placeholder$lambda$13(Image image, ImageRequest imageRequest) {
            return image;
        }

        public final Extras.Builder getExtras() {
            return this.extras;
        }

        public Builder(Context context) {
            this.application = ContextsKt.getApplication(context);
            this.defaults = ImageRequest.Defaults.DEFAULT;
            this.memoryCacheLazy = null;
            this.diskCacheLazy = null;
            this.eventListenerFactory = null;
            this.componentRegistry = null;
            this.logger = null;
            this.extras = new Extras.Builder();
        }

        public Builder(RealImageLoader.Options options) {
            this.application = options.getApplication();
            this.defaults = options.getDefaults();
            this.memoryCacheLazy = options.getMemoryCacheLazy();
            this.diskCacheLazy = options.getDiskCacheLazy();
            this.eventListenerFactory = options.getEventListenerFactory();
            this.componentRegistry = options.getComponentRegistry();
            this.logger = options.getLogger();
            this.extras = options.getDefaults().getExtras().newBuilder();
        }

        public final /* synthetic */ Builder components(Function1<? super ComponentRegistry.Builder, Unit> builder) {
            ComponentRegistry.Builder builder2 = new ComponentRegistry.Builder();
            builder.invoke(builder2);
            return components(builder2.build());
        }

        public final Builder components(ComponentRegistry components) {
            this.componentRegistry = components;
            return this;
        }

        public final Builder memoryCache(MemoryCache memoryCache) {
            this.memoryCacheLazy = LazyKt.lazyOf(memoryCache);
            return this;
        }

        public final Builder memoryCache(Function0<? extends MemoryCache> initializer) {
            this.memoryCacheLazy = LazyKt.lazy(initializer);
            return this;
        }

        public final Builder diskCache(DiskCache diskCache) {
            this.diskCacheLazy = LazyKt.lazyOf(diskCache);
            return this;
        }

        public final Builder diskCache(Function0<? extends DiskCache> initializer) {
            this.diskCacheLazy = LazyKt.lazy(initializer);
            return this;
        }

        public final Builder fileSystem(FileSystem fileSystem) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, fileSystem, null, null, null, null, null, null, null, null, null, null, null, null, null, 16382, null);
            return this;
        }

        public final Builder eventListener(final EventListener listener) {
            return eventListenerFactory(new EventListener.Factory() { // from class: coil3.ImageLoader$Builder$$ExternalSyntheticLambda5
                @Override // coil3.EventListener.Factory
                public final EventListener create(ImageRequest imageRequest) {
                    EventListener eventListener$lambda$6;
                    eventListener$lambda$6 = ImageLoader.Builder.eventListener$lambda$6(EventListener.this, imageRequest);
                    return eventListener$lambda$6;
                }
            });
        }

        public final Builder eventListenerFactory(EventListener.Factory factory) {
            this.eventListenerFactory = factory;
            return this;
        }

        public final Builder precision(Precision precision) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, null, null, null, null, null, null, null, null, null, null, null, precision, null, 12287, null);
            return this;
        }

        public final Builder coroutineContext(CoroutineContext context) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, context, context, context, null, null, null, null, null, null, null, null, null, null, 16369, null);
            return this;
        }

        public final Builder interceptorCoroutineContext(CoroutineContext context) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, context, null, null, null, null, null, null, null, null, null, null, null, null, 16381, null);
            return this;
        }

        public final Builder fetcherCoroutineContext(CoroutineContext context) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, null, context, null, null, null, null, null, null, null, null, null, null, null, 16379, null);
            return this;
        }

        public final Builder decoderCoroutineContext(CoroutineContext context) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, null, null, context, null, null, null, null, null, null, null, null, null, null, 16375, null);
            return this;
        }

        public final Builder placeholder(final Image image) {
            return placeholder(new Function1() { // from class: coil3.ImageLoader$Builder$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Image placeholder$lambda$13;
                    placeholder$lambda$13 = ImageLoader.Builder.placeholder$lambda$13(Image.this, (ImageRequest) obj);
                    return placeholder$lambda$13;
                }
            });
        }

        public final Builder placeholder(Function1<? super ImageRequest, ? extends Image> factory) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, null, null, null, null, null, null, factory, null, null, null, null, null, null, 16255, null);
            return this;
        }

        public final Builder error(final Image image) {
            return error(new Function1() { // from class: coil3.ImageLoader$Builder$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Image error$lambda$15;
                    error$lambda$15 = ImageLoader.Builder.error$lambda$15(Image.this, (ImageRequest) obj);
                    return error$lambda$15;
                }
            });
        }

        public final Builder error(Function1<? super ImageRequest, ? extends Image> factory) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, null, null, null, null, null, null, null, factory, null, null, null, null, null, 16127, null);
            return this;
        }

        public final Builder fallback(final Image image) {
            return fallback(new Function1() { // from class: coil3.ImageLoader$Builder$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Image fallback$lambda$17;
                    fallback$lambda$17 = ImageLoader.Builder.fallback$lambda$17(Image.this, (ImageRequest) obj);
                    return fallback$lambda$17;
                }
            });
        }

        public final Builder fallback(Function1<? super ImageRequest, ? extends Image> factory) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, null, null, null, null, null, null, null, null, factory, null, null, null, null, 15871, null);
            return this;
        }

        public final Builder memoryCachePolicy(CachePolicy policy) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, null, null, null, policy, null, null, null, null, null, null, null, null, null, 16367, null);
            return this;
        }

        public final Builder diskCachePolicy(CachePolicy policy) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, null, null, null, null, policy, null, null, null, null, null, null, null, null, 16351, null);
            return this;
        }

        public final Builder networkCachePolicy(CachePolicy policy) {
            this.defaults = ImageRequest.Defaults.copy$default(this.defaults, null, null, null, null, null, null, policy, null, null, null, null, null, null, null, 16319, null);
            return this;
        }

        public final Builder logger(Logger logger) {
            this.logger = logger;
            return this;
        }

        public final ImageLoader build() {
            Context context = this.application;
            ImageRequest.Defaults copy$default = ImageRequest.Defaults.copy$default(this.defaults, null, null, null, null, null, null, null, null, null, null, null, null, null, this.extras.build(), 8191, null);
            Lazy<? extends MemoryCache> lazy = this.memoryCacheLazy;
            if (lazy == null) {
                lazy = LazyKt.lazy(new Function0() { // from class: coil3.ImageLoader$Builder$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        MemoryCache build$lambda$23;
                        build$lambda$23 = ImageLoader.Builder.build$lambda$23(ImageLoader.Builder.this);
                        return build$lambda$23;
                    }
                });
            }
            Lazy<? extends DiskCache> lazy2 = this.diskCacheLazy;
            if (lazy2 == null) {
                lazy2 = LazyKt.lazy(new Function0() { // from class: coil3.ImageLoader$Builder$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        DiskCache build$lambda$24;
                        build$lambda$24 = ImageLoader.Builder.build$lambda$24();
                        return build$lambda$24;
                    }
                });
            }
            EventListener.Factory factory = this.eventListenerFactory;
            if (factory == null) {
                factory = EventListener.Factory.NONE;
            }
            ComponentRegistry componentRegistry = this.componentRegistry;
            if (componentRegistry == null) {
                componentRegistry = new ComponentRegistry();
            }
            return new RealImageLoader(new RealImageLoader.Options(context, copy$default, lazy, lazy2, factory, componentRegistry, this.logger));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final MemoryCache build$lambda$23(Builder builder) {
            return MemoryCache.Builder.maxSizePercent$default(new MemoryCache.Builder(), builder.application, 0.0d, 2, null).build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final DiskCache build$lambda$24() {
            return UtilsKt.singletonDiskCache();
        }
    }
}
