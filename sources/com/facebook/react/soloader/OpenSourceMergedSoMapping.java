package com.facebook.react.soloader;

import com.facebook.soloader.ExternalSoMapping;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OpenSourceMergedSoMapping.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\t\u0010\n\u001a\u00020\u000bH\u0086 J\t\u0010\f\u001a\u00020\u000bH\u0086 J\t\u0010\r\u001a\u00020\u000bH\u0086 J\t\u0010\u000e\u001a\u00020\u000bH\u0086 J\t\u0010\u000f\u001a\u00020\u000bH\u0086 J\t\u0010\u0010\u001a\u00020\u000bH\u0086 J\t\u0010\u0011\u001a\u00020\u000bH\u0086 J\t\u0010\u0012\u001a\u00020\u000bH\u0086 J\t\u0010\u0013\u001a\u00020\u000bH\u0086 J\t\u0010\u0014\u001a\u00020\u000bH\u0086 J\t\u0010\u0015\u001a\u00020\u000bH\u0086 J\t\u0010\u0016\u001a\u00020\u000bH\u0086 J\t\u0010\u0017\u001a\u00020\u000bH\u0086 J\t\u0010\u0018\u001a\u00020\u000bH\u0086 J\t\u0010\u0019\u001a\u00020\u000bH\u0086 J\t\u0010\u001a\u001a\u00020\u000bH\u0086 J\t\u0010\u001b\u001a\u00020\u000bH\u0086 J\t\u0010\u001c\u001a\u00020\u000bH\u0086 ¨\u0006\u001d"}, d2 = {"Lcom/facebook/react/soloader/OpenSourceMergedSoMapping;", "Lcom/facebook/soloader/ExternalSoMapping;", "<init>", "()V", "mapLibName", "", "input", "invokeJniOnload", "", "libraryName", "libfabricjni_so", "", "libhermes_executor_so", "libhermesinstancejni_so", "libhermestooling_so", "libjsijniprofiler_so", "libjsinspector_so", "libmapbufferjni_so", "libreact_devsupportjni_so", "libreact_featureflagsjni_so", "libreact_newarchdefaults_so", "libreactnative_so", "libreactnativeblob_so", "libreactnativejni_so", "libreactnativejni_common_so", "librninstance_so", "libturbomodulejsijni_so", "libuimanagerjni_so", "libyoga_so", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OpenSourceMergedSoMapping implements ExternalSoMapping {
    public static final OpenSourceMergedSoMapping INSTANCE = new OpenSourceMergedSoMapping();

    public final native int libfabricjni_so();

    public final native int libhermes_executor_so();

    public final native int libhermesinstancejni_so();

    public final native int libhermestooling_so();

    public final native int libjsijniprofiler_so();

    public final native int libjsinspector_so();

    public final native int libmapbufferjni_so();

    public final native int libreact_devsupportjni_so();

    public final native int libreact_featureflagsjni_so();

    public final native int libreact_newarchdefaults_so();

    public final native int libreactnative_so();

    public final native int libreactnativeblob_so();

    public final native int libreactnativejni_common_so();

    public final native int libreactnativejni_so();

    public final native int librninstance_so();

    public final native int libturbomodulejsijni_so();

    public final native int libuimanagerjni_so();

    public final native int libyoga_so();

    private OpenSourceMergedSoMapping() {
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x00a6 A[ORIG_RETURN, RETURN] */
    @Override // com.facebook.soloader.ExternalSoMapping
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String mapLibName(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "input"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            int r0 = r2.hashCode()
            switch(r0) {
                case -1793638007: goto L9e;
                case -1624070447: goto L95;
                case -1570429553: goto L8c;
                case -1438915853: goto L83;
                case -1382694412: goto L7a;
                case -1259441509: goto L71;
                case -579037304: goto L68;
                case -49345041: goto L5f;
                case 3714672: goto L55;
                case 65536138: goto L49;
                case 86183502: goto L40;
                case 352552524: goto L36;
                case 688235659: goto L2c;
                case 716617324: goto L22;
                case 1590431694: goto L18;
                case 2016911584: goto Le;
                default: goto Lc;
            }
        Lc:
            goto La8
        Le:
            java.lang.String r0 = "fabricjni"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L18:
            java.lang.String r0 = "jsinspector"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L22:
            java.lang.String r0 = "uimanagerjni"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L2c:
            java.lang.String r0 = "react_devsupportjni"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L36:
            java.lang.String r0 = "hermes_executor"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L52
            goto La8
        L40:
            java.lang.String r0 = "jsijniprofiler"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L52
            goto La8
        L49:
            java.lang.String r0 = "hermesinstancejni"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L52
            goto La8
        L52:
            java.lang.String r2 = "hermestooling"
            return r2
        L55:
            java.lang.String r0 = "yoga"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L5f:
            java.lang.String r0 = "turbomodulejsijni"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L68:
            java.lang.String r0 = "react_newarchdefaults"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L71:
            java.lang.String r0 = "reactnativejni_common"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L7a:
            java.lang.String r0 = "react_featureflagsjni"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L83:
            java.lang.String r0 = "reactnativeblob"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L8c:
            java.lang.String r0 = "reactnativejni"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L95:
            java.lang.String r0 = "rninstance"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto La6
            goto La8
        L9e:
            java.lang.String r0 = "mapbufferjni"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto La8
        La6:
            java.lang.String r2 = "reactnative"
        La8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.soloader.OpenSourceMergedSoMapping.mapLibName(java.lang.String):java.lang.String");
    }

    @Override // com.facebook.soloader.ExternalSoMapping
    public void invokeJniOnload(String libraryName) {
        Intrinsics.checkNotNullParameter(libraryName, "libraryName");
        switch (libraryName.hashCode()) {
            case -1793638007:
                if (libraryName.equals("mapbufferjni")) {
                    libmapbufferjni_so();
                    return;
                }
                return;
            case -1624070447:
                if (libraryName.equals("rninstance")) {
                    librninstance_so();
                    return;
                }
                return;
            case -1570429553:
                if (libraryName.equals("reactnativejni")) {
                    libreactnativejni_so();
                    return;
                }
                return;
            case -1438915853:
                if (libraryName.equals("reactnativeblob")) {
                    libreactnativeblob_so();
                    return;
                }
                return;
            case -1382694412:
                if (libraryName.equals("react_featureflagsjni")) {
                    libreact_featureflagsjni_so();
                    return;
                }
                return;
            case -1259441509:
                if (libraryName.equals("reactnativejni_common")) {
                    libreactnativejni_common_so();
                    return;
                }
                return;
            case -1033318826:
                if (libraryName.equals("reactnative")) {
                    libreactnative_so();
                    return;
                }
                return;
            case -579037304:
                if (libraryName.equals("react_newarchdefaults")) {
                    libreact_newarchdefaults_so();
                    return;
                }
                return;
            case -49345041:
                if (libraryName.equals("turbomodulejsijni")) {
                    libturbomodulejsijni_so();
                    return;
                }
                return;
            case 3714672:
                if (libraryName.equals("yoga")) {
                    libyoga_so();
                    return;
                }
                return;
            case 65536138:
                if (libraryName.equals("hermesinstancejni")) {
                    libhermesinstancejni_so();
                    return;
                }
                return;
            case 86183502:
                if (libraryName.equals("jsijniprofiler")) {
                    libjsijniprofiler_so();
                    return;
                }
                return;
            case 352552524:
                if (libraryName.equals("hermes_executor")) {
                    libhermes_executor_so();
                    return;
                }
                return;
            case 614482404:
                if (libraryName.equals("hermestooling")) {
                    libhermestooling_so();
                    return;
                }
                return;
            case 688235659:
                if (libraryName.equals("react_devsupportjni")) {
                    libreact_devsupportjni_so();
                    return;
                }
                return;
            case 716617324:
                if (libraryName.equals("uimanagerjni")) {
                    libuimanagerjni_so();
                    return;
                }
                return;
            case 1590431694:
                if (libraryName.equals("jsinspector")) {
                    libjsinspector_so();
                    return;
                }
                return;
            case 2016911584:
                if (libraryName.equals("fabricjni")) {
                    libfabricjni_so();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
