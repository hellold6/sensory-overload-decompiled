package com.swmansion.worklets;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;

/* loaded from: classes3.dex */
public class JSCallInvokerResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static CallInvokerHolderImpl getJSCallInvokerHolder(ReactApplicationContext reactApplicationContext) {
        try {
            try {
                return (CallInvokerHolderImpl) reactApplicationContext.getClass().getMethod("getJSCallInvokerHolder", new Class[0]).invoke(reactApplicationContext, new Object[0]);
            } catch (Exception e) {
                throw new RuntimeException("Failed to get JSCallInvokerHolder", e);
            }
        } catch (Exception unused) {
            Object invoke = reactApplicationContext.getClass().getMethod("getCatalystInstance", new Class[0]).invoke(reactApplicationContext, new Object[0]);
            return (CallInvokerHolderImpl) invoke.getClass().getMethod("getJSCallInvokerHolder", new Class[0]).invoke(invoke, new Object[0]);
        }
    }
}
