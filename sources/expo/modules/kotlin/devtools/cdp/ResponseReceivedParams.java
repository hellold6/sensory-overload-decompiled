package expo.modules.kotlin.devtools.cdp;

import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CdpNetworkTypes.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010B%\b\u0016\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u000f\u0010\u0014J\b\u0010 \u001a\u00020!H\u0016J\r\u0010\"\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\r\u0010$\u001a\u00060\u0007j\u0002`\bHÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003J\t\u0010'\u001a\u00020\u000eHÆ\u0003JM\u0010(\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00032\f\b\u0002\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010)\u001a\u00020\u000e2\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0015\u0010\u0006\u001a\u00060\u0007j\u0002`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006/"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/ResponseReceivedParams;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "requestId", "", "Lexpo/modules/kotlin/devtools/cdp/RequestId;", "loaderId", "timestamp", "Ljava/math/BigDecimal;", "Lexpo/modules/kotlin/devtools/cdp/MonotonicTime;", "type", "Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "response", "Lexpo/modules/kotlin/devtools/cdp/Response;", "hasExtraInfo", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;Lexpo/modules/kotlin/devtools/cdp/ResourceType;Lexpo/modules/kotlin/devtools/cdp/Response;Z)V", "now", "okhttpResponse", "Lokhttp3/Response;", "(Ljava/math/BigDecimal;Ljava/lang/String;Lokhttp3/Response;)V", "getRequestId", "()Ljava/lang/String;", "getLoaderId", "getTimestamp", "()Ljava/math/BigDecimal;", "getType", "()Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "getResponse", "()Lexpo/modules/kotlin/devtools/cdp/Response;", "getHasExtraInfo", "()Z", "toJSONObject", "Lorg/json/JSONObject;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "", "toString", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ResponseReceivedParams implements JsonSerializable {
    private final boolean hasExtraInfo;
    private final String loaderId;
    private final String requestId;
    private final Response response;
    private final BigDecimal timestamp;
    private final ResourceType type;

    public static /* synthetic */ ResponseReceivedParams copy$default(ResponseReceivedParams responseReceivedParams, String str, String str2, BigDecimal bigDecimal, ResourceType resourceType, Response response, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = responseReceivedParams.requestId;
        }
        if ((i & 2) != 0) {
            str2 = responseReceivedParams.loaderId;
        }
        if ((i & 4) != 0) {
            bigDecimal = responseReceivedParams.timestamp;
        }
        if ((i & 8) != 0) {
            resourceType = responseReceivedParams.type;
        }
        if ((i & 16) != 0) {
            response = responseReceivedParams.response;
        }
        if ((i & 32) != 0) {
            z = responseReceivedParams.hasExtraInfo;
        }
        Response response2 = response;
        boolean z2 = z;
        return responseReceivedParams.copy(str, str2, bigDecimal, resourceType, response2, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLoaderId() {
        return this.loaderId;
    }

    /* renamed from: component3, reason: from getter */
    public final BigDecimal getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component4, reason: from getter */
    public final ResourceType getType() {
        return this.type;
    }

    /* renamed from: component5, reason: from getter */
    public final Response getResponse() {
        return this.response;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getHasExtraInfo() {
        return this.hasExtraInfo;
    }

    public final ResponseReceivedParams copy(String requestId, String loaderId, BigDecimal timestamp, ResourceType type, Response response, boolean hasExtraInfo) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(loaderId, "loaderId");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(response, "response");
        return new ResponseReceivedParams(requestId, loaderId, timestamp, type, response, hasExtraInfo);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResponseReceivedParams)) {
            return false;
        }
        ResponseReceivedParams responseReceivedParams = (ResponseReceivedParams) other;
        return Intrinsics.areEqual(this.requestId, responseReceivedParams.requestId) && Intrinsics.areEqual(this.loaderId, responseReceivedParams.loaderId) && Intrinsics.areEqual(this.timestamp, responseReceivedParams.timestamp) && this.type == responseReceivedParams.type && Intrinsics.areEqual(this.response, responseReceivedParams.response) && this.hasExtraInfo == responseReceivedParams.hasExtraInfo;
    }

    public int hashCode() {
        return (((((((((this.requestId.hashCode() * 31) + this.loaderId.hashCode()) * 31) + this.timestamp.hashCode()) * 31) + this.type.hashCode()) * 31) + this.response.hashCode()) * 31) + Boolean.hashCode(this.hasExtraInfo);
    }

    public String toString() {
        return "ResponseReceivedParams(requestId=" + this.requestId + ", loaderId=" + this.loaderId + ", timestamp=" + this.timestamp + ", type=" + this.type + ", response=" + this.response + ", hasExtraInfo=" + this.hasExtraInfo + ")";
    }

    public ResponseReceivedParams(String requestId, String loaderId, BigDecimal timestamp, ResourceType type, Response response, boolean z) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(loaderId, "loaderId");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(response, "response");
        this.requestId = requestId;
        this.loaderId = loaderId;
        this.timestamp = timestamp;
        this.type = type;
        this.response = response;
        this.hasExtraInfo = z;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public /* synthetic */ ResponseReceivedParams(String str, String str2, BigDecimal bigDecimal, ResourceType resourceType, Response response, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2, bigDecimal, resourceType, response, (i & 32) != 0 ? false : z);
    }

    public final String getLoaderId() {
        return this.loaderId;
    }

    public final BigDecimal getTimestamp() {
        return this.timestamp;
    }

    public final ResourceType getType() {
        return this.type;
    }

    public final Response getResponse() {
        return this.response;
    }

    public final boolean getHasExtraInfo() {
        return this.hasExtraInfo;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ResponseReceivedParams(java.math.BigDecimal r13, java.lang.String r14, okhttp3.Response r15) {
        /*
            r12 = this;
            java.lang.String r0 = "now"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "requestId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "okhttpResponse"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            expo.modules.kotlin.devtools.cdp.ResourceType$Companion r0 = expo.modules.kotlin.devtools.cdp.ResourceType.INSTANCE
            java.lang.String r1 = "Content-Type"
            java.lang.String r2 = ""
            java.lang.String r1 = r15.header(r1, r2)
            if (r1 != 0) goto L1c
            goto L1d
        L1c:
            r2 = r1
        L1d:
            expo.modules.kotlin.devtools.cdp.ResourceType r7 = r0.fromMimeType(r2)
            expo.modules.kotlin.devtools.cdp.Response r8 = new expo.modules.kotlin.devtools.cdp.Response
            r8.<init>(r15)
            r10 = 34
            r11 = 0
            r5 = 0
            r9 = 0
            r3 = r12
            r6 = r13
            r4 = r14
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.ResponseReceivedParams.<init>(java.math.BigDecimal, java.lang.String, okhttp3.Response):void");
    }

    @Override // expo.modules.kotlin.devtools.cdp.JsonSerializable
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestId", this.requestId);
        jSONObject.put("loaderId", this.loaderId);
        jSONObject.put("timestamp", this.timestamp);
        jSONObject.put("type", this.type.getValue());
        jSONObject.put("response", this.response.toJSONObject());
        jSONObject.put("hasExtraInfo", this.hasExtraInfo);
        return jSONObject;
    }
}
