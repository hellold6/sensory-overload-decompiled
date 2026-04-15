package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages;

/* loaded from: classes4.dex */
public class Lang {
    private static final String LANGUAGE_RULES_RN = "org/apache/commons/codec/language/bm/%s_lang.txt";
    private static final Map<NameType, Lang> Langs = new EnumMap(NameType.class);
    private final Languages languages;
    private final List<LangRule> rules;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class LangRule {
        private final boolean acceptOnMatch;
        private final Set<String> languages;
        private final Pattern pattern;

        private LangRule(Pattern pattern, Set<String> set, boolean z) {
            this.pattern = pattern;
            this.languages = set;
            this.acceptOnMatch = z;
        }

        public boolean matches(String str) {
            return this.pattern.matcher(str).find();
        }
    }

    static {
        for (NameType nameType : NameType.values()) {
            Langs.put(nameType, loadFromResource(String.format(LANGUAGE_RULES_RN, nameType.getName()), Languages.getInstance(nameType)));
        }
    }

    public static Lang instance(NameType nameType) {
        return Langs.get(nameType);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00af, code lost:
    
        throw new java.lang.IllegalArgumentException("Malformed line '" + r4 + "' in language resource '" + r9 + "'");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.commons.codec.language.bm.Lang loadFromResource(java.lang.String r9, org.apache.commons.codec.language.bm.Languages r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Class<org.apache.commons.codec.language.bm.Lang> r1 = org.apache.commons.codec.language.bm.Lang.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            java.io.InputStream r1 = r1.getResourceAsStream(r9)
            if (r1 == 0) goto Lbe
            java.util.Scanner r2 = new java.util.Scanner
            java.lang.String r3 = "UTF-8"
            r2.<init>(r1, r3)
            r1 = 0
        L19:
            r3 = r1
        L1a:
            boolean r4 = r2.hasNextLine()     // Catch: java.lang.Throwable -> Lb9
            if (r4 == 0) goto Lb0
            java.lang.String r4 = r2.nextLine()     // Catch: java.lang.Throwable -> Lb9
            if (r3 == 0) goto L2f
        */
        //  java.lang.String r5 = "*/"
        /*
            boolean r4 = r4.endsWith(r5)     // Catch: java.lang.Throwable -> Lb9
            if (r4 == 0) goto L1a
            goto L19
        L2f:
            java.lang.String r5 = "/*"
            boolean r5 = r4.startsWith(r5)     // Catch: java.lang.Throwable -> Lb9
            r6 = 1
            if (r5 == 0) goto L3a
            r3 = r6
            goto L1a
        L3a:
            java.lang.String r5 = "//"
            int r5 = r4.indexOf(r5)     // Catch: java.lang.Throwable -> Lb9
            if (r5 < 0) goto L47
            java.lang.String r5 = r4.substring(r1, r5)     // Catch: java.lang.Throwable -> Lb9
            goto L48
        L47:
            r5 = r4
        L48:
            java.lang.String r5 = r5.trim()     // Catch: java.lang.Throwable -> Lb9
            int r7 = r5.length()     // Catch: java.lang.Throwable -> Lb9
            if (r7 != 0) goto L53
            goto L1a
        L53:
            java.lang.String r7 = "\\s+"
            java.lang.String[] r5 = r5.split(r7)     // Catch: java.lang.Throwable -> Lb9
            int r7 = r5.length     // Catch: java.lang.Throwable -> Lb9
            r8 = 3
            if (r7 != r8) goto L87
            r4 = r5[r1]     // Catch: java.lang.Throwable -> Lb9
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch: java.lang.Throwable -> Lb9
            r6 = r5[r6]     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r7 = "\\+"
            java.lang.String[] r6 = r6.split(r7)     // Catch: java.lang.Throwable -> Lb9
            r7 = 2
            r5 = r5[r7]     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r7 = "true"
            boolean r5 = r5.equals(r7)     // Catch: java.lang.Throwable -> Lb9
            org.apache.commons.codec.language.bm.Lang$LangRule r7 = new org.apache.commons.codec.language.bm.Lang$LangRule     // Catch: java.lang.Throwable -> Lb9
            java.util.HashSet r8 = new java.util.HashSet     // Catch: java.lang.Throwable -> Lb9
            java.util.List r6 = java.util.Arrays.asList(r6)     // Catch: java.lang.Throwable -> Lb9
            r8.<init>(r6)     // Catch: java.lang.Throwable -> Lb9
            r6 = 0
            r7.<init>(r4, r8, r5)     // Catch: java.lang.Throwable -> Lb9
            r0.add(r7)     // Catch: java.lang.Throwable -> Lb9
            goto L1a
        L87:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> Lb9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb9
            r0.<init>()     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r1 = "Malformed line '"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> Lb9
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r1 = "' in language resource '"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> Lb9
            java.lang.StringBuilder r9 = r0.append(r9)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r0 = "'"
            java.lang.StringBuilder r9 = r9.append(r0)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Lb9
            r10.<init>(r9)     // Catch: java.lang.Throwable -> Lb9
            throw r10     // Catch: java.lang.Throwable -> Lb9
        Lb0:
            r2.close()
            org.apache.commons.codec.language.bm.Lang r9 = new org.apache.commons.codec.language.bm.Lang
            r9.<init>(r0, r10)
            return r9
        Lb9:
            r9 = move-exception
            r2.close()
            throw r9
        Lbe:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Unable to resolve required resource:org/apache/commons/codec/language/bm/%s_lang.txt"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.bm.Lang.loadFromResource(java.lang.String, org.apache.commons.codec.language.bm.Languages):org.apache.commons.codec.language.bm.Lang");
    }

    private Lang(List<LangRule> list, Languages languages) {
        this.rules = Collections.unmodifiableList(list);
        this.languages = languages;
    }

    public String guessLanguage(String str) {
        Languages.LanguageSet guessLanguages = guessLanguages(str);
        return guessLanguages.isSingleton() ? guessLanguages.getAny() : Languages.ANY;
    }

    public Languages.LanguageSet guessLanguages(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        HashSet hashSet = new HashSet(this.languages.getLanguages());
        for (LangRule langRule : this.rules) {
            if (langRule.matches(lowerCase)) {
                if (langRule.acceptOnMatch) {
                    hashSet.retainAll(langRule.languages);
                } else {
                    hashSet.removeAll(langRule.languages);
                }
            }
        }
        Languages.LanguageSet from = Languages.LanguageSet.from(hashSet);
        return from.equals(Languages.NO_LANGUAGES) ? Languages.ANY_LANGUAGE : from;
    }
}
