package coil3.network;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: NetworkFetcher.kt */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "response", "Lcoil3/network/NetworkResponse;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "coil3.network.NetworkFetcher$executeNetworkRequest$2", f = "NetworkFetcher.kt", i = {}, l = {204}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class NetworkFetcher$executeNetworkRequest$2<T> extends SuspendLambda implements Function2<NetworkResponse, Continuation<? super T>, Object> {
    final /* synthetic */ Function2<NetworkResponse, Continuation<? super T>, Object> $block;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public NetworkFetcher$executeNetworkRequest$2(Function2<? super NetworkResponse, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super NetworkFetcher$executeNetworkRequest$2> continuation) {
        super(2, continuation);
        this.$block = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkFetcher$executeNetworkRequest$2 networkFetcher$executeNetworkRequest$2 = new NetworkFetcher$executeNetworkRequest$2(this.$block, continuation);
        networkFetcher$executeNetworkRequest$2.L$0 = obj;
        return networkFetcher$executeNetworkRequest$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(NetworkResponse networkResponse, Continuation<? super T> continuation) {
        return ((NetworkFetcher$executeNetworkRequest$2) create(networkResponse, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
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
        NetworkResponse networkResponse = (NetworkResponse) this.L$0;
        int code = networkResponse.getCode();
        if ((200 > code || code >= 300) && networkResponse.getCode() != 304) {
            throw new HttpException(networkResponse);
        }
        Function2<NetworkResponse, Continuation<? super T>, Object> function2 = this.$block;
        this.label = 1;
        Object invoke = function2.invoke(networkResponse, this);
        return invoke == coroutine_suspended ? coroutine_suspended : invoke;
    }
}
