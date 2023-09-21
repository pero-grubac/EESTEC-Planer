package com.eestec.planer.dao;

import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.ObjavaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ObjavaDAO extends JpaRepository<ObjavaDTO, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO notifikacija(Sadržaj, IdSuperuser) " +
            "VALUES (:sadrzaj, :idSuperuser)", nativeQuery = true)
    void save(@Param("sadrzaj") String sadrzaj, @Param("idSuperuser") int idSuperuser);
}
