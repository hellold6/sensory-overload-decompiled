package expo.modules.video.player;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.exoplayer.ExoPlayer;
import com.facebook.react.uimanager.ViewProps;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import expo.modules.video.enums.AudioMixingMode;
import expo.modules.video.enums.PlayerStatus;
import expo.modules.video.player.VideoPlayerListener;
import expo.modules.video.records.AudioTrack;
import expo.modules.video.records.PlaybackError;
import expo.modules.video.records.SubtitleTrack;
import expo.modules.video.records.TimeUpdate;
import expo.modules.video.records.VideoSource;
import expo.modules.video.records.VideoTrack;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoPlayerAudioTracks.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\u0018\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020'H\u0016J\u0018\u0010(\u001a\u00020!2\u0006\u0010%\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0016J\u0012\u0010+\u001a\u00020!2\b\u0010,\u001a\u0004\u0018\u00010\u0015H\u0002J\n\u0010-\u001a\u0004\u0018\u00010\rH\u0002R\u001c\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00030\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR!\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u001cj\b\u0012\u0004\u0012\u00020\u0015`\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006."}, d2 = {"Lexpo/modules/video/player/VideoPlayerAudioTracks;", "Lexpo/modules/video/player/VideoPlayerListener;", "owner", "Lexpo/modules/video/player/VideoPlayer;", "<init>", "(Lexpo/modules/video/player/VideoPlayer;)V", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "videoPlayer", "getVideoPlayer", "()Lexpo/modules/video/player/VideoPlayer;", "formatsToGroups", "", "Landroidx/media3/common/Format;", "Lkotlin/Pair;", "Landroidx/media3/common/TrackGroup;", "", "currentAudioTrackFormat", "currentOverride", "Landroidx/media3/common/TrackSelectionOverride;", "value", "Lexpo/modules/video/records/AudioTrack;", "currentAudioTrack", "getCurrentAudioTrack", "()Lexpo/modules/video/records/AudioTrack;", "setCurrentAudioTrack", "(Lexpo/modules/video/records/AudioTrack;)V", "availableAudioTracks", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getAvailableAudioTracks", "()Ljava/util/ArrayList;", "setAudioTracksEnabled", "", ViewProps.ENABLED, "", "onTrackSelectionParametersChanged", "player", "trackSelectionParameters", "Landroidx/media3/common/TrackSelectionParameters;", "onTracksChanged", "tracks", "Landroidx/media3/common/Tracks;", "applyAudioTrack", "audioTrack", "findSelectedAudioFormat", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoPlayerAudioTracks implements VideoPlayerListener {
    private final ArrayList<AudioTrack> availableAudioTracks;
    private Format currentAudioTrackFormat;
    private TrackSelectionOverride currentOverride;
    private final Map<Format, Pair<TrackGroup, Integer>> formatsToGroups;
    private final WeakReference<VideoPlayer> owner;

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onAudioMixingModeChanged(VideoPlayer videoPlayer, AudioMixingMode audioMixingMode, AudioMixingMode audioMixingMode2) {
        VideoPlayerListener.DefaultImpls.onAudioMixingModeChanged(this, videoPlayer, audioMixingMode, audioMixingMode2);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onIsPlayingChanged(VideoPlayer videoPlayer, boolean z, Boolean bool) {
        VideoPlayerListener.DefaultImpls.onIsPlayingChanged(this, videoPlayer, z, bool);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onMutedChanged(VideoPlayer videoPlayer, boolean z, Boolean bool) {
        VideoPlayerListener.DefaultImpls.onMutedChanged(this, videoPlayer, z, bool);
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
    public void onVideoSourceLoaded(VideoPlayer videoPlayer, VideoSource videoSource, Double d, List<VideoTrack> list, List<SubtitleTrack> list2, List<AudioTrack> list3) {
        VideoPlayerListener.DefaultImpls.onVideoSourceLoaded(this, videoPlayer, videoSource, d, list, list2, list3);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onVideoTrackChanged(VideoPlayer videoPlayer, VideoTrack videoTrack, VideoTrack videoTrack2) {
        VideoPlayerListener.DefaultImpls.onVideoTrackChanged(this, videoPlayer, videoTrack, videoTrack2);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onVolumeChanged(VideoPlayer videoPlayer, float f, Float f2) {
        VideoPlayerListener.DefaultImpls.onVolumeChanged(this, videoPlayer, f, f2);
    }

    public VideoPlayerAudioTracks(VideoPlayer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.owner = new WeakReference<>(owner);
        this.formatsToGroups = new LinkedHashMap();
        this.availableAudioTracks = new ArrayList<>();
        owner.addListener(this);
    }

    private final VideoPlayer getVideoPlayer() {
        return this.owner.get();
    }

    public final AudioTrack getCurrentAudioTrack() {
        return AudioTrack.INSTANCE.fromFormat(this.currentAudioTrackFormat);
    }

    public final void setCurrentAudioTrack(AudioTrack audioTrack) {
        applyAudioTrack(audioTrack);
    }

    public final ArrayList<AudioTrack> getAvailableAudioTracks() {
        return this.availableAudioTracks;
    }

    public final void setAudioTracksEnabled(boolean enabled) {
        ExoPlayer player;
        TrackSelectionParameters trackSelectionParameters;
        ExoPlayer player2;
        VideoPlayer videoPlayer = getVideoPlayer();
        if (videoPlayer == null || (player = videoPlayer.getPlayer()) == null || (trackSelectionParameters = player.getTrackSelectionParameters()) == null) {
            return;
        }
        TrackSelectionParameters build = trackSelectionParameters.buildUpon().setTrackTypeDisabled(1, !enabled).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        if (!enabled) {
            build = build.buildUpon().clearOverridesOfType(1).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        }
        VideoPlayer videoPlayer2 = getVideoPlayer();
        if (videoPlayer2 == null || (player2 = videoPlayer2.getPlayer()) == null) {
            return;
        }
        player2.setTrackSelectionParameters(build);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onTrackSelectionParametersChanged(VideoPlayer player, TrackSelectionParameters trackSelectionParameters) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(trackSelectionParameters, "trackSelectionParameters");
        this.currentAudioTrackFormat = findSelectedAudioFormat();
        VideoPlayerListener.DefaultImpls.onTrackSelectionParametersChanged(this, player, trackSelectionParameters);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onTracksChanged(VideoPlayer player, Tracks tracks) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        this.formatsToGroups.clear();
        this.availableAudioTracks.clear();
        UnmodifiableIterator<Tracks.Group> it = tracks.getGroups().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Tracks.Group next = it.next();
            int i = next.length;
            for (int i2 = 0; i2 < i; i2++) {
                Format trackFormat = next.getTrackFormat(i2);
                Intrinsics.checkNotNullExpressionValue(trackFormat, "getTrackFormat(...)");
                if (MimeTypes.isAudio(trackFormat.sampleMimeType)) {
                    this.formatsToGroups.put(trackFormat, TuplesKt.to(next.getMediaTrackGroup(), Integer.valueOf(i2)));
                    AudioTrack fromFormat = AudioTrack.INSTANCE.fromFormat(trackFormat);
                    if (fromFormat != null) {
                        this.availableAudioTracks.add(fromFormat);
                    }
                }
            }
        }
        this.currentAudioTrackFormat = findSelectedAudioFormat();
        VideoPlayerListener.DefaultImpls.onTracksChanged(this, player, tracks);
    }

    private final void applyAudioTrack(AudioTrack audioTrack) {
        ExoPlayer player;
        Pair<TrackGroup, Integer> pair;
        VideoPlayer videoPlayer = getVideoPlayer();
        if (videoPlayer == null || (player = videoPlayer.getPlayer()) == null) {
            return;
        }
        TrackSelectionParameters trackSelectionParameters = player.getTrackSelectionParameters();
        Intrinsics.checkNotNullExpressionValue(trackSelectionParameters, "getTrackSelectionParameters(...)");
        if (this.currentOverride != null) {
            trackSelectionParameters = trackSelectionParameters.buildUpon().clearOverridesOfType(1).build();
            Intrinsics.checkNotNullExpressionValue(trackSelectionParameters, "build(...)");
        }
        Object obj = null;
        if (audioTrack == null) {
            player.setTrackSelectionParameters(trackSelectionParameters);
            setAudioTracksEnabled(false);
            this.currentOverride = null;
            return;
        }
        Iterator<T> it = this.formatsToGroups.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Intrinsics.areEqual(((Format) next).id, audioTrack.getId())) {
                obj = next;
                break;
            }
        }
        Format format = (Format) obj;
        if (format == null || (pair = this.formatsToGroups.get(format)) == null) {
            return;
        }
        TrackSelectionOverride trackSelectionOverride = new TrackSelectionOverride(pair.getFirst(), pair.getSecond().intValue());
        TrackSelectionParameters build = trackSelectionParameters.buildUpon().addOverride(trackSelectionOverride).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        player.setTrackSelectionParameters(build);
        setAudioTracksEnabled(true);
        this.currentOverride = trackSelectionOverride;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Format findSelectedAudioFormat() {
        Format format;
        ImmutableMap<TrackGroup, TrackSelectionOverride> immutableMap;
        ExoPlayer player;
        VideoPlayer videoPlayer = getVideoPlayer();
        Format format2 = null;
        TrackSelectionParameters trackSelectionParameters = (videoPlayer == null || (player = videoPlayer.getPlayer()) == null) ? null : player.getTrackSelectionParameters();
        ImmutableList<String> immutableList = trackSelectionParameters != null ? trackSelectionParameters.preferredAudioLanguages : null;
        if (trackSelectionParameters != null && (immutableMap = trackSelectionParameters.overrides) != null) {
            Iterator<Map.Entry<TrackGroup, TrackSelectionOverride>> it = immutableMap.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<TrackGroup, TrackSelectionOverride> next = it.next();
                TrackGroup key = next.getKey();
                TrackSelectionOverride value = next.getValue();
                if (key.type == 1) {
                    ImmutableList<Integer> trackIndices = value.trackIndices;
                    Intrinsics.checkNotNullExpressionValue(trackIndices, "trackIndices");
                    Integer num = (Integer) CollectionsKt.firstOrNull((List) trackIndices);
                    if (num != null) {
                        format = key.getFormat(num.intValue());
                    }
                }
            }
        }
        format = null;
        if (immutableList != null) {
            UnmodifiableIterator<String> it2 = immutableList.iterator();
            Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
            if (it2.hasNext()) {
                String next2 = it2.next();
                Iterator<T> it3 = this.formatsToGroups.keySet().iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    Object next3 = it3.next();
                    if (Intrinsics.areEqual(((Format) next3).language, next2)) {
                        format2 = next3;
                        break;
                    }
                }
                format2 = format2;
            }
        }
        return format == null ? format2 : format;
    }
}
