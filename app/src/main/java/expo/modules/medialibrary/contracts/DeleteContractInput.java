package expo.modules.medialibrary.contracts;

import android.net.Uri;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeleteContract.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lexpo/modules/medialibrary/contracts/DeleteContractInput;", "Ljava/io/Serializable;", "uris", "", "Landroid/net/Uri;", "<init>", "(Ljava/util/List;)V", "getUris", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DeleteContractInput implements Serializable {
    private final List<Uri> uris;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DeleteContractInput copy$default(DeleteContractInput deleteContractInput, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = deleteContractInput.uris;
        }
        return deleteContractInput.copy(list);
    }

    public final List<Uri> component1() {
        return this.uris;
    }

    public final DeleteContractInput copy(List<? extends Uri> uris) {
        Intrinsics.checkNotNullParameter(uris, "uris");
        return new DeleteContractInput(uris);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DeleteContractInput) && Intrinsics.areEqual(this.uris, ((DeleteContractInput) other).uris);
    }

    public int hashCode() {
        return this.uris.hashCode();
    }

    public String toString() {
        return "DeleteContractInput(uris=" + this.uris + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DeleteContractInput(List<? extends Uri> uris) {
        Intrinsics.checkNotNullParameter(uris, "uris");
        this.uris = uris;
    }

    public final List<Uri> getUris() {
        return this.uris;
    }
}
