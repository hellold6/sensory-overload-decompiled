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
/* compiled from: SAFDocumentFile.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lexpo/modules/filesystem/unifiedfile/SAFDocumentFile;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.filesystem.unifiedfile.SAFDocumentFile$walkTopDown$1", f = "SAFDocumentFile.kt", i = {0, 1, 1}, l = {TsExtractor.TS_STREAM_TYPE_DVBSUBS, 92}, m = "invokeSuspend", n = {"$this$sequence", "$this$sequence", "$this$forEach$iv"}, s = {"L$0", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class SAFDocumentFile$walkTopDown$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super SAFDocumentFile>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ SAFDocumentFile this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SAFDocumentFile$walkTopDown$1(SAFDocumentFile sAFDocumentFile, Continuation<? super SAFDocumentFile$walkTopDown$1> continuation) {
        super(2, continuation);
        this.this$0 = sAFDocumentFile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SAFDocumentFile$walkTopDown$1 sAFDocumentFile$walkTopDown$1 = new SAFDocumentFile$walkTopDown$1(this.this$0, continuation);
        sAFDocumentFile$walkTopDown$1.L$0 = obj;
        return sAFDocumentFile$walkTopDown$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super SAFDocumentFile> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SAFDocumentFile$walkTopDown$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0096, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
    
        r12 = r11.this$0.getDocumentFile();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0048, code lost:
    
        if (r1.yield(r11.this$0, r11) == r0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0094, code lost:
    
        if (r7.yieldAll(r12, r11) == r0) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x006c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x0094 -> B:6:0x0097). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 == r3) goto L2b
            if (r1 != r2) goto L23
            int r1 = r11.I$1
            int r4 = r11.I$0
            java.lang.Object r5 = r11.L$2
            expo.modules.filesystem.unifiedfile.SAFDocumentFile r5 = (expo.modules.filesystem.unifiedfile.SAFDocumentFile) r5
            java.lang.Object r6 = r11.L$1
            androidx.documentfile.provider.DocumentFile[] r6 = (androidx.documentfile.provider.DocumentFile[]) r6
            java.lang.Object r7 = r11.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            goto L97
        L23:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L2b:
            java.lang.Object r1 = r11.L$0
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto L4b
        L33:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            r1 = r12
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            expo.modules.filesystem.unifiedfile.SAFDocumentFile r12 = r11.this$0
            r4 = r11
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r11.L$0 = r1
            r11.label = r3
            java.lang.Object r12 = r1.yield(r12, r4)
            if (r12 != r0) goto L4b
            goto L96
        L4b:
            expo.modules.filesystem.unifiedfile.SAFDocumentFile r12 = r11.this$0
            boolean r12 = r12.isDirectory()
            if (r12 == 0) goto L99
            expo.modules.filesystem.unifiedfile.SAFDocumentFile r12 = r11.this$0
            androidx.documentfile.provider.DocumentFile r12 = expo.modules.filesystem.unifiedfile.SAFDocumentFile.access$getDocumentFile(r12)
            if (r12 == 0) goto L99
            androidx.documentfile.provider.DocumentFile[] r12 = r12.listFiles()
            if (r12 == 0) goto L99
            expo.modules.filesystem.unifiedfile.SAFDocumentFile r4 = r11.this$0
            int r5 = r12.length
            r6 = 0
            r7 = r1
            r1 = r5
            r5 = r4
            r4 = r6
            r6 = r12
        L6a:
            if (r4 >= r1) goto L99
            r12 = r6[r4]
            expo.modules.filesystem.unifiedfile.SAFDocumentFile r8 = new expo.modules.filesystem.unifiedfile.SAFDocumentFile
            android.content.Context r9 = expo.modules.filesystem.unifiedfile.SAFDocumentFile.access$getContext$p(r5)
            android.net.Uri r12 = r12.getUri()
            java.lang.String r10 = "getUri(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r10)
            r8.<init>(r9, r12)
            kotlin.sequences.Sequence r12 = r8.walkTopDown()
            r11.L$0 = r7
            r11.L$1 = r6
            r11.L$2 = r5
            r11.I$0 = r4
            r11.I$1 = r1
            r11.label = r2
            java.lang.Object r12 = r7.yieldAll(r12, r11)
            if (r12 != r0) goto L97
        L96:
            return r0
        L97:
            int r4 = r4 + r3
            goto L6a
        L99:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.unifiedfile.SAFDocumentFile$walkTopDown$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
