package com.eestec.planer.service;

import com.eestec.planer.dao.ClanOdboraDAO;
import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dto.ClanOdboraDTO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.SuperUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClanOdboraServiceImpl implements ClanOdboraService {
    private KorisnikDAO korisnikDAO;
    private ClanOdboraDAO clanOdboraDAO;
    private SuperUserServiceImpl superUserService;


    @Autowired
    public ClanOdboraServiceImpl(ClanOdboraDAO clanOdboraDAO, KorisnikDAO korisnikDAO,
                                 SuperUserServiceImpl superUserService) {
        this.clanOdboraDAO = clanOdboraDAO;
        this.korisnikDAO = korisnikDAO;

        this.superUserService = superUserService;

    }

    @Override
    public List<ClanOdboraDTO> getAllClanOdbora() {
        return clanOdboraDAO.findAll();
    }

    @Override
    @Transactional
    public ClanOdboraDTO getClanOdbora(Integer id) {
        return clanOdboraDAO.findById(id).get();
    }

    @Override
    @Transactional
    public ClanOdboraDTO createClanOdbora(Integer id) {
        Optional<KorisnikDTO> korisnikDTOOptional = korisnikDAO.findById(id);
        if (korisnikDTOOptional.isPresent()) {
            KorisnikDTO korisnikDTO = korisnikDTOOptional.get();
            SuperUserDTO superuser = new SuperUserDTO();
            superuser.setKorisnik(korisnikDTO);
            ClanOdboraDTO clanOdboraDTO = new ClanOdboraDTO();
            clanOdboraDTO.setSuperuser(superuser);
            clanOdboraDAO.saveWithIdClanOdbora(id);
            return clanOdboraDTO;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteClanOdbora(Integer id) {
        ClanOdboraDTO clanOdboraDTO =clanOdboraDAO.findById(id).orElse(null);
        if (clanOdboraDTO != null) {
            clanOdboraDAO.delete(clanOdboraDTO);
            return true;
        }
        return false;
    }
}
