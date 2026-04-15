package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.C;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class SessionPositionInfo {
    public static final SessionPositionInfo DEFAULT;
    public static final Player.PositionInfo DEFAULT_POSITION_INFO;
    private static final String FIELD_BUFFERED_PERCENTAGE;
    static final String FIELD_BUFFERED_POSITION_MS;
    static final String FIELD_CONTENT_BUFFERED_POSITION_MS;
    private static final String FIELD_CONTENT_DURATION_MS;
    private static final String FIELD_CURRENT_LIVE_OFFSET_MS;
    private static final String FIELD_DURATION_MS;
    private static final String FIELD_EVENT_TIME_MS;
    private static final String FIELD_IS_PLAYING_AD;
    static final String FIELD_POSITION_INFO;
    private static final String FIELD_TOTAL_BUFFERED_DURATION_MS;
    public final int bufferedPercentage;
    public final long bufferedPositionMs;
    public final long contentBufferedPositionMs;
    public final long contentDurationMs;
    public final long currentLiveOffsetMs;
    public final long durationMs;
    public final long eventTimeMs;
    public final boolean isPlayingAd;
    public final Player.PositionInfo positionInfo;
    public final long totalBufferedDurationMs;

    static {
        Player.PositionInfo positionInfo = new Player.PositionInfo(null, 0, null, null, 0, 0L, 0L, -1, -1);
        DEFAULT_POSITION_INFO = positionInfo;
        DEFAULT = new SessionPositionInfo(positionInfo, false, C.TIME_UNSET, C.TIME_UNSET, 0L, 0, 0L, C.TIME_UNSET, C.TIME_UNSET, 0L);
        FIELD_POSITION_INFO = Util.intToStringMaxRadix(0);
        FIELD_IS_PLAYING_AD = Util.intToStringMaxRadix(1);
        FIELD_EVENT_TIME_MS = Util.intToStringMaxRadix(2);
        FIELD_DURATION_MS = Util.intToStringMaxRadix(3);
        FIELD_BUFFERED_POSITION_MS = Util.intToStringMaxRadix(4);
        FIELD_BUFFERED_PERCENTAGE = Util.intToStringMaxRadix(5);
        FIELD_TOTAL_BUFFERED_DURATION_MS = Util.intToStringMaxRadix(6);
        FIELD_CURRENT_LIVE_OFFSET_MS = Util.intToStringMaxRadix(7);
        FIELD_CONTENT_DURATION_MS = Util.intToStringMaxRadix(8);
        FIELD_CONTENT_BUFFERED_POSITION_MS = Util.intToStringMaxRadix(9);
    }

    public SessionPositionInfo(Player.PositionInfo positionInfo, boolean z, long j, long j2, long j3, int i, long j4, long j5, long j6, long j7) {
        Assertions.checkArgument(z == (positionInfo.adGroupIndex != -1));
        this.positionInfo = positionInfo;
        this.isPlayingAd = z;
        this.eventTimeMs = j;
        this.durationMs = j2;
        this.bufferedPositionMs = j3;
        this.bufferedPercentage = i;
        this.totalBufferedDurationMs = j4;
        this.currentLiveOffsetMs = j5;
        this.contentDurationMs = j6;
        this.contentBufferedPositionMs = j7;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SessionPositionInfo sessionPositionInfo = (SessionPositionInfo) obj;
            if (this.eventTimeMs == sessionPositionInfo.eventTimeMs && this.positionInfo.equals(sessionPositionInfo.positionInfo) && this.isPlayingAd == sessionPositionInfo.isPlayingAd && this.durationMs == sessionPositionInfo.durationMs && this.bufferedPositionMs == sessionPositionInfo.bufferedPositionMs && this.bufferedPercentage == sessionPositionInfo.bufferedPercentage && this.totalBufferedDurationMs == sessionPositionInfo.totalBufferedDurationMs && this.currentLiveOffsetMs == sessionPositionInfo.currentLiveOffsetMs && this.contentDurationMs == sessionPositionInfo.contentDurationMs && this.contentBufferedPositionMs == sessionPositionInfo.contentBufferedPositionMs) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.positionInfo, Boolean.valueOf(this.isPlayingAd));
    }

    public String toString() {
        return "SessionPositionInfo {PositionInfo {mediaItemIndex=" + this.positionInfo.mediaItemIndex + ", periodIndex=" + this.positionInfo.periodIndex + ", positionMs=" + this.positionInfo.positionMs + ", contentPositionMs=" + this.positionInfo.contentPositionMs + ", adGroupIndex=" + this.positionInfo.adGroupIndex + ", adIndexInAdGroup=" + this.positionInfo.adIndexInAdGroup + "}, isPlayingAd=" + this.isPlayingAd + ", eventTimeMs=" + this.eventTimeMs + ", durationMs=" + this.durationMs + ", bufferedPositionMs=" + this.bufferedPositionMs + ", bufferedPercentage=" + this.bufferedPercentage + ", totalBufferedDurationMs=" + this.totalBufferedDurationMs + ", currentLiveOffsetMs=" + this.currentLiveOffsetMs + ", contentDurationMs=" + this.contentDurationMs + ", contentBufferedPositionMs=" + this.contentBufferedPositionMs + "}";
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public androidx.media3.session.SessionPositionInfo filterByAvailableCommands(boolean r24, boolean r25) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            if (r1 == 0) goto Lb
            if (r2 == 0) goto Lb
            return r0
        Lb:
            androidx.media3.session.SessionPositionInfo r3 = new androidx.media3.session.SessionPositionInfo
            androidx.media3.common.Player$PositionInfo r4 = r0.positionInfo
            androidx.media3.common.Player$PositionInfo r2 = r4.filterByAvailableCommands(r1, r2)
            r4 = 0
            if (r1 == 0) goto L1c
            boolean r5 = r0.isPlayingAd
            if (r5 == 0) goto L1c
            r5 = 1
            goto L1d
        L1c:
            r5 = r4
        L1d:
            long r6 = r0.eventTimeMs
            if (r1 == 0) goto L24
            long r10 = r0.durationMs
            goto L29
        L24:
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L29:
            if (r1 == 0) goto L2e
            long r14 = r0.bufferedPositionMs
            goto L30
        L2e:
            r14 = 0
        L30:
            if (r1 == 0) goto L34
            int r4 = r0.bufferedPercentage
        L34:
            if (r1 == 0) goto L39
            long r8 = r0.totalBufferedDurationMs
            goto L3b
        L39:
            r8 = 0
        L3b:
            if (r1 == 0) goto L40
            long r12 = r0.currentLiveOffsetMs
            goto L45
        L40:
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L45:
            r25 = r2
            if (r1 == 0) goto L4e
            long r1 = r0.contentDurationMs
            r16 = r1
            goto L53
        L4e:
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L53:
            if (r24 == 0) goto L6c
            long r1 = r0.contentBufferedPositionMs
            r19 = r1
            r1 = r3
            r3 = r5
            r21 = r10
            r10 = r4
            r4 = r6
            r6 = r21
            r21 = r16
            r17 = r19
            r19 = r12
            r11 = r8
            r8 = r14
            r15 = r21
            goto L7c
        L6c:
            r1 = r3
            r3 = r5
            r19 = r10
            r10 = r4
            r4 = r6
            r6 = r19
            r19 = r12
            r11 = r8
            r8 = r14
            r15 = r16
            r17 = 0
        L7c:
            r2 = r25
            r13 = r19
            r1.<init>(r2, r3, r4, r6, r8, r10, r11, r13, r15, r17)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.SessionPositionInfo.filterByAvailableCommands(boolean, boolean):androidx.media3.session.SessionPositionInfo");
    }

    public Bundle toBundle(int i) {
        Bundle bundle = new Bundle();
        if (i < 3 || !DEFAULT_POSITION_INFO.equalsForBundling(this.positionInfo)) {
            bundle.putBundle(FIELD_POSITION_INFO, this.positionInfo.toBundle(i));
        }
        boolean z = this.isPlayingAd;
        if (z) {
            bundle.putBoolean(FIELD_IS_PLAYING_AD, z);
        }
        long j = this.eventTimeMs;
        if (j != C.TIME_UNSET) {
            bundle.putLong(FIELD_EVENT_TIME_MS, j);
        }
        long j2 = this.durationMs;
        if (j2 != C.TIME_UNSET) {
            bundle.putLong(FIELD_DURATION_MS, j2);
        }
        if (i < 3 || this.bufferedPositionMs != 0) {
            bundle.putLong(FIELD_BUFFERED_POSITION_MS, this.bufferedPositionMs);
        }
        int i2 = this.bufferedPercentage;
        if (i2 != 0) {
            bundle.putInt(FIELD_BUFFERED_PERCENTAGE, i2);
        }
        long j3 = this.totalBufferedDurationMs;
        if (j3 != 0) {
            bundle.putLong(FIELD_TOTAL_BUFFERED_DURATION_MS, j3);
        }
        long j4 = this.currentLiveOffsetMs;
        if (j4 != C.TIME_UNSET) {
            bundle.putLong(FIELD_CURRENT_LIVE_OFFSET_MS, j4);
        }
        long j5 = this.contentDurationMs;
        if (j5 != C.TIME_UNSET) {
            bundle.putLong(FIELD_CONTENT_DURATION_MS, j5);
        }
        if (i >= 3 && this.contentBufferedPositionMs == 0) {
            return bundle;
        }
        bundle.putLong(FIELD_CONTENT_BUFFERED_POSITION_MS, this.contentBufferedPositionMs);
        return bundle;
    }

    public static SessionPositionInfo fromBundle(Bundle bundle) {
        Player.PositionInfo fromBundle;
        Bundle bundle2 = bundle.getBundle(FIELD_POSITION_INFO);
        if (bundle2 == null) {
            fromBundle = DEFAULT_POSITION_INFO;
        } else {
            fromBundle = Player.PositionInfo.fromBundle(bundle2);
        }
        return new SessionPositionInfo(fromBundle, bundle.getBoolean(FIELD_IS_PLAYING_AD, false), bundle.getLong(FIELD_EVENT_TIME_MS, C.TIME_UNSET), bundle.getLong(FIELD_DURATION_MS, C.TIME_UNSET), bundle.getLong(FIELD_BUFFERED_POSITION_MS, 0L), bundle.getInt(FIELD_BUFFERED_PERCENTAGE, 0), bundle.getLong(FIELD_TOTAL_BUFFERED_DURATION_MS, 0L), bundle.getLong(FIELD_CURRENT_LIVE_OFFSET_MS, C.TIME_UNSET), bundle.getLong(FIELD_CONTENT_DURATION_MS, C.TIME_UNSET), bundle.getLong(FIELD_CONTENT_BUFFERED_POSITION_MS, 0L));
    }
}
