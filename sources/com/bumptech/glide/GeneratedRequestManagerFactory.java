package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import expo.modules.image.GlideRequests;

/* loaded from: classes2.dex */
final class GeneratedRequestManagerFactory implements RequestManagerRetriever.RequestManagerFactory {
    @Override // com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory
    public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
        return new GlideRequests(glide, lifecycle, requestManagerTreeNode, context);
    }
}
