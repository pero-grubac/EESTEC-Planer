package com.eestec.planer.service;

import com.eestec.planer.dto.SuperUserDTO;

import java.util.List;

public interface SuperUserService {

    List<SuperUserDTO> getAllSuperUsers();
    SuperUserDTO getSuperUser(Integer id);

    SuperUserDTO createSuperUser(Integer id);
    boolean deleteSuperUser(Integer id);
}
