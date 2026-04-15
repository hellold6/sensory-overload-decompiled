package androidx.media3.exoplayer;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Looper;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class WifiLockManager {
    private static final String TAG = "WifiLockManager";
    private static final String WIFI_LOCK_TAG = "ExoPlayer:WifiLockManager";
    private boolean enabled;
    private boolean stayAwake;
    private final HandlerWrapper wifiLockHandler;
    private final WifiLockManagerInternal wifiLockManagerInternal;

    public WifiLockManager(Context context, Looper looper, Clock clock) {
        this.wifiLockManagerInternal = new WifiLockManagerInternal(context.getApplicationContext());
        this.wifiLockHandler = clock.createHandler(looper, null);
    }

    public void setEnabled(final boolean z) {
        if (this.enabled == z) {
            return;
        }
        this.enabled = z;
        final boolean z2 = this.stayAwake;
        this.wifiLockHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.WifiLockManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WifiLockManager.this.m176lambda$setEnabled$0$androidxmedia3exoplayerWifiLockManager(z, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setEnabled$0$androidx-media3-exoplayer-WifiLockManager, reason: not valid java name */
    public /* synthetic */ void m176lambda$setEnabled$0$androidxmedia3exoplayerWifiLockManager(boolean z, boolean z2) {
        this.wifiLockManagerInternal.updateWifiLock(z, z2);
    }

    public void setStayAwake(final boolean z) {
        if (this.stayAwake == z) {
            return;
        }
        this.stayAwake = z;
        if (this.enabled) {
            this.wifiLockHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.WifiLockManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WifiLockManager.this.m177lambda$setStayAwake$1$androidxmedia3exoplayerWifiLockManager(z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setStayAwake$1$androidx-media3-exoplayer-WifiLockManager, reason: not valid java name */
    public /* synthetic */ void m177lambda$setStayAwake$1$androidxmedia3exoplayerWifiLockManager(boolean z) {
        this.wifiLockManagerInternal.updateWifiLock(true, z);
    }

    /* loaded from: classes.dex */
    private static final class WifiLockManagerInternal {
        private final Context applicationContext;
        private WifiManager.WifiLock wifiLock;

        public WifiLockManagerInternal(Context context) {
            this.applicationContext = context;
        }

        public void updateWifiLock(boolean z, boolean z2) {
            if (z && this.wifiLock == null) {
                WifiManager wifiManager = (WifiManager) this.applicationContext.getApplicationContext().getSystemService("wifi");
                if (wifiManager == null) {
                    Log.w(WifiLockManager.TAG, "WifiManager is null, therefore not creating the WifiLock.");
                    return;
                } else {
                    WifiManager.WifiLock createWifiLock = wifiManager.createWifiLock(3, WifiLockManager.WIFI_LOCK_TAG);
                    this.wifiLock = createWifiLock;
                    createWifiLock.setReferenceCounted(false);
                }
            }
            WifiManager.WifiLock wifiLock = this.wifiLock;
            if (wifiLock == null) {
                return;
            }
            if (z && z2) {
                wifiLock.acquire();
            } else {
                wifiLock.release();
            }
        }
    }
}
