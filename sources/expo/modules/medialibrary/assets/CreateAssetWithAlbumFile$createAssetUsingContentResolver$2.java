package expo.modules.medialibrary.assets;

import android.os.Bundle;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CreateAsset.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u0003*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "Lkotlin/collections/ArrayList;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.assets.CreateAssetWithAlbumFile$createAssetUsingContentResolver$2", f = "CreateAsset.kt", i = {0, 0}, l = {110, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID}, m = "invokeSuspend", n = {"$this$withContext", "assetUri"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class CreateAssetWithAlbumFile$createAssetUsingContentResolver$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<Bundle>>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ CreateAssetWithAlbumFile this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreateAssetWithAlbumFile$createAssetUsingContentResolver$2(CreateAssetWithAlbumFile createAssetWithAlbumFile, Continuation<? super CreateAssetWithAlbumFile$createAssetUsingContentResolver$2> continuation) {
        super(2, continuation);
        this.this$0 = createAssetWithAlbumFile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CreateAssetWithAlbumFile$createAssetUsingContentResolver$2 createAssetWithAlbumFile$createAssetUsingContentResolver$2 = new CreateAssetWithAlbumFile$createAssetUsingContentResolver$2(this.this$0, continuation);
        createAssetWithAlbumFile$createAssetUsingContentResolver$2.L$0 = obj;
        return createAssetWithAlbumFile$createAssetUsingContentResolver$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArrayList<Bundle>> continuation) {
        return ((CreateAssetWithAlbumFile$createAssetUsingContentResolver$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0057, code lost:
    
        if (r8 == r0) goto L20;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L26
            if (r1 == r3) goto L1a
            if (r1 != r2) goto L12
            kotlin.ResultKt.throwOnFailure(r8)
            return r8
        L12:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1a:
            java.lang.Object r1 = r7.L$1
            android.net.Uri r1 = (android.net.Uri) r1
            java.lang.Object r4 = r7.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5a
        L26:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            r4 = r8
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            expo.modules.medialibrary.assets.CreateAssetWithAlbumFile r8 = r7.this$0
            android.net.Uri r1 = expo.modules.medialibrary.assets.CreateAssetWithAlbumFile.access$createContentResolverAssetEntry(r8)
            if (r1 == 0) goto L91
            expo.modules.medialibrary.assets.CreateAssetWithAlbumFile r8 = r7.this$0
            java.io.File r5 = new java.io.File
            expo.modules.medialibrary.assets.CreateAssetWithAlbumFile r6 = r7.this$0
            android.net.Uri r6 = expo.modules.medialibrary.assets.CreateAssetWithAlbumFile.access$getMUri$p(r6)
            java.lang.String r6 = r6.getPath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r5.<init>(r6)
            r6 = r7
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7.L$0 = r4
            r7.L$1 = r1
            r7.label = r3
            java.lang.Object r8 = expo.modules.medialibrary.assets.CreateAssetWithAlbumFile.access$writeFileContentsToAsset(r8, r5, r1, r6)
            if (r8 != r0) goto L5a
            goto L8e
        L5a:
            kotlin.coroutines.CoroutineContext r8 = r4.getCoroutineContext()
            kotlinx.coroutines.JobKt.ensureActive(r8)
            expo.modules.medialibrary.assets.CreateAssetWithAlbumFile r8 = r7.this$0
            boolean r8 = expo.modules.medialibrary.assets.CreateAssetWithAlbumFile.access$getResolveWithAdditionalData$p(r8)
            r4 = 0
            if (r8 == 0) goto L90
            java.lang.String[] r8 = new java.lang.String[r3]
            long r5 = android.content.ContentUris.parseId(r1)
            java.lang.String r1 = java.lang.String.valueOf(r5)
            r3 = 0
            r8[r3] = r1
            expo.modules.medialibrary.assets.CreateAssetWithAlbumFile r1 = r7.this$0
            android.content.Context r1 = expo.modules.medialibrary.assets.CreateAssetWithAlbumFile.access$getContext$p(r1)
            r5 = r7
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r7.L$0 = r4
            r7.L$1 = r4
            r7.label = r2
            java.lang.String r2 = "_id=?"
            java.lang.Object r8 = expo.modules.medialibrary.assets.AssetUtilsKt.queryAssetInfo(r1, r2, r8, r3, r5)
            if (r8 != r0) goto L8f
        L8e:
            return r0
        L8f:
            return r8
        L90:
            return r4
        L91:
            expo.modules.medialibrary.ContentEntryException r8 = new expo.modules.medialibrary.ContentEntryException
            r8.<init>()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.assets.CreateAssetWithAlbumFile$createAssetUsingContentResolver$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
