package expo.modules.notifications.notifications.categories;

import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.records.Required;
import expo.modules.notifications.service.NotificationsService;
import kotlin.Metadata;

/* compiled from: ExpoNotificationCategoriesModule.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u0016\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0003\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lexpo/modules/notifications/notifications/categories/NotificationActionRecord;", "Lexpo/modules/kotlin/records/Record;", "<init>", "()V", NotificationsService.IDENTIFIER_KEY, "", "getIdentifier$annotations", "getIdentifier", "()Ljava/lang/String;", "buttonTitle", "getButtonTitle$annotations", "getButtonTitle", "textInput", "Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$TextInput;", "getTextInput$annotations", "getTextInput", "()Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$TextInput;", "options", "Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$Options;", "getOptions$annotations", "getOptions", "()Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$Options;", "TextInput", "Options", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NotificationActionRecord implements Record {
    private final TextInput textInput;
    private final String identifier = "";
    private final String buttonTitle = "";
    private final Options options = new Options();

    @Field
    @Required
    public static /* synthetic */ void getButtonTitle$annotations() {
    }

    @Field
    @Required
    public static /* synthetic */ void getIdentifier$annotations() {
    }

    @Field
    public static /* synthetic */ void getOptions$annotations() {
    }

    @Field
    public static /* synthetic */ void getTextInput$annotations() {
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final String getButtonTitle() {
        return this.buttonTitle;
    }

    public final TextInput getTextInput() {
        return this.textInput;
    }

    public final Options getOptions() {
        return this.options;
    }

    /* compiled from: ExpoNotificationCategoriesModule.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$TextInput;", "Lexpo/modules/kotlin/records/Record;", "<init>", "()V", ReactTextInputShadowNode.PROP_PLACEHOLDER, "", "getPlaceholder$annotations", "getPlaceholder", "()Ljava/lang/String;", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class TextInput implements Record {
        private final String placeholder = "";

        @Field
        @Required
        public static /* synthetic */ void getPlaceholder$annotations() {
        }

        public final String getPlaceholder() {
            return this.placeholder;
        }
    }

    /* compiled from: ExpoNotificationCategoriesModule.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$Options;", "Lexpo/modules/kotlin/records/Record;", "<init>", "()V", "opensAppToForeground", "", "getOpensAppToForeground$annotations", "getOpensAppToForeground", "()Z", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Options implements Record {
        private final boolean opensAppToForeground = true;

        @Field
        public static /* synthetic */ void getOpensAppToForeground$annotations() {
        }

        public final boolean getOpensAppToForeground() {
            return this.opensAppToForeground;
        }
    }
}
