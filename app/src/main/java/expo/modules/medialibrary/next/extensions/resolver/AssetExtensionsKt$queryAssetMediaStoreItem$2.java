package expo.modules.medialibrary.next.extensions.resolver;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import expo.modules.medialibrary.next.objects.wrappers.MediaType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetExtensions.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt$queryAssetMediaStoreItem$2", f = "AssetExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AssetExtensionsKt$queryAssetMediaStoreItem$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssetMediaStoreItem>, Object> {
    final /* synthetic */ Uri $contentUri;
    final /* synthetic */ ContentResolver $this_queryAssetMediaStoreItem;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetExtensionsKt$queryAssetMediaStoreItem$2(Uri uri, ContentResolver contentResolver, Continuation<? super AssetExtensionsKt$queryAssetMediaStoreItem$2> continuation) {
        super(2, continuation);
        this.$contentUri = uri;
        this.$this_queryAssetMediaStoreItem = contentResolver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssetExtensionsKt$queryAssetMediaStoreItem$2(this.$contentUri, this.$this_queryAssetMediaStoreItem, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssetMediaStoreItem> continuation) {
        return ((AssetExtensionsKt$queryAssetMediaStoreItem$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = MediaType.INSTANCE.fromContentUri(this.$contentUri) != MediaType.IMAGE;
        Cursor safeQuery$default = SafeQueryKt.safeQuery$default(this.$this_queryAssetMediaStoreItem, this.$contentUri, AssetMediaStoreProperty.INSTANCE.projection(z), null, null, null, 16, null);
        if (safeQuery$default == null) {
            return null;
        }
        Cursor cursor = safeQuery$default;
        try {
            Cursor cursor2 = cursor;
            AssetMediaStoreItem buildAssetMediaStoreItem = cursor2.moveToFirst() ? AssetMediaStoreItemBuilder.INSTANCE.buildAssetMediaStoreItem(cursor2, z) : null;
            CloseableKt.closeFinally(cursor, null);
            return buildAssetMediaStoreItem;
        } finally {
        }
    }
}
