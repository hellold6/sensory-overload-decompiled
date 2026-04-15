package expo.modules.video.player;

import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import com.google.firebase.messaging.Constants;
import expo.modules.video.enums.AudioMixingMode;
import expo.modules.video.enums.PlayerStatus;
import expo.modules.video.records.AudioTrack;
import expo.modules.video.records.AudioTrackChangedEventPayload;
import expo.modules.video.records.AvailableAudioTracksChangedEventPayload;
import expo.modules.video.records.AvailableSubtitleTracksChangedEventPayload;
import expo.modules.video.records.IsPlayingEventPayload;
import expo.modules.video.records.MutedChangedEventPayload;
import expo.modules.video.records.PlaybackError;
import expo.modules.video.records.PlaybackRateChangedEventPayload;
import expo.modules.video.records.SourceChangedEventPayload;
import expo.modules.video.records.StatusChangedEventPayload;
import expo.modules.video.records.SubtitleTrack;
import expo.modules.video.records.SubtitleTrackChangedEventPayload;
import expo.modules.video.records.TimeUpdate;
import expo.modules.video.records.VideoEventPayload;
import expo.modules.video.records.VideoSource;
import expo.modules.video.records.VideoSourceLoadedEventPayload;
import expo.modules.video.records.VideoTrack;
import expo.modules.video.records.VideoTrackChangedEventPayload;
import expo.modules.video.records.VolumeChangedEventPayload;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerEvent.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0012\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'(B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0012)*+,-./0123456789:¨\u0006;"}, d2 = {"Lexpo/modules/video/player/PlayerEvent;", "", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/VideoEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/VideoEventPayload;", "emitToJS", "", "getEmitToJS", "()Z", "emit", "", "player", "Lexpo/modules/video/player/VideoPlayer;", "listeners", "", "Lexpo/modules/video/player/VideoPlayerListener;", "StatusChanged", "IsPlayingChanged", "VolumeChanged", "MutedChanged", "SourceChanged", "PlaybackRateChanged", "TracksChanged", "TrackSelectionParametersChanged", "SubtitleTrackChanged", "AudioTrackChanged", "VideoTrackChanged", "RenderedFirstFrame", "AvailableSubtitleTracksChanged", "AvailableAudioTracksChanged", "VideoSourceLoaded", "TimeUpdated", "AudioMixingModeChanged", "PlayedToEnd", "Lexpo/modules/video/player/PlayerEvent$AudioMixingModeChanged;", "Lexpo/modules/video/player/PlayerEvent$AudioTrackChanged;", "Lexpo/modules/video/player/PlayerEvent$AvailableAudioTracksChanged;", "Lexpo/modules/video/player/PlayerEvent$AvailableSubtitleTracksChanged;", "Lexpo/modules/video/player/PlayerEvent$IsPlayingChanged;", "Lexpo/modules/video/player/PlayerEvent$MutedChanged;", "Lexpo/modules/video/player/PlayerEvent$PlaybackRateChanged;", "Lexpo/modules/video/player/PlayerEvent$PlayedToEnd;", "Lexpo/modules/video/player/PlayerEvent$RenderedFirstFrame;", "Lexpo/modules/video/player/PlayerEvent$SourceChanged;", "Lexpo/modules/video/player/PlayerEvent$StatusChanged;", "Lexpo/modules/video/player/PlayerEvent$SubtitleTrackChanged;", "Lexpo/modules/video/player/PlayerEvent$TimeUpdated;", "Lexpo/modules/video/player/PlayerEvent$TrackSelectionParametersChanged;", "Lexpo/modules/video/player/PlayerEvent$TracksChanged;", "Lexpo/modules/video/player/PlayerEvent$VideoSourceLoaded;", "Lexpo/modules/video/player/PlayerEvent$VideoTrackChanged;", "Lexpo/modules/video/player/PlayerEvent$VolumeChanged;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class PlayerEvent {
    private final boolean emitToJS;
    private final VideoEventPayload jsEventPayload;
    private final String name;

    public /* synthetic */ PlayerEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PlayerEvent() {
        this.name = "";
        this.emitToJS = true;
    }

    public String getName() {
        return this.name;
    }

    public VideoEventPayload getJsEventPayload() {
        return this.jsEventPayload;
    }

    public boolean getEmitToJS() {
        return this.emitToJS;
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J+\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$StatusChanged;", "Lexpo/modules/video/player/PlayerEvent;", "status", "Lexpo/modules/video/enums/PlayerStatus;", "oldStatus", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Lexpo/modules/video/records/PlaybackError;", "<init>", "(Lexpo/modules/video/enums/PlayerStatus;Lexpo/modules/video/enums/PlayerStatus;Lexpo/modules/video/records/PlaybackError;)V", "getStatus", "()Lexpo/modules/video/enums/PlayerStatus;", "getOldStatus", "getError", "()Lexpo/modules/video/records/PlaybackError;", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/StatusChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/StatusChangedEventPayload;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class StatusChanged extends PlayerEvent {
        private final PlaybackError error;
        private final StatusChangedEventPayload jsEventPayload;
        private final String name;
        private final PlayerStatus oldStatus;
        private final PlayerStatus status;

        public static /* synthetic */ StatusChanged copy$default(StatusChanged statusChanged, PlayerStatus playerStatus, PlayerStatus playerStatus2, PlaybackError playbackError, int i, Object obj) {
            if ((i & 1) != 0) {
                playerStatus = statusChanged.status;
            }
            if ((i & 2) != 0) {
                playerStatus2 = statusChanged.oldStatus;
            }
            if ((i & 4) != 0) {
                playbackError = statusChanged.error;
            }
            return statusChanged.copy(playerStatus, playerStatus2, playbackError);
        }

        /* renamed from: component1, reason: from getter */
        public final PlayerStatus getStatus() {
            return this.status;
        }

        /* renamed from: component2, reason: from getter */
        public final PlayerStatus getOldStatus() {
            return this.oldStatus;
        }

        /* renamed from: component3, reason: from getter */
        public final PlaybackError getError() {
            return this.error;
        }

        public final StatusChanged copy(PlayerStatus status, PlayerStatus oldStatus, PlaybackError error) {
            Intrinsics.checkNotNullParameter(status, "status");
            return new StatusChanged(status, oldStatus, error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StatusChanged)) {
                return false;
            }
            StatusChanged statusChanged = (StatusChanged) other;
            return this.status == statusChanged.status && this.oldStatus == statusChanged.oldStatus && Intrinsics.areEqual(this.error, statusChanged.error);
        }

        public int hashCode() {
            int hashCode = this.status.hashCode() * 31;
            PlayerStatus playerStatus = this.oldStatus;
            int hashCode2 = (hashCode + (playerStatus == null ? 0 : playerStatus.hashCode())) * 31;
            PlaybackError playbackError = this.error;
            return hashCode2 + (playbackError != null ? playbackError.hashCode() : 0);
        }

        public String toString() {
            return "StatusChanged(status=" + this.status + ", oldStatus=" + this.oldStatus + ", error=" + this.error + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StatusChanged(PlayerStatus status, PlayerStatus playerStatus, PlaybackError playbackError) {
            super(null);
            Intrinsics.checkNotNullParameter(status, "status");
            this.status = status;
            this.oldStatus = playerStatus;
            this.error = playbackError;
            this.name = "statusChange";
            this.jsEventPayload = new StatusChangedEventPayload(status, playerStatus, playbackError);
        }

        public final PlaybackError getError() {
            return this.error;
        }

        public final PlayerStatus getOldStatus() {
            return this.oldStatus;
        }

        public final PlayerStatus getStatus() {
            return this.status;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public StatusChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ$\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$IsPlayingChanged;", "Lexpo/modules/video/player/PlayerEvent;", "isPlaying", "", "oldIsPlaying", "<init>", "(ZLjava/lang/Boolean;)V", "()Z", "getOldIsPlaying", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/IsPlayingEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/IsPlayingEventPayload;", "component1", "component2", "copy", "(ZLjava/lang/Boolean;)Lexpo/modules/video/player/PlayerEvent$IsPlayingChanged;", "equals", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class IsPlayingChanged extends PlayerEvent {
        private final boolean isPlaying;
        private final IsPlayingEventPayload jsEventPayload;
        private final String name;
        private final Boolean oldIsPlaying;

        public static /* synthetic */ IsPlayingChanged copy$default(IsPlayingChanged isPlayingChanged, boolean z, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                z = isPlayingChanged.isPlaying;
            }
            if ((i & 2) != 0) {
                bool = isPlayingChanged.oldIsPlaying;
            }
            return isPlayingChanged.copy(z, bool);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsPlaying() {
            return this.isPlaying;
        }

        /* renamed from: component2, reason: from getter */
        public final Boolean getOldIsPlaying() {
            return this.oldIsPlaying;
        }

        public final IsPlayingChanged copy(boolean isPlaying, Boolean oldIsPlaying) {
            return new IsPlayingChanged(isPlaying, oldIsPlaying);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof IsPlayingChanged)) {
                return false;
            }
            IsPlayingChanged isPlayingChanged = (IsPlayingChanged) other;
            return this.isPlaying == isPlayingChanged.isPlaying && Intrinsics.areEqual(this.oldIsPlaying, isPlayingChanged.oldIsPlaying);
        }

        public int hashCode() {
            int hashCode = Boolean.hashCode(this.isPlaying) * 31;
            Boolean bool = this.oldIsPlaying;
            return hashCode + (bool == null ? 0 : bool.hashCode());
        }

        public String toString() {
            return "IsPlayingChanged(isPlaying=" + this.isPlaying + ", oldIsPlaying=" + this.oldIsPlaying + ")";
        }

        public IsPlayingChanged(boolean z, Boolean bool) {
            super(null);
            this.isPlaying = z;
            this.oldIsPlaying = bool;
            this.name = "playingChange";
            this.jsEventPayload = new IsPlayingEventPayload(z, bool);
        }

        public final Boolean getOldIsPlaying() {
            return this.oldIsPlaying;
        }

        public final boolean isPlaying() {
            return this.isPlaying;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public IsPlayingEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ$\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$VolumeChanged;", "Lexpo/modules/video/player/PlayerEvent;", "volume", "", "oldVolume", "<init>", "(FLjava/lang/Float;)V", "getVolume", "()F", "getOldVolume", "()Ljava/lang/Float;", "Ljava/lang/Float;", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/VolumeChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/VolumeChangedEventPayload;", "component1", "component2", "copy", "(FLjava/lang/Float;)Lexpo/modules/video/player/PlayerEvent$VolumeChanged;", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class VolumeChanged extends PlayerEvent {
        private final VolumeChangedEventPayload jsEventPayload;
        private final String name;
        private final Float oldVolume;
        private final float volume;

        public static /* synthetic */ VolumeChanged copy$default(VolumeChanged volumeChanged, float f, Float f2, int i, Object obj) {
            if ((i & 1) != 0) {
                f = volumeChanged.volume;
            }
            if ((i & 2) != 0) {
                f2 = volumeChanged.oldVolume;
            }
            return volumeChanged.copy(f, f2);
        }

        /* renamed from: component1, reason: from getter */
        public final float getVolume() {
            return this.volume;
        }

        /* renamed from: component2, reason: from getter */
        public final Float getOldVolume() {
            return this.oldVolume;
        }

        public final VolumeChanged copy(float volume, Float oldVolume) {
            return new VolumeChanged(volume, oldVolume);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VolumeChanged)) {
                return false;
            }
            VolumeChanged volumeChanged = (VolumeChanged) other;
            return Float.compare(this.volume, volumeChanged.volume) == 0 && Intrinsics.areEqual((Object) this.oldVolume, (Object) volumeChanged.oldVolume);
        }

        public int hashCode() {
            int hashCode = Float.hashCode(this.volume) * 31;
            Float f = this.oldVolume;
            return hashCode + (f == null ? 0 : f.hashCode());
        }

        public String toString() {
            return "VolumeChanged(volume=" + this.volume + ", oldVolume=" + this.oldVolume + ")";
        }

        public VolumeChanged(float f, Float f2) {
            super(null);
            this.volume = f;
            this.oldVolume = f2;
            this.name = "volumeChange";
            this.jsEventPayload = new VolumeChangedEventPayload(f, f2);
        }

        public final Float getOldVolume() {
            return this.oldVolume;
        }

        public final float getVolume() {
            return this.volume;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public VolumeChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ$\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$MutedChanged;", "Lexpo/modules/video/player/PlayerEvent;", "muted", "", "oldMuted", "<init>", "(ZLjava/lang/Boolean;)V", "getMuted", "()Z", "getOldMuted", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/MutedChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/MutedChangedEventPayload;", "component1", "component2", "copy", "(ZLjava/lang/Boolean;)Lexpo/modules/video/player/PlayerEvent$MutedChanged;", "equals", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class MutedChanged extends PlayerEvent {
        private final MutedChangedEventPayload jsEventPayload;
        private final boolean muted;
        private final String name;
        private final Boolean oldMuted;

        public static /* synthetic */ MutedChanged copy$default(MutedChanged mutedChanged, boolean z, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                z = mutedChanged.muted;
            }
            if ((i & 2) != 0) {
                bool = mutedChanged.oldMuted;
            }
            return mutedChanged.copy(z, bool);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getMuted() {
            return this.muted;
        }

        /* renamed from: component2, reason: from getter */
        public final Boolean getOldMuted() {
            return this.oldMuted;
        }

        public final MutedChanged copy(boolean muted, Boolean oldMuted) {
            return new MutedChanged(muted, oldMuted);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MutedChanged)) {
                return false;
            }
            MutedChanged mutedChanged = (MutedChanged) other;
            return this.muted == mutedChanged.muted && Intrinsics.areEqual(this.oldMuted, mutedChanged.oldMuted);
        }

        public int hashCode() {
            int hashCode = Boolean.hashCode(this.muted) * 31;
            Boolean bool = this.oldMuted;
            return hashCode + (bool == null ? 0 : bool.hashCode());
        }

        public String toString() {
            return "MutedChanged(muted=" + this.muted + ", oldMuted=" + this.oldMuted + ")";
        }

        public MutedChanged(boolean z, Boolean bool) {
            super(null);
            this.muted = z;
            this.oldMuted = bool;
            this.name = "mutedChange";
            this.jsEventPayload = new MutedChangedEventPayload(z, bool);
        }

        public final boolean getMuted() {
            return this.muted;
        }

        public final Boolean getOldMuted() {
            return this.oldMuted;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public MutedChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u000bHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$SourceChanged;", "Lexpo/modules/video/player/PlayerEvent;", Constants.ScionAnalytics.PARAM_SOURCE, "Lexpo/modules/video/records/VideoSource;", "oldSource", "<init>", "(Lexpo/modules/video/records/VideoSource;Lexpo/modules/video/records/VideoSource;)V", "getSource", "()Lexpo/modules/video/records/VideoSource;", "getOldSource", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/SourceChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/SourceChangedEventPayload;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class SourceChanged extends PlayerEvent {
        private final SourceChangedEventPayload jsEventPayload;
        private final String name;
        private final VideoSource oldSource;
        private final VideoSource source;

        public static /* synthetic */ SourceChanged copy$default(SourceChanged sourceChanged, VideoSource videoSource, VideoSource videoSource2, int i, Object obj) {
            if ((i & 1) != 0) {
                videoSource = sourceChanged.source;
            }
            if ((i & 2) != 0) {
                videoSource2 = sourceChanged.oldSource;
            }
            return sourceChanged.copy(videoSource, videoSource2);
        }

        /* renamed from: component1, reason: from getter */
        public final VideoSource getSource() {
            return this.source;
        }

        /* renamed from: component2, reason: from getter */
        public final VideoSource getOldSource() {
            return this.oldSource;
        }

        public final SourceChanged copy(VideoSource source, VideoSource oldSource) {
            return new SourceChanged(source, oldSource);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SourceChanged)) {
                return false;
            }
            SourceChanged sourceChanged = (SourceChanged) other;
            return Intrinsics.areEqual(this.source, sourceChanged.source) && Intrinsics.areEqual(this.oldSource, sourceChanged.oldSource);
        }

        public int hashCode() {
            VideoSource videoSource = this.source;
            int hashCode = (videoSource == null ? 0 : videoSource.hashCode()) * 31;
            VideoSource videoSource2 = this.oldSource;
            return hashCode + (videoSource2 != null ? videoSource2.hashCode() : 0);
        }

        public String toString() {
            return "SourceChanged(source=" + this.source + ", oldSource=" + this.oldSource + ")";
        }

        public SourceChanged(VideoSource videoSource, VideoSource videoSource2) {
            super(null);
            this.source = videoSource;
            this.oldSource = videoSource2;
            this.name = "sourceChange";
            this.jsEventPayload = new SourceChangedEventPayload(videoSource, videoSource2);
        }

        public final VideoSource getOldSource() {
            return this.oldSource;
        }

        public final VideoSource getSource() {
            return this.source;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public SourceChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ$\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$PlaybackRateChanged;", "Lexpo/modules/video/player/PlayerEvent;", "rate", "", "oldRate", "<init>", "(FLjava/lang/Float;)V", "getRate", "()F", "getOldRate", "()Ljava/lang/Float;", "Ljava/lang/Float;", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/PlaybackRateChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/PlaybackRateChangedEventPayload;", "component1", "component2", "copy", "(FLjava/lang/Float;)Lexpo/modules/video/player/PlayerEvent$PlaybackRateChanged;", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class PlaybackRateChanged extends PlayerEvent {
        private final PlaybackRateChangedEventPayload jsEventPayload;
        private final String name;
        private final Float oldRate;
        private final float rate;

        public static /* synthetic */ PlaybackRateChanged copy$default(PlaybackRateChanged playbackRateChanged, float f, Float f2, int i, Object obj) {
            if ((i & 1) != 0) {
                f = playbackRateChanged.rate;
            }
            if ((i & 2) != 0) {
                f2 = playbackRateChanged.oldRate;
            }
            return playbackRateChanged.copy(f, f2);
        }

        /* renamed from: component1, reason: from getter */
        public final float getRate() {
            return this.rate;
        }

        /* renamed from: component2, reason: from getter */
        public final Float getOldRate() {
            return this.oldRate;
        }

        public final PlaybackRateChanged copy(float rate, Float oldRate) {
            return new PlaybackRateChanged(rate, oldRate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PlaybackRateChanged)) {
                return false;
            }
            PlaybackRateChanged playbackRateChanged = (PlaybackRateChanged) other;
            return Float.compare(this.rate, playbackRateChanged.rate) == 0 && Intrinsics.areEqual((Object) this.oldRate, (Object) playbackRateChanged.oldRate);
        }

        public int hashCode() {
            int hashCode = Float.hashCode(this.rate) * 31;
            Float f = this.oldRate;
            return hashCode + (f == null ? 0 : f.hashCode());
        }

        public String toString() {
            return "PlaybackRateChanged(rate=" + this.rate + ", oldRate=" + this.oldRate + ")";
        }

        public PlaybackRateChanged(float f, Float f2) {
            super(null);
            this.rate = f;
            this.oldRate = f2;
            this.name = "playbackRateChange";
            this.jsEventPayload = new PlaybackRateChangedEventPayload(f, f2);
        }

        public final Float getOldRate() {
            return this.oldRate;
        }

        public final float getRate() {
            return this.rate;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public PlaybackRateChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$TracksChanged;", "Lexpo/modules/video/player/PlayerEvent;", "tracks", "Landroidx/media3/common/Tracks;", "<init>", "(Landroidx/media3/common/Tracks;)V", "getTracks", "()Landroidx/media3/common/Tracks;", "name", "", "getName", "()Ljava/lang/String;", "emitToJS", "", "getEmitToJS", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class TracksChanged extends PlayerEvent {
        private final boolean emitToJS;
        private final String name;
        private final Tracks tracks;

        public static /* synthetic */ TracksChanged copy$default(TracksChanged tracksChanged, Tracks tracks, int i, Object obj) {
            if ((i & 1) != 0) {
                tracks = tracksChanged.tracks;
            }
            return tracksChanged.copy(tracks);
        }

        /* renamed from: component1, reason: from getter */
        public final Tracks getTracks() {
            return this.tracks;
        }

        public final TracksChanged copy(Tracks tracks) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            return new TracksChanged(tracks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TracksChanged) && Intrinsics.areEqual(this.tracks, ((TracksChanged) other).tracks);
        }

        public int hashCode() {
            return this.tracks.hashCode();
        }

        public String toString() {
            return "TracksChanged(tracks=" + this.tracks + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TracksChanged(Tracks tracks) {
            super(null);
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            this.tracks = tracks;
            this.name = "tracksChange";
        }

        public final Tracks getTracks() {
            return this.tracks;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public boolean getEmitToJS() {
            return this.emitToJS;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$TrackSelectionParametersChanged;", "Lexpo/modules/video/player/PlayerEvent;", "trackSelectionParameters", "Landroidx/media3/common/TrackSelectionParameters;", "<init>", "(Landroidx/media3/common/TrackSelectionParameters;)V", "getTrackSelectionParameters", "()Landroidx/media3/common/TrackSelectionParameters;", "name", "", "getName", "()Ljava/lang/String;", "emitToJS", "", "getEmitToJS", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class TrackSelectionParametersChanged extends PlayerEvent {
        private final boolean emitToJS;
        private final String name;
        private final TrackSelectionParameters trackSelectionParameters;

        public static /* synthetic */ TrackSelectionParametersChanged copy$default(TrackSelectionParametersChanged trackSelectionParametersChanged, TrackSelectionParameters trackSelectionParameters, int i, Object obj) {
            if ((i & 1) != 0) {
                trackSelectionParameters = trackSelectionParametersChanged.trackSelectionParameters;
            }
            return trackSelectionParametersChanged.copy(trackSelectionParameters);
        }

        /* renamed from: component1, reason: from getter */
        public final TrackSelectionParameters getTrackSelectionParameters() {
            return this.trackSelectionParameters;
        }

        public final TrackSelectionParametersChanged copy(TrackSelectionParameters trackSelectionParameters) {
            Intrinsics.checkNotNullParameter(trackSelectionParameters, "trackSelectionParameters");
            return new TrackSelectionParametersChanged(trackSelectionParameters);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TrackSelectionParametersChanged) && Intrinsics.areEqual(this.trackSelectionParameters, ((TrackSelectionParametersChanged) other).trackSelectionParameters);
        }

        public int hashCode() {
            return this.trackSelectionParameters.hashCode();
        }

        public String toString() {
            return "TrackSelectionParametersChanged(trackSelectionParameters=" + this.trackSelectionParameters + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            super(null);
            Intrinsics.checkNotNullParameter(trackSelectionParameters, "trackSelectionParameters");
            this.trackSelectionParameters = trackSelectionParameters;
            this.name = "trackSelectionParametersChange";
        }

        public final TrackSelectionParameters getTrackSelectionParameters() {
            return this.trackSelectionParameters;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public boolean getEmitToJS() {
            return this.emitToJS;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u000bHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$SubtitleTrackChanged;", "Lexpo/modules/video/player/PlayerEvent;", "subtitleTrack", "Lexpo/modules/video/records/SubtitleTrack;", "oldSubtitleTrack", "<init>", "(Lexpo/modules/video/records/SubtitleTrack;Lexpo/modules/video/records/SubtitleTrack;)V", "getSubtitleTrack", "()Lexpo/modules/video/records/SubtitleTrack;", "getOldSubtitleTrack", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/SubtitleTrackChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/SubtitleTrackChangedEventPayload;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class SubtitleTrackChanged extends PlayerEvent {
        private final SubtitleTrackChangedEventPayload jsEventPayload;
        private final String name;
        private final SubtitleTrack oldSubtitleTrack;
        private final SubtitleTrack subtitleTrack;

        public static /* synthetic */ SubtitleTrackChanged copy$default(SubtitleTrackChanged subtitleTrackChanged, SubtitleTrack subtitleTrack, SubtitleTrack subtitleTrack2, int i, Object obj) {
            if ((i & 1) != 0) {
                subtitleTrack = subtitleTrackChanged.subtitleTrack;
            }
            if ((i & 2) != 0) {
                subtitleTrack2 = subtitleTrackChanged.oldSubtitleTrack;
            }
            return subtitleTrackChanged.copy(subtitleTrack, subtitleTrack2);
        }

        /* renamed from: component1, reason: from getter */
        public final SubtitleTrack getSubtitleTrack() {
            return this.subtitleTrack;
        }

        /* renamed from: component2, reason: from getter */
        public final SubtitleTrack getOldSubtitleTrack() {
            return this.oldSubtitleTrack;
        }

        public final SubtitleTrackChanged copy(SubtitleTrack subtitleTrack, SubtitleTrack oldSubtitleTrack) {
            return new SubtitleTrackChanged(subtitleTrack, oldSubtitleTrack);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubtitleTrackChanged)) {
                return false;
            }
            SubtitleTrackChanged subtitleTrackChanged = (SubtitleTrackChanged) other;
            return Intrinsics.areEqual(this.subtitleTrack, subtitleTrackChanged.subtitleTrack) && Intrinsics.areEqual(this.oldSubtitleTrack, subtitleTrackChanged.oldSubtitleTrack);
        }

        public int hashCode() {
            SubtitleTrack subtitleTrack = this.subtitleTrack;
            int hashCode = (subtitleTrack == null ? 0 : subtitleTrack.hashCode()) * 31;
            SubtitleTrack subtitleTrack2 = this.oldSubtitleTrack;
            return hashCode + (subtitleTrack2 != null ? subtitleTrack2.hashCode() : 0);
        }

        public String toString() {
            return "SubtitleTrackChanged(subtitleTrack=" + this.subtitleTrack + ", oldSubtitleTrack=" + this.oldSubtitleTrack + ")";
        }

        public SubtitleTrackChanged(SubtitleTrack subtitleTrack, SubtitleTrack subtitleTrack2) {
            super(null);
            this.subtitleTrack = subtitleTrack;
            this.oldSubtitleTrack = subtitleTrack2;
            this.name = "subtitleTrackChange";
            this.jsEventPayload = new SubtitleTrackChangedEventPayload(subtitleTrack, subtitleTrack2);
        }

        public final SubtitleTrack getOldSubtitleTrack() {
            return this.oldSubtitleTrack;
        }

        public final SubtitleTrack getSubtitleTrack() {
            return this.subtitleTrack;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public SubtitleTrackChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u000bHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$AudioTrackChanged;", "Lexpo/modules/video/player/PlayerEvent;", "audioTrack", "Lexpo/modules/video/records/AudioTrack;", "oldAudioTrack", "<init>", "(Lexpo/modules/video/records/AudioTrack;Lexpo/modules/video/records/AudioTrack;)V", "getAudioTrack", "()Lexpo/modules/video/records/AudioTrack;", "getOldAudioTrack", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/AudioTrackChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/AudioTrackChangedEventPayload;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class AudioTrackChanged extends PlayerEvent {
        private final AudioTrack audioTrack;
        private final AudioTrackChangedEventPayload jsEventPayload;
        private final String name;
        private final AudioTrack oldAudioTrack;

        public static /* synthetic */ AudioTrackChanged copy$default(AudioTrackChanged audioTrackChanged, AudioTrack audioTrack, AudioTrack audioTrack2, int i, Object obj) {
            if ((i & 1) != 0) {
                audioTrack = audioTrackChanged.audioTrack;
            }
            if ((i & 2) != 0) {
                audioTrack2 = audioTrackChanged.oldAudioTrack;
            }
            return audioTrackChanged.copy(audioTrack, audioTrack2);
        }

        /* renamed from: component1, reason: from getter */
        public final AudioTrack getAudioTrack() {
            return this.audioTrack;
        }

        /* renamed from: component2, reason: from getter */
        public final AudioTrack getOldAudioTrack() {
            return this.oldAudioTrack;
        }

        public final AudioTrackChanged copy(AudioTrack audioTrack, AudioTrack oldAudioTrack) {
            return new AudioTrackChanged(audioTrack, oldAudioTrack);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AudioTrackChanged)) {
                return false;
            }
            AudioTrackChanged audioTrackChanged = (AudioTrackChanged) other;
            return Intrinsics.areEqual(this.audioTrack, audioTrackChanged.audioTrack) && Intrinsics.areEqual(this.oldAudioTrack, audioTrackChanged.oldAudioTrack);
        }

        public int hashCode() {
            AudioTrack audioTrack = this.audioTrack;
            int hashCode = (audioTrack == null ? 0 : audioTrack.hashCode()) * 31;
            AudioTrack audioTrack2 = this.oldAudioTrack;
            return hashCode + (audioTrack2 != null ? audioTrack2.hashCode() : 0);
        }

        public String toString() {
            return "AudioTrackChanged(audioTrack=" + this.audioTrack + ", oldAudioTrack=" + this.oldAudioTrack + ")";
        }

        public AudioTrackChanged(AudioTrack audioTrack, AudioTrack audioTrack2) {
            super(null);
            this.audioTrack = audioTrack;
            this.oldAudioTrack = audioTrack2;
            this.name = "audioTrackChange";
            this.jsEventPayload = new AudioTrackChangedEventPayload(audioTrack, audioTrack2);
        }

        public final AudioTrack getAudioTrack() {
            return this.audioTrack;
        }

        public final AudioTrack getOldAudioTrack() {
            return this.oldAudioTrack;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public AudioTrackChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u000bHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$VideoTrackChanged;", "Lexpo/modules/video/player/PlayerEvent;", "videoTrack", "Lexpo/modules/video/records/VideoTrack;", "oldVideoTrack", "<init>", "(Lexpo/modules/video/records/VideoTrack;Lexpo/modules/video/records/VideoTrack;)V", "getVideoTrack", "()Lexpo/modules/video/records/VideoTrack;", "getOldVideoTrack", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/VideoTrackChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/VideoTrackChangedEventPayload;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class VideoTrackChanged extends PlayerEvent {
        private final VideoTrackChangedEventPayload jsEventPayload;
        private final String name;
        private final VideoTrack oldVideoTrack;
        private final VideoTrack videoTrack;

        public static /* synthetic */ VideoTrackChanged copy$default(VideoTrackChanged videoTrackChanged, VideoTrack videoTrack, VideoTrack videoTrack2, int i, Object obj) {
            if ((i & 1) != 0) {
                videoTrack = videoTrackChanged.videoTrack;
            }
            if ((i & 2) != 0) {
                videoTrack2 = videoTrackChanged.oldVideoTrack;
            }
            return videoTrackChanged.copy(videoTrack, videoTrack2);
        }

        /* renamed from: component1, reason: from getter */
        public final VideoTrack getVideoTrack() {
            return this.videoTrack;
        }

        /* renamed from: component2, reason: from getter */
        public final VideoTrack getOldVideoTrack() {
            return this.oldVideoTrack;
        }

        public final VideoTrackChanged copy(VideoTrack videoTrack, VideoTrack oldVideoTrack) {
            return new VideoTrackChanged(videoTrack, oldVideoTrack);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoTrackChanged)) {
                return false;
            }
            VideoTrackChanged videoTrackChanged = (VideoTrackChanged) other;
            return Intrinsics.areEqual(this.videoTrack, videoTrackChanged.videoTrack) && Intrinsics.areEqual(this.oldVideoTrack, videoTrackChanged.oldVideoTrack);
        }

        public int hashCode() {
            VideoTrack videoTrack = this.videoTrack;
            int hashCode = (videoTrack == null ? 0 : videoTrack.hashCode()) * 31;
            VideoTrack videoTrack2 = this.oldVideoTrack;
            return hashCode + (videoTrack2 != null ? videoTrack2.hashCode() : 0);
        }

        public String toString() {
            return "VideoTrackChanged(videoTrack=" + this.videoTrack + ", oldVideoTrack=" + this.oldVideoTrack + ")";
        }

        public VideoTrackChanged(VideoTrack videoTrack, VideoTrack videoTrack2) {
            super(null);
            this.videoTrack = videoTrack;
            this.oldVideoTrack = videoTrack2;
            this.name = "videoTrackChange";
            this.jsEventPayload = new VideoTrackChangedEventPayload(videoTrack, videoTrack2);
        }

        public final VideoTrack getOldVideoTrack() {
            return this.oldVideoTrack;
        }

        public final VideoTrack getVideoTrack() {
            return this.videoTrack;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public VideoTrackChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$RenderedFirstFrame;", "Lexpo/modules/video/player/PlayerEvent;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "emitToJS", "", "getEmitToJS", "()Z", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class RenderedFirstFrame extends PlayerEvent {
        private final boolean emitToJS;
        private final String name;

        public RenderedFirstFrame() {
            super(null);
            this.name = "renderFirstFrame";
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public boolean getEmitToJS() {
            return this.emitToJS;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\fHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$AvailableSubtitleTracksChanged;", "Lexpo/modules/video/player/PlayerEvent;", "availableSubtitleTracks", "", "Lexpo/modules/video/records/SubtitleTrack;", "oldAvailableSubtitleTracks", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getAvailableSubtitleTracks", "()Ljava/util/List;", "getOldAvailableSubtitleTracks", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/AvailableSubtitleTracksChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/AvailableSubtitleTracksChangedEventPayload;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class AvailableSubtitleTracksChanged extends PlayerEvent {
        private final List<SubtitleTrack> availableSubtitleTracks;
        private final AvailableSubtitleTracksChangedEventPayload jsEventPayload;
        private final String name;
        private final List<SubtitleTrack> oldAvailableSubtitleTracks;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AvailableSubtitleTracksChanged copy$default(AvailableSubtitleTracksChanged availableSubtitleTracksChanged, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = availableSubtitleTracksChanged.availableSubtitleTracks;
            }
            if ((i & 2) != 0) {
                list2 = availableSubtitleTracksChanged.oldAvailableSubtitleTracks;
            }
            return availableSubtitleTracksChanged.copy(list, list2);
        }

        public final List<SubtitleTrack> component1() {
            return this.availableSubtitleTracks;
        }

        public final List<SubtitleTrack> component2() {
            return this.oldAvailableSubtitleTracks;
        }

        public final AvailableSubtitleTracksChanged copy(List<SubtitleTrack> availableSubtitleTracks, List<SubtitleTrack> oldAvailableSubtitleTracks) {
            Intrinsics.checkNotNullParameter(availableSubtitleTracks, "availableSubtitleTracks");
            Intrinsics.checkNotNullParameter(oldAvailableSubtitleTracks, "oldAvailableSubtitleTracks");
            return new AvailableSubtitleTracksChanged(availableSubtitleTracks, oldAvailableSubtitleTracks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AvailableSubtitleTracksChanged)) {
                return false;
            }
            AvailableSubtitleTracksChanged availableSubtitleTracksChanged = (AvailableSubtitleTracksChanged) other;
            return Intrinsics.areEqual(this.availableSubtitleTracks, availableSubtitleTracksChanged.availableSubtitleTracks) && Intrinsics.areEqual(this.oldAvailableSubtitleTracks, availableSubtitleTracksChanged.oldAvailableSubtitleTracks);
        }

        public int hashCode() {
            return (this.availableSubtitleTracks.hashCode() * 31) + this.oldAvailableSubtitleTracks.hashCode();
        }

        public String toString() {
            return "AvailableSubtitleTracksChanged(availableSubtitleTracks=" + this.availableSubtitleTracks + ", oldAvailableSubtitleTracks=" + this.oldAvailableSubtitleTracks + ")";
        }

        public final List<SubtitleTrack> getAvailableSubtitleTracks() {
            return this.availableSubtitleTracks;
        }

        public final List<SubtitleTrack> getOldAvailableSubtitleTracks() {
            return this.oldAvailableSubtitleTracks;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AvailableSubtitleTracksChanged(List<SubtitleTrack> availableSubtitleTracks, List<SubtitleTrack> oldAvailableSubtitleTracks) {
            super(null);
            Intrinsics.checkNotNullParameter(availableSubtitleTracks, "availableSubtitleTracks");
            Intrinsics.checkNotNullParameter(oldAvailableSubtitleTracks, "oldAvailableSubtitleTracks");
            this.availableSubtitleTracks = availableSubtitleTracks;
            this.oldAvailableSubtitleTracks = oldAvailableSubtitleTracks;
            this.name = "availableSubtitleTracksChange";
            this.jsEventPayload = new AvailableSubtitleTracksChangedEventPayload(availableSubtitleTracks, oldAvailableSubtitleTracks);
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public AvailableSubtitleTracksChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\fHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$AvailableAudioTracksChanged;", "Lexpo/modules/video/player/PlayerEvent;", "availableAudioTracks", "", "Lexpo/modules/video/records/AudioTrack;", "oldAvailableAudioTracks", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getAvailableAudioTracks", "()Ljava/util/List;", "getOldAvailableAudioTracks", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/AvailableAudioTracksChangedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/AvailableAudioTracksChangedEventPayload;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class AvailableAudioTracksChanged extends PlayerEvent {
        private final List<AudioTrack> availableAudioTracks;
        private final AvailableAudioTracksChangedEventPayload jsEventPayload;
        private final String name;
        private final List<AudioTrack> oldAvailableAudioTracks;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AvailableAudioTracksChanged copy$default(AvailableAudioTracksChanged availableAudioTracksChanged, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = availableAudioTracksChanged.availableAudioTracks;
            }
            if ((i & 2) != 0) {
                list2 = availableAudioTracksChanged.oldAvailableAudioTracks;
            }
            return availableAudioTracksChanged.copy(list, list2);
        }

        public final List<AudioTrack> component1() {
            return this.availableAudioTracks;
        }

        public final List<AudioTrack> component2() {
            return this.oldAvailableAudioTracks;
        }

        public final AvailableAudioTracksChanged copy(List<AudioTrack> availableAudioTracks, List<AudioTrack> oldAvailableAudioTracks) {
            Intrinsics.checkNotNullParameter(availableAudioTracks, "availableAudioTracks");
            Intrinsics.checkNotNullParameter(oldAvailableAudioTracks, "oldAvailableAudioTracks");
            return new AvailableAudioTracksChanged(availableAudioTracks, oldAvailableAudioTracks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AvailableAudioTracksChanged)) {
                return false;
            }
            AvailableAudioTracksChanged availableAudioTracksChanged = (AvailableAudioTracksChanged) other;
            return Intrinsics.areEqual(this.availableAudioTracks, availableAudioTracksChanged.availableAudioTracks) && Intrinsics.areEqual(this.oldAvailableAudioTracks, availableAudioTracksChanged.oldAvailableAudioTracks);
        }

        public int hashCode() {
            return (this.availableAudioTracks.hashCode() * 31) + this.oldAvailableAudioTracks.hashCode();
        }

        public String toString() {
            return "AvailableAudioTracksChanged(availableAudioTracks=" + this.availableAudioTracks + ", oldAvailableAudioTracks=" + this.oldAvailableAudioTracks + ")";
        }

        public final List<AudioTrack> getAvailableAudioTracks() {
            return this.availableAudioTracks;
        }

        public final List<AudioTrack> getOldAvailableAudioTracks() {
            return this.oldAvailableAudioTracks;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AvailableAudioTracksChanged(List<AudioTrack> availableAudioTracks, List<AudioTrack> oldAvailableAudioTracks) {
            super(null);
            Intrinsics.checkNotNullParameter(availableAudioTracks, "availableAudioTracks");
            Intrinsics.checkNotNullParameter(oldAvailableAudioTracks, "oldAvailableAudioTracks");
            this.availableAudioTracks = availableAudioTracks;
            this.oldAvailableAudioTracks = oldAvailableAudioTracks;
            this.name = "availableAudioTracksChange";
            this.jsEventPayload = new AvailableAudioTracksChangedEventPayload(availableAudioTracks, oldAvailableAudioTracks);
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public AvailableAudioTracksChangedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\u0007HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u0007HÆ\u0003JO\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0018HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\u0018X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006,"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$VideoSourceLoaded;", "Lexpo/modules/video/player/PlayerEvent;", "videoSource", "Lexpo/modules/video/records/VideoSource;", "duration", "", "availableVideoTracks", "", "Lexpo/modules/video/records/VideoTrack;", "availableSubtitleTracks", "Lexpo/modules/video/records/SubtitleTrack;", "availableAudioTracks", "Lexpo/modules/video/records/AudioTrack;", "<init>", "(Lexpo/modules/video/records/VideoSource;DLjava/util/List;Ljava/util/List;Ljava/util/List;)V", "getVideoSource", "()Lexpo/modules/video/records/VideoSource;", "getDuration", "()D", "getAvailableVideoTracks", "()Ljava/util/List;", "getAvailableSubtitleTracks", "getAvailableAudioTracks", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "Lexpo/modules/video/records/VideoSourceLoadedEventPayload;", "getJsEventPayload", "()Lexpo/modules/video/records/VideoSourceLoadedEventPayload;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class VideoSourceLoaded extends PlayerEvent {
        private final List<AudioTrack> availableAudioTracks;
        private final List<SubtitleTrack> availableSubtitleTracks;
        private final List<VideoTrack> availableVideoTracks;
        private final double duration;
        private final VideoSourceLoadedEventPayload jsEventPayload;
        private final String name;
        private final VideoSource videoSource;

        public static /* synthetic */ VideoSourceLoaded copy$default(VideoSourceLoaded videoSourceLoaded, VideoSource videoSource, double d, List list, List list2, List list3, int i, Object obj) {
            if ((i & 1) != 0) {
                videoSource = videoSourceLoaded.videoSource;
            }
            if ((i & 2) != 0) {
                d = videoSourceLoaded.duration;
            }
            if ((i & 4) != 0) {
                list = videoSourceLoaded.availableVideoTracks;
            }
            if ((i & 8) != 0) {
                list2 = videoSourceLoaded.availableSubtitleTracks;
            }
            if ((i & 16) != 0) {
                list3 = videoSourceLoaded.availableAudioTracks;
            }
            List list4 = list3;
            List list5 = list;
            return videoSourceLoaded.copy(videoSource, d, list5, list2, list4);
        }

        /* renamed from: component1, reason: from getter */
        public final VideoSource getVideoSource() {
            return this.videoSource;
        }

        /* renamed from: component2, reason: from getter */
        public final double getDuration() {
            return this.duration;
        }

        public final List<VideoTrack> component3() {
            return this.availableVideoTracks;
        }

        public final List<SubtitleTrack> component4() {
            return this.availableSubtitleTracks;
        }

        public final List<AudioTrack> component5() {
            return this.availableAudioTracks;
        }

        public final VideoSourceLoaded copy(VideoSource videoSource, double duration, List<VideoTrack> availableVideoTracks, List<SubtitleTrack> availableSubtitleTracks, List<AudioTrack> availableAudioTracks) {
            Intrinsics.checkNotNullParameter(availableVideoTracks, "availableVideoTracks");
            Intrinsics.checkNotNullParameter(availableSubtitleTracks, "availableSubtitleTracks");
            Intrinsics.checkNotNullParameter(availableAudioTracks, "availableAudioTracks");
            return new VideoSourceLoaded(videoSource, duration, availableVideoTracks, availableSubtitleTracks, availableAudioTracks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoSourceLoaded)) {
                return false;
            }
            VideoSourceLoaded videoSourceLoaded = (VideoSourceLoaded) other;
            return Intrinsics.areEqual(this.videoSource, videoSourceLoaded.videoSource) && Double.compare(this.duration, videoSourceLoaded.duration) == 0 && Intrinsics.areEqual(this.availableVideoTracks, videoSourceLoaded.availableVideoTracks) && Intrinsics.areEqual(this.availableSubtitleTracks, videoSourceLoaded.availableSubtitleTracks) && Intrinsics.areEqual(this.availableAudioTracks, videoSourceLoaded.availableAudioTracks);
        }

        public int hashCode() {
            VideoSource videoSource = this.videoSource;
            return ((((((((videoSource == null ? 0 : videoSource.hashCode()) * 31) + Double.hashCode(this.duration)) * 31) + this.availableVideoTracks.hashCode()) * 31) + this.availableSubtitleTracks.hashCode()) * 31) + this.availableAudioTracks.hashCode();
        }

        public String toString() {
            return "VideoSourceLoaded(videoSource=" + this.videoSource + ", duration=" + this.duration + ", availableVideoTracks=" + this.availableVideoTracks + ", availableSubtitleTracks=" + this.availableSubtitleTracks + ", availableAudioTracks=" + this.availableAudioTracks + ")";
        }

        public final VideoSource getVideoSource() {
            return this.videoSource;
        }

        public final double getDuration() {
            return this.duration;
        }

        public final List<VideoTrack> getAvailableVideoTracks() {
            return this.availableVideoTracks;
        }

        public final List<SubtitleTrack> getAvailableSubtitleTracks() {
            return this.availableSubtitleTracks;
        }

        public final List<AudioTrack> getAvailableAudioTracks() {
            return this.availableAudioTracks;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VideoSourceLoaded(VideoSource videoSource, double d, List<VideoTrack> availableVideoTracks, List<SubtitleTrack> availableSubtitleTracks, List<AudioTrack> availableAudioTracks) {
            super(null);
            Intrinsics.checkNotNullParameter(availableVideoTracks, "availableVideoTracks");
            Intrinsics.checkNotNullParameter(availableSubtitleTracks, "availableSubtitleTracks");
            Intrinsics.checkNotNullParameter(availableAudioTracks, "availableAudioTracks");
            this.videoSource = videoSource;
            this.duration = d;
            this.availableVideoTracks = availableVideoTracks;
            this.availableSubtitleTracks = availableSubtitleTracks;
            this.availableAudioTracks = availableAudioTracks;
            this.name = "sourceLoad";
            this.jsEventPayload = new VideoSourceLoadedEventPayload(videoSource, d, availableVideoTracks, availableSubtitleTracks, availableAudioTracks);
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public VideoSourceLoadedEventPayload getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007¨\u0006\u0017"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$TimeUpdated;", "Lexpo/modules/video/player/PlayerEvent;", "timeUpdate", "Lexpo/modules/video/records/TimeUpdate;", "<init>", "(Lexpo/modules/video/records/TimeUpdate;)V", "getTimeUpdate", "()Lexpo/modules/video/records/TimeUpdate;", "name", "", "getName", "()Ljava/lang/String;", "jsEventPayload", "getJsEventPayload", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class TimeUpdated extends PlayerEvent {
        private final TimeUpdate jsEventPayload;
        private final String name;
        private final TimeUpdate timeUpdate;

        public static /* synthetic */ TimeUpdated copy$default(TimeUpdated timeUpdated, TimeUpdate timeUpdate, int i, Object obj) {
            if ((i & 1) != 0) {
                timeUpdate = timeUpdated.timeUpdate;
            }
            return timeUpdated.copy(timeUpdate);
        }

        /* renamed from: component1, reason: from getter */
        public final TimeUpdate getTimeUpdate() {
            return this.timeUpdate;
        }

        public final TimeUpdated copy(TimeUpdate timeUpdate) {
            Intrinsics.checkNotNullParameter(timeUpdate, "timeUpdate");
            return new TimeUpdated(timeUpdate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TimeUpdated) && Intrinsics.areEqual(this.timeUpdate, ((TimeUpdated) other).timeUpdate);
        }

        public int hashCode() {
            return this.timeUpdate.hashCode();
        }

        public String toString() {
            return "TimeUpdated(timeUpdate=" + this.timeUpdate + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TimeUpdated(TimeUpdate timeUpdate) {
            super(null);
            Intrinsics.checkNotNullParameter(timeUpdate, "timeUpdate");
            this.timeUpdate = timeUpdate;
            this.name = "timeUpdate";
            this.jsEventPayload = timeUpdate;
        }

        public final TimeUpdate getTimeUpdate() {
            return this.timeUpdate;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public TimeUpdate getJsEventPayload() {
            return this.jsEventPayload;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$AudioMixingModeChanged;", "Lexpo/modules/video/player/PlayerEvent;", "audioMixingMode", "Lexpo/modules/video/enums/AudioMixingMode;", "oldAudioMixingMode", "<init>", "(Lexpo/modules/video/enums/AudioMixingMode;Lexpo/modules/video/enums/AudioMixingMode;)V", "getAudioMixingMode", "()Lexpo/modules/video/enums/AudioMixingMode;", "getOldAudioMixingMode", "name", "", "getName", "()Ljava/lang/String;", "emitToJS", "", "getEmitToJS", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class AudioMixingModeChanged extends PlayerEvent {
        private final AudioMixingMode audioMixingMode;
        private final boolean emitToJS;
        private final String name;
        private final AudioMixingMode oldAudioMixingMode;

        public static /* synthetic */ AudioMixingModeChanged copy$default(AudioMixingModeChanged audioMixingModeChanged, AudioMixingMode audioMixingMode, AudioMixingMode audioMixingMode2, int i, Object obj) {
            if ((i & 1) != 0) {
                audioMixingMode = audioMixingModeChanged.audioMixingMode;
            }
            if ((i & 2) != 0) {
                audioMixingMode2 = audioMixingModeChanged.oldAudioMixingMode;
            }
            return audioMixingModeChanged.copy(audioMixingMode, audioMixingMode2);
        }

        /* renamed from: component1, reason: from getter */
        public final AudioMixingMode getAudioMixingMode() {
            return this.audioMixingMode;
        }

        /* renamed from: component2, reason: from getter */
        public final AudioMixingMode getOldAudioMixingMode() {
            return this.oldAudioMixingMode;
        }

        public final AudioMixingModeChanged copy(AudioMixingMode audioMixingMode, AudioMixingMode oldAudioMixingMode) {
            Intrinsics.checkNotNullParameter(audioMixingMode, "audioMixingMode");
            return new AudioMixingModeChanged(audioMixingMode, oldAudioMixingMode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AudioMixingModeChanged)) {
                return false;
            }
            AudioMixingModeChanged audioMixingModeChanged = (AudioMixingModeChanged) other;
            return this.audioMixingMode == audioMixingModeChanged.audioMixingMode && this.oldAudioMixingMode == audioMixingModeChanged.oldAudioMixingMode;
        }

        public int hashCode() {
            int hashCode = this.audioMixingMode.hashCode() * 31;
            AudioMixingMode audioMixingMode = this.oldAudioMixingMode;
            return hashCode + (audioMixingMode == null ? 0 : audioMixingMode.hashCode());
        }

        public String toString() {
            return "AudioMixingModeChanged(audioMixingMode=" + this.audioMixingMode + ", oldAudioMixingMode=" + this.oldAudioMixingMode + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AudioMixingModeChanged(AudioMixingMode audioMixingMode, AudioMixingMode audioMixingMode2) {
            super(null);
            Intrinsics.checkNotNullParameter(audioMixingMode, "audioMixingMode");
            this.audioMixingMode = audioMixingMode;
            this.oldAudioMixingMode = audioMixingMode2;
            this.name = "audioMixingModeChange";
        }

        public final AudioMixingMode getAudioMixingMode() {
            return this.audioMixingMode;
        }

        public final AudioMixingMode getOldAudioMixingMode() {
            return this.oldAudioMixingMode;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }

        @Override // expo.modules.video.player.PlayerEvent
        public boolean getEmitToJS() {
            return this.emitToJS;
        }
    }

    /* compiled from: PlayerEvent.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/video/player/PlayerEvent$PlayedToEnd;", "Lexpo/modules/video/player/PlayerEvent;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class PlayedToEnd extends PlayerEvent {
        private final String name;

        public PlayedToEnd() {
            super(null);
            this.name = "playToEnd";
        }

        @Override // expo.modules.video.player.PlayerEvent
        public String getName() {
            return this.name;
        }
    }

    public final void emit(VideoPlayer player, List<? extends VideoPlayerListener> listeners) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(listeners, "listeners");
        if (!(this instanceof StatusChanged)) {
            if (!(this instanceof IsPlayingChanged)) {
                if (!(this instanceof VolumeChanged)) {
                    if (!(this instanceof SourceChanged)) {
                        if (!(this instanceof PlaybackRateChanged)) {
                            if (!(this instanceof TracksChanged)) {
                                if (!(this instanceof TrackSelectionParametersChanged)) {
                                    if (!(this instanceof TimeUpdated)) {
                                        if (!(this instanceof PlayedToEnd)) {
                                            if (!(this instanceof MutedChanged)) {
                                                if (!(this instanceof AudioMixingModeChanged)) {
                                                    if (!(this instanceof VideoTrackChanged)) {
                                                        if (!(this instanceof RenderedFirstFrame)) {
                                                            if (this instanceof VideoSourceLoaded) {
                                                                Iterator<T> it = listeners.iterator();
                                                                while (it.hasNext()) {
                                                                    VideoSourceLoaded videoSourceLoaded = (VideoSourceLoaded) this;
                                                                    ((VideoPlayerListener) it.next()).onVideoSourceLoaded(player, videoSourceLoaded.getVideoSource(), Double.valueOf(videoSourceLoaded.getDuration()), videoSourceLoaded.getAvailableVideoTracks(), videoSourceLoaded.getAvailableSubtitleTracks(), videoSourceLoaded.getAvailableAudioTracks());
                                                                }
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        Iterator<T> it2 = listeners.iterator();
                                                        while (it2.hasNext()) {
                                                            ((VideoPlayerListener) it2.next()).onRenderedFirstFrame(player);
                                                        }
                                                        return;
                                                    }
                                                    Iterator<T> it3 = listeners.iterator();
                                                    while (it3.hasNext()) {
                                                        VideoTrackChanged videoTrackChanged = (VideoTrackChanged) this;
                                                        ((VideoPlayerListener) it3.next()).onVideoTrackChanged(player, videoTrackChanged.getVideoTrack(), videoTrackChanged.getOldVideoTrack());
                                                    }
                                                    return;
                                                }
                                                Iterator<T> it4 = listeners.iterator();
                                                while (it4.hasNext()) {
                                                    AudioMixingModeChanged audioMixingModeChanged = (AudioMixingModeChanged) this;
                                                    ((VideoPlayerListener) it4.next()).onAudioMixingModeChanged(player, audioMixingModeChanged.getAudioMixingMode(), audioMixingModeChanged.getOldAudioMixingMode());
                                                }
                                                return;
                                            }
                                            Iterator<T> it5 = listeners.iterator();
                                            while (it5.hasNext()) {
                                                MutedChanged mutedChanged = (MutedChanged) this;
                                                ((VideoPlayerListener) it5.next()).onMutedChanged(player, mutedChanged.getMuted(), mutedChanged.getOldMuted());
                                            }
                                            return;
                                        }
                                        Iterator<T> it6 = listeners.iterator();
                                        while (it6.hasNext()) {
                                            ((VideoPlayerListener) it6.next()).onPlayedToEnd(player);
                                        }
                                        return;
                                    }
                                    Iterator<T> it7 = listeners.iterator();
                                    while (it7.hasNext()) {
                                        ((VideoPlayerListener) it7.next()).onTimeUpdate(player, ((TimeUpdated) this).getTimeUpdate());
                                    }
                                    return;
                                }
                                Iterator<T> it8 = listeners.iterator();
                                while (it8.hasNext()) {
                                    ((VideoPlayerListener) it8.next()).onTrackSelectionParametersChanged(player, ((TrackSelectionParametersChanged) this).getTrackSelectionParameters());
                                }
                                return;
                            }
                            Iterator<T> it9 = listeners.iterator();
                            while (it9.hasNext()) {
                                ((VideoPlayerListener) it9.next()).onTracksChanged(player, ((TracksChanged) this).getTracks());
                            }
                            return;
                        }
                        Iterator<T> it10 = listeners.iterator();
                        while (it10.hasNext()) {
                            PlaybackRateChanged playbackRateChanged = (PlaybackRateChanged) this;
                            ((VideoPlayerListener) it10.next()).onPlaybackRateChanged(player, playbackRateChanged.getRate(), playbackRateChanged.getOldRate());
                        }
                        return;
                    }
                    Iterator<T> it11 = listeners.iterator();
                    while (it11.hasNext()) {
                        SourceChanged sourceChanged = (SourceChanged) this;
                        ((VideoPlayerListener) it11.next()).onSourceChanged(player, sourceChanged.getSource(), sourceChanged.getOldSource());
                    }
                    return;
                }
                Iterator<T> it12 = listeners.iterator();
                while (it12.hasNext()) {
                    VolumeChanged volumeChanged = (VolumeChanged) this;
                    ((VideoPlayerListener) it12.next()).onVolumeChanged(player, volumeChanged.getVolume(), volumeChanged.getOldVolume());
                }
                return;
            }
            Iterator<T> it13 = listeners.iterator();
            while (it13.hasNext()) {
                IsPlayingChanged isPlayingChanged = (IsPlayingChanged) this;
                ((VideoPlayerListener) it13.next()).onIsPlayingChanged(player, isPlayingChanged.isPlaying(), isPlayingChanged.getOldIsPlaying());
            }
            return;
        }
        Iterator<T> it14 = listeners.iterator();
        while (it14.hasNext()) {
            StatusChanged statusChanged = (StatusChanged) this;
            ((VideoPlayerListener) it14.next()).onStatusChanged(player, statusChanged.getStatus(), statusChanged.getOldStatus(), statusChanged.getError());
        }
    }
}
