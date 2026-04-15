package expo.modules.audio;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.messaging.Constants;
import expo.modules.audio.service.AudioRecordingService;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.sharedobjects.SharedObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: AudioRecorder.kt */
@kotlin.Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\n\u0010-\u001a\u0004\u0018\u00010\rH\u0002J\u000f\u0010.\u001a\u0004\u0018\u00010/H\u0002¢\u0006\u0002\u00100J\u0010\u00101\u001a\u0002022\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0006\u00103\u001a\u000202J#\u00104\u001a\u0002022\n\b\u0002\u00105\u001a\u0004\u0018\u00010/2\n\b\u0002\u00106\u001a\u0004\u0018\u00010/¢\u0006\u0002\u00107J\u000e\u00108\u001a\u0002022\u0006\u00109\u001a\u00020/J\u000e\u0010:\u001a\u0002022\u0006\u00109\u001a\u00020/J\u0006\u0010;\u001a\u000202J\u0006\u0010<\u001a\u00020=J\b\u0010>\u001a\u000202H\u0002J\u0010\u0010?\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010@\u001a\u0002022\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010A\u001a\u000202H\u0016J\u0006\u0010B\u001a\u00020=J\b\u0010C\u001a\u00020\u0015H\u0002J\"\u0010D\u001a\u0002022\b\u0010E\u001a\u0004\u0018\u00010\u001b2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020GH\u0016J\"\u0010I\u001a\u0002022\b\u0010E\u001a\u0004\u0018\u00010\u001b2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020GH\u0016J\u000e\u0010J\u001a\u00020=2\u0006\u0010K\u001a\u00020LJ\b\u0010M\u001a\u00020\u0013H\u0002J\u0014\u0010N\u001a\b\u0012\u0004\u0012\u00020=0O2\u0006\u0010K\u001a\u00020LJ\u0016\u0010P\u001a\u0002022\u0006\u0010Q\u001a\u00020\r2\u0006\u0010K\u001a\u00020LJ\u001a\u0010R\u001a\u0004\u0018\u00010S2\u0006\u0010Q\u001a\u00020\r2\u0006\u0010K\u001a\u00020LH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000fR\u001a\u0010\u001e\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0017\"\u0004\b$\u0010\u0019R\u001a\u0010%\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0017\"\u0004\b&\u0010\u0019R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010\u0019R\u000e\u0010,\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lexpo/modules/audio/AudioRecorder;", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "Landroid/media/MediaRecorder$OnErrorListener;", "Landroid/media/MediaRecorder$OnInfoListener;", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", "options", "Lexpo/modules/audio/RecordingOptions;", "<init>", "(Landroid/content/Context;Lexpo/modules/kotlin/AppContext;Lexpo/modules/audio/RecordingOptions;)V", "filePath", "", "getFilePath", "()Ljava/lang/String;", "setFilePath", "(Ljava/lang/String;)V", "meteringEnabled", "", "durationAlreadyRecorded", "", "isPrepared", "()Z", "setPrepared", "(Z)V", "recorder", "Landroid/media/MediaRecorder;", "id", "getId", "startTime", "getStartTime", "()J", "setStartTime", "(J)V", "isRecording", "setRecording", "isPaused", "setPaused", "recordingTimerJob", "Lkotlinx/coroutines/Job;", "useForegroundService", "getUseForegroundService", "setUseForegroundService", "isRegisteredWithService", "currentFileUrl", "getAudioRecorderLevels", "", "()Ljava/lang/Double;", "prepareRecording", "", "record", "recordWithOptions", "atTimeSeconds", "forDurationSeconds", "(Ljava/lang/Double;Ljava/lang/Double;)V", "recordForDuration", "seconds", "startRecordingAtTime", "pauseRecording", "stopRecording", "Landroid/os/Bundle;", "reset", "createRecorder", "setRecordingOptions", "sharedObjectDidRelease", "getAudioRecorderStatus", "getAudioRecorderDurationMillis", "onError", "mr", "what", "", "extra", "onInfo", "getCurrentInput", "audioManager", "Landroid/media/AudioManager;", "hasRecordingPermissions", "getAvailableInputs", "", "setInput", "uid", "getDeviceInfoFromUid", "Landroid/media/AudioDeviceInfo;", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioRecorder extends SharedObject implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {
    private final Context context;
    private long durationAlreadyRecorded;
    private String filePath;
    private final String id;
    private boolean isPaused;
    private boolean isPrepared;
    private boolean isRecording;
    private boolean isRegisteredWithService;
    private boolean meteringEnabled;
    private final RecordingOptions options;
    private MediaRecorder recorder;
    private Job recordingTimerJob;
    private long startTime;
    private boolean useForegroundService;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioRecorder(Context context, AppContext appContext, RecordingOptions options) {
        super(appContext);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(options, "options");
        this.context = context;
        this.options = options;
        this.meteringEnabled = options.isMeteringEnabled();
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        this.id = uuid;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final void setFilePath(String str) {
        this.filePath = str;
    }

    /* renamed from: isPrepared, reason: from getter */
    public final boolean getIsPrepared() {
        return this.isPrepared;
    }

    public final void setPrepared(boolean z) {
        this.isPrepared = z;
    }

    public final String getId() {
        return this.id;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long j) {
        this.startTime = j;
    }

    /* renamed from: isRecording, reason: from getter */
    public final boolean getIsRecording() {
        return this.isRecording;
    }

    public final void setRecording(boolean z) {
        this.isRecording = z;
    }

    /* renamed from: isPaused, reason: from getter */
    public final boolean getIsPaused() {
        return this.isPaused;
    }

    public final void setPaused(boolean z) {
        this.isPaused = z;
    }

    public final boolean getUseForegroundService() {
        return this.useForegroundService;
    }

    public final void setUseForegroundService(boolean z) {
        this.useForegroundService = z;
    }

    private final String currentFileUrl() {
        Uri fromFile;
        String str = this.filePath;
        if (str == null || (fromFile = Uri.fromFile(new File(str))) == null) {
            return null;
        }
        return fromFile.toString();
    }

    private final Double getAudioRecorderLevels() {
        MediaRecorder mediaRecorder;
        if (!this.meteringEnabled || (mediaRecorder = this.recorder) == null || !this.isRecording) {
            return null;
        }
        int i = 0;
        if (mediaRecorder != null) {
            try {
                i = mediaRecorder.getMaxAmplitude();
            } catch (Exception unused) {
            }
        }
        if (i == 0) {
            return Double.valueOf(-160.0d);
        }
        return Double.valueOf(20 * Math.log10(i / 32767.0d));
    }

    public final void prepareRecording(RecordingOptions options) {
        if (this.recorder != null || this.isPrepared || this.isRecording || this.isPaused) {
            throw new AudioRecorderAlreadyPreparedException();
        }
        if (options == null) {
            options = this.options;
        }
        MediaRecorder createRecorder = createRecorder(options);
        this.recorder = createRecorder;
        try {
            createRecorder.prepare();
            this.isPrepared = true;
        } catch (Exception e) {
            createRecorder.release();
            this.recorder = null;
            this.isPrepared = false;
            throw new AudioRecorderPrepareException(e);
        }
    }

    public final void record() {
        if (this.isPaused) {
            MediaRecorder mediaRecorder = this.recorder;
            if (mediaRecorder != null) {
                mediaRecorder.resume();
            }
        } else {
            MediaRecorder mediaRecorder2 = this.recorder;
            if (mediaRecorder2 != null) {
                mediaRecorder2.start();
            }
        }
        this.startTime = System.currentTimeMillis();
        this.isRecording = true;
        this.isPaused = false;
        if (!this.useForegroundService || this.isRegisteredWithService) {
            return;
        }
        AudioRecordingService.INSTANCE.startService(this.context, this);
        this.isRegisteredWithService = true;
    }

    public static /* synthetic */ void recordWithOptions$default(AudioRecorder audioRecorder, Double d, Double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = null;
        }
        if ((i & 2) != 0) {
            d2 = null;
        }
        audioRecorder.recordWithOptions(d, d2);
    }

    public final void recordWithOptions(Double atTimeSeconds, Double forDurationSeconds) {
        CoroutineScope mainQueue;
        Job job = this.recordingTimerJob;
        Job job2 = null;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (forDurationSeconds != null) {
            double doubleValue = forDurationSeconds.doubleValue();
            record();
            AppContext appContext = getAppContext();
            if (appContext != null && (mainQueue = appContext.getMainQueue()) != null) {
                job2 = BuildersKt__Builders_commonKt.launch$default(mainQueue, null, null, new AudioRecorder$recordWithOptions$1$1(doubleValue, this, null), 3, null);
            }
            this.recordingTimerJob = job2;
            return;
        }
        record();
    }

    public final void recordForDuration(double seconds) {
        recordWithOptions$default(this, null, Double.valueOf(seconds), 1, null);
    }

    public final void startRecordingAtTime(double seconds) {
        recordWithOptions$default(this, Double.valueOf(seconds), null, 2, null);
    }

    public final void pauseRecording() {
        MediaRecorder mediaRecorder = this.recorder;
        if (mediaRecorder != null) {
            mediaRecorder.pause();
        }
        this.durationAlreadyRecorded = getAudioRecorderDurationMillis();
        this.isRecording = false;
        this.isPaused = true;
    }

    public final Bundle stopRecording() {
        CoroutineScope mainQueue;
        String currentFileUrl = currentFileUrl();
        if (this.useForegroundService && this.isRegisteredWithService) {
            AudioRecordingService companion = AudioRecordingService.INSTANCE.getInstance();
            if (companion != null) {
                companion.unregisterRecorder(this);
            }
            this.isRegisteredWithService = false;
        }
        try {
            MediaRecorder mediaRecorder = this.recorder;
            if (mediaRecorder != null) {
                mediaRecorder.stop();
            }
            long audioRecorderDurationMillis = getAudioRecorderDurationMillis();
            reset();
            Bundle bundle = new Bundle();
            bundle.putBoolean("canRecord", false);
            bundle.putBoolean("isRecording", false);
            bundle.putLong("durationMillis", audioRecorderDurationMillis);
            if (currentFileUrl != null) {
                bundle.putString(ImagesContract.URL, currentFileUrl);
            }
            AppContext appContext = getAppContext();
            if (appContext != null && (mainQueue = appContext.getMainQueue()) != null) {
                BuildersKt__Builders_commonKt.launch$default(mainQueue, null, null, new AudioRecorder$stopRecording$1(this, currentFileUrl, null), 3, null);
            }
            return bundle;
        } catch (Throwable th) {
            reset();
            throw th;
        }
    }

    private final void reset() {
        Job job = this.recordingTimerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.recordingTimerJob = null;
        MediaRecorder mediaRecorder = this.recorder;
        if (mediaRecorder != null) {
            mediaRecorder.release();
        }
        this.recorder = null;
        this.isRecording = false;
        this.isPaused = false;
        this.durationAlreadyRecorded = 0L;
        this.startTime = 0L;
        this.isPrepared = false;
    }

    private final MediaRecorder createRecorder(RecordingOptions options) {
        MediaRecorder mediaRecorder;
        if (Build.VERSION.SDK_INT >= 31) {
            mediaRecorder = new MediaRecorder(this.context);
        } else {
            mediaRecorder = new MediaRecorder();
        }
        setRecordingOptions(mediaRecorder, options);
        return mediaRecorder;
    }

    private final void setRecordingOptions(MediaRecorder recorder, RecordingOptions options) {
        if (hasRecordingPermissions()) {
            RecordingSource audioSource = options.getAudioSource();
            recorder.setAudioSource(audioSource != null ? audioSource.toAudioSource() : 1);
            if (options.getOutputFormat() != null) {
                recorder.setOutputFormat(options.getOutputFormat().toMediaOutputFormat());
            } else {
                recorder.setOutputFormat(0);
            }
            if (options.getAudioEncoder() != null) {
                recorder.setAudioEncoder(options.getAudioEncoder().toMediaEncoding());
            } else {
                recorder.setAudioEncoder(0);
            }
            Double sampleRate = options.getSampleRate();
            if (sampleRate != null) {
                recorder.setAudioSamplingRate((int) sampleRate.doubleValue());
            }
            Double numberOfChannels = options.getNumberOfChannels();
            if (numberOfChannels != null) {
                recorder.setAudioChannels((int) numberOfChannels.doubleValue());
            }
            Double bitRate = options.getBitRate();
            if (bitRate != null) {
                recorder.setAudioEncodingBitRate((int) bitRate.doubleValue());
            }
            if (options.getMaxFileSize() != null) {
                recorder.setMaxFileSize(r0.intValue());
            }
            String str = "recording-" + UUID.randomUUID() + options.getExtension();
            try {
                File file = new File(this.context.getCacheDir(), "Audio");
                AudioUtilsKt.ensureDirExists(file);
                this.filePath = new File(file, str).getAbsolutePath();
            } catch (IOException unused) {
            }
            recorder.setOnErrorListener(this);
            recorder.setOnInfoListener(this);
            recorder.setOutputFile(this.filePath);
            this.isPrepared = false;
        }
    }

    @Override // expo.modules.kotlin.sharedobjects.SharedObject
    public void sharedObjectDidRelease() {
        super.sharedObjectDidRelease();
        if (this.useForegroundService && this.isRegisteredWithService) {
            AudioRecordingService companion = AudioRecordingService.INSTANCE.getInstance();
            if (companion != null) {
                companion.unregisterRecorder(this);
            }
            this.isRegisteredWithService = false;
        }
        reset();
    }

    public final Bundle getAudioRecorderStatus() {
        if (hasRecordingPermissions()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("canRecord", this.isPrepared);
            bundle.putBoolean("isRecording", this.isRecording);
            bundle.putLong("durationMillis", getAudioRecorderDurationMillis());
            Double audioRecorderLevels = getAudioRecorderLevels();
            if (audioRecorderLevels != null) {
                bundle.putDouble("metering", audioRecorderLevels.doubleValue());
            }
            String currentFileUrl = currentFileUrl();
            if (currentFileUrl != null) {
                bundle.putString(ImagesContract.URL, currentFileUrl);
            }
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("canRecord", false);
        bundle2.putBoolean("isRecording", false);
        bundle2.putLong("durationMillis", 0L);
        bundle2.putString(ImagesContract.URL, null);
        return bundle2;
    }

    private final long getAudioRecorderDurationMillis() {
        long j = this.durationAlreadyRecorded;
        return this.isRecording ? j + (System.currentTimeMillis() - this.startTime) : j;
    }

    @Override // android.media.MediaRecorder.OnErrorListener
    public void onError(MediaRecorder mr, int what, int extra) {
        String str = "An unknown recording error occurred";
        if (what != 1 && what == 100) {
            str = "The media server has crashed";
        }
        emit("recordingStatusUpdate", MapsKt.mapOf(TuplesKt.to("isFinished", true), TuplesKt.to("hasError", true), TuplesKt.to(Constants.IPC_BUNDLE_KEY_SEND_ERROR, str), TuplesKt.to(ImagesContract.URL, null)));
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder mr, int what, int extra) {
        if (what == 801) {
            MediaRecorder mediaRecorder = this.recorder;
            if (mediaRecorder != null) {
                mediaRecorder.stop();
            }
            emit("recordingStatusUpdate", MapsKt.mapOf(TuplesKt.to("isFinished", true), TuplesKt.to("hasError", true), TuplesKt.to(Constants.IPC_BUNDLE_KEY_SEND_ERROR, null), TuplesKt.to(ImagesContract.URL, currentFileUrl())));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.os.Bundle getCurrentInput(android.media.AudioManager r5) {
        /*
            r4 = this;
            java.lang.String r0 = "audioManager"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L59
            boolean r0 = r4.isRecording
            r1 = 0
            if (r0 == 0) goto L19
            android.media.MediaRecorder r0 = r4.recorder     // Catch: java.lang.Exception -> L19
            if (r0 == 0) goto L19
            android.media.AudioDeviceInfo r0 = r0.getRoutedDevice()     // Catch: java.lang.Exception -> L19
            goto L1a
        L19:
            r0 = r1
        L1a:
            if (r0 != 0) goto L25
            android.media.MediaRecorder r0 = r4.recorder
            if (r0 == 0) goto L24
            android.media.AudioDeviceInfo r1 = r0.getPreferredDevice()
        L24:
            r0 = r1
        L25:
            if (r0 != 0) goto L4c
            r1 = 1
            android.media.AudioDeviceInfo[] r5 = r5.getDevices(r1)
            java.util.Iterator r5 = kotlin.jvm.internal.ArrayIteratorKt.iterator(r5)
        L30:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L4c
            java.lang.Object r1 = r5.next()
            android.media.AudioDeviceInfo r1 = (android.media.AudioDeviceInfo) r1
            int r2 = r1.getType()
            r3 = 15
            if (r2 != r3) goto L30
            android.media.MediaRecorder r5 = r4.recorder
            if (r5 == 0) goto L4b
            r5.setPreferredDevice(r1)
        L4b:
            r0 = r1
        L4c:
            if (r0 == 0) goto L53
            android.os.Bundle r5 = expo.modules.audio.AudioUtilsKt.getMapFromDeviceInfo(r0)
            return r5
        L53:
            expo.modules.audio.DeviceInfoNotFoundException r5 = new expo.modules.audio.DeviceInfoNotFoundException
            r5.<init>()
            throw r5
        L59:
            expo.modules.audio.GetAudioInputNotSupportedException r5 = new expo.modules.audio.GetAudioInputNotSupportedException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.audio.AudioRecorder.getCurrentInput(android.media.AudioManager):android.os.Bundle");
    }

    private final boolean hasRecordingPermissions() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.RECORD_AUDIO") == 0;
    }

    public final List<Bundle> getAvailableInputs(AudioManager audioManager) {
        Bundle mapFromDeviceInfo;
        Intrinsics.checkNotNullParameter(audioManager, "audioManager");
        AudioDeviceInfo[] devices = audioManager.getDevices(1);
        Intrinsics.checkNotNullExpressionValue(devices, "getDevices(...)");
        ArrayList arrayList = new ArrayList();
        for (AudioDeviceInfo audioDeviceInfo : devices) {
            int type = audioDeviceInfo.getType();
            if (type == 3 || type == 7 || type == 15) {
                Intrinsics.checkNotNull(audioDeviceInfo);
                mapFromDeviceInfo = AudioUtilsKt.getMapFromDeviceInfo(audioDeviceInfo);
            } else {
                mapFromDeviceInfo = null;
            }
            if (mapFromDeviceInfo != null) {
                arrayList.add(mapFromDeviceInfo);
            }
        }
        return arrayList;
    }

    public final void setInput(String uid, AudioManager audioManager) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(audioManager, "audioManager");
        AudioDeviceInfo deviceInfoFromUid = getDeviceInfoFromUid(uid, audioManager);
        if (Build.VERSION.SDK_INT <= 28) {
            throw new SetAudioInputNotSupportedException();
        }
        if (deviceInfoFromUid != null && deviceInfoFromUid.getType() == 7) {
            if (Build.VERSION.SDK_INT >= 31) {
                audioManager.setCommunicationDevice(deviceInfoFromUid);
            } else {
                audioManager.startBluetoothSco();
            }
        } else if (Build.VERSION.SDK_INT >= 31) {
            audioManager.clearCommunicationDevice();
        } else {
            audioManager.stopBluetoothSco();
        }
        MediaRecorder mediaRecorder = this.recorder;
        if (Intrinsics.areEqual((Object) (mediaRecorder != null ? Boolean.valueOf(mediaRecorder.setPreferredDevice(deviceInfoFromUid)) : null), (Object) false)) {
            throw new PreferredInputNotFoundException();
        }
    }

    private final AudioDeviceInfo getDeviceInfoFromUid(String uid, AudioManager audioManager) {
        int parseInt = Integer.parseInt(uid);
        AudioDeviceInfo[] devices = audioManager.getDevices(1);
        Intrinsics.checkNotNullExpressionValue(devices, "getDevices(...)");
        for (AudioDeviceInfo audioDeviceInfo : devices) {
            if (audioDeviceInfo.getId() == parseInt) {
                return audioDeviceInfo;
            }
        }
        return null;
    }
}
