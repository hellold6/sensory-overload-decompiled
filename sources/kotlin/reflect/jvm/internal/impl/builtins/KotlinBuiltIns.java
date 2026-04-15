package kotlin.reflect.jvm.internal.impl.builtins;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.location.LocationRequestCompat;
import androidx.media3.extractor.ts.TsExtractor;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* loaded from: classes3.dex */
public abstract class KotlinBuiltIns {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Name BUILTINS_MODULE_NAME = Name.special("<built-ins module>");
    private final MemoizedFunctionToNotNull<Name, ClassDescriptor> builtInClassesByName;
    private final NotNullLazyValue<Collection<PackageViewDescriptor>> builtInPackagesImportedByDefault;
    private ModuleDescriptorImpl builtInsModule;
    private NotNullLazyValue<ModuleDescriptorImpl> postponedBuiltInsModule;
    private final NotNullLazyValue<Primitives> primitives;
    private final StorageManager storageManager;

    /* JADX WARN: Failed to find 'out' block for switch in B:141:0x0419. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:142:0x041c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:143:0x041f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:100:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0409 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x041c  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x042b A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0058 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0035 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0369  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static /* synthetic */ void $$$reportNull$$$0(int r23) {
        /*
            Method dump skipped, instructions count: 2222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.$$$reportNull$$$0(int):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KotlinBuiltIns(StorageManager storageManager) {
        if (storageManager == null) {
            $$$reportNull$$$0(0);
        }
        this.storageManager = storageManager;
        this.builtInPackagesImportedByDefault = storageManager.createLazyValue(new Function0<Collection<PackageViewDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.1
            @Override // kotlin.jvm.functions.Function0
            public Collection<PackageViewDescriptor> invoke() {
                return Arrays.asList(KotlinBuiltIns.this.getBuiltInsModule().getPackage(StandardNames.BUILT_INS_PACKAGE_FQ_NAME), KotlinBuiltIns.this.getBuiltInsModule().getPackage(StandardNames.COLLECTIONS_PACKAGE_FQ_NAME), KotlinBuiltIns.this.getBuiltInsModule().getPackage(StandardNames.RANGES_PACKAGE_FQ_NAME), KotlinBuiltIns.this.getBuiltInsModule().getPackage(StandardNames.ANNOTATION_PACKAGE_FQ_NAME));
            }
        });
        this.primitives = storageManager.createLazyValue(new Function0<Primitives>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.2
            @Override // kotlin.jvm.functions.Function0
            public Primitives invoke() {
                EnumMap enumMap = new EnumMap(PrimitiveType.class);
                HashMap hashMap = new HashMap();
                HashMap hashMap2 = new HashMap();
                for (PrimitiveType primitiveType : PrimitiveType.values()) {
                    SimpleType builtInTypeByClassName = KotlinBuiltIns.this.getBuiltInTypeByClassName(primitiveType.getTypeName().asString());
                    SimpleType builtInTypeByClassName2 = KotlinBuiltIns.this.getBuiltInTypeByClassName(primitiveType.getArrayTypeName().asString());
                    enumMap.put((EnumMap) primitiveType, (PrimitiveType) builtInTypeByClassName2);
                    hashMap.put(builtInTypeByClassName, builtInTypeByClassName2);
                    hashMap2.put(builtInTypeByClassName2, builtInTypeByClassName);
                }
                return new Primitives(enumMap, hashMap, hashMap2);
            }
        });
        this.builtInClassesByName = storageManager.createMemoizedFunction(new Function1<Name, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.3
            @Override // kotlin.jvm.functions.Function1
            public ClassDescriptor invoke(Name name) {
                ClassifierDescriptor contributedClassifier = KotlinBuiltIns.this.getBuiltInsPackageScope().mo2700getContributedClassifier(name, NoLookupLocation.FROM_BUILTINS);
                if (contributedClassifier == null) {
                    throw new AssertionError("Built-in class " + StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(name) + " is not found");
                }
                if (!(contributedClassifier instanceof ClassDescriptor)) {
                    throw new AssertionError("Must be a class descriptor " + name + ", but was " + contributedClassifier);
                }
                return (ClassDescriptor) contributedClassifier;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createBuiltInsModule(boolean z) {
        ModuleDescriptorImpl moduleDescriptorImpl = new ModuleDescriptorImpl(BUILTINS_MODULE_NAME, this.storageManager, this, null);
        this.builtInsModule = moduleDescriptorImpl;
        moduleDescriptorImpl.initialize(BuiltInsLoader.Companion.getInstance().createPackageFragmentProvider(this.storageManager, this.builtInsModule, getClassDescriptorFactories(), getPlatformDependentDeclarationFilter(), getAdditionalClassPartsProvider(), z));
        ModuleDescriptorImpl moduleDescriptorImpl2 = this.builtInsModule;
        moduleDescriptorImpl2.setDependencies(moduleDescriptorImpl2);
    }

    public void setBuiltInsModule(final ModuleDescriptorImpl moduleDescriptorImpl) {
        if (moduleDescriptorImpl == null) {
            $$$reportNull$$$0(1);
        }
        this.storageManager.compute(new Function0<Void>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.4
            @Override // kotlin.jvm.functions.Function0
            public Void invoke() {
                if (KotlinBuiltIns.this.builtInsModule != null) {
                    throw new AssertionError("Built-ins module is already set: " + KotlinBuiltIns.this.builtInsModule + " (attempting to reset to " + moduleDescriptorImpl + ")");
                }
                KotlinBuiltIns.this.builtInsModule = moduleDescriptorImpl;
                return null;
            }
        });
    }

    protected AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        AdditionalClassPartsProvider.None none = AdditionalClassPartsProvider.None.INSTANCE;
        if (none == null) {
            $$$reportNull$$$0(3);
        }
        return none;
    }

    protected PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        PlatformDependentDeclarationFilter.NoPlatformDependent noPlatformDependent = PlatformDependentDeclarationFilter.NoPlatformDependent.INSTANCE;
        if (noPlatformDependent == null) {
            $$$reportNull$$$0(4);
        }
        return noPlatformDependent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterable<ClassDescriptorFactory> getClassDescriptorFactories() {
        List singletonList = Collections.singletonList(new BuiltInFictitiousFunctionClassFactory(this.storageManager, getBuiltInsModule()));
        if (singletonList == null) {
            $$$reportNull$$$0(5);
        }
        return singletonList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StorageManager getStorageManager() {
        StorageManager storageManager = this.storageManager;
        if (storageManager == null) {
            $$$reportNull$$$0(6);
        }
        return storageManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class Primitives {
        public final Map<SimpleType, SimpleType> kotlinArrayTypeToPrimitiveKotlinType;
        public final Map<KotlinType, SimpleType> primitiveKotlinTypeToKotlinArrayType;
        public final Map<PrimitiveType, SimpleType> primitiveTypeToArrayKotlinType;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "primitiveKotlinTypeToKotlinArrayType";
            } else if (i != 2) {
                objArr[0] = "primitiveTypeToArrayKotlinType";
            } else {
                objArr[0] = "kotlinArrayTypeToPrimitiveKotlinType";
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/builtins/KotlinBuiltIns$Primitives";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private Primitives(Map<PrimitiveType, SimpleType> map, Map<KotlinType, SimpleType> map2, Map<SimpleType, SimpleType> map3) {
            if (map == null) {
                $$$reportNull$$$0(0);
            }
            if (map2 == null) {
                $$$reportNull$$$0(1);
            }
            if (map3 == null) {
                $$$reportNull$$$0(2);
            }
            this.primitiveTypeToArrayKotlinType = map;
            this.primitiveKotlinTypeToKotlinArrayType = map2;
            this.kotlinArrayTypeToPrimitiveKotlinType = map3;
        }
    }

    public ModuleDescriptorImpl getBuiltInsModule() {
        if (this.builtInsModule == null) {
            this.builtInsModule = this.postponedBuiltInsModule.invoke();
        }
        ModuleDescriptorImpl moduleDescriptorImpl = this.builtInsModule;
        if (moduleDescriptorImpl == null) {
            $$$reportNull$$$0(7);
        }
        return moduleDescriptorImpl;
    }

    public static boolean isBuiltIn(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(9);
        }
        return DescriptorUtils.getParentOfType(declarationDescriptor, BuiltInsPackageFragment.class, false) != null;
    }

    public static boolean isUnderKotlinPackage(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(10);
        }
        while (declarationDescriptor != null) {
            if (declarationDescriptor instanceof PackageFragmentDescriptor) {
                return ((PackageFragmentDescriptor) declarationDescriptor).getFqName().startsWith(StandardNames.BUILT_INS_PACKAGE_NAME);
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        return false;
    }

    public MemberScope getBuiltInsPackageScope() {
        MemberScope memberScope = getBuiltInsModule().getPackage(StandardNames.BUILT_INS_PACKAGE_FQ_NAME).getMemberScope();
        if (memberScope == null) {
            $$$reportNull$$$0(11);
        }
        return memberScope;
    }

    public ClassDescriptor getBuiltInClassByFqName(FqName fqName) {
        if (fqName == null) {
            $$$reportNull$$$0(12);
        }
        ClassDescriptor resolveClassByFqName = DescriptorUtilKt.resolveClassByFqName(getBuiltInsModule(), fqName, NoLookupLocation.FROM_BUILTINS);
        if (resolveClassByFqName == null) {
            $$$reportNull$$$0(13);
        }
        return resolveClassByFqName;
    }

    private ClassDescriptor getBuiltInClassByName(String str) {
        if (str == null) {
            $$$reportNull$$$0(14);
        }
        ClassDescriptor invoke = this.builtInClassesByName.invoke(Name.identifier(str));
        if (invoke == null) {
            $$$reportNull$$$0(15);
        }
        return invoke;
    }

    public ClassDescriptor getAny() {
        return getBuiltInClassByName("Any");
    }

    public ClassDescriptor getNothing() {
        return getBuiltInClassByName("Nothing");
    }

    private ClassDescriptor getPrimitiveClassDescriptor(PrimitiveType primitiveType) {
        if (primitiveType == null) {
            $$$reportNull$$$0(16);
        }
        return getBuiltInClassByName(primitiveType.getTypeName().asString());
    }

    public ClassDescriptor getArray() {
        return getBuiltInClassByName("Array");
    }

    public ClassDescriptor getNumber() {
        return getBuiltInClassByName("Number");
    }

    public ClassDescriptor getUnit() {
        return getBuiltInClassByName("Unit");
    }

    public ClassDescriptor getFunction(int i) {
        return getBuiltInClassByName(StandardNames.getFunctionName(i));
    }

    public ClassDescriptor getSuspendFunction(int i) {
        ClassDescriptor builtInClassByFqName = getBuiltInClassByFqName(StandardNames.COROUTINES_PACKAGE_FQ_NAME.child(Name.identifier(StandardNames.getSuspendFunctionName(i))));
        if (builtInClassByFqName == null) {
            $$$reportNull$$$0(18);
        }
        return builtInClassByFqName;
    }

    public ClassDescriptor getString() {
        return getBuiltInClassByName("String");
    }

    public ClassDescriptor getComparable() {
        return getBuiltInClassByName("Comparable");
    }

    public ClassDescriptor getKClass() {
        ClassDescriptor builtInClassByFqName = getBuiltInClassByFqName(StandardNames.FqNames.kClass.toSafe());
        if (builtInClassByFqName == null) {
            $$$reportNull$$$0(21);
        }
        return builtInClassByFqName;
    }

    public ClassDescriptor getCollection() {
        ClassDescriptor builtInClassByFqName = getBuiltInClassByFqName(StandardNames.FqNames.collection);
        if (builtInClassByFqName == null) {
            $$$reportNull$$$0(35);
        }
        return builtInClassByFqName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SimpleType getBuiltInTypeByClassName(String str) {
        if (str == null) {
            $$$reportNull$$$0(47);
        }
        SimpleType defaultType = getBuiltInClassByName(str).getDefaultType();
        if (defaultType == null) {
            $$$reportNull$$$0(48);
        }
        return defaultType;
    }

    public SimpleType getNothingType() {
        SimpleType defaultType = getNothing().getDefaultType();
        if (defaultType == null) {
            $$$reportNull$$$0(49);
        }
        return defaultType;
    }

    public SimpleType getNullableNothingType() {
        SimpleType makeNullableAsSpecified = getNothingType().makeNullableAsSpecified(true);
        if (makeNullableAsSpecified == null) {
            $$$reportNull$$$0(50);
        }
        return makeNullableAsSpecified;
    }

    public SimpleType getAnyType() {
        SimpleType defaultType = getAny().getDefaultType();
        if (defaultType == null) {
            $$$reportNull$$$0(51);
        }
        return defaultType;
    }

    public SimpleType getNullableAnyType() {
        SimpleType makeNullableAsSpecified = getAnyType().makeNullableAsSpecified(true);
        if (makeNullableAsSpecified == null) {
            $$$reportNull$$$0(52);
        }
        return makeNullableAsSpecified;
    }

    public SimpleType getDefaultBound() {
        SimpleType nullableAnyType = getNullableAnyType();
        if (nullableAnyType == null) {
            $$$reportNull$$$0(53);
        }
        return nullableAnyType;
    }

    public SimpleType getPrimitiveKotlinType(PrimitiveType primitiveType) {
        if (primitiveType == null) {
            $$$reportNull$$$0(54);
        }
        SimpleType defaultType = getPrimitiveClassDescriptor(primitiveType).getDefaultType();
        if (defaultType == null) {
            $$$reportNull$$$0(55);
        }
        return defaultType;
    }

    public SimpleType getNumberType() {
        SimpleType defaultType = getNumber().getDefaultType();
        if (defaultType == null) {
            $$$reportNull$$$0(56);
        }
        return defaultType;
    }

    public SimpleType getByteType() {
        SimpleType primitiveKotlinType = getPrimitiveKotlinType(PrimitiveType.BYTE);
        if (primitiveKotlinType == null) {
            $$$reportNull$$$0(57);
        }
        return primitiveKotlinType;
    }

    public SimpleType getShortType() {
        SimpleType primitiveKotlinType = getPrimitiveKotlinType(PrimitiveType.SHORT);
        if (primitiveKotlinType == null) {
            $$$reportNull$$$0(58);
        }
        return primitiveKotlinType;
    }

    public SimpleType getIntType() {
        SimpleType primitiveKotlinType = getPrimitiveKotlinType(PrimitiveType.INT);
        if (primitiveKotlinType == null) {
            $$$reportNull$$$0(59);
        }
        return primitiveKotlinType;
    }

    public SimpleType getLongType() {
        SimpleType primitiveKotlinType = getPrimitiveKotlinType(PrimitiveType.LONG);
        if (primitiveKotlinType == null) {
            $$$reportNull$$$0(60);
        }
        return primitiveKotlinType;
    }

    public SimpleType getFloatType() {
        SimpleType primitiveKotlinType = getPrimitiveKotlinType(PrimitiveType.FLOAT);
        if (primitiveKotlinType == null) {
            $$$reportNull$$$0(61);
        }
        return primitiveKotlinType;
    }

    public SimpleType getDoubleType() {
        SimpleType primitiveKotlinType = getPrimitiveKotlinType(PrimitiveType.DOUBLE);
        if (primitiveKotlinType == null) {
            $$$reportNull$$$0(62);
        }
        return primitiveKotlinType;
    }

    public SimpleType getCharType() {
        SimpleType primitiveKotlinType = getPrimitiveKotlinType(PrimitiveType.CHAR);
        if (primitiveKotlinType == null) {
            $$$reportNull$$$0(63);
        }
        return primitiveKotlinType;
    }

    public SimpleType getBooleanType() {
        SimpleType primitiveKotlinType = getPrimitiveKotlinType(PrimitiveType.BOOLEAN);
        if (primitiveKotlinType == null) {
            $$$reportNull$$$0(64);
        }
        return primitiveKotlinType;
    }

    public SimpleType getUnitType() {
        SimpleType defaultType = getUnit().getDefaultType();
        if (defaultType == null) {
            $$$reportNull$$$0(65);
        }
        return defaultType;
    }

    public SimpleType getStringType() {
        SimpleType defaultType = getString().getDefaultType();
        if (defaultType == null) {
            $$$reportNull$$$0(66);
        }
        return defaultType;
    }

    public KotlinType getArrayElementType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(68);
        }
        KotlinType arrayElementTypeOrNull = getArrayElementTypeOrNull(kotlinType);
        if (arrayElementTypeOrNull == null) {
            throw new IllegalStateException("not array: " + kotlinType);
        }
        if (arrayElementTypeOrNull == null) {
            $$$reportNull$$$0(69);
        }
        return arrayElementTypeOrNull;
    }

    public KotlinType getArrayElementTypeOrNull(KotlinType kotlinType) {
        KotlinType elementTypeForUnsignedArray;
        if (kotlinType == null) {
            $$$reportNull$$$0(70);
        }
        if (isArray(kotlinType)) {
            if (kotlinType.getArguments().size() != 1) {
                return null;
            }
            return kotlinType.getArguments().get(0).getType();
        }
        KotlinType makeNotNullable = TypeUtils.makeNotNullable(kotlinType);
        SimpleType simpleType = this.primitives.invoke().kotlinArrayTypeToPrimitiveKotlinType.get(makeNotNullable);
        if (simpleType != null) {
            return simpleType;
        }
        ModuleDescriptor containingModuleOrNull = DescriptorUtils.getContainingModuleOrNull(makeNotNullable);
        if (containingModuleOrNull == null || (elementTypeForUnsignedArray = getElementTypeForUnsignedArray(makeNotNullable, containingModuleOrNull)) == null) {
            return null;
        }
        return elementTypeForUnsignedArray;
    }

    private static KotlinType getElementTypeForUnsignedArray(KotlinType kotlinType, ModuleDescriptor moduleDescriptor) {
        ClassId classId;
        ClassId unsignedClassIdByArrayClassId;
        ClassDescriptor findClassAcrossModuleDependencies;
        if (kotlinType == null) {
            $$$reportNull$$$0(71);
        }
        if (moduleDescriptor == null) {
            $$$reportNull$$$0(72);
        }
        ClassifierDescriptor mo2698getDeclarationDescriptor = kotlinType.getConstructor().mo2698getDeclarationDescriptor();
        if (mo2698getDeclarationDescriptor == null || !UnsignedTypes.INSTANCE.isShortNameOfUnsignedArray(mo2698getDeclarationDescriptor.getName()) || (classId = DescriptorUtilsKt.getClassId(mo2698getDeclarationDescriptor)) == null || (unsignedClassIdByArrayClassId = UnsignedTypes.INSTANCE.getUnsignedClassIdByArrayClassId(classId)) == null || (findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor, unsignedClassIdByArrayClassId)) == null) {
            return null;
        }
        return findClassAcrossModuleDependencies.getDefaultType();
    }

    public SimpleType getPrimitiveArrayKotlinType(PrimitiveType primitiveType) {
        if (primitiveType == null) {
            $$$reportNull$$$0(73);
        }
        SimpleType simpleType = this.primitives.invoke().primitiveTypeToArrayKotlinType.get(primitiveType);
        if (simpleType == null) {
            $$$reportNull$$$0(74);
        }
        return simpleType;
    }

    public static PrimitiveType getPrimitiveType(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(76);
        }
        if (StandardNames.FqNames.primitiveTypeShortNames.contains(declarationDescriptor.getName())) {
            return StandardNames.FqNames.fqNameToPrimitiveType.get(DescriptorUtils.getFqName(declarationDescriptor));
        }
        return null;
    }

    public static PrimitiveType getPrimitiveArrayType(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(77);
        }
        if (StandardNames.FqNames.primitiveArrayTypeShortNames.contains(declarationDescriptor.getName())) {
            return StandardNames.FqNames.arrayClassFqNameToPrimitiveType.get(DescriptorUtils.getFqName(declarationDescriptor));
        }
        return null;
    }

    public SimpleType getArrayType(Variance variance, KotlinType kotlinType, Annotations annotations) {
        if (variance == null) {
            $$$reportNull$$$0(78);
        }
        if (kotlinType == null) {
            $$$reportNull$$$0(79);
        }
        if (annotations == null) {
            $$$reportNull$$$0(80);
        }
        SimpleType simpleNotNullType = KotlinTypeFactory.simpleNotNullType(TypeAttributesKt.toDefaultAttributes(annotations), getArray(), Collections.singletonList(new TypeProjectionImpl(variance, kotlinType)));
        if (simpleNotNullType == null) {
            $$$reportNull$$$0(81);
        }
        return simpleNotNullType;
    }

    public SimpleType getArrayType(Variance variance, KotlinType kotlinType) {
        if (variance == null) {
            $$$reportNull$$$0(82);
        }
        if (kotlinType == null) {
            $$$reportNull$$$0(83);
        }
        SimpleType arrayType = getArrayType(variance, kotlinType, Annotations.Companion.getEMPTY());
        if (arrayType == null) {
            $$$reportNull$$$0(84);
        }
        return arrayType;
    }

    public static boolean isArray(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(88);
        }
        return isConstructedFromGivenClass(kotlinType, StandardNames.FqNames.array);
    }

    public static boolean isArrayOrPrimitiveArray(ClassDescriptor classDescriptor) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(89);
        }
        return classFqNameEquals(classDescriptor, StandardNames.FqNames.array) || getPrimitiveArrayType(classDescriptor) != null;
    }

    public static boolean isArrayOrPrimitiveArray(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(90);
        }
        return isArray(kotlinType) || isPrimitiveArray(kotlinType);
    }

    public static boolean isPrimitiveArray(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(91);
        }
        ClassifierDescriptor mo2698getDeclarationDescriptor = kotlinType.getConstructor().mo2698getDeclarationDescriptor();
        return (mo2698getDeclarationDescriptor == null || getPrimitiveArrayType(mo2698getDeclarationDescriptor) == null) ? false : true;
    }

    public static PrimitiveType getPrimitiveArrayElementType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(92);
        }
        ClassifierDescriptor mo2698getDeclarationDescriptor = kotlinType.getConstructor().mo2698getDeclarationDescriptor();
        if (mo2698getDeclarationDescriptor == null) {
            return null;
        }
        return getPrimitiveArrayType(mo2698getDeclarationDescriptor);
    }

    public static boolean isPrimitiveType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(94);
        }
        return !kotlinType.isMarkedNullable() && isPrimitiveTypeOrNullablePrimitiveType(kotlinType);
    }

    public static boolean isPrimitiveTypeOrNullablePrimitiveType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(95);
        }
        ClassifierDescriptor mo2698getDeclarationDescriptor = kotlinType.getConstructor().mo2698getDeclarationDescriptor();
        return (mo2698getDeclarationDescriptor instanceof ClassDescriptor) && isPrimitiveClass((ClassDescriptor) mo2698getDeclarationDescriptor);
    }

    public static boolean isPrimitiveClass(ClassDescriptor classDescriptor) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(96);
        }
        return getPrimitiveType(classDescriptor) != null;
    }

    private static boolean isConstructedFromGivenClass(KotlinType kotlinType, FqNameUnsafe fqNameUnsafe) {
        if (kotlinType == null) {
            $$$reportNull$$$0(97);
        }
        if (fqNameUnsafe == null) {
            $$$reportNull$$$0(98);
        }
        return isTypeConstructorForGivenClass(kotlinType.getConstructor(), fqNameUnsafe);
    }

    public static boolean isTypeConstructorForGivenClass(TypeConstructor typeConstructor, FqNameUnsafe fqNameUnsafe) {
        if (typeConstructor == null) {
            $$$reportNull$$$0(101);
        }
        if (fqNameUnsafe == null) {
            $$$reportNull$$$0(LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY);
        }
        ClassifierDescriptor mo2698getDeclarationDescriptor = typeConstructor.mo2698getDeclarationDescriptor();
        return (mo2698getDeclarationDescriptor instanceof ClassDescriptor) && classFqNameEquals(mo2698getDeclarationDescriptor, fqNameUnsafe);
    }

    private static boolean classFqNameEquals(ClassifierDescriptor classifierDescriptor, FqNameUnsafe fqNameUnsafe) {
        if (classifierDescriptor == null) {
            $$$reportNull$$$0(103);
        }
        if (fqNameUnsafe == null) {
            $$$reportNull$$$0(LocationRequestCompat.QUALITY_LOW_POWER);
        }
        return classifierDescriptor.getName().equals(fqNameUnsafe.shortName()) && fqNameUnsafe.equals(DescriptorUtils.getFqName(classifierDescriptor));
    }

    private static boolean isNotNullConstructedFromGivenClass(KotlinType kotlinType, FqNameUnsafe fqNameUnsafe) {
        if (kotlinType == null) {
            $$$reportNull$$$0(105);
        }
        if (fqNameUnsafe == null) {
            $$$reportNull$$$0(106);
        }
        return !kotlinType.isMarkedNullable() && isConstructedFromGivenClass(kotlinType, fqNameUnsafe);
    }

    public static boolean isSpecialClassWithNoSupertypes(ClassDescriptor classDescriptor) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(107);
        }
        return classFqNameEquals(classDescriptor, StandardNames.FqNames.any) || classFqNameEquals(classDescriptor, StandardNames.FqNames.nothing);
    }

    public static boolean isAny(ClassDescriptor classDescriptor) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
        }
        return classFqNameEquals(classDescriptor, StandardNames.FqNames.any);
    }

    public static boolean isBoolean(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(110);
        }
        return isConstructedFromGivenClassAndNotNullable(kotlinType, StandardNames.FqNames._boolean);
    }

    public static boolean isUByteArray(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(128);
        }
        return isConstructedFromGivenClassAndNotNullable(kotlinType, StandardNames.FqNames.uByteArrayFqName.toUnsafe());
    }

    public static boolean isUShortArray(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(TsExtractor.TS_STREAM_TYPE_AC3);
        }
        return isConstructedFromGivenClassAndNotNullable(kotlinType, StandardNames.FqNames.uShortArrayFqName.toUnsafe());
    }

    public static boolean isUIntArray(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
        }
        return isConstructedFromGivenClassAndNotNullable(kotlinType, StandardNames.FqNames.uIntArrayFqName.toUnsafe());
    }

    public static boolean isULongArray(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(131);
        }
        return isConstructedFromGivenClassAndNotNullable(kotlinType, StandardNames.FqNames.uLongArrayFqName.toUnsafe());
    }

    public static boolean isUnsignedArrayType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(132);
        }
        return isUByteArray(kotlinType) || isUShortArray(kotlinType) || isUIntArray(kotlinType) || isULongArray(kotlinType);
    }

    private static boolean isConstructedFromGivenClassAndNotNullable(KotlinType kotlinType, FqNameUnsafe fqNameUnsafe) {
        if (kotlinType == null) {
            $$$reportNull$$$0(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO);
        }
        if (fqNameUnsafe == null) {
            $$$reportNull$$$0(TsExtractor.TS_STREAM_TYPE_E_AC3);
        }
        return isConstructedFromGivenClass(kotlinType, fqNameUnsafe) && !kotlinType.isMarkedNullable();
    }

    public static boolean isNothing(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(TsExtractor.TS_STREAM_TYPE_DTS_HD);
        }
        return isNothingOrNullableNothing(kotlinType) && !TypeUtils.isNullableType(kotlinType);
    }

    public static boolean isNothingOrNullableNothing(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(TsExtractor.TS_STREAM_TYPE_DTS);
        }
        return isConstructedFromGivenClass(kotlinType, StandardNames.FqNames.nothing);
    }

    public static boolean isAnyOrNullableAny(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(TsExtractor.TS_STREAM_TYPE_DTS_UHD);
        }
        return isConstructedFromGivenClass(kotlinType, StandardNames.FqNames.any);
    }

    public static boolean isNullableAny(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(140);
        }
        return isAnyOrNullableAny(kotlinType) && kotlinType.isMarkedNullable();
    }

    public static boolean isDefaultBound(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(141);
        }
        return isNullableAny(kotlinType);
    }

    public static boolean isUnit(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(142);
        }
        return isNotNullConstructedFromGivenClass(kotlinType, StandardNames.FqNames.unit);
    }

    public static boolean isString(KotlinType kotlinType) {
        return kotlinType != null && isNotNullConstructedFromGivenClass(kotlinType, StandardNames.FqNames.string);
    }

    public static boolean isKClass(ClassDescriptor classDescriptor) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(158);
        }
        return classFqNameEquals(classDescriptor, StandardNames.FqNames.kClass);
    }

    public static boolean isDeprecated(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(160);
        }
        if (declarationDescriptor.getOriginal().getAnnotations().hasAnnotation(StandardNames.FqNames.deprecated)) {
            return true;
        }
        if (declarationDescriptor instanceof PropertyDescriptor) {
            PropertyDescriptor propertyDescriptor = (PropertyDescriptor) declarationDescriptor;
            boolean isVar = propertyDescriptor.isVar();
            PropertyGetterDescriptor getter = propertyDescriptor.getGetter();
            PropertySetterDescriptor setter = propertyDescriptor.getSetter();
            if (getter != null && isDeprecated(getter) && (!isVar || (setter != null && isDeprecated(setter)))) {
                return true;
            }
        }
        return false;
    }
}
