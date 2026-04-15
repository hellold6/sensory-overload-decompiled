package com.swmansion.reanimated;

import android.os.SystemClock;
import android.provider.Settings;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.soloader.SoLoader;
import com.swmansion.common.GestureHandlerStateManager;
import com.swmansion.reanimated.keyboard.KeyboardAnimationManager;
import com.swmansion.reanimated.keyboard.KeyboardWorkletWrapper;
import com.swmansion.reanimated.nativeProxy.AnimationFrameCallback;
import com.swmansion.reanimated.nativeProxy.EventHandler;
import com.swmansion.reanimated.nativeProxy.SensorSetter;
import com.swmansion.reanimated.sensor.ReanimatedSensorContainer;
import com.swmansion.reanimated.sensor.ReanimatedSensorType;
import com.swmansion.worklets.JSCallInvokerResolver;
import com.swmansion.worklets.WorkletsModule;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class NativeProxy {
    private static final int CMD_BACKGROUND_COLOR = 15;
    private static final int CMD_BORDER_BOTTOM_COLOR = 42;
    private static final int CMD_BORDER_BOTTOM_END_RADIUS = 28;
    private static final int CMD_BORDER_BOTTOM_LEFT_RADIUS = 25;
    private static final int CMD_BORDER_BOTTOM_RIGHT_RADIUS = 26;
    private static final int CMD_BORDER_BOTTOM_START_RADIUS = 27;
    private static final int CMD_BORDER_COLOR = 40;
    private static final int CMD_BORDER_END_COLOR = 46;
    private static final int CMD_BORDER_END_END_RADIUS = 32;
    private static final int CMD_BORDER_END_START_RADIUS = 31;
    private static final int CMD_BORDER_LEFT_COLOR = 43;
    private static final int CMD_BORDER_RADIUS = 20;
    private static final int CMD_BORDER_RIGHT_COLOR = 44;
    private static final int CMD_BORDER_START_COLOR = 45;
    private static final int CMD_BORDER_START_END_RADIUS = 30;
    private static final int CMD_BORDER_START_START_RADIUS = 29;
    private static final int CMD_BORDER_TOP_COLOR = 41;
    private static final int CMD_BORDER_TOP_END_RADIUS = 24;
    private static final int CMD_BORDER_TOP_LEFT_RADIUS = 21;
    private static final int CMD_BORDER_TOP_RIGHT_RADIUS = 22;
    private static final int CMD_BORDER_TOP_START_RADIUS = 23;
    private static final int CMD_COLOR = 16;
    private static final int CMD_ELEVATION = 11;
    private static final int CMD_END_OF_TRANSFORM = 3;
    private static final int CMD_END_OF_VIEW = 4;
    private static final int CMD_OPACITY = 10;
    private static final int CMD_SHADOW_OPACITY = 13;
    private static final int CMD_SHADOW_RADIUS = 14;
    private static final int CMD_START_OF_TRANSFORM = 2;
    private static final int CMD_START_OF_VIEW = 1;
    private static final int CMD_TINT_COLOR = 17;
    private static final int CMD_TRANSFORM_MATRIX = 111;
    private static final int CMD_TRANSFORM_PERSPECTIVE = 112;
    private static final int CMD_TRANSFORM_ROTATE = 105;
    private static final int CMD_TRANSFORM_ROTATE_X = 106;
    private static final int CMD_TRANSFORM_ROTATE_Y = 107;
    private static final int CMD_TRANSFORM_ROTATE_Z = 108;
    private static final int CMD_TRANSFORM_SCALE = 102;
    private static final int CMD_TRANSFORM_SCALE_X = 103;
    private static final int CMD_TRANSFORM_SCALE_Y = 104;
    private static final int CMD_TRANSFORM_SKEW_X = 109;
    private static final int CMD_TRANSFORM_SKEW_Y = 110;
    private static final int CMD_TRANSFORM_TRANSLATE_X = 100;
    private static final int CMD_TRANSFORM_TRANSLATE_Y = 101;
    private static final int CMD_UNIT_DEG = 200;
    private static final int CMD_UNIT_PERCENT = 203;
    private static final int CMD_UNIT_PX = 202;
    private static final int CMD_UNIT_RAD = 201;
    private static final int CMD_Z_INDEX = 12;
    private final GestureHandlerStateManager gestureHandlerStateManager;
    private final KeyboardAnimationManager keyboardAnimationManager;
    protected final WeakReference<ReactApplicationContext> mContext;
    protected final FabricUIManager mFabricUIManager;
    private final HybridData mHybridData;
    protected NodesManager mNodesManager;
    protected final WorkletsModule mWorkletsModule;
    private final ReanimatedSensorContainer reanimatedSensorContainer;
    private Long firstUptime = Long.valueOf(SystemClock.uptimeMillis());
    private boolean slowAnimationsEnabled = false;
    private final int ANIMATIONS_DRAG_FACTOR = 10;
    protected String cppVersion = null;
    private final AtomicBoolean mInvalidated = new AtomicBoolean(false);

    private native HybridData initHybrid(WorkletsModule workletsModule, long j, CallInvokerHolderImpl callInvokerHolderImpl, FabricUIManager fabricUIManager);

    private native void invalidateCpp();

    /* JADX INFO: Access modifiers changed from: protected */
    public native void installJSIBindings();

    public native boolean isAnyHandlerWaitingForEvent(String str, int i);

    public native void performOperations();

    static {
        SoLoader.loadLibrary("reanimated");
    }

    public NativeProxy(ReactApplicationContext reactApplicationContext, WorkletsModule workletsModule, NodesManager nodesManager) {
        GestureHandlerStateManager gestureHandlerStateManager = null;
        reactApplicationContext.assertOnJSQueueThread();
        this.mWorkletsModule = workletsModule;
        WeakReference<ReactApplicationContext> weakReference = new WeakReference<>(reactApplicationContext);
        this.mContext = weakReference;
        this.reanimatedSensorContainer = new ReanimatedSensorContainer(weakReference);
        this.keyboardAnimationManager = new KeyboardAnimationManager(weakReference);
        addDevMenuOption();
        try {
            gestureHandlerStateManager = (GestureHandlerStateManager) reactApplicationContext.getNativeModule(Class.forName("com.swmansion.gesturehandler.react.RNGestureHandlerModule"));
        } catch (ClassCastException | ClassNotFoundException unused) {
        }
        this.gestureHandlerStateManager = gestureHandlerStateManager;
        this.mNodesManager = nodesManager;
        FabricUIManager fabricUIManager = (FabricUIManager) UIManagerHelper.getUIManager(reactApplicationContext, 2);
        this.mFabricUIManager = fabricUIManager;
        this.mHybridData = initHybrid(workletsModule, ((JavaScriptContextHolder) Objects.requireNonNull(reactApplicationContext.getJavaScriptContextHolder())).getContext(), JSCallInvokerResolver.getJSCallInvokerHolder(reactApplicationContext), fabricUIManager);
    }

    protected HybridData getHybridData() {
        return this.mHybridData;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void invalidate() {
        HybridData hybridData;
        if (this.mInvalidated.getAndSet(true) || (hybridData = this.mHybridData) == null || !hybridData.isValid()) {
            return;
        }
        invalidateCpp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleSlowAnimations() {
        boolean z = this.slowAnimationsEnabled;
        this.slowAnimationsEnabled = !z;
        if (!z) {
            this.firstUptime = Long.valueOf(SystemClock.uptimeMillis());
        }
        this.mNodesManager.enableSlowAnimations(this.slowAnimationsEnabled, 10);
        this.mWorkletsModule.toggleSlowAnimations();
    }

    private void addDevMenuOption() {
        DevMenuUtils.addDevMenuOption(this.mContext.get(), new DevOptionHandler() { // from class: com.swmansion.reanimated.NativeProxy$$ExternalSyntheticLambda0
            @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
            public final void onOptionSelected() {
                NativeProxy.this.toggleSlowAnimations();
            }
        });
    }

    public void requestRender(AnimationFrameCallback animationFrameCallback) {
        UiThreadUtil.assertOnUiThread();
        this.mNodesManager.postOnAnimation(animationFrameCallback);
    }

    public String getReanimatedJavaVersion() {
        return BuildConfig.REANIMATED_VERSION_JAVA;
    }

    protected void setCppVersion(String str) {
        this.cppVersion = str;
    }

    protected void checkCppVersion() {
        if (this.cppVersion == null) {
            throw new RuntimeException("[Reanimated] Java side failed to resolve C++ code version. See https://docs.swmansion.com/react-native-reanimated/docs/guides/troubleshooting#java-side-failed-to-resolve-c-code-version for more information.");
        }
        String reanimatedJavaVersion = getReanimatedJavaVersion();
        if (!this.cppVersion.equals(reanimatedJavaVersion)) {
            throw new RuntimeException("[Reanimated] Mismatch between Java code version and C++ code version (" + reanimatedJavaVersion + " vs. " + this.cppVersion + " respectively). See https://docs.swmansion.com/react-native-reanimated/docs/guides/troubleshooting#mismatch-between-java-code-version-and-c-code-version for more information.");
        }
    }

    private static String commandToString(int i) {
        switch (i) {
            case 10:
                return ViewProps.OPACITY;
            case 11:
                return ViewProps.ELEVATION;
            case 12:
                return ViewProps.Z_INDEX;
            case 13:
                return "shadowOpacity";
            case 14:
                return "shadowRadius";
            case 15:
                return "backgroundColor";
            case 16:
                return "color";
            case 17:
                return "tintColor";
            default:
                switch (i) {
                    case 20:
                        return ViewProps.BORDER_RADIUS;
                    case 21:
                        return ViewProps.BORDER_TOP_LEFT_RADIUS;
                    case 22:
                        return ViewProps.BORDER_TOP_RIGHT_RADIUS;
                    case 23:
                        return ViewProps.BORDER_TOP_START_RADIUS;
                    case 24:
                        return ViewProps.BORDER_TOP_END_RADIUS;
                    case 25:
                        return ViewProps.BORDER_BOTTOM_LEFT_RADIUS;
                    case 26:
                        return ViewProps.BORDER_BOTTOM_RIGHT_RADIUS;
                    case 27:
                        return ViewProps.BORDER_BOTTOM_START_RADIUS;
                    case 28:
                        return ViewProps.BORDER_BOTTOM_END_RADIUS;
                    case 29:
                        return ViewProps.BORDER_START_START_RADIUS;
                    case 30:
                        return ViewProps.BORDER_START_END_RADIUS;
                    case 31:
                        return ViewProps.BORDER_END_START_RADIUS;
                    case 32:
                        return ViewProps.BORDER_END_END_RADIUS;
                    default:
                        switch (i) {
                            case 40:
                                return ViewProps.BORDER_COLOR;
                            case 41:
                                return ViewProps.BORDER_TOP_COLOR;
                            case 42:
                                return ViewProps.BORDER_BOTTOM_COLOR;
                            case 43:
                                return ViewProps.BORDER_LEFT_COLOR;
                            case 44:
                                return ViewProps.BORDER_RIGHT_COLOR;
                            case 45:
                                return ViewProps.BORDER_START_COLOR;
                            case 46:
                                return ViewProps.BORDER_END_COLOR;
                            default:
                                throw new RuntimeException("Unknown command: " + i);
                        }
                }
        }
    }

    private static String transformCommandToString(int i) {
        switch (i) {
            case 100:
                return ViewProps.TRANSLATE_X;
            case 101:
                return ViewProps.TRANSLATE_Y;
            case 102:
                return "scale";
            case CMD_TRANSFORM_SCALE_X /* 103 */:
                return ViewProps.SCALE_X;
            case 104:
                return ViewProps.SCALE_Y;
            case CMD_TRANSFORM_ROTATE /* 105 */:
                return "rotate";
            case CMD_TRANSFORM_ROTATE_X /* 106 */:
                return "rotateX";
            case CMD_TRANSFORM_ROTATE_Y /* 107 */:
                return "rotateY";
            case 108:
                return "rotateZ";
            case 109:
                return "skewX";
            case CMD_TRANSFORM_SKEW_Y /* 110 */:
                return "skewY";
            case CMD_TRANSFORM_MATRIX /* 111 */:
                return "matrix";
            case CMD_TRANSFORM_PERSPECTIVE /* 112 */:
                return "perspective";
            default:
                throw new RuntimeException("Unknown transform command: " + i);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:69:0x0031. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:70:0x0034. Please report as an issue. */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfInt] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.PrimitiveIterator$OfDouble] */
    public void synchronouslyUpdateUIProps(int[] iArr, double[] dArr) {
        String str;
        ?? it = Arrays.stream(iArr).iterator();
        ?? it2 = Arrays.stream(dArr).iterator();
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        int i = -1;
        while (it.hasNext()) {
            int nextInt = it.nextInt();
            if (nextInt == 1) {
                i = it.nextInt();
                javaOnlyMap = new JavaOnlyMap();
            } else if (nextInt == 2) {
                JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
                while (true) {
                    int nextInt2 = it.nextInt();
                    if (nextInt2 == 3) {
                        javaOnlyMap.putArray(ViewProps.TRANSFORM, javaOnlyArray);
                    } else {
                        String transformCommandToString = transformCommandToString(nextInt2);
                        switch (nextInt2) {
                            case 100:
                            case 101:
                                double nextDouble = it2.nextDouble();
                                int nextInt3 = it.nextInt();
                                if (nextInt3 == CMD_UNIT_PX) {
                                    javaOnlyArray.pushMap(JavaOnlyMap.of(transformCommandToString, Double.valueOf(nextDouble)));
                                    break;
                                } else if (nextInt3 == CMD_UNIT_PERCENT) {
                                    javaOnlyArray.pushMap(JavaOnlyMap.of(transformCommandToString, nextDouble + "%"));
                                    break;
                                } else {
                                    throw new RuntimeException("Unknown unit command");
                                }
                            case 102:
                            case CMD_TRANSFORM_SCALE_X /* 103 */:
                            case 104:
                            case CMD_TRANSFORM_PERSPECTIVE /* 112 */:
                                javaOnlyArray.pushMap(JavaOnlyMap.of(transformCommandToString, Double.valueOf(it2.nextDouble())));
                                break;
                            case CMD_TRANSFORM_ROTATE /* 105 */:
                            case CMD_TRANSFORM_ROTATE_X /* 106 */:
                            case CMD_TRANSFORM_ROTATE_Y /* 107 */:
                            case 108:
                            case 109:
                            case CMD_TRANSFORM_SKEW_Y /* 110 */:
                                double nextDouble2 = it2.nextDouble();
                                int nextInt4 = it.nextInt();
                                if (nextInt4 == 200) {
                                    str = "deg";
                                } else if (nextInt4 == CMD_UNIT_RAD) {
                                    str = "rad";
                                } else {
                                    throw new RuntimeException("Unknown unit command");
                                }
                                javaOnlyArray.pushMap(JavaOnlyMap.of(transformCommandToString, nextDouble2 + str));
                                break;
                            case CMD_TRANSFORM_MATRIX /* 111 */:
                                int nextInt5 = it.nextInt();
                                JavaOnlyArray javaOnlyArray2 = new JavaOnlyArray();
                                for (int i2 = 0; i2 < nextInt5; i2++) {
                                    javaOnlyArray2.pushDouble(it2.nextDouble());
                                }
                                javaOnlyArray.pushMap(JavaOnlyMap.of(transformCommandToString, javaOnlyArray2));
                                break;
                            default:
                                throw new RuntimeException("Unknown transform type: " + nextInt2);
                        }
                    }
                }
            } else if (nextInt != 4) {
                switch (nextInt) {
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                        javaOnlyMap.putDouble(commandToString(nextInt), it2.nextDouble());
                        break;
                    default:
                        switch (nextInt) {
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                                String commandToString = commandToString(nextInt);
                                double nextDouble3 = it2.nextDouble();
                                int nextInt6 = it.nextInt();
                                if (nextInt6 == CMD_UNIT_PX) {
                                    javaOnlyMap.putDouble(commandToString, nextDouble3);
                                } else if (nextInt6 == CMD_UNIT_PERCENT) {
                                    javaOnlyMap.putString(commandToString, nextDouble3 + "%");
                                } else {
                                    throw new RuntimeException("Unknown unit command");
                                }
                                break;
                            default:
                                switch (nextInt) {
                                    case 40:
                                    case 41:
                                    case 42:
                                    case 43:
                                    case 44:
                                    case 45:
                                    case 46:
                                        break;
                                    default:
                                        throw new RuntimeException("Unexcepted command: " + nextInt);
                                }
                                break;
                        }
                    case 15:
                    case 16:
                    case 17:
                        javaOnlyMap.putInt(commandToString(nextInt), it.nextInt());
                        break;
                }
            } else {
                this.mFabricUIManager.synchronouslyUpdateViewOnUIThread(i, javaOnlyMap);
            }
        }
    }

    public void setGestureState(int i, int i2) {
        GestureHandlerStateManager gestureHandlerStateManager = this.gestureHandlerStateManager;
        if (gestureHandlerStateManager != null) {
            gestureHandlerStateManager.setGestureHandlerState(i, i2);
        }
    }

    public long getAnimationTimestamp() {
        if (this.slowAnimationsEnabled) {
            return this.firstUptime.longValue() + ((SystemClock.uptimeMillis() - this.firstUptime.longValue()) / 10);
        }
        return SystemClock.uptimeMillis();
    }

    public void registerEventHandler(EventHandler eventHandler) {
        eventHandler.mCustomEventNamesResolver = this.mNodesManager.getEventNameResolver();
        this.mNodesManager.registerEventHandler(eventHandler);
    }

    public int registerSensor(int i, int i2, SensorSetter sensorSetter) {
        return this.reanimatedSensorContainer.registerSensor(ReanimatedSensorType.getInstanceById(i), i2, sensorSetter);
    }

    public void unregisterSensor(int i) {
        this.reanimatedSensorContainer.unregisterSensor(i);
    }

    public int subscribeForKeyboardEvents(KeyboardWorkletWrapper keyboardWorkletWrapper, boolean z, boolean z2) {
        return this.keyboardAnimationManager.subscribeForKeyboardUpdates(keyboardWorkletWrapper, z, z2);
    }

    public void unsubscribeFromKeyboardEvents(int i) {
        this.keyboardAnimationManager.unsubscribeFromKeyboardUpdates(i);
    }

    public boolean getIsReducedMotion() {
        String string = Settings.Global.getString(this.mContext.get().getContentResolver(), "transition_animation_scale");
        return (string != null ? Float.parseFloat(string) : 1.0f) == 0.0f;
    }

    void maybeFlushUIUpdatesQueue() {
        UiThreadUtil.assertOnUiThread();
        if (this.mNodesManager.isAnimationRunning()) {
            return;
        }
        this.mNodesManager.performOperations();
    }
}
