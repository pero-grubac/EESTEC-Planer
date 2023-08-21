package com.eestec.planer.dao;

import com.eestec.planer.dto.AdminDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminDAO extends JpaRepository<AdminDTO,Integer> {
    Optional<AdminDTO>  getAdminByKorisnickoIme(String korisnickoIme);


}
