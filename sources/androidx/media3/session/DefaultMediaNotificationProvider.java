package androidx.media3.session;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.media3.common.C;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.CommandButton;
import androidx.media3.session.DefaultMediaNotificationProvider;
import androidx.media3.session.MediaNotification;
import androidx.media3.session.MediaStyleNotificationHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import expo.modules.notifications.service.NotificationsService;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class DefaultMediaNotificationProvider implements MediaNotification.Provider {
    public static final String COMMAND_KEY_COMPACT_VIEW_INDEX = "androidx.media3.session.command.COMPACT_VIEW_INDEX";
    public static final String DEFAULT_CHANNEL_ID = "default_channel_id";
    public static final int DEFAULT_CHANNEL_NAME_RESOURCE_ID = R.string.default_notification_channel_name;
    public static final int DEFAULT_NOTIFICATION_ID = 1001;
    public static final String GROUP_KEY = "media3_group_key";
    private static final String TAG = "NotificationProvider";
    private final String channelId;
    private final int channelNameResourceId;
    private final Context context;
    private final NotificationIdProvider notificationIdProvider;
    private final NotificationManager notificationManager;
    private OnBitmapLoadedFutureCallback pendingOnBitmapLoadedFutureCallback;
    private int smallIconResourceId;

    /* loaded from: classes.dex */
    public interface NotificationIdProvider {
        int getNotificationId(MediaSession mediaSession);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$new$0(MediaSession mediaSession) {
        return 1001;
    }

    @Override // androidx.media3.session.MediaNotification.Provider
    public final boolean handleCustomCommand(MediaSession mediaSession, String str, Bundle bundle) {
        return false;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean built;
        private final Context context;
        private NotificationIdProvider notificationIdProvider = new NotificationIdProvider() { // from class: androidx.media3.session.DefaultMediaNotificationProvider$Builder$$ExternalSyntheticLambda0
            @Override // androidx.media3.session.DefaultMediaNotificationProvider.NotificationIdProvider
            public final int getNotificationId(MediaSession mediaSession) {
                return DefaultMediaNotificationProvider.Builder.lambda$new$0(mediaSession);
            }
        };
        private String channelId = DefaultMediaNotificationProvider.DEFAULT_CHANNEL_ID;
        private int channelNameResourceId = DefaultMediaNotificationProvider.DEFAULT_CHANNEL_NAME_RESOURCE_ID;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ int lambda$new$0(MediaSession mediaSession) {
            return 1001;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ int lambda$setNotificationId$1(int i, MediaSession mediaSession) {
            return i;
        }

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setNotificationId(final int i) {
            this.notificationIdProvider = new NotificationIdProvider() { // from class: androidx.media3.session.DefaultMediaNotificationProvider$Builder$$ExternalSyntheticLambda1
                @Override // androidx.media3.session.DefaultMediaNotificationProvider.NotificationIdProvider
                public final int getNotificationId(MediaSession mediaSession) {
                    return DefaultMediaNotificationProvider.Builder.lambda$setNotificationId$1(i, mediaSession);
                }
            };
            return this;
        }

        public Builder setNotificationIdProvider(NotificationIdProvider notificationIdProvider) {
            this.notificationIdProvider = notificationIdProvider;
            return this;
        }

        public Builder setChannelId(String str) {
            this.channelId = str;
            return this;
        }

        public Builder setChannelName(int i) {
            this.channelNameResourceId = i;
            return this;
        }

        public DefaultMediaNotificationProvider build() {
            Assertions.checkState(!this.built);
            DefaultMediaNotificationProvider defaultMediaNotificationProvider = new DefaultMediaNotificationProvider(this);
            this.built = true;
            return defaultMediaNotificationProvider;
        }
    }

    public DefaultMediaNotificationProvider(Context context) {
        this(context, new NotificationIdProvider() { // from class: androidx.media3.session.DefaultMediaNotificationProvider$$ExternalSyntheticLambda0
            @Override // androidx.media3.session.DefaultMediaNotificationProvider.NotificationIdProvider
            public final int getNotificationId(MediaSession mediaSession) {
                return DefaultMediaNotificationProvider.lambda$new$0(mediaSession);
            }
        }, DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME_RESOURCE_ID);
    }

    public DefaultMediaNotificationProvider(Context context, NotificationIdProvider notificationIdProvider, String str, int i) {
        this.context = context;
        this.notificationIdProvider = notificationIdProvider;
        this.channelId = str;
        this.channelNameResourceId = i;
        this.notificationManager = (NotificationManager) Assertions.checkStateNotNull((NotificationManager) context.getSystemService(NotificationsService.NOTIFICATION_KEY));
        this.smallIconResourceId = R.drawable.media3_notification_small_icon;
    }

    private DefaultMediaNotificationProvider(Builder builder) {
        this(builder.context, builder.notificationIdProvider, builder.channelId, builder.channelNameResourceId);
    }

    @Override // androidx.media3.session.MediaNotification.Provider
    public final MediaNotification createNotification(MediaSession mediaSession, ImmutableList<CommandButton> immutableList, MediaNotification.ActionFactory actionFactory, MediaNotification.Provider.Callback callback) {
        ensureNotificationChannel();
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = immutableList.get(i);
            if (commandButton.sessionCommand != null && commandButton.sessionCommand.commandCode == 0 && commandButton.isEnabled) {
                builder.add((ImmutableList.Builder) immutableList.get(i));
            }
        }
        Player player = mediaSession.getPlayer();
        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this.context, this.channelId);
        int notificationId = this.notificationIdProvider.getNotificationId(mediaSession);
        MediaStyleNotificationHelper.MediaStyle mediaStyle = new MediaStyleNotificationHelper.MediaStyle(mediaSession);
        mediaStyle.setShowActionsInCompactView(addNotificationActions(mediaSession, getMediaButtons(mediaSession, player.getAvailableCommands(), builder.build(), !Util.shouldShowPlayButton(player, mediaSession.getShowPlayButtonIfPlaybackIsSuppressed())), builder2, actionFactory));
        if (player.isCommandAvailable(18)) {
            MediaMetadata mediaMetadata = player.getMediaMetadata();
            builder2.setContentTitle(getNotificationContentTitle(mediaMetadata)).setContentText(getNotificationContentText(mediaMetadata));
            ListenableFuture<Bitmap> loadBitmapFromMetadata = mediaSession.getBitmapLoader().loadBitmapFromMetadata(mediaMetadata);
            if (loadBitmapFromMetadata != null) {
                OnBitmapLoadedFutureCallback onBitmapLoadedFutureCallback = this.pendingOnBitmapLoadedFutureCallback;
                if (onBitmapLoadedFutureCallback != null) {
                    onBitmapLoadedFutureCallback.discardIfPending();
                }
                if (loadBitmapFromMetadata.isDone()) {
                    try {
                        builder2.setLargeIcon((Bitmap) Futures.getDone(loadBitmapFromMetadata));
                    } catch (CancellationException | ExecutionException e) {
                        Log.w(TAG, getBitmapLoadErrorMessage(e));
                    }
                } else {
                    OnBitmapLoadedFutureCallback onBitmapLoadedFutureCallback2 = new OnBitmapLoadedFutureCallback(notificationId, builder2, callback);
                    this.pendingOnBitmapLoadedFutureCallback = onBitmapLoadedFutureCallback2;
                    final Handler applicationHandler = mediaSession.getImpl().getApplicationHandler();
                    Objects.requireNonNull(applicationHandler);
                    Futures.addCallback(loadBitmapFromMetadata, onBitmapLoadedFutureCallback2, new Executor() { // from class: androidx.media3.session.DefaultMediaNotificationProvider$$ExternalSyntheticLambda1
                        @Override // java.util.concurrent.Executor
                        public final void execute(Runnable runnable) {
                            applicationHandler.post(runnable);
                        }
                    });
                }
            }
        }
        long playbackStartTimeEpochMs = getPlaybackStartTimeEpochMs(player);
        boolean z = playbackStartTimeEpochMs != C.TIME_UNSET;
        if (!z) {
            playbackStartTimeEpochMs = 0;
        }
        builder2.setWhen(playbackStartTimeEpochMs).setShowWhen(z).setUsesChronometer(z);
        if (Build.VERSION.SDK_INT >= 31) {
            Api31.setForegroundServiceBehavior(builder2);
        }
        return new MediaNotification(notificationId, builder2.setContentIntent(mediaSession.getSessionActivity()).setDeleteIntent(actionFactory.createNotificationDismissalIntent(mediaSession)).setOnlyAlertOnce(true).setSmallIcon(this.smallIconResourceId).setStyle(mediaStyle).setVisibility(1).setOngoing(false).setGroup(GROUP_KEY).build());
    }

    public final void setSmallIcon(int i) {
        this.smallIconResourceId = i;
    }

    protected ImmutableList<CommandButton> getMediaButtons(MediaSession mediaSession, Player.Commands commands, ImmutableList<CommandButton> immutableList, boolean z) {
        ImmutableList<CommandButton> customLayoutFromMediaButtonPreferences = CommandButton.getCustomLayoutFromMediaButtonPreferences(immutableList, true, true);
        boolean containsButtonForSlot = CommandButton.containsButtonForSlot(customLayoutFromMediaButtonPreferences, 2);
        boolean containsButtonForSlot2 = CommandButton.containsButtonForSlot(customLayoutFromMediaButtonPreferences, 3);
        ImmutableList.Builder builder = new ImmutableList.Builder();
        int i = 0;
        if (containsButtonForSlot) {
            builder.add((ImmutableList.Builder) customLayoutFromMediaButtonPreferences.get(0).copyWithSlots(ImmutableIntArray.of(2)));
            i = 1;
        } else if (commands.containsAny(7, 6)) {
            builder.add((ImmutableList.Builder) new CommandButton.Builder(CommandButton.ICON_PREVIOUS).setPlayerCommand(6).setDisplayName(this.context.getString(R.string.media3_controls_seek_to_previous_description)).build());
        }
        if (commands.contains(1)) {
            if (z) {
                builder.add((ImmutableList.Builder) new CommandButton.Builder(CommandButton.ICON_PAUSE).setPlayerCommand(1).setDisplayName(this.context.getString(R.string.media3_controls_pause_description)).build());
            } else {
                builder.add((ImmutableList.Builder) new CommandButton.Builder(CommandButton.ICON_PLAY).setPlayerCommand(1).setDisplayName(this.context.getString(R.string.media3_controls_play_description)).build());
            }
        }
        if (containsButtonForSlot2) {
            builder.add((ImmutableList.Builder) customLayoutFromMediaButtonPreferences.get(i).copyWithSlots(ImmutableIntArray.of(3)));
            i++;
        } else if (commands.containsAny(9, 8)) {
            builder.add((ImmutableList.Builder) new CommandButton.Builder(CommandButton.ICON_NEXT).setPlayerCommand(8).setDisplayName(this.context.getString(R.string.media3_controls_seek_to_next_description)).build());
        }
        while (i < customLayoutFromMediaButtonPreferences.size()) {
            builder.add((ImmutableList.Builder) customLayoutFromMediaButtonPreferences.get(i).copyWithSlots(ImmutableIntArray.of(6)));
            i++;
        }
        return builder.build();
    }

    protected int[] addNotificationActions(MediaSession mediaSession, ImmutableList<CommandButton> immutableList, NotificationCompat.Builder builder, MediaNotification.ActionFactory actionFactory) {
        int[] iArr = new int[3];
        int[] iArr2 = new int[3];
        Arrays.fill(iArr, -1);
        Arrays.fill(iArr2, -1);
        boolean z = false;
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = immutableList.get(i);
            if (commandButton.sessionCommand != null) {
                builder.addAction(actionFactory.createCustomActionFromCustomCommandButton(mediaSession, commandButton));
            } else {
                Assertions.checkState(commandButton.playerCommand != -1);
                builder.addAction(actionFactory.createMediaAction(mediaSession, IconCompat.createWithResource(this.context, commandButton.iconResId), commandButton.displayName, commandButton.playerCommand));
            }
            int i2 = commandButton.extras.getInt(COMMAND_KEY_COMPACT_VIEW_INDEX, -1);
            if (i2 >= 0 && i2 < 3) {
                iArr[i2] = i;
                z = true;
            } else if (commandButton.slots.get(0) == 2) {
                iArr2[0] = i;
            } else if (commandButton.slots.get(0) == 1) {
                iArr2[1] = i;
            } else if (commandButton.slots.get(0) == 3) {
                iArr2[2] = i;
            }
        }
        if (!z) {
            int i3 = 0;
            for (int i4 = 0; i4 < 3; i4++) {
                int i5 = iArr2[i4];
                if (i5 != -1) {
                    iArr[i3] = i5;
                    i3++;
                }
            }
        }
        for (int i6 = 0; i6 < 3; i6++) {
            if (iArr[i6] == -1) {
                return Arrays.copyOf(iArr, i6);
            }
        }
        return iArr;
    }

    protected CharSequence getNotificationContentTitle(MediaMetadata mediaMetadata) {
        return mediaMetadata.title;
    }

    protected CharSequence getNotificationContentText(MediaMetadata mediaMetadata) {
        return mediaMetadata.artist;
    }

    private void ensureNotificationChannel() {
        if (Build.VERSION.SDK_INT < 26 || this.notificationManager.getNotificationChannel(this.channelId) != null) {
            return;
        }
        Api26.createNotificationChannel(this.notificationManager, this.channelId, this.context.getString(this.channelNameResourceId));
    }

    private static long getPlaybackStartTimeEpochMs(Player player) {
        return (!player.isPlaying() || player.isPlayingAd() || player.isCurrentMediaItemDynamic() || player.getPlaybackParameters().speed != 1.0f) ? C.TIME_UNSET : System.currentTimeMillis() - player.getContentPosition();
    }

    /* loaded from: classes.dex */
    private static class OnBitmapLoadedFutureCallback implements FutureCallback<Bitmap> {
        private final NotificationCompat.Builder builder;
        private boolean discarded;
        private final int notificationId;
        private final MediaNotification.Provider.Callback onNotificationChangedCallback;

        public OnBitmapLoadedFutureCallback(int i, NotificationCompat.Builder builder, MediaNotification.Provider.Callback callback) {
            this.notificationId = i;
            this.builder = builder;
            this.onNotificationChangedCallback = callback;
        }

        public void discardIfPending() {
            this.discarded = true;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onSuccess(Bitmap bitmap) {
            if (this.discarded) {
                return;
            }
            this.builder.setLargeIcon(bitmap);
            this.onNotificationChangedCallback.onNotificationChanged(new MediaNotification(this.notificationId, this.builder.build()));
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
            if (this.discarded) {
                return;
            }
            Log.w(DefaultMediaNotificationProvider.TAG, DefaultMediaNotificationProvider.getBitmapLoadErrorMessage(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api26 {
        private Api26() {
        }

        public static void createNotificationChannel(NotificationManager notificationManager, String str, String str2) {
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, 2);
            if (Build.VERSION.SDK_INT <= 27) {
                notificationChannel.setShowBadge(false);
            }
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /* loaded from: classes.dex */
    private static class Api31 {
        private Api31() {
        }

        public static void setForegroundServiceBehavior(NotificationCompat.Builder builder) {
            builder.setForegroundServiceBehavior(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBitmapLoadErrorMessage(Throwable th) {
        return "Failed to load bitmap: " + th.getMessage();
    }
}
