package expo.modules;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReactActivityDelegateWrapper.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.ReactActivityDelegateWrapper", f = "ReactActivityDelegateWrapper.kt", i = {0}, l = {453}, m = "loadAppImpl", n = {"appKey"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class ReactActivityDelegateWrapper$loadAppImpl$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ReactActivityDelegateWrapper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper$loadAppImpl$1(ReactActivityDelegateWrapper reactActivityDelegateWrapper, Continuation<? super ReactActivityDelegateWrapper$loadAppImpl$1> continuation) {
        super(continuation);
        this.this$0 = reactActivityDelegateWrapper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object loadAppImpl;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        loadAppImpl = this.this$0.loadAppImpl(null, false, this);
        return loadAppImpl;
    }
}
