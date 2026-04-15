package expo.modules.video.playbackService;

import androidx.media3.session.MediaSession;
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
@DebugMetadata(c = "expo.modules.video.playbackService.ExpoVideoPlaybackService$onUpdateNotification$1", f = "ExpoVideoPlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class ExpoVideoPlaybackService$onUpdateNotification$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaSession $session;
    final /* synthetic */ boolean $startInForegroundRequired;
    int label;
    final /* synthetic */ ExpoVideoPlaybackService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoVideoPlaybackService$onUpdateNotification$1(boolean z, ExpoVideoPlaybackService expoVideoPlaybackService, MediaSession mediaSession, Continuation<? super ExpoVideoPlaybackService$onUpdateNotification$1> continuation) {
        super(2, continuation);
        this.$startInForegroundRequired = z;
        this.this$0 = expoVideoPlaybackService;
        this.$session = mediaSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExpoVideoPlaybackService$onUpdateNotification$1(this.$startInForegroundRequired, this.this$0, this.$session, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExpoVideoPlaybackService$onUpdateNotification$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MediaSession findMostRecentInteractionSession;
        boolean wantsToShowNotification;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (this.$startInForegroundRequired) {
            wantsToShowNotification = this.this$0.wantsToShowNotification(this.$session);
            if (wantsToShowNotification) {
                this.this$0.setMostRecentInteractionSession(this.$session);
                return Unit.INSTANCE;
            }
        }
        ExpoVideoPlaybackService expoVideoPlaybackService = this.this$0;
        findMostRecentInteractionSession = expoVideoPlaybackService.findMostRecentInteractionSession();
        expoVideoPlaybackService.setMostRecentInteractionSession(findMostRecentInteractionSession);
        return Unit.INSTANCE;
    }
}
