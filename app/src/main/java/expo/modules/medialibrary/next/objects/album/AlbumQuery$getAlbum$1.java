package expo.modules.medialibrary.next.objects.album;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumQuery.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.album.AlbumQuery", f = "AlbumQuery.kt", i = {}, l = {19}, m = "getAlbum", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AlbumQuery$getAlbum$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlbumQuery this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumQuery$getAlbum$1(AlbumQuery albumQuery, Continuation<? super AlbumQuery$getAlbum$1> continuation) {
        super(continuation);
        this.this$0 = albumQuery;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getAlbum(null, this);
    }
}
