package expo.modules.interfaces.taskManager;

import android.content.Context;
import expo.modules.core.interfaces.Package;

/* loaded from: classes3.dex */
public interface TaskServiceProviderInterface extends Package {
    TaskServiceInterface getTaskServiceImpl(Context context);
}
