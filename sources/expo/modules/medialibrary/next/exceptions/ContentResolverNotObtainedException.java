package expo.modules.medialibrary.next.exceptions;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ContentResolverExceptions.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/medialibrary/next/exceptions/ContentResolverNotObtainedException;", "Lexpo/modules/kotlin/exception/CodedException;", "cause", "", "<init>", "(Ljava/lang/Throwable;)V", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContentResolverNotObtainedException extends CodedException {
    /* JADX WARN: Multi-variable type inference failed */
    public ContentResolverNotObtainedException() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ ContentResolverNotObtainedException(Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : th);
    }

    public ContentResolverNotObtainedException(Throwable th) {
        super("Could not obtain the content resolver", th);
    }
}
