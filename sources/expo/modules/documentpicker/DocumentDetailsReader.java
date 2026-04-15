package expo.modules.documentpicker;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DocumentDetailsReader.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lexpo/modules/documentpicker/DocumentDetailsReader;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "read", "Lexpo/modules/documentpicker/DocumentInfo;", "uri", "Landroid/net/Uri;", "expo-document-picker_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DocumentDetailsReader {
    private final Context context;

    public DocumentDetailsReader(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final DocumentInfo read(Uri uri) {
        long currentTimeMillis;
        Intrinsics.checkNotNullParameter(uri, "uri");
        Cursor query = this.context.getContentResolver().query(uri, null, null, null, null);
        if (query != null) {
            Cursor cursor = query;
            try {
                Cursor cursor2 = cursor;
                cursor2.moveToFirst();
                String string = cursor2.getString(cursor2.getColumnIndex("_display_name"));
                int columnIndex = cursor2.getColumnIndex("_size");
                Long valueOf = !cursor2.isNull(columnIndex) ? Long.valueOf(cursor2.getLong(columnIndex)) : null;
                String type = this.context.getContentResolver().getType(uri);
                try {
                    int columnIndex2 = cursor2.getColumnIndex("last_modified");
                    if (columnIndex2 != -1 && !cursor2.isNull(columnIndex2)) {
                        currentTimeMillis = cursor2.getLong(columnIndex2);
                    } else {
                        String path = uri.getPath();
                        if (path == null) {
                            path = "";
                        }
                        File file = new File(path);
                        if (file.exists()) {
                            currentTimeMillis = file.lastModified();
                        } else {
                            currentTimeMillis = System.currentTimeMillis();
                        }
                    }
                } catch (Exception unused) {
                    currentTimeMillis = System.currentTimeMillis();
                }
                Intrinsics.checkNotNull(string);
                DocumentInfo documentInfo = new DocumentInfo(uri, string, type, valueOf, currentTimeMillis);
                CloseableKt.closeFinally(cursor, null);
                return documentInfo;
            } finally {
            }
        } else {
            throw new IOException("Failed to read document details for URI: " + uri);
        }
    }
}
