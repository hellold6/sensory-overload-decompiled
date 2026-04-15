package expo.modules.video.playbackService;

import android.app.NotificationManager;
import android.os.Build;
import androidx.media3.session.MediaSession;
import expo.modules.notifications.service.NotificationsService;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExpoVideoPlaybackService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.video.playbackService.ExpoVideoPlaybackService$cleanup$1", f = "ExpoVideoPlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class ExpoVideoPlaybackService$cleanup$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ExpoVideoPlaybackService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoVideoPlaybackService$cleanup$1(ExpoVideoPlaybackService expoVideoPlaybackService, Continuation<? super ExpoVideoPlaybackService$cleanup$1> continuation) {
        super(2, continuation);
        this.this$0 = expoVideoPlaybackService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExpoVideoPlaybackService$cleanup$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExpoVideoPlaybackService$cleanup$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Map map;
        Map map2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.stopForeground(1);
        this.this$0.isForeground = false;
        this.this$0.hideAllNotifications();
        map = this.this$0.mediaSessions;
        List list = CollectionsKt.toList(map.values());
        map2 = this.this$0.mediaSessions;
        map2.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((MediaSession) it.next()).release();
        }
        Object systemService = this.this$0.getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.deleteNotificationChannel(ExpoVideoPlaybackService.CHANNEL_ID);
        }
        return Unit.INSTANCE;
    }
}
