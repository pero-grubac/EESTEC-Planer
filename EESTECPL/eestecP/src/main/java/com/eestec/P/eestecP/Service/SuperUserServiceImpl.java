package com.eestec.P.eestecP.Service;
import com.eestec.P.eestecP.SuperUser.SuperUserDAO;
import com.eestec.P.eestecP.SuperUser.SuperUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuperUserServiceImpl implements SuperUserService {

    private SuperUserDAO superUserDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SuperUserServiceImpl(SuperUserDAO superUserDAO) {
        this.superUserDAO = superUserDAO;
    }

    @Override
    public List<SuperUserDTO> getAllSuperUsers() {
        List<SuperUserDTO> superUserDTOList = superUserDAO.findAll();
        return superUserDTOList;
    }

    @Override
    @Transactional
    public SuperUserDTO getSuperUser(Integer id) {
        return superUserDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public SuperUserDTO createSuperUser(SuperUserDTO superUserDTO) {
        String hash = passwordEncoder.encode(superUserDTO.getLozinka());
        superUserDTO.setLozinka(hash);
        if (superUserDAO.save(superUserDTO) != null)
            return superUserDTO;
        else return null;
    }

    @Override
    @Transactional
    public SuperUserDTO updateSuperUser(SuperUserDTO superUserDTO) {
        if (superUserDTO != null) {
            String hash = passwordEncoder.encode(superUserDTO.getLozinka());
            superUserDTO.setLozinka(hash);
            SuperUserDTO superUser = superUserDAO.findById(superUserDTO.getIdKorisnika()).orElse(null);
            if (superUser != null) {
                superUser.setLozinka((hash));
                superUser.setIme(superUserDTO.getIme());
                superUser.setEmail(superUserDTO.getEmail());
                superUser.setPrezime(superUserDTO.getPrezime());
                superUser.setKorisnickoIme(superUserDTO.getKorisnickoIme());
                superUserDAO.save(superUser);
                return superUser;
            }
            return null;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteSuperUser(Integer id) {
        SuperUserDTO superUser = superUserDAO.findById(id).orElse(null);
        if (superUser != null) {
            superUserDAO.delete(superUser);
            return true;
        }
        return false;
    }
}