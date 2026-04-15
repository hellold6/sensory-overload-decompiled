package expo.modules.documentpicker;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

/* compiled from: DocumentPickerExceptions.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lexpo/modules/documentpicker/PickingInProgressException;", "Lexpo/modules/kotlin/exception/CodedException;", "<init>", "()V", "expo-document-picker_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PickingInProgressException extends CodedException {
    public PickingInProgressException() {
        super("Different document picking in progress. Await other document picking first.", null, 2, null);
    }
}
