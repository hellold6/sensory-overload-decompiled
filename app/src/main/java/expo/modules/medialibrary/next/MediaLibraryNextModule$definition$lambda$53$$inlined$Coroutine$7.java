package expo.modules.medialibrary.next;

import android.net.Uri;
import expo.modules.kotlin.types.Either;
import expo.modules.medialibrary.next.objects.album.factories.AlbumFactory;
import expo.modules.medialibrary.next.objects.asset.Asset;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "<destruct>", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$7"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$7", f = "MediaLibraryNextModule.kt", i = {}, l = {275, 279}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$7 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaLibraryNextModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$7(Continuation continuation, MediaLibraryNextModule mediaLibraryNextModule) {
        super(3, continuation);
        this.this$0 = mediaLibraryNextModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$7 mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$7 = new MediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$7(continuation, this.this$0);
        mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$7.L$0 = objArr;
        return mediaLibraryNextModule$definition$lambda$53$$inlined$Coroutine$7.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AlbumFactory albumFactory;
        AlbumFactory albumFactory2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        Object[] objArr = (Object[]) this.L$0;
        Object obj2 = objArr[0];
        Object obj3 = objArr[1];
        boolean booleanValue = ((Boolean) objArr[2]).booleanValue();
        Either either = (Either) obj3;
        String str = (String) obj2;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(List.class);
        if (either.isFirstType(orCreateKotlinClass)) {
            List<Asset> list = (List) either.getFirstType(orCreateKotlinClass);
            albumFactory2 = this.this$0.getAlbumFactory();
            this.label = 1;
            Object createFromAssets = albumFactory2.createFromAssets(str, list, booleanValue, this);
            if (createFromAssets != coroutine_suspended) {
                return createFromAssets;
            }
        } else {
            List<? extends Uri> list2 = (List) either.getSecondType(Reflection.getOrCreateKotlinClass(List.class));
            albumFactory = this.this$0.getAlbumFactory();
            this.label = 2;
            Object createFromFilePaths = albumFactory.createFromFilePaths(str, list2, this);
            if (createFromFilePaths != coroutine_suspended) {
                return createFromFilePaths;
            }
        }
        return coroutine_suspended;
    }
}
