package expo.modules.image.events;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import expo.modules.image.ExpoImageViewWrapper;
import expo.modules.image.enums.ImageCacheType;
import expo.modules.image.records.ImageLoadEvent;
import expo.modules.image.records.ImageSource;
import expo.modules.kotlin.viewevent.ViewEventCallback;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GlideRequestListener.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.image.events.GlideRequestListener$onResourceReady$1", f = "GlideRequestListener.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class GlideRequestListener$onResourceReady$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DataSource $dataSource;
    final /* synthetic */ ExpoImageViewWrapper $imageWrapper;
    final /* synthetic */ int $intrinsicHeight;
    final /* synthetic */ int $intrinsicWidth;
    final /* synthetic */ Object $model;
    final /* synthetic */ Drawable $resource;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlideRequestListener$onResourceReady$1(ExpoImageViewWrapper expoImageViewWrapper, DataSource dataSource, Object obj, int i, int i2, Drawable drawable, Continuation<? super GlideRequestListener$onResourceReady$1> continuation) {
        super(2, continuation);
        this.$imageWrapper = expoImageViewWrapper;
        this.$dataSource = dataSource;
        this.$model = obj;
        this.$intrinsicWidth = i;
        this.$intrinsicHeight = i2;
        this.$resource = drawable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GlideRequestListener$onResourceReady$1(this.$imageWrapper, this.$dataSource, this.$model, this.$intrinsicWidth, this.$intrinsicHeight, this.$resource, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GlideRequestListener$onResourceReady$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ViewEventCallback<ImageLoadEvent> onLoad$expo_image_release = this.$imageWrapper.getOnLoad$expo_image_release();
        String name = ImageCacheType.INSTANCE.fromNativeValue(this.$dataSource).name();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = name.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        onLoad$expo_image_release.invoke(new ImageLoadEvent(lowerCase, new ImageSource(this.$model.toString(), this.$intrinsicWidth, this.$intrinsicHeight, null, this.$resource instanceof Animatable)));
        return Unit.INSTANCE;
    }
}
