package expo.modules.medialibrary.assets;

import com.facebook.imagepipeline.common.RotationOptions;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CreateAsset.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.assets.CreateAssetKt", f = "CreateAsset.kt", i = {0, 0, 0}, l = {179, RotationOptions.ROTATE_180}, m = "createAssetWithAlbumId", n = {"context", "uri", "resolveWithAdditionalData"}, s = {"L$0", "L$1", "Z$0"})
/* loaded from: classes3.dex */
public final class CreateAssetKt$createAssetWithAlbumId$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CreateAssetKt$createAssetWithAlbumId$1(Continuation<? super CreateAssetKt$createAssetWithAlbumId$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CreateAssetKt.createAssetWithAlbumId(null, null, false, null, this);
    }
}
