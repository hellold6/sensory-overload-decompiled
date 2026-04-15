package expo.modules.video;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Rational;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.CaptioningManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.ui.PlayerView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.TouchEventCoalescingKeyHelper;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.viewevent.ViewEventCallback;
import expo.modules.kotlin.viewevent.ViewEventDelegate;
import expo.modules.kotlin.views.ExpoView;
import expo.modules.video.delegates.IgnoreSameSet;
import expo.modules.video.enums.AudioMixingMode;
import expo.modules.video.enums.ContentFit;
import expo.modules.video.enums.PlayerStatus;
import expo.modules.video.player.VideoPlayer;
import expo.modules.video.player.VideoPlayerListener;
import expo.modules.video.records.AudioTrack;
import expo.modules.video.records.FullscreenOptions;
import expo.modules.video.records.PlaybackError;
import expo.modules.video.records.SubtitleTrack;
import expo.modules.video.records.TimeUpdate;
import expo.modules.video.records.VideoSource;
import expo.modules.video.records.VideoTrack;
import expo.modules.video.utils.EventDispatcherUtilsKt;
import expo.modules.video.utils.PictureInPictureUtilsKt;
import expo.modules.video.utils.SubtitleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: VideoView.kt */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0017\u0018\u0000 \u009f\u00012\u00020\u00012\u00020\u0002:\u0002\u009f\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010r\u001a\u00020\u0015J\u0006\u0010s\u001a\u00020\u0015J\u0006\u0010t\u001a\u00020\u0015J\u0006\u0010u\u001a\u00020\u0015J\u0006\u0010v\u001a\u00020\u0015J\n\u0010w\u001a\u0004\u0018\u00010xH\u0002J\u0006\u0010y\u001a\u00020\u0015J\u0006\u0010z\u001a\u00020\u0015J^\u0010{\u001a\u00020\u00152\u0006\u0010|\u001a\u00020^2\b\u0010}\u001a\u0004\u0018\u00010~2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u00012\u000f\u0010\u0081\u0001\u001a\n\u0012\u0005\u0012\u00030\u0083\u00010\u0082\u00012\u000f\u0010\u0084\u0001\u001a\n\u0012\u0005\u0012\u00030\u0085\u00010\u0082\u00012\u000f\u0010\u0086\u0001\u001a\n\u0012\u0005\u0012\u00030\u0087\u00010\u0082\u0001H\u0016¢\u0006\u0003\u0010\u0088\u0001J\u001b\u0010\u0089\u0001\u001a\u00020\u00152\u0006\u0010|\u001a\u00020^2\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0016J\u0011\u0010\u008c\u0001\u001a\u00020\u00152\u0006\u0010|\u001a\u00020^H\u0016J\t\u0010\u008d\u0001\u001a\u00020\u0015H\u0016J6\u0010\u008e\u0001\u001a\u00020\u00152\u0007\u0010\u008f\u0001\u001a\u00020\b2\u0007\u0010\u0090\u0001\u001a\u00020?2\u0007\u0010\u0091\u0001\u001a\u00020?2\u0007\u0010\u0092\u0001\u001a\u00020?2\u0007\u0010\u0093\u0001\u001a\u00020?H\u0014J\t\u0010\u0094\u0001\u001a\u00020\u0015H\u0014J\u001b\u0010\u0095\u0001\u001a\u00020\u00152\u0007\u0010\u0096\u0001\u001a\u0002072\u0007\u0010\u0097\u0001\u001a\u00020?H\u0014J\t\u0010\u0098\u0001\u001a\u00020\u0015H\u0014J\u0015\u0010\u0099\u0001\u001a\u00020\b2\n\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u0001H\u0016J\u0015\u0010\u009c\u0001\u001a\u00020\b2\n\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u0001H\u0016J\u0011\u0010\u009d\u0001\u001a\u00020?2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\t\u0010\u009e\u0001\u001a\u00020\u0015H\u0002R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001b\u0010\u0017R!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0019\u001a\u0004\b\u001e\u0010\u0017R!\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00150\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0019\u001a\u0004\b!\u0010\u0017R!\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u0019\u001a\u0004\b$\u0010\u0017R\u001a\u0010&\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*R\u001e\u0010/\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u0010(R\u001e\u00100\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u0010(R\u001e\u00102\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b3\u0010(R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u00108\u001a\n :*\u0004\u0018\u00010909X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010=\u001a\u0012\u0012\u0004\u0012\u00020?0>j\b\u0012\u0004\u0012\u00020?`@X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010H\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010(\"\u0004\bJ\u0010*R*\u0010K\u001a\u0004\u0018\u00010\b2\b\u0010.\u001a\u0004\u0018\u00010\b@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010P\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR+\u0010R\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bU\u0010V\u001a\u0004\bS\u0010(\"\u0004\bT\u0010*R$\u0010X\u001a\u00020W2\u0006\u0010.\u001a\u00020W@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R(\u0010_\u001a\u0004\u0018\u00010^2\b\u0010]\u001a\u0004\u0018\u00010^@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR$\u0010d\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010(\"\u0004\bf\u0010*R$\u0010g\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010(\"\u0004\bi\u0010*R$\u0010k\u001a\u00020j2\u0006\u0010.\u001a\u00020j@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\u000e\u0010p\u001a\u00020qX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 \u0001"}, d2 = {"Lexpo/modules/video/VideoView;", "Lexpo/modules/kotlin/views/ExpoView;", "Lexpo/modules/video/player/VideoPlayerListener;", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", "useTextureView", "", "<init>", "(Landroid/content/Context;Lexpo/modules/kotlin/AppContext;Z)V", "videoViewId", "", "getVideoViewId", "()Ljava/lang/String;", "playerView", "Landroidx/media3/ui/PlayerView;", "getPlayerView", "()Landroidx/media3/ui/PlayerView;", "onPictureInPictureStart", "Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "", "getOnPictureInPictureStart", "()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "onPictureInPictureStart$delegate", "Lexpo/modules/kotlin/viewevent/ViewEventDelegate;", "onPictureInPictureStop", "getOnPictureInPictureStop", "onPictureInPictureStop$delegate", "onFullscreenEnter", "getOnFullscreenEnter", "onFullscreenEnter$delegate", "onFullscreenExit", "getOnFullscreenExit", "onFullscreenExit$delegate", "onFirstFrameRender", "getOnFirstFrameRender", "onFirstFrameRender$delegate", "willEnterPiP", "getWillEnterPiP", "()Z", "setWillEnterPiP", "(Z)V", "wasAutoPaused", "getWasAutoPaused", "setWasAutoPaused", "value", "isInFullscreen", "showsSubtitlesButton", "getShowsSubtitlesButton", "showsAudioTracksButton", "getShowsAudioTracksButton", "currentActivity", "Landroid/app/Activity;", "decorView", "Landroid/view/View;", "rootView", "Landroid/view/ViewGroup;", "kotlin.jvm.PlatformType", "touchEventCoalescingKeyHelper", "Lcom/facebook/react/uimanager/events/TouchEventCoalescingKeyHelper;", "rootViewChildrenOriginalVisibility", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "pictureInPictureHelperTag", "reactNativeEventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "captioningChangeListener", "Landroid/view/accessibility/CaptioningManager$CaptioningChangeListener;", "windowFocusChangeListener", "Landroid/view/View$OnFocusChangeListener;", "shouldHideSurfaceView", "getShouldHideSurfaceView", "setShouldHideSurfaceView", "useExoShutter", "getUseExoShutter", "()Ljava/lang/Boolean;", "setUseExoShutter", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "<set-?>", "autoEnterPiP", "getAutoEnterPiP", "setAutoEnterPiP", "autoEnterPiP$delegate", "Lexpo/modules/video/delegates/IgnoreSameSet;", "Lexpo/modules/video/enums/ContentFit;", "contentFit", "getContentFit", "()Lexpo/modules/video/enums/ContentFit;", "setContentFit", "(Lexpo/modules/video/enums/ContentFit;)V", "newPlayer", "Lexpo/modules/video/player/VideoPlayer;", "videoPlayer", "getVideoPlayer", "()Lexpo/modules/video/player/VideoPlayer;", "setVideoPlayer", "(Lexpo/modules/video/player/VideoPlayer;)V", "useNativeControls", "getUseNativeControls", "setUseNativeControls", "allowsFullscreen", "getAllowsFullscreen", "setAllowsFullscreen", "Lexpo/modules/video/records/FullscreenOptions;", "fullscreenOptions", "getFullscreenOptions", "()Lexpo/modules/video/records/FullscreenOptions;", "setFullscreenOptions", "(Lexpo/modules/video/records/FullscreenOptions;)V", "mLayoutRunnable", "Ljava/lang/Runnable;", "applySurfaceViewVisibility", "enterFullscreen", "attachPlayer", "exitFullscreen", "enterPictureInPicture", "calculateCurrentPipAspectRatio", "Landroid/util/Rational;", "layoutForPiPEnter", "layoutForPiPExit", "onVideoSourceLoaded", "player", "videoSource", "Lexpo/modules/video/records/VideoSource;", "duration", "", "availableVideoTracks", "", "Lexpo/modules/video/records/VideoTrack;", "availableSubtitleTracks", "Lexpo/modules/video/records/SubtitleTrack;", "availableAudioTracks", "Lexpo/modules/video/records/AudioTrack;", "(Lexpo/modules/video/player/VideoPlayer;Lexpo/modules/video/records/VideoSource;Ljava/lang/Double;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "onTracksChanged", "tracks", "Landroidx/media3/common/Tracks;", "onRenderedFirstFrame", "requestLayout", "onLayout", "changed", CmcdData.STREAM_TYPE_LIVE, "t", "r", "b", "onAttachedToWindow", "onVisibilityChanged", "changedView", "visibility", "onDetachedFromWindow", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onInterceptTouchEvent", "getPlayerViewLayoutId", "setupCaptioningChangeListener", "Companion", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class VideoView extends ExpoView implements VideoPlayerListener {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(VideoView.class, "onPictureInPictureStart", "getOnPictureInPictureStart()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(VideoView.class, "onPictureInPictureStop", "getOnPictureInPictureStop()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(VideoView.class, "onFullscreenEnter", "getOnFullscreenEnter()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(VideoView.class, "onFullscreenExit", "getOnFullscreenExit()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(VideoView.class, "onFirstFrameRender", "getOnFirstFrameRender()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(VideoView.class, "autoEnterPiP", "getAutoEnterPiP()Z", 0))};

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean allowsFullscreen;

    /* renamed from: autoEnterPiP$delegate, reason: from kotlin metadata */
    private final IgnoreSameSet autoEnterPiP;
    private CaptioningManager.CaptioningChangeListener captioningChangeListener;
    private ContentFit contentFit;
    private final Activity currentActivity;
    private final View decorView;
    private FullscreenOptions fullscreenOptions;
    private boolean isInFullscreen;
    private final Runnable mLayoutRunnable;

    /* renamed from: onFirstFrameRender$delegate, reason: from kotlin metadata */
    private final ViewEventDelegate onFirstFrameRender;

    /* renamed from: onFullscreenEnter$delegate, reason: from kotlin metadata */
    private final ViewEventDelegate onFullscreenEnter;

    /* renamed from: onFullscreenExit$delegate, reason: from kotlin metadata */
    private final ViewEventDelegate onFullscreenExit;

    /* renamed from: onPictureInPictureStart$delegate, reason: from kotlin metadata */
    private final ViewEventDelegate onPictureInPictureStart;

    /* renamed from: onPictureInPictureStop$delegate, reason: from kotlin metadata */
    private final ViewEventDelegate onPictureInPictureStop;
    private String pictureInPictureHelperTag;
    private final PlayerView playerView;
    private EventDispatcher reactNativeEventDispatcher;
    private final ViewGroup rootView;
    private final ArrayList<Integer> rootViewChildrenOriginalVisibility;
    private boolean shouldHideSurfaceView;
    private boolean showsAudioTracksButton;
    private boolean showsSubtitlesButton;
    private final TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper;
    private Boolean useExoShutter;
    private boolean useNativeControls;
    private VideoPlayer videoPlayer;
    private final String videoViewId;
    private boolean wasAutoPaused;
    private boolean willEnterPiP;
    private final View.OnFocusChangeListener windowFocusChangeListener;

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
    public void onVideoTrackChanged(VideoPlayer videoPlayer, VideoTrack videoTrack, VideoTrack videoTrack2) {
        VideoPlayerListener.DefaultImpls.onVideoTrackChanged(this, videoPlayer, videoTrack, videoTrack2);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onVolumeChanged(VideoPlayer videoPlayer, float f, Float f2) {
        VideoPlayerListener.DefaultImpls.onVolumeChanged(this, videoPlayer, f, f2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoView(final Context context, AppContext appContext, boolean z) {
        super(context, appContext);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        this.videoViewId = uuid;
        View inflate = LayoutInflater.from(context.getApplicationContext()).inflate(getPlayerViewLayoutId(z), (ViewGroup) null);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type androidx.media3.ui.PlayerView");
        PlayerView playerView = (PlayerView) inflate;
        this.playerView = playerView;
        VideoView videoView = this;
        this.onPictureInPictureStart = new ViewEventDelegate(videoView, null);
        this.onPictureInPictureStop = new ViewEventDelegate(videoView, null);
        this.onFullscreenEnter = new ViewEventDelegate(videoView, null);
        this.onFullscreenExit = new ViewEventDelegate(videoView, null);
        this.onFirstFrameRender = new ViewEventDelegate(videoView, null);
        Activity throwingActivity = appContext.getThrowingActivity();
        this.currentActivity = throwingActivity;
        View decorView = throwingActivity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        this.decorView = decorView;
        this.rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        this.touchEventCoalescingKeyHelper = new TouchEventCoalescingKeyHelper();
        this.rootViewChildrenOriginalVisibility = new ArrayList<>();
        this.windowFocusChangeListener = new View.OnFocusChangeListener() { // from class: expo.modules.video.VideoView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z2) {
                VideoView.windowFocusChangeListener$lambda$0(VideoView.this, context, view, z2);
            }
        };
        this.shouldHideSurfaceView = true;
        this.autoEnterPiP = new IgnoreSameSet(false, null, new Function2() { // from class: expo.modules.video.VideoView$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit autoEnterPiP_delegate$lambda$1;
                autoEnterPiP_delegate$lambda$1 = VideoView.autoEnterPiP_delegate$lambda$1(VideoView.this, ((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue());
                return autoEnterPiP_delegate$lambda$1;
            }
        }, 2, null);
        this.contentFit = ContentFit.CONTAIN;
        this.useNativeControls = true;
        this.allowsFullscreen = true;
        this.fullscreenOptions = new FullscreenOptions(false, null, false, 7, null);
        this.mLayoutRunnable = new Runnable() { // from class: expo.modules.video.VideoView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                VideoView.mLayoutRunnable$lambda$6(VideoView.this);
            }
        };
        VideoManager.INSTANCE.registerVideoView(this);
        playerView.setFullscreenButtonClickListener(new PlayerView.FullscreenButtonClickListener() { // from class: expo.modules.video.VideoView$$ExternalSyntheticLambda4
            @Override // androidx.media3.ui.PlayerView.FullscreenButtonClickListener
            public final void onFullscreenButtonClick(boolean z2) {
                VideoView.this.enterFullscreen();
            }
        });
        playerView.setUseController(false);
        playerView.setShutterBackgroundColor(0);
        View videoSurfaceView = playerView.getVideoSurfaceView();
        if (videoSurfaceView != null) {
            videoSurfaceView.setAlpha(0.0f);
        }
        SubtitleUtils.INSTANCE.configureSubtitleView(playerView, context);
        addView(playerView, new ViewGroup.LayoutParams(-1, -1));
        Context reactContext = appContext.getReactContext();
        Intrinsics.checkNotNull(reactContext, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        this.reactNativeEventDispatcher = UIManagerHelper.getEventDispatcher((ReactContext) reactContext, getId());
    }

    public /* synthetic */ VideoView(Context context, AppContext appContext, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, appContext, (i & 4) != 0 ? false : z);
    }

    public final String getVideoViewId() {
        return this.videoViewId;
    }

    public final PlayerView getPlayerView() {
        return this.playerView;
    }

    public final ViewEventCallback<Unit> getOnPictureInPictureStart() {
        return this.onPictureInPictureStart.getValue(this, $$delegatedProperties[0]);
    }

    public final ViewEventCallback<Unit> getOnPictureInPictureStop() {
        return this.onPictureInPictureStop.getValue(this, $$delegatedProperties[1]);
    }

    public final ViewEventCallback<Unit> getOnFullscreenEnter() {
        return this.onFullscreenEnter.getValue(this, $$delegatedProperties[2]);
    }

    public final ViewEventCallback<Unit> getOnFullscreenExit() {
        return this.onFullscreenExit.getValue(this, $$delegatedProperties[3]);
    }

    public final ViewEventCallback<Unit> getOnFirstFrameRender() {
        return this.onFirstFrameRender.getValue(this, $$delegatedProperties[4]);
    }

    public final boolean getWillEnterPiP() {
        return this.willEnterPiP;
    }

    public final void setWillEnterPiP(boolean z) {
        this.willEnterPiP = z;
    }

    public final boolean getWasAutoPaused() {
        return this.wasAutoPaused;
    }

    public final void setWasAutoPaused(boolean z) {
        this.wasAutoPaused = z;
    }

    /* renamed from: isInFullscreen, reason: from getter */
    public final boolean getIsInFullscreen() {
        return this.isInFullscreen;
    }

    public final boolean getShowsSubtitlesButton() {
        return this.showsSubtitlesButton;
    }

    public final boolean getShowsAudioTracksButton() {
        return this.showsAudioTracksButton;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void windowFocusChangeListener$lambda$0(VideoView videoView, Context context, View view, boolean z) {
        if (z) {
            SubtitleUtils.INSTANCE.configureSubtitleView(videoView.playerView, context);
        }
    }

    public final boolean getShouldHideSurfaceView() {
        return this.shouldHideSurfaceView;
    }

    public final void setShouldHideSurfaceView(boolean z) {
        this.shouldHideSurfaceView = z;
    }

    public final Boolean getUseExoShutter() {
        return this.useExoShutter;
    }

    public final void setUseExoShutter(Boolean bool) {
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            this.playerView.setShutterBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.playerView.setShutterBackgroundColor(0);
        }
        applySurfaceViewVisibility();
        this.useExoShutter = bool;
    }

    public final boolean getAutoEnterPiP() {
        return ((Boolean) this.autoEnterPiP.getValue(this, $$delegatedProperties[5])).booleanValue();
    }

    public final void setAutoEnterPiP(boolean z) {
        this.autoEnterPiP.setValue(this, $$delegatedProperties[5], Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoEnterPiP_delegate$lambda$1(VideoView videoView, boolean z, boolean z2) {
        PictureInPictureUtilsKt.applyPiPParams(videoView.currentActivity, z, videoView.calculateCurrentPipAspectRatio());
        return Unit.INSTANCE;
    }

    public final ContentFit getContentFit() {
        return this.contentFit;
    }

    public final void setContentFit(ContentFit value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.playerView.setResizeMode(value.toResizeMode());
        this.contentFit = value;
    }

    public final VideoPlayer getVideoPlayer() {
        return this.videoPlayer;
    }

    public final void setVideoPlayer(VideoPlayer videoPlayer) {
        VideoPlayer videoPlayer2 = this.videoPlayer;
        if (videoPlayer2 != null) {
            VideoManager.INSTANCE.onVideoPlayerDetachedFromView(videoPlayer2, this);
        }
        VideoPlayer videoPlayer3 = this.videoPlayer;
        if (videoPlayer3 != null) {
            videoPlayer3.removeListener(this);
        }
        if (videoPlayer != null) {
            videoPlayer.addListener(this);
        }
        this.videoPlayer = videoPlayer;
        this.shouldHideSurfaceView = !(videoPlayer != null ? videoPlayer.getHasRenderedAFrameOfVideoSource() : false);
        applySurfaceViewVisibility();
        attachPlayer();
        if (videoPlayer != null) {
            VideoManager.INSTANCE.onVideoPlayerAttachedToView(videoPlayer, this);
        }
        if (Intrinsics.areEqual(videoPlayer3, videoPlayer) || videoPlayer3 == null) {
            return;
        }
        videoPlayer3.hasBeenDisconnectedFromPlayerView();
    }

    public final boolean getUseNativeControls() {
        return this.useNativeControls;
    }

    public final void setUseNativeControls(boolean z) {
        this.playerView.setUseController(z);
        this.playerView.setShowSubtitleButton(z);
        this.useNativeControls = z;
    }

    public final boolean getAllowsFullscreen() {
        return this.allowsFullscreen;
    }

    public final void setAllowsFullscreen(boolean z) {
        if (z) {
            this.playerView.setFullscreenButtonClickListener(new PlayerView.FullscreenButtonClickListener() { // from class: expo.modules.video.VideoView$$ExternalSyntheticLambda0
                @Override // androidx.media3.ui.PlayerView.FullscreenButtonClickListener
                public final void onFullscreenButtonClick(boolean z2) {
                    VideoView.this.enterFullscreen();
                }
            });
        } else {
            this.playerView.setFullscreenButtonClickListener(null);
            PlayerViewExtensionKt.setFullscreenButtonVisibility(this.playerView, false);
        }
        this.allowsFullscreen = z;
    }

    public final FullscreenOptions getFullscreenOptions() {
        return this.fullscreenOptions;
    }

    public final void setFullscreenOptions(FullscreenOptions value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.fullscreenOptions = value;
        if (value.getEnable()) {
            this.playerView.setFullscreenButtonClickListener(new PlayerView.FullscreenButtonClickListener() { // from class: expo.modules.video.VideoView$$ExternalSyntheticLambda5
                @Override // androidx.media3.ui.PlayerView.FullscreenButtonClickListener
                public final void onFullscreenButtonClick(boolean z) {
                    VideoView.this.enterFullscreen();
                }
            });
        } else {
            this.playerView.setFullscreenButtonClickListener(null);
            PlayerViewExtensionKt.setFullscreenButtonVisibility(this.playerView, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mLayoutRunnable$lambda$6(VideoView videoView) {
        videoView.measure(View.MeasureSpec.makeMeasureSpec(videoView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(videoView.getHeight(), 1073741824));
        videoView.layout(videoView.getLeft(), videoView.getTop(), videoView.getRight(), videoView.getBottom());
    }

    public final void applySurfaceViewVisibility() {
        if (!Intrinsics.areEqual((Object) this.useExoShutter, (Object) true) && this.shouldHideSurfaceView) {
            View videoSurfaceView = this.playerView.getVideoSurfaceView();
            if (videoSurfaceView != null) {
                videoSurfaceView.setAlpha(0.0f);
                return;
            }
            return;
        }
        View videoSurfaceView2 = this.playerView.getVideoSurfaceView();
        if (videoSurfaceView2 != null) {
            videoSurfaceView2.setAlpha(1.0f);
        }
    }

    public final void enterFullscreen() {
        Intent intent = new Intent(getContext(), (Class<?>) FullscreenPlayerActivity.class);
        intent.putExtra(VideoManager.INTENT_PLAYER_KEY, this.videoViewId);
        intent.putExtra(FullscreenPlayerActivity.INTENT_FULLSCREEN_OPTIONS_KEY, this.fullscreenOptions);
        this.isInFullscreen = true;
        this.currentActivity.startActivity(intent);
        if (Build.VERSION.SDK_INT >= 34) {
            this.currentActivity.overrideActivityTransition(0, 0, 0);
        } else {
            this.currentActivity.overridePendingTransition(0, 0);
        }
        getOnFullscreenEnter().invoke(Unit.INSTANCE);
        PictureInPictureUtilsKt.applyPiPParams(this.currentActivity, false, calculateCurrentPipAspectRatio());
    }

    public final void attachPlayer() {
        VideoPlayer videoPlayer = this.videoPlayer;
        if (videoPlayer != null) {
            videoPlayer.changePlayerView(this.playerView);
        }
    }

    public final void exitFullscreen() {
        View findViewById = this.playerView.findViewById(androidx.media3.ui.R.id.exo_fullscreen);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        ((ImageButton) findViewById).setImageResource(androidx.media3.ui.R.drawable.exo_icon_fullscreen_enter);
        attachPlayer();
        getOnFullscreenExit().invoke(Unit.INSTANCE);
        this.isInFullscreen = false;
        PictureInPictureUtilsKt.applyPiPParams(this.currentActivity, getAutoEnterPiP(), calculateCurrentPipAspectRatio());
    }

    public final void enterPictureInPicture() {
        if (!INSTANCE.isPictureInPictureSupported(this.currentActivity)) {
            throw new PictureInPictureUnsupportedException();
        }
        if (this.playerView.getPlayer() == null) {
            throw new PictureInPictureEnterException("No player attached to the VideoView");
        }
        this.playerView.setUseController(false);
        PictureInPictureUtilsKt.applyPiPParams(this.currentActivity, getAutoEnterPiP(), calculateCurrentPipAspectRatio());
        this.willEnterPiP = true;
        if (Build.VERSION.SDK_INT >= 26) {
            this.currentActivity.enterPictureInPictureMode(new PictureInPictureParams.Builder().build());
        } else {
            this.currentActivity.enterPictureInPictureMode();
        }
    }

    private final Rational calculateCurrentPipAspectRatio() {
        ExoPlayer player;
        VideoPlayer videoPlayer = this.videoPlayer;
        if (videoPlayer == null || (player = videoPlayer.getPlayer()) == null) {
            return null;
        }
        VideoSize videoSize = player.getVideoSize();
        Intrinsics.checkNotNullExpressionValue(videoSize, "getVideoSize(...)");
        return PictureInPictureUtilsKt.calculatePiPAspectRatio(videoSize, getWidth(), getHeight(), this.contentFit);
    }

    public final void layoutForPiPEnter() {
        this.playerView.setUseController(false);
        ViewParent parent = this.playerView.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(this.playerView);
        }
        int childCount = this.rootView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!Intrinsics.areEqual(this.rootView.getChildAt(i), this.playerView)) {
                this.rootViewChildrenOriginalVisibility.add(Integer.valueOf(this.rootView.getChildAt(i).getVisibility()));
                this.rootView.getChildAt(i).setVisibility(8);
            }
        }
        this.rootView.addView(this.playerView, new FrameLayout.LayoutParams(-1, -1));
    }

    public final void layoutForPiPExit() {
        this.playerView.setUseController(this.useNativeControls);
        this.rootView.removeView(this.playerView);
        int childCount = this.rootView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.rootView.getChildAt(i);
            Integer num = this.rootViewChildrenOriginalVisibility.get(i);
            Intrinsics.checkNotNullExpressionValue(num, "get(...)");
            childAt.setVisibility(num.intValue());
        }
        this.rootViewChildrenOriginalVisibility.clear();
        addView(this.playerView);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onVideoSourceLoaded(VideoPlayer player, VideoSource videoSource, Double duration, List<VideoTrack> availableVideoTracks, List<SubtitleTrack> availableSubtitleTracks, List<AudioTrack> availableAudioTracks) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(availableVideoTracks, "availableVideoTracks");
        Intrinsics.checkNotNullParameter(availableSubtitleTracks, "availableSubtitleTracks");
        Intrinsics.checkNotNullParameter(availableAudioTracks, "availableAudioTracks");
        VideoTrack videoTrack = (VideoTrack) CollectionsKt.firstOrNull((List) availableVideoTracks);
        if (videoTrack != null) {
            PictureInPictureUtilsKt.applyPiPParams(this.currentActivity, getAutoEnterPiP(), PictureInPictureUtilsKt.calculatePiPAspectRatio(new VideoSize(videoTrack.getSize().getWidth(), videoTrack.getSize().getHeight()), getWidth(), getHeight(), this.contentFit));
        }
        VideoPlayerListener.DefaultImpls.onVideoSourceLoaded(this, player, videoSource, duration, availableVideoTracks, availableSubtitleTracks, availableAudioTracks);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onTracksChanged(VideoPlayer player, Tracks tracks) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        this.showsSubtitlesButton = !player.getSubtitles().getAvailableSubtitleTracks().isEmpty();
        this.showsAudioTracksButton = player.getAudioTracks().getAvailableAudioTracks().size() > 1;
        this.playerView.setShowSubtitleButton(this.showsSubtitlesButton);
        VideoPlayerListener.DefaultImpls.onTracksChanged(this, player, tracks);
    }

    @Override // expo.modules.video.player.VideoPlayerListener
    public void onRenderedFirstFrame(VideoPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        this.shouldHideSurfaceView = false;
        applySurfaceViewVisibility();
        getOnFirstFrameRender().invoke(Unit.INSTANCE);
    }

    @Override // expo.modules.kotlin.views.ExpoView, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(this.mLayoutRunnable);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        PlayerView playerView = this.playerView;
        VideoPlayer videoPlayer = this.videoPlayer;
        PlayerViewExtensionKt.setTimeBarInteractive(playerView, videoPlayer != null ? videoPlayer.getRequiresLinearPlayback() : true);
        PictureInPictureUtilsKt.applyRectHint(this.currentActivity, PictureInPictureUtilsKt.calculateRectHint(this.playerView));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Activity activity = this.currentActivity;
        FragmentActivity fragmentActivity = activity instanceof FragmentActivity ? (FragmentActivity) activity : null;
        if (fragmentActivity != null) {
            PictureInPictureHelperFragment pictureInPictureHelperFragment = new PictureInPictureHelperFragment(this);
            this.pictureInPictureHelperTag = pictureInPictureHelperFragment.getId();
            fragmentActivity.getSupportFragmentManager().beginTransaction().add(pictureInPictureHelperFragment, pictureInPictureHelperFragment.getId()).commitAllowingStateLoss();
        }
        setupCaptioningChangeListener();
        SubtitleUtils subtitleUtils = SubtitleUtils.INSTANCE;
        PlayerView playerView = this.playerView;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitleUtils.configureSubtitleView(playerView, context);
        this.decorView.setOnFocusChangeListener(this.windowFocusChangeListener);
        PictureInPictureUtilsKt.applyPiPParams$default(this.currentActivity, getAutoEnterPiP(), null, 4, null);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        Intrinsics.checkNotNullParameter(changedView, "changedView");
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == 0) {
            SubtitleUtils subtitleUtils = SubtitleUtils.INSTANCE;
            PlayerView playerView = this.playerView;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            subtitleUtils.configureSubtitleView(playerView, context);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Activity activity = this.currentActivity;
        FragmentActivity fragmentActivity = activity instanceof FragmentActivity ? (FragmentActivity) activity : null;
        if (fragmentActivity != null) {
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            String str = this.pictureInPictureHelperTag;
            if (str == null) {
                str = "";
            }
            Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(str);
            if (findFragmentByTag == null) {
                return;
            } else {
                fragmentActivity.getSupportFragmentManager().beginTransaction().remove(findFragmentByTag).commitAllowingStateLoss();
            }
        }
        CaptioningManager.CaptioningChangeListener captioningChangeListener = this.captioningChangeListener;
        if (captioningChangeListener != null) {
            Object systemService = getContext().getSystemService("captioning");
            CaptioningManager captioningManager = systemService instanceof CaptioningManager ? (CaptioningManager) systemService : null;
            if (captioningManager != null) {
                captioningManager.removeCaptioningChangeListener(captioningChangeListener);
            }
            this.captioningChangeListener = null;
        }
        this.decorView.setOnFocusChangeListener(null);
        PictureInPictureUtilsKt.applyPiPParams$default(this.currentActivity, false, null, 4, null);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (!this.useNativeControls && event != null) {
            this.touchEventCoalescingKeyHelper.addCoalescingKey(event.getEventTime());
            EventDispatcher eventDispatcher = this.reactNativeEventDispatcher;
            if (eventDispatcher != null) {
                EventDispatcherUtilsKt.dispatchMotionEvent(eventDispatcher, this, event, this.touchEventCoalescingKeyHelper);
            }
        }
        if (event != null && event.getActionMasked() == 1) {
            performClick();
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!this.useNativeControls || event == null) {
            return false;
        }
        this.touchEventCoalescingKeyHelper.addCoalescingKey(event.getEventTime());
        EventDispatcher eventDispatcher = this.reactNativeEventDispatcher;
        if (eventDispatcher == null) {
            return false;
        }
        EventDispatcherUtilsKt.dispatchMotionEvent(eventDispatcher, this, MotionEvent.obtainNoHistory(event), this.touchEventCoalescingKeyHelper);
        return false;
    }

    private final int getPlayerViewLayoutId(boolean useTextureView) {
        if (useTextureView) {
            return R.layout.texture_player_view;
        }
        return R.layout.surface_player_view;
    }

    private final void setupCaptioningChangeListener() {
        Object systemService = getContext().getSystemService("captioning");
        CaptioningManager captioningManager = systemService instanceof CaptioningManager ? (CaptioningManager) systemService : null;
        SubtitleUtils subtitleUtils = SubtitleUtils.INSTANCE;
        PlayerView playerView = this.playerView;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        CaptioningManager.CaptioningChangeListener createCaptioningChangeListener = subtitleUtils.createCaptioningChangeListener(playerView, context);
        this.captioningChangeListener = createCaptioningChangeListener;
        if (createCaptioningChangeListener == null || captioningManager == null) {
            return;
        }
        captioningManager.addCaptioningChangeListener(createCaptioningChangeListener);
    }

    /* compiled from: VideoView.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/video/VideoView$Companion;", "", "<init>", "()V", "isPictureInPictureSupported", "", "currentActivity", "Landroid/app/Activity;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isPictureInPictureSupported(Activity currentActivity) {
            Intrinsics.checkNotNullParameter(currentActivity, "currentActivity");
            return Build.VERSION.SDK_INT >= 26 && currentActivity.getPackageManager().hasSystemFeature("android.software.picture_in_picture");
        }
    }
}
