package com.eestec.planer.service.implementations;

import com.eestec.planer.controller.util.Triple;
import com.eestec.planer.dao.KorisnikDAO;
import com.eestec.planer.dao.TimDAO;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;
import com.eestec.planer.service.StatistikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StatistikaServiceImpl implements StatistikaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private KorisnikDAO korisnikDAO;
    @Autowired
    private TimDAO timDAO;

    @Override
    public Long brojKorisnika() {
        return korisnikDAO.count();
    }

    @Override
    public List<Triple<Optional<KorisnikDTO>, Integer, Long>> mjesecniBrojZadatakaPoKorisniku(Integer year) {
        String query = "SELECT k.IdKorisnika as IdKorisnika, MONTH(z.DatumArhiviranja) AS month,COUNT(*) as broj_zadataka\n" +
                "FROM korisnik k \n" +
                "JOIN korisnik_radi_zadatak krz on k.IdKorisnika=krz.Korisnik_IdKorisnika \n" +
                "JOIN zadatak z on z.IdZadatak=krz.Zadatak_IdZadatak \n" +
                "WHERE z.Arhiviran = 1 and YEAR(z.DatumArhiviranja)= ? \n" +
                "GROUP BY k.KorisnickoIme, MONTH(z.DatumArhiviranja) \n" +
                "ORDER BY  month DESC,k.KorisnickoIme;";
        List<Triple<Optional<KorisnikDTO>, Integer, Long>> resultList = new ArrayList<>();
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query, year);

        for (Map<String, Object> row : results) {
            Optional<KorisnikDTO> korisnik = korisnikDAO.findById((Integer) row.get("IdKorisnika"));
            int mjesec = (int) row.get("month");
            Long brojZadataka = (Long) row.get("broj_zadataka");

            Triple<Optional<KorisnikDTO>, Integer, Long> triple = new Triple<>(korisnik, mjesec, brojZadataka);
            resultList.add(triple);
        }

        return resultList;
    }

    @Override
    public List<Pair<Optional<KorisnikDTO>, Integer>> brojZadatakaPoKorisniku() {
        String query = "SELECT k.IdKorisnika as IdKorisnika ,COUNT(krz.Zadatak_IdZadatak) as broj_zadataka \n" +
                "FROM korisnik k \n" +
                "JOIN korisnik_radi_zadatak krz on k.IdKorisnika=krz.Korisnik_IdKorisnika \n" +
                "JOIN zadatak z on z.IdZadatak=krz.Zadatak_IdZadatak \n" +
                "WHERE z.Arhiviran = 1 \n" +
                "GROUP BY k.IdKorisnika";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
        List<Pair<Optional<KorisnikDTO>, Integer>> resultList = new ArrayList<>();

        for (Map<String, Object> row : results) {
            Optional<KorisnikDTO> korisnikDTO = korisnikDAO.findById((Integer) row.get("IdKorisnika"));

            Integer brojZadataka = ((Long) row.get("broj_zadataka")).intValue();

            Pair<Optional<KorisnikDTO>, Integer> pair = Pair.of(korisnikDTO, brojZadataka);
            resultList.add(pair);
        }

        return resultList;
    }

    @Override
    public List<Triple<Optional<TimDTO>, Integer, Long>> mjesecniBrojZadatakaPoTimu(Integer year) {
        String query = "SELECT t.IdTim as IdTim, MONTH(z.DatumArhiviranja) AS month,COUNT(*) as broj_zadataka \n" +
                "FROM tim t \n" +
                "JOIN korisnik_pripada_timu kpt on t.IdTim=kpt.Tim_IdTim \n" +
                "JOIN korisnik k on kpt.Korisnik_IdKorisnika=k.IdKorisnika \n" +
                "JOIN korisnik_radi_zadatak krz on k.IdKorisnika=krz.Korisnik_IdKorisnika \n" +
                "JOIN zadatak z on krz.Zadatak_IdZadatak=z.IdZadatak \n" +
                "JOIN kategorija kat on  z.IdKategorija=kat.IdKategorija \n" +
                "WHERE z.Arhiviran = 1 and kat.IdTim=t.IdTim and YEAR(z.DatumArhiviranja)= ?\n" +
                "GROUP BY t.IdTim, MONTH(z.DatumArhiviranja) \n" +
                "ORDER BY  month DESC,t.IdTim;";
        List<Triple<Optional<TimDTO>, Integer, Long>> resultList = new ArrayList<>();
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query, year);
        for (Map<String, Object> row : results) {
            Optional<TimDTO> tim = timDAO.findById((Integer) row.get("IdTim"));
            int mjesec = (int) row.get("month");
            Long brojZadataka = (Long) row.get("broj_zadataka");

            Triple<Optional<TimDTO>, Integer, Long> triple = new Triple<>(tim, mjesec, brojZadataka);
            resultList.add(triple);
        }
        return resultList;
    }

    @Override
    public List<Pair<Integer, Integer>> mjesecniBrojZadatakaKorisnika(Integer idKorisnika, Integer godina) {
        String query = "SELECT  MONTH(z.DatumArhiviranja) AS month, COUNT(*) as broj_zadataka \n" +
                "FROM korisnik k \n" +
                "JOIN korisnik_radi_zadatak krz on k.IdKorisnika=krz.Korisnik_IdKorisnika \n" +
                "JOIN zadatak z on z.IdZadatak=krz.Zadatak_IdZadatak \n" +
                "WHERE z.Arhiviran = 1 and k.IdKorisnika=? and YEAR(z.DatumArhiviranja) = ? \n" +
                "GROUP BY MONTH(z.DatumArhiviranja) \n" +
                "ORDER BY  month DESC;";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query,idKorisnika,godina);
        List<Pair<Integer, Integer>> resultList = new ArrayList<>();
        for (Map<String, Object> row : results) {
            Pair<Integer, Integer> pair = Pair.of((Integer) row.get("month"), ((Long) row.get("broj_zadataka")).intValue());
            resultList.add(pair);
        }
        return resultList;
    }

    @Override
    public List<Triple<Optional<KorisnikDTO>,Integer,Integer>> mjesecniBrojZadatakaPoKorisnikuUnutarTima(Integer idTim, Integer godina) {
        String query="SELECT k.IdKorisnika as IdKorisnika,MONTH(z.DatumArhiviranja) AS month,COUNT(*) AS broj_zadataka \n" +
                "FROM korisnik k \n" +
                "JOIN korisnik_radi_zadatak  krt ON k.IdKorisnika = krt.Korisnik_IdKorisnika \n" +
                "JOIN zadatak z ON krt.Zadatak_IdZadatak = z.IdZadatak \n" +
                "JOIN kategorija c ON z.IdKategorija = c.IdKategorija \n" +
                "WHERE z.Arhiviran=1 and c.IdTim = ?  and YEAR(z.DatumArhiviranja)=? \n" +
                "GROUP BY IdKorisnika, MONTH(z.DatumArhiviranja) \n" +
                "ORDER BY IdKorisnika,  month DESC;";
        List<Triple<Optional<KorisnikDTO>, Integer, Integer>> resultList = new ArrayList<>();
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query,idTim, godina);
        for (Map<String, Object> row : results) {
            Optional<KorisnikDTO> korisnik = korisnikDAO.findById((Integer) row.get("IdKorisnika"));
            int mjesec = (int) row.get("month");
            Integer brojZadataka = ((Long) row.get("broj_zadataka")).intValue();

            Triple<Optional<KorisnikDTO>, Integer, Integer> triple = new Triple<>(korisnik, mjesec, brojZadataka);
            resultList.add(triple);
        }
        return resultList;
    }
}
