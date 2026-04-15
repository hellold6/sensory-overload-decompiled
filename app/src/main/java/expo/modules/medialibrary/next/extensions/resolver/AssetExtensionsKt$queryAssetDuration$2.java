package expo.modules.medialibrary.next.extensions.resolver;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssetExtensions.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
/* synthetic */ class AssetExtensionsKt$queryAssetDuration$2 extends FunctionReferenceImpl implements Function2<Cursor, Integer, Long> {
    public static final AssetExtensionsKt$queryAssetDuration$2 INSTANCE = new AssetExtensionsKt$queryAssetDuration$2();

    AssetExtensionsKt$queryAssetDuration$2() {
        super(2, Cursor.class, "getLong", "getLong(I)J", 0);
    }

    public final Long invoke(Cursor p0, int i) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Long.valueOf(p0.getLong(i));
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Long invoke(Cursor cursor, Integer num) {
        return invoke(cursor, num.intValue());
    }
}
