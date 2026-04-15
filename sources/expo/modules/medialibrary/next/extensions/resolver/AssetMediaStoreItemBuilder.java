package expo.modules.medialibrary.next.extensions.resolver;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssetMediaStoreItem.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0011\u001a\u00020\u0012J\u0012\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreItemBuilder;", "", "<init>", "()V", "displayName", "", "height", "", "Ljava/lang/Integer;", "width", "dateTaken", "", "Ljava/lang/Long;", "dateModified", "duration", "data", "bucketId", "build", "Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreItem;", "set", "", "Landroid/database/Cursor;", "property", "Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreProperty;", "Companion", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetMediaStoreItemBuilder {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String bucketId;
    private String data;
    private Long dateModified;
    private Long dateTaken;
    private String displayName;
    private Long duration;
    private Integer height;
    private Integer width;

    /* compiled from: AssetMediaStoreItem.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssetMediaStoreProperty.values().length];
            try {
                iArr[AssetMediaStoreProperty.Data.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AssetMediaStoreProperty.DisplayName.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AssetMediaStoreProperty.Height.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AssetMediaStoreProperty.Width.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AssetMediaStoreProperty.DateTaken.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[AssetMediaStoreProperty.DateModified.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[AssetMediaStoreProperty.Duration.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[AssetMediaStoreProperty.BucketId.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final AssetMediaStoreItem build() {
        return new AssetMediaStoreItem(this.displayName, this.height, this.width, this.dateTaken, this.dateModified, this.duration, this.data, this.bucketId);
    }

    public final void set(Cursor cursor, AssetMediaStoreProperty property) {
        Intrinsics.checkNotNullParameter(cursor, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        switch (WhenMappings.$EnumSwitchMapping$0[property.ordinal()]) {
            case 1:
                this.data = property.getString(cursor);
                return;
            case 2:
                this.displayName = property.getString(cursor);
                return;
            case 3:
                this.height = property.getInt(cursor);
                return;
            case 4:
                this.width = property.getInt(cursor);
                return;
            case 5:
                this.dateTaken = property.getLong(cursor);
                return;
            case 6:
                this.dateModified = property.getLong(cursor);
                return;
            case 7:
                this.duration = property.getLong(cursor);
                return;
            case 8:
                this.bucketId = property.getString(cursor);
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* compiled from: AssetMediaStoreItem.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreItemBuilder$Companion;", "", "<init>", "()V", "buildAssetMediaStoreItem", "Lexpo/modules/medialibrary/next/extensions/resolver/AssetMediaStoreItem;", "Landroid/database/Cursor;", "includeDuration", "", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AssetMediaStoreItem buildAssetMediaStoreItem(Cursor cursor, boolean z) {
            Intrinsics.checkNotNullParameter(cursor, "<this>");
            AssetMediaStoreItemBuilder assetMediaStoreItemBuilder = new AssetMediaStoreItemBuilder();
            AssetMediaStoreProperty[] values = AssetMediaStoreProperty.values();
            ArrayList arrayList = new ArrayList();
            for (AssetMediaStoreProperty assetMediaStoreProperty : values) {
                if (z || assetMediaStoreProperty != AssetMediaStoreProperty.Duration) {
                    arrayList.add(assetMediaStoreProperty);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                assetMediaStoreItemBuilder.set(cursor, (AssetMediaStoreProperty) it.next());
            }
            return assetMediaStoreItemBuilder.build();
        }
    }
}
