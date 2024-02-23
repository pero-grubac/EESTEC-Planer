package com.eestec.planer.service.implementations;

import com.eestec.planer.dao.LogDAO;
import com.eestec.planer.dto.LogDTO;
import com.eestec.planer.dto.PorukaLoga;
import com.eestec.planer.service.LogService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class LogServiceImpl implements LogService {
    private final LogDAO logDAO;

    @Autowired
    public LogServiceImpl(LogDAO logDAO) {
        this.logDAO = logDAO;
    }

    @Override
    public void create(int poruka,String subjekat) {
        LogDTO log = new LogDTO(poruka,subjekat);
        logDAO.save(log);
    }

    public List<LogDTO> getLogsForAdmin() {
        return logDAO.findAllByIdPorukaIn(Arrays.asList(PorukaLoga.POKUSAJ_REGISTRACIJE.getValue(),
                PorukaLoga.USPJESNO_REGISTROVAN_NALOG_POTVRDJEN_OD_REGISTRACIJE.getValue(),
                PorukaLoga.EMAIL_NIJE_USPJESNO_POSLAT.getValue()));
    }

    public List<LogDTO> getLogsForKoordinator() {
        return logDAO.findAllByIdPorukaIn(Arrays.asList(PorukaLoga.PRIJAVA_U_TIM.getValue(), PorukaLoga.PRIJAVA_NA_ZADATAK.getValue(),
                PorukaLoga.PROMJENA_KATEGORIJE_ZADATKA.getValue(), PorukaLoga.IZMJENA_ZADATKA.getValue(),
                PorukaLoga.KREIRANJE_ZADATKA.getValue(), PorukaLoga.ARHIVIRAN_ZADATAK.getValue()));
    }

    public List<LogDTO> getLogsForClanOdbora() {
        return logDAO.findAllByIdPorukaIn(Arrays.asList(PorukaLoga.PRIJAVA_U_TIM.getValue(), PorukaLoga.PRIJAVA_NA_ZADATAK.getValue(),
                PorukaLoga.PROMJENA_KATEGORIJE_ZADATKA.getValue(), PorukaLoga.IZMJENA_ZADATKA.getValue(), PorukaLoga.KREIRANJE_ZADATKA.getValue(),
                PorukaLoga.ARHIVIRAN_ZADATAK.getValue(), PorukaLoga.PROMJENA_ULOGE.getValue()));
    }
}
