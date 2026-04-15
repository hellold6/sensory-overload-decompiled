package expo.modules.medialibrary.next;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "<destruct>", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$3"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$11", f = "MediaLibraryNextModule.kt", i = {0}, l = {272, 283}, m = "invokeSuspend", n = {"destination$iv$iv"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$11 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MediaLibraryNextModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$11(Continuation continuation, MediaLibraryNextModule mediaLibraryNextModule) {
        super(3, continuation);
        this.this$0 = mediaLibraryNextModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$11 mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$11 = new MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$11(continuation, this.this$0);
        mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$11.L$0 = objArr;
        return mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$11.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00b3, code lost:
    
        if (r8.delete(r1, r7) == r0) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0071  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0069 -> B:12:0x006a). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 10
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L2d
            if (r1 == r4) goto L1d
            if (r1 != r3) goto L15
            kotlin.ResultKt.throwOnFailure(r8)
            goto Lb6
        L15:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1d:
            java.lang.Object r1 = r7.L$2
            java.util.Collection r1 = (java.util.Collection) r1
            java.lang.Object r5 = r7.L$1
            java.util.Iterator r5 = (java.util.Iterator) r5
            java.lang.Object r6 = r7.L$0
            java.util.Collection r6 = (java.util.Collection) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6a
        L2d:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            java.lang.Object[] r8 = (java.lang.Object[]) r8
            r1 = 0
            r8 = r8[r1]
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            java.util.List r8 = (java.util.List) r8
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r1 = new java.util.ArrayList
            int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r8, r2)
            r1.<init>(r5)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r8 = r8.iterator()
            r5 = r8
        L4e:
            boolean r8 = r5.hasNext()
            if (r8 == 0) goto L71
            java.lang.Object r8 = r5.next()
            expo.modules.medialibrary.next.objects.album.Album r8 = (expo.modules.medialibrary.next.objects.album.Album) r8
            r7.L$0 = r1
            r7.L$1 = r5
            r7.L$2 = r1
            r7.label = r4
            java.lang.Object r8 = r8.getAssets(r7)
            if (r8 != r0) goto L69
            goto Lb5
        L69:
            r6 = r1
        L6a:
            java.util.List r8 = (java.util.List) r8
            r1.add(r8)
            r1 = r6
            goto L4e
        L71:
            java.util.List r1 = (java.util.List) r1
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.List r8 = kotlin.collections.CollectionsKt.flatten(r1)
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r8, r2)
            r1.<init>(r2)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r8 = r8.iterator()
        L8a:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L9e
            java.lang.Object r2 = r8.next()
            expo.modules.medialibrary.next.objects.asset.Asset r2 = (expo.modules.medialibrary.next.objects.asset.Asset) r2
            android.net.Uri r2 = r2.getContentUri()
            r1.add(r2)
            goto L8a
        L9e:
            java.util.List r1 = (java.util.List) r1
            expo.modules.medialibrary.next.MediaLibraryNextModule r8 = r7.this$0
            expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter r8 = expo.modules.medialibrary.next.MediaLibraryNextModule.access$getAssetDeleter(r8)
            r2 = 0
            r7.L$0 = r2
            r7.L$1 = r2
            r7.L$2 = r2
            r7.label = r3
            java.lang.Object r8 = r8.delete(r1, r7)
            if (r8 != r0) goto Lb6
        Lb5:
            return r0
        Lb6:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$11.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
