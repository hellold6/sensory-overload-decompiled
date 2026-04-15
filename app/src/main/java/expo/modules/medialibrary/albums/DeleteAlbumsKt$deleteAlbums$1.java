package expo.modules.medialibrary.albums;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeleteAlbums.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.DeleteAlbumsKt", f = "DeleteAlbums.kt", i = {0, 0, 0}, l = {12, 13}, m = "deleteAlbums", n = {"context", "albumIds", "selectionVideos"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
public final class DeleteAlbumsKt$deleteAlbums$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeleteAlbumsKt$deleteAlbums$1(Continuation<? super DeleteAlbumsKt$deleteAlbums$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DeleteAlbumsKt.deleteAlbums(null, null, this);
    }
}
