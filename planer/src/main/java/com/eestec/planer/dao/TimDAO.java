package com.eestec.planer.dao;

import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface TimDAO extends JpaRepository<TimDTO,Integer> {




}
