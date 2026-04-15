package expo.modules.filesystem.unifiedfile;

import androidx.media3.extractor.ts.TsExtractor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AssetFile.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lexpo/modules/filesystem/unifiedfile/AssetFile;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.filesystem.unifiedfile.AssetFile$walkTopDown$1", f = "AssetFile.kt", i = {0, 1, 1}, l = {TsExtractor.TS_STREAM_TYPE_HDMV_DTS, TsExtractor.TS_STREAM_TYPE_DTS_HD}, m = "invokeSuspend", n = {"$this$sequence", "$this$sequence", "$this$forEach$iv"}, s = {"L$0", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class AssetFile$walkTopDown$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super AssetFile>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ AssetFile this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetFile$walkTopDown$1(AssetFile assetFile, Continuation<? super AssetFile$walkTopDown$1> continuation) {
        super(2, continuation);
        this.this$0 = assetFile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AssetFile$walkTopDown$1 assetFile$walkTopDown$1 = new AssetFile$walkTopDown$1(this.this$0, continuation);
        assetFile$walkTopDown$1.L$0 = obj;
        return assetFile$walkTopDown$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super AssetFile> sequenceScope, Continuation<? super Unit> continuation) {
        return ((AssetFile$walkTopDown$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x00c0, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0048, code lost:
    
        if (r1.yield(r14.this$0, r14) == r0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00be, code lost:
    
        if (r7.yieldAll(r15, r14) == r0) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0075  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x00be -> B:6:0x00c1). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 == r3) goto L2b
            if (r1 != r2) goto L23
            int r1 = r14.I$1
            int r4 = r14.I$0
            java.lang.Object r5 = r14.L$2
            expo.modules.filesystem.unifiedfile.AssetFile r5 = (expo.modules.filesystem.unifiedfile.AssetFile) r5
            java.lang.Object r6 = r14.L$1
            java.lang.String[] r6 = (java.lang.String[]) r6
            java.lang.Object r7 = r14.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            kotlin.ResultKt.throwOnFailure(r15)
            goto Lc1
        L23:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L2b:
            java.lang.Object r1 = r14.L$0
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L4c
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            r1 = r15
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            expo.modules.filesystem.unifiedfile.AssetFile r15 = r14.this$0
            r4 = r14
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r14.L$0 = r1
            r14.label = r3
            java.lang.Object r15 = r1.yield(r15, r4)
            if (r15 != r0) goto L4c
            goto Lc0
        L4c:
            expo.modules.filesystem.unifiedfile.AssetFile r15 = r14.this$0
            boolean r15 = r15.isDirectory()
            if (r15 == 0) goto Lc3
            expo.modules.filesystem.unifiedfile.AssetFile r15 = r14.this$0
            android.content.Context r15 = expo.modules.filesystem.unifiedfile.AssetFile.access$getContext$p(r15)
            android.content.res.AssetManager r15 = r15.getAssets()
            expo.modules.filesystem.unifiedfile.AssetFile r4 = r14.this$0
            java.lang.String r4 = r4.getPath()
            java.lang.String[] r15 = r15.list(r4)
            if (r15 == 0) goto Lc3
            expo.modules.filesystem.unifiedfile.AssetFile r4 = r14.this$0
            int r5 = r15.length
            r6 = 0
            r7 = r1
            r1 = r5
            r5 = r4
            r4 = r6
            r6 = r15
        L73:
            if (r4 >= r1) goto Lc3
            r15 = r6[r4]
            android.net.Uri r8 = r5.getUri()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.StringBuilder r8 = r9.append(r8)
            java.lang.String r9 = "/"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r15 = r8.append(r15)
            java.lang.String r8 = r15.toString()
            r12 = 4
            r13 = 0
            java.lang.String r9 = "//"
            java.lang.String r10 = "/"
            r11 = 0
            java.lang.String r15 = kotlin.text.StringsKt.replace$default(r8, r9, r10, r11, r12, r13)
            android.net.Uri r15 = android.net.Uri.parse(r15)
            expo.modules.filesystem.unifiedfile.AssetFile r8 = new expo.modules.filesystem.unifiedfile.AssetFile
            android.content.Context r9 = expo.modules.filesystem.unifiedfile.AssetFile.access$getContext$p(r5)
            r8.<init>(r9, r15)
            kotlin.sequences.Sequence r15 = r8.walkTopDown()
            r14.L$0 = r7
            r14.L$1 = r6
            r14.L$2 = r5
            r14.I$0 = r4
            r14.I$1 = r1
            r14.label = r2
            java.lang.Object r15 = r7.yieldAll(r15, r14)
            if (r15 != r0) goto Lc1
        Lc0:
            return r0
        Lc1:
            int r4 = r4 + r3
            goto L73
        Lc3:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.unifiedfile.AssetFile$walkTopDown$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
