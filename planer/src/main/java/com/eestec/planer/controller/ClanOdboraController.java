package com.eestec.planer.controller;

import com.eestec.planer.dto.ClanOdboraDTO;
import com.eestec.planer.dto.SuperUserDTO;
import com.eestec.planer.dto.TimDTO;
import com.eestec.planer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clanodbora")
@CrossOrigin(origins = "http://localhost:3000")
public class ClanOdboraController {
    private final ClanOdboraServiceImpl clanOdboraService;
    private final SuperUserServiceImpl superUserService;
    private final TimServiceImpl timService;
    private final KoordinatorServiceImpl koordinatorService;
    private final KorisnikServiceImpl korisnikService;

    @Autowired
    public ClanOdboraController(ClanOdboraServiceImpl clanOdboraService, SuperUserServiceImpl superUserService, TimServiceImpl timService, KoordinatorServiceImpl koordinatorService, KorisnikServiceImpl korisnikService) {
        this.clanOdboraService = clanOdboraService;
        this.superUserService = superUserService;
        this.timService = timService;
        this.koordinatorService = koordinatorService;
        this.korisnikService = korisnikService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClanOdboraDTO>> getAllClanOdbora() {
        List<ClanOdboraDTO> clanOdboraDTOList = clanOdboraService.getAllClanOdbora();
        return ResponseEntity.ok(clanOdboraDTOList);

    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ClanOdboraDTO> createClanOdbora(@RequestParam Integer id) {
        timService.removeIdKoordinator(id);
        koordinatorService.deleteKoordinator(id);
        SuperUserDTO superUserDTO = superUserService.getSuperUser(id);
        if (superUserDTO == null)
            superUserService.createSuperUser(id);
        ClanOdboraDTO clanOdboraDTO = clanOdboraService.createClanOdbora(id);
        List<TimDTO> timovi = timService.getAllTeams();
        for(TimDTO tim : timovi)
            korisnikService.joinTim(id, tim.getIdTim());
        if (clanOdboraDTO != null)
            return ResponseEntity.ok(clanOdboraDTO);
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
}
