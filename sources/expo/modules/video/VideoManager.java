package expo.modules.video;

import android.content.Context;
import androidx.media3.exoplayer.ExoPlayer;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.video.playbackService.ExpoVideoPlaybackService;
import expo.modules.video.playbackService.PlaybackServiceBinder;
import expo.modules.video.player.VideoPlayer;
import expo.modules.video.utils.WeakMutableSet;
import expo.modules.video.utils.WeakMutableSetKt;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoManager.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0006\u001a\u00020\bJ\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0005J\u000e\u0010!\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u000fJ\u000e\u0010$\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u000fJ\u0016\u0010%\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u00052\u0006\u0010&\u001a\u00020\rJ\u000e\u0010'\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0005J\u0016\u0010(\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000bJ\u0016\u0010)\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u000fJ\u000e\u0010,\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u000fJ\u000e\u0010-\u001a\u00020.2\u0006\u0010#\u001a\u00020\u000fJ\u0006\u0010/\u001a\u00020.J\u0006\u00100\u001a\u00020\u001cJ\u0006\u00101\u001a\u00020\u001cJ\b\u00102\u001a\u00020\u001cH\u0002J\u0010\u00103\u001a\u00020.2\u0006\u0010\u001e\u001a\u00020\u000bH\u0002J\u0010\u00104\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\nX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00100\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u00065"}, d2 = {"Lexpo/modules/video/VideoManager;", "", "<init>", "()V", "INTENT_PLAYER_KEY", "", "appContext", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "videoViews", "", "Lexpo/modules/video/VideoView;", "fullscreenPlayerActivities", "Lexpo/modules/video/FullscreenPlayerActivity;", "videoPlayersToVideoViews", "Lexpo/modules/video/player/VideoPlayer;", "", "playersRequestingKeepAwake", "Lexpo/modules/video/utils/WeakMutableSet;", "audioFocusManager", "Lexpo/modules/video/AudioFocusManager;", "cache", "Lexpo/modules/video/VideoCache;", "getCache", "()Lexpo/modules/video/VideoCache;", "setCache", "(Lexpo/modules/video/VideoCache;)V", "onModuleCreated", "", "registerVideoView", "videoView", "getVideoView", "id", "unregisterVideoView", "registerVideoPlayer", "videoPlayer", "unregisterVideoPlayer", "registerFullscreenPlayerActivity", "fullscreenActivity", "unregisterFullscreenPlayerActivity", "onVideoPlayerAttachedToView", "onVideoPlayerDetachedFromView", "requestKeepAwake", "player", "releaseKeepAwake", "isVideoPlayerAttachedToView", "", "hasRegisteredPlayers", "onAppForegrounded", "onAppBackgrounded", "applyKeepAwake", "shouldPauseVideo", "handleVideoPause", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoManager {
    public static final String INTENT_PLAYER_KEY = "player_uuid";
    private static AudioFocusManager audioFocusManager;
    public static VideoCache cache;
    public static final VideoManager INSTANCE = new VideoManager();
    private static WeakReference<AppContext> appContext = new WeakReference<>(null);
    private static Map<String, VideoView> videoViews = new LinkedHashMap();
    private static Map<String, WeakReference<FullscreenPlayerActivity>> fullscreenPlayerActivities = new LinkedHashMap();
    private static Map<VideoPlayer, List<VideoView>> videoPlayersToVideoViews = new LinkedHashMap();
    private static WeakMutableSet<VideoPlayer> playersRequestingKeepAwake = WeakMutableSetKt.weakMutableHashSetOf();

    private VideoManager() {
    }

    public final VideoCache getCache() {
        VideoCache videoCache = cache;
        if (videoCache != null) {
            return videoCache;
        }
        Intrinsics.throwUninitializedPropertyAccessException("cache");
        return null;
    }

    public final void setCache(VideoCache videoCache) {
        Intrinsics.checkNotNullParameter(videoCache, "<set-?>");
        cache = videoCache;
    }

    public final void onModuleCreated(AppContext appContext2) {
        Intrinsics.checkNotNullParameter(appContext2, "appContext");
        Context reactContext = appContext2.getReactContext();
        if (reactContext == null) {
            throw new Exceptions.ReactContextLost();
        }
        appContext = new WeakReference<>(appContext2);
        if (audioFocusManager == null) {
            audioFocusManager = new AudioFocusManager(appContext2);
        }
        if (cache == null) {
            setCache(new VideoCache(reactContext));
        }
    }

    public final void registerVideoView(VideoView videoView) {
        Intrinsics.checkNotNullParameter(videoView, "videoView");
        videoViews.put(videoView.getVideoViewId(), videoView);
    }

    public final VideoView getVideoView(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        VideoView videoView = videoViews.get(id);
        if (videoView != null) {
            return videoView;
        }
        throw new VideoViewNotFoundException(id);
    }

    public final void unregisterVideoView(VideoView videoView) {
        Intrinsics.checkNotNullParameter(videoView, "videoView");
        videoViews.remove(videoView.getVideoViewId());
    }

    public final void registerVideoPlayer(VideoPlayer videoPlayer) {
        Intrinsics.checkNotNullParameter(videoPlayer, "videoPlayer");
        Map<VideoPlayer, List<VideoView>> map = videoPlayersToVideoViews;
        ArrayList arrayList = map.get(videoPlayer);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        map.put(videoPlayer, arrayList);
        AudioFocusManager audioFocusManager2 = audioFocusManager;
        if (audioFocusManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioFocusManager");
            audioFocusManager2 = null;
        }
        audioFocusManager2.registerPlayer(videoPlayer);
    }

    public final void unregisterVideoPlayer(VideoPlayer videoPlayer) {
        Intrinsics.checkNotNullParameter(videoPlayer, "videoPlayer");
        videoPlayersToVideoViews.remove(videoPlayer);
        AudioFocusManager audioFocusManager2 = audioFocusManager;
        if (audioFocusManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioFocusManager");
            audioFocusManager2 = null;
        }
        audioFocusManager2.unregisterPlayer(videoPlayer);
    }

    public final void registerFullscreenPlayerActivity(String id, FullscreenPlayerActivity fullscreenActivity) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(fullscreenActivity, "fullscreenActivity");
        fullscreenPlayerActivities.put(id, new WeakReference<>(fullscreenActivity));
    }

    public final void unregisterFullscreenPlayerActivity(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        fullscreenPlayerActivities.remove(id);
    }

    public final void onVideoPlayerAttachedToView(VideoPlayer videoPlayer, VideoView videoView) {
        PlaybackServiceBinder playbackServiceBinder;
        ExpoVideoPlaybackService service;
        Intrinsics.checkNotNullParameter(videoPlayer, "videoPlayer");
        Intrinsics.checkNotNullParameter(videoView, "videoView");
        List<VideoView> list = videoPlayersToVideoViews.get(videoPlayer);
        if (list == null || !list.contains(videoView)) {
            List<VideoView> list2 = videoPlayersToVideoViews.get(videoPlayer);
            if (list2 != null) {
                list2.add(videoView);
            } else {
                videoPlayersToVideoViews.put(videoPlayer, CollectionsKt.arrayListOf(videoView));
            }
            List<VideoView> list3 = videoPlayersToVideoViews.get(videoPlayer);
            if (list3 == null || list3.size() != 1 || (playbackServiceBinder = videoPlayer.getServiceConnection().getPlaybackServiceBinder()) == null || (service = playbackServiceBinder.getService()) == null) {
                return;
            }
            service.registerPlayer(videoPlayer);
        }
    }

    public final void onVideoPlayerDetachedFromView(VideoPlayer videoPlayer, VideoView videoView) {
        PlaybackServiceBinder playbackServiceBinder;
        ExpoVideoPlaybackService service;
        List<VideoView> list;
        Intrinsics.checkNotNullParameter(videoPlayer, "videoPlayer");
        Intrinsics.checkNotNullParameter(videoView, "videoView");
        List<VideoView> list2 = videoPlayersToVideoViews.get(videoPlayer);
        if (list2 != null) {
            list2.remove(videoView);
        }
        if ((videoPlayersToVideoViews.get(videoPlayer) != null && ((list = videoPlayersToVideoViews.get(videoPlayer)) == null || list.size() != 0)) || (playbackServiceBinder = videoPlayer.getServiceConnection().getPlaybackServiceBinder()) == null || (service = playbackServiceBinder.getService()) == null) {
            return;
        }
        service.unregisterPlayer(videoPlayer.getPlayer());
    }

    public final void requestKeepAwake(VideoPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        playersRequestingKeepAwake.add(player);
        applyKeepAwake();
    }

    public final void releaseKeepAwake(VideoPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        playersRequestingKeepAwake.remove(player);
        applyKeepAwake();
    }

    public final boolean isVideoPlayerAttachedToView(VideoPlayer videoPlayer) {
        Intrinsics.checkNotNullParameter(videoPlayer, "videoPlayer");
        if (videoPlayersToVideoViews.get(videoPlayer) != null) {
            return !r2.isEmpty();
        }
        return false;
    }

    public final boolean hasRegisteredPlayers() {
        return !videoPlayersToVideoViews.isEmpty();
    }

    public final void onAppForegrounded() {
        for (VideoView videoView : videoViews.values()) {
            videoView.getPlayerView().setUseController(videoView.getUseNativeControls());
        }
        Iterator<WeakReference<FullscreenPlayerActivity>> it = fullscreenPlayerActivities.values().iterator();
        while (it.hasNext()) {
            FullscreenPlayerActivity fullscreenPlayerActivity = it.next().get();
            if (fullscreenPlayerActivity != null) {
                fullscreenPlayerActivity.finish();
            }
        }
    }

    public final void onAppBackgrounded() {
        for (VideoView videoView : videoViews.values()) {
            if (shouldPauseVideo(videoView)) {
                handleVideoPause(videoView);
            } else {
                videoView.setWasAutoPaused(false);
            }
        }
    }

    private final void applyKeepAwake() {
        Iterator<VideoView> it = videoViews.values().iterator();
        while (it.hasNext()) {
            it.next().setKeepScreenOn(!playersRequestingKeepAwake.isEmpty());
        }
    }

    private final boolean shouldPauseVideo(VideoView videoView) {
        VideoPlayer videoPlayer = videoView.getVideoPlayer();
        return (videoPlayer == null || videoPlayer.getStaysActiveInBackground() || videoView.getWillEnterPiP() || videoView.getIsInFullscreen()) ? false : true;
    }

    private final void handleVideoPause(VideoView videoView) {
        ExoPlayer player;
        videoView.getPlayerView().setUseController(false);
        VideoPlayer videoPlayer = videoView.getVideoPlayer();
        if (videoPlayer == null || (player = videoPlayer.getPlayer()) == null || !player.isPlaying()) {
            return;
        }
        player.pause();
        videoView.setWasAutoPaused(true);
    }
}
