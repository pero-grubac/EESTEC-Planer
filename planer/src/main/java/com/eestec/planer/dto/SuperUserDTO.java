package com.eestec.planer.dto;

import jakarta.persistence.*;


@Entity
@Table(name = "superuser")
public class SuperUserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSuperuser")
    private int idsuperuser;

    @OneToOne
    @JoinColumn(name = "IdSuperuser")
    private KorisnikDTO korisnik;

    @OneToOne(mappedBy ="superuser",cascade = CascadeType.REMOVE)
    private KoordinatorDTO koordinatorDTO;

    @OneToOne(mappedBy ="superuser",cascade = CascadeType.REMOVE)
    private ClanOdboraDTO clanOdboraDTO;

    public SuperUserDTO() {
    }

   /* public SuperUserDTO(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
        idsuperuser = korisnik.getIdKorisnika();
    }*/
    public SuperUserDTO(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }
    public SuperUserDTO(Integer id) {
        idsuperuser = id;
    }

    public KorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }

    public void setIdsuperuser(int idsuperuser) {
        this.idsuperuser = idsuperuser;
    }

//    public int getIdsuperuser() {
//        return idsuperuser;
//    }
}
