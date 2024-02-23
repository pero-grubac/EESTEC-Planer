package com.eestec.planer.service.implementations;

import com.eestec.planer.dao.KategorijaDAO;
import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dao.ZadatakDAO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.ZadatakDTO;
import com.eestec.planer.service.ZadatakService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZadatakServiceImpl implements ZadatakService {

    @Autowired
    private ZadatakDAO zadatakDAO;
    private KorisnikDAO korisnikDAO;
    private KategorijaDAO kategorijaDAO;

    @Override
    public List<ZadatakDTO> getAllZadaci(Byte arc) {
        return zadatakDAO.getZadatakDTOByArhiviran(arc);
    }

    @Override
    @Transactional
    public ZadatakDTO getZadatak(Integer id) {
        return zadatakDAO.findById(id).orElse(null);
    }

    @Autowired
    public ZadatakServiceImpl(ZadatakDAO zadatakDAO, KorisnikDAO korisnikDAO) {
        this.zadatakDAO = zadatakDAO;
        this.korisnikDAO = korisnikDAO;
    }

    public void joinZadatak(Integer korisnikId, Integer zadatakId) {
        // Pronađite korisnika i zadatak po ID-ima
        KorisnikDTO korisnik = korisnikDAO.findById(korisnikId).orElse(null);
        ZadatakDTO zadatak = zadatakDAO.findById(zadatakId).orElse(null);

        if (korisnik != null && zadatak != null) {
            // Dodajte zadatka korisniku i spremiti promjene
            korisnik.getZadaci().add(zadatak);
            korisnikDAO.save(korisnik);
        } else {
            throw new EntityNotFoundException("Korisnik ili zadatak nije pronađen.");
        }
    }

    @Override
    public ZadatakDTO createZadatak(ZadatakDTO zadatakDTO) {
        ZadatakDTO zadatak = new ZadatakDTO();
        zadatak.setTekst(zadatakDTO.getTekst());
        zadatak.setRok(zadatakDTO.getRok());
        zadatak.setNaslov(zadatakDTO.getNaslov());
        zadatak.setIdAutora(zadatakDTO.getIdAutora());

        zadatak.setKategorija(zadatakDTO.getKategorija());

        zadatak.setDatumKreiranja(LocalDateTime.now());
        zadatak = zadatakDAO.save(zadatak);
        return zadatak;
    }


    public List<ZadatakDTO> getZadaciByKategorijaId(int idKategorije) {
        return zadatakDAO.findByKategorija_IdKategorija(idKategorije);
    }

    @Override
    @Transactional
    public ZadatakDTO updateZadatak(ZadatakDTO zadatakDTO) {
        ZadatakDTO zadatak = zadatakDAO.findById(zadatakDTO.getIdZadatak()).orElse(null);
        if (zadatak != null) {
            if (zadatakDTO.getNaslov() != null || !zadatakDTO.getNaslov().isEmpty())
                zadatak.setNaslov(zadatakDTO.getNaslov());

            if (zadatakDTO.getTekst() != null || !zadatakDTO.getTekst().isEmpty())
                zadatak.setTekst(zadatakDTO.getTekst());

            if (zadatakDTO.getRok() != null)
                zadatak.setRok(zadatakDTO.getRok());

            if (zadatakDTO.getKategorija() != null) {
                zadatak.setKategorija(zadatakDTO.getKategorija());
            }
            return zadatak;
        }
        return null;
    }

    @Override
    public boolean deleteZadtak(Integer id) {
        ZadatakDTO zadatak = zadatakDAO.findById(id).orElse(null);
        if (zadatak != null) {
            zadatakDAO.delete(zadatak);
            return true;
        }
        return false;
    }

    @Override
    public List<KorisnikDTO> getKorisniciInTeam(Integer id) {
        return zadatakDAO.userEmails(id);
    }


}
