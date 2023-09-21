package com.eestec.planer.dao;


import com.eestec.planer.dto.AdminDTO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface KorisnikDAO extends JpaRepository<KorisnikDTO, Integer> {

    @Modifying
    @Query("UPDATE KorisnikDTO k SET k.ime = :ime, k.prezime = :prezime, k.korisnickoime = :korisnickoime, k.lozinka = :lozinka, k.email = :email WHERE k.IdKorisnika = :id")
    void updateKorisnik(@Param("ime") String ime, @Param("prezime") String prezime, @Param("korisnickoime") String korisnickoime, @Param("lozinka") String lozinka, @Param("email") String email, @Param("id") Integer id);

    @Modifying
    @Query(value = "INSERT INTO korisnik_pripada_timu (Korisnik_IdKorisnika, Tim_IdTim) " +
            "SELECT :idKorisnika AS Korisnik_IdKorisnika, :idTim AS Tim_IdTim " +
            "FROM dual " +
            "WHERE NOT EXISTS (" +
            "    SELECT 1 FROM korisnik_pripada_timu " +
            "    WHERE Korisnik_IdKorisnika = :idKorisnika AND Tim_IdTim = :idTim" +
            ")", nativeQuery = true)
    @Transactional
    void joinTeam(@Param("idKorisnika") Integer idKorisnika, @Param("idTim") Integer idTim);

    @Modifying
    @Query(value = "DELETE FROM korisnik_pripada_timu " +
            "WHERE Korisnik_IdKorisnika = :idKorisnika AND Tim_IdTim = :idTim", nativeQuery = true)
    @Transactional
    void leaveTeam(@Param("idKorisnika") Integer idKorisnika, @Param("idTim") Integer idTim);

    @Query("SELECT k FROM KorisnikDTO k WHERE k.korisnickoime = :korisnickoime")
    Optional<KorisnikDTO> findBykorisnickoIme(@Param("korisnickoime") String korisnickoIme);

    @Query(value = "SELECT k.* FROM korisnik k " +
            "INNER JOIN korisnik_pripada_timu kt on k.IdKorisnika=kt.Korisnik_IdKorisnika " +
            "WHERE kt.Tim_IdTim =:idTim", nativeQuery = true)
    List<KorisnikDTO> getAllByIdTim(@Param("idTim") Integer idTim);
}