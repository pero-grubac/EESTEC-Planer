package com.eestec.planer.dao;


import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.ZadatakDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ZadatakDAO extends JpaRepository<ZadatakDTO, Integer> {
    @Query(value = "SELECT z.* FROM zadatak z  WHERE z.Arhiviran = 0 AND z.IdKategorija = :id", nativeQuery = true)
    List<ZadatakDTO> findByKategorija_IdKategorija(@Param("id") int id);

    @Modifying
    @Query(value = "UPDATE zadatak z SET z.Tekst = :tekst, z.Rok = :rok, z.Naslov = :naslov, z.IdKategorija = :idKategorija WHERE z.IdZadatak = :id", nativeQuery = true)
    void updateZadatak(@Param("tekst") String tekst, @Param("rok") LocalDateTime rok, @Param("naslov") String naslov, @Param("idKategorija") Integer idKategorija, @Param("id") Integer id);


     List<ZadatakDTO> getZadatakDTOByArhiviran(Byte aByte);

     //STATISTIKA


}