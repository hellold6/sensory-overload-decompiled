package expo.modules.medialibrary.albums;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import com.facebook.react.modules.dialog.AlertFragment;
import expo.modules.medialibrary.AlbumException;
import expo.modules.medialibrary.MediaLibraryConstantsKt;
import expo.modules.medialibrary.UnableToLoadException;
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
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumUtils.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/os/Bundle;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.AlbumUtilsKt$queryAlbum$2", f = "AlbumUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AlbumUtilsKt$queryAlbum$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bundle>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $selection;
    final /* synthetic */ String[] $selectionArgs;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumUtilsKt$queryAlbum$2(Context context, String str, String[] strArr, Continuation<? super AlbumUtilsKt$queryAlbum$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$selection = str;
        this.$selectionArgs = strArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AlbumUtilsKt$queryAlbum$2 albumUtilsKt$queryAlbum$2 = new AlbumUtilsKt$queryAlbum$2(this.$context, this.$selection, this.$selectionArgs, continuation);
        albumUtilsKt$queryAlbum$2.L$0 = obj;
        return albumUtilsKt$queryAlbum$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bundle> continuation) {
        return ((AlbumUtilsKt$queryAlbum$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        try {
            Cursor query = this.$context.getContentResolver().query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), new String[]{"bucket_id", "bucket_display_name"}, this.$selection, this.$selectionArgs, "bucket_display_name");
            try {
                Cursor cursor = query;
                JobKt.ensureActive(coroutineScope.getCoroutineContext());
                if (cursor == null) {
                    throw new AlbumException("Could not get album. Query is incorrect.");
                }
                if (!cursor.moveToNext()) {
                    CloseableKt.closeFinally(query, null);
                    return null;
                }
                int columnIndex = cursor.getColumnIndex("bucket_id");
                int columnIndex2 = cursor.getColumnIndex("bucket_display_name");
                Bundle bundle = new Bundle();
                bundle.putString("id", cursor.getString(columnIndex));
                bundle.putString(AlertFragment.ARG_TITLE, cursor.getString(columnIndex2));
                bundle.putInt("assetCount", cursor.getCount());
                CloseableKt.closeFinally(query, null);
                return bundle;
            } finally {
            }
        } catch (IllegalArgumentException e) {
            throw new UnableToLoadException("Could not get album: " + e.getMessage(), e);
        } catch (SecurityException e2) {
            throw new UnableToLoadException("Could not get albums: need READ_EXTERNAL_STORAGE permission " + e2.getMessage(), e2);
        }
    }
}
