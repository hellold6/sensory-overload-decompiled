package com.facebook.react;

import android.content.Context;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.soloader.OpenSourceMergedSoMapping;
import com.facebook.react.views.view.WindowUtilKt;
import com.facebook.soloader.SoLoader;
import java.io.IOException;

/* loaded from: classes2.dex */
public class ReactNativeApplicationEntryPoint {
    public static void loadReactNative(Context context) {
        try {
            SoLoader.init(context, OpenSourceMergedSoMapping.INSTANCE);
            DefaultNewArchitectureEntryPoint.load();
            WindowUtilKt.setEdgeToEdgeFeatureFlagOn();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
