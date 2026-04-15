package expo.modules.video.utils;

import android.content.Context;
import android.view.accessibility.CaptioningManager;
import androidx.media3.ui.CaptionStyleCompat;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.SubtitleView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubtitleUtils.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\f"}, d2 = {"Lexpo/modules/video/utils/SubtitleUtils;", "", "<init>", "()V", "configureSubtitleView", "", "playerView", "Landroidx/media3/ui/PlayerView;", "context", "Landroid/content/Context;", "createCaptioningChangeListener", "Landroid/view/accessibility/CaptioningManager$CaptioningChangeListener;", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubtitleUtils {
    public static final SubtitleUtils INSTANCE = new SubtitleUtils();

    private SubtitleUtils() {
    }

    public final void configureSubtitleView(PlayerView playerView, Context context) {
        Intrinsics.checkNotNullParameter(playerView, "playerView");
        Intrinsics.checkNotNullParameter(context, "context");
        SubtitleView subtitleView = playerView.getSubtitleView();
        if (subtitleView != null) {
            subtitleView.setApplyEmbeddedStyles(false);
            subtitleView.setApplyEmbeddedFontSizes(false);
            Object systemService = context.getSystemService("captioning");
            CaptioningManager captioningManager = systemService instanceof CaptioningManager ? (CaptioningManager) systemService : null;
            CaptioningManager.CaptionStyle userStyle = captioningManager != null ? captioningManager.getUserStyle() : null;
            if (userStyle != null) {
                CaptionStyleCompat createFromCaptionStyle = CaptionStyleCompat.createFromCaptionStyle(userStyle);
                Intrinsics.checkNotNullExpressionValue(createFromCaptionStyle, "createFromCaptionStyle(...)");
                subtitleView.setStyle(createFromCaptionStyle);
                subtitleView.setFixedTextSize(2, captioningManager.getFontScale() * 16.0f);
            }
        }
    }

    public final CaptioningManager.CaptioningChangeListener createCaptioningChangeListener(final PlayerView playerView, final Context context) {
        Intrinsics.checkNotNullParameter(playerView, "playerView");
        Intrinsics.checkNotNullParameter(context, "context");
        return new CaptioningManager.CaptioningChangeListener() { // from class: expo.modules.video.utils.SubtitleUtils$createCaptioningChangeListener$1
            @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
            public void onEnabledChanged(boolean enabled) {
                SubtitleUtils.INSTANCE.configureSubtitleView(PlayerView.this, context);
            }

            @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
            public void onUserStyleChanged(CaptioningManager.CaptionStyle userStyle) {
                Intrinsics.checkNotNullParameter(userStyle, "userStyle");
                SubtitleUtils.INSTANCE.configureSubtitleView(PlayerView.this, context);
            }

            @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
            public void onFontScaleChanged(float fontScale) {
                SubtitleUtils.INSTANCE.configureSubtitleView(PlayerView.this, context);
            }
        };
    }
}
