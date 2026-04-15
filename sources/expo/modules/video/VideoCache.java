package expo.modules.video;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.util.Log;
import androidx.media3.database.DatabaseProvider;
import androidx.media3.database.StandaloneDatabaseProvider;
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor;
import androidx.media3.datasource.cache.SimpleCache;
import expo.modules.kotlin.exception.Exceptions;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.apache.commons.io.FileUtils;

/* compiled from: VideoCache.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018J\u0006\u0010\u001c\u001a\u00020\u0018J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0006\u0010!\u001a\u00020\u001aJ\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u001eH\u0002J\b\u0010$\u001a\u00020\u001aH\u0002R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00030\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \b*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lexpo/modules/video/VideoCache;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "weakContext", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "databaseProvider", "Landroidx/media3/database/DatabaseProvider;", "sharedPreferences", "Landroid/content/SharedPreferences;", "cacheEvictor", "Landroidx/media3/datasource/cache/LeastRecentlyUsedCacheEvictor;", "instance", "Landroidx/media3/datasource/cache/SimpleCache;", "getInstance", "()Landroidx/media3/datasource/cache/SimpleCache;", "setInstance", "(Landroidx/media3/datasource/cache/SimpleCache;)V", "getMaxCacheSize", "", "setMaxCacheSize", "", "size", "getCurrentCacheSize", "getCacheDir", "Ljava/io/File;", "generateCacheDirName", "", "clear", "getFileSize", "file", "assertModificationReleaseConditions", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoCache {
    private LeastRecentlyUsedCacheEvictor cacheEvictor;
    private final DatabaseProvider databaseProvider;
    private SimpleCache instance;
    private final SharedPreferences sharedPreferences;
    private final WeakReference<Context> weakContext;

    public VideoCache(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.weakContext = new WeakReference<>(context);
        StandaloneDatabaseProvider standaloneDatabaseProvider = new StandaloneDatabaseProvider(context);
        this.databaseProvider = standaloneDatabaseProvider;
        this.sharedPreferences = context.getSharedPreferences("ExpoVideoCache", 0);
        this.cacheEvictor = new LeastRecentlyUsedCacheEvictor(getMaxCacheSize());
        this.instance = new SimpleCache(getCacheDir(), this.cacheEvictor, standaloneDatabaseProvider);
    }

    private final Context getContext() {
        Context context = this.weakContext.get();
        if (context != null) {
            return context;
        }
        throw new Exceptions.ReactContextLost();
    }

    public final SimpleCache getInstance() {
        return this.instance;
    }

    public final void setInstance(SimpleCache simpleCache) {
        Intrinsics.checkNotNullParameter(simpleCache, "<set-?>");
        this.instance = simpleCache;
    }

    private final long getMaxCacheSize() {
        return this.sharedPreferences.getLong("cacheSize", FileUtils.ONE_GB);
    }

    public final void setMaxCacheSize(long size) {
        assertModificationReleaseConditions();
        this.instance.release();
        this.sharedPreferences.edit().putLong("cacheSize", size).apply();
        this.cacheEvictor = new LeastRecentlyUsedCacheEvictor(size);
        this.instance = new SimpleCache(getCacheDir(), this.cacheEvictor, this.databaseProvider);
    }

    public final long getCurrentCacheSize() {
        return getFileSize(getCacheDir());
    }

    private final File getCacheDir() {
        String string = this.sharedPreferences.getString("cacheDir", null);
        if (string == null) {
            string = generateCacheDirName();
            this.sharedPreferences.edit().putString("cacheDir", string).apply();
        }
        File file = new File(new File(getContext().getCacheDir(), "ExpoVideoCache"), string);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private final String generateCacheDirName() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        return uuid;
    }

    public final void clear() {
        assertModificationReleaseConditions();
        File cacheDir = getCacheDir();
        SimpleCache simpleCache = this.instance;
        this.sharedPreferences.edit().putString("cacheDir", generateCacheDirName()).apply();
        this.instance = new SimpleCache(getCacheDir(), this.cacheEvictor, this.databaseProvider);
        simpleCache.release();
        FilesKt.deleteRecursively(cacheDir);
    }

    private final long getFileSize(File file) {
        return SequencesKt.sumOfLong(SequencesKt.map(SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: expo.modules.video.VideoCache$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean fileSize$lambda$1;
                fileSize$lambda$1 = VideoCache.getFileSize$lambda$1((File) obj);
                return Boolean.valueOf(fileSize$lambda$1);
            }
        }), new Function1() { // from class: expo.modules.video.VideoCache$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long fileSize$lambda$2;
                fileSize$lambda$2 = VideoCache.getFileSize$lambda$2((File) obj);
                return Long.valueOf(fileSize$lambda$2);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getFileSize$lambda$1(File it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.isFile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getFileSize$lambda$2(File it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.length();
    }

    private final void assertModificationReleaseConditions() {
        if (VideoManager.INSTANCE.hasRegisteredPlayers()) {
            throw new VideoCacheException("Cannot clear cache while there are active players", null, 2, null);
        }
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            Log.w("ExpoVideo", "Clearing cache on the main thread, this might cause performance issues");
        }
    }
}
