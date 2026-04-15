package com.google.android.gms.cloudmessaging;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
/* loaded from: classes2.dex */
final class zzr extends zzs {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(int i, int i2, Bundle bundle) {
        super(i, i2, bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.cloudmessaging.zzs
    public final void zza(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            zzd(null);
        } else {
            zzc(new zzt(4, "Invalid response to one way request", null));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.cloudmessaging.zzs
    public final boolean zzb() {
        return true;
    }
}
