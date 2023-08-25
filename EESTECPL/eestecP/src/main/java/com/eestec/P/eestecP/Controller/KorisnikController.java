package com.eestec.P.eestecP.Controller;

import com.eestec.P.eestecP.Korisnik.KorisnikDTO;
import com.eestec.P.eestecP.Service.KorisnikServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

    @RestController
    @RequestMapping("/user")
    @CrossOrigin(origins = "http://localhost:3000")
    public class KorisnikController
    {
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
        public ResponseEntity<KorisnikDTO> deleteKorisnik(@PathVariable Integer id) {
            boolean isOk = korisnikService.deleteKorisnik(id);
            if (isOk) return ResponseEntity.noContent().build();
            else return ResponseEntity.notFound().build();
        }

    }

