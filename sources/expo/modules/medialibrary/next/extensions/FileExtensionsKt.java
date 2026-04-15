package expo.modules.medialibrary.next.extensions;

import expo.modules.medialibrary.MediaLibraryUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileExtensions.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0018\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"safeMove", "Ljava/io/File;", "destinationDirectory", "safeCopy", "createUniqueFileIn", "directory", "newFileName", "", "expo-media-library_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FileExtensionsKt {
    public static final File safeMove(File file, File destinationDirectory) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(destinationDirectory, "destinationDirectory");
        File safeCopy = safeCopy(file, destinationDirectory);
        file.delete();
        return safeCopy;
    }

    public static final File safeCopy(File file, File destinationDirectory) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(destinationDirectory, "destinationDirectory");
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        File createUniqueFileIn = createUniqueFileIn(destinationDirectory, name);
        FileChannel channel = new FileInputStream(file).getChannel();
        try {
            FileChannel fileChannel = channel;
            channel = new FileOutputStream(createUniqueFileIn).getChannel();
            try {
                if (fileChannel.transferTo(0L, fileChannel.size(), channel) != fileChannel.size()) {
                    createUniqueFileIn.delete();
                    throw new IOException("Could not save file to " + destinationDirectory + " Not enough space.");
                }
                CloseableKt.closeFinally(channel, null);
                CloseableKt.closeFinally(channel, null);
                return createUniqueFileIn;
            } finally {
            }
        } finally {
        }
    }

    private static final File createUniqueFileIn(File file, String str) {
        File file2 = new File(file, str);
        Pair<String, String> fileNameAndExtension = MediaLibraryUtils.INSTANCE.getFileNameAndExtension(str);
        String component1 = fileNameAndExtension.component1();
        String component2 = fileNameAndExtension.component2();
        int i = 2;
        while (file2.exists()) {
            file2 = new File(file, component1 + "_" + i + component2);
            i++;
            if (i > 32767) {
                throw new IOException("File name suffix limit reached (32767)");
            }
        }
        return file2;
    }
}
