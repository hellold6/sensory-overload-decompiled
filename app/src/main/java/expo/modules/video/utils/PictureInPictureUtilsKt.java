package expo.modules.video.utils;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.util.Rational;
import android.view.View;
import androidx.media3.common.VideoSize;
import androidx.media3.ui.PlayerView;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.video.PictureInPictureConfigurationException;
import expo.modules.video.VideoView;
import expo.modules.video.enums.ContentFit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PictureInPictureUtils.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0000\u001a\"\u0010\u0012\u001a\u00020\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016H\u0000\u001a$\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\u001b"}, d2 = {"calculateRectHint", "Landroid/graphics/Rect;", "playerView", "Landroidx/media3/ui/PlayerView;", "calculatePiPAspectRatio", "Landroid/util/Rational;", "videoSize", "Landroidx/media3/common/VideoSize;", "viewWidth", "", "viewHeight", "contentFit", "Lexpo/modules/video/enums/ContentFit;", "applyRectHint", "", "activity", "Landroid/app/Activity;", "rectHint", "runWithPiPMisconfigurationSoftHandling", "shouldThrow", "", "block", "Lkotlin/Function0;", "", "applyPiPParams", "autoEnterPiP", ViewProps.ASPECT_RATIO, "expo-video_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PictureInPictureUtilsKt {
    public static final Rect calculateRectHint(PlayerView playerView) {
        Intrinsics.checkNotNullParameter(playerView, "playerView");
        Rect rect = new Rect();
        View videoSurfaceView = playerView.getVideoSurfaceView();
        if (videoSurfaceView != null) {
            videoSurfaceView.getGlobalVisibleRect(rect);
        }
        int[] iArr = new int[2];
        View videoSurfaceView2 = playerView.getVideoSurfaceView();
        if (videoSurfaceView2 != null) {
            videoSurfaceView2.getLocationOnScreen(iArr);
        }
        int i = rect.bottom - rect.top;
        rect.top = iArr[1];
        rect.bottom = rect.top + i;
        return rect;
    }

    public static final Rational calculatePiPAspectRatio(VideoSize videoSize, int i, int i2, ContentFit contentFit) {
        Rational rational;
        Intrinsics.checkNotNullParameter(videoSize, "videoSize");
        Intrinsics.checkNotNullParameter(contentFit, "contentFit");
        if (contentFit == ContentFit.CONTAIN) {
            rational = new Rational(videoSize.width, videoSize.height);
        } else {
            rational = new Rational(i, i2);
        }
        Rational rational2 = new Rational(239, 100);
        Rational rational3 = new Rational(100, 239);
        return rational.floatValue() > rational2.floatValue() ? rational2 : rational.floatValue() < rational3.floatValue() ? rational3 : rational;
    }

    public static final void applyRectHint(final Activity activity, final Rect rectHint) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(rectHint, "rectHint");
        if (Build.VERSION.SDK_INT < 26 || !VideoView.Companion.isPictureInPictureSupported(activity)) {
            return;
        }
        runWithPiPMisconfigurationSoftHandling$default(false, new Function0() { // from class: expo.modules.video.utils.PictureInPictureUtilsKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit applyRectHint$lambda$0;
                applyRectHint$lambda$0 = PictureInPictureUtilsKt.applyRectHint$lambda$0(activity, rectHint);
                return applyRectHint$lambda$0;
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit applyRectHint$lambda$0(Activity activity, Rect rect) {
        activity.setPictureInPictureParams(new PictureInPictureParams.Builder().setSourceRectHint(rect).build());
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void runWithPiPMisconfigurationSoftHandling$default(boolean z, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        runWithPiPMisconfigurationSoftHandling(z, function0);
    }

    public static final void runWithPiPMisconfigurationSoftHandling(boolean z, Function0<? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            block.invoke();
        } catch (IllegalStateException unused) {
            Log.e("ExpoVideo", "Current activity does not support picture-in-picture. Make sure you have configured the `expo-video` config plugin correctly.");
            if (z) {
                throw new PictureInPictureConfigurationException();
            }
        }
    }

    public static /* synthetic */ void applyPiPParams$default(Activity activity, boolean z, Rational rational, int i, Object obj) {
        if ((i & 4) != 0) {
            rational = null;
        }
        applyPiPParams(activity, z, rational);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001d, code lost:
    
        if (r1 <= 2.39d) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void applyPiPParams(final android.app.Activity r5, boolean r6, android.util.Rational r7) {
        /*
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = 0
            if (r7 == 0) goto L20
            float r1 = r7.floatValue()
            double r1 = (double) r1
            r3 = 4601209024398258277(0x3fdac73abc947065, double:0.41841)
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 > 0) goto L20
            r3 = 4612564220354725151(0x40031eb851eb851f, double:2.39)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto L20
            goto L21
        L20:
            r7 = r0
        L21:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r1 < r2) goto L4c
            expo.modules.video.VideoView$Companion r1 = expo.modules.video.VideoView.Companion
            boolean r1 = r1.isPictureInPictureSupported(r5)
            if (r1 == 0) goto L4c
            android.app.PictureInPictureParams$Builder r1 = new android.app.PictureInPictureParams$Builder
            r1.<init>()
            if (r7 == 0) goto L39
            r1.setAspectRatio(r7)
        L39:
            int r7 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r7 < r2) goto L42
            r1.setAutoEnterEnabled(r6)
        L42:
            expo.modules.video.utils.PictureInPictureUtilsKt$$ExternalSyntheticLambda0 r6 = new expo.modules.video.utils.PictureInPictureUtilsKt$$ExternalSyntheticLambda0
            r6.<init>()
            r5 = 1
            r7 = 0
            runWithPiPMisconfigurationSoftHandling$default(r7, r6, r5, r0)
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.video.utils.PictureInPictureUtilsKt.applyPiPParams(android.app.Activity, boolean, android.util.Rational):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit applyPiPParams$lambda$3(Activity activity, PictureInPictureParams.Builder builder) {
        activity.setPictureInPictureParams(builder.build());
        return Unit.INSTANCE;
    }
}
