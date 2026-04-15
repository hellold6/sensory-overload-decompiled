package expo.modules.medialibrary.next.objects.asset.deleters;

import android.content.ContentResolver;
import android.net.Uri;
import expo.modules.medialibrary.AssetFileException;
import expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException;
import expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetLegacyDeleter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.deleters.AssetLegacyDeleter$delete$2", f = "AssetLegacyDeleter.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AssetLegacyDeleter$delete$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Uri $contentUri;
    int label;
    final /* synthetic */ AssetLegacyDeleter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetLegacyDeleter$delete$2(AssetLegacyDeleter assetLegacyDeleter, Uri uri, Continuation<? super AssetLegacyDeleter$delete$2> continuation) {
        super(2, continuation);
        this.this$0 = assetLegacyDeleter;
        this.$contentUri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssetLegacyDeleter$delete$2(this.this$0, this.$contentUri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AssetLegacyDeleter$delete$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ContentResolver contentResolver;
        ContentResolver contentResolver2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.getSystemPermissionsDelegate().requireWritePermissions();
            contentResolver = this.this$0.getContentResolver();
            this.label = 1;
            obj = AssetExtensionsKt.queryAssetData(contentResolver, this.$contentUri, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        String str = (String) obj;
        if (str == null) {
            throw new AssetPropertyNotFoundException("Uri", null, 2, null);
        }
        if (new File(str).delete()) {
            contentResolver2 = this.this$0.getContentResolver();
            contentResolver2.delete(this.$contentUri, null, null);
            return Unit.INSTANCE;
        }
        throw new AssetFileException("Could not delete a file.");
    }
}
