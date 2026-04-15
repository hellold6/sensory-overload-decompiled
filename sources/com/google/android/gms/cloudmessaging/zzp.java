package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
/* loaded from: classes2.dex */
public final class zzp implements ServiceConnection {
    zzq zzc;
    final /* synthetic */ zzv zzf;
    int zza = 0;
    final Messenger zzb = new Messenger(new com.google.android.gms.internal.cloudmessaging.zzf(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.gms.cloudmessaging.zzm
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i = message.arg1;
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                Log.d("MessengerIpcClient", "Received response to request: " + i);
            }
            zzp zzpVar = zzp.this;
            synchronized (zzpVar) {
                zzs zzsVar = (zzs) zzpVar.zze.get(i);
                if (zzsVar == null) {
                    Log.w("MessengerIpcClient", "Received response for unknown request: " + i);
                    return true;
                }
                zzpVar.zze.remove(i);
                zzpVar.zzf();
                Bundle data = message.getData();
                if (data.getBoolean("unsupported", false)) {
                    zzsVar.zzc(new zzt(4, "Not supported by GmsCore", null));
                    return true;
                }
                zzsVar.zza(data);
                return true;
            }
        }
    }));
    final Queue zzd = new ArrayDeque();
    final SparseArray zze = new SparseArray();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzp(zzv zzvVar, zzo zzoVar) {
        this.zzf = zzvVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        zzv.zze(this.zzf).execute(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzi
            @Override // java.lang.Runnable
            public final void run() {
                zzp zzpVar = zzp.this;
                IBinder iBinder2 = iBinder;
                synchronized (zzpVar) {
                    try {
                        if (iBinder2 == null) {
                            zzpVar.zza(0, "Null service connection");
                            return;
                        }
                        try {
                            zzpVar.zzc = new zzq(iBinder2);
                            zzpVar.zza = 2;
                            zzpVar.zzc();
                        } catch (RemoteException e) {
                            zzpVar.zza(0, e.getMessage());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zzv.zze(this.zzf).execute(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzl
            @Override // java.lang.Runnable
            public final void run() {
                zzp.this.zza(2, "Service disconnected");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i, String str) {
        zzb(i, str, null);
    }

    final synchronized void zzb(int i, String str, Throwable th) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", "Disconnected: ".concat(String.valueOf(str)));
        }
        int i2 = this.zza;
        if (i2 == 0) {
            throw new IllegalStateException();
        }
        if (i2 != 1 && i2 != 2) {
            if (i2 != 3) {
                return;
            }
            this.zza = 4;
            return;
        }
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Unbinding service");
        }
        this.zza = 4;
        ConnectionTracker.getInstance().unbindService(zzv.zza(this.zzf), this);
        zzt zztVar = new zzt(i, str, th);
        Iterator it = this.zzd.iterator();
        while (it.hasNext()) {
            ((zzs) it.next()).zzc(zztVar);
        }
        this.zzd.clear();
        for (int i3 = 0; i3 < this.zze.size(); i3++) {
            ((zzs) this.zze.valueAt(i3)).zzc(zztVar);
        }
        this.zze.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc() {
        zzv.zze(this.zzf).execute(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzj
            @Override // java.lang.Runnable
            public final void run() {
                final zzs zzsVar;
                while (true) {
                    final zzp zzpVar = zzp.this;
                    synchronized (zzpVar) {
                        if (zzpVar.zza != 2) {
                            return;
                        }
                        if (!zzpVar.zzd.isEmpty()) {
                            zzsVar = (zzs) zzpVar.zzd.poll();
                            zzpVar.zze.put(zzsVar.zza, zzsVar);
                            zzv.zze(zzpVar.zzf).schedule(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzn
                                @Override // java.lang.Runnable
                                public final void run() {
                                    zzp.this.zze(zzsVar.zza);
                                }
                            }, 30L, TimeUnit.SECONDS);
                        } else {
                            zzpVar.zzf();
                            return;
                        }
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        Log.d("MessengerIpcClient", "Sending ".concat(String.valueOf(String.valueOf(zzsVar))));
                    }
                    zzv zzvVar = zzpVar.zzf;
                    Messenger messenger = zzpVar.zzb;
                    int i = zzsVar.zzc;
                    Context zza = zzv.zza(zzvVar);
                    Message obtain = Message.obtain();
                    obtain.what = i;
                    obtain.arg1 = zzsVar.zza;
                    obtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", zzsVar.zzb());
                    bundle.putString("pkg", zza.getPackageName());
                    bundle.putBundle("data", zzsVar.zzd);
                    obtain.setData(bundle);
                    try {
                        zzpVar.zzc.zza(obtain);
                    } catch (RemoteException e) {
                        zzpVar.zza(2, e.getMessage());
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzd() {
        if (this.zza == 1) {
            zza(1, "Timed out while binding");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zze(int i) {
        zzs zzsVar = (zzs) this.zze.get(i);
        if (zzsVar != null) {
            Log.w("MessengerIpcClient", "Timing out request: " + i);
            this.zze.remove(i);
            zzsVar.zzc(new zzt(3, "Timed out waiting for response", null));
            zzf();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzf() {
        if (this.zza == 2 && this.zzd.isEmpty() && this.zze.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.zza = 3;
            ConnectionTracker.getInstance().unbindService(zzv.zza(this.zzf), this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zzg(zzs zzsVar) {
        int i = this.zza;
        if (i != 0) {
            if (i == 1) {
                this.zzd.add(zzsVar);
                return true;
            }
            if (i != 2) {
                return false;
            }
            this.zzd.add(zzsVar);
            zzc();
            return true;
        }
        this.zzd.add(zzsVar);
        Preconditions.checkState(this.zza == 0);
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Starting bind to GmsCore");
        }
        this.zza = 1;
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gms");
        try {
            if (ConnectionTracker.getInstance().bindService(zzv.zza(this.zzf), intent, this, 1)) {
                zzv.zze(this.zzf).schedule(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzk
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzp.this.zzd();
                    }
                }, 30L, TimeUnit.SECONDS);
            } else {
                zza(0, "Unable to bind to service");
            }
        } catch (SecurityException e) {
            zzb(0, "Unable to bind to service", e);
        }
        return true;
    }
}
