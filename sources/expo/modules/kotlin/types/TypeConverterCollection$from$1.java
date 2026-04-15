package expo.modules.kotlin.types;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Add missing generic type declarations: [Type] */
/* compiled from: TypeConverterCollection.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
/* loaded from: classes3.dex */
public final class TypeConverterCollection$from$1<Type> implements Function1<Object, Type> {
    final /* synthetic */ Function1<P0, Type> $body;

    /* JADX WARN: Multi-variable type inference failed */
    public TypeConverterCollection$from$1(Function1<? super P0, ? extends Type> function1) {
        this.$body = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Type invoke(Object obj) {
        return this.$body.invoke(obj);
    }
}
