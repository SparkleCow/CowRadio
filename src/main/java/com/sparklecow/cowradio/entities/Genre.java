package com.sparklecow.cowradio.entities;

public enum Genre {
    POP("Pop"),
    ROCK("Rock"),
    JAZZ("Jazz"),
    HIP_HOP("Hip Hop"),
    CLASSICAL("Classical"),
    ELECTRONIC("Electronic"),
    COUNTRY("Country"),
    BLUES("Blues"),
    REGGAE("Reggae"),
    R_AND_B("R&B"),
    METAL("Metal"),
    FOLK("Folk"),
    PUNK("Punk"),
    LATIN("Latin"),
    SOUL("Soul"),
    FUNK("Funk"),
    DISCO("Disco"),
    HOUSE("House"),
    TECHNO("Techno"),
    TRANCE("Trance"),
    AMBIENT("Ambient"),
    DRUM_AND_BASS("Drum and Bass"),
    DUBSTEP("Dubstep"),
    INDIE("Indie"),
    ALTERNATIVE("Alternative"),
    GOSPEL("Gospel"),
    OPERA("Opera"),
    REGGAETON("Reggaeton"),
    SALSA("Salsa"),
    MERENGUE("Merengue"),
    BACHATA("Bachata"),
    SAMBA("Samba"),
    BOLERO("Bolero"),
    TANGO("Tango"),
    FLAMENCO("Flamenco"),
    K_POP("K-Pop"),
    J_POP("J-Pop"),
    WORLD("World"),
    AFROBEAT("Afrobeat"),
    HIGHLIFE("Highlife"),
    CAJUN("Cajun"),
    ZYDECO("Zydeco"),
    BLUEGRASS("Bluegrass"),
    NEW_AGE("New Age"),
    CHILLWAVE("Chillwave"),
    LO_FI("Lo-Fi"),
    SYNTHPOP("Synthpop"),
    GARAGE("Garage"),
    GRUNGE("Grunge"),
    SKA("Ska"),
    DANCEHALL("Dancehall");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}