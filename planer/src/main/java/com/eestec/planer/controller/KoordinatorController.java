package com.eestec.planer.controller;

import com.eestec.planer.config.AdminInfoDetails;
import com.eestec.planer.controller.util.KorisnikTim;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/koordinator")
@CrossOrigin(origins = "http://localhost:3000")
public class KoordinatorController {

    private final Logger logger = LoggerFactory.getLogger(KoordinatorController.class);
    private final KoordinatorServiceImpl koordinatorService;
    private final SuperUserServiceImpl superUserService;
    private final TimServiceImpl timService;
    private final ClanOdboraServiceImpl clanOdboraService;
    private final KorisnikServiceImpl korisnikService;
    private final LogService logService;
    private final EmailService emailService;

    @Autowired
    public KoordinatorController(KoordinatorServiceImpl koordinatorService,
                                 SuperUserServiceImpl superUserService,
                                 TimServiceImpl timService, ClanOdboraServiceImpl clanOdboraService,
                                 KorisnikServiceImpl korisnikService, LogService logService, EmailService emailService) {
        this.koordinatorService = koordinatorService;
        this.superUserService = superUserService;
        this.timService = timService;
        this.clanOdboraService = clanOdboraService;
        this.korisnikService = korisnikService;
        this.logService = logService;
        this.emailService = emailService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<KoordinatorDTO>> getAllKoordinator() {
        List<KoordinatorDTO> koordinatorDTOList = koordinatorService.getAllKoordinatori();
        return ResponseEntity.ok(koordinatorDTOList);
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> createKoordinator(@RequestBody KorisnikTim korisnikTim, @AuthenticationPrincipal AdminInfoDetails adminInfoDetails) {

        clanOdboraService.deleteClanOdbora(korisnikTim.getIdKorisnika());
        SuperUserDTO superUserDTO = superUserService.getSuperUser(korisnikTim.getIdKorisnika());
        if (superUserDTO == null)
            superUserService.createSuperUser(korisnikTim.getIdKorisnika());

        KoordinatorDTO koordinatorDTO = koordinatorService.createKoordinator(korisnikTim.getIdKorisnika());
        boolean isOK = koordinatorService.addToTeam(korisnikTim.getIdKorisnika(), korisnikTim.getIdTim());
        korisnikService.joinTim(korisnikTim.getIdKorisnika(), korisnikTim.getIdTim());
        if (koordinatorDTO != null && isOK) {
            KorisnikDTO korisnik = koordinatorDTO.getSuperuser().getKorisnik();
            List<ClanOdboraDTO> clanoviOdbora = clanOdboraService.getAllClanOdbora();
            clanoviOdbora.forEach(clanOdbora -> {
                KorisnikDTO clanOdboraKorisnik = clanOdbora.getSuperuser().getKorisnik();
                emailService.email(clanOdboraKorisnik.getEmail(),clanOdboraKorisnik.getKorisnickoIme(),
                        "Kreiran je novi koordinator!", "Korisnik sa korisničkim imenom "
                                + korisnik.getKorisnickoIme() + " je novi koordinator.");
            });
            logService.create(PorukaLoga.PROMJENA_ULOGE.getValue(), adminInfoDetails.getUsername());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteKoordinator(@RequestBody KorisnikTim korisnikTim) {
        logger.info(korisnikTim.getIdKorisnika()+" "+ korisnikTim.getIdTim());
        timService.removeIdKoordinator(korisnikTim.getIdKorisnika());
        boolean isOK = superUserService.deleteSuperUser(korisnikTim.getIdKorisnika());
        if (isOK) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/addToTeam")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addToTeam(@RequestBody KorisnikTim korisnikTim) {
        if (korisnikTim != null && korisnikTim.getIdKorisnika() != null && korisnikTim.getIdTim() != null) {
            timService.removeIdKoordinator(korisnikTim.getIdKorisnika());
            boolean isOK = koordinatorService.addToTeam(korisnikTim.getIdKorisnika(), korisnikTim.getIdTim());
            if (isOK) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/logs")
    @PreAuthorize("hasAuthority('Koordinator')")
    public ResponseEntity<List<LogDTO>> getLogs() {
        return new ResponseEntity<>(logService.getLogsForKoordinator(), HttpStatus.OK);
    }
}
