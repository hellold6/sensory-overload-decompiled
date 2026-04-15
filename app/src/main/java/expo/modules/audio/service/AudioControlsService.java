package expo.modules.audio.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.CommandButton;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionService;
import androidx.media3.session.MediaStyleNotificationHelper;
import androidx.media3.session.R;
import androidx.media3.session.SessionCommand;
import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.audio.AudioLockScreenOptions;
import expo.modules.audio.AudioPlayer;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.service.NotificationsService;
import java.net.URL;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AudioControlsService.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 C2\u00020\u0001:\u0002BCB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u001a\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0015H\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020 H\u0002J\n\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\n\u0010$\u001a\u0004\u0018\u00010%H\u0002J\u0010\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020(H\u0002J\u0018\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020(H\u0016J*\u0010.\u001a\u00020 2\b\u0010/\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\rH\u0002J\u001a\u00102\u001a\u00020 2\u0006\u0010/\u001a\u00020\u000b2\b\u00100\u001a\u0004\u0018\u00010\tH\u0002J\b\u00103\u001a\u00020 H\u0002J\u0012\u00104\u001a\u0004\u0018\u00010\u00072\u0006\u00105\u001a\u000206H\u0016J\u001c\u00107\u001a\u00020 2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020 09H\u0002J\u0012\u0010;\u001a\u00020<2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J&\u0010=\u001a\u00020 2\u0006\u0010>\u001a\u00020\u00112\u0014\u0010?\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0004\u0012\u00020 09H\u0002J\b\u0010@\u001a\u00020 H\u0002J\b\u0010A\u001a\u00020 H\u0016R\u0012\u0010\u0004\u001a\u00060\u0005R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lexpo/modules/audio/service/AudioControlsService;", "Landroidx/media3/session/MediaSessionService;", "<init>", "()V", "binder", "Lexpo/modules/audio/service/AudioControlsService$AudioControlsBinder;", "mediaSession", "Landroidx/media3/session/MediaSession;", "currentMetadata", "Lexpo/modules/audio/Metadata;", "currentPlayer", "Lexpo/modules/audio/AudioPlayer;", "currentOptions", "Lexpo/modules/audio/AudioLockScreenOptions;", PermissionsResponse.SCOPE_KEY, "Lkotlinx/coroutines/CoroutineScope;", "currentArtworkUrl", "Ljava/net/URL;", "currentArtwork", "Landroid/graphics/Bitmap;", "notificationId", "", "getNotificationId", "()I", "playbackListener", "Landroidx/media3/common/Player$Listener;", "onStartCommand", "intent", "Landroid/content/Intent;", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY, "startId", "onCreate", "", "createNotificationChannelIfNeeded", "buildContentIntent", "Landroid/app/PendingIntent;", "buildNotification", "Landroid/app/Notification;", "updateSessionCustomLayout", "isPlaying", "", "postOrStartForegroundNotification", "startInForeground", "onUpdateNotification", "session", "startInForegroundRequired", "setActivePlayerInternal", "player", TtmlNode.TAG_METADATA, "options", "updateMetadataInternal", "clearSessionInternal", "onGetSession", "controllerInfo", "Landroidx/media3/session/MediaSession$ControllerInfo;", "withPlayerOnAppThread", "block", "Lkotlin/Function1;", "Landroidx/media3/common/Player;", "onBind", "Landroid/os/IBinder;", "loadArtworkFromUrl", ImagesContract.URL, "callback", "hideNotification", "onDestroy", "AudioControlsBinder", "Companion", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioControlsService extends MediaSessionService {
    private static final String ACTION_PAUSE = "expo.modules.audio.action.PAUSE";
    private static final String ACTION_PLAY = "expo.modules.audio.action.PLAY";
    public static final String ACTION_SEEK_BACKWARD = "expo.modules.audio.action.SEEK_REWIND";
    public static final String ACTION_SEEK_FORWARD = "expo.modules.audio.action.SEEK_FORWARD";
    private static final String ACTION_TOGGLE = "expo.modules.audio.action.TOGGLE";
    private static final String CHANNEL_ID = "expo_audio_channel";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final long SEEK_INTERVAL_MS = 10000;
    private static volatile AudioControlsService instance;
    private static expo.modules.audio.Metadata pendingMetadata;
    private static AudioLockScreenOptions pendingOptions;
    private static AudioPlayer pendingPlayer;
    private Bitmap currentArtwork;
    private URL currentArtworkUrl;
    private expo.modules.audio.Metadata currentMetadata;
    private AudioLockScreenOptions currentOptions;
    private AudioPlayer currentPlayer;
    private MediaSession mediaSession;
    private Player.Listener playbackListener;
    private final AudioControlsBinder binder = new AudioControlsBinder();
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    private final int getNotificationId() {
        AudioPlayer audioPlayer = this.currentPlayer;
        return audioPlayer != null ? audioPlayer.hashCode() : CHANNEL_ID.hashCode();
    }

    /* compiled from: AudioControlsService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/audio/service/AudioControlsService$AudioControlsBinder;", "Landroid/os/Binder;", "<init>", "(Lexpo/modules/audio/service/AudioControlsService;)V", "getService", "Lexpo/modules/audio/service/AudioControlsService;", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class AudioControlsBinder extends Binder {
        public AudioControlsBinder() {
        }

        /* renamed from: getService, reason: from getter */
        public final AudioControlsService getThis$0() {
            return AudioControlsService.this;
        }
    }

    @Override // androidx.media3.session.MediaSessionService, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            switch (action.hashCode()) {
                case -2041083429:
                    if (action.equals(ACTION_SEEK_FORWARD)) {
                        withPlayerOnAppThread(new Function1() { // from class: expo.modules.audio.service.AudioControlsService$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit onStartCommand$lambda$3;
                                onStartCommand$lambda$3 = AudioControlsService.onStartCommand$lambda$3((Player) obj);
                                return onStartCommand$lambda$3;
                            }
                        });
                        break;
                    }
                    break;
                case -1106621831:
                    if (action.equals(ACTION_PAUSE)) {
                        withPlayerOnAppThread(new Function1() { // from class: expo.modules.audio.service.AudioControlsService$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit onStartCommand$lambda$1;
                                onStartCommand$lambda$1 = AudioControlsService.onStartCommand$lambda$1((Player) obj);
                                return onStartCommand$lambda$1;
                            }
                        });
                        break;
                    }
                    break;
                case 181479185:
                    if (action.equals(ACTION_TOGGLE)) {
                        withPlayerOnAppThread(new Function1() { // from class: expo.modules.audio.service.AudioControlsService$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit onStartCommand$lambda$2;
                                onStartCommand$lambda$2 = AudioControlsService.onStartCommand$lambda$2((Player) obj);
                                return onStartCommand$lambda$2;
                            }
                        });
                        break;
                    }
                    break;
                case 407156421:
                    if (action.equals(ACTION_SEEK_BACKWARD)) {
                        withPlayerOnAppThread(new Function1() { // from class: expo.modules.audio.service.AudioControlsService$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit onStartCommand$lambda$4;
                                onStartCommand$lambda$4 = AudioControlsService.onStartCommand$lambda$4((Player) obj);
                                return onStartCommand$lambda$4;
                            }
                        });
                        break;
                    }
                    break;
                case 657049137:
                    if (action.equals(ACTION_PLAY)) {
                        withPlayerOnAppThread(new Function1() { // from class: expo.modules.audio.service.AudioControlsService$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit onStartCommand$lambda$0;
                                onStartCommand$lambda$0 = AudioControlsService.onStartCommand$lambda$0((Player) obj);
                                return onStartCommand$lambda$0;
                            }
                        });
                        break;
                    }
                    break;
            }
        }
        postOrStartForegroundNotification(false);
        return super.onStartCommand(intent, flags, startId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStartCommand$lambda$0(Player it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.play();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStartCommand$lambda$1(Player it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.pause();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStartCommand$lambda$2(Player player) {
        Intrinsics.checkNotNullParameter(player, "player");
        if (player.isPlaying()) {
            player.pause();
        } else {
            player.play();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStartCommand$lambda$3(Player player) {
        Intrinsics.checkNotNullParameter(player, "player");
        player.seekTo(player.getCurrentPosition() + 10000);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStartCommand$lambda$4(Player player) {
        Intrinsics.checkNotNullParameter(player, "player");
        player.seekTo(player.getCurrentPosition() - 10000);
        return Unit.INSTANCE;
    }

    @Override // androidx.media3.session.MediaSessionService, android.app.Service
    public void onCreate() {
        super.onCreate();
        instance = this;
        createNotificationChannelIfNeeded();
        AudioPlayer audioPlayer = pendingPlayer;
        if (audioPlayer != null) {
            setActivePlayerInternal(audioPlayer, pendingMetadata, pendingOptions);
            pendingPlayer = null;
            pendingMetadata = null;
        }
    }

    private final void createNotificationChannelIfNeeded() {
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        if (Build.VERSION.SDK_INT < 26 || notificationManager.getNotificationChannel(CHANNEL_ID) != null) {
            return;
        }
        notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_ID, 2));
    }

    private final PendingIntent buildContentIntent() {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage == null) {
            return null;
        }
        return PendingIntent.getActivity(this, 0, launchIntentForPackage, 201326592);
    }

    private final Notification buildNotification() {
        String str;
        MediaSession mediaSession = this.mediaSession;
        if (mediaSession == null) {
            return null;
        }
        NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.media3_icon_circular_play);
        expo.modules.audio.Metadata metadata = this.currentMetadata;
        if (metadata == null || (str = metadata.getTitle()) == null) {
            str = "\u200e";
        }
        NotificationCompat.Builder contentTitle = smallIcon.setContentTitle(str);
        expo.modules.audio.Metadata metadata2 = this.currentMetadata;
        NotificationCompat.Builder contentText = contentTitle.setContentText(metadata2 != null ? metadata2.getArtist() : null);
        expo.modules.audio.Metadata metadata3 = this.currentMetadata;
        NotificationCompat.Builder category = contentText.setSubText(metadata3 != null ? metadata3.getAlbumTitle() : null).setLargeIcon(this.currentArtwork).setContentIntent(buildContentIntent()).setAutoCancel(false).setCategory(NotificationCompat.CATEGORY_TRANSPORT);
        Intrinsics.checkNotNullExpressionValue(category, "setCategory(...)");
        category.setStyle(new MediaStyleNotificationHelper.MediaStyle(mediaSession));
        return category.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSessionCustomLayout(boolean isPlaying) {
        MediaSession mediaSession = this.mediaSession;
        if (mediaSession == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        AudioLockScreenOptions audioLockScreenOptions = this.currentOptions;
        if (audioLockScreenOptions != null && audioLockScreenOptions.getShowSeekBackward()) {
            CommandButton build = new CommandButton.Builder(CommandButton.ICON_SKIP_BACK).setDisplayName("Seek Backward").setEnabled(true).setSessionCommand(new SessionCommand(ACTION_SEEK_BACKWARD, Bundle.EMPTY)).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            arrayList.add(build);
        }
        CommandButton build2 = new CommandButton.Builder(isPlaying ? CommandButton.ICON_PAUSE : CommandButton.ICON_PLAY).setDisplayName(isPlaying ? "Pause" : "Play").setEnabled(true).setPlayerCommand(1).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        arrayList.add(build2);
        AudioLockScreenOptions audioLockScreenOptions2 = this.currentOptions;
        if (audioLockScreenOptions2 != null && audioLockScreenOptions2.getShowSeekForward()) {
            CommandButton build3 = new CommandButton.Builder(CommandButton.ICON_SKIP_FORWARD).setDisplayName("Seek Forward").setEnabled(true).setSessionCommand(new SessionCommand(ACTION_SEEK_FORWARD, Bundle.EMPTY)).build();
            Intrinsics.checkNotNullExpressionValue(build3, "build(...)");
            arrayList.add(build3);
        }
        mediaSession.setCustomLayout(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void postOrStartForegroundNotification(boolean startInForeground) {
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        Notification buildNotification = buildNotification();
        if (buildNotification == null) {
            return;
        }
        if (startInForeground) {
            if (Build.VERSION.SDK_INT >= 30) {
                startForeground(getNotificationId(), buildNotification, 2);
                return;
            } else {
                startForeground(getNotificationId(), buildNotification);
                return;
            }
        }
        notificationManager.notify(getNotificationId(), buildNotification);
    }

    @Override // androidx.media3.session.MediaSessionService
    public void onUpdateNotification(MediaSession session, boolean startInForegroundRequired) {
        Intrinsics.checkNotNullParameter(session, "session");
        postOrStartForegroundNotification(startInForegroundRequired);
    }

    static /* synthetic */ void setActivePlayerInternal$default(AudioControlsService audioControlsService, AudioPlayer audioPlayer, expo.modules.audio.Metadata metadata, AudioLockScreenOptions audioLockScreenOptions, int i, Object obj) {
        if ((i & 2) != 0) {
            metadata = null;
        }
        if ((i & 4) != 0) {
            audioLockScreenOptions = null;
        }
        audioControlsService.setActivePlayerInternal(audioPlayer, metadata, audioLockScreenOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setActivePlayerInternal(AudioPlayer player, expo.modules.audio.Metadata metadata, AudioLockScreenOptions options) {
        URL artworkUrl;
        AudioPlayer audioPlayer;
        ExoPlayer ref;
        Player.Listener listener = this.playbackListener;
        if (listener != null && (audioPlayer = this.currentPlayer) != null && (ref = audioPlayer.getRef()) != null) {
            ref.removeListener(listener);
        }
        this.playbackListener = null;
        AudioPlayer audioPlayer2 = this.currentPlayer;
        if (audioPlayer2 != null) {
            audioPlayer2.setActiveForLockScreen(false);
        }
        hideNotification();
        this.currentPlayer = player;
        this.currentMetadata = metadata;
        this.currentOptions = options;
        if (metadata != null && (artworkUrl = metadata.getArtworkUrl()) != null) {
            loadArtworkFromUrl(artworkUrl, new Function1() { // from class: expo.modules.audio.service.AudioControlsService$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit activePlayerInternal$lambda$8$lambda$7;
                    activePlayerInternal$lambda$8$lambda$7 = AudioControlsService.setActivePlayerInternal$lambda$8$lambda$7(AudioControlsService.this, (Bitmap) obj);
                    return activePlayerInternal$lambda$8$lambda$7;
                }
            });
        }
        if (player != null) {
            player.setActiveForLockScreen(true);
        }
        if (player != null) {
            MediaSession mediaSession = this.mediaSession;
            if (mediaSession != null) {
                mediaSession.release();
            }
            MediaSession build = new MediaSession.Builder(this, player.getRef()).setCallback((MediaSession.Callback) new AudioMediaSessionCallback()).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            addSession(build);
            this.mediaSession = build;
            updateSessionCustomLayout(player.getRef().isPlaying());
            postOrStartForegroundNotification(true);
            Player.Listener listener2 = new Player.Listener() { // from class: expo.modules.audio.service.AudioControlsService$setActivePlayerInternal$listener$1
                @Override // androidx.media3.common.Player.Listener
                public void onIsPlayingChanged(boolean isPlaying) {
                    AudioControlsService.this.updateSessionCustomLayout(isPlaying);
                    AudioControlsService.this.postOrStartForegroundNotification(false);
                }

                @Override // androidx.media3.common.Player.Listener
                public void onPlaybackStateChanged(int playbackState) {
                    AudioControlsService.this.postOrStartForegroundNotification(false);
                }
            };
            this.playbackListener = listener2;
            player.getRef().addListener(listener2);
            postOrStartForegroundNotification(false);
            return;
        }
        clearSessionInternal();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setActivePlayerInternal$lambda$8$lambda$7(AudioControlsService audioControlsService, Bitmap bitmap) {
        audioControlsService.currentArtwork = bitmap;
        audioControlsService.postOrStartForegroundNotification(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMetadataInternal(AudioPlayer player, expo.modules.audio.Metadata metadata) {
        URL artworkUrl;
        if (!Intrinsics.areEqual(player, this.currentPlayer) || Intrinsics.areEqual(metadata, this.currentMetadata)) {
            return;
        }
        this.currentMetadata = metadata;
        if (metadata != null && (artworkUrl = metadata.getArtworkUrl()) != null) {
            loadArtworkFromUrl(artworkUrl, new Function1() { // from class: expo.modules.audio.service.AudioControlsService$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit updateMetadataInternal$lambda$10$lambda$9;
                    updateMetadataInternal$lambda$10$lambda$9 = AudioControlsService.updateMetadataInternal$lambda$10$lambda$9(AudioControlsService.this, (Bitmap) obj);
                    return updateMetadataInternal$lambda$10$lambda$9;
                }
            });
        } else {
            postOrStartForegroundNotification(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit updateMetadataInternal$lambda$10$lambda$9(AudioControlsService audioControlsService, Bitmap bitmap) {
        audioControlsService.currentArtwork = bitmap;
        audioControlsService.postOrStartForegroundNotification(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearSessionInternal() {
        AudioPlayer audioPlayer;
        ExoPlayer ref;
        AudioPlayer audioPlayer2 = this.currentPlayer;
        if (audioPlayer2 != null) {
            audioPlayer2.setActiveForLockScreen(false);
        }
        Player.Listener listener = this.playbackListener;
        if (listener != null && (audioPlayer = this.currentPlayer) != null && (ref = audioPlayer.getRef()) != null) {
            ref.removeListener(listener);
        }
        this.playbackListener = null;
        this.currentPlayer = null;
        this.currentMetadata = null;
        MediaSession mediaSession = this.mediaSession;
        if (mediaSession != null) {
            mediaSession.release();
        }
        this.mediaSession = null;
        stopForeground(1);
    }

    @Override // androidx.media3.session.MediaSessionService
    public MediaSession onGetSession(MediaSession.ControllerInfo controllerInfo) {
        Intrinsics.checkNotNullParameter(controllerInfo, "controllerInfo");
        return this.mediaSession;
    }

    private final void withPlayerOnAppThread(final Function1<? super Player, Unit> block) {
        final ExoPlayer ref;
        AudioPlayer audioPlayer = this.currentPlayer;
        if (audioPlayer == null || (ref = audioPlayer.getRef()) == null) {
            return;
        }
        Looper applicationLooper = ref.getApplicationLooper();
        Intrinsics.checkNotNullExpressionValue(applicationLooper, "getApplicationLooper(...)");
        if (Intrinsics.areEqual(Looper.myLooper(), applicationLooper)) {
            block.invoke(ref);
        } else {
            new Handler(applicationLooper).post(new Runnable() { // from class: expo.modules.audio.service.AudioControlsService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Function1.this.invoke(ref);
                }
            });
        }
    }

    @Override // androidx.media3.session.MediaSessionService, android.app.Service
    public IBinder onBind(Intent intent) {
        IBinder onBind = super.onBind(intent);
        return onBind == null ? this.binder : onBind;
    }

    private final void loadArtworkFromUrl(URL url, Function1<? super Bitmap, Unit> callback) {
        if (Intrinsics.areEqual(url, this.currentArtworkUrl)) {
            return;
        }
        this.currentArtworkUrl = url;
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AudioControlsService$loadArtworkFromUrl$1(url, callback, null), 3, null);
    }

    private final void hideNotification() {
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(getNotificationId());
    }

    @Override // androidx.media3.session.MediaSessionService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        try {
            CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        } catch (Exception unused) {
        }
        MediaSession mediaSession = this.mediaSession;
        if (mediaSession != null) {
            mediaSession.release();
        }
        this.currentPlayer = null;
    }

    /* compiled from: AudioControlsService.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014J0\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0012J\u0018\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u001e\u001a\u00020\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lexpo/modules/audio/service/AudioControlsService$Companion;", "", "<init>", "()V", "CHANNEL_ID", "", "ACTION_PLAY", "ACTION_PAUSE", "ACTION_TOGGLE", "ACTION_SEEK_FORWARD", "ACTION_SEEK_BACKWARD", "SEEK_INTERVAL_MS", "", "pendingPlayer", "Lexpo/modules/audio/AudioPlayer;", "pendingMetadata", "Lexpo/modules/audio/Metadata;", "pendingOptions", "Lexpo/modules/audio/AudioLockScreenOptions;", "instance", "Lexpo/modules/audio/service/AudioControlsService;", "getInstance", "setActivePlayer", "", "context", "Landroid/content/Context;", "player", TtmlNode.TAG_METADATA, "options", "updateMetadata", "clearSession", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AudioControlsService getInstance() {
            return AudioControlsService.instance;
        }

        public static /* synthetic */ void setActivePlayer$default(Companion companion, Context context, AudioPlayer audioPlayer, expo.modules.audio.Metadata metadata, AudioLockScreenOptions audioLockScreenOptions, int i, Object obj) {
            if ((i & 4) != 0) {
                metadata = null;
            }
            if ((i & 8) != 0) {
                audioLockScreenOptions = null;
            }
            companion.setActivePlayer(context, audioPlayer, metadata, audioLockScreenOptions);
        }

        public final void setActivePlayer(Context context, AudioPlayer player, expo.modules.audio.Metadata metadata, AudioLockScreenOptions options) {
            Intrinsics.checkNotNullParameter(context, "context");
            AudioControlsService companion = getInstance();
            if (companion != null) {
                companion.setActivePlayerInternal(player, metadata, options);
                return;
            }
            Intent intent = new Intent(context, (Class<?>) AudioControlsService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
            AudioControlsService.pendingPlayer = player;
            AudioControlsService.pendingMetadata = metadata;
            AudioControlsService.pendingOptions = options;
        }

        public final void updateMetadata(AudioPlayer player, expo.modules.audio.Metadata metadata) {
            Intrinsics.checkNotNullParameter(player, "player");
            AudioControlsService companion = getInstance();
            if (companion != null) {
                companion.updateMetadataInternal(player, metadata);
            }
        }

        public final void clearSession() {
            AudioControlsService companion = getInstance();
            if (companion != null) {
                companion.clearSessionInternal();
            }
        }
    }
}
