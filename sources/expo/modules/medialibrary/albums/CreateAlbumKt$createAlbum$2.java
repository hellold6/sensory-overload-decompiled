package expo.modules.medialibrary.albums;

import android.content.Context;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: CreateAlbum.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/os/Bundle;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.CreateAlbumKt$createAlbum$2", f = "CreateAlbum.kt", i = {0}, l = {32, 39}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
/* loaded from: classes3.dex */
final class CreateAlbumKt$createAlbum$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bundle>, Object> {
    final /* synthetic */ String $albumName;
    final /* synthetic */ String $assetId;
    final /* synthetic */ Context $context;
    final /* synthetic */ boolean $copyAsset;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreateAlbumKt$createAlbum$2(boolean z, Context context, String str, String str2, Continuation<? super CreateAlbumKt$createAlbum$2> continuation) {
        super(2, continuation);
        this.$copyAsset = z;
        this.$context = context;
        this.$assetId = str;
        this.$albumName = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CreateAlbumKt$createAlbum$2 createAlbumKt$createAlbum$2 = new CreateAlbumKt$createAlbum$2(this.$copyAsset, this.$context, this.$assetId, this.$albumName, continuation);
        createAlbumKt$createAlbum$2.L$0 = obj;
        return createAlbumKt$createAlbum$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bundle> continuation) {
        return ((CreateAlbumKt$createAlbum$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x00b5, code lost:
    
        if (r11 == r0) goto L29;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.albums.CreateAlbumKt$createAlbum$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
