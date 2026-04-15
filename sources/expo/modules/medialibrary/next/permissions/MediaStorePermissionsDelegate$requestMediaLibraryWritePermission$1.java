package expo.modules.medialibrary.next.permissions;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MediaStorePermissionsDelegate.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate", f = "MediaStorePermissionsDelegate.kt", i = {}, l = {45}, m = "requestMediaLibraryWritePermission", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MediaStorePermissionsDelegate$requestMediaLibraryWritePermission$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaStorePermissionsDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaStorePermissionsDelegate$requestMediaLibraryWritePermission$1(MediaStorePermissionsDelegate mediaStorePermissionsDelegate, Continuation<? super MediaStorePermissionsDelegate$requestMediaLibraryWritePermission$1> continuation) {
        super(continuation);
        this.this$0 = mediaStorePermissionsDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.requestMediaLibraryWritePermission(null, this);
    }
}
