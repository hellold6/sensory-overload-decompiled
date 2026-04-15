package expo.modules.medialibrary.next.objects.query;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import expo.modules.medialibrary.next.extensions.CursorExtensionsKt;
import expo.modules.medialibrary.next.objects.asset.Asset;
import expo.modules.medialibrary.next.objects.query.builder.QueryLegacyExecutor;
import expo.modules.medialibrary.next.objects.query.builder.QueryModernExecutor;
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
/* compiled from: Query.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.query.Query$exe$2", f = "Query.kt", i = {0}, l = {101}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class Query$exe$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Asset>>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Query this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Query$exe$2(Query query, Continuation<? super Query$exe$2> continuation) {
        super(2, continuation);
        this.this$0 = query;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Query$exe$2 query$exe$2 = new Query$exe$2(this.this$0, continuation);
        query$exe$2.L$0 = obj;
        return query$exe$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Asset>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Asset>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Asset>> continuation) {
        return ((Query$exe$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List list;
        List list2;
        List list3;
        Integer num;
        Integer num2;
        QueryLegacyExecutor queryLegacyExecutor;
        ContentResolver contentResolver;
        CoroutineScope coroutineScope;
        List list4;
        List list5;
        List list6;
        Integer num3;
        Integer num4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            if (Build.VERSION.SDK_INT >= 30) {
                list4 = this.this$0.clauses;
                list5 = this.this$0.args;
                list6 = this.this$0.orderBy;
                num3 = this.this$0.limit;
                num4 = this.this$0.offset;
                queryLegacyExecutor = new QueryModernExecutor(list4, list5, list6, num3, num4);
            } else {
                list = this.this$0.clauses;
                list2 = this.this$0.args;
                list3 = this.this$0.orderBy;
                num = this.this$0.limit;
                num2 = this.this$0.offset;
                queryLegacyExecutor = new QueryLegacyExecutor(list, list2, list3, num, num2);
            }
            contentResolver = this.this$0.getContentResolver();
            this.L$0 = coroutineScope2;
            this.label = 1;
            Object exe = queryLegacyExecutor.exe(new String[]{"_id", "media_type"}, contentResolver, this);
            if (exe == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            obj = exe;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Cursor cursor = (Cursor) obj;
        Query query = this.this$0;
        try {
            Cursor cursor2 = cursor;
            CoroutineScopeKt.ensureActive(coroutineScope);
            int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow2 = cursor2.getColumnIndexOrThrow("media_type");
            Iterable<Cursor> asIterable = CursorExtensionsKt.asIterable(cursor2);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(asIterable, 10));
            Iterator<Cursor> it = asIterable.iterator();
            while (it.hasNext()) {
                arrayList.add(expo.modules.medialibrary.next.extensions.resolver.CursorExtensionsKt.extractAssetContentUri(it.next(), columnIndexOrThrow, columnIndexOrThrow2));
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayList3.add(query.getAssetFactory().create((Uri) it2.next()));
            }
            List list7 = CollectionsKt.toList(arrayList3);
            CloseableKt.closeFinally(cursor, null);
            return list7;
        } finally {
        }
    }
}
