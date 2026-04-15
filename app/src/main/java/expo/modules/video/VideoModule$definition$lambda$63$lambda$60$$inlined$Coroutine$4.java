package expo.modules.video;

import android.media.MediaMetadataRetriever;
import expo.modules.video.player.VideoPlayer;
import expo.modules.video.records.VideoThumbnailOptions;
import java.util.List;
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
@DebugMetadata(c = "expo.modules.video.VideoModule$definition$lambda$63$lambda$60$$inlined$Coroutine$4", f = "VideoModule.kt", i = {}, l = {271}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class VideoModule$definition$lambda$63$lambda$60$$inlined$Coroutine$4 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoModule$definition$lambda$63$lambda$60$$inlined$Coroutine$4(Continuation continuation, VideoModule videoModule) {
        super(3, continuation);
        this.this$0 = videoModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        VideoModule$definition$lambda$63$lambda$60$$inlined$Coroutine$4 videoModule$definition$lambda$63$lambda$60$$inlined$Coroutine$4 = new VideoModule$definition$lambda$63$lambda$60$$inlined$Coroutine$4(continuation, this.this$0);
        videoModule$definition$lambda$63$lambda$60$$inlined$Coroutine$4.L$0 = objArr;
        return videoModule$definition$lambda$63$lambda$60$$inlined$Coroutine$4.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        Object[] objArr = (Object[]) this.L$0;
        Object obj2 = objArr[0];
        Object obj3 = objArr[1];
        VideoThumbnailOptions videoThumbnailOptions = (VideoThumbnailOptions) objArr[2];
        MediaMetadataRetriever metadataRetriever = ((VideoPlayer) obj2).toMetadataRetriever();
        VideoModule$definition$1$8$48$1 videoModule$definition$1$8$48$1 = new VideoModule$definition$1$8$48$1((List) obj3, this.this$0, videoThumbnailOptions, null);
        this.label = 1;
        Object safeUse = MediaMetadataRetrieverKt.safeUse(metadataRetriever, videoModule$definition$1$8$48$1, this);
        return safeUse == coroutine_suspended ? coroutine_suspended : safeUse;
    }
}
