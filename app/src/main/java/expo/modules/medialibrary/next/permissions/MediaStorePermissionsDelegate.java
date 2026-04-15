package expo.modules.medialibrary.next.permissions;

import android.content.Context;
import android.net.Uri;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.activityresult.AppContextActivityResultLauncher;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.medialibrary.next.permissions.contracts.DeleteContractInput;
import expo.modules.medialibrary.next.permissions.contracts.WriteContractInput;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaStorePermissionsDelegate.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0087@¢\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0019H\u0086@¢\u0006\u0002\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u0013*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u0016H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000f0\rX\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lexpo/modules/medialibrary/next/permissions/MediaStorePermissionsDelegate;", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "<init>", "(Lexpo/modules/kotlin/AppContext;)V", "getAppContext", "()Lexpo/modules/kotlin/AppContext;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "deleteLauncher", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultLauncher;", "Lexpo/modules/medialibrary/next/permissions/contracts/DeleteContractInput;", "", "writeLauncher", "Lexpo/modules/medialibrary/next/permissions/contracts/WriteContractInput;", "launchMediaStoreDeleteRequest", "", "uris", "", "Landroid/net/Uri;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestMediaLibraryWritePermission", "", "(Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerMediaStoreContracts", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;", "appContextProvider", "Lexpo/modules/kotlin/providers/AppContextProvider;", "(Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;Lexpo/modules/kotlin/providers/AppContextProvider;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasWritePermissionForUri", "uri", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaStorePermissionsDelegate {
    private final AppContext appContext;
    private AppContextActivityResultLauncher<DeleteContractInput, Boolean> deleteLauncher;
    private AppContextActivityResultLauncher<WriteContractInput, Boolean> writeLauncher;

    public MediaStorePermissionsDelegate(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
    }

    public final AppContext getAppContext() {
        return this.appContext;
    }

    private final Context getContext() {
        Context reactContext = this.appContext.getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object launchMediaStoreDeleteRequest(java.util.List<? extends android.net.Uri> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$launchMediaStoreDeleteRequest$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$launchMediaStoreDeleteRequest$1 r0 = (expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$launchMediaStoreDeleteRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$launchMediaStoreDeleteRequest$1 r0 = new expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$launchMediaStoreDeleteRequest$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L55
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher<expo.modules.medialibrary.next.permissions.contracts.DeleteContractInput, java.lang.Boolean> r6 = r4.deleteLauncher
            if (r6 != 0) goto L3f
            java.lang.String r6 = "deleteLauncher"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r6 = 0
        L3f:
            expo.modules.medialibrary.next.permissions.contracts.DeleteContractInput r2 = new expo.modules.medialibrary.next.permissions.contracts.DeleteContractInput
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r5 = kotlin.collections.CollectionsKt.toList(r5)
            r2.<init>(r5)
            java.io.Serializable r2 = (java.io.Serializable) r2
            r0.label = r3
            java.lang.Object r6 = r6.launch(r2, r0)
            if (r6 != r1) goto L55
            return r1
        L55:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r5 = r6.booleanValue()
            if (r5 == 0) goto L60
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L60:
            expo.modules.medialibrary.PermissionsException r5 = new expo.modules.medialibrary.PermissionsException
            java.lang.String r6 = "User didn't grant write permission to requested files."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate.launchMediaStoreDeleteRequest(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object requestMediaLibraryWritePermission(java.lang.Iterable<? extends android.net.Uri> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$requestMediaLibraryWritePermission$1
            if (r0 == 0) goto L14
            r0 = r7
            expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$requestMediaLibraryWritePermission$1 r0 = (expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$requestMediaLibraryWritePermission$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$requestMediaLibraryWritePermission$1 r0 = new expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$requestMediaLibraryWritePermission$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L85
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            kotlin.ResultKt.throwOnFailure(r7)
            int r7 = android.os.Build.VERSION.SDK_INT
            r2 = 30
            if (r7 >= r2) goto L3e
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L3e:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Collection r7 = (java.util.Collection) r7
            java.util.Iterator r6 = r6.iterator()
        L49:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L60
            java.lang.Object r2 = r6.next()
            r4 = r2
            android.net.Uri r4 = (android.net.Uri) r4
            boolean r4 = r5.hasWritePermissionForUri(r4)
            if (r4 != 0) goto L49
            r7.add(r2)
            goto L49
        L60:
            java.util.List r7 = (java.util.List) r7
            boolean r6 = r7.isEmpty()
            if (r6 == 0) goto L6b
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L6b:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher<expo.modules.medialibrary.next.permissions.contracts.WriteContractInput, java.lang.Boolean> r6 = r5.writeLauncher
            if (r6 != 0) goto L75
            java.lang.String r6 = "writeLauncher"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r6 = 0
        L75:
            expo.modules.medialibrary.next.permissions.contracts.WriteContractInput r2 = new expo.modules.medialibrary.next.permissions.contracts.WriteContractInput
            r2.<init>(r7)
            java.io.Serializable r2 = (java.io.Serializable) r2
            r0.label = r3
            java.lang.Object r7 = r6.launch(r2, r0)
            if (r7 != r1) goto L85
            return r1
        L85:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r6 = r7.booleanValue()
            if (r6 == 0) goto L90
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L90:
            expo.modules.medialibrary.PermissionsException r6 = new expo.modules.medialibrary.PermissionsException
            java.lang.String r7 = "User didn't grant write permission to requested files."
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate.requestMediaLibraryWritePermission(java.lang.Iterable, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object registerMediaStoreContracts(expo.modules.kotlin.activityresult.AppContextActivityResultCaller r9, expo.modules.kotlin.providers.AppContextProvider r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$registerMediaStoreContracts$1
            if (r0 == 0) goto L14
            r0 = r11
            expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$registerMediaStoreContracts$1 r0 = (expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$registerMediaStoreContracts$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$registerMediaStoreContracts$1 r0 = new expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate$registerMediaStoreContracts$1
            r0.<init>(r8, r11)
        L19:
            r4 = r0
            java.lang.Object r11 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 2
            r2 = 1
            if (r1 == 0) goto L4a
            if (r1 == r2) goto L3a
            if (r1 != r7) goto L32
            java.lang.Object r9 = r4.L$0
            expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate r9 = (expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L89
        L32:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3a:
            java.lang.Object r9 = r4.L$2
            expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate r9 = (expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate) r9
            java.lang.Object r10 = r4.L$1
            expo.modules.kotlin.providers.AppContextProvider r10 = (expo.modules.kotlin.providers.AppContextProvider) r10
            java.lang.Object r1 = r4.L$0
            expo.modules.kotlin.activityresult.AppContextActivityResultCaller r1 = (expo.modules.kotlin.activityresult.AppContextActivityResultCaller) r1
            kotlin.ResultKt.throwOnFailure(r11)
            goto L69
        L4a:
            kotlin.ResultKt.throwOnFailure(r11)
            expo.modules.medialibrary.next.permissions.contracts.DeleteContract r11 = new expo.modules.medialibrary.next.permissions.contracts.DeleteContract
            r11.<init>(r10)
            expo.modules.kotlin.activityresult.AppContextActivityResultContract r11 = (expo.modules.kotlin.activityresult.AppContextActivityResultContract) r11
            r4.L$0 = r9
            r4.L$1 = r10
            r4.L$2 = r8
            r4.label = r2
            r3 = 0
            r5 = 2
            r6 = 0
            r1 = r9
            r2 = r11
            java.lang.Object r11 = expo.modules.kotlin.activityresult.AppContextActivityResultCaller.DefaultImpls.registerForActivityResult$default(r1, r2, r3, r4, r5, r6)
            if (r11 != r0) goto L68
            goto L87
        L68:
            r9 = r8
        L69:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher r11 = (expo.modules.kotlin.activityresult.AppContextActivityResultLauncher) r11
            r9.deleteLauncher = r11
            expo.modules.medialibrary.next.permissions.contracts.WriteContract r9 = new expo.modules.medialibrary.next.permissions.contracts.WriteContract
            r9.<init>(r10)
            r2 = r9
            expo.modules.kotlin.activityresult.AppContextActivityResultContract r2 = (expo.modules.kotlin.activityresult.AppContextActivityResultContract) r2
            r4.L$0 = r8
            r9 = 0
            r4.L$1 = r9
            r4.L$2 = r9
            r4.label = r7
            r3 = 0
            r5 = 2
            r6 = 0
            java.lang.Object r11 = expo.modules.kotlin.activityresult.AppContextActivityResultCaller.DefaultImpls.registerForActivityResult$default(r1, r2, r3, r4, r5, r6)
            if (r11 != r0) goto L88
        L87:
            return r0
        L88:
            r9 = r8
        L89:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher r11 = (expo.modules.kotlin.activityresult.AppContextActivityResultLauncher) r11
            r9.writeLauncher = r11
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.medialibrary.next.permissions.MediaStorePermissionsDelegate.registerMediaStoreContracts(expo.modules.kotlin.activityresult.AppContextActivityResultCaller, expo.modules.kotlin.providers.AppContextProvider, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean hasWritePermissionForUri(Uri uri) {
        try {
            Result.Companion companion = Result.INSTANCE;
            MediaStorePermissionsDelegate mediaStorePermissionsDelegate = this;
            OutputStream openOutputStream = getContext().getContentResolver().openOutputStream(uri, "rw");
            if (openOutputStream == null) {
                return true;
            }
            openOutputStream.close();
            return true;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Object m1409constructorimpl = Result.m1409constructorimpl(ResultKt.createFailure(th));
            if (Result.m1415isFailureimpl(m1409constructorimpl)) {
                m1409constructorimpl = false;
            }
            return ((Boolean) m1409constructorimpl).booleanValue();
        }
    }
}
