package expo.modules.audio.service;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import expo.modules.audio.AudioRecorder;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.service.NotificationsService;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioRecordingService.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\rH\u0002J\u000e\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\tJ\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\tJ\b\u0010\u001a\u001a\u00020\rH\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u001d\u001a\u00020\rH\u0016R\u0012\u0010\u0004\u001a\u00060\u0005R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lexpo/modules/audio/service/AudioRecordingService;", "Landroid/app/Service;", "<init>", "()V", "binder", "Lexpo/modules/audio/service/AudioRecordingService$AudioRecordingBinder;", "activeRecorders", "", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/audio/AudioRecorder;", "notificationId", "", "onCreate", "", "onStartCommand", "intent", "Landroid/content/Intent;", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY, "startId", "createNotificationChannelIfNeeded", "buildNotification", "Landroid/app/Notification;", "startForegroundWithNotification", "registerRecorder", "recorder", "unregisterRecorder", "stopRecordingAndService", "onBind", "Landroid/os/IBinder;", "onDestroy", "AudioRecordingBinder", "Companion", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioRecordingService extends Service {
    private static final String ACTION_START_RECORDING = "expo.modules.audio.action.START_RECORDING";
    private static final String ACTION_STOP_RECORDING = "expo.modules.audio.action.STOP_RECORDING";
    private static final String CHANNEL_ID = "expo_audio_recording_channel";
    private static final int NOTIFICATION_ID = 2001;
    private static volatile AudioRecordingService instance;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<AudioRecorder> pendingRecorders = new LinkedHashSet();
    private final AudioRecordingBinder binder = new AudioRecordingBinder();
    private Set<WeakReference<AudioRecorder>> activeRecorders = new LinkedHashSet();
    private int notificationId = 2001;

    /* compiled from: AudioRecordingService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/audio/service/AudioRecordingService$AudioRecordingBinder;", "Landroid/os/Binder;", "<init>", "(Lexpo/modules/audio/service/AudioRecordingService;)V", "getService", "Lexpo/modules/audio/service/AudioRecordingService;", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class AudioRecordingBinder extends Binder {
        public AudioRecordingBinder() {
        }

        /* renamed from: getService, reason: from getter */
        public final AudioRecordingService getThis$0() {
            return AudioRecordingService.this;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("AudioRecordingService", "Service onCreate()");
        instance = this;
        createNotificationChannelIfNeeded();
        Set<AudioRecorder> set = pendingRecorders;
        synchronized (set) {
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                registerRecorder((AudioRecorder) it.next());
            }
            pendingRecorders.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : null;
        if (action == null) {
            return 2;
        }
        int hashCode = action.hashCode();
        if (hashCode != 509630199) {
            if (hashCode != 1004558481 || !action.equals(ACTION_STOP_RECORDING)) {
                return 2;
            }
            stopRecordingAndService();
            return 2;
        }
        if (!action.equals(ACTION_START_RECORDING) || this.activeRecorders.isEmpty()) {
            return 2;
        }
        startForegroundWithNotification();
        return 2;
    }

    private final void createNotificationChannelIfNeeded() {
        if (Build.VERSION.SDK_INT >= 26) {
            Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationManager notificationManager = (NotificationManager) systemService;
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Audio Recording", 2);
                notificationChannel.setDescription("Shows when audio is being recorded in the background");
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    private final Notification buildNotification() {
        AudioRecordingService audioRecordingService = this;
        Intent intent = new Intent(audioRecordingService, (Class<?>) AudioRecordingService.class);
        intent.setAction(ACTION_STOP_RECORDING);
        PendingIntent service = PendingIntent.getService(audioRecordingService, 0, intent, 201326592);
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        Notification build = new NotificationCompat.Builder(audioRecordingService, CHANNEL_ID).setContentTitle("Recording audio").setContentText("Tap to return to app").setSmallIcon(R.drawable.ic_btn_speak_now).setOngoing(true).setContentIntent(launchIntentForPackage != null ? PendingIntent.getActivity(audioRecordingService, 0, launchIntentForPackage, 201326592) : null).addAction(R.drawable.ic_delete, "Stop", service).setCategory(NotificationCompat.CATEGORY_SERVICE).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    private final void startForegroundWithNotification() {
        Notification buildNotification = buildNotification();
        try {
            if (Build.VERSION.SDK_INT >= 30) {
                startForeground(this.notificationId, buildNotification, 128);
            } else {
                startForeground(this.notificationId, buildNotification);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean registerRecorder$lambda$5(WeakReference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.get() == null;
    }

    public final void registerRecorder(AudioRecorder recorder) {
        Intrinsics.checkNotNullParameter(recorder, "recorder");
        CollectionsKt.removeAll(this.activeRecorders, new Function1() { // from class: expo.modules.audio.service.AudioRecordingService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean registerRecorder$lambda$5;
                registerRecorder$lambda$5 = AudioRecordingService.registerRecorder$lambda$5((WeakReference) obj);
                return Boolean.valueOf(registerRecorder$lambda$5);
            }
        });
        this.activeRecorders.add(new WeakReference<>(recorder));
        if (this.activeRecorders.size() == 1) {
            startForegroundWithNotification();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unregisterRecorder$lambda$6(AudioRecorder audioRecorder, WeakReference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it.get(), audioRecorder) || it.get() == null;
    }

    public final void unregisterRecorder(final AudioRecorder recorder) {
        Intrinsics.checkNotNullParameter(recorder, "recorder");
        CollectionsKt.removeAll(this.activeRecorders, new Function1() { // from class: expo.modules.audio.service.AudioRecordingService$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean unregisterRecorder$lambda$6;
                unregisterRecorder$lambda$6 = AudioRecordingService.unregisterRecorder$lambda$6(AudioRecorder.this, (WeakReference) obj);
                return Boolean.valueOf(unregisterRecorder$lambda$6);
            }
        });
        if (this.activeRecorders.isEmpty()) {
            stopSelf();
        }
    }

    private final void stopRecordingAndService() {
        Iterator<T> it = this.activeRecorders.iterator();
        while (it.hasNext()) {
            AudioRecorder audioRecorder = (AudioRecorder) ((WeakReference) it.next()).get();
            if (audioRecorder != null) {
                audioRecorder.stopRecording();
            }
        }
        this.activeRecorders.clear();
        stopSelf();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        this.activeRecorders.clear();
    }

    /* compiled from: AudioRecordingService.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u0004\u0018\u00010\u000bJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eJ\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lexpo/modules/audio/service/AudioRecordingService$Companion;", "", "<init>", "()V", "CHANNEL_ID", "", "NOTIFICATION_ID", "", "ACTION_START_RECORDING", "ACTION_STOP_RECORDING", "instance", "Lexpo/modules/audio/service/AudioRecordingService;", "pendingRecorders", "", "Lexpo/modules/audio/AudioRecorder;", "getInstance", "startService", "", "context", "Landroid/content/Context;", "recorder", "stopService", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AudioRecordingService getInstance() {
            return AudioRecordingService.instance;
        }

        public final void startService(Context context, AudioRecorder recorder) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(recorder, "recorder");
            Intent intent = new Intent(context, (Class<?>) AudioRecordingService.class);
            intent.setAction(AudioRecordingService.ACTION_START_RECORDING);
            AudioRecordingService companion = getInstance();
            if (companion == null) {
                synchronized (AudioRecordingService.pendingRecorders) {
                    AudioRecordingService.pendingRecorders.add(recorder);
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    context.startForegroundService(intent);
                    return;
                } else {
                    context.startService(intent);
                    return;
                }
            }
            companion.registerRecorder(recorder);
            Unit unit = Unit.INSTANCE;
        }

        public final void stopService(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) AudioRecordingService.class);
            intent.setAction(AudioRecordingService.ACTION_STOP_RECORDING);
            context.startService(intent);
        }
    }
}
