


package com.eestec.P.eestecP.Service;

import com.eestec.P.eestecP.Zahtjev.Zahtjev;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
    public interface ZahtjevService
    {  public List<Zahtjev> getAllZahtjevi();
        public void deleteZahtjev(int IdZahtjev);
        public Zahtjev getZahtjevById(int id);

        public Zahtjev addZahtjev(Zahtjev zahtjev);
        public void odobriZahtjev(int id);
    }


