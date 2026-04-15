package expo.modules.audio;

import android.media.AudioDeviceInfo;
import android.os.Bundle;
import java.io.File;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioUtils.kt */
@kotlin.Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"ensureDirExists", "Ljava/io/File;", "dir", "getMapFromDeviceInfo", "Landroid/os/Bundle;", "deviceInfo", "Landroid/media/AudioDeviceInfo;", "expo-audio_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioUtilsKt {
    public static final File ensureDirExists(File dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        if (dir.isDirectory() || dir.mkdirs()) {
            return dir;
        }
        throw new IOException("Couldn't create directory '" + dir + "'");
    }

    public static final Bundle getMapFromDeviceInfo(AudioDeviceInfo deviceInfo) {
        String str;
        Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        Bundle bundle = new Bundle();
        int type = deviceInfo.getType();
        if (type == 3) {
            str = "MicrophoneWired";
        } else if (type == 15) {
            str = "MicrophoneBuiltIn";
        } else if (type == 18) {
            str = "Telephony";
        } else if (type == 7) {
            str = "BluetoothSCO";
        } else if (type == 8) {
            str = "BluetoothA2DP";
        } else {
            str = "Unknown device type";
        }
        bundle.putString("name", deviceInfo.getProductName().toString());
        bundle.putString("type", str);
        bundle.putString("uid", String.valueOf(deviceInfo.getId()));
        return bundle;
    }
}
