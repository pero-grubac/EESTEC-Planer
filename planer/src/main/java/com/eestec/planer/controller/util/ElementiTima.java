package com.eestec.planer.controller.util;

import com.eestec.planer.dto.KategorijaDTO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.ZadatakDTO;

import java.util.ArrayList;
import java.util.List;

public class ElementiTima {
    private List<KategorijaDTO> kategorije = new ArrayList<>();
    private List<ZadatakDTO> zadaci = new ArrayList<>();
    private List<KorisnikDTO> korisnici = new ArrayList<>();
    private int brojKorisnika;

    public ElementiTima() {
    }

    public List<KategorijaDTO> getKategorije() {
        return kategorije;
    }

    public void setKategorije(List<KategorijaDTO> kategorije) {
        this.kategorije = kategorije;
    }

    public List<ZadatakDTO> getZadaci() {
        return zadaci;
    }

    public void setZadaci(List<ZadatakDTO> zadaci) {
        this.zadaci = zadaci;
    }

    public List<KorisnikDTO> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<KorisnikDTO> korisnici) {
        this.korisnici = korisnici;
    }

    public int getBrojKorisnika() {
        return brojKorisnika;
    }

    public void setBrojKorisnika(int brojKorisnika) {
        this.brojKorisnika = brojKorisnika;
    }
}
