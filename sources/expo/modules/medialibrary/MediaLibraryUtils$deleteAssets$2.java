package expo.modules.medialibrary;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MediaLibraryUtils.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.MediaLibraryUtils$deleteAssets$2", f = "MediaLibraryUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MediaLibraryUtils$deleteAssets$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $selection;
    final /* synthetic */ String[] $selectionArgs;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaLibraryUtils$deleteAssets$2(Context context, String str, String[] strArr, Continuation<? super MediaLibraryUtils$deleteAssets$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$selection = str;
        this.$selectionArgs = strArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaLibraryUtils$deleteAssets$2 mediaLibraryUtils$deleteAssets$2 = new MediaLibraryUtils$deleteAssets$2(this.$context, this.$selection, this.$selectionArgs, continuation);
        mediaLibraryUtils$deleteAssets$2.L$0 = obj;
        return mediaLibraryUtils$deleteAssets$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((MediaLibraryUtils$deleteAssets$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            Cursor query = this.$context.getContentResolver().query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), new String[]{"_id", "_data"}, this.$selection, this.$selectionArgs, null);
            Context context = this.$context;
            try {
                Cursor cursor = query;
                if (cursor == null) {
                    throw new AssetFileException("Could not delete assets. Cursor is null.");
                }
                while (cursor.moveToNext()) {
                    JobKt.ensureActive(coroutineScope.getCoroutineContext());
                    if (Build.VERSION.SDK_INT >= 30) {
                        Uri withAppendedId = ContentUris.withAppendedId(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), cursor.getLong(cursor.getColumnIndex("_id")));
                        Intrinsics.checkNotNullExpressionValue(withAppendedId, "withAppendedId(...)");
                        if (context.getContentResolver().delete(withAppendedId, null) == 0) {
                            throw new AssetFileException("Could not delete file.");
                        }
                    } else {
                        String string = cursor.getString(cursor.getColumnIndex("_data"));
                        if (!new File(string).delete()) {
                            throw new AssetFileException("Could not delete file.");
                        }
                        Boxing.boxInt(context.getContentResolver().delete(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), "_data=?", new String[]{string}));
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(query, null);
                return Boxing.boxBoolean(true);
            } finally {
            }
        } catch (SecurityException e) {
            throw new UnableToDeleteException("Could not delete asset: need WRITE_EXTERNAL_STORAGE permission.", e);
        } catch (Exception e2) {
            throw new UnableToDeleteException("Could not delete file: " + e2.getMessage(), e2);
        }
    }
}
