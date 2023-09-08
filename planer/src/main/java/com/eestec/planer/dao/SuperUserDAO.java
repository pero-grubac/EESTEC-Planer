package com.eestec.planer.dao;

import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.SuperUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SuperUserDAO  extends JpaRepository<SuperUserDTO, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO superuser (IdSuperuser) VALUES (:idsuperuser)", nativeQuery = true)
    void saveWithIdSuperuser(@Param("idsuperuser") int idsuperuser);
}
