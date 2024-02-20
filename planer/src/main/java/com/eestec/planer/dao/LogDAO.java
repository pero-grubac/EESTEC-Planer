package com.eestec.planer.dao;

import com.eestec.planer.dto.AdminDTO;
import com.eestec.planer.dto.LogDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDAO extends JpaRepository<LogDTO,Integer> {
}
