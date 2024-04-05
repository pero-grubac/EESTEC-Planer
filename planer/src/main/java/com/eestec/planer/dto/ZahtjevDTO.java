package com.eestec.planer.dto;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name="zahtjev")
public class ZahtjevDTO
{
    public ZahtjevDTO(String korisnickoIme, String ime, String lozinka, int idZahtjev, String prezime, String email, LocalDateTime datumKreiranja) {
        KorisnickoIme = korisnickoIme;
        Ime = ime;
        Lozinka = lozinka;
        IdZahtjev = idZahtjev;
        Prezime = prezime;
        Email = email;
        this.datumKreiranja = datumKreiranja;
    }

    public ZahtjevDTO() {
    }

    @Column(name = "KorisničkoIme")
    private String KorisnickoIme;
    @Column(name = "Ime")
    private String Ime;
    @Column(name = "Lozinka")
    private String Lozinka;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int IdZahtjev;

    @Column(name = "Prezime")
    private String Prezime;

    @Column(name = "Email")
    private String Email;

    @Column(name = "DatumKreiranja")
    private LocalDateTime datumKreiranja;




    public String getKorisnickoIme() {
        return KorisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        KorisnickoIme = korisnickoIme;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setDatumKreiranja(LocalDateTime date){
        datumKreiranja=date;
    }
    public LocalDateTime getDatumKreiranja(){
        return  datumKreiranja;
    }
}
