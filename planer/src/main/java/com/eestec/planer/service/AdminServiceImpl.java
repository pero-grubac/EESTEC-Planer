package com.eestec.planer.service;

import com.eestec.planer.dao.AdminDAO;
import com.eestec.planer.dto.AdminDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements  AdminService{
    private final AdminDAO adminDAO;
    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }
    @Override
    @Transactional
    public List<AdminDTO> getAllAdmins() {
        List<AdminDTO> admins = adminDAO.getAllAdmin();
        List<AdminDTO> adminDTOs = new ArrayList<>();
        for (AdminDTO admin : admins) {
            AdminDTO adminDTO = new AdminDTO();
            BeanUtils.copyProperties(admin, adminDTO);
            adminDTOs.add(adminDTO);
        }
        return adminDTOs;
    }
    @Override
    @Transactional
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        AdminDTO admin = new AdminDTO();
        BeanUtils.copyProperties(adminDTO, admin);
        adminDAO.createAdmin(admin);
        return adminDTO;
    }

    @Override
    @Transactional
    public AdminDTO getAdminById(int id) {
        AdminDTO admin = adminDAO.getAdminById(id);
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(admin, adminDTO);
        return adminDTO;
    }

    @Override
    @Transactional
    public AdminDTO updateAdmin(int id, AdminDTO adminDTO) {
        AdminDTO admin = adminDAO.getAdminById(id);
        BeanUtils.copyProperties(adminDTO, admin);
        adminDAO.updateAdmin(admin);
        return adminDTO;
    }

    @Override
    @Transactional
    public void deleteAdmin(int id) {
        AdminDTO adminDTO = adminDAO.getAdminById(id);
        adminDAO.deleteAdmin(adminDTO);
    }

}
