package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Surface;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.ConnectedControllersManager;
import androidx.media3.session.IMediaSession;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* loaded from: classes.dex */
public final class MediaSessionStub extends IMediaSession.Stub {
    private static final String TAG = "MediaSessionStub";
    public static final int UNKNOWN_SEQUENCE_NUMBER = Integer.MIN_VALUE;
    public static final int VERSION_INT = 5;
    private final ConnectedControllersManager<IBinder> connectedControllersManager;
    private int nextUniqueTrackGroupIdPrefix;
    private final WeakReference<MediaSessionImpl> sessionImpl;
    private final Set<MediaSession.ControllerInfo> pendingControllers = Collections.synchronizedSet(new HashSet());
    private ImmutableBiMap<TrackGroup, String> trackGroupIdMap = ImmutableBiMap.of();

    /* loaded from: classes.dex */
    public interface ControllerPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo);
    }

    /* loaded from: classes.dex */
    public interface MediaItemPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List<MediaItem> list);
    }

    /* loaded from: classes.dex */
    public interface MediaItemsWithStartPositionPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition);
    }

    /* loaded from: classes.dex */
    public interface SessionTask<T, K extends MediaSessionImpl> {
        T run(K k, MediaSession.ControllerInfo controllerInfo, int i);
    }

    public MediaSessionStub(MediaSessionImpl mediaSessionImpl) {
        this.sessionImpl = new WeakReference<>(mediaSessionImpl);
        this.connectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
    }

    public ConnectedControllersManager<IBinder> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    private static void sendSessionResult(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i, SessionResult sessionResult) {
        try {
            ((MediaSession.ControllerCb) Assertions.checkStateNotNull(controllerInfo.getControllerCb())).onSessionResult(i, sessionResult);
            mediaSessionImpl.triggerPlayerInfoUpdate();
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to send result to controller " + controllerInfo, e);
        }
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultSuccess(final Consumer<PlayerWrapper> consumer) {
        return sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda68
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                Consumer.this.accept(playerWrapper);
            }
        });
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultSuccess(final ControllerPlayerTask controllerPlayerTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda84
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                return MediaSessionStub.lambda$sendSessionResultSuccess$1(MediaSessionStub.ControllerPlayerTask.this, mediaSessionImpl, controllerInfo, i);
            }
        };
    }

    public static /* synthetic */ ListenableFuture lambda$sendSessionResultSuccess$1(ControllerPlayerTask controllerPlayerTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        if (mediaSessionImpl.isReleased()) {
            return Futures.immediateVoidFuture();
        }
        controllerPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), controllerInfo);
        sendSessionResult(mediaSessionImpl, controllerInfo, i, new SessionResult(0));
        return Futures.immediateVoidFuture();
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultWhenReady(final SessionTask<ListenableFuture<SessionResult>, K> sessionTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda70
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                ListenableFuture handleSessionTaskWhenReady;
                handleSessionTaskWhenReady = MediaSessionStub.handleSessionTaskWhenReady(mediaSessionImpl, controllerInfo, i, MediaSessionStub.SessionTask.this, new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda76
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaSessionStub.lambda$sendSessionResultWhenReady$2(MediaSessionImpl.this, controllerInfo, i, (ListenableFuture) obj);
                    }
                });
                return handleSessionTaskWhenReady;
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void lambda$sendSessionResultWhenReady$2(androidx.media3.session.MediaSessionImpl r2, androidx.media3.session.MediaSession.ControllerInfo r3, int r4, com.google.common.util.concurrent.ListenableFuture r5) {
        /*
            java.lang.String r0 = "MediaSessionStub"
            java.lang.Object r5 = r5.get()     // Catch: java.lang.InterruptedException -> L11 java.util.concurrent.ExecutionException -> L13 java.util.concurrent.CancellationException -> L2b
            androidx.media3.session.SessionResult r5 = (androidx.media3.session.SessionResult) r5     // Catch: java.lang.InterruptedException -> L11 java.util.concurrent.ExecutionException -> L13 java.util.concurrent.CancellationException -> L2b
            java.lang.String r1 = "SessionResult must not be null"
            java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r5, r1)     // Catch: java.lang.InterruptedException -> L11 java.util.concurrent.ExecutionException -> L13 java.util.concurrent.CancellationException -> L2b
            androidx.media3.session.SessionResult r5 = (androidx.media3.session.SessionResult) r5     // Catch: java.lang.InterruptedException -> L11 java.util.concurrent.ExecutionException -> L13 java.util.concurrent.CancellationException -> L2b
            goto L37
        L11:
            r5 = move-exception
            goto L14
        L13:
            r5 = move-exception
        L14:
            java.lang.String r1 = "Session operation failed"
            androidx.media3.common.util.Log.w(r0, r1, r5)
            androidx.media3.session.SessionResult r0 = new androidx.media3.session.SessionResult
            java.lang.Throwable r5 = r5.getCause()
            boolean r5 = r5 instanceof java.lang.UnsupportedOperationException
            if (r5 == 0) goto L25
            r5 = -6
            goto L26
        L25:
            r5 = -1
        L26:
            r0.<init>(r5)
            r5 = r0
            goto L37
        L2b:
            r5 = move-exception
            java.lang.String r1 = "Session operation cancelled"
            androidx.media3.common.util.Log.w(r0, r1, r5)
            androidx.media3.session.SessionResult r5 = new androidx.media3.session.SessionResult
            r0 = 1
            r5.<init>(r0)
        L37:
            sendSessionResult(r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaSessionStub.lambda$sendSessionResultWhenReady$2(androidx.media3.session.MediaSessionImpl, androidx.media3.session.MediaSession$ControllerInfo, int, com.google.common.util.concurrent.ListenableFuture):void");
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<SessionResult>, K> handleMediaItemsWhenReady(final SessionTask<ListenableFuture<List<MediaItem>>, K> sessionTask, final MediaItemPlayerTask mediaItemPlayerTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda80
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                return MediaSessionStub.lambda$handleMediaItemsWhenReady$6(MediaSessionStub.SessionTask.this, mediaItemPlayerTask, mediaSessionImpl, controllerInfo, i);
            }
        };
    }

    public static /* synthetic */ ListenableFuture lambda$handleMediaItemsWhenReady$6(SessionTask sessionTask, final MediaItemPlayerTask mediaItemPlayerTask, final MediaSessionImpl mediaSessionImpl, final MediaSession.ControllerInfo controllerInfo, int i) {
        if (mediaSessionImpl.isReleased()) {
            return Futures.immediateFuture(new SessionResult(-100));
        }
        return Util.transformFutureAsync((ListenableFuture) sessionTask.run(mediaSessionImpl, controllerInfo, i), new AsyncFunction() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda23
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                ListenableFuture postOrRunWithCompletion;
                postOrRunWithCompletion = Util.postOrRunWithCompletion(r0.getApplicationHandler(), r0.callWithControllerForCurrentRequestSet(r1, new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda78
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSessionStub.lambda$handleMediaItemsWhenReady$4(MediaSessionImpl.this, r2, r3, r4);
                    }
                }), new SessionResult(0));
                return postOrRunWithCompletion;
            }
        });
    }

    public static /* synthetic */ void lambda$handleMediaItemsWhenReady$4(MediaSessionImpl mediaSessionImpl, MediaItemPlayerTask mediaItemPlayerTask, MediaSession.ControllerInfo controllerInfo, List list) {
        if (mediaSessionImpl.isReleased()) {
            return;
        }
        mediaItemPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), controllerInfo, list);
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<SessionResult>, K> handleMediaItemsWithStartPositionWhenReady(final SessionTask<ListenableFuture<MediaSession.MediaItemsWithStartPosition>, K> sessionTask, final MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda63
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                return MediaSessionStub.lambda$handleMediaItemsWithStartPositionWhenReady$9(MediaSessionStub.SessionTask.this, mediaItemsWithStartPositionPlayerTask, mediaSessionImpl, controllerInfo, i);
            }
        };
    }

    public static /* synthetic */ ListenableFuture lambda$handleMediaItemsWithStartPositionWhenReady$9(SessionTask sessionTask, final MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, final MediaSessionImpl mediaSessionImpl, final MediaSession.ControllerInfo controllerInfo, int i) {
        if (mediaSessionImpl.isReleased()) {
            return Futures.immediateFuture(new SessionResult(-100));
        }
        return Util.transformFutureAsync((ListenableFuture) sessionTask.run(mediaSessionImpl, controllerInfo, i), new AsyncFunction() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda12
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                ListenableFuture postOrRunWithCompletion;
                postOrRunWithCompletion = Util.postOrRunWithCompletion(r0.getApplicationHandler(), r0.callWithControllerForCurrentRequestSet(controllerInfo, new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda39
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSessionStub.lambda$handleMediaItemsWithStartPositionWhenReady$7(MediaSessionImpl.this, r2, r3);
                    }
                }), new SessionResult(0));
                return postOrRunWithCompletion;
            }
        });
    }

    public static /* synthetic */ void lambda$handleMediaItemsWithStartPositionWhenReady$7(MediaSessionImpl mediaSessionImpl, MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
        if (mediaSessionImpl.isReleased()) {
            return;
        }
        mediaItemsWithStartPositionPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), mediaItemsWithStartPosition);
    }

    private static void sendLibraryResult(MediaSession.ControllerInfo controllerInfo, int i, LibraryResult<?> libraryResult) {
        try {
            ((MediaSession.ControllerCb) Assertions.checkStateNotNull(controllerInfo.getControllerCb())).onLibraryResult(i, libraryResult);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to send result to browser " + controllerInfo, e);
        }
    }

    private static <V, K extends MediaLibrarySessionImpl> SessionTask<ListenableFuture<Void>, K> sendLibraryResultWhenReady(final SessionTask<ListenableFuture<LibraryResult<V>>, K> sessionTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda69
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                ListenableFuture handleSessionTaskWhenReady;
                handleSessionTaskWhenReady = MediaSessionStub.handleSessionTaskWhenReady((MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i, MediaSessionStub.SessionTask.this, new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda11
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaSessionStub.lambda$sendLibraryResultWhenReady$10(MediaSession.ControllerInfo.this, i, (ListenableFuture) obj);
                    }
                });
                return handleSessionTaskWhenReady;
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void lambda$sendLibraryResultWhenReady$10(MediaSession.ControllerInfo controllerInfo, int i, ListenableFuture listenableFuture) {
        LibraryResult ofError;
        try {
            ofError = (LibraryResult) Assertions.checkNotNull((LibraryResult) listenableFuture.get(), "LibraryResult must not be null");
        } catch (InterruptedException e) {
            e = e;
            Log.w(TAG, "Library operation failed", e);
            ofError = LibraryResult.ofError(-1);
        } catch (CancellationException e2) {
            Log.w(TAG, "Library operation cancelled", e2);
            ofError = LibraryResult.ofError(1);
        } catch (ExecutionException e3) {
            e = e3;
            Log.w(TAG, "Library operation failed", e);
            ofError = LibraryResult.ofError(-1);
        }
        sendLibraryResult(controllerInfo, i, ofError);
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommand(IMediaController iMediaController, int i, int i2, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
        if (controller != null) {
            queueSessionTaskWithPlayerCommandForControllerInfo(controller, i, i2, sessionTask);
        }
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommandForControllerInfo(final MediaSession.ControllerInfo controllerInfo, final int i, final int i2, final SessionTask<ListenableFuture<Void>, K> sessionTask) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda51
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSessionStub.this.m511x89a6a664(controllerInfo, i2, mediaSessionImpl, i, sessionTask);
                    }
                });
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    /* renamed from: lambda$queueSessionTaskWithPlayerCommandForControllerInfo$14$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m511x89a6a664(final MediaSession.ControllerInfo controllerInfo, int i, final MediaSessionImpl mediaSessionImpl, final int i2, final SessionTask sessionTask) {
        if (!this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, i)) {
            sendSessionResult(mediaSessionImpl, controllerInfo, i2, new SessionResult(-4));
            return;
        }
        int onPlayerCommandRequestOnHandler = mediaSessionImpl.onPlayerCommandRequestOnHandler(controllerInfo, i);
        if (onPlayerCommandRequestOnHandler != 0) {
            sendSessionResult(mediaSessionImpl, controllerInfo, i2, new SessionResult(onPlayerCommandRequestOnHandler));
        } else if (i == 27) {
            mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda17
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionStub.SessionTask.this.run(mediaSessionImpl, controllerInfo, i2);
                }
            }).run();
            this.connectedControllersManager.addToCommandQueue(controllerInfo, i, new ConnectedControllersManager.AsyncCommand() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda18
                @Override // androidx.media3.session.ConnectedControllersManager.AsyncCommand
                public final ListenableFuture run() {
                    ListenableFuture immediateVoidFuture;
                    immediateVoidFuture = Futures.immediateVoidFuture();
                    return immediateVoidFuture;
                }
            });
        } else {
            this.connectedControllersManager.addToCommandQueue(controllerInfo, i, new ConnectedControllersManager.AsyncCommand() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda19
                @Override // androidx.media3.session.ConnectedControllersManager.AsyncCommand
                public final ListenableFuture run() {
                    return MediaSessionStub.lambda$queueSessionTaskWithPlayerCommandForControllerInfo$13(MediaSessionStub.SessionTask.this, mediaSessionImpl, controllerInfo, i2);
                }
            });
        }
    }

    public static /* synthetic */ ListenableFuture lambda$queueSessionTaskWithPlayerCommandForControllerInfo$13(SessionTask sessionTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return (ListenableFuture) sessionTask.run(mediaSessionImpl, controllerInfo, i);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, int i, int i2, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        dispatchSessionTaskWithSessionCommand(iMediaController, i, null, i2, sessionTask);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, int i, SessionCommand sessionCommand, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        dispatchSessionTaskWithSessionCommand(iMediaController, i, sessionCommand, 0, sessionTask);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, final int i, final SessionCommand sessionCommand, final int i2, final SessionTask<ListenableFuture<Void>, K> sessionTask) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                final MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                if (controller == null) {
                    return;
                }
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda49
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSessionStub.this.m508xc8132c6c(controller, sessionCommand, mediaSessionImpl, i, i2, sessionTask);
                    }
                });
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    /* renamed from: lambda$dispatchSessionTaskWithSessionCommand$15$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m508xc8132c6c(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, MediaSessionImpl mediaSessionImpl, int i, int i2, SessionTask sessionTask) {
        if (this.connectedControllersManager.isConnected(controllerInfo)) {
            if (sessionCommand != null) {
                if (!this.connectedControllersManager.isSessionCommandAvailable(controllerInfo, sessionCommand)) {
                    sendSessionResult(mediaSessionImpl, controllerInfo, i, new SessionResult(-4));
                    return;
                }
            } else if (!this.connectedControllersManager.isSessionCommandAvailable(controllerInfo, i2)) {
                sendSessionResult(mediaSessionImpl, controllerInfo, i, new SessionResult(-4));
                return;
            }
            sessionTask.run(mediaSessionImpl, controllerInfo, i);
        }
    }

    public static <T, K extends MediaSessionImpl> ListenableFuture<Void> handleSessionTaskWhenReady(final K k, MediaSession.ControllerInfo controllerInfo, int i, SessionTask<ListenableFuture<T>, K> sessionTask, final Consumer<ListenableFuture<T>> consumer) {
        if (k.isReleased()) {
            return Futures.immediateVoidFuture();
        }
        final ListenableFuture<T> run = sessionTask.run(k, controllerInfo, i);
        final SettableFuture create = SettableFuture.create();
        run.addListener(new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                MediaSessionStub.lambda$handleSessionTaskWhenReady$16(MediaSessionImpl.this, create, consumer, run);
            }
        }, MoreExecutors.directExecutor());
        return create;
    }

    public static /* synthetic */ void lambda$handleSessionTaskWhenReady$16(MediaSessionImpl mediaSessionImpl, SettableFuture settableFuture, Consumer consumer, ListenableFuture listenableFuture) {
        if (mediaSessionImpl.isReleased()) {
            settableFuture.set(null);
            return;
        }
        try {
            consumer.accept(listenableFuture);
            settableFuture.set(null);
        } catch (Throwable th) {
            settableFuture.setException(th);
        }
    }

    private int maybeCorrectMediaItemIndex(MediaSession.ControllerInfo controllerInfo, PlayerWrapper playerWrapper, int i) {
        return (playerWrapper.isCommandAvailable(17) && !this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 17) && this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 16)) ? i + playerWrapper.getCurrentMediaItemIndex() : i;
    }

    public void connect(final IMediaController iMediaController, final MediaSession.ControllerInfo controllerInfo) {
        if (iMediaController == null || controllerInfo == null) {
            SessionUtil.disconnectIMediaController(iMediaController);
            return;
        }
        final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (mediaSessionImpl == null || mediaSessionImpl.isReleased()) {
            SessionUtil.disconnectIMediaController(iMediaController);
        } else {
            this.pendingControllers.add(controllerInfo);
            Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda44
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionStub.this.m507lambda$connect$17$androidxmedia3sessionMediaSessionStub(controllerInfo, mediaSessionImpl, iMediaController);
                }
            });
        }
    }

    /* renamed from: lambda$connect$17$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m507lambda$connect$17$androidxmedia3sessionMediaSessionStub(MediaSession.ControllerInfo controllerInfo, MediaSessionImpl mediaSessionImpl, IMediaController iMediaController) {
        IMediaController iMediaController2;
        Player.Commands commands;
        boolean z = false;
        try {
            this.pendingControllers.remove(controllerInfo);
            if (mediaSessionImpl.isReleased()) {
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            IBinder callbackBinder = ((Controller2Cb) Assertions.checkStateNotNull((Controller2Cb) controllerInfo.getControllerCb())).getCallbackBinder();
            MediaSession.ConnectionResult onConnectOnHandler = mediaSessionImpl.onConnectOnHandler(controllerInfo);
            if (!onConnectOnHandler.isAccepted && !controllerInfo.isTrusted()) {
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            if (!onConnectOnHandler.isAccepted) {
                onConnectOnHandler = MediaSession.ConnectionResult.accept(SessionCommands.EMPTY, Player.Commands.EMPTY);
            }
            if (this.connectedControllersManager.isConnected(controllerInfo)) {
                Log.w(TAG, "Controller " + controllerInfo + " has sent connection request multiple times");
            }
            this.connectedControllersManager.addController(callbackBinder, controllerInfo, onConnectOnHandler.availableSessionCommands, onConnectOnHandler.availablePlayerCommands);
            SequencedFutureManager sequencedFutureManager = this.connectedControllersManager.getSequencedFutureManager(controllerInfo);
            if (sequencedFutureManager == null) {
                Log.w(TAG, "Ignoring connection request from unknown controller info");
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            PlayerWrapper playerWrapper = mediaSessionImpl.getPlayerWrapper();
            PlayerInfo playerInfo = mediaSessionImpl.getPlayerInfo();
            PlaybackException playbackException = mediaSessionImpl.getPlaybackException();
            if (playbackException == null) {
                commands = onConnectOnHandler.availablePlayerCommands;
            } else {
                this.connectedControllersManager.setPlaybackException(controllerInfo, playbackException, onConnectOnHandler.availablePlayerCommands);
                playerInfo = MediaSessionImpl.createPlayerInfoForCustomPlaybackException(playerInfo, playbackException);
                commands = (Player.Commands) Assertions.checkNotNull(MediaSessionImpl.createPlayerCommandsForCustomErrorState(onConnectOnHandler.availablePlayerCommands));
            }
            iMediaController2 = iMediaController;
            try {
                ConnectionState connectionState = new ConnectionState(MediaLibraryInfo.VERSION_INT, 5, this, onConnectOnHandler.sessionActivity != null ? onConnectOnHandler.sessionActivity : mediaSessionImpl.getSessionActivity(), onConnectOnHandler.customLayout != null ? onConnectOnHandler.customLayout : mediaSessionImpl.getCustomLayout(), onConnectOnHandler.mediaButtonPreferences != null ? onConnectOnHandler.mediaButtonPreferences : mediaSessionImpl.getMediaButtonPreferences(), mediaSessionImpl.getCommandButtonsForMediaItems(), onConnectOnHandler.availableSessionCommands, commands, playerWrapper.getAvailableCommands(), mediaSessionImpl.getToken().getExtras(), onConnectOnHandler.sessionExtras != null ? onConnectOnHandler.sessionExtras : mediaSessionImpl.getSessionExtras(), generateAndCacheUniqueTrackGroupIds(playerInfo), mediaSessionImpl.getPlatformToken());
                if (mediaSessionImpl.isReleased()) {
                    SessionUtil.disconnectIMediaController(iMediaController2);
                    return;
                }
                try {
                    iMediaController2.onConnected(sequencedFutureManager.obtainNextSequenceNumber(), iMediaController2 instanceof MediaControllerStub ? connectionState.toBundleInProcess() : connectionState.toBundleForRemoteProcess(controllerInfo.getInterfaceVersion()));
                    z = true;
                } catch (RemoteException unused) {
                }
                if (z) {
                    mediaSessionImpl.onPostConnectOnHandler(controllerInfo);
                }
                if (z) {
                    return;
                }
                SessionUtil.disconnectIMediaController(iMediaController2);
            } catch (Throwable th) {
                th = th;
                if (!z) {
                    SessionUtil.disconnectIMediaController(iMediaController2);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            iMediaController2 = iMediaController;
        }
    }

    public void release() {
        for (MediaSession.ControllerInfo controllerInfo : this.connectedControllersManager.getConnectedControllers()) {
            this.connectedControllersManager.removeController(controllerInfo);
            MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
            if (controllerCb != null) {
                controllerCb.onDisconnected(0);
            }
        }
        Iterator<MediaSession.ControllerInfo> it = this.pendingControllers.iterator();
        while (it.hasNext()) {
            MediaSession.ControllerCb controllerCb2 = it.next().getControllerCb();
            if (controllerCb2 != null) {
                controllerCb2.onDisconnected(0);
            }
        }
        this.pendingControllers.clear();
        this.sessionImpl.clear();
    }

    @Override // androidx.media3.session.IMediaSession
    public void connect(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            ConnectionRequest fromBundle = ConnectionRequest.fromBundle(bundle);
            int callingUid = Binder.getCallingUid();
            int callingPid = Binder.getCallingPid();
            long clearCallingIdentity = Binder.clearCallingIdentity();
            if (callingPid == 0) {
                callingPid = fromBundle.pid;
            }
            try {
                MediaSessionManager.RemoteUserInfo remoteUserInfo = new MediaSessionManager.RemoteUserInfo(fromBundle.packageName, callingPid, callingUid);
                MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
                connect(iMediaController, new MediaSession.ControllerInfo(remoteUserInfo, fromBundle.libraryVersion, fromBundle.controllerInterfaceVersion, mediaSessionImpl != null && MediaSessionManager.getSessionManager(mediaSessionImpl.getContext()).isTrustedForMediaControl(remoteUserInfo), new Controller2Cb(iMediaController, fromBundle.controllerInterfaceVersion), fromBundle.connectionHints, fromBundle.maxCommandsForMediaItems));
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for ConnectionRequest", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void stop(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        stopForControllerInfo(controller, i);
    }

    public void stopForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 3, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda62
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).stop();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void release(final IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSessionStub.this.m512lambda$release$18$androidxmedia3sessionMediaSessionStub(iMediaController);
                    }
                });
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    /* renamed from: lambda$release$18$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m512lambda$release$18$androidxmedia3sessionMediaSessionStub(IMediaController iMediaController) {
        this.connectedControllersManager.removeController((ConnectedControllersManager<IBinder>) iMediaController.asBinder());
    }

    @Override // androidx.media3.session.IMediaSession
    public void onControllerResult(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            SessionResult fromBundle = SessionResult.fromBundle(bundle);
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                SequencedFutureManager sequencedFutureManager = this.connectedControllersManager.getSequencedFutureManager((ConnectedControllersManager<IBinder>) iMediaController.asBinder());
                if (sequencedFutureManager == null) {
                    return;
                }
                sequencedFutureManager.setFutureResult(i, fromBundle);
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionResult", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void play(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        playForControllerInfo(controller, i);
    }

    public void playForControllerInfo(final MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 1, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda37
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                MediaSessionStub.this.m510x862bd0ab(controllerInfo, (PlayerWrapper) obj);
            }
        }));
    }

    /* renamed from: lambda$playForControllerInfo$19$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m510x862bd0ab(MediaSession.ControllerInfo controllerInfo, PlayerWrapper playerWrapper) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (mediaSessionImpl == null || mediaSessionImpl.isReleased()) {
            return;
        }
        mediaSessionImpl.handleMediaControllerPlayRequest(controllerInfo, false);
    }

    @Override // androidx.media3.session.IMediaSession
    public void pause(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        pauseForControllerInfo(controller, i);
    }

    public void pauseForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 1, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda15
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).pause();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void prepare(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 2, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda67
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).prepare();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToDefaultPosition(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 4, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda66
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToDefaultPosition();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 10, sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda9
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                MediaSessionStub.this.m517x6c0a2b2d(i2, playerWrapper, controllerInfo);
            }
        }));
    }

    /* renamed from: lambda$seekToDefaultPositionWithMediaItemIndex$21$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m517x6c0a2b2d(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.seekToDefaultPosition(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekTo(IMediaController iMediaController, int i, final long j) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 5, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda81
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekTo(j);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToWithMediaItemIndex(IMediaController iMediaController, int i, final int i2, final long j) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 10, sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda14
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                MediaSessionStub.this.m518x442a0fa1(i2, j, playerWrapper, controllerInfo);
            }
        }));
    }

    /* renamed from: lambda$seekToWithMediaItemIndex$23$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m518x442a0fa1(int i, long j, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.seekTo(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), j);
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekBack(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekBackForControllerInfo(controller, i);
    }

    public void seekBackForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 11, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda28
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekBack();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekForward(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekForwardForControllerInfo(controller, i);
    }

    public void seekForwardForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 12, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda46
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekForward();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void onCustomCommand(IMediaController iMediaController, int i, Bundle bundle, final Bundle bundle2) {
        if (iMediaController == null || bundle == null || bundle2 == null) {
            return;
        }
        try {
            final SessionCommand fromBundle = SessionCommand.fromBundle(bundle);
            dispatchSessionTaskWithSessionCommand(iMediaController, i, fromBundle, sendSessionResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda30
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onCustomCommandOnHandler;
                    onCustomCommandOnHandler = mediaSessionImpl.onCustomCommandOnHandler(controllerInfo, SessionCommand.this, bundle2);
                    return onCustomCommandOnHandler;
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionCommand", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRatingWithMediaId(IMediaController iMediaController, int i, final String str, Bundle bundle) {
        if (iMediaController == null || str == null || bundle == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "setRatingWithMediaId(): Ignoring empty mediaId");
            return;
        }
        try {
            final Rating fromBundle = Rating.fromBundle(bundle);
            dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda48
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onSetRatingOnHandler;
                    onSetRatingOnHandler = mediaSessionImpl.onSetRatingOnHandler(controllerInfo, str, fromBundle);
                    return onSetRatingOnHandler;
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for Rating", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRating(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final Rating fromBundle = Rating.fromBundle(bundle);
            dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda3
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onSetRatingOnHandler;
                    onSetRatingOnHandler = mediaSessionImpl.onSetRatingOnHandler(controllerInfo, Rating.this);
                    return onSetRatingOnHandler;
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for Rating", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaybackSpeed(IMediaController iMediaController, int i, final float f) {
        if (iMediaController == null || f <= 0.0f) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 13, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda13
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setPlaybackSpeed(f);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaybackParameters(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final PlaybackParameters fromBundle = PlaybackParameters.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 13, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda31
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    ((PlayerWrapper) obj).setPlaybackParameters(PlaybackParameters.this);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for PlaybackParameters", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItem(IMediaController iMediaController, int i, Bundle bundle) {
        setMediaItemWithResetPosition(iMediaController, i, bundle, true);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemWithStartPosition(IMediaController iMediaController, int i, Bundle bundle, final long j) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final MediaItem fromBundle = MediaItem.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda73
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onSetMediaItemsOnHandler;
                    onSetMediaItemsOnHandler = mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, ImmutableList.of(MediaItem.this), 0, j);
                    return onSetMediaItemsOnHandler;
                }
            }, new MediaSessionStub$$ExternalSyntheticLambda2())));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemWithResetPosition(IMediaController iMediaController, int i, Bundle bundle, final boolean z) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final MediaItem fromBundle = MediaItem.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda32
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onSetMediaItemsOnHandler;
                    MediaItem mediaItem = MediaItem.this;
                    boolean z2 = z;
                    onSetMediaItemsOnHandler = mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, ImmutableList.of(mediaItem), r7 ? -1 : mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex(), r7 ? C.TIME_UNSET : mediaSessionImpl.getPlayerWrapper().getCurrentPosition());
                    return onSetMediaItemsOnHandler;
                }
            }, new MediaSessionStub$$ExternalSyntheticLambda2())));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItems(IMediaController iMediaController, int i, IBinder iBinder) {
        setMediaItemsWithResetPosition(iMediaController, i, iBinder, true);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemsWithResetPosition(IMediaController iMediaController, int i, IBinder iBinder, final boolean z) {
        if (iMediaController == null || iBinder == null) {
            return;
        }
        try {
            final ImmutableList fromBundleList = BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda85(), BundleListRetriever.getList(iBinder));
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda1
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onSetMediaItemsOnHandler;
                    List list = fromBundleList;
                    boolean z2 = z;
                    onSetMediaItemsOnHandler = mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, list, r7 ? -1 : mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex(), r7 ? C.TIME_UNSET : mediaSessionImpl.getPlayerWrapper().getCurrentPosition());
                    return onSetMediaItemsOnHandler;
                }
            }, new MediaSessionStub$$ExternalSyntheticLambda2())));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemsWithStartIndex(IMediaController iMediaController, int i, IBinder iBinder, final int i2, final long j) {
        if (iMediaController == null || iBinder == null) {
            return;
        }
        if (i2 == -1 || i2 >= 0) {
            try {
                final ImmutableList fromBundleList = BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda85(), BundleListRetriever.getList(iBinder));
                queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda43
                    @Override // androidx.media3.session.MediaSessionStub.SessionTask
                    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i3) {
                        return MediaSessionStub.lambda$setMediaItemsWithStartIndex$32(fromBundleList, i2, j, mediaSessionImpl, controllerInfo, i3);
                    }
                }, new MediaSessionStub$$ExternalSyntheticLambda2())));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    public static /* synthetic */ ListenableFuture lambda$setMediaItemsWithStartIndex$32(List list, int i, long j, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
        int currentMediaItemIndex = i == -1 ? mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex() : i;
        if (i == -1) {
            j = mediaSessionImpl.getPlayerWrapper().getCurrentPosition();
        }
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, list, currentMediaItemIndex, j);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaylistMetadata(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final MediaMetadata fromBundle = MediaMetadata.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 19, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda72
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    ((PlayerWrapper) obj).setPlaylistMetadata(MediaMetadata.this);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaMetadata", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItem(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final MediaItem fromBundle = MediaItem.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda57
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onAddMediaItemsOnHandler;
                    onAddMediaItemsOnHandler = mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.of(MediaItem.this));
                    return onAddMediaItemsOnHandler;
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda58
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    playerWrapper.addMediaItems(list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItemWithIndex(IMediaController iMediaController, int i, final int i2, Bundle bundle) {
        if (iMediaController == null || bundle == null || i2 < 0) {
            return;
        }
        try {
            final MediaItem fromBundle = MediaItem.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda20
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i3) {
                    ListenableFuture onAddMediaItemsOnHandler;
                    onAddMediaItemsOnHandler = mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.of(MediaItem.this));
                    return onAddMediaItemsOnHandler;
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda21
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    MediaSessionStub.this.m505x9bd7c586(i2, playerWrapper, controllerInfo, list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    /* renamed from: lambda$addMediaItemWithIndex$37$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m505x9bd7c586(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.addMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), list);
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItems(IMediaController iMediaController, int i, IBinder iBinder) {
        if (iMediaController == null || iBinder == null) {
            return;
        }
        try {
            final ImmutableList fromBundleList = BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda85(), BundleListRetriever.getList(iBinder));
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda54
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onAddMediaItemsOnHandler;
                    onAddMediaItemsOnHandler = mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, fromBundleList);
                    return onAddMediaItemsOnHandler;
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda56
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    playerWrapper.addMediaItems(list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItemsWithIndex(IMediaController iMediaController, int i, final int i2, IBinder iBinder) {
        if (iMediaController == null || iBinder == null || i2 < 0) {
            return;
        }
        try {
            final ImmutableList fromBundleList = BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda85(), BundleListRetriever.getList(iBinder));
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda35
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i3) {
                    ListenableFuture onAddMediaItemsOnHandler;
                    onAddMediaItemsOnHandler = mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, fromBundleList);
                    return onAddMediaItemsOnHandler;
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda36
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    MediaSessionStub.this.m506xa0630f40(i2, playerWrapper, controllerInfo, list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    /* renamed from: lambda$addMediaItemsWithIndex$41$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m506xa0630f40(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.addMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), list);
    }

    @Override // androidx.media3.session.IMediaSession
    public void removeMediaItem(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda77
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                MediaSessionStub.this.m513x6cb7e389(i2, playerWrapper, controllerInfo);
            }
        }));
    }

    /* renamed from: lambda$removeMediaItem$42$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m513x6cb7e389(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.removeMediaItem(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i));
    }

    @Override // androidx.media3.session.IMediaSession
    public void removeMediaItems(IMediaController iMediaController, int i, final int i2, final int i3) {
        if (iMediaController == null || i2 < 0 || i3 < i2) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda0
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                MediaSessionStub.this.m514x3037692f(i2, i3, playerWrapper, controllerInfo);
            }
        }));
    }

    /* renamed from: lambda$removeMediaItems$43$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m514x3037692f(int i, int i2, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.removeMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i2));
    }

    @Override // androidx.media3.session.IMediaSession
    public void clearMediaItems(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda82
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).clearMediaItems();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void moveMediaItem(IMediaController iMediaController, int i, final int i2, final int i3) {
        if (iMediaController == null || i2 < 0 || i3 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda75
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).moveMediaItem(i2, i3);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void moveMediaItems(IMediaController iMediaController, int i, final int i2, final int i3, final int i4) {
        if (iMediaController == null || i2 < 0 || i3 < i2 || i4 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda42
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).moveMediaItems(i2, i3, i4);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void replaceMediaItem(IMediaController iMediaController, int i, final int i2, Bundle bundle) {
        if (iMediaController == null || bundle == null || i2 < 0) {
            return;
        }
        try {
            final MediaItem fromBundle = MediaItem.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda26
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i3) {
                    ListenableFuture onAddMediaItemsOnHandler;
                    onAddMediaItemsOnHandler = mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.of(MediaItem.this));
                    return onAddMediaItemsOnHandler;
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda27
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    MediaSessionStub.this.m515x6cbfe40e(i2, playerWrapper, controllerInfo, list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    /* renamed from: lambda$replaceMediaItem$47$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m515x6cbfe40e(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        if (list.size() == 1) {
            playerWrapper.replaceMediaItem(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), (MediaItem) list.get(0));
        } else {
            playerWrapper.replaceMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i + 1), list);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void replaceMediaItems(IMediaController iMediaController, int i, final int i2, final int i3, IBinder iBinder) {
        if (iMediaController == null || iBinder == null || i2 < 0 || i3 < i2) {
            return;
        }
        try {
            final ImmutableList fromBundleList = BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda85(), BundleListRetriever.getList(iBinder));
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda7
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i4) {
                    ListenableFuture onAddMediaItemsOnHandler;
                    onAddMediaItemsOnHandler = mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.this);
                    return onAddMediaItemsOnHandler;
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda8
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    MediaSessionStub.this.m516xe3478e3f(i2, i3, playerWrapper, controllerInfo, list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    /* renamed from: lambda$replaceMediaItems$49$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m516xe3478e3f(int i, int i2, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.replaceMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i2), list);
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToPreviousMediaItem(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 6, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda41
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToPreviousMediaItem();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToNextMediaItem(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 8, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda24
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToNextMediaItem();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToPrevious(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekToPreviousForControllerInfo(controller, i);
    }

    public void seekToPreviousForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 7, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda34
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToPrevious();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToNext(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekToNextForControllerInfo(controller, i);
    }

    public void seekToNextForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 9, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda47
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToNext();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRepeatMode(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null) {
            return;
        }
        if (i2 == 2 || i2 == 0 || i2 == 1) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 15, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda52
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    ((PlayerWrapper) obj).setRepeatMode(i2);
                }
            }));
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setShuffleModeEnabled(IMediaController iMediaController, int i, final boolean z) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 14, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda61
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setShuffleModeEnabled(z);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVideoSurface(IMediaController iMediaController, int i, final Surface surface) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 27, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda64
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setVideoSurface(surface);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVolume(IMediaController iMediaController, int i, final float f) {
        if (iMediaController == null || f < 0.0f || f > 1.0f) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 24, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda60
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setVolume(f);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceVolume(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 25, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda79
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setDeviceVolume(i2);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceVolumeWithFlags(IMediaController iMediaController, int i, final int i2, final int i3) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 33, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda50
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setDeviceVolume(i2, i3);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void increaseDeviceVolume(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 26, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda25
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).increaseDeviceVolume();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 34, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda38
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).increaseDeviceVolume(i2);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void decreaseDeviceVolume(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 26, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda55
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).decreaseDeviceVolume();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 34, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda33
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).decreaseDeviceVolume(i2);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceMuted(IMediaController iMediaController, int i, final boolean z) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 26, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda29
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setDeviceMuted(z);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceMutedWithFlags(IMediaController iMediaController, int i, final boolean z, final int i2) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 34, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda71
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setDeviceMuted(z, i2);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setAudioAttributes(IMediaController iMediaController, int i, Bundle bundle, final boolean z) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final AudioAttributes fromBundle = AudioAttributes.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 35, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda53
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    ((PlayerWrapper) obj).setAudioAttributes(AudioAttributes.this, z);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for AudioAttributes", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlayWhenReady(IMediaController iMediaController, int i, final boolean z) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 1, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda10
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setPlayWhenReady(z);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void flushCommandQueue(IMediaController iMediaController) {
        if (iMediaController == null) {
            return;
        }
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                final MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                if (controller != null) {
                    Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda40
                        @Override // java.lang.Runnable
                        public final void run() {
                            MediaSessionStub.this.m509x787698c0(controller);
                        }
                    });
                }
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    /* renamed from: lambda$flushCommandQueue$64$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m509x787698c0(MediaSession.ControllerInfo controllerInfo) {
        this.connectedControllersManager.flushCommandQueue(controllerInfo);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setTrackSelectionParameters(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final TrackSelectionParameters fromBundle = TrackSelectionParameters.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 29, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda22
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    MediaSessionStub.this.m519x8b51cbc2(fromBundle, (PlayerWrapper) obj);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for TrackSelectionParameters", e);
        }
    }

    /* renamed from: lambda$setTrackSelectionParameters$65$androidx-media3-session-MediaSessionStub */
    public /* synthetic */ void m519x8b51cbc2(TrackSelectionParameters trackSelectionParameters, PlayerWrapper playerWrapper) {
        playerWrapper.setTrackSelectionParameters(updateOverridesUsingUniqueTrackGroupIds(trackSelectionParameters));
    }

    @Override // androidx.media3.session.IMediaSession
    public void getLibraryRoot(IMediaController iMediaController, int i, Bundle bundle) {
        final MediaLibraryService.LibraryParams fromBundle;
        if (iMediaController == null) {
            return;
        }
        if (bundle == null) {
            fromBundle = null;
        } else {
            try {
                fromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, 50000, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda65
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                ListenableFuture onGetLibraryRootOnHandler;
                onGetLibraryRootOnHandler = ((MediaLibrarySessionImpl) mediaSessionImpl).onGetLibraryRootOnHandler(controllerInfo, MediaLibraryService.LibraryParams.this);
                return onGetLibraryRootOnHandler;
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void getItem(IMediaController iMediaController, int i, final String str) {
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getItem(): Ignoring empty mediaId");
        } else {
            dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda45
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onGetItemOnHandler;
                    onGetItemOnHandler = ((MediaLibrarySessionImpl) mediaSessionImpl).onGetItemOnHandler(controllerInfo, str);
                    return onGetItemOnHandler;
                }
            }));
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void getChildren(IMediaController iMediaController, int i, final String str, final int i2, final int i3, Bundle bundle) {
        final MediaLibraryService.LibraryParams fromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getChildren(): Ignoring empty parentId");
            return;
        }
        if (i2 < 0) {
            Log.w(TAG, "getChildren(): Ignoring negative page");
            return;
        }
        if (i3 < 1) {
            Log.w(TAG, "getChildren(): Ignoring pageSize less than 1");
            return;
        }
        if (bundle == null) {
            fromBundle = null;
        } else {
            try {
                fromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda5
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i4) {
                ListenableFuture onGetChildrenOnHandler;
                onGetChildrenOnHandler = ((MediaLibrarySessionImpl) mediaSessionImpl).onGetChildrenOnHandler(controllerInfo, str, i2, i3, fromBundle);
                return onGetChildrenOnHandler;
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void search(IMediaController iMediaController, int i, final String str, Bundle bundle) {
        final MediaLibraryService.LibraryParams fromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "search(): Ignoring empty query");
            return;
        }
        if (bundle == null) {
            fromBundle = null;
        } else {
            try {
                fromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_SEARCH, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda83
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                ListenableFuture onSearchOnHandler;
                onSearchOnHandler = ((MediaLibrarySessionImpl) mediaSessionImpl).onSearchOnHandler(controllerInfo, str, fromBundle);
                return onSearchOnHandler;
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void getSearchResult(IMediaController iMediaController, int i, final String str, final int i2, final int i3, Bundle bundle) {
        final MediaLibraryService.LibraryParams fromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getSearchResult(): Ignoring empty query");
            return;
        }
        if (i2 < 0) {
            Log.w(TAG, "getSearchResult(): Ignoring negative page");
            return;
        }
        if (i3 < 1) {
            Log.w(TAG, "getSearchResult(): Ignoring pageSize less than 1");
            return;
        }
        if (bundle == null) {
            fromBundle = null;
        } else {
            try {
                fromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda59
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i4) {
                ListenableFuture onGetSearchResultOnHandler;
                onGetSearchResultOnHandler = ((MediaLibrarySessionImpl) mediaSessionImpl).onGetSearchResultOnHandler(controllerInfo, str, i2, i3, fromBundle);
                return onGetSearchResultOnHandler;
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void subscribe(IMediaController iMediaController, int i, final String str, Bundle bundle) {
        final MediaLibraryService.LibraryParams fromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "subscribe(): Ignoring empty parentId");
            return;
        }
        if (bundle == null) {
            fromBundle = null;
        } else {
            try {
                fromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda74
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                ListenableFuture onSubscribeOnHandler;
                onSubscribeOnHandler = ((MediaLibrarySessionImpl) mediaSessionImpl).onSubscribeOnHandler(controllerInfo, str, fromBundle);
                return onSubscribeOnHandler;
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void unsubscribe(IMediaController iMediaController, int i, final String str) {
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "unsubscribe(): Ignoring empty parentId");
        } else {
            dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda4
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    ListenableFuture onUnsubscribeOnHandler;
                    onUnsubscribeOnHandler = ((MediaLibrarySessionImpl) mediaSessionImpl).onUnsubscribeOnHandler(controllerInfo, str);
                    return onUnsubscribeOnHandler;
                }
            }));
        }
    }

    public PlayerInfo generateAndCacheUniqueTrackGroupIds(PlayerInfo playerInfo) {
        ImmutableList<Tracks.Group> groups = playerInfo.currentTracks.getGroups();
        ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableBiMap.Builder builder2 = ImmutableBiMap.builder();
        for (int i = 0; i < groups.size(); i++) {
            Tracks.Group group = groups.get(i);
            TrackGroup mediaTrackGroup = group.getMediaTrackGroup();
            String str = this.trackGroupIdMap.get(mediaTrackGroup);
            if (str == null) {
                str = generateUniqueTrackGroupId(mediaTrackGroup);
            }
            builder2.put((ImmutableBiMap.Builder) mediaTrackGroup, (TrackGroup) str);
            builder.add((ImmutableList.Builder) group.copyWithId(str));
        }
        this.trackGroupIdMap = builder2.buildOrThrow();
        PlayerInfo copyWithCurrentTracks = playerInfo.copyWithCurrentTracks(new Tracks(builder.build()));
        if (copyWithCurrentTracks.trackSelectionParameters.overrides.isEmpty()) {
            return copyWithCurrentTracks;
        }
        TrackSelectionParameters.Builder clearOverrides = copyWithCurrentTracks.trackSelectionParameters.buildUpon().clearOverrides();
        UnmodifiableIterator<TrackSelectionOverride> it = copyWithCurrentTracks.trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            TrackSelectionOverride next = it.next();
            TrackGroup trackGroup = next.mediaTrackGroup;
            String str2 = this.trackGroupIdMap.get(trackGroup);
            if (str2 != null) {
                clearOverrides.addOverride(new TrackSelectionOverride(trackGroup.copyWithId(str2), next.trackIndices));
            } else {
                clearOverrides.addOverride(next);
            }
        }
        return copyWithCurrentTracks.copyWithTrackSelectionParameters(clearOverrides.build());
    }

    private TrackSelectionParameters updateOverridesUsingUniqueTrackGroupIds(TrackSelectionParameters trackSelectionParameters) {
        if (trackSelectionParameters.overrides.isEmpty()) {
            return trackSelectionParameters;
        }
        TrackSelectionParameters.Builder clearOverrides = trackSelectionParameters.buildUpon().clearOverrides();
        UnmodifiableIterator<TrackSelectionOverride> it = trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            TrackSelectionOverride next = it.next();
            TrackGroup trackGroup = this.trackGroupIdMap.inverse().get(next.mediaTrackGroup.id);
            if (trackGroup != null && next.mediaTrackGroup.length == trackGroup.length) {
                clearOverrides.addOverride(new TrackSelectionOverride(trackGroup, next.trackIndices));
            } else {
                clearOverrides.addOverride(next);
            }
        }
        return clearOverrides.build();
    }

    private String generateUniqueTrackGroupId(TrackGroup trackGroup) {
        StringBuilder sb = new StringBuilder();
        int i = this.nextUniqueTrackGroupIdPrefix;
        this.nextUniqueTrackGroupIdPrefix = i + 1;
        return sb.append(Util.intToStringMaxRadix(i)).append("-").append(trackGroup.id).toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Controller2Cb implements MediaSession.ControllerCb {
        private final int controllerInterfaceVersion;
        private final IMediaController iController;

        public Controller2Cb(IMediaController iMediaController, int i) {
            this.iController = iMediaController;
            this.controllerInterfaceVersion = i;
        }

        public IBinder getCallbackBinder() {
            return this.iController.asBinder();
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
            this.iController.onSessionResult(i, sessionResult.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onLibraryResult(int i, LibraryResult<?> libraryResult) throws RemoteException {
            this.iController.onLibraryResult(i, libraryResult.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerInfoChanged(int i, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z2) throws RemoteException {
            Bundle bundleForRemoteProcess;
            Assertions.checkState(this.controllerInterfaceVersion != 0);
            boolean z3 = z || !commands.contains(17);
            boolean z4 = z2 || !commands.contains(30);
            if (this.controllerInterfaceVersion >= 2) {
                PlayerInfo filterByAvailableCommands = playerInfo.filterByAvailableCommands(commands, z, z2);
                if (this.iController instanceof MediaControllerStub) {
                    bundleForRemoteProcess = filterByAvailableCommands.toBundleInProcess();
                } else {
                    bundleForRemoteProcess = filterByAvailableCommands.toBundleForRemoteProcess(this.controllerInterfaceVersion);
                }
                this.iController.onPlayerInfoChangedWithExclusions(i, bundleForRemoteProcess, new PlayerInfo.BundlingExclusions(z3, z4).toBundle());
                return;
            }
            this.iController.onPlayerInfoChanged(i, playerInfo.filterByAvailableCommands(commands, z, true).toBundleForRemoteProcess(this.controllerInterfaceVersion), z3);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setCustomLayout(int i, List<CommandButton> list) throws RemoteException {
            this.iController.onSetCustomLayout(i, BundleCollectionUtil.toBundleList(list, new ConnectionState$$ExternalSyntheticLambda3()));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setMediaButtonPreferences(int i, List<CommandButton> list) throws RemoteException {
            if (this.controllerInterfaceVersion >= 7) {
                this.iController.onSetMediaButtonPreferences(i, BundleCollectionUtil.toBundleList(list, new ConnectionState$$ExternalSyntheticLambda3()));
            } else {
                this.iController.onSetCustomLayout(i, BundleCollectionUtil.toBundleList(CommandButton.getCustomLayoutFromMediaButtonPreferences(list, true, true), new ConnectionState$$ExternalSyntheticLambda3()));
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
            this.iController.onSessionActivityChanged(i, pendingIntent);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromSession(int i, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException {
            this.iController.onAvailableCommandsChangedFromSession(i, sessionCommands.toBundle(), commands.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromPlayer(int i, Player.Commands commands) throws RemoteException {
            this.iController.onAvailableCommandsChangedFromPlayer(i, commands.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
            this.iController.onCustomCommand(i, sessionCommand.toBundle(), bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            this.iController.onChildrenChanged(i, str, i2, libraryParams == null ? null : libraryParams.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            this.iController.onSearchResultChanged(i, str, i2, libraryParams == null ? null : libraryParams.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDisconnected(int i) {
            SessionUtil.disconnectIMediaController(this.iController);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPeriodicSessionPositionInfoChanged(int i, SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, int i2) throws RemoteException {
            this.iController.onPeriodicSessionPositionInfoChanged(i, sessionPositionInfo.filterByAvailableCommands(z, z2).toBundle(i2));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onRenderedFirstFrame(int i) throws RemoteException {
            this.iController.onRenderedFirstFrame(i);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionExtrasChanged(int i, Bundle bundle) throws RemoteException {
            this.iController.onExtrasChanged(i, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onError(int i, SessionError sessionError) throws RemoteException {
            this.iController.onError(i, sessionError.toBundle());
        }

        public int hashCode() {
            return ObjectsCompat.hash(getCallbackBinder());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != Controller2Cb.class) {
                return false;
            }
            return Objects.equals(getCallbackBinder(), ((Controller2Cb) obj).getCallbackBinder());
        }
    }
}
