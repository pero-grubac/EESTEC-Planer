package com.eestec.P.eestecP.Korisnik;


import com.eestec.P.eestecP.SuperUser.SuperUserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("korisnik")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class KorisnikDTO{

    @Column(name = "Ime")
    protected String Ime;
    @Column(name = "Prezime")
   protected   String Prezime;
    @Column(name = "KorisnickoIme")
    protected String KorisnickoIme;

    @Column(name = "Lozinka")
    protected String Lozinka;

    @Column(name = "Email")
    protected String Email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKorisnika")
    protected Integer IdKorisnika;

    @OneToOne(mappedBy = "korisnikDTO", cascade = CascadeType.ALL)
    private SuperUserDTO superUserDTO;


    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    public String getKorisnickoIme() {
        return KorisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        KorisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public void setLozinka(String lozinka) {
        Lozinka = lozinka;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getIdKorisnika() {
        return IdKorisnika;
    }

    public void setIdKorisnika(Integer idKorisnika) {
        IdKorisnika = idKorisnika;
    }


}
