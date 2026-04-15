package expo.modules.medialibrary.next.objects.asset.delegates;

import android.content.ContentResolver;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import expo.modules.medialibrary.next.objects.asset.ExifTagsKt;
import expo.modules.medialibrary.next.objects.wrappers.MediaType;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: AssetModernDelegate.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/os/Bundle;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.delegates.AssetModernDelegate$getExif$2", f = "AssetModernDelegate.kt", i = {0}, l = {149}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
/* loaded from: classes3.dex */
final class AssetModernDelegate$getExif$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bundle>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AssetModernDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetModernDelegate$getExif$2(AssetModernDelegate assetModernDelegate, Continuation<? super AssetModernDelegate$getExif$2> continuation) {
        super(2, continuation);
        this.this$0 = assetModernDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AssetModernDelegate$getExif$2 assetModernDelegate$getExif$2 = new AssetModernDelegate$getExif$2(this.this$0, continuation);
        assetModernDelegate$getExif$2.L$0 = obj;
        return assetModernDelegate$getExif$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bundle> continuation) {
        return ((AssetModernDelegate$getExif$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ContentResolver contentResolver;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            this.L$0 = coroutineScope2;
            this.label = 1;
            Object mediaType = this.this$0.getMediaType(this);
            if (mediaType == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            obj = mediaType;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (obj != MediaType.IMAGE) {
            return new Bundle();
        }
        Bundle bundle = new Bundle();
        contentResolver = this.this$0.getContentResolver();
        InputStream openInputStream = contentResolver.openInputStream(this.this$0.getContentUri());
        if (openInputStream == null) {
            return bundle;
        }
        InputStream inputStream = openInputStream;
        try {
            CoroutineScopeKt.ensureActive(coroutineScope);
            ExifInterface exifInterface = new ExifInterface(inputStream);
            for (String[] strArr : ExifTagsKt.getEXIF_TAGS()) {
                String str = strArr[0];
                String str2 = strArr[1];
                if (exifInterface.getAttribute(str2) != null) {
                    int hashCode = str.hashCode();
                    if (hashCode != -1325958191) {
                        if (hashCode != -891985903) {
                            if (hashCode == 104431 && str.equals("int")) {
                                bundle.putInt(str2, exifInterface.getAttributeInt(str2, 0));
                            }
                        } else if (str.equals("string")) {
                            bundle.putString(str2, exifInterface.getAttribute(str2));
                        }
                    } else if (str.equals("double")) {
                        bundle.putDouble(str2, exifInterface.getAttributeDouble(str2, 0.0d));
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(inputStream, null);
            return bundle;
        } finally {
        }
    }
}
