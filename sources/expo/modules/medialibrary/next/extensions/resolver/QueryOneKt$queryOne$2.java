package expo.modules.medialibrary.next.extensions.resolver;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: QueryOne.kt */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.extensions.resolver.QueryOneKt$queryOne$2", f = "QueryOne.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class QueryOneKt$queryOne$2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ String $column;
    final /* synthetic */ Uri $contentUri;
    final /* synthetic */ Function2<Cursor, Integer, T> $extractor;
    final /* synthetic */ String $selection;
    final /* synthetic */ String[] $selectionArgs;
    final /* synthetic */ String $sortOrder;
    final /* synthetic */ ContentResolver $this_queryOne;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public QueryOneKt$queryOne$2(String str, ContentResolver contentResolver, Uri uri, String str2, String[] strArr, String str3, Function2<? super Cursor, ? super Integer, ? extends T> function2, Continuation<? super QueryOneKt$queryOne$2> continuation) {
        super(2, continuation);
        this.$column = str;
        this.$this_queryOne = contentResolver;
        this.$contentUri = uri;
        this.$selection = str2;
        this.$selectionArgs = strArr;
        this.$sortOrder = str3;
        this.$extractor = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        QueryOneKt$queryOne$2 queryOneKt$queryOne$2 = new QueryOneKt$queryOne$2(this.$column, this.$this_queryOne, this.$contentUri, this.$selection, this.$selectionArgs, this.$sortOrder, this.$extractor, continuation);
        queryOneKt$queryOne$2.L$0 = obj;
        return queryOneKt$queryOne$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
        return ((QueryOneKt$queryOne$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Cursor safeQuery = SafeQueryKt.safeQuery(this.$this_queryOne, this.$contentUri, new String[]{this.$column}, this.$selection, this.$selectionArgs, this.$sortOrder);
        if (safeQuery == null) {
            return null;
        }
        Cursor cursor = safeQuery;
        String str = this.$column;
        Function2<Cursor, Integer, T> function2 = this.$extractor;
        try {
            Cursor cursor2 = cursor;
            CoroutineScopeKt.ensureActive(coroutineScope);
            T invoke = cursor2.moveToFirst() ? function2.invoke(cursor2, Boxing.boxInt(cursor2.getColumnIndexOrThrow(str))) : null;
            CloseableKt.closeFinally(cursor, null);
            return invoke;
        } finally {
        }
    }
}
