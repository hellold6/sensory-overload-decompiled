package expo.modules.documentpicker;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.FileUtils;
import androidx.tracing.Trace;
import expo.modules.core.utilities.FileUtilities;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.events.OnActivityResultPayload;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.types.TypeConverterProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import org.apache.commons.io.FilenameUtils;

/* compiled from: DocumentPickerModule.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lexpo/modules/documentpicker/DocumentPickerModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "pendingPromise", "Lexpo/modules/kotlin/Promise;", "copyToCacheDirectory", "", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "copyDocumentToCacheDirectory", "Landroid/net/Uri;", "documentUri", "name", "", "handleSingleSelection", "", "intent", "Landroid/content/Intent;", "handleMultipleSelection", "readDocumentDetails", "Lexpo/modules/documentpicker/DocumentInfo;", "uri", "expo-document-picker_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DocumentPickerModule extends Module {
    private boolean copyToCacheDirectory = true;
    private Promise pendingPromise;

    private final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        DocumentPickerModule documentPickerModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (documentPickerModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(documentPickerModule);
            moduleDefinitionBuilder.Name("ExpoDocumentPicker");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            TypeConverterProvider converters = moduleDefinitionBuilder2.getConverters();
            AnyType[] anyTypeArr = new AnyType[1];
            AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(DocumentPickerOptions.class), false));
            if (anyType == null) {
                anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DocumentPickerOptions.class), false, new Function0<KType>() { // from class: expo.modules.documentpicker.DocumentPickerModule$definition$lambda$3$$inlined$AsyncFunctionWithPromise$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(DocumentPickerOptions.class);
                    }
                }), converters);
            }
            anyTypeArr[0] = anyType;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("getDocumentAsync", anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.documentpicker.DocumentPickerModule$definition$lambda$3$$inlined$AsyncFunctionWithPromise$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Promise promise2;
                    String str;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    DocumentPickerOptions documentPickerOptions = (DocumentPickerOptions) objArr[0];
                    promise2 = DocumentPickerModule.this.pendingPromise;
                    if (promise2 != null) {
                        throw new PickingInProgressException();
                    }
                    if (!documentPickerOptions.getType().isEmpty()) {
                        DocumentPickerModule.this.pendingPromise = promise;
                        DocumentPickerModule.this.copyToCacheDirectory = documentPickerOptions.getCopyToCacheDirectory();
                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                        intent.addCategory("android.intent.category.OPENABLE");
                        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", documentPickerOptions.getMultiple());
                        if (documentPickerOptions.getType().size() > 1) {
                            intent.putExtra("android.intent.extra.MIME_TYPES", (String[]) documentPickerOptions.getType().toArray(new String[0]));
                            str = "*/*";
                        } else {
                            str = documentPickerOptions.getType().get(0);
                        }
                        intent.setType(str);
                        DocumentPickerModule.this.getAppContext().getThrowingActivity().startActivityForResult(intent, 4137);
                        return;
                    }
                    throw new DocumentPickerOptionsEmptyListException();
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder2.getAsyncFunctions().put("getDocumentAsync", asyncFunctionWithPromiseComponent);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2 = asyncFunctionWithPromiseComponent;
            moduleDefinitionBuilder.getEventListeners().put(EventName.ON_ACTIVITY_RESULT, new EventListenerWithSenderAndPayload(EventName.ON_ACTIVITY_RESULT, new Function2<Activity, OnActivityResultPayload, Unit>() { // from class: expo.modules.documentpicker.DocumentPickerModule$definition$lambda$3$$inlined$OnActivityResult$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Activity activity, OnActivityResultPayload onActivityResultPayload) {
                    invoke2(activity, onActivityResultPayload);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Activity sender, OnActivityResultPayload payload) {
                    Promise promise;
                    Promise promise2;
                    UnexpectedException unexpectedException;
                    ClipData clipData;
                    Intrinsics.checkNotNullParameter(sender, "sender");
                    Intrinsics.checkNotNullParameter(payload, "payload");
                    int requestCode = payload.getRequestCode();
                    int resultCode = payload.getResultCode();
                    Intent data = payload.getData();
                    if (requestCode == 4137) {
                        promise = DocumentPickerModule.this.pendingPromise;
                        if (promise == null) {
                            return;
                        }
                        promise2 = DocumentPickerModule.this.pendingPromise;
                        Intrinsics.checkNotNull(promise2);
                        if (resultCode == -1) {
                            if (data != null) {
                                try {
                                    clipData = data.getClipData();
                                } catch (Exception e) {
                                    Exception exc = e;
                                    if (exc instanceof CodedException) {
                                        unexpectedException = (CodedException) exc;
                                    } else if (exc instanceof expo.modules.core.errors.CodedException) {
                                        expo.modules.core.errors.CodedException codedException = (expo.modules.core.errors.CodedException) exc;
                                        String code = codedException.getCode();
                                        Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                                        unexpectedException = new CodedException(code, codedException.getMessage(), codedException.getCause());
                                    } else {
                                        unexpectedException = new UnexpectedException(exc);
                                    }
                                    promise2.reject(unexpectedException);
                                }
                            } else {
                                clipData = null;
                            }
                            if (clipData != null) {
                                DocumentPickerModule.this.handleMultipleSelection(data);
                            } else {
                                DocumentPickerModule.this.handleSingleSelection(data);
                            }
                        } else {
                            promise2.resolve(new DocumentPickerResult(true, null, 2, null));
                        }
                        DocumentPickerModule.this.pendingPromise = null;
                    }
                }
            }));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    private final Uri copyDocumentToCacheDirectory(Uri documentUri, String name) {
        File file = new File(FileUtilities.generateOutputPath(getContext().getCacheDir(), "DocumentPicker", FilenameUtils.getExtension(name)));
        FileOutputStream openInputStream = getContext().getContentResolver().openInputStream(documentUri);
        try {
            InputStream inputStream = openInputStream;
            if (inputStream == null) {
                throw new FileNotFoundException("Inputstream for " + documentUri + " was null.");
            }
            openInputStream = new FileOutputStream(file);
            try {
                FileOutputStream fileOutputStream = openInputStream;
                if (Build.VERSION.SDK_INT >= 29) {
                    FileUtils.copy(inputStream, fileOutputStream);
                } else {
                    ByteStreamsKt.copyTo$default(inputStream, fileOutputStream, 0, 2, null);
                }
                CloseableKt.closeFinally(openInputStream, null);
                CloseableKt.closeFinally(openInputStream, null);
                Uri fromFile = Uri.fromFile(file);
                Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(...)");
                return fromFile;
            } finally {
            }
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleSingleSelection(Intent intent) {
        Uri data;
        if (intent != null && (data = intent.getData()) != null) {
            Unit unit = null;
            DocumentPickerResult documentPickerResult = new DocumentPickerResult(false, CollectionsKt.listOf(readDocumentDetails(data)), 1, null);
            Promise promise = this.pendingPromise;
            if (promise != null) {
                promise.resolve(documentPickerResult);
                unit = Unit.INSTANCE;
            }
            if (unit != null) {
                return;
            }
        }
        throw new FailedToReadDocumentException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleMultipleSelection(Intent intent) {
        ClipData clipData;
        ClipData.Item itemAt;
        Uri uri;
        ClipData clipData2;
        int itemCount = (intent == null || (clipData2 = intent.getClipData()) == null) ? 0 : clipData2.getItemCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < itemCount; i++) {
            if (intent == null || (clipData = intent.getClipData()) == null || (itemAt = clipData.getItemAt(i)) == null || (uri = itemAt.getUri()) == null) {
                throw new FailedToReadDocumentException();
            }
            arrayList.add(readDocumentDetails(uri));
        }
        Promise promise = this.pendingPromise;
        if (promise != null) {
            promise.resolve(new DocumentPickerResult(false, arrayList, 1, null));
        }
    }

    private final DocumentInfo readDocumentDetails(Uri uri) {
        DocumentInfo read = new DocumentDetailsReader(getContext()).read(uri);
        return !this.copyToCacheDirectory ? read : DocumentInfo.copy$default(read, copyDocumentToCacheDirectory(uri, read.getName()), null, null, null, 0L, 30, null);
    }
}
