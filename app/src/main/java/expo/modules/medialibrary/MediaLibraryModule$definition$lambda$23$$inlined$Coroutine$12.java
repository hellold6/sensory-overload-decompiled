package expo.modules.medialibrary;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "<destruct>", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$5"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12", f = "MediaLibraryModule.kt", i = {0, 0}, l = {272, 273}, m = "invokeSuspend", n = {"albumId", "assetsId"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MediaLibraryModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12(Continuation continuation, MediaLibraryModule mediaLibraryModule) {
        super(3, continuation);
        this.this$0 = mediaLibraryModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12 mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12 = new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12(continuation, this.this$0);
        mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12.L$0 = objArr;
        return mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0067, code lost:
    
        if (expo.modules.medialibrary.albums.RemoveAssetsFromAlbumKt.removeAssetsFromAlbum(r13, r1, r4, r12) == r0) goto L16;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L29
            if (r1 == r4) goto L1c
            if (r1 != r2) goto L14
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r12
            goto L6a
        L14:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L1c:
            java.lang.Object r1 = r12.L$1
            java.lang.String[] r1 = (java.lang.String[]) r1
            java.lang.Object r4 = r12.L$0
            java.lang.String r4 = (java.lang.String) r4
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r12
            goto L57
        L29:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            java.lang.Object[] r13 = (java.lang.Object[]) r13
            r1 = 0
            r5 = r13[r1]
            r13 = r13[r4]
            r6 = r12
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            java.lang.String r13 = (java.lang.String) r13
            r7 = r5
            java.lang.String[] r7 = (java.lang.String[]) r7
            expo.modules.medialibrary.MediaLibraryModule r5 = r12.this$0
            expo.modules.medialibrary.MediaLibraryModule.requireSystemPermissions$default(r5, r1, r4, r3)
            expo.modules.medialibrary.MediaLibraryModule r6 = r12.this$0
            r12.L$0 = r13
            r12.L$1 = r7
            r12.label = r4
            r8 = 0
            r10 = 2
            r11 = 0
            r9 = r12
            java.lang.Object r1 = expo.modules.medialibrary.MediaLibraryModule.requestMediaLibraryActionPermission$default(r6, r7, r8, r9, r10, r11)
            if (r1 != r0) goto L55
            goto L69
        L55:
            r4 = r13
            r1 = r7
        L57:
            expo.modules.medialibrary.MediaLibraryModule r13 = r9.this$0
            android.content.Context r13 = expo.modules.medialibrary.MediaLibraryModule.access$getContext(r13)
            r9.L$0 = r3
            r9.L$1 = r3
            r9.label = r2
            java.lang.Object r13 = expo.modules.medialibrary.albums.RemoveAssetsFromAlbumKt.removeAssetsFromAlbum(r13, r1, r4, r12)
            if (r13 != r0) goto L6a
        L69:
            return r0
        L6a:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$12.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
