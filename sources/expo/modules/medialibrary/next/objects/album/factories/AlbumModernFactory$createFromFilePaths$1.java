package expo.modules.medialibrary.next.objects.album.factories;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumModernFactory.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory", f = "AlbumModernFactory.kt", i = {0}, l = {57, 59}, m = "createFromFilePaths", n = {"relativePath"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class AlbumModernFactory$createFromFilePaths$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlbumModernFactory this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumModernFactory$createFromFilePaths$1(AlbumModernFactory albumModernFactory, Continuation<? super AlbumModernFactory$createFromFilePaths$1> continuation) {
        super(continuation);
        this.this$0 = albumModernFactory;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.createFromFilePaths(null, null, this);
    }
}
