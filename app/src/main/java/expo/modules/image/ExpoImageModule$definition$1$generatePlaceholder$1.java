package expo.modules.image;

import androidx.media3.extractor.ts.TsExtractor;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExpoImageModule.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.image.ExpoImageModule", f = "ExpoImageModule.kt", i = {0}, l = {131, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO}, m = "definition$lambda$30$generatePlaceholder", n = {"encoder"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class ExpoImageModule$definition$1$generatePlaceholder$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExpoImageModule$definition$1$generatePlaceholder$1(Continuation<? super ExpoImageModule$definition$1$generatePlaceholder$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object definition$lambda$30$generatePlaceholder;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        definition$lambda$30$generatePlaceholder = ExpoImageModule.definition$lambda$30$generatePlaceholder(null, null, null, this);
        return definition$lambda$30$generatePlaceholder;
    }
}
