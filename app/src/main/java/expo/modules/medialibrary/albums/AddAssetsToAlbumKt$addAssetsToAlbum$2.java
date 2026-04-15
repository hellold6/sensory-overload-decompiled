package expo.modules.medialibrary.albums;

import android.content.Context;
import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AddAssetsToAlbum.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.AddAssetsToAlbumKt$addAssetsToAlbum$2", f = "AddAssetsToAlbum.kt", i = {0, 0}, l = {26, ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG}, m = "invokeSuspend", n = {"$this$withContext", "strategy"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
final class AddAssetsToAlbumKt$addAssetsToAlbum$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ String $albumId;
    final /* synthetic */ String[] $assetIds;
    final /* synthetic */ Context $context;
    final /* synthetic */ boolean $copyToAlbum;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddAssetsToAlbumKt$addAssetsToAlbum$2(boolean z, Context context, String str, String[] strArr, Continuation<? super AddAssetsToAlbumKt$addAssetsToAlbum$2> continuation) {
        super(2, continuation);
        this.$copyToAlbum = z;
        this.$context = context;
        this.$albumId = str;
        this.$assetIds = strArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AddAssetsToAlbumKt$addAssetsToAlbum$2 addAssetsToAlbumKt$addAssetsToAlbum$2 = new AddAssetsToAlbumKt$addAssetsToAlbum$2(this.$copyToAlbum, this.$context, this.$albumId, this.$assetIds, continuation);
        addAssetsToAlbumKt$addAssetsToAlbum$2.L$0 = obj;
        return addAssetsToAlbumKt$addAssetsToAlbum$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((AddAssetsToAlbumKt$addAssetsToAlbum$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0051, code lost:
    
        if (r10 == r0) goto L31;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.albums.AddAssetsToAlbumKt$addAssetsToAlbum$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(AtomicInteger atomicInteger, CompletableDeferred completableDeferred, String str, Uri uri) {
        if (atomicInteger.decrementAndGet() == 0) {
            completableDeferred.complete(true);
        }
    }
}
