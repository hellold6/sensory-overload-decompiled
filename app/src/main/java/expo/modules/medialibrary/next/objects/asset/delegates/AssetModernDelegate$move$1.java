package expo.modules.medialibrary.next.objects.asset.delegates;

import com.facebook.imagepipeline.common.RotationOptions;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetModernDelegate.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.delegates.AssetModernDelegate", f = "AssetModernDelegate.kt", i = {0, 1}, l = {174, RotationOptions.ROTATE_180}, m = "move-dXLngQ8", n = {"relativePath", "relativePath"}, s = {"L$0", "L$0"})
/* loaded from: classes3.dex */
public final class AssetModernDelegate$move$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AssetModernDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetModernDelegate$move$1(AssetModernDelegate assetModernDelegate, Continuation<? super AssetModernDelegate$move$1> continuation) {
        super(continuation);
        this.this$0 = assetModernDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo1296movedXLngQ8(null, this);
    }
}
