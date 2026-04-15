package expo.modules.medialibrary.assets;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CreateAsset.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.assets.CreateAssetWithAlbumFile", f = "CreateAsset.kt", i = {}, l = {146, 151, 160}, m = "execute", n = {}, s = {})
/* loaded from: classes3.dex */
public final class CreateAssetWithAlbumFile$execute$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CreateAssetWithAlbumFile this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreateAssetWithAlbumFile$execute$1(CreateAssetWithAlbumFile createAssetWithAlbumFile, Continuation<? super CreateAssetWithAlbumFile$execute$1> continuation) {
        super(continuation);
        this.this$0 = createAssetWithAlbumFile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.execute(this);
    }
}
