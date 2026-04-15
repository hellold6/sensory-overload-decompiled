package expo.modules.fetch;

import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.sharedobjects.SharedObject;
import java.net.URL;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/* compiled from: NativeRequest.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0006\u0010\u0018\u001a\u00020\u000fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lexpo/modules/fetch/NativeRequest;", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "appContext", "Lexpo/modules/kotlin/AppContext;", "response", "Lexpo/modules/fetch/NativeResponse;", "<init>", "(Lexpo/modules/kotlin/AppContext;Lexpo/modules/fetch/NativeResponse;)V", "getResponse$expo_release", "()Lexpo/modules/fetch/NativeResponse;", "requestHolder", "Lexpo/modules/fetch/RequestHolder;", "task", "Lokhttp3/Call;", "start", "", "client", "Lokhttp3/OkHttpClient;", ImagesContract.URL, "Ljava/net/URL;", "requestInit", "Lexpo/modules/fetch/NativeRequestInit;", "requestBody", "", "cancel", "expo_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NativeRequest extends SharedObject {
    private final RequestHolder requestHolder;
    private final NativeResponse response;
    private Call task;

    /* renamed from: getResponse$expo_release, reason: from getter */
    public final NativeResponse getResponse() {
        return this.response;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeRequest(AppContext appContext, NativeResponse response) {
        super(appContext);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(response, "response");
        this.response = response;
        this.requestHolder = new RequestHolder(null);
    }

    public final void start(OkHttpClient client, URL url, NativeRequestInit requestInit, byte[] requestBody) {
        RequestBody create$default;
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(requestInit, "requestInit");
        OkHttpClient.Builder newBuilder = client.newBuilder();
        if (requestInit.getCredentials() != NativeRequestCredentials.INCLUDE) {
            newBuilder.cookieJar(CookieJar.NO_COOKIES);
        }
        if (requestInit.getRedirect() != NativeRequestRedirect.FOLLOW) {
            newBuilder.followRedirects(false);
            newBuilder.followSslRedirects(false);
        }
        OkHttpClient build = newBuilder.build();
        this.response.setRedirectMode(requestInit.getRedirect());
        Headers headers = RequestUtilsKt.toHeaders(requestInit.getHeaders());
        String str = headers.get("Content-Type");
        MediaType parse = str != null ? MediaType.INSTANCE.parse(str) : null;
        if (requestBody == null || (create$default = RequestBody.Companion.create$default(RequestBody.INSTANCE, requestBody, parse, 0, 0, 6, (Object) null)) == null) {
            create$default = ArraysKt.contains(NativeRequestKt.getMETHODS_REQUIRING_BODY(), requestInit.getMethod()) ? RequestBody.Companion.create$default(RequestBody.INSTANCE, new byte[]{0}, parse, 0, 0, 6, (Object) null) : null;
        }
        Request build2 = new Request.Builder().headers(headers).method(requestInit.getMethod(), create$default).url(OkHttpFileUrlInterceptor.INSTANCE.handleFileUrl(url)).build();
        this.requestHolder.setRequest(build2);
        Call newCall = build.newCall(build2);
        this.task = newCall;
        if (newCall != null) {
            newCall.enqueue(this.response);
        }
        this.response.onStarted();
    }

    public final void cancel() {
        Call call = this.task;
        if (call == null) {
            return;
        }
        call.cancel();
        this.response.emitRequestCanceled();
    }
}
