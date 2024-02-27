package com.eestec.planer.dao;

import com.eestec.planer.dto.AdminDTO;
import com.eestec.planer.dto.KorisnikDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminDAO extends JpaRepository<AdminDTO,Integer> {
    @Query("SELECT a FROM AdminDTO a WHERE a.korisnickoIme = :korisnickoime")
    Optional<AdminDTO>  findBykorisnickoIme(@Param("korisnickoime") String korisnickoIme);
    Optional<AdminDTO> findAllByKorisnickoIme(String korisnickoIme);

    Optional<AdminDTO>  getAdminByIdAdmin(Integer idAdmin);

    @Modifying
    @Query("UPDATE AdminDTO a SET a.lozinka = :lozinka WHERE a.idAdmin = :id")
    void updateAdmin(@Param("lozinka") String lozinka, @Param("id") Integer id);


}
