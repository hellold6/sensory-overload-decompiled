package expo.modules.medialibrary.next.objects.asset.delegates;

import android.net.Uri;
import android.os.Bundle;
import expo.modules.medialibrary.next.objects.asset.Asset;
import expo.modules.medialibrary.next.objects.wrappers.MediaType;
import expo.modules.medialibrary.next.objects.wrappers.MimeType;
import expo.modules.medialibrary.next.records.AssetInfo;
import expo.modules.medialibrary.next.records.Location;
import expo.modules.medialibrary.next.records.Shape;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: AssetDelegate.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007H¦@¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007H¦@¢\u0006\u0002\u0010\bJ\u000e\u0010\n\u001a\u00020\u000bH¦@¢\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\u00020\rH¦@¢\u0006\u0002\u0010\bJ\u000e\u0010\u000e\u001a\u00020\rH¦@¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010H¦@¢\u0006\u0002\u0010\bJ\u000e\u0010\u0011\u001a\u00020\u0012H¦@¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007H¦@¢\u0006\u0002\u0010\bJ\u000e\u0010\u0014\u001a\u00020\u0003H¦@¢\u0006\u0002\u0010\bJ\u000e\u0010\u0015\u001a\u00020\u0016H¦@¢\u0006\u0002\u0010\bJ\u0010\u0010\u0017\u001a\u00020\u0018H¦@¢\u0006\u0004\b\u0019\u0010\bJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bH¦@¢\u0006\u0002\u0010\bJ\u000e\u0010\u001c\u001a\u00020\u001dH¦@¢\u0006\u0002\u0010\bJ\u000e\u0010\u001e\u001a\u00020\u001fH¦@¢\u0006\u0002\u0010\bJ\u0018\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"H¦@¢\u0006\u0004\b#\u0010$J\u0018\u0010%\u001a\u00020&2\u0006\u0010!\u001a\u00020\"H¦@¢\u0006\u0004\b'\u0010$R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006("}, d2 = {"Lexpo/modules/medialibrary/next/objects/asset/delegates/AssetDelegate;", "", "contentUri", "Landroid/net/Uri;", "getContentUri", "()Landroid/net/Uri;", "getCreationTime", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDuration", "getFilename", "", "getHeight", "", "getWidth", "getShape", "Lexpo/modules/medialibrary/next/records/Shape;", "getMediaType", "Lexpo/modules/medialibrary/next/objects/wrappers/MediaType;", "getModificationTime", "getUri", "getInfo", "Lexpo/modules/medialibrary/next/records/AssetInfo;", "getMimeType", "Lexpo/modules/medialibrary/next/objects/wrappers/MimeType;", "getMimeType-dctPOJs", "getLocation", "Lexpo/modules/medialibrary/next/records/Location;", "getExif", "Landroid/os/Bundle;", "delete", "", "move", "relativePath", "Lexpo/modules/medialibrary/next/objects/wrappers/RelativePath;", "move-dXLngQ8", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copy", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "copy-dXLngQ8", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface AssetDelegate {
    /* renamed from: copy-dXLngQ8, reason: not valid java name */
    Object mo1294copydXLngQ8(String str, Continuation<? super Asset> continuation);

    Object delete(Continuation<? super Unit> continuation);

    Uri getContentUri();

    Object getCreationTime(Continuation<? super Long> continuation);

    Object getDuration(Continuation<? super Long> continuation);

    Object getExif(Continuation<? super Bundle> continuation);

    Object getFilename(Continuation<? super String> continuation);

    Object getHeight(Continuation<? super Integer> continuation);

    Object getInfo(Continuation<? super AssetInfo> continuation);

    Object getLocation(Continuation<? super Location> continuation);

    Object getMediaType(Continuation<? super MediaType> continuation);

    /* renamed from: getMimeType-dctPOJs, reason: not valid java name */
    Object mo1295getMimeTypedctPOJs(Continuation<? super MimeType> continuation);

    Object getModificationTime(Continuation<? super Long> continuation);

    Object getShape(Continuation<? super Shape> continuation);

    Object getUri(Continuation<? super Uri> continuation);

    Object getWidth(Continuation<? super Integer> continuation);

    /* renamed from: move-dXLngQ8, reason: not valid java name */
    Object mo1296movedXLngQ8(String str, Continuation<? super Unit> continuation);
}
