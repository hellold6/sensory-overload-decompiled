package expo.modules.notifications.service.delegates;

import androidx.core.app.NotificationManagerCompat;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehaviorRecord;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ExpoPresentationDelegate.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.notifications.service.delegates.ExpoPresentationDelegate$presentNotification$1", f = "ExpoPresentationDelegate.kt", i = {}, l = {106}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ExpoPresentationDelegate$presentNotification$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NotificationBehaviorRecord $behavior;
    final /* synthetic */ Notification $notification;
    int label;
    final /* synthetic */ ExpoPresentationDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoPresentationDelegate$presentNotification$1(ExpoPresentationDelegate expoPresentationDelegate, Notification notification, NotificationBehaviorRecord notificationBehaviorRecord, Continuation<? super ExpoPresentationDelegate$presentNotification$1> continuation) {
        super(2, continuation);
        this.this$0 = expoPresentationDelegate;
        this.$notification = notification;
        this.$behavior = notificationBehaviorRecord;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExpoPresentationDelegate$presentNotification$1(this.this$0, this.$notification, this.$behavior, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExpoPresentationDelegate$presentNotification$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.createNotification(this.$notification, this.$behavior, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        NotificationManagerCompat.from(this.this$0.getContext()).notify(this.$notification.getNotificationRequest().getIdentifier(), this.this$0.getNotifyId(this.$notification.getNotificationRequest()), (android.app.Notification) obj);
        return Unit.INSTANCE;
    }
}
