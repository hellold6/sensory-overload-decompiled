package expo.modules.audio;

import android.content.Context;
import android.media.audiofx.Visualizer;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.os.EnvironmentCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.messaging.Constants;
import expo.modules.audio.service.AudioControlsService;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.sharedobjects.SharedRef;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: AudioPlayer.kt */
@kotlin.Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u0000 \\2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\\B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010<\u001a\u0004\u0018\u0001072\b\u0010=\u001a\u0004\u0018\u00010\u001c¢\u0006\u0002\u0010>J\u000e\u0010?\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\bJ&\u0010)\u001a\u00020#2\u0006\u0010@\u001a\u00020\u00122\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010BJ\u000e\u0010C\u001a\u00020#2\u0006\u0010*\u001a\u00020+J\u0006\u0010D\u001a\u00020#J\b\u0010E\u001a\u00020#H\u0002J\b\u0010F\u001a\u00020#H\u0002J\u000e\u0010G\u001a\u00020#2\u0006\u0010H\u001a\u00020\u0012J\u000e\u0010I\u001a\u00020#2\u0006\u0010J\u001a\u00020\nJ\u0016\u0010K\u001a\b\u0012\u0004\u0012\u00020\u001c0L2\u0006\u0010M\u001a\u00020NH\u0002J\u0014\u0010O\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010Q0PJ(\u0010R\u001a\u00020#2\u0018\b\u0002\u0010S\u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010Q\u0018\u00010PH\u0082@¢\u0006\u0002\u0010TJ\u0016\u0010U\u001a\u00020#2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u001c0LH\u0002J\u0010\u0010W\u001a\u00020\u000e2\u0006\u0010X\u001a\u00020YH\u0002J\b\u0010Z\u001a\u00020#H\u0002J\b\u0010[\u001a\u00020#H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0019\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R(\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020#\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0014\"\u0004\b)\u0010\u0016R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b2\u00103R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00108\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b9\u0010\u001eR\u0011\u0010:\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b;\u0010\u001e¨\u0006]"}, d2 = {"Lexpo/modules/audio/AudioPlayer;", "Lexpo/modules/kotlin/sharedobjects/SharedRef;", "Landroidx/media3/exoplayer/ExoPlayer;", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", Constants.ScionAnalytics.PARAM_SOURCE, "Landroidx/media3/exoplayer/source/MediaSource;", "updateInterval", "", "<init>", "(Landroid/content/Context;Lexpo/modules/kotlin/AppContext;Landroidx/media3/exoplayer/source/MediaSource;D)V", "id", "", "getId", "()Ljava/lang/String;", "preservesPitch", "", "getPreservesPitch", "()Z", "setPreservesPitch", "(Z)V", "isPaused", "setPaused", "isMuted", "setMuted", "previousVolume", "", "getPreviousVolume", "()F", "setPreviousVolume", "(F)V", "onPlaybackStateChange", "Lkotlin/Function1;", "", "getOnPlaybackStateChange", "()Lkotlin/jvm/functions/Function1;", "setOnPlaybackStateChange", "(Lkotlin/jvm/functions/Function1;)V", "isActiveForLockScreen", "setActiveForLockScreen", TtmlNode.TAG_METADATA, "Lexpo/modules/audio/Metadata;", "playerScope", "Lkotlinx/coroutines/CoroutineScope;", "samplingEnabled", "visualizer", "Landroid/media/audiofx/Visualizer;", "playing", "getContext", "()Landroid/content/Context;", "context$delegate", "Lkotlin/Lazy;", "updateJob", "Lkotlinx/coroutines/Job;", "currentTime", "getCurrentTime", "duration", "getDuration", "setVolume", "volume", "(Ljava/lang/Float;)Lkotlinx/coroutines/Job;", "setMediaSource", AppStateModule.APP_STATE_ACTIVE, "options", "Lexpo/modules/audio/AudioLockScreenOptions;", "updateLockScreenMetadata", "clearLockScreenControls", "startUpdating", "addPlayerListeners", "setSamplingEnabled", ViewProps.ENABLED, "seekTo", "seekTime", "extractAmplitudes", "", "chunk", "", "currentStatus", "", "", "sendPlayerUpdate", "map", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendAudioSampleUpdate", "sample", "playbackStateToString", "state", "", "createVisualizer", "sharedObjectDidRelease", "Companion", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioPlayer extends SharedRef<ExoPlayer> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(AudioPlayer.class).getSimpleName();

    /* renamed from: context$delegate, reason: from kotlin metadata */
    private final Lazy context;
    private final String id;
    private boolean isActiveForLockScreen;
    private boolean isMuted;
    private boolean isPaused;
    private Metadata metadata;
    private Function1<? super Boolean, Unit> onPlaybackStateChange;
    private CoroutineScope playerScope;
    private boolean playing;
    private boolean preservesPitch;
    private float previousVolume;
    private boolean samplingEnabled;
    private final double updateInterval;
    private Job updateJob;
    private Visualizer visualizer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioPlayer(Context context, final AppContext appContext, MediaSource mediaSource, double d) {
        super(new ExoPlayer.Builder(context).setLooper(context.getMainLooper()).setAudioAttributes(AudioAttributes.DEFAULT, false).setSeekForwardIncrementMs(10000L).setSeekBackIncrementMs(10000L).build(), appContext);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.updateInterval = d;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        this.id = uuid;
        this.preservesPitch = true;
        this.previousVolume = 1.0f;
        this.playerScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        this.context = LazyKt.lazy(new Function0() { // from class: expo.modules.audio.AudioPlayer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Context context_delegate$lambda$0;
                context_delegate$lambda$0 = AudioPlayer.context_delegate$lambda$0(AppContext.this);
                return context_delegate$lambda$0;
            }
        });
        addPlayerListeners();
        if (mediaSource != null) {
            setMediaSource(mediaSource);
        }
    }

    public final String getId() {
        return this.id;
    }

    public final boolean getPreservesPitch() {
        return this.preservesPitch;
    }

    public final void setPreservesPitch(boolean z) {
        this.preservesPitch = z;
    }

    /* renamed from: isPaused, reason: from getter */
    public final boolean getIsPaused() {
        return this.isPaused;
    }

    public final void setPaused(boolean z) {
        this.isPaused = z;
    }

    /* renamed from: isMuted, reason: from getter */
    public final boolean getIsMuted() {
        return this.isMuted;
    }

    public final void setMuted(boolean z) {
        this.isMuted = z;
    }

    public final float getPreviousVolume() {
        return this.previousVolume;
    }

    public final void setPreviousVolume(float f) {
        this.previousVolume = f;
    }

    public final Function1<Boolean, Unit> getOnPlaybackStateChange() {
        return this.onPlaybackStateChange;
    }

    public final void setOnPlaybackStateChange(Function1<? super Boolean, Unit> function1) {
        this.onPlaybackStateChange = function1;
    }

    /* renamed from: isActiveForLockScreen, reason: from getter */
    public final boolean getIsActiveForLockScreen() {
        return this.isActiveForLockScreen;
    }

    public final void setActiveForLockScreen(boolean z) {
        this.isActiveForLockScreen = z;
    }

    private final Context getContext() {
        return (Context) this.context.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Context context_delegate$lambda$0(AppContext appContext) {
        Context reactContext = appContext.getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    public final float getCurrentTime() {
        return ((float) getRef().getCurrentPosition()) / 1000.0f;
    }

    public final float getDuration() {
        if (getRef().getDuration() != C.TIME_UNSET) {
            return ((float) getRef().getDuration()) / 1000.0f;
        }
        return 0.0f;
    }

    public final Job setVolume(Float volume) {
        CoroutineScope mainQueue;
        Job launch$default;
        Float normalizedVolume = volume;
        if (volume != null) {
            float max = Math.max(0.0f, Math.min(1.0f, volume.floatValue()));
            if (max > 0.0f) {
                this.previousVolume = max;
            }
            this.isMuted = max == 0.0f;
            normalizedVolume = Float.valueOf(max);
        }
        AppContext appContext = getAppContext();
        if (appContext == null || (mainQueue = appContext.getMainQueue()) == null) {
            return null;
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(mainQueue, null, null, new AudioPlayer$setVolume$1(normalizedVolume, this, null), 3, null);
        return launch$default;
    }

    public final void setMediaSource(MediaSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        getRef().setMediaSource(source);
        getRef().prepare();
        if (this.isMuted || getRef().getVolume() <= 0.0f) {
            float f = this.previousVolume;
            float f2 = f > 0.0f ? f : 0.75f;
            this.isMuted = false;
            getRef().setVolume(f2);
            this.previousVolume = f2;
        }
        startUpdating();
    }

    public static /* synthetic */ void setActiveForLockScreen$default(AudioPlayer audioPlayer, boolean z, Metadata metadata, AudioLockScreenOptions audioLockScreenOptions, int i, Object obj) {
        if ((i & 2) != 0) {
            metadata = null;
        }
        if ((i & 4) != 0) {
            audioLockScreenOptions = null;
        }
        audioPlayer.setActiveForLockScreen(z, metadata, audioLockScreenOptions);
    }

    public final void setActiveForLockScreen(boolean active, Metadata metadata, AudioLockScreenOptions options) {
        if (active) {
            this.metadata = metadata;
            AudioControlsService.INSTANCE.setActivePlayer(getContext(), this, metadata, options);
        } else if (this.isActiveForLockScreen) {
            AudioControlsService.Companion.setActivePlayer$default(AudioControlsService.INSTANCE, getContext(), null, null, null, 12, null);
        }
    }

    public final void updateLockScreenMetadata(Metadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (this.isActiveForLockScreen) {
            this.metadata = metadata;
            AudioControlsService.INSTANCE.updateMetadata(this, metadata);
        }
    }

    public final void clearLockScreenControls() {
        if (this.isActiveForLockScreen) {
            AudioControlsService.Companion.setActivePlayer$default(AudioControlsService.INSTANCE, getContext(), null, null, null, 12, null);
        }
    }

    private final void startUpdating() {
        Job job = this.updateJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.updateJob = FlowKt.launchIn(FlowKt.onEach(FlowKt.onStart(FlowKt.flow(new AudioPlayer$startUpdating$1(this, null)), new AudioPlayer$startUpdating$2(this, null)), new AudioPlayer$startUpdating$3(this, null)), this.playerScope);
    }

    private final void addPlayerListeners() {
        getRef().addListener(new Player.Listener() { // from class: expo.modules.audio.AudioPlayer$addPlayerListeners$1
            @Override // androidx.media3.common.Player.Listener
            public void onIsPlayingChanged(boolean isPlaying) {
                CoroutineScope coroutineScope;
                AudioPlayer.this.playing = isPlaying;
                coroutineScope = AudioPlayer.this.playerScope;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AudioPlayer$addPlayerListeners$1$onIsPlayingChanged$1(AudioPlayer.this, isPlaying, null), 3, null);
                Function1<Boolean, Unit> onPlaybackStateChange = AudioPlayer.this.getOnPlaybackStateChange();
                if (onPlaybackStateChange != null) {
                    onPlaybackStateChange.invoke(Boolean.valueOf(isPlaying));
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onIsLoadingChanged(boolean isLoading) {
                CoroutineScope coroutineScope;
                coroutineScope = AudioPlayer.this.playerScope;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AudioPlayer$addPlayerListeners$1$onIsLoadingChanged$1(AudioPlayer.this, isLoading, null), 3, null);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int playbackState) {
                CoroutineScope coroutineScope;
                coroutineScope = AudioPlayer.this.playerScope;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AudioPlayer$addPlayerListeners$1$onPlaybackStateChanged$1(AudioPlayer.this, playbackState, null), 3, null);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onMediaItemTransition(MediaItem mediaItem, int reason) {
                CoroutineScope coroutineScope;
                coroutineScope = AudioPlayer.this.playerScope;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AudioPlayer$addPlayerListeners$1$onMediaItemTransition$1(AudioPlayer.this, null), 3, null);
            }
        });
    }

    public final void setSamplingEnabled(boolean enabled) {
        Context reactContext;
        AppContext appContext = getAppContext();
        if (appContext != null && (reactContext = appContext.getReactContext()) != null && ContextCompat.checkSelfPermission(reactContext, "android.permission.RECORD_AUDIO") != 0) {
            Log.d(TAG, "'android.permission.RECORD_AUDIO' is required to use audio sampling. Please request this permission and try again.");
            return;
        }
        this.samplingEnabled = enabled;
        if (enabled) {
            createVisualizer();
            return;
        }
        Visualizer visualizer = this.visualizer;
        if (visualizer != null) {
            visualizer.release();
        }
        this.visualizer = null;
    }

    public final void seekTo(double seekTime) {
        getRef().seekTo((long) (seekTime * 1000));
        BuildersKt__Builders_commonKt.launch$default(this.playerScope, null, null, new AudioPlayer$seekTo$1(this, null), 3, null);
    }

    public final Map<String, Object> currentStatus() {
        boolean z = getRef().getVolume() == 0.0f;
        boolean z2 = getRef().getRepeatMode() == 1;
        boolean z3 = getRef().getPlaybackState() == 3;
        boolean z4 = getRef().getPlaybackState() == 2;
        Pair[] pairArr = new Pair[14];
        pairArr[0] = TuplesKt.to("id", this.id);
        pairArr[1] = TuplesKt.to("currentTime", Float.valueOf(getCurrentTime()));
        pairArr[2] = TuplesKt.to("playbackState", playbackStateToString(getRef().getPlaybackState()));
        pairArr[3] = TuplesKt.to("timeControlStatus", getRef().isPlaying() ? "playing" : "paused");
        pairArr[4] = TuplesKt.to("reasonForWaitingToPlay", null);
        pairArr[5] = TuplesKt.to("mute", Boolean.valueOf(z));
        pairArr[6] = TuplesKt.to("duration", Float.valueOf(getDuration()));
        pairArr[7] = TuplesKt.to("playing", Boolean.valueOf(getRef().isPlaying()));
        pairArr[8] = TuplesKt.to("loop", Boolean.valueOf(z2));
        pairArr[9] = TuplesKt.to("didJustFinish", Boolean.valueOf(getRef().getPlaybackState() == 4));
        pairArr[10] = TuplesKt.to("isLoaded", Boolean.valueOf(getRef().getPlaybackState() != 4 ? z3 : true));
        pairArr[11] = TuplesKt.to("playbackRate", Float.valueOf(getRef().getPlaybackParameters().speed));
        pairArr[12] = TuplesKt.to("shouldCorrectPitch", Boolean.valueOf(this.preservesPitch));
        pairArr[13] = TuplesKt.to("isBuffering", Boolean.valueOf(z4));
        return MapsKt.mapOf(pairArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object sendPlayerUpdate$default(AudioPlayer audioPlayer, Map map, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            map = null;
        }
        return audioPlayer.sendPlayerUpdate(map, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object sendPlayerUpdate(Map<String, ? extends Object> map, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new AudioPlayer$sendPlayerUpdate$2(this, map, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAudioSampleUpdate(List<Float> sample) {
        emit("audioSampleUpdate", MapsKt.mapOf(TuplesKt.to(NotificationsChannelGroupSerializer.CHANNELS_KEY, CollectionsKt.listOf(MapsKt.mapOf(TuplesKt.to("frames", sample)))), TuplesKt.to("timestamp", Long.valueOf(getRef().getCurrentPosition()))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String playbackStateToString(int state) {
        if (state == 1) {
            return "idle";
        }
        if (state == 2) {
            return "buffering";
        }
        if (state == 3) {
            return "ready";
        }
        if (state == 4) {
            return "ended";
        }
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    private final void createVisualizer() {
        if (this.visualizer == null) {
            Visualizer visualizer = new Visualizer(getRef().getAudioSessionId());
            visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
            visualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() { // from class: expo.modules.audio.AudioPlayer$createVisualizer$1$1
                @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
                public void onFftDataCapture(Visualizer visualizer2, byte[] fft, int samplingRate) {
                }

                @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
                public void onWaveFormDataCapture(Visualizer visualizer2, byte[] waveform, int samplingRate) {
                    boolean z;
                    List extractAmplitudes;
                    if (waveform != null) {
                        AudioPlayer audioPlayer = AudioPlayer.this;
                        z = audioPlayer.samplingEnabled;
                        if (z && audioPlayer.getRef().isPlaying()) {
                            extractAmplitudes = audioPlayer.extractAmplitudes(waveform);
                            audioPlayer.sendAudioSampleUpdate(extractAmplitudes);
                        }
                    }
                }
            }, Visualizer.getMaxCaptureRate() / 2, true, false);
            visualizer.setEnabled(true);
            this.visualizer = visualizer;
        }
    }

    @Override // expo.modules.kotlin.sharedobjects.SharedObject
    public void sharedObjectDidRelease() {
        CoroutineScope mainQueue;
        super.sharedObjectDidRelease();
        AppContext appContext = getAppContext();
        if (appContext == null || (mainQueue = appContext.getMainQueue()) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(mainQueue, null, null, new AudioPlayer$sharedObjectDidRelease$1(this, null), 3, null);
    }

    /* compiled from: AudioPlayer.kt */
    @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/audio/AudioPlayer$Companion;", "", "<init>", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return AudioPlayer.TAG;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Float> extractAmplitudes(byte[] chunk) {
        ArrayList arrayList = new ArrayList(chunk.length);
        for (byte b : chunk) {
            arrayList.add(Float.valueOf((float) (((b & 255) - 128) / 128.0d)));
        }
        return arrayList;
    }
}
