package expo.modules.medialibrary;

import android.content.Context;
import expo.modules.medialibrary.albums.AddAssetsToAlbumKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "<destruct>", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$7"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9", f = "MediaLibraryModule.kt", i = {0, 0, 0}, l = {272, 273}, m = "invokeSuspend", n = {"albumId", "assetsId", "copyToAlbum"}, s = {"L$0", "L$1", "Z$0"})
/* loaded from: classes3.dex */
public final class MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    final /* synthetic */ MediaLibraryModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9(Continuation continuation, MediaLibraryModule mediaLibraryModule) {
        super(3, continuation);
        this.this$0 = mediaLibraryModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9 mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9 = new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9(continuation, this.this$0);
        mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9.L$0 = objArr;
        return mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9 mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9;
        boolean z;
        String[] strArr;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = (Object[]) this.L$0;
            Object obj2 = objArr[0];
            Object obj3 = objArr[1];
            boolean booleanValue = ((Boolean) objArr[2]).booleanValue();
            String str2 = (String) obj3;
            String[] strArr2 = (String[]) obj2;
            MediaLibraryModule.requireSystemPermissions$default(this.this$0, false, 1, null);
            MediaLibraryModule mediaLibraryModule = this.this$0;
            String[] strArr3 = booleanValue ? new String[0] : strArr2;
            this.L$0 = str2;
            this.L$1 = strArr2;
            this.Z$0 = booleanValue;
            this.label = 1;
            mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9 = this;
            if (MediaLibraryModule.requestMediaLibraryActionPermission$default(mediaLibraryModule, strArr3, false, mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9, 2, null) != coroutine_suspended) {
                z = booleanValue;
                strArr = strArr2;
                str = str2;
            }
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        z = this.Z$0;
        strArr = (String[]) this.L$1;
        str = (String) this.L$0;
        ResultKt.throwOnFailure(obj);
        mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9 = this;
        Context context = mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9.this$0.getContext();
        mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9.L$0 = null;
        mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9.L$1 = null;
        mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$9.label = 2;
        Object addAssetsToAlbum = AddAssetsToAlbumKt.addAssetsToAlbum(context, strArr, str, z, this);
        return addAssetsToAlbum == coroutine_suspended ? coroutine_suspended : addAssetsToAlbum;
    }
}
