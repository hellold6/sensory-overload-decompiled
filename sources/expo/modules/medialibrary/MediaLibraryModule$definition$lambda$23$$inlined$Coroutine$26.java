package expo.modules.medialibrary;

import android.content.Context;
import android.net.Uri;
import expo.modules.medialibrary.albums.CreateAlbumKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "<destruct>", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$9"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$26", f = "MediaLibraryModule.kt", i = {0, 0, 0, 0}, l = {279, 282, 284}, m = "invokeSuspend", n = {"initialAssetUri", "assetId", "albumName", "copyAsset"}, s = {"L$0", "L$1", "L$2", "Z$0"})
/* loaded from: classes3.dex */
public final class MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$26 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    final /* synthetic */ MediaLibraryModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$26(Continuation continuation, MediaLibraryModule mediaLibraryModule) {
        super(3, continuation);
        this.this$0 = mediaLibraryModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$26 mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$26 = new MediaLibraryModule$definition$lambda$23$$inlined$Coroutine$26(continuation, this.this$0);
        mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$26.L$0 = objArr;
        return mediaLibraryModule$definition$lambda$23$$inlined$Coroutine$26.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String[] strArr;
        boolean z;
        Uri uri;
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = (Object[]) this.L$0;
            Object obj2 = objArr[0];
            Object obj3 = objArr[1];
            Object obj4 = objArr[2];
            Uri uri2 = (Uri) objArr[3];
            boolean booleanValue = ((Boolean) obj4).booleanValue();
            String str3 = (String) obj3;
            String str4 = (String) obj2;
            MediaLibraryModule.requireSystemPermissions$default(this.this$0, false, 1, null);
            if (!booleanValue && str3 != null) {
                strArr = new String[]{str3};
            } else {
                strArr = new String[0];
            }
            MediaLibraryModule mediaLibraryModule = this.this$0;
            this.L$0 = uri2;
            this.L$1 = str3;
            this.L$2 = str4;
            this.Z$0 = booleanValue;
            this.label = 1;
            if (MediaLibraryModule.requestMediaLibraryActionPermission$default(mediaLibraryModule, strArr, false, this, 2, null) != coroutine_suspended) {
                z = booleanValue;
                uri = uri2;
                str = str3;
                str2 = str4;
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        z = this.Z$0;
        str2 = (String) this.L$2;
        str = (String) this.L$1;
        uri = (Uri) this.L$0;
        ResultKt.throwOnFailure(obj);
        if (str != null) {
            Context context = this.this$0.getContext();
            this.L$0 = null;
            this.L$1 = null;
            this.L$2 = null;
            this.label = 2;
            Object createAlbum = CreateAlbumKt.createAlbum(context, str2, str, z, this);
            if (createAlbum != coroutine_suspended) {
                return createAlbum;
            }
        } else if (uri != null) {
            Context context2 = this.this$0.getContext();
            this.L$0 = null;
            this.L$1 = null;
            this.L$2 = null;
            this.label = 3;
            Object createAlbumWithInitialFileUri = CreateAlbumKt.createAlbumWithInitialFileUri(context2, str2, uri, this);
            if (createAlbumWithInitialFileUri != coroutine_suspended) {
                return createAlbumWithInitialFileUri;
            }
        } else {
            throw new AlbumException("Could not create the album");
        }
        return coroutine_suspended;
    }
}
