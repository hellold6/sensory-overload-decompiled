package expo.modules.core.logging;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: PersistentFileLogSerialDispatchQueue.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\u00072\u0010\u0010\n\u001a\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\bJ\u0006\u0010\u000b\u001a\u00020\u0007R\u001e\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003¨\u0006\u000f"}, d2 = {"Lexpo/modules/core/logging/PersistentFileLogSerialDispatchQueue;", "", "<init>", "()V", "channel", "Lkotlinx/coroutines/channels/Channel;", "Lkotlin/Function0;", "", "Lexpo/modules/core/logging/PersistentFileLogSerialDispatchQueueBlock;", "add", "block", "stop", "queueRunner", "Lkotlinx/coroutines/Job;", "getQueueRunner$annotations", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PersistentFileLogSerialDispatchQueue {
    private final Channel<Function0<Unit>> channel = ChannelKt.Channel$default(-2, null, null, 6, null);
    private final Job queueRunner;

    private static /* synthetic */ void getQueueRunner$annotations() {
    }

    public PersistentFileLogSerialDispatchQueue() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new PersistentFileLogSerialDispatchQueue$queueRunner$1(this, null), 3, null);
        this.queueRunner = launch$default;
    }

    public final void add(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        BuildersKt__BuildersKt.runBlocking$default(null, new PersistentFileLogSerialDispatchQueue$add$1(this, block, null), 1, null);
    }

    public final void stop() {
        Job.DefaultImpls.cancel$default(this.queueRunner, (CancellationException) null, 1, (Object) null);
    }
}
