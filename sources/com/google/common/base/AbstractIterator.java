package com.google.common.base;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
abstract class AbstractIterator<T> implements Iterator<T> {

    @CheckForNull
    private T next;
    private State state = State.NOT_READY;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum State {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    @CheckForNull
    protected abstract T computeNext();

    /* JADX INFO: Access modifiers changed from: protected */
    @CheckForNull
    public final T endOfData() {
        this.state = State.DONE;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Preconditions.checkState(this.state != State.FAILED);
        int ordinal = this.state.ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal != 2) {
            return tryToComputeNext();
        }
        return false;
    }

    private boolean tryToComputeNext() {
        this.state = State.FAILED;
        this.next = computeNext();
        if (this.state == State.DONE) {
            return false;
        }
        this.state = State.READY;
        return true;
    }

    @Override // java.util.Iterator
    @ParametricNullness
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.state = State.NOT_READY;
        T t = (T) NullnessCasts.uncheckedCastNullableTToT(this.next);
        this.next = null;
        return t;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
