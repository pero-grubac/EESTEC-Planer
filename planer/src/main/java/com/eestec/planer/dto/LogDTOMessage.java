package com.eestec.planer.dto;

import java.time.LocalDateTime;

public class LogDTOMessage {

    private Integer idLog;
    private LocalDateTime datum;
    private String tekstPoruke;
    private String subjekat;


    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public String getTekstPoruke() {
        return tekstPoruke;
    }

    public void setTekstPoruke(String tekstPoruke) {
        this.tekstPoruke = tekstPoruke;
    }

    public String getSubjekat() {
        return subjekat;
    }

    public void setSubjekat(String subjekat) {
        this.subjekat = subjekat;
    }
}
