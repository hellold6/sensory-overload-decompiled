package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KCallables.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class KCallables$callSuspendBy$1<R> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KCallables$callSuspendBy$1(Continuation<? super KCallables$callSuspendBy$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return KCallables.callSuspendBy(null, null, this);
    }
}
