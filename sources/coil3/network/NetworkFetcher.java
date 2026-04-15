package coil3.network;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import coil3.ImageLoader;
import coil3.Uri;
import coil3.decode.ImageSource;
import coil3.decode.ImageSourceKt;
import coil3.disk.DiskCache;
import coil3.fetch.Fetcher;
import coil3.network.NetworkHeaders;
import coil3.network.internal.SingleParameterLazy;
import coil3.network.internal.SingleParameterLazyKt;
import coil3.network.internal.UtilsKt;
import coil3.network.internal.Utils_androidKt;
import coil3.request.Options;
import coil3.util.MimeTypeMap;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;

/* compiled from: NetworkFetcher.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u00016BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u0012H\u0096@¢\u0006\u0002\u0010\u0013J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J4\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0019H\u0082@¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001bH\u0002J@\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010 2\u0006\u0010!\u001a\u00020\u001b2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0$\u0012\u0006\u0012\u0004\u0018\u00010%0#H\u0082@¢\u0006\u0002\u0010&J\u001c\u0010'\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0007J\u000e\u0010)\u001a\u0004\u0018\u00010\u0019*\u00020\u0015H\u0002J\f\u0010*\u001a\u00020+*\u00020\u0015H\u0002J\u0012\u0010*\u001a\u00020+*\u00020,H\u0082@¢\u0006\u0002\u0010-J\f\u0010*\u001a\u00020+*\u00020.H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u0002038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b4\u00105¨\u00067"}, d2 = {"Lcoil3/network/NetworkFetcher;", "Lcoil3/fetch/Fetcher;", ImagesContract.URL, "", "options", "Lcoil3/request/Options;", "networkClient", "Lkotlin/Lazy;", "Lcoil3/network/NetworkClient;", "diskCache", "Lcoil3/disk/DiskCache;", "cacheStrategy", "Lcoil3/network/CacheStrategy;", "connectivityChecker", "Lcoil3/network/ConnectivityChecker;", "<init>", "(Ljava/lang/String;Lcoil3/request/Options;Lkotlin/Lazy;Lkotlin/Lazy;Lkotlin/Lazy;Lcoil3/network/ConnectivityChecker;)V", "fetch", "Lcoil3/fetch/FetchResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFromDiskCache", "Lcoil3/disk/DiskCache$Snapshot;", "writeToDiskCache", "snapshot", "cacheResponse", "Lcoil3/network/NetworkResponse;", "networkRequest", "Lcoil3/network/NetworkRequest;", "networkResponse", "(Lcoil3/disk/DiskCache$Snapshot;Lcoil3/network/NetworkResponse;Lcoil3/network/NetworkRequest;Lcoil3/network/NetworkResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newRequest", "executeNetworkRequest", ExifInterface.GPS_DIRECTION_TRUE, "request", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lcoil3/network/NetworkRequest;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMimeType", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY, "toNetworkResponseOrNull", "toImageSource", "Lcoil3/decode/ImageSource;", "Lcoil3/network/NetworkResponseBody;", "(Lcoil3/network/NetworkResponseBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lokio/Buffer;", "diskCacheKey", "getDiskCacheKey", "()Ljava/lang/String;", "fileSystem", "Lokio/FileSystem;", "getFileSystem", "()Lokio/FileSystem;", "Factory", "coil-network-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkFetcher implements Fetcher {
    private final Lazy<CacheStrategy> cacheStrategy;
    private final ConnectivityChecker connectivityChecker;
    private final Lazy<DiskCache> diskCache;
    private final Lazy<NetworkClient> networkClient;
    private final Options options;
    private final String url;

    /* JADX WARN: Multi-variable type inference failed */
    public NetworkFetcher(String str, Options options, Lazy<? extends NetworkClient> lazy, Lazy<? extends DiskCache> lazy2, Lazy<? extends CacheStrategy> lazy3, ConnectivityChecker connectivityChecker) {
        this.url = str;
        this.options = options;
        this.networkClient = lazy;
        this.diskCache = lazy2;
        this.cacheStrategy = lazy3;
        this.connectivityChecker = connectivityChecker;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0125, code lost:
    
        if (r14 == null) goto L56;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0149 A[Catch: Exception -> 0x004f, TRY_LEAVE, TryCatch #0 {Exception -> 0x004f, blocks: (B:29:0x004a, B:30:0x0145, B:32:0x0149), top: B:28:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f4 A[Catch: Exception -> 0x0065, TRY_LEAVE, TryCatch #4 {Exception -> 0x0065, blocks: (B:42:0x0060, B:43:0x00ec, B:45:0x00f4), top: B:41:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0121 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, coil3.disk.DiskCache$Snapshot] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, coil3.network.NetworkResponse] */
    @Override // coil3.fetch.Fetcher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object fetch(kotlin.coroutines.Continuation<? super coil3.fetch.FetchResult> r14) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil3.network.NetworkFetcher.fetch(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final DiskCache.Snapshot readFromDiskCache() {
        DiskCache value;
        if (!this.options.getDiskCachePolicy().getReadEnabled() || (value = this.diskCache.getValue()) == null) {
            return null;
        }
        return value.openSnapshot(getDiskCacheKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0109, code lost:
    
        if (r0.writeTo(r14, r3, r7) == r1) goto L59;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeToDiskCache(coil3.disk.DiskCache.Snapshot r12, coil3.network.NetworkResponse r13, coil3.network.NetworkRequest r14, coil3.network.NetworkResponse r15, kotlin.coroutines.Continuation<? super coil3.disk.DiskCache.Snapshot> r16) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil3.network.NetworkFetcher.writeToDiskCache(coil3.disk.DiskCache$Snapshot, coil3.network.NetworkResponse, coil3.network.NetworkRequest, coil3.network.NetworkResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final NetworkRequest newRequest() {
        NetworkHeaders.Builder newBuilder = ImageRequestsKt.getHttpHeaders(this.options).newBuilder();
        boolean readEnabled = this.options.getDiskCachePolicy().getReadEnabled();
        boolean z = this.options.getNetworkCachePolicy().getReadEnabled() && this.connectivityChecker.isOnline();
        if (!z && readEnabled) {
            newBuilder.set("Cache-Control", "only-if-cached, max-stale=2147483647");
        } else if (!z || readEnabled) {
            if (!z && !readEnabled) {
                newBuilder.set("Cache-Control", "no-cache, only-if-cached");
            }
        } else if (this.options.getDiskCachePolicy().getWriteEnabled()) {
            newBuilder.set("Cache-Control", "no-cache");
        } else {
            newBuilder.set("Cache-Control", "no-cache, no-store");
        }
        return new NetworkRequest(this.url, ImageRequestsKt.getHttpMethod(this.options), newBuilder.build(), ImageRequestsKt.getHttpBody(this.options));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> Object executeNetworkRequest(NetworkRequest networkRequest, Function2<? super NetworkResponse, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        if (this.options.getNetworkCachePolicy().getReadEnabled()) {
            Utils_androidKt.assertNotOnMainThread();
        }
        return this.networkClient.getValue().executeRequest(networkRequest, new NetworkFetcher$executeNetworkRequest$2(function2, null), continuation);
    }

    public final String getMimeType(String url, String contentType) {
        String mimeTypeFromUrl;
        if ((contentType == null || StringsKt.startsWith$default(contentType, UtilsKt.MIME_TYPE_TEXT_PLAIN, false, 2, (Object) null)) && (mimeTypeFromUrl = MimeTypeMap.INSTANCE.getMimeTypeFromUrl(url)) != null) {
            return mimeTypeFromUrl;
        }
        if (contentType != null) {
            return StringsKt.substringBefore$default(contentType, ';', (String) null, 2, (Object) null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NetworkResponse toNetworkResponseOrNull(DiskCache.Snapshot snapshot) {
        Throwable th;
        NetworkResponse networkResponse;
        try {
            BufferedSource buffer = Okio.buffer(getFileSystem().source(snapshot.getMetadata()));
            try {
                networkResponse = CacheNetworkResponse.INSTANCE.readFrom(buffer);
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                th = null;
            } catch (Throwable th3) {
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (Throwable th4) {
                        ExceptionsKt.addSuppressed(th3, th4);
                    }
                }
                th = th3;
                networkResponse = null;
            }
            if (th == null) {
                return networkResponse;
            }
            throw th;
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageSource toImageSource(DiskCache.Snapshot snapshot) {
        return ImageSourceKt.ImageSource$default(snapshot.getData(), getFileSystem(), getDiskCacheKey(), snapshot, null, 16, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object toImageSource(coil3.network.NetworkResponseBody r5, kotlin.coroutines.Continuation<? super coil3.decode.ImageSource> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof coil3.network.NetworkFetcher$toImageSource$1
            if (r0 == 0) goto L14
            r0 = r6
            coil3.network.NetworkFetcher$toImageSource$1 r0 = (coil3.network.NetworkFetcher$toImageSource$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            coil3.network.NetworkFetcher$toImageSource$1 r0 = new coil3.network.NetworkFetcher$toImageSource$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r5 = r0.L$1
            okio.Buffer r5 = (okio.Buffer) r5
            java.lang.Object r0 = r0.L$0
            coil3.network.NetworkFetcher r0 = (coil3.network.NetworkFetcher) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L54
        L32:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3a:
            kotlin.ResultKt.throwOnFailure(r6)
            okio.Buffer r6 = new okio.Buffer
            r6.<init>()
            r2 = r6
            okio.BufferedSink r2 = (okio.BufferedSink) r2
            r0.L$0 = r4
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r5 = r5.writeTo(r2, r0)
            if (r5 != r1) goto L52
            return r1
        L52:
            r0 = r4
            r5 = r6
        L54:
            coil3.decode.ImageSource r5 = r0.toImageSource(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: coil3.network.NetworkFetcher.toImageSource(coil3.network.NetworkResponseBody, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageSource toImageSource(Buffer buffer) {
        return ImageSourceKt.ImageSource$default(buffer, getFileSystem(), null, 4, null);
    }

    private final String getDiskCacheKey() {
        String diskCacheKey = this.options.getDiskCacheKey();
        return diskCacheKey == null ? this.url : diskCacheKey;
    }

    private final FileSystem getFileSystem() {
        FileSystem fileSystem;
        DiskCache value = this.diskCache.getValue();
        return (value == null || (fileSystem = value.getFileSystem()) == null) ? this.options.getFileSystem() : fileSystem;
    }

    /* compiled from: NetworkFetcher.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B?\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004\u0012\u0018\b\u0002\u0010\b\u001a\u0012\u0012\b\u0012\u00060\nj\u0002`\u000b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0004\b\r\u0010\u000eJ\"\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0002H\u0002R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\b\u0012\u00060\nj\u0002`\u000b\u0012\u0004\u0012\u00020\f0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcoil3/network/NetworkFetcher$Factory;", "Lcoil3/fetch/Fetcher$Factory;", "Lcoil3/Uri;", "networkClient", "Lkotlin/Function0;", "Lcoil3/network/NetworkClient;", "cacheStrategy", "Lcoil3/network/CacheStrategy;", "connectivityChecker", "Lkotlin/Function1;", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "Lcoil3/network/ConnectivityChecker;", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "networkClientLazy", "Lkotlin/Lazy;", "cacheStrategyLazy", "connectivityCheckerLazy", "Lcoil3/network/internal/SingleParameterLazy;", "create", "Lcoil3/fetch/Fetcher;", "data", "options", "Lcoil3/request/Options;", "imageLoader", "Lcoil3/ImageLoader;", "isApplicable", "", "coil-network-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Factory implements Fetcher.Factory<Uri> {
        private final Lazy<CacheStrategy> cacheStrategyLazy;
        private final SingleParameterLazy<Context, ConnectivityChecker> connectivityCheckerLazy;
        private final Lazy<NetworkClient> networkClientLazy;

        public Factory(Function0<? extends NetworkClient> function0, Function0<? extends CacheStrategy> function02, Function1<? super Context, ? extends ConnectivityChecker> function1) {
            this.networkClientLazy = LazyKt.lazy(function0);
            this.cacheStrategyLazy = LazyKt.lazy(function02);
            this.connectivityCheckerLazy = SingleParameterLazyKt.singleParameterLazy(function1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: NetworkFetcher.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* renamed from: coil3.network.NetworkFetcher$Factory$2, reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Context, ConnectivityChecker> {
            public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

            AnonymousClass2() {
                super(1, ConnectivityCheckerKt.class, "ConnectivityChecker", "ConnectivityChecker(Landroid/content/Context;)Lcoil3/network/ConnectivityChecker;", 1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ConnectivityChecker invoke(Context context) {
                return ConnectivityCheckerKt.ConnectivityChecker(context);
            }
        }

        public /* synthetic */ Factory(Function0 function0, Function0 function02, AnonymousClass2 anonymousClass2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(function0, (i & 2) != 0 ? new Function0() { // from class: coil3.network.NetworkFetcher$Factory$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    CacheStrategy cacheStrategy;
                    cacheStrategy = CacheStrategy.DEFAULT;
                    return cacheStrategy;
                }
            } : function02, (i & 4) != 0 ? AnonymousClass2.INSTANCE : anonymousClass2);
        }

        @Override // coil3.fetch.Fetcher.Factory
        public Fetcher create(Uri data, Options options, final ImageLoader imageLoader) {
            if (isApplicable(data)) {
                return new NetworkFetcher(data.getData(), options, this.networkClientLazy, LazyKt.lazy(new Function0() { // from class: coil3.network.NetworkFetcher$Factory$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        DiskCache diskCache;
                        diskCache = ImageLoader.this.getDiskCache();
                        return diskCache;
                    }
                }), this.cacheStrategyLazy, this.connectivityCheckerLazy.get(options.getContext()));
            }
            return null;
        }

        private final boolean isApplicable(Uri data) {
            return Intrinsics.areEqual(data.getScheme(), UriUtil.HTTP_SCHEME) || Intrinsics.areEqual(data.getScheme(), UriUtil.HTTPS_SCHEME);
        }
    }
}
