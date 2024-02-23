package com.eestec.planer.service.implementations;

import com.eestec.planer.dao.KomentarDAO;
import com.eestec.planer.dto.KomentarDTO;
import com.eestec.planer.service.KomentarService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class KomentarServiceImpl implements KomentarService {

    private final KomentarDAO komentarDAO;

    public KomentarServiceImpl(KomentarDAO komentarDAO) {
        this.komentarDAO = komentarDAO;
    }

    @Override
    public List<KomentarDTO> getAllByZadatakId(Integer zadatakId) {
        return komentarDAO.findAllByIdZadatak(zadatakId);
    }

    @Override
    public List<KomentarDTO> getAllByZadatakIdAndKorisnikId(Integer zadatakId, Integer korisnikId) {
        return komentarDAO.findAllByIdZadatakAndIdKorisnik(zadatakId, korisnikId);
    }

    @Override
    public KomentarDTO create(KomentarDTO komentarDTO) {
        komentarDTO.setDatum(LocalDateTime.now());
        return update(komentarDTO);
    }

    @Override
    public KomentarDTO update(KomentarDTO komentarDTO) {
        return komentarDAO.save(komentarDTO);
    }
}
