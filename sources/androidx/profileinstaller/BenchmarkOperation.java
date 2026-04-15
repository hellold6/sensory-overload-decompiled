package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import androidx.profileinstaller.ProfileInstallReceiver;
import java.io.File;

/* loaded from: classes.dex */
class BenchmarkOperation {
    private BenchmarkOperation() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void dropShaderCache(Context context, ProfileInstallReceiver.ResultDiagnostics resultDiagnostics) {
        File codeCacheDir;
        if (Build.VERSION.SDK_INT >= 34) {
            codeCacheDir = Api24ContextHelper.createDeviceProtectedStorageContext(context).getCacheDir();
        } else {
            codeCacheDir = Api21ContextHelper.getCodeCacheDir(Api24ContextHelper.createDeviceProtectedStorageContext(context));
        }
        if (deleteFilesRecursively(codeCacheDir)) {
            resultDiagnostics.onResultReceived(14, null);
        } else {
            resultDiagnostics.onResultReceived(15, null);
        }
    }

    static boolean deleteFilesRecursively(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return false;
            }
            boolean z = true;
            for (File file2 : listFiles) {
                z = deleteFilesRecursively(file2) && z;
            }
            return z;
        }
        file.delete();
        return true;
    }

    /* loaded from: classes.dex */
    private static class Api21ContextHelper {
        private Api21ContextHelper() {
        }

        static File getCodeCacheDir(Context context) {
            return context.getCodeCacheDir();
        }
    }

    /* loaded from: classes.dex */
    private static class Api24ContextHelper {
        private Api24ContextHelper() {
        }

        static Context createDeviceProtectedStorageContext(Context context) {
            return context.createDeviceProtectedStorageContext();
        }
    }
}
