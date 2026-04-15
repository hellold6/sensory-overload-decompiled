package expo.modules.medialibrary.next.extensions.resolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import expo.modules.medialibrary.next.exceptions.AssetCouldNotBeCreated;
import expo.modules.medialibrary.next.objects.wrappers.MimeType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetExtensions.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/net/Uri;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt$insertPendingAsset$2", f = "AssetExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AssetExtensionsKt$insertPendingAsset$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Uri>, Object> {
    final /* synthetic */ String $displayName;
    final /* synthetic */ String $mimeType;
    final /* synthetic */ String $relativePath;
    final /* synthetic */ ContentResolver $this_insertPendingAsset;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetExtensionsKt$insertPendingAsset$2(String str, ContentResolver contentResolver, String str2, String str3, Continuation<? super AssetExtensionsKt$insertPendingAsset$2> continuation) {
        super(2, continuation);
        this.$mimeType = str;
        this.$this_insertPendingAsset = contentResolver;
        this.$displayName = str2;
        this.$relativePath = str3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssetExtensionsKt$insertPendingAsset$2(this.$mimeType, this.$this_insertPendingAsset, this.$displayName, this.$relativePath, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Uri> continuation) {
        return ((AssetExtensionsKt$insertPendingAsset$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ContentValues contentValues = new ContentValues();
        String str = this.$displayName;
        String str2 = this.$mimeType;
        String str3 = this.$relativePath;
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", str2);
        contentValues.put("relative_path", str3);
        contentValues.put("is_pending", Boxing.boxInt(1));
        Uri insert = this.$this_insertPendingAsset.insert(MimeType.m1319mediaCollectionUriimpl(this.$mimeType), contentValues);
        if (insert != null) {
            return insert;
        }
        throw new AssetCouldNotBeCreated("Failed to create asset: contentResolver.insert() returned null.", null, 2, null);
    }
}
