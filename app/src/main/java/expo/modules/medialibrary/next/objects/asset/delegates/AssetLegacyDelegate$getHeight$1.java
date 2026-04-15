package expo.modules.medialibrary.next.objects.asset.delegates;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetLegacyDelegate.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate", f = "AssetLegacyDelegate.kt", i = {}, l = {87, 88}, m = "getHeight", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AssetLegacyDelegate$getHeight$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AssetLegacyDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetLegacyDelegate$getHeight$1(AssetLegacyDelegate assetLegacyDelegate, Continuation<? super AssetLegacyDelegate$getHeight$1> continuation) {
        super(continuation);
        this.this$0 = assetLegacyDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getHeight(this);
    }
}
