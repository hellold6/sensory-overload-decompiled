package expo.modules.securestore;

import com.facebook.imageutils.JfifUtil;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SecureStoreModule.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.securestore.SecureStoreModule", f = "SecureStoreModule.kt", i = {0, 0, 0, 0, 0, 0}, l = {204, JfifUtil.MARKER_RST7}, m = "setItemImpl", n = {"key", "value", "options", "keychainAwareKey", "prefs", "keyIsInvalidated"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "Z$0"})
/* loaded from: classes3.dex */
public final class SecureStoreModule$setItemImpl$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SecureStoreModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecureStoreModule$setItemImpl$1(SecureStoreModule secureStoreModule, Continuation<? super SecureStoreModule$setItemImpl$1> continuation) {
        super(continuation);
        this.this$0 = secureStoreModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object itemImpl;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        itemImpl = this.this$0.setItemImpl(null, null, null, false, this);
        return itemImpl;
    }
}
