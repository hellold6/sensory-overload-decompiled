package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.Throwable;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends FluentFuture.TrustedFuture<V> implements Runnable {

    @CheckForNull
    @LazyInit
    Class<X> exceptionType;

    @CheckForNull
    @LazyInit
    F fallback;

    @CheckForNull
    @LazyInit
    ListenableFuture<? extends V> inputFuture;

    @ParametricNullness
    abstract T doFallback(F fallback, X throwable) throws Exception;

    abstract void setResult(@ParametricNullness T result);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback, Executor executor) {
        CatchingFuture catchingFuture = new CatchingFuture(input, exceptionType, fallback);
        input.addListener(catchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, catchingFuture));
        return catchingFuture;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <X extends Throwable, V> ListenableFuture<V> createAsync(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback, Executor executor) {
        AsyncCatchingFuture asyncCatchingFuture = new AsyncCatchingFuture(input, exceptionType, fallback);
        input.addListener(asyncCatchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, asyncCatchingFuture));
        return asyncCatchingFuture;
    }

    AbstractCatchingFuture(ListenableFuture<? extends V> listenableFuture, Class<X> cls, F f) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.exceptionType = (Class) Preconditions.checkNotNull(cls);
        this.fallback = (F) Preconditions.checkNotNull(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Class<X extends java.lang.Throwable>, F] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r8 = this;
            com.google.common.util.concurrent.ListenableFuture<? extends V> r0 = r8.inputFuture
            java.lang.Class<X extends java.lang.Throwable> r1 = r8.exceptionType
            F r2 = r8.fallback
            r3 = 1
            r4 = 0
            if (r0 != 0) goto Lc
            r5 = r3
            goto Ld
        Lc:
            r5 = r4
        Ld:
            if (r1 != 0) goto L11
            r6 = r3
            goto L12
        L11:
            r6 = r4
        L12:
            r5 = r5 | r6
            if (r2 != 0) goto L16
            goto L17
        L16:
            r3 = r4
        L17:
            r3 = r3 | r5
            if (r3 != 0) goto La3
            boolean r3 = r8.isCancelled()
            if (r3 == 0) goto L22
            goto La3
        L22:
            r3 = 0
            r8.inputFuture = r3
            boolean r4 = r0 instanceof com.google.common.util.concurrent.internal.InternalFutureFailureAccess     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            if (r4 == 0) goto L31
            r4 = r0
            com.google.common.util.concurrent.internal.InternalFutureFailureAccess r4 = (com.google.common.util.concurrent.internal.InternalFutureFailureAccess) r4     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            java.lang.Throwable r4 = com.google.common.util.concurrent.internal.InternalFutures.tryInternalFastPathGetFailure(r4)     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            goto L32
        L31:
            r4 = r3
        L32:
            if (r4 != 0) goto L3a
            java.lang.Object r5 = com.google.common.util.concurrent.Futures.getDone(r0)     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            goto L71
        L39:
            r4 = move-exception
        L3a:
            r5 = r3
            goto L71
        L3c:
            r4 = move-exception
            java.lang.Throwable r5 = r4.getCause()
            if (r5 != 0) goto L6f
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Future type "
            r6.<init>(r7)
            java.lang.Class r7 = r0.getClass()
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " threw "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.Class r4 = r4.getClass()
            java.lang.StringBuilder r4 = r6.append(r4)
            java.lang.String r6 = " without a cause"
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
        L6f:
            r4 = r5
            goto L3a
        L71:
            if (r4 != 0) goto L7b
            java.lang.Object r0 = com.google.common.util.concurrent.NullnessCasts.uncheckedCastNullableTToT(r5)
            r8.set(r0)
            return
        L7b:
            boolean r1 = com.google.common.util.concurrent.Platform.isInstanceOfThrowableClass(r4, r1)
            if (r1 != 0) goto L85
            r8.setFuture(r0)
            return
        L85:
            java.lang.Object r0 = r8.doFallback(r2, r4)     // Catch: java.lang.Throwable -> L91
            r8.exceptionType = r3
            r8.fallback = r3
            r8.setResult(r0)
            return
        L91:
            r0 = move-exception
            com.google.common.util.concurrent.Platform.restoreInterruptIfIsInterruptedException(r0)     // Catch: java.lang.Throwable -> L9d
            r8.setException(r0)     // Catch: java.lang.Throwable -> L9d
            r8.exceptionType = r3
            r8.fallback = r3
            return
        L9d:
            r0 = move-exception
            r8.exceptionType = r3
            r8.fallback = r3
            throw r0
        La3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractCatchingFuture.run():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    @CheckForNull
    public String pendingToString() {
        String str;
        ListenableFuture<? extends V> listenableFuture = this.inputFuture;
        Class<X> cls = this.exceptionType;
        F f = this.fallback;
        String pendingToString = super.pendingToString();
        if (listenableFuture == null) {
            str = "";
        } else {
            str = "inputFuture=[" + listenableFuture + "], ";
        }
        if (cls != null && f != null) {
            return str + "exceptionType=[" + cls + "], fallback=[" + f + "]";
        }
        if (pendingToString != null) {
            return str + pendingToString;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class AsyncCatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        /* bridge */ /* synthetic */ Object doFallback(Object fallback, Throwable cause) throws Exception {
            return doFallback((AsyncFunction<? super AsyncFunction<? super X, ? extends V>, ? extends V>) fallback, (AsyncFunction<? super X, ? extends V>) cause);
        }

        AsyncCatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        /* JADX WARN: Multi-variable type inference failed */
        ListenableFuture<? extends V> doFallback(AsyncFunction<? super X, ? extends V> fallback, X cause) throws Exception {
            ListenableFuture<? extends V> apply = fallback.apply(cause);
            Preconditions.checkNotNull(apply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", fallback);
            return apply;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(ListenableFuture<? extends V> result) {
            setFuture(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        @ParametricNullness
        /* bridge */ /* synthetic */ Object doFallback(Object fallback, Throwable cause) throws Exception {
            return doFallback((Function<? super Function<? super X, ? extends V>, ? extends V>) fallback, (Function<? super X, ? extends V>) cause);
        }

        CatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @ParametricNullness
        V doFallback(Function<? super X, ? extends V> fallback, X cause) throws Exception {
            return fallback.apply(cause);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        void setResult(@ParametricNullness V result) {
            set(result);
        }
    }
}
