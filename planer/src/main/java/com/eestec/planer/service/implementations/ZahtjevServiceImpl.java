package com.eestec.planer.service.implementations;


import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dao.ZahtjevDAO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.ZahtjevDTO;
import com.eestec.planer.service.ZahtjevService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ZahtjevServiceImpl implements ZahtjevService {

// TO DO
    // Nedostaje kreiranje zahtjeva
    // sifrovanje vidi kako je u nekome servisu sto sam ja pravio sa password encoder
    @Autowired
    private ZahtjevDAO zahtjevDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<ZahtjevDTO> getAllZahtjevi() {
        return zahtjevDAO.findAll();
    }

    @Override
    public boolean deleteZahtjev(Integer IdZahtjev) {
        ZahtjevDTO zahtev = zahtjevDAO.findById(IdZahtjev).orElse(null);
        if (zahtev != null) {
            zahtjevDAO.deleteById(IdZahtjev);
            return true;
        }
        return false;
    }

    @Override
    public ZahtjevDTO getZahtjevById(int id) {
        Optional<ZahtjevDTO> zahtjevOptional = zahtjevDAO.findById(id);

        if (zahtjevOptional.isPresent()) {
            return zahtjevOptional.get();
        } else {
            throw new EntityNotFoundException("Zahtjev with id " + id + " not found");
        }
    }


    @Autowired
    private KorisnikDAO korisnikDAO;

    @Transactional
    @Override
    public ZahtjevDTO odobriZahtjev(int id) {
        ZahtjevDTO zahtjev = zahtjevDAO.findById(id).orElse(null);
        if (zahtjev != null) {
            KorisnikDTO korisnik = new KorisnikDTO();
            korisnik.setIme(zahtjev.getIme());
            korisnik.setPrezime(zahtjev.getPrezime());
            korisnik.setKorisnickoIme(zahtjev.getKorisnickoIme());
            korisnik.setLozinka(zahtjev.getLozinka());
            korisnik.setEmail(zahtjev.getEmail());
            korisnikDAO.save(korisnik);
            zahtjevDAO.delete(zahtjev);
            return zahtjev;
        }
        return null;
    }

    @Override
    public ZahtjevDTO addZahtjev(ZahtjevDTO zahtjev) {
        if (zahtjev == null || zahtjev.getIme() == null || zahtjev.getIme().isEmpty()) {
            throw new IllegalArgumentException("Ime ne može biti prazno.");
        }

        zahtjev.setLozinka(passwordEncoder.encode(zahtjev.getLozinka()));
        // Save the Zahtjev entity
        if( zahtjevDAO.save(zahtjev)!=null)
            return  zahtjev;
        else  return  null;
    }

}
