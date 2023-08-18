package com.eestec.planer.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class AdminDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAdmin")
    private int idAdmin;

    @Column(name = "KorisnickoIme")
    private String korisnickoIme;

    @Column(name = "Lozinka")
    private String lozinka;

    // Getters and setters
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}

