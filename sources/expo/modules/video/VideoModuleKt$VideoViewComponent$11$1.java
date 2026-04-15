package expo.modules.video;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX WARN: Incorrect field signature: TT; */
/* compiled from: VideoModule.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoModuleKt$VideoViewComponent$11$1 implements Function0<Unit> {
    final /* synthetic */ VideoView $view;

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    public VideoModuleKt$VideoViewComponent$11$1(VideoView videoView) {
        this.$view = videoView;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        this.$view.enterPictureInPicture();
    }
}
