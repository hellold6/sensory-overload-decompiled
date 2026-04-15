package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Looper;
import android.os.PowerManager;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class WakeLockManager {
    private static final String TAG = "WakeLockManager";
    private static final String WAKE_LOCK_TAG = "ExoPlayer:WakeLockManager";
    private boolean enabled;
    private boolean stayAwake;
    private final HandlerWrapper wakeLockHandler;
    private final WakeLockManagerInternal wakeLockManagerInternal;

    public WakeLockManager(Context context, Looper looper, Clock clock) {
        this.wakeLockManagerInternal = new WakeLockManagerInternal(context.getApplicationContext());
        this.wakeLockHandler = clock.createHandler(looper, null);
    }

    public void setEnabled(final boolean z) {
        if (this.enabled == z) {
            return;
        }
        this.enabled = z;
        final boolean z2 = this.stayAwake;
        this.wakeLockHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.WakeLockManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                WakeLockManager.this.m174lambda$setEnabled$0$androidxmedia3exoplayerWakeLockManager(z, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setEnabled$0$androidx-media3-exoplayer-WakeLockManager, reason: not valid java name */
    public /* synthetic */ void m174lambda$setEnabled$0$androidxmedia3exoplayerWakeLockManager(boolean z, boolean z2) {
        this.wakeLockManagerInternal.updateWakeLock(z, z2);
    }

    public void setStayAwake(final boolean z) {
        if (this.stayAwake == z) {
            return;
        }
        this.stayAwake = z;
        if (this.enabled) {
            this.wakeLockHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.WakeLockManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WakeLockManager.this.m175lambda$setStayAwake$1$androidxmedia3exoplayerWakeLockManager(z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setStayAwake$1$androidx-media3-exoplayer-WakeLockManager, reason: not valid java name */
    public /* synthetic */ void m175lambda$setStayAwake$1$androidxmedia3exoplayerWakeLockManager(boolean z) {
        this.wakeLockManagerInternal.updateWakeLock(true, z);
    }

    /* loaded from: classes.dex */
    private static final class WakeLockManagerInternal {
        private final Context applicationContext;
        private PowerManager.WakeLock wakeLock;

        public WakeLockManagerInternal(Context context) {
            this.applicationContext = context;
        }

        public void updateWakeLock(boolean z, boolean z2) {
            if (z && this.wakeLock == null) {
                PowerManager powerManager = (PowerManager) this.applicationContext.getSystemService("power");
                if (powerManager == null) {
                    Log.w(WakeLockManager.TAG, "PowerManager is null, therefore not creating the WakeLock.");
                    return;
                } else {
                    PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, WakeLockManager.WAKE_LOCK_TAG);
                    this.wakeLock = newWakeLock;
                    newWakeLock.setReferenceCounted(false);
                }
            }
            PowerManager.WakeLock wakeLock = this.wakeLock;
            if (wakeLock == null) {
                return;
            }
            if (z && z2) {
                wakeLock.acquire();
            } else {
                wakeLock.release();
            }
        }
    }
}
