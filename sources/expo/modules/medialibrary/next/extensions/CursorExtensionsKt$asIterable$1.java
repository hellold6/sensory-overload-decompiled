package expo.modules.medialibrary.next.extensions;

import android.database.Cursor;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: CursorExtensions.kt */
@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0096\u0002¨\u0006\u0005"}, d2 = {"expo/modules/medialibrary/next/extensions/CursorExtensionsKt$asIterable$1", "", "Landroid/database/Cursor;", "iterator", "", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CursorExtensionsKt$asIterable$1 implements Iterable<Cursor>, KMappedMarker {
    final /* synthetic */ Cursor $this_asIterable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CursorExtensionsKt$asIterable$1(Cursor cursor) {
        this.$this_asIterable = cursor;
    }

    @Override // java.lang.Iterable
    public Iterator<Cursor> iterator() {
        return new CursorExtensionsKt$asIterable$1$iterator$1(this.$this_asIterable);
    }
}
