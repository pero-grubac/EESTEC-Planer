package com.eestec.planer.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zadatak")
public class ZadatakDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdZadatak")
    private int idZadatak;

    @Column(name = "Tekst")
    private String tekst;

    @Column(name = "Rok")
    private LocalDateTime rok;

    @Column(name = "IdAutora")
    private int idAutora;

    @Column(name = "DatumKreiranja")
    private LocalDateTime datumKreiranja;

    @Column(name = "Naslov")
    private String naslov;

    @ManyToOne
    @JoinColumn(name = "IdKategorija")
    private KategorijaDTO kategorija;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "zadaci")
    @JsonIgnore
    private Set<KorisnikDTO> korisnici = new HashSet<>();

    public int getIdZadatak() {
        return idZadatak;
    }

    public void setIdZadatak(int idZadatak) {
        this.idZadatak = idZadatak;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDateTime getRok() {
        return rok;
    }

    public void setRok(LocalDateTime rok) {
        this.rok = rok;
    }

    public int getIdAutora() {
        return idAutora;
    }

    public void setIdAutora(int idAutora) {
        this.idAutora = idAutora;
    }

    public LocalDateTime getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(LocalDateTime datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public KategorijaDTO getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaDTO kategorija) {
        this.kategorija = kategorija;
    }

    public Set<KorisnikDTO> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(Set<KorisnikDTO> korisnici) {
        this.korisnici = korisnici;
    }

    public ZadatakDTO() {
    }
}

