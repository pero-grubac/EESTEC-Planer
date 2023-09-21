package com.eestec.planer.controller.util;


public class KorisnikZadatak {
    private Integer idKorisnika;
    private Integer idZadatak;

    public KorisnikZadatak(Integer idKorisnika, Integer idZadatak) {
        this.idKorisnika = idKorisnika;
        this.idZadatak = idZadatak;
    }

    public Integer getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(Integer idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public Integer getIdZadatak() {
        return idZadatak;
    }

    public void setIdZadatak(Integer idZadatak) {
        this.idZadatak = idZadatak;
    }
}
