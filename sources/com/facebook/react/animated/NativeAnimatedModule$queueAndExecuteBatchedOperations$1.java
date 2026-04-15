package com.facebook.react.animated;

import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapBuilder;
import com.facebook.react.bridge.WritableMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NativeAnimatedModule.kt */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$queueAndExecuteBatchedOperations$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NativeAnimatedModule$queueAndExecuteBatchedOperations$1 extends NativeAnimatedModule.UIThreadOperation {
    final /* synthetic */ int $opBufferSize;
    final /* synthetic */ ReadableArray $opsAndArgs;
    final /* synthetic */ NativeAnimatedModule this$0;

    /* compiled from: NativeAnimatedModule.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NativeAnimatedModule.BatchExecutionOpCodes.values().length];
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_CREATE_ANIMATED_NODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_UPDATE_ANIMATED_NODE_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_GET_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_START_LISTENING_TO_ANIMATED_NODE_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_STOP_LISTENING_TO_ANIMATED_NODE_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_CONNECT_ANIMATED_NODES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_DISCONNECT_ANIMATED_NODES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_START_ANIMATING_NODE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_STOP_ANIMATION.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_SET_ANIMATED_NODE_VALUE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_SET_ANIMATED_NODE_OFFSET.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_FLATTEN_ANIMATED_NODE_OFFSET.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_EXTRACT_ANIMATED_NODE_OFFSET.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_CONNECT_ANIMATED_NODE_TO_VIEW.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_DISCONNECT_ANIMATED_NODE_FROM_VIEW.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_RESTORE_DEFAULT_VALUES.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_DROP_ANIMATED_NODE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_ADD_ANIMATED_EVENT_TO_VIEW.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_REMOVE_ANIMATED_EVENT_FROM_VIEW.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_ADD_LISTENER.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[NativeAnimatedModule.BatchExecutionOpCodes.OP_CODE_REMOVE_LISTENERS.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeAnimatedModule$queueAndExecuteBatchedOperations$1(NativeAnimatedModule nativeAnimatedModule, int i, ReadableArray readableArray) {
        super();
        this.this$0 = nativeAnimatedModule;
        this.$opBufferSize = i;
        this.$opsAndArgs = readableArray;
    }

    @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
        this.this$0.getReactApplicationContextIfActiveOrWarn();
        int i = 0;
        while (i < this.$opBufferSize) {
            int i2 = i + 1;
            switch (WhenMappings.$EnumSwitchMapping$0[NativeAnimatedModule.BatchExecutionOpCodes.INSTANCE.fromId(this.$opsAndArgs.getInt(i)).ordinal()]) {
                case 1:
                    int i3 = i + 2;
                    int i4 = this.$opsAndArgs.getInt(i2);
                    i += 3;
                    ReadableMap map = this.$opsAndArgs.getMap(i3);
                    if (map != null) {
                        animatedNodesManager.createAnimatedNode(i4, map);
                        break;
                    } else {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                case 2:
                    int i5 = i + 2;
                    int i6 = this.$opsAndArgs.getInt(i2);
                    i += 3;
                    ReadableMap map2 = this.$opsAndArgs.getMap(i5);
                    if (map2 != null) {
                        animatedNodesManager.updateAnimatedNodeConfig(i6, map2);
                        break;
                    } else {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                case 3:
                    i += 2;
                    animatedNodesManager.getValue(this.$opsAndArgs.getInt(i2), null);
                    break;
                case 4:
                    i += 2;
                    final int i7 = this.$opsAndArgs.getInt(i2);
                    final NativeAnimatedModule nativeAnimatedModule = this.this$0;
                    animatedNodesManager.startListeningToAnimatedNodeValue(i7, new AnimatedNodeValueListener() { // from class: com.facebook.react.animated.NativeAnimatedModule$queueAndExecuteBatchedOperations$1$$ExternalSyntheticLambda0
                        @Override // com.facebook.react.animated.AnimatedNodeValueListener
                        public final void onValueUpdate(double d, double d2) {
                            NativeAnimatedModule$queueAndExecuteBatchedOperations$1.execute$lambda$1(NativeAnimatedModule.this, i7, d, d2);
                        }
                    });
                    break;
                case 5:
                    i += 2;
                    animatedNodesManager.stopListeningToAnimatedNodeValue(this.$opsAndArgs.getInt(i2));
                    break;
                case 6:
                    int i8 = i + 2;
                    i += 3;
                    animatedNodesManager.connectAnimatedNodes(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getInt(i8));
                    break;
                case 7:
                    int i9 = i + 2;
                    i += 3;
                    animatedNodesManager.disconnectAnimatedNodes(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getInt(i9));
                    break;
                case 8:
                    int i10 = this.$opsAndArgs.getInt(i2);
                    int i11 = i + 3;
                    int i12 = this.$opsAndArgs.getInt(i + 2);
                    i += 4;
                    ReadableMap map3 = this.$opsAndArgs.getMap(i11);
                    if (map3 != null) {
                        animatedNodesManager.startAnimatingNode(i10, i12, map3, null);
                        break;
                    } else {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                case 9:
                    i += 2;
                    animatedNodesManager.stopAnimation(this.$opsAndArgs.getInt(i2));
                    break;
                case 10:
                    int i13 = i + 2;
                    i += 3;
                    animatedNodesManager.setAnimatedNodeValue(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getDouble(i13));
                    break;
                case 11:
                    int i14 = i + 2;
                    i += 3;
                    animatedNodesManager.setAnimatedNodeOffset(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getDouble(i14));
                    break;
                case 12:
                    i += 2;
                    animatedNodesManager.flattenAnimatedNodeOffset(this.$opsAndArgs.getInt(i2));
                    break;
                case 13:
                    i += 2;
                    animatedNodesManager.extractAnimatedNodeOffset(this.$opsAndArgs.getInt(i2));
                    break;
                case 14:
                    int i15 = i + 2;
                    i += 3;
                    animatedNodesManager.connectAnimatedNodeToView(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getInt(i15));
                    break;
                case 15:
                    int i16 = i + 2;
                    int i17 = this.$opsAndArgs.getInt(i2);
                    i += 3;
                    int i18 = this.$opsAndArgs.getInt(i16);
                    this.this$0.decrementInFlightAnimationsForViewTag(i18);
                    animatedNodesManager.disconnectAnimatedNodeFromView(i17, i18);
                    break;
                case 16:
                    i += 2;
                    animatedNodesManager.restoreDefaultValues(this.$opsAndArgs.getInt(i2));
                    break;
                case 17:
                    i += 2;
                    animatedNodesManager.dropAnimatedNode(this.$opsAndArgs.getInt(i2));
                    break;
                case 18:
                    int i19 = this.$opsAndArgs.getInt(i2);
                    int i20 = i + 3;
                    String string = this.$opsAndArgs.getString(i + 2);
                    if (string == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    i += 4;
                    ReadableMap map4 = this.$opsAndArgs.getMap(i20);
                    if (map4 != null) {
                        animatedNodesManager.addAnimatedEventToView(i19, string, map4);
                        break;
                    } else {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                case 19:
                    int i21 = this.$opsAndArgs.getInt(i2);
                    this.this$0.decrementInFlightAnimationsForViewTag(i21);
                    int i22 = i + 3;
                    String string2 = this.$opsAndArgs.getString(i + 2);
                    if (string2 != null) {
                        i += 4;
                        animatedNodesManager.removeAnimatedEventFromView(i21, string2, this.$opsAndArgs.getInt(i22));
                        break;
                    } else {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                case 20:
                case 21:
                    i += 2;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$1(NativeAnimatedModule nativeAnimatedModule, int i, double d, double d2) {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn;
        WritableMap createMap = Arguments.createMap();
        Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
        ReadableMapBuilder readableMapBuilder = new ReadableMapBuilder(createMap);
        readableMapBuilder.put("tag", i);
        readableMapBuilder.put("value", d);
        readableMapBuilder.put("offset", d2);
        WritableMap writableMap = createMap;
        reactApplicationContextIfActiveOrWarn = nativeAnimatedModule.getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.emitDeviceEvent("onAnimatedValueUpdate", writableMap);
        }
    }
}
