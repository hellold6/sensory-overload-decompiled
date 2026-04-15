package expo.modules.asset;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import expo.modules.core.errors.InvalidArgumentException;
import java.io.InputStream;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: ResourceAsset.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0001H\u0000\u001a\u001f\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u000b\u001a\u001f\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u000b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"ANDROID_EMBEDDED_URL_BASE_RESOURCE", "", "openAssetResourceStream", "Ljava/io/InputStream;", "context", "Landroid/content/Context;", "assetName", "openAndroidResStream", "resourceFilePath", "findResourceId", "", "(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/Integer;", "findResourceIdForAndroidResPath", "expo-asset_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ResourceAssetKt {
    public static final String ANDROID_EMBEDDED_URL_BASE_RESOURCE = "file:///android_res/";

    public static final InputStream openAssetResourceStream(Context context, String assetName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetName, "assetName");
        Integer findResourceId = findResourceId(context, assetName);
        if (findResourceId == null) {
            throw new Resources.NotFoundException(assetName);
        }
        InputStream openRawResource = context.getResources().openRawResource(findResourceId.intValue());
        Intrinsics.checkNotNullExpressionValue(openRawResource, "openRawResource(...)");
        return openRawResource;
    }

    public static final InputStream openAndroidResStream(Context context, String resourceFilePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resourceFilePath, "resourceFilePath");
        Integer findResourceIdForAndroidResPath = findResourceIdForAndroidResPath(context, resourceFilePath);
        if (findResourceIdForAndroidResPath == null) {
            throw new Resources.NotFoundException(resourceFilePath);
        }
        InputStream openRawResource = context.getResources().openRawResource(findResourceIdForAndroidResPath.intValue());
        Intrinsics.checkNotNullExpressionValue(openRawResource, "openRawResource(...)");
        return openRawResource;
    }

    private static final Integer findResourceId(Context context, String str) {
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        Integer valueOf = Integer.valueOf(resources.getIdentifier(str, "raw", packageName));
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        if (valueOf != null) {
            return valueOf;
        }
        Integer valueOf2 = Integer.valueOf(resources.getIdentifier(str, "drawable", packageName));
        if (valueOf2.intValue() != 0) {
            return valueOf2;
        }
        return null;
    }

    private static final Integer findResourceIdForAndroidResPath(Context context, String str) {
        if (!StringsKt.startsWith$default(str, ANDROID_EMBEDDED_URL_BASE_RESOURCE, false, 2, (Object) null)) {
            throw new InvalidArgumentException("Invalid resource file path: " + str);
        }
        List<String> pathSegments = Uri.parse(str).getPathSegments();
        if (pathSegments.size() < 3) {
            throw new InvalidArgumentException("Invalid resource file path: " + str);
        }
        String str2 = pathSegments.get(1);
        Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
        String substringBefore$default = StringsKt.substringBefore$default(str2, '-', (String) null, 2, (Object) null);
        String str3 = pathSegments.get(2);
        Intrinsics.checkNotNull(str3);
        Integer valueOf = Integer.valueOf(context.getResources().getIdentifier(StringsKt.substringBeforeLast(str3, FilenameUtils.EXTENSION_SEPARATOR, str3), substringBefore$default, context.getPackageName()));
        if (valueOf.intValue() != 0) {
            return valueOf;
        }
        return null;
    }
}
