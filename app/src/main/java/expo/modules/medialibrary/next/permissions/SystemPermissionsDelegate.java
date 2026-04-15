package expo.modules.medialibrary.next.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.medialibrary.R;
import expo.modules.medialibrary.next.exceptions.PermissionException;
import expo.modules.medialibrary.next.permissions.enums.GranularPermission;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

/* compiled from: SystemPermissionsDelegate.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J&\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u000bJ\u0006\u0010\u0015\u001a\u00020\u000bJ\u0016\u0010\u001a\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J$\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#H\u0002J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020#0%2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J)\u0010$\u001a\b\u0012\u0004\u0012\u00020#0&2\u0006\u0010\f\u001a\u00020\r2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002¢\u0006\u0002\u0010'J)\u0010(\u001a\b\u0012\u0004\u0012\u00020#0&2\u0006\u0010)\u001a\u00020\r2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002¢\u0006\u0002\u0010'J\u001e\u0010*\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0016\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001c\u0010\u001d¨\u0006+"}, d2 = {"Lexpo/modules/medialibrary/next/permissions/SystemPermissionsDelegate;", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "<init>", "(Lexpo/modules/kotlin/AppContext;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "requestPermissions", "", "writeOnly", "", "permissions", "", "Lexpo/modules/medialibrary/next/permissions/enums/GranularPermission;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "getPermissions", "requireReadPermissions", "requireWritePermissions", "isExpoGo", "()Z", "isExpoGo$delegate", "Lkotlin/Lazy;", "maybeThrowIfExpoGo", "allowedPermissionsList", "getAllowedPermissionsList", "()Ljava/util/List;", "allowedPermissionsList$delegate", "getManifestDeclaredPermissions", "granularPermissions", "hasManifestPermission", "permission", "", "getManifestPermissions", "", "", "(ZLjava/util/List;)[Ljava/lang/String;", "getGranularPermissions", "shouldIncludeGranular", "assertGranularPermissionIntegrity", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SystemPermissionsDelegate {

    /* renamed from: allowedPermissionsList$delegate, reason: from kotlin metadata */
    private final Lazy allowedPermissionsList;
    private final AppContext appContext;

    /* renamed from: isExpoGo$delegate, reason: from kotlin metadata */
    private final Lazy isExpoGo;

    public SystemPermissionsDelegate(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        this.isExpoGo = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.next.permissions.SystemPermissionsDelegate$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean isExpoGo_delegate$lambda$0;
                isExpoGo_delegate$lambda$0 = SystemPermissionsDelegate.isExpoGo_delegate$lambda$0(SystemPermissionsDelegate.this);
                return Boolean.valueOf(isExpoGo_delegate$lambda$0);
            }
        });
        this.allowedPermissionsList = LazyKt.lazy(new Function0() { // from class: expo.modules.medialibrary.next.permissions.SystemPermissionsDelegate$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                List allowedPermissionsList_delegate$lambda$1;
                allowedPermissionsList_delegate$lambda$1 = SystemPermissionsDelegate.allowedPermissionsList_delegate$lambda$1(SystemPermissionsDelegate.this);
                return allowedPermissionsList_delegate$lambda$1;
            }
        });
    }

    private final Context getContext() {
        Context reactContext = this.appContext.getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    public final void requestPermissions(boolean writeOnly, List<? extends GranularPermission> permissions, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (permissions == null) {
            permissions = getAllowedPermissionsList();
        }
        maybeThrowIfExpoGo(permissions);
        Permissions permissions2 = this.appContext.getPermissions();
        MediaLibraryPermissionPromiseWrapper mediaLibraryPermissionPromiseWrapper = new MediaLibraryPermissionPromiseWrapper(permissions, promise, new WeakReference(getContext()));
        String[] manifestPermissions = getManifestPermissions(writeOnly, permissions);
        Permissions.askForPermissionsWithPermissionsManager(permissions2, mediaLibraryPermissionPromiseWrapper, (String[]) Arrays.copyOf(manifestPermissions, manifestPermissions.length));
    }

    public final void getPermissions(boolean writeOnly, List<? extends GranularPermission> permissions, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (permissions == null) {
            permissions = getAllowedPermissionsList();
        }
        maybeThrowIfExpoGo(permissions);
        Permissions permissions2 = this.appContext.getPermissions();
        MediaLibraryPermissionPromiseWrapper mediaLibraryPermissionPromiseWrapper = new MediaLibraryPermissionPromiseWrapper(permissions, promise, new WeakReference(getContext()));
        String[] manifestPermissions = getManifestPermissions(writeOnly, permissions);
        Permissions.getPermissionsWithPermissionsManager(permissions2, mediaLibraryPermissionPromiseWrapper, (String[]) Arrays.copyOf(manifestPermissions, manifestPermissions.length));
    }

    public final void requireReadPermissions() {
        Permissions permissions = this.appContext.getPermissions();
        if (!Intrinsics.areEqual((Object) (permissions != null ? Boolean.valueOf(permissions.hasGrantedPermissions("android.permission.READ_EXTERNAL_STORAGE")) : null), (Object) true)) {
            throw new PermissionException("Missing READ_EXTERNAL_STORAGE permission", null, 2, null);
        }
    }

    public final void requireWritePermissions() {
        Permissions permissions = this.appContext.getPermissions();
        if (!Intrinsics.areEqual((Object) (permissions != null ? Boolean.valueOf(permissions.hasGrantedPermissions("android.permission.WRITE_EXTERNAL_STORAGE")) : null), (Object) true)) {
            throw new PermissionException("Missing WRITE_EXTERNAL_STORAGE permission", null, 2, null);
        }
    }

    private final boolean isExpoGo() {
        return ((Boolean) this.isExpoGo.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isExpoGo_delegate$lambda$0(SystemPermissionsDelegate systemPermissionsDelegate) {
        return Boolean.parseBoolean(systemPermissionsDelegate.getContext().getResources().getString(R.string.is_expo_go));
    }

    private final void maybeThrowIfExpoGo(List<? extends GranularPermission> permissions) {
        if (isExpoGo()) {
            if (permissions.contains(GranularPermission.PHOTO) || permissions.contains(GranularPermission.VIDEO)) {
                throw new PermissionException("Due to changes in Androids permission requirements, Expo Go can no longer provide full access to the media library. To test the full functionality of this module, you can create a development build", null, 2, null);
            }
        }
    }

    private final List<GranularPermission> getAllowedPermissionsList() {
        return (List) this.allowedPermissionsList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List allowedPermissionsList_delegate$lambda$1(SystemPermissionsDelegate systemPermissionsDelegate) {
        if (systemPermissionsDelegate.isExpoGo()) {
            return CollectionsKt.listOf(GranularPermission.AUDIO);
        }
        return systemPermissionsDelegate.getManifestDeclaredPermissions(systemPermissionsDelegate.getContext(), CollectionsKt.listOf((Object[]) new GranularPermission[]{GranularPermission.PHOTO, GranularPermission.VIDEO, GranularPermission.AUDIO}));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<GranularPermission> getManifestDeclaredPermissions(Context context, List<? extends GranularPermission> granularPermissions) {
        if (Build.VERSION.SDK_INT < 33) {
            return granularPermissions;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : granularPermissions) {
            if (hasManifestPermission(context, ((GranularPermission) obj).toManifestPermission())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final boolean hasManifestPermission(Context context, String permission) {
        return getManifestPermissions(context).contains(permission);
    }

    private final Set<String> getManifestPermissions(Context context) {
        Set<String> set;
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null && (set = ArraysKt.toSet(strArr)) != null) {
                return set;
            }
            return SetsKt.emptySet();
        } catch (PackageManager.NameNotFoundException unused) {
            return SetsKt.emptySet();
        }
    }

    private final String[] getManifestPermissions(boolean writeOnly, List<? extends GranularPermission> granularPermissions) {
        boolean z = Build.VERSION.SDK_INT >= 29 && hasManifestPermission(getContext(), "android.permission.ACCESS_MEDIA_LOCATION") && !(Build.VERSION.SDK_INT >= 33 && granularPermissions.size() == 1 && granularPermissions.contains(GranularPermission.AUDIO));
        boolean z2 = Build.VERSION.SDK_INT < 33 && hasManifestPermission(getContext(), "android.permission.WRITE_EXTERNAL_STORAGE");
        boolean z3 = Build.VERSION.SDK_INT >= 33;
        boolean z4 = z3 && !writeOnly;
        SpreadBuilder spreadBuilder = new SpreadBuilder(4);
        spreadBuilder.add(z2 ? "android.permission.WRITE_EXTERNAL_STORAGE" : null);
        spreadBuilder.add((writeOnly || z3) ? null : "android.permission.READ_EXTERNAL_STORAGE");
        spreadBuilder.add(z ? "android.permission.ACCESS_MEDIA_LOCATION" : null);
        spreadBuilder.addSpread(getGranularPermissions(z4, granularPermissions));
        return (String[]) CollectionsKt.listOfNotNull(spreadBuilder.toArray(new String[spreadBuilder.size()])).toArray(new String[0]);
    }

    private final String[] getGranularPermissions(boolean shouldIncludeGranular, List<? extends GranularPermission> granularPermissions) {
        if (shouldIncludeGranular) {
            assertGranularPermissionIntegrity(getContext(), granularPermissions);
            String[] strArr = new String[3];
            strArr[0] = granularPermissions.contains(GranularPermission.PHOTO) ? "android.permission.READ_MEDIA_IMAGES" : null;
            strArr[1] = granularPermissions.contains(GranularPermission.VIDEO) ? "android.permission.READ_MEDIA_VIDEO" : null;
            strArr[2] = granularPermissions.contains(GranularPermission.AUDIO) ? "android.permission.READ_MEDIA_AUDIO" : null;
            return (String[]) CollectionsKt.listOfNotNull((Object[]) strArr).toArray(new String[0]);
        }
        return new String[0];
    }

    private final void assertGranularPermissionIntegrity(Context context, List<? extends GranularPermission> granularPermissions) {
        for (GranularPermission granularPermission : granularPermissions) {
            if (!hasManifestPermission(context, granularPermission.toManifestPermission())) {
                throw new PermissionException("You have requested the " + granularPermission + " permission, but it is not declared in AndroidManifest. Update expo-media-library config plugin to include the permission before requesting it.", null, 2, null);
            }
        }
    }
}
