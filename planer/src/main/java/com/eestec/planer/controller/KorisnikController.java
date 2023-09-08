package com.eestec.planer.controller;

import com.eestec.planer.controller.util.KorisnikTim;
import com.eestec.planer.controller.util.LoginForm;
import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.service.KorisnikServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class KorisnikController {
    private final KorisnikServiceImpl korisnikService;

    @Autowired
    public KorisnikController(KorisnikServiceImpl korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping("/getAll")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<KorisnikDTO>> getAllUsers() {
        List<KorisnikDTO> korisnikDTOList = korisnikService.getAllKorisnici();
        return ResponseEntity.ok(korisnikDTOList);
    }

    @PostMapping("/new")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<KorisnikDTO> createKorisnik(@RequestBody KorisnikDTO korisnikDTO) {
        KorisnikDTO korisnik = korisnikService.createKorisnik(korisnikDTO);
        if (korisnik != null) {
            return ResponseEntity.ok(korisnik);
        } else return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<KorisnikDTO> updateKorisnik(@RequestBody KorisnikDTO korisnikDTO) {
        KorisnikDTO korisnik = korisnikService.updateKorisnik(korisnikDTO);
        if (korisnik != null)
            return ResponseEntity.ok(korisnik);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteKorisnik(@PathVariable Integer id) {
        boolean isOk = korisnikService.deleteKorisnik(id);
        if (isOk) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/joinTeam")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> joinTeam(@RequestBody KorisnikTim korisnikTim) {
        if (korisnikTim != null && korisnikTim.getIdKorisnika() != null && korisnikTim.getIdTim() != null) {
            boolean isOK = korisnikService.joinTim(korisnikTim.getIdKorisnika(), korisnikTim.getIdTim());
            if (isOK) {
                return ResponseEntity.ok("Uspjesna prijava.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/leaveTeam")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> leaveTeam(@RequestBody KorisnikTim korisnikTim) {
        if (korisnikTim != null && korisnikTim.getIdKorisnika() != null && korisnikTim.getIdTim() != null) {
            boolean isOK = korisnikService.leaveTim(korisnikTim.getIdKorisnika(), korisnikTim.getIdTim());
            if (isOK) {
                return ResponseEntity.ok("Dovidjenja.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        if (korisnikService.login(loginForm))
            return ResponseEntity.ok().build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin" + loginForm.getUsername() + " not found");

    }
}
