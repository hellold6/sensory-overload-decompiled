package expo.modules.constants;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.media3.common.C;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.interfaces.constants.ConstantsInterface;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.IOUtils;

/* compiled from: ConstantsService.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u000b\b\u0016\u0018\u0000 \u001e2\u00020\u00012\u00020\u0002:\u0002\u001d\u001eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016J\u0016\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013H\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0016J\b\u0010\u0017\u001a\u00020\bH\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lexpo/modules/constants/ConstantsService;", "Lexpo/modules/core/interfaces/InternalModule;", "Lexpo/modules/interfaces/constants/ConstantsInterface;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "statusBarHeightInternal", "", "getStatusBarHeightInternal", "()I", "setStatusBarHeightInternal", "(I)V", "sessionId", "", "getExportedInterfaces", "", "Ljava/lang/Class;", "getConstants", "", "", "getAppScopeKey", "getDeviceName", "getStatusBarHeight", "getSystemVersion", "getSystemFonts", "appConfig", "getAppConfig", "()Ljava/lang/String;", "ExecutionEnvironment", "Companion", "expo-constants_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ConstantsService implements InternalModule, ConstantsInterface {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Context context;
    private final String sessionId;
    private int statusBarHeightInternal;

    public ConstantsService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        Integer valueOf = Integer.valueOf(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
        this.statusBarHeightInternal = (valueOf.intValue() <= 0 ? null : valueOf) != null ? INSTANCE.convertPixelsToDp(context.getResources().getDimensionPixelSize(r0.intValue()), context) : 0;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        this.sessionId = uuid;
    }

    public final int getStatusBarHeightInternal() {
        return this.statusBarHeightInternal;
    }

    public final void setStatusBarHeightInternal(int i) {
        this.statusBarHeightInternal = i;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: ConstantsService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lexpo/modules/constants/ConstantsService$ExecutionEnvironment;", "", "string", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getString", "()Ljava/lang/String;", "BARE", "STANDALONE", "STORE_CLIENT", "expo-constants_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ExecutionEnvironment {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ExecutionEnvironment[] $VALUES;
        public static final ExecutionEnvironment BARE = new ExecutionEnvironment("BARE", 0, "bare");
        public static final ExecutionEnvironment STANDALONE = new ExecutionEnvironment("STANDALONE", 1, "standalone");
        public static final ExecutionEnvironment STORE_CLIENT = new ExecutionEnvironment("STORE_CLIENT", 2, "storeClient");
        private final String string;

        private static final /* synthetic */ ExecutionEnvironment[] $values() {
            return new ExecutionEnvironment[]{BARE, STANDALONE, STORE_CLIENT};
        }

        public static EnumEntries<ExecutionEnvironment> getEntries() {
            return $ENTRIES;
        }

        private ExecutionEnvironment(String str, int i, String str2) {
            this.string = str2;
        }

        public final String getString() {
            return this.string;
        }

        static {
            ExecutionEnvironment[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static ExecutionEnvironment valueOf(String str) {
            return (ExecutionEnvironment) Enum.valueOf(ExecutionEnvironment.class, str);
        }

        public static ExecutionEnvironment[] values() {
            return (ExecutionEnvironment[]) $VALUES.clone();
        }
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class<?>> getExportedInterfaces() {
        return CollectionsKt.listOf(ConstantsInterface.class);
    }

    @Override // expo.modules.interfaces.constants.ConstantsInterface
    public Map<String, Object> getConstants() {
        return MapsKt.mutableMapOf(TuplesKt.to("sessionId", this.sessionId), TuplesKt.to("executionEnvironment", ExecutionEnvironment.BARE.getString()), TuplesKt.to("statusBarHeight", Integer.valueOf(this.statusBarHeightInternal)), TuplesKt.to("deviceName", getDeviceName()), TuplesKt.to("systemFonts", getSystemFonts()), TuplesKt.to("systemVersion", getSystemVersion()), TuplesKt.to("manifest", getAppConfig()), TuplesKt.to("platform", MapsKt.mapOf(TuplesKt.to("android", MapsKt.emptyMap()))));
    }

    @Override // expo.modules.interfaces.constants.ConstantsInterface
    public String getAppScopeKey() {
        return this.context.getPackageName();
    }

    @Override // expo.modules.interfaces.constants.ConstantsInterface
    public String getDeviceName() {
        String MODEL = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
        return MODEL;
    }

    @Override // expo.modules.interfaces.constants.ConstantsInterface
    /* renamed from: getStatusBarHeight, reason: from getter */
    public int getStatusBarHeightInternal() {
        return this.statusBarHeightInternal;
    }

    @Override // expo.modules.interfaces.constants.ConstantsInterface
    public String getSystemVersion() {
        String RELEASE = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
        return RELEASE;
    }

    @Override // expo.modules.interfaces.constants.ConstantsInterface
    public List<String> getSystemFonts() {
        return CollectionsKt.listOf((Object[]) new String[]{"normal", "notoserif", C.SANS_SERIF_NAME, "sans-serif-light", "sans-serif-thin", "sans-serif-condensed", "sans-serif-medium", C.SERIF_NAME, "Roboto", "monospace"});
    }

    private final String getAppConfig() {
        String str;
        try {
            InputStream open = this.context.getAssets().open("app.config");
            try {
                String iOUtils = IOUtils.toString(open, StandardCharsets.UTF_8);
                CloseableKt.closeFinally(open, null);
                return iOUtils;
            } finally {
            }
        } catch (FileNotFoundException unused) {
            return null;
        } catch (Exception e) {
            str = ConstantsServiceKt.TAG;
            Log.e(str, "Error reading embedded app config", e);
            return null;
        }
    }

    /* compiled from: ConstantsService.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\n"}, d2 = {"Lexpo/modules/constants/ConstantsService$Companion;", "", "<init>", "()V", "convertPixelsToDp", "", "px", "", "context", "Landroid/content/Context;", "expo-constants_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int convertPixelsToDp(float px, Context context) {
            return (int) (px / (context.getResources().getDisplayMetrics().densityDpi / 160.0f));
        }
    }
}
