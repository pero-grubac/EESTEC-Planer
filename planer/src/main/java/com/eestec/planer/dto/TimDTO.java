package com.eestec.planer.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tim")
public class TimDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTim")
    private int IdTim;

    @Column(name = "Naziv")
    private String naziv;

    @OneToOne(optional = true)
    @JoinColumn(name = "IdKoordinator")
    private KoordinatorDTO koordinator;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "timovi")
    @JsonIgnore
    private Set<KorisnikDTO> korisnici = new HashSet<>();
    public TimDTO(){}

    public TimDTO(int idTim,String naziv,KoordinatorDTO koordinatorDTO){
        this.IdTim=idTim;
        this.naziv=naziv;
        this.koordinator=koordinatorDTO;
    }
    public TimDTO(String naziv,KoordinatorDTO koordinatorDTO){

        this.naziv=naziv;
        this.koordinator=koordinatorDTO;
    }
    public TimDTO(int idTim){
        this.IdTim=idTim;
    }
    public int getIdTim() {
        return IdTim;
    }

    public void setIdTim(int idTim) {
        IdTim = idTim;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public KoordinatorDTO getKoordinator() {
        return koordinator;
    }

    public void setKoordinator(KoordinatorDTO koordinator) {
        this.koordinator = koordinator;
    }

    public Set<KorisnikDTO> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(Set<KorisnikDTO> korisnici) {
        this.korisnici = korisnici;
    }
}
