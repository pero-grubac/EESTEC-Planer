package com.eestec.planer.dto;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Kategorija")
public class KategorijaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKategorija")
    private Integer idKategorija;

    @Column(name = "Naziv")
    private String naziv;

    @ManyToOne
    @JoinColumn(name = "IdTim")
    private TimDTO timDTO;



    public Integer getIdKategorija() {
        return idKategorija;
    }

    public void setIdKategorija(int idKategorija) {
        this.idKategorija = idKategorija;
    }

    public TimDTO getTimDTO() {
        return timDTO;
    }

    public void setTimDTO(TimDTO timDTO) {
        this.timDTO = timDTO;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public KategorijaDTO(int idKategorija, TimDTO timDTO, String naziv) {
        this.idKategorija = idKategorija;
        this.timDTO = timDTO;
        this.naziv = naziv;
    }

    public KategorijaDTO(int idKategorija, String naziv, TimDTO timDTO, Set<ZadatakDTO> zadaci) {
        this.idKategorija = idKategorija;
        this.naziv = naziv;
        this.timDTO = timDTO;

    }

    public KategorijaDTO() {
    }


}
