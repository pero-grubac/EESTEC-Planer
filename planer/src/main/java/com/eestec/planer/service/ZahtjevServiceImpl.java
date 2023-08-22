package com.eestec.planer.service;


import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dao.ZahtjevDAO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.ZahtjevDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ZahtjevServiceImpl implements ZahtjevService {


    @Autowired
    private ZahtjevDAO zahtjevDAO;

    @Override
    public List<ZahtjevDTO> getAllZahtjevi() {
        return zahtjevDAO.findAll();
    }

    @Override
    public void deleteZahtjev(int IdZahtjev) {
        zahtjevDAO.deleteById(IdZahtjev);
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
    public void odobriZahtjev(int id) {
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
        }
    }

    @Override
    public ZahtjevDTO addZahtjev(ZahtjevDTO zahtjev) {
        if (zahtjev == null || zahtjev.getIme() == null || zahtjev.getIme().isEmpty()) {
            throw new IllegalArgumentException("Ime ne može biti prazno.");
        }

        // Save the Zahtjev entity
        return zahtjevDAO.save(zahtjev);
    }

}
