package com.facebook.react.devsupport;

import androidx.core.os.EnvironmentCompat;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.MultipartStreamReader;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.DeprecatedUpgrade;
import okio.Sink;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BundleDownloader.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0002 !B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J6\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007J:\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002JB\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/facebook/react/devsupport/BundleDownloader;", "", "client", "Lokhttp3/OkHttpClient;", "<init>", "(Lokhttp3/OkHttpClient;)V", "downloadBundleFromURLCall", "Lokhttp3/Call;", "downloadBundleFromURL", "", "callback", "Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;", "outputFile", "Ljava/io/File;", "bundleURL", "", "bundleInfo", "Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo;", "requestBuilder", "Lokhttp3/Request$Builder;", "processMultipartResponse", ImagesContract.URL, "response", "Lokhttp3/Response;", "boundary", "processBundleResult", "statusCode", "", "headers", "Lokhttp3/Headers;", "body", "Lokio/BufferedSource;", "BundleInfo", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BundleDownloader {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int FILES_CHANGED_COUNT_NOT_BUILT_BY_BUNDLER = -2;
    private static final String TAG = "BundleDownloader";
    private final OkHttpClient client;
    private Call downloadBundleFromURLCall;

    public final void downloadBundleFromURL(DevBundleDownloadListener callback, File outputFile, String str, BundleInfo bundleInfo) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        downloadBundleFromURL$default(this, callback, outputFile, str, bundleInfo, null, 16, null);
    }

    public BundleDownloader(OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
    }

    /* compiled from: BundleDownloader.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo;", "", "<init>", "()V", "_url", "", "get_url$ReactAndroid_release", "()Ljava/lang/String;", "set_url$ReactAndroid_release", "(Ljava/lang/String;)V", ImagesContract.URL, "getUrl", "value", "", "filesChangedCount", "getFilesChangedCount", "()I", "setFilesChangedCount$ReactAndroid_release", "(I)V", "toJSONString", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class BundleInfo {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private String _url;
        private int filesChangedCount;

        @JvmStatic
        public static final BundleInfo fromJSONString(String str) {
            return INSTANCE.fromJSONString(str);
        }

        /* renamed from: get_url$ReactAndroid_release, reason: from getter */
        public final String get_url() {
            return this._url;
        }

        public final void set_url$ReactAndroid_release(String str) {
            this._url = str;
        }

        public final String getUrl() {
            String str = this._url;
            return str == null ? EnvironmentCompat.MEDIA_UNKNOWN : str;
        }

        public final int getFilesChangedCount() {
            return this.filesChangedCount;
        }

        public final void setFilesChangedCount$ReactAndroid_release(int i) {
            this.filesChangedCount = i;
        }

        public final String toJSONString() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ImagesContract.URL, this._url);
                jSONObject.put("filesChangedCount", this.filesChangedCount);
                return jSONObject.toString();
            } catch (JSONException e) {
                FLog.e(BundleDownloader.TAG, "Can't serialize bundle info: ", e);
                return null;
            }
        }

        /* compiled from: BundleDownloader.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo$Companion;", "", "<init>", "()V", "fromJSONString", "Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo;", "jsonStr", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final BundleInfo fromJSONString(String jsonStr) {
                if (jsonStr == null) {
                    return null;
                }
                try {
                    JSONObject jSONObject = new JSONObject(jsonStr);
                    BundleInfo bundleInfo = new BundleInfo();
                    bundleInfo.set_url$ReactAndroid_release(jSONObject.getString(ImagesContract.URL));
                    bundleInfo.setFilesChangedCount$ReactAndroid_release(jSONObject.getInt("filesChangedCount"));
                    return bundleInfo;
                } catch (JSONException e) {
                    FLog.e(BundleDownloader.TAG, "Invalid bundle info: ", e);
                    return null;
                }
            }
        }
    }

    public static /* synthetic */ void downloadBundleFromURL$default(BundleDownloader bundleDownloader, DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleInfo bundleInfo, Request.Builder builder, int i, Object obj) {
        if ((i & 16) != 0) {
            builder = new Request.Builder();
        }
        bundleDownloader.downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, builder);
    }

    public final void downloadBundleFromURL(final DevBundleDownloadListener callback, final File outputFile, String bundleURL, final BundleInfo bundleInfo, Request.Builder requestBuilder) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        Intrinsics.checkNotNullParameter(requestBuilder, "requestBuilder");
        if (bundleURL == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        Call newCall = this.client.newCall(requestBuilder.url(bundleURL).addHeader(HttpHeaders.ACCEPT, "multipart/mixed").build());
        this.downloadBundleFromURLCall = newCall;
        if (newCall == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        newCall.enqueue(new Callback() { // from class: com.facebook.react.devsupport.BundleDownloader$downloadBundleFromURL$1
            /* JADX WARN: Code restructure failed: missing block: B:3:0x0013, code lost:
            
                r0 = r4.this$0.downloadBundleFromURLCall;
             */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onFailure(okhttp3.Call r5, java.io.IOException r6) {
                /*
                    r4 = this;
                    java.lang.String r0 = "call"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    java.lang.String r0 = "e"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                    com.facebook.react.devsupport.BundleDownloader r0 = com.facebook.react.devsupport.BundleDownloader.this
                    okhttp3.Call r0 = com.facebook.react.devsupport.BundleDownloader.access$getDownloadBundleFromURLCall$p(r0)
                    r1 = 0
                    if (r0 == 0) goto L55
                    com.facebook.react.devsupport.BundleDownloader r0 = com.facebook.react.devsupport.BundleDownloader.this
                    okhttp3.Call r0 = com.facebook.react.devsupport.BundleDownloader.access$getDownloadBundleFromURLCall$p(r0)
                    if (r0 == 0) goto L23
                    boolean r0 = r0.getCanceled()
                    r2 = 1
                    if (r0 != r2) goto L23
                    goto L55
                L23:
                    com.facebook.react.devsupport.BundleDownloader r0 = com.facebook.react.devsupport.BundleDownloader.this
                    com.facebook.react.devsupport.BundleDownloader.access$setDownloadBundleFromURLCall$p(r0, r1)
                    okhttp3.Request r5 = r5.request()
                    okhttp3.HttpUrl r5 = r5.getUrl()
                    java.lang.String r5 = r5.getUrl()
                    com.facebook.react.devsupport.interfaces.DevBundleDownloadListener r0 = r2
                    com.facebook.react.common.DebugServerException$Companion r1 = com.facebook.react.common.DebugServerException.INSTANCE
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    java.lang.String r3 = "URL: "
                    r2.<init>(r3)
                    java.lang.StringBuilder r2 = r2.append(r5)
                    java.lang.String r2 = r2.toString()
                    java.lang.Throwable r6 = (java.lang.Throwable) r6
                    java.lang.String r3 = "Could not connect to development server."
                    com.facebook.react.common.DebugServerException r5 = r1.makeGeneric(r5, r3, r2, r6)
                    java.lang.Exception r5 = (java.lang.Exception) r5
                    r0.onFailure(r5)
                    return
                L55:
                    com.facebook.react.devsupport.BundleDownloader r5 = com.facebook.react.devsupport.BundleDownloader.this
                    com.facebook.react.devsupport.BundleDownloader.access$setDownloadBundleFromURLCall$p(r5, r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.devsupport.BundleDownloader$downloadBundleFromURL$1.onFailure(okhttp3.Call, java.io.IOException):void");
            }

            /* JADX WARN: Code restructure failed: missing block: B:5:0x001e, code lost:
            
                r12 = r0.downloadBundleFromURLCall;
             */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onResponse(okhttp3.Call r12, okhttp3.Response r13) throws java.io.IOException {
                /*
                    r11 = this;
                    java.lang.String r0 = "call"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
                    java.lang.String r12 = "response"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r12)
                    java.io.Closeable r13 = (java.io.Closeable) r13
                    com.facebook.react.devsupport.BundleDownloader r0 = com.facebook.react.devsupport.BundleDownloader.this
                    java.io.File r4 = r3
                    com.facebook.react.devsupport.BundleDownloader$BundleInfo r5 = r4
                    com.facebook.react.devsupport.interfaces.DevBundleDownloadListener r6 = r2
                    r2 = r13
                    okhttp3.Response r2 = (okhttp3.Response) r2     // Catch: java.lang.Throwable -> Lb1
                    okhttp3.Call r12 = com.facebook.react.devsupport.BundleDownloader.access$getDownloadBundleFromURLCall$p(r0)     // Catch: java.lang.Throwable -> Lb1
                    r8 = 0
                    if (r12 == 0) goto Laa
                    okhttp3.Call r12 = com.facebook.react.devsupport.BundleDownloader.access$getDownloadBundleFromURLCall$p(r0)     // Catch: java.lang.Throwable -> Lb1
                    r1 = 1
                    if (r12 == 0) goto L2d
                    boolean r12 = r12.getCanceled()     // Catch: java.lang.Throwable -> Lb1
                    if (r12 != r1) goto L2d
                    goto Laa
                L2d:
                    com.facebook.react.devsupport.BundleDownloader.access$setDownloadBundleFromURLCall$p(r0, r8)     // Catch: java.lang.Throwable -> Lb1
                    okhttp3.Request r12 = r2.getRequest()     // Catch: java.lang.Throwable -> Lb1
                    okhttp3.HttpUrl r12 = r12.getUrl()     // Catch: java.lang.Throwable -> Lb1
                    java.lang.String r12 = r12.getUrl()     // Catch: java.lang.Throwable -> Lb1
                    java.lang.String r3 = "content-type"
                    r7 = 2
                    java.lang.String r3 = okhttp3.Response.header$default(r2, r3, r8, r7, r8)     // Catch: java.lang.Throwable -> Lb1
                    if (r3 != 0) goto L47
                    java.lang.String r3 = ""
                L47:
                    java.lang.String r7 = "multipart/mixed;.*boundary=\"([^\"]+)\""
                    java.util.regex.Pattern r7 = java.util.regex.Pattern.compile(r7)     // Catch: java.lang.Throwable -> Lb1
                    r9 = r3
                    java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch: java.lang.Throwable -> Lb1
                    java.util.regex.Matcher r7 = r7.matcher(r9)     // Catch: java.lang.Throwable -> Lb1
                    java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch: java.lang.Throwable -> Lb1
                    int r3 = r3.length()     // Catch: java.lang.Throwable -> Lb1
                    if (r3 <= 0) goto L75
                    boolean r3 = r7.find()     // Catch: java.lang.Throwable -> Lb1
                    if (r3 == 0) goto L75
                    java.lang.String r1 = r7.group(r1)     // Catch: java.lang.Throwable -> Lb1
                    java.lang.Object r1 = com.facebook.infer.annotation.Assertions.assertNotNull(r1)     // Catch: java.lang.Throwable -> Lb1
                    r3 = r1
                    java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> Lb1
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Throwable -> Lb1
                    r1 = r12
                    com.facebook.react.devsupport.BundleDownloader.access$processMultipartResponse(r0, r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> Lb1
                    goto L9c
                L75:
                    r1 = r12
                    okhttp3.ResponseBody r12 = r2.getBody()     // Catch: java.lang.Throwable -> Lb1
                    java.io.Closeable r12 = (java.io.Closeable) r12     // Catch: java.lang.Throwable -> Lb1
                    r3 = r12
                    okhttp3.ResponseBody r3 = (okhttp3.ResponseBody) r3     // Catch: java.lang.Throwable -> La2
                    if (r3 == 0) goto L97
                    r7 = r2
                    int r2 = r7.getCode()     // Catch: java.lang.Throwable -> La2
                    okhttp3.Headers r7 = r7.getHeaders()     // Catch: java.lang.Throwable -> La2
                    okio.BufferedSource r3 = r3.getSource()     // Catch: java.lang.Throwable -> La2
                    r10 = r4
                    r4 = r3
                    r3 = r7
                    r7 = r6
                    r6 = r5
                    r5 = r10
                    com.facebook.react.devsupport.BundleDownloader.access$processBundleResult(r0, r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> La2
                L97:
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> La2
                    kotlin.io.CloseableKt.closeFinally(r12, r8)     // Catch: java.lang.Throwable -> Lb1
                L9c:
                    kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lb1
                    kotlin.io.CloseableKt.closeFinally(r13, r8)
                    return
                La2:
                    r0 = move-exception
                    r1 = r0
                    throw r1     // Catch: java.lang.Throwable -> La5
                La5:
                    r0 = move-exception
                    kotlin.io.CloseableKt.closeFinally(r12, r1)     // Catch: java.lang.Throwable -> Lb1
                    throw r0     // Catch: java.lang.Throwable -> Lb1
                Laa:
                    com.facebook.react.devsupport.BundleDownloader.access$setDownloadBundleFromURLCall$p(r0, r8)     // Catch: java.lang.Throwable -> Lb1
                    kotlin.io.CloseableKt.closeFinally(r13, r8)
                    return
                Lb1:
                    r0 = move-exception
                    r12 = r0
                    throw r12     // Catch: java.lang.Throwable -> Lb4
                Lb4:
                    r0 = move-exception
                    kotlin.io.CloseableKt.closeFinally(r13, r12)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.devsupport.BundleDownloader$downloadBundleFromURL$1.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processMultipartResponse(final String url, final Response response, String boundary, final File outputFile, final BundleInfo bundleInfo, final DevBundleDownloadListener callback) throws IOException {
        if (response.getBody() == null) {
            callback.onFailure(new DebugServerException(StringsKt.trimIndent("\n                    Error while reading multipart response.\n                    \n                    Response body was empty: " + response.getCode() + "\n                    \n                    URL: " + url + "\n                    \n                    \n                    ")));
            return;
        }
        ResponseBody body = response.getBody();
        BufferedSource source = body != null ? body.getSource() : null;
        if (source == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        if (new MultipartStreamReader(source, boundary).readAllParts(new MultipartStreamReader.ChunkListener() { // from class: com.facebook.react.devsupport.BundleDownloader$processMultipartResponse$completed$1
            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkComplete(Map<String, String> headers, Buffer body2, boolean isLastChunk) throws IOException {
                Intrinsics.checkNotNullParameter(headers, "headers");
                Intrinsics.checkNotNullParameter(body2, "body");
                if (isLastChunk) {
                    int code = Response.this.getCode();
                    if (headers.containsKey("X-Http-Status")) {
                        code = Integer.parseInt(headers.getOrDefault("X-Http-Status", "0"));
                    }
                    this.processBundleResult(url, code, Headers.INSTANCE.m3059deprecated_of(headers), body2, outputFile, bundleInfo, callback);
                    return;
                }
                if (headers.containsKey("Content-Type") && Intrinsics.areEqual(headers.get("Content-Type"), "application/json")) {
                    try {
                        JSONObject jSONObject = new JSONObject(body2.readUtf8());
                        callback.onProgress(jSONObject.has("status") ? jSONObject.getString("status") : "Bundling", jSONObject.has("done") ? Integer.valueOf(jSONObject.getInt("done")) : null, jSONObject.has("total") ? Integer.valueOf(jSONObject.getInt("total")) : null);
                    } catch (JSONException e) {
                        FLog.e(ReactConstants.TAG, "Error parsing progress JSON. " + e);
                    }
                }
            }

            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkProgress(Map<String, String> headers, long loaded, long total) {
                Intrinsics.checkNotNullParameter(headers, "headers");
                if (Intrinsics.areEqual("application/javascript", headers.get("Content-Type"))) {
                    long j = 1024;
                    callback.onProgress("Downloading", Integer.valueOf((int) (loaded / j)), Integer.valueOf((int) (total / j)));
                }
            }
        })) {
            return;
        }
        callback.onFailure(new DebugServerException(StringsKt.trimIndent("\n                    Error while reading multipart response.\n                    \n                    Response code: " + response.getCode() + "\n                    \n                    URL: " + url + "\n                    \n                    \n                    ")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processBundleResult(String url, int statusCode, Headers headers, BufferedSource body, File outputFile, BundleInfo bundleInfo, DevBundleDownloadListener callback) throws IOException {
        if (statusCode != 200) {
            String readUtf8 = body.readUtf8();
            DebugServerException parse = DebugServerException.INSTANCE.parse(url, readUtf8);
            if (parse != null) {
                callback.onFailure(parse);
                return;
            }
            StringBuilder sb = new StringBuilder("The development server returned response error code: ");
            sb.append(statusCode).append("\n\nURL: ").append(url).append("\n\nBody:\n").append(readUtf8);
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            callback.onFailure(new DebugServerException(sb2));
            return;
        }
        if (bundleInfo != null) {
            INSTANCE.populateBundleInfo(url, headers, bundleInfo);
        }
        File file = new File(outputFile.getPath() + DefaultDiskStorage.FileType.TEMP);
        if (INSTANCE.storePlainJSInFile(body, file) && !file.renameTo(outputFile)) {
            throw new IOException("Couldn't rename " + file + " to " + outputFile);
        }
        callback.onSuccess();
    }

    /* compiled from: BundleDownloader.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/devsupport/BundleDownloader$Companion;", "", "<init>", "()V", "TAG", "", "FILES_CHANGED_COUNT_NOT_BUILT_BY_BUNDLER", "", "storePlainJSInFile", "", "body", "Lokio/BufferedSource;", "outputFile", "Ljava/io/File;", "populateBundleInfo", "", ImagesContract.URL, "headers", "Lokhttp3/Headers;", "bundleInfo", "Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean storePlainJSInFile(BufferedSource body, File outputFile) throws IOException {
            Sink sink = DeprecatedUpgrade.getOkio().sink(outputFile);
            try {
                body.readAll(sink);
                CloseableKt.closeFinally(sink, null);
                return true;
            } finally {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void populateBundleInfo(String url, Headers headers, BundleInfo bundleInfo) {
            bundleInfo.set_url$ReactAndroid_release(url);
            String str = headers.get("X-Metro-Files-Changed-Count");
            if (str != null) {
                try {
                    bundleInfo.setFilesChangedCount$ReactAndroid_release(Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    bundleInfo.setFilesChangedCount$ReactAndroid_release(-2);
                    FLog.e(BundleDownloader.TAG, "Can't populate bundle info: ", e);
                }
            }
        }
    }
}
