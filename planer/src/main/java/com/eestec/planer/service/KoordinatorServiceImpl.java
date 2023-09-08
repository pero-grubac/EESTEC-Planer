package com.eestec.planer.service;

import com.eestec.planer.dao.KoordinatorDAO;
import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dao.SuperUserDAO;
import com.eestec.planer.dao.TimDAO;
import com.eestec.planer.dto.KoordinatorDTO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.SuperUserDTO;
import com.eestec.planer.dto.TimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class KoordinatorServiceImpl implements KoordinatorService {
    private KoordinatorDAO koordinatorDAO;
    private KorisnikDAO korisnikDAO;
    private TimDAO timDAO;

    @Autowired
    public KoordinatorServiceImpl(KoordinatorDAO koordinatorDAO, KorisnikDAO korisnikDAO, TimDAO timDAO) {
        this.koordinatorDAO = koordinatorDAO;
        this.korisnikDAO = korisnikDAO;
        this.timDAO = timDAO;
    }

    @Override
    public List<KoordinatorDTO> getAllKoordinatori() {

        return koordinatorDAO.findAll();
    }

    @Override
    @Transactional
    public KoordinatorDTO getKoordinator(Integer id) {
        return koordinatorDAO.findById(id).get();
    }

    @Override
    @Transactional
    public KoordinatorDTO createKoordinator(Integer id) {
        Optional<KorisnikDTO> koordinatorDTOOptional = korisnikDAO.findById(id);
        if (koordinatorDTOOptional.isPresent()) {
            KorisnikDTO korisnik = koordinatorDTOOptional.get();
            KoordinatorDTO koordinator = new KoordinatorDTO();
            SuperUserDTO superuser = new SuperUserDTO();
            superuser.setKorisnik(korisnik);
            koordinator.setSuperuser(superuser);
            koordinatorDAO.saveWithIdKoordinator(id);
            return koordinator;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteKoordinator(Integer id) {
        KoordinatorDTO koordinatorDTO = koordinatorDAO.findById(id).orElse(null);
        if (koordinatorDTO != null) {
            koordinatorDAO.delete(koordinatorDTO);
            return true;
        }
        return false;
    }

    @Override
    public boolean addToTeam(Integer idKoordinator, Integer idTim) {
        TimDTO tim = timDAO.findById(idTim).orElse(null);
        KoordinatorDTO koordinator = koordinatorDAO.findById(idKoordinator).orElse(null);
        if (tim != null & koordinator != null) {
            koordinatorDAO.addKoordinatorToTeam(idKoordinator, idTim);
            return true;
        }
        return false;
    }


}
