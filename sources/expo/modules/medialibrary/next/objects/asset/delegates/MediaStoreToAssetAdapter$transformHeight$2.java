package expo.modules.medialibrary.next.objects.asset.delegates;

import android.graphics.BitmapFactory;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MediaStoreToAssetAdapter.kt */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.delegates.MediaStoreToAssetAdapter$transformHeight$2", f = "MediaStoreToAssetAdapter.kt", i = {}, l = {28}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MediaStoreToAssetAdapter$transformHeight$2 extends SuspendLambda implements Function1<Continuation<? super Integer>, Object> {
    final /* synthetic */ Uri $contentUri;
    int label;
    final /* synthetic */ MediaStoreToAssetAdapter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaStoreToAssetAdapter$transformHeight$2(MediaStoreToAssetAdapter mediaStoreToAssetAdapter, Uri uri, Continuation<? super MediaStoreToAssetAdapter$transformHeight$2> continuation) {
        super(1, continuation);
        this.this$0 = mediaStoreToAssetAdapter;
        this.$contentUri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new MediaStoreToAssetAdapter$transformHeight$2(this.this$0, this.$contentUri, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Integer> continuation) {
        return ((MediaStoreToAssetAdapter$transformHeight$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object downloadBitmapAndGet;
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
        this.label = 1;
        downloadBitmapAndGet = this.this$0.downloadBitmapAndGet(this.$contentUri, new Function1() { // from class: expo.modules.medialibrary.next.objects.asset.delegates.MediaStoreToAssetAdapter$transformHeight$2$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                int i2;
                i2 = ((BitmapFactory.Options) obj2).outHeight;
                return Integer.valueOf(i2);
            }
        }, this);
        return downloadBitmapAndGet == coroutine_suspended ? coroutine_suspended : downloadBitmapAndGet;
    }
}
