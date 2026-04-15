package expo.modules.video.player;

import androidx.media3.common.Player;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.kotlin.AppContext;
import expo.modules.video.VideoManager;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: VideoPlayerKeepAwake.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0002J$\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u00022\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0096\u0002¢\u0006\u0002\u0010\u001fJ'\u0010 \u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u00022\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0096\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lexpo/modules/video/player/VideoPlayerKeepAwake;", "Lkotlin/properties/ReadWriteProperty;", "", "", "player", "Lexpo/modules/video/player/VideoPlayer;", "appContext", "Lexpo/modules/kotlin/AppContext;", "enableOnInit", "<init>", "(Lexpo/modules/video/player/VideoPlayer;Lexpo/modules/kotlin/AppContext;Z)V", "getAppContext", "()Lexpo/modules/kotlin/AppContext;", "videoPlayer", "Ljava/lang/ref/WeakReference;", "value", ViewProps.ENABLED, "getEnabled", "()Z", "setEnabled", "(Z)V", "playerListener", "Landroidx/media3/common/Player$Listener;", "enable", "", "disable", "createPlayerListener", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Boolean;", "setValue", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoPlayerKeepAwake implements ReadWriteProperty<Object, Boolean> {
    private final AppContext appContext;
    private boolean enabled;
    private Player.Listener playerListener;
    private final WeakReference<VideoPlayer> videoPlayer;

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
        return getValue(obj, (KProperty<?>) kProperty);
    }

    @Override // kotlin.properties.ReadWriteProperty
    public /* bridge */ /* synthetic */ void setValue(Object obj, KProperty kProperty, Boolean bool) {
        setValue(obj, (KProperty<?>) kProperty, bool.booleanValue());
    }

    public VideoPlayerKeepAwake(VideoPlayer player, AppContext appContext, boolean z) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        this.videoPlayer = new WeakReference<>(player);
        this.enabled = z;
        if (z) {
            enable();
        }
    }

    public /* synthetic */ VideoPlayerKeepAwake(VideoPlayer videoPlayer, AppContext appContext, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(videoPlayer, appContext, (i & 4) != 0 ? true : z);
    }

    public final AppContext getAppContext() {
        return this.appContext;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        if (z) {
            enable();
        } else {
            disable();
        }
        this.enabled = z;
    }

    private final void enable() {
        BuildersKt__Builders_commonKt.launch$default(this.appContext.getMainQueue(), null, null, new VideoPlayerKeepAwake$enable$1(this, null), 3, null);
    }

    private final void disable() {
        BuildersKt__Builders_commonKt.launch$default(this.appContext.getMainQueue(), null, null, new VideoPlayerKeepAwake$disable$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Player.Listener createPlayerListener() {
        return new Player.Listener() { // from class: expo.modules.video.player.VideoPlayerKeepAwake$createPlayerListener$1
            @Override // androidx.media3.common.Player.Listener
            public void onIsPlayingChanged(boolean isPlaying) {
                WeakReference weakReference;
                weakReference = VideoPlayerKeepAwake.this.videoPlayer;
                VideoPlayer videoPlayer = (VideoPlayer) weakReference.get();
                if (videoPlayer == null) {
                    return;
                }
                if (isPlaying) {
                    VideoManager.INSTANCE.requestKeepAwake(videoPlayer);
                } else {
                    VideoManager.INSTANCE.releaseKeepAwake(videoPlayer);
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int playbackState) {
                WeakReference weakReference;
                weakReference = VideoPlayerKeepAwake.this.videoPlayer;
                VideoPlayer videoPlayer = (VideoPlayer) weakReference.get();
                if (videoPlayer == null) {
                    return;
                }
                if (playbackState != 1) {
                    if (playbackState == 2 || playbackState == 3) {
                        if (videoPlayer.getPlayer().getPlayWhenReady()) {
                            VideoManager.INSTANCE.requestKeepAwake(videoPlayer);
                            return;
                        }
                        return;
                    } else if (playbackState != 4) {
                        return;
                    }
                }
                VideoManager.INSTANCE.requestKeepAwake(videoPlayer);
            }
        };
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public Boolean getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        return Boolean.valueOf(this.enabled);
    }

    public void setValue(Object thisRef, KProperty<?> property, boolean value) {
        Intrinsics.checkNotNullParameter(property, "property");
        setEnabled(value);
    }
}
