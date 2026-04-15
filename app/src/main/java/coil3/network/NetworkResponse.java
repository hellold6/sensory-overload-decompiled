package coil3.network;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkClient.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001BG\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\f\u0010\rJF\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcoil3/network/NetworkResponse;", "", "code", "", "requestMillis", "", "responseMillis", "headers", "Lcoil3/network/NetworkHeaders;", "body", "Lcoil3/network/NetworkResponseBody;", "delegate", "<init>", "(IJJLcoil3/network/NetworkHeaders;Lcoil3/network/NetworkResponseBody;Ljava/lang/Object;)V", "getCode", "()I", "getRequestMillis", "()J", "getResponseMillis", "getHeaders", "()Lcoil3/network/NetworkHeaders;", "getBody", "()Lcoil3/network/NetworkResponseBody;", "getDelegate", "()Ljava/lang/Object;", "copy", "coil-network-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkResponse {
    private final NetworkResponseBody body;
    private final int code;
    private final Object delegate;
    private final NetworkHeaders headers;
    private final long requestMillis;
    private final long responseMillis;

    public NetworkResponse() {
        this(0, 0L, 0L, null, null, null, 63, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetworkResponse)) {
            return false;
        }
        NetworkResponse networkResponse = (NetworkResponse) obj;
        return this.code == networkResponse.code && this.requestMillis == networkResponse.requestMillis && this.responseMillis == networkResponse.responseMillis && Intrinsics.areEqual(this.headers, networkResponse.headers) && Intrinsics.areEqual(this.body, networkResponse.body) && Intrinsics.areEqual(this.delegate, networkResponse.delegate);
    }

    public int hashCode() {
        int hashCode = ((((((this.code * 31) + Long.hashCode(this.requestMillis)) * 31) + Long.hashCode(this.responseMillis)) * 31) + this.headers.hashCode()) * 31;
        NetworkResponseBody networkResponseBody = this.body;
        int hashCode2 = (hashCode + (networkResponseBody == null ? 0 : networkResponseBody.hashCode())) * 31;
        Object obj = this.delegate;
        return hashCode2 + (obj != null ? obj.hashCode() : 0);
    }

    public String toString() {
        return "NetworkResponse(code=" + this.code + ", requestMillis=" + this.requestMillis + ", responseMillis=" + this.responseMillis + ", headers=" + this.headers + ", body=" + this.body + ", delegate=" + this.delegate + ')';
    }

    public NetworkResponse(int i, long j, long j2, NetworkHeaders networkHeaders, NetworkResponseBody networkResponseBody, Object obj) {
        this.code = i;
        this.requestMillis = j;
        this.responseMillis = j2;
        this.headers = networkHeaders;
        this.body = networkResponseBody;
        this.delegate = obj;
    }

    public final int getCode() {
        return this.code;
    }

    public final long getRequestMillis() {
        return this.requestMillis;
    }

    public final long getResponseMillis() {
        return this.responseMillis;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ NetworkResponse(int r3, long r4, long r6, coil3.network.NetworkHeaders r8, coil3.network.NetworkResponseBody r9, java.lang.Object r10, int r11, kotlin.jvm.internal.DefaultConstructorMarker r12) {
        /*
            r2 = this;
            r12 = r11 & 1
            if (r12 == 0) goto L6
            r3 = 200(0xc8, float:2.8E-43)
        L6:
            r12 = r11 & 2
            r0 = 0
            if (r12 == 0) goto Ld
            r4 = r0
        Ld:
            r12 = r11 & 4
            if (r12 == 0) goto L12
            r6 = r0
        L12:
            r12 = r11 & 8
            if (r12 == 0) goto L18
            coil3.network.NetworkHeaders r8 = coil3.network.NetworkHeaders.EMPTY
        L18:
            r12 = r11 & 16
            r0 = 0
            if (r12 == 0) goto L1e
            r9 = r0
        L1e:
            r11 = r11 & 32
            if (r11 == 0) goto L26
            r12 = r0
            r10 = r8
            r11 = r9
            goto L29
        L26:
            r12 = r10
            r11 = r9
            r10 = r8
        L29:
            r8 = r6
            r6 = r4
            r4 = r2
            r5 = r3
            r4.<init>(r5, r6, r8, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil3.network.NetworkResponse.<init>(int, long, long, coil3.network.NetworkHeaders, coil3.network.NetworkResponseBody, java.lang.Object, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final NetworkHeaders getHeaders() {
        return this.headers;
    }

    public final NetworkResponseBody getBody() {
        return this.body;
    }

    public final Object getDelegate() {
        return this.delegate;
    }

    public static /* synthetic */ NetworkResponse copy$default(NetworkResponse networkResponse, int i, long j, long j2, NetworkHeaders networkHeaders, NetworkResponseBody networkResponseBody, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = networkResponse.code;
        }
        if ((i2 & 2) != 0) {
            j = networkResponse.requestMillis;
        }
        if ((i2 & 4) != 0) {
            j2 = networkResponse.responseMillis;
        }
        if ((i2 & 8) != 0) {
            networkHeaders = networkResponse.headers;
        }
        if ((i2 & 16) != 0) {
            networkResponseBody = networkResponse.body;
        }
        if ((i2 & 32) != 0) {
            obj = networkResponse.delegate;
        }
        Object obj3 = obj;
        NetworkHeaders networkHeaders2 = networkHeaders;
        long j3 = j2;
        return networkResponse.copy(i, j, j3, networkHeaders2, networkResponseBody, obj3);
    }

    public final NetworkResponse copy(int code, long requestMillis, long responseMillis, NetworkHeaders headers, NetworkResponseBody body, Object delegate) {
        return new NetworkResponse(code, requestMillis, responseMillis, headers, body, delegate);
    }
}
