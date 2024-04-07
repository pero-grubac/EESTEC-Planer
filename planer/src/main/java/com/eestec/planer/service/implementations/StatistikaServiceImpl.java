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
        String query ="SELECT t.IdTim as IdTim, MONTH(z.DatumArhiviranja) AS month,COUNT(*) as broj_zadataka \n" +
                "FROM tim t \n" +
                " JOIN kategorija kat on  t.IdTim=kat.IdTim \n" +
                " JOIN zadatak z on z.IdKategorija = kat.IdKategorija\n" +
                "   WHERE z.Arhiviran = 1   and YEAR(z.DatumArhiviranja)= ? \n" +
                "    GROUP BY t.IdTim, MONTH(z.DatumArhiviranja) \n" +
                "                ORDER BY  month DESC,t.IdTim;";
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
        String query = "SELECT months.month AS month, IFNULL(COUNT(z.IdZadatak), 0) AS broj_zadataka \n" +
                "FROM ( \n" +
                "    SELECT 1 AS month\n" +
                "    UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 \n" +
                "    UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 \n" +
                "    UNION SELECT 12 \n" +
                ") AS months \n" +
                "LEFT JOIN ( \n" +
                "    SELECT MONTH(z.DatumArhiviranja) AS month, z.IdZadatak \n" +
                "    FROM korisnik k \n" +
                "    JOIN korisnik_radi_zadatak krz ON k.IdKorisnika = krz.Korisnik_IdKorisnika \n" +
                "    JOIN zadatak z ON z.IdZadatak = krz.Zadatak_IdZadatak \n" +
                "    WHERE z.Arhiviran = 1 AND k.IdKorisnika = ? AND YEAR(z.DatumArhiviranja) = ? \n" +
                ") AS z ON months.month = z.month \n" +
                "WHERE months.month <= MONTH(CURDATE()) \n" +
                "GROUP BY months.month \n" +
                "ORDER BY months.month ASC;";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(query,idKorisnika,godina);
        List<Pair<Integer, Integer>> resultList = new ArrayList<>();
        for (Map<String, Object> row : results) {
            Pair<Integer, Integer> pair = Pair.of(((Long) row.get("month")).intValue(), ((Long) row.get("broj_zadataka")).intValue());
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
