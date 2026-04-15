package com.facebook.react.devsupport;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import com.facebook.common.logging.FLog;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.BundleDownloader;
import com.facebook.react.devsupport.DevServerHelper;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.FileIoHandler;
import com.facebook.react.packagerconnection.JSPackagerClient;
import com.facebook.react.packagerconnection.NotificationOnlyHandler;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import com.facebook.react.packagerconnection.ReconnectingWebSocket;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.util.RNLog;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.DeprecatedOkio;
import okio.DeprecatedUpgrade;
import okio.Sink;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: DevServerHelper.kt */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0017\u0018\u0000 N2\u00020\u0001:\u0003LMNB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u000b2\u0006\u0010&\u001a\u00020'J\u0006\u0010(\u001a\u00020$J\u0006\u0010)\u001a\u00020$J\u0006\u0010*\u001a\u00020$J\u0006\u0010+\u001a\u00020$J6\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u000b2\b\u00102\u001a\u0004\u0018\u0001032\b\b\u0002\u00104\u001a\u000205H\u0007J\u0018\u00106\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\u000bH\u0002J6\u00109\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020;2\b\b\u0002\u00108\u001a\u00020\u000b2\b\b\u0002\u0010<\u001a\u00020\u001e2\b\b\u0002\u0010=\u001a\u00020\u001eH\u0002J\u0010\u0010>\u001a\u00020\u000b2\u0006\u0010?\u001a\u00020\u000bH\u0016J\u0010\u0010@\u001a\u00020\u000b2\u0006\u0010?\u001a\u00020\u000bH\u0016J\u0010\u0010A\u001a\u00020$2\u0006\u0010-\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020\u000b2\u0006\u0010D\u001a\u00020\u000bH\u0016J\u0010\u0010E\u001a\u00020\u000b2\u0006\u0010D\u001a\u00020\u000bH\u0016J\u0018\u0010F\u001a\u0004\u0018\u0001002\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010/\u001a\u000200J\u001a\u0010H\u001a\u00020$2\b\u0010I\u001a\u0004\u0018\u00010J2\b\u0010K\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\rR\u0014\u0010\u001b\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\rR\u0014\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010 ¨\u0006O"}, d2 = {"Lcom/facebook/react/devsupport/DevServerHelper;", "", "settings", "Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;", "applicationContext", "Landroid/content/Context;", "packagerConnectionSettings", "Lcom/facebook/react/packagerconnection/PackagerConnectionSettings;", "<init>", "(Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;Landroid/content/Context;Lcom/facebook/react/packagerconnection/PackagerConnectionSettings;)V", "websocketProxyURL", "", "getWebsocketProxyURL", "()Ljava/lang/String;", "client", "Lokhttp3/OkHttpClient;", "bundleDownloader", "Lcom/facebook/react/devsupport/BundleDownloader;", "packagerStatusCheck", "Lcom/facebook/react/devsupport/PackagerStatusCheck;", "packageName", "packagerClient", "Lcom/facebook/react/packagerconnection/JSPackagerClient;", "inspectorPackagerConnection", "Lcom/facebook/react/devsupport/IInspectorPackagerConnection;", "inspectorDeviceId", "getInspectorDeviceId", "inspectorDeviceUrl", "getInspectorDeviceUrl", "devMode", "", "getDevMode", "()Z", "jSMinifyMode", "getJSMinifyMode", "openPackagerConnection", "", "clientId", "commandListener", "Lcom/facebook/react/devsupport/DevServerHelper$PackagerCommandListener;", "closePackagerConnection", "openInspectorConnection", "disableDebugger", "closeInspectorConnection", "downloadBundleFromURL", "callback", "Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;", "outputFile", "Ljava/io/File;", "bundleURL", "bundleInfo", "Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo;", "requestBuilder", "Lokhttp3/Request$Builder;", "createSplitBundleURL", "mainModuleID", "host", "createBundleURL", "type", "Lcom/facebook/react/devsupport/DevServerHelper$BundleType;", "modulesOnly", "runModule", "getDevServerBundleURL", "jsModulePath", "getDevServerSplitBundleURL", "isPackagerRunning", "Lcom/facebook/react/devsupport/interfaces/PackagerStatusCallback;", "getSourceMapUrl", "mainModuleName", "getSourceUrl", "downloadBundleResourceFromUrlSync", "resourcePath", "openDebugger", "context", "Lcom/facebook/react/bridge/ReactContext;", "errorMessage", "PackagerCommandListener", "BundleType", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DevServerHelper {
    private static final Companion Companion = new Companion(null);
    private static final String DEBUGGER_MSG_DISABLE = "{ \"id\":1,\"method\":\"Debugger.disable\" }";
    private static final int HTTP_CONNECT_TIMEOUT_MS = 5000;
    private final Context applicationContext;
    private final BundleDownloader bundleDownloader;
    private final OkHttpClient client;
    private IInspectorPackagerConnection inspectorPackagerConnection;
    private final String packageName;
    private JSPackagerClient packagerClient;
    private final PackagerConnectionSettings packagerConnectionSettings;
    private final PackagerStatusCheck packagerStatusCheck;
    private final DeveloperSettings settings;

    /* compiled from: DevServerHelper.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/DevServerHelper$PackagerCommandListener;", "", "onPackagerConnected", "", "onPackagerDisconnected", "onPackagerReloadCommand", "onPackagerDevMenuCommand", "customCommandHandlers", "", "", "Lcom/facebook/react/packagerconnection/RequestHandler;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface PackagerCommandListener {
        Map<String, RequestHandler> customCommandHandlers();

        void onPackagerConnected();

        void onPackagerDevMenuCommand();

        void onPackagerDisconnected();

        void onPackagerReloadCommand();
    }

    public final void downloadBundleFromURL(DevBundleDownloadListener callback, File outputFile, String str, BundleDownloader.BundleInfo bundleInfo) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        downloadBundleFromURL$default(this, callback, outputFile, str, bundleInfo, null, 16, null);
    }

    public DevServerHelper(DeveloperSettings settings, Context applicationContext, PackagerConnectionSettings packagerConnectionSettings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(packagerConnectionSettings, "packagerConnectionSettings");
        this.settings = settings;
        this.applicationContext = applicationContext;
        this.packagerConnectionSettings = packagerConnectionSettings;
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(5000L, TimeUnit.MILLISECONDS).readTimeout(0L, TimeUnit.MILLISECONDS).writeTimeout(0L, TimeUnit.MILLISECONDS).build();
        this.client = build;
        this.bundleDownloader = new BundleDownloader(build);
        this.packagerStatusCheck = new PackagerStatusCheck(build);
        String packageName = applicationContext.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        this.packageName = packageName;
    }

    public final String getWebsocketProxyURL() {
        return "ws://" + this.packagerConnectionSettings.getDebugServerHost() + "/debugger-proxy?role=client";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: DevServerHelper.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/facebook/react/devsupport/DevServerHelper$BundleType;", "", "typeID", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getTypeID", "()Ljava/lang/String;", "BUNDLE", "MAP", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class BundleType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ BundleType[] $VALUES;
        public static final BundleType BUNDLE = new BundleType("BUNDLE", 0, "bundle");
        public static final BundleType MAP = new BundleType("MAP", 1, "map");
        private final String typeID;

        private static final /* synthetic */ BundleType[] $values() {
            return new BundleType[]{BUNDLE, MAP};
        }

        public static EnumEntries<BundleType> getEntries() {
            return $ENTRIES;
        }

        private BundleType(String str, int i, String str2) {
            this.typeID = str2;
        }

        public final String getTypeID() {
            return this.typeID;
        }

        static {
            BundleType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static BundleType valueOf(String str) {
            return (BundleType) Enum.valueOf(BundleType.class, str);
        }

        public static BundleType[] values() {
            return (BundleType[]) $VALUES.clone();
        }
    }

    private final String getInspectorDeviceId() {
        String str = this.packageName;
        String string = Settings.Secure.getString(this.applicationContext.getContentResolver(), "android_id");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "android-%s-%s-%s", Arrays.copyOf(new Object[]{str, string, InspectorFlags.getFuseboxEnabled() ? "fusebox" : "legacy"}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return Companion.getSHA256(format);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getInspectorDeviceUrl() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "http://%s/inspector/device?name=%s&app=%s&device=%s&profiling=%b", Arrays.copyOf(new Object[]{this.packagerConnectionSettings.getDebugServerHost(), Uri.encode(AndroidInfoHelpers.getFriendlyDeviceName()), Uri.encode(this.packageName), Uri.encode(getInspectorDeviceId()), Boolean.valueOf(InspectorFlags.getIsProfilingBuild())}, 5));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final boolean getDevMode() {
        return this.settings.isJSDevModeEnabled();
    }

    private final boolean getJSMinifyMode() {
        return this.settings.isJSMinifyEnabled();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.facebook.react.devsupport.DevServerHelper$openPackagerConnection$1] */
    public final void openPackagerConnection(final String clientId, final PackagerCommandListener commandListener) {
        Intrinsics.checkNotNullParameter(commandListener, "commandListener");
        if (this.packagerClient != null) {
            FLog.w(ReactConstants.TAG, "Packager connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper$openPackagerConnection$1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                @Deprecated(message = "This needs to be rewritten to not use AsyncTasks")
                public Void doInBackground(Void... backgroundParams) {
                    PackagerConnectionSettings packagerConnectionSettings;
                    Intrinsics.checkNotNullParameter(backgroundParams, "backgroundParams");
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    final DevServerHelper.PackagerCommandListener packagerCommandListener = DevServerHelper.PackagerCommandListener.this;
                    linkedHashMap.put("reload", new NotificationOnlyHandler() { // from class: com.facebook.react.devsupport.DevServerHelper$openPackagerConnection$1$doInBackground$1
                        @Override // com.facebook.react.packagerconnection.NotificationOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
                        public void onNotification(Object params) {
                            DevServerHelper.PackagerCommandListener.this.onPackagerReloadCommand();
                        }
                    });
                    final DevServerHelper.PackagerCommandListener packagerCommandListener2 = DevServerHelper.PackagerCommandListener.this;
                    linkedHashMap.put("devMenu", new NotificationOnlyHandler() { // from class: com.facebook.react.devsupport.DevServerHelper$openPackagerConnection$1$doInBackground$2
                        @Override // com.facebook.react.packagerconnection.NotificationOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
                        public void onNotification(Object params) {
                            DevServerHelper.PackagerCommandListener.this.onPackagerDevMenuCommand();
                        }
                    });
                    Map<String, RequestHandler> customCommandHandlers = DevServerHelper.PackagerCommandListener.this.customCommandHandlers();
                    if (customCommandHandlers != null) {
                        linkedHashMap.putAll(customCommandHandlers);
                    }
                    linkedHashMap.putAll(new FileIoHandler().handlers());
                    final DevServerHelper.PackagerCommandListener packagerCommandListener3 = DevServerHelper.PackagerCommandListener.this;
                    ReconnectingWebSocket.ConnectionCallback connectionCallback = new ReconnectingWebSocket.ConnectionCallback() { // from class: com.facebook.react.devsupport.DevServerHelper$openPackagerConnection$1$doInBackground$onPackagerConnectedCallback$1
                        @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.ConnectionCallback
                        public void onConnected() {
                            DevServerHelper.PackagerCommandListener.this.onPackagerConnected();
                        }

                        @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.ConnectionCallback
                        public void onDisconnected() {
                            DevServerHelper.PackagerCommandListener.this.onPackagerDisconnected();
                        }
                    };
                    if (clientId == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    DevServerHelper devServerHelper = this;
                    String str = clientId;
                    packagerConnectionSettings = this.packagerConnectionSettings;
                    JSPackagerClient jSPackagerClient = new JSPackagerClient(str, packagerConnectionSettings, linkedHashMap, connectionCallback);
                    jSPackagerClient.init();
                    devServerHelper.packagerClient = jSPackagerClient;
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.facebook.react.devsupport.DevServerHelper$closePackagerConnection$1] */
    public final void closePackagerConnection() {
        new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper$closePackagerConnection$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            @Deprecated(message = "This class needs to be rewritten to don't use AsyncTasks")
            public Void doInBackground(Void... params) {
                JSPackagerClient jSPackagerClient;
                Intrinsics.checkNotNullParameter(params, "params");
                jSPackagerClient = DevServerHelper.this.packagerClient;
                if (jSPackagerClient != null) {
                    jSPackagerClient.close();
                }
                DevServerHelper.this.packagerClient = null;
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.facebook.react.devsupport.DevServerHelper$openInspectorConnection$1] */
    public final void openInspectorConnection() {
        if (this.inspectorPackagerConnection != null) {
            FLog.w(ReactConstants.TAG, "Inspector connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper$openInspectorConnection$1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                @Deprecated(message = "This class needs to be rewritten to don't use AsyncTasks")
                public Void doInBackground(Void... params) {
                    Context context;
                    String inspectorDeviceUrl;
                    String str;
                    Intrinsics.checkNotNullParameter(params, "params");
                    context = DevServerHelper.this.applicationContext;
                    String str2 = AndroidInfoHelpers.getInspectorHostMetadata(context).get("deviceName");
                    if (str2 == null) {
                        FLog.w(ReactConstants.TAG, "Could not get device name from Inspector Host Metadata.");
                        return null;
                    }
                    DevServerHelper devServerHelper = DevServerHelper.this;
                    inspectorDeviceUrl = DevServerHelper.this.getInspectorDeviceUrl();
                    str = DevServerHelper.this.packageName;
                    CxxInspectorPackagerConnection cxxInspectorPackagerConnection = new CxxInspectorPackagerConnection(inspectorDeviceUrl, str2, str);
                    cxxInspectorPackagerConnection.connect();
                    devServerHelper.inspectorPackagerConnection = cxxInspectorPackagerConnection;
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public final void disableDebugger() {
        IInspectorPackagerConnection iInspectorPackagerConnection = this.inspectorPackagerConnection;
        if (iInspectorPackagerConnection != null) {
            iInspectorPackagerConnection.sendEventToAllConnections(DEBUGGER_MSG_DISABLE);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.facebook.react.devsupport.DevServerHelper$closeInspectorConnection$1] */
    public final void closeInspectorConnection() {
        new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper$closeInspectorConnection$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            @Deprecated(message = "This class needs to be rewritten to don't use AsyncTasks")
            public Void doInBackground(Void... params) {
                IInspectorPackagerConnection iInspectorPackagerConnection;
                Intrinsics.checkNotNullParameter(params, "params");
                iInspectorPackagerConnection = DevServerHelper.this.inspectorPackagerConnection;
                if (iInspectorPackagerConnection != null) {
                    iInspectorPackagerConnection.closeQuietly();
                }
                DevServerHelper.this.inspectorPackagerConnection = null;
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static /* synthetic */ void downloadBundleFromURL$default(DevServerHelper devServerHelper, DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleDownloader.BundleInfo bundleInfo, Request.Builder builder, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: downloadBundleFromURL");
        }
        if ((i & 16) != 0) {
            builder = new Request.Builder();
        }
        devServerHelper.downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, builder);
    }

    public final void downloadBundleFromURL(DevBundleDownloadListener callback, File outputFile, String bundleURL, BundleDownloader.BundleInfo bundleInfo, Request.Builder requestBuilder) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        Intrinsics.checkNotNullParameter(requestBuilder, "requestBuilder");
        this.bundleDownloader.downloadBundleFromURL(callback, outputFile, bundleURL, bundleInfo, requestBuilder);
    }

    private final String createSplitBundleURL(String mainModuleID, String host) {
        return createBundleURL(mainModuleID, BundleType.BUNDLE, host, true, false);
    }

    static /* synthetic */ String createBundleURL$default(DevServerHelper devServerHelper, String str, BundleType bundleType, String str2, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createBundleURL");
        }
        if ((i & 4) != 0) {
            str2 = devServerHelper.packagerConnectionSettings.getDebugServerHost();
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            z = false;
        }
        boolean z3 = z;
        if ((i & 16) != 0) {
            z2 = true;
        }
        return devServerHelper.createBundleURL(str, bundleType, str3, z3, z2);
    }

    private final String createBundleURL(String mainModuleID, BundleType type, String host, boolean modulesOnly, boolean runModule) {
        boolean devMode = getDevMode();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.packagerConnectionSettings.getAdditionalOptionsForPackager().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.length() != 0) {
                sb.append("&" + key + "=" + Uri.encode(value));
            }
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "http://%s/%s.%s?platform=android&dev=%s&lazy=%s&minify=%s&app=%s&modulesOnly=%s&runModule=%s", Arrays.copyOf(new Object[]{host, mainModuleID, type.getTypeID(), Boolean.valueOf(devMode), Boolean.valueOf(devMode), Boolean.valueOf(getJSMinifyMode()), this.packageName, modulesOnly ? "true" : Constants.CASEFIRST_FALSE, runModule ? "true" : Constants.CASEFIRST_FALSE}, 9));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format + (InspectorFlags.getFuseboxEnabled() ? "&excludeSource=true&sourcePaths=url-server" : "") + ((Object) sb);
    }

    public String getDevServerBundleURL(String jsModulePath) {
        Intrinsics.checkNotNullParameter(jsModulePath, "jsModulePath");
        return createBundleURL$default(this, jsModulePath, BundleType.BUNDLE, this.packagerConnectionSettings.getDebugServerHost(), false, false, 24, null);
    }

    public String getDevServerSplitBundleURL(String jsModulePath) {
        Intrinsics.checkNotNullParameter(jsModulePath, "jsModulePath");
        return createSplitBundleURL(jsModulePath, this.packagerConnectionSettings.getDebugServerHost());
    }

    public void isPackagerRunning(PackagerStatusCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.packagerStatusCheck.run(this.packagerConnectionSettings.getDebugServerHost(), callback);
    }

    public String getSourceMapUrl(String mainModuleName) {
        Intrinsics.checkNotNullParameter(mainModuleName, "mainModuleName");
        return createBundleURL$default(this, mainModuleName, BundleType.MAP, null, false, false, 28, null);
    }

    public String getSourceUrl(String mainModuleName) {
        Intrinsics.checkNotNullParameter(mainModuleName, "mainModuleName");
        return createBundleURL$default(this, mainModuleName, BundleType.BUNDLE, null, false, false, 28, null);
    }

    public final File downloadBundleResourceFromUrlSync(String resourcePath, File outputFile) {
        Intrinsics.checkNotNullParameter(resourcePath, "resourcePath");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        try {
            Sink execute = this.client.newCall(new Request.Builder().url(Companion.createResourceURL(this.packagerConnectionSettings.getDebugServerHost(), resourcePath)).build()).execute();
            try {
                Response response = execute;
                if (response.isSuccessful() && response.getBody() != null) {
                    execute = DeprecatedUpgrade.getOkio().sink(outputFile);
                    try {
                        Sink sink = execute;
                        DeprecatedOkio okio2 = DeprecatedUpgrade.getOkio();
                        ResponseBody body = response.getBody();
                        BufferedSource source = body != null ? body.getSource() : null;
                        Intrinsics.checkNotNull(source);
                        okio2.buffer(source).readAll(sink);
                        CloseableKt.closeFinally(execute, null);
                        CloseableKt.closeFinally(execute, null);
                        return outputFile;
                    } finally {
                    }
                }
                CloseableKt.closeFinally(execute, null);
                return null;
            } finally {
            }
        } catch (Exception e) {
            FLog.e(ReactConstants.TAG, "Failed to fetch resource synchronously - resourcePath: \"%s\", outputFile: \"%s\"", resourcePath, outputFile.getAbsolutePath(), e);
            return null;
        }
    }

    public final void openDebugger(final ReactContext context, final String errorMessage) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "http://%s/open-debugger?device=%s", Arrays.copyOf(new Object[]{this.packagerConnectionSettings.getDebugServerHost(), Uri.encode(getInspectorDeviceId())}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        this.client.newCall(new Request.Builder().url(format).method("POST", RequestBody.INSTANCE.create((MediaType) null, "")).build()).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper$openDebugger$1
            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                ReactContext reactContext = ReactContext.this;
                String str = errorMessage;
                if (str == null) {
                    str = "openDebugger error";
                }
                RNLog.w(reactContext, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DevServerHelper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/facebook/react/devsupport/DevServerHelper$Companion;", "", "<init>", "()V", "HTTP_CONNECT_TIMEOUT_MS", "", "DEBUGGER_MSG_DISABLE", "", "getSHA256", "string", "createResourceURL", "host", "resourcePathParam", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getSHA256(String string) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
                messageDigest.reset();
                try {
                    Charset forName = Charset.forName("UTF-8");
                    Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
                    byte[] bytes = string.getBytes(forName);
                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                    byte[] digest = messageDigest.digest(bytes);
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x", Arrays.copyOf(new Object[]{Byte.valueOf(digest[0]), Byte.valueOf(digest[1]), Byte.valueOf(digest[2]), Byte.valueOf(digest[3]), Byte.valueOf(digest[4]), Byte.valueOf(digest[5]), Byte.valueOf(digest[6]), Byte.valueOf(digest[7]), Byte.valueOf(digest[8]), Byte.valueOf(digest[9]), Byte.valueOf(digest[10]), Byte.valueOf(digest[11]), Byte.valueOf(digest[12]), Byte.valueOf(digest[13]), Byte.valueOf(digest[14]), Byte.valueOf(digest[15]), Byte.valueOf(digest[16]), Byte.valueOf(digest[17]), Byte.valueOf(digest[18]), Byte.valueOf(digest[19])}, 20));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    return format;
                } catch (UnsupportedEncodingException e) {
                    throw new AssertionError("This environment doesn't support UTF-8 encoding", e);
                }
            } catch (NoSuchAlgorithmException e2) {
                throw new AssertionError("Could not get standard SHA-256 algorithm", e2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String createResourceURL(String host, String resourcePathParam) {
            if (StringsKt.startsWith$default(resourcePathParam, "/", false, 2, (Object) null)) {
                FLog.w(ReactConstants.TAG, "Resource path should not begin with `/`, removing it.");
                resourcePathParam = resourcePathParam.substring(1);
                Intrinsics.checkNotNullExpressionValue(resourcePathParam, "substring(...)");
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.US, "http://%s/%s", Arrays.copyOf(new Object[]{host, resourcePathParam}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        }
    }
}
