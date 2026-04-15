package expo.modules.video.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableIterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: WeakMutableSet.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0010)\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0002\u001a\u00020\u0003H\u0096\u0002J\u000e\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"expo/modules/video/utils/WeakMutableSet$iterator$1", "", "hasNext", "", "next", "()Ljava/lang/Object;", "remove", "", "expo-video_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeakMutableSet$iterator$1<T> implements Iterator<T>, KMutableIterator {
    final /* synthetic */ Iterator<WeakElement<T>> $i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WeakMutableSet$iterator$1(Iterator<WeakElement<T>> it) {
        this.$i = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.$i.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        T t = (T) this.$i.next().get();
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("The next element was garbage collected.");
    }

    @Override // java.util.Iterator
    public void remove() {
        this.$i.remove();
    }
}
