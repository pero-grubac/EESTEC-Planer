package com.eestec.planer.service.implementations;

import com.eestec.planer.dao.LogDAO;
import com.eestec.planer.dto.LogDTO;
import com.eestec.planer.dto.LogDTOMessage;
import com.eestec.planer.dto.PorukaLoga;
import com.eestec.planer.dto.TimDTO;
import com.eestec.planer.service.LogService;
import com.eestec.planer.service.TimService;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    private TimService timService;
    private final List<Integer> logsForAdmin = Arrays.asList(PorukaLoga.POKUSAJ_REGISTRACIJE.getValue(),
            PorukaLoga.USPJESNO_REGISTROVAN_NALOG_POTVRDJEN_OD_REGISTRACIJE.getValue(),
            PorukaLoga.EMAIL_NIJE_USPJESNO_POSLAT.getValue());

    private final List<Integer> logsForKoordinator = Arrays.asList(PorukaLoga.PRIJAVA_U_TIM.getValue(), PorukaLoga.PRIJAVA_NA_ZADATAK.getValue(),
            PorukaLoga.PROMJENA_KATEGORIJE_ZADATKA.getValue(), PorukaLoga.IZMJENA_ZADATKA.getValue(),
            PorukaLoga.KREIRANJE_ZADATKA.getValue(), PorukaLoga.ARHIVIRAN_ZADATAK.getValue());

    private final List<Integer> logsForClanOdbora = Arrays.asList(PorukaLoga.PRIJAVA_U_TIM.getValue(), PorukaLoga.PRIJAVA_NA_ZADATAK.getValue(),
            PorukaLoga.PROMJENA_KATEGORIJE_ZADATKA.getValue(), PorukaLoga.IZMJENA_ZADATKA.getValue(), PorukaLoga.KREIRANJE_ZADATKA.getValue(),
            PorukaLoga.ARHIVIRAN_ZADATAK.getValue(), PorukaLoga.PROMJENA_ULOGE.getValue());

    @Autowired
    public LogServiceImpl(LogDAO logDAO) {
        this.logDAO = logDAO;
    }

    @Override
    public void create(int poruka,String subjekat) {
        LogDTO log = new LogDTO(poruka,subjekat);
        logDAO.save(log);
    }

    @Override
    public List<LogDTOMessage> getLogsForAdmin() {
        List<LogDTO> logs = logDAO.findAllByIdPorukaIn(logsForAdmin);
        return mapLogs(logs);
    }

    @Override
    public List<LogDTOMessage> getLogsForAdminBySubject(String subject) {
        List<LogDTO> logs = logDAO.findAllByIdPorukaInAndSubjekat(logsForAdmin, subject);
        return mapLogs(logs);
    }

    @Override
    public List<LogDTOMessage> getLogsForKoordinator(Integer idTim) {
        List<LogDTO> logs = logDAO.findAllByIdPorukaInAndTim(logsForKoordinator,idTim);
        return mapLogs(logs);
    }

    @Override
    public List<LogDTOMessage> getLogsForKoordinatorBySubject(String subject, Integer idTim) {
        List<LogDTO> logs = logDAO.findAllByIdPorukaInAndSubjekatAndTim(logsForKoordinator, subject,idTim);
        return mapLogs(logs);
    }

    @Override
    public List<LogDTOMessage> getLogsForClanOdbora() {
        List<LogDTO> logs =  logDAO.findAllByIdPorukaIn(logsForClanOdbora);
        return mapLogs(logs);
    }

    @Override
    public List<LogDTOMessage> getLogsForClanOdboraBySubject(String subject) {
        List<LogDTO> logs =  logDAO.findAllByIdPorukaInAndSubjekat(logsForClanOdbora, subject);
        return mapLogs(logs);
    }

    private List<LogDTOMessage> mapLogs(List<LogDTO> logs) {
        List<LogDTOMessage> logsWithMessage = new ArrayList<>();
        logs.forEach(log -> {
            LogDTOMessage logWithMessage = new LogDTOMessage();
            logWithMessage.setDatum(log.getDatum());
            logWithMessage.setIdLog((log.getIdLog()));
            logWithMessage.setSubjekat(log.getSubjekat());
            if(log.getTim()!=null){
                TimDTO tim = timService.getTim(log.getTim());
                logWithMessage.setTim(tim);
            }
            // nece ovdje nikad biti null, ali eto
            logWithMessage.setTekstPoruke(PorukaLoga.getByValue(log.getIdPoruka()).toString());
            logsWithMessage.add(logWithMessage);
        });
        return logsWithMessage;
    }
}
