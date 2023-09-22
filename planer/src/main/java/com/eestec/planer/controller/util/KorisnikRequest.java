package com.eestec.planer.controller.util;

public class KorisnikRequest {

    private String ime;

    private String prezime;

    private String korisnickoime;

    private String lozinka;

    private String email;

    private int IdKorisnika;

    public KorisnikRequest(String ime, String prezime, String korisnickoime, String lozinka, String email, int idKorisnika) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoime = korisnickoime;
        this.lozinka = lozinka;
        this.email = email;
        IdKorisnika = idKorisnika;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoime() {
        return korisnickoime;
    }

    public void setKorisnickoime(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdKorisnika() {
        return IdKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        IdKorisnika = idKorisnika;
    }


}
