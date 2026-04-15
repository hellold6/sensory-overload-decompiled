package coil3.network;

import androidx.core.location.LocationRequestCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkFetcher.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "coil3.network.NetworkFetcher", f = "NetworkFetcher.kt", i = {0, 0, 0, 1, 1, 2}, l = {LockFreeTaskQueueCore.CLOSED_SHIFT, 74, LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY}, m = "fetch", n = {"this", "snapshot", "cacheResponse", "this", "snapshot", "snapshot"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0"})
/* loaded from: classes2.dex */
public final class NetworkFetcher$fetch$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NetworkFetcher this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkFetcher$fetch$1(NetworkFetcher networkFetcher, Continuation<? super NetworkFetcher$fetch$1> continuation) {
        super(continuation);
        this.this$0 = networkFetcher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.fetch(this);
    }
}
