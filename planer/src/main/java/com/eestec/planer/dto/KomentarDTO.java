package com.eestec.planer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "komentar")
public class KomentarDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKomentar")
    private Integer idKomentar;
    @Column(name = "IdZadatak")
    private Integer idZadatak;


    @ManyToOne
    @JoinColumn(name = "IdKorisnik")
    private KorisnikDTO korisnik;


    @Column(name = "Datum")
    private LocalDateTime datum;
    @Column(name = "Tekst")
    private String tekst;

   

    public KomentarDTO(Integer idZadatak, KorisnikDTO korisnik, LocalDateTime datum, String tekst) {
        this.idZadatak = idZadatak;
        this.korisnik = korisnik;
        this.datum = datum;
        this.tekst = tekst;
    }

    public KomentarDTO() {

    }

    public Integer getIdKomentar() {
        return idKomentar;
    }

    public void setIdKomentar(Integer idKomentar) {
        this.idKomentar = idKomentar;
    }

    public Integer getIdZadatak() {
        return idZadatak;
    }

    public void setIdZadatak(Integer idZadatak) {
        this.idZadatak = idZadatak;
    }

    public KorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }


}
