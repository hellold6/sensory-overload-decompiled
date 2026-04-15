package expo.modules.medialibrary.albums;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CreateAlbum.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.CreateAlbumKt", f = "CreateAlbum.kt", i = {0, 0}, l = {64, 65}, m = "createAlbumWithInitialFileUri", n = {"context", "albumName"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class CreateAlbumKt$createAlbumWithInitialFileUri$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CreateAlbumKt$createAlbumWithInitialFileUri$1(Continuation<? super CreateAlbumKt$createAlbumWithInitialFileUri$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CreateAlbumKt.createAlbumWithInitialFileUri(null, null, null, this);
    }
}
