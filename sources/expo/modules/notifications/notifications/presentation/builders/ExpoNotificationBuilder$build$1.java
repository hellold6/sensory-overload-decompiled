package expo.modules.notifications.notifications.presentation.builders;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExpoNotificationBuilder.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.notifications.notifications.presentation.builders.ExpoNotificationBuilder", f = "ExpoNotificationBuilder.kt", i = {0}, l = {151}, m = "build$suspendImpl", n = {"builder"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class ExpoNotificationBuilder$build$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ExpoNotificationBuilder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoNotificationBuilder$build$1(ExpoNotificationBuilder expoNotificationBuilder, Continuation<? super ExpoNotificationBuilder$build$1> continuation) {
        super(continuation);
        this.this$0 = expoNotificationBuilder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ExpoNotificationBuilder.build$suspendImpl(this.this$0, this);
    }
}
