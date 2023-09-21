package com.eestec.planer.service;

import com.eestec.planer.dao.ObjavaDAO;
import com.eestec.planer.dao.SuperUserDAO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.ObjavaDTO;
import com.eestec.planer.dto.SuperUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ObjavaServiceImpl implements ObjavaService {


    private final ObjavaDAO objavaDAO;

    @Autowired
    public ObjavaServiceImpl(ObjavaDAO objavaDAO) {
        this.objavaDAO = objavaDAO;
    }

    /* public ObjavaDTO createNotification(String sadrzaj, Integer idKorisnika) {
             ObjavaDTO objavaDTO = new ObjavaDTO();
         objavaDTO.setSadrazaj(sadrzaj);
         objavaDTO.setVrijemeKreiranja(LocalDateTime.now());

             return objavaDAO.save(objavaDTO);
         }
     }*/
    @Autowired
    private SuperUserDAO superUserDAO;

    public ObjavaDTO createNotification(String sadrzaj, Integer idKorisnika) {


        SuperUserDTO superUserDTO = superUserDAO.findById(idKorisnika).orElse(null);
        if (superUserDTO != null) {

            ObjavaDTO objavaDTO = new ObjavaDTO();
            objavaDTO.setSadrzaj(sadrzaj);
            objavaDTO.setVrijemeKreiranja(LocalDateTime.now());
            objavaDTO.setSuperUser(superUserDTO);
            return objavaDAO.save(objavaDTO);

        }
    return null;
    }

    @Override
    public ObjavaDTO updateObjava(ObjavaDTO objavaDTO) {
        Optional<ObjavaDTO> existingObjavaOptional = objavaDAO.findById(objavaDTO.getIdObjave());

        if (existingObjavaOptional.isPresent()) {
            ObjavaDTO existingObjava = existingObjavaOptional.get();
            existingObjava.setSadrzaj(objavaDTO.getSadrzaj());
            ObjavaDTO updatedObjava = objavaDAO.save(existingObjava);
            return updatedObjava;
        } else { return null; }
    }



    public boolean deleteNotificationById(int notificationId) {
        try {
            objavaDAO.deleteById(notificationId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}









/*
    @Override
    @Transactional
    public boolean deleteNotification(int idObjave) {
        // Prvo proverite da li postoji notifikacija sa datim idObjave
        Optional<ObjavaDTO> notifikacija = objavaDAO.findById(idObjave);

        if (notifikacija.isPresent()) {
            // Ako notifikacija postoji, obrišite je
            ObjavaDAO.deleteNotificationById(idObjave);
            return true;
        } else {
            // Ako notifikacija ne postoji, vratite false
            return false;
        }
    }*/




