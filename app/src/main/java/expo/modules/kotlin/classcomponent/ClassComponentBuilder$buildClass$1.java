package expo.modules.kotlin.classcomponent;

import expo.modules.kotlin.sharedobjects.SharedObject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ClassComponentBuilder.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public /* synthetic */ class ClassComponentBuilder$buildClass$1 extends FunctionReferenceImpl implements Function2<SharedObject, String, Unit> {
    public static final ClassComponentBuilder$buildClass$1 INSTANCE = new ClassComponentBuilder$buildClass$1();

    ClassComponentBuilder$buildClass$1() {
        super(2, SharedObject.class, "onStartListeningToEvent", "onStartListeningToEvent(Ljava/lang/String;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(SharedObject sharedObject, String str) {
        invoke2(sharedObject, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(SharedObject p0, String p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        p0.onStartListeningToEvent(p1);
    }
}
