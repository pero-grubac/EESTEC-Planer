package com.eestec.planer.service;

import com.eestec.planer.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> getAllAdmins();

    AdminDTO createAdmin(AdminDTO adminDTO);

    AdminDTO updateAdmin(AdminDTO adminDTO);

    int deleteAdmin(String korisnickoIme);
}
