package com.eestec.P.eestecP.Admin;

import  com.eestec.P.eestecP.Admin.AdminDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminDAO extends JpaRepository<AdminDTO,Integer> {
    Optional<AdminDTO>  getAdminByKorisnickoIme(String korisnickoIme);

    Optional<AdminDTO>  getAdminByIdAdmin(Integer idAdmin);

    @Modifying
    @Query("UPDATE AdminDTO a SET a.lozinka = :lozinka WHERE a.idAdmin = :id")
    void updateAdmin(@Param("lozinka") String lozinka, @Param("id") Integer id);


}
