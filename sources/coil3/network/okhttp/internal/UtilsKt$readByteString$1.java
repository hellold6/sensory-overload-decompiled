package coil3.network.okhttp.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: utils.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "coil3.network.okhttp.internal.UtilsKt", f = "utils.kt", i = {0}, l = {39}, m = "readByteString", n = {"buffer"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class UtilsKt$readByteString$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UtilsKt$readByteString$1(Continuation<? super UtilsKt$readByteString$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object readByteString;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        readByteString = UtilsKt.readByteString(null, this);
        return readByteString;
    }
}
