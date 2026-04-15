package expo.modules.video;

import android.media.MediaMetadataRetriever;
import expo.modules.video.records.VideoThumbnailOptions;
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
import kotlin.jvm.functions.Function2;
import kotlin.time.Duration;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Deferred;

/* compiled from: VideoModule.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lexpo/modules/video/VideoThumbnail;", "Landroid/media/MediaMetadataRetriever;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.video.VideoModule$definition$1$8$48$1", f = "VideoModule.kt", i = {}, l = {336}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class VideoModule$definition$1$8$48$1 extends SuspendLambda implements Function2<MediaMetadataRetriever, Continuation<? super List<? extends VideoThumbnail>>, Object> {
    final /* synthetic */ VideoThumbnailOptions $options;
    final /* synthetic */ List<Duration> $times;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoModule$definition$1$8$48$1(List<Duration> list, VideoModule videoModule, VideoThumbnailOptions videoThumbnailOptions, Continuation<? super VideoModule$definition$1$8$48$1> continuation) {
        super(2, continuation);
        this.$times = list;
        this.this$0 = videoModule;
        this.$options = videoThumbnailOptions;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoModule$definition$1$8$48$1 videoModule$definition$1$8$48$1 = new VideoModule$definition$1$8$48$1(this.$times, this.this$0, this.$options, continuation);
        videoModule$definition$1$8$48$1.L$0 = obj;
        return videoModule$definition$1$8$48$1;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(MediaMetadataRetriever mediaMetadataRetriever, Continuation<? super List<VideoThumbnail>> continuation) {
        return ((VideoModule$definition$1$8$48$1) create(mediaMetadataRetriever, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(MediaMetadataRetriever mediaMetadataRetriever, Continuation<? super List<? extends VideoThumbnail>> continuation) {
        return invoke2(mediaMetadataRetriever, (Continuation<? super List<VideoThumbnail>>) continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Deferred async$default;
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
        MediaMetadataRetriever mediaMetadataRetriever = (MediaMetadataRetriever) this.L$0;
        List<Duration> list = this.$times;
        VideoModule videoModule = this.this$0;
        VideoThumbnailOptions videoThumbnailOptions = this.$options;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            async$default = BuildersKt__Builders_commonKt.async$default(videoModule.getAppContext().getBackgroundCoroutineScope(), null, null, new VideoModule$definition$1$8$48$1$bitmaps$1$1(mediaMetadataRetriever, ((Duration) it.next()).getRawValue(), videoThumbnailOptions, null), 3, null);
            arrayList.add(async$default);
        }
        this.label = 1;
        Object awaitAll = AwaitKt.awaitAll(arrayList, this);
        return awaitAll == coroutine_suspended ? coroutine_suspended : awaitAll;
    }
}
