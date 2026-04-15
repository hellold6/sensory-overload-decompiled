package com.github.penfeizhou.animation.gif.decode;

import android.content.Context;
import com.github.penfeizhou.animation.gif.io.GifReader;
import com.github.penfeizhou.animation.io.Reader;
import com.github.penfeizhou.animation.io.StreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class GifParser {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class FormatException extends IOException {
        /* JADX INFO: Access modifiers changed from: package-private */
        public FormatException() {
            super("Gif Format error");
        }
    }

    public static boolean isGif(String str) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                boolean isGif = isGif(new StreamReader(fileInputStream2));
                try {
                    fileInputStream2.close();
                    return isGif;
                } catch (IOException e) {
                    e.printStackTrace();
                    return isGif;
                }
            } catch (Exception unused) {
                fileInputStream = fileInputStream2;
                if (fileInputStream == null) {
                    return false;
                }
                try {
                    fileInputStream.close();
                    return false;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return false;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean isGif(Context context, String str) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            boolean isGif = isGif(new StreamReader(inputStream));
            if (inputStream != null) {
                try {
                    inputStream.close();
                    return isGif;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return isGif;
        } catch (Exception unused) {
            if (inputStream == null) {
                return false;
            }
            try {
                inputStream.close();
                return false;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean isGif(Context context, int i) {
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().openRawResource(i);
            boolean isGif = isGif(new StreamReader(inputStream));
            if (inputStream != null) {
                try {
                    inputStream.close();
                    return isGif;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return isGif;
        } catch (Exception unused) {
            if (inputStream == null) {
                return false;
            }
            try {
                inputStream.close();
                return false;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean isGif(Reader reader) {
        try {
            checkHeader(reader instanceof GifReader ? (GifReader) reader : new GifReader(reader));
            return true;
        } catch (IOException e) {
            if (e instanceof FormatException) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
    
        throw new com.github.penfeizhou.animation.gif.decode.GifParser.FormatException();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.github.penfeizhou.animation.gif.decode.Block> parse(com.github.penfeizhou.animation.gif.io.GifReader r3) throws java.io.IOException {
        /*
            checkHeader(r3)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.github.penfeizhou.animation.gif.decode.LogicalScreenDescriptor r1 = new com.github.penfeizhou.animation.gif.decode.LogicalScreenDescriptor
            r1.<init>()
            r1.receive(r3)
            r0.add(r1)
            boolean r2 = r1.gColorTableFlag()
            if (r2 == 0) goto L28
            com.github.penfeizhou.animation.gif.decode.ColorTable r2 = new com.github.penfeizhou.animation.gif.decode.ColorTable
            int r1 = r1.gColorTableSize()
            r2.<init>(r1)
            r2.receive(r3)
            r0.add(r2)
        L28:
            byte r1 = r3.peek()     // Catch: java.lang.Exception -> L54
            r2 = 59
            if (r1 == r2) goto L53
            r2 = 33
            if (r1 == r2) goto L40
            r2 = 44
            if (r1 == r2) goto L3a
            r1 = 0
            goto L44
        L3a:
            com.github.penfeizhou.animation.gif.decode.ImageDescriptor r1 = new com.github.penfeizhou.animation.gif.decode.ImageDescriptor     // Catch: java.lang.Exception -> L54
            r1.<init>()     // Catch: java.lang.Exception -> L54
            goto L44
        L40:
            com.github.penfeizhou.animation.gif.decode.ExtensionBlock r1 = com.github.penfeizhou.animation.gif.decode.ExtensionBlock.retrieve(r3)     // Catch: java.lang.Exception -> L54
        L44:
            if (r1 == 0) goto L4d
            r1.receive(r3)     // Catch: java.lang.Exception -> L54
            r0.add(r1)     // Catch: java.lang.Exception -> L54
            goto L28
        L4d:
            com.github.penfeizhou.animation.gif.decode.GifParser$FormatException r3 = new com.github.penfeizhou.animation.gif.decode.GifParser$FormatException     // Catch: java.lang.Exception -> L54
            r3.<init>()     // Catch: java.lang.Exception -> L54
            throw r3     // Catch: java.lang.Exception -> L54
        L53:
            return r0
        L54:
            r3 = move-exception
            r3.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.penfeizhou.animation.gif.decode.GifParser.parse(com.github.penfeizhou.animation.gif.io.GifReader):java.util.List");
    }

    private static void checkHeader(GifReader gifReader) throws IOException {
        byte peek;
        if (gifReader.peek() != 71 || gifReader.peek() != 73 || gifReader.peek() != 70 || gifReader.peek() != 56 || (((peek = gifReader.peek()) != 55 && peek != 57) || gifReader.peek() != 97)) {
            throw new FormatException();
        }
    }
}
