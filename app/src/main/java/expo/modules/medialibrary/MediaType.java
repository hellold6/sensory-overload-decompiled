package expo.modules.medialibrary;

import androidx.core.os.EnvironmentCompat;
import androidx.media3.common.MimeTypes;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.apache.commons.codec.language.bm.Rule;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: MediaLibraryEnums.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lexpo/modules/medialibrary/MediaType;", "", "apiName", "", "mediaColumn", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Integer;)V", "getApiName", "()Ljava/lang/String;", "getMediaColumn", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "AUDIO", "PHOTO", "VIDEO", "UNKNOWN", Rule.ALL, "Companion", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ MediaType[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final String apiName;
    private final Integer mediaColumn;
    public static final MediaType AUDIO = new MediaType("AUDIO", 0, MimeTypes.BASE_TYPE_AUDIO, 2);
    public static final MediaType PHOTO = new MediaType("PHOTO", 1, "photo", 1);
    public static final MediaType VIDEO = new MediaType("VIDEO", 2, MimeTypes.BASE_TYPE_VIDEO, 3);
    public static final MediaType UNKNOWN = new MediaType("UNKNOWN", 3, EnvironmentCompat.MEDIA_UNKNOWN, 0);
    public static final MediaType ALL = new MediaType(Rule.ALL, 4, "all", null);

    private static final /* synthetic */ MediaType[] $values() {
        return new MediaType[]{AUDIO, PHOTO, VIDEO, UNKNOWN, ALL};
    }

    public static EnumEntries<MediaType> getEntries() {
        return $ENTRIES;
    }

    private MediaType(String str, int i, String str2, Integer num) {
        this.apiName = str2;
        this.mediaColumn = num;
    }

    public final String getApiName() {
        return this.apiName;
    }

    public final Integer getMediaColumn() {
        return this.mediaColumn;
    }

    static {
        MediaType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: MediaLibraryEnums.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lexpo/modules/medialibrary/MediaType$Companion;", "", "<init>", "()V", "getConstants", "", "", "fromApiName", "Lexpo/modules/medialibrary/MediaType;", "constantName", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, String> getConstants() {
            MediaType[] values = MediaType.values();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(values.length), 16));
            for (MediaType mediaType : values) {
                Pair pair = new Pair(mediaType.getApiName(), mediaType.getApiName());
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            return linkedHashMap;
        }

        public final MediaType fromApiName(String constantName) {
            Intrinsics.checkNotNullParameter(constantName, "constantName");
            for (MediaType mediaType : MediaType.values()) {
                if (Intrinsics.areEqual(mediaType.getApiName(), constantName)) {
                    return mediaType;
                }
            }
            return null;
        }
    }

    public static MediaType valueOf(String str) {
        return (MediaType) Enum.valueOf(MediaType.class, str);
    }

    public static MediaType[] values() {
        return (MediaType[]) $VALUES.clone();
    }
}
