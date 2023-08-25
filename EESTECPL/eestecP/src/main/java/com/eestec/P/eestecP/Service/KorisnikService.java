package com.eestec.P.eestecP.Service;



import com.eestec.P.eestecP.Korisnik.KorisnikDTO;

import java.util.List;

public interface KorisnikService {

    List<KorisnikDTO> getAllKorisnici();

    KorisnikDTO getKorisnik(Integer id);

    KorisnikDTO createKorisnik(KorisnikDTO korisnikDTO);

    KorisnikDTO updateKorisnik(KorisnikDTO korisnikDTO);

    boolean deleteKorisnik(Integer id);
}