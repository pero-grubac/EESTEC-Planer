package com.eestec.planer.dao;


import com.eestec.planer.dto.KorisnikDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikDAO extends JpaRepository<KorisnikDTO, Integer> {

}