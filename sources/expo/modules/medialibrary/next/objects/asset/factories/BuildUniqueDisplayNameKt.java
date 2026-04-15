package expo.modules.medialibrary.next.objects.asset.factories;

import android.net.Uri;
import com.facebook.common.util.UriUtil;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: BuildUniqueDisplayName.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"buildUniqueDisplayName", "", "filePath", "Landroid/net/Uri;", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BuildUniqueDisplayNameKt {
    public static final String buildUniqueDisplayName(Uri filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        String lastPathSegment = filePath.getLastPathSegment();
        if (lastPathSegment == null) {
            lastPathSegment = UriUtil.LOCAL_ASSET_SCHEME;
        }
        String substringBeforeLast$default = StringsKt.substringBeforeLast$default(lastPathSegment, ".", (String) null, 2, (Object) null);
        String str = "";
        String substringAfterLast = StringsKt.substringAfterLast(lastPathSegment, ".", "");
        if (substringAfterLast.length() > 0) {
            str = "." + substringAfterLast;
        }
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        return substringBeforeLast$default + "_" + StringsKt.take(uuid, 8) + str;
    }
}
