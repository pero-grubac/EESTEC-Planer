package com.eestec.planer.dto;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class LogDTO {
    public LogDTO( String poruka) {
        this.datum = LocalDateTime.now();
        this.poruka = poruka;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLog")
    private Integer idLog;
    @Column(name = "Datum")
    private LocalDateTime datum;
    @Column(name = "Poruka")
    private String poruka;

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


    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
}
