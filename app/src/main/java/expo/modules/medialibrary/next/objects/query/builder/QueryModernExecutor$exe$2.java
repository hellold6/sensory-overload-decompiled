package expo.modules.medialibrary.next.objects.query.builder;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import expo.modules.medialibrary.next.exceptions.QueryCouldNotBeExecuted;
import expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: QueryModernExecutor.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/database/Cursor;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.query.builder.QueryModernExecutor$exe$2", f = "QueryModernExecutor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class QueryModernExecutor$exe$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Cursor>, Object> {
    final /* synthetic */ ContentResolver $contentResolver;
    final /* synthetic */ String[] $projection;
    int label;
    final /* synthetic */ QueryModernExecutor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QueryModernExecutor$exe$2(QueryModernExecutor queryModernExecutor, ContentResolver contentResolver, String[] strArr, Continuation<? super QueryModernExecutor$exe$2> continuation) {
        super(2, continuation);
        this.this$0 = queryModernExecutor;
        this.$contentResolver = contentResolver;
        this.$projection = strArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new QueryModernExecutor$exe$2(this.this$0, this.$contentResolver, this.$projection, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Cursor> continuation) {
        return ((QueryModernExecutor$exe$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Bundle build;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            build = this.this$0.build();
            Cursor query = this.$contentResolver.query(AlbumExtensionsKt.getEXTERNAL_CONTENT_URI(), this.$projection, build, null);
            if (query != null) {
                return query;
            }
            throw new QueryCouldNotBeExecuted("Cursor is null", null, 2, null);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
