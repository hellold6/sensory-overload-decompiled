package com.facebook.react.fabric.mounting;

import android.os.SystemClock;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.mounting.mountitems.DispatchCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.systrace.Systrace;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MountItemDispatcher.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0005\b\u0000\u0018\u0000 *2\u00020\u0001:\u0002)*B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nJ\u000e\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fJ\u000e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fJ\b\u0010\u001d\u001a\u00020\u0019H\u0007J\u0018\u0010\u001e\u001a\u00020\u00192\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\tH\u0007J\b\u0010\u001e\u001a\u00020\u0019H\u0003J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0011H\u0007J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u0011H\u0002J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\fH\u0002J\u0010\u0010%\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010&H\u0003J\u0010\u0010'\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010&H\u0003J\u0010\u0010(\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010&H\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/facebook/react/fabric/mounting/MountItemDispatcher;", "", "mountingManager", "Lcom/facebook/react/fabric/mounting/MountingManager;", "itemDispatchListener", "Lcom/facebook/react/fabric/mounting/MountItemDispatcher$ItemDispatchListener;", "<init>", "(Lcom/facebook/react/fabric/mounting/MountingManager;Lcom/facebook/react/fabric/mounting/MountItemDispatcher$ItemDispatchListener;)V", "viewCommandMountItems", "Ljava/util/Queue;", "Lcom/facebook/react/fabric/mounting/mountitems/DispatchCommandMountItem;", "mountItems", "Lcom/facebook/react/fabric/mounting/mountitems/MountItem;", "preMountItems", "inDispatch", "", "value", "", "batchedExecutionTime", "getBatchedExecutionTime", "()J", "runStartTime", "getRunStartTime", "lastFrameTimeNanos", "addViewCommandMountItem", "", "mountItem", "addMountItem", "addPreAllocateMountItem", "tryDispatchMountItems", "dispatchMountItems", "dispatchPreMountItems", "frameTimeNanos", "dispatchPreMountItemsImpl", "deadline", "executeOrEnqueue", "item", "getAndResetViewCommandMountItems", "", "getAndResetMountItems", "getAndResetPreMountItems", "ItemDispatchListener", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MountItemDispatcher {
    private static final Companion Companion = new Companion(null);
    private static final long FRAME_TIME_NS = 16666666;
    private static final String TAG = "MountItemDispatcher";
    private long batchedExecutionTime;
    private boolean inDispatch;
    private final ItemDispatchListener itemDispatchListener;
    private long lastFrameTimeNanos;
    private final Queue<MountItem> mountItems;
    private final MountingManager mountingManager;
    private final Queue<MountItem> preMountItems;
    private long runStartTime;
    private final Queue<DispatchCommandMountItem> viewCommandMountItems;

    /* compiled from: MountItemDispatcher.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&J\b\u0010\b\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/fabric/mounting/MountItemDispatcher$ItemDispatchListener;", "", "willMountItems", "", "mountItems", "", "Lcom/facebook/react/fabric/mounting/mountitems/MountItem;", "didMountItems", "didDispatchMountItems", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface ItemDispatchListener {
        void didDispatchMountItems();

        void didMountItems(List<? extends MountItem> mountItems);

        void willMountItems(List<? extends MountItem> mountItems);
    }

    public MountItemDispatcher(MountingManager mountingManager, ItemDispatchListener itemDispatchListener) {
        Intrinsics.checkNotNullParameter(mountingManager, "mountingManager");
        Intrinsics.checkNotNullParameter(itemDispatchListener, "itemDispatchListener");
        this.mountingManager = mountingManager;
        this.itemDispatchListener = itemDispatchListener;
        this.viewCommandMountItems = new ConcurrentLinkedQueue();
        this.mountItems = new ConcurrentLinkedQueue();
        this.preMountItems = new ConcurrentLinkedQueue();
    }

    public final long getBatchedExecutionTime() {
        return this.batchedExecutionTime;
    }

    public final long getRunStartTime() {
        return this.runStartTime;
    }

    public final void addViewCommandMountItem(DispatchCommandMountItem mountItem) {
        Intrinsics.checkNotNullParameter(mountItem, "mountItem");
        this.viewCommandMountItems.add(mountItem);
    }

    public final void addMountItem(MountItem mountItem) {
        Intrinsics.checkNotNullParameter(mountItem, "mountItem");
        this.mountItems.add(mountItem);
    }

    public final void addPreAllocateMountItem(MountItem mountItem) {
        Intrinsics.checkNotNullParameter(mountItem, "mountItem");
        if (!this.mountingManager.surfaceIsStopped(mountItem.get_surfaceId())) {
            this.preMountItems.add(mountItem);
        } else if (FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
            FLog.e(TAG, "Not queueing PreAllocateMountItem: surfaceId stopped: [%d] - %s", Integer.valueOf(mountItem.get_surfaceId()), mountItem.toString());
        }
    }

    public final void tryDispatchMountItems() {
        if (this.inDispatch) {
            return;
        }
        this.inDispatch = true;
        try {
            dispatchMountItems();
            this.inDispatch = false;
            this.itemDispatchListener.didDispatchMountItems();
        } catch (Throwable th) {
            this.inDispatch = false;
            throw th;
        }
    }

    public final void dispatchMountItems(Queue<MountItem> mountItems) {
        Intrinsics.checkNotNullParameter(mountItems, "mountItems");
        while (!mountItems.isEmpty()) {
            MountItem poll = mountItems.poll();
            if (poll == null) {
                throw new IllegalArgumentException("MountItem should not be null".toString());
            }
            MountItem mountItem = poll;
            try {
                mountItem.execute(this.mountingManager);
            } catch (RetryableMountingLayerException e) {
                if (mountItem instanceof DispatchCommandMountItem) {
                    DispatchCommandMountItem dispatchCommandMountItem = (DispatchCommandMountItem) mountItem;
                    if (dispatchCommandMountItem.getNumRetries() == 0) {
                        dispatchCommandMountItem.incrementRetries();
                        addViewCommandMountItem(dispatchCommandMountItem);
                    }
                } else {
                    Companion.printMountItem(mountItem, "dispatchExternalMountItems: mounting failed with " + e.getMessage());
                }
            }
        }
    }

    private final void dispatchMountItems() {
        boolean isIgnorable;
        this.batchedExecutionTime = 0L;
        this.runStartTime = SystemClock.uptimeMillis();
        List<DispatchCommandMountItem> andResetViewCommandMountItems = getAndResetViewCommandMountItems();
        List<MountItem> andResetMountItems = getAndResetMountItems();
        if (andResetMountItems == null && andResetViewCommandMountItems == null) {
            return;
        }
        this.itemDispatchListener.willMountItems(andResetMountItems);
        if (andResetViewCommandMountItems != null) {
            Systrace.beginSection(0L, "MountItemDispatcher::mountViews viewCommandMountItems");
            for (DispatchCommandMountItem dispatchCommandMountItem : andResetViewCommandMountItems) {
                if (ReactNativeFeatureFlags.enableFabricLogs()) {
                    Companion.printMountItem(dispatchCommandMountItem, "dispatchMountItems: Executing viewCommandMountItem");
                }
                try {
                    executeOrEnqueue(dispatchCommandMountItem);
                } catch (RetryableMountingLayerException e) {
                    if (dispatchCommandMountItem.getNumRetries() == 0) {
                        dispatchCommandMountItem.incrementRetries();
                        addViewCommandMountItem(dispatchCommandMountItem);
                    } else {
                        ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Caught exception executing ViewCommand: " + dispatchCommandMountItem, e));
                    }
                } catch (Throwable th) {
                    ReactSoftExceptionLogger.logSoftException(TAG, new RuntimeException("Caught exception executing ViewCommand: " + dispatchCommandMountItem, th));
                }
            }
            Systrace.endSection(0L);
        }
        List<MountItem> andResetPreMountItems = getAndResetPreMountItems();
        if (andResetPreMountItems != null) {
            Systrace.beginSection(0L, "MountItemDispatcher::mountViews preMountItems");
            for (MountItem mountItem : andResetPreMountItems) {
                if (ReactNativeFeatureFlags.enableFabricLogs()) {
                    Companion.printMountItem(mountItem, "dispatchMountItems: Executing preMountItem");
                }
                executeOrEnqueue(mountItem);
            }
            Systrace.endSection(0L);
        }
        if (andResetMountItems != null) {
            Systrace.beginSection(0L, "MountItemDispatcher::mountViews mountItems to execute");
            long uptimeMillis = SystemClock.uptimeMillis();
            Iterator<MountItem> it = andResetMountItems.iterator();
            while (it.hasNext()) {
                MountItem next = it.next();
                if (ReactNativeFeatureFlags.enableFabricLogs()) {
                    Companion.printMountItem(next, "dispatchMountItems: Executing mountItem");
                }
                try {
                    executeOrEnqueue(next);
                } finally {
                    if (isIgnorable) {
                    }
                }
            }
            this.batchedExecutionTime += SystemClock.uptimeMillis() - uptimeMillis;
            Systrace.endSection(0L);
        }
        this.itemDispatchListener.didMountItems(andResetMountItems);
    }

    public final void dispatchPreMountItems(long frameTimeNanos) {
        this.lastFrameTimeNanos = frameTimeNanos;
        if (this.preMountItems.isEmpty()) {
            return;
        }
        dispatchPreMountItemsImpl(this.lastFrameTimeNanos + 8333333);
    }

    private final void dispatchPreMountItemsImpl(long deadline) {
        MountItem poll;
        Systrace.beginSection(0L, "MountItemDispatcher::premountViews");
        this.inDispatch = true;
        while (System.nanoTime() <= deadline && (poll = this.preMountItems.poll()) != null) {
            try {
                if (ReactNativeFeatureFlags.enableFabricLogs()) {
                    Companion.printMountItem(poll, "dispatchPreMountItems");
                }
                executeOrEnqueue(poll);
            } catch (Throwable th) {
                this.inDispatch = false;
                throw th;
            }
        }
        this.inDispatch = false;
        Systrace.endSection(0L);
    }

    private final void executeOrEnqueue(MountItem item) {
        if (this.mountingManager.isWaitingForViewAttach(item.get_surfaceId())) {
            if (ReactNativeFeatureFlags.enableFabricLogs()) {
                FLog.e(TAG, "executeOrEnqueue: Item execution delayed, surface %s is not ready yet", Integer.valueOf(item.get_surfaceId()));
            }
            this.mountingManager.getSurfaceManagerEnforced(item.get_surfaceId(), "MountItemDispatcher::executeOrEnqueue").scheduleMountItemOnViewAttach(item);
            return;
        }
        item.execute(this.mountingManager);
    }

    private final List<DispatchCommandMountItem> getAndResetViewCommandMountItems() {
        return Companion.drainConcurrentItemQueue(this.viewCommandMountItems);
    }

    private final List<MountItem> getAndResetMountItems() {
        return Companion.drainConcurrentItemQueue(this.mountItems);
    }

    private final List<MountItem> getAndResetPreMountItems() {
        return Companion.drainConcurrentItemQueue(this.preMountItems);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MountItemDispatcher.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\b\u001a\n\u0012\u0004\u0012\u0002H\n\u0018\u00010\t\"\u0004\b\u0000\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/react/fabric/mounting/MountItemDispatcher$Companion;", "", "<init>", "()V", "TAG", "", "FRAME_TIME_NS", "", "drainConcurrentItemQueue", "", ExifInterface.LONGITUDE_EAST, "queue", "Ljava/util/Queue;", "printMountItem", "", "mountItem", "Lcom/facebook/react/fabric/mounting/mountitems/MountItem;", "prefix", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <E> List<E> drainConcurrentItemQueue(Queue<E> queue) {
            if (queue.isEmpty()) {
                return null;
            }
            List createListBuilder = CollectionsKt.createListBuilder();
            do {
                E poll = queue.poll();
                if (poll != null) {
                    createListBuilder.add(poll);
                }
            } while (!queue.isEmpty());
            List<E> build = CollectionsKt.build(createListBuilder);
            if (build.isEmpty()) {
                return null;
            }
            return build;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void printMountItem(MountItem mountItem, String prefix) {
            List emptyList;
            List split$default = StringsKt.split$default((CharSequence) mountItem.toString(), new String[]{"\n"}, false, 0, 6, (Object) null);
            if (!split$default.isEmpty()) {
                ListIterator listIterator = split$default.listIterator(split$default.size());
                while (listIterator.hasPrevious()) {
                    if (((String) listIterator.previous()).length() != 0) {
                        emptyList = CollectionsKt.take(split$default, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt.emptyList();
            Iterator it = emptyList.iterator();
            while (it.hasNext()) {
                FLog.e(MountItemDispatcher.TAG, prefix + ": " + ((String) it.next()));
            }
        }
    }
}
