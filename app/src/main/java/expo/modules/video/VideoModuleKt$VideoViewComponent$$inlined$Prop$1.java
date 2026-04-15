package expo.modules.video;

import expo.modules.video.player.VideoPlayer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: AnyType.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoModuleKt$VideoViewComponent$$inlined$Prop$1 implements Function0<KType> {
    public static final VideoModuleKt$VideoViewComponent$$inlined$Prop$1 INSTANCE = new VideoModuleKt$VideoViewComponent$$inlined$Prop$1();

    @Override // kotlin.jvm.functions.Function0
    public final KType invoke() {
        return Reflection.typeOf(VideoPlayer.class);
    }
}
