package expo.modules.video.playbackService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.CommandButton;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionService;
import androidx.media3.session.MediaStyleNotificationHelper;
import androidx.media3.session.SessionCommand;
import expo.modules.core.logging.Logger;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.video.R;
import expo.modules.video.VideoExceptionsKt;
import expo.modules.video.player.VideoPlayer;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: ExpoVideoPlaybackService.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u000fJ\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000fJ\u0012\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0018\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u0015H\u0016J\u0012\u0010+\u001a\u00020\u001d2\b\u0010)\u001a\u0004\u0018\u00010\u0010H\u0003J\n\u0010,\u001a\u0004\u0018\u00010\u0010H\u0003J\u0012\u0010-\u001a\u00020\u001d2\b\u0010.\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010/\u001a\u0004\u0018\u00010\u00102\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u001dH\u0016J\u001a\u00103\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u00102\b\b\u0002\u0010*\u001a\u00020\u0015H\u0003J\b\u00104\u001a\u00020\u001dH\u0002J\u0010\u00105\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000fH\u0003J\b\u00106\u001a\u00020\u001dH\u0003J\f\u00107\u001a\u00020\u0015*\u00020\u0010H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R$\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lexpo/modules/video/playbackService/ExpoVideoPlaybackService;", "Landroidx/media3/session/MediaSessionService;", "<init>", "()V", "weakContext", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "value", "appContext", "getAppContext", "()Lexpo/modules/kotlin/AppContext;", "setAppContext", "(Lexpo/modules/kotlin/AppContext;)V", "mediaSessions", "", "Landroidx/media3/exoplayer/ExoPlayer;", "Landroidx/media3/session/MediaSession;", "binder", "Lexpo/modules/video/playbackService/PlaybackServiceBinder;", "mostRecentInteractionSession", "isForeground", "", "commandSeekForward", "Landroidx/media3/session/SessionCommand;", "commandSeekBackward", "seekForwardButton", "Landroidx/media3/session/CommandButton;", "seekBackwardButton", "setShowNotification", "", ExpoVideoPlaybackService.SESSION_SHOW_NOTIFICATION, "player", "registerPlayer", "videoPlayer", "Lexpo/modules/video/player/VideoPlayer;", "unregisterPlayer", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onUpdateNotification", "session", "startInForegroundRequired", "setMostRecentInteractionSession", "findMostRecentInteractionSession", "onTaskRemoved", "rootIntent", "onGetSession", "controllerInfo", "Landroidx/media3/session/MediaSession$ControllerInfo;", "onDestroy", "createNotification", "cleanup", "hidePlayerNotification", "hideAllNotifications", "wantsToShowNotification", "Companion", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExpoVideoPlaybackService extends MediaSessionService {
    public static final String CHANNEL_ID = "PlaybackService";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SEEK_BACKWARD_COMMAND = "SEEK_REWIND";
    public static final String SEEK_FORWARD_COMMAND = "SEEK_FORWARD";
    public static final long SEEK_INTERVAL_MS = 10000;
    public static final String SESSION_SHOW_NOTIFICATION = "showNotification";
    private final SessionCommand commandSeekBackward;
    private final SessionCommand commandSeekForward;
    private boolean isForeground;
    private MediaSession mostRecentInteractionSession;
    private final CommandButton seekBackwardButton;
    private final CommandButton seekForwardButton;
    private WeakReference<AppContext> weakContext;
    private final Map<ExoPlayer, MediaSession> mediaSessions = new LinkedHashMap();
    private final PlaybackServiceBinder binder = new PlaybackServiceBinder(this);

    @Override // androidx.media3.session.MediaSessionService
    public MediaSession onGetSession(MediaSession.ControllerInfo controllerInfo) {
        Intrinsics.checkNotNullParameter(controllerInfo, "controllerInfo");
        return null;
    }

    public ExpoVideoPlaybackService() {
        SessionCommand sessionCommand = new SessionCommand(SEEK_FORWARD_COMMAND, Bundle.EMPTY);
        this.commandSeekForward = sessionCommand;
        SessionCommand sessionCommand2 = new SessionCommand(SEEK_BACKWARD_COMMAND, Bundle.EMPTY);
        this.commandSeekBackward = sessionCommand2;
        CommandButton build = new CommandButton.Builder().setDisplayName("rewind").setSessionCommand(sessionCommand).setIconResId(R.drawable.seek_forwards_10s).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        this.seekForwardButton = build;
        CommandButton build2 = new CommandButton.Builder().setDisplayName("forward").setSessionCommand(sessionCommand2).setIconResId(R.drawable.seek_backwards_10s).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        this.seekBackwardButton = build2;
    }

    public final AppContext getAppContext() {
        WeakReference<AppContext> weakReference = this.weakContext;
        if (weakReference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weakContext");
            weakReference = null;
        }
        AppContext appContext = weakReference.get();
        if (appContext != null) {
            return appContext;
        }
        throw new Exceptions.AppContextLost();
    }

    public final void setAppContext(AppContext value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.weakContext = new WeakReference<>(value);
    }

    public final void setShowNotification(boolean showNotification, ExoPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        BuildersKt__Builders_commonKt.launch$default(getAppContext().getMainQueue(), null, null, new ExpoVideoPlaybackService$setShowNotification$1(this, player, showNotification, null), 3, null);
    }

    public final void registerPlayer(VideoPlayer videoPlayer) {
        Intrinsics.checkNotNullParameter(videoPlayer, "videoPlayer");
        BuildersKt__Builders_commonKt.launch$default(getAppContext().getMainQueue(), null, null, new ExpoVideoPlaybackService$registerPlayer$1(videoPlayer, this, null), 3, null);
    }

    public final void unregisterPlayer(ExoPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        BuildersKt__Builders_commonKt.launch$default(getAppContext().getMainQueue(), null, null, new ExpoVideoPlaybackService$unregisterPlayer$1(this, player, null), 3, null);
    }

    @Override // androidx.media3.session.MediaSessionService, android.app.Service
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return this.binder;
    }

    @Override // androidx.media3.session.MediaSessionService
    public void onUpdateNotification(MediaSession session, boolean startInForegroundRequired) {
        Intrinsics.checkNotNullParameter(session, "session");
        BuildersKt__Builders_commonKt.launch$default(getAppContext().getMainQueue(), null, null, new ExpoVideoPlaybackService$onUpdateNotification$1(startInForegroundRequired, this, session, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMostRecentInteractionSession(MediaSession session) {
        Player player;
        if (session != null && (player = session.getPlayer()) != null && !player.getPlayWhenReady()) {
            stopForeground(2);
            this.isForeground = false;
        }
        if (!Intrinsics.areEqual(this.mostRecentInteractionSession, session)) {
            hideAllNotifications();
        }
        this.mostRecentInteractionSession = session;
        if (session != null) {
            createNotification(session, session.getPlayer().getPlayWhenReady());
        } else {
            stopForeground(1);
            this.isForeground = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaSession findMostRecentInteractionSession() {
        Object obj;
        Object obj2;
        List distinct = CollectionsKt.distinct(CollectionsKt.plus((Collection) CollectionsKt.listOfNotNull(this.mostRecentInteractionSession), (Iterable) CollectionsKt.toList(this.mediaSessions.values())));
        Iterator it = distinct.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            MediaSession mediaSession = (MediaSession) obj2;
            if (wantsToShowNotification(mediaSession) && mediaSession.getPlayer().getPlayWhenReady()) {
                break;
            }
        }
        MediaSession mediaSession2 = (MediaSession) obj2;
        if (mediaSession2 != null) {
            return mediaSession2;
        }
        Iterator it2 = distinct.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (wantsToShowNotification((MediaSession) next)) {
                obj = next;
                break;
            }
        }
        return (MediaSession) obj;
    }

    @Override // androidx.media3.session.MediaSessionService, android.app.Service
    public void onTaskRemoved(Intent rootIntent) {
        cleanup();
        stopSelf();
    }

    @Override // androidx.media3.session.MediaSessionService, android.app.Service
    public void onDestroy() {
        cleanup();
        super.onDestroy();
    }

    static /* synthetic */ void createNotification$default(ExpoVideoPlaybackService expoVideoPlaybackService, MediaSession mediaSession, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        expoVideoPlaybackService.createNotification(mediaSession, z);
    }

    private final void createNotification(MediaSession session, boolean startInForegroundRequired) {
        String str;
        MediaMetadata mediaMetadata;
        if (session.getPlayer().getCurrentMediaItem() == null) {
            return;
        }
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_ID, 2));
        }
        MediaItem currentMediaItem = session.getPlayer().getCurrentMediaItem();
        if (currentMediaItem == null || (mediaMetadata = currentMediaItem.mediaMetadata) == null || (str = mediaMetadata.title) == null) {
        }
        Notification build = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(androidx.media3.session.R.drawable.media3_icon_circular_play).setContentTitle(str).setStyle(new MediaStyleNotificationHelper.MediaStyle(session)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        int hashCode = session.getPlayer().hashCode();
        if (startInForegroundRequired) {
            try {
                startForeground(hashCode, build);
                this.isForeground = true;
                Unit unit = Unit.INSTANCE;
                return;
            } catch (Exception e) {
                Logger jsLogger = getAppContext().getJsLogger();
                if (jsLogger != null) {
                    jsLogger.error(VideoExceptionsKt.getPlaybackServiceErrorMessage$default("Failed to start the expo-video foreground service", null, 2, null), e);
                    Unit unit2 = Unit.INSTANCE;
                    return;
                }
                return;
            }
        }
        notificationManager.notify(hashCode, build);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cleanup() {
        BuildersKt__Builders_commonKt.launch$default(getAppContext().getMainQueue(), null, null, new ExpoVideoPlaybackService$cleanup$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hidePlayerNotification(ExoPlayer player) {
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(player.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideAllNotifications() {
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancelAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean wantsToShowNotification(MediaSession mediaSession) {
        return mediaSession.getSessionExtras().getBoolean(SESSION_SHOW_NOTIFICATION, false);
    }

    /* compiled from: ExpoVideoPlaybackService.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lexpo/modules/video/playbackService/ExpoVideoPlaybackService$Companion;", "", "<init>", "()V", "SEEK_FORWARD_COMMAND", "", "SEEK_BACKWARD_COMMAND", "CHANNEL_ID", "SESSION_SHOW_NOTIFICATION", "SEEK_INTERVAL_MS", "", "startService", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "context", "Landroid/content/Context;", "serviceConnection", "Lexpo/modules/video/playbackService/PlaybackServiceConnection;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean startService(AppContext appContext, Context context, PlaybackServiceConnection serviceConnection) {
            Intrinsics.checkNotNullParameter(appContext, "appContext");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(serviceConnection, "serviceConnection");
            Context reactContext = appContext.getReactContext();
            if (reactContext == null) {
                return false;
            }
            Intent intent = new Intent(context, (Class<?>) ExpoVideoPlaybackService.class);
            intent.setAction(MediaSessionService.SERVICE_INTERFACE);
            reactContext.startService(intent);
            return reactContext.bindService(intent, serviceConnection, Build.VERSION.SDK_INT >= 29 ? FragmentTransaction.TRANSIT_FRAGMENT_OPEN : 1);
        }
    }
}
