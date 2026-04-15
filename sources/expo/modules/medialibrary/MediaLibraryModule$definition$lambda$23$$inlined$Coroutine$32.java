package expo.modules.medialibrary;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "<destruct>", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$3"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32", f = "MediaLibraryModule.kt", i = {0, 0}, l = {332, 333}, m = "invokeSuspend", n = {"assets", "albumDir"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MediaLibraryModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32(Continuation continuation, MediaLibraryModule mediaLibraryModule) {
        super(3, continuation);
        this.this$0 = mediaLibraryModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32 mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32 = new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32(continuation, this.this$0);
        mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32.L$0 = objArr;
        return mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x015c, code lost:
    
        if (expo.modules.medialibrary.albums.migration.MigrateAlbumKt.migrateAlbum(r14, r4, r1, r13) == r0) goto L49;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$32.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
