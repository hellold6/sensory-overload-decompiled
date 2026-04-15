package expo.modules.medialibrary.next.extensions.resolver;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: QueryOne.kt */
@Metadata(d1 = {"\u0000:\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\u001a\u0082\u0001\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062,\u0010\u0007\u001a(\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u0002H\u00010\b¢\u0006\u0002\b\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0086@¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"queryOne", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/content/ContentResolver;", "contentUri", "Landroid/net/Uri;", "column", "", "extractor", "Lkotlin/Function2;", "Landroid/database/Cursor;", "", "Lkotlin/ParameterName;", "name", "index", "Lkotlin/ExtensionFunctionType;", "selection", "selectionArgs", "", "sortOrder", "(Landroid/content/ContentResolver;Landroid/net/Uri;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QueryOneKt {
    public static /* synthetic */ Object queryOne$default(ContentResolver contentResolver, Uri uri, String str, Function2 function2, String str2, String[] strArr, String str3, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            str2 = null;
        }
        if ((i & 16) != 0) {
            strArr = null;
        }
        if ((i & 32) != 0) {
            str3 = null;
        }
        return queryOne(contentResolver, uri, str, function2, str2, strArr, str3, continuation);
    }

    public static final <T> Object queryOne(ContentResolver contentResolver, Uri uri, String str, Function2<? super Cursor, ? super Integer, ? extends T> function2, String str2, String[] strArr, String str3, Continuation<? super T> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new QueryOneKt$queryOne$2(str, contentResolver, uri, str2, strArr, str3, function2, null), continuation);
    }
}
