package expo.modules.medialibrary.next.objects.asset.delegates;

import expo.modules.medialibrary.next.objects.asset.Asset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetModernDelegate.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.delegates.AssetModernDelegate$copyInternal$2", f = "AssetModernDelegate.kt", i = {0, 1, 2, 3}, l = {193, 195, 199, 197, 220}, m = "invokeSuspend", n = {"$this$withContext", "$this$withContext", "$this$withContext", "$this$withContext"}, s = {"L$0", "L$0", "L$0", "L$0"})
/* loaded from: classes3.dex */
public final class AssetModernDelegate$copyInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Asset>, Object> {
    final /* synthetic */ boolean $forceUniqueName;
    final /* synthetic */ String $relativePath;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ AssetModernDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetModernDelegate$copyInternal$2(boolean z, AssetModernDelegate assetModernDelegate, String str, Continuation<? super AssetModernDelegate$copyInternal$2> continuation) {
        super(2, continuation);
        this.$forceUniqueName = z;
        this.this$0 = assetModernDelegate;
        this.$relativePath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AssetModernDelegate$copyInternal$2 assetModernDelegate$copyInternal$2 = new AssetModernDelegate$copyInternal$2(this.$forceUniqueName, this.this$0, this.$relativePath, continuation);
        assetModernDelegate$copyInternal$2.L$0 = obj;
        return assetModernDelegate$copyInternal$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Asset> continuation) {
        return ((AssetModernDelegate$copyInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x013f, code lost:
    
        if (r12 == r0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x006e, code lost:
    
        if (r12 == r0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0089, code lost:
    
        if (r12 == r0) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cc  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetModernDelegate$copyInternal$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
