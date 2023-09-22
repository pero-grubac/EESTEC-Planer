package com.eestec.planer.service;

import com.eestec.planer.controller.util.KorisnikRequest;
import com.eestec.planer.controller.util.LoginForm;
import com.eestec.planer.dao.AdminDAO;
import com.eestec.planer.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminDAO adminDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public List<AdminDTO> getAllAdmins() {

        List<AdminDTO> adminDTOList = adminDAO.findAll();
        return adminDTOList;
    }

    @Override
    @Transactional
    public AdminDTO createAdmin(LoginForm admin) {
        String hash = passwordEncoder.encode(admin.getLozinka());
        admin.setLozinka(hash);
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setLozinka(hash);
        adminDTO.setKorisnickoIme(admin.getUsername());

        if (adminDTO != null) {
            adminDAO.save(adminDTO);
            return adminDTO;
        } else return null;
    }

    @Override
    @Transactional
    public AdminDTO updateAdmin(KorisnikRequest korisnikRequest) {
        if (korisnikRequest != null && !korisnikRequest.getLozinka().isEmpty()) {
            String hash = passwordEncoder.encode(korisnikRequest.getLozinka());
            korisnikRequest.setLozinka(hash);
            AdminDTO admin = adminDAO.findBykorisnickoIme(korisnikRequest.getKorisnickoime()).orElse(null);
            if (admin != null) {
                admin.setLozinka(hash);
                adminDAO.save(admin);
                return admin;
            }
            return null;
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public boolean deleteAdmin(Integer id) {
        AdminDTO adminDTO = adminDAO.findById(id).orElse(null);
        if (adminDTO != null) {
            adminDAO.delete(adminDTO);
            return true;
        }
        return false;
    }

    @Override
    public boolean login(LoginForm loginForm) {
        Optional<AdminDTO> optionalAdminDTO = adminDAO.findBykorisnickoIme(loginForm.getUsername());
        if (optionalAdminDTO.isPresent()) {
            AdminDTO admin = optionalAdminDTO.get();
            return passwordEncoder.matches(loginForm.getLozinka(), admin.getLozinka());
        }
        return false;
    }


}
