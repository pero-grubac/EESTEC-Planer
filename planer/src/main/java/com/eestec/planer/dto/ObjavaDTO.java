package com.eestec.planer.dto;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifikacija")
public class ObjavaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdObjava")
    private int idObjave;

    @Column(name = "Sadržaj")
    private String sadrzaj;

    @Column(name = "VrijemeKreiranja")
    private LocalDateTime vrijemeKreiranja;

    @ManyToOne
    @JoinColumn(name = "IdSuperuser")
    private SuperUserDTO superUser;

    public ObjavaDTO() {
    }

    public ObjavaDTO(String sadrzaj, LocalDateTime vrijemeKreiranja, SuperUserDTO superUser) {
        this.sadrzaj = sadrzaj;
        this.vrijemeKreiranja = vrijemeKreiranja;
        this.superUser = superUser;
    }

    public int getIdObjave() {
        return idObjave;
    }

    public void setIdObjave(int idObjave) {
        this.idObjave = idObjave;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public LocalDateTime getVrijemeKreiranja() {
        return vrijemeKreiranja;
    }

    public void setVrijemeKreiranja(LocalDateTime vrijemeKreiranja) {
        this.vrijemeKreiranja = vrijemeKreiranja;
    }

    public SuperUserDTO getSuperUser() {
        return superUser;
    }

    public void setSuperUser(SuperUserDTO superUser) {
        this.superUser = superUser;
    }
}
