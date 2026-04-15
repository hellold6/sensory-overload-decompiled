package expo.modules.medialibrary;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

/* compiled from: Exceptions.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lexpo/modules/medialibrary/AssetQueryException;", "Lexpo/modules/kotlin/exception/CodedException;", "<init>", "()V", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetQueryException extends CodedException {
    public AssetQueryException() {
        super("Could not get asset. Query returns null", null, 2, null);
    }
}
