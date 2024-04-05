package com.eestec.planer.controller;


import com.eestec.planer.dto.PorukaLoga;
import com.eestec.planer.dto.ZahtjevDTO;
import com.eestec.planer.service.EmailService;
import com.eestec.planer.service.LogService;
import com.eestec.planer.service.ZahtjevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:3000")
public class ZahtjevController {
    @Autowired
    ZahtjevService zahtjevService;
    @Autowired
    LogService logService;
    @Autowired
    private EmailService emailService;
    private final Logger logger = LoggerFactory.getLogger(ZahtjevController.class);

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<ZahtjevDTO> getAllQuestions() {

        return zahtjevService.getAllZahtjevi();

    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getDeleteZahtjev(@PathVariable int id) {
        boolean result = zahtjevService.deleteZahtjev(id);
        if (result)
            return ResponseEntity.ok("Zahtjev s ID-om " + id + " je obrisan.");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ZahtjevDTO getZahtjevById(@PathVariable int id) {
        return zahtjevService.getZahtjevById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addZahtjev(@RequestBody ZahtjevDTO zahtjev) {
        logger.info(" " + zahtjev.toString());
        ZahtjevDTO zahtjevDTO = zahtjevService.addZahtjev(zahtjev);
        if (zahtjevDTO != null) {
            logService.create(PorukaLoga.POKUSAJ_REGISTRACIJE.getValue(), zahtjev.getKorisnickoIme());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/approve/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> approveRequest(@PathVariable int id) {
        ZahtjevDTO zahtjev = zahtjevService.odobriZahtjev(id);
        if (zahtjev != null) {
            emailService.email(zahtjev.getEmail(), zahtjev.getKorisnickoIme(), "EESTEC Planer", "Dobrodošli u EESTEC Planer");
            logService.create(PorukaLoga.USPJESNO_REGISTROVAN_NALOG_POTVRDJEN_OD_REGISTRACIJE.getValue(), zahtjev.getKorisnickoIme());
            return ResponseEntity.ok("Zahtjev s ID-om " + id + " je odobren.");
        } else return ResponseEntity.notFound().build();
    }


}