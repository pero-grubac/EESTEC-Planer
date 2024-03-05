package com.eestec.planer.dto;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class LogDTO {
    public LogDTO(int poruka, String subjekat) {
        this.datum = LocalDateTime.now();
        this.idPoruka = poruka;
        this.subjekat = subjekat;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLog")
    private Integer idLog;
    @Column(name = "Datum")
    private LocalDateTime datum;
    @Column(name = "IdPoruka")
    private int idPoruka;
    @Column(name = "Subjekat")
    private String subjekat;
    @Column(name = "IdTIm")
    private Integer tim;

    public LogDTO() {

    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }


    public int getIdPoruka() {
        return idPoruka;
    }

    public void setIdPoruka(int poruka) {
        this.idPoruka = poruka;
    }

    public String getSubjekat() {
        return subjekat;
    }

    public void setSubjekat(String subjekat) {
        this.subjekat = subjekat;
    }

    public Integer getTim() {
        return tim;
    }

    public void setTim(Integer tim) {
        this.tim = tim;
    }
}
