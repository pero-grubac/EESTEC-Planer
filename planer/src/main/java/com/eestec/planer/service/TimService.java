package com.eestec.planer.service;

import com.eestec.planer.dto.TimDTO;

import java.util.List;

public interface TimService {

    List<TimDTO> getAllTeams();
    TimDTO getTim(Integer id);
    TimDTO createTim(TimDTO timDTO);
    TimDTO updateTim(TimDTO timDTO);
    boolean deleteTime(Integer id);

}
