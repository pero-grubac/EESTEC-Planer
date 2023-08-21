package com.example.EestecPlaner.EestecPlaner.Service;

import com.example.EestecPlaner.EestecPlaner.Korisnik.Korisnik;
import com.example.EestecPlaner.EestecPlaner.Korisnik.KorisnikDAO;
import com.example.EestecPlaner.EestecPlaner.Zahtjev.Zahtjev;
import com.example.EestecPlaner.EestecPlaner.Zahtjev.ZahtjevDAO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService
{
    @Autowired
    ZahtjevDAO zahtjevDAO;

   public List<Zahtjev> getAllZahtjevi()
    {
        return zahtjevDAO.findAll();
    }

    public void deleteZahtjev(int IdZahtjev) {
        zahtjevDAO.deleteById(IdZahtjev);
    }
    public Zahtjev getZahtjevbyId(int id) {
        Optional<Zahtjev> optionalZahtjev = zahtjevDAO.findById(id);
        if (optionalZahtjev.isPresent()) {
            return optionalZahtjev.get();
        } else {
            throw new EntityNotFoundException("Zahtjev sa ID " + id + " nije pronađen.");
        }
    }


    public String addZahtjev(Zahtjev zahtjev)
    {
        zahtjevDAO.save(zahtjev);
        return "success";
    }

        @Autowired
        private KorisnikDAO korisnikDAO;
    @Transactional
        public void odobriZahtjev(int id) {
            Zahtjev zahtjev = zahtjevDAO.findById(id).orElse(null);
            if (zahtjev != null) {
                Korisnik korisnik = new Korisnik();
                korisnik.setIme(zahtjev.getIme());
                korisnik.setPrezime(zahtjev.getPrezime());
                korisnik.setKorisnickoIme(zahtjev.getKorisničkoIme());
                korisnik.setLozinka(zahtjev.getLozinka());
                korisnikDAO.save(korisnik);
                zahtjevDAO.delete(zahtjev);
            }
        }
    }


