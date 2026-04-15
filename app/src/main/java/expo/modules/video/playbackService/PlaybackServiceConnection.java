package expo.modules.video.playbackService;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import expo.modules.core.logging.Logger;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.video.VideoExceptionsKt;
import expo.modules.video.player.VideoPlayer;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlaybackServiceConnection.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0012\u0010\u001e\u001a\u00020\u00182\b\u0010\u001f\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010 \u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\u00060\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Lexpo/modules/video/playbackService/PlaybackServiceConnection;", "Landroid/content/ServiceConnection;", "player", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/video/player/VideoPlayer;", "appContext", "Lexpo/modules/kotlin/AppContext;", "<init>", "(Ljava/lang/ref/WeakReference;Lexpo/modules/kotlin/AppContext;)V", "getPlayer", "()Ljava/lang/ref/WeakReference;", "value", "Lexpo/modules/video/playbackService/PlaybackServiceBinder;", "playbackServiceBinder", "getPlaybackServiceBinder", "()Lexpo/modules/video/playbackService/PlaybackServiceBinder;", "", "isConnected", "()Z", "_appContext", "kotlin.jvm.PlatformType", "getAppContext", "()Lexpo/modules/kotlin/AppContext;", "onServiceConnected", "", "componentName", "Landroid/content/ComponentName;", "binder", "Landroid/os/IBinder;", "onServiceDisconnected", "onBindingDied", "name", "onNullBinding", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlaybackServiceConnection implements ServiceConnection {
    private final WeakReference<AppContext> _appContext;
    private boolean isConnected;
    private PlaybackServiceBinder playbackServiceBinder;
    private final WeakReference<VideoPlayer> player;

    public PlaybackServiceConnection(WeakReference<VideoPlayer> player, AppContext appContext) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.player = player;
        this._appContext = new WeakReference<>(appContext);
    }

    public final WeakReference<VideoPlayer> getPlayer() {
        return this.player;
    }

    public final PlaybackServiceBinder getPlaybackServiceBinder() {
        return this.playbackServiceBinder;
    }

    /* renamed from: isConnected, reason: from getter */
    public final boolean getIsConnected() {
        return this.isConnected;
    }

    private final AppContext getAppContext() {
        AppContext appContext = this._appContext.get();
        if (appContext != null) {
            return appContext;
        }
        throw new Exceptions.AppContextLost();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder binder) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        Intrinsics.checkNotNullParameter(binder, "binder");
        VideoPlayer videoPlayer = this.player.get();
        if (videoPlayer == null) {
            return;
        }
        PlaybackServiceBinder playbackServiceBinder = binder instanceof PlaybackServiceBinder ? (PlaybackServiceBinder) binder : null;
        if (playbackServiceBinder == null) {
            Logger jsLogger = getAppContext().getJsLogger();
            if (jsLogger != null) {
                Logger.error$default(jsLogger, VideoExceptionsKt.getPlaybackServiceErrorMessage$default("Expo-video could not bind to the playback service", null, 2, null), null, 2, null);
                return;
            }
            return;
        }
        this.isConnected = true;
        this.playbackServiceBinder = playbackServiceBinder;
        playbackServiceBinder.getService().setAppContext(getAppContext());
        playbackServiceBinder.getService().registerPlayer(videoPlayer);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.playbackServiceBinder = null;
        this.isConnected = false;
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName name) {
        this.isConnected = false;
        Logger jsLogger = getAppContext().getJsLogger();
        if (jsLogger != null) {
            Logger.error$default(jsLogger, VideoExceptionsKt.getPlaybackServiceErrorMessage("Expo-video has lost connection to the playback service binder", "This will cause issues with now playing notification and sustaining background playback."), null, 2, null);
        }
        super.onBindingDied(name);
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.isConnected = false;
        Logger jsLogger = getAppContext().getJsLogger();
        if (jsLogger != null) {
            Logger.error$default(jsLogger, VideoExceptionsKt.getPlaybackServiceErrorMessage$default("Expo Video could not bind to the playback service", null, 2, null), null, 2, null);
        }
        super.onNullBinding(componentName);
    }
}
