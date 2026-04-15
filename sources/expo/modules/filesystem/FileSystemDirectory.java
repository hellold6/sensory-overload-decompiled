package expo.modules.filesystem;

import android.net.Uri;
import coil3.network.internal.UtilsKt;
import expo.modules.filesystem.unifiedfile.UnifiedFileInterface;
import expo.modules.interfaces.filesystem.Permission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* compiled from: FileSystemDirectory.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019J\u0018\u0010\u001c\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001f0\u001e0\u001dJ\u0006\u0010 \u001a\u00020\u0019J\u000e\u0010!\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\""}, d2 = {"Lexpo/modules/filesystem/FileSystemDirectory;", "Lexpo/modules/filesystem/FileSystemPath;", "uri", "Landroid/net/Uri;", "<init>", "(Landroid/net/Uri;)V", "validatePath", "", "validateType", "exists", "", "getExists", "()Z", "size", "", "getSize", "()J", "info", "Lexpo/modules/filesystem/DirectoryInfo;", "create", "options", "Lexpo/modules/filesystem/CreateOptions;", "createFile", "Lexpo/modules/filesystem/FileSystemFile;", "mimeType", "", "fileName", "createDirectory", "listAsRecords", "", "", "", "asString", "needsCreation", "expo-file-system_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FileSystemDirectory extends FileSystemPath {
    public final void validatePath() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileSystemDirectory(Uri uri) {
        super(uri);
        Intrinsics.checkNotNullParameter(uri, "uri");
    }

    @Override // expo.modules.filesystem.FileSystemPath
    public void validateType() {
        if (getFile().exists() && !getFile().isDirectory()) {
            throw new InvalidTypeFolderException();
        }
    }

    public final boolean getExists() {
        if (checkPermission(Permission.READ)) {
            return getFile().isDirectory();
        }
        return false;
    }

    public final long getSize() {
        validatePermission(Permission.READ);
        validateType();
        return SequencesKt.sumOfLong(SequencesKt.map(SequencesKt.filter(getFile().walkTopDown(), new Function1() { // from class: expo.modules.filesystem.FileSystemDirectory$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean _get_size_$lambda$0;
                _get_size_$lambda$0 = FileSystemDirectory._get_size_$lambda$0((UnifiedFileInterface) obj);
                return Boolean.valueOf(_get_size_$lambda$0);
            }
        }), new Function1() { // from class: expo.modules.filesystem.FileSystemDirectory$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long _get_size_$lambda$1;
                _get_size_$lambda$1 = FileSystemDirectory._get_size_$lambda$1((UnifiedFileInterface) obj);
                return Long.valueOf(_get_size_$lambda$1);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _get_size_$lambda$0(UnifiedFileInterface it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.isFile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long _get_size_$lambda$1(UnifiedFileInterface it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.length();
    }

    public final DirectoryInfo info() {
        validateType();
        validatePermission(Permission.READ);
        if (!getFile().exists()) {
            return new DirectoryInfo(false, FileSystemPathKt.slashifyFilePath(getFile().getUri().toString()), null, null, null, null, null, 124, null);
        }
        String slashifyFilePath = FileSystemPathKt.slashifyFilePath(getFile().getUri().toString());
        List<UnifiedFileInterface> listFilesAsUnified = getFile().listFilesAsUnified();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listFilesAsUnified.iterator();
        while (it.hasNext()) {
            String fileName = ((UnifiedFileInterface) it.next()).getFileName();
            if (fileName != null) {
                arrayList.add(fileName);
            }
        }
        return new DirectoryInfo(true, slashifyFilePath, arrayList, null, Long.valueOf(getSize()), getModificationTime(), getCreationTime(), 8, null);
    }

    public static /* synthetic */ void create$default(FileSystemDirectory fileSystemDirectory, CreateOptions createOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            createOptions = new CreateOptions(false, false, false, 7, null);
        }
        fileSystemDirectory.create(createOptions);
    }

    public final void create(CreateOptions options) {
        boolean mkdir;
        Intrinsics.checkNotNullParameter(options, "options");
        validateType();
        validatePermission(Permission.WRITE);
        if (needsCreation(options)) {
            if (FileSystemPathKt.isContentUri(getUri())) {
                throw new UnableToCreateException("create function does not work with SAF Uris, use `createDirectory` and `createFile` instead");
            }
            validateCanCreate(options);
            if (options.getOverwrite() && getFile().exists()) {
                getFile().delete();
            }
            if (options.getIntermediates()) {
                mkdir = getJavaFile().mkdirs();
            } else {
                mkdir = getJavaFile().mkdir();
            }
            if (!mkdir) {
                throw new UnableToCreateException("directory already exists or could not be created");
            }
        }
    }

    public final FileSystemFile createFile(String mimeType, String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        validateType();
        validatePermission(Permission.WRITE);
        UnifiedFileInterface file = getFile();
        if (mimeType == null) {
            mimeType = UtilsKt.MIME_TYPE_TEXT_PLAIN;
        }
        UnifiedFileInterface createFile = file.createFile(mimeType, fileName);
        if (createFile == null) {
            throw new UnableToCreateException("file could not be created");
        }
        return new FileSystemFile(createFile.getUri());
    }

    public final FileSystemDirectory createDirectory(String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        validateType();
        validatePermission(Permission.WRITE);
        UnifiedFileInterface createDirectory = getFile().createDirectory(fileName);
        if (createDirectory == null) {
            throw new UnableToCreateException("directory could not be created");
        }
        return new FileSystemDirectory(createDirectory.getUri());
    }

    public final List<Map<String, Object>> listAsRecords() {
        validateType();
        validatePermission(Permission.READ);
        List<UnifiedFileInterface> listFilesAsUnified = getFile().listFilesAsUnified();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFilesAsUnified, 10));
        for (UnifiedFileInterface unifiedFileInterface : listFilesAsUnified) {
            String uri = unifiedFileInterface.getUri().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            Pair[] pairArr = new Pair[2];
            pairArr[0] = TuplesKt.to("isDirectory", Boolean.valueOf(unifiedFileInterface.isDirectory()));
            if (!StringsKt.endsWith$default(uri, "/", false, 2, (Object) null)) {
                uri = uri + "/";
            }
            pairArr[1] = TuplesKt.to("uri", uri);
            arrayList.add(MapsKt.mapOf(pairArr));
        }
        return arrayList;
    }

    public final String asString() {
        String uri = getFile().getUri().toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        return StringsKt.endsWith$default(uri, "/", false, 2, (Object) null) ? uri : uri + "/";
    }

    public final boolean needsCreation(CreateOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return (getFile().exists() && options.getIdempotent()) ? false : true;
    }
}
