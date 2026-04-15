package expo.modules.medialibrary.next.objects.query.builder;

import android.content.ContentResolver;
import android.database.Cursor;
import expo.modules.medialibrary.next.records.SortDescriptor;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: QueryLegacyExecutor.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001BE\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ$\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0004H\u0002J\b\u0010\u0016\u001a\u00020\u0004H\u0002J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u0018\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u001a\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0012\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u001d"}, d2 = {"Lexpo/modules/medialibrary/next/objects/query/builder/QueryLegacyExecutor;", "Lexpo/modules/medialibrary/next/objects/query/builder/QueryExecutor;", "clauses", "", "", "args", "sortDescriptors", "Lexpo/modules/medialibrary/next/records/SortDescriptor;", "limit", "", "offset", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "exe", "Landroid/database/Cursor;", "projection", "", "contentResolver", "Landroid/content/ContentResolver;", "([Ljava/lang/String;Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildSortOrder", "buildSelection", "buildOrderBy", "addLimit", "sortOrder", "requireNotEmptySortOrder", "addOffset", "orderBy", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QueryLegacyExecutor implements QueryExecutor {
    private final List<String> args;
    private final List<String> clauses;
    private final Integer limit;
    private final Integer offset;
    private final List<SortDescriptor> sortDescriptors;

    public QueryLegacyExecutor(List<String> clauses, List<String> args, List<SortDescriptor> sortDescriptors, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(clauses, "clauses");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(sortDescriptors, "sortDescriptors");
        this.clauses = clauses;
        this.args = args;
        this.sortDescriptors = sortDescriptors;
        this.limit = num;
        this.offset = num2;
    }

    @Override // expo.modules.medialibrary.next.objects.query.builder.QueryExecutor
    public Object exe(String[] strArr, ContentResolver contentResolver, Continuation<? super Cursor> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new QueryLegacyExecutor$exe$2(this, contentResolver, strArr, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildSortOrder() {
        return addOffset(addLimit(buildOrderBy()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildSelection() {
        return CollectionsKt.joinToString$default(this.clauses, " AND ", null, null, 0, null, null, 62, null);
    }

    private final String buildOrderBy() {
        if (this.sortDescriptors.isEmpty()) {
            return null;
        }
        return CollectionsKt.joinToString$default(this.sortDescriptors, ", ", null, null, 0, null, new Function1() { // from class: expo.modules.medialibrary.next.objects.query.builder.QueryLegacyExecutor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence buildOrderBy$lambda$0;
                buildOrderBy$lambda$0 = QueryLegacyExecutor.buildOrderBy$lambda$0((SortDescriptor) obj);
                return buildOrderBy$lambda$0;
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence buildOrderBy$lambda$0(SortDescriptor it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.toMediaStoreQueryString();
    }

    private final String addLimit(String sortOrder) {
        if (this.limit != null) {
            return requireNotEmptySortOrder(sortOrder) + " LIMIT " + this.limit;
        }
        if (this.offset != null) {
            return requireNotEmptySortOrder(sortOrder) + " LIMIT -1";
        }
        return sortOrder == null ? "" : sortOrder;
    }

    private final String requireNotEmptySortOrder(String sortOrder) {
        return sortOrder == null ? "_id" : sortOrder;
    }

    private final String addOffset(String orderBy) {
        Integer num = this.offset;
        return num != null ? orderBy + " OFFSET " + num : orderBy;
    }
}
