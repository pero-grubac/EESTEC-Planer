package com.eestec.planer.service;

import com.eestec.planer.dao.KategorijaDAO;
import com.eestec.planer.dao.TimDAO;
import com.eestec.planer.dto.KategorijaDTO;
import com.eestec.planer.dto.KoordinatorDTO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KategorijaServiceImpl implements KategorijaService {

    @Autowired
    KategorijaDAO kategorijaDAO;
    @Autowired
    private TimDAO timDAO;

    @Autowired
    public KategorijaServiceImpl(KategorijaDAO kategorijaDAO) {
        this.kategorijaDAO = kategorijaDAO;
    }

    @Override
    public List<KategorijaDTO> getAllKategorije() {
        return kategorijaDAO.findAll();
    }

    @Override
    @Transactional
    public boolean deleteKategorija(Integer id) {
        KategorijaDTO kategorijaDTO = kategorijaDAO.findById(id).orElse(null);
        if (kategorijaDTO != null) {
            kategorijaDAO.delete(kategorijaDTO);
            return true;
        }
        return false;
    }

    /*public KategorijaDTO updateCategory(KategorijaDTO updatedCategory) {
        Integer categoryId = updatedCategory.getIdKategorija();
        KategorijaDTO existingCategory = kategorijaDAO.findById(categoryId).orElse(null);
        if (existingCategory != null) {
            existingCategory.setNaziv(updatedCategory.getNaziv());
            return kategorijaDAO.save(existingCategory);
        }
        return null;
    }*/

/*    @Override
    public KategorijaDTO updateCategory(KategorijaDTO updatedCategory) {
        Integer categoryId = updatedCategory.getIdKategorija();
        KategorijaDTO existingCategory = kategorijaDAO.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Kategorija s ID-om " + categoryId + " nije pronađena"));

        existingCategory.setNaziv(updatedCategory.getNaziv());
        return kategorijaDAO.save(existingCategory);
    }*/

    @Override
    public KategorijaDTO updateCategory(KategorijaDTO kategorijaDTO) {
        Integer categoryId = kategorijaDTO.getIdKategorija();

        // Ispisivanje idKategorije na konzolu
        System.out.println("ID kategorije koji je proslijeđen: " + categoryId);

        KategorijaDTO existingCategory = kategorijaDAO.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Kategorija s ID-om " + kategorijaDTO.getIdKategorija() + " nije pronađena"));

        existingCategory.setNaziv(kategorijaDTO.getNaziv());
        return kategorijaDAO.save(existingCategory);

    }

    @Override
    public KategorijaDTO getKategoriju(int idKategorija) {
        return kategorijaDAO.findByIdKategorija(idKategorija);
    }

    public KategorijaDTO createCategory(KategorijaDTO kategorija) {
        return kategorijaDAO.save(kategorija);
    }

    public List<KategorijaDTO> getAllByIdTim(Integer idTim) {
        return kategorijaDAO.findByTimDTO_IdTim(idTim);
    }

}
