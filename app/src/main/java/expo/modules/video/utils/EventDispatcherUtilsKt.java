package expo.modules.video.utils;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.TouchEvent;
import com.facebook.react.uimanager.events.TouchEventCoalescingKeyHelper;
import com.facebook.react.uimanager.events.TouchEventType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EventDispatcherUtils.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\f\u0010\t\u001a\u00020\n*\u00020\u0006H\u0002¨\u0006\u000b"}, d2 = {"dispatchMotionEvent", "", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "view", "Landroid/view/View;", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "touchEventCoalescingKeyHelper", "Lcom/facebook/react/uimanager/events/TouchEventCoalescingKeyHelper;", "toTouchEventType", "Lcom/facebook/react/uimanager/events/TouchEventType;", "expo-video_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EventDispatcherUtilsKt {
    public static final void dispatchMotionEvent(EventDispatcher eventDispatcher, View view, MotionEvent motionEvent, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        Intrinsics.checkNotNullParameter(eventDispatcher, "<this>");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(touchEventCoalescingKeyHelper, "touchEventCoalescingKeyHelper");
        if (motionEvent == null) {
            return;
        }
        try {
            eventDispatcher.dispatchEvent(TouchEvent.INSTANCE.obtain(UIManagerHelper.getSurfaceId(view), view.getId(), toTouchEventType(motionEvent), motionEvent, motionEvent.getEventTime(), motionEvent.getX(), motionEvent.getY(), touchEventCoalescingKeyHelper));
        } catch (RuntimeException e) {
            Log.e("EventDispatcherUtils", "Error dispatching touch event", e);
        }
    }

    private static final TouchEventType toTouchEventType(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            return TouchEventType.START;
        }
        if (actionMasked == 1) {
            return TouchEventType.END;
        }
        if (actionMasked == 2) {
            return TouchEventType.MOVE;
        }
        if (actionMasked == 3) {
            return TouchEventType.CANCEL;
        }
        return TouchEventType.CANCEL;
    }
}
