package expo.modules.medialibrary.next.objects.asset.factories;

import android.content.Context;
import android.net.Uri;
import androidx.core.net.UriKt;
import expo.modules.medialibrary.next.exceptions.AssetCouldNotBeCreated;
import expo.modules.medialibrary.next.extensions.FileExtensionsKt;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.asset.Asset;
import java.io.File;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: AssetLegacyFactory.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.next.objects.asset.factories.AssetLegacyFactory$create$2", f = "AssetLegacyFactory.kt", i = {0}, l = {56}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
/* loaded from: classes3.dex */
final class AssetLegacyFactory$create$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Asset>, Object> {
    final /* synthetic */ Uri $filePath;
    final /* synthetic */ String $relativePath;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AssetLegacyFactory this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetLegacyFactory$create$2(AssetLegacyFactory assetLegacyFactory, Uri uri, String str, Continuation<? super AssetLegacyFactory$create$2> continuation) {
        super(2, continuation);
        this.this$0 = assetLegacyFactory;
        this.$filePath = uri;
        this.$relativePath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AssetLegacyFactory$create$2 assetLegacyFactory$create$2 = new AssetLegacyFactory$create$2(this.this$0, this.$filePath, this.$relativePath, continuation);
        assetLegacyFactory$create$2.L$0 = obj;
        return assetLegacyFactory$create$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Asset> continuation) {
        return ((AssetLegacyFactory$create$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        File m1302createDestinationDirectorymc4yU64;
        WeakReference weakReference;
        Object scanFile;
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            this.this$0.getSystemPermissionsDelegate().requireWritePermissions();
            m1302createDestinationDirectorymc4yU64 = this.this$0.m1302createDestinationDirectorymc4yU64(this.$filePath, this.$relativePath);
            File safeCopy = FileExtensionsKt.safeCopy(UriKt.toFile(this.$filePath), m1302createDestinationDirectorymc4yU64);
            AssetLegacyFactory assetLegacyFactory = this.this$0;
            weakReference = assetLegacyFactory.contextRef;
            Context orThrow = WeakReferenceExtensionsKt.getOrThrow(weakReference);
            String[] strArr = {safeCopy.toString()};
            this.L$0 = coroutineScope2;
            this.label = 1;
            scanFile = assetLegacyFactory.scanFile(orThrow, strArr, null, this);
            if (scanFile == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            obj = scanFile;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Uri uri = (Uri) ((Pair) obj).component2();
        CoroutineScopeKt.ensureActive(coroutineScope);
        if (uri == null) {
            throw new AssetCouldNotBeCreated("Failed to create asset: could not add asset to MediaStore", null, 2, null);
        }
        return this.this$0.create(uri);
    }
}
