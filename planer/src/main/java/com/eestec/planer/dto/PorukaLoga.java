package com.eestec.planer.dto;

public enum PorukaLoga {
    POKUSAJ_REGISTRACIJE(1),
    USPJESNO_REGISTROVAN_NALOG_POTVRDJEN_OD_REGISTRACIJE(2),
    EMAIL_NIJE_USPJESNO_POSLAT(3),
    PRIJAVA_U_TIM(4),
    PRIJAVA_NA_ZADATAK(5),
    PROMJENA_KATEGORIJE_ZADATKA(6),
    IZMJENA_ZADATKA(7),
    KREIRANJE_ZADATKA(8),
    ARHIVIRAN_ZADATAK(9),
    PROMJENA_ULOGE(10),
    NEUSPJESNA_PRIJAVA(11),
    USPJESNA_PRIJAVA(12);

    private final int value;

    PorukaLoga(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PorukaLoga getByValue(int value) {
        for (PorukaLoga porukaLoga : PorukaLoga.values()) {
            if (porukaLoga.getValue() == value)
                return porukaLoga;
        }
        return null;
    }
}

