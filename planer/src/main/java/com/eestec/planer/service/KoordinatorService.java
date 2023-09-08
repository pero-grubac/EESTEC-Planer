package com.eestec.planer.service;


import com.eestec.planer.dto.KoordinatorDTO;

import java.util.List;


public interface KoordinatorService {

    List<KoordinatorDTO> getAllKoordinatori();

    KoordinatorDTO getKoordinator(Integer id);

    KoordinatorDTO createKoordinator(Integer id);

    boolean deleteKoordinator(Integer id);

    boolean addToTeam(Integer idKoordinator, Integer idTim);



}

