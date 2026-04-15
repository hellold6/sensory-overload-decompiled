package com.reactnativecommunity.asyncstorage;

import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import java.util.concurrent.Executor;

@ReactModule(name = "RNCAsyncStorage")
/* loaded from: classes3.dex */
public final class AsyncStorageModule extends NativeAsyncStorageModuleSpec {
    private static final int MAX_SQL_KEYS = 999;
    public static final String NAME = "RNCAsyncStorage";
    private final SerialExecutor executor;
    private ReactDatabaseSupplier mReactDatabaseSupplier;
    private boolean mShuttingDown;

    public AsyncStorageModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @VisibleForTesting
    AsyncStorageModule(ReactApplicationContext reactApplicationContext, Executor executor) {
        super(reactApplicationContext);
        this.mShuttingDown = false;
        AsyncStorageExpoMigration.migrate(reactApplicationContext);
        this.executor = new SerialExecutor(executor);
        this.mReactDatabaseSupplier = ReactDatabaseSupplier.getInstance(reactApplicationContext);
    }

    @Override // com.reactnativecommunity.asyncstorage.NativeAsyncStorageModuleSpec, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCAsyncStorage";
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        this.mShuttingDown = true;
        this.mReactDatabaseSupplier.closeDatabase();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.reactnativecommunity.asyncstorage.AsyncStorageModule$1] */
    @Override // com.reactnativecommunity.asyncstorage.NativeAsyncStorageModuleSpec
    @ReactMethod
    public void multiGet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray == null) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null), null);
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Code restructure failed: missing block: B:19:0x0084, code lost:
                
                    if (r2.moveToFirst() != false) goto L18;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:20:0x0086, code lost:
                
                    r3 = com.facebook.react.bridge.Arguments.createArray();
                    r3.pushString(r2.getString(0));
                    r3.pushString(r2.getString(1));
                    r11.pushArray(r3);
                    r15.remove(r2.getString(0));
                 */
                /* JADX WARN: Code restructure failed: missing block: B:21:0x00a6, code lost:
                
                    if (r2.moveToNext() != false) goto L42;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:24:0x00a8, code lost:
                
                    r2.close();
                    r2 = r15.iterator();
                 */
                /* JADX WARN: Code restructure failed: missing block: B:26:0x00b3, code lost:
                
                    if (r2.hasNext() == false) goto L43;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:27:0x00b5, code lost:
                
                    r3 = (java.lang.String) r2.next();
                    r5 = com.facebook.react.bridge.Arguments.createArray();
                    r5.pushString(r3);
                    r5.pushNull();
                    r11.pushArray(r5);
                 */
                /* JADX WARN: Code restructure failed: missing block: B:29:0x00c9, code lost:
                
                    r15.clear();
                    r12 = r12 + com.reactnativecommunity.asyncstorage.AsyncStorageModule.MAX_SQL_KEYS;
                 */
                /* JADX WARN: Finally extract failed */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void doInBackgroundGuarded(java.lang.Void... r15) {
                    /*
                        r14 = this;
                        com.reactnativecommunity.asyncstorage.AsyncStorageModule r15 = com.reactnativecommunity.asyncstorage.AsyncStorageModule.this
                        boolean r15 = com.reactnativecommunity.asyncstorage.AsyncStorageModule.m1169$$Nest$mensureDatabase(r15)
                        r1 = 0
                        if (r15 != 0) goto L17
                        com.facebook.react.bridge.Callback r15 = r3
                        com.facebook.react.bridge.WritableMap r0 = com.reactnativecommunity.asyncstorage.AsyncStorageErrorUtil.getDBError(r1)
                        java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                        r15.invoke(r0)
                        return
                    L17:
                        r15 = 2
                        java.lang.String[] r4 = new java.lang.String[r15]
                        java.lang.String r15 = "key"
                        r0 = 0
                        r4[r0] = r15
                        java.lang.String r15 = "value"
                        r10 = 1
                        r4[r10] = r15
                        java.util.HashSet r15 = new java.util.HashSet
                        r15.<init>()
                        com.facebook.react.bridge.WritableArray r11 = com.facebook.react.bridge.Arguments.createArray()
                        r12 = r0
                    L2e:
                        com.facebook.react.bridge.ReadableArray r2 = r4
                        int r2 = r2.size()
                        if (r12 >= r2) goto Lf7
                        com.facebook.react.bridge.ReadableArray r2 = r4
                        int r2 = r2.size()
                        int r2 = r2 - r12
                        r3 = 999(0x3e7, float:1.4E-42)
                        int r13 = java.lang.Math.min(r2, r3)
                        com.reactnativecommunity.asyncstorage.AsyncStorageModule r2 = com.reactnativecommunity.asyncstorage.AsyncStorageModule.this
                        com.reactnativecommunity.asyncstorage.ReactDatabaseSupplier r2 = com.reactnativecommunity.asyncstorage.AsyncStorageModule.m1168$$Nest$fgetmReactDatabaseSupplier(r2)
                        android.database.sqlite.SQLiteDatabase r2 = r2.get()
                        java.lang.String r5 = com.reactnativecommunity.asyncstorage.AsyncLocalStorageUtil.buildKeySelection(r13)
                        com.facebook.react.bridge.ReadableArray r3 = r4
                        java.lang.String[] r6 = com.reactnativecommunity.asyncstorage.AsyncLocalStorageUtil.buildKeySelectionArgs(r3, r12, r13)
                        r8 = 0
                        r9 = 0
                        java.lang.String r3 = "catalystLocalStorage"
                        r7 = 0
                        android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)
                        r15.clear()
                        int r3 = r2.getCount()     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        com.facebook.react.bridge.ReadableArray r5 = r4     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        int r5 = r5.size()     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        if (r3 == r5) goto L80
                        r3 = r12
                    L70:
                        int r5 = r12 + r13
                        if (r3 >= r5) goto L80
                        com.facebook.react.bridge.ReadableArray r5 = r4     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        java.lang.String r5 = r5.getString(r3)     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        r15.add(r5)     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        int r3 = r3 + 1
                        goto L70
                    L80:
                        boolean r3 = r2.moveToFirst()     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        if (r3 == 0) goto La8
                    L86:
                        com.facebook.react.bridge.WritableArray r3 = com.facebook.react.bridge.Arguments.createArray()     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        java.lang.String r5 = r2.getString(r0)     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        r3.pushString(r5)     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        java.lang.String r5 = r2.getString(r10)     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        r3.pushString(r5)     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        r11.pushArray(r3)     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        java.lang.String r3 = r2.getString(r0)     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        r15.remove(r3)     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> Ld0 java.lang.Exception -> Ld3
                        if (r3 != 0) goto L86
                    La8:
                        r2.close()
                        java.util.Iterator r2 = r15.iterator()
                    Laf:
                        boolean r3 = r2.hasNext()
                        if (r3 == 0) goto Lc9
                        java.lang.Object r3 = r2.next()
                        java.lang.String r3 = (java.lang.String) r3
                        com.facebook.react.bridge.WritableArray r5 = com.facebook.react.bridge.Arguments.createArray()
                        r5.pushString(r3)
                        r5.pushNull()
                        r11.pushArray(r5)
                        goto Laf
                    Lc9:
                        r15.clear()
                        int r12 = r12 + 999
                        goto L2e
                    Ld0:
                        r0 = move-exception
                        r15 = r0
                        goto Lf3
                    Ld3:
                        r0 = move-exception
                        r15 = r0
                        java.lang.String r0 = "ReactNative"
                        java.lang.String r3 = r15.getMessage()     // Catch: java.lang.Throwable -> Ld0
                        com.facebook.common.logging.FLog.w(r0, r3, r15)     // Catch: java.lang.Throwable -> Ld0
                        com.facebook.react.bridge.Callback r0 = r3     // Catch: java.lang.Throwable -> Ld0
                        java.lang.String r15 = r15.getMessage()     // Catch: java.lang.Throwable -> Ld0
                        com.facebook.react.bridge.WritableMap r15 = com.reactnativecommunity.asyncstorage.AsyncStorageErrorUtil.getError(r1, r15)     // Catch: java.lang.Throwable -> Ld0
                        java.lang.Object[] r15 = new java.lang.Object[]{r15, r1}     // Catch: java.lang.Throwable -> Ld0
                        r0.invoke(r15)     // Catch: java.lang.Throwable -> Ld0
                        r2.close()
                        return
                    Lf3:
                        r2.close()
                        throw r15
                    Lf7:
                        com.facebook.react.bridge.Callback r15 = r3
                        java.lang.Object[] r0 = new java.lang.Object[]{r1, r11}
                        r15.invoke(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.asyncstorage.AsyncStorageModule.AnonymousClass1.doInBackgroundGuarded(java.lang.Void[]):void");
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.reactnativecommunity.asyncstorage.AsyncStorageModule$2] */
    @Override // com.reactnativecommunity.asyncstorage.NativeAsyncStorageModuleSpec
    @ReactMethod
    public void multiSet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(new Object[0]);
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                public void doInBackgroundGuarded(Void... voidArr) {
                    WritableMap writableMap = null;
                    if (!AsyncStorageModule.this.ensureDatabase()) {
                        callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                        return;
                    }
                    SQLiteStatement compileStatement = AsyncStorageModule.this.mReactDatabaseSupplier.get().compileStatement("INSERT OR REPLACE INTO catalystLocalStorage VALUES (?, ?);");
                    try {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                            for (int i = 0; i < readableArray.size(); i++) {
                                if (readableArray.getArray(i).size() != 2) {
                                    WritableMap invalidValueError = AsyncStorageErrorUtil.getInvalidValueError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e) {
                                        e = e;
                                        FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                        if (invalidValueError != null) {
                                            return;
                                        }
                                    }
                                } else if (readableArray.getArray(i).getString(0) == null) {
                                    WritableMap invalidKeyError = AsyncStorageErrorUtil.getInvalidKeyError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e2) {
                                        e = e2;
                                        FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                        if (invalidKeyError != null) {
                                            return;
                                        }
                                    }
                                } else if (readableArray.getArray(i).getString(1) == null) {
                                    WritableMap invalidValueError2 = AsyncStorageErrorUtil.getInvalidValueError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e3) {
                                        e = e3;
                                        FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                        if (invalidValueError2 != null) {
                                            return;
                                        }
                                    }
                                } else {
                                    compileStatement.clearBindings();
                                    compileStatement.bindString(1, readableArray.getArray(i).getString(0));
                                    compileStatement.bindString(2, readableArray.getArray(i).getString(1));
                                    compileStatement.execute();
                                }
                                AsyncStorageErrorUtil.getError(null, e.getMessage());
                                return;
                            }
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e4) {
                                FLog.w(ReactConstants.TAG, e4.getMessage(), e4);
                                writableMap = AsyncStorageErrorUtil.getError(null, e4.getMessage());
                            }
                        } catch (Throwable th) {
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e5) {
                                FLog.w(ReactConstants.TAG, e5.getMessage(), e5);
                                AsyncStorageErrorUtil.getError(null, e5.getMessage());
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        FLog.w(ReactConstants.TAG, e6.getMessage(), e6);
                        WritableMap error = AsyncStorageErrorUtil.getError(null, e6.getMessage());
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e7) {
                            FLog.w(ReactConstants.TAG, e7.getMessage(), e7);
                            if (error == null) {
                                writableMap = AsyncStorageErrorUtil.getError(null, e7.getMessage());
                            }
                        }
                        writableMap = error;
                    }
                    if (writableMap != null) {
                        callback.invoke(writableMap);
                    } else {
                        callback.invoke(new Object[0]);
                    }
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.reactnativecommunity.asyncstorage.AsyncStorageModule$3] */
    @Override // com.reactnativecommunity.asyncstorage.NativeAsyncStorageModuleSpec
    @ReactMethod
    public void multiRemove(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(new Object[0]);
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                public void doInBackgroundGuarded(Void... voidArr) {
                    WritableMap writableMap = null;
                    if (!AsyncStorageModule.this.ensureDatabase()) {
                        callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                        return;
                    }
                    try {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                            for (int i = 0; i < readableArray.size(); i += AsyncStorageModule.MAX_SQL_KEYS) {
                                int min = Math.min(readableArray.size() - i, AsyncStorageModule.MAX_SQL_KEYS);
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().delete("catalystLocalStorage", AsyncLocalStorageUtil.buildKeySelection(min), AsyncLocalStorageUtil.buildKeySelectionArgs(readableArray, i, min));
                            }
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e) {
                                FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                writableMap = AsyncStorageErrorUtil.getError(null, e.getMessage());
                            }
                        } catch (Exception e2) {
                            FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                            WritableMap error = AsyncStorageErrorUtil.getError(null, e2.getMessage());
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e3) {
                                FLog.w(ReactConstants.TAG, e3.getMessage(), e3);
                                if (error == null) {
                                    writableMap = AsyncStorageErrorUtil.getError(null, e3.getMessage());
                                }
                            }
                            writableMap = error;
                        }
                        if (writableMap != null) {
                            callback.invoke(writableMap);
                        } else {
                            callback.invoke(new Object[0]);
                        }
                    } catch (Throwable th) {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e4) {
                            FLog.w(ReactConstants.TAG, e4.getMessage(), e4);
                            AsyncStorageErrorUtil.getError(null, e4.getMessage());
                        }
                        throw th;
                    }
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.reactnativecommunity.asyncstorage.AsyncStorageModule$4] */
    @Override // com.reactnativecommunity.asyncstorage.NativeAsyncStorageModuleSpec
    @ReactMethod
    public void multiMerge(final ReadableArray readableArray, final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap writableMap = null;
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                    return;
                }
                try {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                        for (int i = 0; i < readableArray.size(); i++) {
                            if (readableArray.getArray(i).size() != 2) {
                                WritableMap invalidValueError = AsyncStorageErrorUtil.getInvalidValueError(null);
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                    return;
                                } catch (Exception e) {
                                    e = e;
                                    FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                    if (invalidValueError != null) {
                                        return;
                                    }
                                }
                            } else if (readableArray.getArray(i).getString(0) == null) {
                                WritableMap invalidKeyError = AsyncStorageErrorUtil.getInvalidKeyError(null);
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                    return;
                                } catch (Exception e2) {
                                    e = e2;
                                    FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                    if (invalidKeyError != null) {
                                        return;
                                    }
                                }
                            } else if (readableArray.getArray(i).getString(1) == null) {
                                WritableMap invalidValueError2 = AsyncStorageErrorUtil.getInvalidValueError(null);
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                    return;
                                } catch (Exception e3) {
                                    e = e3;
                                    FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                    if (invalidValueError2 != null) {
                                        return;
                                    }
                                }
                            } else if (!AsyncLocalStorageUtil.mergeImpl(AsyncStorageModule.this.mReactDatabaseSupplier.get(), readableArray.getArray(i).getString(0), readableArray.getArray(i).getString(1))) {
                                WritableMap dBError = AsyncStorageErrorUtil.getDBError(null);
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                    return;
                                } catch (Exception e4) {
                                    e = e4;
                                    FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                    if (dBError != null) {
                                        return;
                                    }
                                }
                            }
                            AsyncStorageErrorUtil.getError(null, e.getMessage());
                            return;
                        }
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e5) {
                            FLog.w(ReactConstants.TAG, e5.getMessage(), e5);
                            writableMap = AsyncStorageErrorUtil.getError(null, e5.getMessage());
                        }
                    } catch (Exception e6) {
                        FLog.w(ReactConstants.TAG, e6.getMessage(), e6);
                        WritableMap error = AsyncStorageErrorUtil.getError(null, e6.getMessage());
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e7) {
                            FLog.w(ReactConstants.TAG, e7.getMessage(), e7);
                            if (error == null) {
                                writableMap = AsyncStorageErrorUtil.getError(null, e7.getMessage());
                            }
                        }
                        writableMap = error;
                    }
                    if (writableMap != null) {
                        callback.invoke(writableMap);
                    } else {
                        callback.invoke(new Object[0]);
                    }
                } catch (Throwable th) {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                    } catch (Exception e8) {
                        FLog.w(ReactConstants.TAG, e8.getMessage(), e8);
                        AsyncStorageErrorUtil.getError(null, e8.getMessage());
                    }
                    throw th;
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.reactnativecommunity.asyncstorage.AsyncStorageModule$5] */
    @Override // com.reactnativecommunity.asyncstorage.NativeAsyncStorageModuleSpec
    @ReactMethod
    public void clear(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                if (!AsyncStorageModule.this.mReactDatabaseSupplier.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                    return;
                }
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.clear();
                    callback.invoke(new Object[0]);
                } catch (Exception e) {
                    FLog.w(ReactConstants.TAG, e.getMessage(), e);
                    callback.invoke(AsyncStorageErrorUtil.getError(null, e.getMessage()));
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.reactnativecommunity.asyncstorage.AsyncStorageModule$6] */
    @Override // com.reactnativecommunity.asyncstorage.NativeAsyncStorageModuleSpec
    @ReactMethod
    public void getAllKeys(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.6
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Code restructure failed: missing block: B:10:0x003e, code lost:
            
                r12.pushString(r2.getString(0));
             */
            /* JADX WARN: Code restructure failed: missing block: B:11:0x0049, code lost:
            
                if (r2.moveToNext() != false) goto L26;
             */
            /* JADX WARN: Code restructure failed: missing block: B:15:0x004b, code lost:
            
                r2.close();
                r3.invoke(null, r12);
             */
            /* JADX WARN: Code restructure failed: missing block: B:16:0x0057, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:9:0x003c, code lost:
            
                if (r2.moveToFirst() != false) goto L9;
             */
            /* JADX WARN: Finally extract failed */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void doInBackgroundGuarded(java.lang.Void... r12) {
                /*
                    r11 = this;
                    com.reactnativecommunity.asyncstorage.AsyncStorageModule r12 = com.reactnativecommunity.asyncstorage.AsyncStorageModule.this
                    boolean r12 = com.reactnativecommunity.asyncstorage.AsyncStorageModule.m1169$$Nest$mensureDatabase(r12)
                    r1 = 0
                    if (r12 != 0) goto L17
                    com.facebook.react.bridge.Callback r12 = r3
                    com.facebook.react.bridge.WritableMap r0 = com.reactnativecommunity.asyncstorage.AsyncStorageErrorUtil.getDBError(r1)
                    java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                    r12.invoke(r0)
                    return
                L17:
                    com.facebook.react.bridge.WritableArray r12 = com.facebook.react.bridge.Arguments.createArray()
                    r0 = 1
                    java.lang.String[] r4 = new java.lang.String[r0]
                    java.lang.String r0 = "key"
                    r10 = 0
                    r4[r10] = r0
                    com.reactnativecommunity.asyncstorage.AsyncStorageModule r0 = com.reactnativecommunity.asyncstorage.AsyncStorageModule.this
                    com.reactnativecommunity.asyncstorage.ReactDatabaseSupplier r0 = com.reactnativecommunity.asyncstorage.AsyncStorageModule.m1168$$Nest$fgetmReactDatabaseSupplier(r0)
                    android.database.sqlite.SQLiteDatabase r2 = r0.get()
                    r8 = 0
                    r9 = 0
                    java.lang.String r3 = "catalystLocalStorage"
                    r5 = 0
                    r6 = 0
                    r7 = 0
                    android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)
                    boolean r0 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5b
                    if (r0 == 0) goto L4b
                L3e:
                    java.lang.String r0 = r2.getString(r10)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5b
                    r12.pushString(r0)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5b
                    boolean r0 = r2.moveToNext()     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5b
                    if (r0 != 0) goto L3e
                L4b:
                    r2.close()
                    com.facebook.react.bridge.Callback r0 = r3
                    java.lang.Object[] r12 = new java.lang.Object[]{r1, r12}
                    r0.invoke(r12)
                    return
                L58:
                    r0 = move-exception
                    r12 = r0
                    goto L7b
                L5b:
                    r0 = move-exception
                    r12 = r0
                    java.lang.String r0 = "ReactNative"
                    java.lang.String r3 = r12.getMessage()     // Catch: java.lang.Throwable -> L58
                    com.facebook.common.logging.FLog.w(r0, r3, r12)     // Catch: java.lang.Throwable -> L58
                    com.facebook.react.bridge.Callback r0 = r3     // Catch: java.lang.Throwable -> L58
                    java.lang.String r12 = r12.getMessage()     // Catch: java.lang.Throwable -> L58
                    com.facebook.react.bridge.WritableMap r12 = com.reactnativecommunity.asyncstorage.AsyncStorageErrorUtil.getError(r1, r12)     // Catch: java.lang.Throwable -> L58
                    java.lang.Object[] r12 = new java.lang.Object[]{r12, r1}     // Catch: java.lang.Throwable -> L58
                    r0.invoke(r12)     // Catch: java.lang.Throwable -> L58
                    r2.close()
                    return
                L7b:
                    r2.close()
                    throw r12
                */
                throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.asyncstorage.AsyncStorageModule.AnonymousClass6.doInBackgroundGuarded(java.lang.Void[]):void");
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ensureDatabase() {
        return !this.mShuttingDown && this.mReactDatabaseSupplier.ensureDatabase();
    }
}
