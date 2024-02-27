package com.eestec.planer.service;

import com.eestec.planer.dto.KomentarDTO;

import java.util.List;

public interface KomentarService {
    List<KomentarDTO> getAllByZadatakId(Integer zadatakId);
    List<KomentarDTO> getAllByZadatakIdAndKorisnikId(Integer zadatakId, Integer korisnikId);
    KomentarDTO create(KomentarDTO komentarDTO);
    KomentarDTO update(KomentarDTO komentarDTO);
}
