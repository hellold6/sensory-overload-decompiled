package expo.modules.video;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import expo.modules.kotlin.AppContext;
import expo.modules.video.enums.AudioMixingMode;
import expo.modules.video.enums.PlayerStatus;
import expo.modules.video.player.VideoPlayer;
import expo.modules.video.player.VideoPlayerListener;
import expo.modules.video.records.AudioTrack;
import expo.modules.video.records.PlaybackError;
import expo.modules.video.records.SubtitleTrack;
import expo.modules.video.records.TimeUpdate;
import expo.modules.video.records.VideoSource;
import expo.modules.video.records.VideoTrack;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: AudioFocusManager.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0010J\u000e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0010J\"\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00142\b\u0010!\u001a\u0004\u0018\u00010\u0014H\u0016J'\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010%J'\u0010&\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010(H\u0016¢\u0006\u0002\u0010*J'\u0010+\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u00162\b\u0010-\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010%J\u0010\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u000200H\u0016J\u0016\u00101\u001a\u00020\u00162\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u0016\u00103\u001a\u00020\u001a2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u0016\u00104\u001a\u00020\u001a2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u0016\u00105\u001a\u00020\u001a2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\b\u00106\u001a\u00020\u001aH\u0002J\b\u00107\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u00068"}, d2 = {"Lexpo/modules/video/AudioFocusManager;", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "Lexpo/modules/video/player/VideoPlayerListener;", "appContext", "Lexpo/modules/kotlin/AppContext;", "<init>", "(Lexpo/modules/kotlin/AppContext;)V", "audioManager", "Landroid/media/AudioManager;", "getAudioManager", "()Landroid/media/AudioManager;", "audioManager$delegate", "Lkotlin/Lazy;", "players", "", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/video/player/VideoPlayer;", "currentFocusRequest", "Landroid/media/AudioFocusRequest;", "currentMixingMode", "Lexpo/modules/video/enums/AudioMixingMode;", "anyPlayerRequiresFocus", "", "getAnyPlayerRequiresFocus", "()Z", "requestAudioFocus", "", "abandonAudioFocus", "registerPlayer", "player", "unregisterPlayer", "onAudioMixingModeChanged", "audioMixingMode", "oldAudioMixingMode", "onIsPlayingChanged", "isPlaying", "oldIsPlaying", "(Lexpo/modules/video/player/VideoPlayer;ZLjava/lang/Boolean;)V", "onVolumeChanged", "volume", "", "oldVolume", "(Lexpo/modules/video/player/VideoPlayer;FLjava/lang/Float;)V", "onMutedChanged", "muted", "oldMuted", "onAudioFocusChange", "focusChange", "", "playerRequiresFocus", "weakPlayer", "pausePlayerIfUnmuted", "duckPlayer", "unduckPlayer", "updateAudioFocus", "findAudioMixingMode", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioFocusManager implements AudioManager.OnAudioFocusChangeListener, VideoPlayerListener {
    private final AppContext appContext;

    /* renamed from: audioManager$delegate, reason: from kotlin metadata */
    private final Lazy audioManager;
    private AudioFocusRequest currentFocusRequest;
    private AudioMixingMode currentMixingMode;
    private List<WeakReference<VideoPlayer>> players;

    /* compiled from: AudioFocusManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AudioMixingMode.values().length];
            try {
                iArr[AudioMixingMode.DUCK_OTHERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AudioMixingMode.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AudioMixingMode.DO_NOT_MIX.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onPlaybackRateChanged(VideoPlayer videoPlayer, float f, Float f2) {
        VideoPlayerListener.DefaultImpls.onPlaybackRateChanged(this, videoPlayer, f, f2);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onPlayedToEnd(VideoPlayer videoPlayer) {
        VideoPlayerListener.DefaultImpls.onPlayedToEnd(this, videoPlayer);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onRenderedFirstFrame(VideoPlayer videoPlayer) {
        VideoPlayerListener.DefaultImpls.onRenderedFirstFrame(this, videoPlayer);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onSourceChanged(VideoPlayer videoPlayer, VideoSource videoSource, VideoSource videoSource2) {
        VideoPlayerListener.DefaultImpls.onSourceChanged(this, videoPlayer, videoSource, videoSource2);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onStatusChanged(VideoPlayer videoPlayer, PlayerStatus playerStatus, PlayerStatus playerStatus2, PlaybackError playbackError) {
        VideoPlayerListener.DefaultImpls.onStatusChanged(this, videoPlayer, playerStatus, playerStatus2, playbackError);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onTimeUpdate(VideoPlayer videoPlayer, TimeUpdate timeUpdate) {
        VideoPlayerListener.DefaultImpls.onTimeUpdate(this, videoPlayer, timeUpdate);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onTrackSelectionParametersChanged(VideoPlayer videoPlayer, TrackSelectionParameters trackSelectionParameters) {
        VideoPlayerListener.DefaultImpls.onTrackSelectionParametersChanged(this, videoPlayer, trackSelectionParameters);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onTracksChanged(VideoPlayer videoPlayer, Tracks tracks) {
        VideoPlayerListener.DefaultImpls.onTracksChanged(this, videoPlayer, tracks);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onVideoSourceLoaded(VideoPlayer videoPlayer, VideoSource videoSource, Double d, List<VideoTrack> list, List<SubtitleTrack> list2, List<AudioTrack> list3) {
        VideoPlayerListener.DefaultImpls.onVideoSourceLoaded(this, videoPlayer, videoSource, d, list, list2, list3);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onVideoTrackChanged(VideoPlayer videoPlayer, VideoTrack videoTrack, VideoTrack videoTrack2) {
        VideoPlayerListener.DefaultImpls.onVideoTrackChanged(this, videoPlayer, videoTrack, videoTrack2);
    }

    public AudioFocusManager(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        this.audioManager = LazyKt.lazy(new Function0() { // from class: expo.modules.video.AudioFocusManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AudioManager audioManager_delegate$lambda$1;
                audioManager_delegate$lambda$1 = AudioFocusManager.audioManager_delegate$lambda$1(AudioFocusManager.this);
                return audioManager_delegate$lambda$1;
            }
        });
        this.players = new ArrayList();
        this.currentMixingMode = AudioMixingMode.MIX_WITH_OTHERS;
    }

    private final AudioManager getAudioManager() {
        return (AudioManager) this.audioManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AudioManager audioManager_delegate$lambda$1(AudioFocusManager audioFocusManager) {
        Context reactContext = audioFocusManager.appContext.getReactContext();
        Object systemService = reactContext != null ? reactContext.getSystemService(MimeTypes.BASE_TYPE_AUDIO) : null;
        AudioManager audioManager = systemService instanceof AudioManager ? (AudioManager) systemService : null;
        if (audioManager != null) {
            return audioManager;
        }
        throw new FailedToGetAudioFocusManagerException();
    }

    private final boolean getAnyPlayerRequiresFocus() {
        List list = CollectionsKt.toList(this.players);
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (playerRequiresFocus((WeakReference) it.next())) {
                return true;
            }
        }
        return false;
    }

    private final void requestAudioFocus() {
        AudioMixingMode findAudioMixingMode = findAudioMixingMode();
        if (findAudioMixingMode == AudioMixingMode.MIX_WITH_OTHERS || !getAnyPlayerRequiresFocus()) {
            abandonAudioFocus();
            this.currentMixingMode = findAudioMixingMode;
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[findAudioMixingMode.ordinal()] != 1 ? 1 : 3;
        if (Build.VERSION.SDK_INT >= 26) {
            AudioFocusRequest audioFocusRequest = this.currentFocusRequest;
            if (audioFocusRequest != null && audioFocusRequest.getFocusGain() == i) {
                return;
            }
            AudioFocusRequest.Builder builder = new AudioFocusRequest.Builder(i);
            AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
            builder2.setUsage(1);
            builder2.setContentType(3);
            builder.setOnAudioFocusChangeListener(this);
            AudioFocusRequest build = builder.setAudioAttributes(builder2.build()).build();
            this.currentFocusRequest = build;
            getAudioManager().requestAudioFocus(build);
        } else {
            getAudioManager().requestAudioFocus(this, 3, i);
        }
        this.currentMixingMode = findAudioMixingMode;
    }

    private final void abandonAudioFocus() {
        AudioFocusRequest audioFocusRequest = this.currentFocusRequest;
        if (audioFocusRequest != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                getAudioManager().abandonAudioFocusRequest(audioFocusRequest);
            } else {
                getAudioManager().abandonAudioFocus(this);
            }
        }
        this.currentFocusRequest = null;
    }

    public final void registerPlayer(VideoPlayer player) {
        Object obj;
        Intrinsics.checkNotNullParameter(player, "player");
        Iterator<T> it = this.players.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((WeakReference) obj).get(), player)) {
                    break;
                }
            }
        }
        if (((WeakReference) obj) == null) {
            Boolean.valueOf(this.players.add(new WeakReference<>(player)));
        }
        player.addListener(this);
        updateAudioFocus();
    }

    public final void unregisterPlayer(final VideoPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        player.removeListener(this);
        CollectionsKt.removeAll((List) this.players, new Function1() { // from class: expo.modules.video.AudioFocusManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean unregisterPlayer$lambda$9;
                unregisterPlayer$lambda$9 = AudioFocusManager.unregisterPlayer$lambda$9(VideoPlayer.this, (WeakReference) obj);
                return Boolean.valueOf(unregisterPlayer$lambda$9);
            }
        });
        updateAudioFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unregisterPlayer$lambda$9(VideoPlayer videoPlayer, WeakReference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it.get(), videoPlayer);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onAudioMixingModeChanged(VideoPlayer player, AudioMixingMode audioMixingMode, AudioMixingMode oldAudioMixingMode) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(audioMixingMode, "audioMixingMode");
        requestAudioFocus();
        VideoPlayerListener.DefaultImpls.onAudioMixingModeChanged(this, player, audioMixingMode, oldAudioMixingMode);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onIsPlayingChanged(VideoPlayer player, boolean isPlaying, Boolean oldIsPlaying) {
        Intrinsics.checkNotNullParameter(player, "player");
        if (!isPlaying && !getAnyPlayerRequiresFocus()) {
            abandonAudioFocus();
        } else if (isPlaying && getAnyPlayerRequiresFocus()) {
            requestAudioFocus();
        }
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onVolumeChanged(VideoPlayer player, float volume, Float oldVolume) {
        Intrinsics.checkNotNullParameter(player, "player");
        updateAudioFocus();
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onMutedChanged(VideoPlayer player, boolean muted, Boolean oldMuted) {
        Intrinsics.checkNotNullParameter(player, "player");
        updateAudioFocus();
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int focusChange) {
        if (focusChange == -3) {
            BuildersKt__Builders_commonKt.launch$default(this.appContext.getMainQueue(), null, null, new AudioFocusManager$onAudioFocusChange$3(this, findAudioMixingMode(), null), 3, null);
            return;
        }
        if (focusChange == -2) {
            if (findAudioMixingMode() == AudioMixingMode.MIX_WITH_OTHERS) {
                return;
            }
            BuildersKt__Builders_commonKt.launch$default(this.appContext.getMainQueue(), null, null, new AudioFocusManager$onAudioFocusChange$2(this, null), 3, null);
        } else if (focusChange == -1) {
            BuildersKt__Builders_commonKt.launch$default(this.appContext.getMainQueue(), null, null, new AudioFocusManager$onAudioFocusChange$1(this, null), 3, null);
        } else {
            if (focusChange != 1) {
                return;
            }
            BuildersKt__Builders_commonKt.launch$default(this.appContext.getMainQueue(), null, null, new AudioFocusManager$onAudioFocusChange$4(this, null), 3, null);
        }
    }

    private final boolean playerRequiresFocus(WeakReference<VideoPlayer> weakPlayer) {
        VideoPlayer videoPlayer;
        return (weakPlayer == null || (videoPlayer = weakPlayer.get()) == null || ((videoPlayer.getMuted() || !videoPlayer.getPlaying() || videoPlayer.getVolume() <= 0.0f) && videoPlayer.getAudioMixingMode() != AudioMixingMode.DO_NOT_MIX)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pausePlayerIfUnmuted(WeakReference<VideoPlayer> weakPlayer) {
        VideoPlayer videoPlayer = weakPlayer.get();
        if (videoPlayer == null || videoPlayer.getMuted()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(this.appContext.getMainQueue(), null, null, new AudioFocusManager$pausePlayerIfUnmuted$1$1(videoPlayer, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void duckPlayer(WeakReference<VideoPlayer> weakPlayer) {
        VideoPlayer videoPlayer = weakPlayer.get();
        if (videoPlayer != null) {
            BuildersKt__Builders_commonKt.launch$default(this.appContext.getMainQueue(), null, null, new AudioFocusManager$duckPlayer$1$1(videoPlayer, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unduckPlayer(WeakReference<VideoPlayer> weakPlayer) {
        VideoPlayer videoPlayer = weakPlayer.get();
        if (videoPlayer == null || videoPlayer.getMuted()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(this.appContext.getMainQueue(), null, null, new AudioFocusManager$unduckPlayer$1$1(videoPlayer, null), 3, null);
    }

    private final void updateAudioFocus() {
        if (getAnyPlayerRequiresFocus() || findAudioMixingMode() != this.currentMixingMode) {
            requestAudioFocus();
        } else {
            abandonAudioFocus();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    private final AudioMixingMode findAudioMixingMode() {
        List list = CollectionsKt.toList(this.players);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            AudioMixingMode audioMixingMode = null;
            if (!it.hasNext()) {
                break;
            }
            VideoPlayer videoPlayer = (VideoPlayer) ((WeakReference) it.next()).get();
            if (videoPlayer != null) {
                if (!videoPlayer.getPlaying()) {
                    videoPlayer = null;
                }
                if (videoPlayer != null) {
                    audioMixingMode = videoPlayer.getAudioMixingMode();
                }
            }
            if (audioMixingMode != null) {
                arrayList.add(audioMixingMode);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return AudioMixingMode.MIX_WITH_OTHERS;
        }
        Iterator it2 = arrayList2.iterator();
        if (!it2.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            AudioMixingMode audioMixingMode2 = (AudioMixingMode) it2.next();
            next = (AudioMixingMode) next;
            if (audioMixingMode2.getPriority() <= next.getPriority()) {
                audioMixingMode2 = null;
            }
            if (audioMixingMode2 != null) {
                next = audioMixingMode2;
            }
        }
        return (AudioMixingMode) next;
    }
}
