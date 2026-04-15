package expo.modules.video.player;

import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import com.google.firebase.messaging.Constants;
import expo.modules.video.enums.AudioMixingMode;
import expo.modules.video.enums.PlayerStatus;
import expo.modules.video.records.AudioTrack;
import expo.modules.video.records.PlaybackError;
import expo.modules.video.records.SubtitleTrack;
import expo.modules.video.records.TimeUpdate;
import expo.modules.video.records.VideoSource;
import expo.modules.video.records.VideoTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoPlayerListener.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J'\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u000fJ'\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0014J'\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u000fJ$\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0016J'\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0014J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!H\u0016J\u0018\u0010\"\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010#\u001a\u00020$H\u0016J\u0018\u0010%\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\"\u0010)\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010+H\u0016J$\u0010-\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u00010/H\u0016JS\u00101\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u00102\u001a\u0004\u0018\u00010\u001a2\b\u00103\u001a\u0004\u0018\u0001042\f\u00105\u001a\b\u0012\u0004\u0012\u00020/062\f\u00107\u001a\b\u0012\u0004\u0012\u000208062\f\u00109\u001a\b\u0012\u0004\u0012\u00020:06H\u0016¢\u0006\u0002\u0010;J\u0010\u0010<\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006="}, d2 = {"Lexpo/modules/video/player/VideoPlayerListener;", "", "onStatusChanged", "", "player", "Lexpo/modules/video/player/VideoPlayer;", "status", "Lexpo/modules/video/enums/PlayerStatus;", "oldStatus", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Lexpo/modules/video/records/PlaybackError;", "onIsPlayingChanged", "isPlaying", "", "oldIsPlaying", "(Lexpo/modules/video/player/VideoPlayer;ZLjava/lang/Boolean;)V", "onVolumeChanged", "volume", "", "oldVolume", "(Lexpo/modules/video/player/VideoPlayer;FLjava/lang/Float;)V", "onMutedChanged", "muted", "oldMuted", "onSourceChanged", Constants.ScionAnalytics.PARAM_SOURCE, "Lexpo/modules/video/records/VideoSource;", "oldSource", "onPlaybackRateChanged", "rate", "oldRate", "onTracksChanged", "tracks", "Landroidx/media3/common/Tracks;", "onTrackSelectionParametersChanged", "trackSelectionParameters", "Landroidx/media3/common/TrackSelectionParameters;", "onTimeUpdate", "timeUpdate", "Lexpo/modules/video/records/TimeUpdate;", "onPlayedToEnd", "onAudioMixingModeChanged", "audioMixingMode", "Lexpo/modules/video/enums/AudioMixingMode;", "oldAudioMixingMode", "onVideoTrackChanged", "videoTrack", "Lexpo/modules/video/records/VideoTrack;", "oldVideoTrack", "onVideoSourceLoaded", "videoSource", "duration", "", "availableVideoTracks", "", "availableSubtitleTracks", "Lexpo/modules/video/records/SubtitleTrack;", "availableAudioTracks", "Lexpo/modules/video/records/AudioTrack;", "(Lexpo/modules/video/player/VideoPlayer;Lexpo/modules/video/records/VideoSource;Ljava/lang/Double;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "onRenderedFirstFrame", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface VideoPlayerListener {

    /* compiled from: VideoPlayerListener.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void onAudioMixingModeChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, AudioMixingMode audioMixingMode, AudioMixingMode audioMixingMode2) {
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(audioMixingMode, "audioMixingMode");
        }

        public static void onIsPlayingChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, boolean z, Boolean bool) {
            Intrinsics.checkNotNullParameter(player, "player");
        }

        public static void onMutedChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, boolean z, Boolean bool) {
            Intrinsics.checkNotNullParameter(player, "player");
        }

        public static void onPlaybackRateChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, float f, Float f2) {
            Intrinsics.checkNotNullParameter(player, "player");
        }

        public static void onPlayedToEnd(VideoPlayerListener videoPlayerListener, VideoPlayer player) {
            Intrinsics.checkNotNullParameter(player, "player");
        }

        public static void onRenderedFirstFrame(VideoPlayerListener videoPlayerListener, VideoPlayer player) {
            Intrinsics.checkNotNullParameter(player, "player");
        }

        public static void onSourceChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, VideoSource videoSource, VideoSource videoSource2) {
            Intrinsics.checkNotNullParameter(player, "player");
        }

        public static void onStatusChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, PlayerStatus status, PlayerStatus playerStatus, PlaybackError playbackError) {
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(status, "status");
        }

        public static void onTimeUpdate(VideoPlayerListener videoPlayerListener, VideoPlayer player, TimeUpdate timeUpdate) {
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(timeUpdate, "timeUpdate");
        }

        public static void onTrackSelectionParametersChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, TrackSelectionParameters trackSelectionParameters) {
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(trackSelectionParameters, "trackSelectionParameters");
        }

        public static void onTracksChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, Tracks tracks) {
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(tracks, "tracks");
        }

        public static void onVideoSourceLoaded(VideoPlayerListener videoPlayerListener, VideoPlayer player, VideoSource videoSource, Double d, List<VideoTrack> availableVideoTracks, List<SubtitleTrack> availableSubtitleTracks, List<AudioTrack> availableAudioTracks) {
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(availableVideoTracks, "availableVideoTracks");
            Intrinsics.checkNotNullParameter(availableSubtitleTracks, "availableSubtitleTracks");
            Intrinsics.checkNotNullParameter(availableAudioTracks, "availableAudioTracks");
        }

        public static void onVideoTrackChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, VideoTrack videoTrack, VideoTrack videoTrack2) {
            Intrinsics.checkNotNullParameter(player, "player");
        }

        public static void onVolumeChanged(VideoPlayerListener videoPlayerListener, VideoPlayer player, float f, Float f2) {
            Intrinsics.checkNotNullParameter(player, "player");
        }
    }

    void onAudioMixingModeChanged(VideoPlayer player, AudioMixingMode audioMixingMode, AudioMixingMode oldAudioMixingMode);

    void onIsPlayingChanged(VideoPlayer player, boolean isPlaying, Boolean oldIsPlaying);

    void onMutedChanged(VideoPlayer player, boolean muted, Boolean oldMuted);

    void onPlaybackRateChanged(VideoPlayer player, float rate, Float oldRate);

    void onPlayedToEnd(VideoPlayer player);

    void onRenderedFirstFrame(VideoPlayer player);

    void onSourceChanged(VideoPlayer player, VideoSource source, VideoSource oldSource);

    void onStatusChanged(VideoPlayer player, PlayerStatus status, PlayerStatus oldStatus, PlaybackError error);

    void onTimeUpdate(VideoPlayer player, TimeUpdate timeUpdate);

    void onTrackSelectionParametersChanged(VideoPlayer player, TrackSelectionParameters trackSelectionParameters);

    void onTracksChanged(VideoPlayer player, Tracks tracks);

    void onVideoSourceLoaded(VideoPlayer player, VideoSource videoSource, Double duration, List<VideoTrack> availableVideoTracks, List<SubtitleTrack> availableSubtitleTracks, List<AudioTrack> availableAudioTracks);

    void onVideoTrackChanged(VideoPlayer player, VideoTrack videoTrack, VideoTrack oldVideoTrack);

    void onVolumeChanged(VideoPlayer player, float volume, Float oldVolume);
}
