package expo.modules.medialibrary.next.extensions.resolver;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssetExtensions.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
/* synthetic */ class AssetExtensionsKt$queryAssetHeight$2 extends FunctionReferenceImpl implements Function2<Cursor, Integer, Integer> {
    public static final AssetExtensionsKt$queryAssetHeight$2 INSTANCE = new AssetExtensionsKt$queryAssetHeight$2();

    AssetExtensionsKt$queryAssetHeight$2() {
        super(2, Cursor.class, "getInt", "getInt(I)I", 0);
    }

    public final Integer invoke(Cursor p0, int i) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Integer.valueOf(p0.getInt(i));
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Integer invoke(Cursor cursor, Integer num) {
        return invoke(cursor, num.intValue());
    }
}
