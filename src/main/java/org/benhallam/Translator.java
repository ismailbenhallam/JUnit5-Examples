package org.benhallam;

import java.util.HashMap;
import java.util.Map;

public class Translator {
    private final String defaultLang;
    private Map<String, Map<String, String>> dict;

    public Translator(String defaultLang) {
        this.defaultLang = defaultLang;
        init();
    }

    private void init() {
        dict = new HashMap<>();
        Map<String, String> frenchMap = new HashMap<>();
        frenchMap.put("hello", "salut");
        frenchMap.put("morning", "matin");
        frenchMap.put("night", "nuit");
        dict.put("fr", frenchMap);
        Map<String, String> spanishMap = new HashMap<>();
        spanishMap.put("hello", "hola");
        spanishMap.put("morning", "mañana");
        spanishMap.put("night", "noche");
        dict.put("es", spanishMap);
    }

    private String fetchWord(String lang, String word) {
        return dict.get(lang).get(word);
    }

    public String translate(String word) {
        return fetchWord(defaultLang, word);
    }

    public String translate(String lang, String word) {
        return fetchWord(lang, word);
    }
}