package expo.modules.securestore;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.exifinterface.media.ExifInterface;
import androidx.tracing.Trace;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.functions.SuspendFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.functions.UntypedAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeProvider;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.types.ReturnType;
import expo.modules.kotlin.types.ReturnTypeProvider;
import expo.modules.kotlin.types.TypeConverterProvider;
import expo.modules.securestore.encryptors.AESEncryptor;
import expo.modules.securestore.encryptors.HybridAESEncryptor;
import expo.modules.securestore.encryptors.KeyBasedEncryptor;
import java.security.KeyStore;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.json.JSONObject;

/* compiled from: SecureStoreModule.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0016J \u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0082@¢\u0006\u0002\u0010\u0017J(\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0016H\u0082@¢\u0006\u0002\u0010\u001bJ0\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0082@¢\u0006\u0002\u0010!J0\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0013H\u0002J\u0018\u0010'\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0013H\u0002J\u0010\u0010*\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u0013H\u0002J=\u0010+\u001a\u0004\u0018\u0001H,\"\b\b\u0000\u0010,*\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H,0/2\f\u00100\u001a\b\u0012\u0004\u0012\u0002H,012\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0002\u00102JE\u00103\u001a\u0004\u0018\u0001H,\"\b\b\u0000\u0010,*\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H,0/2\f\u00100\u001a\b\u0012\u0004\u0012\u0002H,012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010%\u001a\u00020 H\u0002¢\u0006\u0002\u00104JC\u00105\u001a\u0002H,\"\b\b\u0000\u0010,*\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H,0/2\f\u00100\u001a\b\u0012\u0004\u0012\u0002H,012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010%\u001a\u00020 H\u0002¢\u0006\u0002\u00104JM\u00106\u001a\u0004\u0018\u0001H,\"\b\b\u0000\u0010,*\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H,0/2\f\u00100\u001a\b\u0012\u0004\u0012\u0002H,012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010%\u001a\u00020 2\u0006\u00107\u001a\u00020 H\u0002¢\u0006\u0002\u00108J\u0006\u00109\u001a\u00020\u001aJ\u0018\u0010:\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lexpo/modules/securestore/SecureStoreModule;", "Lexpo/modules/kotlin/modules/Module;", "<init>", "()V", "mAESEncryptor", "Lexpo/modules/securestore/encryptors/AESEncryptor;", "reactContext", "Landroid/content/Context;", "getReactContext", "()Landroid/content/Context;", "keyStore", "Ljava/security/KeyStore;", "hybridAESEncryptor", "Lexpo/modules/securestore/encryptors/HybridAESEncryptor;", "authenticationHelper", "Lexpo/modules/securestore/AuthenticationHelper;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getItemImpl", "", "key", "options", "Lexpo/modules/securestore/SecureStoreOptions;", "(Ljava/lang/String;Lexpo/modules/securestore/SecureStoreOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readJSONEncodedItem", "prefs", "Landroid/content/SharedPreferences;", "(Ljava/lang/String;Landroid/content/SharedPreferences;Lexpo/modules/securestore/SecureStoreOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setItemImpl", "", "value", "keyIsInvalidated", "", "(Ljava/lang/String;Ljava/lang/String;Lexpo/modules/securestore/SecureStoreOptions;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveEncryptedItem", "encryptedItem", "Lorg/json/JSONObject;", AuthenticationHelper.REQUIRE_AUTHENTICATION_PROPERTY, "keychainService", "deleteItemImpl", "removeKeyFromKeystore", "keyStoreAlias", "removeAllEntriesUnderKeychainService", "getLegacyKeyEntry", ExifInterface.LONGITUDE_EAST, "Ljava/security/KeyStore$Entry;", "keyStoreEntryClass", "Ljava/lang/Class;", "encryptor", "Lexpo/modules/securestore/encryptors/KeyBasedEncryptor;", "(Ljava/lang/Class;Lexpo/modules/securestore/encryptors/KeyBasedEncryptor;Lexpo/modules/securestore/SecureStoreOptions;)Ljava/security/KeyStore$Entry;", "getKeyEntry", "(Ljava/lang/Class;Lexpo/modules/securestore/encryptors/KeyBasedEncryptor;Lexpo/modules/securestore/SecureStoreOptions;Z)Ljava/security/KeyStore$Entry;", "getOrCreateKeyEntry", "getKeyEntryCompat", SecureStoreModule.USES_KEYSTORE_SUFFIX_PROPERTY, "(Ljava/lang/Class;Lexpo/modules/securestore/encryptors/KeyBasedEncryptor;Lexpo/modules/securestore/SecureStoreOptions;ZZ)Ljava/security/KeyStore$Entry;", "getSharedPreferences", "createKeychainAwareKey", "Companion", "expo-secure-store_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class SecureStoreModule extends Module {
    public static final String AUTHENTICATED_KEYSTORE_SUFFIX = "keystoreAuthenticated";
    public static final String DEFAULT_KEYSTORE_ALIAS = "key_v1";
    private static final String KEYSTORE_ALIAS_PROPERTY = "keystoreAlias";
    private static final String KEYSTORE_PROVIDER = "AndroidKeyStore";
    private static final String SCHEME_PROPERTY = "scheme";
    private static final String SHARED_PREFERENCES_NAME = "SecureStore";
    public static final String TAG = "ExpoSecureStore";
    public static final String UNAUTHENTICATED_KEYSTORE_SUFFIX = "keystoreUnauthenticated";
    public static final String USES_KEYSTORE_SUFFIX_PROPERTY = "usesKeystoreSuffix";
    private AuthenticationHelper authenticationHelper;
    private HybridAESEncryptor hybridAESEncryptor;
    private KeyStore keyStore;
    private final AESEncryptor mAESEncryptor = new AESEncryptor();

    public Context getReactContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        UntypedAsyncFunctionComponent untypedAsyncFunctionComponent;
        SecureStoreModule secureStoreModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (secureStoreModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(secureStoreModule);
            moduleDefinitionBuilder.Name(TAG);
            AsyncFunctionBuilder AsyncFunction = moduleDefinitionBuilder.AsyncFunction("setValueWithKeyAsync");
            String name = AsyncFunction.getName();
            TypeConverterProvider converters = AsyncFunction.getConverters();
            AnyType[] anyTypeArr = new AnyType[3];
            AnyType anyType = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType == null) {
                anyType = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Coroutine$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters);
            }
            anyTypeArr[0] = anyType;
            AnyType anyType2 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType2 == null) {
                anyType2 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Coroutine$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters);
            }
            anyTypeArr[1] = anyType2;
            AnyType anyType3 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false));
            if (anyType3 == null) {
                anyType3 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Coroutine$3
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(SecureStoreOptions.class);
                    }
                }), converters);
            }
            anyTypeArr[2] = anyType3;
            AsyncFunction.setAsyncFunctionComponent(new SuspendFunctionComponent(name, anyTypeArr, new SecureStoreModule$definition$lambda$7$$inlined$Coroutine$4(null, this)));
            AsyncFunctionBuilder AsyncFunction2 = moduleDefinitionBuilder.AsyncFunction("getValueWithKeyAsync");
            String name2 = AsyncFunction2.getName();
            TypeConverterProvider converters2 = AsyncFunction2.getConverters();
            AnyType[] anyTypeArr2 = new AnyType[2];
            AnyType anyType4 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType4 == null) {
                anyType4 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Coroutine$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters2);
            }
            anyTypeArr2[0] = anyType4;
            AnyType anyType5 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false));
            if (anyType5 == null) {
                anyType5 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Coroutine$6
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(SecureStoreOptions.class);
                    }
                }), converters2);
            }
            anyTypeArr2[1] = anyType5;
            AsyncFunction2.setAsyncFunctionComponent(new SuspendFunctionComponent(name2, anyTypeArr2, new SecureStoreModule$definition$lambda$7$$inlined$Coroutine$7(null, this)));
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            TypeConverterProvider converters3 = moduleDefinitionBuilder2.getConverters();
            AnyType[] anyTypeArr3 = new AnyType[3];
            AnyType anyType6 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType6 == null) {
                anyType6 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Function$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters3);
            }
            anyTypeArr3[0] = anyType6;
            AnyType anyType7 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), true));
            if (anyType7 == null) {
                anyType7 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Function$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }), converters3);
            }
            anyTypeArr3[1] = anyType7;
            AnyType anyType8 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false));
            if (anyType8 == null) {
                anyType8 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Function$3
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(SecureStoreOptions.class);
                    }
                }), converters3);
            }
            anyTypeArr3[2] = anyType8;
            ReturnTypeProvider returnTypeProvider = ReturnTypeProvider.INSTANCE;
            ReturnType returnType = returnTypeProvider.getTypes().get(Reflection.getOrCreateKotlinClass(Unit.class));
            if (returnType == null) {
                returnType = new ReturnType(Reflection.getOrCreateKotlinClass(Unit.class));
                returnTypeProvider.getTypes().put(Reflection.getOrCreateKotlinClass(Unit.class), returnType);
            }
            moduleDefinitionBuilder2.getSyncFunctions().put("setValueWithKeySync", new SyncFunctionComponent("setValueWithKeySync", anyTypeArr3, returnType, new Function1<Object[], Object>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Function$4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    SecureStoreOptions secureStoreOptions = (SecureStoreOptions) objArr[2];
                    String str = (String) obj2;
                    String str2 = (String) obj;
                    if (str != null) {
                        BuildersKt__BuildersKt.runBlocking$default(null, new SecureStoreModule$definition$1$3$1(SecureStoreModule.this, str, str2, secureStoreOptions, null), 1, null);
                        return Unit.INSTANCE;
                    }
                    throw new NullKeyException();
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            TypeConverterProvider converters4 = moduleDefinitionBuilder3.getConverters();
            AnyType[] anyTypeArr4 = new AnyType[2];
            AnyType anyType9 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType9 == null) {
                anyType9 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Function$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters4);
            }
            anyTypeArr4[0] = anyType9;
            AnyType anyType10 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false));
            if (anyType10 == null) {
                anyType10 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Function$6
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(SecureStoreOptions.class);
                    }
                }), converters4);
            }
            anyTypeArr4[1] = anyType10;
            ReturnTypeProvider returnTypeProvider2 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType2 = returnTypeProvider2.getTypes().get(Reflection.getOrCreateKotlinClass(String.class));
            if (returnType2 == null) {
                returnType2 = new ReturnType(Reflection.getOrCreateKotlinClass(String.class));
                returnTypeProvider2.getTypes().put(Reflection.getOrCreateKotlinClass(String.class), returnType2);
            }
            moduleDefinitionBuilder3.getSyncFunctions().put("getValueWithKeySync", new SyncFunctionComponent("getValueWithKeySync", anyTypeArr4, returnType2, new Function1<Object[], Object>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$Function$7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Object runBlocking$default;
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    SecureStoreOptions secureStoreOptions = (SecureStoreOptions) objArr[1];
                    runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new SecureStoreModule$definition$1$4$1(SecureStoreModule.this, (String) obj, secureStoreOptions, null), 1, null);
                    return (String) runBlocking$default;
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            TypeConverterProvider converters5 = moduleDefinitionBuilder4.getConverters();
            AnyType[] anyTypeArr5 = new AnyType[2];
            AnyType anyType11 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(String.class), false));
            if (anyType11 == null) {
                anyType11 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$AsyncFunction$1
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }), converters5);
            }
            anyTypeArr5[0] = anyType11;
            AnyType anyType12 = AnyTypeProvider.INSTANCE.getTypesMap().get(new Pair(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false));
            if (anyType12 == null) {
                anyType12 = new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(SecureStoreOptions.class), false, new Function0<KType>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(SecureStoreOptions.class);
                    }
                }), converters5);
            }
            anyTypeArr5[1] = anyType12;
            Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$AsyncFunction$3
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<destruct>");
                    Object obj = objArr[0];
                    SecureStoreOptions secureStoreOptions = (SecureStoreOptions) objArr[1];
                    String str = (String) obj;
                    try {
                        SecureStoreModule.this.deleteItemImpl(str, secureStoreOptions);
                        return Unit.INSTANCE;
                    } catch (CodedException e) {
                        throw e;
                    } catch (Exception e2) {
                        throw new DeleteException(e2.getMessage(), str, secureStoreOptions.getKeychainService(), e2);
                    }
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                untypedAsyncFunctionComponent = new StringAsyncFunctionComponent("deleteValueWithKeyAsync", anyTypeArr5, function1);
                            } else {
                                untypedAsyncFunctionComponent = new UntypedAsyncFunctionComponent("deleteValueWithKeyAsync", anyTypeArr5, function1);
                            }
                        } else {
                            untypedAsyncFunctionComponent = new FloatAsyncFunctionComponent("deleteValueWithKeyAsync", anyTypeArr5, function1);
                        }
                    } else {
                        untypedAsyncFunctionComponent = new DoubleAsyncFunctionComponent("deleteValueWithKeyAsync", anyTypeArr5, function1);
                    }
                } else {
                    untypedAsyncFunctionComponent = new BoolAsyncFunctionComponent("deleteValueWithKeyAsync", anyTypeArr5, function1);
                }
            } else {
                untypedAsyncFunctionComponent = new IntAsyncFunctionComponent("deleteValueWithKeyAsync", anyTypeArr5, function1);
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("deleteValueWithKeyAsync", untypedAsyncFunctionComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder5 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr6 = new AnyType[0];
            ReturnTypeProvider returnTypeProvider3 = ReturnTypeProvider.INSTANCE;
            ReturnType returnType3 = returnTypeProvider3.getTypes().get(Reflection.getOrCreateKotlinClass(Object.class));
            if (returnType3 == null) {
                returnType3 = new ReturnType(Reflection.getOrCreateKotlinClass(Object.class));
                returnTypeProvider3.getTypes().put(Reflection.getOrCreateKotlinClass(Object.class), returnType3);
            }
            moduleDefinitionBuilder5.getSyncFunctions().put("canUseBiometricAuthentication", new SyncFunctionComponent("canUseBiometricAuthentication", anyTypeArr6, returnType3, new Function1<Object[], Object>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$FunctionWithoutArgs$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    boolean z;
                    AuthenticationHelper authenticationHelper;
                    Intrinsics.checkNotNullParameter(it, "it");
                    try {
                        authenticationHelper = SecureStoreModule.this.authenticationHelper;
                        if (authenticationHelper == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("authenticationHelper");
                            authenticationHelper = null;
                        }
                        authenticationHelper.assertBiometricsSupport();
                        z = true;
                    } catch (AuthenticationException unused) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
            }));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.securestore.SecureStoreModule$definition$lambda$7$$inlined$OnCreate$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AESEncryptor aESEncryptor;
                    SecureStoreModule.this.authenticationHelper = new AuthenticationHelper(SecureStoreModule.this.getReactContext(), SecureStoreModule.this.getAppContext().getLegacyModuleRegistry());
                    SecureStoreModule secureStoreModule2 = SecureStoreModule.this;
                    Context reactContext = SecureStoreModule.this.getReactContext();
                    aESEncryptor = SecureStoreModule.this.mAESEncryptor;
                    secureStoreModule2.hybridAESEncryptor = new HybridAESEncryptor(reactContext, aESEncryptor);
                    KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                    keyStore.load(null);
                    SecureStoreModule.this.keyStore = keyStore;
                }
            }));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getItemImpl(String str, SecureStoreOptions secureStoreOptions, Continuation<? super String> continuation) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences.contains(createKeychainAwareKey(str, secureStoreOptions.getKeychainService()))) {
            return readJSONEncodedItem(str, sharedPreferences, secureStoreOptions, continuation);
        }
        if (sharedPreferences.contains(str)) {
            return readJSONEncodedItem(str, sharedPreferences, secureStoreOptions, continuation);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0037  */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v21, types: [expo.modules.securestore.SecureStoreModule] */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readJSONEncodedItem(java.lang.String r19, android.content.SharedPreferences r20, expo.modules.securestore.SecureStoreOptions r21, kotlin.coroutines.Continuation<? super java.lang.String> r22) {
        /*
            Method dump skipped, instructions count: 670
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.securestore.SecureStoreModule.readJSONEncodedItem(java.lang.String, android.content.SharedPreferences, expo.modules.securestore.SecureStoreOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(2:3|(9:5|6|7|(1:(1:(3:11|12|13)(2:15|16))(4:17|18|19|20))(2:50|(2:52|(2:54|55)(2:56|57))(11:58|(2:80|81)|60|61|(2:63|64)|68|69|70|71|(1:73)|40))|21|22|(1:24)|32|33))|84|6|7|(0)(0)|21|22|(0)|32|33|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0131, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0132, code lost:
    
        r3 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x017d, code lost:
    
        if (setItemImpl(r2, r3, r4, true, r8) == r11) goto L73;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0123 A[Catch: CodedException -> 0x0068, KeyPermanentlyInvalidatedException -> 0x0131, Exception -> 0x0136, GeneralSecurityException -> 0x0148, TRY_LEAVE, TryCatch #1 {CodedException -> 0x0068, blocks: (B:19:0x005a, B:22:0x0108, B:24:0x0123, B:81:0x00ad, B:61:0x00c3, B:63:0x00e2, B:68:0x00e8, B:71:0x00fb), top: B:7:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setItemImpl(java.lang.String r17, java.lang.String r18, expo.modules.securestore.SecureStoreOptions r19, boolean r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.securestore.SecureStoreModule.setItemImpl(java.lang.String, java.lang.String, expo.modules.securestore.SecureStoreOptions, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean saveEncryptedItem(JSONObject encryptedItem, SharedPreferences prefs, String key, boolean requireAuthentication, String keychainService) {
        encryptedItem.put(USES_KEYSTORE_SUFFIX_PROPERTY, true);
        encryptedItem.put(KEYSTORE_ALIAS_PROPERTY, keychainService);
        encryptedItem.put(AuthenticationHelper.REQUIRE_AUTHENTICATION_PROPERTY, requireAuthentication);
        String jSONObject = encryptedItem.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
        if (jSONObject.length() == 0) {
            throw new WriteException("Could not JSON-encode the encrypted item for SecureStore - the string " + jSONObject + " is null or empty", key, keychainService, null, 8, null);
        }
        return prefs.edit().putString(key, jSONObject).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteItemImpl(String key, SecureStoreOptions options) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        String createKeychainAwareKey = createKeychainAwareKey(key, options.getKeychainService());
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getReactContext());
        boolean commit = sharedPreferences.contains(createKeychainAwareKey) ? sharedPreferences.edit().remove(createKeychainAwareKey).commit() : true;
        if (sharedPreferences.contains(key)) {
            commit = sharedPreferences.edit().remove(key).commit() && commit;
        }
        if (defaultSharedPreferences.contains(key)) {
            commit = defaultSharedPreferences.edit().remove(key).commit() && commit;
        }
        if (!commit) {
            throw new DeleteException("Could not delete the item from SecureStore", key, options.getKeychainService(), null, 8, null);
        }
    }

    private final void removeKeyFromKeystore(String keyStoreAlias, String keychainService) {
        KeyStore keyStore = this.keyStore;
        if (keyStore == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyStore");
            keyStore = null;
        }
        keyStore.deleteEntry(keyStoreAlias);
        removeAllEntriesUnderKeychainService(keychainService);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void removeAllEntriesUnderKeychainService(java.lang.String r8) {
        /*
            r7 = this;
            android.content.SharedPreferences r0 = r7.getSharedPreferences()
            java.util.Map r1 = r0.getAll()
            java.lang.String r2 = "getAll(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L15:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L79
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.getValue()
            boolean r4 = r2 instanceof java.lang.String
            if (r4 == 0) goto L32
            java.lang.String r2 = (java.lang.String) r2
            goto L33
        L32:
            r2 = 0
        L33:
            if (r2 != 0) goto L36
            goto L15
        L36:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L15
            r4.<init>(r2)     // Catch: org.json.JSONException -> L15
            java.lang.String r2 = "keystoreAlias"
            java.lang.String r2 = r4.optString(r2)
            if (r2 != 0) goto L44
            goto L15
        L44:
            java.lang.String r5 = "requireAuthentication"
            r6 = 0
            boolean r4 = r4.optBoolean(r5, r6)
            if (r4 == 0) goto L15
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r2)
            if (r2 == 0) goto L15
            android.content.SharedPreferences$Editor r2 = r0.edit()
            android.content.SharedPreferences$Editor r2 = r2.remove(r3)
            r2.apply()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "Removing entry: "
            r2.<init>(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = " due to the encryption key being deleted"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "ExpoSecureStore"
            android.util.Log.w(r3, r2)
            goto L15
        L79:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.securestore.SecureStoreModule.removeAllEntriesUnderKeychainService(java.lang.String):void");
    }

    private final <E extends KeyStore.Entry> E getLegacyKeyEntry(Class<E> keyStoreEntryClass, KeyBasedEncryptor<E> encryptor, SecureStoreOptions options) {
        String keyStoreAlias = encryptor.getKeyStoreAlias(options);
        KeyStore keyStore = this.keyStore;
        if (keyStore == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyStore");
            keyStore = null;
        }
        if (!keyStore.containsAlias(encryptor.getKeyStoreAlias(options))) {
            return null;
        }
        KeyStore keyStore2 = this.keyStore;
        if (keyStore2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyStore");
            keyStore2 = null;
        }
        KeyStore.Entry entry = keyStore2.getEntry(keyStoreAlias, null);
        if (keyStoreEntryClass.isInstance(entry)) {
            return keyStoreEntryClass.cast(entry);
        }
        return null;
    }

    private final <E extends KeyStore.Entry> E getKeyEntry(Class<E> keyStoreEntryClass, KeyBasedEncryptor<E> encryptor, SecureStoreOptions options, boolean requireAuthentication) {
        String extendedKeyStoreAlias = encryptor.getExtendedKeyStoreAlias(options, requireAuthentication);
        KeyStore keyStore = this.keyStore;
        if (keyStore == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyStore");
            keyStore = null;
        }
        if (!keyStore.containsAlias(extendedKeyStoreAlias)) {
            return null;
        }
        KeyStore keyStore2 = this.keyStore;
        if (keyStore2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyStore");
            keyStore2 = null;
        }
        KeyStore.Entry entry = keyStore2.getEntry(extendedKeyStoreAlias, null);
        if (!keyStoreEntryClass.isInstance(entry)) {
            throw new KeyStoreException("The entry for the keystore alias \"" + extendedKeyStoreAlias + "\" is not a " + keyStoreEntryClass.getSimpleName());
        }
        E cast = keyStoreEntryClass.cast(entry);
        if (cast != null) {
            return cast;
        }
        throw new KeyStoreException("The entry for the keystore alias \"" + extendedKeyStoreAlias + "\" couldn't be cast to correct class");
    }

    private final <E extends KeyStore.Entry> E getOrCreateKeyEntry(Class<E> keyStoreEntryClass, KeyBasedEncryptor<E> encryptor, SecureStoreOptions options, boolean requireAuthentication) {
        E e = (E) getKeyEntry(keyStoreEntryClass, encryptor, options, requireAuthentication);
        if (e != null) {
            return e;
        }
        KeyStore keyStore = null;
        if (requireAuthentication) {
            AuthenticationHelper authenticationHelper = this.authenticationHelper;
            if (authenticationHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("authenticationHelper");
                authenticationHelper = null;
            }
            authenticationHelper.assertBiometricsSupport();
        }
        KeyStore keyStore2 = this.keyStore;
        if (keyStore2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyStore");
        } else {
            keyStore = keyStore2;
        }
        return encryptor.initializeKeyStoreEntry(keyStore, options);
    }

    private final <E extends KeyStore.Entry> E getKeyEntryCompat(Class<E> keyStoreEntryClass, KeyBasedEncryptor<E> encryptor, SecureStoreOptions options, boolean requireAuthentication, boolean usesKeystoreSuffix) {
        if (usesKeystoreSuffix) {
            return (E) getKeyEntry(keyStoreEntryClass, encryptor, options, requireAuthentication);
        }
        return (E) getLegacyKeyEntry(keyStoreEntryClass, encryptor, options);
    }

    public final SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences = getReactContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        return sharedPreferences;
    }

    private final String createKeychainAwareKey(String key, String keychainService) {
        return keychainService + "-" + key;
    }
}
