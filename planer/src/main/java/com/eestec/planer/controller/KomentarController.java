package com.eestec.planer.controller;

import com.eestec.planer.dto.KomentarDTO;
import com.eestec.planer.service.KomentarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class KomentarController {

    private final KomentarService komentarService;

    public KomentarController(KomentarService komentarService) {
        this.komentarService = komentarService;
    }

    @GetMapping("/{zadatakId}")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    ResponseEntity<List<KomentarDTO>> getAllByZadatakId(@PathVariable Integer zadatakId) {
        return new ResponseEntity<>(komentarService.getAllByZadatakId(zadatakId), HttpStatus.OK);
    }

    @GetMapping("/{zadatakId}/{korisnikId}")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    ResponseEntity<List<KomentarDTO>> getAllByZadatakIdAndKorisnikId(@PathVariable Integer zadatakId, @PathVariable Integer korisnikId) {
        return new ResponseEntity<>(komentarService.getAllByZadatakIdAndKorisnikId(zadatakId, korisnikId), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    ResponseEntity<KomentarDTO> create(@RequestBody KomentarDTO komentarDTO) {
        KomentarDTO result = komentarService.create(komentarDTO);
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    ResponseEntity<KomentarDTO> update(@RequestBody KomentarDTO komentarDTO) {
        KomentarDTO result = komentarService.update(komentarDTO);
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
