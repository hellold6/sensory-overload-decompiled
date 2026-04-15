package expo.modules.medialibrary.next.objects.asset.delegates;

import androidx.media3.extractor.ts.TsExtractor;
import expo.modules.medialibrary.next.objects.wrappers.MimeType;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetModernDelegate.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.delegates.AssetModernDelegate", f = "AssetModernDelegate.kt", i = {}, l = {TsExtractor.TS_STREAM_TYPE_DTS}, m = "getMimeType-dctPOJs", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AssetModernDelegate$getMimeType$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AssetModernDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetModernDelegate$getMimeType$1(AssetModernDelegate assetModernDelegate, Continuation<? super AssetModernDelegate$getMimeType$1> continuation) {
        super(continuation);
        this.this$0 = assetModernDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object mo1295getMimeTypedctPOJs = this.this$0.mo1295getMimeTypedctPOJs(this);
        return mo1295getMimeTypedctPOJs == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mo1295getMimeTypedctPOJs : MimeType.m1308boximpl((String) mo1295getMimeTypedctPOJs);
    }
}
