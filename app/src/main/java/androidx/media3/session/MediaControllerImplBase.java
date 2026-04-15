package androidx.media3.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.collection.ArraySet;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda10;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaSession;
import androidx.media3.session.IMediaSessionService;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.SequencedFutureManager;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public class MediaControllerImplBase implements MediaController.MediaControllerImpl {
    private static final long RELEASE_TIMEOUT_MS = 30000;
    public static final String TAG = "MCImplBase";
    private SessionToken connectedToken;
    private final Bundle connectionHints;
    private final Context context;
    protected final MediaControllerStub controllerStub;
    private long currentPositionMs;
    private final IBinder.DeathRecipient deathRecipient;
    private final Handler fallbackPlaybackInfoUpdateHandler;
    private final FlushCommandQueueHandler flushCommandQueueHandler;
    private IMediaSession iSession;
    private final MediaController instance;
    private Player.Commands intersectedPlayerCommands;
    private long lastSetPlayWhenReadyCalledTimeMs;
    private final ListenerSet<Player.Listener> listeners;
    private final ArraySet<Integer> pendingMaskingSequencedFutureNumbers;
    private PlayerInfo pendingPlayerInfo;
    private android.media.session.MediaController platformController;
    private Player.Commands playerCommandsFromPlayer;
    private boolean released;
    protected final SequencedFutureManager sequencedFutureManager;
    private SessionServiceConnection serviceConnection;
    private PendingIntent sessionActivity;
    private Bundle sessionExtras;
    private final SurfaceCallback surfaceCallback;
    private final SessionToken token;
    private Surface videoSurface;
    private SurfaceHolder videoSurfaceHolder;
    private TextureView videoTextureView;
    private PlayerInfo playerInfo = PlayerInfo.DEFAULT;
    private Size surfaceSize = Size.UNKNOWN;
    private SessionCommands sessionCommands = SessionCommands.EMPTY;
    private ImmutableList<CommandButton> customLayoutOriginal = ImmutableList.of();
    private ImmutableList<CommandButton> mediaButtonPreferencesOriginal = ImmutableList.of();
    private ImmutableList<CommandButton> resolvedMediaButtonPreferences = ImmutableList.of();
    private ImmutableList<CommandButton> resolvedCustomLayout = ImmutableList.of();
    private ImmutableMap<String, CommandButton> commandButtonsForMediaItemsMap = ImmutableMap.of();
    private Player.Commands playerCommandsFromSession = Player.Commands.EMPTY;

    /* loaded from: classes.dex */
    public interface RemoteSessionTask {
        void run(IMediaSession iMediaSession, int i) throws RemoteException;
    }

    private static int convertRepeatModeForNavigation(int i) {
        if (i == 1) {
            return 0;
        }
        return i;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaBrowserCompat getBrowserCompat() {
        return null;
    }

    public MediaControllerImplBase(Context context, MediaController mediaController, SessionToken sessionToken, Bundle bundle, Looper looper) {
        Player.Commands commands = Player.Commands.EMPTY;
        this.playerCommandsFromPlayer = commands;
        this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
        this.listeners = new ListenerSet<>(looper, Clock.DEFAULT, new ListenerSet.IterationFinishedEvent() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda95
            @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
            public final void invoke(Object obj, FlagSet flagSet) {
                MediaControllerImplBase.this.m337lambda$new$0$androidxmedia3sessionMediaControllerImplBase((Player.Listener) obj, flagSet);
            }
        });
        this.fallbackPlaybackInfoUpdateHandler = new Handler(looper);
        this.instance = mediaController;
        Assertions.checkNotNull(context, "context must not be null");
        Assertions.checkNotNull(sessionToken, "token must not be null");
        this.context = context;
        this.sequencedFutureManager = new SequencedFutureManager();
        this.controllerStub = new MediaControllerStub(this);
        this.pendingMaskingSequencedFutureNumbers = new ArraySet<>();
        this.token = sessionToken;
        this.connectionHints = bundle;
        this.deathRecipient = new IBinder.DeathRecipient() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda96
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                MediaControllerImplBase.this.m338lambda$new$1$androidxmedia3sessionMediaControllerImplBase();
            }
        };
        this.surfaceCallback = new SurfaceCallback();
        this.sessionExtras = Bundle.EMPTY;
        this.serviceConnection = sessionToken.getType() != 0 ? new SessionServiceConnection(bundle) : null;
        this.flushCommandQueueHandler = new FlushCommandQueueHandler(looper);
        this.currentPositionMs = C.TIME_UNSET;
        this.lastSetPlayWhenReadyCalledTimeMs = C.TIME_UNSET;
    }

    /* renamed from: lambda$new$0$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m337lambda$new$0$androidxmedia3sessionMediaControllerImplBase(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(getInstance(), new Player.Events(flagSet));
    }

    /* renamed from: lambda$new$1$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m338lambda$new$1$androidxmedia3sessionMediaControllerImplBase() {
        MediaController mediaControllerImplBase = getInstance();
        MediaController mediaControllerImplBase2 = getInstance();
        Objects.requireNonNull(mediaControllerImplBase2);
        mediaControllerImplBase.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(mediaControllerImplBase2));
    }

    public MediaController getInstance() {
        return this.instance;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void connect() {
        boolean requestConnectToService;
        if (this.token.getType() == 0) {
            this.serviceConnection = null;
            requestConnectToService = requestConnectToSession(this.connectionHints);
        } else {
            this.serviceConnection = new SessionServiceConnection(this.connectionHints);
            requestConnectToService = requestConnectToService();
        }
        if (requestConnectToService) {
            return;
        }
        MediaController mediaControllerImplBase = getInstance();
        MediaController mediaControllerImplBase2 = getInstance();
        Objects.requireNonNull(mediaControllerImplBase2);
        mediaControllerImplBase.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(mediaControllerImplBase2));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Bundle getConnectionHints() {
        return this.connectionHints;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addListener(Player.Listener listener) {
        this.listeners.add(listener);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeListener(Player.Listener listener) {
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void stop() {
        if (isPlayerCommandAvailable(3)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda110
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m404lambda$stop$2$androidxmedia3sessionMediaControllerImplBase(iMediaSession, i);
                }
            });
            PlayerInfo copyWithSessionPositionInfo = this.playerInfo.copyWithSessionPositionInfo(new SessionPositionInfo(this.playerInfo.sessionPositionInfo.positionInfo, this.playerInfo.sessionPositionInfo.isPlayingAd, SystemClock.elapsedRealtime(), this.playerInfo.sessionPositionInfo.durationMs, this.playerInfo.sessionPositionInfo.positionInfo.positionMs, MediaUtils.calculateBufferedPercentage(this.playerInfo.sessionPositionInfo.positionInfo.positionMs, this.playerInfo.sessionPositionInfo.durationMs), 0L, this.playerInfo.sessionPositionInfo.currentLiveOffsetMs, this.playerInfo.sessionPositionInfo.contentDurationMs, this.playerInfo.sessionPositionInfo.positionInfo.positionMs));
            this.playerInfo = copyWithSessionPositionInfo;
            if (copyWithSessionPositionInfo.playbackState != 1) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithPlaybackState(1, playerInfo.playerError);
                this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda112
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onPlaybackStateChanged(1);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$stop$2$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m404lambda$stop$2$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.stop(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void release() {
        IMediaSession iMediaSession = this.iSession;
        if (this.released) {
            return;
        }
        this.released = true;
        this.connectedToken = null;
        this.fallbackPlaybackInfoUpdateHandler.removeCallbacksAndMessages(null);
        this.flushCommandQueueHandler.release();
        this.iSession = null;
        if (iMediaSession != null) {
            int obtainNextSequenceNumber = this.sequencedFutureManager.obtainNextSequenceNumber();
            try {
                iMediaSession.asBinder().unlinkToDeath(this.deathRecipient, 0);
                iMediaSession.release(this.controllerStub, obtainNextSequenceNumber);
            } catch (RemoteException unused) {
            }
        }
        this.listeners.release();
        this.sequencedFutureManager.lazyRelease(30000L, new Runnable() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda80
            @Override // java.lang.Runnable
            public final void run() {
                MediaControllerImplBase.this.m355lambda$release$4$androidxmedia3sessionMediaControllerImplBase();
            }
        });
    }

    /* renamed from: lambda$release$4$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m355lambda$release$4$androidxmedia3sessionMediaControllerImplBase() {
        SessionServiceConnection sessionServiceConnection = this.serviceConnection;
        if (sessionServiceConnection != null) {
            this.context.unbindService(sessionServiceConnection);
            this.serviceConnection = null;
        }
        this.controllerStub.destroy();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionToken getConnectedToken() {
        return this.connectedToken;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isConnected() {
        return this.iSession != null;
    }

    public boolean isReleased() {
        return this.released;
    }

    private void dispatchRemoteSessionTaskWithPlayerCommand(RemoteSessionTask remoteSessionTask) {
        this.flushCommandQueueHandler.sendFlushCommandQueueMessage();
        dispatchRemoteSessionTask(this.iSession, remoteSessionTask, true);
    }

    public void dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(RemoteSessionTask remoteSessionTask) {
        this.flushCommandQueueHandler.sendFlushCommandQueueMessage();
        ListenableFuture<SessionResult> dispatchRemoteSessionTask = dispatchRemoteSessionTask(this.iSession, remoteSessionTask, true);
        try {
            LegacyConversions.getFutureResult(dispatchRemoteSessionTask, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        } catch (TimeoutException e2) {
            if (dispatchRemoteSessionTask instanceof SequencedFutureManager.SequencedFuture) {
                int sequenceNumber = ((SequencedFutureManager.SequencedFuture) dispatchRemoteSessionTask).getSequenceNumber();
                this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(sequenceNumber));
                this.sequencedFutureManager.setFutureResult(sequenceNumber, new SessionResult(-1));
            }
            Log.w(TAG, "Synchronous command takes too long on the session side.", e2);
        }
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskWithSessionCommand(int i, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskWithSessionCommandInternal(i, null, remoteSessionTask);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskWithSessionCommand(SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskWithSessionCommandInternal(0, sessionCommand, remoteSessionTask);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskWithSessionCommandInternal(int i, SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        IMediaSession sessionInterfaceWithSessionCommandIfAble;
        if (sessionCommand != null) {
            sessionInterfaceWithSessionCommandIfAble = getSessionInterfaceWithSessionCommandIfAble(sessionCommand);
        } else {
            sessionInterfaceWithSessionCommandIfAble = getSessionInterfaceWithSessionCommandIfAble(i);
        }
        return dispatchRemoteSessionTask(sessionInterfaceWithSessionCommandIfAble, remoteSessionTask, false);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTask(IMediaSession iMediaSession, RemoteSessionTask remoteSessionTask, boolean z) {
        if (iMediaSession != null) {
            SequencedFutureManager.SequencedFuture createSequencedFuture = this.sequencedFutureManager.createSequencedFuture(new SessionResult(1));
            int sequenceNumber = createSequencedFuture.getSequenceNumber();
            if (z) {
                if (this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
                    this.pendingPlayerInfo = this.playerInfo;
                }
                this.pendingMaskingSequencedFutureNumbers.add(Integer.valueOf(sequenceNumber));
            }
            try {
                remoteSessionTask.run(iMediaSession, sequenceNumber);
                return createSequencedFuture;
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
                this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(sequenceNumber));
                this.sequencedFutureManager.setFutureResult(sequenceNumber, new SessionResult(-100));
                return createSequencedFuture;
            }
        }
        return Futures.immediateFuture(new SessionResult(-4));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void play() {
        android.media.session.MediaController mediaController;
        if (!isPlayerCommandAvailable(1)) {
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
            return;
        }
        if (Build.VERSION.SDK_INT >= 31 && (mediaController = this.platformController) != null) {
            mediaController.getTransportControls().sendCustomAction("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST", (Bundle) null);
        }
        dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda109
            @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
            public final void run(IMediaSession iMediaSession, int i) {
                MediaControllerImplBase.this.m353lambda$play$5$androidxmedia3sessionMediaControllerImplBase(iMediaSession, i);
            }
        });
        setPlayWhenReady(true, 1);
    }

    /* renamed from: lambda$play$5$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m353lambda$play$5$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.play(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void pause() {
        if (isPlayerCommandAvailable(1)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda101
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m352lambda$pause$6$androidxmedia3sessionMediaControllerImplBase(iMediaSession, i);
                }
            });
            setPlayWhenReady(false, 1);
        }
    }

    /* renamed from: lambda$pause$6$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m352lambda$pause$6$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.pause(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void prepare() {
        if (isPlayerCommandAvailable(2)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda113
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m354lambda$prepare$7$androidxmedia3sessionMediaControllerImplBase(iMediaSession, i);
                }
            });
            if (this.playerInfo.playbackState == 1) {
                PlayerInfo playerInfo = this.playerInfo;
                updatePlayerInfo(playerInfo.copyWithPlaybackState(playerInfo.timeline.isEmpty() ? 4 : 2, null), null, null, null, null);
            }
        }
    }

    /* renamed from: lambda$prepare$7$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m354lambda$prepare$7$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.prepare(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition() {
        if (isPlayerCommandAvailable(4)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda43
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m364x4935e2a(iMediaSession, i);
                }
            });
            seekToInternal(getCurrentMediaItemIndex(), C.TIME_UNSET);
        }
    }

    /* renamed from: lambda$seekToDefaultPosition$8$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m364x4935e2a(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToDefaultPosition(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition(final int i) {
        if (isPlayerCommandAvailable(10)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda0
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m365x41cf82b(i, iMediaSession, i2);
                }
            });
            seekToInternal(i, C.TIME_UNSET);
        }
    }

    /* renamed from: lambda$seekToDefaultPosition$9$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m365x41cf82b(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.seekToDefaultPositionWithMediaItemIndex(this.controllerStub, i2, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(final long j) {
        if (isPlayerCommandAvailable(5)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda32
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m362lambda$seekTo$10$androidxmedia3sessionMediaControllerImplBase(j, iMediaSession, i);
                }
            });
            seekToInternal(getCurrentMediaItemIndex(), j);
        }
    }

    /* renamed from: lambda$seekTo$10$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m362lambda$seekTo$10$androidxmedia3sessionMediaControllerImplBase(long j, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekTo(this.controllerStub, i, j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(final int i, final long j) {
        if (isPlayerCommandAvailable(10)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda106
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m363lambda$seekTo$11$androidxmedia3sessionMediaControllerImplBase(i, j, iMediaSession, i2);
                }
            });
            seekToInternal(i, j);
        }
    }

    /* renamed from: lambda$seekTo$11$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m363lambda$seekTo$11$androidxmedia3sessionMediaControllerImplBase(int i, long j, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.seekToWithMediaItemIndex(this.controllerStub, i2, i, j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekBackIncrement() {
        return this.playerInfo.seekBackIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekBack() {
        if (isPlayerCommandAvailable(11)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda97
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m360xa77bbc45(iMediaSession, i);
                }
            });
            seekToInternalByOffset(-getSeekBackIncrement());
        }
    }

    /* renamed from: lambda$seekBack$12$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m360xa77bbc45(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekBack(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekForwardIncrement() {
        return this.playerInfo.seekForwardIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekForward() {
        if (isPlayerCommandAvailable(12)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda34
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m361xc4e101b2(iMediaSession, i);
                }
            });
            seekToInternalByOffset(getSeekForwardIncrement());
        }
    }

    /* renamed from: lambda$seekForward$13$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m361xc4e101b2(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekForward(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlayWhenReady(final boolean z) {
        if (isPlayerCommandAvailable(1)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda6
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m389x3c78c7d3(z, iMediaSession, i);
                }
            });
            setPlayWhenReady(z, 1);
        } else if (z) {
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
        }
    }

    /* renamed from: lambda$setPlayWhenReady$14$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m389x3c78c7d3(boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlayWhenReady(this.controllerStub, i, z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getPlayWhenReady() {
        return this.playerInfo.playWhenReady;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackSuppressionReason() {
        return this.playerInfo.playbackSuppressionReason;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackException getPlayerError() {
        return this.playerInfo.playerError;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackState() {
        return this.playerInfo.playbackState;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlaying() {
        return this.playerInfo.isPlaying;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isLoading() {
        return this.playerInfo.isLoading;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getDuration() {
        return this.playerInfo.sessionPositionInfo.durationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentPosition() {
        long updatedCurrentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.currentPositionMs = updatedCurrentPositionMs;
        return updatedCurrentPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getBufferedPosition() {
        return this.playerInfo.sessionPositionInfo.bufferedPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getBufferedPercentage() {
        return this.playerInfo.sessionPositionInfo.bufferedPercentage;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getTotalBufferedDuration() {
        return this.playerInfo.sessionPositionInfo.totalBufferedDurationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentLiveOffset() {
        return this.playerInfo.sessionPositionInfo.currentLiveOffsetMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentDuration() {
        return this.playerInfo.sessionPositionInfo.contentDurationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentPosition() {
        if (!this.playerInfo.sessionPositionInfo.isPlayingAd) {
            return getCurrentPosition();
        }
        return this.playerInfo.sessionPositionInfo.positionInfo.contentPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentBufferedPosition() {
        return this.playerInfo.sessionPositionInfo.contentBufferedPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlayingAd() {
        return this.playerInfo.sessionPositionInfo.isPlayingAd;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdGroupIndex() {
        return this.playerInfo.sessionPositionInfo.positionInfo.adGroupIndex;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdIndexInAdGroup() {
        return this.playerInfo.sessionPositionInfo.positionInfo.adIndexInAdGroup;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackParameters(final PlaybackParameters playbackParameters) {
        if (isPlayerCommandAvailable(13)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda30
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m390x7ad849fa(playbackParameters, iMediaSession, i);
                }
            });
            if (this.playerInfo.playbackParameters.equals(playbackParameters)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithPlaybackParameters(playbackParameters);
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda31
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackParametersChanged(PlaybackParameters.this);
                }
            });
            this.listeners.flushEvents();
        }
    }

    /* renamed from: lambda$setPlaybackParameters$15$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m390x7ad849fa(PlaybackParameters playbackParameters, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlaybackParameters(this.controllerStub, i, playbackParameters.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackParameters getPlaybackParameters() {
        return this.playerInfo.playbackParameters;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackSpeed(final float f) {
        if (isPlayerCommandAvailable(13)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda77
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m391x786a6f3f(f, iMediaSession, i);
                }
            });
            if (this.playerInfo.playbackParameters.speed != f) {
                final PlaybackParameters withSpeed = this.playerInfo.playbackParameters.withSpeed(f);
                this.playerInfo = this.playerInfo.copyWithPlaybackParameters(withSpeed);
                this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda79
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onPlaybackParametersChanged(PlaybackParameters.this);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$setPlaybackSpeed$17$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m391x786a6f3f(float f, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlaybackSpeed(this.controllerStub, i, f);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public AudioAttributes getAudioAttributes() {
        return this.playerInfo.audioAttributes;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> setRating(final String str, final Rating rating) {
        return dispatchRemoteSessionTaskWithSessionCommand(SessionCommand.COMMAND_CODE_SESSION_SET_RATING, new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda39
            @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
            public final void run(IMediaSession iMediaSession, int i) {
                MediaControllerImplBase.this.m393x577a1446(str, rating, iMediaSession, i);
            }
        });
    }

    /* renamed from: lambda$setRating$19$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m393x577a1446(String str, Rating rating, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setRatingWithMediaId(this.controllerStub, i, str, rating.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> setRating(final Rating rating) {
        return dispatchRemoteSessionTaskWithSessionCommand(SessionCommand.COMMAND_CODE_SESSION_SET_RATING, new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda19
            @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
            public final void run(IMediaSession iMediaSession, int i) {
                MediaControllerImplBase.this.m394x4d4d505c(rating, iMediaSession, i);
            }
        });
    }

    /* renamed from: lambda$setRating$20$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m394x4d4d505c(Rating rating, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setRating(this.controllerStub, i, rating.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> sendCustomCommand(final SessionCommand sessionCommand, final Bundle bundle) {
        return dispatchRemoteSessionTaskWithSessionCommand(sessionCommand, new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda42
            @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
            public final void run(IMediaSession iMediaSession, int i) {
                MediaControllerImplBase.this.m371xbdee5b2a(sessionCommand, bundle, iMediaSession, i);
            }
        });
    }

    /* renamed from: lambda$sendCustomCommand$21$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m371xbdee5b2a(SessionCommand sessionCommand, Bundle bundle, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.onCustomCommand(this.controllerStub, i, sessionCommand.toBundle(), bundle);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PendingIntent getSessionActivity() {
        return this.sessionActivity;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.resolvedMediaButtonPreferences;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getCustomLayout() {
        return this.resolvedCustomLayout;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        ImmutableList<String> immutableList = mediaItem.mediaMetadata.supportedCommands;
        SessionCommands availableSessionCommands = getAvailableSessionCommands();
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = this.commandButtonsForMediaItemsMap.get(immutableList.get(i));
            if (commandButton != null && commandButton.sessionCommand != null && availableSessionCommands.contains(commandButton.sessionCommand)) {
                builder.add((ImmutableList.Builder) commandButton);
            }
        }
        return builder.build();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Bundle getSessionExtras() {
        return this.sessionExtras;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Timeline getCurrentTimeline() {
        return this.playerInfo.timeline;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(final MediaItem mediaItem) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda38
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m383x9063a1ce(mediaItem, iMediaSession, i);
                }
            });
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, C.TIME_UNSET, true);
        }
    }

    /* renamed from: lambda$setMediaItem$22$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m383x9063a1ce(MediaItem mediaItem, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItem(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(final MediaItem mediaItem, final long j) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda89
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m384x8fed3bcf(mediaItem, j, iMediaSession, i);
                }
            });
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, j, false);
        }
    }

    /* renamed from: lambda$setMediaItem$23$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m384x8fed3bcf(MediaItem mediaItem, long j, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItemWithStartPosition(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration(), j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(final MediaItem mediaItem, final boolean z) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda10
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m385x8f76d5d0(mediaItem, z, iMediaSession, i);
                }
            });
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, C.TIME_UNSET, z);
        }
    }

    /* renamed from: lambda$setMediaItem$24$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m385x8f76d5d0(MediaItem mediaItem, boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItemWithResetPosition(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration(), z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(final List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda35
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m386x501359e2(list, iMediaSession, i);
                }
            });
            setMediaItemsInternal(list, -1, C.TIME_UNSET, true);
        }
    }

    /* renamed from: lambda$setMediaItems$25$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m386x501359e2(List list, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItems(this.controllerStub, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda122())));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(final List<MediaItem> list, final boolean z) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda90
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m387x4f9cf3e3(list, z, iMediaSession, i);
                }
            });
            setMediaItemsInternal(list, -1, C.TIME_UNSET, z);
        }
    }

    /* renamed from: lambda$setMediaItems$26$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m387x4f9cf3e3(List list, boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItemsWithResetPosition(this.controllerStub, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda122())), z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(final List<MediaItem> list, final int i, final long j) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda22
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m388x4f268de4(list, i, j, iMediaSession, i2);
                }
            });
            setMediaItemsInternal(list, i, j, false);
        }
    }

    /* renamed from: lambda$setMediaItems$27$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m388x4f268de4(List list, int i, long j, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setMediaItemsWithStartIndex(this.controllerStub, i2, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda122())), i, j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaylistMetadata(final MediaMetadata mediaMetadata) {
        if (isPlayerCommandAvailable(19)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda36
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m392x8d147ea0(mediaMetadata, iMediaSession, i);
                }
            });
            if (this.playerInfo.playlistMetadata.equals(mediaMetadata)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithPlaylistMetadata(mediaMetadata);
            this.listeners.queueEvent(15, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda37
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaylistMetadataChanged(MediaMetadata.this);
                }
            });
            this.listeners.flushEvents();
        }
    }

    /* renamed from: lambda$setPlaylistMetadata$28$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m392x8d147ea0(MediaMetadata mediaMetadata, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlaylistMetadata(this.controllerStub, i, mediaMetadata.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getPlaylistMetadata() {
        return this.playerInfo.playlistMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(final MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda67
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m321x274a4faa(mediaItem, iMediaSession, i);
                }
            });
            addMediaItemsInternal(getCurrentTimeline().getWindowCount(), Collections.singletonList(mediaItem));
        }
    }

    /* renamed from: lambda$addMediaItem$30$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m321x274a4faa(MediaItem mediaItem, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.addMediaItem(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(final int i, final MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda11
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m322x26d3e9ab(i, mediaItem, iMediaSession, i2);
                }
            });
            addMediaItemsInternal(i, Collections.singletonList(mediaItem));
        }
    }

    /* renamed from: lambda$addMediaItem$31$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m322x26d3e9ab(int i, MediaItem mediaItem, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.addMediaItemWithIndex(this.controllerStub, i2, i, mediaItem.toBundleIncludeLocalConfiguration());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(final List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda26
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m323x28d76e1f(list, iMediaSession, i);
                }
            });
            addMediaItemsInternal(getCurrentTimeline().getWindowCount(), list);
        }
    }

    /* renamed from: lambda$addMediaItems$32$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m323x28d76e1f(List list, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.addMediaItems(this.controllerStub, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda122())));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(final int i, final List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda99
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m324x28610820(i, list, iMediaSession, i2);
                }
            });
            addMediaItemsInternal(i, list);
        }
    }

    /* renamed from: lambda$addMediaItems$33$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m324x28610820(int i, List list, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.addMediaItemsWithIndex(this.controllerStub, i2, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda122())));
    }

    private void addMediaItemsInternal(int i, List<MediaItem> list) {
        if (list.isEmpty()) {
            return;
        }
        if (this.playerInfo.timeline.isEmpty()) {
            setMediaItemsInternal(list, -1, C.TIME_UNSET, false);
        } else {
            updatePlayerInfo(maskPlayerInfoForAddedItems(this.playerInfo, Math.min(i, this.playerInfo.timeline.getWindowCount()), list, getCurrentPosition(), getContentPosition()), 0, null, null, this.playerInfo.timeline.isEmpty() ? 3 : null);
        }
    }

    private static PlayerInfo maskPlayerInfoForAddedItems(PlayerInfo playerInfo, int i, List<MediaItem> list, long j, long j2) {
        int i2;
        int i3;
        int i4;
        Timeline timeline = playerInfo.timeline;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i5 = 0;
        for (int i6 = 0; i6 < timeline.getWindowCount(); i6++) {
            arrayList.add(timeline.getWindow(i6, new Timeline.Window()));
        }
        for (int i7 = 0; i7 < list.size(); i7++) {
            arrayList.add(i7 + i, createNewWindow(list.get(i7)));
        }
        rebuildPeriods(timeline, arrayList, arrayList2);
        Timeline createMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
        if (playerInfo.timeline.isEmpty()) {
            i4 = 0;
        } else {
            if (playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex >= i) {
                i2 = playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex + list.size();
            } else {
                i2 = playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
            }
            i5 = i2;
            if (playerInfo.sessionPositionInfo.positionInfo.periodIndex >= i) {
                i3 = playerInfo.sessionPositionInfo.positionInfo.periodIndex + list.size();
            } else {
                i3 = playerInfo.sessionPositionInfo.positionInfo.periodIndex;
            }
            i4 = i3;
        }
        return maskTimelineAndPositionInfo(playerInfo, createMaskingTimeline, i5, i4, j, j2, 5);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItem(final int i) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda87
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m356xcb8143eb(i, iMediaSession, i2);
                }
            });
            removeMediaItemsInternal(i, i + 1);
        }
    }

    /* renamed from: lambda$removeMediaItem$34$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m356xcb8143eb(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.removeMediaItem(this.controllerStub, i2, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItems(final int i, final int i2) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0 && i2 >= i);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda44
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i3) {
                    MediaControllerImplBase.this.m357x43753785(i, i2, iMediaSession, i3);
                }
            });
            removeMediaItemsInternal(i, i2);
        }
    }

    /* renamed from: lambda$removeMediaItems$35$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m357x43753785(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.removeMediaItems(this.controllerStub, i3, i, i2);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearMediaItems() {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda91
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m325x7e2b5cb7(iMediaSession, i);
                }
            });
            removeMediaItemsInternal(0, Integer.MAX_VALUE);
        }
    }

    /* renamed from: lambda$clearMediaItems$36$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m325x7e2b5cb7(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.clearMediaItems(this.controllerStub, i);
    }

    private void removeMediaItemsInternal(int i, int i2) {
        int windowCount = this.playerInfo.timeline.getWindowCount();
        int min = Math.min(i2, windowCount);
        if (i >= windowCount || i == min || windowCount == 0) {
            return;
        }
        boolean z = getCurrentMediaItemIndex() >= i && getCurrentMediaItemIndex() < min;
        updatePlayerInfo(maskPlayerInfoForRemovedItems(this.playerInfo, i, min, false, getCurrentPosition(), getContentPosition()), 0, null, z ? 4 : null, this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex >= i && this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex < min ? 3 : null);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0106  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.session.PlayerInfo maskPlayerInfoForRemovedItems(androidx.media3.session.PlayerInfo r34, int r35, int r36, boolean r37, long r38, long r40) {
        /*
            Method dump skipped, instructions count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaControllerImplBase.maskPlayerInfoForRemovedItems(androidx.media3.session.PlayerInfo, int, int, boolean, long, long):androidx.media3.session.PlayerInfo");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItem(final int i, final int i2) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0 && i2 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda7
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i3) {
                    MediaControllerImplBase.this.m335x94a376fb(i, i2, iMediaSession, i3);
                }
            });
            moveMediaItemsInternal(i, i + 1, i2);
        }
    }

    /* renamed from: lambda$moveMediaItem$37$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m335x94a376fb(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.moveMediaItem(this.controllerStub, i3, i, i2);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItems(final int i, final int i2, final int i3) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0 && i <= i2 && i3 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda83
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i4) {
                    MediaControllerImplBase.this.m336xc839421b(i, i2, i3, iMediaSession, i4);
                }
            });
            moveMediaItemsInternal(i, i2, i3);
        }
    }

    /* renamed from: lambda$moveMediaItems$38$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m336xc839421b(int i, int i2, int i3, IMediaSession iMediaSession, int i4) throws RemoteException {
        iMediaSession.moveMediaItems(this.controllerStub, i4, i, i2, i3);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItem(final int i, final MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda25
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m358x2a7593c6(i, mediaItem, iMediaSession, i2);
                }
            });
            replaceMediaItemsInternal(i, i + 1, ImmutableList.of(mediaItem));
        }
    }

    /* renamed from: lambda$replaceMediaItem$39$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m358x2a7593c6(int i, MediaItem mediaItem, IMediaSession iMediaSession, int i2) throws RemoteException {
        if (((SessionToken) Assertions.checkNotNull(this.connectedToken)).getInterfaceVersion() >= 2) {
            iMediaSession.replaceMediaItem(this.controllerStub, i2, i, mediaItem.toBundleIncludeLocalConfiguration());
        } else {
            iMediaSession.addMediaItemWithIndex(this.controllerStub, i2, i + 1, mediaItem.toBundleIncludeLocalConfiguration());
            iMediaSession.removeMediaItem(this.controllerStub, i2, i);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItems(final int i, final int i2, final List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0 && i <= i2);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda111
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i3) {
                    MediaControllerImplBase.this.m359xfeb44889(list, i, i2, iMediaSession, i3);
                }
            });
            replaceMediaItemsInternal(i, i2, list);
        }
    }

    /* renamed from: lambda$replaceMediaItems$40$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m359xfeb44889(List list, int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        BundleListRetriever bundleListRetriever = new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda122()));
        if (((SessionToken) Assertions.checkNotNull(this.connectedToken)).getInterfaceVersion() >= 2) {
            iMediaSession.replaceMediaItems(this.controllerStub, i3, i, i2, bundleListRetriever);
        } else {
            iMediaSession.addMediaItemsWithIndex(this.controllerStub, i3, i2, bundleListRetriever);
            iMediaSession.removeMediaItems(this.controllerStub, i3, i, i2);
        }
    }

    private void replaceMediaItemsInternal(int i, int i2, List<MediaItem> list) {
        int windowCount = this.playerInfo.timeline.getWindowCount();
        if (i > windowCount) {
            return;
        }
        if (this.playerInfo.timeline.isEmpty()) {
            setMediaItemsInternal(list, -1, C.TIME_UNSET, false);
            return;
        }
        int min = Math.min(i2, windowCount);
        PlayerInfo maskPlayerInfoForRemovedItems = maskPlayerInfoForRemovedItems(maskPlayerInfoForAddedItems(this.playerInfo, min, list, getCurrentPosition(), getContentPosition()), i, min, true, getCurrentPosition(), getContentPosition());
        boolean z = this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex >= i && this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex < min;
        updatePlayerInfo(maskPlayerInfoForRemovedItems, 0, null, z ? 4 : null, z ? 3 : null);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentPeriodIndex() {
        return this.playerInfo.sessionPositionInfo.positionInfo.periodIndex;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentMediaItemIndex() {
        return getCurrentMediaItemIndexInternal(this.playerInfo);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPreviousMediaItemIndex() {
        if (this.playerInfo.timeline.isEmpty()) {
            return -1;
        }
        return this.playerInfo.timeline.getPreviousWindowIndex(getCurrentMediaItemIndex(), convertRepeatModeForNavigation(this.playerInfo.repeatMode), this.playerInfo.shuffleModeEnabled);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getNextMediaItemIndex() {
        if (this.playerInfo.timeline.isEmpty()) {
            return -1;
        }
        return this.playerInfo.timeline.getNextWindowIndex(getCurrentMediaItemIndex(), convertRepeatModeForNavigation(this.playerInfo.repeatMode), this.playerInfo.shuffleModeEnabled);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasPreviousMediaItem() {
        return getPreviousMediaItemIndex() != -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasNextMediaItem() {
        return getNextMediaItemIndex() != -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPreviousMediaItem() {
        if (isPlayerCommandAvailable(6)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda78
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m369xb9ec60ad(iMediaSession, i);
                }
            });
            if (getPreviousMediaItemIndex() != -1) {
                seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    /* renamed from: lambda$seekToPreviousMediaItem$41$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m369xb9ec60ad(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToPreviousMediaItem(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNextMediaItem() {
        if (isPlayerCommandAvailable(8)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda12
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m367x391820aa(iMediaSession, i);
                }
            });
            if (getNextMediaItemIndex() != -1) {
                seekToInternal(getNextMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    /* renamed from: lambda$seekToNextMediaItem$42$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m367x391820aa(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToNextMediaItem(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPrevious() {
        if (isPlayerCommandAvailable(7)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda88
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m368x5c7914d8(iMediaSession, i);
                }
            });
            Timeline currentTimeline = getCurrentTimeline();
            if (currentTimeline.isEmpty() || isPlayingAd()) {
                return;
            }
            boolean hasPreviousMediaItem = hasPreviousMediaItem();
            Timeline.Window window = currentTimeline.getWindow(getCurrentMediaItemIndex(), new Timeline.Window());
            if (window.isDynamic && window.isLive()) {
                if (hasPreviousMediaItem) {
                    seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
                }
            } else if (hasPreviousMediaItem && getCurrentPosition() <= getMaxSeekToPreviousPosition()) {
                seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
            } else {
                seekToInternal(getCurrentMediaItemIndex(), 0L);
            }
        }
    }

    /* renamed from: lambda$seekToPrevious$43$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m368x5c7914d8(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToPrevious(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getMaxSeekToPreviousPosition() {
        return this.playerInfo.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNext() {
        if (isPlayerCommandAvailable(9)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda100
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m366x194a9d5d(iMediaSession, i);
                }
            });
            Timeline currentTimeline = getCurrentTimeline();
            if (currentTimeline.isEmpty() || isPlayingAd()) {
                return;
            }
            if (hasNextMediaItem()) {
                seekToInternal(getNextMediaItemIndex(), C.TIME_UNSET);
                return;
            }
            Timeline.Window window = currentTimeline.getWindow(getCurrentMediaItemIndex(), new Timeline.Window());
            if (window.isDynamic && window.isLive()) {
                seekToInternal(getCurrentMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    /* renamed from: lambda$seekToNext$44$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m366x194a9d5d(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToNext(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getRepeatMode() {
        return this.playerInfo.repeatMode;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setRepeatMode(final int i) {
        if (isPlayerCommandAvailable(15)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda8
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m395xe0f5373e(i, iMediaSession, i2);
                }
            });
            if (this.playerInfo.repeatMode != i) {
                this.playerInfo = this.playerInfo.copyWithRepeatMode(i);
                this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda9
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onRepeatModeChanged(i);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$setRepeatMode$45$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m395xe0f5373e(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setRepeatMode(this.controllerStub, i2, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getShuffleModeEnabled() {
        return this.playerInfo.shuffleModeEnabled;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setShuffleModeEnabled(final boolean z) {
        if (isPlayerCommandAvailable(14)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda84
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m396x9eb3c759(z, iMediaSession, i);
                }
            });
            if (this.playerInfo.shuffleModeEnabled != z) {
                this.playerInfo = this.playerInfo.copyWithShuffleModeEnabled(z);
                this.listeners.queueEvent(9, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda85
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onShuffleModeEnabledChanged(z);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$setShuffleModeEnabled$47$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m396x9eb3c759(boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setShuffleModeEnabled(this.controllerStub, i, z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public CueGroup getCurrentCues() {
        return this.playerInfo.cueGroup;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public float getVolume() {
        return this.playerInfo.volume;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVolume(final float f) {
        if (isPlayerCommandAvailable(24)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda116
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m403xd97b1206(f, iMediaSession, i);
                }
            });
            if (this.playerInfo.volume != f) {
                this.playerInfo = this.playerInfo.copyWithVolume(f);
                this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda117
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onVolumeChanged(f);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$setVolume$49$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m403xd97b1206(float f, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVolume(this.controllerStub, i, f);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public DeviceInfo getDeviceInfo() {
        return this.playerInfo.deviceInfo;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getDeviceVolume() {
        return this.playerInfo.deviceVolume;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isDeviceMuted() {
        return this.playerInfo.deviceMuted;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceVolume(final int i) {
        if (isPlayerCommandAvailable(25)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda13
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m377x4638e627(i, iMediaSession, i2);
                }
            });
            DeviceInfo deviceInfo = getDeviceInfo();
            if (this.playerInfo.deviceVolume == i || deviceInfo.minVolume > i) {
                return;
            }
            if (deviceInfo.maxVolume == 0 || i <= deviceInfo.maxVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda14
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m378x45c28028(i, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$setDeviceVolume$51$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m377x4638e627(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setDeviceVolume(this.controllerStub, i2, i);
    }

    /* renamed from: lambda$setDeviceVolume$52$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m378x45c28028(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceVolume(final int i, final int i2) {
        if (isPlayerCommandAvailable(33)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda75
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i3) {
                    MediaControllerImplBase.this.m379x454c1a29(i, i2, iMediaSession, i3);
                }
            });
            DeviceInfo deviceInfo = getDeviceInfo();
            if (this.playerInfo.deviceVolume == i || deviceInfo.minVolume > i) {
                return;
            }
            if (deviceInfo.maxVolume == 0 || i <= deviceInfo.maxVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda76
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m380x44d5b42a(i, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$setDeviceVolume$53$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m379x454c1a29(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.setDeviceVolumeWithFlags(this.controllerStub, i3, i, i2);
    }

    /* renamed from: lambda$setDeviceVolume$54$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m380x44d5b42a(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void increaseDeviceVolume() {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda102
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m331x83ebc271(iMediaSession, i);
                }
            });
            final int i = this.playerInfo.deviceVolume + 1;
            int i2 = getDeviceInfo().maxVolume;
            if (i2 == 0 || i <= i2) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda103
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m332x83755c72(i, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$increaseDeviceVolume$55$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m331x83ebc271(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.increaseDeviceVolume(this.controllerStub, i);
    }

    /* renamed from: lambda$increaseDeviceVolume$56$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m332x83755c72(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void increaseDeviceVolume(final int i) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda107
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m333x82fef673(i, iMediaSession, i2);
                }
            });
            final int i2 = this.playerInfo.deviceVolume + 1;
            int i3 = getDeviceInfo().maxVolume;
            if (i3 == 0 || i2 <= i3) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i2, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda108
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m334x82889074(i2, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$increaseDeviceVolume$57$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m333x82fef673(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.increaseDeviceVolumeWithFlags(this.controllerStub, i2, i);
    }

    /* renamed from: lambda$increaseDeviceVolume$58$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m334x82889074(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void decreaseDeviceVolume() {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda15
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m327x12544499(iMediaSession, i);
                }
            });
            final int i = this.playerInfo.deviceVolume - 1;
            if (i >= getDeviceInfo().minVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda16
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m328x82780af(i, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$decreaseDeviceVolume$59$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m327x12544499(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.decreaseDeviceVolume(this.controllerStub, i);
    }

    /* renamed from: lambda$decreaseDeviceVolume$60$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m328x82780af(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void decreaseDeviceVolume(final int i) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda28
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m329x7b11ab0(i, iMediaSession, i2);
                }
            });
            final int i2 = this.playerInfo.deviceVolume - 1;
            if (i2 >= getDeviceInfo().minVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i2, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda29
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m330x73ab4b1(i2, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$decreaseDeviceVolume$61$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m329x7b11ab0(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.decreaseDeviceVolumeWithFlags(this.controllerStub, i2, i);
    }

    /* renamed from: lambda$decreaseDeviceVolume$62$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m330x73ab4b1(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceMuted(final boolean z) {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda114
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m373x98e70aad(z, iMediaSession, i);
                }
            });
            if (this.playerInfo.deviceMuted != z) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(playerInfo.deviceVolume, z);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda115
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m374x9870a4ae(z, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$setDeviceMuted$63$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m373x98e70aad(boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setDeviceMuted(this.controllerStub, i, z);
    }

    /* renamed from: lambda$setDeviceMuted$64$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m374x9870a4ae(boolean z, Player.Listener listener) {
        listener.onDeviceVolumeChanged(this.playerInfo.deviceVolume, z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceMuted(final boolean z, final int i) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda81
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) {
                    MediaControllerImplBase.this.m375x97fa3eaf(z, i, iMediaSession, i2);
                }
            });
            if (this.playerInfo.deviceMuted != z) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(playerInfo.deviceVolume, z);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda82
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m376x9783d8b0(z, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$setDeviceMuted$65$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m375x97fa3eaf(boolean z, int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setDeviceMutedWithFlags(this.controllerStub, i2, z, i);
    }

    /* renamed from: lambda$setDeviceMuted$66$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m376x9783d8b0(boolean z, Player.Listener listener) {
        listener.onDeviceVolumeChanged(this.playerInfo.deviceVolume, z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setAudioAttributes(final AudioAttributes audioAttributes, final boolean z) {
        if (isPlayerCommandAvailable(35)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda23
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m372xd2c92c19(audioAttributes, z, iMediaSession, i);
                }
            });
            if (this.playerInfo.audioAttributes.equals(audioAttributes)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithAudioAttributes(audioAttributes);
            this.listeners.queueEvent(20, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda24
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAudioAttributesChanged(AudioAttributes.this);
                }
            });
            this.listeners.flushEvents();
        }
    }

    /* renamed from: lambda$setAudioAttributes$67$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m372xd2c92c19(AudioAttributes audioAttributes, boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setAudioAttributes(this.controllerStub, i, audioAttributes.toBundle(), z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public VideoSize getVideoSize() {
        return this.playerInfo.videoSize;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Size getSurfaceSize() {
        return this.surfaceSize;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface() {
        if (isPlayerCommandAvailable(27)) {
            clearSurfacesAndCallbacks();
            dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda33
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m326xf1aa0fe1(iMediaSession, i);
                }
            });
            maybeNotifySurfaceSizeChanged(0, 0);
        }
    }

    /* renamed from: lambda$clearVideoSurface$69$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m326xf1aa0fe1(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, null);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface(Surface surface) {
        if (isPlayerCommandAvailable(27) && surface != null && this.videoSurface == surface) {
            clearVideoSurface();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurface(final Surface surface) {
        if (isPlayerCommandAvailable(27)) {
            clearSurfacesAndCallbacks();
            this.videoSurface = surface;
            dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda27
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m398x50be6282(surface, iMediaSession, i);
                }
            });
            int i = surface == null ? 0 : -1;
            maybeNotifySurfaceSizeChanged(i, i);
        }
    }

    /* renamed from: lambda$setVideoSurface$70$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m398x50be6282(Surface surface, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, surface);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (isPlayerCommandAvailable(27)) {
            if (surfaceHolder == null) {
                clearVideoSurface();
                return;
            }
            if (this.videoSurfaceHolder == surfaceHolder) {
                return;
            }
            clearSurfacesAndCallbacks();
            this.videoSurfaceHolder = surfaceHolder;
            surfaceHolder.addCallback(this.surfaceCallback);
            final Surface surface = surfaceHolder.getSurface();
            if (surface != null && surface.isValid()) {
                this.videoSurface = surface;
                dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda40
                    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                    public final void run(IMediaSession iMediaSession, int i) {
                        MediaControllerImplBase.this.m399x96618c77(surface, iMediaSession, i);
                    }
                });
                Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
                maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
                return;
            }
            this.videoSurface = null;
            dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda41
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m400x95eb2678(iMediaSession, i);
                }
            });
            maybeNotifySurfaceSizeChanged(0, 0);
        }
    }

    /* renamed from: lambda$setVideoSurfaceHolder$71$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m399x96618c77(Surface surface, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, surface);
    }

    /* renamed from: lambda$setVideoSurfaceHolder$72$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m400x95eb2678(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, null);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (isPlayerCommandAvailable(27) && surfaceHolder != null && this.videoSurfaceHolder == surfaceHolder) {
            clearVideoSurface();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceView(SurfaceView surfaceView) {
        if (isPlayerCommandAvailable(27)) {
            setVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        if (isPlayerCommandAvailable(27)) {
            clearVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoTextureView(TextureView textureView) {
        if (isPlayerCommandAvailable(27)) {
            if (textureView == null) {
                clearVideoSurface();
                return;
            }
            if (this.videoTextureView == textureView) {
                return;
            }
            clearSurfacesAndCallbacks();
            this.videoTextureView = textureView;
            textureView.setSurfaceTextureListener(this.surfaceCallback);
            SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
            if (surfaceTexture == null) {
                dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda20
                    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                    public final void run(IMediaSession iMediaSession, int i) {
                        MediaControllerImplBase.this.m401x1d29f4f2(iMediaSession, i);
                    }
                });
                maybeNotifySurfaceSizeChanged(0, 0);
            } else {
                this.videoSurface = new Surface(surfaceTexture);
                dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda21
                    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                    public final void run(IMediaSession iMediaSession, int i) {
                        MediaControllerImplBase.this.m402x1cb38ef3(iMediaSession, i);
                    }
                });
                maybeNotifySurfaceSizeChanged(textureView.getWidth(), textureView.getHeight());
            }
        }
    }

    /* renamed from: lambda$setVideoTextureView$73$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m401x1d29f4f2(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, null);
    }

    /* renamed from: lambda$setVideoTextureView$74$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m402x1cb38ef3(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, this.videoSurface);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoTextureView(TextureView textureView) {
        if (isPlayerCommandAvailable(27) && textureView != null && this.videoTextureView == textureView) {
            clearVideoSurface();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getMediaMetadata() {
        return this.playerInfo.mediaMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Player.Commands getAvailableCommands() {
        return this.intersectedPlayerCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Tracks getCurrentTracks() {
        return this.playerInfo.currentTracks;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public TrackSelectionParameters getTrackSelectionParameters() {
        return this.playerInfo.trackSelectionParameters;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setTrackSelectionParameters(final TrackSelectionParameters trackSelectionParameters) {
        if (isPlayerCommandAvailable(29)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda17
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.this.m397xd4d571ce(trackSelectionParameters, iMediaSession, i);
                }
            });
            if (trackSelectionParameters != this.playerInfo.trackSelectionParameters) {
                this.playerInfo = this.playerInfo.copyWithTrackSelectionParameters(trackSelectionParameters);
                this.listeners.queueEvent(19, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda18
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onTrackSelectionParametersChanged(TrackSelectionParameters.this);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* renamed from: lambda$setTrackSelectionParameters$75$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m397xd4d571ce(TrackSelectionParameters trackSelectionParameters, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setTrackSelectionParameters(this.controllerStub, i, trackSelectionParameters.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionCommands getAvailableSessionCommands() {
        return this.sessionCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Context getContext() {
        return this.context;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public IMediaController getBinder() {
        return this.controllerStub;
    }

    private static Timeline createMaskingTimeline(List<Timeline.Window> list, List<Timeline.Period> list2) {
        return new Timeline.RemotableTimeline(new ImmutableList.Builder().addAll((Iterable) list).build(), new ImmutableList.Builder().addAll((Iterable) list2).build(), MediaUtils.generateUnshuffledIndices(list.size()));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r26v0 ??, still in use, count: 1, list:
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo) from 0x0135: CONSTRUCTOR (r25v0 ?? I:androidx.media3.session.SessionPositionInfo) = 
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo)
          (r27v0 ?? I:boolean)
          (r28v0 ?? I:long)
          (r30v0 ?? I:long)
          (r32v0 ?? I:long)
          (r34v0 ?? I:int)
          (r35v0 ?? I:long)
          (r37v0 ?? I:long)
          (r39v0 ?? I:long)
          (r41v0 ?? I:long)
         A[MD:(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void (m)] (LINE:2141) call: androidx.media3.session.SessionPositionInfo.<init>(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void type: CONSTRUCTOR
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:88)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:87)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:72)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:54)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:34)
        */
    private void setMediaItemsInternal(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r26v0 ??, still in use, count: 1, list:
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo) from 0x0135: CONSTRUCTOR (r25v0 ?? I:androidx.media3.session.SessionPositionInfo) = 
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo)
          (r27v0 ?? I:boolean)
          (r28v0 ?? I:long)
          (r30v0 ?? I:long)
          (r32v0 ?? I:long)
          (r34v0 ?? I:int)
          (r35v0 ?? I:long)
          (r37v0 ?? I:long)
          (r39v0 ?? I:long)
          (r41v0 ?? I:long)
         A[MD:(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void (m)] (LINE:2141) call: androidx.media3.session.SessionPositionInfo.<init>(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void type: CONSTRUCTOR
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:88)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:87)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:72)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:54)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r44v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:237)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:223)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:168)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:401)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    private void moveMediaItemsInternal(int i, int i2, int i3) {
        int i4;
        int i5;
        Timeline timeline = this.playerInfo.timeline;
        int windowCount = this.playerInfo.timeline.getWindowCount();
        int min = Math.min(i2, windowCount);
        int i6 = min - i;
        int min2 = Math.min(i3, windowCount - i6);
        if (i >= windowCount || i == min || i == min2) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i7 = 0; i7 < windowCount; i7++) {
            arrayList.add(timeline.getWindow(i7, new Timeline.Window()));
        }
        Util.moveItems(arrayList, i, min, min2);
        rebuildPeriods(timeline, arrayList, arrayList2);
        Timeline createMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
        if (createMaskingTimeline.isEmpty()) {
            return;
        }
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        if (currentMediaItemIndex >= i && currentMediaItemIndex < min) {
            i5 = (currentMediaItemIndex - i) + min2;
        } else if (min <= currentMediaItemIndex && min2 > currentMediaItemIndex) {
            i5 = currentMediaItemIndex - i6;
        } else {
            if (min <= currentMediaItemIndex || min2 > currentMediaItemIndex) {
                i4 = currentMediaItemIndex;
                Timeline.Window window = new Timeline.Window();
                updatePlayerInfo(maskTimelineAndPositionInfo(this.playerInfo, createMaskingTimeline, i4, createMaskingTimeline.getWindow(i4, window).firstPeriodIndex + (this.playerInfo.sessionPositionInfo.positionInfo.periodIndex - timeline.getWindow(currentMediaItemIndex, window).firstPeriodIndex), getCurrentPosition(), getContentPosition(), 5), 0, null, null, null);
            }
            i5 = currentMediaItemIndex + i6;
        }
        i4 = i5;
        Timeline.Window window2 = new Timeline.Window();
        updatePlayerInfo(maskTimelineAndPositionInfo(this.playerInfo, createMaskingTimeline, i4, createMaskingTimeline.getWindow(i4, window2).firstPeriodIndex + (this.playerInfo.sessionPositionInfo.positionInfo.periodIndex - timeline.getWindow(currentMediaItemIndex, window2).firstPeriodIndex), getCurrentPosition(), getContentPosition(), 5), 0, null, null, null);
    }

    private void seekToInternalByOffset(long j) {
        long currentPosition = getCurrentPosition() + j;
        long duration = getDuration();
        if (duration != C.TIME_UNSET) {
            currentPosition = Math.min(currentPosition, duration);
        }
        seekToInternal(getCurrentMediaItemIndex(), Math.max(currentPosition, 0L));
    }

    private void seekToInternal(int i, long j) {
        int i2;
        int i3;
        PlayerInfo maskPositionInfo;
        Timeline timeline = this.playerInfo.timeline;
        if ((timeline.isEmpty() || i < timeline.getWindowCount()) && !isPlayingAd()) {
            int i4 = getPlaybackState() == 1 ? 1 : 2;
            PlayerInfo playerInfo = this.playerInfo;
            PlayerInfo copyWithPlaybackState = playerInfo.copyWithPlaybackState(i4, playerInfo.playerError);
            PeriodInfo periodInfo = getPeriodInfo(timeline, i, j);
            if (periodInfo == null) {
                i2 = 1;
                i3 = 2;
                Player.PositionInfo positionInfo = new Player.PositionInfo(null, i, null, null, i, j == C.TIME_UNSET ? 0L : j, j == C.TIME_UNSET ? 0L : j, -1, -1);
                PlayerInfo playerInfo2 = this.playerInfo;
                maskPositionInfo = maskTimelineAndPositionInfo(playerInfo2, playerInfo2.timeline, positionInfo, new SessionPositionInfo(positionInfo, this.playerInfo.sessionPositionInfo.isPlayingAd, SystemClock.elapsedRealtime(), this.playerInfo.sessionPositionInfo.durationMs, j == C.TIME_UNSET ? 0L : j, 0, 0L, this.playerInfo.sessionPositionInfo.currentLiveOffsetMs, this.playerInfo.sessionPositionInfo.contentDurationMs, j == C.TIME_UNSET ? 0L : j), 1);
            } else {
                i2 = 1;
                i3 = 2;
                maskPositionInfo = maskPositionInfo(copyWithPlaybackState, timeline, periodInfo);
            }
            int i5 = (this.playerInfo.timeline.isEmpty() || maskPositionInfo.sessionPositionInfo.positionInfo.mediaItemIndex == this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex) ? 0 : i2;
            if (i5 == 0 && maskPositionInfo.sessionPositionInfo.positionInfo.positionMs == this.playerInfo.sessionPositionInfo.positionInfo.positionMs) {
                return;
            }
            updatePlayerInfo(maskPositionInfo, null, null, Integer.valueOf(i2), i5 != 0 ? Integer.valueOf(i3) : null);
        }
    }

    private void setPlayWhenReady(boolean z, int i) {
        int playbackSuppressionReason = getPlaybackSuppressionReason();
        if (playbackSuppressionReason == 1) {
            playbackSuppressionReason = 0;
        }
        if (this.playerInfo.playWhenReady == z && this.playerInfo.playbackSuppressionReason == playbackSuppressionReason) {
            return;
        }
        this.currentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.lastSetPlayWhenReadyCalledTimeMs = SystemClock.elapsedRealtime();
        updatePlayerInfo(this.playerInfo.copyWithPlayWhenReady(z, i, playbackSuppressionReason), null, Integer.valueOf(i), null, null);
    }

    private void updatePlayerInfo(PlayerInfo playerInfo, Integer num, Integer num2, Integer num3, Integer num4) {
        PlayerInfo playerInfo2 = this.playerInfo;
        this.playerInfo = playerInfo;
        notifyPlayerInfoListenersWithReasons(playerInfo2, playerInfo, num, num2, num3, num4);
    }

    private void notifyPlayerInfoListenersWithReasons(PlayerInfo playerInfo, final PlayerInfo playerInfo2, final Integer num, final Integer num2, final Integer num3, final Integer num4) {
        if (num != null) {
            this.listeners.queueEvent(0, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda45
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onTimelineChanged(PlayerInfo.this.timeline, num.intValue());
                }
            });
        }
        if (num3 != null) {
            this.listeners.queueEvent(11, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda57
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPositionDiscontinuity(r0.oldPositionInfo, PlayerInfo.this.newPositionInfo, num3.intValue());
                }
            });
        }
        final MediaItem currentMediaItem = playerInfo2.getCurrentMediaItem();
        if (num4 != null) {
            this.listeners.queueEvent(1, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda65
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMediaItemTransition(MediaItem.this, num4.intValue());
                }
            });
        }
        PlaybackException playbackException = playerInfo.playerError;
        final PlaybackException playbackException2 = playerInfo2.playerError;
        if (playbackException != playbackException2 && (playbackException == null || !playbackException.errorInfoEquals(playbackException2))) {
            this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda66
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlayerErrorChanged(PlaybackException.this);
                }
            });
            if (playbackException2 != null) {
                this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda68
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onPlayerError(PlaybackException.this);
                    }
                });
            }
        }
        if (!playerInfo.currentTracks.equals(playerInfo2.currentTracks)) {
            this.listeners.queueEvent(2, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda69
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onTracksChanged(PlayerInfo.this.currentTracks);
                }
            });
        }
        if (!playerInfo.mediaMetadata.equals(playerInfo2.mediaMetadata)) {
            this.listeners.queueEvent(14, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda70
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMediaMetadataChanged(PlayerInfo.this.mediaMetadata);
                }
            });
        }
        if (playerInfo.isLoading != playerInfo2.isLoading) {
            this.listeners.queueEvent(3, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda71
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onIsLoadingChanged(PlayerInfo.this.isLoading);
                }
            });
        }
        if (playerInfo.playbackState != playerInfo2.playbackState) {
            this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda72
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackStateChanged(PlayerInfo.this.playbackState);
                }
            });
        }
        if (num2 != null) {
            this.listeners.queueEvent(5, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda73
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlayWhenReadyChanged(PlayerInfo.this.playWhenReady, num2.intValue());
                }
            });
        }
        if (playerInfo.playbackSuppressionReason != playerInfo2.playbackSuppressionReason) {
            this.listeners.queueEvent(6, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda47
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackSuppressionReasonChanged(PlayerInfo.this.playbackSuppressionReason);
                }
            });
        }
        if (playerInfo.isPlaying != playerInfo2.isPlaying) {
            this.listeners.queueEvent(7, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda48
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onIsPlayingChanged(PlayerInfo.this.isPlaying);
                }
            });
        }
        if (!playerInfo.playbackParameters.equals(playerInfo2.playbackParameters)) {
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda49
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackParametersChanged(PlayerInfo.this.playbackParameters);
                }
            });
        }
        if (playerInfo.repeatMode != playerInfo2.repeatMode) {
            this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda50
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onRepeatModeChanged(PlayerInfo.this.repeatMode);
                }
            });
        }
        if (playerInfo.shuffleModeEnabled != playerInfo2.shuffleModeEnabled) {
            this.listeners.queueEvent(9, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda51
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onShuffleModeEnabledChanged(PlayerInfo.this.shuffleModeEnabled);
                }
            });
        }
        if (!playerInfo.playlistMetadata.equals(playerInfo2.playlistMetadata)) {
            this.listeners.queueEvent(15, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda52
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaylistMetadataChanged(PlayerInfo.this.playlistMetadata);
                }
            });
        }
        if (playerInfo.volume != playerInfo2.volume) {
            this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda53
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onVolumeChanged(PlayerInfo.this.volume);
                }
            });
        }
        if (!playerInfo.audioAttributes.equals(playerInfo2.audioAttributes)) {
            this.listeners.queueEvent(20, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda54
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAudioAttributesChanged(PlayerInfo.this.audioAttributes);
                }
            });
        }
        if (!playerInfo.cueGroup.cues.equals(playerInfo2.cueGroup.cues)) {
            this.listeners.queueEvent(27, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda55
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onCues(PlayerInfo.this.cueGroup.cues);
                }
            });
            this.listeners.queueEvent(27, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda56
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onCues(PlayerInfo.this.cueGroup);
                }
            });
        }
        if (!playerInfo.deviceInfo.equals(playerInfo2.deviceInfo)) {
            this.listeners.queueEvent(29, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda58
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onDeviceInfoChanged(PlayerInfo.this.deviceInfo);
                }
            });
        }
        if (playerInfo.deviceVolume != playerInfo2.deviceVolume || playerInfo.deviceMuted != playerInfo2.deviceMuted) {
            this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda59
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onDeviceVolumeChanged(r0.deviceVolume, PlayerInfo.this.deviceMuted);
                }
            });
        }
        if (!playerInfo.videoSize.equals(playerInfo2.videoSize)) {
            this.listeners.queueEvent(25, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda60
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onVideoSizeChanged(PlayerInfo.this.videoSize);
                }
            });
        }
        if (playerInfo.seekBackIncrementMs != playerInfo2.seekBackIncrementMs) {
            this.listeners.queueEvent(16, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda61
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onSeekBackIncrementChanged(PlayerInfo.this.seekBackIncrementMs);
                }
            });
        }
        if (playerInfo.seekForwardIncrementMs != playerInfo2.seekForwardIncrementMs) {
            this.listeners.queueEvent(17, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda62
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onSeekForwardIncrementChanged(PlayerInfo.this.seekForwardIncrementMs);
                }
            });
        }
        if (playerInfo.maxSeekToPreviousPositionMs != playerInfo2.maxSeekToPreviousPositionMs) {
            this.listeners.queueEvent(18, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda63
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMaxSeekToPreviousPositionChanged(PlayerInfo.this.maxSeekToPreviousPositionMs);
                }
            });
        }
        if (!playerInfo.trackSelectionParameters.equals(playerInfo2.trackSelectionParameters)) {
            this.listeners.queueEvent(19, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda64
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onTrackSelectionParametersChanged(PlayerInfo.this.trackSelectionParameters);
                }
            });
        }
        this.listeners.flushEvents();
    }

    private boolean requestConnectToService() {
        int i = Build.VERSION.SDK_INT >= 29 ? FragmentTransaction.TRANSIT_FRAGMENT_OPEN : 1;
        Intent intent = new Intent(MediaSessionService.SERVICE_INTERFACE);
        intent.setClassName(this.token.getPackageName(), this.token.getServiceName());
        try {
            if (this.context.bindService(intent, this.serviceConnection, i)) {
                return true;
            }
            Log.w(TAG, "bind to " + this.token + " failed");
            return false;
        } catch (SecurityException e) {
            Log.w(TAG, "bind to " + this.token + " not allowed", e);
            return false;
        }
    }

    private boolean requestConnectToSession(Bundle bundle) {
        try {
            IMediaSession.Stub.asInterface((IBinder) Assertions.checkStateNotNull(this.token.getBinder())).connect(this.controllerStub, this.sequencedFutureManager.obtainNextSequenceNumber(), new ConnectionRequest(this.context.getPackageName(), Process.myPid(), bundle, this.instance.getMaxCommandsForMediaItems()).toBundle());
            return true;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call connection request.", e);
            return false;
        }
    }

    private void clearSurfacesAndCallbacks() {
        TextureView textureView = this.videoTextureView;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(null);
            this.videoTextureView = null;
        }
        SurfaceHolder surfaceHolder = this.videoSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.surfaceCallback);
            this.videoSurfaceHolder = null;
        }
        if (this.videoSurface != null) {
            this.videoSurface = null;
        }
    }

    public void maybeNotifySurfaceSizeChanged(final int i, final int i2) {
        if (this.surfaceSize.getWidth() == i && this.surfaceSize.getHeight() == i2) {
            return;
        }
        this.surfaceSize = new Size(i, i2);
        this.listeners.sendEvent(24, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda5
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                ((Player.Listener) obj).onSurfaceSizeChanged(i, i2);
            }
        });
    }

    public IMediaSession getSessionInterfaceWithSessionCommandIfAble(int i) {
        Assertions.checkArgument(i != 0);
        if (!this.sessionCommands.contains(i)) {
            Log.w(TAG, "Controller isn't allowed to call command, commandCode=" + i);
            return null;
        }
        return this.iSession;
    }

    IMediaSession getSessionInterfaceWithSessionCommandIfAble(SessionCommand sessionCommand) {
        Assertions.checkArgument(sessionCommand.commandCode == 0);
        if (!this.sessionCommands.contains(sessionCommand)) {
            Log.w(TAG, "Controller isn't allowed to call custom session command:" + sessionCommand.customAction);
            return null;
        }
        return this.iSession;
    }

    public void notifyPeriodicSessionPositionInfoChanged(SessionPositionInfo sessionPositionInfo) {
        if (isConnected()) {
            updateSessionPositionInfoIfNeeded(sessionPositionInfo);
        }
    }

    public <T> void setFutureResult(final int i, T t) {
        this.sequencedFutureManager.setFutureResult(i, t);
        getInstance().runOnApplicationLooper(new Runnable() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda105
            @Override // java.lang.Runnable
            public final void run() {
                MediaControllerImplBase.this.m382x8eac6d74(i);
            }
        });
    }

    /* renamed from: lambda$setFutureResult$106$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m382x8eac6d74(int i) {
        this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(i));
        SessionToken sessionToken = this.connectedToken;
        if (sessionToken == null || sessionToken.getInterfaceVersion() >= 5 || !this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
            return;
        }
        this.fallbackPlaybackInfoUpdateHandler.postDelayed(new Runnable() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda93
            @Override // java.lang.Runnable
            public final void run() {
                MediaControllerImplBase.this.m381x8f22d373();
            }
        }, 500L);
    }

    /* renamed from: lambda$setFutureResult$105$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m381x8f22d373() {
        PlayerInfo playerInfo = this.pendingPlayerInfo;
        if (playerInfo != null) {
            onPlayerInfoChanged(playerInfo, PlayerInfo.BundlingExclusions.NONE);
        }
    }

    public void onConnected(ConnectionState connectionState) {
        if (this.iSession != null) {
            Log.e(TAG, "Cannot be notified about the connection result many times. Probably a bug or malicious app.");
            getInstance().release();
            return;
        }
        this.iSession = connectionState.sessionBinder;
        this.sessionActivity = connectionState.sessionActivity;
        this.sessionCommands = connectionState.sessionCommands;
        this.playerCommandsFromSession = connectionState.playerCommandsFromSession;
        Player.Commands commands = connectionState.playerCommandsFromPlayer;
        this.playerCommandsFromPlayer = commands;
        this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
        this.customLayoutOriginal = connectionState.customLayout;
        ImmutableList<CommandButton> immutableList = connectionState.mediaButtonPreferences;
        this.mediaButtonPreferencesOriginal = immutableList;
        ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(immutableList, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, connectionState.sessionExtras);
        this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
        this.resolvedCustomLayout = resolveCustomLayout(resolveMediaButtonPreferences, this.customLayoutOriginal, connectionState.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        for (int i = 0; i < connectionState.commandButtonsForMediaItems.size(); i++) {
            CommandButton commandButton = connectionState.commandButtonsForMediaItems.get(i);
            if (commandButton.sessionCommand != null && commandButton.sessionCommand.commandCode == 0) {
                builder.put(commandButton.sessionCommand.customAction, commandButton);
            }
        }
        this.commandButtonsForMediaItemsMap = builder.buildOrThrow();
        this.playerInfo = connectionState.playerInfo;
        MediaSession.Token platformToken = connectionState.platformToken == null ? this.token.getPlatformToken() : connectionState.platformToken;
        if (platformToken != null) {
            this.platformController = new android.media.session.MediaController(this.context, platformToken);
        }
        try {
            connectionState.sessionBinder.asBinder().linkToDeath(this.deathRecipient, 0);
            this.connectedToken = new SessionToken(this.token.getUid(), 0, connectionState.libraryVersion, connectionState.sessionInterfaceVersion, this.token.getPackageName(), connectionState.sessionBinder, connectionState.tokenExtras, platformToken);
            this.sessionExtras = connectionState.sessionExtras;
            getInstance().notifyAccepted();
        } catch (RemoteException unused) {
            getInstance().release();
        }
    }

    private void sendControllerResult(int i, SessionResult sessionResult) {
        IMediaSession iMediaSession = this.iSession;
        if (iMediaSession == null) {
            return;
        }
        try {
            iMediaSession.onControllerResult(this.controllerStub, i, sessionResult.toBundle());
        } catch (RemoteException unused) {
            Log.w(TAG, "Error in sending");
        }
    }

    private void sendControllerResultWhenReady(final int i, final ListenableFuture<SessionResult> listenableFuture) {
        listenableFuture.addListener(new Runnable() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda94
            @Override // java.lang.Runnable
            public final void run() {
                MediaControllerImplBase.this.m370xcc63a2fb(listenableFuture, i);
            }
        }, MoreExecutors.directExecutor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: lambda$sendControllerResultWhenReady$107$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m370xcc63a2fb(ListenableFuture listenableFuture, int i) {
        SessionResult sessionResult;
        try {
            sessionResult = (SessionResult) Assertions.checkNotNull((SessionResult) listenableFuture.get(), "SessionResult must not be null");
        } catch (InterruptedException e) {
            e = e;
            Log.w(TAG, "Session operation failed", e);
            sessionResult = new SessionResult(-1);
        } catch (CancellationException e2) {
            Log.w(TAG, "Session operation cancelled", e2);
            sessionResult = new SessionResult(1);
        } catch (ExecutionException e3) {
            e = e3;
            Log.w(TAG, "Session operation failed", e);
            sessionResult = new SessionResult(-1);
        }
        sendControllerResult(i, sessionResult);
    }

    public void onCustomCommand(final int i, final SessionCommand sessionCommand, final Bundle bundle) {
        if (isConnected()) {
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda98
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    MediaControllerImplBase.this.m346xd2a174af(sessionCommand, bundle, i, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* renamed from: lambda$onCustomCommand$108$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m346xd2a174af(SessionCommand sessionCommand, Bundle bundle, int i, MediaController.Listener listener) {
        sendControllerResultWhenReady(i, (ListenableFuture) Assertions.checkNotNull(listener.onCustomCommand(getInstance(), sessionCommand, bundle), "ControllerCallback#onCustomCommand() must not return null"));
    }

    public void onPlayerInfoChanged(PlayerInfo playerInfo, PlayerInfo.BundlingExclusions bundlingExclusions) {
        if (isConnected()) {
            PlayerInfo playerInfo2 = this.pendingPlayerInfo;
            if (playerInfo2 != null) {
                this.pendingPlayerInfo = MediaUtils.mergePlayerInfo(playerInfo2, playerInfo, bundlingExclusions, this.intersectedPlayerCommands);
                if (!this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
                    return;
                }
                playerInfo = this.pendingPlayerInfo;
                bundlingExclusions = PlayerInfo.BundlingExclusions.NONE;
                this.pendingPlayerInfo = null;
            }
            PlayerInfo playerInfo3 = this.playerInfo;
            PlayerInfo mergePlayerInfo = MediaUtils.mergePlayerInfo(playerInfo3, playerInfo, bundlingExclusions, this.intersectedPlayerCommands);
            this.playerInfo = mergePlayerInfo;
            notifyPlayerInfoListenersWithReasons(playerInfo3, mergePlayerInfo, !playerInfo3.timeline.equals(mergePlayerInfo.timeline) ? Integer.valueOf(mergePlayerInfo.timelineChangeReason) : null, (playerInfo3.playWhenReadyChangeReason == mergePlayerInfo.playWhenReadyChangeReason && playerInfo3.playWhenReady == mergePlayerInfo.playWhenReady) ? null : Integer.valueOf(mergePlayerInfo.playWhenReadyChangeReason), (playerInfo3.oldPositionInfo.equals(playerInfo.oldPositionInfo) && playerInfo3.newPositionInfo.equals(playerInfo.newPositionInfo)) ? null : Integer.valueOf(mergePlayerInfo.discontinuityReason), !Objects.equals(playerInfo3.getCurrentMediaItem(), mergePlayerInfo.getCurrentMediaItem()) ? Integer.valueOf(mergePlayerInfo.mediaItemTransitionReason) : null);
        }
    }

    public void onAvailableCommandsChangedFromSession(final SessionCommands sessionCommands, Player.Commands commands) {
        boolean z;
        boolean z2;
        if (isConnected()) {
            boolean equals = Objects.equals(this.playerCommandsFromSession, commands);
            boolean equals2 = Objects.equals(this.sessionCommands, sessionCommands);
            if (equals && equals2) {
                return;
            }
            this.sessionCommands = sessionCommands;
            boolean z3 = false;
            if (equals) {
                z = false;
            } else {
                this.playerCommandsFromSession = commands;
                Player.Commands commands2 = this.intersectedPlayerCommands;
                Player.Commands createIntersectedCommandsEnsuringCommandReleaseAvailable = createIntersectedCommandsEnsuringCommandReleaseAvailable(commands, this.playerCommandsFromPlayer);
                this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable;
                z = !Objects.equals(createIntersectedCommandsEnsuringCommandReleaseAvailable, commands2);
            }
            if (!equals2 || z) {
                ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
                ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
                ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
                this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
                this.resolvedCustomLayout = resolveCustomLayout(resolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, sessionCommands, this.intersectedPlayerCommands);
                z2 = !this.resolvedMediaButtonPreferences.equals(immutableList);
                z3 = !this.resolvedCustomLayout.equals(immutableList2);
            } else {
                z2 = false;
            }
            if (z) {
                this.listeners.sendEvent(13, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda119
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m342xd4c1ee1f((Player.Listener) obj);
                    }
                });
            }
            if (!equals2) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda120
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaControllerImplBase.this.m343xca952a35(sessionCommands, (MediaController.Listener) obj);
                    }
                });
            }
            if (z3) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda121
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaControllerImplBase.this.m344xca1ec436((MediaController.Listener) obj);
                    }
                });
            }
            if (z2) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda1
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaControllerImplBase.this.m345xc9a85e37((MediaController.Listener) obj);
                    }
                });
            }
        }
    }

    /* renamed from: lambda$onAvailableCommandsChangedFromSession$109$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m342xd4c1ee1f(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.intersectedPlayerCommands);
    }

    /* renamed from: lambda$onAvailableCommandsChangedFromSession$110$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m343xca952a35(SessionCommands sessionCommands, MediaController.Listener listener) {
        listener.onAvailableSessionCommandsChanged(getInstance(), sessionCommands);
    }

    /* renamed from: lambda$onAvailableCommandsChangedFromSession$111$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m344xca1ec436(MediaController.Listener listener) {
        listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
    }

    /* renamed from: lambda$onAvailableCommandsChangedFromSession$112$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m345xc9a85e37(MediaController.Listener listener) {
        listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
    }

    public void onAvailableCommandsChangedFromPlayer(Player.Commands commands) {
        boolean z;
        boolean z2;
        if (isConnected() && !Objects.equals(this.playerCommandsFromPlayer, commands)) {
            this.playerCommandsFromPlayer = commands;
            Player.Commands commands2 = this.intersectedPlayerCommands;
            Player.Commands createIntersectedCommandsEnsuringCommandReleaseAvailable = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
            this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable;
            if (Objects.equals(createIntersectedCommandsEnsuringCommandReleaseAvailable, commands2)) {
                z = false;
                z2 = false;
            } else {
                ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
                ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
                ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
                this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
                this.resolvedCustomLayout = resolveCustomLayout(resolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
                z = !this.resolvedMediaButtonPreferences.equals(immutableList);
                z2 = !this.resolvedCustomLayout.equals(immutableList2);
                this.listeners.sendEvent(13, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda2
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        MediaControllerImplBase.this.m339xdadf9435((Player.Listener) obj);
                    }
                });
            }
            if (z2) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda3
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaControllerImplBase.this.m340xda692e36((MediaController.Listener) obj);
                    }
                });
            }
            if (z) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda4
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaControllerImplBase.this.m341xd9f2c837((MediaController.Listener) obj);
                    }
                });
            }
        }
    }

    /* renamed from: lambda$onAvailableCommandsChangedFromPlayer$113$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m339xdadf9435(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.intersectedPlayerCommands);
    }

    /* renamed from: lambda$onAvailableCommandsChangedFromPlayer$114$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m340xda692e36(MediaController.Listener listener) {
        listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
    }

    /* renamed from: lambda$onAvailableCommandsChangedFromPlayer$115$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m341xd9f2c837(MediaController.Listener listener) {
        listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
    }

    public void onSetCustomLayout(final int i, List<CommandButton> list) {
        if (isConnected()) {
            ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
            ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
            this.customLayoutOriginal = ImmutableList.copyOf((Collection) list);
            ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, list, this.sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
            this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
            this.resolvedCustomLayout = resolveCustomLayout(resolveMediaButtonPreferences, list, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
            final boolean z = !this.resolvedMediaButtonPreferences.equals(immutableList);
            final boolean z2 = !this.resolvedCustomLayout.equals(immutableList2);
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda104
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    MediaControllerImplBase.this.m349xdaf6072f(z2, z, i, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* renamed from: lambda$onSetCustomLayout$116$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m349xdaf6072f(boolean z, boolean z2, int i, MediaController.Listener listener) {
        ListenableFuture<SessionResult> listenableFuture = (ListenableFuture) Assertions.checkNotNull(listener.onSetCustomLayout(getInstance(), this.resolvedCustomLayout), "MediaController.Listener#onSetCustomLayout() must not return null");
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
        }
        if (z2) {
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
        sendControllerResultWhenReady(i, listenableFuture);
    }

    public void onSetMediaButtonPreferences(final int i, List<CommandButton> list) {
        if (isConnected()) {
            ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
            ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
            this.mediaButtonPreferencesOriginal = ImmutableList.copyOf((Collection) list);
            ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(list, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
            this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
            this.resolvedCustomLayout = resolveCustomLayout(resolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
            final boolean z = !this.resolvedMediaButtonPreferences.equals(immutableList);
            final boolean z2 = !this.resolvedCustomLayout.equals(immutableList2);
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda86
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    MediaControllerImplBase.this.m350x5ad4317(z2, z, i, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* renamed from: lambda$onSetMediaButtonPreferences$117$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m350x5ad4317(boolean z, boolean z2, int i, MediaController.Listener listener) {
        ListenableFuture<SessionResult> listenableFuture = (ListenableFuture) Assertions.checkNotNull(listener.onSetCustomLayout(getInstance(), this.resolvedCustomLayout), "MediaController.Listener#onSetCustomLayout() must not return null");
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
        }
        if (z2) {
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
        sendControllerResultWhenReady(i, listenableFuture);
    }

    public void onExtrasChanged(final Bundle bundle) {
        if (isConnected()) {
            ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
            ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
            this.sessionExtras = bundle;
            ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, bundle);
            this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
            this.resolvedCustomLayout = resolveCustomLayout(resolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
            final boolean z = !this.resolvedMediaButtonPreferences.equals(immutableList);
            final boolean z2 = !this.resolvedCustomLayout.equals(immutableList2);
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda74
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    MediaControllerImplBase.this.m348x8748985(bundle, z2, z, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* renamed from: lambda$onExtrasChanged$118$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m348x8748985(Bundle bundle, boolean z, boolean z2, MediaController.Listener listener) {
        listener.onExtrasChanged(getInstance(), bundle);
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
        }
        if (z2) {
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
    }

    public void onSetSessionActivity(int i, final PendingIntent pendingIntent) {
        if (!isConnected() || Objects.equals(this.sessionActivity, pendingIntent)) {
            return;
        }
        this.sessionActivity = pendingIntent;
        getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda92
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                MediaControllerImplBase.this.m351x81830230(pendingIntent, (MediaController.Listener) obj);
            }
        });
    }

    /* renamed from: lambda$onSetSessionActivity$119$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m351x81830230(PendingIntent pendingIntent, MediaController.Listener listener) {
        listener.onSessionActivityChanged(getInstance(), pendingIntent);
    }

    public void onError(int i, final SessionError sessionError) {
        if (isConnected()) {
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda118
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    MediaControllerImplBase.this.m347xcc4af153(sessionError, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* renamed from: lambda$onError$120$androidx-media3-session-MediaControllerImplBase */
    public /* synthetic */ void m347xcc4af153(SessionError sessionError, MediaController.Listener listener) {
        listener.onError(getInstance(), sessionError);
    }

    public void onRenderedFirstFrame() {
        this.listeners.sendEvent(26, new SimpleBasePlayer$$ExternalSyntheticLambda10());
    }

    private void updateSessionPositionInfoIfNeeded(SessionPositionInfo sessionPositionInfo) {
        if (this.pendingMaskingSequencedFutureNumbers.isEmpty() && this.playerInfo.sessionPositionInfo.eventTimeMs < sessionPositionInfo.eventTimeMs && MediaUtils.areSessionPositionInfosInSamePeriodOrAd(sessionPositionInfo, this.playerInfo.sessionPositionInfo)) {
            this.playerInfo = this.playerInfo.copyWithSessionPositionInfo(sessionPositionInfo);
        }
    }

    private boolean isPlayerCommandAvailable(int i) {
        if (this.intersectedPlayerCommands.contains(i)) {
            return true;
        }
        Log.w(TAG, "Controller isn't allowed to call command= " + i);
        return false;
    }

    private PlayerInfo maskPositionInfo(PlayerInfo playerInfo, Timeline timeline, PeriodInfo periodInfo) {
        int i = playerInfo.sessionPositionInfo.positionInfo.periodIndex;
        int i2 = periodInfo.index;
        Timeline.Period period = new Timeline.Period();
        timeline.getPeriod(i, period);
        Timeline.Period period2 = new Timeline.Period();
        timeline.getPeriod(i2, period2);
        boolean z = i != i2;
        long j = periodInfo.periodPositionUs;
        long msToUs = Util.msToUs(getCurrentPosition()) - period.getPositionInWindowUs();
        if (!z && j == msToUs) {
            return playerInfo;
        }
        Assertions.checkState(playerInfo.sessionPositionInfo.positionInfo.adGroupIndex == -1);
        Player.PositionInfo positionInfo = new Player.PositionInfo(null, period.windowIndex, playerInfo.sessionPositionInfo.positionInfo.mediaItem, null, i, Util.usToMs(period.positionInWindowUs + msToUs), Util.usToMs(period.positionInWindowUs + msToUs), -1, -1);
        timeline.getPeriod(i2, period2);
        Timeline.Window window = new Timeline.Window();
        timeline.getWindow(period2.windowIndex, window);
        long usToMs = Util.usToMs(period2.positionInWindowUs + j);
        Player.PositionInfo positionInfo2 = new Player.PositionInfo(null, period2.windowIndex, window.mediaItem, null, i2, usToMs, usToMs, -1, -1);
        PlayerInfo copyWithPositionInfos = playerInfo.copyWithPositionInfos(positionInfo, positionInfo2, 1);
        if (z || j < msToUs) {
            return copyWithPositionInfos.copyWithSessionPositionInfo(new SessionPositionInfo(positionInfo2, false, SystemClock.elapsedRealtime(), window.getDurationMs(), usToMs, MediaUtils.calculateBufferedPercentage(usToMs, window.getDurationMs()), 0L, C.TIME_UNSET, C.TIME_UNSET, usToMs));
        }
        long max = Math.max(0L, Util.msToUs(copyWithPositionInfos.sessionPositionInfo.totalBufferedDurationMs) - (j - msToUs));
        long usToMs2 = Util.usToMs(period2.positionInWindowUs + j + max);
        return copyWithPositionInfos.copyWithSessionPositionInfo(new SessionPositionInfo(positionInfo2, false, SystemClock.elapsedRealtime(), window.getDurationMs(), usToMs2, MediaUtils.calculateBufferedPercentage(usToMs2, window.getDurationMs()), Util.usToMs(max), C.TIME_UNSET, C.TIME_UNSET, usToMs2));
    }

    private PeriodInfo getPeriodInfo(Timeline timeline, int i, long j) {
        if (timeline.isEmpty()) {
            return null;
        }
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        if (i == -1 || i >= timeline.getWindowCount()) {
            i = timeline.getFirstWindowIndex(getShuffleModeEnabled());
            j = timeline.getWindow(i, window).getDefaultPositionMs();
        }
        return getPeriodInfo(timeline, window, period, i, Util.msToUs(j));
    }

    private static PeriodInfo getPeriodInfo(Timeline timeline, Timeline.Window window, Timeline.Period period, int i, long j) {
        Assertions.checkIndex(i, 0, timeline.getWindowCount());
        timeline.getWindow(i, window);
        if (j == C.TIME_UNSET) {
            j = window.getDefaultPositionUs();
            if (j == C.TIME_UNSET) {
                return null;
            }
        }
        int i2 = window.firstPeriodIndex;
        timeline.getPeriod(i2, period);
        while (i2 < window.lastPeriodIndex && period.positionInWindowUs != j) {
            int i3 = i2 + 1;
            if (timeline.getPeriod(i3, period).positionInWindowUs > j) {
                break;
            }
            i2 = i3;
        }
        timeline.getPeriod(i2, period);
        return new PeriodInfo(i2, j - period.positionInWindowUs);
    }

    private static int getCurrentMediaItemIndexInternal(PlayerInfo playerInfo) {
        if (playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex == -1) {
            return 0;
        }
        return playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
    }

    private static PlayerInfo maskTimelineAndPositionInfo(PlayerInfo playerInfo, Timeline timeline, int i, int i2, long j, long j2, int i3) {
        Player.PositionInfo positionInfo = new Player.PositionInfo(null, i, timeline.getWindow(i, new Timeline.Window()).mediaItem, null, i2, j, j2, playerInfo.sessionPositionInfo.positionInfo.adGroupIndex, playerInfo.sessionPositionInfo.positionInfo.adIndexInAdGroup);
        return maskTimelineAndPositionInfo(playerInfo, timeline, positionInfo, new SessionPositionInfo(positionInfo, playerInfo.sessionPositionInfo.isPlayingAd, SystemClock.elapsedRealtime(), playerInfo.sessionPositionInfo.durationMs, playerInfo.sessionPositionInfo.bufferedPositionMs, playerInfo.sessionPositionInfo.bufferedPercentage, playerInfo.sessionPositionInfo.totalBufferedDurationMs, playerInfo.sessionPositionInfo.currentLiveOffsetMs, playerInfo.sessionPositionInfo.contentDurationMs, playerInfo.sessionPositionInfo.contentBufferedPositionMs), i3);
    }

    private static PlayerInfo maskTimelineAndPositionInfo(PlayerInfo playerInfo, Timeline timeline, Player.PositionInfo positionInfo, SessionPositionInfo sessionPositionInfo, int i) {
        return new PlayerInfo.Builder(playerInfo).setTimeline(timeline).setOldPositionInfo(playerInfo.sessionPositionInfo.positionInfo).setNewPositionInfo(positionInfo).setSessionPositionInfo(sessionPositionInfo).setDiscontinuityReason(i).build();
    }

    private static Timeline.Period getPeriodWithNewWindowIndex(Timeline timeline, int i, int i2) {
        Timeline.Period period = new Timeline.Period();
        timeline.getPeriod(i, period);
        period.windowIndex = i2;
        return period;
    }

    private static int getNewPeriodIndexWithoutRemovedPeriods(Timeline timeline, int i, int i2, int i3) {
        if (i == -1) {
            return i;
        }
        while (i2 < i3) {
            Timeline.Window window = new Timeline.Window();
            timeline.getWindow(i2, window);
            i -= (window.lastPeriodIndex - window.firstPeriodIndex) + 1;
            i2++;
        }
        return i;
    }

    private static Timeline.Window createNewWindow(MediaItem mediaItem) {
        return new Timeline.Window().set(0, mediaItem, null, 0L, 0L, 0L, true, false, null, 0L, C.TIME_UNSET, -1, -1, 0L);
    }

    private static Timeline.Period createNewPeriod(int i) {
        return new Timeline.Period().set(null, null, i, C.TIME_UNSET, 0L, AdPlaybackState.NONE, true);
    }

    private static void rebuildPeriods(Timeline timeline, List<Timeline.Window> list, List<Timeline.Period> list2) {
        for (int i = 0; i < list.size(); i++) {
            Timeline.Window window = list.get(i);
            int i2 = window.firstPeriodIndex;
            int i3 = window.lastPeriodIndex;
            if (i2 == -1 || i3 == -1) {
                window.firstPeriodIndex = list2.size();
                window.lastPeriodIndex = list2.size();
                list2.add(createNewPeriod(i));
            } else {
                window.firstPeriodIndex = list2.size();
                window.lastPeriodIndex = list2.size() + (i3 - i2);
                while (i2 <= i3) {
                    list2.add(getPeriodWithNewWindowIndex(timeline, i2, i));
                    i2++;
                }
            }
        }
    }

    private static int resolveSubsequentMediaItemIndex(int i, boolean z, int i2, Timeline timeline, int i3, int i4) {
        int windowCount = timeline.getWindowCount();
        for (int i5 = 0; i5 < windowCount && (i2 = timeline.getNextWindowIndex(i2, i, z)) != -1; i5++) {
            if (i2 < i3 || i2 >= i4) {
                return i2;
            }
        }
        return -1;
    }

    private static ImmutableList<CommandButton> resolveMediaButtonPreferences(List<CommandButton> list, List<CommandButton> list2, SessionCommands sessionCommands, Player.Commands commands, Bundle bundle) {
        if (list.isEmpty()) {
            list = CommandButton.getMediaButtonPreferencesFromCustomLayout(list2, commands, bundle);
        }
        return CommandButton.copyWithUnavailableButtonsDisabled(list, sessionCommands, commands);
    }

    private static ImmutableList<CommandButton> resolveCustomLayout(List<CommandButton> list, List<CommandButton> list2, Bundle bundle, SessionCommands sessionCommands, Player.Commands commands) {
        if (!list2.isEmpty()) {
            return CommandButton.copyWithUnavailableButtonsDisabled(list2, sessionCommands, commands);
        }
        return CommandButton.getCustomLayoutFromMediaButtonPreferences(list, (bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS") || commands.containsAny(6, 7)) ? false : true, (bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT") || commands.containsAny(8, 9)) ? false : true);
    }

    private static Player.Commands createIntersectedCommandsEnsuringCommandReleaseAvailable(Player.Commands commands, Player.Commands commands2) {
        Player.Commands intersect = MediaUtils.intersect(commands, commands2);
        return intersect.contains(32) ? intersect : intersect.buildUpon().add(32).build();
    }

    /* loaded from: classes.dex */
    public class SessionServiceConnection implements ServiceConnection {
        private final Bundle connectionHints;

        public SessionServiceConnection(Bundle bundle) {
            this.connectionHints = bundle;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MediaController mediaControllerImplBase;
            MediaControllerImplBase$$ExternalSyntheticLambda46 mediaControllerImplBase$$ExternalSyntheticLambda46;
            try {
                try {
                    if (!MediaControllerImplBase.this.token.getPackageName().equals(componentName.getPackageName())) {
                        Log.e(MediaControllerImplBase.TAG, "Expected connection to " + MediaControllerImplBase.this.token.getPackageName() + " but is connected to " + componentName);
                        mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
                        MediaController mediaControllerImplBase2 = MediaControllerImplBase.this.getInstance();
                        Objects.requireNonNull(mediaControllerImplBase2);
                        mediaControllerImplBase$$ExternalSyntheticLambda46 = new MediaControllerImplBase$$ExternalSyntheticLambda46(mediaControllerImplBase2);
                    } else {
                        IMediaSessionService asInterface = IMediaSessionService.Stub.asInterface(iBinder);
                        if (asInterface == null) {
                            Log.e(MediaControllerImplBase.TAG, "Service interface is missing.");
                            mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
                            MediaController mediaControllerImplBase3 = MediaControllerImplBase.this.getInstance();
                            Objects.requireNonNull(mediaControllerImplBase3);
                            mediaControllerImplBase$$ExternalSyntheticLambda46 = new MediaControllerImplBase$$ExternalSyntheticLambda46(mediaControllerImplBase3);
                        } else {
                            asInterface.connect(MediaControllerImplBase.this.controllerStub, new ConnectionRequest(MediaControllerImplBase.this.getContext().getPackageName(), Process.myPid(), this.connectionHints, MediaControllerImplBase.this.instance.getMaxCommandsForMediaItems()).toBundle());
                            return;
                        }
                    }
                } catch (RemoteException unused) {
                    Log.w(MediaControllerImplBase.TAG, "Service " + componentName + " has died prematurely");
                    mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
                    MediaController mediaControllerImplBase4 = MediaControllerImplBase.this.getInstance();
                    Objects.requireNonNull(mediaControllerImplBase4);
                    mediaControllerImplBase$$ExternalSyntheticLambda46 = new MediaControllerImplBase$$ExternalSyntheticLambda46(mediaControllerImplBase4);
                }
                mediaControllerImplBase.runOnApplicationLooper(mediaControllerImplBase$$ExternalSyntheticLambda46);
            } catch (Throwable th) {
                MediaController mediaControllerImplBase5 = MediaControllerImplBase.this.getInstance();
                MediaController mediaControllerImplBase6 = MediaControllerImplBase.this.getInstance();
                Objects.requireNonNull(mediaControllerImplBase6);
                mediaControllerImplBase5.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(mediaControllerImplBase6));
                throw th;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MediaController mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
            MediaController mediaControllerImplBase2 = MediaControllerImplBase.this.getInstance();
            Objects.requireNonNull(mediaControllerImplBase2);
            mediaControllerImplBase.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(mediaControllerImplBase2));
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            MediaController mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
            MediaController mediaControllerImplBase2 = MediaControllerImplBase.this.getInstance();
            Objects.requireNonNull(mediaControllerImplBase2);
            mediaControllerImplBase.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(mediaControllerImplBase2));
        }
    }

    /* loaded from: classes.dex */
    public class SurfaceCallback implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener {
        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        private SurfaceCallback() {
        }

        /* synthetic */ SurfaceCallback(MediaControllerImplBase mediaControllerImplBase, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (MediaControllerImplBase.this.videoSurfaceHolder != surfaceHolder) {
                return;
            }
            MediaControllerImplBase.this.videoSurface = surfaceHolder.getSurface();
            MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda0
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.SurfaceCallback.this.m408x74a7f40e(iMediaSession, i);
                }
            });
            Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
            MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
        }

        /* renamed from: lambda$surfaceCreated$0$androidx-media3-session-MediaControllerImplBase$SurfaceCallback */
        public /* synthetic */ void m408x74a7f40e(IMediaSession iMediaSession, int i) throws RemoteException {
            iMediaSession.setVideoSurface(MediaControllerImplBase.this.controllerStub, i, MediaControllerImplBase.this.videoSurface);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (MediaControllerImplBase.this.videoSurfaceHolder != surfaceHolder) {
                return;
            }
            MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(i2, i3);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (MediaControllerImplBase.this.videoSurfaceHolder != surfaceHolder) {
                return;
            }
            MediaControllerImplBase.this.videoSurface = null;
            MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda1
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) {
                    MediaControllerImplBase.SurfaceCallback.this.m409x3f436f00(iMediaSession, i);
                }
            });
            MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(0, 0);
        }

        /* renamed from: lambda$surfaceDestroyed$1$androidx-media3-session-MediaControllerImplBase$SurfaceCallback */
        public /* synthetic */ void m409x3f436f00(IMediaSession iMediaSession, int i) throws RemoteException {
            iMediaSession.setVideoSurface(MediaControllerImplBase.this.controllerStub, i, null);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            if (MediaControllerImplBase.this.videoTextureView == null || MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() != surfaceTexture) {
                return;
            }
            MediaControllerImplBase.this.videoSurface = new Surface(surfaceTexture);
            MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda2
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i3) {
                    MediaControllerImplBase.SurfaceCallback.this.m406x47b74597(iMediaSession, i3);
                }
            });
            MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(i, i2);
        }

        /* renamed from: lambda$onSurfaceTextureAvailable$2$androidx-media3-session-MediaControllerImplBase$SurfaceCallback */
        public /* synthetic */ void m406x47b74597(IMediaSession iMediaSession, int i) throws RemoteException {
            iMediaSession.setVideoSurface(MediaControllerImplBase.this.controllerStub, i, MediaControllerImplBase.this.videoSurface);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            if (MediaControllerImplBase.this.videoTextureView == null || MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() != surfaceTexture) {
                return;
            }
            MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(i, i2);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (MediaControllerImplBase.this.videoTextureView != null && MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() == surfaceTexture) {
                MediaControllerImplBase.this.videoSurface = null;
                MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda3
                    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                    public final void run(IMediaSession iMediaSession, int i) {
                        MediaControllerImplBase.SurfaceCallback.this.m407x1e8b4e8(iMediaSession, i);
                    }
                });
                MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(0, 0);
            }
            return true;
        }

        /* renamed from: lambda$onSurfaceTextureDestroyed$3$androidx-media3-session-MediaControllerImplBase$SurfaceCallback */
        public /* synthetic */ void m407x1e8b4e8(IMediaSession iMediaSession, int i) throws RemoteException {
            iMediaSession.setVideoSurface(MediaControllerImplBase.this.controllerStub, i, null);
        }
    }

    /* loaded from: classes.dex */
    public class FlushCommandQueueHandler {
        private static final int MSG_FLUSH_COMMAND_QUEUE = 1;
        private final Handler handler;

        public FlushCommandQueueHandler(Looper looper) {
            this.handler = new Handler(looper, new Handler.Callback() { // from class: androidx.media3.session.MediaControllerImplBase$FlushCommandQueueHandler$$ExternalSyntheticLambda0
                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    boolean handleMessage;
                    handleMessage = MediaControllerImplBase.FlushCommandQueueHandler.this.handleMessage(message);
                    return handleMessage;
                }
            });
        }

        public void sendFlushCommandQueueMessage() {
            if (MediaControllerImplBase.this.iSession == null || this.handler.hasMessages(1)) {
                return;
            }
            this.handler.sendEmptyMessage(1);
        }

        public void release() {
            if (this.handler.hasMessages(1)) {
                flushCommandQueue();
            }
            this.handler.removeCallbacksAndMessages(null);
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                flushCommandQueue();
            }
            return true;
        }

        private void flushCommandQueue() {
            try {
                MediaControllerImplBase.this.iSession.flushCommandQueue(MediaControllerImplBase.this.controllerStub);
            } catch (RemoteException unused) {
                Log.w(MediaControllerImplBase.TAG, "Error in sending flushCommandQueue");
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class PeriodInfo {
        private final int index;
        private final long periodPositionUs;

        static /* synthetic */ int access$100(PeriodInfo periodInfo) {
            return periodInfo.index;
        }

        static /* synthetic */ long access$200(PeriodInfo periodInfo) {
            return periodInfo.periodPositionUs;
        }

        public PeriodInfo(int i, long j) {
            this.index = i;
            this.periodPositionUs = j;
        }
    }
}
