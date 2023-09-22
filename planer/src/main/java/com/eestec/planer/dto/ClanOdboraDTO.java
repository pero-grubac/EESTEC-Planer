package com.eestec.planer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="clanodbora")
public class ClanOdboraDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdClana")
    private int IdClana;

    @OneToOne
    @JoinColumn(name = "IdClana")
    private SuperUserDTO superuser;

    public ClanOdboraDTO(){}

    private transient String role = "Clan odbora";
    private transient String uloga = "Clan odbora";
    public ClanOdboraDTO(SuperUserDTO superUserDTO){this.superuser=superUserDTO;}



    public SuperUserDTO getSuperuser() {
        return superuser;
    }

    public void setSuperuser(SuperUserDTO superuser) {
        this.superuser = superuser;
    }
    public void setIdClana(int id){
        IdClana=id;
    }
    @JsonIgnore
    public int getIdClana(){
        return  IdClana;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public String getRole() {
        return role;
    }
}
