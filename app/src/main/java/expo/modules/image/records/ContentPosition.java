package expo.modules.image.records;

import android.graphics.Matrix;
import android.graphics.RectF;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.image.ImageUtilsKt;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ContentPosition.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 )2\u00020\u0001:\u0001)B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J®\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182y\u0010\u001a\u001au\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00140\u001bj\u0002` H\u0002¢\u0006\u0002\u0010!J\u0018\u0010\"\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0018\u0010#\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J%\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0000¢\u0006\u0002\b(R$\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\tR$\u0010\n\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\tR$\u0010\r\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\tR$\u0010\u0010\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\t¨\u0006*"}, d2 = {"Lexpo/modules/image/records/ContentPosition;", "Lexpo/modules/kotlin/records/Record;", "<init>", "()V", ViewProps.TOP, "", "Lexpo/modules/image/records/ContentPositionValue;", "getTop$annotations", "getTop", "()Ljava/lang/Object;", ViewProps.BOTTOM, "getBottom$annotations", "getBottom", "right", "getRight$annotations", "getRight", "left", "getLeft$annotations", "getLeft", "calcOffset", "", "isReverse", "", "imageRect", "Landroid/graphics/RectF;", "viewRect", "calcAxisOffset", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "value", "isPercentage", "Lexpo/modules/image/records/CalcAxisOffset;", "(Ljava/lang/Object;ZLandroid/graphics/RectF;Landroid/graphics/RectF;Lkotlin/jvm/functions/Function5;)Ljava/lang/Float;", "offsetX", "offsetY", "apply", "", "to", "Landroid/graphics/Matrix;", "apply$expo_image_release", "Companion", "expo-image_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContentPosition implements Record {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ContentPosition center = new ContentPosition();
    private final Object bottom;
    private final Object left;
    private final Object right;
    private final Object top;

    @Field
    public static /* synthetic */ void getBottom$annotations() {
    }

    @Field
    public static /* synthetic */ void getLeft$annotations() {
    }

    @Field
    public static /* synthetic */ void getRight$annotations() {
    }

    @Field
    public static /* synthetic */ void getTop$annotations() {
    }

    public final Object getTop() {
        return this.top;
    }

    public final Object getBottom() {
        return this.bottom;
    }

    public final Object getRight() {
        return this.right;
    }

    public final Object getLeft() {
        return this.left;
    }

    private final Float calcOffset(Object obj, boolean z, RectF rectF, RectF rectF2, Function5<? super Float, ? super RectF, ? super RectF, ? super Boolean, ? super Boolean, Float> function5) {
        float floatValue;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Double) {
            return function5.invoke(Float.valueOf((float) ((Number) obj).doubleValue()), rectF, rectF2, false, Boolean.valueOf(z));
        }
        String str = (String) obj;
        if (Intrinsics.areEqual(str, TtmlNode.CENTER)) {
            floatValue = function5.invoke(Float.valueOf(50.0f), rectF, rectF2, true, Boolean.valueOf(z)).floatValue();
        } else {
            floatValue = function5.invoke(Float.valueOf(Float.parseFloat(StringsKt.removeSuffix(str, (CharSequence) "%"))), rectF, rectF2, true, Boolean.valueOf(z)).floatValue();
        }
        return Float.valueOf(floatValue);
    }

    private final float offsetX(RectF imageRect, RectF viewRect) {
        Float calcOffset = calcOffset(this.left, false, imageRect, viewRect, ContentPosition$offsetX$1.INSTANCE);
        if (calcOffset != null) {
            return calcOffset.floatValue();
        }
        Float calcOffset2 = calcOffset(this.right, true, imageRect, viewRect, ContentPosition$offsetX$2.INSTANCE);
        if (calcOffset2 != null) {
            return calcOffset2.floatValue();
        }
        return ImageUtilsKt.calcXTranslation$default(50.0f, imageRect, viewRect, true, false, 16, null);
    }

    private final float offsetY(RectF imageRect, RectF viewRect) {
        Float calcOffset = calcOffset(this.top, false, imageRect, viewRect, ContentPosition$offsetY$1.INSTANCE);
        if (calcOffset != null) {
            return calcOffset.floatValue();
        }
        Float calcOffset2 = calcOffset(this.bottom, true, imageRect, viewRect, ContentPosition$offsetY$2.INSTANCE);
        if (calcOffset2 != null) {
            return calcOffset2.floatValue();
        }
        return ImageUtilsKt.calcYTranslation$default(50.0f, imageRect, viewRect, true, false, 16, null);
    }

    public final void apply$expo_image_release(Matrix to, RectF imageRect, RectF viewRect) {
        Intrinsics.checkNotNullParameter(to, "to");
        Intrinsics.checkNotNullParameter(imageRect, "imageRect");
        Intrinsics.checkNotNullParameter(viewRect, "viewRect");
        to.postTranslate(offsetX(imageRect, viewRect), offsetY(imageRect, viewRect));
    }

    /* compiled from: ContentPosition.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/image/records/ContentPosition$Companion;", "", "<init>", "()V", TtmlNode.CENTER, "Lexpo/modules/image/records/ContentPosition;", "getCenter", "()Lexpo/modules/image/records/ContentPosition;", "expo-image_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ContentPosition getCenter() {
            return ContentPosition.center;
        }
    }
}
