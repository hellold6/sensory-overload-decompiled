package expo.modules.medialibrary.next.objects.album.factories;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumLegacyFactory.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.album.factories.AlbumLegacyFactory", f = "AlbumLegacyFactory.kt", i = {0, 0, 1, 1}, l = {72, 74}, m = "processAssetsLocation-KTsBHyQ", n = {"relativePath", "destination$iv$iv", "relativePath", "destination$iv$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class AlbumLegacyFactory$processAssetsLocation$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlbumLegacyFactory this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumLegacyFactory$processAssetsLocation$1(AlbumLegacyFactory albumLegacyFactory, Continuation<? super AlbumLegacyFactory$processAssetsLocation$1> continuation) {
        super(continuation);
        this.this$0 = albumLegacyFactory;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m1288processAssetsLocationKTsBHyQ;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        m1288processAssetsLocationKTsBHyQ = this.this$0.m1288processAssetsLocationKTsBHyQ(null, null, false, this);
        return m1288processAssetsLocationKTsBHyQ;
    }
}
