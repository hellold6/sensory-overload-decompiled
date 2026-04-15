package expo.modules.filesystem.legacy;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import expo.modules.filesystem.legacy.FileSystemLegacyModule;
import expo.modules.kotlin.Promise;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FileSystemLegacyModule.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.filesystem.legacy.FileSystemLegacyModule$downloadResumableTask$2", f = "FileSystemLegacyModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class FileSystemLegacyModule$downloadResumableTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ FileSystemLegacyModule.DownloadResumableTaskParams $params;
    int label;
    final /* synthetic */ FileSystemLegacyModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileSystemLegacyModule$downloadResumableTask$2(FileSystemLegacyModule.DownloadResumableTaskParams downloadResumableTaskParams, FileSystemLegacyModule fileSystemLegacyModule, Continuation<? super FileSystemLegacyModule$downloadResumableTask$2> continuation) {
        super(2, continuation);
        this.$params = downloadResumableTaskParams;
        this.this$0 = fileSystemLegacyModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileSystemLegacyModule$downloadResumableTask$2(this.$params, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((FileSystemLegacyModule$downloadResumableTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        Bundle translateHeaders;
        String md5;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FileSystemLegacyModule.DownloadResumableTaskParams downloadResumableTaskParams = this.$params;
        DownloadOptionsLegacy options = downloadResumableTaskParams.getOptions();
        Call call = downloadResumableTaskParams.getCall();
        File file = downloadResumableTaskParams.getFile();
        boolean isResume = downloadResumableTaskParams.getIsResume();
        Promise promise = downloadResumableTaskParams.getPromise();
        try {
            Response execute = call.execute();
            ResponseBody body = execute.body();
            Intrinsics.checkNotNull(body);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(body.byteStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file, isResume);
            byte[] bArr = new byte[1024];
            Ref.IntRef intRef = new Ref.IntRef();
            while (true) {
                int read = bufferedInputStream.read(bArr);
                intRef.element = read;
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, intRef.element);
            }
            Bundle bundle = new Bundle();
            FileSystemLegacyModule fileSystemLegacyModule = this.this$0;
            bundle.putString("uri", Uri.fromFile(file).toString());
            bundle.putInt("status", execute.code());
            translateHeaders = fileSystemLegacyModule.translateHeaders(execute.headers());
            bundle.putBundle("headers", translateHeaders);
            Boolean boxBoolean = Boxing.boxBoolean(options.getMd5());
            if (!boxBoolean.booleanValue()) {
                boxBoolean = null;
            }
            if (boxBoolean != null) {
                boxBoolean.booleanValue();
                md5 = fileSystemLegacyModule.md5(file);
                bundle.putString("md5", md5);
            }
            execute.close();
            promise.resolve(bundle);
            return null;
        } catch (Exception e) {
            if (call.getCanceled()) {
                promise.resolve((Object) null);
                return null;
            }
            String message = e.getMessage();
            if (message != null) {
                str2 = FileSystemLegacyModuleKt.TAG;
                Boxing.boxInt(Log.e(str2, message));
            }
            str = FileSystemLegacyModuleKt.TAG;
            Intrinsics.checkNotNullExpressionValue(str, "access$getTAG$p(...)");
            promise.reject(str, e.getMessage(), e);
            return null;
        }
    }
}
