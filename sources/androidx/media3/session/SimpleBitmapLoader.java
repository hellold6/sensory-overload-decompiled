package androidx.media3.session;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.io.ByteStreams;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Deprecated
/* loaded from: classes.dex */
public final class SimpleBitmapLoader implements androidx.media3.common.util.BitmapLoader {
    private static final Supplier<ListeningExecutorService> DEFAULT_EXECUTOR_SERVICE = Suppliers.memoize(new Supplier() { // from class: androidx.media3.session.SimpleBitmapLoader$$ExternalSyntheticLambda1
        @Override // com.google.common.base.Supplier
        public final Object get() {
            ListeningExecutorService listeningDecorator;
            listeningDecorator = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
            return listeningDecorator;
        }
    });
    private static final String FILE_URI_EXCEPTION_MESSAGE = "Could not read image from file";
    private final ListeningExecutorService executorService;

    public SimpleBitmapLoader() {
        this((ExecutorService) Assertions.checkStateNotNull(DEFAULT_EXECUTOR_SERVICE.get()));
    }

    public SimpleBitmapLoader(ExecutorService executorService) {
        this.executorService = MoreExecutors.listeningDecorator(executorService);
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public boolean supportsMimeType(String str) {
        return Util.isBitmapFactorySupportedMimeType(str);
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> decodeBitmap(final byte[] bArr) {
        return this.executorService.submit(new Callable() { // from class: androidx.media3.session.SimpleBitmapLoader$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Bitmap decode;
                decode = SimpleBitmapLoader.decode(bArr);
                return decode;
            }
        });
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> loadBitmap(final Uri uri) {
        return this.executorService.submit(new Callable() { // from class: androidx.media3.session.SimpleBitmapLoader$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Bitmap load;
                load = SimpleBitmapLoader.load(uri);
                return load;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap decode(byte[] bArr) {
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        Assertions.checkArgument(decodeByteArray != null, "Could not decode image data");
        return decodeByteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap load(Uri uri) throws IOException {
        if ("file".equals(uri.getScheme())) {
            String path = uri.getPath();
            if (path == null) {
                throw new IllegalArgumentException(FILE_URI_EXCEPTION_MESSAGE);
            }
            Bitmap decodeFile = BitmapFactory.decodeFile(path);
            if (decodeFile != null) {
                return decodeFile;
            }
            throw new IllegalArgumentException(FILE_URI_EXCEPTION_MESSAGE);
        }
        URLConnection openConnection = new URL(uri.toString()).openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new UnsupportedOperationException("Unsupported scheme: " + uri.getScheme());
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Invalid response status code: " + responseCode);
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        try {
            Bitmap decode = decode(ByteStreams.toByteArray(inputStream));
            if (inputStream != null) {
                inputStream.close();
            }
            return decode;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
