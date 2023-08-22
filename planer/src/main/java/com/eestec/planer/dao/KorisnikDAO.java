package com.eestec.planer.dao;


import com.eestec.planer.dto.KorisnikDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikDAO extends JpaRepository<KorisnikDTO, Integer> {

    @Modifying
    @Query("UPDATE KorisnikDTO k SET k.ime = :ime, k.prezime = :prezime, k.korisnickoime = :korisnickoime, k.lozinka = :lozinka, k.email = :email WHERE k.idKorisnika = :id")
    void updateKorisnik(@Param("ime") String ime, @Param("prezime") String prezime, @Param("korisnickoime") String korisnickoime, @Param("lozinka") String lozinka, @Param("email") String email, @Param("id") Integer id);

}