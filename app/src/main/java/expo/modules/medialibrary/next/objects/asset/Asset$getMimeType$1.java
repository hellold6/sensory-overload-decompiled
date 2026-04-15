package expo.modules.medialibrary.next.objects.asset;

import expo.modules.medialibrary.next.objects.wrappers.MimeType;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Asset.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.Asset", f = "Asset.kt", i = {}, l = {50}, m = "getMimeType-dctPOJs", n = {}, s = {})
/* loaded from: classes3.dex */
public final class Asset$getMimeType$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Asset this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Asset$getMimeType$1(Asset asset, Continuation<? super Asset$getMimeType$1> continuation) {
        super(continuation);
        this.this$0 = asset;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object m1292getMimeTypedctPOJs = this.this$0.m1292getMimeTypedctPOJs(this);
        return m1292getMimeTypedctPOJs == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m1292getMimeTypedctPOJs : MimeType.m1308boximpl((String) m1292getMimeTypedctPOJs);
    }
}
