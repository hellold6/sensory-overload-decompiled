package expo.modules.video.records;

import androidx.media3.common.Format;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Tracks.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0007\u0018\u0000 )2\u00020\u00012\u00020\u0002:\u0001)BO\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u001a\u0010\u0015R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0013\u001a\u0004\b\b\u0010\u001cR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010 \u0012\u0004\b\u001d\u0010\u0013\u001a\u0004\b\u001e\u0010\u001fR \u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010$\u0012\u0004\b!\u0010\u0013\u001a\u0004\b\"\u0010#R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006*"}, d2 = {"Lexpo/modules/video/records/VideoTrack;", "Lexpo/modules/kotlin/records/Record;", "Ljava/io/Serializable;", "id", "", "size", "Lexpo/modules/video/records/VideoSize;", "mimeType", "isSupported", "", "bitrate", "", "frameRate", "", "format", "Landroidx/media3/common/Format;", "<init>", "(Ljava/lang/String;Lexpo/modules/video/records/VideoSize;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Float;Landroidx/media3/common/Format;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "getSize$annotations", "getSize", "()Lexpo/modules/video/records/VideoSize;", "getMimeType$annotations", "getMimeType", "isSupported$annotations", "()Z", "getBitrate$annotations", "getBitrate", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getFrameRate$annotations", "getFrameRate", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getFormat", "()Landroidx/media3/common/Format;", "setFormat", "(Landroidx/media3/common/Format;)V", "Companion", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoTrack implements Record, Serializable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer bitrate;
    private Format format;
    private final Float frameRate;
    private final String id;
    private final boolean isSupported;
    private final String mimeType;
    private final VideoSize size;

    @Field
    public static /* synthetic */ void getBitrate$annotations() {
    }

    @Field
    public static /* synthetic */ void getFrameRate$annotations() {
    }

    @Field
    public static /* synthetic */ void getId$annotations() {
    }

    @Field
    public static /* synthetic */ void getMimeType$annotations() {
    }

    @Field
    public static /* synthetic */ void getSize$annotations() {
    }

    @Field
    public static /* synthetic */ void isSupported$annotations() {
    }

    public VideoTrack(String id, VideoSize size, String str, boolean z, Integer num, Float f, Format format) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(size, "size");
        this.id = id;
        this.size = size;
        this.mimeType = str;
        this.isSupported = z;
        this.bitrate = num;
        this.frameRate = f;
        this.format = format;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ VideoTrack(java.lang.String r9, expo.modules.video.records.VideoSize r10, java.lang.String r11, boolean r12, java.lang.Integer r13, java.lang.Float r14, androidx.media3.common.Format r15, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*
            r8 = this;
            r0 = r16 & 8
            if (r0 == 0) goto L5
            r12 = 1
        L5:
            r4 = r12
            r12 = r16 & 16
            r0 = 0
            if (r12 == 0) goto Ld
            r5 = r0
            goto Le
        Ld:
            r5 = r13
        Le:
            r12 = r16 & 32
            if (r12 == 0) goto L14
            r6 = r0
            goto L15
        L14:
            r6 = r14
        L15:
            r12 = r16 & 64
            if (r12 == 0) goto L1f
            r7 = r0
            r1 = r9
            r2 = r10
            r3 = r11
            r0 = r8
            goto L24
        L1f:
            r7 = r15
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
        L24:
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.video.records.VideoTrack.<init>(java.lang.String, expo.modules.video.records.VideoSize, java.lang.String, boolean, java.lang.Integer, java.lang.Float, androidx.media3.common.Format, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getId() {
        return this.id;
    }

    public final VideoSize getSize() {
        return this.size;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    /* renamed from: isSupported, reason: from getter */
    public final boolean getIsSupported() {
        return this.isSupported;
    }

    public final Integer getBitrate() {
        return this.bitrate;
    }

    public final Float getFrameRate() {
        return this.frameRate;
    }

    public final Format getFormat() {
        return this.format;
    }

    public final void setFormat(Format format) {
        this.format = format;
    }

    /* compiled from: Tracks.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lexpo/modules/video/records/VideoTrack$Companion;", "", "<init>", "()V", "fromFormat", "Lexpo/modules/video/records/VideoTrack;", "format", "Landroidx/media3/common/Format;", "isSupported", "", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VideoTrack fromFormat(Format format, boolean isSupported) {
            String str;
            if (format == null || (str = format.id) == null) {
                return null;
            }
            VideoSize videoSize = new VideoSize(format);
            String str2 = format.sampleMimeType;
            Integer valueOf = Integer.valueOf(format.bitrate);
            Integer num = valueOf.intValue() != -1 ? valueOf : null;
            Float valueOf2 = Float.valueOf(format.frameRate);
            return new VideoTrack(str, videoSize, str2, isSupported, num, !((valueOf2.floatValue() > (-1.0f) ? 1 : (valueOf2.floatValue() == (-1.0f) ? 0 : -1)) == 0) ? valueOf2 : null, format);
        }
    }
}
