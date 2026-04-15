package expo.modules.filesystem.unifiedfile;

import android.content.Context;
import android.net.Uri;
import androidx.documentfile.provider.DocumentFile;
import expo.modules.kotlin.AppContext;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: SAFDocumentFile.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u001a\u001a\u00020\u000fH\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u001dH\u0016J\u000f\u0010!\u001a\u0004\u0018\u00010\"H\u0016¢\u0006\u0002\u0010#J\u0010\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020\"H\u0016J\u000e\u00100\u001a\b\u0012\u0004\u0012\u00020\u000001H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0016\u0010$\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010 R\u0016\u0010)\u001a\u0004\u0018\u00010\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010#¨\u00062"}, d2 = {"Lexpo/modules/filesystem/unifiedfile/SAFDocumentFile;", "Lexpo/modules/filesystem/unifiedfile/UnifiedFileInterface;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "<init>", "(Landroid/content/Context;Landroid/net/Uri;)V", "getUri", "()Landroid/net/Uri;", "documentFile", "Landroidx/documentfile/provider/DocumentFile;", "getDocumentFile", "()Landroidx/documentfile/provider/DocumentFile;", "exists", "", "isDirectory", "isFile", "parentFile", "getParentFile", "()Lexpo/modules/filesystem/unifiedfile/UnifiedFileInterface;", "createFile", "mimeType", "", "displayName", "createDirectory", "delete", "deleteRecursively", "listFilesAsUnified", "", "type", "getType", "()Ljava/lang/String;", "lastModified", "", "()Ljava/lang/Long;", "fileName", "getFileName", "getContentUri", "appContext", "Lexpo/modules/kotlin/AppContext;", "creationTime", "getCreationTime", "outputStream", "Ljava/io/OutputStream;", "inputStream", "Ljava/io/InputStream;", "length", "walkTopDown", "Lkotlin/sequences/Sequence;", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SAFDocumentFile implements UnifiedFileInterface {
    private final Context context;
    private final Uri uri;

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Long getCreationTime() {
        return null;
    }

    public SAFDocumentFile(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.context = context;
        this.uri = uri;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Uri getUri() {
        return this.uri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DocumentFile getDocumentFile() {
        List<String> pathSegments = getUri().getPathSegments();
        Intrinsics.checkNotNullExpressionValue(pathSegments, "getPathSegments(...)");
        String str = (String) CollectionsKt.getOrNull(pathSegments, 0);
        if (str == null) {
            str = "tree";
        }
        if (Intrinsics.areEqual(str, "document")) {
            return DocumentFile.fromSingleUri(this.context, getUri());
        }
        return DocumentFile.fromTreeUri(this.context, getUri());
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean exists() {
        DocumentFile documentFile = getDocumentFile();
        return documentFile != null && documentFile.exists();
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean isDirectory() {
        DocumentFile documentFile = getDocumentFile();
        return documentFile != null && documentFile.isDirectory();
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean isFile() {
        DocumentFile documentFile = getDocumentFile();
        return documentFile != null && documentFile.isFile();
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public UnifiedFileInterface getParentFile() {
        DocumentFile parentFile;
        Uri uri;
        DocumentFile documentFile = getDocumentFile();
        return (documentFile == null || (parentFile = documentFile.getParentFile()) == null || (uri = parentFile.getUri()) == null) ? null : new SAFDocumentFile(this.context, uri);
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public UnifiedFileInterface createFile(String mimeType, String displayName) {
        Uri uri;
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        DocumentFile documentFile = getDocumentFile();
        SAFDocumentFile sAFDocumentFile = null;
        DocumentFile createFile = documentFile != null ? documentFile.createFile(mimeType, displayName) : null;
        if (createFile != null && (uri = createFile.getUri()) != null) {
            sAFDocumentFile = new SAFDocumentFile(this.context, uri);
        }
        return sAFDocumentFile;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public UnifiedFileInterface createDirectory(String displayName) {
        Uri uri;
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        DocumentFile documentFile = getDocumentFile();
        SAFDocumentFile sAFDocumentFile = null;
        DocumentFile createDirectory = documentFile != null ? documentFile.createDirectory(displayName) : null;
        if (createDirectory != null && (uri = createDirectory.getUri()) != null) {
            sAFDocumentFile = new SAFDocumentFile(this.context, uri);
        }
        return sAFDocumentFile;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean delete() {
        DocumentFile documentFile = getDocumentFile();
        return documentFile != null && documentFile.delete();
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean deleteRecursively() {
        DocumentFile documentFile = getDocumentFile();
        return documentFile != null && SAFDocumentFileKt.deleteRecursively(documentFile);
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public List<UnifiedFileInterface> listFilesAsUnified() {
        DocumentFile[] listFiles;
        DocumentFile documentFile = getDocumentFile();
        if (documentFile == null || (listFiles = documentFile.listFiles()) == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(listFiles.length);
        for (DocumentFile documentFile2 : listFiles) {
            Context context = this.context;
            Uri uri = documentFile2.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            arrayList.add(new SAFDocumentFile(context, uri));
        }
        return arrayList;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public String getType() {
        DocumentFile documentFile = getDocumentFile();
        if (documentFile != null) {
            return documentFile.getType();
        }
        return null;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Long lastModified() {
        DocumentFile documentFile = getDocumentFile();
        if (documentFile != null) {
            return Long.valueOf(documentFile.lastModified());
        }
        return null;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public String getFileName() {
        DocumentFile documentFile = getDocumentFile();
        if (documentFile != null) {
            return documentFile.getName();
        }
        return null;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Uri getContentUri(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        return getUri();
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public OutputStream outputStream() {
        OutputStream openOutputStream = this.context.getContentResolver().openOutputStream(getUri());
        if (openOutputStream != null) {
            return openOutputStream;
        }
        throw new IllegalStateException("Unable to open output stream for URI: " + getUri());
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public InputStream inputStream() {
        InputStream openInputStream = this.context.getContentResolver().openInputStream(getUri());
        if (openInputStream != null) {
            return openInputStream;
        }
        throw new IllegalStateException("Unable to open output stream for URI: " + getUri());
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public long length() {
        DocumentFile documentFile = getDocumentFile();
        if (documentFile != null) {
            return documentFile.length();
        }
        return 0L;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Sequence<SAFDocumentFile> walkTopDown() {
        return SequencesKt.sequence(new SAFDocumentFile$walkTopDown$1(this, null));
    }
}
