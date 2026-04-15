package expo.modules.medialibrary.next.objects.wrappers;

import android.net.Uri;
import androidx.core.os.EnvironmentCompat;
import androidx.media3.common.MimeTypes;
import expo.modules.kotlin.types.Enumerable;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: MediaType.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \u000f2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u000fB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\r\u001a\u00020\u000eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u0010"}, d2 = {"Lexpo/modules/medialibrary/next/objects/wrappers/MediaType;", "Lexpo/modules/kotlin/types/Enumerable;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "AUDIO", "IMAGE", "VIDEO", "UNKNOWN", "toMediaStoreValue", "", "Companion", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaType implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ MediaType[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final String value;
    public static final MediaType AUDIO = new MediaType("AUDIO", 0, MimeTypes.BASE_TYPE_AUDIO);
    public static final MediaType IMAGE = new MediaType("IMAGE", 1, "image");
    public static final MediaType VIDEO = new MediaType("VIDEO", 2, MimeTypes.BASE_TYPE_VIDEO);
    public static final MediaType UNKNOWN = new MediaType("UNKNOWN", 3, EnvironmentCompat.MEDIA_UNKNOWN);

    /* compiled from: MediaType.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaType.values().length];
            try {
                iArr[MediaType.AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MediaType.IMAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MediaType.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[MediaType.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ MediaType[] $values() {
        return new MediaType[]{AUDIO, IMAGE, VIDEO, UNKNOWN};
    }

    public static EnumEntries<MediaType> getEntries() {
        return $ENTRIES;
    }

    private MediaType(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        MediaType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    public final int toMediaStoreValue() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 3;
        }
        if (i == 4) {
            return 0;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* compiled from: MediaType.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lexpo/modules/medialibrary/next/objects/wrappers/MediaType$Companion;", "", "<init>", "()V", "fromString", "Lexpo/modules/medialibrary/next/objects/wrappers/MediaType;", "string", "", "fromMediaStoreValue", "mediaStoreValue", "", "fromContentUri", "contentUri", "Landroid/net/Uri;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MediaType fromString(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            String lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            int hashCode = lowerCase.hashCode();
            if (hashCode != 93166550) {
                if (hashCode != 100313435) {
                    if (hashCode == 112202875 && lowerCase.equals(MimeTypes.BASE_TYPE_VIDEO)) {
                        return MediaType.VIDEO;
                    }
                } else if (lowerCase.equals("image")) {
                    return MediaType.IMAGE;
                }
            } else if (lowerCase.equals(MimeTypes.BASE_TYPE_AUDIO)) {
                return MediaType.AUDIO;
            }
            return MediaType.UNKNOWN;
        }

        public final MediaType fromMediaStoreValue(int mediaStoreValue) {
            if (mediaStoreValue == 1) {
                return MediaType.IMAGE;
            }
            if (mediaStoreValue == 2) {
                return MediaType.AUDIO;
            }
            if (mediaStoreValue == 3) {
                return MediaType.VIDEO;
            }
            return MediaType.UNKNOWN;
        }

        public final MediaType fromContentUri(Uri contentUri) {
            Intrinsics.checkNotNullParameter(contentUri, "contentUri");
            List<String> pathSegments = contentUri.getPathSegments();
            if (pathSegments.contains("images")) {
                return MediaType.IMAGE;
            }
            if (pathSegments.contains(MimeTypes.BASE_TYPE_VIDEO)) {
                return MediaType.VIDEO;
            }
            if (pathSegments.contains(MimeTypes.BASE_TYPE_AUDIO)) {
                return MediaType.AUDIO;
            }
            return MediaType.UNKNOWN;
        }
    }

    public static MediaType valueOf(String str) {
        return (MediaType) Enum.valueOf(MediaType.class, str);
    }

    public static MediaType[] values() {
        return (MediaType[]) $VALUES.clone();
    }
}
