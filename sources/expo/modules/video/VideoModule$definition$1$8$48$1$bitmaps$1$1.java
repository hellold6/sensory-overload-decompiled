package expo.modules.video;

import android.media.MediaMetadataRetriever;
import expo.modules.video.records.VideoThumbnailOptions;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VideoModule.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lexpo/modules/video/VideoThumbnail;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.video.VideoModule$definition$1$8$48$1$bitmaps$1$1", f = "VideoModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class VideoModule$definition$1$8$48$1$bitmaps$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super VideoThumbnail>, Object> {
    final /* synthetic */ MediaMetadataRetriever $$this$safeUse;
    final /* synthetic */ VideoThumbnailOptions $options;
    final /* synthetic */ long $time;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoModule$definition$1$8$48$1$bitmaps$1$1(MediaMetadataRetriever mediaMetadataRetriever, long j, VideoThumbnailOptions videoThumbnailOptions, Continuation<? super VideoModule$definition$1$8$48$1$bitmaps$1$1> continuation) {
        super(2, continuation);
        this.$$this$safeUse = mediaMetadataRetriever;
        this.$time = j;
        this.$options = videoThumbnailOptions;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoModule$definition$1$8$48$1$bitmaps$1$1(this.$$this$safeUse, this.$time, this.$options, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super VideoThumbnail> continuation) {
        return ((VideoModule$definition$1$8$48$1$bitmaps$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return MediaMetadataRetrieverKt.m1387generateThumbnailAtTime8Mi8wO0(this.$$this$safeUse, this.$time, this.$options);
    }
}
