package com.eestec.planer.service;
import com.eestec.planer.dto.ZahtjevDTO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ZahtjevService {
    public List<ZahtjevDTO> getAllZahtjevi();

    public void deleteZahtjev(int IdZahtjev);

    public ZahtjevDTO getZahtjevById(int id);

    public ZahtjevDTO addZahtjev(ZahtjevDTO zahtjev);

    public void odobriZahtjev(int id);
}


