package com.eestec.planer.controller;

import com.eestec.planer.controller.util.*;
import com.eestec.planer.dto.*;
import com.eestec.planer.service.*;
import com.eestec.planer.service.implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class KorisnikController {
    @Autowired
    private LogServiceImpl logService;
    private final KorisnikServiceImpl korisnikService;
    private final KoordinatorServiceImpl koordinatorService;
    private final ClanOdboraServiceImpl clanOdboraService;
    private final TimService timService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    public KorisnikController(KorisnikServiceImpl korisnikService, KoordinatorServiceImpl koordinatorService, ClanOdboraServiceImpl clanOdboraService, TimService timService) {
        this.korisnikService = korisnikService;
        this.koordinatorService = koordinatorService;
        this.clanOdboraService = clanOdboraService;
        this.timService = timService;
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<KorisnikDTO>> getAllUsers() {
        List<KorisnikDTO> korisnikDTOList = korisnikService.getAllKorisnici();
        List<KoordinatorDTO> koordinatorDTOList = koordinatorService.getAllKoordinatori();
        List<ClanOdboraDTO> clanOdboraDTOList = clanOdboraService.getAllClanOdbora();

        for (KorisnikDTO korisnikDTO : korisnikDTOList) {
            for (KoordinatorDTO koordinatorDTO : koordinatorDTOList) {
                if (korisnikDTO.getIdKorisnika() == koordinatorDTO.getIdKoordinator()) {

                    korisnikDTO.setUloga(koordinatorDTO.getUloga());
                }
            }
            for (ClanOdboraDTO clanOdboraDTO : clanOdboraDTOList)
                if (korisnikDTO.getIdKorisnika() == clanOdboraDTO.getIdClana())
                    korisnikDTO.setUloga(clanOdboraDTO.getRole());
        }
        return ResponseEntity.ok(korisnikDTOList);
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<KorisnikDTO> getKorisnik(@PathVariable Integer id) {
        KorisnikDTO korisnik = korisnikService.getKorisnik(id);
        if (korisnik != null)
            return ResponseEntity.ok(korisnik);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<KorisnikDTO> createKorisnik(@RequestBody KorisnikDTO korisnikDTO) {
        KorisnikDTO korisnik = korisnikService.createKorisnik(korisnikDTO);
        if (korisnik != null) {
            emailService.email(korisnik.getEmail(),korisnik.getKorisnickoIme(), "EESTEC Planer", "Dobrodošli u EESTEC Planer");
            return ResponseEntity.ok(korisnik);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<?> updateKorisnik(@RequestBody KorisnikRequest korisnikRequest) {
        KorisnikDTO korisnik = korisnikService.updateKorisnik(korisnikRequest);
        if (korisnik != null) {

            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteKorisnik(@PathVariable Integer id) {
        boolean isOk = korisnikService.deleteKorisnik(id);
        if (isOk) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/joinTeam")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<String> joinTeam(@RequestBody KorisnikTim korisnikTim) {
        if (korisnikTim != null && korisnikTim.getIdKorisnika() != null && korisnikTim.getIdTim() != null) {
            KorisnikDTO korisnik = korisnikService.joinTim(korisnikTim.getIdKorisnika(), korisnikTim.getIdTim());
            if (korisnik != null) {
                TimDTO tim = timService.getTim(korisnikTim.getIdTim());
                KoordinatorDTO koordinator = koordinatorService.getKoordinator(tim.getIdKoordinator());
                if (koordinator != null) {
                    KorisnikDTO koordinatorKorisnik = koordinator.getSuperuser().getKorisnik();
                    emailService.email(koordinatorKorisnik.getEmail(),koordinatorKorisnik.getKorisnickoIme(),
                            "Korisnik se pridružio Vašem timu", "Korisnik sa korisničkim imenom "
                                    + korisnik.getKorisnickoIme() + " se pridružio Vašem timu " + tim.getNaziv() + ".");
                }
                logService.create(PorukaLoga.PRIJAVA_U_TIM.getValue(), korisnik.getKorisnickoIme());
                return ResponseEntity.ok("Uspjesna prijava.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/leaveTeam")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<?> leaveTeam(@RequestBody KorisnikTim korisnikTim) {
        if (korisnikTim != null && korisnikTim.getIdKorisnika() != null && korisnikTim.getIdTim() != null) {
            boolean isOK = korisnikService.leaveTim(korisnikTim.getIdKorisnika(), korisnikTim.getIdTim());
            if (isOK) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        if (!korisnikService.isDeleted(loginForm.getUsername())) {
            try {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getLozinka()));

                KorisnikDTO korisnik = korisnikService.login(loginForm);

                if (korisnik != null) {
                    if (authentication.isAuthenticated()) {
                        List<KoordinatorDTO> koordinatorDTOList = koordinatorService.getAllKoordinatori();
                        List<ClanOdboraDTO> clanOdboraDTOList = clanOdboraService.getAllClanOdbora();

                        for (KoordinatorDTO koordinator : koordinatorDTOList)
                            if (koordinator.getIdKoordinator() == korisnik.getIdKorisnika())
                                korisnik.setUloga("Koordinator");
                        for (ClanOdboraDTO clanOdboraDTO : clanOdboraDTOList)
                            if (clanOdboraDTO.getIdClana() == korisnik.getIdKorisnika())
                                korisnik.setUloga("Clan odbora");
                        AuthResponse response = new AuthResponse();
                        response.setKorisnik(korisnik);
                        response.setToken(jwtService.generateToken(loginForm.getUsername()));
                        logService.create(PorukaLoga.USPJESNA_PRIJAVA.getValue(),loginForm.getUsername());
                        return ResponseEntity.ok(response);
                    }
                }
                logService.create(PorukaLoga.NEUSPJESNA_PRIJAVA.getValue(),loginForm.getUsername());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + loginForm.getUsername() + " not found");
            } catch (Exception e) {
                logService.create(PorukaLoga.NEUSPJESNA_PRIJAVA.getValue(),loginForm.getUsername());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + loginForm.getUsername() + " not found");
            }
        }
        logService.create(PorukaLoga.NEUSPJESNA_PRIJAVA.getValue(),loginForm.getUsername());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + loginForm.getUsername() + " not found");
    }

    @PutMapping("/assign")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<String> assign(@RequestBody KorisnikZadatak korisnikZadatak) {
        if (korisnikZadatak != null && korisnikZadatak.getIdKorisnika() != null && korisnikZadatak.getIdZadatak() != null) {
            KorisnikDTO korisnik = korisnikService.assignTask(korisnikZadatak.getIdKorisnika(), korisnikZadatak.getIdZadatak());
            if (korisnik != null) {
                logService.create(PorukaLoga.PRIJAVA_NA_ZADATAK.getValue(),korisnik.getKorisnickoIme());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/drop")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<String> drop(@RequestBody KorisnikZadatak korisnikZadatak) {
        if (korisnikZadatak != null && korisnikZadatak.getIdKorisnika() != null && korisnikZadatak.getIdZadatak() != null) {
            boolean isOK = korisnikService.dropTask(korisnikZadatak.getIdKorisnika(), korisnikZadatak.getIdZadatak());
            if (isOK) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
