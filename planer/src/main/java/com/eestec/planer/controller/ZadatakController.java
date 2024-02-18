package com.eestec.planer.controller;


import com.eestec.planer.controller.util.ElementiTima;
import com.eestec.planer.dao.KategorijaDAO;
import com.eestec.planer.dao.ZadatakDAO;
import com.eestec.planer.dto.*;
import com.eestec.planer.service.EmailServiceImpl;
import com.eestec.planer.service.KorisnikServiceImpl;
import com.eestec.planer.service.ZadatakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/zadatak")
@CrossOrigin(origins = "http://localhost:3000")
public class ZadatakController {
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
    public ResponseEntity<?> kreirajZadatak(@RequestBody ZadatakDTO zadatakDTO) {
        ZadatakDTO kreiraniZadatak = zadatakService.createZadatak(zadatakDTO);

        sendEmails(kreiraniZadatak, "Novi zadatak - ");

        if (kreiraniZadatak != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(kreiraniZadatak);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Došlo je do greške prilikom kreiranja zadatka.");
        }
    }

    private void sendEmails(ZadatakDTO kreiraniZadatak, String naslov) {
        List<String> emails = zadatakService.getEmails(kreiraniZadatak.getIdZadatak());
        for (String email : emails) {
            emailService.email(email, naslov + kreiraniZadatak.getNaslov(), kreiraniZadatak.getTekst());
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
    public ResponseEntity<ZadatakDTO> update(@RequestBody ZadatakDTO zadatak) {
        ZadatakDTO zadatakDTO = zadatakService.updateZadatak(zadatak);
        sendEmails(zadatakDTO, "Ažuriran zadatak - ");
        if (zadatakDTO != null)
            return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ZadatakDTO zadatak = zadatakService.getZadatak(id);
        sendEmails(zadatak, "Obrisan zadatak - ");
        if (zadatakService.deleteZadtak(id)) {
            return ResponseEntity.ok("Zahtjev s ID-om " + id + " je obrisan.");
        }
        return ResponseEntity.notFound().build();
    }


}