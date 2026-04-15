package expo.modules.video.player;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.Tracks;
import com.google.common.collect.UnmodifiableIterator;
import expo.modules.video.records.VideoTrack;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoPlayer.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0003¨\u0006\u0004"}, d2 = {"toVideoTracks", "", "Lexpo/modules/video/records/VideoTrack;", "Landroidx/media3/common/Tracks;", "expo-video_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoPlayerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final List<VideoTrack> toVideoTracks(Tracks tracks) {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<Tracks.Group> it = tracks.getGroups().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Tracks.Group next = it.next();
            int i = next.length;
            for (int i2 = 0; i2 < i; i2++) {
                Format trackFormat = next.getTrackFormat(i2);
                Intrinsics.checkNotNullExpressionValue(trackFormat, "getTrackFormat(...)");
                boolean isTrackSupported = next.isTrackSupported(i2);
                if (MimeTypes.isVideo(trackFormat.sampleMimeType)) {
                    arrayList.add(VideoTrack.INSTANCE.fromFormat(trackFormat, isTrackSupported));
                }
            }
        }
        return CollectionsKt.filterNotNull(arrayList);
    }
}
