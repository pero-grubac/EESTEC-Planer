package com.eestec.planer.service;

import com.eestec.planer.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> getAllAdmins();

    String createAdmin(AdminDTO adminDTO);
    AdminDTO updateAdmin(Integer id,AdminDTO adminDTO);
}
