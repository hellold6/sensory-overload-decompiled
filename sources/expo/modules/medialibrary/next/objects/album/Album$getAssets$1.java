package expo.modules.medialibrary.next.objects.album;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Album.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.album.Album", f = "Album.kt", i = {}, l = {60}, m = "getAssets", n = {}, s = {})
/* loaded from: classes3.dex */
public final class Album$getAssets$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Album this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Album$getAssets$1(Album album, Continuation<? super Album$getAssets$1> continuation) {
        super(continuation);
        this.this$0 = album;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getAssets(this);
    }
}
