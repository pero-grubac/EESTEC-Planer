package com.eestec.planer.dao;

import com.eestec.planer.dto.AdminDTO;

import java.util.List;

public interface AdminDAO {
    AdminDTO createAdmin(AdminDTO adminDTO);
    AdminDTO getAdminById(int id);
    void updateAdmin(AdminDTO adminDTO);
    void deleteAdmin(AdminDTO adminDTO);
    List<AdminDTO> getAllAdmin();

}
