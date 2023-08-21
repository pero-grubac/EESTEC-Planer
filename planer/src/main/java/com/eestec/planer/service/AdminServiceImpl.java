package com.eestec.planer.service;

import com.eestec.planer.dao.AdminDAO;
import com.eestec.planer.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public String createAdmin(AdminDTO adminDTO) {
        adminDTO.setLozinka(passwordEncoder.encode(adminDTO.getLozinka()));
       if( adminDAO.save(adminDTO)!=null)
        return "adminDTO";
       else return "fuck";
    }

    @Override
    public AdminDTO updateAdmin(Integer id, AdminDTO adminDTO) {
        adminDTO.setLozinka(passwordEncoder.encode(adminDTO.getLozinka()));
        List<AdminDTO> adminDTOList = adminDAO.findAll();
        adminDTOList.stream()
                .filter(admin ->admin.getIdAdmin()==id)
                .findFirst()
                .ifPresent(admin -> {
                    int index=adminDTOList.indexOf(admin);
                    adminDTOList.set(index,adminDTO);
                });
        return adminDTO;
    }


}
