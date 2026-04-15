package expo.modules.medialibrary.next;

import expo.modules.medialibrary.next.objects.asset.Asset;
import expo.modules.medialibrary.next.objects.asset.deleters.AssetDeleter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "<destruct>", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$3"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$13", f = "MediaLibraryNextModule.kt", i = {}, l = {271}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$13 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaLibraryNextModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$13(Continuation continuation, MediaLibraryNextModule mediaLibraryNextModule) {
        super(3, continuation);
        this.this$0 = mediaLibraryNextModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$13 mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$13 = new MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$13(continuation, this.this$0);
        mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$13.L$0 = objArr;
        return mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$13.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AssetDeleter assetDeleter;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            List list = (List) ((Object[]) this.L$0)[0];
            assetDeleter = this.this$0.getAssetDeleter();
            List list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(((Asset) it.next()).getContentUri());
            }
            this.label = 1;
            if (assetDeleter.delete(arrayList, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
