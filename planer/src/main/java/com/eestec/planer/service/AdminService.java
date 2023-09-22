package com.eestec.planer.service;

import com.eestec.planer.controller.util.KorisnikRequest;
import com.eestec.planer.controller.util.LoginForm;
import com.eestec.planer.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> getAllAdmins();
    //umjesto get all get jednog tj. read
    AdminDTO createAdmin(LoginForm admin);

    AdminDTO updateAdmin(KorisnikRequest korisnikRequest);

    boolean deleteAdmin(Integer id);
    boolean login(LoginForm loginForm);
}
