package com.eestec.planer.controller;

import com.eestec.planer.config.AdminInfoDetails;
import com.eestec.planer.dto.*;
import com.eestec.planer.service.EmailService;
import com.eestec.planer.service.LogService;
import com.eestec.planer.service.implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clanodbora")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class ClanOdboraController {
    private final ClanOdboraServiceImpl clanOdboraService;
    private final SuperUserServiceImpl superUserService;
    private final TimServiceImpl timService;
    private final KoordinatorServiceImpl koordinatorService;
    private final KorisnikServiceImpl korisnikService;
    private final LogService logService;
    private final EmailService emailService;

    @Autowired
    public ClanOdboraController(ClanOdboraServiceImpl clanOdboraService, SuperUserServiceImpl superUserService, TimServiceImpl timService, KoordinatorServiceImpl koordinatorService, KorisnikServiceImpl korisnikService, LogService logService, EmailService emailService) {
        this.clanOdboraService = clanOdboraService;
        this.superUserService = superUserService;
        this.timService = timService;
        this.koordinatorService = koordinatorService;
        this.korisnikService = korisnikService;
        this.logService = logService;
        this.emailService = emailService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClanOdboraDTO>> getAllClanOdbora() {
        List<ClanOdboraDTO> clanOdboraDTOList = clanOdboraService.getAllClanOdbora();
        return ResponseEntity.ok(clanOdboraDTOList);

    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ClanOdboraDTO> createClanOdbora(@RequestParam Integer id, @AuthenticationPrincipal AdminInfoDetails adminInfoDetails) {
        timService.removeIdKoordinator(id);
        koordinatorService.deleteKoordinator(id);
        SuperUserDTO superUserDTO = superUserService.getSuperUser(id);
        if (superUserDTO == null)
            superUserService.createSuperUser(id);
        ClanOdboraDTO clanOdboraDTO = clanOdboraService.createClanOdbora(id);
        List<TimDTO> timovi = timService.getAllTeams();
        for(TimDTO tim : timovi)
            korisnikService.joinTim(id, tim.getIdTim());
        if (clanOdboraDTO != null) {
            KorisnikDTO korisnik = clanOdboraDTO.getSuperuser().getKorisnik();
            List<ClanOdboraDTO> clanoviOdbora = clanOdboraService.getAllClanOdbora();
            clanoviOdbora.forEach(clanOdbora -> {
                KorisnikDTO clanOdboraKorisnik = clanOdbora.getSuperuser().getKorisnik();
                emailService.email(clanOdboraKorisnik.getEmail(),clanOdboraKorisnik.getKorisnickoIme(),
                        "Kreiran je novi član odbora!", "Korisnik sa korisničkim imenom "
                                + korisnik.getKorisnickoIme() + " je novi član odbora.");
            });
            logService.create(PorukaLoga.PROMJENA_ULOGE.getValue(), adminInfoDetails.getUsername());
            return ResponseEntity.ok(clanOdboraDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteClanOdbora(@PathVariable Integer id) {
        boolean isOK = superUserService.deleteSuperUser(id);
        if (isOK) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/logs")
    @PreAuthorize("hasAuthority('Clan odbora')")
    public ResponseEntity<List<LogDTOMessage>> getLogs() {
        return new ResponseEntity<>(logService.getLogsForClanOdbora(), HttpStatus.OK);
    }

    @GetMapping("/logs/{subject}")
    @PreAuthorize("hasAuthority('Clan odbora')")
    public ResponseEntity<List<LogDTOMessage>> getLogsBySubject(@PathVariable String subject) {
        return new ResponseEntity<>(logService.getLogsForClanOdboraBySubject(subject), HttpStatus.OK);
    }
}
