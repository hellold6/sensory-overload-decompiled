package expo.modules.medialibrary.next;

import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: MediaLibraryNextModule.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.MediaLibraryNextModule$definition$1$11", f = "MediaLibraryNextModule.kt", i = {}, l = {258}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class MediaLibraryNextModule$definition$1$11 extends SuspendLambda implements Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaLibraryNextModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryNextModule$definition$1$11(MediaLibraryNextModule mediaLibraryNextModule, Continuation<? super MediaLibraryNextModule$definition$1$11> continuation) {
        super(2, continuation);
        this.this$0 = mediaLibraryNextModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaLibraryNextModule$definition$1$11 mediaLibraryNextModule$definition$1$11 = new MediaLibraryNextModule$definition$1$11(this.this$0, continuation);
        mediaLibraryNextModule$definition$1$11.L$0 = obj;
        return mediaLibraryNextModule$definition$1$11;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AppContextActivityResultCaller appContextActivityResultCaller, Continuation<? super Unit> continuation) {
        return ((MediaLibraryNextModule$definition$1$11) create(appContextActivityResultCaller, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MediaStorePermissionsDelegate mediaStorePermissionsDelegate;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AppContextActivityResultCaller appContextActivityResultCaller = (AppContextActivityResultCaller) this.L$0;
            mediaStorePermissionsDelegate = this.this$0.getMediaStorePermissionsDelegate();
            MediaLibraryNextModule mediaLibraryNextModule = this.this$0;
            this.label = 1;
            if (mediaStorePermissionsDelegate.registerMediaStoreContracts(appContextActivityResultCaller, mediaLibraryNextModule, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
