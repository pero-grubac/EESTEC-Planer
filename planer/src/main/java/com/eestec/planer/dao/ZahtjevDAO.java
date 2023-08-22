package com.eestec.planer.dao;


import com.eestec.planer.dto.ZahtjevDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZahtjevDAO extends JpaRepository<ZahtjevDTO, Integer> {

}
