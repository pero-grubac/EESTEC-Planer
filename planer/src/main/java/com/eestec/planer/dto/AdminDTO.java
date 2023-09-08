package com.eestec.planer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class AdminDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAdmin")
    private int idAdmin;

    @Column(name = "KorisnickoIme", unique = true)
    private String korisnickoIme;

    @Column(name = "Lozinka")
    private String lozinka;

    public AdminDTO(String korisnickoIme, String lozinka) {
        this.korisnickoIme=korisnickoIme;
        this.lozinka=lozinka;
    }

    public AdminDTO(int idAdmin, String korisnickoIme, String lozinka) {
        this.idAdmin = idAdmin;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public AdminDTO() {

    }
    private static transient String role="ROLE_ADMIN";
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

    @JsonIgnore
    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }


    public String getRole() {
        return role;
    }
}

