package expo.modules.kotlin.activityaware;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: AppCompatActivityAware.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
/* loaded from: classes3.dex */
public final class AppCompatActivityAwareKt$withActivityAvailable$2$1 implements Function1<Throwable, Unit> {
    final /* synthetic */ AppCompatActivityAwareKt$withActivityAvailable$2$listener$1 $listener;
    final /* synthetic */ AppCompatActivityAware $this_withActivityAvailable;

    public AppCompatActivityAwareKt$withActivityAvailable$2$1(AppCompatActivityAware appCompatActivityAware, AppCompatActivityAwareKt$withActivityAvailable$2$listener$1 appCompatActivityAwareKt$withActivityAvailable$2$listener$1) {
        this.$this_withActivityAvailable = appCompatActivityAware;
        this.$listener = appCompatActivityAwareKt$withActivityAvailable$2$listener$1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable th) {
        this.$this_withActivityAvailable.removeOnActivityAvailableListener(this.$listener);
    }
}
