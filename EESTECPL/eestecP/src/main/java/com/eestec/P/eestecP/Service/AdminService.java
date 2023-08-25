package com.eestec.P.eestecP.Service;

import com.eestec.P.eestecP.Admin.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> getAllAdmins();
    //umjesto get all get jednog tj. read
    AdminDTO createAdmin(AdminDTO adminDTO);

    AdminDTO updateAdmin(AdminDTO adminDTO);

    boolean deleteAdmin(Integer id);
}
