package expo.modules.notifications.notifications.channels;

import android.content.Context;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.ExpoNotificationsChannelGroupSerializer;
import expo.modules.notifications.notifications.channels.serializers.ExpoNotificationsChannelSerializer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidXNotificationsChannelsProvider.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001b\u0010\u0006\u001a\u00020\u00078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lexpo/modules/notifications/notifications/channels/AndroidXNotificationsChannelsProvider;", "Lexpo/modules/notifications/notifications/channels/AbstractNotificationsChannelsProvider;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "groupManager", "Lexpo/modules/notifications/notifications/channels/managers/AndroidXNotificationsChannelGroupManager;", "getGroupManager", "()Lexpo/modules/notifications/notifications/channels/managers/AndroidXNotificationsChannelGroupManager;", "groupManager$delegate", "Lkotlin/Lazy;", "channelManager", "Lexpo/modules/notifications/notifications/channels/managers/AndroidXNotificationsChannelManager;", "getChannelManager", "()Lexpo/modules/notifications/notifications/channels/managers/AndroidXNotificationsChannelManager;", "channelManager$delegate", "channelSerializer", "Lexpo/modules/notifications/notifications/channels/serializers/ExpoNotificationsChannelSerializer;", "getChannelSerializer", "()Lexpo/modules/notifications/notifications/channels/serializers/ExpoNotificationsChannelSerializer;", "channelSerializer$delegate", "groupSerializer", "Lexpo/modules/notifications/notifications/channels/serializers/ExpoNotificationsChannelGroupSerializer;", "getGroupSerializer", "()Lexpo/modules/notifications/notifications/channels/serializers/ExpoNotificationsChannelGroupSerializer;", "groupSerializer$delegate", "expo-notifications_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidXNotificationsChannelsProvider extends AbstractNotificationsChannelsProvider {

    /* renamed from: channelManager$delegate, reason: from kotlin metadata */
    private final Lazy channelManager;

    /* renamed from: channelSerializer$delegate, reason: from kotlin metadata */
    private final Lazy channelSerializer;

    /* renamed from: groupManager$delegate, reason: from kotlin metadata */
    private final Lazy groupManager;

    /* renamed from: groupSerializer$delegate, reason: from kotlin metadata */
    private final Lazy groupSerializer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidXNotificationsChannelsProvider(final Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.groupManager = LazyKt.lazy(new Function0() { // from class: expo.modules.notifications.notifications.channels.AndroidXNotificationsChannelsProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidXNotificationsChannelGroupManager groupManager_delegate$lambda$0;
                groupManager_delegate$lambda$0 = AndroidXNotificationsChannelsProvider.groupManager_delegate$lambda$0(context);
                return groupManager_delegate$lambda$0;
            }
        });
        this.channelManager = LazyKt.lazy(new Function0() { // from class: expo.modules.notifications.notifications.channels.AndroidXNotificationsChannelsProvider$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidXNotificationsChannelManager channelManager_delegate$lambda$1;
                channelManager_delegate$lambda$1 = AndroidXNotificationsChannelsProvider.channelManager_delegate$lambda$1(context, this);
                return channelManager_delegate$lambda$1;
            }
        });
        this.channelSerializer = LazyKt.lazy(new Function0() { // from class: expo.modules.notifications.notifications.channels.AndroidXNotificationsChannelsProvider$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ExpoNotificationsChannelSerializer channelSerializer_delegate$lambda$2;
                channelSerializer_delegate$lambda$2 = AndroidXNotificationsChannelsProvider.channelSerializer_delegate$lambda$2();
                return channelSerializer_delegate$lambda$2;
            }
        });
        this.groupSerializer = LazyKt.lazy(new Function0() { // from class: expo.modules.notifications.notifications.channels.AndroidXNotificationsChannelsProvider$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ExpoNotificationsChannelGroupSerializer groupSerializer_delegate$lambda$3;
                groupSerializer_delegate$lambda$3 = AndroidXNotificationsChannelsProvider.groupSerializer_delegate$lambda$3(AndroidXNotificationsChannelsProvider.this);
                return groupSerializer_delegate$lambda$3;
            }
        });
    }

    @Override // expo.modules.notifications.notifications.channels.NotificationsChannelsProvider
    public AndroidXNotificationsChannelGroupManager getGroupManager() {
        return (AndroidXNotificationsChannelGroupManager) this.groupManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AndroidXNotificationsChannelGroupManager groupManager_delegate$lambda$0(Context context) {
        return new AndroidXNotificationsChannelGroupManager(context);
    }

    @Override // expo.modules.notifications.notifications.channels.NotificationsChannelsProvider
    public AndroidXNotificationsChannelManager getChannelManager() {
        return (AndroidXNotificationsChannelManager) this.channelManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AndroidXNotificationsChannelManager channelManager_delegate$lambda$1(Context context, AndroidXNotificationsChannelsProvider androidXNotificationsChannelsProvider) {
        return new AndroidXNotificationsChannelManager(context, androidXNotificationsChannelsProvider.getGroupManager());
    }

    @Override // expo.modules.notifications.notifications.channels.NotificationsChannelsProvider
    public ExpoNotificationsChannelSerializer getChannelSerializer() {
        return (ExpoNotificationsChannelSerializer) this.channelSerializer.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExpoNotificationsChannelSerializer channelSerializer_delegate$lambda$2() {
        return new ExpoNotificationsChannelSerializer();
    }

    @Override // expo.modules.notifications.notifications.channels.NotificationsChannelsProvider
    public ExpoNotificationsChannelGroupSerializer getGroupSerializer() {
        return (ExpoNotificationsChannelGroupSerializer) this.groupSerializer.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExpoNotificationsChannelGroupSerializer groupSerializer_delegate$lambda$3(AndroidXNotificationsChannelsProvider androidXNotificationsChannelsProvider) {
        return new ExpoNotificationsChannelGroupSerializer(androidXNotificationsChannelsProvider.getChannelSerializer());
    }
}
