package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.util.HashMap;
import java.util.Map;

public enum Languages {

    ENGLISH("English", "en_uk"),
    ITALIANO("Italiano", "it_it"),
    ESPAÑOL("Español", "es_es"), // Se si modifica la N in ESPANOL la si deve modificare anche nel parametro language altrimenti non va.
    DEUTSCH("Deutsch", "de_de"),
    POLSKI("Polski", "pl_pl"),
    RUSSIAN("Russian", "ru_ru"),
    MSA("MSA", "msa_msa");

    private final String language;
    private final String countryCode;

    Languages(final String language, final String countryCode){
        this.language = language;
        this.countryCode = countryCode;
    }

    public String getLanguage(){
        return this.language;
    }

    public String getCountryCode(){
        return this.countryCode;
    }

    public static Map<String, String> getLanguagesMap(){
        final Map<String,String> languagesMap = new HashMap<>();
        for(final var l : Languages.values()){
            languagesMap.put(l.getCountryCode(), l.getLanguage());
        }
        return languagesMap;
    }

    public static Languages getDefaultLanguage(){
        return Languages.ENGLISH;
    }

}
