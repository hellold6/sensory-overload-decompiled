package expo.modules.medialibrary.albums;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import expo.modules.medialibrary.AlbumException;
import expo.modules.medialibrary.MediaLibraryConstantsKt;
import expo.modules.medialibrary.UnableToLoadException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt;

/* compiled from: GetAlbums.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Landroid/os/Bundle;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.GetAlbumsKt$getAlbums$2", f = "GetAlbums.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class GetAlbumsKt$getAlbums$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Bundle>>, Object> {
    final /* synthetic */ Context $context;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetAlbumsKt$getAlbums$2(Context context, Continuation<? super GetAlbumsKt$getAlbums$2> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        GetAlbumsKt$getAlbums$2 getAlbumsKt$getAlbums$2 = new GetAlbumsKt$getAlbums$2(this.$context, continuation);
        getAlbumsKt$getAlbums$2.L$0 = obj;
        return getAlbumsKt$getAlbums$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Bundle>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Bundle>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Bundle>> continuation) {
        return ((GetAlbumsKt$getAlbums$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        String[] strArr = {"bucket_id", "bucket_display_name"};
        HashMap hashMap = new HashMap();
        try {
            Cursor query = this.$context.getContentResolver().query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), strArr, "media_type != 0", null, "bucket_display_name");
            try {
                Cursor cursor = query;
                JobKt.ensureActive(coroutineScope.getCoroutineContext());
                if (cursor == null) {
                    throw new AlbumException("Could not get albums. Query returns null");
                }
                int columnIndex = cursor.getColumnIndex("bucket_id");
                int columnIndex2 = cursor.getColumnIndex("bucket_display_name");
                while (cursor.moveToNext()) {
                    JobKt.ensureActive(coroutineScope.getCoroutineContext());
                    String string = cursor.getString(columnIndex);
                    if (cursor.getType(columnIndex2) != 0) {
                        Album album = (Album) hashMap.get(string);
                        if (album == null) {
                            Intrinsics.checkNotNull(string);
                            String string2 = cursor.getString(columnIndex2);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                            Album album2 = new Album(string, string2, 0, 4, null);
                            hashMap.put(string, album2);
                            album = album2;
                        }
                        album.setCount(album.getCount() + 1);
                    }
                }
                Collection values = hashMap.values();
                Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
                Collection collection = values;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    arrayList.add(((Album) it.next()).toBundle());
                }
                ArrayList arrayList2 = arrayList;
                CloseableKt.closeFinally(query, null);
                return arrayList2;
            } finally {
            }
        } catch (SecurityException e) {
            throw new UnableToLoadException("Could not get albums: need READ_EXTERNAL_STORAGE permission " + e.getMessage(), e);
        } catch (RuntimeException e2) {
            throw new UnableToLoadException("Could not get albums " + e2.getMessage(), e2);
        }
    }
}
