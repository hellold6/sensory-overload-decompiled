package expo.modules.medialibrary.assets;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
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
/* compiled from: CreateAsset.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.assets.CreateAssetWithAlbumFile$writeFileContentsToAsset$2", f = "CreateAsset.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class CreateAssetWithAlbumFile$writeFileContentsToAsset$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final /* synthetic */ Uri $assetUri;
    final /* synthetic */ File $localFile;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CreateAssetWithAlbumFile this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreateAssetWithAlbumFile$writeFileContentsToAsset$2(CreateAssetWithAlbumFile createAssetWithAlbumFile, File file, Uri uri, Continuation<? super CreateAssetWithAlbumFile$writeFileContentsToAsset$2> continuation) {
        super(2, continuation);
        this.this$0 = createAssetWithAlbumFile;
        this.$localFile = file;
        this.$assetUri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CreateAssetWithAlbumFile$writeFileContentsToAsset$2 createAssetWithAlbumFile$writeFileContentsToAsset$2 = new CreateAssetWithAlbumFile$writeFileContentsToAsset$2(this.this$0, this.$localFile, this.$assetUri, continuation);
        createAssetWithAlbumFile$writeFileContentsToAsset$2.L$0 = obj;
        return createAssetWithAlbumFile$writeFileContentsToAsset$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((CreateAssetWithAlbumFile$writeFileContentsToAsset$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            context = this.this$0.context;
            ContentResolver contentResolver = context.getContentResolver();
            JobKt.ensureActive(coroutineScope.getCoroutineContext());
            FileChannel channel = new FileInputStream(this.$localFile).getChannel();
            Uri uri = this.$assetUri;
            try {
                FileChannel fileChannel = channel;
                OutputStream openOutputStream = contentResolver.openOutputStream(uri);
                Intrinsics.checkNotNull(openOutputStream, "null cannot be cast to non-null type java.io.FileOutputStream");
                channel = ((FileOutputStream) openOutputStream).getChannel();
                try {
                    if (fileChannel.transferTo(0L, fileChannel.size(), channel) != fileChannel.size()) {
                        contentResolver.delete(uri, null, null);
                        throw new IOException("Could not save file to " + uri + " Not enough space.");
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(channel, null);
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(channel, null);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("is_pending", Boxing.boxInt(0));
                    return Boxing.boxInt(contentResolver.update(this.$assetUri, contentValues, null, null));
                } finally {
                }
            } finally {
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
