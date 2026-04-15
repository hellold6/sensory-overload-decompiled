package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class Fresco {
    private static final Class<?> TAG = Fresco.class;

    @Nullable
    private static PipelineDraweeControllerBuilderSupplier sDraweeControllerBuilderSupplier = null;
    private static volatile boolean sIsInitialized = false;

    private Fresco() {
    }

    public static void initialize(Context context) {
        initialize(context, null, null);
    }

    public static void initialize(Context context, @Nullable ImagePipelineConfig imagePipelineConfig) {
        initialize(context, imagePipelineConfig, null);
    }

    public static void initialize(Context context, @Nullable ImagePipelineConfig imagePipelineConfig, @Nullable DraweeConfig draweeConfig) {
        initialize(context, imagePipelineConfig, draweeConfig, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004d, code lost:
    
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x008b, code lost:
    
        com.facebook.imagepipeline.systrace.FrescoSystrace.endSection();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005c, code lost:
    
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006b, code lost:
    
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0089, code lost:
    
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007a, code lost:
    
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void initialize(android.content.Context r4, @javax.annotation.Nullable com.facebook.imagepipeline.core.ImagePipelineConfig r5, @javax.annotation.Nullable com.facebook.drawee.backends.pipeline.DraweeConfig r6, boolean r7) {
        /*
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r0 == 0) goto Lb
            java.lang.String r0 = "Fresco#initialize"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)
        Lb:
            boolean r0 = com.facebook.drawee.backends.pipeline.Fresco.sIsInitialized
            r1 = 1
            if (r0 == 0) goto L18
            java.lang.Class<?> r0 = com.facebook.drawee.backends.pipeline.Fresco.TAG
            java.lang.String r2 = "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!"
            com.facebook.common.logging.FLog.w(r0, r2)
            goto L1a
        L18:
            com.facebook.drawee.backends.pipeline.Fresco.sIsInitialized = r1
        L1a:
            com.facebook.imagepipeline.core.NativeCodeSetup.setUseNativeCode(r7)
            boolean r7 = com.facebook.soloader.nativeloader.NativeLoader.isInitialized()
            if (r7 != 0) goto L9a
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L2e
            java.lang.String r7 = "Fresco.initialize->SoLoader.init"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r7)
        L2e:
            java.lang.String r7 = "com.facebook.imagepipeline.nativecode.NativeCodeInitializer"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch: java.lang.NoSuchMethodException -> L50 java.lang.reflect.InvocationTargetException -> L5f java.lang.IllegalAccessException -> L6e java.lang.ClassNotFoundException -> L7d java.lang.Throwable -> L8f
            java.lang.String r0 = "init"
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch: java.lang.NoSuchMethodException -> L50 java.lang.reflect.InvocationTargetException -> L5f java.lang.IllegalAccessException -> L6e java.lang.ClassNotFoundException -> L7d java.lang.Throwable -> L8f
            java.lang.Class<android.content.Context> r2 = android.content.Context.class
            r3 = 0
            r1[r3] = r2     // Catch: java.lang.NoSuchMethodException -> L50 java.lang.reflect.InvocationTargetException -> L5f java.lang.IllegalAccessException -> L6e java.lang.ClassNotFoundException -> L7d java.lang.Throwable -> L8f
            java.lang.reflect.Method r7 = r7.getMethod(r0, r1)     // Catch: java.lang.NoSuchMethodException -> L50 java.lang.reflect.InvocationTargetException -> L5f java.lang.IllegalAccessException -> L6e java.lang.ClassNotFoundException -> L7d java.lang.Throwable -> L8f
            java.lang.Object[] r0 = new java.lang.Object[]{r4}     // Catch: java.lang.NoSuchMethodException -> L50 java.lang.reflect.InvocationTargetException -> L5f java.lang.IllegalAccessException -> L6e java.lang.ClassNotFoundException -> L7d java.lang.Throwable -> L8f
            r1 = 0
            r7.invoke(r1, r0)     // Catch: java.lang.NoSuchMethodException -> L50 java.lang.reflect.InvocationTargetException -> L5f java.lang.IllegalAccessException -> L6e java.lang.ClassNotFoundException -> L7d java.lang.Throwable -> L8f
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L9a
            goto L8b
        L50:
            com.facebook.soloader.nativeloader.SystemDelegate r7 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch: java.lang.Throwable -> L8f
            r7.<init>()     // Catch: java.lang.Throwable -> L8f
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r7)     // Catch: java.lang.Throwable -> L8f
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L9a
            goto L8b
        L5f:
            com.facebook.soloader.nativeloader.SystemDelegate r7 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch: java.lang.Throwable -> L8f
            r7.<init>()     // Catch: java.lang.Throwable -> L8f
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r7)     // Catch: java.lang.Throwable -> L8f
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L9a
            goto L8b
        L6e:
            com.facebook.soloader.nativeloader.SystemDelegate r7 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch: java.lang.Throwable -> L8f
            r7.<init>()     // Catch: java.lang.Throwable -> L8f
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r7)     // Catch: java.lang.Throwable -> L8f
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L9a
            goto L8b
        L7d:
            com.facebook.soloader.nativeloader.SystemDelegate r7 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch: java.lang.Throwable -> L8f
            r7.<init>()     // Catch: java.lang.Throwable -> L8f
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r7)     // Catch: java.lang.Throwable -> L8f
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L9a
        L8b:
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
            goto L9a
        L8f:
            r4 = move-exception
            boolean r5 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r5 == 0) goto L99
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L99:
            throw r4
        L9a:
            android.content.Context r4 = r4.getApplicationContext()
            if (r5 != 0) goto La4
            com.facebook.imagepipeline.core.ImagePipelineFactory.initialize(r4)
            goto La7
        La4:
            com.facebook.imagepipeline.core.ImagePipelineFactory.initialize(r5)
        La7:
            initializeDrawee(r4, r6)
            boolean r4 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r4 == 0) goto Lb3
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        Lb3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.backends.pipeline.Fresco.initialize(android.content.Context, com.facebook.imagepipeline.core.ImagePipelineConfig, com.facebook.drawee.backends.pipeline.DraweeConfig, boolean):void");
    }

    private static void initializeDrawee(Context context, @Nullable DraweeConfig draweeConfig) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("Fresco.initializeDrawee");
        }
        PipelineDraweeControllerBuilderSupplier pipelineDraweeControllerBuilderSupplier = new PipelineDraweeControllerBuilderSupplier(context, draweeConfig);
        sDraweeControllerBuilderSupplier = pipelineDraweeControllerBuilderSupplier;
        SimpleDraweeView.initialize(pipelineDraweeControllerBuilderSupplier);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    public static PipelineDraweeControllerBuilderSupplier getDraweeControllerBuilderSupplier() {
        return sDraweeControllerBuilderSupplier;
    }

    public static PipelineDraweeControllerBuilder newDraweeControllerBuilder() {
        return sDraweeControllerBuilderSupplier.get();
    }

    public static ImagePipelineFactory getImagePipelineFactory() {
        return ImagePipelineFactory.getInstance();
    }

    public static ImagePipeline getImagePipeline() {
        return getImagePipelineFactory().getImagePipeline();
    }

    public static void shutDown() {
        sDraweeControllerBuilderSupplier = null;
        SimpleDraweeView.shutDown();
        ImagePipelineFactory.shutDown();
    }

    public static boolean hasBeenInitialized() {
        return sIsInitialized;
    }
}
