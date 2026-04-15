package expo.modules.medialibrary.next.permissions;

import com.facebook.soloader.Elf64;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MediaStorePermissionsDelegate.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate", f = "MediaStorePermissionsDelegate.kt", i = {0, 0}, l = {Elf64.Ehdr.E_EHSIZE, 53}, m = "registerMediaStoreContracts", n = {"$this$registerMediaStoreContracts", "appContextProvider"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class MediaStorePermissionsDelegate$registerMediaStoreContracts$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaStorePermissionsDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaStorePermissionsDelegate$registerMediaStoreContracts$1(MediaStorePermissionsDelegate mediaStorePermissionsDelegate, Continuation<? super MediaStorePermissionsDelegate$registerMediaStoreContracts$1> continuation) {
        super(continuation);
        this.this$0 = mediaStorePermissionsDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.registerMediaStoreContracts(null, null, this);
    }
}
