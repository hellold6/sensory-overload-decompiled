package expo.modules.video.delegates;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: IgnoreSameSet.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001*\u0004\u0018\u00010\u00022\u00020\u0002Ba\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\u0005\u0012:\b\u0002\u0010\u0006\u001a4\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007¢\u0006\u0004\b\r\u0010\u000eJ$\u0010\u0014\u001a\u00028\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u00022\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0086\u0002¢\u0006\u0002\u0010\u0018J,\u0010\u0019\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00022\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0003\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u001aR\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011RC\u0010\u0006\u001a4\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Lexpo/modules/video/delegates/IgnoreSameSet;", ExifInterface.GPS_DIRECTION_TRUE, "", "value", "propertyMapper", "Lkotlin/Function1;", "didSet", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "new", "old", "", "<init>", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "Ljava/lang/Object;", "getPropertyMapper", "()Lkotlin/jvm/functions/Function1;", "getDidSet", "()Lkotlin/jvm/functions/Function2;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IgnoreSameSet<T> {
    private final Function2<T, T, Unit> didSet;
    private final Function1<T, T> propertyMapper;
    private T value;

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object _init_$lambda$0(Object obj) {
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IgnoreSameSet(T t, Function1<? super T, ? extends T> propertyMapper, Function2<? super T, ? super T, Unit> function2) {
        Intrinsics.checkNotNullParameter(propertyMapper, "propertyMapper");
        this.value = t;
        this.propertyMapper = propertyMapper;
        this.didSet = function2;
    }

    public /* synthetic */ IgnoreSameSet(Object obj, Function1 function1, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? new Function1() { // from class: expo.modules.video.delegates.IgnoreSameSet$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Object _init_$lambda$0;
                _init_$lambda$0 = IgnoreSameSet._init_$lambda$0(obj2);
                return _init_$lambda$0;
            }
        } : function1, (i & 4) != 0 ? null : function2);
    }

    public final Function2<T, T, Unit> getDidSet() {
        return this.didSet;
    }

    public final Function1<T, T> getPropertyMapper() {
        return this.propertyMapper;
    }

    public final T getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        return this.value;
    }

    public final void setValue(Object thisRef, KProperty<?> property, T value) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (Intrinsics.areEqual(this.value, this.propertyMapper.invoke(value))) {
            return;
        }
        T t = this.value;
        T invoke = this.propertyMapper.invoke(value);
        this.value = invoke;
        Function2<T, T, Unit> function2 = this.didSet;
        if (function2 != null) {
            function2.invoke(invoke, t);
        }
    }
}
