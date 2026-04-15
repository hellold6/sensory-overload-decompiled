package com.facebook.common.callercontext;

import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public interface ImageAttribution {
    String getCallingClassName();

    @Nullable
    ContextChain getContextChain();
}
