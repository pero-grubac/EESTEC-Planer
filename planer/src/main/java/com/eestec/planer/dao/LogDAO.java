package com.eestec.planer.dao;

import com.eestec.planer.dto.AdminDTO;
import com.eestec.planer.dto.LogDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogDAO extends JpaRepository<LogDTO,Integer> {

    List<LogDTO> findAllByIdPorukaIn(List<Integer> numbers);
    List<LogDTO> findAllByIdPorukaInAndTim(List<Integer> numbers,Integer tim);
    List<LogDTO> findAllByIdPorukaInAndSubjekat(List<Integer> numbers, String subjekat);
    List<LogDTO> findAllByIdPorukaInAndSubjekatAndTim(List<Integer> numbers, String subjekat,Integer tim);

}
