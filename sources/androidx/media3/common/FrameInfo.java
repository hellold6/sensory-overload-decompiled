package androidx.media3.common;

import androidx.media3.common.util.Assertions;

/* loaded from: classes.dex */
public class FrameInfo {
    public final Format format;
    public final long offsetToAddUs;

    public FrameInfo(Format format, long j) {
        Assertions.checkArgument(format.colorInfo != null, "format colorInfo must be set");
        Assertions.checkArgument(format.width > 0, "format width must be positive, but is: " + format.width);
        Assertions.checkArgument(format.height > 0, "format height must be positive, but is: " + format.height);
        this.format = format;
        this.offsetToAddUs = j;
    }
}
