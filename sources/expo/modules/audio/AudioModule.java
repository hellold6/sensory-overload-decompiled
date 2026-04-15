package expo.modules.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.dash.DashMediaSource;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.smoothstreaming.SsMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import com.facebook.common.util.UriUtil;
import com.google.firebase.messaging.Constants;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.modules.Module;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import okhttp3.OkHttpClient;

/* compiled from: AudioModule.kt */
@kotlin.Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 A2\u00020\u0001:\u0001AB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001e\u001a\u00020\u0013H\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\b\u0010\"\u001a\u00020#H\u0016J\u0014\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u001e\u0010(\u001a\u00020)2\u0014\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010+H\u0002J\u0010\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020.H\u0002J\u0018\u0010/\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020.2\u0006\u00100\u001a\u00020\u000eH\u0002J\u0010\u00101\u001a\u00020.2\u0006\u00102\u001a\u00020\u000eH\u0002J\u0010\u00103\u001a\u00020 2\u0006\u00104\u001a\u00020\u0013H\u0002J\u0010\u00105\u001a\u0002062\u0006\u0010-\u001a\u00020.H\u0002J\u0018\u00107\u001a\u00020%2\u0006\u00108\u001a\u00020)2\u0006\u00109\u001a\u00020:H\u0002J!\u0010;\u001a\u0002H<\"\u0004\b\u0000\u0010<2\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H<0>H\u0002¢\u0006\u0002\u0010?J\b\u0010@\u001a\u00020 H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lexpo/modules/audio/AudioModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "audioManager", "Landroid/media/AudioManager;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "httpClient", "Lokhttp3/OkHttpClient;", "players", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lexpo/modules/audio/AudioPlayer;", "recorders", "Lexpo/modules/audio/AudioRecorder;", "staysActiveInBackground", "", "audioEnabled", "shouldRouteThroughEarpiece", "focusAcquired", "interruptionMode", "Lexpo/modules/audio/InterruptionMode;", "allowsBackgroundRecording", "audioFocusRequest", "Landroid/media/AudioFocusRequest;", "audioFocusChangeListener", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "shouldReleaseFocus", "requestAudioFocus", "", "releaseAudioFocus", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "createMediaItem", "Landroidx/media3/exoplayer/source/MediaSource;", Constants.ScionAnalytics.PARAM_SOURCE, "Lexpo/modules/audio/AudioSource;", "httpDataSourceFactory", "Landroidx/media3/datasource/DataSource$Factory;", "headers", "", "isRawResource", "uri", "Landroid/net/Uri;", "getResourceName", "fallback", "getRawResourceURI", "file", "updatePlaySoundThroughEarpiece", "playThroughEarpiece", "retrieveStreamType", "", "buildMediaSourceFactory", "factory", "mediaItem", "Landroidx/media3/common/MediaItem;", "runOnMain", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "checkRecordingPermission", "Companion", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioModule extends Module {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private boolean allowsBackgroundRecording;
    private AudioFocusRequest audioFocusRequest;
    private AudioManager audioManager;
    private boolean focusAcquired;
    private InterruptionMode interruptionMode;
    private boolean shouldRouteThroughEarpiece;
    private boolean staysActiveInBackground;
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ConcurrentHashMap<String, AudioPlayer> players = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AudioRecorder> recorders = new ConcurrentHashMap<>();
    private boolean audioEnabled = true;
    private final AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: expo.modules.audio.AudioModule$$ExternalSyntheticLambda0
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public final void onAudioFocusChange(int i) {
            AudioModule.audioFocusChangeListener$lambda$0(AudioModule.this, i);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void audioFocusChangeListener$lambda$0(AudioModule audioModule, int i) {
        BuildersKt__Builders_commonKt.launch$default(audioModule.getAppContext().getMainQueue(), null, null, new AudioModule$audioFocusChangeListener$1$1(i, audioModule, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldReleaseFocus() {
        Collection<AudioPlayer> values = this.players.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        Collection<AudioPlayer> collection = values;
        if (collection.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (((AudioPlayer) it.next()).getRef().isPlaying()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestAudioFocus() {
        if (this.focusAcquired || !this.audioEnabled) {
            return;
        }
        AudioManager audioManager = null;
        AudioManager audioManager2 = null;
        Integer valueOf = null;
        if (Build.VERSION.SDK_INT >= 26) {
            InterruptionMode interruptionMode = this.interruptionMode;
            AudioFocusRequest.Builder builder = new AudioFocusRequest.Builder((interruptionMode == null || interruptionMode == InterruptionMode.DO_NOT_MIX) ? 1 : 3);
            builder.setAudioAttributes(new AudioAttributes.Builder().setContentType(2).build());
            builder.setAcceptsDelayedFocusGain(true);
            builder.setOnAudioFocusChangeListener(this.audioFocusChangeListener);
            AudioFocusRequest build = builder.build();
            this.audioFocusRequest = build;
            if (build != null) {
                AudioManager audioManager3 = this.audioManager;
                if (audioManager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioManager");
                } else {
                    audioManager2 = audioManager3;
                }
                valueOf = Integer.valueOf(audioManager2.requestAudioFocus(build));
            }
        } else {
            AudioManager audioManager4 = this.audioManager;
            if (audioManager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioManager");
            } else {
                audioManager = audioManager4;
            }
            valueOf = Integer.valueOf(audioManager.requestAudioFocus(this.audioFocusChangeListener, 3, 1));
        }
        if (valueOf != null && valueOf.intValue() == 1) {
            this.focusAcquired = true;
        } else {
            Log.e(TAG, "Audio focus request failed with: " + valueOf);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseAudioFocus() {
        if (this.focusAcquired) {
            AudioManager audioManager = null;
            if (Build.VERSION.SDK_INT >= 26) {
                AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
                if (audioFocusRequest != null) {
                    AudioManager audioManager2 = this.audioManager;
                    if (audioManager2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("audioManager");
                    } else {
                        audioManager = audioManager2;
                    }
                    audioManager.abandonAudioFocusRequest(audioFocusRequest);
                }
            } else {
                AudioManager audioManager3 = this.audioManager;
                if (audioManager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioManager");
                } else {
                    audioManager = audioManager3;
                }
                audioManager.abandonAudioFocus(this.audioFocusChangeListener);
            }
            this.focusAcquired = false;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r10v62 expo.modules.kotlin.classcomponent.ClassComponentBuilder, still in use, count: 2, list:
          (r10v62 expo.modules.kotlin.classcomponent.ClassComponentBuilder) from 0x046f: MOVE (r21v0 expo.modules.kotlin.classcomponent.ClassComponentBuilder) = (r10v62 expo.modules.kotlin.classcomponent.ClassComponentBuilder)
          (r10v62 expo.modules.kotlin.classcomponent.ClassComponentBuilder) from 0x045f: MOVE (r21v10 expo.modules.kotlin.classcomponent.ClassComponentBuilder) = (r10v62 expo.modules.kotlin.classcomponent.ClassComponentBuilder)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:80)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:56)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:447)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    @Override // expo.modules.kotlin.modules.Module
    public expo.modules.kotlin.modules.ModuleDefinitionData definition() {
        /*
            Method dump skipped, instructions count: 8105
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.audio.AudioModule.definition():expo.modules.kotlin.modules.ModuleDefinitionData");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaSource createMediaItem(AudioSource source) {
        String uri;
        MediaItem fromUri;
        DefaultDataSource.Factory factory;
        int hashCode;
        if (source == null || (uri = source.getUri()) == null) {
            return null;
        }
        Uri parse = Uri.parse(uri);
        if (isRawResource(parse)) {
            fromUri = MediaItem.fromUri(getRawResourceURI(getResourceName(parse, uri)));
        } else {
            fromUri = MediaItem.fromUri(parse);
        }
        Intrinsics.checkNotNull(fromUri);
        String scheme = parse.getScheme();
        if (scheme != null && ((hashCode = scheme.hashCode()) == 3213448 ? scheme.equals(UriUtil.HTTP_SCHEME) : hashCode == 99617003 && scheme.equals(UriUtil.HTTPS_SCHEME))) {
            factory = httpDataSourceFactory(source.getHeaders());
        } else {
            factory = new DefaultDataSource.Factory(getContext());
        }
        return buildMediaSourceFactory(factory, fromUri);
    }

    private final DataSource.Factory httpDataSourceFactory(Map<String, String> headers) {
        OkHttpDataSource.Factory factory = new OkHttpDataSource.Factory(this.httpClient);
        if (headers != null) {
            factory.setDefaultRequestProperties(headers);
        }
        return factory;
    }

    private final boolean isRawResource(Uri uri) {
        String path;
        return uri.getScheme() == null || (Intrinsics.areEqual(uri.getScheme(), "file") && (path = uri.getPath()) != null && StringsKt.startsWith$default(path, "/android_res/raw/", false, 2, (Object) null));
    }

    private final String getResourceName(Uri uri, String fallback) {
        String path;
        String substringAfterLast$default;
        String substringBeforeLast$default;
        return (uri.getScheme() == null || (path = uri.getPath()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(path, "/", (String) null, 2, (Object) null)) == null || (substringBeforeLast$default = StringsKt.substringBeforeLast$default(substringAfterLast$default, ".", (String) null, 2, (Object) null)) == null) ? fallback : substringBeforeLast$default;
    }

    private final Uri getRawResourceURI(String file) {
        if (getContext().getResources().getIdentifier(file, "raw", getContext().getPackageName()) == 0) {
            Uri fromFile = Uri.fromFile(new File(file));
            Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(...)");
            return fromFile;
        }
        Uri build = new Uri.Builder().scheme(UriUtil.QUALIFIED_RESOURCE_SCHEME).authority(getContext().getPackageName()).appendPath("raw").appendPath(file).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePlaySoundThroughEarpiece(boolean playThroughEarpiece) {
        AudioManager audioManager = this.audioManager;
        AudioManager audioManager2 = null;
        if (audioManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioManager");
            audioManager = null;
        }
        audioManager.setMode(playThroughEarpiece ? 3 : 0);
        AudioManager audioManager3 = this.audioManager;
        if (audioManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioManager");
        } else {
            audioManager2 = audioManager3;
        }
        audioManager2.setSpeakerphoneOn(!playThroughEarpiece);
    }

    private final int retrieveStreamType(Uri uri) {
        return Util.inferContentType(uri);
    }

    private final MediaSource buildMediaSourceFactory(DataSource.Factory factory, MediaItem mediaItem) {
        ProgressiveMediaSource.Factory factory2;
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        Uri uri = localConfiguration != null ? localConfiguration.uri : null;
        Integer valueOf = uri != null ? Integer.valueOf(retrieveStreamType(uri)) : null;
        if (valueOf != null && valueOf.intValue() == 1) {
            factory2 = new SsMediaSource.Factory(factory);
        } else if (valueOf != null && valueOf.intValue() == 0) {
            factory2 = new DashMediaSource.Factory(factory);
        } else if (valueOf != null && valueOf.intValue() == 2) {
            factory2 = new HlsMediaSource.Factory(factory);
        } else {
            if (valueOf == null || valueOf.intValue() != 4) {
                throw new IllegalStateException("Unsupported type: " + valueOf);
            }
            factory2 = new ProgressiveMediaSource.Factory(factory);
        }
        MediaSource createMediaSource = factory2.createMediaSource(mediaItem);
        Intrinsics.checkNotNullExpressionValue(createMediaSource, "createMediaSource(...)");
        return createMediaSource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> T runOnMain(Function0<? extends T> block) {
        return (T) BuildersKt.runBlocking(getAppContext().getMainQueue().getCoroutineContext(), new AudioModule$runOnMain$1(block, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkRecordingPermission() {
        if (ContextCompat.checkSelfPermission(getAppContext().getThrowingActivity().getApplicationContext(), "android.permission.RECORD_AUDIO") != 0) {
            throw new AudioPermissionsException();
        }
    }

    /* compiled from: AudioModule.kt */
    @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/audio/AudioModule$Companion;", "", "<init>", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return AudioModule.TAG;
        }
    }

    static {
        Intrinsics.checkNotNullExpressionValue("AudioModule", "getSimpleName(...)");
        TAG = "AudioModule";
    }
}
