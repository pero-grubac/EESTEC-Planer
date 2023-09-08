package com.eestec.planer.controller;

import com.eestec.planer.controller.util.KorisnikTim;
import com.eestec.planer.dto.KoordinatorDTO;
import com.eestec.planer.dto.SuperUserDTO;
import com.eestec.planer.service.KoordinatorServiceImpl;
import com.eestec.planer.service.KorisnikServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/koordinator")
@CrossOrigin(origins = "http://localhost:3000")
public class KoordinatorController {

    private final KoordinatorServiceImpl koordinatorService;


    @Autowired
    public KoordinatorController(KoordinatorServiceImpl koordinatorService) {
        this.koordinatorService = koordinatorService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<KoordinatorDTO>> getAllKoordinator() {
        List<KoordinatorDTO> koordinatorDTOList = koordinatorService.getAllKoordinatori();
        return ResponseEntity.ok(koordinatorDTOList);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createKoordinator(@RequestParam Integer id) {
        KoordinatorDTO koordinatorDTO = koordinatorService.createKoordinator(id);
        if (koordinatorDTO != null)
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteKoordinator(@PathVariable Integer id) {
        boolean isOK = koordinatorService.deleteKoordinator(id);
        if (isOK) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/addToTeam")
    public ResponseEntity<String> addToTeam(@RequestBody KorisnikTim korisnikTim) {
        if (korisnikTim != null && korisnikTim.getIdKorisnika() != null && korisnikTim.getIdTim() != null) {
            boolean isOK = koordinatorService.addToTeam(korisnikTim.getIdKorisnika(), korisnikTim.getIdTim());
            if (isOK) {
                return ResponseEntity.ok("Uspjesna prijava.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }



}
