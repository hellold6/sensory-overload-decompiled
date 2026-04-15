package expo.modules.notifications;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import expo.modules.kotlin.types.JSTypeConverter;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u001aP\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102<\u0010\u0011\u001a8\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0001j\u0002`\u0012H\u0000\u001a\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0000\u001a7\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062%\u0010\u0014\u001a!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\nj\u0002`\u0015H\u0000\u001a\u0012\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0000\"3\u0010\u0016\u001a!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\nj\u0002`\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017*n\u0010\u0000\"4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u000124\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0001*@\u0010\t\"\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\n2\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\n¨\u0006\u001b"}, d2 = {"ResultReceiverBody", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "resultCode", "Landroid/os/Bundle;", "resultData", "", "BundleConversionTester", "Lkotlin/Function1;", "bundle", "", "createDefaultResultReceiver", "Landroid/os/ResultReceiver;", "handler", "Landroid/os/Handler;", "body", "Lexpo/modules/notifications/ResultReceiverBody;", "filteredBundleForJSTypeConverter", "testBundle", "Lexpo/modules/notifications/BundleConversionTester;", "isBundleConvertibleToJSValue", "()Lkotlin/jvm/functions/Function1;", "isValidJSONString", "test", "", "expo-notifications_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UtilsKt {
    private static final Function1<Bundle, Boolean> isBundleConvertibleToJSValue = new Function1() { // from class: expo.modules.notifications.UtilsKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            boolean isBundleConvertibleToJSValue$lambda$4;
            isBundleConvertibleToJSValue$lambda$4 = UtilsKt.isBundleConvertibleToJSValue$lambda$4((Bundle) obj);
            return Boolean.valueOf(isBundleConvertibleToJSValue$lambda$4);
        }
    };

    public static final ResultReceiver createDefaultResultReceiver(final Handler handler, final Function2<? super Integer, ? super Bundle, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return new ResultReceiver(handler) { // from class: expo.modules.notifications.UtilsKt$createDefaultResultReceiver$1
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                super.onReceiveResult(resultCode, resultData);
                body.invoke(Integer.valueOf(resultCode), resultData);
            }
        };
    }

    public static final Bundle filteredBundleForJSTypeConverter(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        return filteredBundleForJSTypeConverter(bundle, isBundleConvertibleToJSValue);
    }

    public static final Bundle filteredBundleForJSTypeConverter(Bundle bundle, Function1<? super Bundle, Boolean> testBundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(testBundle, "testBundle");
        if (testBundle.invoke(bundle).booleanValue()) {
            return bundle;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Set<String> keySet = bundle.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "keySet(...)");
        for (String str : keySet) {
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                bundle.putBundle(str, filteredBundleForJSTypeConverter((Bundle) obj, testBundle));
                linkedHashSet.add(str);
            }
        }
        Set<String> keySet2 = bundle.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet2, "keySet(...)");
        for (String str2 : keySet2) {
            if (!linkedHashSet.contains(str2)) {
                Bundle bundle2 = new Bundle();
                bundle2.putAll(bundle);
                Set<String> keySet3 = bundle.keySet();
                Intrinsics.checkNotNullExpressionValue(keySet3, "keySet(...)");
                for (String str3 : keySet3) {
                    if (!str3.equals(str2)) {
                        bundle2.remove(str3);
                    }
                }
                if (testBundle.invoke(bundle2).booleanValue()) {
                    linkedHashSet.add(str2);
                }
            }
        }
        Bundle bundle3 = new Bundle();
        bundle3.putAll(bundle);
        Set<String> keySet4 = bundle.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet4, "keySet(...)");
        for (String str4 : keySet4) {
            if (!linkedHashSet.contains(str4)) {
                bundle3.remove(str4);
            }
        }
        return bundle3;
    }

    public static final Function1<Bundle, Boolean> isBundleConvertibleToJSValue() {
        return isBundleConvertibleToJSValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isBundleConvertibleToJSValue$lambda$4(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        try {
            JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, bundle, null, false, 6, null);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static final boolean isValidJSONString(String str) {
        if (str != null) {
            try {
                try {
                    new JSONObject(str);
                    return true;
                } catch (JSONException unused) {
                    new JSONArray(str);
                    return true;
                }
            } catch (JSONException unused2) {
            }
        }
        return false;
    }
}
