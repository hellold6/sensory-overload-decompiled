package androidx.media3.session.legacy;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.media3.common.util.Assertions;
import androidx.media3.session.legacy.IMediaControllerCallback;
import androidx.media3.session.legacy.MediaSessionCompat;
import java.util.List;

/* loaded from: classes.dex */
public interface IMediaSession extends IInterface {
    void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) throws RemoteException;

    void adjustVolume(int i, int i2, String str) throws RemoteException;

    void fastForward() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    long getFlags() throws RemoteException;

    PendingIntent getLaunchPendingIntent() throws RemoteException;

    MediaMetadataCompat getMetadata() throws RemoteException;

    String getPackageName() throws RemoteException;

    PlaybackStateCompat getPlaybackState() throws RemoteException;

    List<MediaSessionCompat.QueueItem> getQueue() throws RemoteException;

    CharSequence getQueueTitle() throws RemoteException;

    int getRatingType() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    Bundle getSessionInfo() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    String getTag() throws RemoteException;

    ParcelableVolumeInfo getVolumeAttributes() throws RemoteException;

    boolean isCaptioningEnabled() throws RemoteException;

    boolean isShuffleModeEnabledRemoved() throws RemoteException;

    boolean isTransportControlEnabled() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void playFromMediaId(String str, Bundle bundle) throws RemoteException;

    void playFromSearch(String str, Bundle bundle) throws RemoteException;

    void playFromUri(Uri uri, Bundle bundle) throws RemoteException;

    void prepare() throws RemoteException;

    void prepareFromMediaId(String str, Bundle bundle) throws RemoteException;

    void prepareFromSearch(String str, Bundle bundle) throws RemoteException;

    void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException;

    void previous() throws RemoteException;

    void rate(RatingCompat ratingCompat) throws RemoteException;

    void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException;

    void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void removeQueueItemAt(int i) throws RemoteException;

    void rewind() throws RemoteException;

    void seekTo(long j) throws RemoteException;

    void sendCommand(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper) throws RemoteException;

    void sendCustomAction(String str, Bundle bundle) throws RemoteException;

    boolean sendMediaButton(KeyEvent keyEvent) throws RemoteException;

    void setCaptioningEnabled(boolean z) throws RemoteException;

    void setPlaybackSpeed(float f) throws RemoteException;

    void setRepeatMode(int i) throws RemoteException;

    void setShuffleMode(int i) throws RemoteException;

    void setShuffleModeEnabledRemoved(boolean z) throws RemoteException;

    void setVolumeTo(int i, int i2, String str) throws RemoteException;

    void skipToQueueItem(long j) throws RemoteException;

    void stop() throws RemoteException;

    void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IMediaSession {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
        static final int TRANSACTION_addQueueItem = 41;
        static final int TRANSACTION_addQueueItemAt = 42;
        static final int TRANSACTION_adjustVolume = 11;
        static final int TRANSACTION_fastForward = 22;
        static final int TRANSACTION_getExtras = 31;
        static final int TRANSACTION_getFlags = 9;
        static final int TRANSACTION_getLaunchPendingIntent = 8;
        static final int TRANSACTION_getMetadata = 27;
        static final int TRANSACTION_getPackageName = 6;
        static final int TRANSACTION_getPlaybackState = 28;
        static final int TRANSACTION_getQueue = 29;
        static final int TRANSACTION_getQueueTitle = 30;
        static final int TRANSACTION_getRatingType = 32;
        static final int TRANSACTION_getRepeatMode = 37;
        static final int TRANSACTION_getSessionInfo = 50;
        static final int TRANSACTION_getShuffleMode = 47;
        static final int TRANSACTION_getTag = 7;
        static final int TRANSACTION_getVolumeAttributes = 10;
        static final int TRANSACTION_isCaptioningEnabled = 45;
        static final int TRANSACTION_isShuffleModeEnabledRemoved = 38;
        static final int TRANSACTION_isTransportControlEnabled = 5;
        static final int TRANSACTION_next = 20;
        static final int TRANSACTION_pause = 18;
        static final int TRANSACTION_play = 13;
        static final int TRANSACTION_playFromMediaId = 14;
        static final int TRANSACTION_playFromSearch = 15;
        static final int TRANSACTION_playFromUri = 16;
        static final int TRANSACTION_prepare = 33;
        static final int TRANSACTION_prepareFromMediaId = 34;
        static final int TRANSACTION_prepareFromSearch = 35;
        static final int TRANSACTION_prepareFromUri = 36;
        static final int TRANSACTION_previous = 21;
        static final int TRANSACTION_rate = 25;
        static final int TRANSACTION_rateWithExtras = 51;
        static final int TRANSACTION_registerCallbackListener = 3;
        static final int TRANSACTION_removeQueueItem = 43;
        static final int TRANSACTION_removeQueueItemAt = 44;
        static final int TRANSACTION_rewind = 23;
        static final int TRANSACTION_seekTo = 24;
        static final int TRANSACTION_sendCommand = 1;
        static final int TRANSACTION_sendCustomAction = 26;
        static final int TRANSACTION_sendMediaButton = 2;
        static final int TRANSACTION_setCaptioningEnabled = 46;
        static final int TRANSACTION_setPlaybackSpeed = 49;
        static final int TRANSACTION_setRepeatMode = 39;
        static final int TRANSACTION_setShuffleMode = 48;
        static final int TRANSACTION_setShuffleModeEnabledRemoved = 40;
        static final int TRANSACTION_setVolumeTo = 12;
        static final int TRANSACTION_skipToQueueItem = 17;
        static final int TRANSACTION_stop = 19;
        static final int TRANSACTION_unregisterCallbackListener = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMediaSession)) {
                return (IMediaSession) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                ((Parcel) Assertions.checkNotNull(parcel2)).writeString("android.support.v4.media.session.IMediaSession");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    sendCommand(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean sendMediaButton = sendMediaButton(parcel.readInt() != 0 ? (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(sendMediaButton ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    registerCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    unregisterCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean isTransportControlEnabled = isTransportControlEnabled();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(isTransportControlEnabled ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String packageName = getPackageName();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeString(packageName);
                    return true;
                case 7:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String tag = getTag();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeString(tag);
                    return true;
                case 8:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PendingIntent launchPendingIntent = getLaunchPendingIntent();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    if (launchPendingIntent != null) {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(1);
                        launchPendingIntent.writeToParcel(parcel2, 1);
                    } else {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    long flags = getFlags();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeLong(flags);
                    return true;
                case 10:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    ParcelableVolumeInfo volumeAttributes = getVolumeAttributes();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    if (volumeAttributes != null) {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(1);
                        volumeAttributes.writeToParcel(parcel2, 1);
                    } else {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    adjustVolume(parcel.readInt(), parcel.readInt(), parcel.readString());
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setVolumeTo(parcel.readInt(), parcel.readInt(), parcel.readString());
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    play();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    playFromMediaId(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    playFromSearch(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    playFromUri(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    skipToQueueItem(parcel.readLong());
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    pause();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    stop();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    next();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    previous();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    fastForward();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    rewind();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    seekTo(parcel.readLong());
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    rate(parcel.readInt() != 0 ? RatingCompat.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    sendCustomAction(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    MediaMetadataCompat metadata = getMetadata();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    if (metadata != null) {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(1);
                        metadata.writeToParcel(parcel2, 1);
                    } else {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(0);
                    }
                    return true;
                case 28:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PlaybackStateCompat playbackState = getPlaybackState();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    if (playbackState != null) {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(1);
                        playbackState.writeToParcel(parcel2, 1);
                    } else {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(0);
                    }
                    return true;
                case 29:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    List<MediaSessionCompat.QueueItem> queue = getQueue();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeTypedList(queue);
                    return true;
                case 30:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    CharSequence queueTitle = getQueueTitle();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    if (queueTitle != null) {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(1);
                        TextUtils.writeToParcel(queueTitle, parcel2, 1);
                    } else {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle extras = getExtras();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    if (extras != null) {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(1);
                        extras.writeToParcel(parcel2, 1);
                    } else {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(0);
                    }
                    return true;
                case 32:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int ratingType = getRatingType();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(ratingType);
                    return true;
                case 33:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    prepare();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    prepareFromMediaId(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    prepareFromSearch(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    prepareFromUri(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int repeatMode = getRepeatMode();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(repeatMode);
                    return true;
                case 38:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean isShuffleModeEnabledRemoved = isShuffleModeEnabledRemoved();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(isShuffleModeEnabledRemoved ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setRepeatMode(parcel.readInt());
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setShuffleModeEnabledRemoved(parcel.readInt() != 0);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    addQueueItem(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    addQueueItemAt(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    removeQueueItem(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    removeQueueItemAt(parcel.readInt());
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean isCaptioningEnabled = isCaptioningEnabled();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(isCaptioningEnabled ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setCaptioningEnabled(parcel.readInt() != 0);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int shuffleMode = getShuffleMode();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(shuffleMode);
                    return true;
                case 48:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setShuffleMode(parcel.readInt());
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setPlaybackSpeed(parcel.readFloat());
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle sessionInfo = getSessionInfo();
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    if (sessionInfo != null) {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(1);
                        sessionInfo.writeToParcel(parcel2, 1);
                    } else {
                        ((Parcel) Assertions.checkNotNull(parcel2)).writeInt(0);
                    }
                    return true;
                case 51:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    rateWithExtras(parcel.readInt() != 0 ? RatingCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    ((Parcel) Assertions.checkNotNull(parcel2)).writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IMediaSession {
            public static IMediaSession defaultImpl;
            private IBinder remote;

            Proxy(IBinder iBinder) {
                this.remote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.remote;
            }

            public String getInterfaceDescriptor() {
                return "android.support.v4.media.session.IMediaSession";
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void sendCommand(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (resultReceiverWrapper != null) {
                        obtain.writeInt(1);
                        resultReceiverWrapper.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).sendCommand(str, bundle, resultReceiverWrapper);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean sendMediaButton(KeyEvent keyEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (keyEvent != null) {
                        obtain.writeInt(1);
                        keyEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).sendMediaButton(keyEvent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (!this.remote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).registerCallbackListener(iMediaControllerCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (!this.remote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).unregisterCallbackListener(iMediaControllerCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean isTransportControlEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).isTransportControlEnabled();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public String getPackageName() throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getPackageName();
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public String getTag() throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getTag();
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public PendingIntent getLaunchPendingIntent() throws RemoteException {
                PendingIntent pendingIntent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        pendingIntent = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getLaunchPendingIntent();
                    } else {
                        obtain2.readException();
                        pendingIntent = obtain2.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return pendingIntent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public long getFlags() throws RemoteException {
                long readLong;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readLong = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getFlags();
                    } else {
                        obtain2.readException();
                        readLong = obtain2.readLong();
                    }
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public ParcelableVolumeInfo getVolumeAttributes() throws RemoteException {
                ParcelableVolumeInfo createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getVolumeAttributes();
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ParcelableVolumeInfo.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void adjustVolume(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    if (!this.remote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).adjustVolume(i, i2, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setVolumeTo(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    if (!this.remote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setVolumeTo(i, i2, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public MediaMetadataCompat getMetadata() throws RemoteException {
                MediaMetadataCompat createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getMetadata();
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? MediaMetadataCompat.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public PlaybackStateCompat getPlaybackState() throws RemoteException {
                PlaybackStateCompat createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getPlaybackState();
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public List<MediaSessionCompat.QueueItem> getQueue() throws RemoteException {
                List<MediaSessionCompat.QueueItem> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getQueue();
                    } else {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public CharSequence getQueueTitle() throws RemoteException {
                CharSequence charSequence;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        charSequence = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getQueueTitle();
                    } else {
                        obtain2.readException();
                        charSequence = obtain2.readInt() != 0 ? (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(obtain2) : null;
                    }
                    return charSequence;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public Bundle getExtras() throws RemoteException {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getExtras();
                    } else {
                        obtain2.readException();
                        bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getRatingType() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getRatingType();
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean isCaptioningEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(45, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).isCaptioningEnabled();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getRepeatMode() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getRepeatMode();
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean isShuffleModeEnabledRemoved() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).isShuffleModeEnabledRemoved();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getShuffleMode() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(47, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getShuffleMode();
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaDescriptionCompat != null) {
                        obtain.writeInt(1);
                        mediaDescriptionCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(41, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).addQueueItem(mediaDescriptionCompat);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaDescriptionCompat != null) {
                        obtain.writeInt(1);
                        mediaDescriptionCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (!this.remote.transact(42, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).addQueueItemAt(mediaDescriptionCompat, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaDescriptionCompat != null) {
                        obtain.writeInt(1);
                        mediaDescriptionCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(43, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).removeQueueItem(mediaDescriptionCompat);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void removeQueueItemAt(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    if (!this.remote.transact(44, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).removeQueueItemAt(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public Bundle getSessionInfo() throws RemoteException {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(50, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle = ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getSessionInfo();
                    } else {
                        obtain2.readException();
                        bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void prepare() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).prepare();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void prepareFromMediaId(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).prepareFromMediaId(str, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void prepareFromSearch(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).prepareFromSearch(str, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).prepareFromUri(uri, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void play() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).play();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).playFromMediaId(str, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void playFromSearch(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).playFromSearch(str, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void playFromUri(Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).playFromUri(uri, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void skipToQueueItem(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeLong(j);
                    if (!this.remote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).skipToQueueItem(j);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).pause();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void stop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).stop();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void next() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).next();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void previous() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).previous();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void fastForward() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).fastForward();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void rewind() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).rewind();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void seekTo(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeLong(j);
                    if (!this.remote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).seekTo(j);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void rate(RatingCompat ratingCompat) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (ratingCompat != null) {
                        obtain.writeInt(1);
                        ratingCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).rate(ratingCompat);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (ratingCompat != null) {
                        obtain.writeInt(1);
                        ratingCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(51, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).rateWithExtras(ratingCompat, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setPlaybackSpeed(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeFloat(f);
                    if (!this.remote.transact(49, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setPlaybackSpeed(f);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setCaptioningEnabled(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.remote.transact(46, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setCaptioningEnabled(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setRepeatMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    if (!this.remote.transact(39, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setRepeatMode(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setShuffleModeEnabledRemoved(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.remote.transact(40, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setShuffleModeEnabledRemoved(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setShuffleMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    if (!this.remote.transact(48, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setShuffleMode(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.remote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).sendCustomAction(str, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaSession iMediaSession) {
            if (Proxy.defaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaSession == null) {
                return false;
            }
            Proxy.defaultImpl = iMediaSession;
            return true;
        }

        public static IMediaSession getDefaultImpl() {
            return Proxy.defaultImpl;
        }
    }
}
