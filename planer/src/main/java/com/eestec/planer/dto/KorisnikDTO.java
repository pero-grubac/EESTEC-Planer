package com.eestec.planer.dto;

import jakarta.persistence.*;


@Entity
@Table(name = "korisnik")
public class KorisnikDTO {

    @Column(name = "Ime")
    private String ime;
    @Column(name = "Prezime")
    private String prezime;
    @Column(name = "Korisnickoime",unique = true)
    private String korisnickoime;

    @Column(name = "Lozinka")
    private String lozinka;

    @Column(name = "Email",unique = true)
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKorisnika")
    private int idKorisnika;

    public KorisnikDTO() {
    }

    public KorisnikDTO(String ime, String Prezime, String Korisnickoime, String Lozinka, String Email) {
        this.ime = ime;
        this.prezime = Prezime;
        this.korisnickoime = Korisnickoime;
        this.lozinka = Lozinka;
        this.email = Email;
    }

    public KorisnikDTO(String ime, String prezime, String korisnickoime, String lozinka, String email, int idKorisnika) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoime = korisnickoime;
        this.lozinka = lozinka;
        this.email = email;
        this.idKorisnika = idKorisnika;
    }

    private static transient String role="ROLE_USER";

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

    public String getKorisnickoIme() {
        return korisnickoime;
    }

    public void setKorisnickoIme(String korisnickoime) {
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
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public static String getRole() {
        return role;
    }


}
