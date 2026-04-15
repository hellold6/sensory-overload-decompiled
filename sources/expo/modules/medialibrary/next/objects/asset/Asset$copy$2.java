package expo.modules.medialibrary.next.objects.asset;

import androidx.core.text.HtmlCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Asset.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.Asset$copy$2", f = "Asset.kt", i = {}, l = {HtmlCompat.FROM_HTML_MODE_COMPACT}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class Asset$copy$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Asset>, Object> {
    final /* synthetic */ String $relativePath;
    int label;
    final /* synthetic */ Asset this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Asset$copy$2(Asset asset, String str, Continuation<? super Asset$copy$2> continuation) {
        super(2, continuation);
        this.this$0 = asset;
        this.$relativePath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Asset$copy$2(this.this$0, this.$relativePath, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Asset> continuation) {
        return ((Asset$copy$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        this.label = 1;
        Object mo1294copydXLngQ8 = this.this$0.getAssetDelegate().mo1294copydXLngQ8(this.$relativePath, this);
        return mo1294copydXLngQ8 == coroutine_suspended ? coroutine_suspended : mo1294copydXLngQ8;
    }
}
