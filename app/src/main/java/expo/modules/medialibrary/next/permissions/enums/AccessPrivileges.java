package expo.modules.medialibrary.next.permissions.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.apache.commons.codec.language.bm.Rule;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: AccessPrivileges.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lexpo/modules/medialibrary/next/permissions/enums/AccessPrivileges;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", Rule.ALL, "LIMITED", "NONE", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AccessPrivileges {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AccessPrivileges[] $VALUES;
    public static final AccessPrivileges ALL = new AccessPrivileges(Rule.ALL, 0, "all");
    public static final AccessPrivileges LIMITED = new AccessPrivileges("LIMITED", 1, "limited");
    public static final AccessPrivileges NONE = new AccessPrivileges("NONE", 2, "none");
    private final String value;

    private static final /* synthetic */ AccessPrivileges[] $values() {
        return new AccessPrivileges[]{ALL, LIMITED, NONE};
    }

    public static EnumEntries<AccessPrivileges> getEntries() {
        return $ENTRIES;
    }

    private AccessPrivileges(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        AccessPrivileges[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static AccessPrivileges valueOf(String str) {
        return (AccessPrivileges) Enum.valueOf(AccessPrivileges.class, str);
    }

    public static AccessPrivileges[] values() {
        return (AccessPrivileges[]) $VALUES.clone();
    }
}
