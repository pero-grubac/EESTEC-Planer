package com.eestec.planer.controller;

import com.eestec.planer.controller.util.KorisnikTim;
import com.eestec.planer.dto.ClanOdboraDTO;
import com.eestec.planer.dto.KoordinatorDTO;
import com.eestec.planer.dto.SuperUserDTO;
import com.eestec.planer.dto.TimDTO;
import com.eestec.planer.service.ClanOdboraServiceImpl;
import com.eestec.planer.service.KoordinatorServiceImpl;
import com.eestec.planer.service.SuperUserServiceImpl;
import com.eestec.planer.service.TimServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/koordinator")
@CrossOrigin(origins = "http://localhost:3000")
public class KoordinatorController {

    private final KoordinatorServiceImpl koordinatorService;
    private final SuperUserServiceImpl superUserService;
    private final TimServiceImpl timService;
    private final ClanOdboraServiceImpl clanOdboraService;

    @Autowired
    public KoordinatorController(KoordinatorServiceImpl koordinatorService,
                                 SuperUserServiceImpl superUserService,
                                 TimServiceImpl timService, ClanOdboraServiceImpl clanOdboraService) {
        this.koordinatorService = koordinatorService;
        this.superUserService = superUserService;
        this.timService = timService;
        this.clanOdboraService = clanOdboraService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<KoordinatorDTO>> getAllKoordinator() {
        List<KoordinatorDTO> koordinatorDTOList = koordinatorService.getAllKoordinatori();
        return ResponseEntity.ok(koordinatorDTOList);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createKoordinator(@RequestBody KorisnikTim korisnikTim) {

        clanOdboraService.deleteClanOdbora(korisnikTim.getIdKorisnika());
        SuperUserDTO superUserDTO = superUserService.getSuperUser(korisnikTim.getIdKorisnika());
        if (superUserDTO == null)
            superUserService.createSuperUser(korisnikTim.getIdKorisnika());
        KoordinatorDTO koordinatorDTO = koordinatorService.createKoordinator(korisnikTim.getIdKorisnika());
        boolean isOK = koordinatorService.addToTeam(korisnikTim.getIdKorisnika(), korisnikTim.getIdTim());
        if (koordinatorDTO != null && isOK)
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteKoordinator(@RequestBody KorisnikTim korisnikTim) {
       // timService.removeIdKoordinator(korisnikTim.getIdKorisnika());
        boolean isOK = superUserService.deleteSuperUser(korisnikTim.getIdKorisnika());
        if (isOK) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/addToTeam")
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


}
