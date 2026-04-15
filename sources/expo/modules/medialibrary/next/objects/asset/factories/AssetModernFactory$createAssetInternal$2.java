package expo.modules.medialibrary.next.objects.asset.factories;

import android.net.Uri;
import expo.modules.medialibrary.next.objects.asset.Asset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetModernFactory.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory$createAssetInternal$2", f = "AssetModernFactory.kt", i = {0}, l = {69, 82}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class AssetModernFactory$createAssetInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Asset>, Object> {
    final /* synthetic */ Uri $filePath;
    final /* synthetic */ boolean $forceUniqueName;
    final /* synthetic */ String $relativePath;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AssetModernFactory this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetModernFactory$createAssetInternal$2(AssetModernFactory assetModernFactory, Uri uri, boolean z, String str, Continuation<? super AssetModernFactory$createAssetInternal$2> continuation) {
        super(2, continuation);
        this.this$0 = assetModernFactory;
        this.$filePath = uri;
        this.$forceUniqueName = z;
        this.$relativePath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AssetModernFactory$createAssetInternal$2 assetModernFactory$createAssetInternal$2 = new AssetModernFactory$createAssetInternal$2(this.this$0, this.$filePath, this.$forceUniqueName, this.$relativePath, continuation);
        assetModernFactory$createAssetInternal$2.L$0 = obj;
        return assetModernFactory$createAssetInternal$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Asset> continuation) {
        return ((AssetModernFactory$createAssetInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00ce, code lost:
    
        if (r10 == r0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00d0, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0077, code lost:
    
        if (r10 == r0) goto L38;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L24
            if (r1 == r4) goto L1c
            if (r1 != r2) goto L14
            kotlin.ResultKt.throwOnFailure(r10)
            goto Ld1
        L14:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L1c:
            java.lang.Object r1 = r9.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L7a
        L24:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            r1 = r10
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory r10 = r9.this$0
            android.content.ContentResolver r10 = expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory.access$getContentResolver(r10)
            android.net.Uri r5 = r9.$filePath
            java.lang.String r10 = r10.getType(r5)
            if (r10 == 0) goto L3f
            java.lang.String r10 = expo.modules.medialibrary.next.objects.wrappers.MimeType.m1309constructorimpl(r10)
            goto L47
        L3f:
            expo.modules.medialibrary.next.objects.wrappers.MimeType$Companion r10 = expo.modules.medialibrary.next.objects.wrappers.MimeType.INSTANCE
            android.net.Uri r5 = r9.$filePath
            java.lang.String r10 = r10.m1322fromdctPOJs(r5)
        L47:
            boolean r5 = r9.$forceUniqueName
            if (r5 == 0) goto L52
            android.net.Uri r5 = r9.$filePath
            java.lang.String r5 = expo.modules.medialibrary.next.objects.asset.factories.BuildUniqueDisplayNameKt.buildUniqueDisplayName(r5)
            goto L5c
        L52:
            android.net.Uri r5 = r9.$filePath
            java.lang.String r5 = r5.getLastPathSegment()
            if (r5 != 0) goto L5c
            java.lang.String r5 = "asset"
        L5c:
            java.lang.String r6 = r9.$relativePath
            if (r6 != 0) goto L66
            expo.modules.medialibrary.next.objects.wrappers.RelativePath$Companion r6 = expo.modules.medialibrary.next.objects.wrappers.RelativePath.INSTANCE
            java.lang.String r6 = expo.modules.medialibrary.next.objects.wrappers.RelativePath.Companion.m1331createwht0CjE$default(r6, r10, r3, r2, r3)
        L66:
            expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory r7 = r9.this$0
            android.content.ContentResolver r7 = expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory.access$getContentResolver(r7)
            r8 = r9
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r9.L$0 = r1
            r9.label = r4
            java.lang.Object r10 = expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.m1281insertPendingAssetcT81_0k(r7, r5, r10, r6, r8)
            if (r10 != r0) goto L7a
            goto Ld0
        L7a:
            android.net.Uri r10 = (android.net.Uri) r10
            kotlinx.coroutines.CoroutineScopeKt.ensureActive(r1)     // Catch: java.lang.IllegalStateException -> L9d
            expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory r5 = r9.this$0     // Catch: java.lang.IllegalStateException -> L9d
            android.content.ContentResolver r5 = expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory.access$getContentResolver(r5)     // Catch: java.lang.IllegalStateException -> L9d
            android.net.Uri r6 = r9.$filePath     // Catch: java.lang.IllegalStateException -> L9d
            expo.modules.medialibrary.next.extensions.resolver.TransferExtensionsKt.copyUriContent(r5, r6, r10)     // Catch: java.lang.IllegalStateException -> L9d
            kotlinx.coroutines.CoroutineScopeKt.ensureActive(r1)     // Catch: java.lang.IllegalStateException -> L9d
            expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory r1 = r9.this$0     // Catch: java.lang.IllegalStateException -> L9d
            android.content.ContentResolver r1 = expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory.access$getContentResolver(r1)     // Catch: java.lang.IllegalStateException -> L9d
            expo.modules.medialibrary.next.extensions.resolver.AssetExtensionsKt.publishPendingAsset(r1, r10)     // Catch: java.lang.IllegalStateException -> L9d
            expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory r1 = r9.this$0     // Catch: java.lang.IllegalStateException -> L9d
            expo.modules.medialibrary.next.objects.asset.Asset r10 = r1.create(r10)     // Catch: java.lang.IllegalStateException -> L9d
            return r10
        L9d:
            r1 = move-exception
            expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory r5 = r9.this$0
            android.content.ContentResolver r5 = expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory.access$getContentResolver(r5)
            r5.delete(r10, r3, r3)
            java.lang.String r10 = r1.getMessage()
            if (r10 == 0) goto Ld4
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            java.lang.String r5 = "Failed to build unique file"
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            boolean r10 = kotlin.text.StringsKt.contains(r10, r5, r4)
            if (r10 != r4) goto Ld4
            boolean r10 = r9.$forceUniqueName
            if (r10 != 0) goto Ld4
            expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory r10 = r9.this$0
            android.net.Uri r1 = r9.$filePath
            java.lang.String r5 = r9.$relativePath
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r9.L$0 = r3
            r9.label = r2
            java.lang.Object r10 = expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory.m1303access$createAssetInternal7lvfX64(r10, r1, r5, r4, r6)
            if (r10 != r0) goto Ld1
        Ld0:
            return r0
        Ld1:
            expo.modules.medialibrary.next.objects.asset.Asset r10 = (expo.modules.medialibrary.next.objects.asset.Asset) r10
            return r10
        Ld4:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.objects.asset.factories.AssetModernFactory$createAssetInternal$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
