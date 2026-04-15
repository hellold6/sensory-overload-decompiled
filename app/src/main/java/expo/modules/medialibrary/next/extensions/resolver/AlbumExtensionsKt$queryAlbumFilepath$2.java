package expo.modules.medialibrary.next.extensions.resolver;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlbumExtensions.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
/* synthetic */ class AlbumExtensionsKt$queryAlbumFilepath$2 extends FunctionReferenceImpl implements Function2<Cursor, Integer, String> {
    public static final AlbumExtensionsKt$queryAlbumFilepath$2 INSTANCE = new AlbumExtensionsKt$queryAlbumFilepath$2();

    AlbumExtensionsKt$queryAlbumFilepath$2() {
        super(2, Cursor.class, "getString", "getString(I)Ljava/lang/String;", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ String invoke(Cursor cursor, Integer num) {
        return invoke(cursor, num.intValue());
    }

    public final String invoke(Cursor p0, int i) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return p0.getString(i);
    }
}
