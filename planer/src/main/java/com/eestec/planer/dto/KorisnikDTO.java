package com.eestec.planer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "korisnik")
public class KorisnikDTO {

    @Column(name = "Ime")
    private String ime;
    @Column(name = "Prezime")
    private String prezime;
    @Column(name = "Korisnickoime", unique = true)
    private String korisnickoime;

    @JsonIgnore
    @Column(name = "Lozinka")
    private String lozinka;

    @JsonIgnore
    @Column(name = "Obrisan")
    private boolean deleted;
    @Column(name = "Email", unique = true)
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKorisnika")
    private int IdKorisnika;

    @OneToOne(mappedBy = "korisnik", cascade = CascadeType.REMOVE)
    private SuperUserDTO superUserDTO;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "korisnik_pripada_timu",
            joinColumns = @JoinColumn(name = "Korisnik_IdKorisnika", referencedColumnName = "IdKorisnika"),
            inverseJoinColumns = @JoinColumn(name = "Tim_IdTim", referencedColumnName = "IdTim")
    )
    private Set<TimDTO> timovi = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "korisnik_radi_zadatak",
            joinColumns = @JoinColumn(name = "Korisnik_IdKorisnika", referencedColumnName = "IdKorisnika"),
            inverseJoinColumns = @JoinColumn(name = "Zadatak_IdZadatak", referencedColumnName = "IdZadatak")
    )
    private Set<ZadatakDTO> zadaci = new HashSet<>();

    public KorisnikDTO() {
    }

    public KorisnikDTO(int id) {
        IdKorisnika = id;
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
        this.IdKorisnika = idKorisnika;
    }


    private static transient String role = "KORISNIK";
    private transient String uloga = "Korisnik";

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
        return IdKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        IdKorisnika = idKorisnika;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public Set<TimDTO> getTimovi() {
        return timovi;
    }

    public void setTimovi(Set<TimDTO> timovi) {
        this.timovi = timovi;
    }

    public void deleteTim(int id) {
        TimDTO tim = timovi.stream()
                .filter(timDTO -> timDTO.getIdTim() == id).findFirst().orElse(null);
        if (tim != null) {
            this.timovi.remove(tim);
            tim.getKorisnici().remove(this);
        }

    }

    public static void setRole(String role) {
        KorisnikDTO.role = role;
    }

    @JsonIgnore
    public String getRole() {
        return role;
    }


    public Set<ZadatakDTO> getZadaci() {
        return zadaci;
    }

    public void setZadaci(Set<ZadatakDTO> zadaci) {
        this.zadaci = zadaci;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
