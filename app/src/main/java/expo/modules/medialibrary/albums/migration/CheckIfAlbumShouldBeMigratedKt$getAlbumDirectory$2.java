package expo.modules.medialibrary.albums.migration;

import android.content.Context;
import android.database.Cursor;
import expo.modules.medialibrary.MediaLibraryConstantsKt;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt;

/* compiled from: CheckIfAlbumShouldBeMigrated.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Ljava/io/File;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.migration.CheckIfAlbumShouldBeMigratedKt$getAlbumDirectory$2", f = "CheckIfAlbumShouldBeMigrated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class CheckIfAlbumShouldBeMigratedKt$getAlbumDirectory$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super File>, Object> {
    final /* synthetic */ String $albumId;
    final /* synthetic */ Context $context;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CheckIfAlbumShouldBeMigratedKt$getAlbumDirectory$2(String str, Context context, Continuation<? super CheckIfAlbumShouldBeMigratedKt$getAlbumDirectory$2> continuation) {
        super(2, continuation);
        this.$albumId = str;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CheckIfAlbumShouldBeMigratedKt$getAlbumDirectory$2 checkIfAlbumShouldBeMigratedKt$getAlbumDirectory$2 = new CheckIfAlbumShouldBeMigratedKt$getAlbumDirectory$2(this.$albumId, this.$context, continuation);
        checkIfAlbumShouldBeMigratedKt$getAlbumDirectory$2.L$0 = obj;
        return checkIfAlbumShouldBeMigratedKt$getAlbumDirectory$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super File> continuation) {
        return ((CheckIfAlbumShouldBeMigratedKt$getAlbumDirectory$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Cursor query = this.$context.getContentResolver().query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), new String[]{"_data"}, "media_type != 0 AND bucket_id=?", new String[]{this.$albumId}, null);
        try {
            Cursor cursor = query;
            JobKt.ensureActive(coroutineScope.getCoroutineContext());
            if (cursor != null && cursor.moveToNext()) {
                File file = new File(cursor.getString(cursor.getColumnIndex("_data")));
                if (file.isFile()) {
                    String parent = file.getParent();
                    if (parent == null) {
                        CloseableKt.closeFinally(query, null);
                        return null;
                    }
                    File file2 = new File(parent);
                    CloseableKt.closeFinally(query, null);
                    return file2;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(query, null);
            return null;
        } finally {
        }
    }
}
