package expo.modules.medialibrary.next.objects.query;

import android.content.ContentResolver;
import android.content.Context;
import expo.modules.kotlin.runtime.Runtime;
import expo.modules.kotlin.sharedobjects.SharedObject;
import expo.modules.medialibrary.next.exceptions.ContentResolverNotObtainedException;
import expo.modules.medialibrary.next.extensions.WeakReferenceExtensionsKt;
import expo.modules.medialibrary.next.objects.album.Album;
import expo.modules.medialibrary.next.objects.asset.Asset;
import expo.modules.medialibrary.next.objects.asset.factories.AssetFactory;
import expo.modules.medialibrary.next.records.AssetField;
import expo.modules.medialibrary.next.records.SortDescriptor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;

/* compiled from: Query.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0013J\u001c\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130!J\u0016\u0010\"\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0013J\u0016\u0010#\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0013J\u0016\u0010$\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0013J\u0016\u0010%\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u0018J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u0016J\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020+0!H\u0086@¢\u0006\u0002\u0010,R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00050\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0019R\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0019¨\u0006-"}, d2 = {"Lexpo/modules/medialibrary/next/objects/query/Query;", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "assetFactory", "Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "context", "Landroid/content/Context;", "<init>", "(Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;Landroid/content/Context;)V", "getAssetFactory", "()Lexpo/modules/medialibrary/next/objects/asset/factories/AssetFactory;", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "clauses", "", "", "args", "orderBy", "Lexpo/modules/medialibrary/next/records/SortDescriptor;", "limit", "", "Ljava/lang/Integer;", "offset", "eq", "field", "Lexpo/modules/medialibrary/next/records/AssetField;", "value", "within", "values", "", "gt", "gte", "lt", "lte", "album", "Lexpo/modules/medialibrary/next/objects/album/Album;", NewHtcHomeBadger.COUNT, "descriptor", "exe", "Lexpo/modules/medialibrary/next/objects/asset/Asset;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-media-library_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Query extends SharedObject {
    private final List<String> args;
    private final AssetFactory assetFactory;
    private final List<String> clauses;
    private final WeakReference<Context> contextRef;
    private Integer limit;
    private Integer offset;
    private final List<SortDescriptor> orderBy;

    public final AssetFactory getAssetFactory() {
        return this.assetFactory;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Query(AssetFactory assetFactory, Context context) {
        super((Runtime) null, 1, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(assetFactory, "assetFactory");
        Intrinsics.checkNotNullParameter(context, "context");
        this.assetFactory = assetFactory;
        this.contextRef = new WeakReference<>(context);
        this.clauses = new ArrayList();
        this.args = new ArrayList();
        this.orderBy = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContentResolver getContentResolver() {
        ContentResolver contentResolver = WeakReferenceExtensionsKt.getOrThrow(this.contextRef).getContentResolver();
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new ContentResolverNotObtainedException(null, 1, null);
    }

    public final Query eq(AssetField field, String value) {
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(value, "value");
        this.clauses.add(field.toMediaStoreColumn() + " = ?");
        this.args.add(value);
        return this;
    }

    public final Query within(AssetField field, List<String> values) {
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(values, "values");
        this.clauses.add(field.toMediaStoreColumn() + " IN (" + CollectionsKt.joinToString$default(values, ", ", null, null, 0, null, new Function1() { // from class: expo.modules.medialibrary.next.objects.query.Query$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence within$lambda$2$lambda$1;
                within$lambda$2$lambda$1 = Query.within$lambda$2$lambda$1((String) obj);
                return within$lambda$2$lambda$1;
            }
        }, 30, null) + ")");
        this.args.addAll(values);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence within$lambda$2$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "?";
    }

    public final Query gt(AssetField field, String value) {
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(value, "value");
        this.clauses.add(field.toMediaStoreColumn() + " > ?");
        this.args.add(value);
        return this;
    }

    public final Query gte(AssetField field, String value) {
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(value, "value");
        this.clauses.add(field.toMediaStoreColumn() + " >= ?");
        this.args.add(value);
        return this;
    }

    public final Query lt(AssetField field, String value) {
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(value, "value");
        this.clauses.add(field.toMediaStoreColumn() + " < ?");
        this.args.add(value);
        return this;
    }

    public final Query lte(AssetField field, String value) {
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(value, "value");
        this.clauses.add(field.toMediaStoreColumn() + " <= ?");
        this.args.add(value);
        return this;
    }

    public final Query limit(int limit) {
        this.limit = Integer.valueOf(limit);
        return this;
    }

    public final Query album(Album album) {
        Intrinsics.checkNotNullParameter(album, "album");
        this.clauses.add("bucket_id = ?");
        this.args.add(album.getId());
        return this;
    }

    public final Query offset(int count) {
        this.offset = Integer.valueOf(count);
        return this;
    }

    public final Query orderBy(SortDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.orderBy.add(descriptor);
        return this;
    }

    public final Object exe(Continuation<? super List<Asset>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new Query$exe$2(this, null), continuation);
    }
}
