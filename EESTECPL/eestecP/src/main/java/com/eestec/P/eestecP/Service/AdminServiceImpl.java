package com.eestec.P.eestecP.Service;

import com.eestec.P.eestecP.Admin.AdminDAO;
import com.eestec.P.eestecP.Admin.AdminDTO;
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
            AdminDTO admin = adminDAO.findById(adminDTO.getIdAdmin()).orElse(null);
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


}
