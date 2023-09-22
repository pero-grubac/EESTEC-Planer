package com.eestec.planer.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "koordinator")
public class KoordinatorDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKoordinator")
    private int idKoordinator;
    @OneToOne
    @JoinColumn(name = "IdKoordinator")
    private SuperUserDTO superuser;

    private transient String uloga = "Koordinator";
    private transient String role = "Koordinator";

    /*   @OneToOne(mappedBy ="koordinator",cascade = CascadeType.REMOVE)
       private TimDTO tim;*/
    public KoordinatorDTO() {
    }

    public KoordinatorDTO(SuperUserDTO superUserDTO) {
        superuser = superUserDTO;
    }

    public KoordinatorDTO(int id) {
        idKoordinator = id;
    }



    public SuperUserDTO getSuperuser() {
        return superuser;
    }

    public void setSuperuser(SuperUserDTO superuser) {
        this.superuser = superuser;
    }

    public void setIdKoordinator(int idKoordinator) {
        this.idKoordinator = idKoordinator;
    }

    @JsonIgnore
    public int getIdKoordinator() {
        return idKoordinator;
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

    public void setRole(String role) {
        this.role = role;
    }
}
