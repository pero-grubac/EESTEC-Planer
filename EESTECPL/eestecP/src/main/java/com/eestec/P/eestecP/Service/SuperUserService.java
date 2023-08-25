package com.eestec.P.eestecP.Service;

import com.eestec.P.eestecP.SuperUser.SuperUserDTO;

import java.util.List;

public interface SuperUserService
{
    List<SuperUserDTO> getAllSuperUsers();

    SuperUserDTO getSuperUser(Integer id);

    SuperUserDTO createSuperUser(SuperUserDTO superUserDTO);

    SuperUserDTO updateSuperUser(SuperUserDTO superUserDTO);

    boolean deleteSuperUser(Integer id);
}
