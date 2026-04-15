package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.media3.common.Player;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaController;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.PlayerInfo;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MediaControllerStub extends IMediaController.Stub {
    private static final String TAG = "MediaControllerStub";
    public static final int VERSION_INT = 7;
    private final WeakReference<MediaControllerImplBase> controller;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface ControllerTask<T extends MediaControllerImplBase> {
        void run(T t);
    }

    public MediaControllerStub(MediaControllerImplBase mediaControllerImplBase) {
        this.controller = new WeakReference<>(mediaControllerImplBase);
    }

    @Override // androidx.media3.session.IMediaController
    public void onSessionResult(int i, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            setControllerFutureResult(i, SessionResult.fromBundle(bundle));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionResult", e);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onLibraryResult(int i, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            setControllerFutureResult(i, LibraryResult.fromUnknownBundle(bundle));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for LibraryResult", e);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onConnected(int i, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            final ConnectionState fromBundle = ConnectionState.fromBundle(bundle);
            dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda1
                @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                public final void run(MediaControllerImplBase mediaControllerImplBase) {
                    mediaControllerImplBase.onConnected(ConnectionState.this);
                }
            });
        } catch (RuntimeException e) {
            Log.w(TAG, "Malformed Bundle for ConnectionResult. Disconnected from the session.", e);
            onDisconnected(i);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onDisconnected(int i) {
        dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda13
            @Override // androidx.media3.session.MediaControllerStub.ControllerTask
            public final void run(MediaControllerImplBase mediaControllerImplBase) {
                MediaControllerStub.lambda$onDisconnected$1(mediaControllerImplBase);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onDisconnected$1(MediaControllerImplBase mediaControllerImplBase) {
        MediaController mediaControllerImplBase2 = mediaControllerImplBase.getInstance();
        MediaController mediaControllerImplBase3 = mediaControllerImplBase.getInstance();
        Objects.requireNonNull(mediaControllerImplBase3);
        mediaControllerImplBase2.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(mediaControllerImplBase3));
    }

    @Override // androidx.media3.session.IMediaController
    public void onSetCustomLayout(final int i, List<Bundle> list) {
        if (list == null) {
            return;
        }
        try {
            final int sessionInterfaceVersion = getSessionInterfaceVersion();
            if (sessionInterfaceVersion == -1) {
                return;
            }
            final ImmutableList fromBundleList = BundleCollectionUtil.fromBundleList(new Function() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda3
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    CommandButton fromBundle;
                    fromBundle = CommandButton.fromBundle((Bundle) obj, sessionInterfaceVersion);
                    return fromBundle;
                }
            }, list);
            dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda4
                @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                public final void run(MediaControllerImplBase mediaControllerImplBase) {
                    mediaControllerImplBase.onSetCustomLayout(i, fromBundleList);
                }
            });
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for CommandButton", e);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onSetMediaButtonPreferences(final int i, List<Bundle> list) {
        if (list == null) {
            return;
        }
        try {
            final int sessionInterfaceVersion = getSessionInterfaceVersion();
            if (sessionInterfaceVersion == -1) {
                return;
            }
            final ImmutableList fromBundleList = BundleCollectionUtil.fromBundleList(new Function() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    CommandButton fromBundle;
                    fromBundle = CommandButton.fromBundle((Bundle) obj, sessionInterfaceVersion);
                    return fromBundle;
                }
            }, list);
            dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda9
                @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                public final void run(MediaControllerImplBase mediaControllerImplBase) {
                    mediaControllerImplBase.onSetMediaButtonPreferences(i, fromBundleList);
                }
            });
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for CommandButton", e);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onAvailableCommandsChangedFromSession(int i, Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return;
        }
        try {
            final SessionCommands fromBundle = SessionCommands.fromBundle(bundle);
            try {
                final Player.Commands fromBundle2 = Player.Commands.fromBundle(bundle2);
                dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda12
                    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                    public final void run(MediaControllerImplBase mediaControllerImplBase) {
                        mediaControllerImplBase.onAvailableCommandsChangedFromSession(SessionCommands.this, fromBundle2);
                    }
                });
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for Commands", e);
            }
        } catch (RuntimeException e2) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionCommands", e2);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onAvailableCommandsChangedFromPlayer(int i, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            final Player.Commands fromBundle = Player.Commands.fromBundle(bundle);
            dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda6
                @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                public final void run(MediaControllerImplBase mediaControllerImplBase) {
                    mediaControllerImplBase.onAvailableCommandsChangedFromPlayer(Player.Commands.this);
                }
            });
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for Commands", e);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onCustomCommand(final int i, Bundle bundle, final Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            Log.w(TAG, "Ignoring custom command with null args.");
            return;
        }
        try {
            final SessionCommand fromBundle = SessionCommand.fromBundle(bundle);
            dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda16
                @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                public final void run(MediaControllerImplBase mediaControllerImplBase) {
                    mediaControllerImplBase.onCustomCommand(i, fromBundle, bundle2);
                }
            });
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionCommand", e);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onSessionActivityChanged(final int i, final PendingIntent pendingIntent) throws RemoteException {
        dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda15
            @Override // androidx.media3.session.MediaControllerStub.ControllerTask
            public final void run(MediaControllerImplBase mediaControllerImplBase) {
                mediaControllerImplBase.onSetSessionActivity(i, pendingIntent);
            }
        });
    }

    @Override // androidx.media3.session.IMediaController
    public void onPeriodicSessionPositionInfoChanged(int i, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            final SessionPositionInfo fromBundle = SessionPositionInfo.fromBundle(bundle);
            dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda2
                @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                public final void run(MediaControllerImplBase mediaControllerImplBase) {
                    mediaControllerImplBase.notifyPeriodicSessionPositionInfoChanged(SessionPositionInfo.this);
                }
            });
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionPositionInfo", e);
        }
    }

    @Override // androidx.media3.session.IMediaController
    @Deprecated
    public void onPlayerInfoChanged(int i, Bundle bundle, boolean z) {
        onPlayerInfoChangedWithExclusions(i, bundle, new PlayerInfo.BundlingExclusions(z, true).toBundle());
    }

    @Override // androidx.media3.session.IMediaController
    public void onPlayerInfoChangedWithExclusions(int i, Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return;
        }
        try {
            int sessionInterfaceVersion = getSessionInterfaceVersion();
            if (sessionInterfaceVersion == -1) {
                return;
            }
            final PlayerInfo fromBundle = PlayerInfo.fromBundle(bundle, sessionInterfaceVersion);
            try {
                final PlayerInfo.BundlingExclusions fromBundle2 = PlayerInfo.BundlingExclusions.fromBundle(bundle2);
                dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda7
                    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                    public final void run(MediaControllerImplBase mediaControllerImplBase) {
                        mediaControllerImplBase.onPlayerInfoChanged(PlayerInfo.this, fromBundle2);
                    }
                });
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for BundlingExclusions", e);
            }
        } catch (RuntimeException e2) {
            Log.w(TAG, "Ignoring malformed Bundle for PlayerInfo", e2);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onExtrasChanged(int i, final Bundle bundle) {
        if (bundle == null) {
            Log.w(TAG, "Ignoring null Bundle for extras");
        } else {
            dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda17
                @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                public final void run(MediaControllerImplBase mediaControllerImplBase) {
                    mediaControllerImplBase.onExtrasChanged(bundle);
                }
            });
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onError(final int i, Bundle bundle) throws RemoteException {
        try {
            final SessionError fromBundle = SessionError.fromBundle(bundle);
            dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda10
                @Override // androidx.media3.session.MediaControllerStub.ControllerTask
                public final void run(MediaControllerImplBase mediaControllerImplBase) {
                    mediaControllerImplBase.onError(i, fromBundle);
                }
            });
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionError", e);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onRenderedFirstFrame(int i) {
        dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda11
            @Override // androidx.media3.session.MediaControllerStub.ControllerTask
            public final void run(MediaControllerImplBase mediaControllerImplBase) {
                mediaControllerImplBase.onRenderedFirstFrame();
            }
        });
    }

    @Override // androidx.media3.session.IMediaController
    public void onSearchResultChanged(int i, final String str, final int i2, Bundle bundle) throws RuntimeException {
        final MediaLibraryService.LibraryParams fromBundle;
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onSearchResultChanged(): Ignoring empty query");
            return;
        }
        if (i2 < 0) {
            Log.w(TAG, "onSearchResultChanged(): Ignoring negative itemCount: " + i2);
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
        dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda5
            @Override // androidx.media3.session.MediaControllerStub.ControllerTask
            public final void run(MediaControllerImplBase mediaControllerImplBase) {
                ((MediaBrowserImplBase) mediaControllerImplBase).notifySearchResultChanged(str, i2, fromBundle);
            }
        });
    }

    @Override // androidx.media3.session.IMediaController
    public void onChildrenChanged(int i, final String str, final int i2, Bundle bundle) {
        final MediaLibraryService.LibraryParams fromBundle;
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onChildrenChanged(): Ignoring empty parentId");
            return;
        }
        if (i2 < 0) {
            Log.w(TAG, "onChildrenChanged(): Ignoring negative itemCount: " + i2);
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
        dispatchControllerTaskOnHandler(new ControllerTask() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda14
            @Override // androidx.media3.session.MediaControllerStub.ControllerTask
            public final void run(MediaControllerImplBase mediaControllerImplBase) {
                ((MediaBrowserImplBase) mediaControllerImplBase).notifyChildrenChanged(str, i2, fromBundle);
            }
        });
    }

    public void destroy() {
        this.controller.clear();
    }

    private <T> void setControllerFutureResult(int i, T t) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaControllerImplBase mediaControllerImplBase = this.controller.get();
            if (mediaControllerImplBase == null) {
                return;
            }
            mediaControllerImplBase.setFutureResult(i, t);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    private <T extends MediaControllerImplBase> void dispatchControllerTaskOnHandler(final ControllerTask<T> controllerTask) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaControllerImplBase mediaControllerImplBase = this.controller.get();
            if (mediaControllerImplBase == null) {
                return;
            }
            Util.postOrRun(mediaControllerImplBase.getInstance().applicationHandler, new Runnable() { // from class: androidx.media3.session.MediaControllerStub$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    MediaControllerStub.lambda$dispatchControllerTaskOnHandler$16(MediaControllerImplBase.this, controllerTask);
                }
            });
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$dispatchControllerTaskOnHandler$16(MediaControllerImplBase mediaControllerImplBase, ControllerTask controllerTask) {
        if (mediaControllerImplBase.isReleased()) {
            return;
        }
        controllerTask.run(mediaControllerImplBase);
    }

    private int getSessionInterfaceVersion() {
        SessionToken connectedToken;
        MediaControllerImplBase mediaControllerImplBase = this.controller.get();
        if (mediaControllerImplBase == null || (connectedToken = mediaControllerImplBase.getConnectedToken()) == null) {
            return -1;
        }
        return connectedToken.getInterfaceVersion();
    }
}
