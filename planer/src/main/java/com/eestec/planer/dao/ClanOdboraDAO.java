package com.eestec.planer.dao;

import com.eestec.planer.dto.ClanOdboraDTO;
import com.eestec.planer.dto.KoordinatorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClanOdboraDAO extends JpaRepository<ClanOdboraDTO,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO clanodbora (IdClana) VALUES (:idclana)", nativeQuery = true)
    void saveWithIdClanOdbora(@Param("idclana") int idclana);

    @Query(value = "SELECT * FROM clanodbora " +
            " INNER JOIN korisnik ON korisnik.IdKorisnika = clanodbora.IdClana " +
            "WHERE  KorisnickoIme = :korisnickoime", nativeQuery = true)
    Optional<ClanOdboraDTO> findBykorisnickoIme(@Param("korisnickoime") String korisnickoIme);

}
