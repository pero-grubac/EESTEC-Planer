package com.example.EestecPlaner.EestecPlaner.Zahtjev;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Zahtjev
{
    @Column(name = "KorisničkoIme")
String KorisničkoIme;

    public Zahtjev() {

    }

    @Column(name = "Ime")
    String Ime;
    @Column(name = "Lozinka")
    String Lozinka;
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
int IdZahtjev;
@Column(name = "Prezime")
String Prezime;

    public String getKorisničkoIme() {
        return KorisničkoIme;
    }

    public void setKorisničkoIme(String korisničkoIme) {
        KorisničkoIme = korisničkoIme;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public void setLozinka(String lozinka) {
        Lozinka = lozinka;
    }

    public int getIdZahtjev() {
        return IdZahtjev;
    }

    public void setIdZahtjev(int idZahtjev) {
        IdZahtjev = idZahtjev;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }
}
