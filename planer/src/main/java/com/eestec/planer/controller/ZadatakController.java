package com.eestec.planer.controller;


import com.eestec.planer.config.KorisnikInfoDetails;
import com.eestec.planer.controller.util.ElementiTima;
import com.eestec.planer.dao.KategorijaDAO;
import com.eestec.planer.dao.ZadatakDAO;
import com.eestec.planer.dto.*;
import com.eestec.planer.service.EmailService;
import com.eestec.planer.service.LogService;
import com.eestec.planer.service.implementations.EmailServiceImpl;
import com.eestec.planer.service.implementations.KorisnikServiceImpl;
import com.eestec.planer.service.ZadatakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/zadatak")
@CrossOrigin(origins = "http://localhost:3000")
public class ZadatakController {
    @Autowired
    LogService logService;
    @Autowired
    ZadatakService zadatakService;
    @Autowired
    private KategorijaDAO kategorijaDAO;
    @Autowired
    private ZadatakDAO zadatakDAO;
    @Autowired
    private KorisnikServiceImpl korisnikService;

    @Autowired
    private EmailServiceImpl emailService;
    private final Logger logger = LoggerFactory.getLogger(ZadatakController.class);

    @GetMapping("/all/{arc}")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public List<ZadatakDTO> getAllZadaci(@PathVariable Byte arc) {

        return zadatakService.getAllZadaci(arc);

    }


    //  @PreAuthorize("hasAuthority('ROLE_SUPERUSER')")
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<?> kreirajZadatak(@RequestBody ZadatakDTO zadatakDTO, @AuthenticationPrincipal KorisnikInfoDetails korisnikInfoDetails) {
        ZadatakDTO kreiraniZadatak = zadatakService.createZadatak(zadatakDTO);
        if (kreiraniZadatak != null) {
            logService.create(PorukaLoga.KREIRANJE_ZADATKA.getValue(), korisnikInfoDetails.getUsername());
            sendEmails(kreiraniZadatak, "Novi zadatak - ");
            return ResponseEntity.status(HttpStatus.CREATED).body(kreiraniZadatak);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Došlo je do greške prilikom kreiranja zadatka.");
        }
    }

    private void sendEmails(ZadatakDTO kreiraniZadatak, String naslov) {
        List<KorisnikDTO> korisnici = zadatakService.getKorisniciInTeam(kreiraniZadatak.getIdZadatak());
        for (KorisnikDTO korisnik : korisnici) {
            emailService.email(korisnik.getEmail(), korisnik.getKorisnickoIme(), naslov + kreiraniZadatak.getNaslov(), kreiraniZadatak.getTekst());
        }
    }

    @GetMapping("/find/{id}")
    public ZadatakDTO getZadatakById(@PathVariable int id) {
        return zadatakService.getZadatak(id);
    }

    @GetMapping("/byCategory/{idKategorije}")
    public ResponseEntity<List<ZadatakDTO>> getZadaciByKategorijaId(@PathVariable int idKategorije) {
        List<ZadatakDTO> zadaci = zadatakService.getZadaciByKategorijaId(idKategorije);
        return ResponseEntity.ok(zadaci);
    }

    @GetMapping("/byTim/{idTim}")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<ElementiTima> getZadaciByTimId(@PathVariable int idTim) {
        List<ZadatakDTO> zadaci = new ArrayList<>();
        List<KategorijaDTO> kategorije = kategorijaDAO.findByTimDTO_IdTim(idTim);
        for (KategorijaDTO kategorija : kategorije) {
            List<ZadatakDTO> temp = zadatakService.getZadaciByKategorijaId(kategorija.getIdKategorija());
            zadaci.addAll(temp);
        }
        List<KorisnikDTO> sviKorisnici = korisnikService.getAllKorisnici();
        List<KorisnikDTO> korisniciTima = new ArrayList<>();
        int brojKorisnika = 0;
        for (KorisnikDTO korisnikDTO : sviKorisnici) {
            KorisnikDTO tempKorisnik = new KorisnikDTO();

            tempKorisnik.setUloga(korisnikDTO.getUloga());
            tempKorisnik.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
            tempKorisnik.setIme(korisnikDTO.getIme());
            tempKorisnik.setPrezime(korisnikDTO.getPrezime());
            tempKorisnik.setEmail(korisnikDTO.getEmail());
            tempKorisnik.setTimovi(korisnikDTO.getTimovi());
            tempKorisnik.setIdKorisnika(korisnikDTO.getIdKorisnika());
            tempKorisnik.setZadaci(new HashSet<>());


            for (ZadatakDTO zadatak : korisnikDTO.getZadaci()) {
                for (ZadatakDTO zadatakDTO : zadaci) {
                    if (zadatak.getIdZadatak() == zadatakDTO.getIdZadatak()) {
                        tempKorisnik.getZadaci().add(zadatak);
                    }
                }
            }
            if (!tempKorisnik.getZadaci().isEmpty()) {
                korisniciTima.add(tempKorisnik);
            }

            for (TimDTO timDTO : korisnikDTO.getTimovi()) {
                if (timDTO.getIdTim() == idTim) {
                    ++brojKorisnika;
                }
            }
        }

        ElementiTima elementiTima = new ElementiTima();
        elementiTima.setKategorije(kategorije);
        elementiTima.setZadaci(zadaci);
        elementiTima.setKorisnici(korisniciTima);
        elementiTima.setBrojKorisnika(brojKorisnika);

        return ResponseEntity.ok(elementiTima);
    }


    @PostMapping("/update")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<ZadatakDTO> update(@RequestBody ZadatakDTO zadatak, @AuthenticationPrincipal KorisnikInfoDetails korisnikInfoDetails) {
        ZadatakDTO zadatakDTOBeforeUpdate = zadatakService.getZadatak(zadatak.getIdZadatak());
        if (zadatakDTOBeforeUpdate == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Integer idKategorija = zadatakDTOBeforeUpdate.getKategorija().getIdKategorija();
        String tekst = zadatakDTOBeforeUpdate.getTekst();
        String naslov = zadatakDTOBeforeUpdate.getNaslov();
        LocalDateTime rok = zadatakDTOBeforeUpdate.getRok();

        boolean flag = false;
        ZadatakDTO zadatakDTOAfterUpdate = zadatakService.updateZadatak(zadatak);
        // ovjde zadatakDTOAfterUpdate nece nikad biti null, ali necu mijenjati metodu u servisu,
        // posto postoji mogucnost da ce se ista metoda iz servisa koristiti na nekom drugom mjestu
        // tako da ce ovdje biti jedna bespotrebna null provjera
        if (zadatakDTOAfterUpdate != null) {
            if (idKategorija != null && !idKategorija.equals(zadatakDTOAfterUpdate.getKategorija().getIdKategorija())) {
                logService.create(PorukaLoga.PROMJENA_KATEGORIJE_ZADATKA.getValue(), korisnikInfoDetails.getUsername());
                flag = true;
            }
            if ((tekst != null && !tekst.equals(zadatakDTOAfterUpdate.getTekst())) ||
                    (naslov != null && !naslov.equals(zadatakDTOAfterUpdate.getNaslov())) ||
                    (rok != null && !rok.isEqual(zadatakDTOAfterUpdate.getRok()))) {
                logService.create(PorukaLoga.IZMJENA_ZADATKA.getValue(), korisnikInfoDetails.getUsername());
                flag = true;
            }
            if (flag)
                sendEmails(zadatakDTOAfterUpdate, "Ažuriran zadatak - ");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ZadatakDTO zadatak = zadatakService.getZadatak(id);

        if (zadatakService.deleteZadtak(id)) {
            sendEmails(zadatak, "Obrisan zadatak - ");
            return ResponseEntity.ok("Zahtjev s ID-om " + id + " je obrisan.");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/archive/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<List<ZadatakDTO>> archive(@PathVariable int id) {
        zadatakService.archiving(id);
        ZadatakDTO zadatakDTO = zadatakService.getZadatak(id);
        List<ZadatakDTO> zadaci = new ArrayList<>();
        KategorijaDTO kategorijaDTO = kategorijaDAO.findByIdKategorija(zadatakDTO.getKategorija().getIdKategorija());
        List<KategorijaDTO> kategorije = kategorijaDAO.findByTimDTO_IdTim(kategorijaDTO.getTimDTO().getIdTim());

        for (KategorijaDTO kategorija : kategorije) {
            List<ZadatakDTO> temp = zadatakService.getZadaciByKategorijaId(kategorija.getIdKategorija());
            zadaci.addAll(temp);
        }
        return ResponseEntity.ok(zadaci);
    }
}