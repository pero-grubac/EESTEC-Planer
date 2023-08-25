package com.eestec.P.eestecP.Service;

import com.eestec.P.eestecP.Koordinator.KoordinatorDTO;
import com.eestec.P.eestecP.Korisnik.KorisnikDTO;

import java.util.List;



    public interface KoordinatorService
    {

        List<KoordinatorDTO> getAllKoordinatori();

        KoordinatorDTO getKoordinator(Integer id);

        KoordinatorDTO createKoordinator(KoordinatorDTO koordinatorDTO);

        KoordinatorDTO updateKoordinator(KoordinatorDTO koordinatorDTO);

        boolean deleteKoordinator(Integer id);
    }

