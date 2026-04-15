package expo.modules.medialibrary.assets;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import expo.modules.medialibrary.AssetQueryException;
import expo.modules.medialibrary.MediaLibraryConstantsKt;
import expo.modules.medialibrary.UnableToLoadException;
import java.io.IOException;
import java.util.ArrayList;
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
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetUtils.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.assets.AssetUtilsKt$queryAssetInfo$2", f = "AssetUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AssetUtilsKt$queryAssetInfo$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<Bundle>>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ boolean $resolveWithFullInfo;
    final /* synthetic */ String $selection;
    final /* synthetic */ String[] $selectionArgs;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetUtilsKt$queryAssetInfo$2(Context context, String str, String[] strArr, boolean z, Continuation<? super AssetUtilsKt$queryAssetInfo$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$selection = str;
        this.$selectionArgs = strArr;
        this.$resolveWithFullInfo = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AssetUtilsKt$queryAssetInfo$2 assetUtilsKt$queryAssetInfo$2 = new AssetUtilsKt$queryAssetInfo$2(this.$context, this.$selection, this.$selectionArgs, this.$resolveWithFullInfo, continuation);
        assetUtilsKt$queryAssetInfo$2.L$0 = obj;
        return assetUtilsKt$queryAssetInfo$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArrayList<Bundle>> continuation) {
        return ((AssetUtilsKt$queryAssetInfo$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        ContentResolver contentResolver = this.$context.getContentResolver();
        try {
            Cursor query = contentResolver.query(MediaLibraryConstantsKt.getEXTERNAL_CONTENT_URI(), MediaLibraryConstantsKt.getASSET_PROJECTION(), this.$selection, this.$selectionArgs, null);
            boolean z = this.$resolveWithFullInfo;
            try {
                Cursor cursor = query;
                JobKt.ensureActive(coroutineScope.getCoroutineContext());
                if (cursor == null) {
                    throw new AssetQueryException();
                }
                if (cursor.getCount() == 1) {
                    cursor.moveToFirst();
                    ArrayList arrayList = new ArrayList();
                    Intrinsics.checkNotNull(contentResolver);
                    AssetUtilsKt.putAssetsInfo(contentResolver, cursor, arrayList, 1, 0, z);
                    CloseableKt.closeFinally(query, null);
                    return arrayList;
                }
                CloseableKt.closeFinally(query, null);
                return null;
            } finally {
            }
        } catch (Exception e) {
            if (!(e instanceof SecurityException)) {
                if (!(e instanceof IOException)) {
                    if (e instanceof UnsupportedOperationException) {
                        String message = e.getMessage();
                        if (message == null) {
                            message = "Invalid MediaType";
                        }
                        throw new UnableToLoadException(message, e);
                    }
                    throw e;
                }
                throw new UnableToLoadException("Could not read file " + e.getMessage(), e);
            }
            throw new UnableToLoadException("Could not get asset: need READ_EXTERNAL_STORAGE permission", e);
        }
    }
}
