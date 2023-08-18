package com.eestec.planer.dao;
import com.eestec.planer.dto.AdminDTO;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.*;
import java.util.List;

@Repository
public class AdminDAOImpl implements AdminDAO {

    private final EntityManager entityManager;

    @Autowired
    public AdminDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AdminDTO> getAllAdmin() {
        TypedQuery<AdminDTO> query = entityManager.createQuery("SELECT a FROM AdminDTO a", AdminDTO.class);
        return query.getResultList();
    }
    @Override
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        entityManager.persist(adminDTO);
        return adminDTO;
    }

    @Override
    public AdminDTO getAdminById(int id) {
        return entityManager.find(AdminDTO.class, id);
    }

    @Override
    public void updateAdmin(AdminDTO adminDTO) {
        entityManager.merge(adminDTO);
    }

    @Override
    public void deleteAdmin(AdminDTO adminDTO) {
        entityManager.remove(adminDTO);
    }
}