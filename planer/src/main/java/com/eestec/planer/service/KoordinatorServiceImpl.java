package com.eestec.planer.service;

import com.eestec.planer.dao.KoordinatorDAO;
import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dao.TimDAO;
import com.eestec.planer.dto.KoordinatorDTO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.SuperUserDTO;
import com.eestec.planer.dto.TimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KoordinatorServiceImpl implements KoordinatorService {
    private KoordinatorDAO koordinatorDAO;

    private KorisnikServiceImpl korisnikService;
    private SuperUserServiceImpl superUserService;
    private ClanOdboraServiceImpl clanOdboraService;
    private TimServiceImpl timService;

    @Autowired
    public KoordinatorServiceImpl(KoordinatorDAO koordinatorDAO,KorisnikServiceImpl korisnikService,
                                  SuperUserServiceImpl superUserService, ClanOdboraServiceImpl clanOdboraService,
                                  TimServiceImpl timService) {
        this.koordinatorDAO = koordinatorDAO;
        this.korisnikService = korisnikService;

        this.superUserService = superUserService;
        this.clanOdboraService = clanOdboraService;
        this.timService = timService;
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
        KorisnikDTO korisnikDTO = korisnikService.getKorisnik(id);
        if (korisnikDTO != null ) {
            KoordinatorDTO koordinator = new KoordinatorDTO();
            SuperUserDTO superuser = new SuperUserDTO();
            superuser.setKorisnik(korisnikDTO);
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
        TimDTO tim = timService.getTim(idTim);
        KoordinatorDTO koordinator = koordinatorDAO.findById(idKoordinator).orElse(null);
        if (tim != null & koordinator != null) {
            tim.setIdKoordinator(koordinator.getIdKoordinator());
          if(  timService.updateTim(tim)!=null)
            return true;
        }
        return false;
    }


}
