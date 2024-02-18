package com.eestec.planer.service;

import com.eestec.planer.dto.TimDTO;
import com.eestec.planer.dto.ZadatakDTO;


import java.util.List;

public interface ZadatakService {
    public List<ZadatakDTO> getAllZadaci(Byte arc);

    ZadatakDTO getZadatak(Integer id);

    public void joinZadatak(Integer korisnikId, Integer zadatakId);

    ZadatakDTO createZadatak(ZadatakDTO zadatakDTO);

    public List<ZadatakDTO> getZadaciByKategorijaId(int idKategorije);

    ZadatakDTO updateZadatak(ZadatakDTO zadatakDTO);

    boolean deleteZadtak(Integer id);

    List<String> getEmails(Integer id);
}