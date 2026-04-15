package expo.modules.kotlin.jni;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: ExpectedType.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\f\u001a\u00020\rH\u0007J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0007J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0007J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0015"}, d2 = {"Lexpo/modules/kotlin/jni/SingleType;", "", "expectedCppType", "Lexpo/modules/kotlin/jni/CppType;", "parameterTypes", "", "Lexpo/modules/kotlin/jni/ExpectedType;", "<init>", "(Lexpo/modules/kotlin/jni/CppType;[Lexpo/modules/kotlin/jni/ExpectedType;)V", "getExpectedCppType$expo_modules_core_release", "()Lexpo/modules/kotlin/jni/CppType;", "[Lexpo/modules/kotlin/jni/ExpectedType;", "getCppType", "", "getFirstParameterType", "getSecondParameterType", "equals", "", "other", "hashCode", "Companion", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SingleType {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CppType expectedCppType;
    private final ExpectedType[] parameterTypes;

    public SingleType(CppType expectedCppType, ExpectedType[] expectedTypeArr) {
        Intrinsics.checkNotNullParameter(expectedCppType, "expectedCppType");
        this.expectedCppType = expectedCppType;
        this.parameterTypes = expectedTypeArr;
    }

    public /* synthetic */ SingleType(CppType cppType, ExpectedType[] expectedTypeArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(cppType, (i & 2) != 0 ? null : expectedTypeArr);
    }

    /* renamed from: getExpectedCppType$expo_modules_core_release, reason: from getter */
    public final CppType getExpectedCppType() {
        return this.expectedCppType;
    }

    public final int getCppType() {
        return this.expectedCppType.getValue();
    }

    public final ExpectedType getFirstParameterType() {
        ExpectedType[] expectedTypeArr = this.parameterTypes;
        if (expectedTypeArr != null) {
            return expectedTypeArr[0];
        }
        return null;
    }

    public final ExpectedType getSecondParameterType() {
        ExpectedType[] expectedTypeArr = this.parameterTypes;
        if (expectedTypeArr != null) {
            return expectedTypeArr[1];
        }
        return null;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type expo.modules.kotlin.jni.SingleType");
        SingleType singleType = (SingleType) other;
        return this.expectedCppType == singleType.expectedCppType && Arrays.equals(this.parameterTypes, singleType.parameterTypes);
    }

    public int hashCode() {
        int hashCode = this.expectedCppType.hashCode() * 31;
        ExpectedType[] expectedTypeArr = this.parameterTypes;
        return hashCode + (expectedTypeArr != null ? Arrays.hashCode(expectedTypeArr) : 0);
    }

    /* compiled from: ExpectedType.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005¨\u0006\b"}, d2 = {"Lexpo/modules/kotlin/jni/SingleType$Companion;", "", "<init>", "()V", "merge", "Lexpo/modules/kotlin/jni/SingleType;", "first", "second", "expo-modules-core_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SingleType merge(SingleType first, SingleType second) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            if (first.getExpectedCppType() == second.getExpectedCppType()) {
                ExpectedType[] expectedTypeArr = first.parameterTypes;
                ExpectedType[] expectedTypeArr2 = second.parameterTypes;
                if (expectedTypeArr == null || expectedTypeArr2 == null) {
                    return first;
                }
                if (expectedTypeArr.length != expectedTypeArr2.length) {
                    throw new IllegalArgumentException(("Cannot merge types with different number of parameters: " + first.parameterTypes.length + " and " + second.parameterTypes.length).toString());
                }
                IntRange until = RangesKt.until(0, expectedTypeArr.length);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                Iterator<Integer> it = until.iterator();
                while (it.hasNext()) {
                    int nextInt = ((IntIterator) it).nextInt();
                    arrayList.add(ExpectedType.INSTANCE.merge(expectedTypeArr[nextInt], expectedTypeArr2[nextInt]));
                }
                return new SingleType(first.getExpectedCppType(), (ExpectedType[]) arrayList.toArray(new ExpectedType[0]));
            }
            throw new IllegalArgumentException("Cannot merge types with different CppType: " + first.getExpectedCppType() + " and " + second.getExpectedCppType());
        }
    }
}
