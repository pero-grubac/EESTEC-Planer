package com.eestec.planer.service;

import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface StatistikaService {

    Integer brojKorisnika();
    Map<KorisnikDTO,Integer> zadaciPoKorisniku();
    Map<KorisnikDTO,Integer> zadaciPoKorisniku(int idTim);
    Map<String,Integer> zadaciPoTimovima();
    Map<String,Integer> zadaciPoTimovima(LocalDateTime pocetakIntervala,LocalDateTime krajIntervala);
    //TODO korisniku grupisanje po necemu
}
