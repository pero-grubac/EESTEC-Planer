package com.eestec.planer.dao;


import com.eestec.planer.dto.KoordinatorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface KoordinatorDAO  extends JpaRepository<KoordinatorDTO, Integer>
{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO koordinator (IdKoordinator) VALUES (:idkoordinator)", nativeQuery = true)
    void saveWithIdKoordinator(@Param("idkoordinator") int idkoordinator);


    @Modifying
    @Query(value = "UPDATE tim " +
            "SET IdKoordinator = :idKoordinator " +
            "WHERE IdTim = :idTim", nativeQuery = true)
    @Transactional
    void addKoordinatorToTeam(@Param("idKoordinator") Integer idKoordinator, @Param("idTim") Integer idTim);

}