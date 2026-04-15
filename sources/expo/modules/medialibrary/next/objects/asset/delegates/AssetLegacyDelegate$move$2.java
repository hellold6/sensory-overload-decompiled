package expo.modules.medialibrary.next.objects.asset.delegates;

import androidx.media3.extractor.ts.PsExtractor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AssetLegacyDelegate.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$move$2", f = "AssetLegacyDelegate.kt", i = {}, l = {185, PsExtractor.PRIVATE_STREAM_1}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class AssetLegacyDelegate$move$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $relativePath;
    int label;
    final /* synthetic */ AssetLegacyDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetLegacyDelegate$move$2(AssetLegacyDelegate assetLegacyDelegate, String str, Continuation<? super AssetLegacyDelegate$move$2> continuation) {
        super(2, continuation);
        this.this$0 = assetLegacyDelegate;
        this.$relativePath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssetLegacyDelegate$move$2(this.this$0, this.$relativePath, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AssetLegacyDelegate$move$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0080, code lost:
    
        if (r7 == r0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0082, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0040, code lost:
    
        if (r7 == r0) goto L17;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            r3 = 2
            r4 = 0
            if (r1 == 0) goto L1f
            if (r1 == r2) goto L1b
            if (r1 != r3) goto L13
            kotlin.ResultKt.throwOnFailure(r7)
            goto L83
        L13:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1b:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L43
        L1f:
            kotlin.ResultKt.throwOnFailure(r7)
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate r7 = r6.this$0
            expo.modules.medialibrary.next.permissions.SystemPermissionsDelegate r7 = r7.getSystemPermissionsDelegate()
            r7.requireWritePermissions()
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate r7 = r6.this$0
            android.content.ContentResolver r7 = expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.access$getContentResolver(r7)
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate r1 = r6.this$0
            android.net.Uri r1 = r1.getContentUri()
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6.label = r2
            java.lang.Object r7 = expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.queryAssetData(r7, r1, r5)
            if (r7 != r0) goto L43
            goto L82
        L43:
            java.lang.String r7 = (java.lang.String) r7
            if (r7 == 0) goto L9d
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            java.io.File r2 = new java.io.File
            java.lang.String r5 = r6.$relativePath
            java.lang.String r5 = expo.modules.medialibrary.next.objects.wrappers.RelativePath.m1328toFilePathimpl(r5)
            r2.<init>(r5)
            java.io.File r1 = expo.modules.medialibrary.next.extensions.FileExtensionsKt.safeMove(r1, r2)
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate r2 = r6.this$0
            android.content.ContentResolver r2 = expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.access$getContentResolver(r2)
            expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.deleteBy(r2, r7)
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate r7 = r6.this$0
            java.lang.ref.WeakReference r7 = expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.access$getContextRef$p(r7)
            android.content.Context r7 = expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt.getOrThrow(r7)
            java.lang.String r1 = r1.getPath()
            java.lang.String r2 = "getPath(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r2 = r6
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r6.label = r3
            java.lang.Object r7 = expo.modules.medialibrary.next.extensions.ContextExtensionsKt.scanFile(r7, r1, r4, r2)
            if (r7 != r0) goto L83
        L82:
            return r0
        L83:
            kotlin.Pair r7 = (kotlin.Pair) r7
            java.lang.Object r7 = r7.component2()
            android.net.Uri r7 = (android.net.Uri) r7
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate r0 = r6.this$0
            if (r7 == 0) goto L95
            expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate.access$setContentUri$p(r0, r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L95:
            expo.modules.medialibrary.next.exceptions.AssetCouldNotBeCreated r7 = new expo.modules.medialibrary.next.exceptions.AssetCouldNotBeCreated
            java.lang.String r0 = "Could not create a new asset while moving the old one"
            r7.<init>(r0, r4, r3, r4)
            throw r7
        L9d:
            expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException r7 = new expo.modules.medialibrary.next.exceptions.AssetPropertyNotFoundException
            java.lang.String r0 = "Asset path"
            r7.<init>(r0, r4, r3, r4)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.delegates.AssetLegacyDelegate$move$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
