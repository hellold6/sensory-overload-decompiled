package expo.modules.medialibrary.next.objects.album.factories;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumLegacyFactory.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.album.factories.AlbumLegacyFactory", f = "AlbumLegacyFactory.kt", i = {0, 0, 1, 1, 2}, l = {43, 46, 47}, m = "createFromAssets", n = {"albumName", "assets", "assets", "relativePath", "relativePath"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0"})
/* loaded from: classes3.dex */
public final class AlbumLegacyFactory$createFromAssets$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlbumLegacyFactory this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumLegacyFactory$createFromAssets$1(AlbumLegacyFactory albumLegacyFactory, Continuation<? super AlbumLegacyFactory$createFromAssets$1> continuation) {
        super(continuation);
        this.this$0 = albumLegacyFactory;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.createFromAssets(null, null, false, this);
    }
}
