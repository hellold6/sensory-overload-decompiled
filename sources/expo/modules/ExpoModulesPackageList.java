package expo.modules;

import expo.modules.adapters.react.ReactAdapterPackage;
import expo.modules.application.ApplicationModule;
import expo.modules.asset.AssetModule;
import expo.modules.audio.AudioModule;
import expo.modules.constants.ConstantsModule;
import expo.modules.constants.ConstantsPackage;
import expo.modules.core.BasePackage;
import expo.modules.core.interfaces.Package;
import expo.modules.documentpicker.DocumentPickerModule;
import expo.modules.fetch.ExpoFetchModule;
import expo.modules.filesystem.FileSystemModule;
import expo.modules.filesystem.legacy.FileSystemLegacyModule;
import expo.modules.filesystem.legacy.FileSystemPackage;
import expo.modules.font.FontLoaderModule;
import expo.modules.font.FontUtilsModule;
import expo.modules.haptics.HapticsModule;
import expo.modules.image.ExpoImageModule;
import expo.modules.intentlauncher.IntentLauncherModule;
import expo.modules.keepawake.KeepAwakeModule;
import expo.modules.keepawake.KeepAwakePackage;
import expo.modules.kotlin.ModulesProvider;
import expo.modules.kotlin.edgeToEdge.EdgeToEdgePackage;
import expo.modules.kotlin.modules.Module;
import expo.modules.linking.ExpoLinkingModule;
import expo.modules.linking.ExpoLinkingPackage;
import expo.modules.medialibrary.MediaLibraryModule;
import expo.modules.medialibrary.next.MediaLibraryNextModule;
import expo.modules.notifications.NotificationsPackage;
import expo.modules.notifications.badge.BadgeModule;
import expo.modules.notifications.notifications.background.ExpoBackgroundNotificationTasksModule;
import expo.modules.notifications.notifications.categories.ExpoNotificationCategoriesModule;
import expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule;
import expo.modules.notifications.notifications.channels.NotificationChannelManagerModule;
import expo.modules.notifications.notifications.emitting.NotificationsEmitter;
import expo.modules.notifications.notifications.handling.NotificationsHandler;
import expo.modules.notifications.notifications.presentation.ExpoNotificationPresentationModule;
import expo.modules.notifications.notifications.scheduling.NotificationScheduler;
import expo.modules.notifications.permissions.NotificationPermissionsModule;
import expo.modules.notifications.serverregistration.ServerRegistrationModule;
import expo.modules.notifications.tokens.PushTokenModule;
import expo.modules.securestore.SecureStoreModule;
import expo.modules.splashscreen.SplashScreenModule;
import expo.modules.systemui.SystemUIModule;
import expo.modules.systemui.SystemUIPackage;
import expo.modules.video.VideoModule;
import expo.modules.webbrowser.WebBrowserModule;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class ExpoModulesPackageList implements ModulesProvider {

    /* loaded from: classes3.dex */
    private static class LazyHolder {
        static final List<Package> packagesList = Arrays.asList(new ReactAdapterPackage(), new ConstantsPackage(), new BasePackage(), new FileSystemPackage(), new KeepAwakePackage(), new EdgeToEdgePackage(), new ExpoLinkingPackage(), new NotificationsPackage(), new SystemUIPackage());
        static final List<Class<? extends Module>> modulesList = Arrays.asList(ExpoFetchModule.class, ApplicationModule.class, AssetModule.class, AudioModule.class, ConstantsModule.class, DocumentPickerModule.class, FileSystemModule.class, FileSystemLegacyModule.class, FontLoaderModule.class, FontUtilsModule.class, HapticsModule.class, ExpoImageModule.class, IntentLauncherModule.class, KeepAwakeModule.class, ExpoLinkingModule.class, MediaLibraryModule.class, MediaLibraryNextModule.class, BadgeModule.class, ExpoBackgroundNotificationTasksModule.class, ExpoNotificationCategoriesModule.class, NotificationChannelGroupManagerModule.class, NotificationChannelManagerModule.class, NotificationsEmitter.class, NotificationsHandler.class, NotificationPermissionsModule.class, ExpoNotificationPresentationModule.class, NotificationScheduler.class, ServerRegistrationModule.class, PushTokenModule.class, SecureStoreModule.class, SplashScreenModule.class, SystemUIModule.class, VideoModule.class, WebBrowserModule.class);

        private LazyHolder() {
        }
    }

    public static List<Package> getPackageList() {
        return LazyHolder.packagesList;
    }

    @Override // expo.modules.kotlin.ModulesProvider
    public List<Class<? extends Module>> getModulesList() {
        return LazyHolder.modulesList;
    }
}
