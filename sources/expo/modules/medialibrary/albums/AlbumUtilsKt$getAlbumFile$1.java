package expo.modules.medialibrary.albums;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumUtils.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.AlbumUtilsKt", f = "AlbumUtils.kt", i = {}, l = {133}, m = "getAlbumFile", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AlbumUtilsKt$getAlbumFile$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlbumUtilsKt$getAlbumFile$1(Continuation<? super AlbumUtilsKt$getAlbumFile$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AlbumUtilsKt.getAlbumFile(null, null, this);
    }
}
