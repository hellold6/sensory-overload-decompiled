package com.facebook.react.internal.featureflags;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactNativeFeatureFlagsLocalAccessor.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b/\n\u0002\u0010\u0006\n\u0002\bM\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010J\u001a\u00020\nH\u0016J\b\u0010K\u001a\u00020\nH\u0016J\b\u0010L\u001a\u00020\nH\u0016J\b\u0010M\u001a\u00020\nH\u0016J\b\u0010N\u001a\u00020\nH\u0016J\b\u0010O\u001a\u00020\nH\u0016J\b\u0010P\u001a\u00020\nH\u0016J\b\u0010Q\u001a\u00020\nH\u0016J\b\u0010R\u001a\u00020\nH\u0016J\b\u0010S\u001a\u00020\nH\u0016J\b\u0010T\u001a\u00020\nH\u0016J\b\u0010U\u001a\u00020\nH\u0016J\b\u0010V\u001a\u00020\nH\u0016J\b\u0010W\u001a\u00020\nH\u0016J\b\u0010X\u001a\u00020\nH\u0016J\b\u0010Y\u001a\u00020\nH\u0016J\b\u0010Z\u001a\u00020\nH\u0016J\b\u0010[\u001a\u00020\nH\u0016J\b\u0010\\\u001a\u00020\nH\u0016J\b\u0010]\u001a\u00020\nH\u0016J\b\u0010^\u001a\u00020\nH\u0016J\b\u0010_\u001a\u00020\nH\u0016J\b\u0010`\u001a\u00020\nH\u0016J\b\u0010a\u001a\u00020\nH\u0016J\b\u0010b\u001a\u00020\nH\u0016J\b\u0010c\u001a\u00020\nH\u0016J\b\u0010d\u001a\u00020\nH\u0016J\b\u0010e\u001a\u00020\nH\u0016J\b\u0010f\u001a\u00020\nH\u0016J\b\u0010g\u001a\u00020\nH\u0016J\b\u0010h\u001a\u00020\nH\u0016J\b\u0010i\u001a\u00020\nH\u0016J\b\u0010j\u001a\u00020\nH\u0016J\b\u0010k\u001a\u00020\nH\u0016J\b\u0010l\u001a\u00020\nH\u0016J\b\u0010m\u001a\u00020\nH\u0016J\b\u0010n\u001a\u00020\nH\u0016J\b\u0010o\u001a\u00020\nH\u0016J\b\u0010p\u001a\u00020\nH\u0016J\b\u0010q\u001a\u00020\nH\u0016J\b\u0010r\u001a\u00020\nH\u0016J\b\u0010s\u001a\u00020\nH\u0016J\b\u0010t\u001a\u00020\nH\u0016J\b\u0010u\u001a\u00020\nH\u0016J\b\u0010v\u001a\u00020\nH\u0016J\b\u0010w\u001a\u00020\nH\u0016J\b\u0010x\u001a\u00020:H\u0016J\b\u0010y\u001a\u00020\nH\u0016J\b\u0010z\u001a\u00020\nH\u0016J\b\u0010{\u001a\u00020\nH\u0016J\b\u0010|\u001a\u00020\nH\u0016J\b\u0010}\u001a\u00020\nH\u0016J\b\u0010~\u001a\u00020\nH\u0016J\b\u0010\u007f\u001a\u00020\nH\u0016J\t\u0010\u0080\u0001\u001a\u00020\nH\u0016J\t\u0010\u0081\u0001\u001a\u00020\nH\u0016J\t\u0010\u0082\u0001\u001a\u00020\nH\u0016J\t\u0010\u0083\u0001\u001a\u00020\nH\u0016J\t\u0010\u0084\u0001\u001a\u00020\nH\u0016J\t\u0010\u0085\u0001\u001a\u00020\nH\u0016J\t\u0010\u0086\u0001\u001a\u00020:H\u0016J\u0013\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u0005H\u0016J\n\u0010\u008a\u0001\u001a\u00030\u0088\u0001H\u0016J\u0014\u0010\u008b\u0001\u001a\u0004\u0018\u00010\b2\u0007\u0010\u0089\u0001\u001a\u00020\u0005H\u0016J\u0011\u0010\u008c\u0001\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0003\b\u008d\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\r\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u000e\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0010\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0011\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0013\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0014\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0015\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0016\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0017\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0018\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0019\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u001a\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u001b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u001c\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u001d\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u001e\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u001f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010 \u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010!\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\"\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010#\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010$\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010%\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010&\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010'\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010(\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010)\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010*\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010+\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010,\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010-\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010.\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010/\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00100\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00101\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00102\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00103\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00104\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00105\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00106\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00107\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00108\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010<\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010=\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010>\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010?\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010@\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010A\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010B\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010C\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010D\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010E\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010F\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010G\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010H\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010I\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;¨\u0006\u008e\u0001"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsLocalAccessor;", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsAccessor;", "<init>", "()V", "currentProvider", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "accessedFeatureFlags", "", "", "commonTestFlagCache", "", "Ljava/lang/Boolean;", "animatedShouldSignalBatchCache", "cxxNativeAnimatedEnabledCache", "cxxNativeAnimatedRemoveJsSyncCache", "disableMainQueueSyncDispatchIOSCache", "disableMountItemReorderingAndroidCache", "disableTextLayoutManagerCacheAndroidCache", "enableAccessibilityOrderCache", "enableAccumulatedUpdatesInRawPropsAndroidCache", "enableAndroidTextMeasurementOptimizationsCache", "enableBridgelessArchitectureCache", "enableCppPropsIteratorSetterCache", "enableCustomFocusSearchOnClippedElementsAndroidCache", "enableDestroyShadowTreeRevisionAsyncCache", "enableDoubleMeasurementFixAndroidCache", "enableEagerRootViewAttachmentCache", "enableFabricLogsCache", "enableFabricRendererCache", "enableFixForParentTagDuringReparentingCache", "enableFontScaleChangesUpdatingLayoutCache", "enableIOSTextBaselineOffsetPerLineCache", "enableIOSViewClipToPaddingBoxCache", "enableInteropViewManagerClassLookUpOptimizationIOSCache", "enableLayoutAnimationsOnAndroidCache", "enableLayoutAnimationsOnIOSCache", "enableMainQueueCoordinatorOnIOSCache", "enableMainQueueModulesOnIOSCache", "enableModuleArgumentNSNullConversionIOSCache", "enableNativeCSSParsingCache", "enableNetworkEventReportingCache", "enableNewBackgroundAndBorderDrawablesCache", "enablePreparedTextLayoutCache", "enablePropsUpdateReconciliationAndroidCache", "enableResourceTimingAPICache", "enableSynchronousStateUpdatesCache", "enableViewCullingCache", "enableViewRecyclingCache", "enableViewRecyclingForTextCache", "enableViewRecyclingForViewCache", "enableVirtualViewDebugFeaturesCache", "enableVirtualViewRenderStateCache", "enableVirtualViewWindowFocusDetectionCache", "fixMappingOfEventPrioritiesBetweenFabricAndReactCache", "fuseboxEnabledReleaseCache", "fuseboxNetworkInspectionEnabledCache", "hideOffscreenVirtualViewsOnIOSCache", "preparedTextCacheSizeCache", "", "Ljava/lang/Double;", "preventShadowTreeCommitExhaustionCache", "traceTurboModulePromiseRejectionsOnAndroidCache", "updateRuntimeShadowNodeReferencesOnCommitCache", "useAlwaysAvailableJSErrorHandlingCache", "useFabricInteropCache", "useNativeEqualsInNativeReadableArrayAndroidCache", "useNativeTransformHelperAndroidCache", "useNativeViewConfigsInBridgelessModeCache", "useOptimizedEventBatchingOnAndroidCache", "useRawPropsJsiValueCache", "useShadowNodeStateOnCloneCache", "useTurboModuleInteropCache", "useTurboModulesCache", "virtualViewPrerenderRatioCache", "commonTestFlag", "animatedShouldSignalBatch", "cxxNativeAnimatedEnabled", "cxxNativeAnimatedRemoveJsSync", "disableMainQueueSyncDispatchIOS", "disableMountItemReorderingAndroid", "disableTextLayoutManagerCacheAndroid", "enableAccessibilityOrder", "enableAccumulatedUpdatesInRawPropsAndroid", "enableAndroidTextMeasurementOptimizations", "enableBridgelessArchitecture", "enableCppPropsIteratorSetter", "enableCustomFocusSearchOnClippedElementsAndroid", "enableDestroyShadowTreeRevisionAsync", "enableDoubleMeasurementFixAndroid", "enableEagerRootViewAttachment", "enableFabricLogs", "enableFabricRenderer", "enableFixForParentTagDuringReparenting", "enableFontScaleChangesUpdatingLayout", "enableIOSTextBaselineOffsetPerLine", "enableIOSViewClipToPaddingBox", "enableInteropViewManagerClassLookUpOptimizationIOS", "enableLayoutAnimationsOnAndroid", "enableLayoutAnimationsOnIOS", "enableMainQueueCoordinatorOnIOS", "enableMainQueueModulesOnIOS", "enableModuleArgumentNSNullConversionIOS", "enableNativeCSSParsing", "enableNetworkEventReporting", "enableNewBackgroundAndBorderDrawables", "enablePreparedTextLayout", "enablePropsUpdateReconciliationAndroid", "enableResourceTimingAPI", "enableSynchronousStateUpdates", "enableViewCulling", "enableViewRecycling", "enableViewRecyclingForText", "enableViewRecyclingForView", "enableVirtualViewDebugFeatures", "enableVirtualViewRenderState", "enableVirtualViewWindowFocusDetection", "fixMappingOfEventPrioritiesBetweenFabricAndReact", "fuseboxEnabledRelease", "fuseboxNetworkInspectionEnabled", "hideOffscreenVirtualViewsOnIOS", "preparedTextCacheSize", "preventShadowTreeCommitExhaustion", "traceTurboModulePromiseRejectionsOnAndroid", "updateRuntimeShadowNodeReferencesOnCommit", "useAlwaysAvailableJSErrorHandling", "useFabricInterop", "useNativeEqualsInNativeReadableArrayAndroid", "useNativeTransformHelperAndroid", "useNativeViewConfigsInBridgelessMode", "useOptimizedEventBatchingOnAndroid", "useRawPropsJsiValue", "useShadowNodeStateOnClone", "useTurboModuleInterop", "useTurboModules", "virtualViewPrerenderRatio", "override", "", "provider", "dangerouslyReset", "dangerouslyForceOverride", "getAccessedFeatureFlags", "getAccessedFeatureFlags$ReactAndroid_release", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactNativeFeatureFlagsLocalAccessor implements ReactNativeFeatureFlagsAccessor {
    private Boolean animatedShouldSignalBatchCache;
    private Boolean commonTestFlagCache;
    private Boolean cxxNativeAnimatedEnabledCache;
    private Boolean cxxNativeAnimatedRemoveJsSyncCache;
    private Boolean disableMainQueueSyncDispatchIOSCache;
    private Boolean disableMountItemReorderingAndroidCache;
    private Boolean disableTextLayoutManagerCacheAndroidCache;
    private Boolean enableAccessibilityOrderCache;
    private Boolean enableAccumulatedUpdatesInRawPropsAndroidCache;
    private Boolean enableAndroidTextMeasurementOptimizationsCache;
    private Boolean enableBridgelessArchitectureCache;
    private Boolean enableCppPropsIteratorSetterCache;
    private Boolean enableCustomFocusSearchOnClippedElementsAndroidCache;
    private Boolean enableDestroyShadowTreeRevisionAsyncCache;
    private Boolean enableDoubleMeasurementFixAndroidCache;
    private Boolean enableEagerRootViewAttachmentCache;
    private Boolean enableFabricLogsCache;
    private Boolean enableFabricRendererCache;
    private Boolean enableFixForParentTagDuringReparentingCache;
    private Boolean enableFontScaleChangesUpdatingLayoutCache;
    private Boolean enableIOSTextBaselineOffsetPerLineCache;
    private Boolean enableIOSViewClipToPaddingBoxCache;
    private Boolean enableInteropViewManagerClassLookUpOptimizationIOSCache;
    private Boolean enableLayoutAnimationsOnAndroidCache;
    private Boolean enableLayoutAnimationsOnIOSCache;
    private Boolean enableMainQueueCoordinatorOnIOSCache;
    private Boolean enableMainQueueModulesOnIOSCache;
    private Boolean enableModuleArgumentNSNullConversionIOSCache;
    private Boolean enableNativeCSSParsingCache;
    private Boolean enableNetworkEventReportingCache;
    private Boolean enableNewBackgroundAndBorderDrawablesCache;
    private Boolean enablePreparedTextLayoutCache;
    private Boolean enablePropsUpdateReconciliationAndroidCache;
    private Boolean enableResourceTimingAPICache;
    private Boolean enableSynchronousStateUpdatesCache;
    private Boolean enableViewCullingCache;
    private Boolean enableViewRecyclingCache;
    private Boolean enableViewRecyclingForTextCache;
    private Boolean enableViewRecyclingForViewCache;
    private Boolean enableVirtualViewDebugFeaturesCache;
    private Boolean enableVirtualViewRenderStateCache;
    private Boolean enableVirtualViewWindowFocusDetectionCache;
    private Boolean fixMappingOfEventPrioritiesBetweenFabricAndReactCache;
    private Boolean fuseboxEnabledReleaseCache;
    private Boolean fuseboxNetworkInspectionEnabledCache;
    private Boolean hideOffscreenVirtualViewsOnIOSCache;
    private Double preparedTextCacheSizeCache;
    private Boolean preventShadowTreeCommitExhaustionCache;
    private Boolean traceTurboModulePromiseRejectionsOnAndroidCache;
    private Boolean updateRuntimeShadowNodeReferencesOnCommitCache;
    private Boolean useAlwaysAvailableJSErrorHandlingCache;
    private Boolean useFabricInteropCache;
    private Boolean useNativeEqualsInNativeReadableArrayAndroidCache;
    private Boolean useNativeTransformHelperAndroidCache;
    private Boolean useNativeViewConfigsInBridgelessModeCache;
    private Boolean useOptimizedEventBatchingOnAndroidCache;
    private Boolean useRawPropsJsiValueCache;
    private Boolean useShadowNodeStateOnCloneCache;
    private Boolean useTurboModuleInteropCache;
    private Boolean useTurboModulesCache;
    private Double virtualViewPrerenderRatioCache;
    private ReactNativeFeatureFlagsProvider currentProvider = new ReactNativeFeatureFlagsDefaults();
    private final Set<String> accessedFeatureFlags = new LinkedHashSet();

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsAccessor
    public void dangerouslyReset() {
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean commonTestFlag() {
        Boolean bool = this.commonTestFlagCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.commonTestFlag());
            this.accessedFeatureFlags.add("commonTestFlag");
            this.commonTestFlagCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean animatedShouldSignalBatch() {
        Boolean bool = this.animatedShouldSignalBatchCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.animatedShouldSignalBatch());
            this.accessedFeatureFlags.add("animatedShouldSignalBatch");
            this.animatedShouldSignalBatchCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean cxxNativeAnimatedEnabled() {
        Boolean bool = this.cxxNativeAnimatedEnabledCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.cxxNativeAnimatedEnabled());
            this.accessedFeatureFlags.add("cxxNativeAnimatedEnabled");
            this.cxxNativeAnimatedEnabledCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean cxxNativeAnimatedRemoveJsSync() {
        Boolean bool = this.cxxNativeAnimatedRemoveJsSyncCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.cxxNativeAnimatedRemoveJsSync());
            this.accessedFeatureFlags.add("cxxNativeAnimatedRemoveJsSync");
            this.cxxNativeAnimatedRemoveJsSyncCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean disableMainQueueSyncDispatchIOS() {
        Boolean bool = this.disableMainQueueSyncDispatchIOSCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.disableMainQueueSyncDispatchIOS());
            this.accessedFeatureFlags.add("disableMainQueueSyncDispatchIOS");
            this.disableMainQueueSyncDispatchIOSCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean disableMountItemReorderingAndroid() {
        Boolean bool = this.disableMountItemReorderingAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.disableMountItemReorderingAndroid());
            this.accessedFeatureFlags.add("disableMountItemReorderingAndroid");
            this.disableMountItemReorderingAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean disableTextLayoutManagerCacheAndroid() {
        Boolean bool = this.disableTextLayoutManagerCacheAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.disableTextLayoutManagerCacheAndroid());
            this.accessedFeatureFlags.add("disableTextLayoutManagerCacheAndroid");
            this.disableTextLayoutManagerCacheAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableAccessibilityOrder() {
        Boolean bool = this.enableAccessibilityOrderCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableAccessibilityOrder());
            this.accessedFeatureFlags.add("enableAccessibilityOrder");
            this.enableAccessibilityOrderCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableAccumulatedUpdatesInRawPropsAndroid() {
        Boolean bool = this.enableAccumulatedUpdatesInRawPropsAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableAccumulatedUpdatesInRawPropsAndroid());
            this.accessedFeatureFlags.add("enableAccumulatedUpdatesInRawPropsAndroid");
            this.enableAccumulatedUpdatesInRawPropsAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableAndroidTextMeasurementOptimizations() {
        Boolean bool = this.enableAndroidTextMeasurementOptimizationsCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableAndroidTextMeasurementOptimizations());
            this.accessedFeatureFlags.add("enableAndroidTextMeasurementOptimizations");
            this.enableAndroidTextMeasurementOptimizationsCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    /* renamed from: enableBridgelessArchitecture */
    public boolean getNewArchitectureEnabled() {
        Boolean bool = this.enableBridgelessArchitectureCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.getNewArchitectureEnabled());
            this.accessedFeatureFlags.add("enableBridgelessArchitecture");
            this.enableBridgelessArchitectureCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableCppPropsIteratorSetter() {
        Boolean bool = this.enableCppPropsIteratorSetterCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableCppPropsIteratorSetter());
            this.accessedFeatureFlags.add("enableCppPropsIteratorSetter");
            this.enableCppPropsIteratorSetterCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableCustomFocusSearchOnClippedElementsAndroid() {
        Boolean bool = this.enableCustomFocusSearchOnClippedElementsAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableCustomFocusSearchOnClippedElementsAndroid());
            this.accessedFeatureFlags.add("enableCustomFocusSearchOnClippedElementsAndroid");
            this.enableCustomFocusSearchOnClippedElementsAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableDestroyShadowTreeRevisionAsync() {
        Boolean bool = this.enableDestroyShadowTreeRevisionAsyncCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableDestroyShadowTreeRevisionAsync());
            this.accessedFeatureFlags.add("enableDestroyShadowTreeRevisionAsync");
            this.enableDestroyShadowTreeRevisionAsyncCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableDoubleMeasurementFixAndroid() {
        Boolean bool = this.enableDoubleMeasurementFixAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableDoubleMeasurementFixAndroid());
            this.accessedFeatureFlags.add("enableDoubleMeasurementFixAndroid");
            this.enableDoubleMeasurementFixAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableEagerRootViewAttachment() {
        Boolean bool = this.enableEagerRootViewAttachmentCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableEagerRootViewAttachment());
            this.accessedFeatureFlags.add("enableEagerRootViewAttachment");
            this.enableEagerRootViewAttachmentCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableFabricLogs() {
        Boolean bool = this.enableFabricLogsCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableFabricLogs());
            this.accessedFeatureFlags.add("enableFabricLogs");
            this.enableFabricLogsCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableFabricRenderer() {
        Boolean bool = this.enableFabricRendererCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableFabricRenderer());
            this.accessedFeatureFlags.add("enableFabricRenderer");
            this.enableFabricRendererCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableFixForParentTagDuringReparenting() {
        Boolean bool = this.enableFixForParentTagDuringReparentingCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableFixForParentTagDuringReparenting());
            this.accessedFeatureFlags.add("enableFixForParentTagDuringReparenting");
            this.enableFixForParentTagDuringReparentingCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableFontScaleChangesUpdatingLayout() {
        Boolean bool = this.enableFontScaleChangesUpdatingLayoutCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableFontScaleChangesUpdatingLayout());
            this.accessedFeatureFlags.add("enableFontScaleChangesUpdatingLayout");
            this.enableFontScaleChangesUpdatingLayoutCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableIOSTextBaselineOffsetPerLine() {
        Boolean bool = this.enableIOSTextBaselineOffsetPerLineCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableIOSTextBaselineOffsetPerLine());
            this.accessedFeatureFlags.add("enableIOSTextBaselineOffsetPerLine");
            this.enableIOSTextBaselineOffsetPerLineCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableIOSViewClipToPaddingBox() {
        Boolean bool = this.enableIOSViewClipToPaddingBoxCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableIOSViewClipToPaddingBox());
            this.accessedFeatureFlags.add("enableIOSViewClipToPaddingBox");
            this.enableIOSViewClipToPaddingBoxCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableInteropViewManagerClassLookUpOptimizationIOS() {
        Boolean bool = this.enableInteropViewManagerClassLookUpOptimizationIOSCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableInteropViewManagerClassLookUpOptimizationIOS());
            this.accessedFeatureFlags.add("enableInteropViewManagerClassLookUpOptimizationIOS");
            this.enableInteropViewManagerClassLookUpOptimizationIOSCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableLayoutAnimationsOnAndroid() {
        Boolean bool = this.enableLayoutAnimationsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableLayoutAnimationsOnAndroid());
            this.accessedFeatureFlags.add("enableLayoutAnimationsOnAndroid");
            this.enableLayoutAnimationsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableLayoutAnimationsOnIOS() {
        Boolean bool = this.enableLayoutAnimationsOnIOSCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableLayoutAnimationsOnIOS());
            this.accessedFeatureFlags.add("enableLayoutAnimationsOnIOS");
            this.enableLayoutAnimationsOnIOSCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableMainQueueCoordinatorOnIOS() {
        Boolean bool = this.enableMainQueueCoordinatorOnIOSCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableMainQueueCoordinatorOnIOS());
            this.accessedFeatureFlags.add("enableMainQueueCoordinatorOnIOS");
            this.enableMainQueueCoordinatorOnIOSCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableMainQueueModulesOnIOS() {
        Boolean bool = this.enableMainQueueModulesOnIOSCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableMainQueueModulesOnIOS());
            this.accessedFeatureFlags.add("enableMainQueueModulesOnIOS");
            this.enableMainQueueModulesOnIOSCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableModuleArgumentNSNullConversionIOS() {
        Boolean bool = this.enableModuleArgumentNSNullConversionIOSCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableModuleArgumentNSNullConversionIOS());
            this.accessedFeatureFlags.add("enableModuleArgumentNSNullConversionIOS");
            this.enableModuleArgumentNSNullConversionIOSCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableNativeCSSParsing() {
        Boolean bool = this.enableNativeCSSParsingCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableNativeCSSParsing());
            this.accessedFeatureFlags.add("enableNativeCSSParsing");
            this.enableNativeCSSParsingCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableNetworkEventReporting() {
        Boolean bool = this.enableNetworkEventReportingCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableNetworkEventReporting());
            this.accessedFeatureFlags.add("enableNetworkEventReporting");
            this.enableNetworkEventReportingCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableNewBackgroundAndBorderDrawables() {
        Boolean bool = this.enableNewBackgroundAndBorderDrawablesCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableNewBackgroundAndBorderDrawables());
            this.accessedFeatureFlags.add("enableNewBackgroundAndBorderDrawables");
            this.enableNewBackgroundAndBorderDrawablesCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enablePreparedTextLayout() {
        Boolean bool = this.enablePreparedTextLayoutCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enablePreparedTextLayout());
            this.accessedFeatureFlags.add("enablePreparedTextLayout");
            this.enablePreparedTextLayoutCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enablePropsUpdateReconciliationAndroid() {
        Boolean bool = this.enablePropsUpdateReconciliationAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enablePropsUpdateReconciliationAndroid());
            this.accessedFeatureFlags.add("enablePropsUpdateReconciliationAndroid");
            this.enablePropsUpdateReconciliationAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableResourceTimingAPI() {
        Boolean bool = this.enableResourceTimingAPICache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableResourceTimingAPI());
            this.accessedFeatureFlags.add("enableResourceTimingAPI");
            this.enableResourceTimingAPICache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableSynchronousStateUpdates() {
        Boolean bool = this.enableSynchronousStateUpdatesCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableSynchronousStateUpdates());
            this.accessedFeatureFlags.add("enableSynchronousStateUpdates");
            this.enableSynchronousStateUpdatesCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableViewCulling() {
        Boolean bool = this.enableViewCullingCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableViewCulling());
            this.accessedFeatureFlags.add("enableViewCulling");
            this.enableViewCullingCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableViewRecycling() {
        Boolean bool = this.enableViewRecyclingCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableViewRecycling());
            this.accessedFeatureFlags.add("enableViewRecycling");
            this.enableViewRecyclingCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableViewRecyclingForText() {
        Boolean bool = this.enableViewRecyclingForTextCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableViewRecyclingForText());
            this.accessedFeatureFlags.add("enableViewRecyclingForText");
            this.enableViewRecyclingForTextCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableViewRecyclingForView() {
        Boolean bool = this.enableViewRecyclingForViewCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableViewRecyclingForView());
            this.accessedFeatureFlags.add("enableViewRecyclingForView");
            this.enableViewRecyclingForViewCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableVirtualViewDebugFeatures() {
        Boolean bool = this.enableVirtualViewDebugFeaturesCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableVirtualViewDebugFeatures());
            this.accessedFeatureFlags.add("enableVirtualViewDebugFeatures");
            this.enableVirtualViewDebugFeaturesCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableVirtualViewRenderState() {
        Boolean bool = this.enableVirtualViewRenderStateCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableVirtualViewRenderState());
            this.accessedFeatureFlags.add("enableVirtualViewRenderState");
            this.enableVirtualViewRenderStateCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableVirtualViewWindowFocusDetection() {
        Boolean bool = this.enableVirtualViewWindowFocusDetectionCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableVirtualViewWindowFocusDetection());
            this.accessedFeatureFlags.add("enableVirtualViewWindowFocusDetection");
            this.enableVirtualViewWindowFocusDetectionCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fixMappingOfEventPrioritiesBetweenFabricAndReact() {
        Boolean bool = this.fixMappingOfEventPrioritiesBetweenFabricAndReactCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.fixMappingOfEventPrioritiesBetweenFabricAndReact());
            this.accessedFeatureFlags.add("fixMappingOfEventPrioritiesBetweenFabricAndReact");
            this.fixMappingOfEventPrioritiesBetweenFabricAndReactCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fuseboxEnabledRelease() {
        Boolean bool = this.fuseboxEnabledReleaseCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.fuseboxEnabledRelease());
            this.accessedFeatureFlags.add("fuseboxEnabledRelease");
            this.fuseboxEnabledReleaseCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fuseboxNetworkInspectionEnabled() {
        Boolean bool = this.fuseboxNetworkInspectionEnabledCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.fuseboxNetworkInspectionEnabled());
            this.accessedFeatureFlags.add("fuseboxNetworkInspectionEnabled");
            this.fuseboxNetworkInspectionEnabledCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean hideOffscreenVirtualViewsOnIOS() {
        Boolean bool = this.hideOffscreenVirtualViewsOnIOSCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.hideOffscreenVirtualViewsOnIOS());
            this.accessedFeatureFlags.add("hideOffscreenVirtualViewsOnIOS");
            this.hideOffscreenVirtualViewsOnIOSCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public double preparedTextCacheSize() {
        Double d = this.preparedTextCacheSizeCache;
        if (d == null) {
            d = Double.valueOf(this.currentProvider.preparedTextCacheSize());
            this.accessedFeatureFlags.add("preparedTextCacheSize");
            this.preparedTextCacheSizeCache = d;
        }
        return d.doubleValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean preventShadowTreeCommitExhaustion() {
        Boolean bool = this.preventShadowTreeCommitExhaustionCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.preventShadowTreeCommitExhaustion());
            this.accessedFeatureFlags.add("preventShadowTreeCommitExhaustion");
            this.preventShadowTreeCommitExhaustionCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean traceTurboModulePromiseRejectionsOnAndroid() {
        Boolean bool = this.traceTurboModulePromiseRejectionsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.traceTurboModulePromiseRejectionsOnAndroid());
            this.accessedFeatureFlags.add("traceTurboModulePromiseRejectionsOnAndroid");
            this.traceTurboModulePromiseRejectionsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean updateRuntimeShadowNodeReferencesOnCommit() {
        Boolean bool = this.updateRuntimeShadowNodeReferencesOnCommitCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.updateRuntimeShadowNodeReferencesOnCommit());
            this.accessedFeatureFlags.add("updateRuntimeShadowNodeReferencesOnCommit");
            this.updateRuntimeShadowNodeReferencesOnCommitCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useAlwaysAvailableJSErrorHandling() {
        Boolean bool = this.useAlwaysAvailableJSErrorHandlingCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useAlwaysAvailableJSErrorHandling());
            this.accessedFeatureFlags.add("useAlwaysAvailableJSErrorHandling");
            this.useAlwaysAvailableJSErrorHandlingCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useFabricInterop() {
        Boolean bool = this.useFabricInteropCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useFabricInterop());
            this.accessedFeatureFlags.add("useFabricInterop");
            this.useFabricInteropCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useNativeEqualsInNativeReadableArrayAndroid() {
        Boolean bool = this.useNativeEqualsInNativeReadableArrayAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useNativeEqualsInNativeReadableArrayAndroid());
            this.accessedFeatureFlags.add("useNativeEqualsInNativeReadableArrayAndroid");
            this.useNativeEqualsInNativeReadableArrayAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useNativeTransformHelperAndroid() {
        Boolean bool = this.useNativeTransformHelperAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useNativeTransformHelperAndroid());
            this.accessedFeatureFlags.add("useNativeTransformHelperAndroid");
            this.useNativeTransformHelperAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useNativeViewConfigsInBridgelessMode() {
        Boolean bool = this.useNativeViewConfigsInBridgelessModeCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useNativeViewConfigsInBridgelessMode());
            this.accessedFeatureFlags.add("useNativeViewConfigsInBridgelessMode");
            this.useNativeViewConfigsInBridgelessModeCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useOptimizedEventBatchingOnAndroid() {
        Boolean bool = this.useOptimizedEventBatchingOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useOptimizedEventBatchingOnAndroid());
            this.accessedFeatureFlags.add("useOptimizedEventBatchingOnAndroid");
            this.useOptimizedEventBatchingOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useRawPropsJsiValue() {
        Boolean bool = this.useRawPropsJsiValueCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useRawPropsJsiValue());
            this.accessedFeatureFlags.add("useRawPropsJsiValue");
            this.useRawPropsJsiValueCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useShadowNodeStateOnClone() {
        Boolean bool = this.useShadowNodeStateOnCloneCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useShadowNodeStateOnClone());
            this.accessedFeatureFlags.add("useShadowNodeStateOnClone");
            this.useShadowNodeStateOnCloneCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useTurboModuleInterop() {
        Boolean bool = this.useTurboModuleInteropCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useTurboModuleInterop());
            this.accessedFeatureFlags.add("useTurboModuleInterop");
            this.useTurboModuleInteropCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useTurboModules() {
        Boolean bool = this.useTurboModulesCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useTurboModules());
            this.accessedFeatureFlags.add("useTurboModules");
            this.useTurboModulesCache = bool;
        }
        return bool.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public double virtualViewPrerenderRatio() {
        Double d = this.virtualViewPrerenderRatioCache;
        if (d == null) {
            d = Double.valueOf(this.currentProvider.virtualViewPrerenderRatio());
            this.accessedFeatureFlags.add("virtualViewPrerenderRatio");
            this.virtualViewPrerenderRatioCache = d;
        }
        return d.doubleValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsAccessor
    public void override(ReactNativeFeatureFlagsProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        if (!this.accessedFeatureFlags.isEmpty()) {
            throw new IllegalStateException("Feature flags were accessed before being overridden: " + CollectionsKt.joinToString$default(this.accessedFeatureFlags, ", ", null, null, 0, null, new Function1() { // from class: com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsLocalAccessor$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    CharSequence override$lambda$0;
                    override$lambda$0 = ReactNativeFeatureFlagsLocalAccessor.override$lambda$0((String) obj);
                    return override$lambda$0;
                }
            }, 30, null));
        }
        this.currentProvider = provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence override$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsAccessor
    public String dangerouslyForceOverride(ReactNativeFeatureFlagsProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        String accessedFeatureFlags$ReactAndroid_release = getAccessedFeatureFlags$ReactAndroid_release();
        this.currentProvider = provider;
        return accessedFeatureFlags$ReactAndroid_release;
    }

    public final String getAccessedFeatureFlags$ReactAndroid_release() {
        if (this.accessedFeatureFlags.isEmpty()) {
            return null;
        }
        return CollectionsKt.joinToString$default(this.accessedFeatureFlags, ", ", null, null, 0, null, new Function1() { // from class: com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsLocalAccessor$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence accessedFeatureFlags$lambda$1;
                accessedFeatureFlags$lambda$1 = ReactNativeFeatureFlagsLocalAccessor.getAccessedFeatureFlags$lambda$1((String) obj);
                return accessedFeatureFlags$lambda$1;
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence getAccessedFeatureFlags$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it;
    }
}
