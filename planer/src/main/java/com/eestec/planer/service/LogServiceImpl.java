package com.eestec.planer.service;

import com.eestec.planer.dao.LogDAO;
import com.eestec.planer.dto.LogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    private final LogDAO logDAO;

    @Autowired
    public LogServiceImpl(LogDAO logDAO) {
        this.logDAO = logDAO;
    }

    @Override
    public void create( String poruka) {
        LogDTO log = new LogDTO(poruka);
        logDAO.save(log);
    }
}
