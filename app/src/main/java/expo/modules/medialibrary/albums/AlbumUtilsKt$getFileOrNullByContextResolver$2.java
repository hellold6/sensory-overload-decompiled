package expo.modules.medialibrary.albums;

import android.content.Context;
import android.database.Cursor;
import expo.modules.medialibrary.AlbumException;
import expo.modules.medialibrary.MediaLibraryConstantsKt;
import expo.modules.medialibrary.MediaLibraryException;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumUtils.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Ljava/io/File;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.AlbumUtilsKt$getFileOrNullByContextResolver$2", f = "AlbumUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AlbumUtilsKt$getFileOrNullByContextResolver$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super File>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $selection;
    final /* synthetic */ String[] $selectionArgs;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumUtilsKt$getFileOrNullByContextResolver$2(Context context, String str, String[] strArr, Continuation<? super AlbumUtilsKt$getFileOrNullByContextResolver$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$selection = str;
        this.$selectionArgs = strArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AlbumUtilsKt$getFileOrNullByContextResolver$2(this.$context, this.$selection, this.$selectionArgs, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super File> continuation) {
        return ((AlbumUtilsKt$getFileOrNullByContextResolver$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Cursor query = this.$context.getContentResolver().query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), new String[]{"_data"}, this.$selection, this.$selectionArgs, null);
        try {
            Cursor cursor = query;
            if (cursor == null) {
                throw new AlbumException("Could not get album. Query returns null.");
            }
            if (cursor.getCount() == 0) {
                CloseableKt.closeFinally(query, null);
                return null;
            }
            cursor.moveToNext();
            File file = new File(cursor.getString(cursor.getColumnIndex("_data")));
            if (!file.isFile() && !file.isDirectory()) {
                throw new MediaLibraryException();
            }
            String parent = file.getParent();
            Intrinsics.checkNotNull(parent);
            File file2 = new File(parent);
            CloseableKt.closeFinally(query, null);
            return file2;
        } finally {
        }
    }
}
