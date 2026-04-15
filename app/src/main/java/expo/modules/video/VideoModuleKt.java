package expo.modules.video;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.Queues;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.functions.UntypedAsyncFunctionComponent;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.views.AnyViewProp;
import expo.modules.kotlin.views.ConcreteViewProp;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import expo.modules.video.enums.ContentFit;
import expo.modules.video.player.VideoPlayer;
import expo.modules.video.records.FullscreenOptions;
import expo.modules.video.utils.PictureInPictureUtilsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: VideoModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0083\b¨\u0006\u0005"}, d2 = {"VideoViewComponent", "", ExifInterface.GPS_DIRECTION_TRUE, "Lexpo/modules/video/VideoView;", "Lexpo/modules/kotlin/views/ViewDefinitionBuilder;", "expo-video_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoModuleKt {
    private static final /* synthetic */ <T extends VideoView> void VideoViewComponent(ViewDefinitionBuilder<T> viewDefinitionBuilder) {
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent2;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        viewDefinitionBuilder.Events("onPictureInPictureStart", "onPictureInPictureStop", "onFullscreenEnter", "onFullscreenExit", "onFirstFrameRender");
        Intrinsics.needClassReification();
        VideoModuleKt$VideoViewComponent$1 videoModuleKt$VideoViewComponent$1 = VideoModuleKt$VideoViewComponent$1.INSTANCE;
        Map<String, AnyViewProp> props = viewDefinitionBuilder.getProps();
        AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(VideoPlayer.class), false));
        if (anyType == null) {
            anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(VideoPlayer.class), false, VideoModuleKt$VideoViewComponent$$inlined$Prop$1.INSTANCE), null);
        }
        props.put("player", new ConcreteViewProp("player", anyType, videoModuleKt$VideoViewComponent$1));
        Intrinsics.needClassReification();
        VideoModuleKt$VideoViewComponent$2 videoModuleKt$VideoViewComponent$2 = VideoModuleKt$VideoViewComponent$2.INSTANCE;
        Map<String, AnyViewProp> props2 = viewDefinitionBuilder.getProps();
        AnyType anyType2 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Boolean.class), false));
        if (anyType2 == null) {
            anyType2 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, VideoModuleKt$VideoViewComponent$$inlined$Prop$2.INSTANCE), null);
        }
        props2.put("nativeControls", new ConcreteViewProp("nativeControls", anyType2, videoModuleKt$VideoViewComponent$2));
        Intrinsics.needClassReification();
        VideoModuleKt$VideoViewComponent$3 videoModuleKt$VideoViewComponent$3 = VideoModuleKt$VideoViewComponent$3.INSTANCE;
        Map<String, AnyViewProp> props3 = viewDefinitionBuilder.getProps();
        AnyType anyType3 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(ContentFit.class), false));
        if (anyType3 == null) {
            anyType3 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ContentFit.class), false, VideoModuleKt$VideoViewComponent$$inlined$Prop$3.INSTANCE), null);
        }
        props3.put("contentFit", new ConcreteViewProp("contentFit", anyType3, videoModuleKt$VideoViewComponent$3));
        Intrinsics.needClassReification();
        VideoModuleKt$VideoViewComponent$4 videoModuleKt$VideoViewComponent$4 = VideoModuleKt$VideoViewComponent$4.INSTANCE;
        Map<String, AnyViewProp> props4 = viewDefinitionBuilder.getProps();
        AnyType anyType4 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Boolean.class), false));
        if (anyType4 == null) {
            anyType4 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, VideoModuleKt$VideoViewComponent$$inlined$Prop$4.INSTANCE), null);
        }
        props4.put("startsPictureInPictureAutomatically", new ConcreteViewProp("startsPictureInPictureAutomatically", anyType4, videoModuleKt$VideoViewComponent$4));
        Intrinsics.needClassReification();
        VideoModuleKt$VideoViewComponent$5 videoModuleKt$VideoViewComponent$5 = VideoModuleKt$VideoViewComponent$5.INSTANCE;
        Map<String, AnyViewProp> props5 = viewDefinitionBuilder.getProps();
        AnyType anyType5 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Boolean.class), true));
        if (anyType5 == null) {
            anyType5 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, VideoModuleKt$VideoViewComponent$$inlined$Prop$5.INSTANCE), null);
        }
        props5.put("allowsFullscreen", new ConcreteViewProp("allowsFullscreen", anyType5, videoModuleKt$VideoViewComponent$5));
        Intrinsics.needClassReification();
        VideoModuleKt$VideoViewComponent$6 videoModuleKt$VideoViewComponent$6 = VideoModuleKt$VideoViewComponent$6.INSTANCE;
        Map<String, AnyViewProp> props6 = viewDefinitionBuilder.getProps();
        AnyType anyType6 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(FullscreenOptions.class), true));
        if (anyType6 == null) {
            anyType6 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FullscreenOptions.class), true, VideoModuleKt$VideoViewComponent$$inlined$Prop$6.INSTANCE), null);
        }
        props6.put("fullscreenOptions", new ConcreteViewProp("fullscreenOptions", anyType6, videoModuleKt$VideoViewComponent$6));
        Intrinsics.needClassReification();
        VideoModuleKt$VideoViewComponent$7 videoModuleKt$VideoViewComponent$7 = VideoModuleKt$VideoViewComponent$7.INSTANCE;
        Map<String, AnyViewProp> props7 = viewDefinitionBuilder.getProps();
        AnyType anyType7 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Boolean.class), true));
        if (anyType7 == null) {
            anyType7 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, VideoModuleKt$VideoViewComponent$$inlined$Prop$7.INSTANCE), null);
        }
        props7.put("requiresLinearPlayback", new ConcreteViewProp("requiresLinearPlayback", anyType7, videoModuleKt$VideoViewComponent$7));
        Intrinsics.needClassReification();
        VideoModuleKt$VideoViewComponent$8 videoModuleKt$VideoViewComponent$8 = VideoModuleKt$VideoViewComponent$8.INSTANCE;
        Map<String, AnyViewProp> props8 = viewDefinitionBuilder.getProps();
        AnyType anyType8 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(Boolean.class), true));
        if (anyType8 == null) {
            anyType8 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, VideoModuleKt$VideoViewComponent$$inlined$Prop$8.INSTANCE), null);
        }
        props8.put("useExoShutter", new ConcreteViewProp("useExoShutter", anyType8, videoModuleKt$VideoViewComponent$8));
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        if (Object.class == Promise.class) {
            Intrinsics.needClassReification();
            asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("enterFullscreen", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.video.VideoModuleKt$VideoViewComponent$$inlined$AsyncFunction$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
                    ((VideoView) promise).enterFullscreen();
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            AnyType[] anyTypeArr = new AnyType[1];
            AnyTypeProvider anyTypeProvider = AnyTypeProvider.INSTANCE;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            AnyType anyType9 = anyTypeProvider.getTypesMap().get(new Pair(orCreateKotlinClass, false));
            if (anyType9 == null) {
                Intrinsics.needClassReification();
                VideoModuleKt$VideoViewComponent$$inlined$AsyncFunction$2 videoModuleKt$VideoViewComponent$$inlined$AsyncFunction$2 = new Function0<KType>() { // from class: expo.modules.video.VideoModuleKt$VideoViewComponent$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                        return null;
                    }
                };
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                anyType9 = new AnyType(new LazyKType(orCreateKotlinClass2, false, videoModuleKt$VideoViewComponent$$inlined$AsyncFunction$2), null);
            }
            anyTypeArr[0] = anyType9;
            Intrinsics.needClassReification();
            Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.video.VideoModuleKt$VideoViewComponent$$inlined$AsyncFunction$3
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    ((VideoView) objArr[0]).enterFullscreen();
                    return Unit.INSTANCE;
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                untypedAsyncFunctionComponent = new StringAsyncFunctionComponent("enterFullscreen", anyTypeArr, function1);
                            } else {
                                untypedAsyncFunctionComponent = new UntypedAsyncFunctionComponent("enterFullscreen", anyTypeArr, function1);
                            }
                        } else {
                            untypedAsyncFunctionComponent = new FloatAsyncFunctionComponent("enterFullscreen", anyTypeArr, function1);
                        }
                    } else {
                        untypedAsyncFunctionComponent = new DoubleAsyncFunctionComponent("enterFullscreen", anyTypeArr, function1);
                    }
                } else {
                    untypedAsyncFunctionComponent = new BoolAsyncFunctionComponent("enterFullscreen", anyTypeArr, function1);
                }
            } else {
                untypedAsyncFunctionComponent = new IntAsyncFunctionComponent("enterFullscreen", anyTypeArr, function1);
            }
            asyncFunctionWithPromiseComponent = untypedAsyncFunctionComponent;
        }
        viewDefinitionBuilder.getAsyncFunctions().put("enterFullscreen", asyncFunctionWithPromiseComponent);
        asyncFunctionWithPromiseComponent.runOnQueue(Queues.MAIN);
        viewDefinitionBuilder.getAsyncFunctions().put("exitFullscreen", new UntypedAsyncFunctionComponent("exitFullscreen", new AnyType[0], new VideoModuleKt$VideoViewComponent$$inlined$AsyncFunctionWithoutArgs$1()));
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        if (Object.class == Promise.class) {
            Intrinsics.needClassReification();
            asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("startPictureInPicture", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.video.VideoModuleKt$VideoViewComponent$$inlined$AsyncFunction$4
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<unused var>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
                    PictureInPictureUtilsKt.runWithPiPMisconfigurationSoftHandling(true, new VideoModuleKt$VideoViewComponent$11$1((VideoView) promise));
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            AnyType[] anyTypeArr2 = new AnyType[1];
            AnyTypeProvider anyTypeProvider2 = AnyTypeProvider.INSTANCE;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            AnyType anyType10 = anyTypeProvider2.getTypesMap().get(new Pair(orCreateKotlinClass3, false));
            if (anyType10 == null) {
                Intrinsics.needClassReification();
                VideoModuleKt$VideoViewComponent$$inlined$AsyncFunction$5 videoModuleKt$VideoViewComponent$$inlined$AsyncFunction$5 = new Function0<KType>() { // from class: expo.modules.video.VideoModuleKt$VideoViewComponent$$inlined$AsyncFunction$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                        return null;
                    }
                };
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                anyType10 = new AnyType(new LazyKType(orCreateKotlinClass4, false, videoModuleKt$VideoViewComponent$$inlined$AsyncFunction$5), null);
            }
            anyTypeArr2[0] = anyType10;
            Intrinsics.needClassReification();
            Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.video.VideoModuleKt$VideoViewComponent$$inlined$AsyncFunction$6
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    PictureInPictureUtilsKt.runWithPiPMisconfigurationSoftHandling(true, new VideoModuleKt$VideoViewComponent$11$1((VideoView) objArr[0]));
                    return Unit.INSTANCE;
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                untypedAsyncFunctionComponent2 = new StringAsyncFunctionComponent("startPictureInPicture", anyTypeArr2, function12);
                            } else {
                                untypedAsyncFunctionComponent2 = new UntypedAsyncFunctionComponent("startPictureInPicture", anyTypeArr2, function12);
                            }
                        } else {
                            untypedAsyncFunctionComponent2 = new FloatAsyncFunctionComponent("startPictureInPicture", anyTypeArr2, function12);
                        }
                    } else {
                        untypedAsyncFunctionComponent2 = new DoubleAsyncFunctionComponent("startPictureInPicture", anyTypeArr2, function12);
                    }
                } else {
                    untypedAsyncFunctionComponent2 = new BoolAsyncFunctionComponent("startPictureInPicture", anyTypeArr2, function12);
                }
            } else {
                untypedAsyncFunctionComponent2 = new IntAsyncFunctionComponent("startPictureInPicture", anyTypeArr2, function12);
            }
            asyncFunctionWithPromiseComponent2 = untypedAsyncFunctionComponent2;
        }
        viewDefinitionBuilder.getAsyncFunctions().put("startPictureInPicture", asyncFunctionWithPromiseComponent2);
        viewDefinitionBuilder.getAsyncFunctions().put("stopPictureInPicture", new UntypedAsyncFunctionComponent("stopPictureInPicture", new AnyType[0], new VideoModuleKt$VideoViewComponent$$inlined$AsyncFunctionWithoutArgs$2()));
        Intrinsics.needClassReification();
        viewDefinitionBuilder.setOnViewDestroys(new Function1<View, Unit>() { // from class: expo.modules.video.VideoModuleKt$VideoViewComponent$$inlined$OnViewDestroys$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VideoManager.INSTANCE.unregisterVideoView((VideoView) it);
            }
        });
        Intrinsics.needClassReification();
        viewDefinitionBuilder.setOnViewDidUpdateProps(new Function1<View, Unit>() { // from class: expo.modules.video.VideoModuleKt$VideoViewComponent$$inlined$OnViewDidUpdateProps$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VideoView videoView = (VideoView) it;
                if (videoView.getPlayerView().getUseController() != videoView.getUseNativeControls()) {
                    videoView.getPlayerView().setUseController(videoView.getUseNativeControls());
                }
            }
        });
    }
}
