package expo.modules.medialibrary;

import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.activityresult.AppContextActivityResultLauncher;
import expo.modules.medialibrary.contracts.DeleteContract;
import expo.modules.medialibrary.contracts.WriteContract;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: MediaLibraryModule.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.MediaLibraryModule$definition$1$21", f = "MediaLibraryModule.kt", i = {0}, l = {275, 277}, m = "invokeSuspend", n = {"$this$RegisterActivityContracts"}, s = {"L$0"})
/* loaded from: classes3.dex */
final class MediaLibraryModule$definition$1$21 extends SuspendLambda implements Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MediaLibraryModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryModule$definition$1$21(MediaLibraryModule mediaLibraryModule, Continuation<? super MediaLibraryModule$definition$1$21> continuation) {
        super(2, continuation);
        this.this$0 = mediaLibraryModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaLibraryModule$definition$1$21 mediaLibraryModule$definition$1$21 = new MediaLibraryModule$definition$1$21(this.this$0, continuation);
        mediaLibraryModule$definition$1$21.L$0 = obj;
        return mediaLibraryModule$definition$1$21;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AppContextActivityResultCaller appContextActivityResultCaller, Continuation<? super Unit> continuation) {
        return ((MediaLibraryModule$definition$1$21) create(appContextActivityResultCaller, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MediaLibraryModule mediaLibraryModule;
        AppContextActivityResultCaller appContextActivityResultCaller;
        MediaLibraryModule mediaLibraryModule2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AppContextActivityResultCaller appContextActivityResultCaller2 = (AppContextActivityResultCaller) this.L$0;
            mediaLibraryModule = this.this$0;
            this.L$0 = appContextActivityResultCaller2;
            this.L$1 = mediaLibraryModule;
            this.label = 1;
            obj = AppContextActivityResultCaller.DefaultImpls.registerForActivityResult$default(appContextActivityResultCaller2, new DeleteContract(this.this$0), null, this, 2, null);
            if (obj != coroutine_suspended) {
                appContextActivityResultCaller = appContextActivityResultCaller2;
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            mediaLibraryModule2 = (MediaLibraryModule) this.L$0;
            ResultKt.throwOnFailure(obj);
            mediaLibraryModule2.writeLauncher = (AppContextActivityResultLauncher) obj;
            return Unit.INSTANCE;
        }
        mediaLibraryModule = (MediaLibraryModule) this.L$1;
        appContextActivityResultCaller = (AppContextActivityResultCaller) this.L$0;
        ResultKt.throwOnFailure(obj);
        mediaLibraryModule.deleteLauncher = (AppContextActivityResultLauncher) obj;
        MediaLibraryModule mediaLibraryModule3 = this.this$0;
        this.L$0 = mediaLibraryModule3;
        this.L$1 = null;
        this.label = 2;
        Object registerForActivityResult$default = AppContextActivityResultCaller.DefaultImpls.registerForActivityResult$default(appContextActivityResultCaller, new WriteContract(this.this$0), null, this, 2, null);
        if (registerForActivityResult$default != coroutine_suspended) {
            mediaLibraryModule2 = mediaLibraryModule3;
            obj = registerForActivityResult$default;
            mediaLibraryModule2.writeLauncher = (AppContextActivityResultLauncher) obj;
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }
}
