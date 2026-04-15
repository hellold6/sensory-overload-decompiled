package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.core.app.FrameMetricsAggregator;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* compiled from: TypeMappingMode.kt */
/* loaded from: classes3.dex */
public final class TypeMappingMode {
    public static final TypeMappingMode CLASS_DECLARATION;
    public static final Companion Companion = new Companion(null);
    public static final TypeMappingMode DEFAULT;
    public static final TypeMappingMode DEFAULT_UAST;
    public static final TypeMappingMode GENERIC_ARGUMENT;
    public static final TypeMappingMode GENERIC_ARGUMENT_UAST;
    public static final TypeMappingMode RETURN_TYPE_BOXED;
    public static final TypeMappingMode SUPER_TYPE;
    public static final TypeMappingMode SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS;
    public static final TypeMappingMode VALUE_FOR_ANNOTATION;
    private final TypeMappingMode genericArgumentMode;
    private final TypeMappingMode genericContravariantArgumentMode;
    private final TypeMappingMode genericInvariantArgumentMode;
    private final boolean isForAnnotationParameter;
    private final boolean kotlinCollectionsToJavaCollections;
    private final boolean mapTypeAliases;
    private final boolean needInlineClassWrapping;
    private final boolean needPrimitiveBoxing;
    private final boolean skipDeclarationSiteWildcards;
    private final boolean skipDeclarationSiteWildcardsIfPossible;

    /* compiled from: TypeMappingMode.kt */
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.INVARIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TypeMappingMode() {
        this(false, false, false, false, false, null, false, null, null, false, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    public TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3, boolean z7) {
        this.needPrimitiveBoxing = z;
        this.needInlineClassWrapping = z2;
        this.isForAnnotationParameter = z3;
        this.skipDeclarationSiteWildcards = z4;
        this.skipDeclarationSiteWildcardsIfPossible = z5;
        this.genericArgumentMode = typeMappingMode;
        this.kotlinCollectionsToJavaCollections = z6;
        this.genericContravariantArgumentMode = typeMappingMode2;
        this.genericInvariantArgumentMode = typeMappingMode3;
        this.mapTypeAliases = z7;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ TypeMappingMode(boolean r3, boolean r4, boolean r5, boolean r6, boolean r7, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode r8, boolean r9, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode r10, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode r11, boolean r12, int r13, kotlin.jvm.internal.DefaultConstructorMarker r14) {
        /*
            r2 = this;
            r14 = r13 & 1
            r0 = 1
            if (r14 == 0) goto L6
            r3 = r0
        L6:
            r14 = r13 & 2
            if (r14 == 0) goto Lb
            r4 = r0
        Lb:
            r14 = r13 & 4
            r1 = 0
            if (r14 == 0) goto L11
            r5 = r1
        L11:
            r14 = r13 & 8
            if (r14 == 0) goto L16
            r6 = r1
        L16:
            r14 = r13 & 16
            if (r14 == 0) goto L1b
            r7 = r1
        L1b:
            r14 = r13 & 32
            if (r14 == 0) goto L20
            r8 = 0
        L20:
            r14 = r13 & 64
            if (r14 == 0) goto L25
            r9 = r0
        L25:
            r14 = r13 & 128(0x80, float:1.8E-43)
            if (r14 == 0) goto L2a
            r10 = r8
        L2a:
            r14 = r13 & 256(0x100, float:3.59E-43)
            if (r14 == 0) goto L2f
            r11 = r8
        L2f:
            r13 = r13 & 512(0x200, float:7.17E-43)
            if (r13 == 0) goto L35
            r13 = r1
            goto L36
        L35:
            r13 = r12
        L36:
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r2
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode.<init>(boolean, boolean, boolean, boolean, boolean, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode, boolean, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final boolean getNeedPrimitiveBoxing() {
        return this.needPrimitiveBoxing;
    }

    public final boolean getNeedInlineClassWrapping() {
        return this.needInlineClassWrapping;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    public final boolean getKotlinCollectionsToJavaCollections() {
        return this.kotlinCollectionsToJavaCollections;
    }

    public final boolean getMapTypeAliases() {
        return this.mapTypeAliases;
    }

    /* compiled from: TypeMappingMode.kt */
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        TypeMappingMode typeMappingMode = new TypeMappingMode(z, z2, z3, z4, z5, null, false, null, null, z6, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
        GENERIC_ARGUMENT = typeMappingMode;
        TypeMappingMode typeMappingMode2 = new TypeMappingMode(false, false, z6, false, false, null, false, null, null, true, FrameMetricsAggregator.EVERY_DURATION, null);
        GENERIC_ARGUMENT_UAST = typeMappingMode2;
        RETURN_TYPE_BOXED = new TypeMappingMode(false, true, false, false, false, null, false, null, null, false, 1021, null);
        DEFAULT = new TypeMappingMode(z, z2, z3, z4, z5, typeMappingMode, false, null, null, z6, 988, null);
        DEFAULT_UAST = new TypeMappingMode(false, false, z6, false, false, typeMappingMode2, false, null, null, true, 476, null);
        DefaultConstructorMarker defaultConstructorMarker = null;
        boolean z7 = false;
        TypeMappingMode typeMappingMode3 = null;
        TypeMappingMode typeMappingMode4 = null;
        CLASS_DECLARATION = new TypeMappingMode(z, true, z3, z4, z5, typeMappingMode, z7, typeMappingMode3, typeMappingMode4, z6, 988, defaultConstructorMarker);
        boolean z8 = false;
        boolean z9 = true;
        SUPER_TYPE = new TypeMappingMode(z, z8, z3, z9, z5, typeMappingMode, z7, typeMappingMode3, typeMappingMode4, z6, 983, defaultConstructorMarker);
        SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS = new TypeMappingMode(z, z8, z3, z9, z5, typeMappingMode, z7, typeMappingMode3, typeMappingMode4, z6, 919, defaultConstructorMarker);
        VALUE_FOR_ANNOTATION = new TypeMappingMode(z, z8, true, false, z5, typeMappingMode, z7, typeMappingMode3, typeMappingMode4, z6, 984, defaultConstructorMarker);
    }

    public final TypeMappingMode toGenericArgumentMode(Variance effectiveVariance, boolean z) {
        Intrinsics.checkNotNullParameter(effectiveVariance, "effectiveVariance");
        if (!z || !this.isForAnnotationParameter) {
            int i = WhenMappings.$EnumSwitchMapping$0[effectiveVariance.ordinal()];
            if (i == 1) {
                TypeMappingMode typeMappingMode = this.genericContravariantArgumentMode;
                if (typeMappingMode != null) {
                    return typeMappingMode;
                }
            } else if (i == 2) {
                TypeMappingMode typeMappingMode2 = this.genericInvariantArgumentMode;
                if (typeMappingMode2 != null) {
                    return typeMappingMode2;
                }
            } else {
                TypeMappingMode typeMappingMode3 = this.genericArgumentMode;
                if (typeMappingMode3 != null) {
                    return typeMappingMode3;
                }
            }
        }
        return this;
    }

    public final TypeMappingMode wrapInlineClassesMode() {
        return new TypeMappingMode(this.needPrimitiveBoxing, true, this.isForAnnotationParameter, this.skipDeclarationSiteWildcards, this.skipDeclarationSiteWildcardsIfPossible, this.genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode, false, 512, null);
    }
}
