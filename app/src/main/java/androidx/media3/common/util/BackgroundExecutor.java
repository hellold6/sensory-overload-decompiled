package androidx.media3.common.util;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class BackgroundExecutor {
    private static Executor staticInstance;

    public static synchronized Executor get() {
        Executor executor;
        synchronized (BackgroundExecutor.class) {
            if (staticInstance == null) {
                staticInstance = Util.newSingleThreadExecutor("ExoPlayer:BackgroundExecutor");
            }
            executor = staticInstance;
        }
        return executor;
    }

    public static synchronized void set(Executor executor) {
        synchronized (BackgroundExecutor.class) {
            staticInstance = executor;
        }
    }

    private BackgroundExecutor() {
    }
}
