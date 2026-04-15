package expo.modules.asset;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetModule.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.asset.AssetModule$downloadAsset$2", f = "AssetModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AssetModule$downloadAsset$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Uri>, Object> {
    final /* synthetic */ File $localUrl;
    final /* synthetic */ URI $uri;
    int label;
    final /* synthetic */ AssetModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetModule$downloadAsset$2(URI uri, AssetModule assetModule, File file, Continuation<? super AssetModule$downloadAsset$2> continuation) {
        super(2, continuation);
        this.$uri = uri;
        this.this$0 = assetModule;
        this.$localUrl = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssetModule$downloadAsset$2(this.$uri, this.this$0, this.$localUrl, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Uri> continuation) {
        return ((AssetModule$downloadAsset$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputStream openStream;
        Context context;
        Context context2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            String uri = this.$uri.toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            if (!StringsKt.contains$default((CharSequence) uri, (CharSequence) ":", false, 2, (Object) null)) {
                context2 = this.this$0.getContext();
                String uri2 = this.$uri.toString();
                Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
                openStream = ResourceAssetKt.openAssetResourceStream(context2, uri2);
            } else {
                String uri3 = this.$uri.toString();
                Intrinsics.checkNotNullExpressionValue(uri3, "toString(...)");
                if (StringsKt.startsWith$default(uri3, ResourceAssetKt.ANDROID_EMBEDDED_URL_BASE_RESOURCE, false, 2, (Object) null)) {
                    context = this.this$0.getContext();
                    String uri4 = this.$uri.toString();
                    Intrinsics.checkNotNullExpressionValue(uri4, "toString(...)");
                    openStream = ResourceAssetKt.openAndroidResStream(context, uri4);
                } else {
                    openStream = this.$uri.toURL().openStream();
                }
            }
            FileOutputStream fileOutputStream = openStream;
            File file = this.$localUrl;
            try {
                InputStream inputStream = fileOutputStream;
                fileOutputStream = new FileOutputStream(file);
                try {
                    Intrinsics.checkNotNull(inputStream);
                    if (ByteStreamsKt.copyTo$default(inputStream, fileOutputStream, 0, 2, null) == 0) {
                        Log.w("ExpoAsset", "Asset downloaded to " + file + " is empty. It might be conflicting with another asset, or corrupted.");
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, null);
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, null);
                    return Uri.fromFile(this.$localUrl);
                } finally {
                }
            } finally {
            }
        } catch (Exception unused) {
            String uri5 = this.$uri.toString();
            Intrinsics.checkNotNullExpressionValue(uri5, "toString(...)");
            throw new UnableToDownloadAssetException(uri5);
        }
    }
}
