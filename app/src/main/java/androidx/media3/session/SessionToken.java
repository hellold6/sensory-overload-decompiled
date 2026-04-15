package androidx.media3.session;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.LegacyParcelableUtil;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public final class SessionToken {
    private static final String FIELD_IMPL;
    private static final String FIELD_IMPL_TYPE;
    private static final int IMPL_TYPE_BASE = 0;
    private static final int IMPL_TYPE_LEGACY = 1;
    public static final int PLATFORM_SESSION_VERSION = 0;
    static final int TYPE_BROWSER_SERVICE_LEGACY = 101;
    public static final int TYPE_LIBRARY_SERVICE = 2;
    public static final int TYPE_SESSION = 0;
    static final int TYPE_SESSION_LEGACY = 100;
    public static final int TYPE_SESSION_SERVICE = 1;
    public static final int UNKNOWN_INTERFACE_VERSION = 0;
    public static final int UNKNOWN_SESSION_VERSION = 1000000;
    private static final long WAIT_TIME_MS_FOR_SESSION3_TOKEN = 500;
    private final SessionTokenImpl impl;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface SessionTokenImpl {
        Object getBinder();

        ComponentName getComponentName();

        Bundle getExtras();

        int getInterfaceVersion();

        int getLibraryVersion();

        String getPackageName();

        MediaSession.Token getPlatformToken();

        String getServiceName();

        int getType();

        int getUid();

        boolean isLegacySession();

        Bundle toBundle();
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TokenType {
    }

    static {
        MediaLibraryInfo.registerModule("media3.session");
        FIELD_IMPL_TYPE = Util.intToStringMaxRadix(0);
        FIELD_IMPL = Util.intToStringMaxRadix(1);
    }

    public SessionToken(Context context, ComponentName componentName) {
        int i;
        Assertions.checkNotNull(context, "context must not be null");
        Assertions.checkNotNull(componentName, "serviceComponent must not be null");
        PackageManager packageManager = context.getPackageManager();
        int uid = getUid(packageManager, componentName.getPackageName());
        if (isInterfaceDeclared(packageManager, MediaLibraryService.SERVICE_INTERFACE, componentName)) {
            i = 2;
        } else if (isInterfaceDeclared(packageManager, MediaSessionService.SERVICE_INTERFACE, componentName)) {
            i = 1;
        } else {
            if (!isInterfaceDeclared(packageManager, "android.media.browse.MediaBrowserService", componentName)) {
                throw new IllegalArgumentException("Failed to resolve SessionToken for " + componentName + ". Manifest doesn't declare one of either MediaSessionService, MediaLibraryService, MediaBrowserService or MediaBrowserServiceCompat. Use service's full name.");
            }
            i = 101;
        }
        if (i != 101) {
            this.impl = new SessionTokenImplBase(componentName, uid, i);
        } else {
            this.impl = new SessionTokenImplLegacy(componentName, uid);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionToken(int i, int i2, int i3, int i4, String str, IMediaSession iMediaSession, Bundle bundle, MediaSession.Token token) {
        this.impl = new SessionTokenImplBase(i, i2, i3, i4, str, iMediaSession, bundle, token);
    }

    private SessionToken(MediaSessionCompat.Token token, String str, int i, Bundle bundle) {
        this.impl = new SessionTokenImplLegacy(token, str, i, bundle);
    }

    private SessionToken(Bundle bundle, MediaSession.Token token) {
        String str = FIELD_IMPL_TYPE;
        Assertions.checkArgument(bundle.containsKey(str), "Impl type needs to be set.");
        int i = bundle.getInt(str);
        Bundle bundle2 = (Bundle) Assertions.checkNotNull(bundle.getBundle(FIELD_IMPL));
        if (i == 0) {
            this.impl = SessionTokenImplBase.fromBundle(bundle2, token);
        } else {
            this.impl = SessionTokenImplLegacy.fromBundle(bundle2);
        }
    }

    public int hashCode() {
        return this.impl.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof SessionToken) {
            return this.impl.equals(((SessionToken) obj).impl);
        }
        return false;
    }

    public String toString() {
        return this.impl.toString();
    }

    public int getUid() {
        return this.impl.getUid();
    }

    public String getPackageName() {
        return this.impl.getPackageName();
    }

    public String getServiceName() {
        return this.impl.getServiceName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComponentName getComponentName() {
        return this.impl.getComponentName();
    }

    public int getType() {
        return this.impl.getType();
    }

    public int getSessionVersion() {
        return this.impl.getLibraryVersion();
    }

    public int getInterfaceVersion() {
        return this.impl.getInterfaceVersion();
    }

    public Bundle getExtras() {
        return this.impl.getExtras();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLegacySession() {
        return this.impl.isLegacySession();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getBinder() {
        return this.impl.getBinder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaSession.Token getPlatformToken() {
        return this.impl.getPlatformToken();
    }

    public static ListenableFuture<SessionToken> createSessionToken(Context context, MediaSession.Token token) {
        return createSessionToken(context, MediaSessionCompat.Token.fromToken(token));
    }

    public static ListenableFuture<SessionToken> createSessionToken(Context context, Parcelable parcelable) {
        return createSessionToken(context, createCompatToken(parcelable));
    }

    public static ListenableFuture<SessionToken> createSessionToken(Context context, MediaSession.Token token, Looper looper) {
        return createSessionToken(context, MediaSessionCompat.Token.fromToken(token), looper);
    }

    public static ListenableFuture<SessionToken> createSessionToken(Context context, Parcelable parcelable, Looper looper) {
        return createSessionToken(context, createCompatToken(parcelable), looper);
    }

    private static ListenableFuture<SessionToken> createSessionToken(Context context, MediaSessionCompat.Token token) {
        final HandlerThread handlerThread = new HandlerThread("SessionTokenThread");
        handlerThread.start();
        ListenableFuture<SessionToken> createSessionToken = createSessionToken(context, token, handlerThread.getLooper());
        Objects.requireNonNull(handlerThread);
        createSessionToken.addListener(new Runnable() { // from class: androidx.media3.session.SessionToken$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                handlerThread.quit();
            }
        }, MoreExecutors.directExecutor());
        return createSessionToken;
    }

    private static ListenableFuture<SessionToken> createSessionToken(final Context context, final MediaSessionCompat.Token token, Looper looper) {
        Assertions.checkNotNull(context, "context must not be null");
        Assertions.checkNotNull(token, "compatToken must not be null");
        final SettableFuture create = SettableFuture.create();
        final MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(context, token);
        final String str = (String) Assertions.checkNotNull(mediaControllerCompat.getPackageName());
        final Handler handler = new Handler(looper);
        final Runnable runnable = new Runnable() { // from class: androidx.media3.session.SessionToken$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Context context2 = context;
                String str2 = str;
                create.set(new SessionToken(token, str2, SessionToken.getUid(context2.getPackageManager(), str2), mediaControllerCompat.getSessionInfo()));
            }
        };
        handler.postDelayed(runnable, 500L);
        mediaControllerCompat.sendCommand("androidx.media3.session.SESSION_COMMAND_REQUEST_SESSION3_TOKEN", null, new ResultReceiver(handler) { // from class: androidx.media3.session.SessionToken.1
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                handler.removeCallbacksAndMessages(null);
                try {
                    create.set(SessionToken.fromBundle(bundle, token.getToken()));
                } catch (RuntimeException unused) {
                    runnable.run();
                }
            }
        });
        return create;
    }

    public static ImmutableSet<SessionToken> getAllServiceTokens(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList<ResolveInfo> arrayList = new ArrayList();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(new Intent(MediaLibraryService.SERVICE_INTERFACE), 128);
        if (queryIntentServices != null) {
            arrayList.addAll(queryIntentServices);
        }
        List<ResolveInfo> queryIntentServices2 = packageManager.queryIntentServices(new Intent(MediaSessionService.SERVICE_INTERFACE), 128);
        if (queryIntentServices2 != null) {
            arrayList.addAll(queryIntentServices2);
        }
        List<ResolveInfo> queryIntentServices3 = packageManager.queryIntentServices(new Intent("android.media.browse.MediaBrowserService"), 128);
        if (queryIntentServices3 != null) {
            arrayList.addAll(queryIntentServices3);
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (ResolveInfo resolveInfo : arrayList) {
            if (resolveInfo != null && resolveInfo.serviceInfo != null) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                builder.add((ImmutableSet.Builder) new SessionToken(context, new ComponentName(serviceInfo.packageName, serviceInfo.name)));
            }
        }
        return builder.build();
    }

    private static MediaSessionCompat.Token createCompatToken(Parcelable parcelable) {
        if (parcelable instanceof MediaSession.Token) {
            return MediaSessionCompat.Token.fromToken((MediaSession.Token) parcelable);
        }
        return (MediaSessionCompat.Token) LegacyParcelableUtil.convert(parcelable, MediaSessionCompat.Token.CREATOR);
    }

    private static boolean isInterfaceDeclared(PackageManager packageManager, String str, ComponentName componentName) {
        Intent intent = new Intent(str);
        intent.setPackage(componentName.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
        if (queryIntentServices != null) {
            for (int i = 0; i < queryIntentServices.size(); i++) {
                ResolveInfo resolveInfo = queryIntentServices.get(i);
                if (resolveInfo != null && resolveInfo.serviceInfo != null && TextUtils.equals(resolveInfo.serviceInfo.name, componentName.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getUid(PackageManager packageManager, String str) {
        try {
            return packageManager.getApplicationInfo(str, 0).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (this.impl instanceof SessionTokenImplBase) {
            bundle.putInt(FIELD_IMPL_TYPE, 0);
        } else {
            bundle.putInt(FIELD_IMPL_TYPE, 1);
        }
        bundle.putBundle(FIELD_IMPL, this.impl.toBundle());
        return bundle;
    }

    public static SessionToken fromBundle(Bundle bundle) {
        return new SessionToken(bundle, (MediaSession.Token) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SessionToken fromBundle(Bundle bundle, MediaSession.Token token) {
        return new SessionToken(bundle, token);
    }
}
