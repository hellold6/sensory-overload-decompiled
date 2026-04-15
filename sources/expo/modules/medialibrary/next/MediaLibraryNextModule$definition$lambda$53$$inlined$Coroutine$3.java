package expo.modules.medialibrary.next;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "<destruct>", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$5"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$3", f = "MediaLibraryNextModule.kt", i = {}, l = {271, 271}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$3 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MediaLibraryNextModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$3(Continuation continuation, MediaLibraryNextModule mediaLibraryNextModule) {
        super(3, continuation);
        this.this$0 = mediaLibraryNextModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$3 mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$3 = new MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$3(continuation, this.this$0);
        mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$3.L$0 = objArr;
        return mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$3.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0069 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006a A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L2d
            if (r1 == r4) goto L1b
            if (r1 != r2) goto L13
            kotlin.ResultKt.throwOnFailure(r7)
            return r7
        L13:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1b:
            java.lang.Object r1 = r6.L$1
            expo.modules.medialibrary.next.objects.asset.factories.AssetFactory r1 = (expo.modules.medialibrary.next.objects.asset.factories.AssetFactory) r1
            java.lang.Object r4 = r6.L$0
            android.net.Uri r4 = (android.net.Uri) r4
            kotlin.ResultKt.throwOnFailure(r7)
            expo.modules.medialibrary.next.objects.wrappers.RelativePath r7 = (expo.modules.medialibrary.next.objects.wrappers.RelativePath) r7
            java.lang.String r7 = r7.m1330unboximpl()
            goto L57
        L2d:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            r1 = 0
            r1 = r7[r1]
            r7 = r7[r4]
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            expo.modules.medialibrary.next.objects.album.Album r7 = (expo.modules.medialibrary.next.objects.album.Album) r7
            android.net.Uri r1 = (android.net.Uri) r1
            expo.modules.medialibrary.next.MediaLibraryNextModule r5 = r6.this$0
            expo.modules.medialibrary.next.objects.asset.factories.AssetFactory r5 = expo.modules.medialibrary.next.MediaLibraryNextModule.access$getAssetFactory(r5)
            if (r7 == 0) goto L5c
            r6.L$0 = r1
            r6.L$1 = r5
            r6.label = r4
            java.lang.Object r7 = r7.m1285getRelativePathMwbCjzw(r6)
            if (r7 != r0) goto L55
            goto L69
        L55:
            r4 = r1
            r1 = r5
        L57:
            java.lang.String r7 = (java.lang.String) r7
            r5 = r1
            r1 = r4
            goto L5d
        L5c:
            r7 = r3
        L5d:
            r6.L$0 = r3
            r6.L$1 = r3
            r6.label = r2
            java.lang.Object r7 = r5.mo1300createBuevYFM(r1, r7, r6)
            if (r7 != r0) goto L6a
        L69:
            return r0
        L6a:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
