package com.eestec.planer.service;

import com.eestec.planer.dto.LogDTO;

import java.util.List;

public interface LogService {

    void create(int poruka,String subjekat);
    public List<LogDTO> getLogsForAdmin();
    public List<LogDTO> getLogsForKoordinator();
    public List<LogDTO> getLogsForClanOdbora();
}
