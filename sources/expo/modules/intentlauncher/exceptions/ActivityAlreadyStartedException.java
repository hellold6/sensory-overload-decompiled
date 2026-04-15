package expo.modules.intentlauncher.exceptions;

import expo.modules.core.errors.CodedException;
import kotlin.Metadata;

/* compiled from: ActivityAlreadyStartedException.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lexpo/modules/intentlauncher/exceptions/ActivityAlreadyStartedException;", "Lexpo/modules/core/errors/CodedException;", "<init>", "()V", "getCode", "", "expo-intent-launcher_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ActivityAlreadyStartedException extends CodedException {
    public ActivityAlreadyStartedException() {
        super("IntentLauncher activity is already started. You need to wait for its result before starting another activity.");
    }

    @Override // expo.modules.core.errors.CodedException, expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "E_ACTIVITY_ALREADY_STARTED";
    }
}
