package expo.modules.video.playbackService;

import android.os.Build;
import android.os.Bundle;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.MediaSession;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExpoVideoPlaybackService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.video.playbackService.ExpoVideoPlaybackService$setShowNotification$1", f = "ExpoVideoPlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class ExpoVideoPlaybackService$setShowNotification$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ExoPlayer $player;
    final /* synthetic */ boolean $showNotification;
    int label;
    final /* synthetic */ ExpoVideoPlaybackService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoVideoPlaybackService$setShowNotification$1(ExpoVideoPlaybackService expoVideoPlaybackService, ExoPlayer exoPlayer, boolean z, Continuation<? super ExpoVideoPlaybackService$setShowNotification$1> continuation) {
        super(2, continuation);
        this.this$0 = expoVideoPlaybackService;
        this.$player = exoPlayer;
        this.$showNotification = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExpoVideoPlaybackService$setShowNotification$1(this.this$0, this.$player, this.$showNotification, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExpoVideoPlaybackService$setShowNotification$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Bundle bundle;
        Map map;
        Map map2;
        Bundle sessionExtras;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (Build.VERSION.SDK_INT >= 26) {
            map2 = this.this$0.mediaSessions;
            MediaSession mediaSession = (MediaSession) map2.get(this.$player);
            if (mediaSession == null || (sessionExtras = mediaSession.getSessionExtras()) == null || (bundle = sessionExtras.deepCopy()) == null) {
                bundle = new Bundle();
            }
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean(ExpoVideoPlaybackService.SESSION_SHOW_NOTIFICATION, this.$showNotification);
        map = this.this$0.mediaSessions;
        MediaSession mediaSession2 = (MediaSession) map.get(this.$player);
        if (mediaSession2 != null) {
            ExpoVideoPlaybackService expoVideoPlaybackService = this.this$0;
            boolean z = this.$showNotification;
            ExoPlayer exoPlayer = this.$player;
            mediaSession2.setSessionExtras(bundle);
            expoVideoPlaybackService.onUpdateNotification(mediaSession2, z && exoPlayer.getPlayWhenReady());
        }
        return Unit.INSTANCE;
    }
}
