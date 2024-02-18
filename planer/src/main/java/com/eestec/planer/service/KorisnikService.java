package com.eestec.planer.service;

import com.eestec.planer.controller.util.KorisnikRequest;
import com.eestec.planer.controller.util.LoginForm;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;

import java.util.List;

public interface KorisnikService {

    List<KorisnikDTO> getAllKorisnici();

    KorisnikDTO getKorisnik(Integer id);

    KorisnikDTO createKorisnik(KorisnikDTO korisnikDTO);

    KorisnikDTO updateKorisnik(KorisnikRequest korisnikRequest);

    boolean deleteKorisnik(Integer id);

    boolean joinTim(int idKorisnik,int idTim);
    boolean leaveTim(int idKorisnik,int idTim);
    KorisnikDTO login(LoginForm loginForm);
    boolean assignTask(Integer idKorisnik,Integer idZadatak);
    boolean dropTask(Integer idKorisnik,Integer idZadatak);
    boolean isDeleted(String username);
}
