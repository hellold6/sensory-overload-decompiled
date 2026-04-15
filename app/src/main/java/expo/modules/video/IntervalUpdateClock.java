package expo.modules.video;

import android.os.Handler;
import android.os.Looper;
import expo.modules.video.delegates.IgnoreSameSet;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: IntervalUpdateClock.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u0015H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lexpo/modules/video/IntervalUpdateClock;", "", "emitter", "Lexpo/modules/video/IntervalUpdateEmitter;", "<init>", "(Lexpo/modules/video/IntervalUpdateEmitter;)V", "Ljava/lang/ref/WeakReference;", "handler", "Landroid/os/Handler;", "<set-?>", "", "interval", "getInterval", "()J", "setInterval", "(J)V", "interval$delegate", "Lexpo/modules/video/delegates/IgnoreSameSet;", "isRunning", "", "stop", "", "startOrUpdate", "scheduleNextUpdate", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IntervalUpdateClock {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(IntervalUpdateClock.class, "interval", "getInterval()J", 0))};
    private final WeakReference<IntervalUpdateEmitter> emitter;
    private Handler handler;

    /* renamed from: interval$delegate, reason: from kotlin metadata */
    private final IgnoreSameSet interval;
    private boolean isRunning;

    public IntervalUpdateClock(IntervalUpdateEmitter emitter) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = new WeakReference<>(emitter);
        this.handler = new Handler(Looper.getMainLooper());
        this.interval = new IgnoreSameSet(0L, null, new Function2() { // from class: expo.modules.video.IntervalUpdateClock$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit interval_delegate$lambda$0;
                interval_delegate$lambda$0 = IntervalUpdateClock.interval_delegate$lambda$0(IntervalUpdateClock.this, ((Long) obj).longValue(), (Long) obj2);
                return interval_delegate$lambda$0;
            }
        }, 2, null);
    }

    public final long getInterval() {
        return ((Number) this.interval.getValue(this, $$delegatedProperties[0])).longValue();
    }

    public final void setInterval(long j) {
        this.interval.setValue(this, $$delegatedProperties[0], Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit interval_delegate$lambda$0(IntervalUpdateClock intervalUpdateClock, long j, Long l) {
        if (j <= 0) {
            intervalUpdateClock.stop();
        } else {
            intervalUpdateClock.startOrUpdate();
        }
        return Unit.INSTANCE;
    }

    private final void stop() {
        this.handler.removeCallbacksAndMessages(null);
        this.isRunning = false;
    }

    private final void startOrUpdate() {
        if (!this.isRunning) {
            IntervalUpdateEmitter intervalUpdateEmitter = this.emitter.get();
            if (intervalUpdateEmitter != null) {
                intervalUpdateEmitter.emitTimeUpdate();
            }
        } else {
            this.handler.removeCallbacksAndMessages(null);
        }
        this.isRunning = true;
        scheduleNextUpdate();
    }

    private final void scheduleNextUpdate() {
        if (getInterval() <= 0) {
            return;
        }
        final Function0 function0 = new Function0() { // from class: expo.modules.video.IntervalUpdateClock$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit scheduleNextUpdate$lambda$1;
                scheduleNextUpdate$lambda$1 = IntervalUpdateClock.scheduleNextUpdate$lambda$1(IntervalUpdateClock.this);
                return scheduleNextUpdate$lambda$1;
            }
        };
        this.handler.postDelayed(new Runnable() { // from class: expo.modules.video.IntervalUpdateClock$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        }, getInterval());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit scheduleNextUpdate$lambda$1(IntervalUpdateClock intervalUpdateClock) {
        IntervalUpdateEmitter intervalUpdateEmitter = intervalUpdateClock.emitter.get();
        if (intervalUpdateEmitter != null) {
            intervalUpdateEmitter.emitTimeUpdate();
        }
        intervalUpdateClock.scheduleNextUpdate();
        return Unit.INSTANCE;
    }
}
