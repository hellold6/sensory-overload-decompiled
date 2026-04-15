package expo.modules.audio;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AudioModule.kt */
@kotlin.Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioModule$definition$1$3$1 implements Function0<Unit> {
    final /* synthetic */ AudioModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioModule$definition$1$3$1(AudioModule audioModule) {
        this.this$0 = audioModule;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ConcurrentHashMap concurrentHashMap;
        concurrentHashMap = this.this$0.players;
        Collection<AudioPlayer> values = concurrentHashMap.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        for (AudioPlayer audioPlayer : values) {
            if (audioPlayer.getRef().isPlaying()) {
                audioPlayer.getRef().pause();
            }
        }
    }
}
