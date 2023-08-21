package com.example.EestecPlaner.EestecPlaner.Korisnik;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Korisnik {

    @Column(name = "Ime")
    String Ime;
    @Column(name = "Prezime")
    String Prezime;
    @Column(name = "KorisnickoIme")
    String KorisnickoIme;

    public Korisnik(String ime, String prezime, String korisnickoIme, String lozinka, String email) {
        Ime = ime;
        Prezime = prezime;
        KorisnickoIme = korisnickoIme;
        Lozinka = lozinka;
        Email = email;
    }

    @Column(name = "Lozinka")
    String Lozinka;
    @Column(name = "Email")
    String Email;
    @Id
    @Column(name = "IdKorisnika")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer IdKorisnika;

    public Korisnik() {

    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    public String getKorisnickoIme() {
        return KorisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        KorisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public void setLozinka(String lozinka) {
        Lozinka = lozinka;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getIdKorisnika() {
        return IdKorisnika;
    }

    public void setIdKorisnika(Integer idKorisnika) {
        IdKorisnika = idKorisnika;
    }
}
