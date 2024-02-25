package com.eestec.planer.dao;

import com.eestec.planer.dto.KategorijaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KategorijaDAO extends JpaRepository<KategorijaDTO, Integer>
{
    List<KategorijaDTO> findAll();
    KategorijaDTO findByIdKategorija(int idKategorija);

    @Query(value = "SELECT k.* FROM kategorija k " +
            "INNER JOIN tim t ON t.IdTim = k.IdTim " +
            "WHERE t.IdTim = :idTim", nativeQuery = true)
    List<KategorijaDTO> findByTimDTO_IdTim(@Param("idTim") int idTim);


    @Modifying
    @Query(value = "INSERT INTO kategorija (Naziv, IdTim) VALUES (:naziv, :id)", nativeQuery = true)
    void create(@Param("naziv") String naziv, @Param("id") Integer id);

}
