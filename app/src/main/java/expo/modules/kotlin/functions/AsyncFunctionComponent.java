package expo.modules.kotlin.functions;

import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.UtilsKt;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JNIAsyncFunctionBody;
import expo.modules.kotlin.jni.PromiseImpl;
import expo.modules.kotlin.jni.decorators.JSDecoratorsBridgingObject;
import expo.modules.kotlin.types.AnyType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: AsyncFunctionComponent.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H ¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J\u001e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0019H\u0002¨\u0006\u001a"}, d2 = {"Lexpo/modules/kotlin/functions/AsyncFunctionComponent;", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "<init>", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;)V", "callUserImplementation", "", "args", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "appContext", "Lexpo/modules/kotlin/AppContext;", "callUserImplementation$expo_modules_core_release", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;Lexpo/modules/kotlin/AppContext;)V", "attachToJSObject", "jsObject", "Lexpo/modules/kotlin/jni/decorators/JSDecoratorsBridgingObject;", "moduleName", "dispatchOnQueue", "block", "Lkotlin/Function0;", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class AsyncFunctionComponent extends BaseAsyncFunctionComponent {
    public abstract void callUserImplementation$expo_modules_core_release(Object[] args, Promise promise, AppContext appContext);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncFunctionComponent(String name, AnyType[] desiredArgsTypes) {
        super(name, desiredArgsTypes);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
    }

    @Override // expo.modules.kotlin.functions.AnyFunction
    public void attachToJSObject(final AppContext appContext, JSDecoratorsBridgingObject jsObject, final String moduleName) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(jsObject, "jsObject");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        final WeakReference weak = UtilsKt.weak(appContext);
        String name = getName();
        boolean takesOwner$expo_modules_core_release = getTakesOwner$expo_modules_core_release();
        boolean isEnumerable$expo_modules_core_release = getIsEnumerable();
        AnyType[] desiredArgsTypes = getDesiredArgsTypes();
        ArrayList arrayList = new ArrayList(desiredArgsTypes.length);
        for (AnyType anyType : desiredArgsTypes) {
            arrayList.add(anyType.getCppRequiredTypes());
        }
        jsObject.registerAsyncFunction(name, takesOwner$expo_modules_core_release, isEnumerable$expo_modules_core_release, (ExpectedType[]) arrayList.toArray(new ExpectedType[0]), new JNIAsyncFunctionBody() { // from class: expo.modules.kotlin.functions.AsyncFunctionComponent$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.jni.JNIAsyncFunctionBody
            public final void invoke(Object[] objArr, PromiseImpl promiseImpl) {
                AsyncFunctionComponent.attachToJSObject$lambda$4(weak, moduleName, this, appContext, objArr, promiseImpl);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void attachToJSObject$lambda$4(WeakReference weakReference, final String str, final AsyncFunctionComponent asyncFunctionComponent, final AppContext appContext, final Object[] args, final PromiseImpl promiseImpl) {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promiseImpl, "promiseImpl");
        asyncFunctionComponent.dispatchOnQueue(appContext, new Function0() { // from class: expo.modules.kotlin.functions.AsyncFunctionComponent$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit attachToJSObject$lambda$4$lambda$3;
                attachToJSObject$lambda$4$lambda$3 = AsyncFunctionComponent.attachToJSObject$lambda$4$lambda$3(PromiseImpl.this, asyncFunctionComponent, str, args, appContext);
                return attachToJSObject$lambda$4$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit attachToJSObject$lambda$4$lambda$3(PromiseImpl promiseImpl, AsyncFunctionComponent asyncFunctionComponent, String str, Object[] objArr, AppContext appContext) {
        UnexpectedException unexpectedException;
        UnexpectedException unexpectedException2;
        try {
            asyncFunctionComponent.callUserImplementation$expo_modules_core_release(objArr, promiseImpl, appContext);
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        } catch (Throwable th) {
            try {
                if (th instanceof CodedException) {
                    unexpectedException2 = (CodedException) th;
                } else if (th instanceof expo.modules.core.errors.CodedException) {
                    String code = ((expo.modules.core.errors.CodedException) th).getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                    unexpectedException2 = new CodedException(code, ((expo.modules.core.errors.CodedException) th).getMessage(), ((expo.modules.core.errors.CodedException) th).getCause());
                } else {
                    unexpectedException2 = new UnexpectedException(th);
                }
                throw new FunctionCallException(asyncFunctionComponent.getName(), str, unexpectedException2);
            } catch (Throwable th2) {
                if (promiseImpl.getWasSettled()) {
                    throw th2;
                }
                if (th2 instanceof CodedException) {
                    unexpectedException = (CodedException) th2;
                } else if (th2 instanceof expo.modules.core.errors.CodedException) {
                    expo.modules.core.errors.CodedException codedException = (expo.modules.core.errors.CodedException) th2;
                    String code2 = codedException.getCode();
                    Intrinsics.checkNotNullExpressionValue(code2, "getCode(...)");
                    unexpectedException = new CodedException(code2, codedException.getMessage(), codedException.getCause());
                } else {
                    unexpectedException = new UnexpectedException(th2);
                }
                promiseImpl.reject(unexpectedException);
            }
        }
    }

    private final void dispatchOnQueue(AppContext appContext, Function0<Unit> block) {
        FunctionQueue queue = getQueue();
        if (queue == Queues.DEFAULT) {
            BuildersKt__Builders_commonKt.launch$default(appContext.getModulesQueue(), null, null, new AsyncFunctionComponent$dispatchOnQueue$1(block, null), 3, null);
        } else if (queue == Queues.MAIN) {
            BuildersKt__Builders_commonKt.launch$default(appContext.getMainQueue(), null, null, new AsyncFunctionComponent$dispatchOnQueue$3(block, null), 3, null);
        } else {
            if (queue instanceof CustomQueue) {
                BuildersKt__Builders_commonKt.launch$default(((CustomQueue) queue).getScope(), null, null, new AsyncFunctionComponent$dispatchOnQueue$4(block, null), 3, null);
                return;
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
