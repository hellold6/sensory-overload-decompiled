package expo.modules.medialibrary.next.objects.album.factories;

import androidx.media3.container.MdtaMetadataEntry;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumModernFactory.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.album.factories.AlbumModernFactory", f = "AlbumModernFactory.kt", i = {0, 0, 1, 1, 2, 2}, l = {66, MdtaMetadataEntry.TYPE_INDICATOR_INT32, 69}, m = "processAssetsLocation-KTsBHyQ", n = {"assets", "relativePath", "relativePath", "destination$iv$iv", "relativePath", "destination$iv$iv"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class AlbumModernFactory$processAssetsLocation$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlbumModernFactory this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumModernFactory$processAssetsLocation$1(AlbumModernFactory albumModernFactory, Continuation<? super AlbumModernFactory$processAssetsLocation$1> continuation) {
        super(continuation);
        this.this$0 = albumModernFactory;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m1290processAssetsLocationKTsBHyQ;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        m1290processAssetsLocationKTsBHyQ = this.this$0.m1290processAssetsLocationKTsBHyQ(null, null, false, this);
        return m1290processAssetsLocationKTsBHyQ;
    }
}
