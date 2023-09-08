package com.eestec.planer.dao;

import com.eestec.planer.dto.ClanOdboraDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ClanOdboraDAO extends JpaRepository<ClanOdboraDTO,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO clanodbora (IdClana) VALUES (:idclana)", nativeQuery = true)
    void saveWithIdClanOdbora(@Param("idclana") int idclana);
}
