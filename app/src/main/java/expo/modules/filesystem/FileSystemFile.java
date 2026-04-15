package expo.modules.filesystem;

import android.net.Uri;
import android.util.Base64;
import com.facebook.common.util.UriUtil;
import expo.modules.filesystem.unifiedfile.UnifiedFileInterface;
import expo.modules.interfaces.filesystem.Permission;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.typedarray.TypedArray;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.HexExtensionsKt;
import kotlin.text.HexFormat;
import kotlin.text.StringsKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: FileSystemFile.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0013J\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0012J\u0006\u0010\u0016\u001a\u00020\u0012J\u0006\u0010\u0017\u001a\u00020\u0012J\u0006\u0010\u0018\u001a\u00020\u0014J\u0006\u0010\u0019\u001a\u00020\u0003J\u0010\u0010%\u001a\u00020&2\b\u0010\u000e\u001a\u0004\u0018\u00010'R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u001a\u001a\u00020\u00128F¢\u0006\f\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u001f\u001a\u0004\u0018\u00010 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0013\u0010#\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b$\u0010\u001e¨\u0006("}, d2 = {"Lexpo/modules/filesystem/FileSystemFile;", "Lexpo/modules/filesystem/FileSystemPath;", "uri", "Landroid/net/Uri;", "<init>", "(Landroid/net/Uri;)V", "validatePath", "", "validateType", "exists", "", "getExists", "()Z", "create", "options", "Lexpo/modules/filesystem/CreateOptions;", "write", UriUtil.LOCAL_CONTENT_SCHEME, "", "Lexpo/modules/kotlin/typedarray/TypedArray;", "", "asString", "text", "base64", "bytes", "asContentUri", "md5", "getMd5$annotations", "()V", "getMd5", "()Ljava/lang/String;", "size", "", "getSize", "()Ljava/lang/Long;", "type", "getType", "info", "Lexpo/modules/filesystem/FileInfo;", "Lexpo/modules/filesystem/InfoOptions;", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FileSystemFile extends FileSystemPath {
    public static /* synthetic */ void getMd5$annotations() {
    }

    public final void validatePath() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileSystemFile(Uri uri) {
        super(uri);
        Intrinsics.checkNotNullParameter(uri, "uri");
    }

    @Override // expo.modules.filesystem.FileSystemPath
    public void validateType() {
        validatePermission(Permission.READ);
        if (getFile().exists() && getFile().isDirectory()) {
            throw new InvalidTypeFileException();
        }
    }

    public final boolean getExists() {
        if (checkPermission(Permission.READ)) {
            return getFile().isFile();
        }
        return false;
    }

    public static /* synthetic */ void create$default(FileSystemFile fileSystemFile, CreateOptions createOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            createOptions = new CreateOptions(false, false, false, 7, null);
        }
        fileSystemFile.create(createOptions);
    }

    public final void create(CreateOptions options) {
        File parentFile;
        Intrinsics.checkNotNullParameter(options, "options");
        validateType();
        validatePermission(Permission.WRITE);
        validateCanCreate(options);
        if (FileSystemPathKt.isContentUri(getUri())) {
            throw new UnableToCreateException("create function does not work with SAF Uris, use `createDirectory` and `createFile` instead");
        }
        if (options.getOverwrite() && getExists()) {
            getJavaFile().delete();
        }
        if (options.getIntermediates() && (parentFile = getJavaFile().getParentFile()) != null) {
            parentFile.mkdirs();
        }
        if (!getJavaFile().createNewFile()) {
            throw new UnableToCreateException("file already exists or could not be created");
        }
    }

    public final void write(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        validateType();
        validatePermission(Permission.WRITE);
        if (!getExists()) {
            create$default(this, null, 1, null);
        }
        OutputStream outputStream = getFile().outputStream();
        try {
            byte[] bytes = content.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            outputStream.write(bytes);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStream, null);
        } finally {
        }
    }

    public final void write(TypedArray content) {
        FileOutputStream fileOutputStream;
        Intrinsics.checkNotNullParameter(content, "content");
        validateType();
        validatePermission(Permission.WRITE);
        if (!getExists()) {
            create$default(this, null, 1, null);
        }
        if (FileSystemPathKt.isContentUri(getUri())) {
            fileOutputStream = getFile().outputStream();
            try {
                byte[] bArr = new byte[content.getLength()];
                content.toDirectBuffer().get(bArr);
                fileOutputStream.write(bArr);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
            } finally {
            }
        } else {
            fileOutputStream = new FileOutputStream(getJavaFile());
            try {
                Integer.valueOf(fileOutputStream.getChannel().write(content.toDirectBuffer()));
                CloseableKt.closeFinally(fileOutputStream, null);
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        }
    }

    public final void write(byte[] content) {
        FileOutputStream fileOutputStream;
        Intrinsics.checkNotNullParameter(content, "content");
        validateType();
        validatePermission(Permission.WRITE);
        if (!getExists()) {
            create$default(this, null, 1, null);
        }
        if (FileSystemPathKt.isContentUri(getUri())) {
            fileOutputStream = getFile().outputStream();
            try {
                fileOutputStream.write(content);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
            } finally {
            }
        } else {
            fileOutputStream = new FileOutputStream(getJavaFile());
            try {
                fileOutputStream.write(content);
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        }
    }

    public final String asString() {
        String uri = getFile().getUri().toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        return StringsKt.endsWith$default(uri, "/", false, 2, (Object) null) ? StringsKt.dropLast(uri, 1) : uri;
    }

    public final String text() {
        validateType();
        validatePermission(Permission.READ);
        BufferedReader inputStream = getFile().inputStream();
        try {
            Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            inputStream = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String readText = TextStreamsKt.readText(inputStream);
                CloseableKt.closeFinally(inputStream, null);
                CloseableKt.closeFinally(inputStream, null);
                return readText;
            } finally {
            }
        } finally {
        }
    }

    public final String base64() {
        validateType();
        validatePermission(Permission.READ);
        InputStream inputStream = getFile().inputStream();
        try {
            String encodeToString = Base64.encodeToString(ByteStreamsKt.readBytes(inputStream), 2);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(...)");
            CloseableKt.closeFinally(inputStream, null);
            return encodeToString;
        } finally {
        }
    }

    public final byte[] bytes() {
        validateType();
        validatePermission(Permission.READ);
        InputStream inputStream = getFile().inputStream();
        try {
            byte[] readBytes = ByteStreamsKt.readBytes(inputStream);
            CloseableKt.closeFinally(inputStream, null);
            return readBytes;
        } finally {
        }
    }

    public final Uri asContentUri() {
        validateType();
        validatePermission(Permission.READ);
        UnifiedFileInterface file = getFile();
        AppContext appContext = getAppContext();
        if (appContext != null) {
            return file.getContentUri(appContext);
        }
        throw new MissingAppContextException();
    }

    public final String getMd5() {
        validatePermission(Permission.READ);
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        InputStream inputStream = getFile().inputStream();
        try {
            byte[] digest = messageDigest.digest(ByteStreamsKt.readBytes(inputStream));
            Intrinsics.checkNotNull(digest);
            String hexString$default = HexExtensionsKt.toHexString$default(digest, (HexFormat) null, 1, (Object) null);
            CloseableKt.closeFinally(inputStream, null);
            return hexString$default;
        } finally {
        }
    }

    public final Long getSize() {
        if (getFile().exists()) {
            return Long.valueOf(getFile().length());
        }
        return null;
    }

    public final String getType() {
        return getFile().getType();
    }

    public final FileInfo info(InfoOptions options) {
        validateType();
        validatePermission(Permission.READ);
        if (!getFile().exists()) {
            return new FileInfo(false, FileSystemPathKt.slashifyFilePath(getFile().getUri().toString()), null, null, null, null, 60, null);
        }
        FileInfo fileInfo = new FileInfo(true, FileSystemPathKt.slashifyFilePath(getFile().getUri().toString()), null, getSize(), getModificationTime(), getCreationTime(), 4, null);
        if (options != null && Intrinsics.areEqual((Object) options.getMd5(), (Object) true)) {
            fileInfo.setMd5(getMd5());
        }
        return fileInfo;
    }
}
