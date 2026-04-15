package expo.modules.medialibrary.next.objects.query.builder;

import android.content.ContentResolver;
import android.database.Cursor;
import expo.modules.medialibrary.next.exceptions.QueryCouldNotBeExecuted;
import expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt;
import expo.modules.medialibrary.next.extensions.resolver.SafeQueryKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: QueryLegacyExecutor.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/database/Cursor;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.query.builder.QueryLegacyExecutor$exe$2", f = "QueryLegacyExecutor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class QueryLegacyExecutor$exe$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Cursor>, Object> {
    final /* synthetic */ ContentResolver $contentResolver;
    final /* synthetic */ String[] $projection;
    int label;
    final /* synthetic */ QueryLegacyExecutor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QueryLegacyExecutor$exe$2(QueryLegacyExecutor queryLegacyExecutor, ContentResolver contentResolver, String[] strArr, Continuation<? super QueryLegacyExecutor$exe$2> continuation) {
        super(2, continuation);
        this.this$0 = queryLegacyExecutor;
        this.$contentResolver = contentResolver;
        this.$projection = strArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new QueryLegacyExecutor$exe$2(this.this$0, this.$contentResolver, this.$projection, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Cursor> continuation) {
        return ((QueryLegacyExecutor$exe$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String buildSelection;
        String buildSortOrder;
        List list;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            buildSelection = this.this$0.buildSelection();
            buildSortOrder = this.this$0.buildSortOrder();
            list = this.this$0.args;
            Cursor safeQuery = SafeQueryKt.safeQuery(this.$contentResolver, AlbumExtensionsKt.getEXTERNAL_CONTENT_URI(), this.$projection, buildSelection, (String[]) list.toArray(new String[0]), buildSortOrder);
            if (safeQuery != null) {
                return safeQuery;
            }
            throw new QueryCouldNotBeExecuted("Cursor is null", null, 2, null);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
