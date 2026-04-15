package expo.modules.filesystem.unifiedfile;

import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import expo.modules.kotlin.AppContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;

/* compiled from: AssetFile.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000bH\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001d\u001a\u00020\u000bH\u0016J\b\u0010\u001f\u001a\u00020\u000fH\u0016J\b\u0010 \u001a\u00020\u000fH\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\"H\u0016J\u000f\u0010%\u001a\u0004\u0018\u00010&H\u0016¢\u0006\u0002\u0010'J\b\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020&H\u0016J\u000e\u00101\u001a\b\u0012\u0004\u0012\u00020\u000002H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010#\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\rR\u0016\u0010(\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\rR\u0016\u0010*\u001a\u0004\u0018\u00010&8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010'¨\u00063"}, d2 = {"Lexpo/modules/filesystem/unifiedfile/AssetFile;", "Lexpo/modules/filesystem/unifiedfile/UnifiedFileInterface;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "<init>", "(Landroid/content/Context;Landroid/net/Uri;)V", "getUri", "()Landroid/net/Uri;", "path", "", "getPath", "()Ljava/lang/String;", "exists", "", "isDirectory", "isFile", "contentUri", "getContentUri", "setContentUri", "(Landroid/net/Uri;)V", "appContext", "Lexpo/modules/kotlin/AppContext;", "parentFile", "getParentFile", "()Lexpo/modules/filesystem/unifiedfile/UnifiedFileInterface;", "createFile", "mimeType", "displayName", "createDirectory", "delete", "deleteRecursively", "listFilesAsUnified", "", "type", "getType", "lastModified", "", "()Ljava/lang/Long;", "fileName", "getFileName", "creationTime", "getCreationTime", "outputStream", "Ljava/io/OutputStream;", "inputStream", "Ljava/io/InputStream;", "length", "walkTopDown", "Lkotlin/sequences/Sequence;", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetFile implements UnifiedFileInterface {
    private Uri contentUri;
    private final Context context;
    private final String path;
    private final Uri uri;

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Long getCreationTime() {
        return null;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Long lastModified() {
        return null;
    }

    public AssetFile(Context context, Uri uri) {
        String trimStart;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.context = context;
        this.uri = uri;
        String path = getUri().getPath();
        if (path == null || (trimStart = StringsKt.trimStart(path, IOUtils.DIR_SEPARATOR_UNIX)) == null) {
            throw new IllegalArgumentException("Invalid asset URI: " + getUri());
        }
        this.path = trimStart;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Uri getUri() {
        return this.uri;
    }

    public final String getPath() {
        return this.path;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean exists() {
        return isDirectory() || isFile();
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean isDirectory() {
        String[] list = this.context.getAssets().list(this.path);
        if (list != null) {
            if (!(list.length == 0)) {
                return true;
            }
        }
        return false;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean isFile() {
        Object m1409constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            AssetFile assetFile = this;
            InputStream open = this.context.getAssets().open(this.path);
            try {
                InputStream inputStream = open;
                CloseableKt.closeFinally(open, null);
                m1409constructorimpl = Result.m1409constructorimpl(true);
            } finally {
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1409constructorimpl = Result.m1409constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1412exceptionOrNullimpl(m1409constructorimpl) != null) {
            m1409constructorimpl = false;
        }
        return ((Boolean) m1409constructorimpl).booleanValue();
    }

    public final Uri getContentUri() {
        return this.contentUri;
    }

    public final void setContentUri(Uri uri) {
        this.contentUri = uri;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Uri getContentUri(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        FileOutputStream inputStream = inputStream();
        try {
            InputStream inputStream2 = inputStream;
            File file = new File(this.context.getCacheDir(), "expo_shared_assets/" + getFileName());
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            inputStream = new FileOutputStream(file);
            try {
                ByteStreamsKt.copyTo$default(inputStream2, inputStream, 0, 2, null);
                CloseableKt.closeFinally(inputStream, null);
                Uri contentUri = new JavaFile(Uri.fromFile(file)).getContentUri(appContext);
                this.contentUri = contentUri;
                CloseableKt.closeFinally(inputStream, null);
                return contentUri;
            } finally {
            }
        } finally {
        }
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public UnifiedFileInterface getParentFile() {
        String path = getUri().getPath();
        if (path == null) {
            path = "";
        }
        if (path.length() == 0) {
            return null;
        }
        return new AssetFile(this.context, Uri.parse("asset://" + StringsKt.substringBeforeLast$default(path, IOUtils.DIR_SEPARATOR_UNIX, (String) null, 2, (Object) null)));
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public UnifiedFileInterface createFile(String mimeType, String displayName) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        throw new UnsupportedOperationException("Asset files are not writable and cannot be created");
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public UnifiedFileInterface createDirectory(String displayName) {
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        throw new UnsupportedOperationException("Asset directories are not writable and cannot be created");
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean delete() {
        throw new UnsupportedOperationException("Asset files are not writable and cannot be deleted");
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public boolean deleteRecursively() {
        throw new UnsupportedOperationException("Asset files are not writable and cannot be deleted");
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public List<UnifiedFileInterface> listFilesAsUnified() {
        String[] list = this.context.getAssets().list(this.path);
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.length);
        for (String str : list) {
            arrayList.add(new AssetFile(this.context, Uri.fromFile(new File(this.path, str))));
        }
        return arrayList;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public String getType() {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(getUri().toString());
        Intrinsics.checkNotNull(fileExtensionFromUrl);
        if (fileExtensionFromUrl.length() <= 0) {
            return null;
        }
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        String lowerCase = fileExtensionFromUrl.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return singleton.getMimeTypeFromExtension(lowerCase);
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public String getFileName() {
        return getUri().getLastPathSegment();
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public OutputStream outputStream() {
        throw new UnsupportedOperationException("Asset files are not writable");
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public InputStream inputStream() {
        InputStream open = this.context.getAssets().open(this.path);
        Intrinsics.checkNotNullExpressionValue(open, "open(...)");
        return open;
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public long length() {
        InputStream open;
        long length;
        try {
            Result.Companion companion = Result.INSTANCE;
            AssetFile assetFile = this;
            open = this.context.getAssets().openFd(this.path);
            try {
                length = open.getLength();
            } finally {
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m1409constructorimpl(ResultKt.createFailure(th));
        }
        if (length > 0) {
            CloseableKt.closeFinally(open, null);
            return length;
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(open, null);
        Result.m1409constructorimpl(Unit.INSTANCE);
        try {
            Result.Companion companion3 = Result.INSTANCE;
            AssetFile assetFile2 = this;
            open = this.context.getAssets().open(this.path);
            try {
                InputStream inputStream = open;
                byte[] bArr = new byte[8192];
                long j = 0;
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(open, null);
                        return j;
                    }
                    j += read;
                }
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m1409constructorimpl(ResultKt.createFailure(th2));
            return 0L;
        }
    }

    @Override // expo.modules.filesystem.unifiedfile.UnifiedFileInterface
    public Sequence<AssetFile> walkTopDown() {
        return SequencesKt.sequence(new AssetFile$walkTopDown$1(this, null));
    }
}
