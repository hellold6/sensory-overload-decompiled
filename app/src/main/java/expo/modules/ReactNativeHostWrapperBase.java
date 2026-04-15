package expo.modules;

import android.app.Application;
import androidx.collection.ArrayMap;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.MimeTypes;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.defaults.DefaultReactNativeHost;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactNativeHostHandler;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: ReactNativeHostWrapperBase.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0012H\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0012H\u0016J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\u001d\u0010 \u001a\u0002H!\"\u0004\b\u0000\u0010!2\u0006\u0010\"\u001a\u00020\u0012H\u0000¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0015H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001f\u0010\n\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lexpo/modules/ReactNativeHostWrapperBase;", "Lcom/facebook/react/defaults/DefaultReactNativeHost;", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "host", "Lcom/facebook/react/ReactNativeHost;", "<init>", "(Landroid/app/Application;Lcom/facebook/react/ReactNativeHost;)V", "getHost", "()Lcom/facebook/react/ReactNativeHost;", "reactNativeHostHandlers", "", "Lexpo/modules/core/interfaces/ReactNativeHostHandler;", "kotlin.jvm.PlatformType", "getReactNativeHostHandlers", "()Ljava/util/List;", "methodMap", "Landroidx/collection/ArrayMap;", "", "Ljava/lang/reflect/Method;", "createReactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "getJavaScriptExecutorFactory", "Lcom/facebook/react/bridge/JavaScriptExecutorFactory;", "getJSMainModuleName", "getJSBundleFile", "getBundleAssetName", "getUseDeveloperSupport", "", "getPackages", "", "Lcom/facebook/react/ReactPackage;", "invokeDelegateMethod", ExifInterface.GPS_DIRECTION_TRUE, "name", "invokeDelegateMethod$expo_release", "(Ljava/lang/String;)Ljava/lang/Object;", "injectHostReactInstanceManager", "", "reactInstanceManager", "expo_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ReactNativeHostWrapperBase extends DefaultReactNativeHost {
    private final ReactNativeHost host;
    private final ArrayMap<String, Method> methodMap;
    private final List<ReactNativeHostHandler> reactNativeHostHandlers;

    /* JADX INFO: Access modifiers changed from: protected */
    public final ReactNativeHost getHost() {
        return this.host;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactNativeHostWrapperBase(Application application, ReactNativeHost host) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(host, "host");
        this.host = host;
        List<Package> packageList = ExpoModulesPackage.INSTANCE.getPackageList();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = packageList.iterator();
        while (it.hasNext()) {
            List<? extends ReactNativeHostHandler> createReactNativeHostHandlers = ((Package) it.next()).createReactNativeHostHandlers(application);
            Intrinsics.checkNotNullExpressionValue(createReactNativeHostHandlers, "createReactNativeHostHandlers(...)");
            CollectionsKt.addAll(arrayList, createReactNativeHostHandlers);
        }
        this.reactNativeHostHandlers = arrayList;
        this.methodMap = new ArrayMap<>();
    }

    public final List<ReactNativeHostHandler> getReactNativeHostHandlers() {
        return this.reactNativeHostHandlers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactNativeHost
    public ReactInstanceManager createReactInstanceManager() {
        final boolean useDeveloperSupport = getUseDeveloperSupport();
        Iterator<T> it = this.reactNativeHostHandlers.iterator();
        while (it.hasNext()) {
            ((ReactNativeHostHandler) it.next()).onWillCreateReactInstance(useDeveloperSupport);
        }
        ReactInstanceManager createReactInstanceManager = super.createReactInstanceManager();
        Intrinsics.checkNotNullExpressionValue(createReactInstanceManager, "createReactInstanceManager(...)");
        Iterator<T> it2 = this.reactNativeHostHandlers.iterator();
        while (it2.hasNext()) {
            ((ReactNativeHostHandler) it2.next()).onDidCreateDevSupportManager(createReactInstanceManager.getDevSupportManager());
        }
        createReactInstanceManager.addReactInstanceEventListener(new ReactInstanceEventListener() { // from class: expo.modules.ReactNativeHostWrapperBase$createReactInstanceManager$3
            @Override // com.facebook.react.ReactInstanceEventListener
            public void onReactContextInitialized(ReactContext context) {
                Intrinsics.checkNotNullParameter(context, "context");
                List<ReactNativeHostHandler> reactNativeHostHandlers = ReactNativeHostWrapperBase.this.getReactNativeHostHandlers();
                boolean z = useDeveloperSupport;
                Iterator<T> it3 = reactNativeHostHandlers.iterator();
                while (it3.hasNext()) {
                    ((ReactNativeHostHandler) it3.next()).onDidCreateReactInstance(z, context);
                }
            }
        });
        injectHostReactInstanceManager(createReactInstanceManager);
        return createReactInstanceManager;
    }

    @Override // com.facebook.react.ReactNativeHost
    protected JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
        JavaScriptExecutorFactory javaScriptExecutorFactory = (JavaScriptExecutorFactory) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactNativeHostHandlers), new Function1() { // from class: expo.modules.ReactNativeHostWrapperBase$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                JavaScriptExecutorFactory javaScriptExecutorFactory2;
                javaScriptExecutorFactory2 = ((ReactNativeHostHandler) obj).getJavaScriptExecutorFactory();
                return javaScriptExecutorFactory2;
            }
        }));
        return javaScriptExecutorFactory == null ? (JavaScriptExecutorFactory) invokeDelegateMethod$expo_release("getJavaScriptExecutorFactory") : javaScriptExecutorFactory;
    }

    @Override // com.facebook.react.ReactNativeHost
    public String getJSMainModuleName() {
        return (String) invokeDelegateMethod$expo_release("getJSMainModuleName");
    }

    @Override // com.facebook.react.ReactNativeHost
    public String getJSBundleFile() {
        String str = (String) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactNativeHostHandlers), new Function1() { // from class: expo.modules.ReactNativeHostWrapperBase$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String jSBundleFile$lambda$4;
                jSBundleFile$lambda$4 = ReactNativeHostWrapperBase.getJSBundleFile$lambda$4(ReactNativeHostWrapperBase.this, (ReactNativeHostHandler) obj);
                return jSBundleFile$lambda$4;
            }
        }));
        return str == null ? (String) invokeDelegateMethod$expo_release("getJSBundleFile") : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getJSBundleFile$lambda$4(ReactNativeHostWrapperBase reactNativeHostWrapperBase, ReactNativeHostHandler reactNativeHostHandler) {
        return reactNativeHostHandler.getJSBundleFile(reactNativeHostWrapperBase.getUseDeveloperSupport());
    }

    @Override // com.facebook.react.ReactNativeHost
    public String getBundleAssetName() {
        String str = (String) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactNativeHostHandlers), new Function1() { // from class: expo.modules.ReactNativeHostWrapperBase$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String bundleAssetName$lambda$5;
                bundleAssetName$lambda$5 = ReactNativeHostWrapperBase.getBundleAssetName$lambda$5(ReactNativeHostWrapperBase.this, (ReactNativeHostHandler) obj);
                return bundleAssetName$lambda$5;
            }
        }));
        return str == null ? (String) invokeDelegateMethod$expo_release("getBundleAssetName") : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getBundleAssetName$lambda$5(ReactNativeHostWrapperBase reactNativeHostWrapperBase, ReactNativeHostHandler reactNativeHostHandler) {
        return reactNativeHostHandler.getBundleAssetName(reactNativeHostWrapperBase.getUseDeveloperSupport());
    }

    @Override // com.facebook.react.ReactNativeHost
    public boolean getUseDeveloperSupport() {
        Boolean bool = (Boolean) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactNativeHostHandlers), new Function1() { // from class: expo.modules.ReactNativeHostWrapperBase$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Boolean useDeveloperSupport;
                useDeveloperSupport = ((ReactNativeHostHandler) obj).getUseDeveloperSupport();
                return useDeveloperSupport;
            }
        }));
        if (bool != null) {
            return bool.booleanValue();
        }
        return this.host.getUseDeveloperSupport();
    }

    @Override // com.facebook.react.ReactNativeHost
    public List<ReactPackage> getPackages() {
        return (List) invokeDelegateMethod$expo_release("getPackages");
    }

    public final <T> T invokeDelegateMethod$expo_release(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Method method = this.methodMap.get(name);
        if (method == null) {
            method = ReactNativeHost.class.getDeclaredMethod(name, new Class[0]);
            method.setAccessible(true);
            this.methodMap.put(name, method);
        }
        Intrinsics.checkNotNull(method);
        return (T) method.invoke(this.host, new Object[0]);
    }

    private final void injectHostReactInstanceManager(ReactInstanceManager reactInstanceManager) {
        Field declaredField = ReactNativeHost.class.getDeclaredField("mReactInstanceManager");
        declaredField.setAccessible(true);
        declaredField.set(this.host, reactInstanceManager);
    }
}
