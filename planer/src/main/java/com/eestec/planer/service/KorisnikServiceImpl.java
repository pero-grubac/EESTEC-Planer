package com.eestec.planer.service;

import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dto.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    private KorisnikDAO korisnikDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public KorisnikServiceImpl(KorisnikDAO korisnikDAO) {
        this.korisnikDAO = korisnikDAO;
    }

    @Override
    public List<KorisnikDTO> getAllKorisnici() {
        List<KorisnikDTO> korisnikDTOList = korisnikDAO.findAll();
        return korisnikDTOList;
    }

    @Override
    @Transactional
    public KorisnikDTO getKorisnik(Integer id) {
        KorisnikDTO korisnikDTO = korisnikDAO.findById(id).orElse(null);
        return korisnikDTO;
    }

    @Override
    @Transactional
    public KorisnikDTO createKorisnik(KorisnikDTO korisnikDTO) {
        String hash = passwordEncoder.encode(korisnikDTO.getLozinka());
        korisnikDTO.setLozinka(hash);
        if (korisnikDAO.save(korisnikDTO) != null)
            return korisnikDTO;
        else return null;
    }

    @Override
    @Transactional
    public KorisnikDTO updateKorisnik(KorisnikDTO korisnikDTO) {
        if (korisnikDTO != null) {
            String hash = passwordEncoder.encode(korisnikDTO.getLozinka());
            korisnikDTO.setLozinka(hash);
            KorisnikDTO korisnik = korisnikDAO.findById(korisnikDTO.getIdKorisnika()).orElse(null);
            if (korisnik != null) {
                korisnik.setLozinka((hash));
                korisnik.setIme(korisnikDTO.getIme());
                korisnik.setEmail(korisnikDTO.getEmail());
                korisnik.setPrezime(korisnikDTO.getPrezime());
                korisnik.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
                korisnikDAO.save(korisnik);
                return korisnik;
            }
            return null;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteKorisnik(Integer id) {
        KorisnikDTO korisnik = korisnikDAO.findById(id).orElse(null);
        if (korisnik != null) {
            korisnikDAO.delete(korisnik);
            return true;
        }
        return false;
    }
}
