package expo.modules.medialibrary.next.extensions.resolver;

import android.content.ContentResolver;
import android.net.Uri;
import com.google.firebase.messaging.Constants;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TransferExtensions.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"copyUriContent", "", "Landroid/content/ContentResolver;", Constants.MessagePayloadKeys.FROM, "Landroid/net/Uri;", "to", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TransferExtensionsKt {
    public static final void copyUriContent(ContentResolver contentResolver, Uri from, Uri to) {
        Intrinsics.checkNotNullParameter(contentResolver, "<this>");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        OutputStream openInputStream = contentResolver.openInputStream(from);
        try {
            InputStream inputStream = openInputStream;
            openInputStream = contentResolver.openOutputStream(to);
            try {
                OutputStream outputStream = openInputStream;
                if (inputStream != null && outputStream != null) {
                    ByteStreamsKt.copyTo$default(inputStream, outputStream, 0, 2, null);
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(openInputStream, null);
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(openInputStream, null);
            } finally {
            }
        } finally {
        }
    }
}
