package expo.modules.video.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.provider.Settings;
import android.view.OrientationEventListener;
import expo.modules.video.enums.FullscreenOrientation;
import expo.modules.video.records.FullscreenOptions;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FullscreenActivityOrientationHelper.kt */
@Metadata(d1 = {"\u00009\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u001a\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\bJ\u0006\u0010#\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001c¨\u0006$"}, d2 = {"Lexpo/modules/video/utils/FullscreenActivityOrientationHelper;", "", "context", "Landroid/content/Context;", "options", "Lexpo/modules/video/records/FullscreenOptions;", "onShouldAutoExit", "Lkotlin/Function0;", "", "onShouldReleaseOrientation", "<init>", "(Landroid/content/Context;Lexpo/modules/video/records/FullscreenOptions;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getContext", "()Landroid/content/Context;", "getOptions", "()Lexpo/modules/video/records/FullscreenOptions;", "getOnShouldAutoExit", "()Lkotlin/jvm/functions/Function0;", "getOnShouldReleaseOrientation", "userHasRotatedToVideoOrientation", "", "isLockedToLandscape", "isLockedToPortrait", "isAutoRotationEnabled", "()Z", "orientationEventListener", "expo/modules/video/utils/FullscreenActivityOrientationHelper$orientationEventListener$2$1", "getOrientationEventListener", "()Lexpo/modules/video/utils/FullscreenActivityOrientationHelper$orientationEventListener$2$1;", "orientationEventListener$delegate", "Lkotlin/Lazy;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "startOrientationEventListener", "stopOrientationEventListener", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FullscreenActivityOrientationHelper {
    private final Context context;
    private final boolean isLockedToLandscape;
    private final boolean isLockedToPortrait;
    private final Function0<Unit> onShouldAutoExit;
    private final Function0<Unit> onShouldReleaseOrientation;
    private final FullscreenOptions options;

    /* renamed from: orientationEventListener$delegate, reason: from kotlin metadata */
    private final Lazy orientationEventListener;
    private boolean userHasRotatedToVideoOrientation;

    public FullscreenActivityOrientationHelper(Context context, FullscreenOptions options, Function0<Unit> onShouldAutoExit, Function0<Unit> onShouldReleaseOrientation) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(onShouldAutoExit, "onShouldAutoExit");
        Intrinsics.checkNotNullParameter(onShouldReleaseOrientation, "onShouldReleaseOrientation");
        this.context = context;
        this.options = options;
        this.onShouldAutoExit = onShouldAutoExit;
        this.onShouldReleaseOrientation = onShouldReleaseOrientation;
        this.isLockedToLandscape = options.getOrientation() == FullscreenOrientation.LANDSCAPE || options.getOrientation() == FullscreenOrientation.LANDSCAPE_LEFT || options.getOrientation() == FullscreenOrientation.LANDSCAPE_RIGHT;
        this.isLockedToPortrait = options.getOrientation() == FullscreenOrientation.PORTRAIT || options.getOrientation() == FullscreenOrientation.PORTRAIT_UP || options.getOrientation() == FullscreenOrientation.PORTRAIT_DOWN;
        this.orientationEventListener = LazyKt.lazy(new Function0() { // from class: expo.modules.video.utils.FullscreenActivityOrientationHelper$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                FullscreenActivityOrientationHelper$orientationEventListener$2$1 orientationEventListener_delegate$lambda$0;
                orientationEventListener_delegate$lambda$0 = FullscreenActivityOrientationHelper.orientationEventListener_delegate$lambda$0(FullscreenActivityOrientationHelper.this);
                return orientationEventListener_delegate$lambda$0;
            }
        });
    }

    public final Context getContext() {
        return this.context;
    }

    public final Function0<Unit> getOnShouldAutoExit() {
        return this.onShouldAutoExit;
    }

    public final Function0<Unit> getOnShouldReleaseOrientation() {
        return this.onShouldReleaseOrientation;
    }

    public final FullscreenOptions getOptions() {
        return this.options;
    }

    public final boolean isAutoRotationEnabled() {
        return Settings.System.getInt(this.context.getContentResolver(), "accelerometer_rotation", 0) == 1;
    }

    private final FullscreenActivityOrientationHelper$orientationEventListener$2$1 getOrientationEventListener() {
        return (FullscreenActivityOrientationHelper$orientationEventListener$2$1) this.orientationEventListener.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v0, types: [expo.modules.video.utils.FullscreenActivityOrientationHelper$orientationEventListener$2$1] */
    public static final FullscreenActivityOrientationHelper$orientationEventListener$2$1 orientationEventListener_delegate$lambda$0(final FullscreenActivityOrientationHelper fullscreenActivityOrientationHelper) {
        final Context context = fullscreenActivityOrientationHelper.context;
        return new OrientationEventListener(context) { // from class: expo.modules.video.utils.FullscreenActivityOrientationHelper$orientationEventListener$2$1
            /* JADX WARN: Code restructure failed: missing block: B:18:0x0063, code lost:
            
                if (r4 != false) goto L44;
             */
            /* JADX WARN: Code restructure failed: missing block: B:20:0x006e, code lost:
            
                if (r5.this$0.isAutoRotationEnabled() != false) goto L47;
             */
            /* JADX WARN: Code restructure failed: missing block: B:21:0x0071, code lost:
            
                r5.this$0.getOnShouldReleaseOrientation().invoke();
                r5.this$0.stopOrientationEventListener();
             */
            /* JADX WARN: Code restructure failed: missing block: B:22:0x007f, code lost:
            
                if (r6 != 1) goto L54;
             */
            /* JADX WARN: Code restructure failed: missing block: B:23:0x0081, code lost:
            
                r3 = r5.this$0.isLockedToPortrait;
             */
            /* JADX WARN: Code restructure failed: missing block: B:24:0x0087, code lost:
            
                if (r3 == false) goto L54;
             */
            /* JADX WARN: Code restructure failed: missing block: B:25:0x0089, code lost:
            
                r3 = r5.this$0.userHasRotatedToVideoOrientation;
             */
            /* JADX WARN: Code restructure failed: missing block: B:26:0x008f, code lost:
            
                if (r3 != false) goto L54;
             */
            /* JADX WARN: Code restructure failed: missing block: B:27:0x0091, code lost:
            
                r3 = true;
             */
            /* JADX WARN: Code restructure failed: missing block: B:28:0x0094, code lost:
            
                if (r6 != 2) goto L61;
             */
            /* JADX WARN: Code restructure failed: missing block: B:29:0x0096, code lost:
            
                r6 = r5.this$0.isLockedToLandscape;
             */
            /* JADX WARN: Code restructure failed: missing block: B:30:0x009c, code lost:
            
                if (r6 == false) goto L61;
             */
            /* JADX WARN: Code restructure failed: missing block: B:31:0x009e, code lost:
            
                r6 = r5.this$0.userHasRotatedToVideoOrientation;
             */
            /* JADX WARN: Code restructure failed: missing block: B:32:0x00a4, code lost:
            
                if (r6 != false) goto L61;
             */
            /* JADX WARN: Code restructure failed: missing block: B:33:0x00a6, code lost:
            
                r0 = true;
             */
            /* JADX WARN: Code restructure failed: missing block: B:34:0x00a7, code lost:
            
                if (r3 != false) goto L65;
             */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x00a9, code lost:
            
                if (r0 == false) goto L64;
             */
            /* JADX WARN: Code restructure failed: missing block: B:36:0x00ac, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x00ad, code lost:
            
                r5.this$0.userHasRotatedToVideoOrientation = true;
             */
            /* JADX WARN: Code restructure failed: missing block: B:39:0x00b2, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:40:0x0093, code lost:
            
                r3 = false;
             */
            /* JADX WARN: Code restructure failed: missing block: B:41:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:42:0x0066, code lost:
            
                if (r3 == false) goto L48;
             */
            /* JADX WARN: Removed duplicated region for block: B:15:0x0055  */
            /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:8:0x003e  */
            @Override // android.view.OrientationEventListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onOrientationChanged(int r6) {
                /*
                    r5 = this;
                    r0 = 0
                    r1 = 2
                    r2 = 1
                    if (r6 < 0) goto L9
                    r3 = 10
                    if (r6 <= r3) goto L11
                L9:
                    r3 = 350(0x15e, float:4.9E-43)
                    if (r6 < r3) goto L13
                    r3 = 360(0x168, float:5.04E-43)
                    if (r6 >= r3) goto L13
                L11:
                    r6 = r2
                    goto L30
                L13:
                    r3 = 80
                    if (r6 < r3) goto L1d
                    r3 = 100
                    if (r6 > r3) goto L1d
                L1b:
                    r6 = r1
                    goto L30
                L1d:
                    r3 = 170(0xaa, float:2.38E-43)
                    if (r6 < r3) goto L26
                    r3 = 190(0xbe, float:2.66E-43)
                    if (r6 > r3) goto L26
                    goto L11
                L26:
                    r3 = 260(0x104, float:3.64E-43)
                    if (r6 < r3) goto L2f
                    r3 = 280(0x118, float:3.92E-43)
                    if (r6 > r3) goto L2f
                    goto L1b
                L2f:
                    r6 = r0
                L30:
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    expo.modules.video.records.FullscreenOptions r3 = r3.getOptions()
                    boolean r3 = r3.getAutoExitOnRotate()
                    if (r3 != 0) goto L3e
                    goto Lac
                L3e:
                    if (r6 != r2) goto L52
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    boolean r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.access$isLockedToLandscape$p(r3)
                    if (r3 == 0) goto L52
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    boolean r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.access$getUserHasRotatedToVideoOrientation$p(r3)
                    if (r3 == 0) goto L52
                    r3 = r2
                    goto L53
                L52:
                    r3 = r0
                L53:
                    if (r6 != r1) goto L66
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r4 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    boolean r4 = expo.modules.video.utils.FullscreenActivityOrientationHelper.access$isLockedToPortrait$p(r4)
                    if (r4 == 0) goto L66
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r4 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    boolean r4 = expo.modules.video.utils.FullscreenActivityOrientationHelper.access$getUserHasRotatedToVideoOrientation$p(r4)
                    if (r4 == 0) goto L66
                    goto L68
                L66:
                    if (r3 == 0) goto L7f
                L68:
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    boolean r3 = r3.isAutoRotationEnabled()
                    if (r3 != 0) goto L71
                    goto Lac
                L71:
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    kotlin.jvm.functions.Function0 r3 = r3.getOnShouldReleaseOrientation()
                    r3.invoke()
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    r3.stopOrientationEventListener()
                L7f:
                    if (r6 != r2) goto L93
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    boolean r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.access$isLockedToPortrait$p(r3)
                    if (r3 == 0) goto L93
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    boolean r3 = expo.modules.video.utils.FullscreenActivityOrientationHelper.access$getUserHasRotatedToVideoOrientation$p(r3)
                    if (r3 != 0) goto L93
                    r3 = r2
                    goto L94
                L93:
                    r3 = r0
                L94:
                    if (r6 != r1) goto La7
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r6 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    boolean r6 = expo.modules.video.utils.FullscreenActivityOrientationHelper.access$isLockedToLandscape$p(r6)
                    if (r6 == 0) goto La7
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r6 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    boolean r6 = expo.modules.video.utils.FullscreenActivityOrientationHelper.access$getUserHasRotatedToVideoOrientation$p(r6)
                    if (r6 != 0) goto La7
                    r0 = r2
                La7:
                    if (r3 != 0) goto Lad
                    if (r0 == 0) goto Lac
                    goto Lad
                Lac:
                    return
                Lad:
                    expo.modules.video.utils.FullscreenActivityOrientationHelper r6 = expo.modules.video.utils.FullscreenActivityOrientationHelper.this
                    expo.modules.video.utils.FullscreenActivityOrientationHelper.access$setUserHasRotatedToVideoOrientation$p(r6, r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: expo.modules.video.utils.FullscreenActivityOrientationHelper$orientationEventListener$2$1.onOrientationChanged(int):void");
            }
        };
    }

    public final void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        int i = newConfig.orientation;
        if (this.options.getAutoExitOnRotate()) {
            if (this.isLockedToPortrait && i == 2) {
                this.onShouldAutoExit.invoke();
            } else if (this.isLockedToLandscape && i == 1) {
                this.onShouldAutoExit.invoke();
            }
        }
    }

    public final void startOrientationEventListener() {
        if (getOrientationEventListener().canDetectOrientation()) {
            getOrientationEventListener().enable();
        }
    }

    public final void stopOrientationEventListener() {
        getOrientationEventListener().disable();
    }
}
