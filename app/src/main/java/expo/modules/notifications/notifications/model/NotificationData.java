package expo.modules.notifications.notifications.model;

import com.facebook.react.modules.dialog.AlertFragment;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NotificationData.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0016\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\f\b\u0087@\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010*\u001a\u00020\u00132\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020'HÖ\u0001¢\u0006\u0004\b/\u00100J\u0010\u00101\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b2\u0010\tR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\f\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\tR\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0018\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0015R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\tR\u0011\u0010 \u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b!\u0010\u0015R\u0013\u0010\"\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b#\u0010\tR\u0013\u0010$\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b%\u0010\tR\u0013\u0010&\u001a\u0004\u0018\u00010'8F¢\u0006\u0006\u001a\u0004\b(\u0010)\u0088\u0001\u0002\u0092\u0001\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¨\u00063"}, d2 = {"Lexpo/modules/notifications/notifications/model/NotificationData;", "", "data", "", "", "constructor-impl", "(Ljava/util/Map;)Ljava/util/Map;", AlertFragment.ARG_TITLE, "getTitle-impl", "(Ljava/util/Map;)Ljava/lang/String;", "message", "getMessage-impl", "body", "Lorg/json/JSONObject;", "getBody-impl", "(Ljava/util/Map;)Lorg/json/JSONObject;", NotificationsChannelSerializer.SOUND_KEY, "getSound-impl", "shouldPlayDefaultSound", "", "getShouldPlayDefaultSound-impl", "(Ljava/util/Map;)Z", "shouldUseDefaultVibrationPattern", "getShouldUseDefaultVibrationPattern-impl", "isSticky", "isSticky-impl", NotificationsChannelSerializer.VIBRATION_PATTERN_KEY, "", "getVibrationPattern-impl", "(Ljava/util/Map;)[J", "color", "getColor-impl", "autoDismiss", "getAutoDismiss-impl", "categoryId", "getCategoryId-impl", "subText", "getSubText-impl", "badge", "", "getBadge-impl", "(Ljava/util/Map;)Ljava/lang/Integer;", "equals", "other", "equals-impl", "(Ljava/util/Map;Ljava/lang/Object;)Z", "hashCode", "hashCode-impl", "(Ljava/util/Map;)I", "toString", "toString-impl", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class NotificationData {
    private final Map<String, String> data;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ NotificationData m1357boximpl(Map map) {
        return new NotificationData(map);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static Map<String, ? extends String> m1358constructorimpl(Map<String, String> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return data;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m1359equalsimpl(Map<String, ? extends String> map, Object obj) {
        return (obj instanceof NotificationData) && Intrinsics.areEqual(map, ((NotificationData) obj).getData());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1360equalsimpl0(Map<String, ? extends String> map, Map<String, ? extends String> map2) {
        return Intrinsics.areEqual(map, map2);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m1373hashCodeimpl(Map<String, ? extends String> map) {
        return map.hashCode();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m1375toStringimpl(Map<String, ? extends String> map) {
        return "NotificationData(data=" + map + ")";
    }

    public boolean equals(Object obj) {
        return m1359equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m1373hashCodeimpl(this.data);
    }

    public String toString() {
        return m1375toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Map getData() {
        return this.data;
    }

    private /* synthetic */ NotificationData(Map map) {
        this.data = map;
    }

    /* renamed from: getTitle-impl, reason: not valid java name */
    public static final String m1371getTitleimpl(Map<String, ? extends String> map) {
        return map.get(AlertFragment.ARG_TITLE);
    }

    /* renamed from: getMessage-impl, reason: not valid java name */
    public static final String m1366getMessageimpl(Map<String, ? extends String> map) {
        return map.get("message");
    }

    /* renamed from: getBody-impl, reason: not valid java name */
    public static final JSONObject m1363getBodyimpl(Map<String, ? extends String> map) {
        try {
            String str = map.get("body");
            if (str != null) {
                return new JSONObject(str);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* renamed from: getSound-impl, reason: not valid java name */
    public static final String m1369getSoundimpl(Map<String, ? extends String> map) {
        return map.get(NotificationsChannelSerializer.SOUND_KEY);
    }

    /* renamed from: getShouldPlayDefaultSound-impl, reason: not valid java name */
    public static final boolean m1367getShouldPlayDefaultSoundimpl(Map<String, ? extends String> map) {
        return m1369getSoundimpl(map) == null;
    }

    /* renamed from: getShouldUseDefaultVibrationPattern-impl, reason: not valid java name */
    public static final boolean m1368getShouldUseDefaultVibrationPatternimpl(Map<String, ? extends String> map) {
        String str = map.get("vibrate");
        return str != null && Boolean.parseBoolean(str);
    }

    /* renamed from: isSticky-impl, reason: not valid java name */
    public static final boolean m1374isStickyimpl(Map<String, ? extends String> map) {
        String str = map.get("sticky");
        if (str != null) {
            return Boolean.parseBoolean(str);
        }
        return false;
    }

    /* renamed from: getVibrationPattern-impl, reason: not valid java name */
    public static final long[] m1372getVibrationPatternimpl(Map<String, ? extends String> map) {
        try {
            String str = map.get("vibrate");
            if (str != null) {
                JSONArray jSONArray = new JSONArray(str);
                int length = jSONArray.length();
                long[] jArr = new long[length];
                for (int i = 0; i < length; i++) {
                    jArr[i] = jSONArray.getLong(i);
                }
                return jArr;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* renamed from: getColor-impl, reason: not valid java name */
    public static final String m1365getColorimpl(Map<String, ? extends String> map) {
        return map.get("color");
    }

    /* renamed from: getAutoDismiss-impl, reason: not valid java name */
    public static final boolean m1361getAutoDismissimpl(Map<String, ? extends String> map) {
        String str = map.get("autoDismiss");
        if (str != null) {
            return Boolean.parseBoolean(str);
        }
        return true;
    }

    /* renamed from: getCategoryId-impl, reason: not valid java name */
    public static final String m1364getCategoryIdimpl(Map<String, ? extends String> map) {
        return map.get("categoryId");
    }

    /* renamed from: getSubText-impl, reason: not valid java name */
    public static final String m1370getSubTextimpl(Map<String, ? extends String> map) {
        return map.get("subtitle");
    }

    /* renamed from: getBadge-impl, reason: not valid java name */
    public static final Integer m1362getBadgeimpl(Map<String, ? extends String> map) {
        String str = map.get("badge");
        if (str != null) {
            return StringsKt.toIntOrNull(str);
        }
        return null;
    }
}
