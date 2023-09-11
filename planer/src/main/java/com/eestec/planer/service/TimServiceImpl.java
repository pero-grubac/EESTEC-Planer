package com.eestec.planer.service;

import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dao.TimDAO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimServiceImpl implements TimService {

    private TimDAO timDAO;
    private KorisnikDAO korisnikDAO;

    @Autowired
    public TimServiceImpl(TimDAO timDAO, KorisnikDAO korisnikDAO) {
        this.timDAO = timDAO;
        this.korisnikDAO = korisnikDAO;
    }

    @Override
    public List<TimDTO> getAllTeams() {
        return timDAO.findAll();
    }

    @Override
    @Transactional
    public TimDTO getTim(Integer id) {
        return timDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public TimDTO createTim(TimDTO timDTO) {
        if (timDAO.save(timDTO) != null)
            return timDTO;
        return null;
    }

    @Override
    public TimDTO updateTim(TimDTO timDTO) {
        if (timDTO != null) {
            timDAO.save(timDTO);
            return timDTO;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteTim(Integer id) {
        TimDTO tim = timDAO.findById(id).orElse(null);
        if (tim != null) {
            timDAO.delete(tim);
            return true;
        }
        return false;
    }
    public  List<KorisnikDTO> getAllByIdTim(Integer idTim){
        return korisnikDAO.getAllByIdTim(idTim);
    }
    @Override
    @Transactional
    public TimDTO getTimByKoordinator(Integer idKoordinator) {
        return timDAO.findFirstByIdKoordinator(idKoordinator);
    }
    @Override
    @Transactional
    public boolean removeIdKoordinator(Integer idKoordinator) {
        TimDTO tim = timDAO.findFirstByIdKoordinator(idKoordinator);
        if(tim != null){
            tim.setIdKoordinator(null);
            timDAO.save(tim);
            return  true;
        }
        return  false;
    }

}
