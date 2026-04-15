package expo.modules.kotlin.devtools.cdp;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CdpNetworkTypes.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006j\u0002`\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\t\u0010\rJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006j\u0002`\u0007HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003JC\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006j\u0002`\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006\""}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/Request;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", ImagesContract.URL, "", "method", "headers", "", "Lexpo/modules/kotlin/devtools/cdp/Headers;", "postData", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V", "request", "Lokhttp3/Request;", "(Lokhttp3/Request;)V", "getUrl", "()Ljava/lang/String;", "getMethod", "getHeaders", "()Ljava/util/Map;", "getPostData", "toJSONObject", "Lorg/json/JSONObject;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Request implements JsonSerializable {
    private final Map<String, String> headers;
    private final String method;
    private final String postData;
    private final String url;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Request copy$default(Request request, String str, String str2, Map map, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = request.url;
        }
        if ((i & 2) != 0) {
            str2 = request.method;
        }
        if ((i & 4) != 0) {
            map = request.headers;
        }
        if ((i & 8) != 0) {
            str3 = request.postData;
        }
        return request.copy(str, str2, map, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMethod() {
        return this.method;
    }

    public final Map<String, String> component3() {
        return this.headers;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPostData() {
        return this.postData;
    }

    public final Request copy(String url, String method, Map<String, String> headers, String postData) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(headers, "headers");
        return new Request(url, method, headers, postData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Request)) {
            return false;
        }
        Request request = (Request) other;
        return Intrinsics.areEqual(this.url, request.url) && Intrinsics.areEqual(this.method, request.method) && Intrinsics.areEqual(this.headers, request.headers) && Intrinsics.areEqual(this.postData, request.postData);
    }

    public int hashCode() {
        int hashCode = ((((this.url.hashCode() * 31) + this.method.hashCode()) * 31) + this.headers.hashCode()) * 31;
        String str = this.postData;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "Request(url=" + this.url + ", method=" + this.method + ", headers=" + this.headers + ", postData=" + this.postData + ")";
    }

    public Request(String url, String method, Map<String, String> headers, String str) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.postData = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getMethod() {
        return this.method;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final String getPostData() {
        return this.postData;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Request(okhttp3.Request r9) {
        /*
            r8 = this;
            java.lang.String r0 = "request"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            okhttp3.HttpUrl r0 = r9.url()
            java.lang.String r0 = r0.getUrl()
            java.lang.String r1 = r9.method()
            okhttp3.Headers r2 = r9.headers()
            java.util.Map r2 = expo.modules.kotlin.devtools.OkHttpExtensionsKt.toSingleMap(r2)
            okhttp3.RequestBody r9 = r9.body()
            r3 = 0
            if (r9 == 0) goto L43
            long r4 = r9.contentLength()
            r6 = 1048576(0x100000, double:5.180654E-318)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L43
            okio.Buffer r3 = new okio.Buffer
            r3.<init>()
            r4 = r3
            okio.BufferedSink r4 = (okio.BufferedSink) r4
            r9.writeTo(r4)
            long r4 = r3.size()
            long r4 = kotlin.ranges.RangesKt.coerceAtMost(r4, r6)
            java.lang.String r9 = r3.readUtf8(r4)
            r3 = r9
        L43:
            r8.<init>(r0, r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.Request.<init>(okhttp3.Request):void");
    }

    @Override // expo.modules.kotlin.devtools.cdp.JsonSerializable
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ImagesContract.URL, this.url);
        jSONObject.put("method", this.method);
        jSONObject.put("headers", new JSONObject(this.headers));
        String str = this.postData;
        if (str != null) {
            jSONObject.put("postData", str);
        }
        return jSONObject;
    }
}
