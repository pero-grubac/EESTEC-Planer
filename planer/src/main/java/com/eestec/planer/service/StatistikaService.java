package com.eestec.planer.service;

import com.eestec.planer.controller.util.Triple;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Optional;


public interface StatistikaService {

    Long brojKorisnika();

    List<Triple<Optional<KorisnikDTO>, Integer, Long>> mjesecniBrojZadatakaPoKorisniku(Integer year);

    List<Pair<Optional<KorisnikDTO>, Integer>> brojZadatakaPoKorisniku();

    List<Triple<Optional<TimDTO>, Integer, Long>> mjesecniBrojZadatakaPoTimu(Integer year);

    List<Pair<Integer, Integer>> mjesecniBrojZadatakaKorisnika(Integer idKorisnika, Integer godina);
    List<Triple<Optional<KorisnikDTO>,Integer,Integer>> mjesecniBrojZadatakaPoKorisnikuUnutarTima(Integer idTim,Integer godina);
}
