package expo.modules.medialibrary.next.objects.query.builder;

import android.content.ContentResolver;
import android.database.Cursor;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: QueryExecutor.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH¦@¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/medialibrary/next/objects/query/builder/QueryExecutor;", "", "exe", "Landroid/database/Cursor;", "projection", "", "", "contentResolver", "Landroid/content/ContentResolver;", "([Ljava/lang/String;Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QueryExecutor {
    Object exe(String[] strArr, ContentResolver contentResolver, Continuation<? super Cursor> continuation);
}
