package expo.modules.audio;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AudioModule.kt */
@kotlin.Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.audio.AudioModule$audioFocusChangeListener$1$1", f = "AudioModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AudioModule$audioFocusChangeListener$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $focusChange;
    int label;
    final /* synthetic */ AudioModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioModule$audioFocusChangeListener$1$1(int i, AudioModule audioModule, Continuation<? super AudioModule$audioFocusChangeListener$1$1> continuation) {
        super(2, continuation);
        this.$focusChange = i;
        this.this$0 = audioModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioModule$audioFocusChangeListener$1$1(this.$focusChange, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioModule$audioFocusChangeListener$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InterruptionMode interruptionMode;
        ConcurrentHashMap concurrentHashMap;
        ConcurrentHashMap concurrentHashMap2;
        ConcurrentHashMap concurrentHashMap3;
        ConcurrentHashMap concurrentHashMap4;
        ConcurrentHashMap concurrentHashMap5;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        int i = this.$focusChange;
        if (i == -3) {
            interruptionMode = this.this$0.interruptionMode;
            if (interruptionMode == InterruptionMode.DUCK_OTHERS) {
                concurrentHashMap2 = this.this$0.players;
                Collection<AudioPlayer> values = concurrentHashMap2.values();
                Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
                for (AudioPlayer audioPlayer : values) {
                    if (audioPlayer.getPreviousVolume() != audioPlayer.getRef().getVolume()) {
                        audioPlayer.setPreviousVolume(audioPlayer.getRef().getVolume());
                    }
                    audioPlayer.getRef().setVolume(audioPlayer.getPreviousVolume() * 0.5f);
                }
            } else {
                concurrentHashMap = this.this$0.players;
                Collection<AudioPlayer> values2 = concurrentHashMap.values();
                Intrinsics.checkNotNullExpressionValue(values2, "<get-values>(...)");
                for (AudioPlayer audioPlayer2 : values2) {
                    if (audioPlayer2.getRef().isPlaying()) {
                        audioPlayer2.setPaused(true);
                        audioPlayer2.getRef().pause();
                    }
                }
            }
        } else if (i == -2) {
            this.this$0.focusAcquired = false;
            concurrentHashMap3 = this.this$0.players;
            Collection<AudioPlayer> values3 = concurrentHashMap3.values();
            Intrinsics.checkNotNullExpressionValue(values3, "<get-values>(...)");
            for (AudioPlayer audioPlayer3 : values3) {
                if (audioPlayer3.getRef().isPlaying()) {
                    audioPlayer3.setPaused(true);
                    audioPlayer3.getRef().pause();
                }
            }
        } else if (i == -1) {
            this.this$0.focusAcquired = false;
            concurrentHashMap4 = this.this$0.players;
            Collection values4 = concurrentHashMap4.values();
            Intrinsics.checkNotNullExpressionValue(values4, "<get-values>(...)");
            Iterator it = values4.iterator();
            while (it.hasNext()) {
                ((AudioPlayer) it.next()).getRef().pause();
            }
        } else if (i == 1) {
            this.this$0.focusAcquired = true;
            concurrentHashMap5 = this.this$0.players;
            Collection<AudioPlayer> values5 = concurrentHashMap5.values();
            Intrinsics.checkNotNullExpressionValue(values5, "<get-values>(...)");
            for (AudioPlayer audioPlayer4 : values5) {
                audioPlayer4.setVolume(Boxing.boxFloat(audioPlayer4.getPreviousVolume()));
                if (audioPlayer4.getIsPaused()) {
                    audioPlayer4.setPaused(false);
                    audioPlayer4.getRef().play();
                }
            }
        }
        return Unit.INSTANCE;
    }
}
