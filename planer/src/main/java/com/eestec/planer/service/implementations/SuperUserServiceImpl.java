package com.eestec.planer.service.implementations;

import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dao.SuperUserDAO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.SuperUserDTO;
import com.eestec.planer.service.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SuperUserServiceImpl implements SuperUserService {

    private SuperUserDAO superUserDAO;
    private KorisnikDAO korisnikDAO;

    @Autowired
    public SuperUserServiceImpl(SuperUserDAO superUserDAO, KorisnikDAO korisnikDAO) {
        this.superUserDAO = superUserDAO;
        this.korisnikDAO = korisnikDAO;
    }

    @Override
    public List<SuperUserDTO> getAllSuperUsers() {
        return superUserDAO.findAll();
    }

    @Override
    @Transactional
    public SuperUserDTO getSuperUser(Integer id) {
        Optional<SuperUserDTO> superUserDTO = superUserDAO.findById(id);
        if(superUserDTO.isPresent())
            return superUserDTO.get();
        return null;
    }

    @Override
    @Transactional
    public SuperUserDTO createSuperUser(Integer id) {
        Optional<KorisnikDTO> korisnikDTOOptional = korisnikDAO.findById(id);
       // Optional<SuperUserDTO> superUserDTOOptional = superUserDAO.findById(id);
        if (korisnikDTOOptional.isPresent() ) {
            KorisnikDTO korisnik = korisnikDTOOptional.get();
            SuperUserDTO superuser = new SuperUserDTO();
            superuser.setKorisnik(korisnik);
           // superuser.setIdsuperuser(id);
          //  return superUserDAO.save(superuser);
            superUserDAO.saveWithIdSuperuser(id);
            return superuser;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteSuperUser(Integer id) {
        SuperUserDTO superUserDTO = superUserDAO.findById(id).orElse(null);
        if (superUserDTO != null) {
            superUserDAO.delete(superUserDTO);
            return true;
        }
        return false;
    }
}
