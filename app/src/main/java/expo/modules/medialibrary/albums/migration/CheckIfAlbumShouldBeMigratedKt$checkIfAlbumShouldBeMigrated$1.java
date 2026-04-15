package expo.modules.medialibrary.albums.migration;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CheckIfAlbumShouldBeMigrated.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.medialibrary.albums.migration.CheckIfAlbumShouldBeMigratedKt", f = "CheckIfAlbumShouldBeMigrated.kt", i = {}, l = {16}, m = "checkIfAlbumShouldBeMigrated", n = {}, s = {})
/* loaded from: classes3.dex */
public final class CheckIfAlbumShouldBeMigratedKt$checkIfAlbumShouldBeMigrated$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CheckIfAlbumShouldBeMigratedKt$checkIfAlbumShouldBeMigrated$1(Continuation<? super CheckIfAlbumShouldBeMigratedKt$checkIfAlbumShouldBeMigrated$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CheckIfAlbumShouldBeMigratedKt.checkIfAlbumShouldBeMigrated(null, null, this);
    }
}
