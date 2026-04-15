package androidx.media3.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.media3.session.IMediaController;

/* loaded from: classes.dex */
public interface IMediaSessionService extends IInterface {
    public static final String DESCRIPTOR = "androidx.media3.session.IMediaSessionService";

    /* loaded from: classes.dex */
    public static class Default implements IMediaSessionService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.media3.session.IMediaSessionService
        public void connect(IMediaController iMediaController, Bundle bundle) throws RemoteException {
        }
    }

    void connect(IMediaController iMediaController, Bundle bundle) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IMediaSessionService {
        static final int TRANSACTION_connect = 3001;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMediaSessionService.DESCRIPTOR);
        }

        public static IMediaSessionService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMediaSessionService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMediaSessionService)) {
                return (IMediaSessionService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMediaSessionService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IMediaSessionService.DESCRIPTOR);
                return true;
            }
            if (i == 3001) {
                connect(IMediaController.Stub.asInterface(parcel.readStrongBinder()), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IMediaSessionService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMediaSessionService.DESCRIPTOR;
            }

            @Override // androidx.media3.session.IMediaSessionService
            public void connect(IMediaController iMediaController, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSessionService.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(3001, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
