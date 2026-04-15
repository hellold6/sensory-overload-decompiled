package expo.modules.medialibrary.next.extensions.resolver;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumExtensions.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Landroid/net/Uri;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt$queryAlbumAssetsContentUris$2", f = "AlbumExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AlbumExtensionsKt$queryAlbumAssetsContentUris$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Uri>>, Object> {
    final /* synthetic */ String $bucketId;
    final /* synthetic */ ContentResolver $this_queryAlbumAssetsContentUris;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumExtensionsKt$queryAlbumAssetsContentUris$2(ContentResolver contentResolver, String str, Continuation<? super AlbumExtensionsKt$queryAlbumAssetsContentUris$2> continuation) {
        super(2, continuation);
        this.$this_queryAlbumAssetsContentUris = contentResolver;
        this.$bucketId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AlbumExtensionsKt$queryAlbumAssetsContentUris$2 albumExtensionsKt$queryAlbumAssetsContentUris$2 = new AlbumExtensionsKt$queryAlbumAssetsContentUris$2(this.$this_queryAlbumAssetsContentUris, this.$bucketId, continuation);
        albumExtensionsKt$queryAlbumAssetsContentUris$2.L$0 = obj;
        return albumExtensionsKt$queryAlbumAssetsContentUris$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Uri>> continuation) {
        return ((AlbumExtensionsKt$queryAlbumAssetsContentUris$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Cursor query = this.$this_queryAlbumAssetsContentUris.query(AlbumExtensionsKt.getEXTERNAL_CONTENT_URI(), new String[]{"_id", "media_type"}, "bucket_id = ?", new String[]{this.$bucketId}, null);
        if (query != null) {
            Cursor cursor = query;
            try {
                Cursor cursor2 = cursor;
                CoroutineScopeKt.ensureActive(coroutineScope);
                int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("_id");
                int columnIndexOrThrow2 = cursor2.getColumnIndexOrThrow("media_type");
                Iterable<Cursor> asIterable = expo.modules.medialibrary.next.extensions.CursorExtensionsKt.asIterable(cursor2);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(asIterable, 10));
                Iterator<Cursor> it = asIterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(CursorExtensionsKt.extractAssetContentUri(it.next(), columnIndexOrThrow, columnIndexOrThrow2));
                }
                List list = CollectionsKt.toList(arrayList);
                CloseableKt.closeFinally(cursor, null);
                if (list != null) {
                    return list;
                }
            } finally {
            }
        }
        return CollectionsKt.emptyList();
    }
}
