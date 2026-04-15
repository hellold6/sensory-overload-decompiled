package expo.modules.audio;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioRecords.kt */
@kotlin.Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u00103\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010(J\t\u00106\u001a\u00020\u000fHÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0011HÆ\u0003Jv\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u00109J\u0013\u0010:\u001a\u00020\u000f2\b\u0010;\u001a\u0004\u0018\u00010<HÖ\u0003J\t\u0010=\u001a\u00020\rHÖ\u0001J\t\u0010>\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001b\u0012\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u001aR \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001b\u0012\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001d\u0010\u001aR \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001b\u0012\u0004\b\u001e\u0010\u0015\u001a\u0004\b\u001f\u0010\u001aR\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0015\u001a\u0004\b!\u0010\"R\u001e\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0015\u001a\u0004\b$\u0010%R \u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010)\u0012\u0004\b&\u0010\u0015\u001a\u0004\b'\u0010(R\u001c\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u0015\u001a\u0004\b\u000e\u0010+R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u0015\u001a\u0004\b-\u0010.¨\u0006?"}, d2 = {"Lexpo/modules/audio/RecordingOptions;", "Lexpo/modules/kotlin/records/Record;", "extension", "", "sampleRate", "", "numberOfChannels", "bitRate", "outputFormat", "Lexpo/modules/audio/AndroidOutputFormat;", "audioEncoder", "Lexpo/modules/audio/AndroidAudioEncoder;", "maxFileSize", "", "isMeteringEnabled", "", "audioSource", "Lexpo/modules/audio/RecordingSource;", "<init>", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Lexpo/modules/audio/AndroidOutputFormat;Lexpo/modules/audio/AndroidAudioEncoder;Ljava/lang/Integer;ZLexpo/modules/audio/RecordingSource;)V", "getExtension$annotations", "()V", "getExtension", "()Ljava/lang/String;", "getSampleRate$annotations", "getSampleRate", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getNumberOfChannels$annotations", "getNumberOfChannels", "getBitRate$annotations", "getBitRate", "getOutputFormat$annotations", "getOutputFormat", "()Lexpo/modules/audio/AndroidOutputFormat;", "getAudioEncoder$annotations", "getAudioEncoder", "()Lexpo/modules/audio/AndroidAudioEncoder;", "getMaxFileSize$annotations", "getMaxFileSize", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "isMeteringEnabled$annotations", "()Z", "getAudioSource$annotations", "getAudioSource", "()Lexpo/modules/audio/RecordingSource;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Lexpo/modules/audio/AndroidOutputFormat;Lexpo/modules/audio/AndroidAudioEncoder;Ljava/lang/Integer;ZLexpo/modules/audio/RecordingSource;)Lexpo/modules/audio/RecordingOptions;", "equals", "other", "", "hashCode", "toString", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class RecordingOptions implements Record {
    private final AndroidAudioEncoder audioEncoder;
    private final RecordingSource audioSource;
    private final Double bitRate;
    private final String extension;
    private final boolean isMeteringEnabled;
    private final Integer maxFileSize;
    private final Double numberOfChannels;
    private final AndroidOutputFormat outputFormat;
    private final Double sampleRate;

    public static /* synthetic */ RecordingOptions copy$default(RecordingOptions recordingOptions, String str, Double d, Double d2, Double d3, AndroidOutputFormat androidOutputFormat, AndroidAudioEncoder androidAudioEncoder, Integer num, boolean z, RecordingSource recordingSource, int i, Object obj) {
        if ((i & 1) != 0) {
            str = recordingOptions.extension;
        }
        if ((i & 2) != 0) {
            d = recordingOptions.sampleRate;
        }
        if ((i & 4) != 0) {
            d2 = recordingOptions.numberOfChannels;
        }
        if ((i & 8) != 0) {
            d3 = recordingOptions.bitRate;
        }
        if ((i & 16) != 0) {
            androidOutputFormat = recordingOptions.outputFormat;
        }
        if ((i & 32) != 0) {
            androidAudioEncoder = recordingOptions.audioEncoder;
        }
        if ((i & 64) != 0) {
            num = recordingOptions.maxFileSize;
        }
        if ((i & 128) != 0) {
            z = recordingOptions.isMeteringEnabled;
        }
        if ((i & 256) != 0) {
            recordingSource = recordingOptions.audioSource;
        }
        boolean z2 = z;
        RecordingSource recordingSource2 = recordingSource;
        AndroidAudioEncoder androidAudioEncoder2 = androidAudioEncoder;
        Integer num2 = num;
        AndroidOutputFormat androidOutputFormat2 = androidOutputFormat;
        Double d4 = d2;
        return recordingOptions.copy(str, d, d4, d3, androidOutputFormat2, androidAudioEncoder2, num2, z2, recordingSource2);
    }

    @Field
    public static /* synthetic */ void getAudioEncoder$annotations() {
    }

    @Field
    public static /* synthetic */ void getAudioSource$annotations() {
    }

    @Field
    public static /* synthetic */ void getBitRate$annotations() {
    }

    @Field
    public static /* synthetic */ void getExtension$annotations() {
    }

    @Field
    public static /* synthetic */ void getMaxFileSize$annotations() {
    }

    @Field
    public static /* synthetic */ void getNumberOfChannels$annotations() {
    }

    @Field
    public static /* synthetic */ void getOutputFormat$annotations() {
    }

    @Field
    public static /* synthetic */ void getSampleRate$annotations() {
    }

    @Field
    public static /* synthetic */ void isMeteringEnabled$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getExtension() {
        return this.extension;
    }

    /* renamed from: component2, reason: from getter */
    public final Double getSampleRate() {
        return this.sampleRate;
    }

    /* renamed from: component3, reason: from getter */
    public final Double getNumberOfChannels() {
        return this.numberOfChannels;
    }

    /* renamed from: component4, reason: from getter */
    public final Double getBitRate() {
        return this.bitRate;
    }

    /* renamed from: component5, reason: from getter */
    public final AndroidOutputFormat getOutputFormat() {
        return this.outputFormat;
    }

    /* renamed from: component6, reason: from getter */
    public final AndroidAudioEncoder getAudioEncoder() {
        return this.audioEncoder;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getMaxFileSize() {
        return this.maxFileSize;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getIsMeteringEnabled() {
        return this.isMeteringEnabled;
    }

    /* renamed from: component9, reason: from getter */
    public final RecordingSource getAudioSource() {
        return this.audioSource;
    }

    public final RecordingOptions copy(String extension, Double sampleRate, Double numberOfChannels, Double bitRate, AndroidOutputFormat outputFormat, AndroidAudioEncoder audioEncoder, Integer maxFileSize, boolean isMeteringEnabled, RecordingSource audioSource) {
        Intrinsics.checkNotNullParameter(extension, "extension");
        return new RecordingOptions(extension, sampleRate, numberOfChannels, bitRate, outputFormat, audioEncoder, maxFileSize, isMeteringEnabled, audioSource);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RecordingOptions)) {
            return false;
        }
        RecordingOptions recordingOptions = (RecordingOptions) other;
        return Intrinsics.areEqual(this.extension, recordingOptions.extension) && Intrinsics.areEqual((Object) this.sampleRate, (Object) recordingOptions.sampleRate) && Intrinsics.areEqual((Object) this.numberOfChannels, (Object) recordingOptions.numberOfChannels) && Intrinsics.areEqual((Object) this.bitRate, (Object) recordingOptions.bitRate) && this.outputFormat == recordingOptions.outputFormat && this.audioEncoder == recordingOptions.audioEncoder && Intrinsics.areEqual(this.maxFileSize, recordingOptions.maxFileSize) && this.isMeteringEnabled == recordingOptions.isMeteringEnabled && this.audioSource == recordingOptions.audioSource;
    }

    public int hashCode() {
        int hashCode = this.extension.hashCode() * 31;
        Double d = this.sampleRate;
        int hashCode2 = (hashCode + (d == null ? 0 : d.hashCode())) * 31;
        Double d2 = this.numberOfChannels;
        int hashCode3 = (hashCode2 + (d2 == null ? 0 : d2.hashCode())) * 31;
        Double d3 = this.bitRate;
        int hashCode4 = (hashCode3 + (d3 == null ? 0 : d3.hashCode())) * 31;
        AndroidOutputFormat androidOutputFormat = this.outputFormat;
        int hashCode5 = (hashCode4 + (androidOutputFormat == null ? 0 : androidOutputFormat.hashCode())) * 31;
        AndroidAudioEncoder androidAudioEncoder = this.audioEncoder;
        int hashCode6 = (hashCode5 + (androidAudioEncoder == null ? 0 : androidAudioEncoder.hashCode())) * 31;
        Integer num = this.maxFileSize;
        int hashCode7 = (((hashCode6 + (num == null ? 0 : num.hashCode())) * 31) + Boolean.hashCode(this.isMeteringEnabled)) * 31;
        RecordingSource recordingSource = this.audioSource;
        return hashCode7 + (recordingSource != null ? recordingSource.hashCode() : 0);
    }

    public String toString() {
        return "RecordingOptions(extension=" + this.extension + ", sampleRate=" + this.sampleRate + ", numberOfChannels=" + this.numberOfChannels + ", bitRate=" + this.bitRate + ", outputFormat=" + this.outputFormat + ", audioEncoder=" + this.audioEncoder + ", maxFileSize=" + this.maxFileSize + ", isMeteringEnabled=" + this.isMeteringEnabled + ", audioSource=" + this.audioSource + ")";
    }

    public RecordingOptions(String extension, Double d, Double d2, Double d3, AndroidOutputFormat androidOutputFormat, AndroidAudioEncoder androidAudioEncoder, Integer num, boolean z, RecordingSource recordingSource) {
        Intrinsics.checkNotNullParameter(extension, "extension");
        this.extension = extension;
        this.sampleRate = d;
        this.numberOfChannels = d2;
        this.bitRate = d3;
        this.outputFormat = androidOutputFormat;
        this.audioEncoder = androidAudioEncoder;
        this.maxFileSize = num;
        this.isMeteringEnabled = z;
        this.audioSource = recordingSource;
    }

    public /* synthetic */ RecordingOptions(String str, Double d, Double d2, Double d3, AndroidOutputFormat androidOutputFormat, AndroidAudioEncoder androidAudioEncoder, Integer num, boolean z, RecordingSource recordingSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, d, d2, d3, androidOutputFormat, androidAudioEncoder, num, (i & 128) != 0 ? false : z, recordingSource);
    }

    public final String getExtension() {
        return this.extension;
    }

    public final Double getSampleRate() {
        return this.sampleRate;
    }

    public final Double getNumberOfChannels() {
        return this.numberOfChannels;
    }

    public final Double getBitRate() {
        return this.bitRate;
    }

    public final AndroidOutputFormat getOutputFormat() {
        return this.outputFormat;
    }

    public final AndroidAudioEncoder getAudioEncoder() {
        return this.audioEncoder;
    }

    public final Integer getMaxFileSize() {
        return this.maxFileSize;
    }

    public final boolean isMeteringEnabled() {
        return this.isMeteringEnabled;
    }

    public final RecordingSource getAudioSource() {
        return this.audioSource;
    }
}
