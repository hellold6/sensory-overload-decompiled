package expo.modules.notifications.notifications.presentation.builders;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DownloadImage.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.notifications.notifications.presentation.builders.DownloadImageKt", f = "DownloadImage.kt", i = {}, l = {13}, m = "downloadImage", n = {}, s = {})
/* loaded from: classes3.dex */
public final class DownloadImageKt$downloadImage$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadImageKt$downloadImage$1(Continuation<? super DownloadImageKt$downloadImage$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DownloadImageKt.downloadImage(null, 0L, 0L, this);
    }
}
