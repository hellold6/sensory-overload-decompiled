package expo.modules.notifications.notifications.presentation.builders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DownloadImage.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$2$1", f = "DownloadImage.kt", i = {}, l = {14}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class DownloadImageKt$downloadImage$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ long $connectTimeout;
    final /* synthetic */ Uri $imageUrl;
    final /* synthetic */ long $readTimeout;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadImageKt$downloadImage$2$1(Uri uri, long j, long j2, Continuation<? super DownloadImageKt$downloadImage$2$1> continuation) {
        super(2, continuation);
        this.$imageUrl = uri;
        this.$connectTimeout = j;
        this.$readTimeout = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DownloadImageKt$downloadImage$2$1(this.$imageUrl, this.$connectTimeout, this.$readTimeout, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((DownloadImageKt$downloadImage$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadImage.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$2$1$1", f = "DownloadImage.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: expo.modules.notifications.notifications.presentation.builders.DownloadImageKt$downloadImage$2$1$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
        final /* synthetic */ long $connectTimeout;
        final /* synthetic */ Uri $imageUrl;
        final /* synthetic */ long $readTimeout;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Uri uri, long j, long j2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$imageUrl = uri;
            this.$connectTimeout = j;
            this.$readTimeout = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$imageUrl, this.$connectTimeout, this.$readTimeout, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            URLConnection openConnection = new URL(this.$imageUrl.toString()).openConnection();
            openConnection.setConnectTimeout((int) this.$connectTimeout);
            openConnection.setReadTimeout((int) this.$readTimeout);
            return BitmapFactory.decodeStream(openConnection.getInputStream());
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        this.label = 1;
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.$imageUrl, this.$connectTimeout, this.$readTimeout, null), this);
        return withContext == coroutine_suspended ? coroutine_suspended : withContext;
    }
}
