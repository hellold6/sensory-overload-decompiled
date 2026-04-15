package expo.modules.medialibrary.next.objects.asset.delegates;

import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetModernDelegate.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.delegates.AssetModernDelegate", f = "AssetModernDelegate.kt", i = {1, 2, 2, 3, 3, 3}, l = {114, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 117, 118}, m = "getInfo", n = {"mediaStoreItem", "mediaStoreItem", "mediaType", "mediaStoreItem", "mediaType", "height"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
public final class AssetModernDelegate$getInfo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AssetModernDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetModernDelegate$getInfo$1(AssetModernDelegate assetModernDelegate, Continuation<? super AssetModernDelegate$getInfo$1> continuation) {
        super(continuation);
        this.this$0 = assetModernDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getInfo(this);
    }
}
