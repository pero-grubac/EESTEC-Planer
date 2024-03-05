package com.eestec.planer.service;

import com.eestec.planer.dto.LogDTO;
import com.eestec.planer.dto.LogDTOMessage;

import java.util.List;

public interface LogService {

    void create(int poruka,String subjekat);
    public List<LogDTOMessage> getLogsForAdmin();
    public List<LogDTOMessage> getLogsForAdminBySubject(String subject);
    public List<LogDTOMessage> getLogsForKoordinator(Integer idTim);
    public List<LogDTOMessage> getLogsForKoordinatorBySubject(String subject,Integer idTim);
    public List<LogDTOMessage> getLogsForClanOdbora();
    public List<LogDTOMessage> getLogsForClanOdboraBySubject(String subject);
}
