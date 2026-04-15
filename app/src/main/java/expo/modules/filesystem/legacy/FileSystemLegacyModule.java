package expo.modules.filesystem.legacy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.tracing.Trace;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.BaseJavaModule;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import com.google.firebase.messaging.Constants;
import expo.modules.core.errors.ModuleDestroyedException;
import expo.modules.filesystem.legacy.FileSystemLegacyModule;
import expo.modules.interfaces.filesystem.FilePermissionModuleInterface;
import expo.modules.interfaces.filesystem.Permission;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.events.OnActivityResultPayload;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.functions.UntypedAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ConstantComponentBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.types.TypeConverterProvider;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.serverregistration.InstallationId;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KType;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Okio__JvmOkioKt;
import okio.Sink;
import okio.Source;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/* compiled from: FileSystemLegacyModule.kt */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0005WXYZ[B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0017J\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0002J\f\u0010\u0017\u001a\u00020\u0015*\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u001a\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u000eH\u0002J\u0018\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\u0006\u0010 \u001a\u00020\u0016H\u0002J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010 \u001a\u00020\u0016H\u0002J \u0010\"\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u000eH\u0002J\u0018\u0010\"\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u001dH\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010 \u001a\u00020\u0016H\u0002J\u0012\u0010'\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0003J \u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u00020\u001aH\u0002J(\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u000e2\u0006\u00104\u001a\u00020\u000e2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0002J \u00109\u001a\u00020:2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00100\u001a\u00020\u001aH\u0002J\u0018\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010=\u001a\u00020>H\u0082@¢\u0006\u0002\u0010?J\u0010\u0010C\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\u001aH\u0002J\u0010\u0010D\u001a\u00020\u00152\u0006\u00100\u001a\u00020\u001aH\u0002J\u0010\u0010E\u001a\u00020F2\u0006\u00100\u001a\u00020\u001aH\u0002J\u0010\u0010G\u001a\u00020&2\u0006\u0010 \u001a\u00020\u0016H\u0002J\u0010\u0010H\u001a\u00020I2\u0006\u0010 \u001a\u00020\u0016H\u0002J\u0012\u0010J\u001a\u0004\u0018\u00010+2\u0006\u0010 \u001a\u00020\u0016H\u0002J\f\u0010K\u001a\u00020\u001a*\u00020\u0016H\u0002J\u0010\u0010N\u001a\u00020\u000e2\u0006\u0010O\u001a\u00020\u000eH\u0002J\u0010\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020&H\u0002J\u0010\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010@\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0018\u0010L\u001a\u00020.*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bL\u0010M¨\u0006\\"}, d2 = {"Lexpo/modules/filesystem/legacy/FileSystemLegacyModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "client", "Lokhttp3/OkHttpClient;", "dirPermissionsRequest", "Lexpo/modules/kotlin/Promise;", "taskHandlers", "", "", "Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$TaskHandler;", "moduleCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "checkIfFileExists", "", "Landroid/net/Uri;", "checkIfFileDirExists", "ensureDirExists", "dir", "Ljava/io/File;", "permissionsForPath", "Ljava/util/EnumSet;", "Lexpo/modules/interfaces/filesystem/Permission;", "path", "permissionsForUri", "uri", "permissionsForSAFUri", "ensurePermission", "permission", "errorMsg", "openAssetInputStream", "Ljava/io/InputStream;", "openResourceInputStream", "resourceName", "transformFilesFromSAF", "documentFile", "Landroidx/documentfile/provider/DocumentFile;", "outputDir", "copy", "", "contentUriFromFile", "file", "createUploadRequest", "Lokhttp3/Request;", ImagesContract.URL, "fileUriString", "options", "Lexpo/modules/filesystem/legacy/FileSystemUploadOptions;", "decorator", "Lexpo/modules/filesystem/legacy/RequestBodyDecorator;", "createRequestBody", "Lokhttp3/RequestBody;", "downloadResumableTask", "", "params", "Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$DownloadResumableTaskParams;", "(Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$DownloadResumableTaskParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "okHttpClient", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "md5", "forceDelete", "getFileSize", "", "getInputStream", "getOutputStream", "Ljava/io/OutputStream;", "getNearestSAFFile", "toFile", "isSAFUri", "(Landroid/net/Uri;)Z", "parseFileUri", "uriStr", "getInputStreamBytes", "", "inputStream", "translateHeaders", "Landroid/os/Bundle;", "headers", "Lokhttp3/Headers;", "DownloadResumableTaskParams", "TaskHandler", "DownloadTaskHandler", "ProgressResponseBody", "ProgressListener", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class FileSystemLegacyModule extends Module {
    private OkHttpClient client;
    private Promise dirPermissionsRequest;
    private final Map<String, TaskHandler> taskHandlers = new HashMap();
    private final CoroutineScope moduleCoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());

    /* compiled from: FileSystemLegacyModule.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$ProgressListener;", "", "update", "", "bytesRead", "", "contentLength", "done", "", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface ProgressListener {
        void update(long bytesRead, long contentLength, boolean done);
    }

    /* compiled from: FileSystemLegacyModule.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FileSystemUploadType.values().length];
            try {
                iArr[FileSystemUploadType.BINARY_CONTENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FileSystemUploadType.MULTIPART.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.AppContextLost();
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent2;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent3;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent4;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent5;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent6;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent7;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent8;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent9;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent10;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent4;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent11;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent5;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent12;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent13;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent14;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent15;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent6;
        FileSystemLegacyModule fileSystemLegacyModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (fileSystemLegacyModule.getClass() + ".ModuleDefinition"));
        try {
            final ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(fileSystemLegacyModule);
            moduleDefinitionBuilder.Name("ExponentFileSystem");
            ConstantComponentBuilder constantComponentBuilder = new ConstantComponentBuilder("documentDirectory");
            constantComponentBuilder.setGetter(new Function0<String>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$Constant$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    Context context;
                    context = FileSystemLegacyModule.this.getContext();
                    return Uri.fromFile(context.getFilesDir()) + "/";
                }
            });
            moduleDefinitionBuilder.getConstants().put("documentDirectory", constantComponentBuilder);
            ConstantComponentBuilder constantComponentBuilder2 = new ConstantComponentBuilder("cacheDirectory");
            constantComponentBuilder2.setGetter(new Function0<String>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$Constant$2
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    Context context;
                    context = FileSystemLegacyModule.this.getContext();
                    return Uri.fromFile(context.getCacheDir()) + "/";
                }
            });
            moduleDefinitionBuilder.getConstants().put("cacheDirectory", constantComponentBuilder2);
            ConstantComponentBuilder constantComponentBuilder3 = new ConstantComponentBuilder("bundleDirectory");
            constantComponentBuilder3.setGetter(new Function0<String>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$Constant$3
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "asset:///";
                }
            });
            moduleDefinitionBuilder.getConstants().put("bundleDirectory", constantComponentBuilder3);
            moduleDefinitionBuilder.Events("expo-file-system.downloadProgress", "expo-file-system.uploadProgress");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$OnCreate$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Context context;
                    Context context2;
                    try {
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        context = fileSystemLegacyModule2.getContext();
                        File filesDir = context.getFilesDir();
                        Intrinsics.checkNotNullExpressionValue(filesDir, "getFilesDir(...)");
                        fileSystemLegacyModule2.ensureDirExists(filesDir);
                        FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                        context2 = fileSystemLegacyModule3.getContext();
                        File cacheDir = context2.getCacheDir();
                        Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
                        fileSystemLegacyModule3.ensureDirExists(cacheDir);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            TypeConverterProvider converters = moduleDefinitionBuilder2.getConverters();
            AnyType[] anyTypeArr = new AnyType[2];
            AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType == null) {
                anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters);
            }
            anyTypeArr[0] = anyType;
            AnyType anyType2 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(InfoOptionsLegacy.class), false));
            if (anyType2 == null) {
                anyType2 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(InfoOptionsLegacy.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(InfoOptionsLegacy.class);
                    }
                }), converters);
            }
            anyTypeArr[1] = anyType2;
            Function1<Object[], Bundle> function1 = new Function1<Object[], Bundle>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$3
                /* JADX WARN: Removed duplicated region for block: B:37:0x0141 A[Catch: FileNotFoundException -> 0x0182, TryCatch #0 {FileNotFoundException -> 0x0182, blocks: (B:26:0x00fe, B:28:0x0104, B:33:0x0113, B:35:0x0119, B:37:0x0141, B:39:0x0166, B:41:0x017c, B:42:0x0181, B:43:0x0128, B:46:0x012f, B:47:0x0139), top: B:25:0x00fe }] */
                /* JADX WARN: Removed duplicated region for block: B:41:0x017c A[Catch: FileNotFoundException -> 0x0182, TryCatch #0 {FileNotFoundException -> 0x0182, blocks: (B:26:0x00fe, B:28:0x0104, B:33:0x0113, B:35:0x0119, B:37:0x0141, B:39:0x0166, B:41:0x017c, B:42:0x0181, B:43:0x0128, B:46:0x012f, B:47:0x0139), top: B:25:0x00fe }] */
                @Override // kotlin.jvm.functions.Function1
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final android.os.Bundle invoke(java.lang.Object[] r17) {
                    /*
                        Method dump skipped, instructions count: 399
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$3.invoke(java.lang.Object[]):java.lang.Object");
                }
            };
            if (!Intrinsics.areEqual(Bundle.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Bundle.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Bundle.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Bundle.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Bundle.class, String.class)) {
                                untypedAsyncFunctionComponent = new StringAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                            } else {
                                untypedAsyncFunctionComponent = new UntypedAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                            }
                        } else {
                            untypedAsyncFunctionComponent = new FloatAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                        }
                    } else {
                        untypedAsyncFunctionComponent = new DoubleAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                    }
                } else {
                    untypedAsyncFunctionComponent = new BoolAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                }
            } else {
                untypedAsyncFunctionComponent = new IntAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getInfoAsync", untypedAsyncFunctionComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            TypeConverterProvider converters2 = moduleDefinitionBuilder3.getConverters();
            AnyType[] anyTypeArr2 = new AnyType[2];
            AnyType anyType3 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType3 == null) {
                anyType3 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$4
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters2);
            }
            anyTypeArr2[0] = anyType3;
            AnyType anyType4 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(ReadingOptions.class), false));
            if (anyType4 == null) {
                anyType4 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(ReadingOptions.class);
                    }
                }), converters2);
            }
            anyTypeArr2[1] = anyType4;
            moduleDefinitionBuilder3.getAsyncFunctions().put("readAsStringAsync", new UntypedAsyncFunctionComponent("readAsStringAsync", anyTypeArr2, new Function1<Object[], String>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$6
                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] objArr) {
                    boolean isSAFUri;
                    Context context;
                    InputStream openResourceInputStream;
                    InputStream openAssetInputStream;
                    File file;
                    InputStream inputStream;
                    byte[] inputStreamBytes;
                    String encodeToString;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    ReadingOptions readingOptions = (ReadingOptions) objArr[1];
                    String str = (String) obj;
                    Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(str));
                    FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemLegacyModule2.ensurePermission(parse, Permission.READ);
                    if (readingOptions.getEncoding() == EncodingType.BASE64) {
                        inputStream = FileSystemLegacyModule.this.getInputStream(parse);
                        InputStream inputStream2 = inputStream;
                        try {
                            InputStream inputStream3 = inputStream2;
                            if (readingOptions.getLength() == null || readingOptions.getPosition() == null) {
                                inputStreamBytes = FileSystemLegacyModule.this.getInputStreamBytes(inputStream3);
                                encodeToString = Base64.encodeToString(inputStreamBytes, 2);
                            } else {
                                byte[] bArr = new byte[readingOptions.getLength().intValue()];
                                inputStream3.skip(readingOptions.getPosition().intValue());
                                encodeToString = Base64.encodeToString(bArr, 0, inputStream3.read(bArr, 0, readingOptions.getLength().intValue()), 2);
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(inputStream2, null);
                            return encodeToString;
                        } finally {
                        }
                    } else {
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file = FileSystemLegacyModule.this.toFile(parse);
                            return IOUtils.toString(new FileInputStream(file));
                        }
                        if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
                            openAssetInputStream = FileSystemLegacyModule.this.openAssetInputStream(parse);
                            return IOUtils.toString(openAssetInputStream);
                        }
                        if (parse.getScheme() == null) {
                            openResourceInputStream = FileSystemLegacyModule.this.openResourceInputStream(str);
                            return IOUtils.toString(openResourceInputStream);
                        }
                        isSAFUri = FileSystemLegacyModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            context = FileSystemLegacyModule.this.getContext();
                            return IOUtils.toString(context.getContentResolver().openInputStream(parse));
                        }
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            TypeConverterProvider converters3 = moduleDefinitionBuilder4.getConverters();
            AnyType[] anyTypeArr3 = new AnyType[3];
            AnyType anyType5 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType5 == null) {
                anyType5 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$7
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters3);
            }
            anyTypeArr3[0] = anyType5;
            AnyType anyType6 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType6 == null) {
                anyType6 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$8
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters3);
            }
            anyTypeArr3[1] = anyType6;
            AnyType anyType7 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(WritingOptions.class), false));
            if (anyType7 == null) {
                anyType7 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(WritingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$9
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(WritingOptions.class);
                    }
                }), converters3);
            }
            anyTypeArr3[2] = anyType7;
            Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$10
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    OutputStream outputStream;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    WritingOptions writingOptions = (WritingOptions) objArr[2];
                    String str = (String) obj2;
                    Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) obj));
                    FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemLegacyModule2.ensurePermission(parse, Permission.WRITE);
                    EncodingType encoding = writingOptions.getEncoding();
                    outputStream = FileSystemLegacyModule.this.getOutputStream(parse);
                    OutputStreamWriter outputStreamWriter = outputStream;
                    try {
                        OutputStream outputStream2 = outputStreamWriter;
                        if (encoding == EncodingType.BASE64) {
                            outputStream2.write(Base64.decode(str, 0));
                        } else {
                            outputStreamWriter = new OutputStreamWriter(outputStream2);
                            try {
                                outputStreamWriter.write(str);
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(outputStreamWriter, null);
                            } finally {
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(outputStreamWriter, null);
                        return Unit.INSTANCE;
                    } finally {
                    }
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                untypedAsyncFunctionComponent2 = new StringAsyncFunctionComponent("writeAsStringAsync", anyTypeArr3, function12);
                            } else {
                                untypedAsyncFunctionComponent2 = new UntypedAsyncFunctionComponent("writeAsStringAsync", anyTypeArr3, function12);
                            }
                        } else {
                            untypedAsyncFunctionComponent2 = new FloatAsyncFunctionComponent("writeAsStringAsync", anyTypeArr3, function12);
                        }
                    } else {
                        untypedAsyncFunctionComponent2 = new DoubleAsyncFunctionComponent("writeAsStringAsync", anyTypeArr3, function12);
                    }
                } else {
                    untypedAsyncFunctionComponent2 = new BoolAsyncFunctionComponent("writeAsStringAsync", anyTypeArr3, function12);
                }
            } else {
                untypedAsyncFunctionComponent2 = new IntAsyncFunctionComponent("writeAsStringAsync", anyTypeArr3, function12);
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("writeAsStringAsync", untypedAsyncFunctionComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder5 = moduleDefinitionBuilder;
            TypeConverterProvider converters4 = moduleDefinitionBuilder5.getConverters();
            AnyType[] anyTypeArr4 = new AnyType[2];
            AnyType anyType8 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType8 == null) {
                anyType8 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$11
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters4);
            }
            anyTypeArr4[0] = anyType8;
            AnyType anyType9 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(DeletingOptions.class), false));
            if (anyType9 == null) {
                anyType9 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DeletingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$12
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(DeletingOptions.class);
                    }
                }), converters4);
            }
            anyTypeArr4[1] = anyType9;
            Function1<Object[], Unit> function13 = new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$13
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    boolean isSAFUri;
                    DocumentFile nearestSAFFile;
                    File file;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    DeletingOptions deletingOptions = (DeletingOptions) objArr[1];
                    Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) obj));
                    Uri withAppendedPath = Uri.withAppendedPath(parse, "..");
                    FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    Intrinsics.checkNotNull(withAppendedPath);
                    fileSystemLegacyModule2.ensurePermission(withAppendedPath, Permission.WRITE, "Location '" + parse + "' isn't deletable.");
                    if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                        FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        file = fileSystemLegacyModule3.toFile(parse);
                        if (file.exists()) {
                            if (Build.VERSION.SDK_INT < 26) {
                                FileSystemLegacyModule.this.forceDelete(file);
                            } else {
                                FileUtils.forceDelete(file);
                            }
                        } else if (!deletingOptions.getIdempotent()) {
                            throw new FileSystemFileNotFoundException(parse);
                        }
                    } else {
                        FileSystemLegacyModule fileSystemLegacyModule4 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        isSAFUri = fileSystemLegacyModule4.isSAFUri(parse);
                        if (isSAFUri) {
                            nearestSAFFile = FileSystemLegacyModule.this.getNearestSAFFile(parse);
                            if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                nearestSAFFile.delete();
                            } else if (!deletingOptions.getIdempotent()) {
                                throw new FileSystemFileNotFoundException(parse);
                            }
                        } else {
                            throw new IOException("Unsupported scheme for location '" + parse + "'.");
                        }
                    }
                    return Unit.INSTANCE;
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                untypedAsyncFunctionComponent3 = new StringAsyncFunctionComponent("deleteAsync", anyTypeArr4, function13);
                            } else {
                                untypedAsyncFunctionComponent3 = new UntypedAsyncFunctionComponent("deleteAsync", anyTypeArr4, function13);
                            }
                        } else {
                            untypedAsyncFunctionComponent3 = new FloatAsyncFunctionComponent("deleteAsync", anyTypeArr4, function13);
                        }
                    } else {
                        untypedAsyncFunctionComponent3 = new DoubleAsyncFunctionComponent("deleteAsync", anyTypeArr4, function13);
                    }
                } else {
                    untypedAsyncFunctionComponent3 = new BoolAsyncFunctionComponent("deleteAsync", anyTypeArr4, function13);
                }
            } else {
                untypedAsyncFunctionComponent3 = new IntAsyncFunctionComponent("deleteAsync", anyTypeArr4, function13);
            }
            moduleDefinitionBuilder5.getAsyncFunctions().put("deleteAsync", untypedAsyncFunctionComponent3);
            ModuleDefinitionBuilder moduleDefinitionBuilder6 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(RelocatingOptions.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("moveAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$14
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        boolean isSAFUri;
                        DocumentFile nearestSAFFile;
                        File file;
                        File file2;
                        File file3;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        RelocatingOptions relocatingOptions = (RelocatingOptions) promise;
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(relocatingOptions.getFrom()));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Uri withAppendedPath = Uri.withAppendedPath(parse, "..");
                        Intrinsics.checkNotNullExpressionValue(withAppendedPath, "withAppendedPath(...)");
                        fileSystemLegacyModule2.ensurePermission(withAppendedPath, Permission.WRITE, "Location '" + parse + "' isn't movable.");
                        Uri parse2 = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(relocatingOptions.getTo()));
                        FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse2);
                        fileSystemLegacyModule3.ensurePermission(parse2, Permission.WRITE);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            FileSystemLegacyModule fileSystemLegacyModule4 = FileSystemLegacyModule.this;
                            Intrinsics.checkNotNull(parse);
                            file2 = fileSystemLegacyModule4.toFile(parse);
                            file3 = FileSystemLegacyModule.this.toFile(parse2);
                            if (!file2.renameTo(file3)) {
                                throw new FileSystemCannotMoveFileException(parse, parse2);
                            }
                            return;
                        }
                        FileSystemLegacyModule fileSystemLegacyModule5 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        isSAFUri = fileSystemLegacyModule5.isSAFUri(parse);
                        if (isSAFUri) {
                            nearestSAFFile = FileSystemLegacyModule.this.getNearestSAFFile(parse);
                            if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                file = FileSystemLegacyModule.this.toFile(parse2);
                                FileSystemLegacyModule.this.transformFilesFromSAF(nearestSAFFile, file, false);
                                return;
                            }
                            throw new FileSystemCannotMoveFileException(parse, parse2);
                        }
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters5 = moduleDefinitionBuilder6.getConverters();
                AnyType[] anyTypeArr5 = new AnyType[1];
                AnyType anyType10 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(RelocatingOptions.class), false));
                if (anyType10 == null) {
                    anyType10 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RelocatingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$15
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(RelocatingOptions.class);
                        }
                    }), converters5);
                }
                anyTypeArr5[0] = anyType10;
                Function1<Object[], Unit> function14 = new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$16
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        boolean isSAFUri;
                        DocumentFile nearestSAFFile;
                        File file;
                        File file2;
                        File file3;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        RelocatingOptions relocatingOptions = (RelocatingOptions) objArr[0];
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(relocatingOptions.getFrom()));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Uri withAppendedPath = Uri.withAppendedPath(parse, "..");
                        Intrinsics.checkNotNullExpressionValue(withAppendedPath, "withAppendedPath(...)");
                        fileSystemLegacyModule2.ensurePermission(withAppendedPath, Permission.WRITE, "Location '" + parse + "' isn't movable.");
                        Uri parse2 = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(relocatingOptions.getTo()));
                        FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse2);
                        fileSystemLegacyModule3.ensurePermission(parse2, Permission.WRITE);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            FileSystemLegacyModule fileSystemLegacyModule4 = FileSystemLegacyModule.this;
                            Intrinsics.checkNotNull(parse);
                            file2 = fileSystemLegacyModule4.toFile(parse);
                            file3 = FileSystemLegacyModule.this.toFile(parse2);
                            if (!file2.renameTo(file3)) {
                                throw new FileSystemCannotMoveFileException(parse, parse2);
                            }
                        } else {
                            FileSystemLegacyModule fileSystemLegacyModule5 = FileSystemLegacyModule.this;
                            Intrinsics.checkNotNull(parse);
                            isSAFUri = fileSystemLegacyModule5.isSAFUri(parse);
                            if (isSAFUri) {
                                nearestSAFFile = FileSystemLegacyModule.this.getNearestSAFFile(parse);
                                if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                    file = FileSystemLegacyModule.this.toFile(parse2);
                                    FileSystemLegacyModule.this.transformFilesFromSAF(nearestSAFFile, file, false);
                                } else {
                                    throw new FileSystemCannotMoveFileException(parse, parse2);
                                }
                            } else {
                                throw new IOException("Unsupported scheme for location '" + parse + "'.");
                            }
                        }
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    untypedAsyncFunctionComponent4 = new StringAsyncFunctionComponent("moveAsync", anyTypeArr5, function14);
                                } else {
                                    untypedAsyncFunctionComponent4 = new UntypedAsyncFunctionComponent("moveAsync", anyTypeArr5, function14);
                                }
                            } else {
                                untypedAsyncFunctionComponent4 = new FloatAsyncFunctionComponent("moveAsync", anyTypeArr5, function14);
                            }
                        } else {
                            untypedAsyncFunctionComponent4 = new DoubleAsyncFunctionComponent("moveAsync", anyTypeArr5, function14);
                        }
                    } else {
                        untypedAsyncFunctionComponent4 = new BoolAsyncFunctionComponent("moveAsync", anyTypeArr5, function14);
                    }
                } else {
                    untypedAsyncFunctionComponent4 = new IntAsyncFunctionComponent("moveAsync", anyTypeArr5, function14);
                }
                asyncFunctionWithPromiseComponent = untypedAsyncFunctionComponent4;
            }
            moduleDefinitionBuilder6.getAsyncFunctions().put("moveAsync", asyncFunctionWithPromiseComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder7 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(RelocatingOptions.class, Promise.class)) {
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("copyAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$17
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        boolean isSAFUri;
                        InputStream openResourceInputStream;
                        File file;
                        InputStream openAssetInputStream;
                        File file2;
                        Context context;
                        File file3;
                        DocumentFile nearestSAFFile;
                        File file4;
                        File file5;
                        File file6;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        RelocatingOptions relocatingOptions = (RelocatingOptions) promise;
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(relocatingOptions.getFrom()));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemLegacyModule2.ensurePermission(parse, Permission.READ, "Location '" + parse + "' isn't readable.");
                        Uri parse2 = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(relocatingOptions.getTo()));
                        FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse2);
                        fileSystemLegacyModule3.ensurePermission(parse2, Permission.WRITE);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file5 = FileSystemLegacyModule.this.toFile(parse);
                            file6 = FileSystemLegacyModule.this.toFile(parse2);
                            if (file5.isDirectory()) {
                                if (Build.VERSION.SDK_INT >= 26) {
                                    FileUtils.copyDirectory(file5, file6);
                                    return;
                                } else {
                                    FilesKt.copyRecursively$default(file5, file6, true, null, 4, null);
                                    return;
                                }
                            }
                            if (Build.VERSION.SDK_INT >= 26) {
                                FileUtils.copyFile(file5, file6);
                                return;
                            } else {
                                FilesKt.copyTo$default(file5, file6, true, 0, 4, null);
                                return;
                            }
                        }
                        isSAFUri = FileSystemLegacyModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            nearestSAFFile = FileSystemLegacyModule.this.getNearestSAFFile(parse);
                            if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                file4 = FileSystemLegacyModule.this.toFile(parse2);
                                FileSystemLegacyModule.this.transformFilesFromSAF(nearestSAFFile, file4, true);
                                return;
                            }
                            throw new FileSystemCopyFailedException(parse);
                        }
                        if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME)) {
                            context = FileSystemLegacyModule.this.getContext();
                            InputStream openInputStream = context.getContentResolver().openInputStream(parse);
                            file3 = FileSystemLegacyModule.this.toFile(parse2);
                            IOUtils.copy(openInputStream, new FileOutputStream(file3));
                            return;
                        }
                        if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
                            openAssetInputStream = FileSystemLegacyModule.this.openAssetInputStream(parse);
                            file2 = FileSystemLegacyModule.this.toFile(parse2);
                            IOUtils.copy(openAssetInputStream, new FileOutputStream(file2));
                        } else {
                            if (parse.getScheme() == null) {
                                openResourceInputStream = FileSystemLegacyModule.this.openResourceInputStream(relocatingOptions.getFrom());
                                file = FileSystemLegacyModule.this.toFile(parse2);
                                IOUtils.copy(openResourceInputStream, new FileOutputStream(file));
                                return;
                            }
                            throw new IOException("Unsupported scheme for location '" + parse + "'.");
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters6 = moduleDefinitionBuilder7.getConverters();
                AnyType[] anyTypeArr6 = new AnyType[1];
                AnyType anyType11 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(RelocatingOptions.class), false));
                if (anyType11 == null) {
                    anyType11 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RelocatingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$18
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(RelocatingOptions.class);
                        }
                    }), converters6);
                }
                anyTypeArr6[0] = anyType11;
                Function1<Object[], Object> function15 = new Function1<Object[], Object>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object[] objArr) {
                        boolean isSAFUri;
                        InputStream openResourceInputStream;
                        File file;
                        InputStream openAssetInputStream;
                        File file2;
                        Context context;
                        File file3;
                        DocumentFile nearestSAFFile;
                        File file4;
                        File file5;
                        File file6;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        RelocatingOptions relocatingOptions = (RelocatingOptions) objArr[0];
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(relocatingOptions.getFrom()));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemLegacyModule2.ensurePermission(parse, Permission.READ, "Location '" + parse + "' isn't readable.");
                        Uri parse2 = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(relocatingOptions.getTo()));
                        FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse2);
                        fileSystemLegacyModule3.ensurePermission(parse2, Permission.WRITE);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file5 = FileSystemLegacyModule.this.toFile(parse);
                            file6 = FileSystemLegacyModule.this.toFile(parse2);
                            if (file5.isDirectory()) {
                                if (Build.VERSION.SDK_INT >= 26) {
                                    FileUtils.copyDirectory(file5, file6);
                                    return Unit.INSTANCE;
                                }
                                return Boolean.valueOf(FilesKt.copyRecursively$default(file5, file6, true, null, 4, null));
                            }
                            if (Build.VERSION.SDK_INT >= 26) {
                                FileUtils.copyFile(file5, file6);
                                return Unit.INSTANCE;
                            }
                            return FilesKt.copyTo$default(file5, file6, true, 0, 4, null);
                        }
                        isSAFUri = FileSystemLegacyModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            nearestSAFFile = FileSystemLegacyModule.this.getNearestSAFFile(parse);
                            if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                file4 = FileSystemLegacyModule.this.toFile(parse2);
                                FileSystemLegacyModule.this.transformFilesFromSAF(nearestSAFFile, file4, true);
                                return Unit.INSTANCE;
                            }
                            throw new FileSystemCopyFailedException(parse);
                        }
                        if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME)) {
                            context = FileSystemLegacyModule.this.getContext();
                            InputStream openInputStream = context.getContentResolver().openInputStream(parse);
                            file3 = FileSystemLegacyModule.this.toFile(parse2);
                            return Integer.valueOf(IOUtils.copy(openInputStream, new FileOutputStream(file3)));
                        }
                        if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
                            openAssetInputStream = FileSystemLegacyModule.this.openAssetInputStream(parse);
                            file2 = FileSystemLegacyModule.this.toFile(parse2);
                            return Integer.valueOf(IOUtils.copy(openAssetInputStream, new FileOutputStream(file2)));
                        }
                        if (parse.getScheme() == null) {
                            openResourceInputStream = FileSystemLegacyModule.this.openResourceInputStream(relocatingOptions.getFrom());
                            file = FileSystemLegacyModule.this.toFile(parse2);
                            return Integer.valueOf(IOUtils.copy(openResourceInputStream, new FileOutputStream(file)));
                        }
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }
                };
                if (!Intrinsics.areEqual(Object.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Object.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Object.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Object.class, String.class)) {
                                    untypedAsyncFunctionComponent5 = new StringAsyncFunctionComponent("copyAsync", anyTypeArr6, function15);
                                } else {
                                    untypedAsyncFunctionComponent5 = new UntypedAsyncFunctionComponent("copyAsync", anyTypeArr6, function15);
                                }
                            } else {
                                untypedAsyncFunctionComponent5 = new FloatAsyncFunctionComponent("copyAsync", anyTypeArr6, function15);
                            }
                        } else {
                            untypedAsyncFunctionComponent5 = new DoubleAsyncFunctionComponent("copyAsync", anyTypeArr6, function15);
                        }
                    } else {
                        untypedAsyncFunctionComponent5 = new BoolAsyncFunctionComponent("copyAsync", anyTypeArr6, function15);
                    }
                } else {
                    untypedAsyncFunctionComponent5 = new IntAsyncFunctionComponent("copyAsync", anyTypeArr6, function15);
                }
                asyncFunctionWithPromiseComponent2 = untypedAsyncFunctionComponent5;
            }
            moduleDefinitionBuilder7.getAsyncFunctions().put("copyAsync", asyncFunctionWithPromiseComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder8 = moduleDefinitionBuilder;
            TypeConverterProvider converters7 = moduleDefinitionBuilder8.getConverters();
            AnyType[] anyTypeArr7 = new AnyType[2];
            AnyType anyType12 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType12 == null) {
                anyType12 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$20
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters7);
            }
            anyTypeArr7[0] = anyType12;
            AnyType anyType13 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(MakeDirectoryOptions.class), false));
            if (anyType13 == null) {
                anyType13 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(MakeDirectoryOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$21
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(MakeDirectoryOptions.class);
                    }
                }), converters7);
            }
            anyTypeArr7[1] = anyType13;
            Function1<Object[], Unit> function16 = new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$22
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    File file;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    MakeDirectoryOptions makeDirectoryOptions = (MakeDirectoryOptions) objArr[1];
                    Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) obj));
                    FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemLegacyModule2.ensurePermission(parse, Permission.WRITE);
                    if (!Intrinsics.areEqual(parse.getScheme(), "file")) {
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }
                    file = FileSystemLegacyModule.this.toFile(parse);
                    boolean isDirectory = file.isDirectory();
                    boolean intermediates = makeDirectoryOptions.getIntermediates();
                    if (!(intermediates ? file.mkdirs() : file.mkdir()) && (!intermediates || !isDirectory)) {
                        throw new FileSystemCannotCreateDirectoryException(parse);
                    }
                    return Unit.INSTANCE;
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                untypedAsyncFunctionComponent6 = new StringAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr7, function16);
                            } else {
                                untypedAsyncFunctionComponent6 = new UntypedAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr7, function16);
                            }
                        } else {
                            untypedAsyncFunctionComponent6 = new FloatAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr7, function16);
                        }
                    } else {
                        untypedAsyncFunctionComponent6 = new DoubleAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr7, function16);
                    }
                } else {
                    untypedAsyncFunctionComponent6 = new BoolAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr7, function16);
                }
            } else {
                untypedAsyncFunctionComponent6 = new IntAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr7, function16);
            }
            moduleDefinitionBuilder8.getAsyncFunctions().put("makeDirectoryAsync", untypedAsyncFunctionComponent6);
            ModuleDefinitionBuilder moduleDefinitionBuilder9 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("readDirectoryAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$23
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        boolean isSAFUri;
                        File file;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) promise));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemLegacyModule2.ensurePermission(parse, Permission.READ);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file = FileSystemLegacyModule.this.toFile(parse);
                            File[] listFiles = file.listFiles();
                            if (listFiles == null) {
                                throw new FileSystemCannotReadDirectoryException(parse);
                            }
                            ArrayList arrayList = new ArrayList(listFiles.length);
                            int length = listFiles.length;
                            for (int i = 0; i < length; i++) {
                                File file2 = listFiles[i];
                                arrayList.add(file2 != null ? file2.getName() : null);
                            }
                            return;
                        }
                        isSAFUri = FileSystemLegacyModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            throw new FileSystemUnsupportedSchemeException();
                        }
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters8 = moduleDefinitionBuilder9.getConverters();
                AnyType[] anyTypeArr8 = new AnyType[1];
                AnyType anyType14 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
                if (anyType14 == null) {
                    anyType14 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$24
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.nullableTypeOf(String.class);
                        }
                    }), converters8);
                }
                anyTypeArr8[0] = anyType14;
                Function1<Object[], List<? extends String>> function17 = new Function1<Object[], List<? extends String>>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$25
                    @Override // kotlin.jvm.functions.Function1
                    public final List<? extends String> invoke(Object[] objArr) {
                        boolean isSAFUri;
                        File file;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) objArr[0]));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemLegacyModule2.ensurePermission(parse, Permission.READ);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file = FileSystemLegacyModule.this.toFile(parse);
                            File[] listFiles = file.listFiles();
                            if (listFiles == null) {
                                throw new FileSystemCannotReadDirectoryException(parse);
                            }
                            ArrayList arrayList = new ArrayList(listFiles.length);
                            int length = listFiles.length;
                            for (int i = 0; i < length; i++) {
                                File file2 = listFiles[i];
                                arrayList.add(file2 != null ? file2.getName() : null);
                            }
                            return arrayList;
                        }
                        isSAFUri = FileSystemLegacyModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            throw new FileSystemUnsupportedSchemeException();
                        }
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }
                };
                if (!Intrinsics.areEqual(List.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(List.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(List.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(List.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(List.class, String.class)) {
                                    untypedAsyncFunctionComponent7 = new StringAsyncFunctionComponent("readDirectoryAsync", anyTypeArr8, function17);
                                } else {
                                    untypedAsyncFunctionComponent7 = new UntypedAsyncFunctionComponent("readDirectoryAsync", anyTypeArr8, function17);
                                }
                            } else {
                                untypedAsyncFunctionComponent7 = new FloatAsyncFunctionComponent("readDirectoryAsync", anyTypeArr8, function17);
                            }
                        } else {
                            untypedAsyncFunctionComponent7 = new DoubleAsyncFunctionComponent("readDirectoryAsync", anyTypeArr8, function17);
                        }
                    } else {
                        untypedAsyncFunctionComponent7 = new BoolAsyncFunctionComponent("readDirectoryAsync", anyTypeArr8, function17);
                    }
                } else {
                    untypedAsyncFunctionComponent7 = new IntAsyncFunctionComponent("readDirectoryAsync", anyTypeArr8, function17);
                }
                asyncFunctionWithPromiseComponent3 = untypedAsyncFunctionComponent7;
            }
            moduleDefinitionBuilder9.getAsyncFunctions().put("readDirectoryAsync", asyncFunctionWithPromiseComponent3);
            ModuleDefinitionBuilder moduleDefinitionBuilder10 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr9 = new AnyType[0];
            Function1<Object[], Double> function18 = new Function1<Object[], Double>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$26
                @Override // kotlin.jvm.functions.Function1
                public final Double invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
                    return Double.valueOf(RangesKt.coerceAtMost(BigInteger.valueOf(statFs.getBlockCountLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).doubleValue(), Math.pow(2.0d, 53.0d) - 1));
                }
            };
            if (!Intrinsics.areEqual(Double.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Double.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Double.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Double.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Double.class, String.class)) {
                                untypedAsyncFunctionComponent8 = new StringAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr9, function18);
                            } else {
                                untypedAsyncFunctionComponent8 = new UntypedAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr9, function18);
                            }
                        } else {
                            untypedAsyncFunctionComponent8 = new FloatAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr9, function18);
                        }
                    } else {
                        untypedAsyncFunctionComponent8 = new DoubleAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr9, function18);
                    }
                } else {
                    untypedAsyncFunctionComponent8 = new BoolAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr9, function18);
                }
            } else {
                untypedAsyncFunctionComponent8 = new IntAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr9, function18);
            }
            moduleDefinitionBuilder10.getAsyncFunctions().put("getTotalDiskCapacityAsync", untypedAsyncFunctionComponent8);
            ModuleDefinitionBuilder moduleDefinitionBuilder11 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr10 = new AnyType[0];
            Function1<Object[], Double> function19 = new Function1<Object[], Double>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$27
                @Override // kotlin.jvm.functions.Function1
                public final Double invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
                    return Double.valueOf(RangesKt.coerceAtMost(BigInteger.valueOf(statFs.getAvailableBlocksLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).doubleValue(), Math.pow(2.0d, 53.0d) - 1));
                }
            };
            if (!Intrinsics.areEqual(Double.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Double.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Double.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Double.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Double.class, String.class)) {
                                untypedAsyncFunctionComponent9 = new StringAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr10, function19);
                            } else {
                                untypedAsyncFunctionComponent9 = new UntypedAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr10, function19);
                            }
                        } else {
                            untypedAsyncFunctionComponent9 = new FloatAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr10, function19);
                        }
                    } else {
                        untypedAsyncFunctionComponent9 = new DoubleAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr10, function19);
                    }
                } else {
                    untypedAsyncFunctionComponent9 = new BoolAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr10, function19);
                }
            } else {
                untypedAsyncFunctionComponent9 = new IntAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr10, function19);
            }
            moduleDefinitionBuilder11.getAsyncFunctions().put("getFreeDiskStorageAsync", untypedAsyncFunctionComponent9);
            ModuleDefinitionBuilder moduleDefinitionBuilder12 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent4 = new AsyncFunctionWithPromiseComponent("getContentUriAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$28
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        File file;
                        Uri contentUriFromFile;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(str));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemLegacyModule2.ensurePermission(parse, Permission.WRITE);
                        FileSystemLegacyModule.this.ensurePermission(parse, Permission.READ);
                        FileSystemLegacyModule.this.checkIfFileDirExists(parse);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file = FileSystemLegacyModule.this.toFile(parse);
                            contentUriFromFile = FileSystemLegacyModule.this.contentUriFromFile(file);
                            contentUriFromFile.toString();
                            return;
                        }
                        throw new FileSystemUnreadableDirectoryException(str);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters9 = moduleDefinitionBuilder12.getConverters();
                AnyType[] anyTypeArr11 = new AnyType[1];
                AnyType anyType15 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
                if (anyType15 == null) {
                    anyType15 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$29
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(String.class);
                        }
                    }), converters9);
                }
                anyTypeArr11[0] = anyType15;
                Function1<Object[], String> function110 = new Function1<Object[], String>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$30
                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(Object[] objArr) {
                        File file;
                        Uri contentUriFromFile;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        String str = (String) objArr[0];
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(str));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemLegacyModule2.ensurePermission(parse, Permission.WRITE);
                        FileSystemLegacyModule.this.ensurePermission(parse, Permission.READ);
                        FileSystemLegacyModule.this.checkIfFileDirExists(parse);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file = FileSystemLegacyModule.this.toFile(parse);
                            contentUriFromFile = FileSystemLegacyModule.this.contentUriFromFile(file);
                            return contentUriFromFile.toString();
                        }
                        throw new FileSystemUnreadableDirectoryException(str);
                    }
                };
                if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(String.class, String.class)) {
                                    untypedAsyncFunctionComponent10 = new StringAsyncFunctionComponent("getContentUriAsync", anyTypeArr11, function110);
                                } else {
                                    untypedAsyncFunctionComponent10 = new UntypedAsyncFunctionComponent("getContentUriAsync", anyTypeArr11, function110);
                                }
                            } else {
                                untypedAsyncFunctionComponent10 = new FloatAsyncFunctionComponent("getContentUriAsync", anyTypeArr11, function110);
                            }
                        } else {
                            untypedAsyncFunctionComponent10 = new DoubleAsyncFunctionComponent("getContentUriAsync", anyTypeArr11, function110);
                        }
                    } else {
                        untypedAsyncFunctionComponent10 = new BoolAsyncFunctionComponent("getContentUriAsync", anyTypeArr11, function110);
                    }
                } else {
                    untypedAsyncFunctionComponent10 = new IntAsyncFunctionComponent("getContentUriAsync", anyTypeArr11, function110);
                }
                asyncFunctionWithPromiseComponent4 = untypedAsyncFunctionComponent10;
            }
            moduleDefinitionBuilder12.getAsyncFunctions().put("getContentUriAsync", asyncFunctionWithPromiseComponent4);
            ModuleDefinitionBuilder moduleDefinitionBuilder13 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent5 = new AsyncFunctionWithPromiseComponent("readSAFDirectoryAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$31
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        boolean isSAFUri;
                        Context context;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) promise));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemLegacyModule2.ensurePermission(parse, Permission.READ);
                        isSAFUri = FileSystemLegacyModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            context = FileSystemLegacyModule.this.getContext();
                            DocumentFile fromTreeUri = DocumentFile.fromTreeUri(context, parse);
                            if (fromTreeUri == null || !fromTreeUri.exists() || !fromTreeUri.isDirectory()) {
                                throw new FileSystemCannotReadDirectoryException(parse);
                            }
                            DocumentFile[] listFiles = fromTreeUri.listFiles();
                            Intrinsics.checkNotNullExpressionValue(listFiles, "listFiles(...)");
                            ArrayList arrayList = new ArrayList(listFiles.length);
                            for (DocumentFile documentFile : listFiles) {
                                arrayList.add(documentFile.getUri().toString());
                            }
                            return;
                        }
                        throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI. Try using FileSystem.readDirectoryAsync instead.");
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters10 = moduleDefinitionBuilder13.getConverters();
                AnyType[] anyTypeArr12 = new AnyType[1];
                AnyType anyType16 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
                if (anyType16 == null) {
                    anyType16 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$32
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(String.class);
                        }
                    }), converters10);
                }
                anyTypeArr12[0] = anyType16;
                Function1<Object[], List<? extends String>> function111 = new Function1<Object[], List<? extends String>>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$33
                    @Override // kotlin.jvm.functions.Function1
                    public final List<? extends String> invoke(Object[] objArr) {
                        boolean isSAFUri;
                        Context context;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) objArr[0]));
                        FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemLegacyModule2.ensurePermission(parse, Permission.READ);
                        isSAFUri = FileSystemLegacyModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            context = FileSystemLegacyModule.this.getContext();
                            DocumentFile fromTreeUri = DocumentFile.fromTreeUri(context, parse);
                            if (fromTreeUri == null || !fromTreeUri.exists() || !fromTreeUri.isDirectory()) {
                                throw new FileSystemCannotReadDirectoryException(parse);
                            }
                            DocumentFile[] listFiles = fromTreeUri.listFiles();
                            Intrinsics.checkNotNullExpressionValue(listFiles, "listFiles(...)");
                            ArrayList arrayList = new ArrayList(listFiles.length);
                            for (DocumentFile documentFile : listFiles) {
                                arrayList.add(documentFile.getUri().toString());
                            }
                            return arrayList;
                        }
                        throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI. Try using FileSystem.readDirectoryAsync instead.");
                    }
                };
                if (!Intrinsics.areEqual(List.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(List.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(List.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(List.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(List.class, String.class)) {
                                    untypedAsyncFunctionComponent11 = new StringAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr12, function111);
                                } else {
                                    untypedAsyncFunctionComponent11 = new UntypedAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr12, function111);
                                }
                            } else {
                                untypedAsyncFunctionComponent11 = new FloatAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr12, function111);
                            }
                        } else {
                            untypedAsyncFunctionComponent11 = new DoubleAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr12, function111);
                        }
                    } else {
                        untypedAsyncFunctionComponent11 = new BoolAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr12, function111);
                    }
                } else {
                    untypedAsyncFunctionComponent11 = new IntAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr12, function111);
                }
                asyncFunctionWithPromiseComponent5 = untypedAsyncFunctionComponent11;
            }
            moduleDefinitionBuilder13.getAsyncFunctions().put("readSAFDirectoryAsync", asyncFunctionWithPromiseComponent5);
            ModuleDefinitionBuilder moduleDefinitionBuilder14 = moduleDefinitionBuilder;
            TypeConverterProvider converters11 = moduleDefinitionBuilder14.getConverters();
            AnyType[] anyTypeArr13 = new AnyType[2];
            AnyType anyType17 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType17 == null) {
                anyType17 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$34
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters11);
            }
            anyTypeArr13[0] = anyType17;
            AnyType anyType18 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType18 == null) {
                anyType18 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$35
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters11);
            }
            anyTypeArr13[1] = anyType18;
            Function1<Object[], String> function112 = new Function1<Object[], String>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$36
                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] objArr) {
                    boolean isSAFUri;
                    DocumentFile nearestSAFFile;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    String str = (String) objArr[1];
                    Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) obj));
                    FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemLegacyModule2.ensurePermission(parse, Permission.WRITE);
                    isSAFUri = FileSystemLegacyModule.this.isSAFUri(parse);
                    if (isSAFUri) {
                        nearestSAFFile = FileSystemLegacyModule.this.getNearestSAFFile(parse);
                        if (nearestSAFFile != null && !nearestSAFFile.isDirectory()) {
                            throw new FileSystemCannotCreateDirectoryException(parse);
                        }
                        DocumentFile createDirectory = nearestSAFFile != null ? nearestSAFFile.createDirectory(str) : null;
                        if (createDirectory == null) {
                            throw new FileSystemCannotCreateDirectoryException(null);
                        }
                        return createDirectory.getUri().toString();
                    }
                    throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI. Try using FileSystem.makeDirectoryAsync instead.");
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                untypedAsyncFunctionComponent12 = new StringAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr13, function112);
                            } else {
                                untypedAsyncFunctionComponent12 = new UntypedAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr13, function112);
                            }
                        } else {
                            untypedAsyncFunctionComponent12 = new FloatAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr13, function112);
                        }
                    } else {
                        untypedAsyncFunctionComponent12 = new DoubleAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr13, function112);
                    }
                } else {
                    untypedAsyncFunctionComponent12 = new BoolAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr13, function112);
                }
            } else {
                untypedAsyncFunctionComponent12 = new IntAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr13, function112);
            }
            moduleDefinitionBuilder14.getAsyncFunctions().put("makeSAFDirectoryAsync", untypedAsyncFunctionComponent12);
            ModuleDefinitionBuilder moduleDefinitionBuilder15 = moduleDefinitionBuilder;
            TypeConverterProvider converters12 = moduleDefinitionBuilder15.getConverters();
            AnyType[] anyTypeArr14 = new AnyType[3];
            AnyType anyType19 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType19 == null) {
                anyType19 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$37
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters12);
            }
            anyTypeArr14[0] = anyType19;
            AnyType anyType20 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType20 == null) {
                anyType20 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$38
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters12);
            }
            anyTypeArr14[1] = anyType20;
            AnyType anyType21 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType21 == null) {
                anyType21 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$39
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters12);
            }
            anyTypeArr14[2] = anyType21;
            Function1<Object[], String> function113 = new Function1<Object[], String>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$40
                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] objArr) {
                    boolean isSAFUri;
                    DocumentFile nearestSAFFile;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    String str = (String) objArr[2];
                    String str2 = (String) obj2;
                    Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) obj));
                    FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemLegacyModule2.ensurePermission(parse, Permission.WRITE);
                    isSAFUri = FileSystemLegacyModule.this.isSAFUri(parse);
                    if (isSAFUri) {
                        nearestSAFFile = FileSystemLegacyModule.this.getNearestSAFFile(parse);
                        if (nearestSAFFile == null || !nearestSAFFile.isDirectory()) {
                            throw new FileSystemCannotCreateFileException(parse);
                        }
                        DocumentFile createFile = nearestSAFFile.createFile(str, str2);
                        if (createFile == null) {
                            throw new FileSystemCannotCreateFileException(null);
                        }
                        return createFile.getUri().toString();
                    }
                    throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI.");
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                untypedAsyncFunctionComponent13 = new StringAsyncFunctionComponent("createSAFFileAsync", anyTypeArr14, function113);
                            } else {
                                untypedAsyncFunctionComponent13 = new UntypedAsyncFunctionComponent("createSAFFileAsync", anyTypeArr14, function113);
                            }
                        } else {
                            untypedAsyncFunctionComponent13 = new FloatAsyncFunctionComponent("createSAFFileAsync", anyTypeArr14, function113);
                        }
                    } else {
                        untypedAsyncFunctionComponent13 = new DoubleAsyncFunctionComponent("createSAFFileAsync", anyTypeArr14, function113);
                    }
                } else {
                    untypedAsyncFunctionComponent13 = new BoolAsyncFunctionComponent("createSAFFileAsync", anyTypeArr14, function113);
                }
            } else {
                untypedAsyncFunctionComponent13 = new IntAsyncFunctionComponent("createSAFFileAsync", anyTypeArr14, function113);
            }
            moduleDefinitionBuilder15.getAsyncFunctions().put("createSAFFileAsync", untypedAsyncFunctionComponent13);
            ModuleDefinitionBuilder moduleDefinitionBuilder16 = moduleDefinitionBuilder;
            TypeConverterProvider converters13 = moduleDefinitionBuilder16.getConverters();
            AnyType[] anyTypeArr15 = new AnyType[1];
            AnyType anyType22 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType22 == null) {
                anyType22 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters13);
            }
            anyTypeArr15[0] = anyType22;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent7 = new AsyncFunctionWithPromiseComponent("requestDirectoryPermissionsAsync", anyTypeArr15, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Promise promise2;
                    Uri parse;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    String str = (String) objArr[0];
                    promise2 = FileSystemLegacyModule.this.dirPermissionsRequest;
                    if (promise2 != null) {
                        throw new FileSystemPendingPermissionsRequestException();
                    }
                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
                    if (Build.VERSION.SDK_INT >= 26 && str != null && (parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(str))) != null) {
                        intent.putExtra("android.provider.extra.INITIAL_URI", parse);
                    }
                    FileSystemLegacyModule.this.dirPermissionsRequest = promise;
                    FileSystemLegacyModule.this.getAppContext().getThrowingActivity().startActivityForResult(intent, 5394);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder16.getAsyncFunctions().put("requestDirectoryPermissionsAsync", asyncFunctionWithPromiseComponent7);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent8 = asyncFunctionWithPromiseComponent7;
            ModuleDefinitionBuilder moduleDefinitionBuilder17 = moduleDefinitionBuilder;
            TypeConverterProvider converters14 = moduleDefinitionBuilder17.getConverters();
            AnyType[] anyTypeArr16 = new AnyType[3];
            AnyType anyType23 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType23 == null) {
                anyType23 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$3
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters14);
            }
            anyTypeArr16[0] = anyType23;
            AnyType anyType24 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType24 == null) {
                anyType24 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$4
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters14);
            }
            anyTypeArr16[1] = anyType24;
            AnyType anyType25 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false));
            if (anyType25 == null) {
                anyType25 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemUploadOptions.class);
                    }
                }), converters14);
            }
            anyTypeArr16[2] = anyType25;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent9 = new AsyncFunctionWithPromiseComponent("uploadAsync", anyTypeArr16, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$6
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Request createUploadRequest;
                    OkHttpClient okHttpClient;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    FileSystemUploadOptions fileSystemUploadOptions = (FileSystemUploadOptions) objArr[2];
                    FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    createUploadRequest = fileSystemLegacyModule2.createUploadRequest((String) obj, (String) obj2, fileSystemUploadOptions, new RequestBodyDecorator() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$1$20$request$1
                        @Override // expo.modules.filesystem.legacy.RequestBodyDecorator
                        public final RequestBody decorate(RequestBody requestBody) {
                            Intrinsics.checkNotNullParameter(requestBody, "requestBody");
                            return requestBody;
                        }
                    });
                    okHttpClient = FileSystemLegacyModule.this.getOkHttpClient();
                    if (okHttpClient != null) {
                        Call newCall = okHttpClient.newCall(createUploadRequest);
                        final FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                        newCall.enqueue(new Callback() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$1$20$1$1
                            @Override // okhttp3.Callback
                            public void onFailure(Call call, IOException e) {
                                String str;
                                String str2;
                                Intrinsics.checkNotNullParameter(call, "call");
                                Intrinsics.checkNotNullParameter(e, "e");
                                str = FileSystemLegacyModuleKt.TAG;
                                Log.e(str, String.valueOf(e.getMessage()));
                                Promise promise2 = Promise.this;
                                str2 = FileSystemLegacyModuleKt.TAG;
                                Intrinsics.checkNotNullExpressionValue(str2, "access$getTAG$p(...)");
                                promise2.reject(str2, e.getMessage(), e);
                            }

                            @Override // okhttp3.Callback
                            public void onResponse(Call call, Response response) {
                                Bundle translateHeaders;
                                Intrinsics.checkNotNullParameter(call, "call");
                                Intrinsics.checkNotNullParameter(response, "response");
                                Bundle bundle = new Bundle();
                                FileSystemLegacyModule fileSystemLegacyModule4 = fileSystemLegacyModule3;
                                ResponseBody body = response.body();
                                bundle.putString("body", body != null ? body.string() : null);
                                bundle.putInt("status", response.code());
                                translateHeaders = fileSystemLegacyModule4.translateHeaders(response.headers());
                                bundle.putBundle("headers", translateHeaders);
                                response.close();
                                Promise.this.resolve(bundle);
                            }
                        });
                        return;
                    }
                    promise.reject(new FileSystemOkHttpNullException());
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder17.getAsyncFunctions().put("uploadAsync", asyncFunctionWithPromiseComponent9);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent10 = asyncFunctionWithPromiseComponent9;
            ModuleDefinitionBuilder moduleDefinitionBuilder18 = moduleDefinitionBuilder;
            TypeConverterProvider converters15 = moduleDefinitionBuilder18.getConverters();
            AnyType[] anyTypeArr17 = new AnyType[4];
            AnyType anyType26 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType26 == null) {
                anyType26 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$7
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters15);
            }
            anyTypeArr17[0] = anyType26;
            AnyType anyType27 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType27 == null) {
                anyType27 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$8
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters15);
            }
            anyTypeArr17[1] = anyType27;
            AnyType anyType28 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType28 == null) {
                anyType28 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$9
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters15);
            }
            anyTypeArr17[2] = anyType28;
            AnyType anyType29 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false));
            if (anyType29 == null) {
                anyType29 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$10
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(FileSystemUploadOptions.class);
                    }
                }), converters15);
            }
            anyTypeArr17[3] = anyType29;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent11 = new AsyncFunctionWithPromiseComponent("uploadTaskStartAsync", anyTypeArr17, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$11
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Request createUploadRequest;
                    OkHttpClient okHttpClient;
                    Map map;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    Object obj3 = objArr[2];
                    FileSystemUploadOptions fileSystemUploadOptions = (FileSystemUploadOptions) objArr[3];
                    final String str = (String) obj3;
                    final FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    final CountingRequestListener countingRequestListener = new CountingRequestListener() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$1$21$progressListener$1
                        private long mLastUpdate = -1;

                        @Override // expo.modules.filesystem.legacy.CountingRequestListener
                        public void onProgress(long bytesWritten, long contentLength) {
                            Bundle bundle = new Bundle();
                            Bundle bundle2 = new Bundle();
                            long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis > this.mLastUpdate + 100 || bytesWritten == contentLength) {
                                this.mLastUpdate = currentTimeMillis;
                                bundle2.putDouble("totalBytesSent", bytesWritten);
                                bundle2.putDouble("totalBytesExpectedToSend", contentLength);
                                bundle.putString(InstallationId.LEGACY_PREFERENCES_UUID_KEY, str);
                                bundle.putBundle("data", bundle2);
                                fileSystemLegacyModule2.sendEvent("expo-file-system.uploadProgress", bundle);
                            }
                        }
                    };
                    FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                    createUploadRequest = fileSystemLegacyModule3.createUploadRequest((String) obj, (String) obj2, fileSystemUploadOptions, new RequestBodyDecorator() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$1$21$request$1
                        @Override // expo.modules.filesystem.legacy.RequestBodyDecorator
                        public final RequestBody decorate(RequestBody requestBody) {
                            Intrinsics.checkNotNullParameter(requestBody, "requestBody");
                            return new CountingRequestBody(requestBody, CountingRequestListener.this);
                        }
                    });
                    okHttpClient = FileSystemLegacyModule.this.getOkHttpClient();
                    Intrinsics.checkNotNull(okHttpClient);
                    Call newCall = okHttpClient.newCall(createUploadRequest);
                    map = FileSystemLegacyModule.this.taskHandlers;
                    map.put(str, new FileSystemLegacyModule.TaskHandler(newCall));
                    final FileSystemLegacyModule fileSystemLegacyModule4 = FileSystemLegacyModule.this;
                    newCall.enqueue(new Callback() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$1$21$1
                        @Override // okhttp3.Callback
                        public void onFailure(Call call, IOException e) {
                            String str2;
                            String str3;
                            Intrinsics.checkNotNullParameter(call, "call");
                            Intrinsics.checkNotNullParameter(e, "e");
                            if (!call.getCanceled()) {
                                str2 = FileSystemLegacyModuleKt.TAG;
                                Log.e(str2, String.valueOf(e.getMessage()));
                                Promise promise2 = Promise.this;
                                str3 = FileSystemLegacyModuleKt.TAG;
                                Intrinsics.checkNotNullExpressionValue(str3, "access$getTAG$p(...)");
                                promise2.reject(str3, e.getMessage(), e);
                                return;
                            }
                            Promise.this.resolve((Object) null);
                        }

                        @Override // okhttp3.Callback
                        public void onResponse(Call call, Response response) {
                            Bundle translateHeaders;
                            Intrinsics.checkNotNullParameter(call, "call");
                            Intrinsics.checkNotNullParameter(response, "response");
                            Bundle bundle = new Bundle();
                            ResponseBody body = response.body();
                            FileSystemLegacyModule fileSystemLegacyModule5 = fileSystemLegacyModule4;
                            bundle.putString("body", body != null ? body.string() : null);
                            bundle.putInt("status", response.code());
                            translateHeaders = fileSystemLegacyModule5.translateHeaders(response.headers());
                            bundle.putBundle("headers", translateHeaders);
                            response.close();
                            Promise.this.resolve(bundle);
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder18.getAsyncFunctions().put("uploadTaskStartAsync", asyncFunctionWithPromiseComponent11);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent12 = asyncFunctionWithPromiseComponent11;
            ModuleDefinitionBuilder moduleDefinitionBuilder19 = moduleDefinitionBuilder;
            TypeConverterProvider converters16 = moduleDefinitionBuilder19.getConverters();
            AnyType[] anyTypeArr18 = new AnyType[3];
            AnyType anyType30 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType30 == null) {
                anyType30 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$12
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters16);
            }
            anyTypeArr18[0] = anyType30;
            AnyType anyType31 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType31 == null) {
                anyType31 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$13
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters16);
            }
            anyTypeArr18[1] = anyType31;
            AnyType anyType32 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(DownloadOptionsLegacy.class), false));
            if (anyType32 == null) {
                anyType32 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DownloadOptionsLegacy.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$14
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(DownloadOptionsLegacy.class);
                    }
                }), converters16);
            }
            anyTypeArr18[2] = anyType32;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent13 = new AsyncFunctionWithPromiseComponent("downloadAsync", anyTypeArr18, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$15
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    OkHttpClient okHttpClient;
                    Call newCall;
                    Context context;
                    File file;
                    Sink sink$default;
                    String md5;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    final DownloadOptionsLegacy downloadOptionsLegacy = (DownloadOptionsLegacy) objArr[2];
                    String str = (String) obj;
                    final Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) obj2));
                    FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemLegacyModule2.ensurePermission(parse, Permission.WRITE);
                    FileSystemLegacyModule.this.checkIfFileDirExists(parse);
                    if (!StringsKt.contains$default((CharSequence) str, (CharSequence) ":", false, 2, (Object) null)) {
                        context = FileSystemLegacyModule.this.getContext();
                        InputStream openRawResource = context.getResources().openRawResource(context.getResources().getIdentifier(str, "raw", context.getPackageName()));
                        Intrinsics.checkNotNullExpressionValue(openRawResource, "openRawResource(...)");
                        BufferedSource buffer = Okio.buffer(Okio.source(openRawResource));
                        file = FileSystemLegacyModule.this.toFile(parse);
                        file.delete();
                        sink$default = Okio__JvmOkioKt.sink$default(file, false, 1, null);
                        BufferedSink buffer2 = Okio.buffer(sink$default);
                        buffer2.writeAll(buffer);
                        buffer2.close();
                        Bundle bundle = new Bundle();
                        bundle.putString("uri", Uri.fromFile(file).toString());
                        Boolean valueOf = Boolean.valueOf(downloadOptionsLegacy.getMd5());
                        Boolean bool = valueOf.booleanValue() ? valueOf : null;
                        if (bool != null) {
                            bool.booleanValue();
                            md5 = FileSystemLegacyModule.this.md5(file);
                            bundle.putString("md5", md5);
                        }
                        promise.resolve(bundle);
                        return;
                    }
                    if (Intrinsics.areEqual("file", parse.getScheme())) {
                        Request.Builder url = new Request.Builder().url(str);
                        if (downloadOptionsLegacy.getHeaders() != null) {
                            for (Map.Entry<String, String> entry : downloadOptionsLegacy.getHeaders().entrySet()) {
                                url.addHeader(entry.getKey(), entry.getValue());
                            }
                        }
                        okHttpClient = FileSystemLegacyModule.this.getOkHttpClient();
                        if (okHttpClient == null || (newCall = okHttpClient.newCall(url.build())) == null) {
                            promise.reject(new FileSystemOkHttpNullException());
                            return;
                        } else {
                            final FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                            newCall.enqueue(new Callback() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$1$22$4
                                @Override // okhttp3.Callback
                                public void onFailure(Call call, IOException e) {
                                    String str2;
                                    String str3;
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                    str2 = FileSystemLegacyModuleKt.TAG;
                                    Log.e(str2, String.valueOf(e.getMessage()));
                                    Promise promise2 = Promise.this;
                                    str3 = FileSystemLegacyModuleKt.TAG;
                                    Intrinsics.checkNotNullExpressionValue(str3, "access$getTAG$p(...)");
                                    promise2.reject(str3, e.getMessage(), e);
                                }

                                @Override // okhttp3.Callback
                                public void onResponse(Call call, Response response) throws IOException {
                                    File file2;
                                    Sink sink$default2;
                                    Bundle translateHeaders;
                                    String md52;
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    FileSystemLegacyModule fileSystemLegacyModule4 = fileSystemLegacyModule3;
                                    Uri uri = parse;
                                    Intrinsics.checkNotNull(uri);
                                    file2 = fileSystemLegacyModule4.toFile(uri);
                                    file2.delete();
                                    sink$default2 = Okio__JvmOkioKt.sink$default(file2, false, 1, null);
                                    BufferedSink buffer3 = Okio.buffer(sink$default2);
                                    ResponseBody body = response.body();
                                    Intrinsics.checkNotNull(body);
                                    buffer3.writeAll(body.getSource());
                                    buffer3.close();
                                    Bundle bundle2 = new Bundle();
                                    FileSystemLegacyModule fileSystemLegacyModule5 = fileSystemLegacyModule3;
                                    DownloadOptionsLegacy downloadOptionsLegacy2 = downloadOptionsLegacy;
                                    bundle2.putString("uri", Uri.fromFile(file2).toString());
                                    bundle2.putInt("status", response.code());
                                    translateHeaders = fileSystemLegacyModule5.translateHeaders(response.headers());
                                    bundle2.putBundle("headers", translateHeaders);
                                    if (downloadOptionsLegacy2.getMd5()) {
                                        md52 = fileSystemLegacyModule5.md5(file2);
                                        bundle2.putString("md5", md52);
                                    }
                                    response.close();
                                    Promise.this.resolve(bundle2);
                                }
                            });
                            return;
                        }
                    }
                    throw new IOException("Unsupported scheme for location '" + parse + "'.");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder19.getAsyncFunctions().put("downloadAsync", asyncFunctionWithPromiseComponent13);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent14 = asyncFunctionWithPromiseComponent13;
            ModuleDefinitionBuilder moduleDefinitionBuilder20 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                untypedAsyncFunctionComponent14 = new AsyncFunctionWithPromiseComponent("networkTaskCancelAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$41
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Map map;
                        Call call;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        map = FileSystemLegacyModule.this.taskHandlers;
                        FileSystemLegacyModule.TaskHandler taskHandler = (FileSystemLegacyModule.TaskHandler) map.get((String) promise);
                        if (taskHandler == null || (call = taskHandler.getCall()) == null) {
                            return;
                        }
                        call.cancel();
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters17 = moduleDefinitionBuilder20.getConverters();
                AnyType[] anyTypeArr19 = new AnyType[1];
                AnyType anyType33 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
                if (anyType33 == null) {
                    anyType33 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$42
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(String.class);
                        }
                    }), converters17);
                }
                anyTypeArr19[0] = anyType33;
                untypedAsyncFunctionComponent14 = new UntypedAsyncFunctionComponent("networkTaskCancelAsync", anyTypeArr19, new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$43
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Map map;
                        Call call;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        String str = (String) objArr[0];
                        map = FileSystemLegacyModule.this.taskHandlers;
                        FileSystemLegacyModule.TaskHandler taskHandler = (FileSystemLegacyModule.TaskHandler) map.get(str);
                        if (taskHandler == null || (call = taskHandler.getCall()) == null) {
                            return null;
                        }
                        call.cancel();
                        return Unit.INSTANCE;
                    }
                });
            }
            moduleDefinitionBuilder20.getAsyncFunctions().put("networkTaskCancelAsync", untypedAsyncFunctionComponent14);
            ModuleDefinitionBuilder moduleDefinitionBuilder21 = moduleDefinitionBuilder;
            TypeConverterProvider converters18 = moduleDefinitionBuilder21.getConverters();
            AnyType[] anyTypeArr20 = new AnyType[5];
            AnyType anyType34 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType34 == null) {
                anyType34 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$16
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters18);
            }
            anyTypeArr20[0] = anyType34;
            AnyType anyType35 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType35 == null) {
                anyType35 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$17
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters18);
            }
            anyTypeArr20[1] = anyType35;
            AnyType anyType36 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType36 == null) {
                anyType36 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$18
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters18);
            }
            anyTypeArr20[2] = anyType36;
            AnyType anyType37 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(DownloadOptionsLegacy.class), false));
            if (anyType37 == null) {
                anyType37 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DownloadOptionsLegacy.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$19
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(DownloadOptionsLegacy.class);
                    }
                }), converters18);
            }
            anyTypeArr20[3] = anyType37;
            AnyType anyType38 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType38 == null) {
                anyType38 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$20
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters18);
            }
            anyTypeArr20[4] = anyType38;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent15 = new AsyncFunctionWithPromiseComponent("downloadResumableStartAsync", anyTypeArr20, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunctionWithPromise$21
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    OkHttpClient okHttpClient;
                    Map map;
                    File file;
                    CoroutineScope coroutineScope;
                    OkHttpClient.Builder newBuilder;
                    OkHttpClient.Builder addInterceptor;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    Object obj3 = objArr[2];
                    Object obj4 = objArr[3];
                    final String str = (String) objArr[4];
                    DownloadOptionsLegacy downloadOptionsLegacy = (DownloadOptionsLegacy) obj4;
                    final String str2 = (String) obj3;
                    String str3 = (String) obj;
                    Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath((String) obj2));
                    FileSystemLegacyModule fileSystemLegacyModule2 = FileSystemLegacyModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemLegacyModule2.checkIfFileDirExists(parse);
                    if (!Intrinsics.areEqual(parse.getScheme(), "file")) {
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }
                    final FileSystemLegacyModule fileSystemLegacyModule3 = FileSystemLegacyModule.this;
                    final FileSystemLegacyModule.ProgressListener progressListener = new FileSystemLegacyModule.ProgressListener() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$1$24$progressListener$1
                        private long mLastUpdate = -1;

                        public final long getMLastUpdate() {
                            return this.mLastUpdate;
                        }

                        public final void setMLastUpdate(long j) {
                            this.mLastUpdate = j;
                        }

                        @Override // expo.modules.filesystem.legacy.FileSystemLegacyModule.ProgressListener
                        public void update(long bytesRead, long contentLength, boolean done) {
                            Bundle bundle = new Bundle();
                            Bundle bundle2 = new Bundle();
                            String str4 = str;
                            long parseLong = bytesRead + (str4 != null ? Long.parseLong(str4) : 0L);
                            String str5 = str;
                            long parseLong2 = contentLength + (str5 != null ? Long.parseLong(str5) : 0L);
                            long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis > this.mLastUpdate + 100 || parseLong == parseLong2) {
                                this.mLastUpdate = currentTimeMillis;
                                bundle2.putDouble("totalBytesWritten", parseLong);
                                bundle2.putDouble("totalBytesExpectedToWrite", parseLong2);
                                bundle.putString(InstallationId.LEGACY_PREFERENCES_UUID_KEY, str2);
                                bundle.putBundle("data", bundle2);
                                fileSystemLegacyModule3.sendEvent("expo-file-system.downloadProgress", bundle);
                            }
                        }
                    };
                    okHttpClient = FileSystemLegacyModule.this.getOkHttpClient();
                    OkHttpClient build = (okHttpClient == null || (newBuilder = okHttpClient.newBuilder()) == null || (addInterceptor = newBuilder.addInterceptor(new Interceptor() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$lambda$45$$inlined$-addInterceptor$1
                        @Override // okhttp3.Interceptor
                        public final Response intercept(Interceptor.Chain chain) {
                            Intrinsics.checkNotNullParameter(chain, "chain");
                            Response proceed = chain.proceed(chain.request());
                            return proceed.newBuilder().body(new FileSystemLegacyModule.ProgressResponseBody(proceed.body(), FileSystemLegacyModule.ProgressListener.this)).build();
                        }
                    })) == null) ? null : addInterceptor.build();
                    if (build == null) {
                        promise.reject(new FileSystemOkHttpNullException());
                        return;
                    }
                    Request.Builder builder = new Request.Builder();
                    if (str != null) {
                        builder.addHeader(HttpHeaders.RANGE, "bytes=" + str + "-");
                    }
                    if (downloadOptionsLegacy.getHeaders() != null) {
                        for (Map.Entry<String, String> entry : downloadOptionsLegacy.getHeaders().entrySet()) {
                            builder.addHeader(entry.getKey(), entry.getValue());
                        }
                    }
                    Call newCall = build.newCall(builder.url(str3).build());
                    map = FileSystemLegacyModule.this.taskHandlers;
                    map.put(str2, new FileSystemLegacyModule.DownloadTaskHandler(parse, newCall));
                    file = FileSystemLegacyModule.this.toFile(parse);
                    FileSystemLegacyModule.DownloadResumableTaskParams downloadResumableTaskParams = new FileSystemLegacyModule.DownloadResumableTaskParams(downloadOptionsLegacy, newCall, file, str != null, promise);
                    coroutineScope = FileSystemLegacyModule.this.moduleCoroutineScope;
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new FileSystemLegacyModule$definition$1$24$3(FileSystemLegacyModule.this, downloadResumableTaskParams, null), 3, null);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder21.getAsyncFunctions().put("downloadResumableStartAsync", asyncFunctionWithPromiseComponent15);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent16 = asyncFunctionWithPromiseComponent15;
            ModuleDefinitionBuilder moduleDefinitionBuilder22 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent6 = new AsyncFunctionWithPromiseComponent("downloadResumablePauseAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$44
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Map map;
                        Map map2;
                        File file;
                        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        map = FileSystemLegacyModule.this.taskHandlers;
                        FileSystemLegacyModule.TaskHandler taskHandler = (FileSystemLegacyModule.TaskHandler) map.get(str);
                        if (taskHandler == null) {
                            throw new IOException("No download object available");
                        }
                        if (!(taskHandler instanceof FileSystemLegacyModule.DownloadTaskHandler)) {
                            throw new FileSystemCannotFindTaskException();
                        }
                        taskHandler.getCall().cancel();
                        map2 = FileSystemLegacyModule.this.taskHandlers;
                        map2.remove(str);
                        file = FileSystemLegacyModule.this.toFile(((FileSystemLegacyModule.DownloadTaskHandler) taskHandler).getFileUri());
                        new Bundle().putString("resumeData", String.valueOf(file.length()));
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                TypeConverterProvider converters19 = moduleDefinitionBuilder22.getConverters();
                AnyType[] anyTypeArr21 = new AnyType[1];
                AnyType anyType39 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
                if (anyType39 == null) {
                    anyType39 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$45
                        @Override // kotlin.jvm.functions.Function0
                        public final KType invoke() {
                            return Reflection.typeOf(String.class);
                        }
                    }), converters19);
                }
                anyTypeArr21[0] = anyType39;
                Function1<Object[], Bundle> function114 = new Function1<Object[], Bundle>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$AsyncFunction$46
                    @Override // kotlin.jvm.functions.Function1
                    public final Bundle invoke(Object[] objArr) {
                        Map map;
                        Map map2;
                        File file;
                        Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                        String str = (String) objArr[0];
                        map = FileSystemLegacyModule.this.taskHandlers;
                        FileSystemLegacyModule.TaskHandler taskHandler = (FileSystemLegacyModule.TaskHandler) map.get(str);
                        if (taskHandler == null) {
                            throw new IOException("No download object available");
                        }
                        if (!(taskHandler instanceof FileSystemLegacyModule.DownloadTaskHandler)) {
                            throw new FileSystemCannotFindTaskException();
                        }
                        taskHandler.getCall().cancel();
                        map2 = FileSystemLegacyModule.this.taskHandlers;
                        map2.remove(str);
                        file = FileSystemLegacyModule.this.toFile(((FileSystemLegacyModule.DownloadTaskHandler) taskHandler).getFileUri());
                        Bundle bundle = new Bundle();
                        bundle.putString("resumeData", String.valueOf(file.length()));
                        return bundle;
                    }
                };
                if (!Intrinsics.areEqual(Bundle.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Bundle.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Bundle.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Bundle.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Bundle.class, String.class)) {
                                    untypedAsyncFunctionComponent15 = new StringAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr21, function114);
                                } else {
                                    untypedAsyncFunctionComponent15 = new UntypedAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr21, function114);
                                }
                            } else {
                                untypedAsyncFunctionComponent15 = new FloatAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr21, function114);
                            }
                        } else {
                            untypedAsyncFunctionComponent15 = new DoubleAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr21, function114);
                        }
                    } else {
                        untypedAsyncFunctionComponent15 = new BoolAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr21, function114);
                    }
                } else {
                    untypedAsyncFunctionComponent15 = new IntAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr21, function114);
                }
                asyncFunctionWithPromiseComponent6 = untypedAsyncFunctionComponent15;
            }
            moduleDefinitionBuilder22.getAsyncFunctions().put("downloadResumablePauseAsync", asyncFunctionWithPromiseComponent6);
            moduleDefinitionBuilder.getEventListeners().put(EventName.ON_ACTIVITY_RESULT, new EventListenerWithSenderAndPayload(EventName.ON_ACTIVITY_RESULT, new Function2<Activity, OnActivityResultPayload, Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$OnActivityResult$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Activity activity, OnActivityResultPayload onActivityResultPayload) {
                    invoke2(activity, onActivityResultPayload);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Activity sender, OnActivityResultPayload payload) {
                    Promise promise;
                    Promise promise2;
                    Intrinsics.checkNotNullParameter(sender, "sender");
                    Intrinsics.checkNotNullParameter(payload, "payload");
                    int requestCode = payload.getRequestCode();
                    int resultCode = payload.getResultCode();
                    Intent data = payload.getData();
                    if (requestCode == 5394) {
                        promise = FileSystemLegacyModule.this.dirPermissionsRequest;
                        if (promise != null) {
                            Bundle bundle = new Bundle();
                            if (resultCode == -1 && data != null) {
                                Uri data2 = data.getData();
                                int flags = data.getFlags() & 3;
                                if (data2 != null) {
                                    FileSystemLegacyModule.this.getAppContext().getThrowingActivity().getContentResolver().takePersistableUriPermission(data2, flags);
                                }
                                bundle.putBoolean(PermissionsResponse.GRANTED_KEY, true);
                                bundle.putString("directoryUri", String.valueOf(data2));
                            } else {
                                bundle.putBoolean(PermissionsResponse.GRANTED_KEY, false);
                            }
                            promise2 = FileSystemLegacyModule.this.dirPermissionsRequest;
                            if (promise2 != null) {
                                promise2.resolve(bundle);
                            }
                            FileSystemLegacyModule.this.dirPermissionsRequest = null;
                        }
                    }
                }
            }));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new Function0<Unit>() { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$definition$lambda$51$$inlined$OnDestroy$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str;
                    CoroutineScope coroutineScope;
                    try {
                        coroutineScope = FileSystemLegacyModule.this.moduleCoroutineScope;
                        CoroutineScopeKt.cancel(coroutineScope, new ModuleDestroyedException(null, 1, null));
                    } catch (IllegalStateException unused) {
                        str = FileSystemLegacyModuleKt.TAG;
                        Log.e(str, "The scope does not have a job in it");
                    }
                }
            }));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    private final void checkIfFileExists(Uri uri) throws IOException {
        File file = toFile(uri);
        if (file.exists()) {
            return;
        }
        throw new IOException("Directory for '" + file.getPath() + "' doesn't exist.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkIfFileDirExists(Uri uri) throws IOException {
        File file = toFile(uri);
        File parentFile = file.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            throw new IOException("Directory for '" + file.getPath() + "' doesn't exist. Please make sure directory '" + file.getParent() + "' exists before calling downloadAsync.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensureDirExists(File dir) throws IOException {
        if (!dir.isDirectory() && !dir.mkdirs()) {
            throw new IOException("Couldn't create directory '" + dir + "'");
        }
    }

    private final EnumSet<Permission> permissionsForPath(String path) {
        FilePermissionModuleInterface filePermission = getAppContext().getFilePermission();
        if (filePermission != null) {
            return filePermission.getPathPermissions(getContext(), path);
        }
        return null;
    }

    private final EnumSet<Permission> permissionsForUri(Uri uri) {
        if (isSAFUri(uri)) {
            return permissionsForSAFUri(uri);
        }
        if (!Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME) && !Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
            return Intrinsics.areEqual(uri.getScheme(), "file") ? permissionsForPath(uri.getPath()) : uri.getScheme() == null ? EnumSet.of(Permission.READ) : EnumSet.noneOf(Permission.class);
        }
        return EnumSet.of(Permission.READ);
    }

    private final EnumSet<Permission> permissionsForSAFUri(Uri uri) {
        DocumentFile nearestSAFFile = getNearestSAFFile(uri);
        EnumSet<Permission> noneOf = EnumSet.noneOf(Permission.class);
        if (nearestSAFFile != null) {
            if (nearestSAFFile.canRead()) {
                noneOf.add(Permission.READ);
            }
            if (nearestSAFFile.canWrite()) {
                noneOf.add(Permission.WRITE);
            }
        }
        Intrinsics.checkNotNullExpressionValue(noneOf, "apply(...)");
        return noneOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensurePermission(Uri uri, Permission permission, String errorMsg) throws IOException {
        EnumSet<Permission> permissionsForUri = permissionsForUri(uri);
        if (permissionsForUri == null || !permissionsForUri.contains(permission)) {
            throw new IOException(errorMsg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensurePermission(Uri uri, Permission permission) throws IOException {
        if (permission == Permission.READ) {
            ensurePermission(uri, permission, "Location '" + uri + "' isn't readable.");
        }
        if (permission == Permission.WRITE) {
            ensurePermission(uri, permission, "Location '" + uri + "' isn't writable.");
        }
        ensurePermission(uri, permission, "Location '" + uri + "' doesn't have permission '" + permission.name() + "'.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InputStream openAssetInputStream(Uri uri) throws IOException {
        String path = uri.getPath();
        if (path == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        String substring = path.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        InputStream open = getContext().getAssets().open(substring);
        Intrinsics.checkNotNullExpressionValue(open, "open(...)");
        return open;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InputStream openResourceInputStream(String resourceName) throws IOException {
        int identifier = getContext().getResources().getIdentifier(resourceName, "raw", getContext().getPackageName());
        if (identifier == 0 && (identifier = getContext().getResources().getIdentifier(resourceName, "drawable", getContext().getPackageName())) == 0) {
            throw new FileNotFoundException("No resource found with the name '" + resourceName + "'");
        }
        InputStream openRawResource = getContext().getResources().openRawResource(identifier);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "openRawResource(...)");
        return openRawResource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void transformFilesFromSAF(DocumentFile documentFile, File outputDir, boolean copy) throws IOException {
        File file;
        if (!documentFile.exists()) {
            return;
        }
        if (!outputDir.isDirectory()) {
            File parentFile = outputDir.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("Couldn't create folder in output dir.");
            }
        } else if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Couldn't create folder in output dir.");
        }
        if (documentFile.isDirectory()) {
            for (DocumentFile documentFile2 : documentFile.listFiles()) {
                Intrinsics.checkNotNull(documentFile2);
                transformFilesFromSAF(documentFile2, outputDir, copy);
            }
            if (copy) {
                return;
            }
            documentFile.delete();
            return;
        }
        String name = documentFile.getName();
        if (name == null) {
            return;
        }
        if (outputDir.isDirectory()) {
            file = new File(outputDir.getPath(), name);
        } else {
            file = new File(outputDir.getPath());
        }
        FileOutputStream openInputStream = getContext().getContentResolver().openInputStream(documentFile.getUri());
        try {
            InputStream inputStream = openInputStream;
            openInputStream = new FileOutputStream(file);
            try {
                IOUtils.copy(inputStream, openInputStream);
                CloseableKt.closeFinally(openInputStream, null);
                CloseableKt.closeFinally(openInputStream, null);
                if (copy) {
                    return;
                }
                documentFile.delete();
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Uri contentUriFromFile(File file) {
        Uri uriForFile = FileProvider.getUriForFile(getAppContext().getThrowingActivity().getApplication(), getAppContext().getThrowingActivity().getApplication().getPackageName() + ".FileSystemFileProvider", file);
        Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(...)");
        return uriForFile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Request createUploadRequest(String url, String fileUriString, FileSystemUploadOptions options, RequestBodyDecorator decorator) throws IOException {
        Uri parse = Uri.parse(FileSystemLegacyModuleKt.slashifyFilePath(fileUriString));
        Intrinsics.checkNotNull(parse);
        ensurePermission(parse, Permission.READ);
        checkIfFileExists(parse);
        Request.Builder url2 = new Request.Builder().url(url);
        Map<String, String> headers = options.getHeaders();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                url2.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return url2.method(options.getHttpMethod().getValue(), createRequestBody(options, decorator, toFile(parse))).build();
    }

    private final RequestBody createRequestBody(FileSystemUploadOptions options, RequestBodyDecorator decorator, File file) {
        int i = WhenMappings.$EnumSwitchMapping$0[options.getUploadType().ordinal()];
        if (i == 1) {
            return decorator.decorate(RequestBody.INSTANCE.create(file, (MediaType) null));
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        MultipartBody.Builder type = new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM);
        Map<String, String> parameters = options.getParameters();
        if (parameters != null) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                type.addFormDataPart(entry.getKey(), entry.getValue().toString());
            }
        }
        String mimeType = options.getMimeType();
        if (mimeType == null) {
            mimeType = URLConnection.guessContentTypeFromName(file.getName());
            Intrinsics.checkNotNullExpressionValue(mimeType, "guessContentTypeFromName(...)");
        }
        String fieldName = options.getFieldName();
        if (fieldName == null) {
            fieldName = file.getName();
        }
        Intrinsics.checkNotNull(fieldName);
        type.addFormDataPart(fieldName, file.getName(), decorator.decorate(RequestBody.INSTANCE.create(file, MediaType.INSTANCE.parse(mimeType))));
        return type.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemLegacyModule.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÆ\u0003J;\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$DownloadResumableTaskParams;", "", "options", "Lexpo/modules/filesystem/legacy/DownloadOptionsLegacy;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "file", "Ljava/io/File;", "isResume", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "<init>", "(Lexpo/modules/filesystem/legacy/DownloadOptionsLegacy;Lokhttp3/Call;Ljava/io/File;ZLexpo/modules/kotlin/Promise;)V", "getOptions", "()Lexpo/modules/filesystem/legacy/DownloadOptionsLegacy;", "getCall", "()Lokhttp3/Call;", "getFile", "()Ljava/io/File;", "()Z", "getPromise", "()Lexpo/modules/kotlin/Promise;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class DownloadResumableTaskParams {
        private final Call call;
        private final File file;
        private final boolean isResume;
        private final DownloadOptionsLegacy options;
        private final Promise promise;

        public static /* synthetic */ DownloadResumableTaskParams copy$default(DownloadResumableTaskParams downloadResumableTaskParams, DownloadOptionsLegacy downloadOptionsLegacy, Call call, File file, boolean z, Promise promise, int i, Object obj) {
            if ((i & 1) != 0) {
                downloadOptionsLegacy = downloadResumableTaskParams.options;
            }
            if ((i & 2) != 0) {
                call = downloadResumableTaskParams.call;
            }
            if ((i & 4) != 0) {
                file = downloadResumableTaskParams.file;
            }
            if ((i & 8) != 0) {
                z = downloadResumableTaskParams.isResume;
            }
            if ((i & 16) != 0) {
                promise = downloadResumableTaskParams.promise;
            }
            Promise promise2 = promise;
            File file2 = file;
            return downloadResumableTaskParams.copy(downloadOptionsLegacy, call, file2, z, promise2);
        }

        /* renamed from: component1, reason: from getter */
        public final DownloadOptionsLegacy getOptions() {
            return this.options;
        }

        /* renamed from: component2, reason: from getter */
        public final Call getCall() {
            return this.call;
        }

        /* renamed from: component3, reason: from getter */
        public final File getFile() {
            return this.file;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsResume() {
            return this.isResume;
        }

        /* renamed from: component5, reason: from getter */
        public final Promise getPromise() {
            return this.promise;
        }

        public final DownloadResumableTaskParams copy(DownloadOptionsLegacy options, Call call, File file, boolean isResume, Promise promise) {
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(promise, "promise");
            return new DownloadResumableTaskParams(options, call, file, isResume, promise);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadResumableTaskParams)) {
                return false;
            }
            DownloadResumableTaskParams downloadResumableTaskParams = (DownloadResumableTaskParams) other;
            return Intrinsics.areEqual(this.options, downloadResumableTaskParams.options) && Intrinsics.areEqual(this.call, downloadResumableTaskParams.call) && Intrinsics.areEqual(this.file, downloadResumableTaskParams.file) && this.isResume == downloadResumableTaskParams.isResume && Intrinsics.areEqual(this.promise, downloadResumableTaskParams.promise);
        }

        public int hashCode() {
            return (((((((this.options.hashCode() * 31) + this.call.hashCode()) * 31) + this.file.hashCode()) * 31) + Boolean.hashCode(this.isResume)) * 31) + this.promise.hashCode();
        }

        public String toString() {
            return "DownloadResumableTaskParams(options=" + this.options + ", call=" + this.call + ", file=" + this.file + ", isResume=" + this.isResume + ", promise=" + this.promise + ")";
        }

        public DownloadResumableTaskParams(DownloadOptionsLegacy options, Call call, File file, boolean z, Promise promise) {
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(promise, "promise");
            this.options = options;
            this.call = call;
            this.file = file;
            this.isResume = z;
            this.promise = promise;
        }

        public final DownloadOptionsLegacy getOptions() {
            return this.options;
        }

        public final Call getCall() {
            return this.call;
        }

        public final File getFile() {
            return this.file;
        }

        public final boolean isResume() {
            return this.isResume;
        }

        public final Promise getPromise() {
            return this.promise;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object downloadResumableTask(DownloadResumableTaskParams downloadResumableTaskParams, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new FileSystemLegacyModule$downloadResumableTask$2(downloadResumableTaskParams, this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemLegacyModule.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0012\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$TaskHandler;", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "<init>", "(Lokhttp3/Call;)V", "getCall", "()Lokhttp3/Call;", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static class TaskHandler {
        private final Call call;

        public TaskHandler(Call call) {
            Intrinsics.checkNotNullParameter(call, "call");
            this.call = call;
        }

        public final Call getCall() {
            return this.call;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemLegacyModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$DownloadTaskHandler;", "Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$TaskHandler;", "fileUri", "Landroid/net/Uri;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "<init>", "(Landroid/net/Uri;Lokhttp3/Call;)V", "getFileUri", "()Landroid/net/Uri;", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class DownloadTaskHandler extends TaskHandler {
        private final Uri fileUri;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadTaskHandler(Uri fileUri, Call call) {
            super(call);
            Intrinsics.checkNotNullParameter(fileUri, "fileUri");
            Intrinsics.checkNotNullParameter(call, "call");
            this.fileUri = fileUri;
        }

        public final Uri getFileUri() {
            return this.fileUri;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemLegacyModule.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$ProgressResponseBody;", "Lokhttp3/ResponseBody;", "responseBody", "progressListener", "Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$ProgressListener;", "<init>", "(Lokhttp3/ResponseBody;Lexpo/modules/filesystem/legacy/FileSystemLegacyModule$ProgressListener;)V", "bufferedSource", "Lokio/BufferedSource;", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY, "Lokhttp3/MediaType;", "contentLength", "", Constants.ScionAnalytics.PARAM_SOURCE, "Lokio/Source;", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ProgressResponseBody extends ResponseBody {
        private BufferedSource bufferedSource;
        private final ProgressListener progressListener;
        private final ResponseBody responseBody;

        public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            Intrinsics.checkNotNullParameter(progressListener, "progressListener");
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override // okhttp3.ResponseBody
        /* renamed from: contentType */
        public MediaType get$contentType() {
            ResponseBody responseBody = this.responseBody;
            if (responseBody != null) {
                return responseBody.get$contentType();
            }
            return null;
        }

        @Override // okhttp3.ResponseBody
        /* renamed from: contentLength */
        public long getContentLength() {
            ResponseBody responseBody = this.responseBody;
            if (responseBody != null) {
                return responseBody.getContentLength();
            }
            return -1L;
        }

        @Override // okhttp3.ResponseBody
        /* renamed from: source */
        public BufferedSource getSource() {
            BufferedSource bufferedSource = this.bufferedSource;
            if (bufferedSource != null) {
                return bufferedSource;
            }
            ResponseBody responseBody = this.responseBody;
            Intrinsics.checkNotNull(responseBody);
            return Okio.buffer(source(responseBody.getSource()));
        }

        private final Source source(final Source source) {
            return new ForwardingSource(source) { // from class: expo.modules.filesystem.legacy.FileSystemLegacyModule$ProgressResponseBody$source$1
                private long totalBytesRead;

                public final long getTotalBytesRead() {
                    return this.totalBytesRead;
                }

                public final void setTotalBytesRead(long j) {
                    this.totalBytesRead = j;
                }

                @Override // okio.ForwardingSource, okio.Source
                public long read(Buffer sink, long byteCount) throws IOException {
                    FileSystemLegacyModule.ProgressListener progressListener;
                    ResponseBody responseBody;
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    long read = super.read(sink, byteCount);
                    this.totalBytesRead += read != -1 ? read : 0L;
                    progressListener = this.progressListener;
                    long j = this.totalBytesRead;
                    responseBody = this.responseBody;
                    progressListener.update(j, responseBody != null ? responseBody.getContentLength() : -1L, read == -1);
                    return read;
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized OkHttpClient getOkHttpClient() {
        if (this.client == null) {
            this.client = new OkHttpClient.Builder().connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).build();
        }
        return this.client;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String md5(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            char[] encodeHex = Hex.encodeHex(DigestUtils.md5(fileInputStream));
            Intrinsics.checkNotNullExpressionValue(encodeHex, "encodeHex(...)");
            String str = new String(encodeHex);
            CloseableKt.closeFinally(fileInputStream, null);
            return str;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("Failed to list contents of " + file);
            }
            IOException e = null;
            for (File file2 : listFiles) {
                try {
                    Intrinsics.checkNotNull(file2);
                    forceDelete(file2);
                } catch (IOException e2) {
                    e = e2;
                }
            }
            if (e != null) {
                throw e;
            }
            if (!file.delete()) {
                throw new IOException("Unable to delete directory " + file + ".");
            }
            return;
        }
        if (!file.delete()) {
            throw new IOException("Unable to delete file: " + file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getFileSize(File file) {
        Object obj;
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0L;
        }
        ArrayList arrayList = new ArrayList(listFiles.length);
        for (File file2 : listFiles) {
            Intrinsics.checkNotNull(file2);
            arrayList.add(Long.valueOf(getFileSize(file2)));
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = Long.valueOf(((Number) next).longValue() + ((Number) it.next()).longValue());
            }
            obj = next;
        } else {
            obj = null;
        }
        Long l = (Long) obj;
        if (l != null) {
            return l.longValue();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InputStream getInputStream(Uri uri) throws IOException {
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            return new FileInputStream(toFile(uri));
        }
        if (Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
            return openAssetInputStream(uri);
        }
        if (!isSAFUri(uri)) {
            throw new IOException("Unsupported scheme for location '" + uri + "'.");
        }
        InputStream openInputStream = getContext().getContentResolver().openInputStream(uri);
        Intrinsics.checkNotNull(openInputStream);
        return openInputStream;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OutputStream getOutputStream(Uri uri) throws IOException {
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            return new FileOutputStream(toFile(uri));
        }
        if (!isSAFUri(uri)) {
            throw new IOException("Unsupported scheme for location '" + uri + "'.");
        }
        OutputStream openOutputStream = getContext().getContentResolver().openOutputStream(uri);
        Intrinsics.checkNotNull(openOutputStream);
        return openOutputStream;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DocumentFile getNearestSAFFile(Uri uri) {
        DocumentFile fromSingleUri = DocumentFile.fromSingleUri(getContext(), uri);
        return (fromSingleUri == null || !fromSingleUri.isFile()) ? DocumentFile.fromTreeUri(getContext(), uri) : fromSingleUri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File toFile(Uri uri) {
        if (uri.getPath() != null) {
            String path = uri.getPath();
            Intrinsics.checkNotNull(path);
            return new File(path);
        }
        throw new IOException("Invalid Uri: " + uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSAFUri(Uri uri) {
        if (Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME)) {
            String host = uri.getHost();
            if (host != null ? StringsKt.startsWith$default(host, "com.android.externalstorage", false, 2, (Object) null) : false) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String parseFileUri(String uriStr) {
        String substring = uriStr.substring(StringsKt.indexOf$default((CharSequence) uriStr, ':', 0, false, 6, (Object) null) + 3);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] getInputStreamBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bundle translateHeaders(Headers headers) {
        Bundle bundle = new Bundle();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            if (bundle.containsKey(name)) {
                bundle.putString(name, bundle.getString(name) + ", " + headers.value(i));
            } else {
                bundle.putString(name, headers.value(i));
            }
        }
        return bundle;
    }
}
