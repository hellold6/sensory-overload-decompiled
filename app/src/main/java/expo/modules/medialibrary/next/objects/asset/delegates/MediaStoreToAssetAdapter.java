package expo.modules.medialibrary.next.objects.asset.delegates;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import expo.modules.medialibrary.next.exceptions.ContentResolverNotObtainedException;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.wrappers.MediaType;
import java.io.File;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MediaStoreToAssetAdapter.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J\"\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012JB\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u001e\u0010\u0017\u001a\u001a\b\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018H\u0082@¢\u0006\u0002\u0010\u001aJ*\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u000e0\u0018H\u0082@¢\u0006\u0002\u0010\u001eJ\u0017\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0002\u0010\"J\u0017\u0010#\u001a\u0004\u0018\u00010 2\b\u0010$\u001a\u0004\u0018\u00010 ¢\u0006\u0002\u0010\"J\u0017\u0010%\u001a\u0004\u0018\u00010 2\b\u0010&\u001a\u0004\u0018\u00010 ¢\u0006\u0002\u0010\"J\u0012\u0010'\u001a\u0004\u0018\u00010\u00112\b\u0010(\u001a\u0004\u0018\u00010)R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00030\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006*"}, d2 = {"Lexpo/modules/medialibrary/next/objects/asset/delegates/MediaStoreToAssetAdapter;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "transformHeight", "", "mediaStoreHeight", "contentUri", "Landroid/net/Uri;", "(Ljava/lang/Integer;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformWidth", "mediaStoreWidth", "transformDimension", "mediaStoreDimension", "fallback", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Integer;Landroid/net/Uri;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadBitmapAndGet", "extract", "Landroid/graphics/BitmapFactory$Options;", "(Landroid/net/Uri;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformCreationTime", "", "mediaStoreDateTaken", "(Ljava/lang/Long;)Ljava/lang/Long;", "transformDuration", "mediaStoreDuration", "transformModificationTime", "mediaStoreDateModified", "transformUri", "mediaStoreData", "", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaStoreToAssetAdapter {
    private final WeakReference<Context> contextRef;

    public MediaStoreToAssetAdapter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.contextRef = new WeakReference<>(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContentResolver getContentResolver() {
        ContentResolver contentResolver = WeakReferenceExtensionsKt.getOrThrow(this.contextRef).getContentResolver();
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new ContentResolverNotObtainedException(null, 1, null);
    }

    public final Object transformHeight(Integer num, Uri uri, Continuation<? super Integer> continuation) {
        return transformDimension(num, uri, new MediaStoreToAssetAdapter$transformHeight$2(this, uri, null), continuation);
    }

    public final Object transformWidth(Integer num, Uri uri, Continuation<? super Integer> continuation) {
        return transformDimension(num, uri, new MediaStoreToAssetAdapter$transformWidth$2(this, uri, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object transformDimension(Integer num, Uri uri, Function1<? super Continuation<? super Integer>, ? extends Object> function1, Continuation<? super Integer> continuation) {
        if (MediaType.INSTANCE.fromContentUri(uri) == MediaType.IMAGE && (num == null || num.intValue() <= 0)) {
            return function1.invoke(continuation);
        }
        if (num == null || num.intValue() <= 0) {
            return null;
        }
        return num;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object downloadBitmapAndGet(Uri uri, Function1<? super BitmapFactory.Options, Integer> function1, Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new MediaStoreToAssetAdapter$downloadBitmapAndGet$2(this, uri, function1, null), continuation);
    }

    public final Long transformCreationTime(Long mediaStoreDateTaken) {
        if (mediaStoreDateTaken != null && mediaStoreDateTaken.longValue() == 0) {
            return null;
        }
        return mediaStoreDateTaken;
    }

    public final Long transformDuration(Long mediaStoreDuration) {
        if (mediaStoreDuration != null && mediaStoreDuration.longValue() == 0) {
            return null;
        }
        return mediaStoreDuration;
    }

    public final Long transformModificationTime(Long mediaStoreDateModified) {
        if (mediaStoreDateModified != null) {
            if (mediaStoreDateModified.longValue() == 0) {
                mediaStoreDateModified = null;
            }
            if (mediaStoreDateModified != null) {
                return Long.valueOf(Duration.m2782getInWholeMillisecondsimpl(DurationKt.toDuration(mediaStoreDateModified.longValue(), DurationUnit.SECONDS)));
            }
        }
        return null;
    }

    public final Uri transformUri(String mediaStoreData) {
        if (mediaStoreData != null) {
            return Uri.fromFile(new File(mediaStoreData));
        }
        return null;
    }
}
