package expo.modules.medialibrary;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MediaLibraryModule.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.MediaLibraryModule", f = "MediaLibraryModule.kt", i = {}, l = {415, 417}, m = "requestMediaLibraryActionPermission", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MediaLibraryModule$requestMediaLibraryActionPermission$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaLibraryModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryModule$requestMediaLibraryActionPermission$1(MediaLibraryModule mediaLibraryModule, Continuation<? super MediaLibraryModule$requestMediaLibraryActionPermission$1> continuation) {
        super(continuation);
        this.this$0 = mediaLibraryModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object requestMediaLibraryActionPermission;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        requestMediaLibraryActionPermission = this.this$0.requestMediaLibraryActionPermission(null, false, this);
        return requestMediaLibraryActionPermission;
    }
}
