package com.eestec.planer.controller.util;

public class KorisnikTim {
    Integer idKorisnika;
    Integer idTim;

    public KorisnikTim(Integer korisnikDTO, Integer idTim) {
        this.idKorisnika = korisnikDTO;
        this.idTim = idTim;
    }

    public Integer getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(Integer idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public Integer getIdTim() {
        return idTim;
    }

    public void setIdTim(Integer idTim) {
        this.idTim = idTim;
    }
}
