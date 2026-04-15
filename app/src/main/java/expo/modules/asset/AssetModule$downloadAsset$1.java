package expo.modules.asset;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetModule.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.asset.AssetModule", f = "AssetModule.kt", i = {}, l = {57}, m = "downloadAsset", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AssetModule$downloadAsset$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AssetModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetModule$downloadAsset$1(AssetModule assetModule, Continuation<? super AssetModule$downloadAsset$1> continuation) {
        super(continuation);
        this.this$0 = assetModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object downloadAsset;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        downloadAsset = this.this$0.downloadAsset(null, null, null, this);
        return downloadAsset;
    }
}
