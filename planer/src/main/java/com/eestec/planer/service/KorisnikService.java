package com.eestec.planer.service;

import com.eestec.planer.controller.util.LoginForm;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;

import java.util.List;

public interface KorisnikService {

    List<KorisnikDTO> getAllKorisnici();

    KorisnikDTO getKorisnik(Integer id);

    KorisnikDTO createKorisnik(KorisnikDTO korisnikDTO);

    KorisnikDTO updateKorisnik(KorisnikDTO korisnikDTO);

    boolean deleteKorisnik(Integer id);

    boolean joinTim(int idKorisnik,int idTim);
    boolean leaveTim(int idKorisnik,int idTim);
    boolean login(LoginForm loginForm);
}
