package expo.modules.video.player;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.webkit.URLUtil;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.ui.PlayerView;
import com.google.firebase.messaging.Constants;
import expo.modules.core.logging.Logger;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.sharedobjects.SharedObject;
import expo.modules.video.IntervalUpdateClock;
import expo.modules.video.IntervalUpdateEmitter;
import expo.modules.video.VideoExceptionsKt;
import expo.modules.video.VideoManager;
import expo.modules.video.delegates.IgnoreSameSet;
import expo.modules.video.enums.AudioMixingMode;
import expo.modules.video.enums.PlayerStatus;
import expo.modules.video.playbackService.ExpoVideoPlaybackService;
import expo.modules.video.playbackService.PlaybackServiceBinder;
import expo.modules.video.playbackService.PlaybackServiceConnection;
import expo.modules.video.player.PlayerEvent;
import expo.modules.video.records.AudioTrack;
import expo.modules.video.records.BufferOptions;
import expo.modules.video.records.PlaybackError;
import expo.modules.video.records.SubtitleTrack;
import expo.modules.video.records.VideoSource;
import expo.modules.video.records.VideoTrack;
import expo.modules.video.utils.MutableWeakReference;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VideoPlayer.kt */
@Metadata(d1 = {"\u0000\u0088\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\t\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0004 \u0001£\u0001\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u00032\u00020\u0004B!\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\n\u0010¥\u0001\u001a\u00030¦\u0001H\u0016J\n\u0010§\u0001\u001a\u00030¦\u0001H\u0016J\b\u0010¨\u0001\u001a\u00030¦\u0001J\u0013\u0010©\u0001\u001a\u00030¦\u00012\t\u0010ª\u0001\u001a\u0004\u0018\u00010\u0017J\b\u0010«\u0001\u001a\u00030¦\u0001J\u0011\u0010¬\u0001\u001a\u00020q2\u0006\u0010r\u001a\u00020qH\u0002J\u0013\u0010\u00ad\u0001\u001a\u00020R2\b\u0010®\u0001\u001a\u00030¯\u0001H\u0002J\u001d\u0010U\u001a\u00030¦\u00012\u0006\u0010Q\u001a\u00020R2\n\u0010°\u0001\u001a\u0005\u0018\u00010±\u0001H\u0002J\n\u0010²\u0001\u001a\u00030¦\u0001H\u0002J\n\u0010³\u0001\u001a\u00030¦\u0001H\u0002J\t\u0010´\u0001\u001a\u000207H\u0002J\u0013\u0010µ\u0001\u001a\u00030¦\u00012\u0007\u0010¶\u0001\u001a\u000207H\u0002J\u0011\u0010·\u0001\u001a\u00030¦\u00012\u0007\u0010¸\u0001\u001a\u00020\u0014J\u0011\u0010¹\u0001\u001a\u00030¦\u00012\u0007\u0010¸\u0001\u001a\u00020\u0014J\u0014\u0010º\u0001\u001a\u00030¦\u00012\b\u0010»\u0001\u001a\u00030¼\u0001H\u0002J\t\u0010½\u0001\u001a\u00020-H\u0002J\n\u0010¾\u0001\u001a\u00030¦\u0001H\u0016J\b\u0010¿\u0001\u001a\u00030À\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020)¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010.\u001a\u00020/¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u00102\u001a\u000203¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u001a\u00106\u001a\u000207X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R+\u0010=\u001a\u0002072\u0006\u0010<\u001a\u0002078F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\b>\u00109\"\u0004\b?\u0010;R\u001c\u0010B\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR/\u0010G\u001a\u0004\u0018\u00010\n2\b\u0010<\u001a\u0004\u0018\u00010\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bJ\u0010A\u001a\u0004\bH\u0010D\"\u0004\bI\u0010FR\u001a\u0010K\u001a\u00020LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001a\u0010Q\u001a\u00020RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001a\u0010W\u001a\u000207X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u00109\"\u0004\bY\u0010;R$\u0010[\u001a\u0002072\u0006\u0010Z\u001a\u000207@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u00109\"\u0004\b]\u0010;R$\u0010^\u001a\u0002072\u0006\u0010^\u001a\u000207@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u00109\"\u0004\b`\u0010;R$\u0010a\u001a\u0002072\u0006\u0010Z\u001a\u000207@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u00109\"\u0004\bc\u0010;R\u001a\u0010d\u001a\u00020LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010N\"\u0004\bf\u0010PR\u001a\u0010g\u001a\u000207X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u00109\"\u0004\bh\u0010;R+\u0010i\u001a\u00020L2\u0006\u0010<\u001a\u00020L8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bl\u0010A\u001a\u0004\bj\u0010N\"\u0004\bk\u0010PR+\u0010m\u001a\u0002072\u0006\u0010<\u001a\u0002078F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bp\u0010A\u001a\u0004\bn\u00109\"\u0004\bo\u0010;R+\u0010r\u001a\u00020q2\u0006\u0010<\u001a\u00020q8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bw\u0010A\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u0013\u0010x\u001a\u0004\u0018\u00010L8F¢\u0006\u0006\u001a\u0004\by\u0010zR\u0013\u0010{\u001a\u0004\u0018\u00010|8F¢\u0006\u0006\u001a\u0004\b}\u0010~R)\u0010\u0080\u0001\u001a\u00020\u007f2\u0006\u0010Z\u001a\u00020\u007f@FX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0015\u0010\u0085\u0001\u001a\u00030\u0086\u00018F¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R+\u0010\u008a\u0001\u001a\u00030\u0089\u00012\u0007\u0010Z\u001a\u00030\u0089\u0001@FX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u0006\b\u008d\u0001\u0010\u008e\u0001R \u0010\u008f\u0001\u001a\u0002072\u0006\u0010Z\u001a\u000207@BX\u0086\u000e¢\u0006\t\n\u0000\u001a\u0005\b\u008f\u0001\u00109R/\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0090\u00012\t\u0010Z\u001a\u0005\u0018\u00010\u0090\u0001@BX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001\"\u0006\b\u0094\u0001\u0010\u0095\u0001R1\u0010\u0097\u0001\u001a\n\u0012\u0005\u0012\u00030\u0090\u00010\u0096\u00012\u000e\u0010Z\u001a\n\u0012\u0005\u0012\u00030\u0090\u00010\u0096\u0001@BX\u0086\u000e¢\u0006\n\n\u0000\u001a\u0006\b\u0098\u0001\u0010\u0099\u0001R0\u0010\u009a\u0001\u001a\u0002072\u0006\u0010<\u001a\u0002078F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0006\b\u009d\u0001\u0010\u009e\u0001\u001a\u0005\b\u009b\u0001\u00109\"\u0005\b\u009c\u0001\u0010;R\u0013\u0010\u009f\u0001\u001a\u00030 \u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010¡\u0001R\u0013\u0010¢\u0001\u001a\u00030£\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010¤\u0001¨\u0006Á\u0001"}, d2 = {"Lexpo/modules/video/player/VideoPlayer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "Lexpo/modules/video/IntervalUpdateEmitter;", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", Constants.ScionAnalytics.PARAM_SOURCE, "Lexpo/modules/video/records/VideoSource;", "<init>", "(Landroid/content/Context;Lexpo/modules/kotlin/AppContext;Lexpo/modules/video/records/VideoSource;)V", "getContext", "()Landroid/content/Context;", "renderersFactory", "Landroidx/media3/exoplayer/DefaultRenderersFactory;", "listeners", "", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/video/player/VideoPlayerListener;", "currentPlayerView", "Lexpo/modules/video/utils/MutableWeakReference;", "Landroidx/media3/ui/PlayerView;", "loadControl", "Lexpo/modules/video/player/VideoPlayerLoadControl;", "getLoadControl", "()Lexpo/modules/video/player/VideoPlayerLoadControl;", "subtitles", "Lexpo/modules/video/player/VideoPlayerSubtitles;", "getSubtitles", "()Lexpo/modules/video/player/VideoPlayerSubtitles;", "audioTracks", "Lexpo/modules/video/player/VideoPlayerAudioTracks;", "getAudioTracks", "()Lexpo/modules/video/player/VideoPlayerAudioTracks;", "trackSelector", "Landroidx/media3/exoplayer/trackselection/DefaultTrackSelector;", "getTrackSelector", "()Landroidx/media3/exoplayer/trackselection/DefaultTrackSelector;", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "getPlayer", "()Landroidx/media3/exoplayer/ExoPlayer;", "firstFrameEventGenerator", "Lexpo/modules/video/player/FirstFrameEventGenerator;", "serviceConnection", "Lexpo/modules/video/playbackService/PlaybackServiceConnection;", "getServiceConnection", "()Lexpo/modules/video/playbackService/PlaybackServiceConnection;", "intervalUpdateClock", "Lexpo/modules/video/IntervalUpdateClock;", "getIntervalUpdateClock", "()Lexpo/modules/video/IntervalUpdateClock;", "hasRenderedAFrameOfVideoSource", "", "getHasRenderedAFrameOfVideoSource", "()Z", "setHasRenderedAFrameOfVideoSource", "(Z)V", "<set-?>", "playing", "getPlaying", "setPlaying", "playing$delegate", "Lexpo/modules/video/delegates/IgnoreSameSet;", "uncommittedSource", "getUncommittedSource", "()Lexpo/modules/video/records/VideoSource;", "setUncommittedSource", "(Lexpo/modules/video/records/VideoSource;)V", "commitedSource", "getCommitedSource", "setCommitedSource", "commitedSource$delegate", "userVolume", "", "getUserVolume", "()F", "setUserVolume", "(F)V", "status", "Lexpo/modules/video/enums/PlayerStatus;", "getStatus", "()Lexpo/modules/video/enums/PlayerStatus;", "setStatus", "(Lexpo/modules/video/enums/PlayerStatus;)V", "requiresLinearPlayback", "getRequiresLinearPlayback", "setRequiresLinearPlayback", "value", "staysActiveInBackground", "getStaysActiveInBackground", "setStaysActiveInBackground", "preservesPitch", "getPreservesPitch", "setPreservesPitch", "showNowPlayingNotification", "getShowNowPlayingNotification", "setShowNowPlayingNotification", "duration", "getDuration", "setDuration", "isLive", "setLive", "volume", "getVolume", "setVolume", "volume$delegate", "muted", "getMuted", "setMuted", "muted$delegate", "Landroidx/media3/common/PlaybackParameters;", "playbackParameters", "getPlaybackParameters", "()Landroidx/media3/common/PlaybackParameters;", "setPlaybackParameters", "(Landroidx/media3/common/PlaybackParameters;)V", "playbackParameters$delegate", "currentOffsetFromLive", "getCurrentOffsetFromLive", "()Ljava/lang/Float;", "currentLiveTimestamp", "", "getCurrentLiveTimestamp", "()Ljava/lang/Long;", "Lexpo/modules/video/records/BufferOptions;", "bufferOptions", "getBufferOptions", "()Lexpo/modules/video/records/BufferOptions;", "setBufferOptions", "(Lexpo/modules/video/records/BufferOptions;)V", "bufferedPosition", "", "getBufferedPosition", "()D", "Lexpo/modules/video/enums/AudioMixingMode;", "audioMixingMode", "getAudioMixingMode", "()Lexpo/modules/video/enums/AudioMixingMode;", "setAudioMixingMode", "(Lexpo/modules/video/enums/AudioMixingMode;)V", "isLoadingNewSource", "Lexpo/modules/video/records/VideoTrack;", "currentVideoTrack", "getCurrentVideoTrack", "()Lexpo/modules/video/records/VideoTrack;", "setCurrentVideoTrack", "(Lexpo/modules/video/records/VideoTrack;)V", "", "availableVideoTracks", "getAvailableVideoTracks", "()Ljava/util/List;", "keepScreenOnWhilePlaying", "getKeepScreenOnWhilePlaying", "setKeepScreenOnWhilePlaying", "keepScreenOnWhilePlaying$delegate", "Lexpo/modules/video/player/VideoPlayerKeepAwake;", "playerListener", "expo/modules/video/player/VideoPlayer$playerListener$1", "Lexpo/modules/video/player/VideoPlayer$playerListener$1;", "analyticsListener", "expo/modules/video/player/VideoPlayer$analyticsListener$1", "Lexpo/modules/video/player/VideoPlayer$analyticsListener$1;", "close", "", "deallocate", "hasBeenDisconnectedFromPlayerView", "changePlayerView", "playerView", "prepare", "applyPitchCorrection", "playerStateToPlayerStatus", "state", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Landroidx/media3/common/PlaybackException;", "refreshPlaybackInfo", "resetPlaybackInfo", "startPlaybackService", "serviceSetShowNotification", ExpoVideoPlaybackService.SESSION_SHOW_NOTIFICATION, "addListener", "videoPlayerListener", "removeListener", "sendEvent", NotificationCompat.CATEGORY_EVENT, "Lexpo/modules/video/player/PlayerEvent;", "createFirstFrameEventGenerator", "emitTimeUpdate", "toMetadataRetriever", "Landroid/media/MediaMetadataRetriever;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoPlayer extends SharedObject implements AutoCloseable, IntervalUpdateEmitter {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(VideoPlayer.class, "playing", "getPlaying()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(VideoPlayer.class, "commitedSource", "getCommitedSource()Lexpo/modules/video/records/VideoSource;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(VideoPlayer.class, "volume", "getVolume()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(VideoPlayer.class, "muted", "getMuted()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(VideoPlayer.class, "playbackParameters", "getPlaybackParameters()Landroidx/media3/common/PlaybackParameters;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(VideoPlayer.class, "keepScreenOnWhilePlaying", "getKeepScreenOnWhilePlaying()Z", 0))};
    private final VideoPlayer$analyticsListener$1 analyticsListener;
    private AudioMixingMode audioMixingMode;
    private final VideoPlayerAudioTracks audioTracks;
    private List<VideoTrack> availableVideoTracks;
    private BufferOptions bufferOptions;

    /* renamed from: commitedSource$delegate, reason: from kotlin metadata */
    private final IgnoreSameSet commitedSource;
    private final Context context;
    private MutableWeakReference<PlayerView> currentPlayerView;
    private VideoTrack currentVideoTrack;
    private float duration;
    private final FirstFrameEventGenerator firstFrameEventGenerator;
    private boolean hasRenderedAFrameOfVideoSource;
    private final IntervalUpdateClock intervalUpdateClock;
    private boolean isLive;
    private boolean isLoadingNewSource;

    /* renamed from: keepScreenOnWhilePlaying$delegate, reason: from kotlin metadata */
    private final VideoPlayerKeepAwake keepScreenOnWhilePlaying;
    private List<WeakReference<VideoPlayerListener>> listeners;
    private final VideoPlayerLoadControl loadControl;

    /* renamed from: muted$delegate, reason: from kotlin metadata */
    private final IgnoreSameSet muted;

    /* renamed from: playbackParameters$delegate, reason: from kotlin metadata */
    private final IgnoreSameSet playbackParameters;
    private final ExoPlayer player;
    private final VideoPlayer$playerListener$1 playerListener;

    /* renamed from: playing$delegate, reason: from kotlin metadata */
    private final IgnoreSameSet playing;
    private boolean preservesPitch;
    private DefaultRenderersFactory renderersFactory;
    private boolean requiresLinearPlayback;
    private final PlaybackServiceConnection serviceConnection;
    private boolean showNowPlayingNotification;
    private PlayerStatus status;
    private boolean staysActiveInBackground;
    private final VideoPlayerSubtitles subtitles;
    private final DefaultTrackSelector trackSelector;
    private VideoSource uncommittedSource;
    private float userVolume;

    /* renamed from: volume$delegate, reason: from kotlin metadata */
    private final IgnoreSameSet volume;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v15, types: [expo.modules.video.player.VideoPlayer$playerListener$1] */
    /* JADX WARN: Type inference failed for: r2v1, types: [expo.modules.video.player.VideoPlayer$analyticsListener$1] */
    public VideoPlayer(Context context, AppContext appContext, VideoSource videoSource) {
        super(appContext);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.context = context;
        DefaultRenderersFactory enableDecoderFallback = new DefaultRenderersFactory(context).forceEnableMediaCodecAsynchronousQueueing().setEnableDecoderFallback(true);
        Intrinsics.checkNotNullExpressionValue(enableDecoderFallback, "setEnableDecoderFallback(...)");
        this.renderersFactory = enableDecoderFallback;
        this.listeners = new ArrayList();
        this.currentPlayerView = new MutableWeakReference<>(null);
        VideoPlayerLoadControl videoPlayerLoadControl = new VideoPlayerLoadControl();
        this.loadControl = videoPlayerLoadControl;
        this.subtitles = new VideoPlayerSubtitles(this);
        this.audioTracks = new VideoPlayerAudioTracks(this);
        this.trackSelector = new DefaultTrackSelector(context);
        ExoPlayer build = new ExoPlayer.Builder(context, this.renderersFactory).setLooper(context.getMainLooper()).setLoadControl(videoPlayerLoadControl).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        this.player = build;
        this.firstFrameEventGenerator = createFirstFrameEventGenerator();
        this.serviceConnection = new PlaybackServiceConnection(new WeakReference(this), appContext);
        this.intervalUpdateClock = new IntervalUpdateClock(this);
        this.playing = new IgnoreSameSet(false, null, new Function2() { // from class: expo.modules.video.player.VideoPlayer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit playing_delegate$lambda$0;
                playing_delegate$lambda$0 = VideoPlayer.playing_delegate$lambda$0(VideoPlayer.this, ((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue());
                return playing_delegate$lambda$0;
            }
        }, 2, null);
        this.uncommittedSource = videoSource;
        this.commitedSource = new IgnoreSameSet(null, null, new Function2() { // from class: expo.modules.video.player.VideoPlayer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit commitedSource_delegate$lambda$1;
                commitedSource_delegate$lambda$1 = VideoPlayer.commitedSource_delegate$lambda$1(VideoPlayer.this, (VideoSource) obj, (VideoSource) obj2);
                return commitedSource_delegate$lambda$1;
            }
        }, 2, null);
        this.userVolume = 1.0f;
        this.status = PlayerStatus.IDLE;
        this.volume = new IgnoreSameSet(Float.valueOf(1.0f), null, new Function2() { // from class: expo.modules.video.player.VideoPlayer$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit volume_delegate$lambda$2;
                volume_delegate$lambda$2 = VideoPlayer.volume_delegate$lambda$2(VideoPlayer.this, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
                return volume_delegate$lambda$2;
            }
        }, 2, null);
        this.muted = new IgnoreSameSet(false, null, new Function2() { // from class: expo.modules.video.player.VideoPlayer$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit muted_delegate$lambda$3;
                muted_delegate$lambda$3 = VideoPlayer.muted_delegate$lambda$3(VideoPlayer.this, ((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue());
                return muted_delegate$lambda$3;
            }
        }, 2, null);
        this.playbackParameters = new IgnoreSameSet(PlaybackParameters.DEFAULT, new Function1() { // from class: expo.modules.video.player.VideoPlayer$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                PlaybackParameters playbackParameters_delegate$lambda$4;
                playbackParameters_delegate$lambda$4 = VideoPlayer.playbackParameters_delegate$lambda$4(VideoPlayer.this, (PlaybackParameters) obj);
                return playbackParameters_delegate$lambda$4;
            }
        }, new Function2() { // from class: expo.modules.video.player.VideoPlayer$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit playbackParameters_delegate$lambda$5;
                playbackParameters_delegate$lambda$5 = VideoPlayer.playbackParameters_delegate$lambda$5(VideoPlayer.this, (PlaybackParameters) obj, (PlaybackParameters) obj2);
                return playbackParameters_delegate$lambda$5;
            }
        });
        this.bufferOptions = new BufferOptions(null, 0L, false, 0.0d, 15, null);
        this.audioMixingMode = AudioMixingMode.AUTO;
        this.availableVideoTracks = CollectionsKt.emptyList();
        this.keepScreenOnWhilePlaying = new VideoPlayerKeepAwake(this, appContext, false, 4, null);
        ?? r0 = new Player.Listener() { // from class: expo.modules.video.player.VideoPlayer$playerListener$1
            @Override // androidx.media3.common.Player.Listener
            public void onIsPlayingChanged(boolean isPlaying) {
                VideoPlayer.this.setPlaying(isPlaying);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onTracksChanged(Tracks tracks) {
                List videoTracks;
                VideoSource commitedSource;
                Intrinsics.checkNotNullParameter(tracks, "tracks");
                ArrayList arrayList = new ArrayList(VideoPlayer.this.getSubtitles().getAvailableSubtitleTracks());
                ArrayList arrayList2 = new ArrayList(VideoPlayer.this.getAudioTracks().getAvailableAudioTracks());
                SubtitleTrack currentSubtitleTrack = VideoPlayer.this.getSubtitles().getCurrentSubtitleTrack();
                AudioTrack currentAudioTrack = VideoPlayer.this.getAudioTracks().getCurrentAudioTrack();
                VideoPlayer.this.sendEvent(new PlayerEvent.TracksChanged(tracks));
                ArrayList<SubtitleTrack> availableSubtitleTracks = VideoPlayer.this.getSubtitles().getAvailableSubtitleTracks();
                ArrayList<AudioTrack> availableAudioTracks = VideoPlayer.this.getAudioTracks().getAvailableAudioTracks();
                SubtitleTrack currentSubtitleTrack2 = VideoPlayer.this.getSubtitles().getCurrentSubtitleTrack();
                AudioTrack currentAudioTrack2 = VideoPlayer.this.getAudioTracks().getCurrentAudioTrack();
                VideoPlayer videoPlayer = VideoPlayer.this;
                videoTracks = VideoPlayerKt.toVideoTracks(tracks);
                videoPlayer.availableVideoTracks = videoTracks;
                if (VideoPlayer.this.getIsLoadingNewSource()) {
                    VideoPlayer videoPlayer2 = VideoPlayer.this;
                    commitedSource = VideoPlayer.this.getCommitedSource();
                    videoPlayer2.sendEvent(new PlayerEvent.VideoSourceLoaded(commitedSource, VideoPlayer.this.getPlayer().getDuration() / 1000.0d, VideoPlayer.this.getAvailableVideoTracks(), availableSubtitleTracks, availableAudioTracks));
                    VideoPlayer.this.isLoadingNewSource = false;
                }
                if (!Arrays.equals(arrayList.toArray(), availableSubtitleTracks.toArray())) {
                    VideoPlayer.this.sendEvent(new PlayerEvent.AvailableSubtitleTracksChanged(availableSubtitleTracks, arrayList));
                }
                if (!Arrays.equals(arrayList2.toArray(), availableAudioTracks.toArray())) {
                    VideoPlayer.this.sendEvent(new PlayerEvent.AvailableAudioTracksChanged(availableAudioTracks, arrayList2));
                }
                if (!Intrinsics.areEqual(currentSubtitleTrack, currentSubtitleTrack2)) {
                    VideoPlayer.this.sendEvent(new PlayerEvent.SubtitleTrackChanged(currentSubtitleTrack2, currentSubtitleTrack));
                }
                if (!Intrinsics.areEqual(currentAudioTrack, currentAudioTrack2)) {
                    VideoPlayer.this.sendEvent(new PlayerEvent.AudioTrackChanged(currentAudioTrack2, currentAudioTrack));
                }
                super.onTracksChanged(tracks);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onTrackSelectionParametersChanged(TrackSelectionParameters parameters) {
                Intrinsics.checkNotNullParameter(parameters, "parameters");
                SubtitleTrack currentSubtitleTrack = VideoPlayer.this.getSubtitles().getCurrentSubtitleTrack();
                AudioTrack currentAudioTrack = VideoPlayer.this.getAudioTracks().getCurrentAudioTrack();
                VideoPlayer.this.sendEvent(new PlayerEvent.TrackSelectionParametersChanged(parameters));
                SubtitleTrack currentSubtitleTrack2 = VideoPlayer.this.getSubtitles().getCurrentSubtitleTrack();
                AudioTrack currentAudioTrack2 = VideoPlayer.this.getAudioTracks().getCurrentAudioTrack();
                VideoPlayer.this.sendEvent(new PlayerEvent.SubtitleTrackChanged(currentSubtitleTrack2, currentSubtitleTrack));
                VideoPlayer.this.sendEvent(new PlayerEvent.AudioTrackChanged(currentAudioTrack2, currentAudioTrack));
                super.onTrackSelectionParametersChanged(parameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onMediaItemTransition(MediaItem mediaItem, int reason) {
                if (reason == 0) {
                    VideoPlayer.this.sendEvent(new PlayerEvent.PlayedToEnd());
                } else {
                    VideoPlayer.this.resetPlaybackInfo();
                }
                VideoPlayer.this.getSubtitles().setSubtitlesEnabled(false);
                VideoPlayer.this.setHasRenderedAFrameOfVideoSource(false);
                super.onMediaItemTransition(mediaItem, reason);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int playbackState) {
                PlayerStatus playerStateToPlayerStatus;
                if (playbackState != 1 || VideoPlayer.this.getPlayer().getPlayerError() == null) {
                    if (playbackState == 3) {
                        VideoPlayer.this.refreshPlaybackInfo();
                    }
                    VideoPlayer videoPlayer = VideoPlayer.this;
                    playerStateToPlayerStatus = videoPlayer.playerStateToPlayerStatus(playbackState);
                    videoPlayer.setStatus(playerStateToPlayerStatus, null);
                    super.onPlaybackStateChanged(playbackState);
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onVolumeChanged(float volume) {
                if (VideoPlayer.this.getMuted()) {
                    return;
                }
                VideoPlayer.this.setVolume(volume);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                Intrinsics.checkNotNullParameter(playbackParameters, "playbackParameters");
                VideoPlayer.this.setPlaybackParameters(playbackParameters);
                super.onPlaybackParametersChanged(playbackParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlayerErrorChanged(PlaybackException error) {
                PlayerStatus playerStateToPlayerStatus;
                if (error != null) {
                    VideoPlayer videoPlayer = VideoPlayer.this;
                    videoPlayer.resetPlaybackInfo();
                    videoPlayer.setStatus(PlayerStatus.ERROR, error);
                } else {
                    VideoPlayer videoPlayer2 = VideoPlayer.this;
                    playerStateToPlayerStatus = videoPlayer2.playerStateToPlayerStatus(videoPlayer2.getPlayer().getPlaybackState());
                    videoPlayer2.setStatus(playerStateToPlayerStatus, null);
                }
                super.onPlayerErrorChanged(error);
            }
        };
        this.playerListener = r0;
        ?? r2 = new AnalyticsListener() { // from class: expo.modules.video.player.VideoPlayer$analyticsListener$1
            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
                Object obj;
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                Intrinsics.checkNotNullParameter(format, "format");
                VideoPlayer videoPlayer = VideoPlayer.this;
                Iterator<T> it = videoPlayer.getAvailableVideoTracks().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    Format format2 = ((VideoTrack) next).getFormat();
                    if (Intrinsics.areEqual(format2 != null ? format2.id : null, format.id)) {
                        obj = next;
                        break;
                    }
                }
                videoPlayer.setCurrentVideoTrack((VideoTrack) obj);
                super.onVideoInputFormatChanged(eventTime, format, decoderReuseEvaluation);
            }
        };
        this.analyticsListener = r2;
        build.addListener((Player.Listener) r0);
        build.addAnalyticsListener((AnalyticsListener) r2);
        VideoManager.INSTANCE.registerVideoPlayer(this);
        BuildersKt__Builders_commonKt.launch$default(appContext.getMainQueue(), null, null, new AnonymousClass1(null), 3, null);
    }

    public final Context getContext() {
        return this.context;
    }

    public final VideoPlayerLoadControl getLoadControl() {
        return this.loadControl;
    }

    public final VideoPlayerSubtitles getSubtitles() {
        return this.subtitles;
    }

    public final VideoPlayerAudioTracks getAudioTracks() {
        return this.audioTracks;
    }

    public final DefaultTrackSelector getTrackSelector() {
        return this.trackSelector;
    }

    public final ExoPlayer getPlayer() {
        return this.player;
    }

    public final PlaybackServiceConnection getServiceConnection() {
        return this.serviceConnection;
    }

    public final IntervalUpdateClock getIntervalUpdateClock() {
        return this.intervalUpdateClock;
    }

    public final boolean getHasRenderedAFrameOfVideoSource() {
        return this.hasRenderedAFrameOfVideoSource;
    }

    public final void setHasRenderedAFrameOfVideoSource(boolean z) {
        this.hasRenderedAFrameOfVideoSource = z;
    }

    public final boolean getPlaying() {
        return ((Boolean) this.playing.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final void setPlaying(boolean z) {
        this.playing.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit playing_delegate$lambda$0(VideoPlayer videoPlayer, boolean z, boolean z2) {
        videoPlayer.sendEvent(new PlayerEvent.IsPlayingChanged(z, Boolean.valueOf(z2)));
        return Unit.INSTANCE;
    }

    public final VideoSource getUncommittedSource() {
        return this.uncommittedSource;
    }

    public final void setUncommittedSource(VideoSource videoSource) {
        this.uncommittedSource = videoSource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideoSource getCommitedSource() {
        return (VideoSource) this.commitedSource.getValue(this, $$delegatedProperties[1]);
    }

    private final void setCommitedSource(VideoSource videoSource) {
        this.commitedSource.setValue(this, $$delegatedProperties[1], videoSource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit commitedSource_delegate$lambda$1(VideoPlayer videoPlayer, VideoSource videoSource, VideoSource videoSource2) {
        videoPlayer.sendEvent(new PlayerEvent.SourceChanged(videoSource, videoSource2));
        return Unit.INSTANCE;
    }

    public final float getUserVolume() {
        return this.userVolume;
    }

    public final void setUserVolume(float f) {
        this.userVolume = f;
    }

    public final PlayerStatus getStatus() {
        return this.status;
    }

    public final void setStatus(PlayerStatus playerStatus) {
        Intrinsics.checkNotNullParameter(playerStatus, "<set-?>");
        this.status = playerStatus;
    }

    public final boolean getRequiresLinearPlayback() {
        return this.requiresLinearPlayback;
    }

    public final void setRequiresLinearPlayback(boolean z) {
        this.requiresLinearPlayback = z;
    }

    public final boolean getStaysActiveInBackground() {
        return this.staysActiveInBackground;
    }

    public final void setStaysActiveInBackground(boolean z) {
        this.staysActiveInBackground = z;
        if (z) {
            startPlaybackService();
        }
    }

    public final boolean getPreservesPitch() {
        return this.preservesPitch;
    }

    public final void setPreservesPitch(boolean z) {
        this.preservesPitch = z;
        setPlaybackParameters(applyPitchCorrection(getPlaybackParameters()));
    }

    public final boolean getShowNowPlayingNotification() {
        return this.showNowPlayingNotification;
    }

    public final void setShowNowPlayingNotification(boolean z) {
        this.showNowPlayingNotification = z;
        serviceSetShowNotification(z);
    }

    public final float getDuration() {
        return this.duration;
    }

    public final void setDuration(float f) {
        this.duration = f;
    }

    /* renamed from: isLive, reason: from getter */
    public final boolean getIsLive() {
        return this.isLive;
    }

    public final void setLive(boolean z) {
        this.isLive = z;
    }

    public final float getVolume() {
        return ((Number) this.volume.getValue(this, $$delegatedProperties[2])).floatValue();
    }

    public final void setVolume(float f) {
        this.volume.setValue(this, $$delegatedProperties[2], Float.valueOf(f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit volume_delegate$lambda$2(VideoPlayer videoPlayer, float f, float f2) {
        videoPlayer.player.setVolume(videoPlayer.getMuted() ? 0.0f : f);
        videoPlayer.userVolume = videoPlayer.getVolume();
        videoPlayer.sendEvent(new PlayerEvent.VolumeChanged(f, Float.valueOf(f2)));
        return Unit.INSTANCE;
    }

    public final boolean getMuted() {
        return ((Boolean) this.muted.getValue(this, $$delegatedProperties[3])).booleanValue();
    }

    public final void setMuted(boolean z) {
        this.muted.setValue(this, $$delegatedProperties[3], Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit muted_delegate$lambda$3(VideoPlayer videoPlayer, boolean z, boolean z2) {
        videoPlayer.player.setVolume(z ? 0.0f : videoPlayer.userVolume);
        videoPlayer.sendEvent(new PlayerEvent.MutedChanged(z, Boolean.valueOf(z2)));
        return Unit.INSTANCE;
    }

    public final PlaybackParameters getPlaybackParameters() {
        return (PlaybackParameters) this.playbackParameters.getValue(this, $$delegatedProperties[4]);
    }

    public final void setPlaybackParameters(PlaybackParameters playbackParameters) {
        Intrinsics.checkNotNullParameter(playbackParameters, "<set-?>");
        this.playbackParameters.setValue(this, $$delegatedProperties[4], playbackParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlaybackParameters playbackParameters_delegate$lambda$4(VideoPlayer videoPlayer, PlaybackParameters it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return videoPlayer.applyPitchCorrection(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit playbackParameters_delegate$lambda$5(VideoPlayer videoPlayer, PlaybackParameters playbackParameters, PlaybackParameters old) {
        Intrinsics.checkNotNullParameter(playbackParameters, "new");
        Intrinsics.checkNotNullParameter(old, "old");
        videoPlayer.player.setPlaybackParameters(playbackParameters);
        if (old.speed != playbackParameters.speed) {
            videoPlayer.sendEvent(new PlayerEvent.PlaybackRateChanged(playbackParameters.speed, Float.valueOf(old.speed)));
        }
        return Unit.INSTANCE;
    }

    public final Float getCurrentOffsetFromLive() {
        if (this.player.getCurrentLiveOffset() == C.TIME_UNSET) {
            return null;
        }
        return Float.valueOf(((float) this.player.getCurrentLiveOffset()) / 1000.0f);
    }

    public final Long getCurrentLiveTimestamp() {
        Timeline.Window window = new Timeline.Window();
        if (!this.player.getCurrentTimeline().isEmpty()) {
            this.player.getCurrentTimeline().getWindow(this.player.getCurrentMediaItemIndex(), window);
        }
        if (window.windowStartTimeMs == C.TIME_UNSET) {
            return null;
        }
        return Long.valueOf(window.windowStartTimeMs + this.player.getCurrentPosition());
    }

    public final BufferOptions getBufferOptions() {
        return this.bufferOptions;
    }

    public final void setBufferOptions(BufferOptions value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.bufferOptions = value;
        this.loadControl.applyBufferOptions(value);
    }

    public final double getBufferedPosition() {
        if (this.player.getCurrentMediaItem() == null) {
            return -1.0d;
        }
        if (this.player.getPlaybackState() == 2) {
            return 0.0d;
        }
        return this.player.getBufferedPosition() / 1000.0d;
    }

    public final AudioMixingMode getAudioMixingMode() {
        return this.audioMixingMode;
    }

    public final void setAudioMixingMode(AudioMixingMode value) {
        Intrinsics.checkNotNullParameter(value, "value");
        AudioMixingMode audioMixingMode = this.audioMixingMode;
        this.audioMixingMode = value;
        sendEvent(new PlayerEvent.AudioMixingModeChanged(value, audioMixingMode));
    }

    /* renamed from: isLoadingNewSource, reason: from getter */
    public final boolean getIsLoadingNewSource() {
        return this.isLoadingNewSource;
    }

    public final VideoTrack getCurrentVideoTrack() {
        return this.currentVideoTrack;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentVideoTrack(VideoTrack videoTrack) {
        VideoTrack videoTrack2 = this.currentVideoTrack;
        this.currentVideoTrack = videoTrack;
        sendEvent(new PlayerEvent.VideoTrackChanged(videoTrack, videoTrack2));
    }

    public final List<VideoTrack> getAvailableVideoTracks() {
        return this.availableVideoTracks;
    }

    public final boolean getKeepScreenOnWhilePlaying() {
        return this.keepScreenOnWhilePlaying.getValue((Object) this, $$delegatedProperties[5]).booleanValue();
    }

    public final void setKeepScreenOnWhilePlaying(boolean z) {
        this.keepScreenOnWhilePlaying.setValue(this, $$delegatedProperties[5], z);
    }

    /* compiled from: VideoPlayer.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "expo.modules.video.player.VideoPlayer$1", f = "VideoPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: expo.modules.video.player.VideoPlayer$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            VideoPlayer.this.getSubtitles().setSubtitlesEnabled(false);
            return Unit.INSTANCE;
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        CoroutineScope mainQueue;
        ExpoVideoPlaybackService service;
        AppContext appContext;
        Context reactContext;
        if (this.serviceConnection.getIsConnected() && (appContext = getAppContext()) != null && (reactContext = appContext.getReactContext()) != null) {
            reactContext.unbindService(this.serviceConnection);
        }
        PlaybackServiceBinder playbackServiceBinder = this.serviceConnection.getPlaybackServiceBinder();
        if (playbackServiceBinder != null && (service = playbackServiceBinder.getService()) != null) {
            service.unregisterPlayer(this.player);
        }
        VideoManager.INSTANCE.unregisterVideoPlayer(this);
        AppContext appContext2 = getAppContext();
        if (appContext2 != null && (mainQueue = appContext2.getMainQueue()) != null) {
            BuildersKt__Builders_commonKt.launch$default(mainQueue, null, null, new VideoPlayer$close$1(this, null), 3, null);
        }
        this.uncommittedSource = null;
        setCommitedSource(null);
        setKeepScreenOnWhilePlaying(false);
    }

    @Override // expo.modules.kotlin.sharedobjects.SharedObject
    public void deallocate() {
        super.deallocate();
        close();
    }

    public final void hasBeenDisconnectedFromPlayerView() {
        PlayerView playerView = this.currentPlayerView.get();
        if (Intrinsics.areEqual(playerView != null ? playerView.getPlayer() : null, this.player)) {
            throw new IllegalStateException("The player has been notified of disconnection from the player view, even though it's still connected.");
        }
        this.currentPlayerView.set(null);
    }

    public final void changePlayerView(PlayerView playerView) {
        PlayerView.switchTargetView(this.player, this.currentPlayerView.get(), playerView);
        this.currentPlayerView.set(playerView);
    }

    public final void prepare() {
        this.availableVideoTracks = CollectionsKt.emptyList();
        setCurrentVideoTrack(null);
        VideoSource videoSource = this.uncommittedSource;
        MediaSource mediaSource = videoSource != null ? videoSource.toMediaSource(this.context) : null;
        if (mediaSource != null) {
            this.player.setMediaSource(mediaSource);
            this.player.prepare();
            setCommitedSource(videoSource);
            this.uncommittedSource = null;
            this.isLoadingNewSource = true;
            return;
        }
        this.player.clearMediaItems();
        this.player.prepare();
        this.isLoadingNewSource = false;
    }

    private final PlaybackParameters applyPitchCorrection(PlaybackParameters playbackParameters) {
        float f = playbackParameters.speed;
        return new PlaybackParameters(f, this.preservesPitch ? 1.0f : f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlayerStatus playerStateToPlayerStatus(int state) {
        if (state == 1) {
            return PlayerStatus.IDLE;
        }
        if (state == 2) {
            return PlayerStatus.LOADING;
        }
        if (state == 3) {
            return PlayerStatus.READY_TO_PLAY;
        }
        if (state == 4) {
            if (this.player.getPlayerError() != null) {
                return PlayerStatus.ERROR;
            }
            return PlayerStatus.IDLE;
        }
        return PlayerStatus.IDLE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStatus(PlayerStatus status, PlaybackException error) {
        PlayerStatus playerStatus = this.status;
        this.status = status;
        PlaybackError playbackError = error != null ? new PlaybackError(error) : null;
        if (playbackError == null && this.player.getPlaybackState() == 4) {
            sendEvent(new PlayerEvent.PlayedToEnd());
        }
        if (this.status != playerStatus) {
            sendEvent(new PlayerEvent.StatusChanged(status, playerStatus, playbackError));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshPlaybackInfo() {
        this.duration = ((float) this.player.getDuration()) / 1000.0f;
        this.isLive = this.player.isCurrentMediaItemLive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetPlaybackInfo() {
        this.duration = 0.0f;
        this.isLive = false;
    }

    private final boolean startPlaybackService() {
        Logger jsLogger;
        PlaybackServiceBinder playbackServiceBinder = this.serviceConnection.getPlaybackServiceBinder();
        if ((playbackServiceBinder != null ? playbackServiceBinder.getService() : null) != null) {
            return true;
        }
        AppContext appContext = getAppContext();
        if (appContext == null) {
            throw new Exceptions.AppContextLost();
        }
        boolean startService = ExpoVideoPlaybackService.INSTANCE.startService(appContext, this.context, this.serviceConnection);
        if (!startService && (jsLogger = appContext.getJsLogger()) != null) {
            Logger.error$default(jsLogger, VideoExceptionsKt.getPlaybackServiceErrorMessage$default("Expo-video has failed to bind with the playback service binder", null, 2, null), null, 2, null);
        }
        return startService;
    }

    private final void serviceSetShowNotification(boolean showNotification) {
        ExpoVideoPlaybackService service;
        if (showNotification) {
            startPlaybackService();
        }
        PlaybackServiceBinder playbackServiceBinder = this.serviceConnection.getPlaybackServiceBinder();
        if (playbackServiceBinder == null || (service = playbackServiceBinder.getService()) == null) {
            return;
        }
        service.setShowNotification(showNotification, this.player);
    }

    public final void addListener(VideoPlayerListener videoPlayerListener) {
        Intrinsics.checkNotNullParameter(videoPlayerListener, "videoPlayerListener");
        List<WeakReference<VideoPlayerListener>> list = this.listeners;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((WeakReference) it.next()).get(), videoPlayerListener)) {
                    return;
                }
            }
        }
        this.listeners.add(new WeakReference<>(videoPlayerListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeListener$lambda$10(VideoPlayerListener videoPlayerListener, WeakReference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it.get(), videoPlayerListener);
    }

    public final void removeListener(final VideoPlayerListener videoPlayerListener) {
        Intrinsics.checkNotNullParameter(videoPlayerListener, "videoPlayerListener");
        CollectionsKt.removeAll((List) this.listeners, new Function1() { // from class: expo.modules.video.player.VideoPlayer$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean removeListener$lambda$10;
                removeListener$lambda$10 = VideoPlayer.removeListener$lambda$10(VideoPlayerListener.this, (WeakReference) obj);
                return Boolean.valueOf(removeListener$lambda$10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendEvent(PlayerEvent event) {
        List list = CollectionsKt.toList(this.listeners);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            VideoPlayerListener videoPlayerListener = (VideoPlayerListener) ((WeakReference) it.next()).get();
            if (videoPlayerListener != null) {
                arrayList.add(videoPlayerListener);
            }
        }
        event.emit(this, arrayList);
        if (event.getEmitToJS()) {
            emit(event.getName(), event.getJsEventPayload());
        }
    }

    private final FirstFrameEventGenerator createFirstFrameEventGenerator() {
        return new FirstFrameEventGenerator(this.player, this.currentPlayerView, new Function0() { // from class: expo.modules.video.player.VideoPlayer$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit createFirstFrameEventGenerator$lambda$12;
                createFirstFrameEventGenerator$lambda$12 = VideoPlayer.createFirstFrameEventGenerator$lambda$12(VideoPlayer.this);
                return createFirstFrameEventGenerator$lambda$12;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createFirstFrameEventGenerator$lambda$12(VideoPlayer videoPlayer) {
        videoPlayer.hasRenderedAFrameOfVideoSource = true;
        videoPlayer.sendEvent(new PlayerEvent.RenderedFirstFrame());
        return Unit.INSTANCE;
    }

    @Override // expo.modules.video.IntervalUpdateEmitter
    public void emitTimeUpdate() {
        CoroutineScope mainQueue;
        AppContext appContext = getAppContext();
        if (appContext == null || (mainQueue = appContext.getMainQueue()) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(mainQueue, null, null, new VideoPlayer$emitTimeUpdate$1(this, null), 3, null);
    }

    public final MediaMetadataRetriever toMetadataRetriever() {
        Uri uri;
        VideoSource videoSource = this.uncommittedSource;
        if (videoSource == null) {
            videoSource = getCommitedSource();
        }
        if (videoSource == null || (uri = videoSource.getUri()) == null) {
            throw new IllegalStateException("Video source is not set");
        }
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        if (URLUtil.isFileUrl(uri2)) {
            mediaMetadataRetriever.setDataSource(StringsKt.replace$default(uri2, "file://", "", false, 4, (Object) null));
            return mediaMetadataRetriever;
        }
        if (URLUtil.isContentUrl(uri2)) {
            ParcelFileDescriptor openFileDescriptor = this.context.getContentResolver().openFileDescriptor(uri, "r");
            if (openFileDescriptor == null) {
                return mediaMetadataRetriever;
            }
            FileInputStream fileInputStream = openFileDescriptor;
            try {
                fileInputStream = new FileInputStream(fileInputStream.getFileDescriptor());
                try {
                    mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    return mediaMetadataRetriever;
                } finally {
                }
            } finally {
            }
        } else {
            Map<String, String> headers = videoSource.getHeaders();
            if (headers == null) {
                headers = MapsKt.emptyMap();
            }
            mediaMetadataRetriever.setDataSource(uri2, headers);
            return mediaMetadataRetriever;
        }
    }
}
