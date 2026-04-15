package expo.modules.kotlin.devtools;

import androidx.core.app.NotificationCompat;
import expo.modules.kotlin.devtools.cdp.Event;
import expo.modules.kotlin.devtools.cdp.ExpoReceivedResponseBodyParams;
import expo.modules.kotlin.devtools.cdp.LoadingFinishedParams;
import expo.modules.kotlin.devtools.cdp.RequestWillBeSentExtraInfoParams;
import expo.modules.kotlin.devtools.cdp.RequestWillBeSentParams;
import expo.modules.kotlin.devtools.cdp.ResponseReceivedParams;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: ExpoRequestCdpInterceptor.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\"\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J*\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoRequestCdpInterceptor;", "Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpInterceptorsDelegate;", "<init>", "()V", "delegate", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/devtools/ExpoRequestCdpInterceptor$Delegate;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope$expo_modules_core_release", "()Lkotlinx/coroutines/CoroutineScope;", "setCoroutineScope$expo_modules_core_release", "(Lkotlinx/coroutines/CoroutineScope;)V", "setDelegate", "", "dispatchEvent", NotificationCompat.CATEGORY_EVENT, "Lexpo/modules/kotlin/devtools/cdp/Event;", "willSendRequest", "requestId", "", "request", "Lokhttp3/Request;", "redirectResponse", "Lokhttp3/Response;", "didReceiveResponse", "response", "body", "Lokhttp3/ResponseBody;", "Delegate", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExpoRequestCdpInterceptor implements ExpoNetworkInspectOkHttpInterceptorsDelegate {
    public static final ExpoRequestCdpInterceptor INSTANCE = new ExpoRequestCdpInterceptor();
    private static WeakReference<Delegate> delegate = new WeakReference<>(null);
    private static CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());

    /* compiled from: ExpoRequestCdpInterceptor.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoRequestCdpInterceptor$Delegate;", "", "dispatch", "", NotificationCompat.CATEGORY_EVENT, "", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Delegate {
        void dispatch(String event);
    }

    private ExpoRequestCdpInterceptor() {
    }

    public final CoroutineScope getCoroutineScope$expo_modules_core_release() {
        return coroutineScope;
    }

    public final void setCoroutineScope$expo_modules_core_release(CoroutineScope coroutineScope2) {
        Intrinsics.checkNotNullParameter(coroutineScope2, "<set-?>");
        coroutineScope = coroutineScope2;
    }

    public final void setDelegate(Delegate delegate2) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ExpoRequestCdpInterceptor$setDelegate$1(delegate2, null), 3, null);
    }

    private final void dispatchEvent(Event event) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ExpoRequestCdpInterceptor$dispatchEvent$1(event, null), 3, null);
    }

    @Override // expo.modules.kotlin.devtools.ExpoNetworkInspectOkHttpInterceptorsDelegate
    public void willSendRequest(String requestId, Request request, Response redirectResponse) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(request, "request");
        BigDecimal scale = new BigDecimal(System.currentTimeMillis() / 1000.0d).setScale(3, RoundingMode.CEILING);
        Intrinsics.checkNotNull(scale);
        dispatchEvent(new Event("Network.requestWillBeSent", new RequestWillBeSentParams(scale, requestId, request, redirectResponse)));
        dispatchEvent(new Event("Network.requestWillBeSentExtraInfo", new RequestWillBeSentExtraInfoParams(scale, requestId, request)));
    }

    @Override // expo.modules.kotlin.devtools.ExpoNetworkInspectOkHttpInterceptorsDelegate
    public void didReceiveResponse(String requestId, Request request, Response response, ResponseBody body) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(response, "response");
        BigDecimal scale = new BigDecimal(System.currentTimeMillis() / 1000.0d).setScale(3, RoundingMode.CEILING);
        Intrinsics.checkNotNull(scale);
        dispatchEvent(new Event("Network.responseReceived", new ResponseReceivedParams(scale, requestId, response)));
        if (body != null) {
            dispatchEvent(new Event("Expo(Network.receivedResponseBody)", new ExpoReceivedResponseBodyParams(requestId, body)));
        }
        dispatchEvent(new Event("Network.loadingFinished", new LoadingFinishedParams(scale, requestId, response)));
    }
}
