package androidx.media3.session.legacy;

import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import androidx.media3.common.util.Assertions;
import androidx.media3.session.legacy.AudioAttributesCompat;
import androidx.media3.session.legacy.IMediaControllerCallback;
import androidx.media3.session.legacy.IMediaSession;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.PlaybackStateCompat;
import androidx.versionedparcelable.ParcelUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class MediaControllerCompat {
    public static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";
    public static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";
    public static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";
    public static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";
    public static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
    public static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";
    public static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";
    static final String TAG = "MediaControllerCompat";
    private final MediaControllerImpl impl;
    private final Set<Callback> registeredCallbacks;
    private final MediaSessionCompat.Token token;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface MediaControllerImpl {
        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i);

        void adjustVolume(int i, int i2);

        boolean dispatchMediaButtonEvent(KeyEvent keyEvent);

        Bundle getExtras();

        long getFlags();

        Object getMediaController();

        MediaMetadataCompat getMetadata();

        String getPackageName();

        PlaybackInfo getPlaybackInfo();

        PlaybackStateCompat getPlaybackState();

        List<MediaSessionCompat.QueueItem> getQueue();

        CharSequence getQueueTitle();

        int getRatingType();

        int getRepeatMode();

        PendingIntent getSessionActivity();

        Bundle getSessionInfo();

        int getShuffleMode();

        TransportControls getTransportControls();

        boolean isCaptioningEnabled();

        boolean isSessionReady();

        void registerCallback(Callback callback, Handler handler);

        void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void setVolumeTo(int i, int i2);

        void unregisterCallback(Callback callback);
    }

    static void validateCustomAction(String str, Bundle bundle) {
        if (str == null) {
            return;
        }
        str.hashCode();
        if (str.equals("android.support.v4.media.session.action.FOLLOW") || str.equals("android.support.v4.media.session.action.UNFOLLOW")) {
            if (bundle == null || !bundle.containsKey("android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE")) {
                throw new IllegalArgumentException("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action " + str + ".");
            }
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat mediaSessionCompat) {
        this(context, mediaSessionCompat.getSessionToken());
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) {
        this.registeredCallbacks = Collections.synchronizedSet(new HashSet());
        this.token = token;
        if (Build.VERSION.SDK_INT >= 29) {
            this.impl = new MediaControllerImplApi29(context, token);
        } else {
            this.impl = new MediaControllerImplApi21(context, token);
        }
    }

    public TransportControls getTransportControls() {
        return this.impl.getTransportControls();
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        return this.impl.dispatchMediaButtonEvent(keyEvent);
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.impl.getPlaybackState();
    }

    public MediaMetadataCompat getMetadata() {
        return this.impl.getMetadata();
    }

    public List<MediaSessionCompat.QueueItem> getQueue() {
        return this.impl.getQueue();
    }

    public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        this.impl.addQueueItem(mediaDescriptionCompat);
    }

    public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        this.impl.addQueueItem(mediaDescriptionCompat, i);
    }

    public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        this.impl.removeQueueItem(mediaDescriptionCompat);
    }

    @Deprecated
    public void removeQueueItemAt(int i) {
        MediaSessionCompat.QueueItem queueItem;
        List<MediaSessionCompat.QueueItem> queue = getQueue();
        if (queue == null || i < 0 || i >= queue.size() || (queueItem = queue.get(i)) == null) {
            return;
        }
        removeQueueItem(queueItem.getDescription());
    }

    public CharSequence getQueueTitle() {
        return this.impl.getQueueTitle();
    }

    public Bundle getExtras() {
        return this.impl.getExtras();
    }

    public int getRatingType() {
        return this.impl.getRatingType();
    }

    public boolean isCaptioningEnabled() {
        return this.impl.isCaptioningEnabled();
    }

    public int getRepeatMode() {
        return this.impl.getRepeatMode();
    }

    public int getShuffleMode() {
        return this.impl.getShuffleMode();
    }

    public long getFlags() {
        return this.impl.getFlags();
    }

    public PlaybackInfo getPlaybackInfo() {
        return this.impl.getPlaybackInfo();
    }

    public PendingIntent getSessionActivity() {
        return this.impl.getSessionActivity();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.token;
    }

    public void setVolumeTo(int i, int i2) {
        this.impl.setVolumeTo(i, i2);
    }

    public void adjustVolume(int i, int i2) {
        this.impl.adjustVolume(i, i2);
    }

    public void registerCallback(Callback callback, Handler handler) {
        if (!this.registeredCallbacks.add(callback)) {
            Log.w(TAG, "the callback has already been registered");
            return;
        }
        if (handler == null) {
            handler = new Handler();
        }
        callback.setHandler(handler);
        this.impl.registerCallback(callback, handler);
    }

    public void unregisterCallback(Callback callback) {
        if (!this.registeredCallbacks.remove(callback)) {
            Log.w(TAG, "the callback has never been registered");
            return;
        }
        try {
            this.impl.unregisterCallback(callback);
        } finally {
            callback.setHandler(null);
        }
    }

    public void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("command must neither be null nor empty");
        }
        this.impl.sendCommand(str, bundle, resultReceiver);
    }

    public boolean isSessionReady() {
        return this.impl.isSessionReady();
    }

    public String getPackageName() {
        return this.impl.getPackageName();
    }

    public Bundle getSessionInfo() {
        return this.impl.getSessionInfo();
    }

    public Object getMediaController() {
        return this.impl.getMediaController();
    }

    /* loaded from: classes.dex */
    public static abstract class Callback implements IBinder.DeathRecipient {
        final MediaController.Callback callbackFwk = new MediaControllerCallback(this);
        MessageHandler handler;
        IMediaControllerCallback iControllerCallback;

        public void onAudioInfoChanged(PlaybackInfo playbackInfo) {
        }

        public void onCaptioningEnabledChanged(boolean z) {
        }

        public void onExtrasChanged(Bundle bundle) {
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
        }

        public void onRepeatModeChanged(int i) {
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String str, Bundle bundle) {
        }

        public void onSessionReady() {
        }

        public void onShuffleModeChanged(int i) {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            postToHandler(8, null, null);
        }

        void setHandler(Handler handler) {
            if (handler == null) {
                MessageHandler messageHandler = this.handler;
                if (messageHandler != null) {
                    messageHandler.registered = false;
                    this.handler.removeCallbacksAndMessages(null);
                    this.handler = null;
                    return;
                }
                return;
            }
            MessageHandler messageHandler2 = new MessageHandler(handler.getLooper());
            this.handler = messageHandler2;
            messageHandler2.registered = true;
        }

        void postToHandler(int i, Object obj, Bundle bundle) {
            MessageHandler messageHandler = this.handler;
            if (messageHandler != null) {
                Message obtainMessage = messageHandler.obtainMessage(i, obj);
                if (bundle != null) {
                    obtainMessage.setData(bundle);
                }
                obtainMessage.sendToTarget();
            }
        }

        /* loaded from: classes.dex */
        private static class MediaControllerCallback extends MediaController.Callback {
            private final WeakReference<Callback> callback;

            MediaControllerCallback(Callback callback) {
                this.callback = new WeakReference<>(callback);
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onSessionDestroyed();
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String str, Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                Callback callback = this.callback.get();
                if (callback != null) {
                    IMediaControllerCallback iMediaControllerCallback = callback.iControllerCallback;
                    callback.onSessionEvent(str, bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                Callback callback = this.callback.get();
                if (callback == null || callback.iControllerCallback != null) {
                    return;
                }
                callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(playbackState));
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(mediaMetadata));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List<MediaSession.QueueItem> list) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(list));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence charSequence) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onQueueTitleChanged(charSequence);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onExtrasChanged(bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                Callback callback = this.callback.get();
                if (callback == null || playbackInfo == null) {
                    return;
                }
                callback.onAudioInfoChanged(new PlaybackInfo(playbackInfo.getPlaybackType(), AudioAttributesCompat.wrap(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
            }
        }

        /* loaded from: classes.dex */
        private static class StubCompat extends IMediaControllerCallback.Stub {
            private final WeakReference<Callback> callback;

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onShuffleModeChangedRemoved(boolean z) {
            }

            StubCompat(Callback callback) {
                this.callback = new WeakReference<>(callback);
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onEvent(String str, Bundle bundle) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(1, str, bundle);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onSessionDestroyed() {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(8, null, null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(2, playbackStateCompat, null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(3, mediaMetadataCompat, null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(5, list, null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onQueueTitleChanged(CharSequence charSequence) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(6, charSequence, null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onCaptioningEnabledChanged(boolean z) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(11, Boolean.valueOf(z), null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onRepeatModeChanged(int i) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(9, Integer.valueOf(i), null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onShuffleModeChanged(int i) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(12, Integer.valueOf(i), null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onExtrasChanged(Bundle bundle) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(7, bundle, null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(4, parcelableVolumeInfo != null ? new PlaybackInfo(parcelableVolumeInfo.volumeType, parcelableVolumeInfo.audioStream, parcelableVolumeInfo.controlType, parcelableVolumeInfo.maxVolume, parcelableVolumeInfo.currentVolume) : null, null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onSessionReady() {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(13, null, null);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class MessageHandler extends Handler {
            private static final int MSG_DESTROYED = 8;
            private static final int MSG_EVENT = 1;
            private static final int MSG_SESSION_READY = 13;
            private static final int MSG_UPDATE_CAPTIONING_ENABLED = 11;
            private static final int MSG_UPDATE_EXTRAS = 7;
            private static final int MSG_UPDATE_METADATA = 3;
            private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
            private static final int MSG_UPDATE_QUEUE = 5;
            private static final int MSG_UPDATE_QUEUE_TITLE = 6;
            private static final int MSG_UPDATE_REPEAT_MODE = 9;
            private static final int MSG_UPDATE_SHUFFLE_MODE = 12;
            private static final int MSG_UPDATE_VOLUME = 4;
            boolean registered;

            MessageHandler(Looper looper) {
                super(looper);
                this.registered = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (this.registered) {
                    switch (message.what) {
                        case 1:
                            Bundle data = message.getData();
                            MediaSessionCompat.ensureClassLoader(data);
                            Callback.this.onSessionEvent((String) message.obj, data);
                            return;
                        case 2:
                            Callback.this.onPlaybackStateChanged((PlaybackStateCompat) message.obj);
                            return;
                        case 3:
                            Callback.this.onMetadataChanged((MediaMetadataCompat) message.obj);
                            return;
                        case 4:
                            Callback.this.onAudioInfoChanged((PlaybackInfo) message.obj);
                            return;
                        case 5:
                            Callback.this.onQueueChanged((List) message.obj);
                            return;
                        case 6:
                            Callback.this.onQueueTitleChanged((CharSequence) message.obj);
                            return;
                        case 7:
                            Bundle bundle = (Bundle) message.obj;
                            MediaSessionCompat.ensureClassLoader(bundle);
                            Callback.this.onExtrasChanged(bundle);
                            return;
                        case 8:
                            Callback.this.onSessionDestroyed();
                            return;
                        case 9:
                            Callback.this.onRepeatModeChanged(((Integer) message.obj).intValue());
                            return;
                        case 10:
                        default:
                            return;
                        case 11:
                            Callback.this.onCaptioningEnabledChanged(((Boolean) message.obj).booleanValue());
                            return;
                        case 12:
                            Callback.this.onShuffleModeChanged(((Integer) message.obj).intValue());
                            return;
                        case 13:
                            Callback.this.onSessionReady();
                            return;
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class TransportControls {

        @Deprecated
        public static final String EXTRA_LEGACY_STREAM_TYPE = "android.media.session.extra.LEGACY_STREAM_TYPE";

        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String str, Bundle bundle);

        public abstract void playFromSearch(String str, Bundle bundle);

        public abstract void playFromUri(Uri uri, Bundle bundle);

        public abstract void prepare();

        public abstract void prepareFromMediaId(String str, Bundle bundle);

        public abstract void prepareFromSearch(String str, Bundle bundle);

        public abstract void prepareFromUri(Uri uri, Bundle bundle);

        public abstract void rewind();

        public abstract void seekTo(long j);

        public abstract void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle);

        public abstract void sendCustomAction(String str, Bundle bundle);

        public void setPlaybackSpeed(float f) {
        }

        public abstract void setRating(RatingCompat ratingCompat);

        public abstract void setRating(RatingCompat ratingCompat, Bundle bundle);

        public abstract void setRepeatMode(int i);

        public abstract void setShuffleMode(int i);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long j);

        public abstract void stop();

        TransportControls() {
        }
    }

    /* loaded from: classes.dex */
    public static final class PlaybackInfo {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final AudioAttributesCompat audioAttrsCompat;
        private final int currentVolume;
        private final int maxVolume;
        private final int playbackType;
        private final int volumeControl;

        PlaybackInfo(int i, int i2, int i3, int i4, int i5) {
            this(i, new AudioAttributesCompat.Builder().setLegacyStreamType(i2).build(), i3, i4, i5);
        }

        PlaybackInfo(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            this.playbackType = i;
            this.audioAttrsCompat = audioAttributesCompat;
            this.volumeControl = i2;
            this.maxVolume = i3;
            this.currentVolume = i4;
        }

        public int getPlaybackType() {
            return this.playbackType;
        }

        @Deprecated
        public int getAudioStream() {
            return this.audioAttrsCompat.getLegacyStreamType();
        }

        public AudioAttributesCompat getAudioAttributes() {
            return this.audioAttrsCompat;
        }

        public int getVolumeControl() {
            return this.volumeControl;
        }

        public int getMaxVolume() {
            return this.maxVolume;
        }

        public int getCurrentVolume() {
            return this.currentVolume;
        }
    }

    /* loaded from: classes.dex */
    static class MediaControllerImplApi21 implements MediaControllerImpl {
        protected final MediaController controllerFwk;
        protected Bundle sessionInfo;
        final MediaSessionCompat.Token sessionToken;
        final Object lock = new Object();
        private final List<Callback> pendingCallbacks = new ArrayList();
        private final HashMap<Callback, ExtraCallback> callbackMap = new HashMap<>();

        MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.sessionToken = token;
            this.controllerFwk = new MediaController(context, token.getToken());
            if (token.getExtraBinder() == null) {
                requestExtraBinder();
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public final void registerCallback(Callback callback, Handler handler) {
            this.controllerFwk.registerCallback((MediaController.Callback) Assertions.checkNotNull(callback.callbackFwk), handler);
            synchronized (this.lock) {
                IMediaSession extraBinder = this.sessionToken.getExtraBinder();
                if (extraBinder != null) {
                    ExtraCallback extraCallback = new ExtraCallback(callback);
                    this.callbackMap.put(callback, extraCallback);
                    callback.iControllerCallback = extraCallback;
                    try {
                        extraBinder.registerCallbackListener(extraCallback);
                        callback.postToHandler(13, null, null);
                    } catch (RemoteException | SecurityException e) {
                        Log.e(MediaControllerCompat.TAG, "Dead object in registerCallback.", e);
                    }
                } else {
                    callback.iControllerCallback = null;
                    this.pendingCallbacks.add(callback);
                }
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public final void unregisterCallback(Callback callback) {
            this.controllerFwk.unregisterCallback((MediaController.Callback) Assertions.checkNotNull(callback.callbackFwk));
            synchronized (this.lock) {
                IMediaSession extraBinder = this.sessionToken.getExtraBinder();
                if (extraBinder != null) {
                    try {
                        ExtraCallback remove = this.callbackMap.remove(callback);
                        if (remove != null) {
                            callback.iControllerCallback = null;
                            extraBinder.unregisterCallbackListener(remove);
                        }
                    } catch (RemoteException | SecurityException e) {
                        Log.e(MediaControllerCompat.TAG, "Dead object in unregisterCallback.", e);
                    }
                } else {
                    this.pendingCallbacks.remove(callback);
                }
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            return this.controllerFwk.dispatchMediaButtonEvent(keyEvent);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public TransportControls getTransportControls() {
            MediaController.TransportControls transportControls = this.controllerFwk.getTransportControls();
            if (Build.VERSION.SDK_INT >= 29) {
                return new TransportControlsApi29(transportControls);
            }
            return new TransportControlsApi24(transportControls);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public PlaybackStateCompat getPlaybackState() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder != null) {
                try {
                    return extraBinder.getPlaybackState();
                } catch (RemoteException | SecurityException e) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in getPlaybackState.", e);
                }
            }
            PlaybackState playbackState = this.controllerFwk.getPlaybackState();
            if (playbackState != null) {
                return PlaybackStateCompat.fromPlaybackState(playbackState);
            }
            return null;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public MediaMetadataCompat getMetadata() {
            MediaMetadata metadata = this.controllerFwk.getMetadata();
            if (metadata != null) {
                return MediaMetadataCompat.fromMediaMetadata(metadata);
            }
            return null;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public List<MediaSessionCompat.QueueItem> getQueue() {
            List<MediaSession.QueueItem> queue = this.controllerFwk.getQueue();
            if (queue != null) {
                return MediaSessionCompat.QueueItem.fromQueueItemList(queue);
            }
            return null;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", LegacyParcelableUtil.convert(mediaDescriptionCompat, android.support.v4.media.MediaDescriptionCompat.CREATOR));
            sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM", bundle, null);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if ((getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", LegacyParcelableUtil.convert(mediaDescriptionCompat, android.support.v4.media.MediaDescriptionCompat.CREATOR));
            bundle.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", i);
            sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT", bundle, null);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", LegacyParcelableUtil.convert(mediaDescriptionCompat, android.support.v4.media.MediaDescriptionCompat.CREATOR));
            sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", bundle, null);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public CharSequence getQueueTitle() {
            return this.controllerFwk.getQueueTitle();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public Bundle getExtras() {
            return this.controllerFwk.getExtras();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public int getRatingType() {
            return this.controllerFwk.getRatingType();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public boolean isCaptioningEnabled() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder == null) {
                return false;
            }
            try {
                return extraBinder.isCaptioningEnabled();
            } catch (RemoteException | SecurityException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in isCaptioningEnabled.", e);
                return false;
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public int getRepeatMode() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder == null) {
                return -1;
            }
            try {
                return extraBinder.getRepeatMode();
            } catch (RemoteException | SecurityException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getRepeatMode.", e);
                return -1;
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public int getShuffleMode() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder == null) {
                return -1;
            }
            try {
                return extraBinder.getShuffleMode();
            } catch (RemoteException | SecurityException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getShuffleMode.", e);
                return -1;
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public long getFlags() {
            return this.controllerFwk.getFlags();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public PlaybackInfo getPlaybackInfo() {
            MediaController.PlaybackInfo playbackInfo = this.controllerFwk.getPlaybackInfo();
            if (playbackInfo != null) {
                return new PlaybackInfo(playbackInfo.getPlaybackType(), AudioAttributesCompat.wrap(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume());
            }
            return null;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public PendingIntent getSessionActivity() {
            return this.controllerFwk.getSessionActivity();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void setVolumeTo(int i, int i2) {
            this.controllerFwk.setVolumeTo(i, i2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void adjustVolume(int i, int i2) {
            this.controllerFwk.adjustVolume(i, i2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.controllerFwk.sendCommand(str, bundle, resultReceiver);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public boolean isSessionReady() {
            return this.sessionToken.getExtraBinder() != null;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public String getPackageName() {
            return this.controllerFwk.getPackageName();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public Bundle getSessionInfo() {
            if (this.sessionInfo != null) {
                return new Bundle(this.sessionInfo);
            }
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder != null) {
                try {
                    this.sessionInfo = extraBinder.getSessionInfo();
                } catch (RemoteException | SecurityException e) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in getSessionInfo.", e);
                    this.sessionInfo = Bundle.EMPTY;
                }
            }
            Bundle unparcelWithClassLoader = MediaSessionCompat.unparcelWithClassLoader(this.sessionInfo);
            this.sessionInfo = unparcelWithClassLoader;
            return unparcelWithClassLoader == null ? Bundle.EMPTY : new Bundle(this.sessionInfo);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public Object getMediaController() {
            return this.controllerFwk;
        }

        private void requestExtraBinder() {
            sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        void processPendingCallbacksLocked() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder == null) {
                return;
            }
            for (Callback callback : this.pendingCallbacks) {
                ExtraCallback extraCallback = new ExtraCallback(callback);
                this.callbackMap.put(callback, extraCallback);
                callback.iControllerCallback = extraCallback;
                try {
                    extraBinder.registerCallbackListener(extraCallback);
                    callback.postToHandler(13, null, null);
                } catch (RemoteException | SecurityException e) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in registerCallback.", e);
                }
            }
            this.pendingCallbacks.clear();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private final WeakReference<MediaControllerImplApi21> mediaControllerImpl;

            ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.mediaControllerImpl = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.mediaControllerImpl.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.lock) {
                    mediaControllerImplApi21.sessionToken.setExtraBinder(IMediaSession.Stub.asInterface(bundle.getBinder("android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.sessionToken.setSession2Token(ParcelUtils.getVersionedParcelable(bundle, "android.support.v4.media.session.SESSION_TOKEN2"));
                    mediaControllerImplApi21.processPendingCallbacksLocked();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class ExtraCallback extends Callback.StubCompat {
            ExtraCallback(Callback callback) {
                super(callback);
            }

            @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback.StubCompat, androidx.media3.session.legacy.IMediaControllerCallback
            public void onSessionDestroyed() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback.StubCompat, androidx.media3.session.legacy.IMediaControllerCallback
            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback.StubCompat, androidx.media3.session.legacy.IMediaControllerCallback
            public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback.StubCompat, androidx.media3.session.legacy.IMediaControllerCallback
            public void onQueueTitleChanged(CharSequence charSequence) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback.StubCompat, androidx.media3.session.legacy.IMediaControllerCallback
            public void onExtrasChanged(Bundle bundle) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback.StubCompat, androidx.media3.session.legacy.IMediaControllerCallback
            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) {
                throw new AssertionError();
            }
        }
    }

    /* loaded from: classes.dex */
    static class MediaControllerImplApi29 extends MediaControllerImplApi21 {
        MediaControllerImplApi29(Context context, MediaSessionCompat.Token token) {
            super(context, token);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImplApi21, androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public Bundle getSessionInfo() {
            if (this.sessionInfo != null) {
                return new Bundle(this.sessionInfo);
            }
            this.sessionInfo = this.controllerFwk.getSessionInfo();
            this.sessionInfo = MediaSessionCompat.unparcelWithClassLoader(this.sessionInfo);
            return this.sessionInfo == null ? Bundle.EMPTY : new Bundle(this.sessionInfo);
        }
    }

    /* loaded from: classes.dex */
    static class TransportControlsApi21 extends TransportControls {
        protected final MediaController.TransportControls controlsFwk;

        TransportControlsApi21(MediaController.TransportControls transportControls) {
            this.controlsFwk = transportControls;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepare() {
            sendCustomAction("android.support.v4.media.session.action.PREPARE", (Bundle) null);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromMediaId(String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", str);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromSearch(String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("android.support.v4.media.session.action.ARGUMENT_QUERY", str);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_SEARCH", bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromUri(Uri uri, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_URI", bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void play() {
            this.controlsFwk.play();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void pause() {
            this.controlsFwk.pause();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void stop() {
            this.controlsFwk.stop();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void seekTo(long j) {
            this.controlsFwk.seekTo(j);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void fastForward() {
            this.controlsFwk.fastForward();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void rewind() {
            this.controlsFwk.rewind();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void skipToNext() {
            this.controlsFwk.skipToNext();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void skipToPrevious() {
            this.controlsFwk.skipToPrevious();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setRating(RatingCompat ratingCompat) {
            this.controlsFwk.setRating((Rating) ratingCompat.getRating());
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setRating(RatingCompat ratingCompat, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_RATING", LegacyParcelableUtil.convert(ratingCompat, android.support.v4.media.RatingCompat.CREATOR));
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.SET_RATING", bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setPlaybackSpeed(float f) {
            if (f == 0.0f) {
                throw new IllegalArgumentException("speed must not be zero");
            }
            Bundle bundle = new Bundle();
            bundle.putFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", f);
            sendCustomAction("android.support.v4.media.session.action.SET_PLAYBACK_SPEED", bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setRepeatMode(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", i);
            sendCustomAction("android.support.v4.media.session.action.SET_REPEAT_MODE", bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setShuffleMode(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", i);
            sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE", bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void playFromMediaId(String str, Bundle bundle) {
            this.controlsFwk.playFromMediaId(str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void playFromSearch(String str, Bundle bundle) {
            this.controlsFwk.playFromSearch(str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void playFromUri(Uri uri, Bundle bundle) {
            if (Uri.EMPTY.equals(uri)) {
                throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PLAY_FROM_URI", bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void skipToQueueItem(long j) {
            this.controlsFwk.skipToQueueItem(j);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle) {
            MediaControllerCompat.validateCustomAction(customAction.getAction(), bundle);
            this.controlsFwk.sendCustomAction(customAction.getAction(), bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void sendCustomAction(String str, Bundle bundle) {
            MediaControllerCompat.validateCustomAction(str, bundle);
            this.controlsFwk.sendCustomAction(str, bundle);
        }
    }

    /* loaded from: classes.dex */
    static class TransportControlsApi23 extends TransportControlsApi21 {
        TransportControlsApi23(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi21, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void playFromUri(Uri uri, Bundle bundle) {
            this.controlsFwk.playFromUri(uri, bundle);
        }
    }

    /* loaded from: classes.dex */
    static class TransportControlsApi24 extends TransportControlsApi23 {
        TransportControlsApi24(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi21, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepare() {
            this.controlsFwk.prepare();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi21, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromMediaId(String str, Bundle bundle) {
            this.controlsFwk.prepareFromMediaId(str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi21, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromSearch(String str, Bundle bundle) {
            this.controlsFwk.prepareFromSearch(str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi21, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromUri(Uri uri, Bundle bundle) {
            this.controlsFwk.prepareFromUri(uri, bundle);
        }
    }

    /* loaded from: classes.dex */
    static class TransportControlsApi29 extends TransportControlsApi24 {
        TransportControlsApi29(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi21, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setPlaybackSpeed(float f) {
            if (f == 0.0f) {
                throw new IllegalArgumentException("speed must not be zero");
            }
            this.controlsFwk.setPlaybackSpeed(f);
        }
    }
}
