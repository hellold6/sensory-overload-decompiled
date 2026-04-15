package com.horcrux.svg;

import androidx.core.os.EnvironmentCompat;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* loaded from: classes3.dex */
class FilterProperties {
    FilterProperties() {
    }

    /* loaded from: classes3.dex */
    enum Units {
        OBJECT_BOUNDING_BOX("objectBoundingBox"),
        USER_SPACE_ON_USE("userSpaceOnUse");

        private static final Map<String, Units> unitsToEnum = new HashMap();
        private final String units;

        static {
            for (Units units : values()) {
                unitsToEnum.put(units.units, units);
            }
        }

        Units(String str) {
            this.units = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static Units getEnum(String str) {
            Map<String, Units> map = unitsToEnum;
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException("Unknown 'Unit' Value: " + str);
            }
            return map.get(str);
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.units;
        }
    }

    /* loaded from: classes3.dex */
    enum EdgeMode {
        UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN),
        DUPLICATE("duplicate"),
        WRAP("wrap"),
        NONE("none");

        private static final Map<String, EdgeMode> edgeModeToEnum = new HashMap();
        private final String edgeMode;

        static {
            for (EdgeMode edgeMode : values()) {
                edgeModeToEnum.put(edgeMode.edgeMode, edgeMode);
            }
        }

        EdgeMode(String str) {
            this.edgeMode = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static EdgeMode getEnum(String str) {
            Map<String, EdgeMode> map = edgeModeToEnum;
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException("Unknown 'edgeMode' Value: " + str);
            }
            return map.get(str);
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.edgeMode;
        }
    }

    /* loaded from: classes3.dex */
    enum FeBlendMode {
        UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN),
        NORMAL("normal"),
        MULTIPLY("multiply"),
        SCREEN("screen"),
        DARKEN("darken"),
        LIGHTEN("lighten");

        private static final Map<String, FeBlendMode> typeToEnum = new HashMap();
        private final String mode;

        static {
            for (FeBlendMode feBlendMode : values()) {
                typeToEnum.put(feBlendMode.mode, feBlendMode);
            }
        }

        FeBlendMode(String str) {
            this.mode = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static FeBlendMode getEnum(String str) {
            Map<String, FeBlendMode> map = typeToEnum;
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException("Unknown String Value: " + str);
            }
            return map.get(str);
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.mode;
        }
    }

    /* loaded from: classes3.dex */
    enum FeColorMatrixType {
        MATRIX("matrix"),
        SATURATE("saturate"),
        HUE_ROTATE("hueRotate"),
        LUMINANCE_TO_ALPHA("luminanceToAlpha");

        private static final Map<String, FeColorMatrixType> typeToEnum = new HashMap();
        private final String type;

        static {
            for (FeColorMatrixType feColorMatrixType : values()) {
                typeToEnum.put(feColorMatrixType.type, feColorMatrixType);
            }
        }

        FeColorMatrixType(String str) {
            this.type = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static FeColorMatrixType getEnum(String str) {
            Map<String, FeColorMatrixType> map = typeToEnum;
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException("Unknown String Value: " + str);
            }
            return map.get(str);
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.type;
        }
    }

    /* loaded from: classes3.dex */
    enum FeCompositeOperator {
        OVER("over"),
        IN("in"),
        OUT("out"),
        ATOP("atop"),
        XOR("xor"),
        ARITHMETIC("arithmetic");

        private static final Map<String, FeCompositeOperator> typeToEnum = new HashMap();
        private final String type;

        static {
            for (FeCompositeOperator feCompositeOperator : values()) {
                typeToEnum.put(feCompositeOperator.type, feCompositeOperator);
            }
        }

        FeCompositeOperator(String str) {
            this.type = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static FeCompositeOperator getEnum(String str) {
            Map<String, FeCompositeOperator> map = typeToEnum;
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException("Unknown String Value: " + str);
            }
            return map.get(str);
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.type;
        }
    }
}
