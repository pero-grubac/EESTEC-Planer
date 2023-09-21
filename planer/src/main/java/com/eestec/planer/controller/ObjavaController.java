package com.eestec.planer.controller;

import com.eestec.planer.dto.ObjavaDTO;
import com.eestec.planer.service.ObjavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/notifications")
public class ObjavaController {

    private final ObjavaService objavaService;

    @Autowired
    public ObjavaController(ObjavaService objavaService) {
        this.objavaService = objavaService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createNotification(@RequestBody Map<String, Object> requestBody) {
        String message = (String) requestBody.get("message");
        Integer idKorisnika = (Integer) requestBody.get("idKorisnika");

        if (message != null && idKorisnika != null) {
            ObjavaDTO objavaDTO = objavaService.createNotification(message, idKorisnika);
            if (objavaDTO != null) {
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }





    // Endpoint za brisanje notifikacije po ID-u
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable("notificationId") int notificationId) {
        boolean deleted = objavaService.deleteNotificationById(notificationId);

        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ObjavaDTO> updateObjava(@RequestBody ObjavaDTO objavaDTO) {
        ObjavaDTO updatedObjava = objavaService.updateObjava(objavaDTO);

        if (updatedObjava != null) {
            return ResponseEntity.ok(updatedObjava);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


/*
    @PostMapping("/create")
    public ResponseEntity<ObjavaDTO> createNotification(@RequestParam String message) {
        // Dohvati trenutno prijavljenog korisnika iz Spring Security konteksta
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Provjeri je li trenutno prijavljeni korisnik super korisnik
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_SUPER_USER"))) {
            ObjavaDTO createdNotification = objavaService.createNotification(message);
            return ResponseEntity.ok(createdNotification);
        } else {
            // Ako trenutno prijavljeni korisnik nije super korisnik, vrati odgovarajući odgovor ili bacite iznimku
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // HTTP 403 Forbidden
        }
    }*/
}