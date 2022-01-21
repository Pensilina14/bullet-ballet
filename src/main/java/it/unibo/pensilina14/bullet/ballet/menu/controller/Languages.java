package it.unibo.pensilina14.bullet.ballet.menu.controller;

public enum Languages {

    ENGLISH("English", "en_uk"),
    ITALIANO("Italiano", "it_it");

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

}
