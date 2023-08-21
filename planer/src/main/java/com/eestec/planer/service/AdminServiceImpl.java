package com.eestec.planer.service;

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
    @Transactional // Transaction for creating an admin
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        adminDTO.setLozinka(passwordEncoder.encode(adminDTO.getLozinka()));
        if (adminDAO.save(adminDTO) != null)
            return adminDTO;
        else return null;
    }

    @Override
    @Transactional
    public AdminDTO updateAdmin(AdminDTO adminDTO) {
        if (adminDTO != null) {
            String hash = passwordEncoder.encode(adminDTO.getLozinka());
            adminDTO.setLozinka(hash);
            AdminDTO admin = adminDAO.getAdminByKorisnickoIme(adminDTO.getKorisnickoIme()).get();
            admin.setLozinka(hash);
            adminDAO.save(admin);
            return admin;
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public int deleteAdmin(String korisnickoIme) {
        AdminDTO adminDTO=adminDAO.getAdminByKorisnickoIme(korisnickoIme).get();
        if (adminDTO != null) {
                adminDAO.delete(adminDTO);
                return 1;
        }
        return 0;
    }


}
