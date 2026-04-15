package expo.modules.kotlin.devtools.cdp;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CdpNetworkTypes.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bj\u0002`\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eB\u0011\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\r\u0010\u0011J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u0019\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bj\u0002`\tHÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\fHÆ\u0003JU\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bj\u0002`\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020\u0005HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bj\u0002`\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006+"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/Response;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", ImagesContract.URL, "", "status", "", "statusText", "headers", "", "Lexpo/modules/kotlin/devtools/cdp/Headers;", "mimeType", "encodedDataLength", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Ljava/lang/String;J)V", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "getUrl", "()Ljava/lang/String;", "getStatus", "()I", "getStatusText", "getHeaders", "()Ljava/util/Map;", "getMimeType", "getEncodedDataLength", "()J", "toJSONObject", "Lorg/json/JSONObject;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Response implements JsonSerializable {
    private final long encodedDataLength;
    private final Map<String, String> headers;
    private final String mimeType;
    private final int status;
    private final String statusText;
    private final String url;

    public static /* synthetic */ Response copy$default(Response response, String str, int i, String str2, Map map, String str3, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = response.url;
        }
        if ((i2 & 2) != 0) {
            i = response.status;
        }
        if ((i2 & 4) != 0) {
            str2 = response.statusText;
        }
        if ((i2 & 8) != 0) {
            map = response.headers;
        }
        if ((i2 & 16) != 0) {
            str3 = response.mimeType;
        }
        if ((i2 & 32) != 0) {
            j = response.encodedDataLength;
        }
        long j2 = j;
        String str4 = str3;
        String str5 = str2;
        return response.copy(str, i, str5, map, str4, j2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component2, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* renamed from: component3, reason: from getter */
    public final String getStatusText() {
        return this.statusText;
    }

    public final Map<String, String> component4() {
        return this.headers;
    }

    /* renamed from: component5, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    /* renamed from: component6, reason: from getter */
    public final long getEncodedDataLength() {
        return this.encodedDataLength;
    }

    public final Response copy(String url, int status, String statusText, Map<String, String> headers, String mimeType, long encodedDataLength) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(statusText, "statusText");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        return new Response(url, status, statusText, headers, mimeType, encodedDataLength);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Response)) {
            return false;
        }
        Response response = (Response) other;
        return Intrinsics.areEqual(this.url, response.url) && this.status == response.status && Intrinsics.areEqual(this.statusText, response.statusText) && Intrinsics.areEqual(this.headers, response.headers) && Intrinsics.areEqual(this.mimeType, response.mimeType) && this.encodedDataLength == response.encodedDataLength;
    }

    public int hashCode() {
        return (((((((((this.url.hashCode() * 31) + Integer.hashCode(this.status)) * 31) + this.statusText.hashCode()) * 31) + this.headers.hashCode()) * 31) + this.mimeType.hashCode()) * 31) + Long.hashCode(this.encodedDataLength);
    }

    public String toString() {
        return "Response(url=" + this.url + ", status=" + this.status + ", statusText=" + this.statusText + ", headers=" + this.headers + ", mimeType=" + this.mimeType + ", encodedDataLength=" + this.encodedDataLength + ")";
    }

    public Response(String url, int i, String statusText, Map<String, String> headers, String mimeType, long j) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(statusText, "statusText");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        this.url = url;
        this.status = i;
        this.statusText = statusText;
        this.headers = headers;
        this.mimeType = mimeType;
        this.encodedDataLength = j;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getStatusText() {
        return this.statusText;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final long getEncodedDataLength() {
        return this.encodedDataLength;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Response(okhttp3.Response r10) {
        /*
            r9 = this;
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            okhttp3.Request r0 = r10.request()
            okhttp3.HttpUrl r0 = r0.url()
            java.lang.String r2 = r0.getUrl()
            int r3 = r10.code()
            java.lang.String r4 = r10.message()
            okhttp3.Headers r0 = r10.headers()
            java.util.Map r5 = expo.modules.kotlin.devtools.OkHttpExtensionsKt.toSingleMap(r0)
            java.lang.String r0 = "Content-Type"
            java.lang.String r1 = ""
            java.lang.String r0 = r10.header(r0, r1)
            if (r0 != 0) goto L2d
            r6 = r1
            goto L2e
        L2d:
            r6 = r0
        L2e:
            okhttp3.ResponseBody r10 = r10.body()
            if (r10 == 0) goto L39
            long r0 = r10.getContentLength()
            goto L3b
        L39:
            r0 = 0
        L3b:
            r7 = r0
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.Response.<init>(okhttp3.Response):void");
    }

    @Override // expo.modules.kotlin.devtools.cdp.JsonSerializable
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ImagesContract.URL, this.url);
        jSONObject.put("status", this.status);
        jSONObject.put("statusText", this.statusText);
        jSONObject.put("headers", new JSONObject(this.headers));
        jSONObject.put("mimeType", this.mimeType);
        jSONObject.put("encodedDataLength", this.encodedDataLength);
        return jSONObject;
    }
}
