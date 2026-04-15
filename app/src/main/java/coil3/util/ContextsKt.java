package coil3.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.media3.common.MimeTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: contexts.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0007\u001a\u00020\u0005*\u00020\u0001H\u0000\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\u0001H\u0000\u001a\u0016\u0010\f\u001a\u00020\r*\u00020\u00012\b\b\u0001\u0010\u000e\u001a\u00020\tH\u0000\u001a$\u0010\f\u001a\u00020\r*\u00020\u000f2\b\b\u0001\u0010\u000e\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0018\u00010\u0011R\u00020\u000fH\u0000\u001a\u001e\u0010\u0012\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u000f2\b\b\u0001\u0010\u000e\u001a\u00020\tH\u0001\u001a\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u0004\u0018\u00010\u0001H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {MimeTypes.BASE_TYPE_APPLICATION, "Landroid/content/Context;", "getApplication", "(Landroid/content/Context;)Landroid/content/Context;", "STANDARD_MEMORY_MULTIPLIER", "", "LOW_MEMORY_MULTIPLIER", "defaultMemoryCacheSizePercent", "DEFAULT_MEMORY_CLASS_MEGABYTES", "", "totalAvailableMemoryBytes", "", "getDrawableCompat", "Landroid/graphics/drawable/Drawable;", "resId", "Landroid/content/res/Resources;", "theme", "Landroid/content/res/Resources$Theme;", "getXmlDrawableCompat", "resources", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ContextsKt {
    private static final int DEFAULT_MEMORY_CLASS_MEGABYTES = 256;
    private static final double LOW_MEMORY_MULTIPLIER = 0.15d;
    private static final double STANDARD_MEMORY_MULTIPLIER = 0.2d;

    public static final Context getApplication(Context context) {
        return context.getApplicationContext();
    }

    public static final Drawable getDrawableCompat(Context context, int i) {
        Drawable drawable = AppCompatResources.getDrawable(context, i);
        if (drawable != null) {
            return drawable;
        }
        throw new IllegalStateException(("Invalid resource ID: " + i).toString());
    }

    public static final Drawable getDrawableCompat(Resources resources, int i, Resources.Theme theme) {
        Drawable drawable = ResourcesCompat.getDrawable(resources, i, theme);
        if (drawable != null) {
            return drawable;
        }
        throw new IllegalStateException(("Invalid resource ID: " + i).toString());
    }

    public static final Drawable getXmlDrawableCompat(Context context, Resources resources, int i) {
        XmlResourceParser xml = resources.getXml(i);
        int next = xml.next();
        while (next != 2 && next != 1) {
            next = xml.next();
        }
        if (next != 2) {
            throw new XmlPullParserException("No start tag found.");
        }
        return getDrawableCompat(resources, i, context.getTheme());
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
    
        r1 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.lifecycle.Lifecycle getLifecycle(android.content.Context r1) {
        /*
        L0:
            boolean r0 = r1 instanceof androidx.lifecycle.LifecycleOwner
            if (r0 == 0) goto Lb
            androidx.lifecycle.LifecycleOwner r1 = (androidx.lifecycle.LifecycleOwner) r1
            androidx.lifecycle.Lifecycle r1 = r1.getLifecycle()
            return r1
        Lb:
            boolean r0 = r1 instanceof android.content.ContextWrapper
            if (r0 != 0) goto L11
            r1 = 0
            return r1
        L11:
            android.content.ContextWrapper r1 = (android.content.ContextWrapper) r1
            android.content.Context r1 = r1.getBaseContext()
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil3.util.ContextsKt.getLifecycle(android.content.Context):androidx.lifecycle.Lifecycle");
    }

    public static final double defaultMemoryCacheSizePercent(Context context) {
        try {
            Object systemService = ContextCompat.getSystemService(context, ActivityManager.class);
            Intrinsics.checkNotNull(systemService);
            return ((ActivityManager) systemService).isLowRamDevice() ? LOW_MEMORY_MULTIPLIER : STANDARD_MEMORY_MULTIPLIER;
        } catch (Exception unused) {
            return STANDARD_MEMORY_MULTIPLIER;
        }
    }

    public static final long totalAvailableMemoryBytes(Context context) {
        int i;
        try {
            Object systemService = ContextCompat.getSystemService(context, ActivityManager.class);
            Intrinsics.checkNotNull(systemService);
            ActivityManager activityManager = (ActivityManager) systemService;
            i = (context.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass();
        } catch (Exception unused) {
            i = 256;
        }
        return i * 1048576;
    }
}
