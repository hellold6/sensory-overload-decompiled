package expo.modules.medialibrary.next.extensions;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CursorExtensions.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"asIterable", "", "Landroid/database/Cursor;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CursorExtensionsKt {
    public static final Iterable<Cursor> asIterable(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "<this>");
        return new CursorExtensionsKt$asIterable$1(cursor);
    }
}
