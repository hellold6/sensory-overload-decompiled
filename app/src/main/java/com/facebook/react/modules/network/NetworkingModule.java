package com.facebook.react.modules.network;

import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeNetworkingAndroidSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.ByteString;
import okio.DeprecatedUpgrade;
import okio.GzipSource;

/* compiled from: NetworkingModule.kt */
@ReactModule(name = "Networking")
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 `2\u00020\u0001:\u0005\\]^_`B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fB#\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\u000eB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\u000fB!\b\u0016\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\u0010B\u001b\b\u0016\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000b\u0010\u0011J\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020#H\u0016J\u0015\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001dH\u0000¢\u0006\u0002\b'J\u0015\u0010(\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001bH\u0000¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001fH\u0000¢\u0006\u0002\b+J\u0015\u0010,\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001dH\u0000¢\u0006\u0002\b-J\u0015\u0010.\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001bH\u0000¢\u0006\u0002\b/J\u0015\u00100\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001fH\u0000¢\u0006\u0002\b1JT\u00102\u001a\u00020#2\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020!2\u0006\u0010=\u001a\u0002062\u0006\u0010>\u001a\u00020!H\u0016JT\u0010?\u001a\u00020#2\u0006\u00103\u001a\u00020\u00052\b\u00104\u001a\u0004\u0018\u00010\u00052\u0006\u0010@\u001a\u00020\u00182\b\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020!2\u0006\u0010A\u001a\u00020\u00182\u0006\u0010>\u001a\u00020!J\u001c\u0010B\u001a\u0004\u0018\u00010C2\b\u0010D\u001a\u0004\u0018\u00010C2\u0006\u0010@\u001a\u00020\u0018H\u0002J\u0018\u0010E\u001a\u00020#2\u0006\u0010@\u001a\u00020\u00182\u0006\u0010F\u001a\u00020GH\u0002J\u0010\u0010H\u001a\u00020#2\u0006\u0010@\u001a\u00020\u0018H\u0002J\u0010\u0010I\u001a\u00020#2\u0006\u0010@\u001a\u00020\u0018H\u0002J\b\u0010J\u001a\u00020#H\u0002J\u0010\u0010K\u001a\u00020#2\u0006\u00105\u001a\u000206H\u0016J\u0010\u0010L\u001a\u00020#2\u0006\u0010@\u001a\u00020\u0018H\u0002J\u0010\u0010M\u001a\u00020#2\u0006\u0010N\u001a\u00020OH\u0017J\u0012\u0010P\u001a\u00020#2\b\u0010Q\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010R\u001a\u00020#2\u0006\u0010S\u001a\u000206H\u0016J\"\u0010T\u001a\u0004\u0018\u00010U2\u0006\u0010V\u001a\u0002082\u0006\u0010W\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0018H\u0002J\u001e\u0010X\u001a\u0004\u0018\u00010Y2\b\u0010Z\u001a\u0004\u0018\u0001082\b\u0010[\u001a\u0004\u0018\u00010:H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lcom/facebook/react/modules/network/NetworkingModule;", "Lcom/facebook/fbreact/specs/NativeNetworkingAndroidSpec;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "defaultUserAgent", "", "client", "Lokhttp3/OkHttpClient;", "networkInterceptorCreators", "", "Lcom/facebook/react/modules/network/NetworkInterceptorCreator;", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/lang/String;Lokhttp3/OkHttpClient;Ljava/util/List;)V", "context", "(Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/lang/String;Lokhttp3/OkHttpClient;)V", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "(Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/util/List;)V", "(Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/lang/String;)V", "cookieHandler", "Lcom/facebook/react/modules/network/ForwardingCookieHandler;", "cookieJarContainer", "Lcom/facebook/react/modules/network/CookieJarContainer;", "requestIds", "", "", "requestBodyHandlers", "", "Lcom/facebook/react/modules/network/NetworkingModule$RequestBodyHandler;", "uriHandlers", "Lcom/facebook/react/modules/network/NetworkingModule$UriHandler;", "responseHandlers", "Lcom/facebook/react/modules/network/NetworkingModule$ResponseHandler;", "shuttingDown", "", "initialize", "", "invalidate", "addUriHandler", "handler", "addUriHandler$ReactAndroid_release", "addRequestBodyHandler", "addRequestBodyHandler$ReactAndroid_release", "addResponseHandler", "addResponseHandler$ReactAndroid_release", "removeUriHandler", "removeUriHandler$ReactAndroid_release", "removeRequestBodyHandler", "removeRequestBodyHandler$ReactAndroid_release", "removeResponseHandler", "removeResponseHandler$ReactAndroid_release", "sendRequest", "method", ImagesContract.URL, "requestIdAsDouble", "", "headers", "Lcom/facebook/react/bridge/ReadableArray;", "data", "Lcom/facebook/react/bridge/ReadableMap;", "responseType", "useIncrementalUpdates", "timeoutAsDouble", "withCredentials", "sendRequestInternal", "requestId", "timeout", "wrapRequestBodyWithProgressEmitter", "Lokhttp3/RequestBody;", "requestBody", "readWithProgress", "responseBody", "Lokhttp3/ResponseBody;", "addRequest", "removeRequest", "cancelAllRequests", "abortRequest", "cancelRequest", "clearCookies", "callback", "Lcom/facebook/react/bridge/Callback;", "addListener", "eventName", "removeListeners", NewHtcHomeBadger.COUNT, "constructMultipartBody", "Lokhttp3/MultipartBody$Builder;", "body", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY, "extractHeaders", "Lokhttp3/Headers;", "headersArray", "requestData", "UriHandler", "RequestBodyHandler", "ResponseHandler", "CustomClientBuilder", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkingModule extends NativeNetworkingAndroidSpec {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    public static final String NAME = "Networking";
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String TAG = "Networking";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    private static com.facebook.react.modules.network.CustomClientBuilder customClientBuilder;
    private final OkHttpClient client;
    private final ForwardingCookieHandler cookieHandler;
    private CookieJarContainer cookieJarContainer;
    private final String defaultUserAgent;
    private final List<RequestBodyHandler> requestBodyHandlers;
    private final Set<Integer> requestIds;
    private final List<ResponseHandler> responseHandlers;
    private boolean shuttingDown;
    private final List<UriHandler> uriHandlers;

    /* compiled from: NetworkingModule.kt */
    @Deprecated(message = "To be removed in a future release. See\n        https://github.com/facebook/react-native/pull/37798#pullrequestreview-1518338914")
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Lcom/facebook/react/modules/network/NetworkingModule$CustomClientBuilder;", "Lcom/facebook/react/modules/network/CustomClientBuilder;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface CustomClientBuilder extends com.facebook.react.modules.network.CustomClientBuilder {
    }

    /* compiled from: NetworkingModule.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/modules/network/NetworkingModule$RequestBodyHandler;", "", "supports", "", "map", "Lcom/facebook/react/bridge/ReadableMap;", "toRequestBody", "Lokhttp3/RequestBody;", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY, "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface RequestBodyHandler {
        boolean supports(ReadableMap map);

        RequestBody toRequestBody(ReadableMap map, String contentType);
    }

    /* compiled from: NetworkingModule.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/modules/network/NetworkingModule$ResponseHandler;", "", "supports", "", "responseType", "", "toResponseData", "Lcom/facebook/react/bridge/WritableMap;", "body", "Lokhttp3/ResponseBody;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface ResponseHandler {
        boolean supports(String responseType);

        WritableMap toResponseData(ResponseBody body) throws IOException;
    }

    /* compiled from: NetworkingModule.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/modules/network/NetworkingModule$UriHandler;", "", "supports", "", NetworkingModule.REQUEST_BODY_KEY_URI, "Landroid/net/Uri;", "responseType", "", "fetch", "Lcom/facebook/react/bridge/WritableMap;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface UriHandler {
        WritableMap fetch(Uri uri) throws IOException;

        boolean supports(Uri uri, String responseType);
    }

    @JvmStatic
    public static final void setCustomClientBuilder(com.facebook.react.modules.network.CustomClientBuilder customClientBuilder2) {
        INSTANCE.setCustomClientBuilder(customClientBuilder2);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void addListener(String eventName) {
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void removeListeners(double count) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkingModule(ReactApplicationContext reactContext, String str, OkHttpClient client, List<? extends NetworkInterceptorCreator> list) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(client, "client");
        this.cookieHandler = new ForwardingCookieHandler();
        this.requestIds = new HashSet();
        this.requestBodyHandlers = new ArrayList();
        this.uriHandlers = new ArrayList();
        this.responseHandlers = new ArrayList();
        if (list != null) {
            OkHttpClient.Builder newBuilder = client.newBuilder();
            Iterator<? extends NetworkInterceptorCreator> it = list.iterator();
            while (it.hasNext()) {
                newBuilder.addNetworkInterceptor(it.next().create());
            }
            client = newBuilder.build();
        }
        this.client = client;
        CookieJar cookieJar = client.getCookieJar();
        this.cookieJarContainer = cookieJar instanceof CookieJarContainer ? (CookieJarContainer) cookieJar : null;
        this.defaultUserAgent = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NetworkingModule(ReactApplicationContext context, String str, OkHttpClient client) {
        this(context, str, client, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(client, "client");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NetworkingModule(com.facebook.react.bridge.ReactApplicationContext r3) {
        /*
            r2 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            android.content.Context r0 = r3.getApplicationContext()
            java.lang.String r1 = "getApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            okhttp3.OkHttpClient r0 = com.facebook.react.modules.network.OkHttpClientProvider.createClient(r0)
            r1 = 0
            r2.<init>(r3, r1, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.network.NetworkingModule.<init>(com.facebook.react.bridge.ReactApplicationContext):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NetworkingModule(com.facebook.react.bridge.ReactApplicationContext r3, java.util.List<? extends com.facebook.react.modules.network.NetworkInterceptorCreator> r4) {
        /*
            r2 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            android.content.Context r0 = r3.getApplicationContext()
            java.lang.String r1 = "getApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            okhttp3.OkHttpClient r0 = com.facebook.react.modules.network.OkHttpClientProvider.createClient(r0)
            r1 = 0
            r2.<init>(r3, r1, r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.network.NetworkingModule.<init>(com.facebook.react.bridge.ReactApplicationContext, java.util.List):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NetworkingModule(com.facebook.react.bridge.ReactApplicationContext r3, java.lang.String r4) {
        /*
            r2 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            android.content.Context r0 = r3.getApplicationContext()
            java.lang.String r1 = "getApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            okhttp3.OkHttpClient r0 = com.facebook.react.modules.network.OkHttpClientProvider.createClient(r0)
            r1 = 0
            r2.<init>(r3, r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.network.NetworkingModule.<init>(com.facebook.react.bridge.ReactApplicationContext, java.lang.String):void");
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        CookieJarContainer cookieJarContainer = this.cookieJarContainer;
        if (cookieJarContainer != null) {
            cookieJarContainer.setCookieJar(new JavaNetCookieJar(this.cookieHandler));
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        this.shuttingDown = true;
        cancelAllRequests();
        this.cookieHandler.destroy();
        CookieJarContainer cookieJarContainer = this.cookieJarContainer;
        if (cookieJarContainer != null) {
            cookieJarContainer.removeCookieJar();
        }
        this.requestBodyHandlers.clear();
        this.responseHandlers.clear();
        this.uriHandlers.clear();
    }

    public final void addUriHandler$ReactAndroid_release(UriHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.uriHandlers.add(handler);
    }

    public final void addRequestBodyHandler$ReactAndroid_release(RequestBodyHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.requestBodyHandlers.add(handler);
    }

    public final void addResponseHandler$ReactAndroid_release(ResponseHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.responseHandlers.add(handler);
    }

    public final void removeUriHandler$ReactAndroid_release(UriHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.uriHandlers.remove(handler);
    }

    public final void removeRequestBodyHandler$ReactAndroid_release(RequestBodyHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.requestBodyHandlers.remove(handler);
    }

    public final void removeResponseHandler$ReactAndroid_release(ResponseHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.responseHandlers.remove(handler);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void sendRequest(String method, String url, double requestIdAsDouble, ReadableArray headers, ReadableMap data, String responseType, boolean useIncrementalUpdates, double timeoutAsDouble, boolean withCredentials) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(responseType, "responseType");
        int i = (int) requestIdAsDouble;
        try {
            sendRequestInternal(method, url, i, headers, data, responseType, useIncrementalUpdates, (int) timeoutAsDouble, withCredentials);
        } catch (Throwable th) {
            FLog.e("Networking", "Failed to send url request: " + url, th);
            ResponseUtil.onRequestError(getReactApplicationContextIfActiveOrWarn(), i, th.getMessage(), th);
        }
    }

    public final void sendRequestInternal(String method, String url, final int requestId, ReadableArray headers, ReadableMap data, final String responseType, final boolean useIncrementalUpdates, int timeout, boolean withCredentials) {
        RequestBodyHandler requestBodyHandler;
        MultipartBody emptyBody;
        Charset charset;
        String str = url;
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(responseType, "responseType");
        final ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        try {
            Uri parse = Uri.parse(str);
            for (UriHandler uriHandler : this.uriHandlers) {
                Intrinsics.checkNotNull(parse);
                if (uriHandler.supports(parse, responseType)) {
                    WritableMap fetch = uriHandler.fetch(parse);
                    ResponseUtil.onResponseReceived(reactApplicationContextIfActiveOrWarn, requestId, 200, Arguments.createMap(), str);
                    ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, requestId, fetch);
                    ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, requestId);
                    return;
                }
            }
            try {
                Request.Builder builder = new Request.Builder();
                if (str == null) {
                    str = "";
                }
                Request.Builder url2 = builder.url(str);
                if (requestId != 0) {
                    url2.tag(Integer.valueOf(requestId));
                }
                OkHttpClient.Builder newBuilder = this.client.newBuilder();
                INSTANCE.applyCustomBuilder(newBuilder);
                if (!withCredentials) {
                    newBuilder.cookieJar(CookieJar.NO_COOKIES);
                }
                if (useIncrementalUpdates) {
                    newBuilder.addNetworkInterceptor(new Interceptor() { // from class: com.facebook.react.modules.network.NetworkingModule$sendRequestInternal$$inlined$-addNetworkInterceptor$1
                        @Override // okhttp3.Interceptor
                        public final Response intercept(Interceptor.Chain chain) {
                            Intrinsics.checkNotNullParameter(chain, "chain");
                            Response proceed = chain.proceed(chain.request());
                            ResponseBody body = proceed.getBody();
                            if (body == null) {
                                throw new IllegalStateException("Required value was null.".toString());
                            }
                            final String str2 = responseType;
                            final ReactApplicationContext reactApplicationContext = reactApplicationContextIfActiveOrWarn;
                            final int i = requestId;
                            return proceed.newBuilder().body(new ProgressResponseBody(body, new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule$sendRequestInternal$1$responseBody$1
                                private long last = System.nanoTime();

                                public final long getLast() {
                                    return this.last;
                                }

                                public final void setLast(long j) {
                                    this.last = j;
                                }

                                @Override // com.facebook.react.modules.network.ProgressListener
                                public void onProgress(long bytesWritten, long contentLength, boolean done) {
                                    boolean shouldDispatch;
                                    long nanoTime = System.nanoTime();
                                    if (!done) {
                                        shouldDispatch = NetworkingModule.INSTANCE.shouldDispatch(nanoTime, this.last);
                                        if (!shouldDispatch) {
                                            return;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str2, "text")) {
                                        return;
                                    }
                                    ResponseUtil.onDataReceivedProgress(reactApplicationContext, i, bytesWritten, contentLength);
                                    this.last = nanoTime;
                                }
                            })).build();
                        }
                    });
                }
                if (timeout != this.client.getCallTimeoutMillis()) {
                    newBuilder.callTimeout(timeout, TimeUnit.MILLISECONDS);
                }
                OkHttpClient build = newBuilder.build();
                Headers extractHeaders = extractHeaders(headers, data);
                if (extractHeaders == null) {
                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Unrecognized headers format", null);
                    return;
                }
                String str2 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
                String str3 = extractHeaders.get(CONTENT_ENCODING_HEADER_NAME);
                url2.headers(extractHeaders);
                if (data != null) {
                    Iterator<RequestBodyHandler> it = this.requestBodyHandlers.iterator();
                    while (it.hasNext()) {
                        requestBodyHandler = it.next();
                        if (requestBodyHandler.supports(data)) {
                            break;
                        }
                    }
                }
                requestBodyHandler = null;
                if (data != null) {
                    String lowerCase = method.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    if (!Intrinsics.areEqual(lowerCase, "get")) {
                        String lowerCase2 = method.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                        if (!Intrinsics.areEqual(lowerCase2, TtmlNode.TAG_HEAD)) {
                            if (requestBodyHandler != null) {
                                emptyBody = requestBodyHandler.toRequestBody(data, str2);
                            } else if (data.hasKey(REQUEST_BODY_KEY_STRING)) {
                                if (str2 == null) {
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Payload is set but no content-type header specified", null);
                                    return;
                                }
                                String string = data.getString(REQUEST_BODY_KEY_STRING);
                                MediaType m3087deprecated_parse = MediaType.INSTANCE.m3087deprecated_parse(str2);
                                if (RequestBodyUtil.isGzipEncoding(str3)) {
                                    emptyBody = (m3087deprecated_parse == null || string == null) ? null : RequestBodyUtil.createGzip(m3087deprecated_parse, string);
                                    if (emptyBody == null) {
                                        ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Failed to gzip request body", null);
                                        return;
                                    }
                                } else {
                                    if (m3087deprecated_parse == null) {
                                        charset = StandardCharsets.UTF_8;
                                    } else {
                                        charset = m3087deprecated_parse.charset(StandardCharsets.UTF_8);
                                        if (charset == null) {
                                            throw new IllegalStateException("Required value was null.".toString());
                                        }
                                    }
                                    if (string == null) {
                                        ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Received request but body was empty", null);
                                        return;
                                    }
                                    RequestBody.Companion companion = RequestBody.INSTANCE;
                                    Intrinsics.checkNotNull(charset);
                                    byte[] bytes = string.getBytes(charset);
                                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                                    emptyBody = RequestBody.Companion.create$default(companion, m3087deprecated_parse, bytes, 0, 0, 12, (Object) null);
                                }
                            } else if (data.hasKey(REQUEST_BODY_KEY_BASE64)) {
                                if (str2 == null) {
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Payload is set but no content-type header specified", null);
                                    return;
                                }
                                String string2 = data.getString(REQUEST_BODY_KEY_BASE64);
                                if (string2 == null) {
                                    throw new IllegalStateException("Required value was null.".toString());
                                }
                                MediaType m3087deprecated_parse2 = MediaType.INSTANCE.m3087deprecated_parse(str2);
                                if (m3087deprecated_parse2 == null) {
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Invalid content type specified: " + str2, null);
                                    return;
                                }
                                ByteString m3149deprecated_decodeBase64 = ByteString.INSTANCE.m3149deprecated_decodeBase64(string2);
                                if (m3149deprecated_decodeBase64 == null) {
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Request body base64 string was invalid", null);
                                    return;
                                }
                                emptyBody = RequestBody.INSTANCE.create(m3087deprecated_parse2, m3149deprecated_decodeBase64);
                            } else if (data.hasKey(REQUEST_BODY_KEY_URI)) {
                                if (str2 == null) {
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Payload is set but no content-type header specified", null);
                                    return;
                                }
                                String string3 = data.getString(REQUEST_BODY_KEY_URI);
                                if (string3 == null) {
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Request body URI field was set but null", null);
                                    return;
                                }
                                ReactApplicationContext reactApplicationContext = getReactApplicationContext();
                                Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
                                InputStream fileInputStream = RequestBodyUtil.getFileInputStream(reactApplicationContext, string3);
                                if (fileInputStream == null) {
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Could not retrieve file for uri " + string3, null);
                                    return;
                                }
                                emptyBody = RequestBodyUtil.create(MediaType.INSTANCE.m3087deprecated_parse(str2), fileInputStream);
                            } else if (data.hasKey(REQUEST_BODY_KEY_FORMDATA)) {
                                if (str2 == null) {
                                    str2 = ShareTarget.ENCODING_TYPE_MULTIPART;
                                }
                                ReadableArray array = data.getArray(REQUEST_BODY_KEY_FORMDATA);
                                if (array == null) {
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Received request but form data was empty", null);
                                    return;
                                }
                                MultipartBody.Builder constructMultipartBody = constructMultipartBody(array, str2, requestId);
                                if (constructMultipartBody == null) {
                                    return;
                                } else {
                                    emptyBody = constructMultipartBody.build();
                                }
                            } else {
                                emptyBody = RequestBodyUtil.getEmptyBody(method);
                            }
                            url2.method(method, wrapRequestBodyWithProgressEmitter(emptyBody, requestId));
                            addRequest(requestId);
                            build.newCall(url2.build()).enqueue(new Callback() { // from class: com.facebook.react.modules.network.NetworkingModule$sendRequestInternal$2
                                @Override // okhttp3.Callback
                                public void onFailure(Call call, IOException e) {
                                    boolean z;
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                    z = NetworkingModule.this.shuttingDown;
                                    if (z) {
                                        return;
                                    }
                                    NetworkingModule.this.removeRequest(requestId);
                                    String message = e.getMessage();
                                    if (message == null) {
                                        message = "Error while executing request: " + e.getClass().getSimpleName();
                                    }
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, message, e);
                                }

                                @Override // okhttp3.Callback
                                public void onResponse(Call call, Response response) throws IOException {
                                    boolean z;
                                    WritableMap translateHeaders;
                                    List<NetworkingModule.ResponseHandler> list;
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    z = NetworkingModule.this.shuttingDown;
                                    if (z) {
                                        return;
                                    }
                                    NetworkingModule.this.removeRequest(requestId);
                                    ReactApplicationContext reactApplicationContext2 = reactApplicationContextIfActiveOrWarn;
                                    int i = requestId;
                                    int code = response.getCode();
                                    translateHeaders = NetworkingModule.INSTANCE.translateHeaders(response.getHeaders());
                                    ResponseUtil.onResponseReceived(reactApplicationContext2, i, code, translateHeaders, response.getRequest().getUrl().getUrl());
                                    try {
                                        ResponseBody body = response.getBody();
                                        if (body == null) {
                                            ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Response body is null", null);
                                            return;
                                        }
                                        if (StringsKt.equals("gzip", Response.header$default(response, HttpHeaders.CONTENT_ENCODING, null, 2, null), true)) {
                                            GzipSource gzipSource = new GzipSource(body.getSource());
                                            String header$default = Response.header$default(response, "Content-Type", null, 2, null);
                                            body = ResponseBody.INSTANCE.create(header$default != null ? MediaType.INSTANCE.m3087deprecated_parse(header$default) : null, -1L, DeprecatedUpgrade.getOkio().buffer(gzipSource));
                                        }
                                        if (body != null) {
                                            list = NetworkingModule.this.responseHandlers;
                                            for (NetworkingModule.ResponseHandler responseHandler : list) {
                                                if (responseHandler.supports(responseType)) {
                                                    ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, requestId, responseHandler.toResponseData(body));
                                                    ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, requestId);
                                                    return;
                                                }
                                            }
                                            if (useIncrementalUpdates && Intrinsics.areEqual(responseType, "text")) {
                                                NetworkingModule.this.readWithProgress(requestId, body);
                                                ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, requestId);
                                                return;
                                            }
                                            String str4 = "";
                                            if (Intrinsics.areEqual(responseType, "text")) {
                                                try {
                                                    str4 = body.string();
                                                } catch (IOException e) {
                                                    if (!StringsKt.equals(response.getRequest().getMethod(), "HEAD", true)) {
                                                        ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, e.getMessage(), e);
                                                    }
                                                }
                                            } else if (Intrinsics.areEqual(responseType, "base64")) {
                                                str4 = Base64.encodeToString(body.bytes(), 2);
                                            }
                                            ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, requestId, str4);
                                            ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, requestId);
                                            return;
                                        }
                                        throw new IllegalStateException("Required value was null.".toString());
                                    } catch (IOException e2) {
                                        ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, e2.getMessage(), e2);
                                    }
                                }
                            });
                        }
                    }
                }
                emptyBody = RequestBodyUtil.getEmptyBody(method);
                url2.method(method, wrapRequestBodyWithProgressEmitter(emptyBody, requestId));
                addRequest(requestId);
                build.newCall(url2.build()).enqueue(new Callback() { // from class: com.facebook.react.modules.network.NetworkingModule$sendRequestInternal$2
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException e) {
                        boolean z;
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(e, "e");
                        z = NetworkingModule.this.shuttingDown;
                        if (z) {
                            return;
                        }
                        NetworkingModule.this.removeRequest(requestId);
                        String message = e.getMessage();
                        if (message == null) {
                            message = "Error while executing request: " + e.getClass().getSimpleName();
                        }
                        ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, message, e);
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) throws IOException {
                        boolean z;
                        WritableMap translateHeaders;
                        List<NetworkingModule.ResponseHandler> list;
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(response, "response");
                        z = NetworkingModule.this.shuttingDown;
                        if (z) {
                            return;
                        }
                        NetworkingModule.this.removeRequest(requestId);
                        ReactApplicationContext reactApplicationContext2 = reactApplicationContextIfActiveOrWarn;
                        int i = requestId;
                        int code = response.getCode();
                        translateHeaders = NetworkingModule.INSTANCE.translateHeaders(response.getHeaders());
                        ResponseUtil.onResponseReceived(reactApplicationContext2, i, code, translateHeaders, response.getRequest().getUrl().getUrl());
                        try {
                            ResponseBody body = response.getBody();
                            if (body == null) {
                                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Response body is null", null);
                                return;
                            }
                            if (StringsKt.equals("gzip", Response.header$default(response, HttpHeaders.CONTENT_ENCODING, null, 2, null), true)) {
                                GzipSource gzipSource = new GzipSource(body.getSource());
                                String header$default = Response.header$default(response, "Content-Type", null, 2, null);
                                body = ResponseBody.INSTANCE.create(header$default != null ? MediaType.INSTANCE.m3087deprecated_parse(header$default) : null, -1L, DeprecatedUpgrade.getOkio().buffer(gzipSource));
                            }
                            if (body != null) {
                                list = NetworkingModule.this.responseHandlers;
                                for (NetworkingModule.ResponseHandler responseHandler : list) {
                                    if (responseHandler.supports(responseType)) {
                                        ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, requestId, responseHandler.toResponseData(body));
                                        ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, requestId);
                                        return;
                                    }
                                }
                                if (useIncrementalUpdates && Intrinsics.areEqual(responseType, "text")) {
                                    NetworkingModule.this.readWithProgress(requestId, body);
                                    ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, requestId);
                                    return;
                                }
                                String str4 = "";
                                if (Intrinsics.areEqual(responseType, "text")) {
                                    try {
                                        str4 = body.string();
                                    } catch (IOException e) {
                                        if (!StringsKt.equals(response.getRequest().getMethod(), "HEAD", true)) {
                                            ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, e.getMessage(), e);
                                        }
                                    }
                                } else if (Intrinsics.areEqual(responseType, "base64")) {
                                    str4 = Base64.encodeToString(body.bytes(), 2);
                                }
                                ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, requestId, str4);
                                ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, requestId);
                                return;
                            }
                            throw new IllegalStateException("Required value was null.".toString());
                        } catch (IOException e2) {
                            ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, e2.getMessage(), e2);
                        }
                    }
                });
            } catch (Exception e) {
                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, e.getMessage(), null);
            }
        } catch (IOException e2) {
            ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, e2.getMessage(), e2);
        }
    }

    private final RequestBody wrapRequestBodyWithProgressEmitter(RequestBody requestBody, final int requestId) {
        if (requestBody == null) {
            return null;
        }
        final ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        return RequestBodyUtil.createProgressRequest(requestBody, new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule$wrapRequestBodyWithProgressEmitter$1
            private long last = System.nanoTime();

            public final long getLast() {
                return this.last;
            }

            public final void setLast(long j) {
                this.last = j;
            }

            @Override // com.facebook.react.modules.network.ProgressListener
            public void onProgress(long bytesWritten, long contentLength, boolean done) {
                boolean shouldDispatch;
                long nanoTime = System.nanoTime();
                if (!done) {
                    shouldDispatch = NetworkingModule.INSTANCE.shouldDispatch(nanoTime, this.last);
                    if (!shouldDispatch) {
                        return;
                    }
                }
                ResponseUtil.onDataSend(ReactApplicationContext.this, requestId, bytesWritten, contentLength);
                this.last = nanoTime;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void readWithProgress(int requestId, ResponseBody responseBody) throws IOException {
        long j;
        Charset charset;
        long j2 = -1;
        try {
            Intrinsics.checkNotNull(responseBody, "null cannot be cast to non-null type com.facebook.react.modules.network.ProgressResponseBody");
            ProgressResponseBody progressResponseBody = (ProgressResponseBody) responseBody;
            j = progressResponseBody.getTotalBytesRead();
            try {
                j2 = progressResponseBody.getContentLength();
            } catch (ClassCastException unused) {
            }
        } catch (ClassCastException unused2) {
            j = -1;
        }
        long j3 = j2;
        long j4 = j;
        if (responseBody.get$contentType() == null) {
            charset = StandardCharsets.UTF_8;
        } else {
            MediaType mediaType = responseBody.get$contentType();
            charset = mediaType != null ? mediaType.charset(StandardCharsets.UTF_8) : null;
            if (charset == null) {
                throw new IllegalStateException(("Null character set for Content-Type: " + responseBody.get$contentType()).toString());
            }
        }
        Intrinsics.checkNotNull(charset);
        ProgressiveStringDecoder progressiveStringDecoder = new ProgressiveStringDecoder(charset);
        InputStream byteStream = responseBody.byteStream();
        try {
            byte[] bArr = new byte[8192];
            ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
            while (true) {
                int read = byteStream.read(bArr);
                if (read == -1) {
                    return;
                }
                int i = requestId;
                ResponseUtil.onIncrementalDataReceived(reactApplicationContextIfActiveOrWarn, i, progressiveStringDecoder.decodeNext(bArr, read), j4, j3);
                requestId = i;
            }
        } finally {
            byteStream.close();
        }
    }

    private final synchronized void addRequest(int requestId) {
        this.requestIds.add(Integer.valueOf(requestId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void removeRequest(int requestId) {
        this.requestIds.remove(Integer.valueOf(requestId));
    }

    private final synchronized void cancelAllRequests() {
        Iterator<Integer> it = this.requestIds.iterator();
        while (it.hasNext()) {
            cancelRequest(it.next().intValue());
        }
        this.requestIds.clear();
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void abortRequest(double requestIdAsDouble) {
        int i = (int) requestIdAsDouble;
        cancelRequest(i);
        removeRequest(i);
    }

    private final void cancelRequest(int requestId) {
        OkHttpCallUtil.cancelTag(this.client, Integer.valueOf(requestId));
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    @ReactMethod
    public void clearCookies(com.facebook.react.bridge.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.cookieHandler.clearCookies(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final MultipartBody.Builder constructMultipartBody(ReadableArray body, String contentType, int requestId) {
        MediaType mediaType;
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        MultipartBody.Builder builder = new MultipartBody.Builder(null, 1, 0 == true ? 1 : 0);
        MediaType m3087deprecated_parse = MediaType.INSTANCE.m3087deprecated_parse(contentType);
        if (m3087deprecated_parse == null) {
            ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Invalid media type.", null);
            return null;
        }
        builder.setType(m3087deprecated_parse);
        int size = body.size();
        for (int i = 0; i < size; i++) {
            ReadableMap map = body.getMap(i);
            if (map == null) {
                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Unrecognized FormData part.", null);
                return null;
            }
            Headers extractHeaders = extractHeaders(map.getArray("headers"), null);
            if (extractHeaders == null) {
                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Missing or invalid header format for FormData part.", null);
                return null;
            }
            String str = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
            if (str != null) {
                mediaType = MediaType.INSTANCE.m3087deprecated_parse(str);
                extractHeaders = extractHeaders.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            } else {
                mediaType = null;
            }
            if (map.hasKey(REQUEST_BODY_KEY_STRING) && map.getString(REQUEST_BODY_KEY_STRING) != null) {
                String string = map.getString(REQUEST_BODY_KEY_STRING);
                if (string == null) {
                    string = "";
                }
                builder.addPart(extractHeaders, RequestBody.INSTANCE.create(mediaType, string));
            } else if (map.hasKey(REQUEST_BODY_KEY_URI) && map.getString(REQUEST_BODY_KEY_URI) != null) {
                if (mediaType == null) {
                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Binary FormData part needs a content-type header.", null);
                    return null;
                }
                String string2 = map.getString(REQUEST_BODY_KEY_URI);
                if (string2 == null) {
                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Body must have a valid file uri", null);
                    return null;
                }
                ReactApplicationContext reactApplicationContext = getReactApplicationContext();
                Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
                InputStream fileInputStream = RequestBodyUtil.getFileInputStream(reactApplicationContext, string2);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Could not retrieve file for uri " + string2, null);
                    return null;
                }
                builder.addPart(extractHeaders, RequestBodyUtil.create(mediaType, fileInputStream));
            } else {
                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, requestId, "Unrecognized FormData part.", null);
                Unit unit = Unit.INSTANCE;
            }
        }
        return builder;
    }

    private final Headers extractHeaders(ReadableArray headersArray, ReadableMap requestData) {
        String str;
        if (headersArray == null) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        int size = headersArray.size();
        for (int i = 0; i < size; i++) {
            ReadableArray array = headersArray.getArray(i);
            if (array != null && array.size() == 2) {
                String string = array.getString(0);
                if (string != null) {
                    string = HeaderUtil.INSTANCE.stripHeaderName(string);
                }
                String string2 = array.getString(1);
                if (string != null && string2 != null) {
                    builder.addUnsafeNonAscii(string, string2);
                }
            }
            return null;
        }
        if (builder.get(USER_AGENT_HEADER_NAME) == null && (str = this.defaultUserAgent) != null) {
            builder.add(USER_AGENT_HEADER_NAME, str);
        }
        if (requestData == null || !requestData.hasKey(REQUEST_BODY_KEY_STRING)) {
            builder.removeAll(CONTENT_ENCODING_HEADER_NAME);
        }
        return builder.build();
    }

    /* compiled from: NetworkingModule.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0007J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/facebook/react/modules/network/NetworkingModule$Companion;", "", "<init>", "()V", "NAME", "", "TAG", "CONTENT_ENCODING_HEADER_NAME", "CONTENT_TYPE_HEADER_NAME", "REQUEST_BODY_KEY_STRING", "REQUEST_BODY_KEY_URI", "REQUEST_BODY_KEY_FORMDATA", "REQUEST_BODY_KEY_BASE64", "USER_AGENT_HEADER_NAME", "CHUNK_TIMEOUT_NS", "", "MAX_CHUNK_SIZE_BETWEEN_FLUSHES", "customClientBuilder", "Lcom/facebook/react/modules/network/CustomClientBuilder;", "setCustomClientBuilder", "", "ccb", "applyCustomBuilder", "builder", "Lokhttp3/OkHttpClient$Builder;", "shouldDispatch", "", "now", "", "last", "translateHeaders", "Lcom/facebook/react/bridge/WritableMap;", "headers", "Lokhttp3/Headers;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean shouldDispatch(long now, long last) {
            return last + ((long) NetworkingModule.CHUNK_TIMEOUT_NS) < now;
        }

        private Companion() {
        }

        @JvmStatic
        public final void setCustomClientBuilder(com.facebook.react.modules.network.CustomClientBuilder ccb) {
            NetworkingModule.customClientBuilder = ccb;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void applyCustomBuilder(OkHttpClient.Builder builder) {
            com.facebook.react.modules.network.CustomClientBuilder customClientBuilder = NetworkingModule.customClientBuilder;
            if (customClientBuilder != null) {
                customClientBuilder.apply(builder);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final WritableMap translateHeaders(Headers headers) {
            Bundle bundle = new Bundle();
            int m3058deprecated_size = headers.m3058deprecated_size();
            for (int i = 0; i < m3058deprecated_size; i++) {
                String name = headers.name(i);
                if (bundle.containsKey(name)) {
                    bundle.putString(name, bundle.getString(name) + ", " + headers.value(i));
                } else {
                    bundle.putString(name, headers.value(i));
                }
            }
            WritableMap fromBundle = Arguments.fromBundle(bundle);
            Intrinsics.checkNotNullExpressionValue(fromBundle, "fromBundle(...)");
            return fromBundle;
        }
    }
}
