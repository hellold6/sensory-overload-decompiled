package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.module.model.ReactModuleInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: LazyReactPackage.kt */
@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0096\u0002¨\u0006\u0005"}, d2 = {"com/facebook/react/LazyReactPackage$getNativeModuleIterator$1", "", "Lcom/facebook/react/bridge/ModuleHolder;", "iterator", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LazyReactPackage$getNativeModuleIterator$1 implements Iterable<ModuleHolder>, KMappedMarker {
    final /* synthetic */ List<ModuleSpec> $nativeModules;
    final /* synthetic */ Map<String, ReactModuleInfo> $reactModuleInfoMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LazyReactPackage$getNativeModuleIterator$1(List<ModuleSpec> list, Map<String, ReactModuleInfo> map) {
        this.$nativeModules = list;
        this.$reactModuleInfoMap = map;
    }

    @Override // java.lang.Iterable
    public Iterator<ModuleHolder> iterator() {
        return new LazyReactPackage$getNativeModuleIterator$1$iterator$1(new Ref.IntRef(), this.$nativeModules, this.$reactModuleInfoMap);
    }
}
