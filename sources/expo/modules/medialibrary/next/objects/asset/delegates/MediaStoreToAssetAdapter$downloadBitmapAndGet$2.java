package expo.modules.medialibrary.next.objects.asset.delegates;

import android.content.ContentResolver;
import android.graphics.BitmapFactory;
import android.net.Uri;
import expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MediaStoreToAssetAdapter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.delegates.MediaStoreToAssetAdapter$downloadBitmapAndGet$2", f = "MediaStoreToAssetAdapter.kt", i = {0}, l = {56}, m = "invokeSuspend", n = {"options"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class MediaStoreToAssetAdapter$downloadBitmapAndGet$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final /* synthetic */ Uri $contentUri;
    final /* synthetic */ Function1<BitmapFactory.Options, Integer> $extract;
    Object L$0;
    int label;
    final /* synthetic */ MediaStoreToAssetAdapter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MediaStoreToAssetAdapter$downloadBitmapAndGet$2(MediaStoreToAssetAdapter mediaStoreToAssetAdapter, Uri uri, Function1<? super BitmapFactory.Options, Integer> function1, Continuation<? super MediaStoreToAssetAdapter$downloadBitmapAndGet$2> continuation) {
        super(2, continuation);
        this.this$0 = mediaStoreToAssetAdapter;
        this.$contentUri = uri;
        this.$extract = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaStoreToAssetAdapter$downloadBitmapAndGet$2(this.this$0, this.$contentUri, this.$extract, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((MediaStoreToAssetAdapter$downloadBitmapAndGet$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ContentResolver contentResolver;
        BitmapFactory.Options options;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            contentResolver = this.this$0.getContentResolver();
            this.L$0 = options2;
            this.label = 1;
            Object queryAssetData = AssetExtensionsKt.queryAssetData(contentResolver, this.$contentUri, this);
            if (queryAssetData == coroutine_suspended) {
                return coroutine_suspended;
            }
            options = options2;
            obj = queryAssetData;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            options = (BitmapFactory.Options) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        BitmapFactory.decodeFile((String) obj, options);
        return this.$extract.invoke(options);
    }
}
