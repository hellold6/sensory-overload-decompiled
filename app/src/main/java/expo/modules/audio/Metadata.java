package expo.modules.audio;

import com.facebook.react.modules.dialog.AlertFragment;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.net.URL;

/* compiled from: AudioRecords.kt */
@kotlin.Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lexpo/modules/audio/Metadata;", "Lexpo/modules/kotlin/records/Record;", AlertFragment.ARG_TITLE, "", "artist", "albumTitle", "artworkUrl", "Ljava/net/URL;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/net/URL;)V", "getTitle$annotations", "()V", "getTitle", "()Ljava/lang/String;", "getArtist$annotations", "getArtist", "getAlbumTitle$annotations", "getAlbumTitle", "getArtworkUrl$annotations", "getArtworkUrl", "()Ljava/net/URL;", "expo-audio_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Metadata implements Record {
    private final String albumTitle;
    private final String artist;
    private final URL artworkUrl;
    private final String title;

    @Field
    public static /* synthetic */ void getAlbumTitle$annotations() {
    }

    @Field
    public static /* synthetic */ void getArtist$annotations() {
    }

    @Field
    public static /* synthetic */ void getArtworkUrl$annotations() {
    }

    @Field
    public static /* synthetic */ void getTitle$annotations() {
    }

    public Metadata(String str, String str2, String str3, URL url) {
        this.title = str;
        this.artist = str2;
        this.albumTitle = str3;
        this.artworkUrl = url;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final String getAlbumTitle() {
        return this.albumTitle;
    }

    public final URL getArtworkUrl() {
        return this.artworkUrl;
    }
}
