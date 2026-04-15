package expo.modules.medialibrary.next.extensions.resolver;

import expo.modules.medialibrary.next.objects.wrappers.RelativePath;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlbumExtensions.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.extensions.resolver.AlbumExtensionsKt", f = "AlbumExtensions.kt", i = {}, l = {25}, m = "queryAlbumRelativePath", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AlbumExtensionsKt$queryAlbumRelativePath$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlbumExtensionsKt$queryAlbumRelativePath$1(Continuation<? super AlbumExtensionsKt$queryAlbumRelativePath$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object queryAlbumRelativePath = AlbumExtensionsKt.queryAlbumRelativePath(null, null, this);
        if (queryAlbumRelativePath == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return queryAlbumRelativePath;
        }
        String str = (String) queryAlbumRelativePath;
        if (str != null) {
            return RelativePath.m1323boximpl(str);
        }
        return null;
    }
}
